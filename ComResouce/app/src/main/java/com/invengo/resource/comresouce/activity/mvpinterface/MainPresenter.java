package com.invengo.resource.comresouce.activity.mvpinterface;

/**
 * Created by Administrator on 2016/5/19.
 */
public interface MainPresenter {
    void valiData(String userName, String pwd);

    public  interface VailDataInterface{
        void onLoginErr(String msg);
        void onSuccess();
    }

    public interface InFirstDataInterface{
        void onFirstComplete();
    }
}
