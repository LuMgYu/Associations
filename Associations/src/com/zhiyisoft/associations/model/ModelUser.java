package com.zhiyisoft.associations.model;

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

	private int certflag;
	private int sex;
	private String nickname;
	// private String toUrl;
	private int userid;
	private String userlogo;
	private int usertype;
	private int provinceid;
	private int cityid;
	private int univid;
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

	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
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
			// private String city;
			// private String gradeyear;
			// private String univname;
			// private String province;
			// private String dept;
			// private String classname;
			// private String utvalue;
			// private String userauth;
			// private String userauth_top;
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
			if (jsonObject.has("schoolId")) {
				this.setSchoolId(jsonObject.getString("schoolId"));
			}
			if (jsonObject.has("sex")) {
				this.setSex(jsonObject.getInt("sex"));
			}
			if (jsonObject.has("provinceid")) {
				this.setProvinceid(jsonObject.getInt("provinceid"));
			}
			if (jsonObject.has("cityid")) {
				this.setCityid(jsonObject.getInt("cityid"));
			}
			if (jsonObject.has("univid")) {
				this.setUnivid(jsonObject.getInt("univid"));
			}
			if (jsonObject.has("userid")) {
				this.setUserid(jsonObject.getInt("userid"));
			}
			if (jsonObject.has("usertype")) {
				this.setUsertype(jsonObject.getInt("usertype"));
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public int getUnivid() {
		return univid;
	}

	public void setUnivid(int univid) {
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

	@Override
	public String toString() {
		return "ModelUser [mobile=" + mobile + ", pwd=" + pwd + ", toUrl="
				+ toUrl + ", pushuserid=" + pushuserid + ", pushchnlid="
				+ pushchnlid + ", devicetype=" + devicetype + ", account="
				+ account + ", openid=" + openid + ", certflag=" + certflag
				+ ", sex=" + sex + ", nickname=" + nickname + ", userid="
				+ userid + ", userlogo=" + userlogo + ", usertype=" + usertype
				+ ", provinceid=" + provinceid + ", cityid=" + cityid
				+ ", univid=" + univid + ", loginname=" + loginname
				+ ", user_uzone_auth=" + user_uzone_auth + ", username="
				+ username + ", deptid=" + deptid + ", email=" + email
				+ ", classid=" + classid + ", city=" + city + ", gradeyear="
				+ gradeyear + ", univname=" + univname + ", province="
				+ province + ", dept=" + dept + ", classname=" + classname
				+ ", utvalue=" + utvalue + ", userauth=" + userauth
				+ ", userauth_top=" + userauth_top + ", schoolId=" + schoolId
				+ "]";
	}

}
