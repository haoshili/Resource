package com.invengo.resource.comresouce.activity.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import com.invengo.resource.comresouce.dao.base.OperationDao;
import com.invengo.resource.comresouce.dao.base.QueryManager;

public class BaseActivity extends FragmentActivity {

    public OperationDao operate;
    public QueryManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        operate = OperationDao.getOperation();
        manager = QueryManager.getManager();


    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
