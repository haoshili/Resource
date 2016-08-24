package com.invengo.resource.comresouce.utilsnet.netinterface;

/**
 * Created by Administrator on 2016/5/19.
 */
public interface RequestListenser<T> {

    void onLoadError();

    void onLoadSuccess(T object);
}
