package com.invengo.resource.comresouce.utilsnet.model;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.invengo.resource.comresouce.MyApplication;
import com.invengo.resource.comresouce.utils.GsonUtil;
import com.invengo.resource.comresouce.utils.LogUtils;
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
public class OkHttpUtilsTwo {


    private OkHttpClient okClient;
    private Handler handler;

    private Gson gson;

    public OkHttpUtilsTwo() {
        okClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        handler = new Handler(Looper.getMainLooper());
        gson = new Gson();

        Toast.makeText(MyApplication.getContext(),"sss:",Toast.LENGTH_SHORT).show();
    }


    //----------------------------------对外公布的方法-----------------------------------------//


    //------------------------------------执行者---------------------------------------------------//

    private void _getAsyn(String url, final Class<?> cl, final RequestListenser
            listenser) {

        Request request = new Request.Builder()
                .url(url)
                .build();

        deliveryResult(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listenser.onLoadError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.i("test", response.body().string());
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
    public void _getAsynString(String url, final RequestListenser
            listenser) {

        LogUtils.i("test" + "doing1");
        Toast.makeText(MyApplication.getContext(),"ttt:",Toast.LENGTH_SHORT).show();
        final Request request = new Request.Builder()
                .url(url)
                .build();

        deliveryResult(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listenser.onLoadError();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                LogUtils.i("test", response.body().string() + "dafsdfasdfasdf");


                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            String sb = response.body().string();

                            Toast.makeText(MyApplication.getContext(),"sss:"+sb,Toast.LENGTH_SHORT)
                                    .show();

                            LogUtils.i("test", response.body().string());
                            listenser.onLoadSuccess(sb);
                        } catch (IOException e) {
                            e.printStackTrace();
                            LogUtils.i("test", "eee");
                        }

                    }
                });

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

        LogUtils.i("test" + "doingDel");
        okClient.newCall(request).enqueue(callback);
    }

}