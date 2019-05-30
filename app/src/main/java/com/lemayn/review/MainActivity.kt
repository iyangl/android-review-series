package com.lemayn.review

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread(Runnable { test2() }).start()
    }

    @Test
    fun test() {
        val response = OkHttpClient.Builder().build().newCall(Request.Builder().url("http://www.baidu.com").build()).execute()
        println(Thread.currentThread())
        println(String(response.body()!!.bytes()))
    }


    @Test
    fun test2() {
        val call = OkHttpClient.Builder().build().newCall(Request.Builder().url("https://www.baidu.com").build())
        try {
            val response = call.execute()
            println(response.code())
            println(response.request().url())
        } catch (e: IOException) {
            e.printStackTrace()
        }

        call.clone().enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, rawResponse: okhttp3.Response) {
                println(rawResponse.code())
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println(e.message)
            }
        })
    }

    @Test
    fun test3() {
        println(hostHeader(Request.Builder().url("http://192.168.0.1:8080").build().url(), true))
    }

    fun hostHeader(url: HttpUrl, includeDefaultPort: Boolean): String {
        val host = if (":" in url.host()) {
            "[${url.host()}]"
        } else {
            url.host()
        }
        println("${":" in url.host()}------")
        println("$host------")
        return if (includeDefaultPort || url.port() != HttpUrl.defaultPort(url.scheme())) {
            "$host:${url.port()}"
        } else {
            host
        }
    }

    @Test
    fun test4() {
        println(Headers.Builder().build().size().toString())
        val a = null
        checkNotNull(a)
    }
}
