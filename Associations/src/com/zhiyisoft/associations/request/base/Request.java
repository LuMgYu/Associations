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
import com.zhiyisoft.associations.util.StreamTool;

/***********************************************************************
 * Module:  Request.java
 * Author:  qcj qq:260964739
 * Purpose: Defines the Class Request
 ***********************************************************************/

/** 定制網絡請求 */
public abstract class Request {
	private HttpClient mClient;
	/** host的基本地址 */
	public String mHostUrl;
	public List<NameValuePair> mParams;
	public HashMap<String, Object> mHeadMap;
	public String mBodyParams = "";

	public Request() {
		mClient = getHttpClient();
		mHostUrl = getTheHostUrl();
		mParams = new ArrayList<NameValuePair>();
		mHeadMap = new HashMap<String, Object>();
	}

	/**
	 * @param name
	 *            鍵值對
	 * @param value
	 * @pdOid 添加键值对，并且返回request对象
	 */
	public abstract Request addBodyParam(String name, Object value);

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
	 * @return 获取请求方式的对象
	 */
	public abstract HttpRequestBase GetRequestObject();

	/** 獲取網絡數據，并返回object類型，方便具體用時，具體解析 */
	public Object run() {
		HttpRequestBase requestBase = GetRequestObject();
		if (requestBase != null && mClient != null) {
			try {
				HttpResponse response = mClient.execute(requestBase);
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					InputStream stream = response.getEntity().getContent();
					Object result = StreamTool.streamToString(stream);
					Log.i("request", "result=" + result.toString());
					return result;
				}
				Log.i("request", "status=" + status);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

	/** 过滤一下请求地址 */
	public boolean filterTheUrl(String url) {
		if (url.contains("daxs.zhiyicx.com")) {
			return true;
		}
		return false;
	}

	/** 獲取主機地址，注意：主機地址放在配置文件里面，方便修改 */
	public String getTheHostUrl() {
		// TODO: 通过application获取host地址
		return Association.getHostUrl();
	}

	public HttpClient getHttpClient() {
		return Association.getHttpClient();
	}

}