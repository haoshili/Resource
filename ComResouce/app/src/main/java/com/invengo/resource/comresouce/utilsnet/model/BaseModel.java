package com.invengo.resource.comresouce.utilsnet.model;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-15
 * Time: 14:24
 * <p/>
 * 请求下载需要盘库的模式
 */
public class BaseModel {

    private int errorcode;

    private String errorMessage;

    private String data;

    private int statue;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }
}