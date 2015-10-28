package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.img.RoundImageView;
import com.zhiyisoft.associations.model.ModelUser;

/**
 * author：qiuchunjia time：下午4:36:15 类描述：这个类是实现
 *
 */

public class NotifyMsgUserInforActivity extends BaseActivity {

	private RoundImageView user_icon;
	private TextView tv_nick_name;
	private TextView tv_gender_name;
	private TextView tv_phone_name;
	private TextView tv_email_name;
	private TextView tv_school_name;
	private TextView tv_signatue_name;
	private ModelUser mUser;

	@Override
	public void onClick(View v) {

	}

	@Override
	public String setCenterTitle() {

		return "用户资料";
	}

	@Override
	public void initIntent() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			mUser = (ModelUser) bundle.get(Config.SEND_ACTIVITY_DATA);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_notifiy_user_information;
	}

	@Override
	public void initView() {
		user_icon = (RoundImageView) findViewById(R.id.user_icon);
		tv_nick_name = (TextView) findViewById(R.id.tv_nick_name);
		tv_gender_name = (TextView) findViewById(R.id.tv_gender_name);
		tv_phone_name = (TextView) findViewById(R.id.tv_phone_name);
		tv_email_name = (TextView) findViewById(R.id.tv_email_name);
		tv_school_name = (TextView) findViewById(R.id.tv_school_name);
		tv_signatue_name = (TextView) findViewById(R.id.tv_signatue_name);
		initData();

	}

	private void initData() {
		if (mUser != null) {
			mApp.displayImage(mUser.getFaceurl(), user_icon);
			tv_nick_name.setText(mUser.getUname());
			if (mUser.getSex().equals("0")) {
				tv_gender_name.setText("男");
			} else {
				tv_gender_name.setText("女");
			}
			tv_phone_name.setText(mUser.getMobile());
			tv_email_name.setText(mUser.getEmail());
			tv_school_name.setText(mUser.getSchool_name());
			tv_signatue_name.setText(mUser.getAutograph());
		}

	}

	@Override
	public void initListener() {

	}

}
