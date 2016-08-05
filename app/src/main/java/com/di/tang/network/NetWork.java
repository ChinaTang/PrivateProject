package com.di.tang.network;

import android.content.Context;

/**
 * Created by tangdi on 2016/7/29.
 */
public class NetWork {
    private Context mContext;
    private static NetWork mNetWork;
    private NetWork(Context context){
        mContext = context;
    }
    public static NetWork getInstance(Context context){
        if(mNetWork == null){
            mNetWork = new NetWork(context.getApplicationContext());
        }
        return mNetWork;
    }


}
