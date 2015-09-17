package com.zhiyisoft.associations.request.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;

import android.util.Log;

import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.cache.BaseCache;
import com.zhiyisoft.associations.util.StreamTool;

/***********************************************************************
 * Module:  Request.java
 * Author:  qcj qq:260964739
 * Purpose: Defines the Class Request
 ***********************************************************************/

/** 定制網絡請求 */
public abstract class Request {
	BaseCache mCache;
	private HttpClient mClient; // 客服端
								// 需要在application类或者其子类获取,保证访问网络的时候就用这个客服端，避免资源浪费
	/** host的基本地址 */
	public String mHostUrl;
	/** host的尾部 */
	/*
	 * 例如 http://api.univs.cn/appSendSMSCode.action
	 * 
	 * http://api.univs.cn/
	 * 
	 * host地址
	 * 
	 * appSendSMSCode.action
	 * 
	 * mHostUrlfooter尾部地址
	 */
	public String mHostUrlfooter;
	public List<NameValuePair> mParams;
	public HashMap<String, Object> mHeadMap;
	public String mBodyParams = "?";

	public Request() {
		mClient = getHttpClient();
		mHostUrl = getTheHostUrl();
		mParams = new ArrayList<NameValuePair>();
		mHeadMap = new HashMap<String, Object>();
		mCache = BaseCache.getInstance(Association.getContext());
	}

	/**
	 * 添加頭部
	 * 
	 * @param name
	 *            键
	 * @param value
	 *            值
	 */
	public abstract Request addHeaderParam(String name, Object value);

	/**
	 * @param name
	 *            鍵值對
	 * @param value
	 * @pdOid 添加键值对，并且返回request对象
	 */
	public abstract Request addBodyParam(String name, Object value);

	/**
	 * @return 获取请求方式的对象
	 */
	public abstract HttpRequestBase GetRequestObject();

	/** 獲取網絡數據，并返回object類型，方便具體用時，具體解析 */
	public Object run() {
		HttpRequestBase requestBase = GetRequestObject();
		// 从缓存中获取数据
		// if (mCache != null) {
		// Log.i("cache", requestBase.getURI().toString() + "");
		// Object object = mCache.getTheData(requestBase.getURI().toString());
		// if (object != null) {
		// return object;
		// }
		// }
		// 从网络中获取数据
		Log.i("getSchools", requestBase.getURI().toString());
		if (requestBase != null && mClient != null) {
			try {
				HttpResponse response = mClient.execute(requestBase);
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					InputStream stream = response.getEntity().getContent();
					Object result = StreamTool.streamToString(stream);
					Log.i("request", "result=" + result.toString());
					if (stream != null) {
						stream.close(); // 关闭数据流 如果不关闭的话，程序偶尔会出现不可预测的崩溃
					}
					mCache.addTheData(result, requestBase.getURI().toString());
					return result;
				}
				Log.i("request", "status=" + status);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}

		}
		return null;

	}

	/** 獲取主機地址，注意：主機地址放在配置文件里面，方便修改 */
	public String getTheHostUrl() {
		// TODO: 通过application获取host地址
		return Association.getHostUrl(); // association 为application的子类
	}

	public HttpClient getHttpClient() {
		return Association.getHttpClient();
	}

	/**
	 * 把尾部整和赋值给mhosturl 这样链接地址更完整了
	 * 
	 * @param str
	 *            主机的尾部
	 */
	public void setHostUrlFooter(String str) {
		mHostUrl = mHostUrl + str;
	}

	/**
	 * 设置主机地址，主要是考虑到获取学校的地址的时候 是另外一个主机地址，
	 * 
	 * 所以就另外添加了个方法，共外部调用
	 * 
	 * @param hostUrl
	 */
	public void setHostUrl(String hostUrl) {
		this.mHostUrl = hostUrl;
	}

}