package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;

/**
 * author：qiuchunjia time：上午10:32:00 类描述：这个类是实现
 *
 */

public class MeModifyGenderActivity extends BaseActivity {
	private ImageView fill_iv_gender_boy;
	private ImageView fill_iv_gender_girl;
	private RelativeLayout fill_rl_boy;
	private RelativeLayout fill_rl_girl;
	public final static int BOY = 0;
	public final static int GIRL = 1;
	public final static int GENDER = 2;

	@Override
	public String setCenterTitle() {
		return "性别选择";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_me_modify_gender;
	}

	@Override
	public void initView() {
		fill_iv_gender_boy = (ImageView) findViewById(R.id.fill_iv_gender_boy);
		fill_iv_gender_girl = (ImageView) findViewById(R.id.fill_iv_gender_girl);
		fill_rl_boy = (RelativeLayout) findViewById(R.id.fill_rl_boy);
		fill_rl_girl = (RelativeLayout) findViewById(R.id.fill_rl_girl);
		initGender();
	}

	/**
	 * 初始化性别
	 */
	private void initGender() {
		int gender = getGender();
		if (gender == BOY) {
			fill_iv_gender_boy.setVisibility(View.VISIBLE);
			return;
		}
		fill_iv_gender_girl.setVisibility(View.VISIBLE);
	}

	@Override
	public void initListener() {
		fill_rl_boy.setOnClickListener(this);
		fill_rl_girl.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fill_rl_boy:
			resetGender();
			fill_iv_gender_boy.setVisibility(View.VISIBLE);
			saveToSharePreference(BOY);
			returnDataToActivity(BOY);
			onBackPressed();
			break;

		case R.id.fill_rl_girl:
			resetGender();
			fill_iv_gender_girl.setVisibility(View.VISIBLE);
			saveToSharePreference(GIRL);
			returnDataToActivity(GIRL);
			onBackPressed();
			break;
		}

	}

	public void returnDataToActivity(int gender) {
		Intent intent = new Intent();
		intent.putExtra(Config.GENDER, gender);
		this.setResult(GENDER, intent);
	}

	/**
	 * 保存性别
	 * 
	 * @param gender
	 */
	private void saveToSharePreference(int gender) {
		SharedPreferences preferences = this.getSharedPreferences(
				Config.USER_DATA, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(Config.GENDER, gender);
		editor.commit();
	};

	private int getGender() {
		SharedPreferences preferences = this.getSharedPreferences(
				Config.USER_DATA, MODE_PRIVATE);
		int gender = preferences.getInt(Config.GENDER, 0);
		return gender;
	}

	/**
	 * 重置性别
	 */
	private void resetGender() {
		fill_iv_gender_boy.setVisibility(View.GONE);
		fill_iv_gender_girl.setVisibility(View.GONE);
	}

}
