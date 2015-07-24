package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * author：qiuchunjia time：下午5:49:03 类描述：这个类是实现
 *
 */

public class ModelLeague extends Model {
	/*
	 * "id": "30066", "name": "志愿公益" 社团类型返回的值
	 * 
	 * 
	 * 创建社团需要的字段 name:社团名(45字符，约11个字)
	 * 
	 * categoryid：分类
	 * 
	 * logo:logoID
	 * 
	 * description:描述
	 * 
	 * school:学校ID
	 * 
	 * private:是否够开
	 * 
	 * tags: 标签(选填)
	 * 
	 * contact:联系人电话
	 */

	private String id;
	private String name;
	// --------------------------------------------------------------
	private String categoryid;
	private String logo;
	private String description;
	private String school;
	private String mPrivate;
	private String tags;
	private String contact;

	public ModelLeague() {

	}

	public ModelLeague(JSONObject jsonObject) {
		try {
			if (jsonObject.has("id")) {

				this.setId(jsonObject.getString("id"));
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getmPrivate() {
		return mPrivate;
	}

	public void setmPrivate(String mPrivate) {
		this.mPrivate = mPrivate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "ModelLeague [id=" + id + ", name=" + name + ", categoryid="
				+ categoryid + ", logo=" + logo + ", description="
				+ description + ", school=" + school + ", mPrivate=" + mPrivate
				+ ", tags=" + tags + ", contact=" + contact + "]";
	}
	

}
