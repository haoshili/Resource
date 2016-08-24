package com.invengo.resource.comresouce.activity.storage;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.atid.lib.dev.rfid.type.ActionState;
import com.atid.lib.dev.rfid.type.ConnectionState;
import com.atid.lib.dev.rfid.type.ResultCode;
import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.ReadTagNewActivity;
import com.invengo.resource.comresouce.utils.DialogueUtils;
import com.invengo.resource.comresouce.utils.UUIDUtils;
import com.invengo.resource.entity.Product;
import com.invengo.resource.entity.TidData;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class StorageScanThingActivity extends ReadTagNewActivity implements ReadTagNewActivity.ReadTagListenser {


    @Bind(R.id.tv_product_name)
    TextView tv_product_name;

    @Bind(R.id.tv_resource_count_detail)
    TextView tv_resource_count_detail;

    @Bind(R.id.tv_resource_count_detail_real)
    TextView tv_resource_count_detail_real;

    private Product product;
    private DialogueUtils dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_detail);
        ButterKnife.bind(this);
        inItHead("入库扫描", R.drawable.ic_done_white_24dp);
        inItView();
        dia = new DialogueUtils(this);
        setListenster(this);
    }

    private void inItView() {
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");
        product = manager.getProductQuery().getProduct(productId);
        tv_product_name.setText(product.getProduct_name());

        tv_resource_count_detail.setText("");
        tv_resource_count_detail_real.setText(product.getTidDatas().size() + "");
    }

    @Override
    public void subTitleOne(View v) {
        finish();
    }

    @Override
    public void subTitleTwo(View v) {
        dia.showProgressDialogue();
        product.setProduct_upload(true);
        operate.getProductDao().update(product);
        handler.sendEmptyMessageDelayed(0, 1000);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dia.disMiss();
            finish();
        }
    };

    @OnClick(R.id.re_for_detail)
    public void forDetail(View view) {
        Intent intent = new Intent(this, ThingsDetailActivity.class);
        intent.putExtra("productId", product.getId());
        startActivity(intent);
    }

    /**
     * 扫描标签
     *
     * @param view
     */
    public void scanTag(View view) {

        dia.showProgressDialogue();
        readTid();
    }

    @Override
    public void onNullReasult() {

        showLog("null");
        Toast.makeText(this, "未读取到标签", Toast.LENGTH_SHORT).show();
        handlers.sendEmptyMessageDelayed(1, 100);
    }

    @Override
    public void onNoReady() {

    }

    /**
     * 处理返回的数据结果
     */
    private void setReasult(String data) {

        //数据存储
        tv_resource_count_detail.setText(data);
        TidData tidData = new TidData();
        tidData.setProduct(product);
        tidData.setId(UUIDUtils.getUUId());
        tidData.setTid_tid(data);
        operate.getTidData().insert(tidData);

        long count = manager.getTidDataQuery().getProductDataCount(product.getId());
        tv_resource_count_detail_real.setText(count + "");

        handlers.sendEmptyMessageDelayed(1, 500);

    }

    private Handler handlers = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dia.disMiss();
        }
    };

    @Override
    public void onReaderResult(String data, String epc, ResultCode code) {
        setReasult(data);
    }

    @Override
    public void onReaderStateChanged(ConnectionState arg1) {

    }

    @Override
    public void onReaderReadTag(String arg1, float arg2) {

    }

    @Override
    public void onReaderActionChanged(ActionState arg1) {

    }
}