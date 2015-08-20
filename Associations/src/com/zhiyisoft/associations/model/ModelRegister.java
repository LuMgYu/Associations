package com.zhiyisoft.associations.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午2:21:50
 * 
 * 类描述：用户注册
 *
 */

public class ModelRegister extends Model {
	private String loginname;
	private boolean registed;
	private String mobile;
	private String pwd;
	private String smscode;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSmscode() {
		return smscode;
	}

	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}

	public ModelRegister() {

	}

	public ModelRegister(JSONObject jsonObject) {
		try {
			if (jsonObject.has("loginname")) {

				this.setLoginname(jsonObject.getString("loginname"));
			}
			if (jsonObject.has("registed")) {
				this.setRegisted(jsonObject.getBoolean("registed"));
			}
			if (jsonObject.has("mobile")) {
				this.setMobile(jsonObject.getString("mobile"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public boolean isRegisted() {
		return registed;
	}

	public void setRegisted(boolean registed) {
		this.registed = registed;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
