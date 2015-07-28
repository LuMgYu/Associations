package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.testAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.testListview;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentNotifyNotify extends BaseFragment {
	 private testListview mListView;
	 private BAdapter mAdapter;
	 private List<Model> mlist = new ArrayList<Model>();

	@Override
	public void initIntentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_notify_notify_fragment;
	}

	@Override
	public void initView() {
		mListView = (testListview) findViewById(R.id.notify_lv);
		mAdapter = new testAdapter(this, mlist);
		mListView.setAdapter(mAdapter);

	}

	@Override
	public void initListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
