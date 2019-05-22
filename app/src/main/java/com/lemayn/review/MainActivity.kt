package com.lemayn.review

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OkHttpClient()
    }
}
