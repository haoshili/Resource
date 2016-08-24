package com.invengo.resource.comresouce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.entity.Product;

import java.util.List;


public class StorageAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;
    private StorageImageClickLinstenser listenser;

    public StorageAdapter(Context context, List<Product> productList, StorageImageClickLinstenser listenser) {
        this.context = context;
        this.productList = productList;
        this.listenser = listenser;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.storage_list_item_nos, null);
            holder = new ViewHolder();
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_descript = (TextView) convertView.findViewById(R.id.tv_descript);
            holder.tv_count = (TextView) convertView.findViewById(R.id.tv_count);
            holder.re_more = (RelativeLayout) convertView.findViewById(R.id.re_more);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(productList.get(position).getProduct_name());
        holder.tv_descript.setText(productList.get(position).getProduct_type());
        holder.tv_count.setText("扫描 " + productList.get(position).getTidDatas().size() + "");
        holder.re_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenser != null) {
                    listenser.onImageClick(position);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView tv_title;
        private TextView tv_descript;
        private TextView tv_count;
        private RelativeLayout re_more;
    }

    public interface StorageImageClickLinstenser {
        void onImageClick(int position);
    }
}
