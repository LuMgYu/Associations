package com.zhiyisoft.associations.adapter.base;

/***********************************************************************
 * Module:  BaseAdapter.java
 * Author:  qcj qq:260964739
 * Purpose: Defines the Class BaseAdapter
 ***********************************************************************/

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.cache.base.Cache;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.ModelItem;
import com.zhiyisoft.associations.util.ViewHolder;

/** adapter的基類，不要輕易修改這個類 */
public abstract class BAdapter extends BaseAdapter {
	/** 用來裝各個item的控件，方便管理 */
	private ViewHolder mHolder;
	/** 存入activity，必要时用来调用里面的东西 */
	private BaseActivity mBaseActivity;
	/** app全局应用 */
	private BAdapter mApp;
	/** 創建item需要传入的list */
	private List<ModelItem> mList;
	/** mlist的最后的位置 */
	private int mLastPosition;
	/** mlist的第一个位置 */
	private int mFirstPosition;
	/** 需要传入的fragment */
	private BaseFragment mBaseFragment;
	/** 缓存 */
	private Cache mCache;
	/** 需要刷新的条数 */
	private final static int REFRESH_COUNT = 20;

	/** 子类实现，用来第一次打开的时候获取新数据，当刷新到时候是调用refreshHeader */
	public abstract List<ModelItem> refreshNew();

	/**
	 * @param item
	 *            传递item到api用来刷新数据
	 * @param count
	 *            获取刷新数据的多少 默認為20條，考虑的扩展性，可以修改它
	 * @pdOid 上拉刷新數據
	 */
	public abstract List<ModelItem> refreshHeader(ModelItem item, int count);

	/**
	 * @param item
	 * @param count
	 * @pdOid 41d6c9bb-67ea-474c-80f3-fc3509e5fd4e
	 */
	public abstract List<ModelItem> refreshFooter(ModelItem item, int count);

	/**
	 * @param list
	 * @pdOid d4990472-8df3-407f-b567-3b44077a9bc3
	 */
	private void addHeadList(List<ModelItem> list) {
		// TODO: implement
	}

	/** @pdOid e9ac2811-6287-4d6b-a186-b8449ef15875 */
	public void doRefreshNew() {
		// TODO: implement
	}

	/** @pdOid 93ac9c89-f599-4672-9665-16fa1477cc35 */
	public void doRefreshHeader() {
		// TODO: implement
	}

	/** @pdOid 97932285-9108-41e2-88c9-e7242822e901 */
	public void doRefreshFooter() {
		// TODO: implement
	}

	/**
	 * @param list
	 * @pdOid a6fa103d-0db2-4645-946d-2ca44077b7e4
	 */
	public void addFooterList(ArrayList list) {
		// TODO: implement
	}

	/** @pdOid 78936163-86f4-438f-8120-26288da8362e */
	public Cache getCache() {
		return null;
	}

	/**
	 * @param cacheType
	 * @pdOid e7d2628a-dc02-4de3-ab78-b18d14fdaace
	 */
	public abstract int getTheCacheType();

	/** @pdOid 1a56b5bf-f558-40a7-a4e3-94bc295037fd */
	public java.lang.Object getFirstItem() {
		// TODO: implement
		return null;
	}

	/** @pdOid e8fc8a00-e003-4213-9003-76552ccd682f */
	public java.lang.Object getLastItem() {
		// TODO: implement
		return null;
	}

	/** @pdOid 27f312a2-0d4d-4fd1-bf1f-5bab9f6a7d87 */
	public int deleteAlltheItem() {
		// TODO: implement
		return 0;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}