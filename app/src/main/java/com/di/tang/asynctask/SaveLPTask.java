package com.di.tang.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.di.tang.tools.DataSaveFile;

/**
 * Created by tangdi on 2016/8/24.
 */
public class SaveLPTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "SaveLPTask";

    private DataSaveFile dataSaveFile;

    public SaveLPTask(Context context){
        dataSaveFile = DataSaveFile.getInstance(context);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            dataSaveFile.SvaeDataLP();
        }catch(Exception e){
            Log.e(TAG, "doInBackground: " + e.toString());
        }
        return null;
    }
}
