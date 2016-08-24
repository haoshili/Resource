package com.invengo.resource.comresouce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.entity.Job;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: haoshengjun(872860796@qq.com)
 * Date: 2016-05-16
 * Time: 16:29
 */
public class DownlandRecyclerAdapter extends RecyclerView.Adapter<DownlandRecyclerAdapter.ViewHolder> {


    private OnRecyclerViewItemClick listenser;

    private List<Job> listJob;

    public DownlandRecyclerAdapter(OnRecyclerViewItemClick listenser, List<Job> listJob) {
        this.listJob = listJob;
        this.listenser = listenser;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.downland_list_item_check, viewGroup,
                false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.tv_title.setText(listJob.get(position).getJob_name());
        if (listenser != null) {
            viewHolder.iv_checked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenser.ontImageViewClick(viewHolder.getAdapterPosition(), viewHolder);
                }
            });

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenser.onItemClick(viewHolder.getAdapterPosition());
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return listJob.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_checked)
        public ImageView iv_checked;

        @Bind(R.id.tv_title)
        TextView tv_title;

        @Bind(R.id.progressBar)
        public ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnRecyclerViewItemClick {
        void ontImageViewClick(int position, ViewHolder viewHolder);

        void onItemClick(int position);
    }
}