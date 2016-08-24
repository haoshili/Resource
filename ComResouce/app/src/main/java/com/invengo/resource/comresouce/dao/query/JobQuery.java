package com.invengo.resource.comresouce.dao.query;

import com.invengo.resource.entity.Job;
import com.invengo.resource.entity.JobDao;

import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-18
 * Time: 09:40
 */
public class JobQuery {

    private JobDao jobDao;

    public JobQuery(JobDao jobDao) {
        this.jobDao = jobDao;
    }


    public List<Job> getHasDownlandData() {
        return jobDao.queryBuilder().where(JobDao.Properties.Id.isNotNull(),JobDao.Properties.Job_complete.eq(false)).orderDesc(JobDao
                .Properties.Job_ctime)
                .list();
    }

}