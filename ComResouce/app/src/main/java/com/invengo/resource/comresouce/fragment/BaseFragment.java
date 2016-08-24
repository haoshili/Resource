package com.invengo.resource.comresouce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.invengo.resource.comresouce.dao.base.OperationDao;
import com.invengo.resource.comresouce.dao.base.QueryManager;

public class BaseFragment extends Fragment {

    public Context context;

    public QueryManager manager;
    public OperationDao operate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        operate = OperationDao.getOperation();
        manager = QueryManager.getManager();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
