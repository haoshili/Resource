package com.invengo.resource.comresouce.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.activity.mvpinterface.CheckPresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.view.CheckViewInter;
import com.invengo.resource.comresouce.activity.mvppresenter.CheckPresenterImp;
import com.invengo.resource.comresouce.adapter.CheckAdapter;
import com.invengo.resource.comresouce.adapter.HomeRecyclerSwipeAdapter;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.entity.Job;
import com.invengo.resource.entity.Product;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class CheckActivity extends BaseTwoActivity implements HomeRecyclerSwipeAdapter
        .OnRecyclerViewItemClick,CheckViewInter {

    @Bind(R.id.lv_check)
    ListView lv_check;

    @Bind(R.id.tv_message)
    TextView tv_message;

    private List<Job> jobList;
    private Product product;

    private CheckAdapter adapter;

    private CheckPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ButterKnife.bind(this);
        inItHead("资产盘库", R.drawable.ic_cloud_download_white_36dp);
        presenter = new CheckPresenterImp(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getCheckData();
    }

    @OnItemClick(R.id.lv_check)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Job job = jobList.get(position);
        job.getProducts();
        product = job.getProducts().get(0);
        Intent intent = new Intent(this, CheckDetailActivity.class);
        intent.putExtra("productId",product.getId());

        LogUtils.i("test","cpId***"+product.getId());
        startActivity(intent);
    }

    private void inItView() {

//        lv_check.setAdapter(new CheckAdapter(this));
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {
        Intent intent = new Intent(this,DownlandActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(String name) {

    }

    @Override
    public void onLoadDataSuccess(List<Job> jobList) {

        if(jobList.size() == 0){
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText("您没有需要盘点的任务！");
        }else{
            tv_message.setVisibility(View.INVISIBLE);
        }

        this.jobList = jobList;
        adapter = new CheckAdapter(CheckActivity.this,jobList);
        lv_check.setAdapter(adapter);
    }
}