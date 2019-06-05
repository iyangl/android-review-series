/*
 * Copyright (C) 2016 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package okhttp3.internal.http

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.EMPTY_RESPONSE
import okio.buffer
import java.io.IOException
import java.net.ProtocolException

/**
 *  This is the last interceptor in the chain. It makes a network call to the server.
 *  链中最后一个拦截器。向服务器进行网络调用。
 */
class CallServerInterceptor(private val forWebSocket: Boolean) : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val realChain = chain as RealInterceptorChain
    val exchange = realChain.exchange()
    val request = realChain.request()
    val requestBody = request.body()
    val sentRequestMillis = System.currentTimeMillis()

    // codec 写入请求头
    exchange.writeRequestHeaders(request)

    var responseHeadersStarted = false
    var responseBuilder: Response.Builder? = null
    // 非 GET、HEAD 请求，codec 写入请求体
    if (HttpMethod.permitsRequestBody(request.method()) && requestBody != null) {
      // If there's a "Expect: 100-continue" header on the request, wait for a "HTTP/1.1 100
      // Continue" response before transmitting the request body. If we don't get that, return
      // what we did get (such as a 4xx response) without ever transmitting the request body.
      // expect 100 continue header报文，是由客户端对http的post和get的请求策略决定的，
      // 目的是为了避免浪费资源，如带宽，数据传输消耗的时间等等。
      // 所以客户端会在发送header的时候添加expect 100去探探路，如果失败了就不用继续发送data，
      // 从而减少了资源的浪费。
      // https://www.jianshu.com/p/e1fa8eca79eb
      // https://blog.csdn.net/zerooffdate/article/details/81513717
      // https://www.oschina.net/news/77354/http-get-post-different?p=1  评论部分
      // 请求头中含有 100-continue，需要等待 HTTP / 1.1 100 Continue 响应
      if ("100-continue".equals(request.header("Expect"), ignoreCase = true)) {
        // 刷新请求到底层socket
        exchange.flushRequest()
        // 等待请求头响应
        responseHeadersStarted = true
        exchange.responseHeadersStart()
        responseBuilder = exchange.readResponseHeaders(true)
      }
      if (responseBuilder == null) {
        if (requestBody.isDuplex()) {
          // Prepare a duplex body so that the application can send a request body later.
          // 双工请求先刷新请求？
          exchange.flushRequest()
          val bufferedRequestBody = exchange.createRequestBody(request, true).buffer()
          requestBody.writeTo(bufferedRequestBody)
          // 双工为什么不在写入完成后，关闭流
        } else {
          // Write the request body if the "Expect: 100-continue" expectation was met.
          val bufferedRequestBody = exchange.createRequestBody(request, false).buffer()
          requestBody.writeTo(bufferedRequestBody)
          bufferedRequestBody.close()
        }
      } else {
        exchange.noRequestBody()
        if (!exchange.connection()!!.isMultiplexed) {
          // If the "Expect: 100-continue" expectation wasn't met, prevent the HTTP/1 connection
          // from being reused. Otherwise we're still obligated to transmit the request body to
          // leave the connection in a consistent state.
          exchange.noNewExchangesOnConnection()
        }
      }
    } else {
      exchange.noRequestBody()
    }

    if (requestBody == null || !requestBody.isDuplex()) {
      exchange.finishRequest()
    }
    if (!responseHeadersStarted) {
      exchange.responseHeadersStart()
    }
    if (responseBuilder == null) {
      responseBuilder = exchange.readResponseHeaders(false)!!
    }
    // 请求
    var response = responseBuilder
        .request(request)
        .handshake(exchange.connection()!!.handshake())
        .sentRequestAtMillis(sentRequestMillis)
        .receivedResponseAtMillis(System.currentTimeMillis())
        .build()
    var code = response.code()
    // 收到服务器发送的 100-continue 应答，重新进行真正的请求
    if (code == 100) {
      // server sent a 100-continue even though we did not request one.
      // try again to read the actual response
      response = exchange.readResponseHeaders(false)!!
          .request(request)
          .handshake(exchange.connection()!!.handshake())
          .sentRequestAtMillis(sentRequestMillis)
          .receivedResponseAtMillis(System.currentTimeMillis())
          .build()
      code = response.code()
    }

    exchange.responseHeadersEnd(response)

    response = if (forWebSocket && code == 101) {
      // Connection is upgrading, but we need to ensure interceptors see a non-null response body.
      // WebSocket 成功建立会返回101，暂不关注
      response.newBuilder()
          .body(EMPTY_RESPONSE)
          .build()
    } else {
      response.newBuilder()
          .body(exchange.openResponseBody(response))
          .build()
    }
    if ("close".equals(response.request().header("Connection"), ignoreCase = true) ||
        "close".equals(response.header("Connection"), ignoreCase = true)) {
      exchange.noNewExchangesOnConnection()
    }
    if ((code == 204 || code == 205) && response.body()?.contentLength() ?: -1L > 0L) {
      throw ProtocolException(
          "HTTP $code had non-zero Content-Length: ${response.body()?.contentLength()}")
    }
    return response
  }
}
