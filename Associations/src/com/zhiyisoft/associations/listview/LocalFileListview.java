package com.zhiyisoft.associations.listview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.zhiyisoft.associations.listview.base.BaseListView;

/**
 * author：qiuchunjia time：上午10:37:57 类描述：这个类是实现
 *
 */

public class LocalFileListview extends BaseListView {
	private Context mContext;

	public LocalFileListview(Context context) {
		super(context);
		this.mContext = context;
	}

	public LocalFileListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		this.setDivider(new ColorDrawable(0xfff9f9f9));
		this.setDividerHeight(20);
	}

	@Override
	public void onClick(AdapterView<?> parent, View view, int position, long id) {
	}
}
