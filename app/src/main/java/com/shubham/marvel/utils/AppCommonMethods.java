package com.shubham.marvel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppCommonMethods {
    public static String convertTimeFormat1(String currentTimeFormat, String expectedTimeFormat, String time) {
        if (time == null) {
            return "";
        }
        String newTime = null;
        SimpleDateFormat actual = new SimpleDateFormat(currentTimeFormat);
        SimpleDateFormat target = new SimpleDateFormat(expectedTimeFormat);
        Date date;
        try {
            date = actual.parse(time);
            newTime = target.format(date);
            return newTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}
