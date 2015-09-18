package com.zhiyisoft.associations.model;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午2:21:50 类描述：这个类是实现用户model uid:
 * 
 * 用户ID client_id:通用 token：用户token (重要) pic：用户头像 name：用户真实姓名 uname：用户名
 * email：电子邮箱 sex：性别 Male、female、空
 *
 */

public class ModelUser extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 手机号登陆所需要才参数 需要的参数 mobile pwd toUrl pushuserid pushchnlid devicetype
	 */
	private String mobile;
	private String pwd;
	/*
	 * 
	 * 
	 * "status": 1, "certflag": 2, "sex": null, "userid": 6343485, "city": null,
	 * "loginname": "29bJoPGu55", "username": null, "deptid": null, "gradeyear":
	 * null, "univname": null, "province": null, "dept": null, "classid": null,
	 * "userlogo": null, "nickname": "29bJoPGu55", "toUrl": null, "classname":
	 * null, "utvalue": "学生", "provinceid": null, "usertype": 1, "userauth":
	 * "2e9f3f9848c0066682394d67e8501a96", "cityid": null, "univid": null,
	 * "email": null, "userauth_top":
	 * "MTI0MzY0MQkyOWJKb1BHdTU1CTNmSkp3M1hWN2RZOWdPeGRLbmlGbE9zYTJtTWJrTEJYVHd0Ng=="
	 * , "mobile": "13688449697", "oauth_token":
	 * "83497a499f8c2e12bd2c06ead08a940d", "oauth_token_secret":
	 * "35014d41f18ef6679902d3af11015175"
	 */
	/********** 2015-9-14添加的字段 ****************/
	private String oauth_token;
	private String oauth_token_secret;
	private String msg; // 登陆失败的时候才能用到

	private String type;// 帐号类型
	private String type_uid; // 第三方用户标识
	private String access_token;// 第三方access token
	private String refresh_token; // 第三方refresh token（选填，根据第三方返回值）
	private String expire_in;// 过期时间（选填，根据第三方返回值）
	private String regCode;
	private String school_id;
	private String school_name;
	private String sex;

	private String uname;
	private String is_init;
	private String faceurl;
	private String uploadPhotoId;
	private File uploadFile;
	private String oldPwd; // 修改密码的时候 设置旧密码
	private String faceId;
	private String autograph;

	/********** 2015-9-14添加的字段结束 ****************/
	private String userid;
	private String email;

	// ----------------新添加的-----------------------------------------

	public String getschool_id() {
		return school_id;
	}

	public void setschool_id(String school_id) {
		this.school_id = school_id;
	}

	// private String mobile;
	public ModelUser() {

	}

	public ModelUser(JSONObject jsonObject) {
		try {

			if (jsonObject.has("email")) {
				this.setEmail(jsonObject.getString("email"));
			}
			if (jsonObject.has("mobile")) {
				this.setMobile(jsonObject.getString("mobile"));
			}

			if (jsonObject.has("school_id")) {
				this.setschool_id(jsonObject.getString("school_id"));
			}
			if (jsonObject.has("sex")) {
				this.setSex(jsonObject.getString("sex"));
			}

			if (jsonObject.has("oauth_token")) {
				this.setOauth_token(jsonObject.getString("oauth_token"));
			}
			if (jsonObject.has("oauth_token_secret")) {
				this.setOauth_token_secret(jsonObject
						.getString("oauth_token_secret"));
			}
			if (jsonObject.has("uid")) {
				this.setUserid(jsonObject.getString("uid"));
			}
			if (jsonObject.has("msg")) {
				this.setMsg(jsonObject.getString("msg"));
			}
			if (jsonObject.has("regCode")) {
				this.setRegCode(jsonObject.getString("regCode"));
			}
			if (jsonObject.has("school_id")) {
				this.setschool_id(jsonObject.getString("school_id"));
			}
			if (jsonObject.has("uname")) {
				this.setUname(jsonObject.getString("uname"));
			}
			if (jsonObject.has("is_init")) {
				this.setIs_init(jsonObject.getString("is_init"));
			}
			if (jsonObject.has("faceurl")) {
				this.setFaceurl(jsonObject.getString("faceurl"));
			}
			if (jsonObject.has("school_name")) {
				this.setSchool_name(jsonObject.getString("school_name"));
			}
			if (jsonObject.has("autograph")) {
				this.setAutograph(jsonObject.getString("autograph"));
			}
			if (jsonObject.has("email")) {
				this.setEmail(jsonObject.getString("classid"));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getOauth_token() {
		return oauth_token;
	}

	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}

	public String getOauth_token_secret() {
		return oauth_token_secret;
	}

	public void setOauth_token_secret(String oauth_token_secret) {
		this.oauth_token_secret = oauth_token_secret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType_uid() {
		return type_uid;
	}

	public void setType_uid(String type_uid) {
		this.type_uid = type_uid;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getExpire_in() {
		return expire_in;
	}

	public void setExpire_in(String expire_in) {
		this.expire_in = expire_in;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getIs_init() {
		return is_init;
	}

	public void setIs_init(String is_init) {
		this.is_init = is_init;
	}

	public String getFaceurl() {
		return faceurl;
	}

	public void setFaceurl(String faceurl) {
		this.faceurl = faceurl;
	}

	public String getUploadPhotoId() {
		return uploadPhotoId;
	}

	public void setUploadPhotoId(String uploadPhotoId) {
		this.uploadPhotoId = uploadPhotoId;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	@Override
	public String toString() {
		return "ModelUser [mobile=" + mobile + ", pwd=" + pwd
				+ ", oauth_token=" + oauth_token + ", oauth_token_secret="
				+ oauth_token_secret + ", msg=" + msg + ", type=" + type
				+ ", type_uid=" + type_uid + ", access_token=" + access_token
				+ ", refresh_token=" + refresh_token + ", expire_in="
				+ expire_in + ", regCode=" + regCode + ", school_id="
				+ school_id + ", school_name=" + school_name + ", sex=" + sex
				+ ", uname=" + uname + ", is_init=" + is_init + ", faceurl="
				+ faceurl + ", uploadPhotoId=" + uploadPhotoId
				+ ", uploadFile=" + uploadFile + ", oldPwd=" + oldPwd
				+ ", faceId=" + faceId + ", autograph=" + autograph
				+ ", userid=" + userid + ", email=" + email + "]";
	}

}
