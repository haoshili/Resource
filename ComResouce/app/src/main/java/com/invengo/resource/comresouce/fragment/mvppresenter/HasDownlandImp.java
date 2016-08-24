package com.invengo.resource.comresouce.fragment.mvppresenter;

import com.invengo.resource.comresouce.fragment.mvpinterface.HasDownlandPresenter;
import com.invengo.resource.comresouce.fragment.mvpinterface.HasDownlandViewInter;
import com.invengo.resource.comresouce.utils.BaseDao;
import com.invengo.resource.entity.Job;

import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-15
 * Time: 13:46
 * <p/>
 * 执行者，现在分一层就行了，感觉用不了那么多层。
 */
public class HasDownlandImp extends BaseDao implements HasDownlandPresenter{

    private HasDownlandViewInter listenser;

    public HasDownlandImp(HasDownlandViewInter listenser){
        this.listenser = listenser;
    }
    @Override
    public void getHomeData() {
        List<Job> jobList = manager.getJobQuery().getHasDownlandData();
        listenser.onLoadDataSuccess(jobList);
    }
}