package com.invengo.resource.comresouce.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.adapter.HomeRecyclerSwipeAdapter;
import com.invengo.resource.entity.Product;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EditProductActivity extends BaseTwoActivity implements HomeRecyclerSwipeAdapter.OnRecyclerViewItemClick {

    @Bind(R.id.et_product_name)
    EditText productName;

    @Bind(R.id.et_product_type)
    EditText productType;

    private String productId;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_add);
        ButterKnife.bind(this);
        inItHead("编辑产品", R.drawable.ic_done_white_24dp);

        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");
        inItView();
    }

    private void inItView() {

        product = manager.getProductQuery().getProduct(productId);
        productName.setText(product.getProduct_name());
        productType.setText(product.getProduct_type());
    }

    @Override
    public void subTitleOne(View v) {
        finish();
    }

    @Override
    public void subTitleTwo(View v) {
        String name = productName.getText().toString();
        String type = productType.getText().toString();

        product.setProduct_name(name);
        product.setProduct_type(type);
        operate.getProductDao().update(product);

        finish();

//        Intent intent = new Intent(this, StorageScanThingActivity.class);
//        intent.putExtra("productId", product.getId());
//        startActivity(intent);
    }

    @Override
    public void onItemClick(String name) {

    }
}