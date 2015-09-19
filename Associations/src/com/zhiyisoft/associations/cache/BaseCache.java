package com.zhiyisoft.associations.cache;

import java.io.File;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.support.v4.util.LruCache;

import com.zhiyisoft.associations.cache.base.Cache;
import com.zhiyisoft.associations.cache.base.DiskLruCache;

/**
 * author：qiuchunjia time：下午1:44:52 类描述：这个类是实现
 *
 */

public class BaseCache implements Cache {
	public static BaseCache cache;
	private LruCache<String, Object> mLruChe;
	private DiskLruCache mDiskCache;
	// 设置一级缓存的大小
	private int mCacheSize = (int) Runtime.getRuntime().totalMemory();
	// 设置二级缓存的大小
	private int mDiskCacheSize = 30 * 1024*1024;
	private String mFileName = "Association";
	private Context mContext;

	public static BaseCache getInstance(Context context) {
		if (cache == null) {
			cache = new BaseCache(context);
		}
		return cache;
	}

	protected BaseCache(Context context) {
		this.mContext = context;
		if (mLruChe == null || mDiskCache != null) {
			mLruChe = new LruCache<String, Object>(mCacheSize / 4);
			File cacheFile = getDiskCacheDir(context, mFileName);
			try {
				mDiskCache = DiskLruCache.open(cacheFile,
						getAppVersion(context), 1, mDiskCacheSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressLint("NewApi")
	private File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = context.getExternalCacheDir().getPath();
		} else {
			cachePath = context.getCacheDir().getPath();
		}
		return new File(cachePath + File.separator + uniqueName);
	}

	/**
	 * 获取app版本号
	 * 
	 * @param context
	 * @return
	 */
	private int getAppVersion(Context context) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public Object getTheData(String path) {
		if (path != null) {
			Object is = mLruChe.get(path);
			// if (is == null) {
			// try {
			// DiskLruCache.Snapshot snapshot = mDiskCache.get(path);
			// if (snapshot != null) {
			// InputStream inputStream = snapshot.getInputStream(0);
			// return inputStream;
			// }
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			return is;
		}
		return null;
	}

	@Override
	public boolean addTheData(Object is, String path) {
		if (is != null && path != null) {
			mLruChe.put(path, is);
			// try {
			// DiskLruCache.Editor editor = mDiskCache.edit(path);
			// OutputStream os = editor.newOutputStream(0);
			// os = new BufferedOutputStream(os, 8 * 1024);
			// int b;
			// while ((b = is.read()) != -1) {
			// os.write(b);
			// }
			// editor.commit();
			// } catch (IOException e) {
			// e.printStackTrace();
			// } finally {
			// try {
			// mDiskCache.flush();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
		}

		return false;
	}

	@Override
	public int getCacheSize() {
		if (mDiskCache != null) {
			long i = mDiskCache.size();
			int count = (int) i / 1024;
			return count;
		}
		return 0;
	}

	@Override
	public boolean removeCache() {
		if (mDiskCache != null) {
			try {
				mDiskCache.delete();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		return false;
	}

}
