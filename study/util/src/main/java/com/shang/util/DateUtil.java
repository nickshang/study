package com.shang.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * ????????:?????????
 * 
 * @author NICK
 * 
 */
public class DateUtil {
	
	/**
	 * ??????????????????????????????
	 * @param kssj ??????
	 * @param jssj ??????
	 * @return long ??
	 */
	public long getBetweenDateSeconds(String kssj,String jssj)
	{
		Date d1 = StringToDate(kssj, "yyyy-MM-dd HH:mm:ss");
		Date d2 = StringToDate(jssj, "yyyy-MM-dd HH:mm:ss");
	
		return ( d2.getTime()-  d1.getTime() )/1000 ;
	}

	/**
	 * ????????:??????????
	 * @return ???
	 */
	public static Date getServerTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
	
	

	/**
	 * ????????:????????????
	 * @return ??
	 */
	public static long getCurrSeconds(){
	
		long second = DateUtil.getSeconds( DateUtil.DateToString(DateUtil.getServerTime(), "yyyy-MM-dd HH:mm:ss"));
		return second;		
	}
	
	
	/**
	 * ????????:????????(??1970-01-01????)??????
	 * @param sdate ???:(yyyy-MM-dd HH:mm:ss")
	 * @return
	 */
	public static long getSeconds(String sdate) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		try {
			date1 = sdf.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Calendar calendar1 = Calendar.getInstance(Locale.CHINA);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);

		Date date2 =  StringToDate("1970-01-01", "yyyy-MM-dd");
		//Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		
		return   calendar1.getTimeInMillis()/1000L - calendar2.getTimeInMillis()/1000L  ;
	}
	
	
	/**
	 * ????????:??(??1970-01-01????)?????????????
	 * @param seconds  ????
	 * @return ???:(yyyy-MM-dd HH:mm:ss)
	 */
	public static String getDates(long seconds) {
		
		Date date1 =  StringToDate("1970-01-01", "yyyy-MM-dd");
		//Calendar calendar1 = Calendar.getInstance(Locale.CHINA);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		
		//Calendar calendar3 = Calendar.getInstance(Locale.CHINA);
		Calendar calendar3 = Calendar.getInstance();
		calendar3.setTimeInMillis( seconds*1000L +   calendar1.getTimeInMillis()  );
	
		return   DateToString(calendar3.getTime(), "yyyy-MM-dd HH:mm:ss")  ;
	}

	/**
	 * ????????:?????????????????? (yyyy-MM-dd HH:mm:ss)
	 * @param date  ????????
	 * @param str   ???
	 * @return ???????? ??????(1900-00-00)
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
	 * ????????:?????????????????date????
	 * @param str   ?????
	 * @param df  ???
	 * @return ???
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
	 * ????????: ???????????????????????????
	 * 
	 * @param date1
	 *            ???1
	 * @param date2
	 *            ???1
	 * @return ????
	 */
	public static int getDateNum(Date date1, Date date2) {
		return Math
				.abs((int) ((date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24));
	}

	/**
	 * ????????: ??????????????????????
	 * 
	 * @param sd1
	 *            ???1
	 * @param sd2
	 *            ???1
	 * @param df
	 *            ???
	 * @return ????
	 */
	public static int getDateNum(String sd1, String sd2, String df) {
		Date date1 = StringToDate(sd1, df);
		Date date2 = StringToDate(sd2, df);
		return getDateNum(date1, date2);
	}
	


	/**
	 * ????????:??????????????????????????
	 * 
	 * @param sd1
	 *            ???1
	 * @param sd2
	 *            ???2
	 * @param df
	 *            ???
	 * @param df2
	 *            ???
	 * @return ?????????
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
	 * ????????: ?????????????????????????????
	 * @param date1 ???1
	 * @param date2 ???2
	 * @return ?????????
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
	 * ????????: ???????????
	 * 
	 * @param date
	 *            ???
	 * @return ????
	 */
	public static int getRsYearNum(Date date) throws Exception {
		String year = DateToString(date, "yyyy");
		Date dateLast = StringToDate(year + "-12-31", "yyyy-MM-dd");
		return getDateNum(date, dateLast);
	}

	/**
	 * ????????: ????????
	 * 
	 * @param date
	 *            ???
	 * @return ????
	 */
	public static int getMonthNum(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +1);
		Date date2 = calendar.getTime();
		return getDateNum(date, date2);
	}

	/**
	 * ????????:???????????
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
	 * ????????: ???????????
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
	 * ????????: ???????n????????
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
	 * ????????: ??????????n?o??????
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
	 * ????????: ??????????? 0.0 -> #0.00
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
	 * ????????: ??????????
	 * 
	 * @return ????????
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
	 * ????????: ???????????
	 * 
	 * @return ?????????
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
	 * ????????: ??????????? 
	 * @return ?????????
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
	 * ????????: ???????????
	 * 
	 * @return ?????????
	 */
	public static String getNowYearMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * ????????: ???????????
	 * ???:(yyyy-MM-dd HH:mm:ss)
	 * @return ???????????
	 */
	public static String getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	
	/**
	 * ?????????????????????
	 * @param dates String (???:yyyy-MM-dd)
	 * @return List<Date> ???????????????
	 */
	public static List getMonthDays(String dates)
	{
		List days = null;
		
		dates = dates.substring(0, 4)+"-"+dates.substring(5, 7)+"-"+01;
		
		//??????3???????1????????
		Date date = StringToDate(dates, "yyyy-MM-dd");
		Date date_start  = DateUtil.getAfterMonth(date, 0); //????
		
		String date_start_s =  DateToString(date_start, "yyyyMMdd");
		int monthNum = DateUtil.getMonthNum(date_start);
		String date_end_s =  date_start_s.substring(0, 4) + "-" + date_start_s.substring(4, 6) + "-" + monthNum;
		Date date_end  = StringToDate(date_end_s, "yyyy-MM-dd");//?????
		
		days = DateUtil.getBetweenDate(date_start, date_end);
		
		return days;
	}
	
	
	/**
	 * ????????:???????????????????????
	 * @param kssj ??????
	 * @param jssj ???????
	 * @param time ???
	 * @return false:???? true????
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
			//?????????
            check = false;
		}
		if(calEnd.before(calNow)){
			//?????????
			check = false;

		}

		return check;
	}

	public static void main(String[] args) {
		
		System.out.println( getDateNum( "1970-01-01", "2015-02-28 ", "yyyy-MM-dd") ); 
	}
}
	
