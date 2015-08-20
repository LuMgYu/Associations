package com.zhiyisoft.associations.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhiyisoft.associations.fragment.base.BaseFragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
	private List<BaseFragment> mFragments;

	public MyViewPagerAdapter(FragmentManager fm, List<BaseFragment> Fragments) {
		super(fm);
		mFragments = Fragments;
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