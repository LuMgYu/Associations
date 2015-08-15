package com.zhiyisoft.associations.application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.api.Api;
import com.zhiyisoft.associations.util.Anim;

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
	/** api区域 */
	private Api.LoginImpl mLogin;
	private Api.LeagueImpl mLeague;
	private Api.SchoolImpl mSchool;
	private Api.BaseSettingImpl mBaseSetting;
	private Api.RegisterImpl mRegister;

	/** api区域结束 */
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
		return "http://api.univs.cn/";
	}

	/**
	 * 08-15 06:52:01.629: W/SingleClientConnManager(2450): Invalid use of
	 * SingleClientConnManager: connection still allocated.
	 * 报错信息为采用单例的ingleClientConnManager，连接还在持续中
	 * 
	 * ，控制mHttpClient被一个线程访问完后才能被其它线程访问
	 * 
	 * */
	public static HttpClient getHttpClient() {
		if (mHttpClient == null) {
			HttpParams params = new BasicHttpParams();
			ConnManagerParams.setMaxTotalConnections(params, 100);
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			// Create and initialize scheme registry
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			// Create an HttpClient with the ThreadSafeClientConnManager.
			// This connection manager must be used if more than one thread will
			// be using the HttpClient.
			ClientConnectionManager cm = new ThreadSafeClientConnManager(
					params, schemeRegistry);
			mHttpClient = new DefaultHttpClient(cm, params);
		}
		return mHttpClient;
	}

	// -----------------------------获取api------------------------------------------------------
	public Api.LoginImpl getLoginIm() {
		if (mLogin == null) {
			mLogin = new Api.LoginImpl();
		}
		return mLogin;
	}

	public Api.LeagueImpl getLeagueIm() {
		if (mLeague == null) {
			mLeague = new Api.LeagueImpl();
		}
		return mLeague;
	}

	public Api.SchoolImpl getmSchoolIm() {
		if (mSchool == null) {
			mSchool = new Api.SchoolImpl();
		}
		return mSchool;
	}

	public Api.BaseSettingImpl getmBaseSettingIm() {
		if (mBaseSetting == null) {
			mBaseSetting = new Api.BaseSettingImpl();
		}
		return mBaseSetting;
	}

	public Api.RegisterImpl getRegisterIm() {
		if (mRegister == null) {
			mRegister = new Api.RegisterImpl();
		}
		return mRegister;
	}

	// -----------------------------获取api 结束-----------------------------
	public void startActivity(Activity now, Class<? extends Activity> target,
			Bundle data) {
		Intent intent = new Intent();
		intent.setClass(now, target);
		if (data != null) {
			if (intent.getExtras() != null) {
				intent.replaceExtras(data);
			} else {
				intent.putExtras(data);
			}
		}
		now.startActivity(intent);
		System.out.println("now" + now);
		System.out.println("target" + target);
		Anim.in(now);
	}

	public void startActivity(Activity now, Class<? extends Activity> target,
			Bundle data, int flag) {
		Intent intent = new Intent();
		intent.setClass(now, target);
		intent.setFlags(flag); // 注意本行的FLAG设置
		if (data != null) {
			if (intent.getExtras() != null) {
				intent.replaceExtras(data);
			} else {
				intent.putExtras(data);
			}
		}
		now.startActivity(intent);
		Anim.in(now);
	}

	public void startActivityForResult(Activity now,
			Class<? extends Activity> target, Bundle data) {
		Intent intent = new Intent();
		intent.setClass(now, target);
		if (data != null) {
			if (intent.getExtras() != null) {
				intent.replaceExtras(data);
			} else {
				intent.putExtras(data);
			}
		}
		now.startActivityForResult(intent, 3456);
		Anim.in(now);
	}

	/**
	 * 判断网络连接
	 * 
	 * @param context
	 * @return
	 */
	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static String getCache_path() {
		return "cache_path";
	}
}
