package com.zhiyisoft.associations.api;

import com.zhiyisoft.associations.model.Model;

/**
 * author：qiuchunjia time：下午3:42:08 接口描述：基本的设置的接口
 *
 */

public interface BaseSettingIm {
	public static final String USER = "user";
	public static final String SETMASK = "setmask";
	public static final String GETUSERACTIVITEMASKINFO = "getuseractivemaskinfo";
	public static final String SETFACEIMG = "setfaceimg";

	/**
	 * 更新昵称
	 * 
	 * @param item
	 * @return
	 */
	Object updateMask(Model item);

	/**
	 * 获取当前激活昵称信息
	 * 
	 * @param item
	 * @return
	 */
	Object getUserActiveMaskInfo(Model item);

	/**
	 * 更新头像
	 * 
	 * @param item
	 * @return
	 */
	Model setFaceImg(Model item);
}
