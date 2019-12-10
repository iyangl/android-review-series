package com.lemayn.review.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.support.v7.app.AppCompatActivity
import com.lemayn.review.R


class AidlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                val serviceInterface = IMyAidlInterface.Stub.asInterface(service)
                try {
                    serviceInterface.basicTypes(0, 1, true, 3f, 4.0, "5")
                    serviceInterface.sum(1, 2)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }

            override fun onServiceDisconnected(name: ComponentName) {

            }
        }

        bindRemoteService(connection)
    }

    private fun bindRemoteService(connection: ServiceConnection) {
        bindService(Intent(this, MyAidlInterface::class.java), connection, Context.BIND_AUTO_CREATE)
    }
}
