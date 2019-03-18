package com.lemayn.review;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lemayn.review.databinding.ActivityMainBinding;
import com.lemayn.review.jni.Hello;

import java.util.Arrays;

/**
 * author: ly
 * date  : 2019/3/18 22:43
 * desc  :
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Hello hello;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        hello = new Hello(this);

        binding.text.setText(hello.getStringFromC());

        int[] array = {1, 2, 3, 4, 5};
        hello.encodeArray(array);
        binding.text.setText(Arrays.toString(array));
        for (int i : array) {
            System.out.println(i);
        }

        binding.text.postDelayed(new Runnable() {
            @Override
            public void run() {
                hello.cLog();
            }
        }, 3000);
    }

}
