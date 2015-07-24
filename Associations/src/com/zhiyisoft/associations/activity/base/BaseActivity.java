package com.zhiyisoft.associations.activity.base;

/***********************************************************************
 * Module:  BaseActivity.java
 * Author: qcj qq:260964739
 * Purpose: Defines the Class BaseActivity
 ***********************************************************************/

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.impl.RefreshListener;
import com.zhiyisoft.associations.listview.base.BaseListView;

/**
 * activity的基类，任何activity都要继承它，并且实现里面的方法 一般不要轻易修改它
 * 
 * @pdOid
 */
public abstract class BaseActivity extends FragmentActivity {
	/**
	 * activity的总布局，加入mTitleLayout和mBodyLayout
	 * 
	 * 
	 */
	private RelativeLayout mLayout;
	/** title 容器 */
	private LinearLayout mTitlell;
	/** 内容容器 */
	private LinearLayout mContentll;
	/** 底部容器 */
	public LinearLayout mBottomll; //
	/** bundle数据 */
	private Bundle mBundle;
	/** title左边的图片id */
	private int mTitleLeftImageId;
	/** title的右边id */
	private int mTitleRightImageId;
	/** title的布局 */
	private View mTitleLayout;
	/** 內容的布局 */
	private View mBodyLayout;
	/** 底部的布局 */
	private View mBottomLayout;

	public LayoutInflater mInflater;
	// 全局应用
	private Association mApp;

	// adapter的基类
	private BAdapter mAdapter;
	private BaseListView mListView;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mApp = (Association) getApplication();
		mApp.setActivity(this);
		mInflater = LayoutInflater.from(getApplicationContext());
		initTheCommonLayout();
		// 把内容和title结合
		setContentView(combineTheLayout());
		initIntent();
		initView();
		initIntent();
	}

	/** 初始化公共布局 */
	private void initTheCommonLayout() {
		mLayout = (RelativeLayout) mInflater.inflate(R.layout.comom_layout,
				null);
		mTitlell = (LinearLayout) mLayout.findViewById(R.id.ll_Title);
		mContentll = (LinearLayout) mLayout.findViewById(R.id.ll_content);
		mBottomll = (LinearLayout) mLayout.findViewById(R.id.ll_bottom);
	}

	/** 设置title的布局 */
	private void setTitleLayout() {
		// 当需改变的时候就imgeid时就从重新这个方法，这样可以增加扩张性
		mTitleLeftImageId = setTitleLeftImageId();
		mTitleRightImageId = setTitleRightImageId();
		String title = setCenterTitle();

		if (title != null && title.length() > 0) {
			mTitleLayout = mInflater.inflate(R.layout.title, null);
			ImageView iv_left = (ImageView) mTitleLayout
					.findViewById(R.id.iv_title_left);
			ImageView iv_right = (ImageView) mTitleLayout
					.findViewById(R.id.iv_title_right);
			TextView tv_title = (TextView) mTitleLayout
					.findViewById(R.id.tv_title);
			if (mTitleLeftImageId != 0) {
				iv_left.setImageResource(mTitleLeftImageId);
			}
			if (mTitleRightImageId != 0) {
				iv_right.setImageResource(mTitleRightImageId);
			}
			tv_title.setText(title + "");
		}
	}

	/**
	 * 设置底部布局
	 */
	private void setBottomlayout() {
		int id = getBottomLayoutId();
		if (id != 0) {
			mBottomll.setVisibility(View.VISIBLE);
			mBodyLayout = mInflater.inflate(R.layout.bottom_layout, mBottomll);
			Log.i("bottom", "--------------------->mBodyLayout");
		}
	}

	/**
	 * 默认不实现底部布局，如果要实现的话就重新這個方法，增加其扩张性
	 * 
	 * @return 返回底部布局id
	 */
	public int getBottomLayoutId() {
		return 0;
	}

	/** 把内容和title全部加载到这个总布局里面 */
	public View combineTheLayout() {
		setTitleLayout();
		setBottomlayout();
		mBodyLayout = mInflater.inflate(getLayoutId(), null);
		if (mTitleLayout != null)
			mTitlell.addView(mTitleLayout);
		mContentll.addView(mBodyLayout);
		if (mBottomLayout != null) {
			mBottomll.addView(mBottomLayout);
			Log.i("bottom", "--------------------->");
		}
		return mLayout;
	}

	/** 設置中間的title */
	public abstract String setCenterTitle();

	/** 設置title左邊的圖片的id 需要改变图片就在子类中重新这个方法 */
	public int setTitleLeftImageId() {
		return 0;
	}

	/** 设置title右边的的图片id 需要改变图片就在子类中重新这个方法 */
	public int setTitleRightImageId() {
		return 0;
	}

	/** 獲取intent传来的内容 */
	public abstract void initIntent();

	/** 获取内容布局id */
	public abstract int getLayoutId();

	/** 初始化各个控件 */
	public abstract void initView();

	/** 初始化设置各个控件的监听器 */
	public abstract void initListener();

	public void refreshHeader() {
		if (mAdapter != null) {
			mAdapter.doRefreshHeader();
		}
	}

	/**
	 * @param adapter
	 *            需要设置的adapter
	 */
	public void setAdapter(BAdapter adapter) {
		this.mAdapter = adapter;
	}

	/**
	 * @param listView
	 *            需要设置的listView
	 */
	public void setListView(BaseListView listView) {
		this.mListView = listView;
	}

	public RefreshListener getListView() {
		return mListView;
	}

	/**
	 * 设置底部可见
	 */
	public void setBottomVisible() {
		mBottomll.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置底部不可见
	 */
	public void setBottomGone() {
		mBottomll.setVisibility(View.GONE);
	}

}