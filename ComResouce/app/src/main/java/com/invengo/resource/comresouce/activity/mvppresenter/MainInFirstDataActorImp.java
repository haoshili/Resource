package com.invengo.resource.comresouce.activity.mvppresenter;

import com.invengo.resource.comresouce.activity.mvpinterface.MainPresenter;
import com.invengo.resource.comresouce.utils.BaseDao;
import com.invengo.resource.comresouce.utils.ShareSaveUtils;
import com.invengo.resource.entity.Set;

import java.util.ArrayList;
import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-19
 * Time: 14:27
 */
public class MainInFirstDataActorImp extends BaseDao {

    private MainPresenter.InFirstDataInterface inFirstDataInterface;

    public MainInFirstDataActorImp(MainPresenter.InFirstDataInterface vailDataInterface) {
        this.inFirstDataInterface = vailDataInterface;
    }

    public void inItdata() {
        if (ShareSaveUtils.getIntFromTable("first") == -1) {
            inItData();
            ShareSaveUtils.saveIntInTable("first", 100);
        }
        inFirstDataInterface.onFirstComplete();
    }

    /**
     * 初始化数据
     */
    private void inItData() {
        List<Set> listSet = new ArrayList<>();


        Set set1 = new Set();
        set1.setSet_image_name("ic_home_storage");
        set1.setSet_name("入库");
        set1.setSet_set_show(true);
        set1.setSet_sys_show(true);
        listSet.add(set1);

        Set set3 = new Set();
        set3.setSet_image_name("ic_home_check");
        set3.setSet_name("盘库");
        set3.setSet_set_show(true);
        set3.setSet_sys_show(true);
        listSet.add(set3);

        Set set2 = new Set();
        set2.setSet_image_name("ic_home_downland");
        set2.setSet_name("下载");
        set2.setSet_set_show(true);
        set2.setSet_sys_show(true);
        listSet.add(set2);

        Set set4 = new Set();
        set4.setSet_image_name("ic_home_borrow");
        set4.setSet_name("借用");
        set4.setSet_set_show(true);
        set4.setSet_sys_show(true);
        listSet.add(set4);

        Set set5 = new Set();
        set5.setSet_image_name("ic_home_repair");
        set5.setSet_name("维修");
        set5.setSet_set_show(true);
        set5.setSet_sys_show(true);
        listSet.add(set5);

        Set set6 = new Set();
        set6.setSet_image_name("ic_home_bad");
        set6.setSet_name("报废");
        set6.setSet_set_show(true);
        set6.setSet_sys_show(true);
        listSet.add(set6);

        Set set7 = new Set();
        set7.setSet_image_name("ic_home_message");
        set7.setSet_name("消息");
        set7.setSet_set_show(true);
        set7.setSet_sys_show(true);
        listSet.add(set7);

        Set set8 = new Set();
        set8.setSet_image_name("ic_home_help");
        set8.setSet_name("帮助");
        set8.setSet_set_show(true);
        set8.setSet_sys_show(true);
        listSet.add(set8);


        Set set10 = new Set();
        set10.setSet_image_name("ic_home_setting");
        set10.setSet_name("设置");
        set10.setSet_set_show(true);
        set10.setSet_sys_show(true);
        listSet.add(set10);


        Set set9 = new Set();
        set9.setSet_image_name("ic_home_logout");
        set9.setSet_name("退出");
        set9.setSet_set_show(true);
        set9.setSet_sys_show(true);
        listSet.add(set9);

        operationDao.getSetDao().deleteAll();
        operationDao.getSetDao().insertInTx(listSet);
    }
}