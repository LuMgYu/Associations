package com.zhiyisoft.associations.img;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Environment;

import com.zhiyisoft.associations.cache.base.DiskLruCache;
import com.zhiyisoft.associations.util.BitmapUtil;

public class WebImageCache {
	private static DiskLruCache mDiskCache;
	// 让内存有100m 最开始把单位设置错了设置为8b，导致存不进去，卧槽 ，fuck，浪费老子两个小时！
	private int mDiskCacheSize = 100 * 1024 * 1024;
	private String mFileName = "Association";
	private Context mContext;

	public WebImageCache(Context context) {
		this.mContext = context;
		if (mDiskCache == null) {
			File file = getDiskCacheDir(context, mFileName);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				mDiskCache = DiskLruCache.open(file, getAppVersion(context), 1,
						mDiskCacheSize);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Bitmap get(String url) {
		Bitmap bitmap = null;
		if (url != null && url.length() > 0) {
			try {
				String path = hashKeyForDisk(url);
				DiskLruCache.Snapshot snapShot = mDiskCache.get(path);
				if (snapShot != null) {
					InputStream is = snapShot.getInputStream(0);
					if (is != null) {
						bitmap = BitmapUtil.getBitmapByIs(is, 0, 0);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bitmap;
	}

	public void put(String url) {
		if (url != null && url.length() > 0) {
			try {
				String path = hashKeyForDisk(url);
				DiskLruCache.Editor editor = mDiskCache.edit(path);
				if (editor != null) {
					OutputStream outputStream = editor.newOutputStream(0);
					if (downloadUrlToStream(url, outputStream)) {
						editor.commit();
					} else {
						editor.abort();
					}
				}
				mDiskCache.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void clear() {
	}

	/**
	 * 获取内存卡路径
	 * 
	 * @param context
	 * @param uniqueName
	 * @return
	 */
	@SuppressLint("NewApi")
	private File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath;
//		if (Environment.MEDIA_MOUNTED.equals(Environment
//				.getExternalStorageState())
//				|| !Environment.isExternalStorageRemovable()) {
//			cachePath = context.getExternalCacheDir().getPath();
//		} else {
			cachePath = context.getCacheDir().getPath();
		// }
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

	// private String getFilePath(String url) {
	// return url;
	// // return diskCachePath + getCacheKey(url);
	// }

	public String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	private String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	private boolean downloadUrlToStream(String urlString,
			OutputStream outputStream) {
		HttpURLConnection urlConnection = null;
		BufferedOutputStream out = null;
		BufferedInputStream in = null;
		try {
			final URL url = new URL(urlString);
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedInputStream(urlConnection.getInputStream(),
					8 * 1024);
			out = new BufferedOutputStream(outputStream, 8 * 1024);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			return true;
		} catch (final IOException e) {
			System.out.println("-------" + e.toString());
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
