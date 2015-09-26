package com.zhiyisoft.associations.api;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：下午5:28:27 类描述：这个类是实现
 *
 */

public interface HomeIm {
	public static final String HOME = "Home";
	public static final String INDEX = "index";

	/**
	 * 接口名称： Home/index
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Home&act=index
	 * 
	 * 输入参数： (string) oauth_token 选填 (string) oauth_token_secret 选填
	 * 
	 * @return
	 */
	Model index();
}
