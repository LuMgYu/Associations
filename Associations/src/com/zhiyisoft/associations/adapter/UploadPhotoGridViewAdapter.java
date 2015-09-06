package com.zhiyisoft.associations.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationUploadPhotoActivity;
import com.zhiyisoft.associations.img.SmartImageView;
import com.zhiyisoft.associations.util.UIUtils;
import com.zhiyisoft.associations.util.localImageHelper.LocalImageManager;

/**
 * author：qiuchunjia time：下午6:23:39 类描述：这个类是实现
 *
 */

public class UploadPhotoGridViewAdapter extends BaseAdapter {
	// TODO
	private List<String> mList; // 现在用这个来展示效果，以后肯定是url
	private Context mContext;
	private LocalImageManager imageManager;

	public UploadPhotoGridViewAdapter(List<String> list, Context context) {
		this.mList = list;
		this.mContext = context;
		imageManager = LocalImageManager.from(mContext);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new SmartImageView(mContext);
		int width = UIUtils.getWindowWidth(mContext) - 50;
		AbsListView.LayoutParams params = new AbsListView.LayoutParams(
				width / 4, width / 4);
		imageView.setLayoutParams(params);
		imageView.setPadding(10, 0, 0, 0);
		if (mList.get(position).equals(AssociationUploadPhotoActivity.ADDPHOTO)) {
			imageView.setBackgroundResource(R.drawable.add);
			imageView.setTag(AssociationUploadPhotoActivity.ADDPHOTO);
		} else {
			// TODO 加载文件夹路径的照片
			imageManager.displayImage(imageView, mList.get(position),
					R.drawable.default_image_small, width / 4, width / 4);
		}
		return imageView;
	}
}
