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
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：下午3:33:24 类描述：这个类是实现
 *
 */

public class BindAccountActivity extends BaseActivity {
	private EditText et_bindName;
	private EditText et_bindPwd;
	private Button btn_done_bind;
	private ModelUser mUser;
	private ModelUser mResultUser; // 登陆成功返回的用户数据

	protected static final int LOGIN = 0;
	protected static final int BIND_NEW_USER = 1;
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {
			case LOGIN:
				mResultUser = (ModelUser) msg.obj;
				if (mResultUser != null) {
					if (mUser != null) {
						ToastUtils.showToast("登陆成功，正在绑定！");
						mResultUser.setType(mUser.getType());
						mResultUser.setType_uid(mUser.getType_uid());
						mResultUser.setAccess_token(mUser.getAccess_token());
						bindNewUser(mResultUser);
					}
				} else {
					ToastUtils.showToast("登陆失败，无法绑定！");
				}
				break;
			case BIND_NEW_USER:
				boolean isSuccess = (Boolean) msg.obj;
				if (isSuccess) {
					mApp.saveUser(mResultUser);
					ToastUtils.showToast("绑定账号成功");
					mApp.startActivity(BindAccountActivity.this,
							MainActivity.class, null,
							Intent.FLAG_ACTIVITY_CLEAR_TOP);
				} else {
					ToastUtils.showToast("绑定账号成功");
				}
				break;
			}

		};

	};

	@Override
	public String setCenterTitle() {
		return "绑定账号";
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
		return R.layout.activity_bind_account;
	}

	@Override
	public void initView() {
		et_bindName = (EditText) findViewById(R.id.et_bindName);
		et_bindPwd = (EditText) findViewById(R.id.et_bindPwd);
		btn_done_bind = (Button) findViewById(R.id.btn_done_bind);
	}

	@Override
	public void initListener() {
		btn_done_bind.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_done_bind) {
			final String mobile = et_bindName.getText().toString();
			final String pwd = et_bindPwd.getText().toString();
			if (checkTheUserAndPwd(mobile, pwd)) {
				mApp.getExecutor().execute(new Runnable() {

					@Override
					public void run() {
						ModelUser user = new ModelUser();
						user.setMobile(mobile);
						user.setPwd(pwd);
						LoginIm loginIm = mApp.getLoginIm();
						ModelUser modelUser = (ModelUser) loginIm
								.authorize(user);
						Message message = Message.obtain();
						message.what = LOGIN;
						message.obj = modelUser;
						mHandle.sendMessage(message);
					}
				});
			}
		}
	}

	private boolean checkTheUserAndPwd(String userName, String pwd) {
		if (userName != null && pwd != null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否用的第三方登录，如果是的话就绑定第三方用户
	 * 
	 * @param user
	 * @return
	 */
	private void bindNewUser(final ModelUser user) {
		if (user.getType() != null && user.getType().length() > 0) {
			final LoginIm loginIm2 = mApp.getLoginIm();
			mApp.getExecutor().execute(new Runnable() {

				@Override
				public void run() {
					boolean isBind = loginIm2.bindNewUser(user);
					Message message = Message.obtain();
					message.what = BIND_NEW_USER;
					message.obj = isBind;
					mHandle.sendMessage(message);
				}
			});
		}

	}
}
