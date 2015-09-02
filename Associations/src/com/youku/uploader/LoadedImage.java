package com.youku.uploader;

import android.graphics.Bitmap;

/**
 * author：qiuchunjia time：下午5:43:38 类描述：这个类是实现
 *
 */

public class LoadedImage {
	Bitmap mBitmap;

	public LoadedImage(Bitmap bitmap) {
		mBitmap = bitmap;
	}

	public Bitmap getBitmap() {
		return mBitmap;
	}
}
