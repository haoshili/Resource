package com.invengo.resource.comresouce.activity.storage;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.AddProductActivity;
import com.invengo.resource.comresouce.activity.EditProductActivity;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.activity.mvpinterface.StoragePresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.view.StorageViewInter;
import com.invengo.resource.comresouce.activity.mvppresenter.StoragePresenterImp;
import com.invengo.resource.comresouce.adapter.HomeRecyclerSwipeAdapter;
import com.invengo.resource.comresouce.adapter.StorageAdapter;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.comresouce.utils.Value;
import com.invengo.resource.entity.Product;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class StorageActivity extends BaseTwoActivity implements HomeRecyclerSwipeAdapter
        .OnRecyclerViewItemClick, StorageViewInter, StorageAdapter.StorageImageClickLinstenser {

    @Bind(R.id.lv_check)
    ListView lv_check;

    @Bind(R.id.tv_message)
    TextView tv_message;

    private List<Product> productList;
    private StorageAdapter adapter;
    private StoragePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ButterKnife.bind(this);
        inItHead("入库管理", R.drawable.ic_add_white_36dp);
        presenter = new StoragePresenterImp(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getAlldata();
    }

    private void inItView() {
//        lv_check.setAdapter(new StorageAdapter(this,));
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(String name) {

    }

    @OnItemClick(R.id.lv_check)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, StorageScanThingActivity.class);
        intent.putExtra("productId", productList.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onLoadDataSuccess(List<Product> jobList) {
        LogUtils.i(Value.L_STORAGE, "jobList********" + jobList.size());
        this.productList = jobList;
        adapter = new StorageAdapter(this, jobList, this);
        lv_check.setAdapter(adapter);

        showMessage();
    }

    @Override
    public void onDelItem(int position) {
        Product product = productList.get(position);
        product.setProduct_upload(true);
        operate.getProductDao().update(product);
        productList.remove(position);
        adapter.notifyDataSetChanged();
        showMessage();
    }

    @Override
    public void onEditProduct(int position) {
        Intent intent = new Intent(this, EditProductActivity.class);
        intent.putExtra("productId",productList.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onImageClick(int position) {
        toast(position + "");
        presenter.showDialogue(this, position);
    }

    private void showMessage() {
        if (productList.size() != 0) {
            tv_message.setVisibility(View.INVISIBLE);
        } else {
            tv_message.setVisibility(View.VISIBLE);
        }
    }

}