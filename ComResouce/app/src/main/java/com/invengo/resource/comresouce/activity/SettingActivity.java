package com.invengo.resource.comresouce.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.utils.DialogueUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-18
 * Time: 16:23
 */
public class SettingActivity extends BaseTwoActivity {

    @Bind(R.id.re_set_home)
    RelativeLayout re_set_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        inItHead("设置");
    }

    @OnClick(R.id.re_set_home)
    public void setHome(View v) {
        Intent intent = new Intent(this, SettingHomeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.re_set_power)
    public void setPower(View v) {
        showPowerDia(getResources().getStringArray(R.array.power));
    }

    @OnClick(R.id.re_idel_time)
    public void setIdelTime(View view){
        showPowerDia(getResources().getStringArray(R.array.ideltime));
    }

    @OnClick(R.id.re_out_time)
    public void setOutTime(View view){
        showPowerDia(getResources().getStringArray(R.array.outtime));
    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

    }


    private void showPowerDia(String[] body) {
        DialogueUtils dia = new DialogueUtils(this, DialogueUtils.DialogueType.LIST, new DialogueUtils.ClickListenser() {
            @Override
            public void onDialogueClick(int resource, int type, int code, AlertDialog alertDialog) {

            }

            @Override
            public void onListViewItemClick(int position, int type, int code, AlertDialog alertDialog) {

            }

            @Override
            public void onTextChangeListenser(String text, int code) {

            }
        });
        dia.clear(true, true);
        dia.setBody(body);
        dia.showDia();
    }
}