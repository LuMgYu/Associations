package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.JsonUtils;

/**
 * author：qiuchunjia time：下午3:27:15 类描述：这个类是实现
 *
 */

public class ModelTiding extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String gid;
	private String title;
	private String content;
	private String ctime;
	private ModelUser user;

	public ModelTiding() {

	}

	public ModelTiding(JSONObject jsonObject) {
		try {
			if (jsonObject.has("id")) {
				this.setId(jsonObject.getString("id"));
			}
			if (jsonObject.has("gid")) {
				this.setGid(jsonObject.getString("gid"));
			}
			if (jsonObject.has("title")) {
				this.setTitle(jsonObject.getString("title"));
			}
			if (jsonObject.has("content")) {
				this.setContent(jsonObject.getString("content"));
			}
			if (jsonObject.has("ctime")) {
				this.setCtime(jsonObject.getString("ctime"));
			}
			if (jsonObject.has("userinfo")) {
				JSONObject object = jsonObject.getJSONObject("userinfo");
				ModelUser user = (ModelUser) JsonUtils.parseJsonObject(object,
						new ModelUser());
				this.setUser(user);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public ModelUser getUser() {
		return user;
	}

	public void setUser(ModelUser user) {
		this.user = user;
	}

}
