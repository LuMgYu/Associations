package com.zhiyisoft.associations.fragment;

import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.ForgetPwdPhoneActivity;
import com.zhiyisoft.associations.activity.MainActivity;
import com.zhiyisoft.associations.activity.RegisterPhoneActivity;
import com.zhiyisoft.associations.api.LoginIm;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.util.ToastUtils;

/**
 * author：qiuchunjia time：上午9:18:12 类描述：这个类是实现登陆的
 *
 */

public class FragmentLogin extends BaseFragment {

	private ImageView iv_login_icon;
	private EditText et_loginName;
	private EditText et_loginPwd;
	private Button bt_login;
	private Button bt_register;
	private ImageView iv_qq;
	private ImageView iv_weibo;
	private static final int LOGIN = 1;
	private static final int FORGET_PWD = 2;
	private static final int REGISTER = 3;
	private static final int LOGIN_QQ = 4;
	private static final int LOGING_WEIBO = 5;

	private Handler mHandle = new Handler() {

		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {
			case LOGIN:
				ModelUser user = (ModelUser) msg.obj;
				if (user != null) {
					// TODO 把获取的信息，保存到SharedPreferences类中
					Log.i("user", user.getMobile() + "  " + user.getPwd() + " "
							+ user.getUserauth());
					saveToSharePreference(user);
					Intent intent = new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.setClass(mActivity, MainActivity.class);
					mActivity.startActivity(intent);
					// mApp.startActivity(mActivity, MainActivity.class, null,
					// Intent.FLAG_ACTIVITY_CLEAR_TOP);
				} else {
					ToastUtils.showToast("登录失败");
				}
				break;

			case FORGET_PWD:
				break;
			case REGISTER:
				break;
			case LOGIN_QQ:
				break;
			case LOGING_WEIBO:
				break;
			}

		}

	};

	@Override
	public boolean checkTheUser() {
		return true;
	}

	@Override
	public int getLayoutId() {
		return R.layout.fragment_login;
	}

	@Override
	public void initView() {
		iv_login_icon = (ImageView) findViewById(R.id.iv_login_icon);
		et_loginName = (EditText) findViewById(R.id.et_loginName);
		et_loginPwd = (EditText) findViewById(R.id.et_loginPwd);
		bt_login = (Button) findViewById(R.id.bt_login);
		bt_register = (Button) findViewById(R.id.bt_register);
		iv_qq = (ImageView) findViewById(R.id.iv_qq);
		iv_weibo = (ImageView) findViewById(R.id.iv_weibo);

	}

	@Override
	public void initListener() {
		// tv_title_right.setOnClickListener(this);
		iv_login_icon.setOnClickListener(this);
		bt_login.setOnClickListener(this);
		bt_register.setOnClickListener(this);
		iv_qq.setOnClickListener(this);
		iv_weibo.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_title_right:
			mApp.startActivity(getActivity(), ForgetPwdPhoneActivity.class,
					null);
			break;
		case R.id.iv_login_icon:
			ToastUtils.showToast("点击了头像");
			break;

		case R.id.bt_login:
			final String username = et_loginName.getText().toString();
			final String pwd = et_loginPwd.getText().toString();
			if (checkTheUserAndPwd(username, pwd)) {
				mApp.getExecutor().execute(new Runnable() {

					@Override
					public void run() {
						ModelUser user = new ModelUser();
						user.setMobile(username);
						user.setPwd(pwd);
						LoginIm loginIm = mApp.getLoginIm();
						ModelUser modelUser = (ModelUser) loginIm
								.appUserMobileLogin(user);
						Message message = Message.obtain();
						message.what = LOGIN;
						message.obj = modelUser;
						mHandle.sendMessage(message);
					}
				});
			}
			break;
		case R.id.bt_register:
			mApp.startActivity(getActivity(), RegisterPhoneActivity.class, null);
			break;
		case R.id.iv_qq:
			qqLogin();
			break;
		case R.id.iv_weibo:
			sinaLogin();
			break;
		}

	}

	/**
	 * 检查账号和密码是否合格，这里主要是判断是否为空之类的，比较明显的错误
	 * 
	 * @param userName
	 *            用户名
	 * @param pwd
	 *            密码
	 * @return
	 */
	private boolean checkTheUserAndPwd(String userName, String pwd) {
		if (userName != null && pwd != null) {
			return true;
		}
		return false;
	}

	/**
	 * 把获取的user保存到SharePreference，方便下次调用
	 * 
	 * @param user
	 *            需要保存的用户
	 */
	private void saveToSharePreference(ModelUser user) {
		SharedPreferences preferences = mActivity.getSharedPreferences(
				Config.USER_DATA, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(Config.MOBILE, user.getMobile());
		editor.putString(Config.PWD, user.getPwd());
		editor.putString(Config.USERAUTH, user.getUserauth());
		editor.commit();
	}

	@Override
	public void initIntentData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	};

	// ----------------------------------实现第三方登陆----------------------------------------------
	UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.login");

	/**
	 * 新浪登陆
	 */
	public void sinaLogin() {
		mController.doOauthVerify(getActivity(), SHARE_MEDIA.SINA,
				new UMAuthListener() {
					@Override
					public void onError(SocializeException e,
							SHARE_MEDIA platform) {
					}

					@Override
					public void onComplete(Bundle value, SHARE_MEDIA platform) {
						if (value != null
								&& !TextUtils.isEmpty(value.getString("uid"))) {
							Toast.makeText(getActivity(), "授权成功.",
									Toast.LENGTH_SHORT).show();
							getSinaMessage();
						} else {
							Toast.makeText(getActivity(), "授权失败",
									Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onCancel(SHARE_MEDIA platform) {
					}

					@Override
					public void onStart(SHARE_MEDIA platform) {
					}
				});
	}

	private void getSinaMessage() {
		mController.getPlatformInfo(getActivity(), SHARE_MEDIA.SINA,
				new UMDataListener() {
					@Override
					public void onStart() {
						Toast.makeText(getActivity(), "获取平台数据开始...",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onComplete(int status, Map<String, Object> info) {
						if (status == 200 && info != null) {
							StringBuilder sb = new StringBuilder();
							Set<String> keys = info.keySet();
							for (String key : keys) {
								sb.append(key + "=" + info.get(key).toString()
										+ "\r\n");
							}
							Log.d("TestData", sb.toString());
						} else {
							Log.d("TestData", "发生错误：" + status);
						}
					}
				});
	}

	public void qqLogin() {
		// 参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(getActivity(),
				"1104831952", "ioLElezMMAJ94NHm");
		qqSsoHandler.addToSocialSDK();
		mController.doOauthVerify(getActivity(), SHARE_MEDIA.QQ,
				new UMAuthListener() {
					@Override
					public void onStart(SHARE_MEDIA platform) {
						Toast.makeText(getActivity(), "授权开始",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onError(SocializeException e,
							SHARE_MEDIA platform) {
						Toast.makeText(getActivity(), "授权错误",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onComplete(Bundle value, SHARE_MEDIA platform) {
						if (value != null
								&& !TextUtils.isEmpty(value.getString("uid"))) {
							Log.d("TestData", value.getString("uid"));
						}
						Toast.makeText(getActivity(), "授权完成",
								Toast.LENGTH_SHORT).show();
						getQQMessage();
					}

					@Override
					public void onCancel(SHARE_MEDIA platform) {
						Toast.makeText(getActivity(), "授权取消",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	private void getQQMessage() {
		// 获取相关授权信息
		mController.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ,
				new UMDataListener() {
					@Override
					public void onStart() {
						Toast.makeText(getActivity(), "获取平台数据开始...",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onComplete(int status, Map<String, Object> info) {
						if (status == 200 && info != null) {
							StringBuilder sb = new StringBuilder();
							Set<String> keys = info.keySet();
							for (String key : keys) {
								sb.append(key + "=" + info.get(key).toString()
										+ "\r\n");
							}
							Log.d("TestData", sb.toString());
						} else {
							Log.d("TestData", "发生错误：" + status);
						}
					}
				});
	}

}
