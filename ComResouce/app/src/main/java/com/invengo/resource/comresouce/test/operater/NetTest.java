package com.invengo.resource.comresouce.test.operater;

import android.os.Handler;
import android.os.Looper;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.invengo.resource.comresouce.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-16
 * Time: 10:48
 */
public class NetTest {


    /**
     * 测试网络请求
     */
    public void testNet(String url) {

        LogUtils.i("test", "net");


        OkHttpClient okClient;
        Handler handler;
        Gson gson;

        okClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        handler = new Handler(Looper.getMainLooper());
        gson = new Gson();


        Request request = new Request.Builder()
                .url(url)
                .build();

        okClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.i("test", "adfghfdgdfghfh");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.i("test", "34234234");

                try {
                    long str = response.body().contentLength();
                   String sb =  response.body().string();
                    LogUtils.i("test", "dfsdfsd");
                    LogUtils.i("test", "dfsdfsd"+response.code());
                    LogUtils.i("test", "34234234" + sb);
                } catch (IOException e) {

                    LogUtils.i("test", "fgfgf");

                } catch (com.google.gson.JsonParseException e)//Json解析的错误
                {
                    LogUtils.i("test", "uyiui");

                }

            }

        });

    }
}  