package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.ModelLeague;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午11:34:57 接口描述：实现创建社团
 *
 */

public interface LeagueIm {
	public static final String GROUP = "Group";

	public static final String INDEX = "index";
	public static final String CREATEGROUP = "createGroup";

	public static final String NAME = "name";
	public static final String CATEGORYID = "categoryId";
	public static final String LOGO = "logo";
	public static final String DESCRIPTION = "description";
	public static final String SCHOOLID = "schoolId";
	public static final String PRIVATE = "private";
	public static final String OPENERNAME = "openerName";
	public static final String CONTACT = "contact";

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

	// /**
	// * 获取社团的分类
	// *
	// * @param mItem
	// * @return
	// */
	// List<Model> getGroupCommonList(Model mItem);
	//
	// /**
	// * 加入社团
	// *
	// * @param model
	// * 需要传的参数
	// * @return
	// */
	// boolean addLeague(Model model);
	//
	// /**
	// * 获取社团的成员
	// *
	// * @param model
	// * @return
	// */
	// List<Model> getLeagueMember(Model model);
	//
	// /**
	// * 退出社团
	// *
	// * @param model
	// * @return
	// */
	// boolean quitLeague(Model model);
	//
	// /**
	// * 获取社团列表 act=index 传参schooled:学校ID； name:社团名（可选）
	// *
	// * @param model
	// * @return
	// */
	// List<Model> getLeagueList(Model model);
	//
	// /**
	// * 获取社团详情
	// *
	// * 传参 mod=group&act=setmask
	// *
	// * @param model
	// * @return
	// */
	// Model getLeagueDetail(Model model);
	//
	// /**
	// * 根据社团id获取相册列表
	// *
	// * @param model
	// * @return
	// */
	// Model getAlbumByLeagueID(Model model);
	//
	// /**
	// * 根据相册ID获取相片列表
	// *
	// * @param model
	// * @return
	// */
	// Model getPhotoListByAlbumId(Model model);
	//
	// /**
	// * 创建相册 mod=opus&act=createalbum
	// *
	// * 传参gid :社团ID name:相册名
	// *
	// * @param model
	// * @return
	// */
	// Model createAlbum(Model model);

}
