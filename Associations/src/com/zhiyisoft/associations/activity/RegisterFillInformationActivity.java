package com.zhiyisoft.associations.activity;

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
		fill_iv_login_icon = (RoundImageView) findViewById(R.id.fill_iv_login_icon);
		fill_iv_login_icon = (RoundImageView) findViewById(R.id.fill_iv_login_icon);
		fill_iv_login_icon = (RoundImageView) findViewById(R.id.fill_iv_login_icon);
		fill_iv_login_icon = (RoundImageView) findViewById(R.id.fill_iv_login_icon);
		fill_iv_login_icon = (RoundImageView) findViewById(R.id.fill_iv_login_icon);
	}

	@Override
	public void initListener() {

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
