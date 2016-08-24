package com.di.tang.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import com.di.tang.tools.DataSaveFile;

/**
 * Created by tangdi on 2016/8/24.
 */
public class SaveDPTask extends AsyncTask<Void, Void, Void> {

    private DataSaveFile dataSaveFile;

    private static final String TAG  = "SaveDPTask";

    public SaveDPTask(Context context){
        dataSaveFile = DataSaveFile.getInstance(context);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            dataSaveFile.SaveDataDP();
        }catch(Exception e){
            Log.e(TAG, "doInBackground: " + e.toString());
        }
        return null;
    }
}
