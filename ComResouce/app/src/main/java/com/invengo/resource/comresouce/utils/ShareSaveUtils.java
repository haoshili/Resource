package com.invengo.resource.comresouce.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.invengo.resource.comresouce.MyApplication;

public class ShareSaveUtils {

    public static boolean saveStringInTable(String key, String value,
                                            String table) {
        try {
            SharedPreferences sp = MyApplication.getContext().getSharedPreferences(table,
                    Context.MODE_PRIVATE);
            Editor edit = sp.edit();
            edit.putString(key, value);
            edit.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveStringInTable(String key, String value) {
        return saveStringInTable(key, value, "default");
    }

    public static boolean saveIntInTable(String key, int value,
                                         String table) {
        try {
            SharedPreferences sp = MyApplication.getContext().getSharedPreferences(table,
                    Context.MODE_PRIVATE);
            Editor edit = sp.edit();
            edit.putInt(key, value);
            edit.apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveIntInTable(String key, int value) {
        return saveIntInTable(key, value, "default");
    }

    public static boolean saveLongInTable(String key, long value,
                                          String table) {
        try {
            SharedPreferences sp = MyApplication.getContext().getSharedPreferences(table,
                    Context.MODE_PRIVATE);
            Editor edit = sp.edit();
            edit.putLong(key, value);
            edit.apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveLongInTable(String key, long value) {
        return saveLongInTable(key, value, "default");
    }

    /*********************************
     * 获取数据
     **********************************/


    public static long getLongFromTable(String key, String table) {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences(table,
                Context.MODE_PRIVATE);
        long number = sp.getLong(key, -1);
        return number;
    }


    public static long getLongFromTable(String key) {
        return getLongFromTable(key, "default");
    }


    public static int getIntFromTable(String key, String table) {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences(table,
                Context.MODE_PRIVATE);
        return sp.getInt(key, -1);
    }


    public static int getIntFromTable(String key) {
        return getIntFromTable(key, "default");
    }

    public static String getStringFromTable(String key, String table) {

        SharedPreferences sp = MyApplication.getContext().getSharedPreferences(table,
                Context.MODE_PRIVATE);
        String number = sp.getString(key, "null");
        return number;
    }

    public static String getStringFromTable(String key) {
        return getStringFromTable(key);
    }
}
