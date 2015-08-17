package com.zhiyisoft.associations.config;

/**
 * author：qiuchunjia time：上午10:53:55 类描述：这个类是实现
 *
 */

public class Config {
	// -----------------------------activity之间或者fragment之间需要传递的值需要用到-------------------------------------------------
	public static final String PHONE_NUMBER = "phone_number";
	public static final String PROVINCE = "province";
	// -----------------------------访问shareprefrence用到-------------------------------------------------
	public static final String USER_DATA = "user_data";
	public static final String MOBILE = "mobile";
	public static final String PWD = "pwd";
	public static final String USERAUTH = "userauth";
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
