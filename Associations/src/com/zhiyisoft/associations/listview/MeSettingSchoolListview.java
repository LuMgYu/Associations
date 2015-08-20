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

public class MeSettingSchoolListview extends BaseListView {
	private Context mContext;

	public MeSettingSchoolListview(Context context) {
		super(context);
		this.mContext = context;
		setNotPull();
	}

	public MeSettingSchoolListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		setNotPull();
	}

	private void setNotPull() {
		this.setPullRefreshEnable(false);
		this.setPullLoadEnable(false);
		this.setAutoLoadEnable(false);
	}

	@Override
	public void onClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(mContext, "点击了这个按钮" + position, Toast.LENGTH_LONG)
				.show();
	}
}
