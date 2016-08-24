package com.invengo.resource.comresouce.utils;

import android.content.Context;

import com.invengo.resource.comresouce.MyApplication;
import com.invengo.resource.comresouce.dao.base.OperationDao;
import com.invengo.resource.comresouce.dao.base.QueryManager;


public class BaseDao {

    public Context context = MyApplication.getContext();
    public static OperationDao operationDao = OperationDao.getOperation();
    public static QueryManager manager = QueryManager.getManager();

    public static void resetData() {
        operationDao = OperationDao.getOperation();
        manager = QueryManager.getManager();
    }


}
