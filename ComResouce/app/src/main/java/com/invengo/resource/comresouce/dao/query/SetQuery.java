package com.invengo.resource.comresouce.dao.query;

import com.invengo.resource.entity.Set;
import com.invengo.resource.entity.SetDao;

import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-18
 * Time: 09:40
 */
public class SetQuery {

    private SetDao setDao;

    public SetQuery(SetDao setDao) {
        this.setDao = setDao;
    }

    /**
     * 获取到显示
     *
     * @return
     */
    public List<Set> getSetShowLsit() {
        return setDao.queryBuilder().where(SetDao.Properties.Set_set_show.eq(true),SetDao.Properties.Set_sys_show.eq(true)).list();
    }

    /**
     * 获取到显示
     *
     * @return
     */
    public List<Set> getSetSysShowLsit() {
        return setDao.queryBuilder().where(SetDao.Properties.Set_sys_show.eq(true)).list();
    }


}