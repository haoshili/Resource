package com.invengo.resource.comresouce;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.atid.lib.dev.ATRfidManager;
import com.atid.lib.dev.ATRfidReader;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.invengo.resource.comresouce.utils.LogUtils;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

	private static Context context;
	public ATRfidReader mReader = null;
	private String deviceName;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {

		context = getApplicationContext();

		Stetho.initializeWithDefaults(this);
		new OkHttpClient.Builder() .addNetworkInterceptor(new StethoInterceptor()) .build();
		deviceName = getDeviceName();

	 	LogUtils.toast(deviceName);


	}

	public static Context getContext() {
		return context;
	}

	public String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return capitalize(model);
		} else {
			return capitalize(manufacturer) + " " + model;
		}
	}


	private String capitalize(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}

}
