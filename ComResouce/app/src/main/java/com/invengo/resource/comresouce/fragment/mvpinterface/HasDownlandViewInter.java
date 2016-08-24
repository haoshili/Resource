package com.invengo.resource.comresouce.fragment.mvpinterface;

import com.invengo.resource.entity.Job;

import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */
public interface HasDownlandViewInter  {

    void onLoadDataSuccess(List<Job> jobList);

}
