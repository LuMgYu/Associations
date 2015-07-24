package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * author：qiuchunjia time：下午2:21:50 类描述：这个类是实现用户model uid:
 * 
 * 用户ID client_id:通用 token：用户token (重要) pic：用户头像 name：用户真实姓名 uname：用户名
 * email：电子邮箱 sex：性别 Male、female、空
 *
 */

public class ModelUser extends Model {
	/**
	 * "uid": "6309289", "client_id": "6309289", "token": 1, "pic":
	 * "http://dxs.demo.local/public/images/nocheck_thumb.jpg", "name": null,
	 * "uname": "13688449697", "email": null, "sex": null
	 * */
	private String uid;
	private String client_id;
	private String token;
	private String pic;
	private String name;
	private String uname;
	private String email;
	private String sex;

	public ModelUser() {

	}

	public ModelUser(JSONObject jsonObject) {
		try {
			if (jsonObject.has("uid")) {

				this.setUid(jsonObject.getString("uid"));
			}
			if (jsonObject.has("client_id")) {
				this.setClient_id(jsonObject.getString("client_id"));
			}
			if (jsonObject.has("token")) {
				this.setToken(jsonObject.getString("token"));
			}
			if (jsonObject.has("pic")) {
				this.setPic(jsonObject.getString("pic"));
			}
			if (jsonObject.has("name")) {
				this.setName(jsonObject.getString("name"));
			}
			if (jsonObject.has("uname")) {
				this.setUname(jsonObject.getString("uname"));
			}
			if (jsonObject.has("email")) {
				this.setEmail(jsonObject.getString("email"));
			}
			if (jsonObject.has("sex")) {
				this.setSex(jsonObject.getString("sex"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "ModelUser [uid=" + uid + ", client_id=" + client_id
				+ ", token=" + token + ", pic=" + pic + ", name=" + name
				+ ", uname=" + uname + ", email=" + email + ", sex=" + sex
				+ "]";
	}

}
