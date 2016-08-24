package com.invengo.resource.comresouce.utilsnet.model;

import android.os.Handler;
import android.os.Looper;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.invengo.resource.comresouce.utils.GsonUtil;
import com.invengo.resource.comresouce.utilsnet.netinterface.RequestListenser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-15
 * Time: 14:24
 * <p/>
 * <p/>
 * 对okhttp进行封装
 */
public class OkHttpUtils {

    private static OkHttpUtils mInstance;
    private OkHttpClient okClient;
    private Handler handler;

    private Gson gson;

    private OkHttpUtils() {
        okClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        handler = new Handler(Looper.getMainLooper());
        gson = new Gson();

    }

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    //----------------------------------对外公布的方法-----------------------------------------//

    /**
     * 对返回结果进行解析
     *
     * @param url
     * @param cl
     * @param listenser
     */
    public static void getAsyn(String url, Class<?> cl, RequestListenser listenser) {
        getInstance()._getAsyn(url, cl, listenser);
    }

    /**
     * 返回字符创类型数据
     *
     * @param url
     * @param listenser
     */
    public static void getAsynString(String url, RequestListenser listenser) {
        getInstance()._getAsynString(url, listenser);
    }

    //------------------------------------执行者---------------------------------------------------//

    private void _getAsyn(String url, final Class<?> cl, final RequestListenser
            listenser) {

        Request request = new Request.Builder()
                .url(url)
                .build();

        deliveryResult(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listenser.onLoadError();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Object object = GsonUtil.jsonToBean(response.body().string(), cl);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listenser.onLoadSuccess(object);
                    }
                });

            }
        });
    }

    /**
     * 对返回结果进行解析
     *
     * @param url
     * @param listenser
     */
    private void _getAsynString(String url, final RequestListenser
            listenser) {
        final Request request = new Request.Builder()
                .url(url)
                .build();

        deliveryResult(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listenser.onLoadError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    listenser.onLoadSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    /**
     * 请求执行
     *
     * @param request
     * @param callback
     */
    private void deliveryResult(Request request, final Callback callback) {
        okClient.newCall(request).enqueue(callback);
    }

}