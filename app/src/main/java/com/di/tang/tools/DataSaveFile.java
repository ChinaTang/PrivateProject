package com.di.tang.tools;

import android.content.Context;
import android.support.annotation.RequiresPermission;

import com.di.tang.constant.ConstantInformation;
import com.di.tang.data.DataList;
import com.di.tang.data.DetailInformation;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by TD on 2016/8/16.
 */
public class DataSaveFile {

    private Context context;

    private ArrayList<DetailInformation> detailInformations;

    public DataSaveFile(Context context){
        this.context = context;
        detailInformations = DataList.getmDetailInformations();
    }

    public void SaveDataDP() throws JSONException, IOException{
        JSONArray array = new JSONArray();
        for(DetailInformation detailInformation : detailInformations){
            array.put(detailInformation.bpToJSON());
        }
        Writer writer = null;
        try{
            OutputStream out = context.openFileOutput(ConstantInformation.FILENAME, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }finally {
            if(writer != null){
                writer = null;
            }
        }
    }

}
