package com.fltry.androidlibs.view.timeselect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public class GetTimeUtli {

	/**
	 * 得到一个时间(String类型)
	 * */
	public static String getTime(String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date data = new Date();
		String string = format.format(data);
		return string;
	}

	/**
	 * 将Date类型转成String
	 * */
	public static String getTime(Date d) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string = format.format(d);
		return string;
	}

	/**
	 * 比较两个时间的大小
	 * */
	public static int compare_date(Date DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = DATE1;
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 比较两个时间的大小
	 * */
	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 比较两个时间的大小
	 * */
	public static int compare_date(Date DATE1, Date DATE2) {
		try {
			Date dt1 = DATE1;
			Date dt2 = DATE2;
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 从当前日期算起，获取N天前的日期（当前日不算在内），日期格式为yyyy-MM-dd
	 *
	 * @param daily
	 *            天数
	 * @return
	 */
	public static String getDateByDay(Integer daily) {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date))
				- daily;
		if (day < 1) {
			month -= 1;
			if (month == 0) {
				year -= 1;
				month = 12;
			}
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				day = 30 + day;
			} else if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12) {
				day = 31 + day;
			} else if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29 + day;
				} else {
					day = 28 + day;
				}
			}

		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}
		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		return y + "-" + m + "-" + d;
	}

	/**
	 * 从当前日期算起，获取N个月前的日期，日期格式为yyyy-MM-dd
	 *
	 * @param mon
	 *            月份
	 * @return
	 */
	public static String getDateByMonth(Integer mon) {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date))
				- mon;
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
		if (month == 0) {
			year -= 1;
			month = 12;
		} else if (day > 28) {
			if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29;
				} else {
					day = 28;
				}
			} else if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day == 31) {
				day = 30;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10) {
			m = "00" + month;
		} else {
			m = month + "";
		}
		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		return y + "-" + m + "-" + d;
	}
}
