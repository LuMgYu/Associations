package com.zhiyisoft.associations.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.zhiyisoft.associations.listview.base.BaseListView;

/**
 * author：qiuchunjia time：上午10:37:57 类描述：这个类是实现
 *
 */

public class NotifyMsgListview extends BaseListView {
	private Context mContext;

	public NotifyMsgListview(Context context) {
		super(context);
		this.mContext = context;
	}

	public NotifyMsgListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	}

	@Override
	public void isSetFooterGone(int count) {
		// 重新她然后就不需要加载更多这四个字了
	}

	@Override
	public void onClick(AdapterView<?> parent, View view, int position, long id) {
	}
}
