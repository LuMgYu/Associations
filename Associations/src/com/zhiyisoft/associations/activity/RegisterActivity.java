package com.zhiyisoft.associations.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.RegisterIm;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.model.ModelRegister;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ToastUtils;

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
	private String mPhoneNumber;
	private static final int SEND_SUCCESS = 1;
	private static final int REGISTER_SUCCESS = 2;
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
				} else {
					ToastUtils.showToast("发送验证码失败，请稍后重试");
				}
				break;

			case REGISTER_SUCCESS:
				ModelRegister register = (ModelRegister) msg.obj;
				if (register != null) {
					mApp.startActivity(RegisterActivity.this,
							RegisterFillInformationActivity.class, null);
				} else {
					ToastUtils.showToast("注册失败，请稍后重试");
				}
				break;
			}

		};

	};

	@Override
	public String setCenterTitle() {
		return "注册用户";
	}

	@Override
	public void initIntent() {
		mBundle = getIntent().getExtras();
		mPhoneNumber = mBundle.getString(Config.PHONE_NUMBER);
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
			final RegisterIm registerIm = mApp.getRegisterIm();
			final ModelRegister register = new ModelRegister();
			register.setMobile(mPhoneNumber);
			mApp.getExecutor().execute(new Runnable() {

				@Override
				public void run() {
					boolean isSuccess = registerIm.appSendSMSCode(register);
					Message message = Message.obtain();
					message.what = SEND_SUCCESS;
					message.obj = isSuccess;
					mHandle.sendMessage(message);
				}
			});
			break;
		case R.id.btn_done_regster:
			String smsCode = et_vitify.getText().toString();
			String pwd = et_new_pwd.getText().toString();
			String surePwd = et_sure_pwd.getText().toString();
			if (checkThePwdAndSms(smsCode, pwd, surePwd)) {
				final RegisterIm registerIm2 = mApp.getRegisterIm();
				final ModelRegister register2 = new ModelRegister();
				register2.setMobile(mPhoneNumber);
				register2.setSmscode(smsCode);
				register2.setPwd(pwd);
				mApp.getExecutor().execute(new Runnable() {

					@Override
					public void run() {
						Model model = registerIm2.appUserReg(register2);
						Message message = Message.obtain();
						message.what = REGISTER_SUCCESS;
						message.obj = model;
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
		return true;
	}
}
