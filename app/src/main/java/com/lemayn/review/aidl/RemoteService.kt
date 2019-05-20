package com.lemayn.review.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.lemayn.review.RemoteAidlService
import com.lemayn.review.bean.Entity
import com.lemayn.review.util.Util
import timber.log.Timber

/**
 * author: ly
 * date  : 2019/5/20 10:54
 * desc  :
 */
class RemoteService : Service() {

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
        return RemoteBinder()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy")
    }

    class RemoteBinder : RemoteAidlService.Stub() {

        private var list: ArrayList<Entity> = ArrayList()

        override fun add(entity: Entity?) {
            if (entity != null) {
                list.add(entity)
            }
        }

        override fun get(): MutableList<Entity> {
            return list
        }

        override fun log(): String {
            Timber.i("RemoteBinder ---- log")
            return String.format("process: %s, thread: %s", Util.getCurProcessName(), Thread.currentThread())
        }
    }

}