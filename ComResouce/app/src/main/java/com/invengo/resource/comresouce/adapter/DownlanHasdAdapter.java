package com.invengo.resource.comresouce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.invengo.resource.comresouce.R;
import com.invengo.resource.comresouce.utils.TimeUtils;
import com.invengo.resource.entity.Job;

import java.util.List;


public class DownlanHasdAdapter extends BaseAdapter {

	private Context context;
	private List<Job> jobList;

	public DownlanHasdAdapter(Context context,List<Job> jobList) {
		this.context = context;
		this.jobList = jobList;
	}

	@Override
	public int getCount() {
		return jobList.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
	 

		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.downland_list_item_has, null);
			holder = new ViewHolder();
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_down_time = (TextView) convertView.findViewById(R.id.tv_down_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_title.setText(jobList.get(position).getJob_name());
		holder.tv_down_time.setText(TimeUtils.getTimeDetailbyLong(jobList.get(position).getJob_ctime()));
		return convertView;
	}
	
	private class ViewHolder{
		
		private TextView tv_title;
		private  TextView tv_down_time;
	}
}
