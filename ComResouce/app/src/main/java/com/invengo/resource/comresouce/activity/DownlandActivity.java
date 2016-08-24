package com.invengo.resource.comresouce.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.activity.mvpinterface.view.DownlandViewInter;
import com.invengo.resource.comresouce.activity.mvppresenter.DowanlandPresenterImp;
import com.invengo.resource.comresouce.adapter.MyFragmentAdapter;
import com.invengo.resource.comresouce.fragment.HasDownlandFragment;
import com.invengo.resource.comresouce.fragment.NoDownlandFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-18
 * Time: 16:23
 */
public class DownlandActivity extends BaseTwoActivity implements DownlandViewInter {

    @Bind(R.id.vp_downland)
    ViewPager vp_downland;

    @Bind(R.id.tv_has_down)
    TextView tv_has_down;

    @Bind(R.id.tv_need_down)
    TextView tv_need_down;

    @Bind(R.id.re_down_bg1)
    RelativeLayout re_down_bg1;

    @Bind(R.id.re_down_bg2)
    RelativeLayout re_down_bg2;


    private HasDownlandFragment hasDownlandFragment;
    private NoDownlandFragment noDownlandFragment;
    private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
    private MyFragmentAdapter myFragmentAdapter;

    private DowanlandPresenterImp downlandPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downland);
        ButterKnife.bind(this);
        inItHead("数据下载");
        downlandPresenter = new DowanlandPresenterImp(this);
        inItPager();
        setViewPagerChanged(0);
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

    }

    /**
     * 初始化viewpager
     */
    private void inItPager() {
        hasDownlandFragment = HasDownlandFragment.newInstance();
        noDownlandFragment = NoDownlandFragment.newInstance();
        pagerItemList.add(noDownlandFragment);
        pagerItemList.add(hasDownlandFragment);
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), pagerItemList);
        vp_downland.setAdapter(myFragmentAdapter);
        downlandPresenter.inItViewPagerChanger(vp_downland);
    }

    @Override
    public void viewpagerChanger(int position) {
        setViewPagerChanged(position);
    }

    /**
     * 处理滑动效果
     *
     * @param position
     */
    private void setViewPagerChanged(int position) {
        clearView();
        if (position == 0) {
            tv_has_down.setTextColor(getResources().getColor(R.color.green));
            re_down_bg1.setVisibility(View.VISIBLE);
        } else {
            tv_need_down.setTextColor(getResources().getColor(R.color.green));
            re_down_bg2.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 清理view
     */
    private void clearView() {
        tv_need_down.setTextColor(getResources().getColor(R.color.text_color));
        tv_has_down.setTextColor(getResources().getColor(R.color.text_color));
        re_down_bg1.setVisibility(View.INVISIBLE);
        re_down_bg2.setVisibility(View.INVISIBLE);
    }

}