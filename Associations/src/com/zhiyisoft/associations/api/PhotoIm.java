package com.zhiyisoft.associations.api;

import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午11:27:34 类描述：这个类是实现
 *
 * 接口：这个类是实现 上传头像，社团封面，活动封面，以及照片
 */

public interface PhotoIm {
	// 操作方式
	public static final String ATTACH = "Attach";
	// 操作参数
	public static final String FACEPIC = "facepic";

	/**
	 * 7.【上传头像】：Attach/facepic
	 * 
	 * 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Attach&act=facepic&oauth_token
	 * =124441feb0ae5dab7c064f5172948497&oauth_token_secret=
	 * 9eb43b300c0f45ef4250b2750dae34b9
	 * 
	 * @param user
	 * @return
	 */
	Model Attach(ModelUser user);
}
