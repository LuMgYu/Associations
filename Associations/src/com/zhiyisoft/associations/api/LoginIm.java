package com.zhiyisoft.associations.api;

import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午2:27:27 类描述：这个接口是实现登陆和注册。
 *
 */

public interface LoginIm {
	// public static final String MOBILE = "mobile";
	// public static final String PWD = "pwd";
	// public static final String TOURL = "toUrl";
	// public static final String PUSHUSERID = "pushuserid";
	// public static final String PUSHCHNLID = "pushchnlid";
	// public static final String DEVICETYPE = "devicetype";
	// public static final String ACCOUNT = "account";
	// public static final String OPENID = "openid";
	// public static final String USERAUTH = "userauth";
	public static final String LOGIN = "Login";
	public static final String MOBILE = "mobile";
	public static final String PWD = "pwd";
	public static final String UID = "uid";
	// 四种操作方式
	public static final String AUTHORIZE = "authorize";
	public static final String LOGOUT = "logout";
	public static final String GET_OTHER_LOGIN_INFO = "get_other_login_info";
	public static final String BIND_NEW_USER = "bind_new_user";
	public static final String SEND_REGISTER_CODE = "send_register_code";
	public static final String CHECK_REGISTER_CODE = "check_register_code";

	//
	public static final String TYPE = "type";
	public static final String TYPE_UID = "type_uid";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String REFRESH_TOKEN = "refresh_token";
	public static final String EXPIRE_IN = "expire_in";
	public static final String REGCODE = "regCode";

	/**
	 * 1.【登录】Login/authorize
	 * 
	 * (string) mobile，手机号（必填） (string) pwd，密码（必填）
	 * 
	 * @param model
	 * @return
	 * 
	 */
	Model authorize(ModelUser user);

	/**
	 * 2.【注销】：Login/logout
	 * 
	 * string) uid，用户id（必填）
	 * 
	 * @param user
	 * @return
	 */
	Model logout(ModelUser user);

	/**
	 * 
	 * 【记录或获取第三方登录接口获取到的信息】：
	 * 
	 * 
	 * 输入参数： (varchar) type 帐号类型 (varchar) type_uid 第三方用户标识 (varchar)
	 * access_token 第三方access token (varchar) refresh_token 第三方refresh
	 * token（选填，根据第三方返回值） (varchar) expire_in 过期时间（选填，根据第三方返回值）
	 * 
	 * 
	 * @param user
	 * @return
	 */
	Model getOtherLoginInfo(ModelUser user);

	/**
	 * 4.【绑定第三方帐号】：Login/bind_new_user
	 * 
	 * 
	 * 输入参数： (string) uid 新注册账户的uid (varchar) type 帐号类型 (varchar) type_uid
	 * 第三方用户标识 (varchar) access_token 第三方access token (varchar) refresh_token
	 * 第三方refresh token（选填，根据第三方返回值）
	 * 
	 * @param user
	 * @return
	 */
	Model bindNewUser(ModelUser user);

	/**
	 * 【注册发送验证码】
	 * 
	 * 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Login&act=send_register_code&
	 * phone=15828662060
	 * 
	 * @param user
	 * @return
	 */
	boolean sendRegisterCode(ModelUser user);

	/**
	 * 【验证注册验证码】
	 * 
	 * 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Login&act=check_register_code
	 * &phone=15828662060&regCode=123456
	 * 
	 * @param user
	 * @return
	 */
	boolean checkRegisterCode(ModelUser user);
	// /**
	// * 手机号登录 需要传递的参数 mobile pwd toUrl pushuserid pushchnlid devicetype
	// *
	// * @param model
	// * @return
	// */
	// Model appUserMobileLogin(ModelUser model);
	//
	// /**
	// * 用户名/邮箱登录 需要传递的参数 account pwd toUrl pushuserid pushchnlid devicetype
	// *
	// * @param model
	// * @return
	// */
	// Model appUserAccountLogin(ModelUser model);
	//
	// /**
	// * 绑定用户登录 需要传递的参数openid toUrl pushuserid pushchnlid devicetype
	// *
	// * @param model
	// * @return
	// */
	// Model boundedUserLogin(ModelUser model);
	//
	// /**
	// * 绑定手机号用户 需要传递的参数mobile pwd openid toUrl pushuserid
	// *
	// * @param model
	// * @return
	// */
	// Model boundMobileUser(ModelUser model);
	//
	// /**
	// * 验证UserAuth 需要传递的参数userauth toUrl
	// *
	// *
	// * 返回的字段 userid toUrl userauth
	// *
	// * eg："userauth":"d61937499890ce90b0a61c22e8762e6e"
	// *
	// * @param model
	// * @return
	// */
	// Model appValidateUserAuth(ModelUser model);
	//
	// /**
	// * 验证密码 需要传递的参数mobile pwd userauth 返回的字段 无
	// *
	// * @param model
	// * @return
	// */
	// Model appValidateUserPwd(ModelUser model);
}
