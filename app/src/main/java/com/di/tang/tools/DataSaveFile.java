package com.di.tang.tools;

import android.content.Context;
import android.support.annotation.RequiresPermission;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by TD on 2016/8/16.
 */
public class DataSaveFile {

    private static final String TAG = "DataSaveFile";

    private Context context;

    private ArrayList<DetailInformation> detailInformations;

    private ArrayList<DetailLPinformation> detailLPinformations;

    private static DataSaveFile dataSaveFile;

    private DataSaveFile(Context context){
        this.context = context.getApplicationContext();
        detailInformations = DataList.getmDetailInformations();
        detailLPinformations = DataList.getmDetailLPinformation();
    }

    public static DataSaveFile getInstance(Context context){
        if(dataSaveFile == null){
            return new DataSaveFile(context);
        }
        return dataSaveFile;
    }

    public void SaveDataDP() throws JSONException, IOException{
        JSONArray array = new JSONArray();
        for(DetailInformation detailInformation : detailInformations){
            array.put(detailInformation.bpToJSON());
        }
        Writer writer = null;
        try{
            OutputStream out = context.openFileOutput(ConstantInformation.BPFILENAME, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }finally {
            if(writer != null){
                writer = null;
            }
        }
    }

    public void SvaeDataLP() throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for (DetailLPinformation detailLPinformation : detailLPinformations) {
            array.put(detailLPinformation.lpToJSON());
        }
        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput(ConstantInformation.LPFILENAME, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null) {
                writer = null;
            }
        }
    }

    public void ReadDataLP() throws JSONException, IOException {
        BufferedReader read = null;
        try {
            InputStream in = context.openFileInput(ConstantInformation.LPFILENAME);
            read = new BufferedReader(new InputStreamReader(in));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = read.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(stringBuilder.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                DataList.getmDetailLPinformation().add(new DetailLPinformation(jsonArray.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "ReadDataLP: NO SUCH FILE");
        } finally {
            if(read != null){
                read = null;
            }
        }
    }

    public void ReadDataBP() throws JSONException, IOException {
        BufferedReader read = null;
        try {
            InputStream in = context.openFileInput(ConstantInformation.BPFILENAME);
            read = new BufferedReader(new InputStreamReader(in));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = read.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(stringBuilder.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                DataList.getmDetailInformations().add(new DetailInformation(jsonArray.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "ReadDataLP: NO SUCH FILE");
        } finally {
            if(read != null){
                read = null;
            }
        }
    }

}
