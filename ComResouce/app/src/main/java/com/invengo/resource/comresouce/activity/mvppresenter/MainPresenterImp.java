package com.invengo.resource.comresouce.activity.mvppresenter;

import com.invengo.resource.comresouce.activity.mvpinterface.MainPresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.MainVailDataActor;
import com.invengo.resource.comresouce.activity.mvpinterface.view.MainViewInter;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-19
 * Time: 13:40
 */
public class MainPresenterImp implements MainPresenter, MainPresenter.VailDataInterface, MainPresenter.InFirstDataInterface {

    private MainViewInter viewInterMain;
    private MainVailDataActor vailDataActor;

    public MainPresenterImp(MainViewInter viewInterMain) {
        this.viewInterMain = viewInterMain;
        this.vailDataActor = new VailDataActorImp(this);
    }

    /////////////////////////////
    //方法接口
    /////////////////////////////

    @Override
    public void valiData(String userName, String pwd) {
        vailDataActor.valiData(userName, pwd);
    }

    /////////////////////////////
    //验证登录使用的接口
    /////////////////////////////

    @Override
    public void onLoginErr(String msg) {

    }

    @Override
    public void onSuccess() {
        new MainInFirstDataActorImp(this).inItdata();
    }

    @Override
    public void onFirstComplete() {
        viewInterMain.onLoginSuccess();
    }
}