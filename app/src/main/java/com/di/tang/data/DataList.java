package com.di.tang.data;

import java.util.ArrayList;

/**
 * Created by tangdi on 2016/8/2.
 */
public class DataList {

    private static ArrayList<DetailInformation> mDetailInformations =
            new ArrayList<DetailInformation>();

    private static ArrayList<DetailLPinformation> mDetailLPinformation =
            new ArrayList<DetailLPinformation>();

    public static ArrayList<DetailInformation> getmDetailInformations() {
        return mDetailInformations;
    }

    public static ArrayList<DetailLPinformation> getmDetailLPinformation(){
        return mDetailLPinformation;
    }

}
