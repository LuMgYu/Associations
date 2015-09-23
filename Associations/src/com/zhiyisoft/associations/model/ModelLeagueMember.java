package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午5:49:03 类描述：这个类是实现
 *
 */

public class ModelLeagueMember extends Model {
	/*
	 * "name":"Aa", "level":"3", "id":"291956", "schoolName":"云南大学", "faceurl":
	 * "http://daxs.zhiyicx.com/attachment/uploads/2015/0921/09/55ff5fa775084.png"
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String level;
	private String id;
	private String schoolName;
	private String faceurl;

	public ModelLeagueMember() {

	}

	public ModelLeagueMember(JSONObject jsonObject) {
		try {
			if (jsonObject.has("name")) {

				this.setName(jsonObject.getString("name"));
			}
			if (jsonObject.has("level")) {
				this.setLevel(jsonObject.getString("level"));
			}
			if (jsonObject.has("id")) {

				this.setId(jsonObject.getString("id"));
			}
			if (jsonObject.has("schoolName")) {
				this.setSchoolName(jsonObject.getString("schoolName"));
			}
			if (jsonObject.has("faceurl")) {

				this.setFaceurl(jsonObject.getString("faceurl"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getFaceurl() {
		return faceurl;
	}

	public void setFaceurl(String faceurl) {
		this.faceurl = faceurl;
	}
}
