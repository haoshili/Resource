package com.invengo.resource.comresouce.activity.mvppresenter;

import com.invengo.resource.comresouce.activity.mvpinterface.CheckPresenter;
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
public class CheckPresenterImp extends BaseDao implements CheckPresenter {

    private CheckViewInter listenser;

    public CheckPresenterImp(CheckViewInter listenser) {
        this.listenser = listenser;
    }

    @Override
    public void getCheckData() {

        List<Job> jobList = manager.getJobQuery().getHasDownlandData();
        listenser.onLoadDataSuccess(jobList);
    }




}