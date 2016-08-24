package com.invengo.resource.comresouce.dao.query;

import com.invengo.resource.entity.TidData;
import com.invengo.resource.entity.TidDataDao;

import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-18
 * Time: 09:40
 */
public class TidDataQuery {

    private TidDataDao tidDataDao;

    public TidDataQuery(TidDataDao tidDataDao) {
        this.tidDataDao = tidDataDao;
    }


    /**
     * 通过产品id查询出来所有的tid数据集合
     *
     * @param tidID 产品id
     * @return
     */
    public long getProductDataCount(String tidID) {
        return tidDataDao.queryBuilder().where(TidDataDao.Properties.Product_id.eq(tidID)).count();
    }

    /**
     * 查询盘库的相关数据
     *
     * @param isCount   该数据是否盘库
     * @param productID 产品id
     * @return
     */
    public long getProductTidStatueCount(boolean isCount, String productID) {
        return tidDataDao.queryBuilder()
                .where(TidDataDao.Properties.Product_id.eq(productID),
                        TidDataDao.Properties.Tid_has_read.eq(isCount)).count();
    }

    /**
     * 通过tid查询来检测是否含有该数据
     *
     * @param tidData tid数据
     * @return
     */
    public TidData checkHasData(String tidData,String productId) {

       List<TidData> tidData1 = tidDataDao.queryBuilder()
               .where(TidDataDao.Properties.Tid_tid.eq(tidData),TidDataDao.Properties.Product_id.eq(productId))
               .list();

        if(tidData1.size() == 0){
            return  null;
        }else {
            return  tidData1.get(0 );
        }
    }

}