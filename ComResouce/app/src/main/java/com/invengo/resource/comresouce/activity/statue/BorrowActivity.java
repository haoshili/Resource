package com.invengo.resource.comresouce.activity.statue;

import android.content.Intent;
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
 * Des:借入借出Activity，以及归还界面，在该界面中可以将设计借入借出。
 */
public class BorrowActivity extends BaseTwoActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);
        ButterKnife.bind(this);
        inItHead("扫描标签");

    }

    /**
     * 读取标签
     * @param view
     */
    public void readTag(View view){

        Intent intent = new Intent(this,BorrowDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

    }


}