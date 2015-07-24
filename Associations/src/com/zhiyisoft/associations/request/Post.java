package com.zhiyisoft.associations.request;

import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.util.Log;

import com.zhiyisoft.associations.request.base.Request;

/**
 * author：qiuchunjia time：下午2:38:48 类描述：这个类是实现
 *
 */

public class Post extends Request {

	@Override
	public Request addBodyParam(String name, Object value) {
		mParams.add(new BasicNameValuePair(name, value.toString()));
		return this;
	}

	@Override
	public Request addHeaderParam(String name, Object value) {
		mHeadMap.put(name, value);
		return this;
	}

	@Override
	public HttpRequestBase GetRequestObject() {
		HttpPost post = new HttpPost(mHostUrl);
		Set<Entry<String, Object>> set = mHeadMap.entrySet();
		// 通过循环把头部全部添加进去
		for (Entry<String, Object> entry : set) {
			post.addHeader(entry.getKey(), entry.getValue().toString());
			Log.i("request", "entry.getKey()=" + entry.getKey()
					+ " entry.getValue()=" + entry.getValue().toString());
		}
		try {
			HttpEntity entity = new UrlEncodedFormEntity(mParams, HTTP.UTF_8);
			post.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return post;
	}
}
