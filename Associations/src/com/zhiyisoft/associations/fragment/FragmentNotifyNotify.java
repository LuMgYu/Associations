package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.NotifyNfyAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.NotifyNfyListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentNotifyNotify extends BaseFragment {
	private BaseListView mListView;
	private BAdapter mAdapter;
	private List<Model> mlist = new ArrayList<Model>();

	@Override
	public void initIntentData() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_notify_notify_fragment;
	}

	@Override
	public void initView() {
		mListView = (NotifyNfyListview) findViewById(R.id.notify_lv);
		mAdapter = new NotifyNfyAdapter(this, mlist);
		mListView.setAdapter(mAdapter);

	}

	@Override
	public void initListener() {

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
