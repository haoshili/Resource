package com.invengo.resource.comresouce.test.operater;

import com.invengo.resource.comresouce.utils.GsonUtil;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.comresouce.utilsnet.model.BaseModel;
import com.invengo.resource.comresouce.utilsnet.model.DownlandDataModel;
import com.invengo.resource.entity.Job;
import com.invengo.resource.entity.Product;
import com.invengo.resource.entity.TidData;

import java.util.ArrayList;
import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-15
 * Time: 09:53
 * <p/>
 * 创建json数据
 */
public class CreatJsonData {

    /**
     * 创建json数据形式
     */
    public void creatOrderJsonData() {
        LogUtils.i("test", "正在生成数据");
        creatDownlandData();
    }

    /**
     * 创建网络强求放回的数据结构
     */
    public void creatRequestData() {

        BaseModel model = new BaseModel();
        model.setData(getData());
        model.setErrorcode(0);
        model.setErrorMessage("tt");
        model.setStatue(1);

        String utils = GsonUtil.objectToJson(model);

        LogUtils.i("test", utils);
    }

    private String getData() {
        List<Job> listJob = new ArrayList<>();


        Job job1 = new Job();
        job1.setJob_name("软件部盘点正在使用的电脑");
        listJob.add(job1);

//        Job job2 = new Job();
//        job2.setJob_name("软件部盘点正在使用的电脑");
//        listJob.add(job2);
//
//        Job job3 = new Job();
//        job3.setJob_name("软件部盘点正在使用的电脑");
//        listJob.add(job3);

        String utils = GsonUtil.objectToJson(listJob);
        LogUtils.i("test", utils);
        return utils;

    }

    /**
     * 创建需要下载的数据
     */
    private void creatDownlandData() {

        Job job = new Job();
        job.setJob_name("testcase");
        LogUtils.i("test", GsonUtil.objectToJson(job));

        Product product = new Product();
        product.setProduct_name("sssss");
        LogUtils.i("test", GsonUtil.objectToJson(product));

        TidData tidData = new TidData();
        tidData.setTid_has_read(false);
        tidData.setTid_tid("TTTTTT");
        LogUtils.i("test", GsonUtil.objectToJson(tidData));

        TidData tidData1 = new TidData();
        tidData1.setTid_has_read(false);
        tidData1.setTid_tid("dsfadfa");
        LogUtils.i("test", GsonUtil.objectToJson(tidData1));

        List<TidData> listTidData = new ArrayList<>();
        listTidData.add(tidData);
        listTidData.add(tidData1);

        DownlandDataModel model = new DownlandDataModel();
        model.setData(getData());
        model.setErrorcode(0);
        model.setErrorMessage("tt");
        model.setStatue(1);
        model.setJob(GsonUtil.objectToJson(job));
        model.setProduct(GsonUtil.objectToJson(product));
        model.setTidDatas(GsonUtil.objectToJson(listTidData));

        LogUtils.i("test", GsonUtil.objectToJson(model));
    }
}  