package com.zhiyisoft.associations.img;

import android.content.Context;
import android.graphics.Bitmap;

public class WebImage implements SmartImage {
	private static final int CONNECT_TIMEOUT = 5000;
	private static final int READ_TIMEOUT = 10000;

	private static WebImageCache webImageCache;

	private String url;

	public WebImage(String url) {
		this.url = url;
	}

	public Bitmap getBitmap(Context context) {
		// Don't leak context
		if (webImageCache == null) {
			webImageCache = new WebImageCache(context);
		}

		// Try getting bitmap from cache first
		Bitmap bitmap = null;
		if (url != null) {
			bitmap = webImageCache.get(url);
			if (bitmap == null) {
				webImageCache.put(url);
				bitmap = webImageCache.get(url);
			}
		}

		return bitmap;
	}
}
