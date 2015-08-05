package com.zhiyisoft.associations.widget;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiyisoft.associations.R;

/**
 * @author frankiewei 相册的ItemView,自定义View.方便复用.
 */
public class ViewPagerItemView extends FrameLayout {

	/**
	 * 图片的ImageView.
	 */
	private ImageView mAlbumImageView;

	/**
	 * 图片名字的TextView.
	 */
	private TextView mALbumNameTextView;

	/**
	 * 图片的Bitmap.
	 */
	private Bitmap mBitmap;
	private int resid;

	/**
	 * 要显示图片的JSONOBject类.
	 */
	private JSONObject mObject;

	public ViewPagerItemView(Context context) {
		super(context);
		setupViews();
	}

	public ViewPagerItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupViews();
	}

	// 初始化View.
	private void setupViews() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.association_photo_display_item,
				null);
		mAlbumImageView = (ImageView) view.findViewById(R.id.photo_imgview);
		addView(view);
	}

	/**
	 * 填充数据，共外部调用.
	 * 
	 * @param object
	 */
	public void setData(int resId) {
		this.resid = resId;
		mAlbumImageView.setImageResource(resId);

	}

	/**
	 * 这里内存回收.外部调用.
	 */
	public void recycle() {
		mAlbumImageView.setImageBitmap(null);
		if ((this.mBitmap == null) || (this.mBitmap.isRecycled()))
			return;
		this.mBitmap.recycle();
		this.mBitmap = null;
	}

	/**
	 * 重新加载.外部调用.
	 */
	public void reload() {
		// 实战中如果图片耗时应该令其一个线程去拉图片异步,不然把UI线程卡死.
		mAlbumImageView.setImageResource(resid);
	}

}