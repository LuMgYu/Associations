package com.zhiyisoft.associations.application;

import com.zhiyisoft.associations.activity.base.BaseActivity;

import android.app.Application;
import android.content.Context;

/**
 * author：qiuchunjia time：下午2:16:14 类描述：
 * 这个类是应用的application用來存一下常用的變量，獲取用戶認證等等子類的
 *
 */

public class Association extends Application {
	private BaseActivity mActivity;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	/**
	 * @return 返回上下文
	 */
	public static Context getContext() {
		return getContext();
	}

	public void setActivity(BaseActivity activity) {
		this.mActivity = activity;
	}

	public BaseActivity getActivity() {
		return mActivity;
	}
}
