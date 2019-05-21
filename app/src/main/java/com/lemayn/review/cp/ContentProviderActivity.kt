package com.lemayn.review.cp

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lemayn.review.R

/**
 * author: ly
 * date  : 2019/5/21 14:00
 * desc  :
 */
class ContentProviderActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cp)

        val uri = Uri.parse("content://com.lemayn.review.cp.RemoteProvider")
        val cursor = contentResolver?.query(uri, null, null, null, null)
        cursor?.close()

    }

}