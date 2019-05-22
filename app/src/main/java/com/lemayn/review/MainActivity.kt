package com.lemayn.review

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    @Test
    fun test() {
        val response = OkHttpClient.Builder().build().newCall(Request.Builder().url("http://www.baidu.com").build()).execute()
        println(String(response.body()!!.bytes()))
    }
}
