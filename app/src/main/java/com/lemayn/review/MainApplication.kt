package com.lemayn.review

import android.app.Application

import timber.log.Timber

/**
 * @author: ly
 * @date : 2019/2/12
 * @desc :
 */
class MainApplication : Application() {

    companion object {
        private var instance: MainApplication? = null
        fun getInstance(): MainApplication {
            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
