package com.zhiyisoft.associations.fragment;

import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.zhiyisoft.associations.activity.RegisterFillInformationActivity;
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

	private ModelUser modelUser;

	private static final int LOGIN = 1;
	private static final int OTHER_LOGIN = 2;
	// 第三方登录标志
	private static final String TENGXUN_QQ = "QQ";
	private static final String SINA_WEIBO = "SinaWeb";

	private Handler mHandle = new Handler() {

		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {
			case LOGIN:
				ModelUser user = (ModelUser) msg.obj;
				if (user != null) {
					int is_init = Integer.valueOf(user.getIs_init());
					if (is_init == 0) {
						// TODO 表示没有完善资料，跳转到完善资料页面
						Bundle data = new Bundle();
						data.putSerializable(Config.SEND_ACTIVITY_DATA, user);
						mApp.startActivity(mActivity,
								RegisterFillInformationActivity.class, data,
								Intent.FLAG_ACTIVITY_CLEAR_TOP);
					} else {
						mApp.saveUser(user);
						mApp.startActivity(mActivity, MainActivity.class, null,
								Intent.FLAG_ACTIVITY_CLEAR_TOP);
					}
				} else {
					ToastUtils.showToast("登录失败");
				}
				break;

			case OTHER_LOGIN:
				ModelUser user1 = (ModelUser) msg.obj;
				if (user1 != null) {
					// 进入到注册页面，让用户注册，并且绑定手机号码
					Bundle data = new Bundle();
					data.putSerializable(Config.SEND_ACTIVITY_DATA, modelUser);
					mApp.startActivity(mActivity, RegisterPhoneActivity.class,
							data);
				} else {
					// 直接登录 并把这些信息保存到本地里面
					// saveToSharePreference(user1);
					Intent intent = new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.setClass(mActivity, MainActivity.class);
					mActivity.startActivity(intent);
				}
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
								.authorize(user);
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
							modelUser = new ModelUser();
							modelUser.setType(SINA_WEIBO);
							modelUser.setType_uid(String.valueOf(info
									.get("uid")));
							modelUser.setAccess_token(String.valueOf(info
									.get("access_token")));
							isOtherLogined(modelUser);
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

					@SuppressLint("NewApi")
					@Override
					public void onComplete(Bundle value, SHARE_MEDIA platform) {
						if (value != null
								&& !TextUtils.isEmpty(value.getString("uid"))) {
							modelUser = new ModelUser();
							modelUser.setType(TENGXUN_QQ);
							modelUser.setType_uid(String.valueOf(value
									.getString("uid")));
							modelUser.setAccess_token(String.valueOf(value
									.getString("access_token")));
							isOtherLogined(modelUser);
						}
						Toast.makeText(getActivity(), "授权完成",
								Toast.LENGTH_SHORT).show();
						// getQQMessage();
					}

					@Override
					public void onCancel(SHARE_MEDIA platform) {
						Toast.makeText(getActivity(), "授权取消",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	/**
	 * 判断是否曾经第三方登录过，如果登陆了就就不绑定了，直接进入主页
	 * 
	 * ，如果没有登录过，还需要进入注册页面进行手机号码和第三方登录进行绑定
	 * 
	 * @return
	 */
	private void isOtherLogined(final ModelUser user) {
		if (user != null) {
			Log.i("otherLogin", user.toString() + "");
			mApp.getExecutor().execute(new Runnable() {

				@Override
				public void run() {
					LoginIm loginIm = mApp.getLoginIm();
					ModelUser modelUser = (ModelUser) loginIm
							.getOtherLoginInfo(user);
					Message message = Message.obtain();
					message.what = OTHER_LOGIN;
					message.obj = modelUser;
					mHandle.sendMessage(message);
				}
			});
		}

	}
	// private void getQQMessage() {
	// // 获取相关授权信息
	// mController.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ,
	// new UMDataListener() {
	// @Override
	// public void onStart() {
	// Toast.makeText(getActivity(), "获取平台数据开始...",
	// Toast.LENGTH_SHORT).show();
	// }
	//
	// @Override
	// public void onComplete(int status, Map<String, Object> info) {
	// if (status == 200 && info != null) {
	// StringBuilder sb = new StringBuilder();
	// Set<String> keys = info.keySet();
	// for (String key : keys) {
	// sb.append(key + "=" + info.get(key).toString()
	// + "\r\n");
	// }
	// Log.d("TestData", sb.toString());
	// } else {
	// Log.d("TestData", "发生错误：" + status);
	// }
	// }
	// });
	// }

}
