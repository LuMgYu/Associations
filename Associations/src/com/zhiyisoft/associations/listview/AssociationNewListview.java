package com.zhiyisoft.associations.listview;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.zhiyisoft.associations.activity.AssociationTopicDetailActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

/**
 * author：qiuchunjia time：上午10:37:57 类描述：这个类是实现
 *
 */

public class AssociationNewListview extends BaseListView {
	private Context mContext;

	public AssociationNewListview(Context context) {
		super(context);
		this.mContext = context;
	}

	public AssociationNewListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	}

	@Override
	public void onClick(AdapterView<?> parent, View view, int position, long id) {
		Bundle data = new Bundle();
		Model model = (Model) parent.getItemAtPosition(position);
		data.putSerializable(Config.SEND_ACTIVITY_DATA, model);
		mApp.startActivity(mApp.getActivity(),
				AssociationTopicDetailActivity.class, data);
	}
}
