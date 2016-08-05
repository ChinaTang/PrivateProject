package com.di.tang.data;

import java.util.ArrayList;

/**
 * Created by tangdi on 2016/8/2.
 */
public class DataList {

    private static ArrayList<DetailInformation> mDetailInformations =
            new ArrayList<DetailInformation>();

    public static ArrayList<DetailInformation> getmDetailInformations() {
        return mDetailInformations;
    }

}
