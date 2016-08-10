package com.di.tang.data;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tangdi on 2016/8/4.
 */
public class DetailLPinformation {
    private boolean isEmpty = false;
    private boolean isSell = false;
    private UUID uuid;
    private String Address;

    private int number;
    private Date noMilkDay;
    private int sellNumber;
    private Date sellDay;

    private boolean isCastrate;
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
}
