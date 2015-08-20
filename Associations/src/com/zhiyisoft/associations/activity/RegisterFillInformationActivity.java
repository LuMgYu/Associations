package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.img.RoundImageView;

/**
 * author：qiuchunjia time：上午10:28:27 类描述：这个类是实现
 *
 */

public class RegisterFillInformationActivity extends BaseActivity {
	private RoundImageView fill_iv_login_icon;
	private ImageView fill_iv_photo;
	private EditText fill_et_nick;
	private RelativeLayout fill_rl_school;
	private TextView fill_tv_school;
	private ImageView fill_iv_gender_boy;
	private ImageView fill_iv_gender_girl;
	private Button bt_done;

	@Override
	public String setCenterTitle() {
		return "填写资料";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_fill_information;
	}

	// private RoundImageView fill_iv_login_icon;
	// private ImageView fill_iv_photo;
	// private EditText fill_et_nick;
	// private RelativeLayout fill_rl_school;
	// private TextView fill_tv_school;
	// private ImageView fill_iv_gender_boy;
	// private ImageView fill_iv_gender_girl;
	// private Button bt_done;
	@Override
	public void initView() {
		fill_iv_login_icon = (RoundImageView) findViewById(R.id.fill_iv_login_icon);
		fill_iv_photo = (ImageView) findViewById(R.id.fill_iv_photo);
		fill_et_nick = (EditText) findViewById(R.id.fill_et_nick);
		fill_rl_school = (RelativeLayout) findViewById(R.id.fill_rl_school);
		fill_tv_school = (TextView) findViewById(R.id.fill_tv_school);
		fill_iv_gender_boy = (ImageView) findViewById(R.id.fill_iv_gender_boy);
		fill_iv_gender_girl = (ImageView) findViewById(R.id.fill_iv_gender_girl);
		bt_done = (Button) findViewById(R.id.bt_done);
	}

	@Override
	public void initListener() {
		fill_rl_school.setOnClickListener(this);
		fill_iv_gender_boy.setOnClickListener(this);
		fill_iv_gender_girl.setOnClickListener(this);
		bt_done.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fill_rl_school:
			mApp.startActivity(this, ForgetPwdPhoneActivity.class, null);
			break;
		case R.id.fill_iv_gender_boy:
			break;

		case R.id.fill_iv_gender_girl:

			break;
		case R.id.bt_done:
			// TODO
			mApp.startActivity(this, LoginActivity.class, null,
					Intent.FLAG_ACTIVITY_CLEAR_TOP);
			break;
		}

	}

}
