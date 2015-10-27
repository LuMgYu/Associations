package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.HomeAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.HomeListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentHome extends BaseFragment {
	private BaseListView mListView;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	public boolean checkTheUser() {
		return true;
	}

	@Override
	public void initIntentData() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_home;
	}

	@Override
	public void initView() {
		mListView = (HomeListview) findViewById(R.id.homelistView);
		mAdapter = new HomeAdapter(this, mlist);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {

	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {

	}

}
