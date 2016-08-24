package com.invengo.resource.comresouce.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.activity.mvpinterface.view.HomeViewInter;
import com.invengo.resource.comresouce.activity.statue.BorrowActivity;
import com.invengo.resource.comresouce.activity.storage.StorageActivity;
import com.invengo.resource.comresouce.adapter.HomeRecyclerAdapter;
import com.invengo.resource.comresouce.test.TestActivity;
import com.invengo.resource.comresouce.utils.DialogueUtils;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.entity.Set;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/16.
 */
public class HomeActivity extends BaseTwoActivity implements HomeViewInter, HomeRecyclerAdapter
        .OnRecyclerViewItemClick {

    @Bind(R.id.id_recyclerview)
    RecyclerView idRecyclerview;

    private  HomeRecyclerAdapter adapter;
    private    List<Set> listSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        inItHead(-1, "远望谷资产管理", -1);
        inItView();
    }

    /**
     * 初始化界面
     */
    private void inItView() {
        idRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        listSet = manager.getSetQuery().getSetShowLsit();
        adapter = new HomeRecyclerAdapter(listSet, this);
        idRecyclerview.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("test","resatrt");
        inItView();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }



    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

    }

    @Override
    public void inHomeGrid(List<Set> listSet) {

    }

    @Override
    public void onItemClick(String name) {
        toast(name+ getDeviceName());
        if (name.equals("设置")) {
            startActivity(new Intent(this, SettingActivity.class));
        }else if(name.equals("入库")){
            startActivity(new Intent(this, StorageActivity.class));
        }else if(name.equals("盘库")){
            startActivity(new Intent(this, CheckActivity.class));
        }else  if(name.equals("下载")){
            startActivity(new Intent(this, DownlandActivity.class));
        }else if(name.equals("消息")){
            startActivity(new Intent(this, TestActivity.class));
        }else if(name.equals("退出")){
            showMessageDia();
        }else if(name.equals("借用")){
            startActivity(new Intent(this, BorrowActivity.class));
        }
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


    private void showMessageDia(){
        DialogueUtils dia = new DialogueUtils(this, DialogueUtils.DialogueType.NORMAL, new DialogueUtils.ClickListenser() {
            @Override
            public void onDialogueClick(int resource, int type, int code, AlertDialog alertDialog) {
                if(resource == R.id.btn_ok){
                    finish();
                }
            }

            @Override
            public void onListViewItemClick(int position, int type, int code, AlertDialog alertDialog) {

            }

            @Override
            public void onTextChangeListenser(String text, int code) {

            }
        });
        dia.setTitle("提示");
        dia.setBody("您确认要退出吗?");
        dia.setFoot("取消","确定");
        dia.showDiaNoCancel();
    }
}
