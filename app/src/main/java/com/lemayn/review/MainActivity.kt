package com.lemayn.review

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.junit.Test

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Test
    fun test() {
        val str = ProxyBean()
        println(LoginCheckHandler.proxy(str, ProxyBean::class.java))
    }
}
