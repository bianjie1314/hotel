package com.syshotel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil{
	/**
	 * 获取某周的开始日期（该方法以周一为每周开始）
	 * 
	 * @param year
	 *            年份
	 * @param weekNo
	 *            第几周，一年共 52 周，所以值的范围为 （1 - 52）
	 * @return Calendar
	 */
	public static Calendar getStartDayOfWeek(int year, int weekNo) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置周一为每周的开始
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		return cal;
	}

	/**
	 * 获得两个时间相差距离多少天多少小时多少分多少秒
	 *
	 * @param one 时间参数 1 格式：1990-01-01 12:00:00
	 * @param two 时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTime(Date one, Date two) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {

			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long[] times = {day, hour, min, sec};
		return times;
	}
	
	/**
	 * 获取当前日期与时间
	 * @return String
	 */
	public static String getDateTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}


	/**
	 * 获取当前日期与时间
	 * @return long :timeMillis 将指定的毫秒转换为时间格式
	 */
	public static String getMillisToDateTime(long timeMillis){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(timeMillis));
	}
	
	
	/**
	 * 获取当前日期与时间
	 * @return long :timeMillis 将指定的毫秒转换为时间格式
	 */
	public static long getDateTimeToMillis(String dateTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(dateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date.getTime();
	}

	/**
	 * 获取某周的结束日期（该方法以周一为每周开始）
	 * 
	 * @param year
	 *            年份
	 * @param weekNo
	 *            某年的第几周，一年共 52 周，所以值的范围为 （1 - 52）
	 * @return Calendar
	 */
	public static Calendar getEndDayOfWeek(int year, int weekNo) {
		Calendar cal = getStartDayOfWeek(year, weekNo);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		return cal;
	}

	/**
	 * 获取某年某月第一天 （yyyy-MM-dd）格式
	 * 
	 * @param year
	 * @param month
	 * @return yyyy-MM-dd
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfMonth = sdf.format(cal.getTime());
		return firstDayOfMonth;
	}

	/**
	 * 获取某年某月最后一天 （yyyy-MM-dd）格式
	 * 
	 * @param year
	 * @param month
	 * @return yyyy-MM-dd
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	/**
	 * 格式化String时间
	 *
	 * @param time       String类型时间
	 * @param timeFromat String类型格式
	 * @return 格式化后的Date日期
	 */
	public static Date parse2Date(String time, String timeFromat) {
		if (time == null || time.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(timeFromat);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (Exception e) {

		}
		return date;
	}

}
