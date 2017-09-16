package com.shang.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:日期转换类
 * 
 * @author NICK
 * 
 */
public class ConvertDate {

	/**
	 * 功能描述:系统当前的时间
	 * @return java.util.Date(时间)
	 */
	public static Date getServerTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 功能描述:系统当前的时间秒
	 * @return 秒
	 */
	public static long getCurrSeconds(){
	
		long second = DateUtil.getSeconds( DateUtil.DateToString(DateUtil.getServerTime(), "yyyy-MM-dd HH:mm:ss"));
		return second;		
	}
	
	/**
	 * 功能描述:将时间转换为指定的格式类 (yyyy-MM-dd HH:mm:ss)
	 * @param date  转换的时间
	 * @param str   格式
	 * @return 时间字符串 错误返回(1900-00-00)
	 */
	public static String DateToString(Date date, String str)  {
		String dates ="1900-00-00";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(str);
			dates = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dates;
	}

	/**
	 * 功能描述:将指定格式的字符转换为date的类
	 * @param str   字符串
	 * @param df  格式
	 * @return 时间
	 */
	public static Date StringToDate(String str, String df) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(df);
		try {
			calendar.setTime(sdf.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar.getTime();
	}

	/**
	 * 功能描述: 两个日期型日期之间的时间天数。
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间1
	 * @return 天数
	 */
	public static int getDateNum(Date date1, Date date2) {
		return Math
				.abs((int) ((date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24));
	}

	/**
	 * 功能描述: 两个字符格式日期之间的天数
	 * 
	 * @param sd1
	 *            时间1
	 * @param sd2
	 *            时间1
	 * @param df
	 *            格式
	 * @return 天数
	 */
	public static int getDateNum(String sd1, String sd2, String df) {
		Date date1 = StringToDate(sd1, df);
		Date date2 = StringToDate(sd2, df);
		return getDateNum(date1, date2);
	}

	/**
	 * 功能描述:两个字符格式日期之间的每天的字符值
	 * 
	 * @param sd1
	 *            时间1
	 * @param sd2
	 *            时间2
	 * @param df
	 *            格式
	 * @param df2
	 *            格式
	 * @return 字符串数组
	 */
	public static String[] getDateNumStr(String sd1, String sd2, String df,
			String df2) throws Exception {
		Date date1 = StringToDate(sd1, df);
		Date date2 = StringToDate(sd2, df);
		int num = getDateNum(date1, date2) + 1;
		String[] dateStr = new String[num];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		for (int i = 0; i < num; i++) {
			dateStr[i] = DateToString(calendar.getTime(), df2);
			calendar.add(Calendar.DAY_OF_YEAR, +1);
		}
		return dateStr;
	}
	
	
	/**
	 * 功能描述: 两个字符格式日期之间的每天的日期对象
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 时间间隔日期
	 */
	public static List<Date> getBetweenDate(Date date1 ,Date date2) {
		List<Date> list = new ArrayList<Date>();
		
		int num = getDateNum(date1, date2) + 1;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		for (int i = 0; i < num; i++) {
			list.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_YEAR, +1);
		}
		return list;
	}
	
	/**
	 * 功能描述: 本年剩余天数
	 * 
	 * @param date
	 *            时间
	 * @return 天数
	 */
	public static int getRsYearNum(Date date) throws Exception {
		String year = DateToString(date, "yyyy");
		Date dateLast = StringToDate(year + "-12-31", "yyyy-MM-dd");
		return getDateNum(date, dateLast);
	}

	/**
	 * 功能描述: 本月天数
	 * 
	 * @param date
	 *            时间
	 * @return 天数
	 */
	public static int getMonthNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +1);
		Date date2 = calendar.getTime();
		return getDateNum(date, date2);
	}

	/**
	 * 功能描述:本月剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRsMonthNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +1);
		Date date2 = calendar.getTime();
		return getDateNum(date, date2) - calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述: 本周剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRsWeekNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return 7 - calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 功能描述: 指定日期n天后的日期
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getAfterDate(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, n);
		return calendar.getTime();
	}
	

	/**
	 * 功能描述: 得到指定日期n月后的日期
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getAfterMonth(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		return calendar.getTime();
	}

	/**
	 * 功能描述: 数据类型转换 0.0 -> #0.00
	 * 
	 * @param f
	 * @return
	 */
	public static String getTwoDecimal(float f) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
		String new_DCJD = df.format(f);
		return new_DCJD;
	}

	/**
	 * 功能描述: 获取今天年份
	 * 
	 * @return 字符串年份
	 */
	public static String getYear() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String year;
		year = dateString.substring(0, 4);
		return year;
	}

	/**
	 * 功能描述: 获取今天月份
	 * 
	 * @return 字符串月份
	 */
	public static String getNowMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String month;
		month = dateString.substring(5, 7);
		return month;
	}

	/**
	 * 功能描述: 获取今天日数 
	 * @return 字符串日数
	 */
	public static String getNowDay() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String day;
		day = dateString.substring(8, 10);
		return day;
	}
	
	
	/**
	 * 功能描述: 获取本月日期
	 * 
	 * @return 字符串日数
	 */
	public static String getNowYearMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 功能描述: 获取今天日期
	 * 格式:(yyyy-MM-dd HH:mm:ss)
	 * @return 字符串日日期
	 */
	public static String getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * 功能描述：获取本月日期
	 * @param dates String (格式:yyyy-MM-dd)
	 * @return List<Date> 传入格式有错返回为空
	 */
	public static List<Date> getMonthDays(String dates)
	{
		List<Date> days = null;
		
		dates = dates.substring(0, 4)+"-"+dates.substring(5, 7)+"-"+01;
		
		//获取前面3个月的第1个月日期
		Date date = StringToDate(dates, "yyyy-MM-dd");
		Date date_start  = ConvertDate.getAfterMonth(date, 0); //月开始
		
		String date_start_s =  DateToString(date_start, "yyyyMMdd");
		int monthNum = ConvertDate.getMonthNum(date_start);
		String date_end_s =  date_start_s.substring(0, 4) + "-" + date_start_s.substring(4, 6) + "-" + monthNum;
		Date date_end  = StringToDate(date_end_s, "yyyy-MM-dd");//月结束
		
		days = ConvertDate.getBetweenDate(date_start, date_end);
		
		return days;
	}
	
	/**
	 * 功能描述:查看一个时间是否在两个时间之内
	 * @param kssj 开始时间
	 * @param jssj 结束时间
	 * @param time 时间
	 * @return false:不在 true：在
	 */
	public static boolean getTimeInOrOutTime(String kssj,String jssj,String time)
	{
		boolean check = true;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calBegin = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		Calendar calNow = Calendar.getInstance();
		Date beginDate = null;
		Date endDate = null;
		Date nowDate = null;
		try {
			beginDate = sdf.parse(kssj);
			endDate = sdf.parse(jssj);
			nowDate = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calBegin.setTime(beginDate);
		calEnd.setTime(endDate);
		calNow.setTime(nowDate);
		if(calBegin.after(calNow)){
			//不在时间内
            check = false;
		}
		if(calEnd.before(calNow)){
			//不在时间内
			check = false;
		}
		
		return check;
	}
	
	
	/**
	 * 功能描述：得到指定日期n月后的日期
	 * @param dates 
	 * @param i 
	 * @return 
	 */
	public static String getAfterMonth(String dates,int i)
	{
		String d = DateToString( getAfterMonth( StringToDate(dates,"yyyy-MM-dd") ,i) , "yyyy-MM-dd");
		return  d ;
	}
	
	public static void main(String[] args) {
		
	}
}
	
