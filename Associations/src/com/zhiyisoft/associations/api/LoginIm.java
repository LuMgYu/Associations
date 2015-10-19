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
	public static final String USER = "User";
	public static final String LOGIN1 = "login";
	public static final String MOBILE = "mobile";
	public static final String PWD = "password";
	public static final String UID = "uid";
	public static final String OLDPASSWORD = "oldpassword";
	// 四种操作方式
	public static final String AUTHORIZE = "authorize";
	public static final String LOGOUT = "logout";
	public static final String GET_OTHER_LOGIN_INFO = "get_other_login_info";
	public static final String BIND_NEW_USER = "bind_new_user";
	public static final String SEND_REGISTER_CODE = "send_register_code";
	public static final String REGISTER = "register";
	public static final String SENDCODEBYPHONE = "sendCodeByPhone";
	public static final String SAVEUSERPASSWORDBYPHONE = "saveUserPasswordByPhone";
	public static final String SAVEUSERPASSWORDBYPASSWORD = "saveUserPasswordByPassword";

	public static final String CODE = "code"; // 修改密码的时候用到
	public static final String UPDATEPROFILE = "updateProfile";
	public static final String FACEID = "faceid";
	public static final String UNAME = "uname";
	public static final String SEX = "sex";
	public static final String SCHOOL_ID = "school_id";
	public static final String AUTOGRAPH = "autograph";
	public static final String EMAIL = "email";

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
	boolean bindNewUser(ModelUser user);

	/**
	 * 【注册发送验证码】
	 * 
	 * 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Login&act=send_register_code&
	 * phone=15828662060
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Login&act=register&mobile=
	 * 15828662060&regCode=9040&password=512121855
	 * 
	 * @param user
	 * @return
	 */
	Model sendRegisterCode(ModelUser user);

	/**
	 * 6.【注册】：Login/register
	 * 
	 * @param user
	 * @return
	 */
	Model register(ModelUser user);

	/**
	 * 9.【找回密码发送验证码】：User/sendCodeByPhone
	 * 
	 * 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=User&act=sendCodeByPhone&mobile
	 * =15828662060
	 * 
	 * @param user
	 * @return
	 */
	boolean sendCodeByPhone(ModelUser user);

	/**
	 * 10.【修改密码】：User/saveUserPasswordByPhone
	 * 
	 * 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=User&act=saveUserPasswordByPhone
	 * &mobile=15828662060&password=512121855&code=9696
	 * 
	 * @param user
	 * @return
	 */
	boolean saveUserPasswordByPhone(ModelUser user);

	/**
	 * 8.【修改资料】：User/updateProfile
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=User&act=updateProfile
	 * &oauth_token=124441feb0ae5dab7c064f5172948497&oauth_token_secret=
	 * 9eb43b300c0f45ef4250b2750dae34b9&faceid=231312&uname=hhhcode&sex=1&school
	 * _ i d = 1 1 1 1
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) faceid
	 * 接口7中返回的id 选填 (string) uname 用户名 选填 (int) school_id 学校id选填 (int) sex 性别 选填
	 * (string) autograph 个性签名 选填 (string) email 邮箱 选填
	 * 
	 * @param user
	 * @return
	 */
	Model updateProfile(ModelUser user);

	/**
	 * 12.【修改密码】：User/saveUserPasswordByPassword
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=User&act=
	 * saveUserPasswordByPassword
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (string)
	 * password 新密码 (string) oldpassword 老密码
	 * 
	 * @param user
	 * @return
	 */
	boolean saveUserPasswordByPassword(ModelUser user);
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
