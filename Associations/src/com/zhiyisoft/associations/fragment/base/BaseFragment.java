package com.zhiyisoft.associations.fragment.base;

/***********************************************************************
 * Module:  BaseFragment.java
 * Author:  qcj qq:260964739
 * Purpose: Defines the Class BaseFragment
 ***********************************************************************/

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.zhiyisoft.associations.activity.LoginActivity;
import com.zhiyisoft.associations.activity.MainActivity;
import com.zhiyisoft.associations.adapter.base.BAdapter;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelUser;

/**
 * fragment的基类，其它fragment必須實現他，不要隨意修改這個基類
 * 
 **/
public abstract class BaseFragment extends Fragment implements OnClickListener {
	/** adapter 基类 */
	private BAdapter mAdapter;
	/** listview基类 */
	private BaseListView mListView;
	/** list的基类 */
	private List mList;
	/** fragment的布局view */
	private View mView;
	/** @pdOid 0d3b4e48-b478-406c-acc4-17fe74117a81 */
	private ModelUser mUser;
	/** 加載動畫 */
	private View mLoadingView;
	/** application基類 */
	public Association mApp;
	/** mInflater */
	public LayoutInflater mInflater;

	// public FragmentManager mFManager = getChildFragmentManager();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mView == null) {
			mView = inflater.inflate(getLayoutId(), null);
			mInflater = inflater;

		} else {
			// 当存在mview的时候就调用清零
			ViewGroup parent = (ViewGroup) mView.getParent();
			if (parent != null) {
				parent.removeView(mView);
				;
			}
			return mView;
		}
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mApp = (Association) getActivity().getApplication();
		if (checkTheUser()) {
			initIntentData();
			initView();
			initListener();
			initData();
		} else {
			mApp.startActivity(getActivity(), LoginActivity.class, null);
		}
	}

	/**
	 * 判断这个用户是否登录过
	 * 
	 * 如果个别fragement对用户开发的话，就重新这个方法，然后return true就可以了
	 * 
	 * @return
	 */
	public boolean checkTheUser() {
		ModelUser user = mApp.getUser();
		if (user == null) {
			return false;
		}
		return true;
	}

	/**
	 * @param viewid
	 * @pdOid 获取id并返回相应的控件
	 */
	protected View findViewById(int viewid) {
		return mView.findViewById(viewid);
	}

	/** 初始化intent的數據 ，没有的话 就直接返回空 */
	public abstract void initIntentData();

	/** 获取布局的id */
	public abstract int getLayoutId();

	/** 初始化各個控件 */
	public abstract void initView();

	/** 設置監聽器 */
	public abstract void initListener();

	/** 初始化數據 */
	public abstract void initData();

	/** 獲取基類baseListview */
	public BaseListView getListView() {
		if (mListView != null) {
			return mListView;
		}
		return null;
	}

	/**
	 * @param 传入的listview
	 */
	public void setListView(BaseListView lv) {
		this.mListView = lv;
	}

	/** 获取baseAdapter */
	public BAdapter getAdapter() {
		if (mAdapter != null) {
			return mAdapter;
		}
		return null;
	}

	/**
	 * @param 傳入的adapter
	 */
	public void setAdapter(BAdapter adapter) {
		this.mAdapter = adapter;
	}

}