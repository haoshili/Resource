package com.invengo.resource.comresouce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class BaseSetAdapter extends BaseAdapter {

	private Context context;

	public BaseSetAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return 12;
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
//			convertView = inflater.inflate(R.layout.list_set_sound, null);
//			holder = new ViewHolder();
//			holder.tv_china = (TextView) convertView.findViewById(R.id.tv_id);
//			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		return convertView;
	}
	
	private class ViewHolder{
		
		private TextView tv_china;
	}
}
