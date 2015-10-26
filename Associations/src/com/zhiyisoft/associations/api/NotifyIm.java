package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.ModelMsg;
import com.zhiyisoft.associations.model.ModelNotify;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:54:23 类描述：这个类是实现通知列表
 *
 */

public interface NotifyIm {
	public static final String NOTIFY = "Notify";// 通知
	public static final String WALL = "Wall"; // 私信

	public static final String NOTIFYLIST = "notifyList"; // 通知里面的
	public static final String SETREAD = "setRead"; //
	public static final String PUBLISH = "publish"; // 51.【发送私信】：Wall/publish
	public static final String WALLLIST = "wallList"; // 52.Wall/wallList
	public static final String NOTIFYLISTMSG = "notifyList"; // 53.【与某人的私信内容列表】：Wall/notifyList
	public static final String DELNOTIFY = "delNotify"; // 54.【删除私信】：Wall/delNotify

	public static final String ID = "id";
	public static final String TUID = "tuid";
	public static final String UID = "uid";
	public static final String CONTENT = "content";
	public static final String WITHMASKID = "withMaskId";
	public static final String LAST_ID = "last_id";

	/**
	 * 46.【消息列表】：Notify/notifyList
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Notify&act=notifyList
	 * 
	 * 输入参数： (string) oauth_token 必填 (string) oauth_token_secret 必填
	 * 
	 * @return
	 */
	List<Model> notifyList(ModelNotify notify);

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
	boolean setRead(ModelNotify notify);

	/**
	 * 51 发送私信
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Wall&act=publish演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Wall&act=publish
	 * 
	 * 输入参数： (string) oauth_token 必填 (string) oauth_token_secret 必填 (int) tuid
	 * 接收用户的id (string) content 内容
	 * 
	 * @return
	 */
	Model sendMsg(ModelMsg msg);

	/**
	 * 52.【私信列表】：Wall/wallList
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Wall&act=wallList
	 * 
	 * 输入参数： (string) oauth_token 必填 (string) oauth_token_secret 必填
	 * 
	 * @return
	 */
	List<Model> wallList(ModelMsg msg);

	/**
	 * 53.【与某人的私信内容列表】：Wall/notifyList
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Wall&act=notifyList
	 * 
	 * (string) oauth_token 必填 (string) oauth_token_secret 必填 (int) withMaskId
	 * 必填 接口52返回内容里面的字段值
	 * 
	 * @param msg
	 * @return
	 */
	List<Model> notifyList(ModelMsg msg);

	/**
	 * 接口名称： Wall/delNotify 接口描述： 删除私信
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Wall&act=delNotify
	 * 
	 * (string) oauth_token 必填 (string) oauth_token_secret 必填 (int) withMaskId
	 * 必填 接口52返回内容里面的字段值
	 * 
	 * @param msg
	 * @return
	 */
	Model delNotify(ModelMsg msg);

}
