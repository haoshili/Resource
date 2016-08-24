package com.invengo.resource.comresouce.activity.mvppresenter;

import android.support.v4.view.ViewPager;

import com.invengo.resource.comresouce.activity.mvpinterface.view.DownlandViewInter;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-13
 * Time: 13:59
 */
public class DowanlandPresenterImp {


    private DownlandViewInter listenser;

    public DowanlandPresenterImp(DownlandViewInter l) {
        this.listenser = l;
    }

    public void inItViewPagerChanger(ViewPager vp_downland) {

        vp_downland.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                listenser.viewpagerChanger(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}  