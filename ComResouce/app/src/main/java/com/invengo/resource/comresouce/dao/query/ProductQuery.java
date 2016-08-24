package com.invengo.resource.comresouce.dao.query;

import com.invengo.resource.entity.Product;
import com.invengo.resource.entity.ProductDao;

import java.util.List;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-18
 * Time: 09:40
 */
public class ProductQuery {

    private ProductDao product;

    public ProductQuery(ProductDao product) {
        this.product = product;
    }

    public List<Product> getStorageProduct(){
        return product.queryBuilder().where(ProductDao.Properties.Product_storage.eq(true),
                ProductDao.Properties.Product_upload.eq(false))
                .list();
    }

    public Product getProduct(String id){
        return  product.queryBuilder().where(ProductDao.Properties.Id.eq(id)).list().get(0);
    }

}