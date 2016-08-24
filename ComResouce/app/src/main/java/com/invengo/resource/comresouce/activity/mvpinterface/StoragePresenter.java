package com.invengo.resource.comresouce.activity.mvpinterface;

import android.content.Context;

/**
 * Created by Administrator on 2016/5/19.
 */
public interface StoragePresenter {

    void getAlldata();

    /**
     * 显示对话框
     */
    void showDialogue(Context context,int position);
}
