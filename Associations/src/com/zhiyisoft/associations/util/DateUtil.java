package com.zhiyisoft.associations.util;

import java.sql.Timestamp;
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
			long unixTimestamp = d.getTime() / 1000;
			Log.i("time", "dateToStr--------->" + unixTimestamp);
			return String.valueOf(unixTimestamp);
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
			Long timestamp = Long.valueOf(time) * 1000;
			Timestamp unixTime = new Timestamp(timestamp);
			SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d号");
			String d = format.format(unixTime);
			return d;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把时间戳转化为date
	 * 
	 * @param time
	 * @return
	 */
	public static Date stampToDate(String time) {
		if (time != null) {
			Long timestamp = Long.valueOf(time) * 1000;
			Timestamp unixTime = new Timestamp(timestamp);
			return unixTime;
		}
		return null;
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean compareDate(Date first, Date second) {
		if (first != null && second != null) {
			if (first.getTime() > second.getTime()) {
				return true;
			}
			return false;
		}
		return false;

	}
}
