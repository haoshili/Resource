package com.invengo.resource.comresouce.activity.storage;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.adapter.HomeRecyclerSwipeAdapter;
import com.invengo.resource.entity.Product;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ThingsDetailActivity extends BaseTwoActivity implements HomeRecyclerSwipeAdapter.OnRecyclerViewItemClick {

    @Bind(R.id.tv_product_type)
    TextView tv_product_type;

    @Bind(R.id.tv_product_name)
    TextView tv_product_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_detail);
        ButterKnife.bind(this);
        inItHead("资产详情");
        inItView();
    }

    private void inItView() {
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");
        Product product = manager.getProductQuery().getProduct(productId);

        tv_product_name.setText(product.getProduct_name());
        tv_product_type.setText(product.getProduct_type());
    }

    @Override
    public void subTitleOne(View v) {
        finish();
    }

    @Override
    public void subTitleTwo(View v) {

    }

    @Override
    public void onItemClick(String name) {

    }
}