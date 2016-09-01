package com.invengo.resource.comresouce.fragment.mvppresenter;

import com.google.gson.reflect.TypeToken;
import com.invengo.resource.comresouce.fragment.mvpinterface.NoDowanlandViewInter;
import com.invengo.resource.comresouce.fragment.mvpinterface.NoDownlandPresenter;
import com.invengo.resource.comresouce.utils.BaseDao;
import com.invengo.resource.comresouce.utils.GsonUtil;
import com.invengo.resource.comresouce.utils.LogUtils;
import com.invengo.resource.comresouce.utils.UUIDUtils;
import com.invengo.resource.comresouce.utils.Value;
import com.invengo.resource.comresouce.utilsnet.model.BaseModel;
import com.invengo.resource.comresouce.utilsnet.model.DownlandDataModel;
import com.invengo.resource.comresouce.utilsnet.model.OkHttpUtils;
import com.invengo.resource.comresouce.utilsnet.netinterface.RequestListenser;
import com.invengo.resource.entity.Job;
import com.invengo.resource.entity.Product;
import com.invengo.resource.entity.TidData;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-06-15
 * Time: 13:46
 * <p/>
 * 执行者，现在分一层就行了，感觉用不了那么多层。
 */
public class NoDownlandImp extends BaseDao implements NoDownlandPresenter {

    private NoDowanlandViewInter viewInter;

    public NoDownlandImp(NoDowanlandViewInter viewInter) {
        this.viewInter = viewInter;
    }

    /**
     * 获取需要下载的任务
     */
    @Override
    public void getNoDowanlandShowData() {

        OkHttpUtils.getAsyn(Value.U_DOMAIN + "index.html", BaseModel.class, new RequestListenser<BaseModel>() {
            @Override
            public void onLoadError() {
                viewInter.onLoandHomeDataError();
            }

            @Override
            public void onLoadSuccess(BaseModel object) {

                if (object.getStatue() == 0) {
                    LogUtils.i("test", object.getStatue() + "test+statue");
                    viewInter.onLoadHomeDataSuccess(getListJob(object.getData()));
                } else {
                    viewInter.onLoandHomeDataError();
                }

            }
        });
    }

    /**
     * 处理数据下载任务
     *
     * @param url
     * @param position
     */
    @Override
    public void downlandData(String url, final int position) {
        OkHttpUtils.getAsyn(Value.U_DOMAIN + "downlandData.html", DownlandDataModel.class, new
                RequestListenser<DownlandDataModel>() {
                    @Override
                    public void onLoadError() {
                        viewInter.onDownlandError(position);
                    }

                    @Override
                    public void onLoadSuccess(DownlandDataModel object) {

                        if (object.getStatue() == 1) {

                            LogUtils.i(Value.L_NOLANDDATA, object.getJob());
                            viewInter.onDownlandComplete(position);
                            saveDownData(object);
                        } else {
                            viewInter.onDownlandError(position);
                        }

                    }
                });
    }

    /**
     * 保存下载的数据到数据库
     */
    private void saveDownData(DownlandDataModel model) {
        Job job = (Job) GsonUtil.jsonToBean(model.getJob(), Job.class);
        job.setId(UUIDUtils.getUUId());
        job.setJob_url("test");
        job.setJob_ctime(System.currentTimeMillis());
        job.setJob_complete(false);

        operationDao.getJobDao().insert(job);

        Product product = (Product) GsonUtil.jsonToBean(model.getProduct(), Product.class);
        product.setId(UUIDUtils.getUUId());
        product.setProduct_storage(true);
        product.setJob(job);
        operationDao.getProductDao().insert(product);

        Type type = new TypeToken<ArrayList<TidData>>() {
        }.getType();
        List<TidData> tidDataList = (List<TidData>) GsonUtil.jsonToList(model.getTidDatas(), type);
        for (TidData tidData : tidDataList) {
            tidData.setId(UUIDUtils.getUUId());
            tidData.setProduct(product);
        }
        operationDao.getTidData().insertInTx(tidDataList);
    }

    /**
     * 解析对象
     *
     * @param jsonData
     * @return
     */
    private List<Job> getListJob(String jsonData) {
        LogUtils.i(Value.L_NOLANDDATA, jsonData);
        Type type = new TypeToken<ArrayList<Job>>() {
        }.getType();
        return (List<Job>) GsonUtil.jsonToList(jsonData, type);
    }
}