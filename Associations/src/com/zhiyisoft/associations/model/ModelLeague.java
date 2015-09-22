package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午5:49:03 类描述：这个类是实现
 *
 */

public class ModelLeague extends Model {
	/*
	 * (string) name 社团名称 必填 (int)
	 * 
	 * categoryId 社团分类 必填
	 * 
	 * (int) logo社团logo（接口12上传成功返回）
	 * 
	 * 必填 (string) description 社团描述 必填
	 * 
	 * (int) schoolId 社团所属学校
	 * 
	 * 必填 (int) private 是否公开（0公开，1不公开） 必填。
	 * 
	 * (string) openerName
	 * 
	 * 社团联系人 必填 (string)contact 社团联系方式 必填
	 */
	/*** 创建社团需要的数据 ****/
	private String name;
	private int categoryId;
	private int logo;
	private String description;
	private int schoolId;
	private int Private;
	private String openerName;
	private String contact;

	/*** 创建社团需要的数据 end ****/
	/*** 社团列表需要的数据 ****/
	private String gid;
	// private String name;
	// private String description;
	private String logoUrl;
	private String members;
	private int isin;

	/*** 创建社团需要的数据 end ****/

	public ModelLeague() {

	}

	public ModelLeague(JSONObject jsonObject) {
		try {
			if (jsonObject.has("name")) {
				this.setName(jsonObject.getString("name"));
			}
			if (jsonObject.has("gid")) {
				this.setGid(jsonObject.getString("gid"));
			}

			if (jsonObject.has("description")) {
				this.setDescription(jsonObject.getString("description"));
			}

			if (jsonObject.has("logo")) {
				this.setLogoUrl(jsonObject.getString("logo"));
			}
			if (jsonObject.has("members")) {
				this.setMembers(jsonObject.getString("members"));
			}
			if (jsonObject.has("isin")) {
				this.setIsin(jsonObject.getInt("isin"));
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getLogo() {
		return logo;
	}

	public void setLogo(int logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getPrivate() {
		return Private;
	}

	public void setPrivate(int private1) {
		Private = private1;
	}

	public String getOpenerName() {
		return openerName;
	}

	public void setOpenerName(String openerName) {
		this.openerName = openerName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public int getIsin() {
		return isin;
	}

	public void setIsin(int isin) {
		this.isin = isin;
	}

}
