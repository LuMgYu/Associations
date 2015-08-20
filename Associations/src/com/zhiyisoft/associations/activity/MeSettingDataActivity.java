package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;

/**
 * author：qiuchunjia time：上午9:53:45 类描述：这个类是实现
 *
 */

public class MeSettingDataActivity extends BaseActivity {
	private RelativeLayout rl_nick;
	private TextView tv_nick_name;
	private RelativeLayout rl_gender;
	private TextView tv_gender_name;
	private RelativeLayout rl_school;
	private TextView tv_school_name;
	private RelativeLayout rl_homeland;
	private TextView tv_homeland_name;
	private RelativeLayout rl_email;
	private TextView tv_email_name;
	private RelativeLayout rl_phone;
	private TextView tv_phone_name;

	@Override
	public String setCenterTitle() {
		return "更新资料";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_me_setting_data;
	}

	@Override
	public void initView() {
		tv_nick_name = (TextView) findViewById(R.id.tv_nick_name);
		tv_gender_name = (TextView) findViewById(R.id.tv_gender_name);
		tv_school_name = (TextView) findViewById(R.id.tv_school_name);
		tv_homeland_name = (TextView) findViewById(R.id.tv_homeland_name);
		tv_email_name = (TextView) findViewById(R.id.tv_email_name);
		tv_phone_name = (TextView) findViewById(R.id.tv_phone_name);

		rl_nick = (RelativeLayout) findViewById(R.id.rl_nick);
		rl_gender = (RelativeLayout) findViewById(R.id.rl_gender);
		rl_school = (RelativeLayout) findViewById(R.id.rl_school);
		rl_homeland = (RelativeLayout) findViewById(R.id.rl_homeland);
		rl_email = (RelativeLayout) findViewById(R.id.rl_email);
		rl_phone = (RelativeLayout) findViewById(R.id.rl_phone);
	}

	@Override
	public void initListener() {
		rl_nick.setOnClickListener(this);
		rl_gender.setOnClickListener(this);
		rl_school.setOnClickListener(this);
		rl_homeland.setOnClickListener(this);
		rl_email.setOnClickListener(this);
		rl_phone.setOnClickListener(this);

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
