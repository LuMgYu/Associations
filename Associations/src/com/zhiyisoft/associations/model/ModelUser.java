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
	private String toUrl;
	private String pushuserid;
	private String pushchnlid;
	private String devicetype;
	/*
	 * 用户名/邮箱登录 需要的参数 account pwd toUrl pushuserid pushchnlid devicetype
	 */
	private String account;
	/*
	 * 绑定用户登录 需要的参数 openid toUrl pushuserid pushchnlid devicetype
	 */
	private String openid;
	/*
	 * 绑定手机号用户 需要的参数 mobile pwd openid toUrl pushuserid
	 */
	/***
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
	 * */
	/***
	 * ""certflag":2, "sex":null, "userid":6343485, "city":null,
	 * "loginname":"29bJoPGu55", "username":null, "deptid":null,
	 * "gradeyear":null, "univname":null, "province":null, "dept":null,
	 * "classid":null, "userlogo":null, "nickname":"29bJoPGu55", "toUrl":null,
	 * "classname":null, "utvalue":"学生", "provinceid":null, "usertype":1,
	 * "userauth":"d61937499890ce90b0a61c22e8762e6e", "cityid":null,
	 * "univid":null, "email":null, "userauth_top":
	 * "MTI0MzY0MQkyOWJKb1BHdTU1CTNmSkp3M1hWN2RZOWdPeGRLbmlGbE9zYTJtTWJrTEJYVHd0Ng=="
	 * , "mobile":"13688449697"
	 * 
	 *
	 * */
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
	private String sex;

	private String uname;
	private String is_init;
	private String faceurl;
	private File uploadFile;

	/********** 2015-9-14添加的字段结束 ****************/
	private int certflag;
	private String nickname;
	// private String toUrl;
	private String userid;
	private String userlogo;
	private String usertype;
	private String provinceid;
	private String cityid;
	private String univid;
	private String loginname;
	private String user_uzone_auth;
	private String username;
	private String deptid;
	private String email;
	private String classid;
	// ----------------新添加的-----------------------------------------
	private String city;
	private String gradeyear;
	private String univname;
	private String province;
	private String dept;
	private String classname;
	private String utvalue;
	private String userauth;
	private String userauth_top;

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
			if (jsonObject.has("certflag")) {

				this.setCertflag(jsonObject.getInt("certflag"));
			}
			if (jsonObject.has("userlogo")) {
				this.setUserlogo(jsonObject.getString("userlogo"));
			}
			if (jsonObject.has("nickname")) {
				this.setNickname(jsonObject.getString("nickname"));
			}
			if (jsonObject.has("toUrl")) {
				this.setToUrl(jsonObject.getString("toUrl"));
			}
			if (jsonObject.has("loginname")) {
				this.setLoginname(jsonObject.getString("loginname"));
			}
			if (jsonObject.has("user_uzone_auth")) {
				this.setUser_uzone_auth(jsonObject.getString("user_uzone_auth"));
			}
			if (jsonObject.has("username")) {
				this.setUsername(jsonObject.getString("username"));
			}
			if (jsonObject.has("deptid")) {
				this.setDeptid(jsonObject.getString("deptid"));
			}
			if (jsonObject.has("email")) {
				this.setEmail(jsonObject.getString("email"));
			}
			if (jsonObject.has("classid")) {
				this.setClassid(jsonObject.getString("classid"));
			}
			if (jsonObject.has("mobile")) {
				this.setMobile(jsonObject.getString("mobile"));
			}
			if (jsonObject.has("city")) {
				this.setCity(jsonObject.getString("city"));
			}
			if (jsonObject.has("gradeyear")) {
				this.setGradeyear(jsonObject.getString("gradeyear"));
			}
			if (jsonObject.has("univname")) {
				this.setUnivname(jsonObject.getString("univname"));
			}
			if (jsonObject.has("province")) {
				this.setProvince(jsonObject.getString("province"));
			}
			if (jsonObject.has("dept")) {
				this.setDept(jsonObject.getString("dept"));
			}
			if (jsonObject.has("classname")) {
				this.setClassname(jsonObject.getString("classname"));
			}
			if (jsonObject.has("utvalue")) {
				this.setUtvalue(jsonObject.getString("utvalue"));
			}
			if (jsonObject.has("userauth")) {
				this.setUserauth(jsonObject.getString("userauth"));
			}
			if (jsonObject.has("userauth_top")) {
				this.setUserauth_top(jsonObject.getString("userauth_top"));
			}
			if (jsonObject.has("school_id")) {
				this.setschool_id(jsonObject.getString("school_id"));
			}
			if (jsonObject.has("sex")) {
				this.setSex(jsonObject.getString("sex"));
			}
			if (jsonObject.has("provinceid")) {
				this.setProvinceid(jsonObject.getString("provinceid"));
			}
			if (jsonObject.has("cityid")) {
				this.setCityid(jsonObject.getString("cityid"));
			}
			if (jsonObject.has("univid")) {
				this.setUnivid(jsonObject.getString("univid"));
			}
			if (jsonObject.has("userid")) {
				this.setUserid(jsonObject.getString("userid"));
			}
			if (jsonObject.has("usertype")) {
				this.setUsertype(jsonObject.getString("usertype"));
			}
			if (jsonObject.has("oauth_token")) {
				this.setOauth_token(jsonObject.getString("oauth_token"));
			}
			if (jsonObject.has("oauth_token_secret")) {
				this.setOauth_token_secret(jsonObject
						.getString("oauth_token_secret"));
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

	public String getToUrl() {
		return toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}

	public String getPushuserid() {
		return pushuserid;
	}

	public void setPushuserid(String pushuserid) {
		this.pushuserid = pushuserid;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getCertflag() {
		return certflag;
	}

	public void setCertflag(int certflag) {
		this.certflag = certflag;
	}

	public String getUserlogo() {
		return userlogo;
	}

	public void setUserlogo(String userlogo) {
		this.userlogo = userlogo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getUnivid() {
		return univid;
	}

	public void setUnivid(String univid) {
		this.univid = univid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUser_uzone_auth() {
		return user_uzone_auth;
	}

	public void setUser_uzone_auth(String user_uzone_auth) {
		this.user_uzone_auth = user_uzone_auth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClassid() {
		return classid;
	}

	public String getPushchnlid() {
		return pushchnlid;
	}

	public void setPushchnlid(String pushchnlid) {
		this.pushchnlid = pushchnlid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGradeyear() {
		return gradeyear;
	}

	public void setGradeyear(String gradeyear) {
		this.gradeyear = gradeyear;
	}

	public String getUnivname() {
		return univname;
	}

	public void setUnivname(String univname) {
		this.univname = univname;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getUtvalue() {
		return utvalue;
	}

	public void setUtvalue(String utvalue) {
		this.utvalue = utvalue;
	}

	public String getUserauth() {
		return userauth;
	}

	public void setUserauth(String userauth) {
		this.userauth = userauth;
	}

	public String getUserauth_top() {
		return userauth_top;
	}

	public void setUserauth_top(String userauth_top) {
		this.userauth_top = userauth_top;
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

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

}
