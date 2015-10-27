package com.zhiyisoft.associations.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * author：qiuchunjia time：下午2:53:58 类描述：这个类是实现
 *
 */

public class ImageLoader extends BaseCache {
	private static ImageLoader loader;

	private ImageLoader(Context context) {
		super(context);
	}

	public static ImageLoader instance(Context context) {
		if (loader == null) {
			loader = new ImageLoader(context);
		}
		return loader;
	}

	public Bitmap getBitmap(String path) {
//		Object is = getTheData(path);
//		Bitmap bitmap = BitmapFactory.decodeStream(is);
//		if (bitmap != null) {
//			return bitmap;
//		}
		return null;

	}

	public void setimage(ImageView imageView, String path) {
		Bitmap bitmap = getBitmap(path);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		}
	}
}
