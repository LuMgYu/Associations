package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

public class ModelEventWorks extends Model {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String maskName;
	private String content;

	public ModelEventWorks() {

	}

	public ModelEventWorks(JSONObject jsonObject) {
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

}