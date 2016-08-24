package com.invengo.resource.comresouce.test.testcase;

import android.test.InstrumentationTestCase;

import com.invengo.resource.comresouce.utils.GsonUtil;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.entity.TidData;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-17
 * Time: 09:19
 */
public class TestJson extends InstrumentationTestCase {

    @Before
    public void setUp() throws Exception {

    }


    public void test1(){

        List<TidData> lsitTiddata = new ArrayList<>();

        TidData tidData = new TidData();
        tidData.setTid_has_read(false);
        tidData.setTid_tid("dsfadfa");

        String s = GsonUtil.objectToJson(tidData);

        LogUtils.i("test",s);
    }

}  