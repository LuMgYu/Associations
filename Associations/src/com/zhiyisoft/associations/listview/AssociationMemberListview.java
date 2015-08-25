package com.zhiyisoft.associations.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.zhiyisoft.associations.listview.base.BaseListView;

/**
 * author：qiuchunjia time：上午10:37:57 类描述：这个类是实现
 *
 */

public class AssociationMemberListview extends BaseListView {
	private Context mContext;

	public AssociationMemberListview(Context context) {
		super(context);
		this.mContext = context;
	}

	public AssociationMemberListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	}

	@Override
	public void onClick(AdapterView<?> parent, View view, int position, long id) {
	}
}
