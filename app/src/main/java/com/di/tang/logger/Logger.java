package com.di.tang.logger;

import android.util.Log;

/**
 * Created by tangdi on 2016/7/29.
 */
public final class Logger{
    private static final String TAG = "TANGDI.";
    public static int d(String tag, String msg){
        int result;
        result = Log.d(TAG  + "-> "+ tag, msg);
        return result;
    }

    public static int w(String tag, String msg){
        int result;
        result = Log.w(TAG  + "-> "+ tag, msg);
        return result;
    }

    public static int e(String tag, String msg){
        int result;
        result = Log.e(TAG  + "-> "+ tag, msg);
        return result;
    }

}
