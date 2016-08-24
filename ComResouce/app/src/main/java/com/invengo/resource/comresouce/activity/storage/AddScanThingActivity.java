package com.invengo.resource.comresouce.activity.storage;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddScanThingActivity extends BaseTwoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_detail);
        ButterKnife.bind(this);
        inItHead("入库扫描", R.drawable.ic_done_white_24dp);
        inItView();
    }

    private void inItView() {

    }

    @Override
    public void subTitleOne(View v) {
        finish();
    }

    @Override
    public void subTitleTwo(View v) {

    }

    @OnClick(R.id.re_for_detail)
    public void forDetail(View view) {
        Intent intent = new Intent(this,ThingsDetailActivity.class);
        startActivity(intent);
    }

}