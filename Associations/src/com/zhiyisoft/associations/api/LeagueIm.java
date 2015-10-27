package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.ModelLeagueAlbum;
import com.zhiyisoft.associations.model.ModelLeagueTopic;
import com.zhiyisoft.associations.model.ModelMember;
import com.zhiyisoft.associations.model.ModelTiding;
import com.zhiyisoft.associations.model.ModelUser;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午11:34:57 接口描述：实现创建社团
 *
 */

public interface LeagueIm {
	public static final String GROUP = "Group";

	public static final String INDEX = "index";
	public static final String CREATEGROUP = "createGroup";
	public static final String JOIN = "join"; // 15.【加入社团】：Group/join
	public static final String LEAVE = "leave";// 16.【退出社团】：Group/leave
	public static final String VIEW = "view";// 17.【社团详情(未加入)】：Group/view
	public static final String VIEWIN = "viewIn";// 18.【社团详情(已加入)】：Group/viewIn
	public static final String MEMBERLIST = "memberList";// 19.【社团成员列表】：Group/memberList
	public static final String ALBUMLIST = "albumList";// 20.【社团相册列表】：Group/albumList
	public static final String CREATEALBUM = "createAlbum";// 21.【创建相册】：Group/createAlbum
	public static final String PHOTOLIST = "photoList";// 23.【社团相册图片列表】：Group/photoList
	public static final String TOPICLIST = "topicList";// 25.【新鲜事列表】：Group/topicList
	public static final String TOPICVIEW = "topicView";// 26.【话题详情】：Group/topicView
	public static final String REPLYTOPIC = "replyTopic";// 27.【话题回帖】：Group/replyTopic
	public static final String REPLYPOST = "replyPost";// 28.【回复帖子】：Group/replyPost
	public static final String GETTOPICPOSTS = "getTopicPosts";// 29.【获取某个话题下的帖子】：Group/getTopicPosts
	public static final String JOINEDGROUP = "joinedGroup";// 30.【已加入社团】：Group/joinedGroup
	public static final String GETGROUPBASEINFO = "getGroupBaseInfo";// 31.Group/getGroupBaseInfo
	public static final String GROUPWORKS = "groupWorks";// 48.【社团作品】：Group/groupWorks
	public static final String GROUPPHOTOVIEW = "groupPhotoView";// 49.【社团图片详情】：Group/groupPhotoView
	public static final String MEMBEROUT = "memberOut";// 57.【社团踢出成员】：Group/memberOut

	public static final String GROUPNEWSLIST = "groupNewsList";// 55.【社团新闻列表】：Group/groupNewsList
	public static final String GROUPNEWSDETAIL = "groupNewsDetail";// 56.【社团新闻详情】：Group/groupNewsDetail

	public static final String NAME = "name";
	public static final String CATEGORYID = "categoryId";
	public static final String LOGO = "logo";
	public static final String DESCRIPTION = "description";
	public static final String SCHOOLID = "schoolId";
	public static final String PRIVATE = "private";
	public static final String OPENERNAME = "openerName";
	public static final String CONTACT = "contact";

	public static final String GID = "gid";
	public static final String ALBUMNAME = "name";
	public static final String ALBUMINFO = "info";
	public static final String ALBUMHIDE = "hide";
	public static final String ALBUMID = "albumId";
	public static final String TID = "tid"; //
	public static final String PID = "pid"; //
	public static final String CONTENT = "content";// 【回复帖子】内容
	public static final String TYPE = "type"; // 社团作品的类型
	public static final String ID = "id"; // 社团里面的照片id

	public static final String GIDID = "id"; // 社团id
	public static final String TINDINGID = "id"; // 新闻id

	/**
	 * 13.【创建社团】：Group/createGroup 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act=createGroup
	 * 
	 * 
	 * (string) oauth_token必填 (string) oauth_token_secret 必填 (string) name 社团名称
	 * 必填 (int) categoryId 社团分类 必填 (int) logo 社团logo（接口12上传成功返回） 必填 (string)
	 * description 社团描述 必填 (int) schoolId 社团所属学校 必填 (int) private 是否公开（0公开，1不公开）
	 * 必填。 (string) openerName 社团联系人 必填 (string) contact 社团联系方式 必填
	 * 
	 * @param modelItem
	 *            需要传递的社团model
	 * @return
	 */
	boolean createGroup(ModelLeague league);

	/**
	 * 14.【社团列表】：Group/index 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act=index
	 * 
	 * (string) oauth_token选填 (string) oauth_token_secret 选填 (int) schoolId 学校id
	 * 选填 (int) categoryId 社团分类 选填 (string) name（搜索内容）选填
	 * 
	 * 其他说明： Isin字段代表是否加入该社团
	 * 
	 * @param league
	 * @return
	 */
	List<Model> groupIndex(ModelLeague league);

	/**
	 * 15.【加入社团】：Group/join 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act=join
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) gid
	 * 社团id 必填
	 * 
	 * @param league
	 * @return
	 */
	boolean join(ModelLeague league);

	/**
	 * 16.【退出社团】：Group/leave 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act=leave
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) gid
	 * 社团id 必填
	 * 
	 * @param league
	 * @return
	 */
	boolean leave(ModelLeague league);

	/**
	 * 17.【社团详情(未加入)】：Group/view 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act=view
	 * 
	 * 输入参数： (string) oauth_token 选填 (string) oauth_token_secret 选填 (int) gid
	 * 社团id 必填
	 * 
	 * @param league
	 * @return
	 */
	Model view(ModelLeague league);

	/**
	 * 18.【社团详情(已加入)】：Group/viewIn 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act=viewIn
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) gid
	 * 社团id 必填
	 * 
	 * 其他说明： userlevel 表示当前用户在该社团中的身份，3为创始人，2为管理员，1为普通成员
	 * 
	 * @param league
	 * @return
	 */
	Model viewIn(ModelLeague league);

	/**
	 * 19.【社团成员列表】：Group/memberList 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act=memberList
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) gid
	 * 社团id 必填
	 * 
	 * @param league
	 * @return
	 */
	List<Model> memberList(ModelLeague league);

	/**
	 * 20.【社团相册列表】：Group/albumList 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act= albumList
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) gid
	 * 社团id 必填
	 * 
	 * @param league
	 * @return
	 */
	List<Model> albumList(ModelLeague league);

	/**
	 * 21.【创建相册】：Group/createAlbum 演示地址：
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act=createAlbum
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) gid
	 * 社团id 必填 (string) name 相册名称 必填 (string) info 相册描述 选填
	 * 
	 * @param league
	 * @return
	 */
	boolean createAlbum(ModelLeague league);

	/**
	 * 接口名称： Group/photoList
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Attach&act=photoList
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) albumId
	 * 相册id 必填
	 * 
	 * @param league
	 * @return
	 */
	List<Model> photoList(ModelLeagueAlbum league);

	/**
	 * 25.【新鲜事列表】：Group/topicList 演示地址：
	 * 
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act=topicList 输入参数： (string)
	 * oauth_token必填 (string) oauth_token_secret 必填 (int) gid社团id 必填
	 * 
	 * @param league
	 * @return
	 */
	List<Model> topicList(ModelLeague league);

	/**
	 * 26.【话题详情】：Group/topicView
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act=topicView
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) tid话题id
	 * 必填
	 * 
	 * @param league
	 * @return
	 */
	Model topicView(ModelLeagueTopic topic);

	/**
	 * 27.【话题回帖】：Group/replyTopic
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act=replyTopic
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) tid话题id
	 * 必填
	 * 
	 * @param league
	 * @return
	 */
	boolean replyTopic(ModelLeagueTopic topic);

	/**
	 * 28.【回复帖子】：Group/replyPost
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act=replyPost
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int)
	 * pid帖子id必填 (string) content 内容 必填
	 * 
	 * @param league
	 * @return
	 */
	boolean replyPost(ModelLeagueTopic topic);

	/**
	 * 29.【获取某个话题下的帖子】：Group/getTopicPosts 演示地址：
	 * 
	 * daxs.zhiyicx.com/index.php?app=api&mod=Group&act= getTopicPosts
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int)
	 * tid帖子id必填
	 * 
	 * @param league
	 * @return
	 */
	List<Model> getTopicPosts(ModelLeagueTopic topic);

	/**
	 * 30.【已加入社团】：Group/joinedGroup
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act= joinedGroup
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填
	 * 
	 * @param topic
	 * @return
	 */
	List<Model> joinedGroup(ModelUser user);

	/**
	 * 31.【社团基本信息】：Group/getGroupBaseInfo
	 * 
	 * 接口描述： 社团基本信息
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act=getGroupBaseInfo
	 * 
	 * 输入参数： (string) oauth_token必填 (string) oauth_token_secret 必填 (int) gid
	 * 社团id 必填
	 * 
	 * @param topic
	 * @return
	 */
	Model getGroupBaseInfo(ModelLeague league);

	/**
	 * 48.【社团作品】：Group/groupWorks
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act=groupWorks
	 * 
	 * 
	 * 输入参数： (int) gid 社团id (int) type 作品类型（1：文章 2：图片 3：视频 4：音频）
	 * 
	 * @param league
	 * @return
	 */
	List<Model> groupWorks(ModelLeague league);

	/**
	 * 49.【社团图片详情】：Group/groupPhotoView
	 * 
	 * 接口描述： 社团图片详情
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act=groupPhotoView
	 * 
	 * @param works
	 * @return
	 */
	Model groupPhotoView(ModelLeagueAlbum photoModel);

	/**
	 * 
	 * 55.【社团新闻列表】：Group/groupNewsList
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act=groupNewsList
	 * 
	 * 输入参数： (int) id 必填 社团id
	 * 
	 * @param league
	 * @return
	 */
	List<Model> groupNewsList(ModelTiding tiding);

	/**
	 * 56.【社团新闻详情】：Group/groupNewsDetail
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act=groupNewsDetail
	 * 
	 * 输入参数： (int) id 必填 新闻id
	 * 
	 * @param tiding
	 * @return
	 */
	Model groupNewsDetail(ModelTiding tiding);

	/**
	 * 57.【社团踢出成员】：Group/memberOut
	 * 
	 * 演示地址： daxs.zhiyicx.com/index.php?app=api&mod=Group&act=memberOut
	 * 
	 * 输入参数： (int) uid 必填 用户id (int) gid 必填 社团id (string) oauth_token 必填
	 * (string) oauth_token_secret 必填
	 * 
	 * @param member
	 * @return
	 */
	Model memberOut(ModelMember member);

}
