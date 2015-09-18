package com.zhiyisoft.associations.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.umeng.socialize.utils.Log;

/**
 * author：qiuchunjia time：上午10:22:07 类描述：这个类是实现
 *
 */

public class BitmapUtil {
	private static final int IMAGESIZE = 70; // 设置每张图片的最大不能超过100k

	/**
	 * 等比例压缩图片
	 * 
	 * @param image
	 * @return
	 */
	private static Bitmap compressImage(Bitmap originBitmap) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		originBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		Log.i("compressImage", "-------first_baos.toByteArray().length--------"
				+ baos.toByteArray().length / 1024 + "k");
		while (baos.toByteArray().length / 1024 > IMAGESIZE && options > 0) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			originBitmap.compress(Bitmap.CompressFormat.PNG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			Log.i("compressImage", "-------baos.toByteArray().length--------"
					+ baos.toByteArray().length / 1024 + "k");
			options -= 49;// 每次都减少100
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	public static Bitmap getimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	/**
	 * 第三：图片按比例大小压缩方法（根据Bitmap图片压缩）：
	 * 
	 * @param image
	 * @return
	 */
	public static Bitmap compress(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Bitmap originBitmap;
		baos = is2boas(is);
		int count = baos.toByteArray().length / 1024; // 获取原始的图片多少k
		BitmapFactory.Options options = new Options();
		options.inSampleSize = count / IMAGESIZE; // 压缩的倍数，经过计算来压缩 压缩后保证图片在100k
		originBitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
				baos.toByteArray().length, options);
		return compressImage(originBitmap);// 压缩好比例大小后再进行质量压缩

		// if (baos.toByteArray().length / 1024 > 1024) {//
		// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
		// BitmapFactory.Options options = new Options();
		// options.inSampleSize = 20; // 压缩 20倍
		// originBitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
		// baos.toByteArray().length, options);
		// } else {
		// originBitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
		// baos.toByteArray().length);
		// }

	}

	/**
	 * 把输入流转换为输出流ByteArrayOutputStream
	 * 
	 * @param is
	 * @param baos
	 * @return
	 * @throws IOException
	 */
	private static ByteArrayOutputStream is2boas(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			baos.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos;
	}

}
