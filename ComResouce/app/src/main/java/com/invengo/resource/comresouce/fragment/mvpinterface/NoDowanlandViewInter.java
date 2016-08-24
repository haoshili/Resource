package com.invengo.resource.comresouce.fragment.mvpinterface;

import com.invengo.resource.entity.Job;

import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */
public interface NoDowanlandViewInter {

    //请求网络数据失败
    void onLoandHomeDataError();

    //请求网络数据成功
    void onLoadHomeDataSuccess(List<Job> listJob);

    //数据下载成功
    void onDownlandComplete(int position);

    //数据下载失败
    void onDownlandError(int position);
}
