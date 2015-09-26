package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.ModelEvent;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午11:05:00 类描述：这个类是实现
 *
 */

public interface EventIm {
	public static final String EVENT = "Event"; // 创建活动
	// 需要执行的操作
	public static final String JOINEDEVENT = "joinedEvent"; // 33.【我参加的活动】：Event/joinedEvent
	public static final String CREATEDEVENT = "createdEvent";// 34.【我创建的活动】：Event/decremented
	public static final String FOLLOWEDEVENT = "followedEvent"; // 35.【我关注的活动】：Event/followedEvent
	public static final String CREATEEVENT = "createEvent";// 37.【创建活动】：Event/createEvent
	public static final String EVENTVIEW = "eventView";// 38.【活动详情】：Event/eventView
	public static final String JOIN = "join";// 39.【活动报名】：Event/join
	public static final String SUB = "sub";// 40.【活动关注/取消关注】：Event/sub
	public static final String MEMBERLIST = "memberList";// 41.【活动参与者列表】：Event/memberList

	// 需要执行的操作的参数
	public static final String ONLINE = "online"; // 0线上活动，1线下活动 必填
	public static final String LOGO = "logo";// 接口37中返回的id必填
	public static final String GID = "gid";// 所属社团 必填
	public static final String TITLE = "title";// 活动标题 必填
	public static final String ADDRESS = "address";// 活动地点 选填
	public static final String TYPE = "type";// 活动类别 必填
	public static final String HOST = "host";// 主办方 必填
	public static final String EXPLAIN = "explain";// 活动内容 必填
	public static final String STIME = "sTime";// 活动开始时间 必填
	public static final String ETIME = "eTime";// 活动结束时间 必填
	public static final String JOINAUDIT = "joinAudit";// 报名是否审核，0否，1是 必填
	public static final String JOINSTIME = "joinStime";// 报名开始时间 必填
	public static final String JOINETIME = "joinEtime";// 报名结束时间 必填
	public static final String WORKAUDIT = "workAudit";// 作品是否审核，0否，1是 必填
	public static final String WORKSPURVIEW = "worksPurview";// 作品上传权限 1发起人，2所有人
	public static final String WORKSTIME = "workStime";// 品提交开始时间 必填
	public static final String WORKETIME = "workEtime";// 作品提交结束时间 必填
	public static final String EXPLAINTYPE = "explainType";// 作品提交结束时间 必填
	public static final String RANGEDES = "rangeDes";// 指定学校id 选填

	/**
	 * 33.【我参加的活动】：Event/joinedEvent
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Event&act= joinedEvent
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填
	 * 
	 * @return
	 */
	List<Model> joinedEvent();

	/**
	 * 34.【我创建的活动】：Event/createdEvent
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Event&act=createdEvent
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填
	 * 
	 * @return
	 */
	List<Model> createdEvent();

	/**
	 * 35.【我关注的活动】：Event/followedEvent
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Event&act=followedEvent
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填
	 * 
	 * @return
	 */
	List<Model> followedEvent();

	/**
	 * 37.【创建活动】：Event/createEvent
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Event&act=createEvent
	 * 
	 * (string) oauth_token必填 (string) oauth_token_secret 必填 (int) online
	 * 0线上活动，1线下活动 必填 (int) logo 接口37中返回的id必填 (int) gid 所属社团 必填 (string) title
	 * 活动标题 必填 (string) address活动地点 选填 (int) type 活动类别 必填 (string) host 主办方 必填
	 * (string) explain 活动内容 必填 (int) sTime 活动开始时间 必填 (int) eTime 活动结束时间 必填
	 * (int) joinAudit 报名是否审核，0否，1是 必填 (int) joinStime 报名开始时间 必填 (int) joinEtime
	 * 报名结束时间 必填 (int) workAudit 作品是否审核，0否，1是 必填 (int) worksPurview 作品上传权限
	 * 1发起人，2所有人 必填 (int) workStime 作品提交开始时间 必填 (int) workEtime 作品提交结束时间 必填
	 * (int) rangeDes 指定学校id 选填
	 * 
	 * @return
	 */
	boolean createEvent(ModelEvent event);

	/**
	 * 38.【活动详情】：Event/eventView
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Event&act=eventView
	 * 
	 * 输入参数： (int) id 活动id
	 * 
	 * @return
	 */
	Model eventView();

	/**
	 * 39.【活动报名】：Event/join
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Event&act=join
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) id 活动id
	 */
	boolean join();

	/**
	 * 活动关注/取消关注】：Event/sub
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Event&act=sub
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) id 活动id
	 * (int) sub 0关注，1取消关注
	 * 
	 * @return
	 */
	boolean sub();

	/**
	 * 41.【活动参与者列表】：Event/memberList
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Event&act=memberList
	 * 
	 * 
	 * 输入参数： (int) id 活动id
	 * 
	 * @return
	 */
	List<Model> memberList();

}
