package com.invengo.resource.comresouce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.activity.base.BaseTwoActivity;
import com.invengo.resource.comresouce.activity.mvpinterface.MainPresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.view.MainViewInter;
import com.invengo.resource.comresouce.activity.mvppresenter.LoginPresenterImp;
import com.invengo.resource.comresouce.utils.DialogueUtils;

import butterknife.ButterKnife;

public class LoginActivity extends BaseTwoActivity implements MainViewInter {

    private MainPresenter presenter;
    private DialogueUtils dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inItHead(-1, "登录", -1);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImp(this);
        dia = new DialogueUtils(this);
//        presenter
    }

    public void login(View view) {


        dia.showProgressDialogue();
        presenter.valiData("hh ", "ssdfsd");

    }

    @Override
    public void subTitleOne(View v) {

    }

    @Override
    public void subTitleTwo(View v) {

    }

    ///////////////////////////////验证账号////////////////////////////////

    @Override
    public void onLoginErr() {
        toast("dfasfef");
    }

    @Override
    public void onLoginSuccess() {

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
        dia.disMiss();
    }
}
