package com.lemayn.review;

import android.app.Application;

import timber.log.Timber;

/**
 * author: ly
 * date  : 2019/3/18 22:42
 * desc  :
 */
public class MainApplication extends Application {

    static{
        //加载打包完毕的 so类库
        System.loadLibrary("hello");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
