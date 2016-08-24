package com.invengo.resource.comresouce.activity.base;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.invengo.resource.comresouce.R;

public abstract class BaseTwoActivity extends BaseActivity {

    private ImageView iv_head0;

    private ImageView iv_head1;

    private TextView tv_title;

    private ImageView iv_head2;

    private RelativeLayout re_subTitleTwo;

    private RelativeLayout re_subTitleOne;

    private RelativeLayout re_subTitleBack;

    public void inItHead(String name){
        inItHead(R.drawable.ic_back_light,name,-1);
    }

    public void inItHead(String name,int pic){
        inItHead(R.drawable.ic_back_light,name,pic);
    }

    public void inItHead(int image0, String title  , int image2) {
        inItView();

        tv_title.setText(title);

        if (image0 != -1) {
            re_subTitleBack.setVisibility(View.VISIBLE);
            iv_head0.setImageResource(image0);
        } else {
            re_subTitleBack.setVisibility(View.INVISIBLE);
        }

        if (image2 != -1) {
            re_subTitleOne.setVisibility(View.VISIBLE);
            iv_head1.setImageResource(image2);
        } else {
            re_subTitleOne.setVisibility(View.INVISIBLE);
        }

    }

    private void inItView() {
        re_subTitleOne = (RelativeLayout) findViewById(R.id.re_subTitleTwo );
        re_subTitleBack = (RelativeLayout) findViewById(R.id.re_menu_home);
        iv_head0 = (ImageView) findViewById(R.id.iv_head0);
        iv_head1 = (ImageView) findViewById(R.id.iv_head2);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    public void onHomeClick(View v) {
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) { // 监听物理键
        if (keyCode == KeyEvent.KEYCODE_HOME) {// home键
            // 相关响应代码
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回键
            // 相关响应代码

            back();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {// 音量键下
            // 相关响应代码
            return true;
        }
        return false;
    }

    public void back() {
        finish();
    }


    public void toast(String msg) {
        Toast.makeText(BaseTwoActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public abstract void subTitleOne(View v);

    public abstract void subTitleTwo(View v);

}
