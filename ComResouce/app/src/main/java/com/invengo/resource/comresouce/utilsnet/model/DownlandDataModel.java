package com.invengo.resource.comresouce.utilsnet.model;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-15
 * Time: 14:24
 * <p/>
 * 请求下载需要盘库的模式
 */
public class DownlandDataModel extends BaseModel {

    private String job;

    private String product;

    private String tidDatas;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTidDatas() {
        return tidDatas;
    }

    public void setTidDatas(String tidDatas) {
        this.tidDatas = tidDatas;
    }
}