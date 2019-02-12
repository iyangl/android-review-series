package com.lemayn.review

import android.app.Application

import timber.log.Timber

/**
 * @author: ly
 * @date : 2019/2/12
 * @desc :
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
