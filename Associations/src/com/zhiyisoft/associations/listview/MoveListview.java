package com.zhiyisoft.associations.listview;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.zhiyisoft.associations.activity.AssociationMainActivity;
import com.zhiyisoft.associations.activity.AssociationSingleActivity;
import com.zhiyisoft.associations.activity.MoveMainActivity;
import com.zhiyisoft.associations.listview.base.BaseListView;

/**
 * author：qiuchunjia time：上午10:37:57 类描述：这个类是实现
 *
 */

public class MoveListview extends BaseListView {

	public MoveListview(Context context) {
		super(context);
	}

	public MoveListview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onClick(AdapterView<?> parent, View view, int position, long id) {
		Bundle data = new Bundle();
		if (position % 2 == 0) {
			mApp.startActivity(mBaseActivity, MoveMainActivity.class,
					data);
		} else {
			mApp.startActivity(mBaseActivity, AssociationSingleActivity.class,
					data);
		}
	}
}
