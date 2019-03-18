package com.lemayn.review.jni;

import android.app.Activity;
import android.app.AlertDialog;

/**
 * author: ly
 * date  : 2019/3/18 22:21
 * desc  :
 */
public class Hello {

    private Activity activity;

    public Hello(Activity activity) {
        this.activity = activity;
    }

    public native String getStringFromC();

    public native void encodeArray(int[] arr);

    public native void cLog();

    /**
     * 被C调用的方法
     */
    public void show(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("标题");
        builder.setMessage(message);
        builder.show();
    }

}
