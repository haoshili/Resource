package com.invengo.resource.comresouce.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
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
public class HomeRecyclerSwipeAdapter extends RecyclerSwipeAdapter<HomeRecyclerSwipeAdapter.ViewHolder> {
    public String[] datas = null;

    private List<Set> listSet;
    private OnRecyclerViewItemClick listenser;
    private Context context;

    public HomeRecyclerSwipeAdapter(List<Set> listSet, OnRecyclerViewItemClick listenser
    ) {
        this.listSet = listSet;
        this.listenser = listenser;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.storage_list_item, viewGroup,
                false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tv_descript.setText(listSet.get(position).getSet_name());
//        viewHolder.iv_home.setImageResource(getImagePath(listSet.get(position).getSet_image_name()));
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.bottom_wrapper);
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

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_descript)
        TextView tv_descript;

        @Bind(R.id.sl_test)
        SwipeLayout swipeLayout;

        @Bind(R.id.bottom_wrapper)
        LinearLayout bottom_wrapper;

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