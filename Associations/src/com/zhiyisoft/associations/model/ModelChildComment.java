package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

public class ModelChildComment extends Model {

	// 输入参数： (string) commentApp 评论app必填
	// (string) type 评论类型 必填
	// (int) sourceId 资源id必填
	private static final long serialVersionUID = 1L;
	private String maskName;
	private String content;
	private String ctime;


	public ModelChildComment() {

	}

	public ModelChildComment(JSONObject jsonObject) {
		try {
			if (jsonObject.has("maskName")) {
				this.setMaskName(jsonObject.getString("maskName"));
			}
			if (jsonObject.has("name")) {
				this.setMaskName(jsonObject.getString("name"));
			}

			if (jsonObject.has("content")) {
				this.setContent(jsonObject.getString("content"));
			}

			if (jsonObject.has("ctime")) {
				this.setCtime(jsonObject.getString("ctime"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getMaskName() {
		return maskName;
	}

	public void setMaskName(String maskName) {
		this.maskName = maskName;
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

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

}