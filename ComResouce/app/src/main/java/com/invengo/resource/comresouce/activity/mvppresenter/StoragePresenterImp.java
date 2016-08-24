package com.invengo.resource.comresouce.activity.mvppresenter;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.invengo.resource.comresouce.activity.mvpinterface.StoragePresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.view.StorageViewInter;
import com.invengo.resource.comresouce.utils.BaseDao;
import com.invengo.resource.comresouce.utils.DialogueUtils;
import com.invengo.resource.entity.Product;

import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-13
 * Time: 13:59
 */
public class StoragePresenterImp extends BaseDao implements StoragePresenter, DialogueUtils.ClickListenser {

    private StorageViewInter listenser;
    private Context mContext;
    private DialogueUtils diaProgress;
    private int delPosition;

    public StoragePresenterImp(StorageViewInter listenser) {
        this.listenser = listenser;
    }

    @Override
    public void getAlldata() {

        List<Product> productList = manager.getProductQuery().getStorageProduct();
        listenser.onLoadDataSuccess(productList);

    }

    @Override
    public void showDialogue(Context context, int position) {
        mContext = context;
        this.delPosition = position;
        DialogueUtils alert = new DialogueUtils(context, DialogueUtils.DialogueType.LIST,
                this);
        alert.clear(true, true);
        alert.setBody(new String[]{"上传", "编辑"});
        alert.showDia();
    }

    @Override
    public void onDialogueClick(int resource, int type, int code, AlertDialog alertDialog) {

    }

    @Override
    public void onListViewItemClick(int position, int type, int code, AlertDialog alertDialog) {
        if (position == 0) {
            showProgressDialogue();
        } else {
            listenser.onEditProduct(delPosition);
        }
    }

    @Override
    public void onTextChangeListenser(String text, int code) {

    }

    private void showProgressDialogue() {
        diaProgress = new DialogueUtils(mContext);
        diaProgress.showProgressDialogue();
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            diaProgress.disMiss();
            listenser.onDelItem(delPosition);
        }
    };
}