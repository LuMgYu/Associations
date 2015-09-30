package com.zhiyisoft.associations.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.AssociationMemberAdapter;
import com.zhiyisoft.associations.adapter.MoveMemberAdapter;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.AssociationMemberListview;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MoveMemberActivity extends BaseActivity {
	private BaseListView member_lv;
	private List<Model> mlist = new ArrayList<Model>();
	private BAdapter mAdapter;
	private ModelEvent mEvent;

	@Override
	public String setCenterTitle() {
		return "参与者";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mEvent = (ModelEvent) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_move_member;
	}

	@Override
	public void initView() {
		member_lv = (AssociationMemberListview) findViewById(R.id.member_lv);
		mAdapter = new MoveMemberAdapter(this, mEvent);
		member_lv.setAdapter(mAdapter);
	}

	@Override
	public void initListener() {
		// rl_nick.setOnClickListener(this);
		// rl_gender.setOnClickListener(this);
		// rl_school.setOnClickListener(this);
		// rl_homeland.setOnClickListener(this);
		// rl_email.setOnClickListener(this);
		// rl_phone.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_nick:
			Bundle data = new Bundle();
			mApp.startActivity(this, MeSettingNickActivity.class, data);
			break;
		case R.id.rl_gender:
			break;
		case R.id.rl_school:
			Bundle data2 = new Bundle();
			mApp.startActivity(this, MeSettingProvinceActivity.class, data2);
			break;
		case R.id.rl_homeland:
			break;
		case R.id.rl_email:
			break;
		case R.id.rl_phone:
			break;
		}

	}
}
