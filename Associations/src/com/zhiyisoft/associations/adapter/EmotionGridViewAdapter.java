package com.zhiyisoft.associations.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.umeng.socialize.utils.Log;
import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：上午11:14:33 类描述：这个类是实现
 *
 */

public class EmotionGridViewAdapter extends BaseAdapter {
	private int mCount; // 表情的数量
	private Context mContext;

	public EmotionGridViewAdapter(Context context, int count) {
		this.mCount = count;
		this.mContext = context;
	}

	@Override
	public int getCount() {

		return mCount;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(R.drawable.association_icon);
		int width = UIUtils.getWindowWidth(mContext) / 7;
		LayoutParams params = new AbsListView.LayoutParams(width, width);
		imageView.setLayoutParams(params);
		imageView.setPadding(20, 20, 20, 20);
		return imageView;
	}
}
