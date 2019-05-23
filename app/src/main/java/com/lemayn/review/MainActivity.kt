package com.lemayn.review

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radio.setOnCheckedChangeListener { group, checkedId ->
            val index = group.indexOfChild(group.findViewById(checkedId))
            var temp = index - 1
            if (index == 0) {
                temp = 1
            }
            pager.setCurrentItem(temp, false)
            pager.currentItem = index
        }

        pager.currentItem = 0
        pager.offscreenPageLimit = 4
        pager.adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return 4
            }

            override fun isViewFromObject(view: View, o: Any): Boolean {
                return view === o
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val view = LayoutInflater.from(container.context).inflate(R.layout.item, container, false)
                val textView = view.findViewById<TextView>(R.id.text)
                textView.text = position.toString()
                val random = Random()
                val r = random.nextInt(256)
                val g = random.nextInt(256)
                val b = random.nextInt(256)
                view.setBackgroundColor(Color.argb(127, r, g, b))
                container.addView(view)
                return view
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

            }
        }

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                // radio.check(radio.getChildAt(position).id)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }
}
