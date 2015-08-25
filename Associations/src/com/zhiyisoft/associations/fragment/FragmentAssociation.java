package com.zhiyisoft.associations.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.MeSettingProvinceActivity;
import com.zhiyisoft.associations.adapter.AssociationMainAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.listview.AssociationListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:42:36 类描述：这个类是实现
 *
 */

public class FragmentAssociation extends BaseFragment {

	private BaseListView mListView;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	public void initIntentData() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_association;
	}

	@Override
	public boolean checkTheUser() {
		return true;
	}

	@Override
	public void initView() {
		mListView = (AssociationListview) findViewById(R.id.mXlistView);
		mListView.setPullRefreshEnable(false);
		mAdapter = new AssociationMainAdapter(this, mlist);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.school_rl_change:
			mApp.startActivity(getActivity(), MeSettingProvinceActivity.class,
					null);
			break;

		default:
			break;
		}

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
