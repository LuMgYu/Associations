package com.zhiyisoft.associations.impl;

import com.zhiyisoft.associations.model.ModelUser;

/**
 * author：qiuchunjia time：下午2:27:27 类描述：这个接口是实现登陆和注册。
 *
 */

public interface LoginImpl {
	public static final String MOD = "login";
	public static final String ACT = "reg";

	/**
	 * @param User
	 *            需要注冊的user
	 * @return
	 */
	Object registerMem(ModelUser user);

	/**
	 * @param user
	 *            需要登陸的用戶
	 * @return
	 */
	Object Login(ModelUser user);
}
