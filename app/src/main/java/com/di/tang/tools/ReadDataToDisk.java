package com.di.tang.tools;

import android.content.Context;
import android.util.Log;

import com.di.tang.constant.ConstantInformation;
import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;
import com.di.tang.data.DetailLPinformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by TD on 2016/8/16.
 */
public class ReadDataToDisk {

    private static final String TAG = "ReadDataToDisk";

    //public void ReadDate
    private Context context;

    private ReadDataToDisk(Context context){
        this.context = context.getApplicationContext();
    }

    private static ReadDataToDisk readDataToDisk;

    public static ReadDataToDisk getInstance(Context context){
        if(readDataToDisk == null){
            readDataToDisk = new ReadDataToDisk(context);
            return readDataToDisk;
        }
        return readDataToDisk;
    }

    public void ReadBPData() throws IOException, JSONException{
        BufferedReader reader = null;
        try{
            InputStream in = context.openFileInput(ConstantInformation.BPFILENAME);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray)new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0; i < jsonArray.length(); i++){
                DataList.getmDetailInformations().
                        add(new DetailInformation(jsonArray.getJSONObject(i)));
            }
        }catch (FileNotFoundException e){
            if(reader != null){
                reader.close();
            }
            Log.e(TAG, "ReadBPData: ReadBPData " + e.toString());
        }
    }

    public void ReadLPData() throws IOException, JSONException{

        BufferedReader reader = null;
        try{
            InputStream in = context.openFileInput(ConstantInformation.LPFILENAME);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray)new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0; i < jsonArray.length(); i++){
                DataList.getmDetailLPinformation().
                        add(new DetailLPinformation(jsonArray.getJSONObject(i)));
            }
        }catch (FileNotFoundException  e){
            if(reader != null){
                reader.close();
            }
            Log.e(TAG, "ReadLPData: ReadLPData " + e.toString());
        }

    }


}
