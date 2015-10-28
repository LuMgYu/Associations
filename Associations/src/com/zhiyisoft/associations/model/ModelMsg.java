package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.JsonUtils;

/**
 * author：qiuchunjia time：上午9:58:13 类描述：这个类是实现
 *
 */

public class ModelMsg extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uid; // 接收用户的id
	private String content; // content 内容
	private String withMaskId; // maskid 内容
	private String notifyId;
	private String maskId;
	private String toMaskId;
	private String cTime;
	private String isRead;
	private ModelUser mUser;

	public ModelMsg() {

	}

	public ModelMsg(JSONObject jsonObject) {
		try {
			if (jsonObject.has("uid")) {
				this.setUid(jsonObject.getString("uid"));
			}
			if (jsonObject.has("content")) {
				this.setContent(jsonObject.getString("content"));
			}
			if (jsonObject.has("withMaskId")) {
				this.setWithMaskId(jsonObject.getString("withMaskId"));
			}
			if (jsonObject.has("notifyId")) {
				this.setNotifyId(jsonObject.getString("notifyId"));
			}
			if (jsonObject.has("maskId")) {
				this.setMaskId(jsonObject.getString("maskId"));
			}
			if (jsonObject.has("toMaskId")) {
				this.setToMaskId(jsonObject.getString("toMaskId"));
			}
			if (jsonObject.has("cTime")) {
				this.setcTime(jsonObject.getString("cTime"));
			}
			if (jsonObject.has("isRead")) {
				this.setIsRead(jsonObject.getString("isRead"));
			}
			if (jsonObject.has("userinfo")) {
				JSONObject object = jsonObject.getJSONObject("userinfo");
				ModelUser user = (ModelUser) JsonUtils.parseJsonObject(object,
						new ModelUser());
				this.setmUser(user);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getWithMaskId() {
		return withMaskId;
	}

	public void setWithMaskId(String withMaskId) {
		this.withMaskId = withMaskId;
	}

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
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

	public ModelUser getmUser() {
		return mUser;
	}

	public void setmUser(ModelUser mUser) {
		this.mUser = mUser;
	}

	@Override
	public String toString() {
		return "ModelMsg [uid=" + uid + ", content=" + content
				+ ", withMaskId=" + withMaskId + ", notifyId=" + notifyId
				+ ", maskId=" + maskId + ", toMaskId=" + toMaskId + ", cTime="
				+ cTime + ", isRead=" + isRead + ", mUser=" + mUser + "]";
	}

}
