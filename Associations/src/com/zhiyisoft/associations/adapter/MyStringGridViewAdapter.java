package com.zhiyisoft.associations.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：下午6:23:39 类描述：这个类是实现
 *
 */

public class MyStringGridViewAdapter extends BaseAdapter {
	private String[] mStr;
	private Context mContext;

	public MyStringGridViewAdapter(String[] str, Context context) {
		this.mStr = str;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return mStr.length;
	}

	@Override
	public Object getItem(int position) {
		return mStr[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = new TextView(mContext);
		textView.setTextColor(mContext.getResources().getColor(
				R.color.text_black));
		textView.setLayoutParams(new AbsListView.LayoutParams(UIUtils
				.getWindowWidth(mContext) / 6, LayoutParams.WRAP_CONTENT));
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(13);
		textView.setText(mStr[position] + "");
		return textView;
	}
}
