package com.zhiyisoft.associations.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView.ScaleType;

import com.zhiyisoft.associations.activity.base.BaseActivity;
import com.zhiyisoft.associations.application.Association;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.util.UIUtils;

/**
 * author：qiuchunjia time：下午6:23:39 类描述：这个类是实现
 *
 */

public class MyPhotoGridViewAdapter extends BaseAdapter {
	// TODO
	private List<String> photoUrls; // 现在用这个来展示效果，以后肯定是url
	private Context mContext;
	private Association mApp;

	public MyPhotoGridViewAdapter(Context context, List<String> list) {
		this.mContext = context;
		this.photoUrls = list;
		if (context instanceof BaseActivity) {
			mApp = ((BaseActivity) context).mApp;
		}
	}

	@Override
	public int getCount() {
		return photoUrls.size();
	}

	@Override
	public Object getItem(int position) {
		return photoUrls.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SmartImageView imageView = new SmartImageView(mContext);
		imageView.setScaleType(ScaleType.CENTER_CROP);
		int width = UIUtils.getWindowWidth(mContext) - 30;
		imageView.setLayoutParams(new AbsListView.LayoutParams(width / 3,
				width / 3));
		if (mApp != null) {
			mApp.displayImage(photoUrls.get(position), imageView);
		} else {
			imageView.setImageUrl(photoUrls.get(position));
		}
		return imageView;
	}
}
