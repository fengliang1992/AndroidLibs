package com.fltry.androidlibs.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
	private Context context;
	private int size = 0;

	public MyViewPagerAdapter(FragmentManager fm, Context context, int size) {
		super(fm);
		this.context = context;
		this.size = size;
	}

	@Override
	public Fragment getItem(int position) {
		return FragmentFactory.createFragment(position, context);
	}

	@Override
	public int getCount() {
		return size;
	}
}
