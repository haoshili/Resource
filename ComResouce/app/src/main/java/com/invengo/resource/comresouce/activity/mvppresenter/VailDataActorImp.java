package com.invengo.resource.comresouce.activity.mvppresenter;

import com.invengo.resource.comresouce.activity.mvpinterface.MainPresenter;
import com.invengo.resource.comresouce.activity.mvpinterface.MainVailDataActor;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.comresouce.utils.Value;
import com.invengo.resource.comresouce.utilsnet.model.BaseModel;
import com.invengo.resource.comresouce.utilsnet.model.DownlandDataModel;
import com.invengo.resource.comresouce.utilsnet.model.OkHttpUtils;
import com.invengo.resource.comresouce.utilsnet.netinterface.RequestListenser;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-19
 * Time: 14:27
 *
 * 进行执行那个
 */
public class VailDataActorImp implements MainVailDataActor {

    private MainPresenter.VailDataInterface vailDataInterface;

    public VailDataActorImp(MainPresenter.VailDataInterface vailDataInterface) {
        this.vailDataInterface = vailDataInterface;
    }

    @Override
    public void valiData(String userName, String pwd) {

        OkHttpUtils.getAsyn(Value.U_LOGIN+"login.html", BaseModel.class, new RequestListenser<BaseModel>() {
            @Override
            public void onLoadError() {

                //先不用服务器的数据，直接登录成功
                vailDataInterface.onSuccess();

//                vailDataInterface.onLoginErr("请求失败");
            }

            @Override
            public void onLoadSuccess(BaseModel object) {

                vailDataInterface.onSuccess();

                if (object.getStatue() == 0) {

                    LogUtils.i("test",object.getStatue()+"用户登陆成功");
                    vailDataInterface.onSuccess();
                } else {
                    vailDataInterface.onLoginErr(object.getErrorMessage());
                }

            }
        });
    }
}