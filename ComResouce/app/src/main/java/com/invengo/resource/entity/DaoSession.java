package com.invengo.resource.entity;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.invengo.resource.entity.Set;
import com.invengo.resource.entity.Job;
import com.invengo.resource.entity.Product;
import com.invengo.resource.entity.TidData;

import com.invengo.resource.entity.SetDao;
import com.invengo.resource.entity.JobDao;
import com.invengo.resource.entity.ProductDao;
import com.invengo.resource.entity.TidDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig setDaoConfig;
    private final DaoConfig jobDaoConfig;
    private final DaoConfig productDaoConfig;
    private final DaoConfig tidDataDaoConfig;

    private final SetDao setDao;
    private final JobDao jobDao;
    private final ProductDao productDao;
    private final TidDataDao tidDataDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        setDaoConfig = daoConfigMap.get(SetDao.class).clone();
        setDaoConfig.initIdentityScope(type);

        jobDaoConfig = daoConfigMap.get(JobDao.class).clone();
        jobDaoConfig.initIdentityScope(type);

        productDaoConfig = daoConfigMap.get(ProductDao.class).clone();
        productDaoConfig.initIdentityScope(type);

        tidDataDaoConfig = daoConfigMap.get(TidDataDao.class).clone();
        tidDataDaoConfig.initIdentityScope(type);

        setDao = new SetDao(setDaoConfig, this);
        jobDao = new JobDao(jobDaoConfig, this);
        productDao = new ProductDao(productDaoConfig, this);
        tidDataDao = new TidDataDao(tidDataDaoConfig, this);

        registerDao(Set.class, setDao);
        registerDao(Job.class, jobDao);
        registerDao(Product.class, productDao);
        registerDao(TidData.class, tidDataDao);
    }
    
    public void clear() {
        setDaoConfig.getIdentityScope().clear();
        jobDaoConfig.getIdentityScope().clear();
        productDaoConfig.getIdentityScope().clear();
        tidDataDaoConfig.getIdentityScope().clear();
    }

    public SetDao getSetDao() {
        return setDao;
    }

    public JobDao getJobDao() {
        return jobDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public TidDataDao getTidDataDao() {
        return tidDataDao;
    }

}