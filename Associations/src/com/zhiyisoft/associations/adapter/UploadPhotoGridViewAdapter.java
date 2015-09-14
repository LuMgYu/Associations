package com.zhiyisoft.associations.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;
import com.zhiyisoft.associations.activity.AssociationUploadPhotoActivity;
import com.zhiyisoft.associations.util.UIUtils;
import com.zhiyisoft.associations.util.ViewHolder;
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

	private View mView;

	private ViewHolder mViewHolder;
	private LayoutInflater mInflater;

	public UploadPhotoGridViewAdapter(List<String> list, Context context) {
		this.mList = list;
		this.mContext = context;
		imageManager = LocalImageManager.from(mContext);
		mViewHolder = new ViewHolder();
		mInflater = LayoutInflater.from(mContext);
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
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.upload_photo_single_item,
					null);
			mView = convertView;
			initView();
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		initSet(position, mViewHolder);
		return convertView;
	}

	/**
	 * 初始化设置
	 * 
	 * @param position
	 * @param imageView
	 */
	private void initSet(int position, ViewHolder holder) {
		ImageView imageView = holder.iv_upload_photo;
		FrameLayout frameLayout = holder.fl_progress;
		int width = UIUtils.getWindowWidth(mContext) - 50;
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				width / 4, width / 4);
		imageView.setLayoutParams(params);
		imageView.setPadding(10, 0, 0, 0);
		if (mList.get(position).equals(AssociationUploadPhotoActivity.ADDPHOTO)) {
			frameLayout.setVisibility(View.GONE);
			imageView.setImageResource(R.drawable.add);
			imageView.setTag(AssociationUploadPhotoActivity.ADDPHOTO);
		} else {
			// TODO 加载文件夹路径的照片
			imageManager.displayImage(imageView, mList.get(position),
					R.drawable.default_image_small, width / 4, width / 4);
		}
	}

	private void initView() {
		if (mView != null) {
			mViewHolder.iv_upload_photo = (ImageView) mView
					.findViewById(R.id.iv_upload_photo);
			mViewHolder.tv_progress_bg = (TextView) mView
					.findViewById(R.id.tv_progress_bg);
			mViewHolder.tv_progress = (TextView) mView
					.findViewById(R.id.tv_progress);
			mViewHolder.fl_progress = (FrameLayout) mView
					.findViewById(R.id.fl_progress);
		}
	}
}
