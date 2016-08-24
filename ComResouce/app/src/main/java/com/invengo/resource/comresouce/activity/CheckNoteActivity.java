package com.invengo.resource.comresouce.activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;


import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.activity.mvpinterface.CheckDetailPresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.view.CheckDetailViewInter;
import com.invengo.resource.comresouce.activity.mvppresenter.CheckDetailPresenterImp;
import com.invengo.resource.comresouce.utils.DialogueUtils;
import com.invengo.resource.entity.Job;
import com.invengo.resource.entity.Product;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CheckNoteActivity extends BaseTwoActivity implements  CheckDetailViewInter {

    @Bind(R.id.tv_resouce_count)
    TextView tv_resouce_count;

    @Bind(R.id.tv_detail)
    TextView tv_detail;

    @Bind(R.id.tv_need_check)
    TextView tv_need_check;

    @Bind(R.id.tv_has_checked)
    TextView tv_has_checked;



    private Product product;

    private DialogueUtils dia;

    private CheckDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_note);
        ButterKnife.bind(this);
        inItHead("盘库缺失备注", R.drawable.ic_done_white_24dp);
        dia = new DialogueUtils(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("productId");
        product = manager.getProductQuery().getProduct(id);
        tv_detail.setText(product.getProduct_name());

        inItView();
        presenter = new CheckDetailPresenterImp(this);
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {
        dia.showProgressDialogue();
        handler.sendEmptyMessageDelayed(0, 1000);
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
    }



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Job job =  product.getJob();
            job.setJob_complete(true);
            operate.getJobDao().update(job);
            dia.disMiss();

            Intent intent = new Intent(CheckNoteActivity.this,CheckActivity.class);
            startActivity(intent);

        }
    };


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
    private void showDal(){
        DialogueUtils dia = new DialogueUtils(this,DialogueUtils.DialogueType.NORMAL,l);
        dia.setBody("您好，所有的数据已经扫描完毕，是否将该盘库数据上传到服务器？");
        dia.setFoot("取消","上传");
        dia.setTitle("数据扫描完毕");

        dia.showDia();
    }

    private  DialogueUtils.ClickListenser l = new DialogueUtils.ClickListenser(){
        @Override
        public void onDialogueClick(int resource, int type, int code, AlertDialog alertDialog) {

            if(resource == R.id.btn_ok){
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
    private void uploadDataToServer(){
        dia.showProgressDialogue();
        presenter.upLoadDataToServer();
        handler.sendEmptyMessageDelayed(0, 1000);
    }

}