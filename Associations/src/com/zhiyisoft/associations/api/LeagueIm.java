package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午11:34:57 接口描述：实现创建社团
 *
 */

public interface LeagueIm {
	public static final String GROUP = "group";
	public static final String CREATEGROUP = "creategroup";
	public static final String GETGROUPCOMMONLIST = "getgroupcommonlist";

	public static final String JOIN = "join";
	public static final String MEMBERLIST = "memberlist";
	public static final String LEAVE = "leave";

	public static final String INDEX = "index";
	public static final String VIEW = "view";
	public static final String ALBUM = "album";
	public static final String CREATEALBUM = "createalbum";
	public static final String USER = "user";
	public static final String OPUS = "opus";

	/**
	 * 創建社團
	 * 
	 * @param modelItem
	 *            需要传递的社团model
	 * @return
	 */
	Object createLeague(Model modelItem);

	/**
	 * 获取社团的分类
	 * 
	 * @param mItem
	 * @return
	 */
	List<Model> getGroupCommonList(Model mItem);

	/**
	 * 加入社团
	 * 
	 * @param model
	 *            需要传的参数
	 * @return
	 */
	boolean addLeague(Model model);

	/**
	 * 获取社团的成员
	 * 
	 * @param model
	 * @return
	 */
	List<Model> getLeagueMember(Model model);

	/**
	 * 退出社团
	 * 
	 * @param model
	 * @return
	 */
	boolean quitLeague(Model model);

	/**
	 * 获取社团列表 act=index 传参schooled:学校ID； name:社团名（可选）
	 * 
	 * @param model
	 * @return
	 */
	List<Model> getLeagueList(Model model);

	/**
	 * 获取社团详情
	 * 
	 * 传参 mod=group&act=setmask
	 * 
	 * @param model
	 * @return
	 */
	Model getLeagueDetail(Model model);

	/**
	 * 根据社团id获取相册列表
	 * 
	 * @param model
	 * @return
	 */
	Model getAlbumByLeagueID(Model model);

	/**
	 * 根据相册ID获取相片列表
	 * 
	 * @param model
	 * @return
	 */
	Model getPhotoListByAlbumId(Model model);

	/**
	 * 创建相册 mod=opus&act=createalbum
	 * 
	 * 传参gid :社团ID name:相册名
	 * 
	 * @param model
	 * @return
	 */
	Model createAlbum(Model model);

}
