package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午10:28:27 类描述：这个类是实现
 *
 */

public class ForgetPwdPhoneActivity extends BaseActivity implements
		OnClickListener {
	private EditText et_number;
	private Button bt_next;
	private ModelUser mUser = new ModelUser();

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
			String mobile = et_number.getText().toString();
			if (checkThePhoneNumber(mobile)) {
				mUser.setMobile(mobile);
				data.putSerializable(Config.SEND_ACTIVITY_DATA, mUser);
				mApp.startActivityForResult(this, ForgetPwdActivity.class, data);
			} else {
				ToastUtils.showToast("手机号码不正确！");
			}

		}
	}

	/**
	 * 检验手机号码是否为空
	 * 
	 * @param phoneNumber
	 * @return
	 */
	private boolean checkThePhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.length() < 11) {
			return false;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null) {
			onBackPressed();
		}
	}
}
