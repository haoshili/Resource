package com.invengo.resource.comresouce.dao.base;

import com.invengo.resource.comresouce.dao.query.JobQuery;
import com.invengo.resource.comresouce.dao.query.ProductQuery;
import com.invengo.resource.comresouce.dao.query.SetQuery;
import com.invengo.resource.comresouce.dao.query.TidDataQuery;

public class QueryManager {

    private static QueryManager manager;

    private SetQuery setQuery;
    private JobQuery jobQuery;
    private ProductQuery productQuery;
    private TidDataQuery tidDataQuery;

    /**
     * 从新初始化查询工具
     */
    public void reSetQueryManager() {
        setQuery = null;
    }

    public static QueryManager getManager() {
        if (manager == null) {
            manager = new QueryManager();
        }
        return manager;
    }

    public SetQuery getSetQuery() {
        if (setQuery == null) {
            setQuery = new SetQuery(OperationDao.getOperation().getSetDao());
        }
        return setQuery;
    }

    public JobQuery getJobQuery(){
        if(jobQuery == null){
             jobQuery = new JobQuery(OperationDao.getOperation().getJobDao());
        }
        return jobQuery;
    }

    public ProductQuery getProductQuery(){
        if(productQuery == null){
            productQuery = new ProductQuery(OperationDao.getOperation().getProductDao());
        }
        return productQuery;
    }

    public TidDataQuery getTidDataQuery(){
        if(tidDataQuery == null){
            tidDataQuery = new TidDataQuery(OperationDao.getOperation().getTidData());
        }
        return tidDataQuery;
    }

}
