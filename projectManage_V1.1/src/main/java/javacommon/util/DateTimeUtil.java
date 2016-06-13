package javacommon.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期时间工具类，进行各种日期时间格式的转化以及格式化 。
 * 
 * @author wangzg
 * 
 */
public class DateTimeUtil
{
	public final static int TIME_DAY_MILLISECOND = 86400000;

	// 定义时间日期显示格式
	public final static String DATE_FORMAT = "yyyy-MM-dd";

	public final static String DATE_FORMAT_CN = "yyyy年MM月dd日";

	public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public final static String TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";

	public final static String MONTH_FORMAT = "yyyy-MM";

	public final static String DAY_FORMAT = "yyyyMMdd";

	public final static String TIMES_DESC_FORMAT = "yyyyMMddHHmmssSSS";
	
	public static final String strSimple = "yyyy-MM-dd";

	@SuppressWarnings("unused")
	private static final Object[] arrayDate = null;

	/**
	 * 取得当前系统时间，返回java.util.Date类型。
	 * 
	 * @see java.util.Date
	 * @return java.util.Date 返回服务器当前系统时间
	 */
	public static java.util.Date getCurrDate()
	{
		return new java.util.Date();
	}

	/**
	 * 取得当前系统时间戳。
	 * 
	 * @see java.sql.Timestamp
	 * @return java.sql.Timestamp 系统时间戳
	 */
	public static java.sql.Timestamp getCurrTimestamp()
	{
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	/**
	 * 从时间串中获取小时数。
	 * 
	 * @param strTimestr "2007-10-12 13:25:00"
	 * @return
	 */
	public static int getHourFromTimeString(String strTimestr)
	{
		if (StringUtils.isBlank(strTimestr))
		{
			return 0;
		}

		return Integer.parseInt(strTimestr.substring(strTimestr.length() - 8, strTimestr.length() - 6));
	}

	/**
	 * 返回当前时间是上午还是下午。
	 * 
	 * @see Calendar.AM 0
	 * @see Calendar.PM 1
	 * @author lenghao
	 * @createTime 2008-8-2 下午04:22:07
	 * @return
	 */
	public static Integer getCurrDateAMorPM()
	{
		Calendar objCalendar = Calendar.getInstance();
		
		return objCalendar.get(Calendar.AM_PM);
	}

	/**
	 * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15。
	 * 
	 * @param objCurrDate 要格式化的日期
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2006-02-15
	 */
	public static String getFormatDate(java.util.Date objCurrDate)
	{
		return getFormatDate(objCurrDate, DATE_FORMAT);
	}

	/**
	 * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15。
	 * 
	 * @param objCurrDate 要格式化的日期
	 * @see #getFormatDate(java.util.Date)
	 * @return Date 返回格式化后的日期，默认格式为为yyyy-MM-dd，如2006-02-15
	 */
	public static Date getFormatDateToDate(java.util.Date objCurrDate)
	{
		return getFormatDate(getFormatDate(objCurrDate));
	}

	/**
	 * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日。。
	 * 
	 * @param objCurrDate 要格式化的日期
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日。
	 */
	public static String getFormatDate_CN(java.util.Date objCurrDate)
	{
		return getFormatDate(objCurrDate, DATE_FORMAT_CN);
	}

	/**
	 * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日。
	 * 
	 * @param objCurrDate 要格式化的日期
	 * @see #getFormatDate_CN(String)
	 * @return Date 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日。
	 */
	public static Date getFormatDateToDate_CN(java.util.Date objCurrDate)
	{
		return getFormatDate_CN(getFormatDate_CN(objCurrDate));
	}

	/**
	 * 得到格式化后的日期，格式为yyyyMMddHHmmssss，如20111122090212222。
	 * 
	 * @param objCurrDate 要格式化的日期
	 */
	public static String getFormatDateTimesString(java.util.Date objCurrDate)
	{
		return getFormatDate(objCurrDate, TIMES_DESC_FORMAT);
	}

	/**
	 * 得到格式化后的日期，格式为yyyy-MM-dd，如2006-02-15。
	 * 
	 * @param strCurrDate 要格式化的日期
	 * @see #getFormatDate(String, String)
	 * @return Date 返回格式化后的日期，默认格式为yyyy-MM-dd，如2006-02-15
	 */
	public static Date getFormatDate(String strCurrDate)
	{
		return getFormatDate(strCurrDate, DATE_FORMAT);
	}

	/**
	 * 得到格式化后的日期，格式为yyyyMMdd，如20060215。
	 * 
	 * @param strCurrDate 要格式化的日期
	 * @see #getFormatDate(String, String)
	 * @return Date 返回格式化后的日期，默认格式为yyyy-MM-dd，如2006-02-15
	 */
	public static Date getFormatDay(String strCurrDate)
	{
		return getFormatDate(strCurrDate, DAY_FORMAT);
	}

	/**
	 * 得到格式化后的日期，格式为yyyy年MM月dd日，如2006年02月15日。
	 * 
	 * @param strCurrDate 要格式化的日期
	 * @see #getFormatDate(String, String)
	 * @return 返回格式化后的日期，默认格式为yyyy年MM月dd日，如2006年02月15日。
	 */
	public static Date getFormatDate_CN(String strCurrDate)
	{
		return getFormatDate(strCurrDate, DATE_FORMAT_CN);
	}

	/**
	 * 根据格式得到格式化后的日期。
	 * 
	 * @param objCurrDate 要格式化的日期
	 * @param strFormat 日期格式，如yyyy-MM-dd
	 * @see java.text.SimpleDateFormat#format(java.util.Date)
	 * @return String 返回格式化后的日期，格式由参数<code>format</code>
	 *         定义，如yyyy-MM-dd，如2006-02-15
	 */
	public static String getFormatDate(java.util.Date objCurrDate, String strFormat)
	{
		SimpleDateFormat objDtFormatdB;

		if (objCurrDate == null)
		{
			return "";
		}
		objDtFormatdB = null;

		try
		{
			objDtFormatdB = new SimpleDateFormat(strFormat);

			return objDtFormatdB.format(objCurrDate);
		}
		catch (Exception e)
		{
			objDtFormatdB = new SimpleDateFormat(DATE_FORMAT);

			try
			{
				return objDtFormatdB.format(objCurrDate);
			}
			catch (Exception ex)
			{
			}
		}
		return null;
	}

	/**
	 * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45。
	 * 
	 * @param objCurrDate 要格式化的时间
	 * @see #getFormatDateTime(java.util.Date, String)
	 * @return String 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
	 */
	public static String getFormatDateTime(java.util.Date objCurrDate)
	{
		return getFormatDateTime(objCurrDate, TIME_FORMAT);
	}

	/**
	 * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45。
	 * 
	 * @param objCurrDate 要格式环的时间
	 * @see #getFormatDateTime(String)
	 * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
	 */
	public static Date getFormatDateTimeToTime(java.util.Date objCurrDate)
	{
		return getFormatDateTime(getFormatDateTime(objCurrDate));
	}

	/**
	 * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45。
	 * 
	 * @param strCurrDate 要格式化的时间
	 * @see #getFormatDateTime(String, String)
	 * @return Date 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
	 */
	public static Date getFormatDateTime(String strCurrDate)
	{
		return getFormatDateTime(strCurrDate, TIME_FORMAT);
	}

	/**
	 * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日。
	 * 
	 * @param objCurrDate 要格式化的时间
	 * @see #getFormatDateTime(java.util.Date, String)
	 * @return String 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日。 15:23:45
	 */
	public static String getFormatDateTime_CN(java.util.Date objCurrDate)
	{
		return getFormatDateTime(objCurrDate, TIME_FORMAT_CN);
	}

	/**
	 * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日。 15:23:45。
	 * 
	 * @param objCurrDate 要格式化的时间
	 * @see #getFormatDateTime_CN(String)
	 * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日。 15:23:45
	 */
	public static Date getFormatDateTimeToTime_CN(java.util.Date objCurrDate)
	{
		return getFormatDateTime_CN(getFormatDateTime_CN(objCurrDate));
	}

	/**
	 * 得到格式化后的时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日。 15:23:45。
	 * 
	 * @param strCurrDate 要格式化的时间
	 * @see #getFormatDateTime(String, String)
	 * @return Date 返回格式化后的时间，默认格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日。 15:23:45
	 */
	public static Date getFormatDateTime_CN(String strCurrDate)
	{
		return getFormatDateTime(strCurrDate, TIME_FORMAT_CN);
	}

	/**
	 * 根据格式得到格式化后的时间。
	 * 
	 * @param objCurrDate 要格式化的时间
	 * @param strFormat 时间格式，如yyyy-MM-dd HH:mm:ss
	 * @see java.text.SimpleDateFormat#format(java.util.Date)
	 * @return String 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormatDateTime(java.util.Date objCurrDate, String strFormat)
	{
		SimpleDateFormat objFormatdB = null;
		
		if (objCurrDate == null)
		{
			return "";
		}
		try
		{
			objFormatdB = new SimpleDateFormat(strFormat);
			
			return objFormatdB.format(objCurrDate);
		}
		catch (Exception e)
		{
			objFormatdB = new SimpleDateFormat(DATE_FORMAT);
			
			try
			{
				return objFormatdB.format(objCurrDate);
			}
			catch (Exception ex)
			{
			}
		}
		return "";
	}

	/**
	 * 根据格式得到格式化后的日期。
	 * 
	 * @param strCurrDate 要格式化的日期
	 * @param strFormat 日期格式，如yyyy-MM-dd
	 * @see java.text.SimpleDateFormat#parse(java.lang.String)
	 * @return Date 返回格式化后的日期，格式由参数<code>format</code>
	 *         定义，如yyyy-MM-dd，如2006-02-15
	 */
	public static Date getFormatDate(String strCurrDate, String strFormat)
	{
		SimpleDateFormat objFormatdB = null;

		if (strCurrDate == null)
		{
			return null;
		}
		try
		{
			objFormatdB = new SimpleDateFormat(strFormat);

			return objFormatdB.parse(strCurrDate);
		}
		catch (Exception e)
		{
			objFormatdB = new SimpleDateFormat(DATE_FORMAT);

			try
			{
				return objFormatdB.parse(strCurrDate);
			}
			catch (Exception ex)
			{
			}
		}
		return null;
	}

	/**
	 * 根据格式得到格式化后的时间。
	 * 
	 * @param strCurrDate 要格式化的时间
	 * @param strFormat 时间格式，如yyyy-MM-dd HH:mm:ss
	 * @see java.text.SimpleDateFormat#parse(java.lang.String)
	 * @return Date 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd HH:mm:ss
	 */
	public static Date getFormatDateTime(String strCurrDate, String strFormat)
	{
		SimpleDateFormat objDtFormatdB = null;

		if (strCurrDate == null)
		{
			return null;
		}
		try
		{
			objDtFormatdB = new SimpleDateFormat(strFormat);

			return objDtFormatdB.parse(strCurrDate);
		}
		catch (Exception e)
		{
			objDtFormatdB = new SimpleDateFormat(TIME_FORMAT);

			try
			{
				return objDtFormatdB.parse(strCurrDate);
			}
			catch (Exception ex)
			{
			}
		}
		return null;
	}

	/**
	 * 得到本日的上月时间 如果当日为2007-9-1,那么获得2007-8-1。
	 */
	public static String getDateBeforeMonth()
	{
		Calendar objCal = Calendar.getInstance();

		objCal.add(Calendar.MONTH, -1);
		return getFormatDate(objCal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到本日的前几个月时间 如果number=2当日为2007-9-1,那么获得2007-7-1。
	 */
	public static String getDateBeforeMonth(int nNumber)
	{
		Calendar objCal = Calendar.getInstance();

		objCal.add(Calendar.MONTH, - nNumber);
		return getFormatDate(objCal.getTime(), DATE_FORMAT);
	}

	/**
	 * getDaysOfDates。
	 * @param objFirst
	 * @param objSecond
	 * @return
	 */
	public static long getDaysOfDates(Date objFirst, Date objSecond)
	{
		Date objD1 = getFormatDateTime(getFormatDate(objFirst), DATE_FORMAT);
		Date objD2 = getFormatDateTime(getFormatDate(objSecond), DATE_FORMAT);

		long lMils = objD1.getTime() - objD2.getTime();

		return lMils / (TIME_DAY_MILLISECOND);
	}

	/**
	 * 获得两个Date型日期之间相差的天数（第2个减第1个）。
	 * 
	 * @param Date first, Date second
	 * @return int 相差的天数
	 */
	public static int getDaysBetweenDates(Date objFirst, Date objSecond)
	{
		Date objD1 = getFormatDateTime(getFormatDate(objFirst), DATE_FORMAT);
		Date objD2 = getFormatDateTime(getFormatDate(objSecond), DATE_FORMAT);

		Long lMils = (objD2.getTime() - objD1.getTime()) / (TIME_DAY_MILLISECOND);

		return lMils.intValue();
	}

	/**
	 * 获得两个String型日期之间相差的天数（第2个减第1个）。
	 * 
	 * @param String first, String second
	 * @return int 相差的天数
	 */
	public static int getDaysBetweenDates(String strFirst, String strSecond)
	{
		Date objD1 = getFormatDateTime(strFirst, DATE_FORMAT);
		Date objD2 = getFormatDateTime(strSecond, DATE_FORMAT);

		Long lMils = (objD2.getTime() - objD1.getTime()) / (TIME_DAY_MILLISECOND);

		return lMils.intValue();
	}

	/**
	 * @author lenghao。
	 * @createTime 2008-8-5 下午01:57:09
	 * @param objFirst
	 * @param objSecond
	 * @return 获取两个Date之间的天数的列表
	 */
	public static List<Date> getDaysListBetweenDates(Date objFirst, Date objSecond)
	{
		List<Date> lstDateList = new ArrayList<Date>();

		Date objD1 = getFormatDateTime(getFormatDate(objFirst), DATE_FORMAT);
		Date objD2 = getFormatDateTime(getFormatDate(objSecond), DATE_FORMAT);

		if (objD1.compareTo(objD2) > 0)
		{
			return lstDateList;
		}
		do
		{
			lstDateList.add(objD1);
			objD1 = getDateBeforeOrAfter(objD1, 1);
		}
		while (objD1.compareTo(objD2) <= 0);
		return lstDateList;
	}

	/**
	 * getDateBeforeDay。
	 * @return
	 */
	public static String getDateBeforeDay()
	{
		Calendar objCal = Calendar.getInstance();

		objCal.add(Calendar.DAY_OF_YEAR, -1);
		return getFormatDate(objCal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的当前系统日期，格式为yyyy-MM-dd，如2006-02-15。
	 * 
	 * @see #getFormatDate(java.util.Date)
	 * @return String 返回格式化后的当前服务器系统日期，格式为yyyy-MM-dd，如2006-02-15
	 */
	public static String getCurrDateStr()
	{
		return getFormatDate(getCurrDate());
	}

	/**
	 * 得到格式化后的当前系统时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45。
	 * 
	 * @see #getFormatDateTime(java.util.Date)
	 * @return String 返回格式化后的当前服务器系统时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15
	 *         15:23:45
	 */
	public static String getCurrDateTimeStr()
	{
		return getFormatDateTime(getCurrDate());
	}

	/**
	 * 得到格式化后的当前系统日期，格式为yyyy年MM月dd日，如2006年02月15日。
	 * 
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回当前服务器系统日期，格式为yyyy年MM月dd日，如2006年02月15日。
	 */
	public static String getCurrDateStr_CN()
	{
		return getFormatDate(getCurrDate(), DATE_FORMAT_CN);
	}

	/**
	 * 得到格式化后的当前系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日。 15:23:45。
	 * 
	 * @see #getFormatDateTime(java.util.Date, String)
	 * @return String 返回格式化后的当前服务器系统时间，格式为yyyy年MM月dd日 HH:mm:ss，如2006年02月15日。
	 *         15:23:45
	 */
	public static String getCurrDateTimeStr_CN()
	{
		return getFormatDateTime(getCurrDate(), TIME_FORMAT_CN);
	}

	/**
	 * 得到系统当前日期的前或者后几天。
	 * 
	 * @param iDate 如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
	 * @see java.util.Calendar#add(int, int)
	 * @return Date 返回系统当前日期的前或者后几天
	 */
	public static Date getDateBeforeOrAfter(int iDate)
	{
		Calendar objCal = Calendar.getInstance();

		objCal.add(Calendar.DAY_OF_MONTH, iDate);
		return objCal.getTime();
	}

	/**
	 * 得到日期的前或者后几天。
	 * 
	 * @param nDate 如果要获得前几天日期，该参数为负数； 如果要获得后几天日期，该参数为正数
	 * @see java.util.Calendar#add(int, int)
	 * @return Date 返回参数<code>curDate</code>定义日期的前或者后几天
	 */
	public static Date getDateBeforeOrAfter(Date objCurDate, int nDate)
	{
		Calendar objCal = Calendar.getInstance();

		objCal.setTime(objCurDate);
		objCal.add(Calendar.DAY_OF_MONTH, nDate);
		return objCal.getTime();
	}

	/**
	 * 得到格式化后的月份，格式为yyyy-MM，如2006-02。
	 * 
	 * @param objCurrDate 要格式化的日期
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的月份，格式为yyyy-MM，如2006-02
	 */
	public static String getFormatMonth(java.util.Date objCurrDate)
	{
		return getFormatDate(objCurrDate, MONTH_FORMAT);
	}

	/**
	 * 得到格式化后的日，格式为yyyyMMdd，如20060210。
	 * 
	 * @param objCurrDate 要格式化的日期
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的日，格式为yyyyMMdd，如20060210
	 */
	public static String getFormatDay(java.util.Date objCurrDate)
	{
		return getFormatDate(objCurrDate, DAY_FORMAT);
	}

	/**
	 * 得到格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01。
	 * 
	 * @param currDate 要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
	 */
	public static String getFirstDayOfMonth()
	{
		Calendar objCal = Calendar.getInstance();
		int nFirstDay = objCal.getMinimum(Calendar.DAY_OF_MONTH);

		objCal.set(Calendar.DAY_OF_MONTH, nFirstDay);
		return getFormatDate(objCal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的下月第一天，格式为yyyy-MM-dd，如2006-02-01。
	 * 
	 * @param currDate 要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的下月第一天，格式为yyyy-MM-dd，如2006-02-01
	 */
	public static String getFirstDayOfNextMonth()
	{
		Calendar objCal = Calendar.getInstance();
		int nFirstDay;

		objCal.add(Calendar.MONTH, + 1);
		nFirstDay = objCal.getMinimum(Calendar.DAY_OF_MONTH);

		objCal.set(Calendar.DAY_OF_MONTH, nFirstDay);
		return getFormatDate(objCal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01。
	 * 
	 * @param objCurrDate 要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的当月第一天，格式为yyyy-MM-dd，如2006-02-01
	 */
	public static String getFirstDayOfMonth(Date objCurrDate)
	{
		Calendar objCal = Calendar.getInstance();
		int nFirstDay = objCal.getMinimum(Calendar.DAY_OF_MONTH);

		objCal.setTime(objCurrDate);
		objCal.set(Calendar.DAY_OF_MONTH, nFirstDay);

		return getFormatDate(objCal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的当月最后一天，格式为yyyy-MM-dd，如2006-02-28。
	 * 
	 * @param objCurrDate 要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的当月最后一天，格式为yyyy-MM-dd，如2006-02-28
	 */
	public static String getLastDayOfMonth(Date objCurrDate)
	{
		Calendar objCal = Calendar.getInstance();
		int nLastDay = objCal.getActualMaximum(Calendar.DAY_OF_MONTH);

		objCal.setTime(objCurrDate);
		objCal.set(Calendar.DAY_OF_MONTH, nLastDay);
		return getFormatDate(objCal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到格式化后的当月最后一天，格式为yyyy-MM-dd，如2006-02-28。
	 * 
	 * @param currDate 要格式化的日期
	 * @see java.util.Calendar#getMinimum(int)
	 * @see #getFormatDate(java.util.Date, String)
	 * @return String 返回格式化后的当月最后一天，格式为yyyy-MM-dd，如2006-02-28
	 */
	public static String getLastDayOfMonth()
	{
		Calendar objCal = Calendar.getInstance();
		int nLastDay = objCal.getActualMaximum(Calendar.DAY_OF_MONTH);

		objCal.set(Calendar.DAY_OF_MONTH, nLastDay);
		return getFormatDate(objCal.getTime(), DATE_FORMAT);
	}

	/**
	 * 得到日期的前或者后几小时。
	 * 
	 * @param nHour 如果要获得前几小时日期，该参数为负数； 如果要获得后几小时日期，该参数为正数
	 * @see java.util.Calendar#add(int, int)
	 * @return Date 返回参数<code>curDate</code>定义日期的前或者后几小时
	 */
	public static Date getDateBeforeOrAfterHours(Date objCurDate, int nHour)
	{
		Calendar objCal = Calendar.getInstance();

		objCal.setTime(objCurDate);
		objCal.add(Calendar.HOUR_OF_DAY, nHour);
		return objCal.getTime();
	}

	/**
	 * 判断日期是否在当前周内。
	 * 
	 * @param objCurDate
	 * @param objCompareDate
	 * @return
	 */
	public static boolean isSameWeek(Date objCurDate, Date objCompareDate)
	{
		Calendar objCalSun = Calendar.getInstance();
		Calendar objCalNext = Calendar.getInstance();
		Calendar objCalComp = Calendar.getInstance();

		if (objCurDate == null || objCompareDate == null)
		{
			return false;
		}

		objCalSun.setTime(getFormatDateToDate(objCurDate));
		objCalSun.set(Calendar.DAY_OF_WEEK, 1);

		objCalNext.setTime(objCalSun.getTime());
		objCalNext.add(Calendar.DATE, 7);

		objCalComp.setTime(objCompareDate);
		if (objCalComp.after(objCalSun) && objCalComp.before(objCalNext))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 时间查询时,结束时间的 23:59:59。
	 */
	public static String addDateEndfix(String strDatestring)
	{
		if ((strDatestring == null) || strDatestring.equals(""))
		{
			return null;
		}
		return strDatestring + " 23:59:59";
	}

	/**
	 * 返回格式化的日期。
	 * 
	 * @param datePre 格式"yyyy-MM-dd 23:59:59";
	 * @return
	 */
	public static Date getFormatDateEndfix(String strDateStr)
	{
		strDateStr = addDateEndfix(strDateStr);
		return getFormatDateTime(strDateStr);
	}

	/**
	 * 返回格式化的日期。
	 * 
	 * @param strDatePre 格式"yyyy-MM-dd HH:mm:ss";
	 * @return
	 */
	public static Date formatEndTime(String strDatePre)
	{
		String strDate;
		
		if (strDatePre == null)
		{
			return null;
		}
		
		strDate = addDateEndfix(strDatePre);

		return getFormatDateTime(strDate);
	}

	/**
	 * date1加上compday天数以后的日期与当前时间比较，如果大于当前时间返回true，否则false。
	 * 
	 * @param objDate1
	 * @param nCompday
	 * @return
	 */
	public static Boolean compareDay(Date objDate1, int nCompday)
	{
		Date objDateComp;
		Date objNowdate = new Date();

		if (objDate1 == null)
		{
			return false;
		}

		objDateComp = getDateBeforeOrAfter(objDate1, nCompday);

		if (objDateComp.after(objNowdate))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 进行时段格式转换，对于输入的48位的01串，将进行如下操作。
	 * 1.先将输入中每个0变成两个0，每个1变成2个1，形成一个96位的二进制串。 
	 * 2.将上述的96位的二进制串分成3组，每组32位。 3.将每个32位的二进制串转换成一个8位的16进制串。
	 * 4.将3个8位的16进制串合并成一个串，中间以","分割。 
	 */
	public static String convertBinaryTime2Hex(String strTimespan)
	{
		StringBuffer sbufRet = new StringBuffer("");
		String strTmp = "";
		
		if (strTimespan == null || strTimespan.equals(""))
		{
			return "";
		}

		for (int i = 0; i < strTimespan.length(); i++)
		{
			strTmp += strTimespan.charAt(i);
			strTmp += strTimespan.charAt(i);

			if ((i + 1) % 16 == 0)
			{
				Long lLong;
				String strHexStr;

				if (!sbufRet.equals(""))
				{
					sbufRet.append(",");
				}

				lLong = Long.parseLong(strTmp, 2);
				strHexStr = Long.toHexString(lLong);

				if (strHexStr.length() < 8)
				{
					int nLength = strHexStr.length();

					for (int n = 0; n < 8 - nLength; n++)
					{
						strHexStr = "0" + strHexStr;
					}
				}

				sbufRet.append(strHexStr);
				strTmp = "";
			}
		}

		return sbufRet.toString();
	}

	/**
	 * 进行时段格式转换，将输入的26位的2进制串转换成48位的二进制串。
	 */
	public static String convertHexTime2Binary(String strTimespan)
	{
		StringBuffer sbufTmp;
		StringBuffer sbufRet;
		String[] arrayArr;

		if (strTimespan == null || strTimespan.equals(""))
		{
			return "";
		}

		sbufTmp = new StringBuffer("");
		sbufRet = new StringBuffer("");
		arrayArr = strTimespan.split(",");

		for (int i = 0; i < arrayArr.length; i++)
		{
			String strStr = Long.toBinaryString(Long.parseLong(arrayArr[i], 16));

			if (strStr.length() < 32)
			{
				int nLength = strStr.length();

				for (int n = 0; n < 32 - nLength; n++)
				{
					strStr = "0" + strStr;
				}
			}
			sbufTmp.append(strStr);
		}

		for (int i = 0; i < 48; i++)
		{
			sbufRet.append(sbufTmp.charAt(i * 2));
		}

		return sbufRet.toString();
	}

	/**
	 * 进行时段格式转换，将输入的32位的10进制串转换成48位的二进制串。
	 */
	public static String convertDecTime2Binary(String strTimespan)
	{
		StringBuffer sbufTmp;
		StringBuffer sbufRet;
		String[] arrayArr;

		if (strTimespan == null || strTimespan.equals(""))
		{
			return "";
		}

		sbufTmp = new StringBuffer("");
		sbufRet = new StringBuffer("");
		arrayArr = strTimespan.split(",");

		for (int i = 0; i < arrayArr.length; i++)
		{
			String strBinStr = Long.toBinaryString(Long.parseLong(arrayArr[i], 10));

			if (strBinStr.length() < 32)
			{
				int nLength = strBinStr.length();

				for (int n = 0; n < 32 - nLength; n++)
				{
					strBinStr = "0" + strBinStr;
				}
			}
			sbufTmp.append(strBinStr);
		}
		for (int i = 0; i < 48; i++)
		{
			sbufRet.append(sbufTmp.charAt(i * 2));
		}

		return sbufRet.toString();
	}

	/**
	 * 进行时段格式转换，对于输入的48位的01串，将进行如下操作： <li>。
	 * 1.先将输入中每个0变成两个0，每个1变成2个1，形成一个96位的二进制串。 
	 * 2.将上述的96位的二进制串分成3组，每组32位。 将每个32位的二进制串转换成一个10位的10进制串。 
	 * 4.将3个8位的16进制串合并成一个串，中间以","分割。 
	 */
	public static String convertBinaryTime2Dec(String strTimespan)
	{
		StringBuffer sbufSb = new StringBuffer("");
		String strTemp = "";

		if (strTimespan == null || strTimespan.equals(""))
		{
			return "";
		}

		for (int i = 0; i < strTimespan.length(); i++)
		{
			strTemp += strTimespan.charAt(i);
			strTemp += strTimespan.charAt(i);
			if ((i + 1) % 16 == 0)
			{
				Long lLong;
				String strDecStr;

				if (!sbufSb.equals(""))
				{
					sbufSb.append(",");
				}

				lLong = Long.parseLong(strTemp, 2);
				strDecStr = Long.toString(lLong);

				if (strDecStr.length() < 10)
				{
					int nLength = strDecStr.length();

					for (int n = 0; n < 10 - nLength; n++)
					{
						strDecStr = "0" + strDecStr;
					}
				}
				sbufSb.append(strDecStr);
				strTemp = "";
			}
		}

		return sbufSb.toString();
	}

	/**
	 * 计算指定日期+addMonth月+15号 返回格式"2008-02-15"。
	 * 
	 * @param objDate
	 * @param nAddMonth
	 * @param nMonthDay
	 * @return
	 */
	public static String genericSpecdate(Date objDate, int nAddMonth, int nMonthDay)
	{
		Calendar objCal = Calendar.getInstance();

		objCal.setTime(objDate);
		objCal.add(Calendar.MONTH, nAddMonth);
		objCal.set(Calendar.DAY_OF_MONTH, nMonthDay);
		return getFormatDate(objCal.getTime(), DATE_FORMAT);
	}

	/**
	 * 获得以今天为单位若干天以前或以后的日期的标准格式"Wed Feb 20 00:00:00 CST 2008"，是0点0分0秒。
	 * 
	 * @param nIdx
	 * @return
	 */
	public static Date getDateBeforeOrAfterV2(int nIdx)
	{
		return getDateBeforeOrAfter(getFormatDateToDate(getCurrDate()), nIdx);
	}

	/**
	 * 获得给定时间若干秒以前或以后的日期的标准格式。
	 * 
	 * @param objCurDate
	 * @param nSeconds
	 * @return curDate
	 */
	public static Date getSpecifiedDateTimeBySeconds(Date objCurDate, int nSeconds)
	{
		long lTime = (objCurDate.getTime() / 1000) + nSeconds;

		objCurDate.setTime(lTime * 1000);
		return objCurDate;
	}

	/**
	 * 获得给定日期当天23点59分59秒的标准格式。
	 * 
	 * @param objCurDate
	 * @return curDate
	 */
	public static Date getSpecifiedDateTime_235959(Date objCurDate)
	{
		return getSpecifiedDateTimeBySeconds(getFormatDateToDate(objCurDate), 24 * 60 * 60 - 1);
	}

	/**
	 * getSpecifiedDateTime_month。
	 * @param objCurDate
	 * @return
	 */
	public static String getSpecifiedDateTime_month(Date objCurDate)
	{
		return getFormatDateTime(objCurDate, "MM.dd");
	}

	/**
	 * alahan add 20050825 获取传入时间相差的日期。
	 * 
	 * @param objDt 传入日期，可以为空
	 * @param nDiff 需要获取相隔diff天的日期 如果为正则取以后的日期，否则时间往前推
	 * @return
	 */
	public static String getDiffStringDate(Date objDt, int nDiff)
	{
		Calendar objCa = Calendar.getInstance();

		if (objDt == null)
		{
			objCa.setTime(new Date());
		}
		else
		{
			objCa.setTime(objDt);
		}

		objCa.add(Calendar.DATE, nDiff);
		return dtSimpleFormat(objCa.getTime());
	}

	/**
	 * yyyy-MM-dd。
	 * 
	 * @param objDate
	 * @return
	 */
	public static final String dtSimpleFormat(Date objDate)
	{
		if (objDate == null)
		{
			return "";
		}

		return getFormat(strSimple).format(objDate);
	}

	/**
	 * 取得格式。
	 * @param strFormat
	 * @return
	 */
	private static final DateFormat getFormat(String strFormat)
	{
		return new SimpleDateFormat(strFormat);
	}

	/**
	 * 取得多个日期中间隔的最大天数。
	 * 
	 * @author Alvise
	 * @param arrayStartDateAndEndDate
	 * @return
	 */
	public static int maxContinuousDays(Date[][] arrayStartDateAndEndDate)
	{
		int nJ = 0;
		int nMaxDays = 0;
		Date[][] arrayStartDateAndEndDateNew;
		
		for (int i = 0; i < arrayStartDateAndEndDate.length - 1; i++)
		{
			for (int j = 0; j < arrayStartDateAndEndDate.length - i - 1; j++)
			{
				if (DateTimeUtil.getDaysBetweenDates(arrayStartDateAndEndDate[j + 1][0], arrayStartDateAndEndDate[j][0]) > 0)
				{
					Date[] arrayTempDate = arrayStartDateAndEndDate[j];

					arrayStartDateAndEndDate[j] = arrayStartDateAndEndDate[j + 1];
					arrayStartDateAndEndDate[j + 1] = arrayTempDate;
				}
			}
		}

		arrayStartDateAndEndDateNew = new Date[arrayStartDateAndEndDate.length][2];

		for (int i = 0; i < arrayStartDateAndEndDateNew.length; i++)
		{
			if (nJ >= arrayStartDateAndEndDate.length)
			{
				break;
			}
			arrayStartDateAndEndDateNew[i] = arrayStartDateAndEndDate[nJ];

			nJ++;
			while (nJ < arrayStartDateAndEndDate.length)
			{
				if (DateTimeUtil.getDaysBetweenDates(arrayStartDateAndEndDateNew[i][1], arrayStartDateAndEndDate[nJ][0]) > 0)
				{
					break;
				}
				else if (DateTimeUtil.getDaysBetweenDates(arrayStartDateAndEndDateNew[i][1], arrayStartDateAndEndDate[nJ][1]) > 0)
				{
					arrayStartDateAndEndDateNew[i][1] = arrayStartDateAndEndDate[nJ][1];
					nJ++;
				}
				else if (DateTimeUtil.getDaysBetweenDates(arrayStartDateAndEndDateNew[i][1], arrayStartDateAndEndDate[nJ][1]) <= 0)
				{
					nJ++;
				}

			}
		}

		for (int i = 0; i < arrayStartDateAndEndDateNew.length - 1; i++)
		{
			Date objCurEndDate = arrayStartDateAndEndDateNew[i][1];
			Date objNextStartDate = arrayStartDateAndEndDateNew[i + 1][0];
			int nTemDays;
			
			if (objCurEndDate == null || objNextStartDate == null)
			{
				break;
			}

			nTemDays = DateTimeUtil.getDaysBetweenDates(objCurEndDate, objNextStartDate);

			if (nTemDays > nMaxDays)
			{
				nMaxDays = nTemDays;
			}
		}
		return nMaxDays;
	}

	/**
	 * 取得多个日期中间隔的最大天数,这里的参数是用 ","和";"分割的字符字符串例如 "2008-08-03,2008-08-04;"。
	 * 
	 * @author Alvise
	 * @param startDateAndEndDate
	 * @return
	 */
	public static int maxContinuousDays(String strDateStr)
	{
		String[] arraySeDate = strDateStr.split(";");
		Date[][] arrayStartDateAndEndDate = new Date[arraySeDate.length][2];
		String[] arrayTempDate;
		
		for (int i = 0; i < arraySeDate.length; i++)
		{
			arrayTempDate = arraySeDate[i].split(",");
			arrayStartDateAndEndDate[i][0] = DateTimeUtil.getFormatDate(arrayTempDate[0]);
			arrayStartDateAndEndDate[i][1] = DateTimeUtil.getFormatDate(arrayTempDate[1]);
		}
		return maxContinuousDays(arrayStartDateAndEndDate);

	}

	/**
	 * 判断时间段1和时间段2是否有交集。
	 * 
	 * @param strBegintimeOne
	 * @param strEndtimeOne
	 * @param strBegintimeTwo
	 * @param strEndtimeTwo
	 * @return true:有交集,false:没有交集
	 */
	public static boolean isConfilct(String strBegintimeOne, String strEndtimeOne, String strBegintimeTwo, String strEndtimeTwo)
	{
		Date objBeginOne = getFormatDate(strBegintimeOne);
		Date objEndOne = getFormatDate(strEndtimeOne);
		Date objBeginTwo = getFormatDate(strBegintimeTwo);
		Date objEndTwo = getFormatDate(strEndtimeTwo);

		if ((objBeginOne.compareTo(objBeginTwo) <= 0 && objEndOne.compareTo(objBeginTwo) >= 0) || (objBeginOne.compareTo(objEndTwo) <= 0 && objEndOne.compareTo(objEndTwo) >= 0) || (objBeginTwo.compareTo(objBeginOne) <= 0 && objEndTwo.compareTo(objBeginOne) >= 0) || (objBeginTwo.compareTo(objEndOne) <= 0 && objEndTwo.compareTo(objEndOne) >= 0))
		{
			return true;
		}
		return false;
	}

	/**
	 * 取得最早可购买时间。
	 * 
	 * @param strBusytimes
	 *            被购买时间,格式为2008-08-06,2008-08-06;2008-08-9,2008-08-12;2008-08-14,
	 *            2008-08-22;2008-09-04,2008-09-04
	 * @param nDays 购买时长
	 * @return 最高可购买时间
	 */
	public static String getCansellTime(String strBusytimes, int nDays)
	{
		Map<String, Integer> mapDayMap = new HashMap<String, Integer>();
		String[] arrayBusytimeArr = strBusytimes.split(";");
		Date objLastDate;
		Date objBeginDate;
		Date objEndDate;
		
		for (int i = 0; i < arrayBusytimeArr.length; i++)
		{
			String[] arrayTime = arrayBusytimeArr[i].split(",");
			Date objD1 = getFormatDateTime(arrayTime[0], DATE_FORMAT);
			Date objD2 = getFormatDateTime(arrayTime[1], DATE_FORMAT);

			while (objD1.compareTo(objD2) <= 0)
			{
				mapDayMap.put(getFormatDate(objD1), null);
				objD1 = getDateBeforeOrAfter(objD1, 1);
			}
		}

		objLastDate = getFormatDateTime(getFormatDate(getDateBeforeOrAfter(29)), DATE_FORMAT);
		objBeginDate = getFormatDateTime(getFormatDate(getDateBeforeOrAfter(2)), DATE_FORMAT);
		objEndDate = getDateBeforeOrAfter(objBeginDate, nDays - 1);

		while (objBeginDate.compareTo(objLastDate) <= 0)
		{
			boolean bConflict = false;
			List<Date> lstDaysList = getDaysListBetweenDates(objBeginDate, objEndDate);

			for (Date d : lstDaysList)
			{
				if (mapDayMap.containsKey(getFormatDate(d)))
				{
					bConflict = true;
					break;
				}
			}
			if (!bConflict)
			{
				break;
			}
			objBeginDate = getDateBeforeOrAfter(objBeginDate, 1);
			objEndDate = getDateBeforeOrAfter(objBeginDate, nDays - 1);
		}
		return getFormatDate(objBeginDate);
	}

}
