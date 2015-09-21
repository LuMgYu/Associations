package com.zhiyisoft.associations.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.LoginIm;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午10:28:27 类描述：这个类是实现
 *
 */

public class ForgetPwdActivity extends BaseActivity {
	private EditText et_vitify;
	private EditText et_new_pwd;
	private EditText et_sure_pwd;
	private Button btn_reset;
	private Button bt_sure_modify;
	private boolean isRed = true; // 初始化获取验证码为红色背景
	private static final int SEND_SUCCESS = 1;
	private static final int SUCCESS = 2;
	private static final int COUNTTIME = 4; // 倒计时

	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {
			case SEND_SUCCESS:
				boolean isSuccess = (Boolean) msg.obj;
				if (isSuccess) {
					// TODO 把获取的信息，保存到shareprefrence类中
					ToastUtils.showToast("发送验证码成功");
					countTime();
				} else {
					ToastUtils.showToast("发送验证码失败，请稍后重试");
					isRed = true;
					btn_reset.setBackgroundResource(R.color.main_color);
					btn_reset.setText("获取验证码");
				}
				break;

			case SUCCESS:
				boolean modifySuccess = (Boolean) msg.obj;
				// System.out.println("呵呵哒--------" + user.toString());
				if (modifySuccess) {
					ToastUtils.showToast("修改密码成功");
					onBackPressed();
					// 返回到登陆页面
					// mApp.startActivity(ForgetPwdPhoneActivity.this,
					// RegisterFillInformationActivity.class, null);
				} else {
					ToastUtils.showToast("修改密码失败，请稍后重试");
				}
				break;
			case COUNTTIME:
				// 倒计时
				int lastTime = msg.arg1;
				btn_reset.setText(lastTime + "s");
				if (lastTime == 0) {
					isRed = true;
					btn_reset.setBackgroundResource(R.color.main_color);
					btn_reset.setText("获取验证码");
				}
				break;
			}

		};

	};
	private ModelUser mUser;

	@Override
	public String setCenterTitle() {
		return "找回密码";
	}

	@Override
	public void initIntent() {
		Bundle data = getIntent().getExtras();
		if (data != null) {
			mUser = (ModelUser) data.get(Config.SEND_ACTIVITY_DATA);
		}

	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_find_pwd;
	}

	@Override
	public void initView() {
		et_vitify = (EditText) findViewById(R.id.et_vitify);
		et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
		et_sure_pwd = (EditText) findViewById(R.id.et_sure_pwd);
		btn_reset = (Button) findViewById(R.id.btn_reset);
		bt_sure_modify = (Button) findViewById(R.id.bt_sure_modify);

	}

	@Override
	public void initListener() {
		btn_reset.setOnClickListener(this);
		bt_sure_modify.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_reset:
			if (isRed) {
				final LoginIm loginIm = mApp.getLoginIm();
				mApp.getExecutor().execute(new Runnable() {

					@Override
					public void run() {
						boolean isSuccess = loginIm.sendCodeByPhone(mUser);
						Message message = Message.obtain();
						message.what = SEND_SUCCESS;
						message.obj = isSuccess;
						mHandle.sendMessage(message);
					}
				});
				isRed = false;
				btn_reset.setBackgroundResource(R.color.main_gray_color);
			}
			break;
		case R.id.bt_sure_modify:
			String smsCode = et_vitify.getText().toString();
			String pwd = et_new_pwd.getText().toString();
			String surePwd = et_sure_pwd.getText().toString();
			if (checkThePwdAndSms(smsCode, pwd, surePwd)) {
				final LoginIm loginIm2 = mApp.getLoginIm();

				mUser.setRegCode(smsCode);
				mUser.setPwd(pwd);
				mApp.getExecutor().execute(new Runnable() {

					@Override
					public void run() {
						boolean isSuccess = loginIm2
								.saveUserPasswordByPhone(mUser);
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
	 * @param smsCode
	 *            验证码
	 * @param pwd
	 *            密码
	 * @param surePwd
	 *            确认密码
	 * @return
	 */
	private boolean checkThePwdAndSms(String smsCode, String pwd, String surePwd) {
		// TODO Auto-generated method stub
		if (smsCode != null && pwd.equals(surePwd)) {
			return true;
		}
		return false;
	}

	/**
	 * 开启倒计时
	 */
	private void countTime() {

		new Thread() {
			int time = 60;// 默认为60秒 每隔一秒就减一

			@Override
			public void run() {
				while (time > 0) {
					time = time - 1;
					Message message = Message.obtain();
					message.what = COUNTTIME;
					message.arg1 = time;
					mHandle.sendMessage(message);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
