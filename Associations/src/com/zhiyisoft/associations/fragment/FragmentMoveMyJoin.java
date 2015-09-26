package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.MoveAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.api.Api.EventImpl;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.MoveListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentMoveMyJoin extends BaseFragment {
	private BaseListView mListView;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	public void initIntentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_move_my_join;
	}

	@Override
	public void initView() {
		mListView = (MoveListview) findViewById(R.id.my_join_lv);
		ModelEvent events = new ModelEvent();
		events.setOp(1);
		mAdapter = new MoveAdapter(this, events);
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
		// TODO Auto-generated method stub

	}

}
