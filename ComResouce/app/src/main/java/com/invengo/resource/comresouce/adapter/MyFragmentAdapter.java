package com.invengo.resource.comresouce.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyFragmentAdapter extends FragmentPagerAdapter {

	
	private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
	
	public MyFragmentAdapter(FragmentManager fm,
			ArrayList<Fragment> pagerItemList) {
		super(fm);
		this.pagerItemList = pagerItemList;
	}

	public MyFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {
		return pagerItemList.size();
	}

	@Override
	public Fragment getItem(int position) {

		Fragment fragment = null;
		if (position < pagerItemList.size())
			fragment = pagerItemList.get(position);
		else
			fragment = pagerItemList.get(0);

		return fragment;

	}
}
