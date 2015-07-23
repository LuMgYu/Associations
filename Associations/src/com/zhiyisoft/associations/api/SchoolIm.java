package com.zhiyisoft.associations.api;

import java.util.List;

import com.zhiyisoft.associations.model.Model;

/**
 * author：qiuchunjia time：上午10:43:07
 * 
 * 
 * 接口描述：根据省名获取学校列表
 *
 */

public interface SchoolIm {

	public static final String TOOL = "tool";
	public static final String SCHOOLBYPROVINCE = "schoolbyprovince";

	/**
	 * 根据省名返回该省的学校的集合
	 * 
	 * @param province
	 *            省名
	 * @return 返回学校的集合
	 */
	List<Model> getSchools(String province);
}
