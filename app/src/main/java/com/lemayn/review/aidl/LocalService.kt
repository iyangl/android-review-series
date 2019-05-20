package com.lemayn.review.aidl

import android.app.IntentService
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.lemayn.review.util.Util
import timber.log.Timber

/**
 * author: ly
 * date  : 2019/5/18 15:18
 * desc  :
 */
class LocalService : IntentService("LocalService") {
    override fun onHandleIntent(intent: Intent?) {
        Timber.i("onHandleIntent ---- Intent：%s, thread: %s", intent.toString(), Thread.currentThread())
    }

    override fun onCreate() {
        Timber.i("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Timber.i("onStartCommand ---- intent: %s, flags: %s, startId: %s", intent.toString(), flags, startId)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        Timber.i("onBind ---- intent: %s", intent.toString())
        return LocalBinder()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy")
    }

    class LocalBinder : Binder() {

        fun log(): String {
            Timber.i("LocalBinder ---- log")
            return String.format("process: %s, thread: %s", Util.getCurProcessName(), Thread.currentThread())
        }

    }
}
