package com.di.tang.data;

import com.di.tang.constant.ConstantInformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by TD on 2016/8/16.
 * the BP has LP but Lp need to drink milk
 */
public class HaveLp {

    private int number;
    private Date hasDate;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getHasDate() {
        return hasDate;
    }

    public void setHasDate(Date hasDate) {
        this.hasDate = hasDate;
    }

    public JSONObject HaveLpToJSON() throws JSONException{
        JSONObject packData = new JSONObject();
        packData.put(ConstantInformation.NUMBER, number);
        if(hasDate == null){
            packData.put(ConstantInformation.HASDATE, 0);
        }else{
            packData.put(ConstantInformation.HASDATE, hasDate.getTime());
        }
        return packData;
    }

    public HaveLp(JSONObject jsonObject)throws JSONException{
        number = jsonObject.getInt(ConstantInformation.NUMBER);
        if(jsonObject.getLong(ConstantInformation.HASDATE) != 0){
            hasDate = new Date(jsonObject.getLong(ConstantInformation.HASDATE));
        }
    }

    public HaveLp(){}
}
