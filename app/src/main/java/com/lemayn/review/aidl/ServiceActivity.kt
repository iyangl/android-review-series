package com.lemayn.review.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.lemayn.review.R
import com.lemayn.review.RemoteAidlService
import com.lemayn.review.bean.Entity
import kotlinx.android.synthetic.main.activity_service.*
import kotlin.random.Random


/**
 * author: ly
 * date  : 2019/5/18 15:32
 * desc  :
 */
class ServiceActivity : AppCompatActivity(), View.OnClickListener {

    var isServiceBind = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        logText.movementMethod = ScrollingMovementMethod.getInstance()

        startLocalService.setOnClickListener(this)
        stopLocalService.setOnClickListener(this)
        bindLocalService.setOnClickListener(this)
        unBindLocalService.setOnClickListener(this)
        startAidlService.setOnClickListener(this)
        stopAidlService.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            startLocalService -> startLocalService()
            stopLocalService -> stopLocalService()
            bindLocalService -> bindLocalService()
            unBindLocalService -> unBindService()
            startAidlService -> startAidlService()
            stopAidlService -> unBindService()
        }
    }

    private fun startLocalService() {
        startService(Intent(this, LocalService::class.java))
    }

    private fun stopLocalService() {
        stopService(Intent(this, LocalService::class.java))
    }

    /**
     *  本地服务返回的IBinder对象是服务的Binder引用
     *  aidl远程服务返回的是BinderProxy
     */
    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            isServiceBind = true
            append(String.format("onServiceConnected: %s%s", name.packageName, name.shortClassName))
            if (service is LocalService.LocalBinder) {
                append(service.log())
            } else {
                // 需要通过Service.Stub.asInterface拿到binder对象
                val aidlService = RemoteAidlService.Stub.asInterface(service)
                append(aidlService.log())
                aidlService.add(Entity(Random(100).nextInt()))
                append(aidlService.get().toString() + "的size：" + aidlService.get().size)
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            append(String.format("onServiceDisconnected: %s%s", name.packageName, name.shortClassName))
        }
    }

    private fun bindLocalService() {
        bindService(Intent(this, LocalService::class.java), conn, Context.BIND_AUTO_CREATE)
    }

    private fun unBindService() {
        if (isServiceBind) {
            unbindService(conn)
            isServiceBind = false
        }
    }

    private fun startAidlService() {
        val intent = Intent(this, RemoteService::class.java)
        intent.action = "com.lemayn.review.aidl.remote"
        intent.setPackage("com.lemayn.review")
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
    }

    private fun stopAidlService() {
        unbindService(conn)
    }

    private fun append(text: String) {
        logText.text = StringBuilder(logText.text).append(text).append("\n").toString()
    }
}