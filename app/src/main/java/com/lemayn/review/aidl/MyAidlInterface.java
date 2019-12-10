package com.lemayn.review.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lemayn.review.utils.AppUtil;

/**
 * author: ly
 * date  : 2019/12/10 16:25
 * desc  :
 */
public class MyAidlInterface extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    class Binder extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            System.out.println(
                    String.format("MyAidlInterface: basicTypes --> %s, %s, %s, %s, %s, %s",
                            anInt, aLong, aBoolean, aFloat, aDouble, aString)
            );
            System.out.println(AppUtil.getProcessName(getApplicationContext()));
        }

        @Override
        public void sum(int x, int y) {
            System.out.println("MyAidlInterface: sum --> " + x + " " + y + " = " + x + y);
        }
    }
}
