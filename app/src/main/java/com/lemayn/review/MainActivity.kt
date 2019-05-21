package com.lemayn.review

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lemayn.review.aidl.ServiceActivity
import com.lemayn.review.cp.ContentProviderActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toServiceActivity.setOnClickListener(this)
        toContentProviderActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            toServiceActivity -> startActivity(Intent(this, ServiceActivity::class.java))
            toContentProviderActivity -> startActivity(Intent(this, ContentProviderActivity::class.java))
        }
    }
}
