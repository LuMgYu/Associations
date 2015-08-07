package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class AssociationInformationActivity extends BaseActivity {
	private TextView association_tv_name1;
	private ImageView iv_v;
	private TextView association_tv_school_name;
	private TextView association_tv_welfare_name;
	private TextView association_tv_about2;
	private TextView association_tv_theme_name;
	private TextView association_tv_contact2;

	@Override
	public String setCenterTitle() {
		return "社团信息";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_association_information;
	}

	// private TextView association_tv_name1;
	// private TextView iv_v;
	// private TextView association_tv_school_name;
	// private TextView association_tv_welfare_name;
	// private TextView association_tv_about2;
	// private TextView association_tv_theme_name;
	// private TextView association_tv_contact2;
	// private TextView association_rl_contact_way;
	@Override
	public void initView() {
		association_tv_name1 = (TextView) findViewById(R.id.association_tv_name1);
		iv_v = (ImageView) findViewById(R.id.iv_v);
		association_tv_school_name = (TextView) findViewById(R.id.association_tv_school_name);
		association_tv_welfare_name = (TextView) findViewById(R.id.association_tv_welfare_name);
		association_tv_about2 = (TextView) findViewById(R.id.association_tv_about2);
		association_tv_theme_name = (TextView) findViewById(R.id.association_tv_theme_name);
		association_tv_contact2 = (TextView) findViewById(R.id.association_tv_contact2);
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
