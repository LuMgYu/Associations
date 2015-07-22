package com.zhiyisoft.associations.request;

import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.content.Entity;
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
		mHeadName = name;
		mHeadValue = value;
		return this;
	}

	@Override
	public HttpRequestBase GetRequestObject() {
		HttpPost post = new HttpPost(mHostUrl);
		post.setHeader(mHeadName, mHeadValue.toString());
		try {
			for (int i = 0; i < mParams.size(); i++) {
				Log.i("request", mParams.get(i).toString()+"");
			}
			HttpEntity entity = new UrlEncodedFormEntity(mParams, HTTP.UTF_8);
			post.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return post;
	}
}
