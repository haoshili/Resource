package com.invengo.resource.comresouce.activity.mvppresenter;

import com.invengo.resource.comresouce.activity.mvpinterface.CheckDetailPresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.CheckPresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.view.CheckDetailViewInter;
import com.invengo.resource.comresouce.activity.mvpinterface.view.CheckViewInter;
import com.invengo.resource.comresouce.utils.BaseDao;
import com.invengo.resource.entity.Job;
import com.invengo.resource.entity.TidData;

import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-13
 * Time: 13:59
 */
public class CheckDetailPresenterImp extends BaseDao implements CheckDetailPresenter {

    private CheckDetailViewInter listenser;


    public CheckDetailPresenterImp(CheckDetailViewInter listenser) {
        this.listenser = listenser;
    }


    @Override
    public void checkData(String tid,String productId) {
        TidData tidData = manager.getTidDataQuery().checkHasData(tid,productId);

        if (tidData != null) {
            //存在数据，将该数据设置为读取到，
            tidData.setTid_has_read(true);
            operationDao.getTidData().update(tidData);
            listenser.onCheckSuccess();
        } else {
            listenser.onCheckFailed();
        }
    }

    @Override
    public void upLoadDataToServer() {
        listenser.onCheckSuccess();
    }


}