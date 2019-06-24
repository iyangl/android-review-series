package com.lemayn.review

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout.LayoutParams
import com.lemayn.review.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show(SloopView_5(this))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu1 -> show(SloopView_1(this))
            R.id.menu2 -> show(SloopView_2(this))
            R.id.menu3 -> show(SloopView_3(this))
            R.id.menu4 -> show(SloopView_4(this))
        }
        return false
    }

    private fun show(view: View) {
        container.removeAllViews()
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        container.addView(view, params)
    }
}
