package com.invengo.resource.comresouce.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.activity.storage.StorageScanThingActivity;
import com.invengo.resource.comresouce.adapter.HomeRecyclerSwipeAdapter;
import com.invengo.resource.comresouce.utils.UUIDUtils;
import com.invengo.resource.entity.Product;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AddProductActivity extends BaseTwoActivity implements HomeRecyclerSwipeAdapter.OnRecyclerViewItemClick {

    @Bind(R.id.et_product_name)
    EditText productName;

    @Bind(R.id.et_product_type)
    EditText productType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_add);
        ButterKnife.bind(this);
        inItHead("添加产品",R.drawable.ic_done_white_24dp);
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
        String name = productName.getText().toString();
        String type = productType.getText().toString();

        Product product = new Product();
        product.setId(UUIDUtils.getUUId());
        product.setProduct_name(name);
        product.setProduct_type(type);
        product.setProduct_upload(false);
        product.setProduct_storage(true);
        operate.getProductDao().insert(product);

        finish();

        Intent intent = new Intent(this, StorageScanThingActivity.class);
        intent.putExtra("productId",product.getId());
        startActivity(intent);
    }

    @Override
    public void onItemClick(String name) {

    }
}