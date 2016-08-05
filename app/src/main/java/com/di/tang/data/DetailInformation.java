package com.di.tang.data;

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
    private boolean ishas = false;
    private int number;
    private Date hasDate;
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
    private ArrayList<DetailLPinformation> mDetailLPinformation = new ArrayList<DetailLPinformation>();

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

    public boolean isEmpty() {
        if(ishas || isMating || isPregnant){
            isEmpty = true;
        }else{
            isEmpty = false;
        }
        return isEmpty;
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

    public ArrayList<DetailLPinformation> getmDetailLPinformation() {
        return mDetailLPinformation;
    }

    public void setmDetailLPinformation(ArrayList<DetailLPinformation> mDetailLPinformation) {
        this.mDetailLPinformation = mDetailLPinformation;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
