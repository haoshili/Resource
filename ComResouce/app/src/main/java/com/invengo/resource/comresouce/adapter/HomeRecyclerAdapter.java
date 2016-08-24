package com.invengo.resource.comresouce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.entity.Set;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-16
 * Time: 16:29
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {
    public String[] datas = null;

    private List<Set> listSet;
    private OnRecyclerViewItemClick listenser;

    public HomeRecyclerAdapter(List<Set> listSet) {
        this.listSet = listSet;
    }

    public HomeRecyclerAdapter(List<Set> listSet, OnRecyclerViewItemClick listenser) {
        this.listSet = listSet;
        this.listenser = listenser;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_grid_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tv_descript.setText(listSet.get(position).getSet_name());
        viewHolder.iv_home.setImageResource(getImagePath(listSet.get(position).getSet_image_name()));
        if (listenser != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenser.onItemClick(listSet.get(position).getSet_name());
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return listSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_home)
        ImageView iv_home;

        @Bind(R.id.tv_descript)
        TextView tv_descript;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 通过图片的名称得到图片的id
     *
     * @param imageName
     * @return
     */
    private int getImagePath(String imageName) {
        Class<R.drawable> cls = R.drawable.class;
        int value = 0;
        try {
            value = cls.getDeclaredField(imageName).getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public interface OnRecyclerViewItemClick {
        void onItemClick(String name);
    }
}