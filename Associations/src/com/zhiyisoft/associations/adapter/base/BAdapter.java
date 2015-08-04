package com.zhiyisoft.associations.adapter.base;

/***********************************************************************
 * Module:  BaseAdapter.java
 * Author:  qcj qq:260964739
 * Purpose: Defines the Class BaseAdapter
 ***********************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.cache.base.Cache;
import com.zhiyisoft.associations.fragment.base.BaseFragment;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.ViewHolder;

/** adapter的基類，不要輕易修改這個類 */
public abstract class BAdapter extends BaseAdapter {
	/** 用來裝各個item的控件，方便管理 */
	public ViewHolder mHolder;
	/** 存入activity，必要时用来调用里面的东西 */
	public BaseActivity mBaseActivity;
	/** app全局应用 */
	private Association mApp;
	/** 創建item需要传入的list */
	private List<Model> mList;
	/** 需要传入的fragment */
	private BaseFragment mBaseFragment;
	/** 缓存 */
	private Cache mCache;
	/** 获取mapp里面的线程池 */
	private ExecutorService mExecutor;

	/** 工作线程，用來通過網絡獲取數據 */
	private WorkThread mWork;
	/** 需要刷新的条数 */
	private final static int REFRESH_COUNT = 20;
	private final static int REFRESH_NEW = 1;
	private final static int REFRESH_HEADER = 2;
	private final static int REFRESH_FOOTER = 3;

	public LayoutInflater mInflater;

	public BAdapter(BaseActivity activity, List<Model> list) {
		mBaseActivity = activity;
		mBaseActivity.setAdapter(this);
		mApp = (Association) activity.getApplication();
		mExecutor = mApp.getExecutor();
		mList = list;
		mHolder = new ViewHolder();
		mInflater = LayoutInflater.from(activity);
		doRefreshNew();
	}

	public BAdapter(BaseFragment fragment, List<Model> list) {
		this.mBaseFragment = fragment;
		mBaseActivity = (BaseActivity) mBaseFragment.getActivity();
		mBaseActivity.setAdapter(this);
		mInflater = LayoutInflater.from(mBaseActivity);
		mApp = (Association) mBaseFragment.getActivity().getApplication();
		mExecutor = mApp.getExecutor();
		mList = list;
		mHolder = new ViewHolder();
		doRefreshNew();
	}

	/** 子类实现，用来第一次打开的时候获取新数据，当刷新到时候是调用refreshHeader */
	public abstract List<Model> refreshNew();

	/**
	 * @param item
	 *            传递item到api用来刷新数据
	 * @param count
	 *            获取刷新数据的多少 默認為20條，考虑的扩展性，可以修改它
	 * @pdOid 上拉刷新數據
	 */
	public abstract List<Model> refreshHeader(Model item, int count);

	/**
	 * @param item
	 *            需要獲取的對象
	 * @param count
	 *            數量
	 * @pdOid 下拉加载更多
	 */
	public abstract List<Model> refreshFooter(Model item, int count);

	/**
	 * 用来处理线程发送来的数据，然后处理数据加载到mlist里面
	 */
	private Handler mHandle = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {
			case REFRESH_NEW:
				List<Model> refreshData = (List<Model>) msg.obj;
				addHeadList(refreshData);
				break;

			case REFRESH_HEADER:
				Log.i("refresh", "case REFRESH_HEADER:");
				List<Model> items = (List<Model>) msg.obj;
				addHeadList(items);
				break;
			case REFRESH_FOOTER:
				List<Model> footerItems = (List<Model>) msg.obj;
				addFooterList(footerItems);
				break;
			}

		};

	};

	/**
	 * 真正的獲取數據，先查看是否存在缓存，如果存在就调用缓存的， 如果不存在就調用refreshnew（）獲取的數據加載到adapter里面
	 * */
	public void doRefreshNew() {
		// 先获取缓存
		mExecutor.execute(new WorkThread(REFRESH_NEW));
	}

	/** 真正的刷新数据數據，即調用RefreshHeader() 獲取的數據加載到adapter里面 */
	public void doRefreshHeader() {
		this.mBaseActivity.getListView().headerShow();
		this.mBaseActivity.getListView().headerRefresh();
		// TODO 这里要先检查网络是否有，如果没有的话 就return；
		if (mList == null)
			mList = new ArrayList<Model>();
		if (mList.size() > 0) {
			mExecutor.execute(new WorkThread(REFRESH_HEADER));
		} else {
			doRefreshNew();
		}

	}

	/** 真正的獲取數據，即調用RefreshFooter() 獲取的數據加載到adapter里面 */
	public void doRefreshFooter() {
		// TODO 这里要先检查网络是否有，如果没有的话 就return；
		if (mList == null)
			mList = new ArrayList<Model>();
		if (!mList.isEmpty()) {
			mExecutor.execute(new WorkThread(REFRESH_FOOTER));
		}

	}

	/**
	 * @param list
	 * @pdOid 下拉刷新后把数据加载到头部
	 */
	private void addHeadList(List<Model> list) {
		Log.i("refresh", " addHeadList(List<Model> list)=" + list.size());
		if (mList != null && list.size() > 0) {
			List<Model> cacheList = new ArrayList<Model>();
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
	private void addFooterList(List<Model> list) {
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
	public Model getFirstItem() {
		if (mList.size() > 0) {
			return mList.get(0);
		}
		return null;
	}

	/** 获取list的最有一个item */
	public Model getLastItem() {
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
	public Model getLastPositionItem() {
		if (mList != null && !mList.isEmpty()) {
			return mList.get(mList.size() - 1);
		}
		return null;
	}

	/**
	 * @return 返回mlist第一个modelitem
	 */
	public Model getFirstPositionItem() {
		if (mList != null && !mList.isEmpty()) {
			return mList.get(0);
		}
		return null;
	}

	/**
	 * @author qcj 工作线程，用来通过网络获取数据，下拉刷新，加载更多的list
	 *         后期統一把線程放到線程池里面加載，方便管理，退出程序的時候集體清理掉線程
	 */
	private class WorkThread extends Thread {
		int type = 0;

		public WorkThread(int type) {
			this.type = type;
		}

		@Override
		public void run() {
			switch (type) {
			case REFRESH_NEW:
				firstRefreshData();
				sendMessage(REFRESH_NEW, mList);
				break;
			case REFRESH_HEADER:
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Log.i("refresh", "public void run()--REFRESH_HEADER");
				sendMessage(REFRESH_HEADER,
						refreshHeader(mList.get(0), REFRESH_COUNT));
				break;
			case REFRESH_FOOTER:
				sendMessage(
						REFRESH_FOOTER,
						refreshFooter(mList.get(mList.size() - 1),
								REFRESH_COUNT));
				break;
			}
		}

		/**
		 * 第一次刷新数据（就是打開listview 獲取的数据）先獲取緩存中的數據，如果存在就加載出來，否則就去網絡上獲取數據
		 */
		private void firstRefreshData() {
			mCache = getCache();
			if (mCache != null) {
				mList = mCache.getTheData(0);
			}
			if (mList == null || !(mList.size() > 0)) {
				// TODO 这里要先检查网络是否有，如果没有的话 就return；
				List<Model> list = refreshNew();
				if (list == null)
					list = new ArrayList<Model>();
				mList = list;
			}
		}

		/**
		 * 向ui線程發送數據
		 * 
		 * @param type
		 *            传递message的类型
		 * @param items
		 *            傳遞的數據items 类型为list
		 * 
		 */
		private void sendMessage(int type, List<Model> items) {
			Message message = Message.obtain();
			message.what = type;
			message.obj = items;
			mHandle.sendMessage(message);
		}
	}

	// ------------------------------------实现baseadapter必须实现的方法-------------------------------------------------------
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

	public List<Model> getList() {
		return mList;
	}

}