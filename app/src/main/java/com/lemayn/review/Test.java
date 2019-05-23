package com.lemayn.review;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

/**
 * author: ly
 * date  : 2019/4/30 22:58
 * desc  :
 */
public class Test {

    public static void main(String[] args) {

        ViewPager pager = new ViewPager(null);
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView view = new ImageView(container.getContext());
                Random random = new Random();
                int r = random.nextInt(256);
                int g = random.nextInt(256);
                int b = random.nextInt(256);
                view.setBackgroundColor(Color.argb(127, r, g, b));
                return super.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            }
        });


    }

}
