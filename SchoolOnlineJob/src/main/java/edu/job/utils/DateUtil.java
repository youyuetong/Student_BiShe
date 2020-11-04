package edu.job.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil {
	
	public static final String DEF_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEF_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEF_DATE_FORMAT_1 = "yyyy-MM-dd HH:mm";
	public static final String DEF_DATE_FORMAT_2 = "yyyy-MM";
	public static final String DEF_DATE_FORMAT_3 = "yyyy";
	public static final String DEF_DATE_FORMAT_4 = "yyyyMM";
	public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";
	public static final String DATE_TIME_FORMAT_8 = "yyyyMMdd";
	public static final String DATE_TIME_FORMAT_9 = "HH:mm";

	private static final SimpleDateFormat defDateFormat = new SimpleDateFormat(DEF_DATE_FORMAT);

	public static String getNextDateTimeInString(int nextDays) {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DATE, now.get(Calendar.DATE) + nextDays);
		return dateToString(now.getTime(), DEF_DATETIME_FORMAT);
	}

	public static String getNextDateInString(int nextDays) {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DATE, now.get(Calendar.DATE) + nextDays);
		return dateToString(now.getTime(), DEF_DATE_FORMAT);
	}

	public static String getNextDateTimeInString(String currentDateStr, int nextDays) {
		SimpleDateFormat dateFmt = new SimpleDateFormat(DEF_DATETIME_FORMAT);
		Date currentDate = null;
		try {
			currentDate = dateFmt.parse(currentDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar now = Calendar.getInstance();
		now.setTime(currentDate);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + nextDays);
		return dateToString(now.getTime(), DEF_DATETIME_FORMAT);
	}
	public static String getYesterdayInString(String currentDateStr, int nextDays) {
		SimpleDateFormat dateFmt = new SimpleDateFormat(DEF_DATETIME_FORMAT);
		Date currentDate = null;
		try {
			currentDate = dateFmt.parse(currentDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar now = Calendar.getInstance();
		now.setTime(currentDate);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - nextDays);
		return dateToString(now.getTime(), DEF_DATETIME_FORMAT);
	}
	public static String getYesterdayInString_(String currentDateStr, int nextDays) {
		SimpleDateFormat dateFmt = new SimpleDateFormat(DEF_DATE_FORMAT);
		Date currentDate = null;
		try {
			currentDate = dateFmt.parse(currentDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar now = Calendar.getInstance();
		now.setTime(currentDate);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - nextDays);
		return dateToString(now.getTime(), DEF_DATE_FORMAT);
	}

	public static String getDateTimeInString(Date dateTime){
//		Date date = new Date(Long.parseLong(dateTime));
		SimpleDateFormat dateFmt = new SimpleDateFormat(DEF_DATETIME_FORMAT);
		return dateFmt.format(dateTime);
	}

	public static String dateToString(Date date, String format) {
		if (date == null)
			return null;
		SimpleDateFormat dateFmt = new SimpleDateFormat(format);

		return dateFmt.format(date);
	}

	public static String getCurrentDate(){
		return dateToString(Calendar.getInstance().getTime(), DEF_DATETIME_FORMAT);
	}
	public static String getCurrentDate1(){
		return dateToString(Calendar.getInstance().getTime(), DEF_DATE_FORMAT);
	}

	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public static Date getSystemCurrentDate() {
		return getCalendar().getTime();
	}
	
	public static String[] curweek() {
		String[] week = new String[2];
		Calendar cal =Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		week[0] = df.format(cal.getTime());
		
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		week[1] = df.format(cal.getTime());
	    return week;
	}

	public static String formaStringDate(String input){
		if(null!=input){
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(input);
			return dateToString(date, DEF_DATE_FORMAT);

		}catch (Exception e){
			e.printStackTrace();
		}

		}
		return null;
	};

	public static String formatToDisplay(String input) {
		Date inputDate = strToDate(input, DATE_TIME_FORMAT);
		if (null != inputDate) {
			return dateToString(inputDate, DEF_DATE_FORMAT);
		}
		return null;
	}
	public static String formatToDisplayTime(String input) {
		Date inputDate = strToDate(input, DEF_DATETIME_FORMAT);
		if (null != inputDate) {
			return dateToString(inputDate, DATE_TIME_FORMAT);
		}
		return null;
	}
	public static String formatToDisplay2(String input) {
		Date inputDate = strToDate(input, DATE_TIME_FORMAT);
		if (null != inputDate) {
			return dateToString(inputDate, DEF_DATETIME_FORMAT);
		}
		return null;
	}
	public static String formatToDisplay3(String input) {
		Date inputDate = strToDate(input, DATE_TIME_FORMAT);
		if (null != inputDate) {
			return dateToString(inputDate, DATE_TIME_FORMAT_9);
		}
		return null;
	}
	/**
	 * 字符串转换成日期
	 * @param dateStr : 日期字符串
	 * @param format  : 想要得到的格式
	 * @return Date
	 */
	public static Date strToDate(String dateStr, String format) {
		if (StringUtils.isBlank(dateStr))
			return null;
		if (StringUtils.isBlank(format))
			format = dateStr.contains(":") ? DEF_DATETIME_FORMAT : DEF_DATE_FORMAT;

		SimpleDateFormat dateFmt = new SimpleDateFormat(format);
		
		dateFmt.setLenient(false);		//指定是否对日期格式进行容错分析
		ParsePosition pos = new ParsePosition(0);
		return dateFmt.parse(dateStr, pos);
	}
	
	/**
	 * 字符串转换成日期（"yyyy-MM-dd"格式）
	 * @param str   : 日期字符串
	 * @return Date
	 */
	public static Date strToDate(String str) {
		return strToDate(str, DEF_DATE_FORMAT);
	}

	public static Date strToDate2(String str) {
		return strToDate(str, DEF_DATE_FORMAT_1);
	}
	
	//获得当天0点时间
	public static Date getTimesmorning(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	//获得当天24点时间
	public static Date getTimesnight(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	//获得本周一0点时间
	public static Date getTimesWeekmorning(){
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}
	//获得本周日24点时间
	public static Date getTimesWeeknight(){
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(5, 7);
		return cal.getTime();
	}
	
	/**
	 * 得到系统日期
	 * @return Date
	 */
	public static Date getSystemDate() {
		Date date = new Date(); 
		String dateString = defDateFormat.format(date); 
		Date nowDate = defDateFormat.parse(dateString, new ParsePosition(0));
		
		return nowDate;
	}
	/**
	 * 完整的日期时间转换成仅日期
	 * @param date   : 时间
	 * @return Date  : yyyy-MM-dd
	 */
	public static Date datetimeToDate(Date date) {
		if (date==null)
			return null;

		SimpleDateFormat dateFmt = new SimpleDateFormat(DEF_DATE_FORMAT);
		//指定是否对日期格式进行容错分析
		dateFmt.setLenient(false);		
		ParsePosition pos = new ParsePosition(0);
		String str = dateToString(date, DEF_DATETIME_FORMAT);
		return dateFmt.parse(str, pos);
	}

	public static boolean compareMonths(String date1,String date2){
		boolean result = false;
		if(date1.equals(date2)){
			result=true;
		}else{
			result=false;
		}
		return result;
	}
	/**
	 * 获取日期加物位随机数
	 * @return Date  : yyyy-MM-dd
	 */
	public static String getDateUUID(){
		String date =dateToString(new Date(),DEF_DATE_FORMAT).replace("-", "");
		int rannum = (int) (new Random().nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
		return date+Integer.toString(rannum);

	}
/**
 * 两个日期的差距
 * */
	public static int differentDaysByMillisecond(Date date1,Date date2)
	{
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		return days;
	}
	/**
	 * date2比date1多的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDays(Date date1,Date date2)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1= cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if(year1 != year2)   //同一年
		{
			int timeDistance = 0 ;
			for(int i = year1 ; i < year2 ; i ++)
			{
				if(i%4==0 && i%100!=0 || i%400==0)    //闰年
				{
					timeDistance += 366;
				}
				else    //不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2-day1) ;
		}
		else    //不同年
		{
			return day2-day1;
		}
	}
}
