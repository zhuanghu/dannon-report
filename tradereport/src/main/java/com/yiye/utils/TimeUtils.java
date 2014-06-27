package com.yiye.utils;

import java.util.Calendar;
import java.util.Date;
import java.text.*;
import java.util.concurrent.TimeUnit;


public class TimeUtils {

	public final static int DEFAULT_START_TIME = 1325347200; //2012-1-1 00:00:00 UTF+8

	/**
	 * 通过距离2012年1月1日的偏离时间，计算出实际日期
	 * @param offset 偏离时间
	 * @param timeUnit 时间单元
	 * @return
	 */
	public static Date getAbsoluteDate(int offset, TimeUnit timeUnit) {
		long absoluteTime = (long)(DEFAULT_START_TIME + timeUnit.toSeconds(offset))*1000;
		return new Date(absoluteTime);
	}
	
	/**
	 * 计算得到现在距离2012年1月1日的偏离秒数
	 * @return 偏离秒数
	 */
	public static int getOffsetSecond() {
		return getOffsetSecond(new Date());
	}
	
	/**
	 * 计算得到现在距离2012年1月1日的偏离天数
	 * @return 偏离天数
	 */
	public static int getOffsetDay() {
		return getOffsetDay(new Date());
	}
	
	/**
	 * 计算得到指定日期距离2012年1月1日的偏离秒数
	 * @param 指定的日期
	 * @return 偏离秒数
	 */
	public static int getOffsetSecond(Date date) {
		return (int)(date.getTime()/1000 - DEFAULT_START_TIME);
	}
	
	/**
	 * 计算得到指定日期距离2012年1月1日的偏离天数
	 * @param 指定的日期
	 * @return 偏离天数
	 */
	public static int getOffsetDay(Date date) {
		return (int) getOffsetSecond(date)/24/3600;
	}
	
	/**
	 * 计算现在到今天最后一秒还有多少秒数
	 * @return 到今天23:59:59还剩的秒数
	 */
	public static int getTodayLeftSecond() {
		Calendar c = Calendar.getInstance();
		long last = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		long start = c.getTimeInMillis();
		return (int) (start - last)/1000;
	}
	
	/**
	 * 计算本小时还剩几秒
	 * @return 本小时还剩的秒数
	 */
	public static int getHourLeftSecond() {
		Calendar c = Calendar.getInstance();
		long last = c.getTimeInMillis();
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		long start = c.getTimeInMillis() + 1000;
		return (int) (start - last)/1000;
	}
	
	/**
	 * 计算给定时间到目前的天数
	 * @param time 开始时间的偏离值，单位秒
	 * @return
	 */	
	public static int getDaysPassed(int offset, TimeUnit timeUnit) {
		int offset_second = (int) timeUnit.toSeconds(offset);
		return Math.round((getOffsetSecond() - offset_second)/3600/24);
	}
	
	/**
	 * 加/减传入的天数，取得yyyy-MM-dd格式的日期字符串
	 * @param added_day，正数代表后面的天数，负数代表之前的天数
	 * @return
	 */
	public static String getAddedDate(int added_day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, added_day);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(c.getTime());
	}
	
	/**
	 * 取得传入时间的当前小时的第一秒，比如12:00:00
	 * @param date
	 * @return
	 */
	public static Date getFirstSecondOfHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 取得传入时间的当前小时的最后一秒，比如12:59:59
	 * @param date
	 * @return
	 */
	public static Date getLastSecondOfHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 59);
		return c.getTime();
	}
	
	/**
	 * just like function between in mysql
	 * @param source date to compare
	 * @param start start time
	 * @param end end time
	 * @return
	 */
	public static boolean between(Date source, Date start, Date end) {
		if (source == null || start == null || end == null) return true;
		int s = source.compareTo(start);
		int e = source.compareTo(end);
		return (s >= 0) && (e <= 0); 
	}
	
	public static boolean isSameday(long timestamp1, long timestamp2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(timestamp1);
		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(timestamp2);
		int day1 = c1.get(Calendar.DAY_OF_YEAR);
		int day2 = c2.get(Calendar.DAY_OF_YEAR);
		return (day1 == day2 && Math.abs(timestamp2 - timestamp1) < 3600000*24 );
	}
	
}
