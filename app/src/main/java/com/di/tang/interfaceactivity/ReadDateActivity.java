package com.di.tang.interfaceactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.di.tang.data.DataList;
import com.di.tang.firstboundary.activity.MainInterfaceActivity;
import com.di.tang.privateproject.R;
import com.di.tang.tools.ReadDataToDisk;

/**
 * Created by tangdi on 2016/8/24.
 */
public class ReadDateActivity extends AppCompatActivity {

    private static final String TAG = "ReadDateActivity";

    private ReadDataToDisk readDataToDisk;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 0x12:
                    Intent intent = new Intent(ReadDateActivity.this, MainInterfaceActivity.class);
                    ReadDateActivity.this.startActivity(intent);
                    ReadDateActivity.this.finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            readDataToDisk = ReadDataToDisk.getInstance(ReadDateActivity.this);
            DataList.getmDetailInformations().clear();
            DataList.getmDetailLPinformation().clear();
            try{
                readDataToDisk.ReadBPData();
                readDataToDisk.ReadLPData();
            }catch (Exception e){
                Log.e(TAG, "onCreate: " + e.toString());
            }
            Message message = Message.obtain();
            message.what = 0x12;
            handler.sendMessage(message);
        }
    };
    @Override
    public void onCreate(Bundle SaveInstanceBundle){
        super.onCreate(SaveInstanceBundle);
        setContentView(R.layout.read_data_layout);
        new Thread(runnable).start();
    }

}

