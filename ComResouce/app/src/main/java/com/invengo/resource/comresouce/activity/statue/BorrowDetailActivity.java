package com.invengo.resource.comresouce.activity.statue;

import android.os.Bundle;
import android.view.View;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;

import butterknife.ButterKnife;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-18
 * Time: 16:23
 * Des:借入借出Activity，以及归还界面，在该界面中可以将设计借入借出。
 */
public class BorrowDetailActivity extends BaseTwoActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_operate);
        ButterKnife.bind(this);
        inItHead("借入借出管理");

    }


    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

    }


}