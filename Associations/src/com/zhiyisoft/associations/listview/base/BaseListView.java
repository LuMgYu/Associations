package com.zhiyisoft.associations.listview.base;

/***********************************************************************
 * Module:  BaseListView.java
 * Author:  qcj qq:260964739
 * Purpose: Defines the Class BaseListView
 ***********************************************************************/

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.impl.RefreshListener;
import com.zhiyisoft.associations.model.base.Model;
import com.zhiyisoft.associations.util.DragDown;

/** listview的基类 ，任何listview都可以继承它，减少代码的冗余 */
public abstract class BaseListView extends ListView implements RefreshListener {

	public BaseListView(Context context) {
		super(context, null);
		initSet(context);
	}

	public BaseListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initSet(context);
	}

	private Context mContext;

	// /** mlist數據 */
	private List<Model> mList;
	/** 下拉刷新的工具类 */
	private DragDown mDragDown;
	/** activity */
	private BaseActivity mBaseActivity;
	/**
	 * 最后可见的位置
	 */
	private int mLastVisiablPos;

	private Association mApp;

	/** 初始化设置 */
	public void initSet(Context context) {
		this.setScrollbarFadingEnabled(true);
		this.setCacheColorHint(0);
		this.setDividerHeight(1);
		mApp = (Association) context.getApplicationContext();
		mBaseActivity = mApp.getActivity();
		mBaseActivity.setListView(this);
		mDragDown = new DragDown(mBaseActivity, this);
		this.setOnTouchListener(mDragDown);
	}

	/** 設置下拉刷新的頭部 */
	public void setHeaderView() {
		if (mDragDown.getHeaderView() != null) {
			super.addHeaderView(mDragDown.getHeaderView());
		}
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		this.setHeaderView();
		this.setFooterView();
		super.setAdapter(adapter);
		this.mList = ((BAdapter) adapter).getList();
		Log.i("hhh", "---------------走到这里没有？");
		this.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mDragDown.isUnClickable()) {
					return;
				}
				//点击加载更多
				if (view.getId() == R.id.footer_content) {
					Toast.makeText(mContext, "点击了加載更多", Toast.LENGTH_LONG)
							.show();
					return;
				}
				// 子类去实现他
				onClick(parent, view, position, id);
			}
		});
		this.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					// TODO
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});
	}

	/** 设置加载更多的布局 */
	public void setFooterView() {
		if (mDragDown.getFooterView() != null) {
			super.addFooterView(mDragDown.getFooterView());
		}

	}

	/** 重新每一個item的點擊事件 */
	public abstract void onClick(AdapterView<?> parent, View view,
			int position, long id);

	// ----------------------------------实现refreshListener接口------------------------------------------------

	@Override
	public void headerShow() {
		mDragDown.headerShow();
	}

	@Override
	public void headerHiden() {
		mDragDown.headerHiden();
	}

	@Override
	public void headerRefresh() {
		mDragDown.headerRefresh();
	}

	@Override
	public void footerShow() {
		mDragDown.footerShow();
	}

	@Override
	public void footerHiden() {
		mDragDown.footerHiden();
	}

}