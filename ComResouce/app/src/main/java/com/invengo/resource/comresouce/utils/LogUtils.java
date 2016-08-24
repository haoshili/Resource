package com.invengo.resource.comresouce.utils;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-14
 * Time: 15:39
 */

import android.util.Log;
import android.widget.Toast;

import com.invengo.resource.comresouce.MyApplication;

//Logcat统一管理类
public class LogUtils {

    private LogUtils() {
            /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "way";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }
    
    public static void toast(String msg){
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}