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

	private String commentApp;
	private String type;
	private String sourceId;

	public ModelChildComment() {

	}

	public ModelChildComment(JSONObject jsonObject) {
		try {
			if (jsonObject.has("maskName")) {
				this.setMaskName(jsonObject.getString("maskName"));
			}
			if (jsonObject.has("content")) {
				this.setContent(jsonObject.getString("content"));
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

	public String getCommentApp() {
		return commentApp;
	}

	public void setCommentApp(String commentApp) {
		this.commentApp = commentApp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}