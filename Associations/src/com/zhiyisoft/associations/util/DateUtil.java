package com.zhiyisoft.associations.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * author：qiuchunjia time：下午2:19:43 类描述：这个类是实现时间与时间戳的转换
 *
 */

public class DateUtil {
	/**
	 * 将年月日转换为 时间戳 例如 2015-7-12
	 * 
	 * @param year_month_day
	 * @return
	 */
	public static String dateToStr(String year_month_day) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d号");
			Date d = sdf.parse(year_month_day);
			Long time = d.getTime();
			Log.i("time", "dateToStr--------->" + time);
			return String.valueOf(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间戳转为年月日时间
	 * 
	 * @param time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String strTodate(String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d号");
			Date date = new Date(Long.valueOf(time));
			String d = format.format(date);
			return d;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
