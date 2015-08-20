package com.zhiyisoft.associations.api;

import com.zhiyisoft.associations.model.ModelRegister;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午2:27:27 类描述：这个接口是实现登陆和注册。
 *
 */

public interface RegisterIm {
	public static final String MOBILE = "mobile";
	public static final String PWD = "pwd";
	public static final String SMSCODE = "smscode";

	/**
	 * 发送手机验证码
	 * 
	 * @param model
	 */
	public boolean appSendSMSCode(ModelRegister model);

	/**
	 * 用户注册接口
	 * 
	 * @param model
	 */
	public Model appUserReg(ModelRegister model);

	/**
	 * 验证手机号是否注册
	 * 
	 * @param model
	 */
	public Model validMobile(ModelRegister model);

}
