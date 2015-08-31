package com.zhiyisoft.associations.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelSchool;
import com.zhiyisoft.associations.util.Anim;
import com.zhiyisoft.associations.util.ToastUtils;

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
	private RelativeLayout rl_modify_pwd;

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
		rl_modify_pwd = (RelativeLayout) findViewById(R.id.rl_modify_pwd);
		getCurrentSchool(tv_school_name);
		initGender();
	}

	/**
	 * 获取当前的学校
	 * 
	 * @param tv
	 */
	private void getCurrentSchool(TextView tv) {
		SharedPreferences preferences = this.getSharedPreferences(
				Config.USER_DATA, Activity.MODE_PRIVATE);
		String school = preferences.getString(Config.CURRENT_SCHOOL, null);
		if (school != null) {
			tv.setVisibility(View.VISIBLE);
			tv.setText(school + "");
			return;
		}
	}

	@Override
	public void initListener() {
		rl_nick.setOnClickListener(this);
		rl_gender.setOnClickListener(this);
		rl_school.setOnClickListener(this);
		rl_homeland.setOnClickListener(this);
		rl_email.setOnClickListener(this);
		rl_phone.setOnClickListener(this);
		rl_modify_pwd.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_nick:
			Bundle data = new Bundle();
			mApp.startActivity(this, MeSettingNickActivity.class, data);
			break;
		case R.id.rl_gender:
			startActivityForResult(new Intent(this,
					MeModifyGenderActivity.class),
					MeModifyGenderActivity.GENDER);
			Anim.in(this);
			break;
		case R.id.rl_school:
			Bundle data2 = new Bundle();
			mApp.startActivityForResult(this, MeSettingProvinceActivity.class,
					data2);
			break;
		case R.id.rl_homeland:
			break;
		case R.id.rl_email:
			break;
		case R.id.rl_phone:
			break;
		case R.id.rl_modify_pwd:
			mApp.startActivity(this, ForgetPwdPhoneActivity.class, null);
			break;
		}

	}

	/**
	 * 初始化性别
	 */
	private void initGender() {
		int gender = getGender();
		if (gender == MeModifyGenderActivity.BOY) {
			tv_gender_name.setText("男");
			return;
		}
		tv_gender_name.setText("女");
	}

	private int getGender() {
		SharedPreferences preferences = this.getSharedPreferences(
				Config.USER_DATA, MODE_PRIVATE);
		int gender = preferences.getInt(Config.GENDER, 0);
		return gender;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == MeModifyGenderActivity.GENDER) {
			int gender = data.getIntExtra(Config.GENDER, 0);
			if (gender == 0) {
				tv_gender_name.setText("男");
			} else {
				tv_gender_name.setText("女");
			}
		}
		if (requestCode == this.GET_DATA_FROM_ACTIVITY) {
			if (data == null) {
				return;
			}
			Bundle bundle = data.getExtras();
			ModelSchool school = (ModelSchool) bundle
					.get(Config.GET_ACTIVITY_DATA);
			if (school != null) {
				tv_school_name.setText(school.getName() + "");
			}
		}
	}
}
