package com.zhiyisoft.associations.listview;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.zhiyisoft.associations.activity.AssociationAlbumSingleActivity;
import com.zhiyisoft.associations.listview.base.BaseListView;

/**
 * author：qiuchunjia time：上午10:37:57 类描述：这个类是实现
 *
 */

public class AssociationAlbumListview extends BaseListView {
	private Context mContext;

	public AssociationAlbumListview(Context context) {
		super(context);
		this.mContext = context;
	}

	public AssociationAlbumListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	}

	@Override
	public void onClick(AdapterView<?> parent, View view, int position, long id) {
		Bundle data = new Bundle();
		mApp.startActivity(mBaseActivity, AssociationAlbumSingleActivity.class,
				data);
	}
}
