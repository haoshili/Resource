package com.invengo.resource.comresouce.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.adapter.DownlandRecyclerAdapter;
import com.invengo.resource.comresouce.event.DownlandEvent;
import com.invengo.resource.comresouce.fragment.mvpinterface.NoDowanlandViewInter;
import com.invengo.resource.comresouce.fragment.mvpinterface.NoDownlandPresenter;
import com.invengo.resource.comresouce.fragment.mvppresenter.NoDownlandImp;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.comresouce.utils.Value;
import com.invengo.resource.comresouce.view.ThreePointLoadingView;
import com.invengo.resource.entity.Job;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NoDownlandFragment extends BaseFragment implements DownlandRecyclerAdapter
        .OnRecyclerViewItemClick, NoDowanlandViewInter {


    @Bind(R.id.id_recyclerview)
    RecyclerView id_recyclerview;

    @Bind(R.id.three_loading)
    ThreePointLoadingView three_loading;

    @Bind(R.id.tv_remind)
    TextView tv_remind;

    private List<Job> listJob;

    private DownlandRecyclerAdapter downlandRecyclerAdapter;

    private NoDownlandPresenter noDownlandPresenter;

    public static final NoDownlandFragment newInstance() {
        NoDownlandFragment fragment = new NoDownlandFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_down_no, null);
        ButterKnife.bind(this, view);
        inItView();
        noDownlandPresenter = new NoDownlandImp(this);

        //请求服务器数据
        noDownlandPresenter.getNoDowanlandShowData();
        return view;
    }


    /**
     * 初始化界面
     */
    private void inItView() {

    }

    @Override
    public void ontImageViewClick(int position, DownlandRecyclerAdapter.ViewHolder viewHolder) {
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        viewHolder.iv_checked.setVisibility(View.INVISIBLE);
        noDownlandPresenter.downlandData("", position);
    }

    @Override
    public void onItemClick(int position) {

    }

    /**
     * 数据请求错误，点击图片重新请求网络
     */
    @Override
    public void onLoandHomeDataError() {

//        three_loading.setVisibility(View.INVISIBLE);

    }

    /**
     * 数据请求成功
     *
     * @param listJob
     */
    @Override
    public void onLoadHomeDataSuccess(List<Job> listJob) {
        LogUtils.i(Value.L_NOLANDDATA, "dataSize" + listJob.size());
        three_loading.setVisibility(View.INVISIBLE);
        this.listJob = listJob;
        id_recyclerview.setLayoutManager(new LinearLayoutManager(context));
        downlandRecyclerAdapter = new DownlandRecyclerAdapter(this, listJob);
        id_recyclerview.setAdapter(downlandRecyclerAdapter);
    }

    /**
     * 数据下载成功
     *
     * @param position
     */
    @Override
    public void onDownlandComplete(int position) {
        LogUtils.i(Value.L_NOLANDDATA, "position" + position);
        listJob.remove(position);

        if(listJob.size() == 0){
            tv_remind.setText("没有需要下载的数据了！");
            tv_remind.setVisibility(View.VISIBLE);
        }
        downlandRecyclerAdapter.notifyItemRemoved(position);
        EventBus.getDefault().post(new DownlandEvent("test"));
    }

    /**
     * 数据下载失败
     */
    @Override
    public void onDownlandError(int position) {
        //失败就是就停止那个转圈就行了，还有就是把整个的图标修改一下
    }
}
