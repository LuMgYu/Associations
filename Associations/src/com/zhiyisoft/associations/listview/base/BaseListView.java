package com.zhiyisoft.associations.listview.base;

/***********************************************************************
 * Module:  BaseListView.java
 * Author:  qcj qq:260964739
 * Purpose: Defines the Class BaseListView
 ***********************************************************************/

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.model.ModelItem;

/** listview的基类 ，任何listview都可以继承它，减少代码的冗余*/
public abstract class BaseListView extends ListView {

	public BaseListView(Context context) {
		super(context, null);
	}

	public BaseListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/** mlist數據 */
	private List<ModelItem> mList;
	/**下拉刷新的工具类*/
	// private DragDown mDragDown;
	/**activity*/
	private BaseActivity mBaseActivity;


	/** 初始化设置*/
	public void initSet() {
		// TODO: implement
	}

	/** 設置下拉刷新的頭部 */
	public void setHeaderView() {
		// TODO: implement
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
	}

	/** 设置加载更多的布局*/
	public void setFooterView() {
		// TODO: implement
	}

	/**重新每一個item的點擊事件*/
	public abstract void onClick();

}