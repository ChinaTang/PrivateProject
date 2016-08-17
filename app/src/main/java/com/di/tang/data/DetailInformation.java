package com.di.tang.data;

import com.di.tang.constant.ConstantInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tangdi on 2016/8/2.
 */
public class DetailInformation {

    private UUID uuid;
    private boolean isEmpty = false;
    private String address;
    private URI Imageuri = null;

    /*-------has lp-----------*/
    /*--if ishas is TRUE item should include lp's number and lp was burn days----*/
    /*------------------*/

    /*--------MatingTimes----------*/
    /*---------if isMating is TRUE item should include matingTimes &
    Last Time mating-------------------*/
    private boolean isMating = false;
    private int matingTimes;
    private Date matingDate;
    /*------------------*/

    /*--------Pregnant----------*/
    /*-if isPregnan is TRUE item should include PregnantDays and pregnantDate -*/
    private boolean isPregnant = false;
    private Date pregnantDate;
    private int pregnantDays;
    /*------------------*/

     /*---------In total have lp---------*/
    private ArrayList<HaveLp> mDetailLPinformation = new ArrayList<HaveLp>();

    public DetailInformation(String address){
        uuid = UUID.randomUUID();
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isMating() {
        return isMating;
    }

    public void setMating(boolean mating) {
        isMating = mating;
    }

    public int getMatingTimes() {
        return matingTimes;
    }

    public void setMatingTimes(int matingTimes) {
        this.matingTimes = matingTimes;
    }

    public Date getMatingDate() {
        return matingDate;
    }

    public void setMatingDate(Date matingDate) {
        this.matingDate = matingDate;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public Date getPregnantDate() {
        return pregnantDate;
    }

    public void setPregnantDate(Date pregnantDate) {
        this.pregnantDate = pregnantDate;
    }

    public int getPregnantTimes() {
        return pregnantDays;
    }

    public void setPregnantTimes(int pregnantTimes) {
        this.pregnantDays = pregnantTimes;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public URI getImageuri() {
        return Imageuri;
    }

    public void setImageuri(URI imageuri) {
        Imageuri = imageuri;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    public int NoMillkTime(){
        return 0;
    }

    public ArrayList<HaveLp> getmDetailLPinformation() {
        return mDetailLPinformation;
    }

    public void setmDetailLPinformation(ArrayList<HaveLp> mDetailLPinformation) {
        this.mDetailLPinformation = mDetailLPinformation;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JSONObject bpToJSON() throws JSONException{
        JSONObject packData = new JSONObject();
        packData.put(ConstantInformation._UUID, uuid.toString());
        packData.put(ConstantInformation.ISEMPTY, isEmpty);
        packData.put(ConstantInformation.ADDRESS, address);
        packData.put(ConstantInformation.IMAGEURI, Imageuri.toString());
        packData.put(ConstantInformation.ISMATING, isMating);
        packData.put(ConstantInformation.MATINGTIMES, matingTimes);
        packData.put(ConstantInformation.MATINGDATE, matingDate.getTime());
        packData.put(ConstantInformation.ISPREGNANT, isPregnant);
        packData.put(ConstantInformation.PREGNANTDATE, pregnantDate.getTime());
        packData.put(ConstantInformation.PREGNANTDAYS, pregnantDays);
        packData.put(ConstantInformation.HAVELPLIST, LptoJSONArray());
        return packData;
    }

    private JSONArray LptoJSONArray() throws JSONException{
        JSONArray array = new JSONArray();
        for(int i = 0; i < mDetailLPinformation.size(); i++){
            array.put(mDetailLPinformation.get(i).HaveLpToJSON());
        }
        return array;
    }
}
