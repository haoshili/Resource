package com.invengo.resource.comresouce.activity.mvpinterface.view;

import com.invengo.resource.entity.Product;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
public interface StorageViewInter {

    void onLoadDataSuccess(List<Product> productList);

    void onDelItem(int position);

    void onEditProduct(int position);
}
