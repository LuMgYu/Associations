package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:43:07
 * 
 * 
 * 接口描述：关于社团的操作
 *
 */

public interface AssociationIm {

	public static final String GROUP = "group";
	public static final String JOIN = "join";
	public static final String MEMBERLIST = "memberlist";
	public static final String LEAVE = "leave";

	/**
	 * 加入社团
	 * 
	 * @param model
	 *            需要传的参数
	 * @return
	 */
	boolean addAssociation(Model model);

	/**
	 * 获取社团的成员
	 * 
	 * @param model
	 * @return
	 */
	List<Model> getAssociationMember(Model model);

	/**
	 * 退出社团
	 * 
	 * @param model
	 * @return
	 */
	boolean quitAssociation(Model model);
}
