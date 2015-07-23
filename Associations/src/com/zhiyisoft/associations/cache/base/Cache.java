package com.zhiyisoft.associations.cache.base;

/***********************************************************************
 * Module:  Cache.java
 * Author:  Administrator
 * Purpose: Defines the Interface Cache
 ***********************************************************************/

import java.util.List;

import com.zhiyisoft.associations.model.Model;

/** 所有缓存的接口，用单例模式管理缓存，这样比较好 */
public interface Cache {
	/**
	 * @param cacheType
	 *            缓存类型 獲取緩存
	 */
	List<Model> getTheData(int cacheType);

	/**
	 * @param cacheType
	 *            缓存类型 删掉缓存
	 */
	boolean deleteTheData(int cacheType);

	/**
	 * @param list
	 * @param cacheType
	 *            缓存类型 添加缓存类型
	 */
	boolean addTheData(List<Model> list, int cacheType);

}