package com.zhiyisoft.associations.api;

import com.zhiyisoft.associations.model.Model;
import com.zhiyisoft.associations.model.ModelUser;

/**
 * author：qiuchunjia time：下午2:27:27 类描述：这个接口是实现登陆和注册。
 *
 */

public interface LoginIm {
	public static final String LOGIN = "login";
	public static final String REG = "reg";
	public static final String INDEX = "index";

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
	Model Login(ModelUser user);
}
