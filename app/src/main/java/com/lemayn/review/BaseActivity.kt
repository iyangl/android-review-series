package com.lemayn.review

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import timber.log.Timber

/**
 * @author: ly
 * @date : 2019/2/12
 * @desc :
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(this.toString()).i("onCreate")
        supportActionBar?.title = this.toString().substring(this.toString().indexOf(javaClass.simpleName))
    }


    override fun onStart() {
        super.onStart()
        Timber.tag(this.toString()).i("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.tag(this.toString()).i("onRestart")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.tag(this.toString()).i("onRestoreInstanceState: %s", savedInstanceState?.getString("method"))
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(this.toString()).i("onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(this.toString()).i("onPause")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("method", "onSaveInstanceState")
        Timber.tag(this.toString()).i("onSaveInstanceState")
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(this.toString()).i("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(this.toString()).i("onDestroy")
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Timber.tag(this.toString()).i("onConfigurationChanged")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Timber.tag(this.toString()).i("onNewIntent")
    }

    fun viewClick(view: View) {
        when (view.id) {
            R.id.dialog -> {
                AlertDialog.Builder(this).setTitle("这是一个dialog").show()
            }

            R.id.standard -> {
                startActivity(Intent(this, StandardActivity::class.java))
            }

            R.id.translucent -> {
                startActivity(Intent(this, TranslucentActivity::class.java))
            }

            R.id.singleTask -> {
                startActivity(Intent(this, SingleTaskActivity::class.java))
            }

            R.id.singleTop -> {
                startActivity(Intent(this, SingleTopActivity::class.java))
            }

            R.id.singleInstance -> {
                startActivity(Intent(this, SingleInstanceActivity::class.java))
            }

            R.id.clearTop -> {
                val intent = Intent(this, StandardActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
            }

            R.id.newTask -> {
                val intent = Intent(this, StandardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
    }
}
