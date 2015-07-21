package com.zhiyisoft.associations.activity.base;

/***********************************************************************
 * Module:  BaseActivity.java
 * Author: qcj qq:260964739
 * Purpose: Defines the Class BaseActivity
 ***********************************************************************/

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.adapter.base.BAdapter;

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
	private LinearLayout mLayout;
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

	private LayoutInflater mInflater;

	// adapter的基类
	private BAdapter mAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mInflater = LayoutInflater.from(getApplicationContext());
		// 把内容和title结合
		setContentView(combineTheLayout());
		initIntent();
		initView();
		initIntent();
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

	/** 把内容和title全部加载到这个总布局里面 */
	public View combineTheLayout() {
		setTitleLayout();
		mLayout = new LinearLayout(getApplicationContext());
		mLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		mLayout.setOrientation(LinearLayout.VERTICAL);
		mBodyLayout = mInflater.inflate(getLayoutId(), null);
		if (mTitleLayout != null)
			mLayout.addView(mTitleLayout);
		mLayout.addView(mBodyLayout);
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

}