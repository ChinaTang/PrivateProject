package com.di.tang.gateway;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.di.tang.firstboundary.activity.MainInterfaceActivity;
import com.di.tang.logger.Logger;
import com.di.tang.privateproject.R;

import java.util.TimerTask;

public class GateWayActivity extends AppCompatActivity {

    private static final String TAG = "GateWayActivity";
    private static final int TIME = 5000;
    private int times = 0;
    private Intent intent;
    private Handler handler = new Handler();
    private TimerTask task = new TimerTask(){
        public void run(){

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portals_interface);
        intent = new Intent(GateWayActivity.this, MainInterfaceActivity.class);
        handler.postDelayed(mCloseSelf, TIME);
    }

    Runnable mCloseSelf = new Runnable() {
        @Override
        public void run() {
            Logger.d(TAG, "close");
            startActivity(intent);
            GateWayActivity.this.finish();
        }
    };
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onStart(){
        super.onStart();
        Logger.d(TAG, "onStart()");
    }

    @Override
    public void onPause(){
        super.onPause();
        Logger.d(TAG, "onPause()");
    }

    @Override
    public void onResume(){
        super.onResume();
        Logger.d(TAG, "onResume()");
    }

    @Override
    public void onStop(){
        super.onStop();
        Logger.d(TAG, "onStop()");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Logger.d(TAG, "onStop()");
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode){}
}
