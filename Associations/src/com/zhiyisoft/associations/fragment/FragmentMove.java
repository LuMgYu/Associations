package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.MoveMainAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.MoveListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentMove extends BaseFragment {
	private BaseListView mListView;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	public void initIntentData() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_move;
	}

	@Override
	public boolean checkTheUser() {
		return true;
	}

	@Override
	public void initView() {
		mListView = (MoveListview) findViewById(R.id.move_lv);
		mAdapter = new MoveMainAdapter(this, mlist);
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
