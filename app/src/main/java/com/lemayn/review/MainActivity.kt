package com.lemayn.review

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Retrofit.Builder().baseUrl("http://www.baidu.com").build().create(Service::class.java).baidu().execute()
    }
}
