package com.invengo.resource.comresouce.activity.mvppresenter;

import com.invengo.resource.comresouce.activity.mvpinterface.MainPresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.MainVailDataActor;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-19
 * Time: 14:27
 */
public class VailDataActorImp implements MainVailDataActor {

    private MainPresenter.VailDataInterface vailDataInterface;

    public VailDataActorImp(MainPresenter.VailDataInterface vailDataInterface) {
        this.vailDataInterface = vailDataInterface;
    }

    @Override
    public void valiData(String userName, String pwd) {
        vailDataInterface.onSuccess();
    }
}