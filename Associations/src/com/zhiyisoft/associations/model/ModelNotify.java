package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午11:13:28 类描述：这个类是实现通知类
 *
 */

public class ModelNotify extends Model {

	//
	// "notifyId":"673",
	// "uid":"1",
	// "maskId":"0",
	// "toMaskId":"0",
	// "app":null,
	// "type":"",
	// "content":"的撒旦撒旦阿斯顿撒",
	// "cTime":"0",
	// "isRead":"0"
	private String notifyId;
	private String uid;
	private String maskId;
	private String toMaskId;
	private String app;
	private String type;
	private String content;
	private String cTime;
	private String isRead;
	private String title;

	public ModelNotify() {

	}

	public ModelNotify(JSONObject jsonObject) {
		try {
			if (jsonObject.has("notifyId")) {
				this.setNotifyId(jsonObject.getString("notifyId"));
			}
			if (jsonObject.has("uid")) {
				this.setUid(jsonObject.getString("uid"));
			}
			if (jsonObject.has("maskId")) {
				this.setMaskId(jsonObject.getString("maskId"));
			}
			if (jsonObject.has("toMaskId")) {
				this.setToMaskId(jsonObject.getString("toMaskId"));
			}
			if (jsonObject.has("app")) {
				this.setApp(jsonObject.getString("app"));
			}

			if (jsonObject.has("type")) {
				this.setType(jsonObject.getString("type"));
			}
			if (jsonObject.has("content")) {
				this.setContent(jsonObject.getString("content"));
			}
			if (jsonObject.has("info")) {
				this.setContent(jsonObject.getString("info"));
			}
			if (jsonObject.has("cTime")) {
				this.setcTime(jsonObject.getString("cTime"));
			}
			if (jsonObject.has("isRead")) {
				this.setIsRead(jsonObject.getString("isRead"));
			}
			if (jsonObject.has("title")) {
				this.setTitle(jsonObject.getString("title"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMaskId() {
		return maskId;
	}

	public void setMaskId(String maskId) {
		this.maskId = maskId;
	}

	public String getToMaskId() {
		return toMaskId;
	}

	public void setToMaskId(String toMaskId) {
		this.toMaskId = toMaskId;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
