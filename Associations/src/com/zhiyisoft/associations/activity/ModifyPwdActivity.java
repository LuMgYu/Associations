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

public class ModifyPwdActivity extends BaseActivity {
	private EditText et_old_pwd;
	private EditText et_new_pwd;
	private EditText et_sure_pwd;
	private Button btn_done_modify;

	@Override
	public String setCenterTitle() {
		return "修改密码";
	}

	@Override
	public void initIntent() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_modify_pwd;
	}

	@Override
	public void initView() {
		et_old_pwd = (EditText) findViewById(R.id.et_old_pwd);
		et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
		et_sure_pwd = (EditText) findViewById(R.id.et_sure_pwd);
		btn_done_modify = (Button) findViewById(R.id.btn_done_modify);

	}

	@Override
	public void initListener() {
		btn_done_modify.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_reset:
			break;
		case R.id.bt_sure_modify:
			mApp.startActivity(this, LoginActivity.class, null,
					Intent.FLAG_ACTIVITY_CLEAR_TOP);
			break;

		}
	}

}
