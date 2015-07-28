package com.zhiyisoft.associations.adapter;

import java.util.ArrayList;

import com.zhiyisoft.associations.fragment.base.BaseFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
	private ArrayList<BaseFragment> mFragments;

	public MyViewPagerAdapter(FragmentManager fm,
			ArrayList<BaseFragment> fragments) {
		super(fm);
		mFragments = fragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		return mFragments.get(arg0);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

}