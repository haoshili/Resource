package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {

        Schema schema = new Schema(3, "com.invengo.resource.entity");
        addSetEntity(schema);
        addJobNeedDone(schema);
        new DaoGenerator().generateAll(schema, "././app/src/main/java/");
    }

    /**
     * 首页展示数据库
     *
     * @param schema
     */
    private static void addSetEntity(Schema schema) {
        Entity set = schema.addEntity("Set");
        set.addIdProperty();
        set.addStringProperty("set_name");
        set.addStringProperty("set_image_name");
        set.addBooleanProperty("set_sys_show");
        set.addBooleanProperty("set_set_show");
    }

    /**
     * 盘库订单信息
     */
    private static void addJobNeedDone(Schema schema) {
        Entity job = schema.addEntity("Job");

        job.addStringProperty("id").primaryKey();
        job.addStringProperty("job_name");
        job.addBooleanProperty("text");
        job.addLongProperty("job_ctime");
        job.addBooleanProperty("job_complete");
        addProduct(schema, job);
    }


    /**
     * 产品
     * <p/>
     * 现在产品的属性还不是很多，先做个样子货出来。关于产品属性觉得可以使用json数据格式来存。
     * <p/>
     * 好多时候感觉是没有固定的数据格式的
     *
     * @param schema
     */
    private static void addProduct(Schema schema, Entity entityJob) {

        Entity product = schema.addEntity("Product");
        product.addStringProperty("product_name");
        product.addStringProperty("id").primaryKey();
        product.addStringProperty("product_type");
        product.addStringProperty("product_des");
        product.addBooleanProperty("product_storage");
        product.addBooleanProperty("product_upload");

        Property jobId = product.addStringProperty("job_id").getProperty();
        product.addToOne(entityJob, jobId);
        ToMany productToJob = entityJob.addToMany(product, jobId);
        productToJob.setName("products");

        addTidData(schema, product);
    }

    /**
     * tid的数据集合
     *
     * @param schema
     */
    private static void addTidData(Schema schema, Entity entityProduct) {
        Entity tidData = schema.addEntity("TidData");
        tidData.addStringProperty("id").primaryKey();
        tidData.addStringProperty("tid_tid");
        tidData.addBooleanProperty("tid_has_read");

        Property productId = tidData.addStringProperty("product_id").getProperty();
        tidData.addToOne(entityProduct, productId);
        ToMany tidDataToProduct = entityProduct.addToMany(tidData, productId);
        tidDataToProduct.setName("tidDatas");
    }
}
