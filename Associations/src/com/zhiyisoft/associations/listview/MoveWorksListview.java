package com.zhiyisoft.associations.listview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.zhiyisoft.associations.activity.AssociationAlbumSingleActivity;
import com.zhiyisoft.associations.activity.AssociationTopicDetailActivity;
import com.zhiyisoft.associations.config.Config;
import com.zhiyisoft.associations.listview.base.BaseListView;
import com.zhiyisoft.associations.model.base.Model;

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
		Model model = (Model) parent.getItemAtPosition(position);
		Bundle data = new Bundle();
		data.putSerializable(Config.SEND_ACTIVITY_DATA, model);
		mApp.startActivity(mApp.getActivity(),
				AssociationTopicDetailActivity.class, data);
	}
}
