package com.zhiyisoft.associations.api;

import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午2:27:27 类描述：这个接口是实现登陆和注册。
 *
 */

public interface LoginIm {
	public static final String MOBILE = "mobile";
	public static final String PWD = "pwd";
	public static final String TOURL = "toUrl";
	public static final String PUSHUSERID = "pushuserid";
	public static final String PUSHCHNLID = "pushchnlid";
	public static final String DEVICETYPE = "devicetype";
	public static final String ACCOUNT = "account";
	public static final String OPENID = "openid";
	public static final String USERAUTH = "userauth";

	/**
	 * 手机号登录 需要传递的参数 mobile pwd toUrl pushuserid pushchnlid devicetype
	 * 
	 * @param model
	 * @return
	 */
	Model appUserMobileLogin(ModelUser model);

	/**
	 * 用户名/邮箱登录 需要传递的参数 account pwd toUrl pushuserid pushchnlid devicetype
	 * 
	 * @param model
	 * @return
	 */
	Model appUserAccountLogin(ModelUser model);

	/**
	 * 绑定用户登录 需要传递的参数openid toUrl pushuserid pushchnlid devicetype
	 * 
	 * @param model
	 * @return
	 */
	Model boundedUserLogin(ModelUser model);

	/**
	 * 绑定手机号用户 需要传递的参数mobile pwd openid toUrl pushuserid
	 * 
	 * @param model
	 * @return
	 */
	Model boundMobileUser(ModelUser model);

	/**
	 * 验证UserAuth 需要传递的参数userauth toUrl
	 * 
	 * 
	 * 返回的字段 userid toUrl userauth
	 * 
	 * eg："userauth":"d61937499890ce90b0a61c22e8762e6e"
	 * 
	 * @param model
	 * @return
	 */
	Model appValidateUserAuth(ModelUser model);

	/**
	 * 验证密码 需要传递的参数mobile pwd userauth 返回的字段 无
	 * 
	 * @param model
	 * @return
	 */
	Model appValidateUserPwd(ModelUser model);
}
