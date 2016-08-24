package com.invengo.resource.comresouce.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.adapter.SettingHomeRecyclerAdapter;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.comresouce.utils.Value;
import com.invengo.resource.entity.Set;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-18
 * Time: 16:23
 */
public class  SettingHomeActivity extends BaseTwoActivity implements SettingHomeRecyclerAdapter.OnRecyclerViewSettingHomeItemClick {

    @Bind(R.id.id_recyclerview)
    RecyclerView idRecyclerview;

    private  List<Set> setList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        inItHead("设置主界面功能显示");
        inItView();
    }

    /**
     * 初始化界面
     */
    private void inItView() {
        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));
         setList = manager.getSetQuery().getSetSysShowLsit();
        idRecyclerview.setAdapter(new SettingHomeRecyclerAdapter(setList, this));
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

    }

    @Override
    public void onItemClick(String name) {

    }

    @Override
    public void onCheckListenser(int position, boolean isChecked) {

        LogUtils.i(Value.L_SETTTING,"position*******"+position+"******ischecked*****"+isChecked);
        toast("position"+position+"ischecked"+isChecked);

        Set set = setList.get(position);
        set.setSet_set_show(isChecked);
        operate.getSetDao().update(set);
    }
}