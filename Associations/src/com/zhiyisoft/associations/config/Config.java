package com.zhiyisoft.associations.config;

/**
 * author：qiuchunjia time：上午10:53:55 类描述：这个类是实现
 *
 */

public class Config {
	// -----------------------------activity之间或者fragment之间需要传递的值需要用到-------------------------------------------------
	public static final String PHONE_NUMBER = "phone_number";
	public static final String PROVINCE = "province";
	public static final String HOTCATEGORY = "hotcategory"; // 热门分类的
	public static final String MAIN_ACTIVITY = "main_activity"; // 跳转到主activity
	public static final String LOCALVIDEO = "localvideo";
	public static final String LOCALMUSIC = "LocalMusic";
	public static final String PHOTOURL = "photourl";
	public static final String CHECKED_ASSOCITION = "checked_Assocition";
	public static final String LOCATION = "location";

	// -----------------------------访问shareprefrence用到-------------------------------------------------
	public static final String USER_DATA = "user_data";
	/**
	 * "oauth_token":"40fb886c1419ebcd9a72860b53d7c6db",
	 * "oauth_token_secret":"f5b24cee49bc8a8ce3847d5b2c1e9856", "uid":"6309299",
	 * "school_id":"1465", "school_name":"复旦大学", "uname":"呵呵哒", "sex":"0",
	 * "autograph":"", "mobile":"13688449697", "email":null, "is_init":"1",
	 * "faceurl":
	 * "http://daxs.zhiyicx.com/attachment/uploads/2015/0918/14/55fbb364bea26.jpg"
	 * 
	 * */
	public static final String MOBILE = "mobile";
	public static final String PWD = "pwd";
	public static final String USERID = "userid";
	public static final String OAUTH_TOKEN = "oauth_token";
	public static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";
	public static final String UID = "uid";
	public static final String SCHOOL_ID = "school_id";
	public static final String UNAME = "uname";
	public static final String IS_INIT = "is_init";
	public static final String SEX = "sex";
	public static final String FACEURL = "faceurl";
	public static final String SCHOOL_NAME = "school_name";
	public static final String AUTOGRAPH = "autograph";
	public static final String EMAIL = "email";

	public static final String OPEN_MESSAGE = "open_message";
	public static final String CURRENT_PROVINCE = "current_province";
	public static final String CURRENT_SCHOOL = SCHOOL_NAME;
	public static final String ISNOT_GUIDE = "ISNOT_GUIDE";
	public static final String GENDER = "gender";
	public static final String GET_ACTIVITY_DATA = "get_activity_data";
	public static final String SEND_ACTIVITY_DATA = "send_activity_data";
	// -----------------------------访问网络的的接口的尾部地址-------------------------------------------------
	/** 发送手机验证码 */
	public static final String appSendSMSCode = "appSendSMSCode.action";
	/** 用户注册接口 */
	public static final String appUserReg = "appUserReg.action";
	/** 验证手机号是否注册 */
	public static final String validMobile = "validMobile.action";
	/** 手机号登录 */
	public static final String appUserMobileLogin = "appUserMobileLogin.action";
	/** 用户名/邮箱登录 */
	public static final String appUserAccountLogin = "appUserAccountLogin.action";
	/** 绑定用户登录 */
	public static final String boundedUserLogin = "boundedUserLogin.action";
	/** 绑定手机号用户 */
	public static final String boundMobileUser = "boundMobileUser.action";
	/** 验证UserAuth */
	public static final String appValidateUserAuth = "appValidateUserAuth.action";
	/** 验证密码 */
	public static final String appValidateUserPwd = "appValidateUserPwd.action";
	/** 修改所在地区 */
	public static final String appUpdateUserArea = "appUpdateUserArea.action";
	/** 修改所在学校 */
	public static final String appUpdateUserSchool = "appUpdateUserSchool.action";
	/** 修改所在院系 */
	public static final String appUpdateUserDept = "appUpdateUserDept.action";
	/** 修改所在班级 */
	public static final String appUpdateUserClass = "appUpdateUserClass.action";
	/** 修改入学年份 */
	public static final String appUpdateGradeYear = "appUpdateGradeYear.action";
	/** 元数据接口 */
	public static final String appMetaData = "appMetaData.action";
	/** 修改所在班级 */
	// -----------------------------结束-------------------------------------------------

}
