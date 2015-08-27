package com.zhiyisoft.associations.listview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationMainActivity;
import com.zhiyisoft.associations.activity.AssociationSingleActivity;
import com.zhiyisoft.associations.activity.AssociationTopicDetailActivity;
import com.zhiyisoft.associations.listview.base.BaseListView;

/**
 * author：qiuchunjia time：上午10:37:57 类描述：这个类是实现
 *
 */

public class MoveWorksListview extends BaseListView {
	private Context mContext;

	public MoveWorksListview(Context context) {
		super(context);
		this.mContext = context;
	}

	public MoveWorksListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		this.setDivider(new ColorDrawable(0xfff9f9f9));
		this.setDividerHeight(20);
	}

	@Override
	public void onClick(AdapterView<?> parent, View view, int position, long id) {
		Bundle data = new Bundle();
		mApp.startActivity(mApp.getActivity(),
				AssociationTopicDetailActivity.class, data);
	}
}
