package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:54:23 类描述：这个类是实现通知列表
 *
 */

public interface NotifyIm {
	public static final String NOTIFY = "Notify";

	public static final String NOTIFYLIST = "notifyList";
	public static final String SETREAD = "setRead";

	public static final String ID = "id";

	/**
	 * 46.【消息列表】：Notify/notifyList
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Notify&act=notifyList
	 * 
	 * 输入参数： (string) oauth_token 必填 (string) oauth_token_secret 必填
	 * 
	 * @return
	 */
	List<Model> notifyList();

	/**
	 * 47.【消息设置已读】：Notify/setRead
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Notify&act=setRead
	 * 
	 * 
	 * 输入参数： (string) oauth_token 必填 (string) oauth_token_secret 必填 (int) id
	 * 消息id
	 * 
	 * @return
	 */
	boolean setRead();

}
