package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.LoginIm;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午10:28:27 类描述：这个类是实现
 *
 */

public class ModifyPwdActivity extends BaseActivity {
	private EditText et_old_pwd;
	private EditText et_new_pwd;
	private EditText et_sure_pwd;
	private Button btn_done_modify;
	private static final int SUCCESS = 1;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {
			case SUCCESS:
				boolean isSuccess = (Boolean) msg.obj;
				if (isSuccess) {
					// TODO 把获取的信息，保存到shareprefrence类中
					ToastUtils.showToast("修改密码成功");
					quitLogin();
					mApp.startActivity(ModifyPwdActivity.this,
							MainActivity.class, null,
							Intent.FLAG_ACTIVITY_CLEAR_TOP);
				} else {
					ToastUtils.showToast("修改密码失败，请稍后重试");
				}
				break;

			}

		};

	};

	private ModelUser mUser;

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
		mUser = mApp.getUser(); // 从本地获取用户的 保存的消息
	}

	@Override
	public void initListener() {
		btn_done_modify.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_done_modify:
			String oldPwd = et_old_pwd.getText().toString();
			String pwd = et_new_pwd.getText().toString();
			String surePwd = et_sure_pwd.getText().toString();
			if (checkThePwdAndSms(oldPwd, pwd, surePwd)) {
				final LoginIm loginIm2 = mApp.getLoginIm();

				mUser.setOldPwd(oldPwd);
				mUser.setPwd(pwd);
				mApp.getExecutor().execute(new Runnable() {

					@Override
					public void run() {
						boolean isSuccess = loginIm2
								.saveUserPasswordByPassword(mUser);
						Message message = Message.obtain();
						message.what = SUCCESS;
						message.obj = isSuccess;
						mHandle.sendMessage(message);
					}
				});
			}
			break;

		}
	}

	/**
	 * 检验密码和验证码是否合法
	 * 
	 * @param oldPwd
	 *            验证码
	 * @param pwd
	 *            密码
	 * @param surePwd
	 *            确认密码
	 * @return
	 */
	private boolean checkThePwdAndSms(String oldPwd, String pwd, String surePwd) {
		// TODO Auto-generated method stub
		if (oldPwd != null && pwd.equals(surePwd)) {
			return true;
		}
		return false;
	}
}
