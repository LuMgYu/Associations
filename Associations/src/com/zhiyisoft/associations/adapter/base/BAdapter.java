package com.zhiyisoft.associations.adapter.base;

/***********************************************************************
 * Module:  BaseAdapter.java
 * Author:  qcj qq:260964739
 * Purpose: Defines the Class BaseAdapter
 ***********************************************************************/

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.cache.base.Cache;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.ModelItem;
import com.zhiyisoft.associations.util.ToastUtils;
import com.zhiyisoft.associations.util.ViewHolder;

/** adapter的基類，不要輕易修改這個類 */
public abstract class BAdapter extends BaseAdapter {
	/** 用來裝各個item的控件，方便管理 */
	private ViewHolder mHolder;
	/** 存入activity，必要时用来调用里面的东西 */
	private BaseActivity mBaseActivity;
	/** app全局应用 */
	private Association mApp;
	/** 創建item需要传入的list */
	private List<ModelItem> mList;
	/** 需要传入的fragment */
	private BaseFragment mBaseFragment;
	/** 缓存 */
	private Cache mCache;
	/** 需要刷新的条数 */
	private final static int REFRESH_COUNT = 20;
	public LayoutInflater mInflater;

	public BAdapter(BaseActivity activity, List<ModelItem> list) {
		mBaseActivity = activity;
		mBaseActivity.setAdapter(this);
		mApp = (Association) activity.getApplication();
		mList = list;
		mHolder = new ViewHolder();
		mInflater = LayoutInflater.from(activity);
		doRefreshNew();
	}

	public BAdapter(BaseFragment fragment, List<ModelItem> list) {
		this.mBaseFragment = fragment;
		mBaseActivity = (BaseActivity) mBaseFragment.getActivity();
		mApp = (Association) mBaseFragment.getActivity().getApplication();
		mList = list;
		mHolder = new ViewHolder();
		doRefreshNew();
	}

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
	 *            需要獲取的對象
	 * @param count
	 *            數量
	 * @pdOid 下拉加载更多
	 */
	public abstract List<ModelItem> refreshFooter(ModelItem item, int count);

	/**
	 * 真正的獲取數據，先查看是否存在缓存，如果存在就调用缓存的， 如果不存在就調用refreshnew（）獲取的數據加載到adapter里面
	 * */
	public void doRefreshNew() {
		// 先获取缓存
		mCache = getCache();
		if (mCache != null) {
			mList = mCache.getTheData(0);
		}
		if (mList == null || !(mList.size() > 0)) {
			// TODO 这里要先检查网络是否有，如果没有的话 就return；
			List<ModelItem> list = refreshNew();
			if (list == null)
				list = new ArrayList<ModelItem>();
			mList = list;
			addHeadList(mList);
		}

	}

	/** 真正的刷新数据數據，即調用RefreshHeader() 獲取的數據加載到adapter里面 */
	public void doRefreshHeader() {
		this.mBaseActivity.getListView().headerRefresh();
		// TODO 这里要先检查网络是否有，如果没有的话 就return；
		if (mList == null)
			mList = new ArrayList<ModelItem>();
		if (mList.size() > 0) {
			addHeadList(refreshHeader(mList.get(0), REFRESH_COUNT));
		} else {
			doRefreshNew();
		}

	}

	/** 真正的獲取數據，即調用RefreshFooter() 獲取的數據加載到adapter里面 */
	public void doRefreshFooter() {
		// TODO 这里要先检查网络是否有，如果没有的话 就return；
		if (mList == null)
			mList = new ArrayList<ModelItem>();
		if (!mList.isEmpty()) {
			addFooterList(refreshFooter(mList.get(mList.size() - 1),
					REFRESH_COUNT));
		}

	}

	/**
	 * @param list
	 * @pdOid 下拉刷新后把数据加载到头部
	 */
	private void addHeadList(List<ModelItem> list) {
		if (mList != null && list.size() > 0) {
			List<ModelItem> cacheList = new ArrayList<ModelItem>();
			if (mList.size() > 0) {
				for (int i = 0; i < cacheList.size(); i++) {
					cacheList.add(mList.remove(i));
				}
			}
			mList.addAll(list);
			mList.addAll(cacheList);
			// 加了数据后就要通知adapter 更新list
			this.notifyDataSetChanged();
		}
		// 通知更新后就应该隐藏headview了
		this.mBaseActivity.getListView().headerHiden();
	}

	/**
	 * @param list
	 * @pdOid 把数据加载到底部
	 */
	private void addFooterList(List<ModelItem> list) {
		if (mList != null && list.size() > 0) {
			mList.addAll(list);
			// 加了数据后就要通知adapter 更新list
			this.notifyDataSetChanged();
		}
	}

	/** 獲取緩存，通常是調用mapp里面的緩存 */
	private Cache getCache() {
		return null;
	}

	/**
	 * @param cacheType
	 *            获取缓存的类型，因为每一个下拉框的item是不一样的，所以必须要标明获取哪一种缓存 獲取緩存類型
	 */
	public abstract int getTheCacheType();

	/** 获取list 的第一个item */
	public ModelItem getFirstItem() {
		if (mList.size() > 0) {
			return mList.get(0);
		}
		return null;
	}

	/** 获取list的最有一个item */
	public ModelItem getLastItem() {
		if (mList.size() > 0) {
			return mList.get(mList.size() - 1);
		}
		return null;
	}

	/** 刪掉所以的list，估計這個用不到，不過先寫好，萬一要用呢 */
	public void deleteAlltheItem() {
		if (mList.size() > 0) {
			mList.removeAll(mList);
		}
	}

	/**
	 * @return 返回mlist最后一个modelitem
	 */
	public ModelItem getLastPositionItem() {
		if (mList != null && !mList.isEmpty()) {
			return mList.get(mList.size() - 1);
		}
		return null;
	}

	/**
	 * @return 返回mlist第一个modelitem
	 */
	public ModelItem getFirstPositionItem() {
		if (mList != null && !mList.isEmpty()) {
			return mList.get(0);
		}
		return null;
	}

	// --------------------------------------------------------------------------------------------
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public List<ModelItem> getList() {
		return mList;
	}

}