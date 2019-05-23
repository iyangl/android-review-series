package com.lemayn.review

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
}
