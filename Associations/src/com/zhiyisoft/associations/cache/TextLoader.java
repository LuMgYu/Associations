package com.zhiyisoft.associations.cache;

import android.content.Context;

/**
 * author：qiuchunjia time：下午2:40:52 类描述：这个类是实现
 *
 */

public class TextLoader extends BaseCache {
	private static TextLoader loader;

	private TextLoader(Context context) {
		super(context);
	}

	public static TextLoader instance(Context context) {
		if (loader == null) {
			loader = new TextLoader(context);
		}
		return loader;
	}

	public String getString(String path) {
//		InputStream is = getTheData(path);
//		try {
//			byte[] bt = StreamTool.stream2Byte(is);
//			String str = new String(bt);
//			return str;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}
}
