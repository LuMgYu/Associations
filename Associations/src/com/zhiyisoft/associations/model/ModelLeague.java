package com.zhiyisoft.associations.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.JsonUtils;

/**
 * author：qiuchunjia time：下午5:49:03 类描述：这个类是实现
 *
 */

public class ModelLeague extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private String schoolName;
	private int Private;
	private String openerName;
	private String contact;

	/*** 创建社团需要的数据 end ****/
	/*** 社团列表需要的数据 ****/
	private String gid;
	// private String name;
	// private String description;
	private String logourl;
	private String members;
	private int isin;

	/*** 创建社团需要的数据 end ****/
	private String categoryName;
	private String event_count;
	private String userlevel;
	private String members_count;
	private String topic_count;
	/*** 创建相册的时候需要的数据 ****/
	private String hide;
	private String albumName;
	private String albumInfo;
	/*** 创建相册的时候需要的数据 end ****/
	private List<Model> memberlist; // 社团成员

	private List<Model> albums; // 社团详情需要的相册展示

	private int type;

	public ModelLeague() {

	}

	public ModelLeague(JSONObject jsonObject) {
		boolean flag = false;
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
				this.setLogourl(jsonObject.getString("logo"));
			}
			if (jsonObject.has("schoolName")) {
				this.setSchoolName(jsonObject.getString("schoolName"));
			}
			if (jsonObject.has("school")) {
				this.setSchoolName(jsonObject.getString("school"));
			}
			if (jsonObject.has("members")) {
				this.setMembers(jsonObject.getString("members"));
				flag = true;
			}
			if (jsonObject.has("categoryName")) {
				this.setCategoryName(jsonObject.getString("categoryName"));
			}
			if (jsonObject.has("isin")) {
				this.setIsin(jsonObject.getInt("isin"));
			}
			if (jsonObject.has("event_count")) {
				this.setEvent_count(jsonObject.getString("event_count"));
			}
			if (jsonObject.has("userlevel")) {
				this.setUserlevel(jsonObject.getString("userlevel"));
			}
			if (jsonObject.has("members_count")) {
				this.setMembers_count(jsonObject.getString("members_count"));
			}
			if (jsonObject.has("topic_count")) {
				this.setTopic_count(jsonObject.getString("topic_count"));
			}
			if (jsonObject.has("openerName")) {
				this.setOpenerName(jsonObject.getString("openerName"));
			}
			if (jsonObject.has("contact")) {
				this.setContact(jsonObject.getString("contact"));
			}
			if (jsonObject.has("members") && flag != true) {
				JSONArray array = jsonObject.getJSONArray("members");
				List<Model> models = JsonUtils.parseJsonArray(array,
						new ModelLeagueMember());
				this.setMemberlist(models);
			}
			if (jsonObject.has("albums")) {
				JSONArray array = jsonObject.getJSONArray("albums");
				List<Model> models = JsonUtils.parseJsonArray(array,
						new ModelLeagueAlbum());
				this.setAlbums(models);
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

	public String getLogourl() {
		return logourl;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Model> getMemberlist() {
		return memberlist;
	}

	public void setMemberlist(List<Model> memberlist) {
		this.memberlist = memberlist;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getEvent_count() {
		return event_count;
	}

	public void setEvent_count(String event_count) {
		this.event_count = event_count;
	}

	public String getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	public String getMembers_count() {
		return members_count;
	}

	public void setMembers_count(String members_count) {
		this.members_count = members_count;
	}

	public String getTopic_count() {
		return topic_count;
	}

	public void setTopic_count(String topic_count) {
		this.topic_count = topic_count;
	}

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumInfo() {
		return albumInfo;
	}

	public void setAlbumInfo(String albumInfo) {
		this.albumInfo = albumInfo;
	}

	public List<Model> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Model> albums) {
		this.albums = albums;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
