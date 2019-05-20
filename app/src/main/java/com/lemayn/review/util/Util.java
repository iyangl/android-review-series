package com.lemayn.review.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import com.lemayn.review.MainApplication;

/**
 * author: ly
 * date  : 2019/5/18 17:27
 * desc  :
 */
public class Util {


    public static @org.jetbrains.annotations.Nullable String getCurProcessName() {
        int pid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) MainApplication.Companion.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : activityManager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                return process.processName;
            }
        }
        return null;
    }

}
