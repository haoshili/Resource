package com.invengo.resource.comresouce.dao.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.invengo.resource.comresouce.MyApplication;
import com.invengo.resource.comresouce.dao.DataSQL;
import com.invengo.resource.entity.DaoMaster;
import com.invengo.resource.entity.DaoSession;
import com.invengo.resource.entity.JobDao;
import com.invengo.resource.entity.ProductDao;
import com.invengo.resource.entity.SetDao;
import com.invengo.resource.entity.TidDataDao;

import de.greenrobot.dao.identityscope.IdentityScopeType;


public class OperationDao {

    private static OperationDao operate;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    private static SetDao setDao;
    private static JobDao jobDao;
    private static ProductDao productDao;
    private  static TidDataDao tidDataDao;

    public static OperationDao getOperation() {

        if (operate == null) {
            operate = new OperationDao();
        }
        return operate;
    }


    public void reSetOperation() {
        operate = null;
        operate = new OperationDao();
    }

    private OperationDao() {

        Context context = MyApplication.getContext();
        DataSQL helper = new DataSQL(context, "resources", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        if (daoSession != null) {
            daoSession = daoMaster.newSession(IdentityScopeType.None);
        } else {
            daoSession = daoMaster.newSession();
        }
    }

    public SQLiteDatabase getDatabase() {
        return daoSession.getDatabase();
    }

    public SetDao getSetDao() {
        if (setDao == null) {
            setDao = daoSession.getSetDao();
        }
        return setDao;
    }

    public JobDao getJobDao() {
        if (jobDao == null) {
            jobDao = daoSession.getJobDao();
        }
        return jobDao;
    }

    public ProductDao getProductDao(){
        if(productDao == null){
            productDao = daoSession.getProductDao();
        }
        return productDao;
    }

    public TidDataDao getTidData(){
        if(tidDataDao == null){
            tidDataDao = daoSession.getTidDataDao();
        }
        return  tidDataDao;
    }
}
