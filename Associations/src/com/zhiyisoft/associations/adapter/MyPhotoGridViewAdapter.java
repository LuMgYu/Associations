package com.zhiyisoft.associations.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：下午6:23:39 类描述：这个类是实现
 *
 */

public class MyPhotoGridViewAdapter extends BaseAdapter {
	// TODO
	private int[] mStr; // 现在用这个来展示效果，以后肯定是url
	private Context mContext;

	public MyPhotoGridViewAdapter(int[] str, Context context) {
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
		SmartImageView imageView = new SmartImageView(mContext);
		int width = UIUtils.getWindowWidth(mContext);
		imageView.setImageResource(mStr[position]);
		imageView.setLayoutParams(new AbsListView.LayoutParams(width / 3,
				width / 3));
		return imageView;
	}
}
