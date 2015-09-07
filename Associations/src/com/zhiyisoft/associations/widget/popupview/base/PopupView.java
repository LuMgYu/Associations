package com.zhiyisoft.associations.widget.popupview.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * author：qiuchunjia time：上午11:42:38
 * 类描述：这个类是实现PopupWindow的封装，作为activity或者fragment的一种行为！
 * 
 * 这样可以降低耦合
 *
 * 考虑到可变太多（加载的布局，初始化控件，设置监听器，等），就采用模版模式来实现
 * 
 * TODO 后期代码重构的时候再来书写这一块
 */

public class PopupView {
	public Context mContext;
	private LayoutInflater mInflater;
	public PopupWindow mPopWindow;
	private View mView;

	/**
	 * @param context
	 *            上下文
	 * @param xml
	 *            布局文件
	 */
	public PopupView(Context context, int xml) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		mView = mInflater.inflate(xml, null);
	}

	/**
	 * @param id
	 *            view的id
	 *
	 * @return 代表改id的控件
	 */
	public View findViewbyId(int id) {
		return mView.findViewById(id);
	}

	public void setWindowAlpha(float alpha) {
//		WindowManager.LayoutParams params = mContext.get.getAttributes();
//		params.alpha = alpha;
//		mContext.getWindow().setAttributes(params);
	}
}
