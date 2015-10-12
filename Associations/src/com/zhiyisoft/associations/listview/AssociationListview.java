package com.zhiyisoft.associations.listview;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.zhiyisoft.associations.activity.AssociationMainActivity;
import com.zhiyisoft.associations.activity.AssociationSingleActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.ModelLeague;

/**
 * author：qiuchunjia time：上午10:37:57 类描述：这个类是实现
 *
 */

public class AssociationListview extends BaseListView {
	private Context mContext;

	public AssociationListview(Context context) {
		super(context);
		this.mContext = context;
	}

	public AssociationListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	}

	@Override
	public void onClick(AdapterView<?> parent, View view, int position, long id) {
		Bundle data = new Bundle();
		ModelLeague league = (ModelLeague) parent.getItemAtPosition(position);
		data.putSerializable(Config.SEND_ACTIVITY_DATA, league);
		if (league.getIsin() == 0) {
			// 未加入
			mApp.startActivity(mApp.getActivity(),
					AssociationMainActivity.class, data);
		} else {
			mApp.startActivity(mApp.getActivity(),
					AssociationSingleActivity.class, data);
		}

	}
}
