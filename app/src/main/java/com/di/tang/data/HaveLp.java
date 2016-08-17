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

    private boolean ishas = false;
    private int number;
    private Date hasDate;
    public boolean ishas() {
        return ishas;
    }

    public void setIshas(boolean ishas) {
        this.ishas = ishas;
    }

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
        packData.put(ConstantInformation.ISHAS, ishas);
        packData.put(ConstantInformation.NUMBER, number);
        packData.put(ConstantInformation.HASDATE, hasDate.getTime());
        return packData;
    }
}
