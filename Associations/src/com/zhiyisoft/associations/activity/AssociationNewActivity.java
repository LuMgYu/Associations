package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationNewAdapter;
import com.zhiyisoft.associations.adapter.MeSettingSchoolAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.listview.AssociationNewListview;
import com.zhiyisoft.associations.listview.MeSettingSchoolListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现 新鲜事
 *
 */

public class AssociationNewActivity extends BaseActivity {
	private BaseListView new_lv;

	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;

	@Override
	public String setCenterTitle() {
		return "新鲜事";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_new;
	}

	@Override
	public void initView() {
		new_lv = (AssociationNewListview) findViewById(R.id.new_lv);
		mAdapter = new AssociationNewAdapter(this, mlist);
		new_lv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {

	}

	@Override
	public void onClick(View v) {

	}
}
