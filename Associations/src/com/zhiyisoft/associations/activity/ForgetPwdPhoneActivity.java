package com.zhiyisoft.associations.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;

/**
 * author：qiuchunjia time：上午10:28:27 类描述：这个类是实现
 *
 */

public class ForgetPwdPhoneActivity extends BaseActivity implements
		OnClickListener {
	private EditText et_number;
	private Button bt_next;

	@Override
	public String setCenterTitle() {
		return "找回密码";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_find_pwd_phone;
	}

	@Override
	public void initView() {
		et_number = (EditText) findViewById(R.id.et_number);
		bt_next = (Button) findViewById(R.id.bt_next);

	}

	@Override
	public void initListener() {
		bt_next.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_next:
			Bundle data = new Bundle();
			data.putString(Config.PHONE_NUMBER, et_number.getText().toString()
					+ "");
			mApp.startActivity(this, ForgetPwdActivity.class, data);
		}
	}
}
