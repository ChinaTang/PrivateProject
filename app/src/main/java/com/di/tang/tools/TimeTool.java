package com.di.tang.tools;

import com.di.tang.data.DetailInformation;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tangdi on 2016/8/2.
 */
public class TimeTool {
    private static final int HALF_DAY = (24 * 60 * 60)/2;

    private static Date NowDate;


    public static int getDays(Date dateForAgo, Date dateForNow){
        long ago = dateForAgo.getTime();
        long now = dateForNow.getTime();
        int days = (int)((now - ago)/(24 * 60 * 60));
        if((now - ago)%(24 * 60 * 60) > HALF_DAY){
            days++;
        }else{
            days += 0.5;
        }
        return days;
    }

    public static Date getNowDate() {
        return NowDate;
    }

    public static void setNowDate(Date nowDate) {
        NowDate = nowDate;
    }

    public static String DateToYYMMDD(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int Mounth = calendar.get(Calendar.MONTH);
        int Day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "年" + Mounth + "月" + Day + "日";
    }
}
