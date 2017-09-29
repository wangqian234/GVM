package com.dt.util;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author LiuChiJun
 * 
 */

public class DateUtil {

	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_SN_DATE = "yyyyMMdd";
	public static Date getSomeDate(int i){
		 Calendar c = Calendar.getInstance(); 
		 c.add(Calendar.DATE, i);
		 return c.getTime(); 
		}
	public static String timestamp2String(Timestamp timestamp, String pattern) {
		if (timestamp == null) {
			throw new java.lang.IllegalArgumentException(
					"timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(timestamp.getTime()));
	}

	public static String date2String(Date date, String pattern) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException(
					"timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
			;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static String currentTimestamp2String(String pattern) {
		return timestamp2String(currentTimestamp(), pattern);
	}

	public static Timestamp string2Timestamp(String strDateTime, String pattern) {
		if (strDateTime == null || strDateTime.equals("")) {
			throw new java.lang.IllegalArgumentException(
					"Date Time Null Illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(strDateTime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return new Timestamp(date.getTime());
	}

	public static Date string2Date(String strDate, String pattern) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = DateUtil.PATTERN_DATE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;

		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	public static String stringToYear(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return String.valueOf(c.get(Calendar.YEAR));
	}

	public static String stringToMonth(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// return String.valueOf(c.get(Calendar.MONTH));
		int month = c.get(Calendar.MONTH);
		month = month + 1;
		if (month < 10) {
			return "0" + month;
		}
		return String.valueOf(month);
	}

	public static String stringToDay(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			return "0" + day;
		}
		return "" + day;
	}

	public static Date getFirstDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
		c.set(year, month, day, 0, 0, 0);
		return c.getTime();
	}

	public static Date getLastDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = 1;
		if (month > 11) {
			month = 0;
			year = year + 1;
		}
		c.set(year, month, day - 1, 0, 0, 0);
		return c.getTime();
	}

	public static String date2GregorianCalendarString(Date date) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("Date is null");
		}
		long tmp = date.getTime();
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTimeInMillis(tmp);
		try {
			XMLGregorianCalendar t_XMLGregorianCalendar = DatatypeFactory
					.newInstance().newXMLGregorianCalendar(ca);
			return t_XMLGregorianCalendar.normalize().toString();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw new java.lang.IllegalArgumentException("Date is null");
		}

	}

	public static boolean compareDate(Date firstDate, Date secondDate) {
		if (firstDate == null || secondDate == null) {
			throw new java.lang.RuntimeException();
		}

		String strFirstDate = date2String(firstDate, "yyyy-MM-dd");
		String strSecondDate = date2String(secondDate, "yyyy-MM-dd");
		if (strFirstDate.equals(strSecondDate)) {
			return true;
		}
		return false;
	}

	public static Date firstOfQuarter(Date date) {
		if (date == null) {
			throw new java.lang.RuntimeException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (calendar.get(Calendar.MONTH)) {
		case 0:
		case 1:
		case 2:
			calendar.set(Calendar.MONTH, 0);
			break;
		case 3:
		case 4:
		case 5:
			calendar.set(Calendar.MONTH, 3);
			break;
		case 6:
		case 7:
		case 8:
			calendar.set(Calendar.MONTH, 6);
			break;
		case 9:
		case 10:
		case 11:
			calendar.set(Calendar.MONTH, 9);
			break;
		}

		calendar.set(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(df.format(calendar.getTime()));
		} catch (ParseException e) {
			throw new java.lang.RuntimeException();
		}
	}

	public static Date lastOfQuarter(Date date) {
		Date firstDate = firstOfQuarter(date);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 3);

		return calendar.getTime();
	}

	public static Date getLastDayOfMonth(Date date) {
		if (date == null) {
			throw new java.lang.RuntimeException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public static Date getDateAfterMonth(Date date, int amount) {
		if (date == null) {
			throw new java.lang.RuntimeException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, amount);
		return calendar.getTime();
	}

	/**
	 * 用默认的格式字符串获得当前日期的字符串
	 * 
	 * @param date
	 *            要格式化的Date.
	 * @param formatString
	 *            格式字符串.
	 * @return 格式化好的日期字符串.
	 */
	public static String getToday() {
		return getDateStringByFormat(new Date(), PATTERN_DATE);
	}

	/**
	 * 根据默认的格式字符串格式化Date
	 * 
	 * @param date
	 *            要格式化的Date.
	 * @param formatString
	 *            格式字符串.
	 * @return 格式化好的日期字符串.
	 */
	public static String getDateStringByFormat(Date date) {
		return getDateStringByFormat(date, PATTERN_DATE);
	}

	/**
	 * 根据指定的格式字符串格式化Date
	 * 
	 * @param date
	 *            要格式化的Date.
	 * @param formatString
	 *            格式字符串.
	 * @return 格式化好的日期字符串.
	 */
	public static String getDateStringByFormat(Date date, String formatString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		return dateFormat.format(date);
	}

	/**
	 * 用默认的格式字符串和指定的Date字符串来取得Date
	 * 
	 * @param dateString
	 *            Date字符串.
	 * @return 格式化好的Date.
	 */
	public static Date getDateByFormat(String dateString) throws ParseException {
		return getDateByFormat(dateString, PATTERN_DATE);
	}

	/**
	 * 根据指定的格式字符串和Date字符串来取得Date
	 * 
	 * @param dateString
	 *            Date字符串.
	 * @param formatString
	 *            格式字符串.
	 * @return 格式化好的Date.
	 */
	public static Date getDateByFormat(String dateString, String formatString)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
		return dateFormat.parse(dateString);
	}

	/**
	 * 将数字格式化为符合流水号格式的字符串
	 * 
	 * @param num
	 *            要格式化的数字.
	 * @return 格式化好的字符串.
	 */
	public static String getSNFormat(int num) {
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(3);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(3);
		// 输出测试语句
		return nf.format(num);
	}

	/**
	 * 生一个32为的UUID，作为数据库的主键.
	 * 
	 * @return UUID字符串.
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	/**
	 * 把日期字符串转换为符合数据库存储格式的Date
	 * 
	 * @param dateString
	 *            日期字符串.
	 * @return Date.
	 */
	public static java.sql.Date getSQLDate(String dateString) {
		return java.sql.Date.valueOf(dateString);
	}

	/**
	 * 将数据库中得到的timestamp格式化为YYYY-MM-DD hh:mm:ss形式
	 * 
	 * @param timestamp
	 *            Timestamp值
	 * @return 格式化好的timestamp.
	 * @throws ParseException
	 */
	public static String getFormatTimestamp(Timestamp ts) throws ParseException {
		return getDateStringByFormat(ts, PATTERN_STANDARD);
	}

	public static void main(String[] args) {
		// System.out.println(stringToYear("2007-12-10"));
		// System.out.println(stringToMonth("2007-1-10"));
		// System.out.println(stringToDay("2007-12-10"));
		// System.out.println(string2Date("2007-09-11",TangCEDateUtil.PATTERN_DATE));
		// Calendar c = Calendar.getInstance();
		// System.out.println(c.(Calendar.DAY_OF_MONTH));
		// System.out.println(c.getMaximum(Calendar.DAY_OF_MONTH));
		// System.out.println(getFirstDayOfMonth(c));
		// System.out.println(getLastDayOfMonth(c));
		// String pattern =
		// String tmp = System.getProperty("file.separator");
		// String pattern = "yyyy" + tmp + "MM" + tmp + "dd";
		// System.out.println(DateUtil.date2String(new java.util.Date(),
		// pattern));
		// System.out.println(date2GregorianCalendarString(new Date()));;

		// System.out.println(DateUtil.date2String(new java.util.Date(), "hh"));
		// System.out.println(DateUtil.date2String(new Date(), "ss"));
		System.out.println(date2String(new Date(), "yyyy/MM/dd/"));
		System.out.println(date2GregorianCalendarString(new Date()));
	}
	
	/**
	 * 英文简写（默认）如：2010-12-01
	 */
	public static String FORMAT_SHORT = "yyyy-MM-dd";
	
	/**
	 * 英文全称  如：2010-12-01 23:15:06
	 */
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
	
	/**
	 * 英文全称  如：20130728111506
	 */
	public static String FORMAT_EASY = "yyyyMMddHHmmss";
	public static String FORMAT_EASY_E = "yyMMdd";
	/**
	 * 汉字加年月
	 */
	public static String FORMAT_CHINA = "yyyy年MM月dd日";
	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return FORMAT_LONG;
	}

	/**
	 * 根据预设格式返回当前日期
	 * @return
	 */
	public static String getNow() {
		return format(new Date());
	}
	
	/**
	 * 根据用户格式返回当前日期
	 * @param format
	 * @return
	 */
	public static String getNow(String format) {
		return format(new Date(), format);
	}

	
	/**
	 * 使用预设格式格式化日期
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	/**
	 * 使用用户格式格式化日期
	 * @param date 日期
	 * @param pattern 日期格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return (returnValue);
	}

	/**
	 * 使用预设格式提取字符串日期
	 * @param strDate 日期字符串
	 * @return
	 */
	public static Date parse(String strDate) {
		return parse(strDate, getDatePattern());
	}

	/**
	 * 使用用户格式提取字符串日期
	 * @param strDate 日期字符串
	 * @param pattern 日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

    /**
     * 计算当月的天数
     * @return
     */
    public static int getCurrentMonthLastDay(Date date)
    {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
	/**
	 * 在日期上增加数个整月
	 * @param date 日期
	 * @param n 要增加的月数
	 * @return
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加天数
	 * @param date 日期
	 * @param n 要增加的天数
	 * @return
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
	 * 获取时间戳
	 */
	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 获取日期年份
	 * @param date 日期
	 * @return
	 */
	public static String getYear(Date date) {
		return format(date).substring(0, 4);
	}
	
	/**
	 * 按默认格式的字符串距离今天的天数
	 * @param date 日期字符串
	 * @return
	 */
	public static int countDays (String date) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date));
		long t1 = c.getTime().getTime();
		return (int)(t/1000 - t1/1000)/3600/24;
	}
	/**
	 * 按默认格式的字符串距离今天的差几个月
	 * @param date
	 * @return
	 */
	public static int countMonth (String date) {
		//把当前日期换成当月的一号
		Calendar t = Calendar.getInstance();
        t.set(Calendar.DATE,1);
        t.set(Calendar.HOUR_OF_DAY,0);
        t.set(Calendar.MINUTE,0);
        t.set(Calendar.SECOND,0);
        long t2=t.getTime().getTime(); 
        
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date));
		c.set(Calendar.DATE,1);
	    c.set(Calendar.HOUR_OF_DAY,0);
	    c.set(Calendar.MINUTE,0);
	    c.set(Calendar.SECOND,0);
		long t1 = c.getTime().getTime();
		return (int)(t2/1000 - t1/1000)/3600/24/30;
	}
	/**
	 * 按用户格式字符串距离今天的天数
	 * @param date 日期字符串
	 * @param format 日期格式
	 * @return
	 */
	public static int countDays (String date, String format) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date, format));
		long t1 = c.getTime().getTime();
		return (int)(t/1000 - t1/1000)/3600/24;
	}
	/**
	 * 计算小时差
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static double jisuan(String date1, String date2) throws Exception {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date start = sdf.parse(date1);
		java.util.Date end = sdf.parse(date2);
		long cha = end.getTime() - start.getTime();
		double result = cha * 1.0 / (1000 * 60 * 60);
		
		return result;
	}
	/**
	 * 日期向后，前推n年，n月，n天,n时，n分，n秒
	 * @param date : 需要处理的日期
	 * @param num : 处理的数量,+为向后退，-为向前推
	 * @param type ： 类型：年-Y,月-M,日-D,时-H,分-MIN,秒-S
	 * @return
	 */
	public static Date addDate(Date date,int num,String type)  {
		try {
			Calendar calendar = new GregorianCalendar(); 
			calendar.setTime(date); 
			if(type.equals("Y")) {
				calendar.add(Calendar.YEAR,num);//把日期往后增加n年.整数往后推,负数往前移动 
			}
			else if(type.equals("M")) {
				calendar.add(Calendar.MONTH,num);//把日期往后增加n月.整数往后推,负数往前移动 
			}
			else if(type.equals("D")) {
				calendar.add(Calendar.DATE,num);//把日期往后增加n天.整数往后推,负数往前移动 
			}
			else if(type.equals("H")) {
				calendar.add(Calendar.HOUR,num);//把日期往后增加n小时.整数往后推,负数往前移动 
			}
			else if(type.equals("MIN")) {
				calendar.add(Calendar.MINUTE,num);//把日期往后增加n分.整数往后推,负数往前移动 
			}
			else if(type.equals("S")) {
				calendar.add(Calendar.SECOND,num);//把日期往后增加n秒.整数往后推,负数往前移动 
			}
			
			date = calendar.getTime();   //这个时间就是日期往后推一天的结果 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	

	public static Date toDate(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(d != null){
			Date date = sdf.parse(d);
			return date;
		}
		return null;
	}
	
	public static Date toDateTime(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(d != null){
			Date date = sdf.parse(d);
			return date;
		}
		return null;
	}
	
	public static Date toDateHour(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		if(d != null){
			Date date = sdf.parse(d);
			return date;
		}
		return null;
	}
	
	public static Date toDateMin(String d) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(d != null){
			Date date = sdf.parse(d);
			return date;
		}
		return null;
	}
	
	/**
	 * 获取月份
	 * @author 孙东泉
	 * 2014-2-10 上午10:28:46
	 */
	public static Date getMonth(String yearMonth){
		String year = yearMonth.substring(0, 3);
		String month = yearMonth.substring(5, 6);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), Calendar.YEAR);
		calendar.set(Integer.parseInt(month), Calendar.MONTH);
		return calendar.getTime();
	}
	
	/**
	 * 获取本周的周一和周日
	 */
	public static Map getWeekDay() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("mon", df.format(cal.getTime()));
		// System.out.println("********得到本周一的日期*******" + df.format(cal.getTime()));
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("sun", df.format(cal.getTime()));
		// System.out.println("********得到本周天的日期*******" + df.format(cal.getTime()));
		return map;
	}
	
}