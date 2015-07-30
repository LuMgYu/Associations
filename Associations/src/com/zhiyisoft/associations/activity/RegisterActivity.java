package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;

/**
 * author：qiuchunjia time：上午10:28:27 类描述：这个类是实现
 *
 */

public class RegisterActivity extends BaseActivity {
	private EditText et_vitify;
	private EditText et_new_pwd;
	private EditText et_sure_pwd;
	private Button btn_reset;
	private Button btn_done_regster;

	@Override
	public String setCenterTitle() {
		return "找回密码";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_set_pwd;
	}

	@Override
	public void initView() {
		et_vitify = (EditText) findViewById(R.id.et_vitify);
		et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
		et_sure_pwd = (EditText) findViewById(R.id.et_sure_pwd);
		btn_reset = (Button) findViewById(R.id.btn_reset);
		btn_done_regster = (Button) findViewById(R.id.btn_done_regster);

	}

	@Override
	public void initListener() {
		btn_reset.setOnClickListener(this);
		btn_done_regster.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_reset:
			break;
		case R.id.btn_done_regster:
			// FLAG_ACTIVITY_CLEAR_TOP

			mApp.startActivity(this, RegisterFillInformationActivity.class,
					null);
			break;

		}
	}
}
