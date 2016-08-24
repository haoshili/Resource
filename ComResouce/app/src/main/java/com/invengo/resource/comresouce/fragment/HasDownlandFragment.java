package com.invengo.resource.comresouce.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.adapter.DownlanHasdAdapter;
import com.invengo.resource.comresouce.event.DownlandEvent;
import com.invengo.resource.comresouce.fragment.mvpinterface.HasDownlandPresenter;
import com.invengo.resource.comresouce.fragment.mvpinterface.HasDownlandViewInter;
import com.invengo.resource.comresouce.fragment.mvppresenter.HasDownlandImp;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.entity.Job;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HasDownlandFragment extends BaseFragment implements HasDownlandViewInter {

    @Bind(R.id.lv_has)
    ListView lv_has;

    private HasDownlandPresenter presenter;

    public static final HasDownlandFragment newInstance() {
        HasDownlandFragment fragment = new HasDownlandFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_down_has, null);
        ButterKnife.bind(this, view);
        presenter = new HasDownlandImp(this);
        presenter.getHomeData();
        regiest();
        return view;
    }

    public void regiest() {
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEventMainThread(DownlandEvent event) {

        presenter.getHomeData();
        String msg = "onEventMainThread收到了消息：" + event.getmMsg();
        LogUtils.i("test", msg);
    }

    /**
     * 界面初始化
     */
    private void initView() {
//        lv_has.setAdapter(new DownlanHasdAdapter(context));
    }

    @Override
    public void onLoadDataSuccess(List<Job> jobList) {
        lv_has.setAdapter(new DownlanHasdAdapter(context, jobList));
    }
}
