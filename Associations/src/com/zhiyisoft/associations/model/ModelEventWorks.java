package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

public class ModelEventWorks extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
		 * 
		 */
	private String title;
	private String intro;
	private String id;
	private int explainType;

	public ModelEventWorks() {

	}

	public ModelEventWorks(JSONObject jsonObject) {
		try {
			// private String title;
			// private String intro;
			// private String id;
			// private String explainType;
			if (jsonObject.has("title")) {
				this.setTitle(jsonObject.getString("title"));
			}
			if (jsonObject.has("intro")) {
				this.setIntro(jsonObject.getString("intro"));
			}
			if (jsonObject.has("id")) {
				this.setId(jsonObject.getString("id"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getExplainType() {
		return explainType;
	}

	public void setExplainType(int explainType) {
		this.explainType = explainType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}