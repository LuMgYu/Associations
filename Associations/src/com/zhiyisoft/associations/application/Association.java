package com.zhiyisoft.associations.application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.zhiyisoft.associations.activity.base.BaseActivity;

/**
 * author：qiuchunjia time：下午2:16:14 类描述：
 * 这个类是应用的application用來存一下常用的變量，獲取用戶認證等等子類的
 *
 */

public class Association extends Application {
	private BaseActivity mActivity;
	private static Association mApp;
	/** 定义一个线程池，整個app都用它 */
	private ExecutorService mExecutor;
	public static HttpClient mHttpClient;

	@Override
	public void onCreate() {
		super.onCreate();
		mApp = this;
	}

	/**
	 * @return 返回上下文
	 */
	public static Context getContext() {
		return mApp;
	}

	public void setActivity(BaseActivity activity) {
		this.mActivity = activity;
	}

	public BaseActivity getActivity() {
		return mActivity;
	}

	public ExecutorService getExecutor() {
		if (mExecutor == null) {
			// 获取当前系统的CPU 数目 （这样更加考虑各个手机的性能问题）
			int cpuNums = Runtime.getRuntime().availableProcessors();
			mExecutor = Executors.newFixedThreadPool(cpuNums * 4);
		}
		return mExecutor;
	}

	/**
	 * 关闭线程
	 */
	public void shutDownExecutor() {
		if (mExecutor != null) {
			mExecutor.shutdownNow();
		}
	}

	/**
	 * @return 获取主机地址
	 */
	public static String getHostUrl() {
		// TODO 以后把地址写到xml里面
		return "http://daxs.zhiyicx.com/api?";
	}

	public static HttpClient getHttpClient() {
		if (mHttpClient == null) {
			mHttpClient = new DefaultHttpClient();
		}
		return mHttpClient;
	}
}
