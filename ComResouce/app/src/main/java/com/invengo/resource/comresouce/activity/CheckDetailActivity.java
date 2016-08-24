package com.invengo.resource.comresouce.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.activity.base.ReadTagNewActivity;
import com.invengo.resource.comresouce.activity.mvpinterface.CheckDetailPresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.view.CheckDetailViewInter;
import com.invengo.resource.comresouce.activity.mvppresenter.CheckDetailPresenterImp;
import com.invengo.resource.comresouce.utils.DialogueUtils;
import com.invengo.resource.entity.Job;
import com.invengo.resource.entity.Product;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CheckDetailActivity extends ReadTagNewActivity implements ReadTagNewActivity.ReadTagListenser, CheckDetailViewInter {

    @Bind(R.id.tv_resouce_count)
    TextView tv_resouce_count;



    @Bind(R.id.tv_detail)
    TextView tv_detail;

    @Bind(R.id.tv_need_check)
    TextView tv_need_check;

    @Bind(R.id.tv_has_checked)
    TextView tv_has_checked;

    @Bind(R.id.tv_cur_tid)
    TextView tv_cur_tid;

    private Product product;

    private DialogueUtils dia;

    private CheckDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_detail);
        ButterKnife.bind(this);
        inItHead("盘库扫描", R.drawable.ic_done_white_24dp);
        dia = new DialogueUtils(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("productId");
        product = manager.getProductQuery().getProduct(id);
        tv_detail.setText(product.getProduct_name());

        inItView();
        setListenster(this);
        presenter = new CheckDetailPresenterImp(this);
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

        long allNeedReadCount = manager.getTidDataQuery().getProductTidStatueCount(false, product.getId());

        if (allNeedReadCount == 0) {

            dia.showProgressDialogue();
            handler.sendEmptyMessageDelayed(0, 1000);
        } else {
            Intent intent = new Intent(this, CheckNoteActivity.class);
            intent.putExtra("productId", product.getId());
            startActivity(intent);
        }

    }

    /**
     * 读取标签
     *
     * @param view
     */
    public void readTag(View view) {

        if (!readTid()) {
            Toast.makeText(this, "erro", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 初始化数据格式
     */
    private void inItView() {

        long allResourceCount = product.getTidDatas().size();
        tv_resouce_count.setText(allResourceCount + "");

        //待盘资产数目
        long allNeedReadCount = manager.getTidDataQuery().getProductTidStatueCount(false, product.getId());

        tv_need_check.setText(allNeedReadCount + "");


        long restCount = allResourceCount - allNeedReadCount;
        tv_has_checked.setText(restCount + "");

        if (allNeedReadCount == 0) {
            showDal();
        }

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

           Job job =  product.getJob();
            job.setJob_complete(true);
            operate.getJobDao().update(job);

            dia.disMiss();
            finish();
        }
    };

    @Override
    public void onNullReasult() {

    }

    @Override
    public void onNoReady() {
        toast("未读取到标签，请重试");
    }

    @Override
    public void onReaderResult(String data, String epc, ResultCode code) {

        tv_cur_tid.setText(data);

        presenter.checkData(data, product.getId());
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

    @Override
    public void onCheckSuccess() {
        toast("扫描成功！");
        inItView();
    }

    @Override
    public void onCheckFailed() {
        toast("数据库中未含有该标签！");
    }

    @Override
    public void onUploadSuccess() {

    }

    @Override
    public void onUploadFailed() {

    }

    /**
     * 是否要上传数据对话框使用
     */
    private void showDal() {
        DialogueUtils dia = new DialogueUtils(this, DialogueUtils.DialogueType.NORMAL, l);
        dia.setBody("您好，所有的数据已经扫描完毕，是否将该盘库数据上传到服务器？");
        dia.setFoot("取消", "上传");
        dia.setTitle("数据扫描完毕");

        dia.showDia();
    }

    private DialogueUtils.ClickListenser l = new DialogueUtils.ClickListenser() {
        @Override
        public void onDialogueClick(int resource, int type, int code, AlertDialog alertDialog) {

            if (resource == R.id.btn_ok) {
                uploadDataToServer();
            }
        }

        @Override
        public void onListViewItemClick(int position, int type, int code, AlertDialog alertDialog) {

        }

        @Override
        public void onTextChangeListenser(String text, int code) {

        }
    };

    /**
     * 将数据上传到服务器
     */
    private void uploadDataToServer() {
//        dia.showProgressDialogue();
//        presenter.upLoadDataToServer();
        dia.showProgressDialogue();
        handler.sendEmptyMessageDelayed(0, 1000);
    }

}