package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午1:47:37 类描述：这个类是实现
 *
 */

public class ModelSchool extends Model {
	/*
	 * "id": "2978", "area": "四川", "name": "四川师范大学成都学院"
	 */

	/*
	 * 元数据需要的字段 metaName metaID ownerID userauth
	 */
	private String metaName;
	private String metaID;
	private String ownerID;
	private String userauth;

	private String id;
	private String area;
	private String name;

	public ModelSchool() {

	}

	public ModelSchool(JSONObject jsonObject) {
		try {
			if (jsonObject.has("id")) {

				this.setId(jsonObject.getString("id"));
			}
			if (jsonObject.has("area")) {
				this.setArea(jsonObject.getString("area"));
			}
			if (jsonObject.has("name")) {
				this.setName(jsonObject.getString("name"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMetaName() {
		return metaName;
	}

	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}

	public String getMetaID() {
		return metaID;
	}

	public void setMetaID(String metaID) {
		this.metaID = metaID;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	@Override
	public String toString() {
		return "ModelSchool [metaName=" + metaName + ", metaID=" + metaID
				+ ", ownerID=" + ownerID + ", userauth=" + userauth + ", id="
				+ id + ", area=" + area + ", name=" + name + "]";
	}

	public String getUserauth() {
		return userauth;
	}

	public void setUserauth(String userauth) {
		this.userauth = userauth;
	}

}
