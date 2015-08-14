package com.zhiyisoft.associations.request;

import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import android.util.Log;

import com.zhiyisoft.associations.request.base.Request;

/**
 * author：qiuchunjia time：下午2:38:48 类描述：这个类是实现
 *
 */

public class Get extends Request {
	private String url = "";

	@Override
	public Request addBodyParam(String name, Object value) {
		mBodyParams = mBodyParams + name + "=" + value.toString() + "&";
		return this;
	}

	@Override
	public Request addHeaderParam(String name, Object value) {
		mHeadMap.put(name, value);
		return this;
	}

	@Override
	public HttpRequestBase GetRequestObject() {
		url = mHostUrl + mBodyParams;
		if (fiterTheUrl(url)) {
			Log.i("request", "url=" + url);
			HttpGet get = new HttpGet(url);
			Set<Entry<String, Object>> set = mHeadMap.entrySet();
			// 通过循环把头部全部添加进去
			for (Entry<String, Object> entry : set) {
				get.addHeader(entry.getKey(), entry.getValue().toString());
			}
			return get;
		}
		return null;
	}

	/**
	 * 过滤掉最后一个& 且检查主机域名是否正确
	 * 
	 * @param mBodyParams
	 * @return
	 */
	private boolean fiterTheUrl(String url) {
		if (url != null & url.length() > 0) {
			this.url = url.substring(0, url.length() - 1);
		}
		if (url.contains(getTheHostUrl())) {
			return true;
		}
		return false;
	}
}
