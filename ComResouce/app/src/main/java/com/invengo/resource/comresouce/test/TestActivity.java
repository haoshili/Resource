package com.invengo.resource.comresouce.test;

import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.test.operater.CreatJsonData;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.comresouce.utilsnet.model.BaseModel;
import com.invengo.resource.comresouce.utilsnet.model.OkHttpUtils;
import com.invengo.resource.comresouce.utilsnet.netinterface.RequestListenser;
import com.invengo.resource.entity.Job;

import okhttp3.OkHttpClient;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-18
 * Time: 16:23
 */
public class TestActivity extends BaseTwoActivity {

    OkHttpClient okClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        inItHead("测试界面");
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

    }

    /**
     * 生成订单数据
     *
     * @param view
     */
    public void creatJsonData(View view) {

        new CreatJsonData().creatOrderJsonData();
    }

    private static String url = "http://192.170.0.112:67/";

    /**
     * 网络请求测试
     *
     * @param view
     */
    public void testOkHttp(View view) {
        testBaseModel(url);
    }

    /**
     * 测试网络请求结果
     *
     * @param v
     */
    public void testReasult(View v) {
        testNest(url);
    }

    /**
     * 测试job
     *
     * @param v
     */
    public void testJob(View v) {
        testObject(url);
    }

    /**
     * 测试BaseModel对象
     *
     * @param url
     */
    private void testBaseModel(String url) {
        OkHttpUtils.getAsyn(url, BaseModel.class, new RequestListenser<BaseModel>() {
            @Override
            public void onLoadError() {
                LogUtils.i("test", "eee");
            }

            @Override
            public void onLoadSuccess(BaseModel job) {

                if (job == null) {
                    LogUtils.i("test", "nulllllllllllllllll");
                } else {
                    LogUtils.i("test", "" + job.getData() + "**********************************");
                }


            }
        });
    }

    /**
     * 测试job对象请求
     *
     * @param url
     */
    private void testObject(String url) {
        OkHttpUtils.getAsyn(url, Job.class, new RequestListenser<Job>() {
            @Override
            public void onLoadError() {
                LogUtils.i("test", "eee");
            }

            @Override
            public void onLoadSuccess(Job job) {

                if (job == null) {
                    LogUtils.i("test", "nulllllllllllllllll");
                } else {
                    LogUtils.i("test", "" + job.getJob_name() + "**********************************");
                }


            }
        });
    }

    /**
     * 测试网络封装
     */
    private void testNest(String url) {
        LogUtils.i("test", "net");
        OkHttpUtils.getAsynString(url, new RequestListenser<String>() {
            @Override
            public void onLoadError() {
                LogUtils.i("test", "eee");
            }

            @Override
            public void onLoadSuccess(String object) {
                LogUtils.i("test", "" + object + "**********************************");
            }
        });
    }


}