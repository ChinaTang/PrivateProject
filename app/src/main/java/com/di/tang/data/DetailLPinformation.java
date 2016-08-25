package com.di.tang.data;

import com.di.tang.constant.ConstantInformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tangdi on 2016/8/4.
 */
public class DetailLPinformation {
    private UUID uuid;
    private boolean isEmpty = false;
    private boolean isSell = false;
    private String Address = "";

    private int number;
    private Date noMilkDay;
    private int sellNumber;
    private Date sellDay;

    private boolean isCastrate = false;
    private Date castratedDay;

    private URI image;

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getNoMilkDay() {
        return noMilkDay;
    }

    public void setNoMilkDay(Date noMilkDay) {
        this.noMilkDay = noMilkDay;
    }

    public int getSellNumber() {
        return sellNumber;
    }

    public void setSellNumber(int sellNumber) {
        this.sellNumber = sellNumber;
    }

    public Date getSellDay() {
        return sellDay;
    }

    public void setSellDay(Date sellDay) {
        this.sellDay = sellDay;
    }

    public Date getCastratedDay() {
        return castratedDay;
    }

    public void setCastratedDay(Date castratedDay) {
        this.castratedDay = castratedDay;
    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
    public DetailLPinformation(){
        this.uuid = UUID.randomUUID();
        noMilkDay = new Date();
        sellDay = new Date();
        castratedDay = new Date();
    }

    public boolean isCastrate() {
        return isCastrate;
    }

    public void setCastrate(boolean castrate) {
        isCastrate = castrate;
    }

    public URI getImage() {
        return image;
    }

    public void setImage(URI image) {
        this.image = image;
    }

    public JSONObject lpToJSON() throws JSONException{
        JSONObject packData = new JSONObject();
        packData.put(ConstantInformation._UUID, uuid.toString());
        packData.put(ConstantInformation.ISEMPTY, isEmpty);
        packData.put(ConstantInformation.ISSELL, isSell);
        packData.put(ConstantInformation.ADDRESS, Address);
        packData.put(ConstantInformation.NUMBER, number);
        if(noMilkDay == null){
            packData.put(ConstantInformation.NOMILKDAY, 0);
        }else{
            packData.put(ConstantInformation.NOMILKDAY, noMilkDay.getTime());
        }

        packData.put(ConstantInformation.SELLNUMBER, sellNumber);
        if(sellDay == null){
            packData.put(ConstantInformation.SELLDAY, 0);
        }else{
            packData.put(ConstantInformation.SELLDAY, sellDay.getTime());
        }
        packData.put(ConstantInformation.ISCASTRATE, isCastrate);
        if(castratedDay == null){
            packData.put(ConstantInformation.CASTRATEDDAY, 0);
        }else{
            packData.put(ConstantInformation.CASTRATEDDAY, castratedDay.getTime());
        }
        if(image == null){
            packData.put(ConstantInformation.IMAGEURI, "");
        }else{
            packData.put(ConstantInformation.IMAGEURI, image.toString());
        }
        return packData;
    }

    public DetailLPinformation(JSONObject jsonObject) throws JSONException{
        uuid = UUID.fromString(jsonObject.getString(ConstantInformation._UUID));
        isEmpty = jsonObject.getBoolean(ConstantInformation.ISEMPTY);
        isSell = jsonObject.getBoolean(ConstantInformation.ISSELL);
        Address = jsonObject.getString(ConstantInformation.ADDRESS);
        number = jsonObject.getInt(ConstantInformation.NUMBER);
        if(jsonObject.getLong(ConstantInformation.NOMILKDAY) != 0){
            noMilkDay = new Date(jsonObject.getLong(ConstantInformation.NOMILKDAY));
        }
        sellNumber = jsonObject.getInt(ConstantInformation.SELLNUMBER);
        if(jsonObject.getLong(ConstantInformation.SELLDAY) != 0){
            sellDay = new Date(jsonObject.getLong(ConstantInformation.SELLDAY));
        }
        isCastrate = jsonObject.getBoolean(ConstantInformation.ISCASTRATE);
        if(jsonObject.getLong(ConstantInformation.CASTRATEDDAY) != 0){
            castratedDay = new Date(jsonObject.getLong(ConstantInformation.CASTRATEDDAY));
        }
        if(jsonObject.getString(ConstantInformation.IMAGEURI) == ""){
            image = null;
        }else{
            image = URI.create(jsonObject.getString(ConstantInformation.IMAGEURI));
        }
    }
}
