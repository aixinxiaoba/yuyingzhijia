package javacommon.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * String字符串工具类 。
 * 
 * @author wangzg
 * 
 */
@SuppressWarnings("unchecked")
public class StringUtils
{
	/**
	 * 日志对象。
	 */
	private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

	private static SimpleDateFormat objSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 判断字符串是否为空的方法。
	 * 
	 * @param objObj 需要判断的值
	 * @param strStr {String} 为空返回的默认值
	 * @return String}
	 * @author zhangqiang
	 * @2011-8-3
	 */
	public static String VString(Object objObj, String strStr)
	{
		try
		{
			if (objObj == null || objObj.toString().equals("") || objObj.toString().equals("null"))
			{
				return strStr;
			}
			return objObj.toString();
		}
		catch (Exception e)
		{
			return strStr;
		}
	}

	/**
	 * 判断int型是否为空的方法。
	 * 
	 * @param objObj 需要判断的值
	 * @param nStr {int} 为空返回的默认值
	 * @return int}
	 * @author zhangqiang
	 * @2011-8-3
	 */
	public static int VInt(Object objObj, int nStr)
	{
		try
		{
			if (objObj == null || objObj == "")
			{
				return nStr;
			}
			return Integer.parseInt(objObj.toString());
		}
		catch (Exception e)
		{
			return nStr;
		}
	}

	/**
	 * 判断double型是否为空的方法。
	 * 
	 * @param objObj 需要判断的值
	 * @param dbStr {double} 为空返回的默认值
	 * @return double}
	 * @author zhangqiang
	 * @2011-8-3
	 */
	public static double VDouble(Object objObj, double dbStr)
	{
		try
		{
			if (objObj == null || objObj == "")
			{
				return dbStr;
			}
			return Double.parseDouble(objObj.toString());
		}
		catch (Exception e)
		{
			return dbStr;
		}
	}

	/**
	 * 判断long型是否为空的方法。
	 * 
	 * @param objObj 需要判断的值
	 * @param lStr {long}为空返回的默认值
	 * @return long}
	 * @author zhangqiang
	 * @2011-8-3
	 */
	public static long VLong(Object objObj, long lStr)
	{
		try
		{
			if (objObj == null || objObj == "")
			{
				return lStr;
			}
			return Long.parseLong(objObj.toString());
		}
		catch (Exception e)
		{
			return lStr;
		}
	}

	/**
	 * 判断boolean型是否为空的方法。
	 * 
	 * @param objObj 需要判断的值
	 * @param bStr {boolean}为空返回的默认值
	 * @return boolean}
	 * @author zhangqiang
	 * @2011-8-3
	 */
	public static boolean VBoolean(Object objObj, boolean bStr)
	{
		try
		{
			if (objObj == null || objObj == "")
			{
				return bStr;
			}
			return Boolean.parseBoolean(objObj.toString());
		}
		catch (Exception e)
		{
			return bStr;
		}
	}

	/**
	 * 验证是否为数字（true-数字）。
	 * 
	 * @author kangyue
	 * @2010-11-26
	 */
	public static boolean isNumber(String strStr)
	{
		Pattern objPattern = Pattern.compile("[1-9][0-9]*");
		Matcher objMatch = objPattern.matcher(strStr);
		
		if (objMatch.matches())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * MD5加密字符串。
	 * 
	 * @author 张强
	 * @2011-8-2
	 */
	public static String MD5Encode(String strStr)
	{
		char arrayHexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try
		{
			byte[] arrayTemp = strStr.getBytes("UTF-8");
			MessageDigest objMdTemp = MessageDigest.getInstance("MD5");
			byte[] arrayMd;
			int nJ1;
			char arrayCh[];
			int nK1 = 0;
			
			objMdTemp.update(arrayTemp);
			arrayMd = objMdTemp.digest();
			
			nJ1 = arrayMd.length;
			arrayCh = new char[nJ1 * 2];

			for (int i = 0; i < nJ1; i++)
			{
				byte cBydte = arrayMd[i];
				
				arrayCh[nK1++] = arrayHexDigits[cBydte >>> 4 & 0xf];
				arrayCh[nK1++] = arrayHexDigits[cBydte & 0xf];
			}
			return new String(arrayCh);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 验证电子邮件。
	 * 
	 * @author 张强
	 * @2011-8-2
	 */
	public static boolean checkEmail(String strMail)
	{
		String strCheck = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern objRegex = Pattern.compile(strCheck);
		Matcher objMatcher = objRegex.matcher(strMail);

		return objMatcher.matches();
	}

	/**
	 * 验证身份证。
	 * 
	 * @author 张强
	 * @2011-8-2
	 */
	public static boolean checkIDCardNumber(String strCid)
	{
		if (strCid.length() != 15 && strCid.length() != 18)
		{
			return false;
		}
		else if (strCid.length() == 15)
		{
			if (!isNumber(strCid))
			{
				return false;
			}
			return true;
		}
		else if (strCid.length() == 18)
		{
			if (!isNumber(strCid.substring(0, 17)))
			{
				return false;
			}
			if (!isNumber(strCid.substring(17, 18)) && !"x".equals(strCid.substring(17, 18).toLowerCase()))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 把slake返回json字符串转化为map 目前该方法仅取返回的第一条数据，仍需要加工。
	 * 
	 * @author
	 * @2011-8-8
	 */
	public static Map<String, String> slakeParseToMap(String strStr)
	{
		int nBegin = strStr.indexOf('{');
		int nEnd = strStr.lastIndexOf('}');
		int nSbegin;
		String strData;
		Map<String, String> mapNested;
		
		if (strStr != null && !"".equals(strStr))
		{
			strStr = strStr.trim();
		}
		
		strStr = strStr.substring(1, nEnd);
		nBegin = strStr.indexOf('{');
		nEnd = strStr.lastIndexOf('}');
		strStr = strStr.substring(nBegin + 1, nEnd);// 获得数据字符串
		nSbegin = strStr.indexOf("[{");
		nEnd = strStr.indexOf("}]");
		strData = strStr.substring(nSbegin + 2, nEnd);// 获得数据字符串
		mapNested = new HashMap<String, String>();// 截取数字字符串
		
		if (!strData.equals(""))
		{
			String[] arrayData = strData.split(",");
			
			// 封装数据
			for (String datasingle : arrayData)
			{
				String[] arraySecond = datasingle.split(":", 2);
				String strKey = arraySecond[0].trim();
				String strValue = (arraySecond[1] == null) ? "" : arraySecond[1].trim();
				
				strKey = strKey.replace("\"", "");
				strKey = strKey.replace("'", "");
				strValue = strValue.replace("\"", "");
				strValue = strValue.replace("'", "");
				mapNested.put(strKey, strValue);
			}
		}
		return mapNested;
	}

	/**
	 * 判断字符串是否为空的方法。
	 * 
	 * @return true-空 false-非空
	 * @author kangyue
	 * @2010-6-30
	 */
	public static boolean isBlank(String strStr)
	{
		return strStr == null || strStr.trim().equals("");
	}

	/**
	 * 找出list中不同的数据-前提需有排序。
	 * 
	 * @author kangyue
	 * @since 2011-12-2
	 */
	public static List<String> getDistinctValue(List<String> lstList)
	{
		List<String> lstTarget = new ArrayList<String>();
		String strValueInit;

		if (lstList == null || lstList.size() == 0)
		{
			return null;
		}
		if (lstList.size() == 1)
		{
			return lstList;
		}
		lstTarget = new ArrayList<String>();
		strValueInit = lstList.get(0);

		lstTarget.add(strValueInit);
		for (int i = 1; i < lstList.size(); i++)
		{
			String strValue = lstList.get(i);

			if (!strValueInit.equals(strValue))
			{
				lstTarget.add(strValue);
			}
			strValueInit = strValue;
		}
		return lstTarget;
	}

	/**
	 * 取得Double类型list最小值和最大值。
	 * 
	 * @author kangyue
	 * @param timeStr 时间list
	 * @return list[0]为最小值 list[1]为最大值
	 * @sicne 2011-12-01
	 */
	public static List<Double> getMaxMinTimeDou(List<Double> lstValue)
	{
		List<Double> lstTarget = new ArrayList<Double>();
		Double objMinValue;
		Double objMaxValue;
		Double objTemp = null;

		if (lstValue == null || lstValue.size() == 0)
		{
			return null;
		}
		if (lstValue.size() == 1)
		{
			lstTarget.add(lstValue.get(0));
			lstTarget.add(lstValue.get(0));
			return lstTarget;
		}
		objMinValue = lstValue.get(0);
		objMaxValue = lstValue.get(0);

		for (int i = 1; i < lstValue.size(); i++)
		{
			objTemp = lstValue.get(i);
			if (objMinValue.doubleValue() > objTemp.doubleValue())
			{
				objMinValue = objTemp.doubleValue();
			}
			if (objMaxValue.doubleValue() < objTemp.doubleValue())
			{
				objMaxValue = objTemp.doubleValue();
			}
		}
		lstTarget.add(objMinValue);
		lstTarget.add(objMaxValue);
		return lstTarget;
	}

	/**
	 * 取得时间list最小值和最大值。
	 * 
	 * @author kangyue
	 * @param lstTimeStr 时间list
	 * @return list[0]为最小时间 list[1]为最大时间
	 * @sicne 2011-12-01
	 */
	public static List<String> getMaxMinTimeStr(List<String> lstTimeStr)
	{
		List<String> lstTarget = new ArrayList<String>();
		String strMinTime = lstTimeStr.get(0);
		String strMaxTime = lstTimeStr.get(0);
		String strTemp = "";

		if (lstTimeStr == null || lstTimeStr.size() == 0)
		{
			return null;
		}
		if (lstTimeStr.size() == 1)
		{
			lstTarget.add(lstTimeStr.get(0));
			lstTarget.add(lstTimeStr.get(0));
			return lstTarget;
		}
		strMinTime = lstTimeStr.get(0);
		strMaxTime = lstTimeStr.get(0);

		for (int i = 1; i < lstTimeStr.size(); i++)
		{
			strTemp = lstTimeStr.get(i);
			if (compareTime(toYMDDate(strMinTime), toYMDDate(strTemp)))
			{
				strMinTime = lstTimeStr.get(i);
			}
			if (!compareTime(toYMDDate(strMaxTime), toYMDDate(strTemp)))
			{
				strMaxTime = lstTimeStr.get(i);
			}
		}
		lstTarget.add(strMinTime);
		lstTarget.add(strMaxTime);
		return lstTarget;
	}

	/**
	 * 比较两个时间。
	 * 
	 * @author kangyue date1 > date2 返回true date1 < date2 返回false
	 * @return
	 */
	public static boolean compareTime(Date objDate1, Date objDate2)
	{
		Calendar objC1 = Calendar.getInstance();
		Calendar objC2 = Calendar.getInstance();

		objC1.setTime(objDate1);
		objC2.setTime(objDate2);
		if (objC1.after(objC2))
		{
			return true;
		}
		return false;
	}

	/**
	 * 将一个字符串转变为日期类型。
	 * 
	 * @author kangyue
	 * @String value(应为("yyyy-MM-dd HH:mm:ss"形式的字符串)
	 * @return Date
	 * @throws Exception
	 */
	public static Date toYMDDate(String strValue)
	{
		try
		{
			SimpleDateFormat objDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date objDateResult = objDf.parse(strValue);

			return objDateResult;
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
		return null;
	}

	/**
	 * 把map转化为字符串。
	 * 
	 * @return true-空 false-非空
	 * @author qgc
	 * @2010-8-15
	 */
	public static String convertToUrl(Map<String, Object> mapMap)
	{
		Set<String> lstSet = mapMap.keySet();
		Iterator objIt = lstSet.iterator();
		StringBuffer sbufSb = new StringBuffer();
		String strResult;

		while (objIt.hasNext())
		{
			String strKey = objIt.next().toString();
			Object objValuesz = mapMap.get(strKey);
			String strValue = "null";

			if (objValuesz != null && !"".equals(objValuesz.toString()) && objValuesz instanceof String[])
			{
				String[] arraySz = (String[]) objValuesz;
				
				try
				{
					if (arraySz.length > 0)
					{
						if (arraySz[0].length() > 0)
						{
							strValue = java.net.URLDecoder.decode(arraySz[0], "UTF-8");
						}
					}
				}
				catch (UnsupportedEncodingException e)
				{
					logger.error("", e);
				}
			}
			if (!"pname".equals("key"))
			{
				sbufSb.append(strKey).append("=").append(strValue).append("&");
			}
		}
		strResult = sbufSb.toString();

		if (!"".equals(strResult) && strResult.endsWith("&"))
		{
			strResult = strResult.substring(0, strResult.length() - 1);
		}

		return strResult;
	}

	/**
	 * RequestMap 转化为HashMap<String,String>。
	 * 
	 * @param mapAmap RequestMap request 参数键值对
	 * @return
	 */
	public static Map getCommonMap(Map mapAmap)
	{
		Map mapReturnMap = new HashMap();
		Set<String> lstKeySet = mapAmap.keySet();
		String strValue = "";

		for (String strKey : lstKeySet)
		{
			Object objValue = mapAmap.get(strKey);

			if (null == objValue)
			{
				strValue = "";
			}
			else if (objValue instanceof String[])
			{
				String[] arrayValues = (String[]) objValue;

				for (int i = 0; i < arrayValues.length; i++)
				{
					strValue = arrayValues[i] + ",";
				}
				strValue = strValue.substring(0, strValue.length() - 1);
			}
			else
			{
				strValue = objValue.toString();
			}
			mapReturnMap.put(strKey, strValue);

		}
		return mapReturnMap;
	}

	/**
	 * 获取系统当前时间。
	 * 
	 * @param strFormatString 时间格式化字符串 传入的字符串为空时，将提供默认的时间格式
	 * @return 当前时间字符串
	 */
	public static String getCurrentDate(String strFormatString)
	{
		SimpleDateFormat objFormat = new SimpleDateFormat(strFormatString);
		String strCurrentDateString;

		if (strFormatString == null || "".equals(strFormatString))
		{
			strFormatString = "yyyy-MM-dd HH:mm:ss[SSS]";
		}
		strCurrentDateString = objFormat.format(new java.util.Date());

		return strCurrentDateString;
	}

	/**
	 * 把slake返回json字符串转化为map。
	 * 
	 * @author
	 * @2011-8-8
	 */
	public static Map<String, String> newSlakeParseToMap(String strStr)
	{
		int nBegin = strStr.indexOf('[');
		int nEnd = strStr.lastIndexOf(']');
		String strData;
		String[] arrayDat;
		Map<String, String> mapNested = new HashMap<String, String>();

		if (strStr != null && !"".equals(strStr))
		{
			strStr = strStr.trim();
		}
		strStr = strStr.substring(nBegin + 1, nEnd);
		nBegin = strStr.indexOf('{');
		nEnd = strStr.lastIndexOf('}');
		strData = strStr.substring(nBegin + 1, nEnd);// 获得数据字符串
		arrayDat = strData.split(",");// 截取数字字符串
		mapNested = new HashMap<String, String>();

		// 封装数据
		for (String datasingle : arrayDat)
		{
			String[] arraySecondArrary = datasingle.split(":");
			String strKey = arraySecondArrary[0].trim();
			String strValue = ("".equals(arraySecondArrary[1])) ? "" : arraySecondArrary[1].trim();

			strKey = strKey.replace("\"", "");
			strKey = strKey.replace("'", "");
			strValue = strValue.replace("\"", "");
			strValue = strValue.replace("'", "");

			mapNested.put(strKey, strValue);
		}
		return mapNested;
	}

	/**
	 * json字符串转换为集合 返回map 如果传入的字符串为空 返回null。
	 * 
	 * @author
	 */
	public static List jsonToMap(String strJsonstr)
	{
		JSONObject objJson = JSONObject.fromObject(strJsonstr);
		JSONObject objJJ = (JSONObject) objJson.get("response");
		JSONArray objJa = (JSONArray) objJJ.get("data");
		ArrayList lstData = new ArrayList();

		for (int i = 0; i < objJa.size(); i++)
		{
			Map mapMap;
			
			objJson = objJa.getJSONObject(i);
			mapMap = (Map) objJson;

			lstData.add(mapMap);
		}
		return lstData;

	}

	/**
	 * jsonToList。
	 * 
	 * @param strJsons
	 * @return
	 */
	public static List jsonToList(String strJsons)
	{
		List lstData = new ArrayList();
		JSONObject objJson = JSONObject.fromObject(strJsons);
		JSONObject objJJ = (JSONObject) objJson.get("response");
		String strStr = objJJ.toString();
		int nI1 = strStr.indexOf("GOOD_RESP");
		int nJ1 = strStr.indexOf(",", nI1);

		strStr = strStr.substring(0, nI1) + strStr.substring(nJ1 + 2);
		nI1 = strStr.indexOf("allcount");
		nJ1 = strStr.indexOf(",", nI1);
		strStr = strStr.substring(0, nI1) + strStr.substring(nJ1 + 2);

		lstData.add(objJJ);
		return lstData;
	}

	/**
	 * 生成三位随机数。
	 * 
	 * @return
	 */
	public static String generateRandom()
	{
		Random objRandom = new Random();
		int nX1 = objRandom.nextInt(100) * 8 + 100;

		return nX1 + "";
	}

	/**
	 * 查看字符串中是否包含特殊符号。
	 * 
	 * @return true-有特殊字符 false-无特殊字符
	 * @author kangyue
	 * @since 2011-12-6
	 */
	public static boolean containsSpecialChar(String strStr, String[] arrayCharLs)
	{
		for (int i = 0; i < arrayCharLs.length; i++)
		{
			if (strStr.indexOf(arrayCharLs[i]) > -1)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回以逗号分隔的每个字符串中前后加入两个单引号。
	 * 返回''123'',''3'',''3'',''4'',''5'',''6'',''6'',''trr''
	 * 
	 * @param source以逗号分隔的字符串 如"123,3,3,4,5,6,6,trr"
	 * @return
	 */
	public static String getChangedString(String strSource)
	{
		String strResult = "''" + strSource + "''";
		
		strResult = strResult.replace(",", "'',''");

		return strResult;
	}

	/**
	 * 功能描述:通过传入一个列表对象,调用指定方法。将列表中的数据生成一个JSON规格指定字符串。
	 * 
	 * @param list 列表对象
	 * @return java.lang.String
	 */
	public static String listToJson(List<Map<String, String>> lstLs)
	{
		StringBuilder objJson = new StringBuilder();
		int nLen;
		
		if (lstLs == null || lstLs.size() == 0)
		{
			return "";
		}
		nLen = lstLs.size();

		objJson.append("[");
		for (int i = 0; i < nLen; i++)
		{
			Map<String, String> mapTemp;

			objJson.append("{");
			mapTemp = lstLs.get(i);

			for (String key : mapTemp.keySet())
			{
				objJson.append("|").append(key).append("|:");
				objJson.append("|").append(mapTemp.get(key)).append("|,");
			}
			objJson.setLength(objJson.length() - 1);
			objJson.append("},");
		}
		objJson.setLength(objJson.length() - 1);
		objJson.append("]");
		return objJson.toString();
	}

	/**
	 * 功能描述:通过传入一个列表对象,调用指定方法。将列表中的数据生成一个JSON规格指定字符串。
	 * 
	 * @param list 列表对象
	 * @return java.lang.String
	 */
	public static String listToJson2(List<Map<String, Object>> lstLs)
	{
		StringBuilder objJson = new StringBuilder();
		int nLen;

		if (lstLs == null || lstLs.size() == 0)
		{
			return "";
		}
		nLen = lstLs.size();

		objJson.append("[");
		for (int i = 0; i < nLen; i++)
		{
			Map<String, Object> mapTemp = lstLs.get(i);

			objJson.append("{");
			for (String strKey : mapTemp.keySet())
			{
				objJson.append("\"").append(strKey).append("\":");
				objJson.append("\"").append(mapTemp.get(strKey)).append("\",");
			}
			objJson.setLength(objJson.length() - 1);
			objJson.append("},");
		}
		objJson.setLength(objJson.length() - 1);
		objJson.append("]");
		return objJson.toString();
	}

	/**
	 * 验证是否是数字。
	 * 
	 * @author WZF
	 * @param strStr 需要判断的值
	 * @return boolean
	 */
	public static boolean accountNum(String strStr)
	{
		Pattern objPattern = Pattern.compile("[0-9]+");
		Matcher objMatch = objPattern.matcher(strStr);

		if (objMatch.matches())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 为商户生成安全码。
	 * 
	 * @return 新生成的安全码
	 */
	public static String generateSekey()
	{
		String strStr = "";
		java.util.Date objDate = new java.util.Date();
		java.sql.Date objDt = new java.sql.Date(objDate.getTime());
		SimpleDateFormat objMatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate;
		char[] arrayValues;
		StringBuilder objS1 = new StringBuilder();
		StringBuilder objS2 = new StringBuilder();

		strStr = "00000000000000000000000000000000000000";
		strDate = objMatter.format(objDt);
		strStr = strStr + strDate;
		arrayValues = strStr.toCharArray();

		for (int i = 0; i < arrayValues.length; i++)
		{
			if (i % 2 == 0)
			{
				objS1.append(arrayValues[i]);
			}
			else
			{
				objS2.append(arrayValues[i]);
			}
		}
		strStr = objS1.toString() + objS2.toString();
		return strStr;
	}

	/**
	 * @param params 加密的参数，secretkey:商户的安全码。
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String generateSign(String strParam, String strSecretkey)
	{
		Map<String, String> mapParams = new HashMap<String, String>();
		String[] arrayQueryParams = strParam.split("&");
		String strUrl;

		if (arrayQueryParams != null)
		{
			// 1解析参数
			for (String str : arrayQueryParams)
			{
				String[] arrayKeyAndValue = str.split("=");

				if (arrayKeyAndValue == null || arrayKeyAndValue.length != 2)
				{
					continue;
				}
				if (arrayKeyAndValue[1] == null || arrayKeyAndValue[1].equals("null"))
				{
					continue;
				}
				mapParams.put(arrayKeyAndValue[0], arrayKeyAndValue[1]);
			}
			mapParams.put("secret_key", strSecretkey);// 加入安全码
			strUrl = mapToURL(mapParams);// 组装成URL

			return md5(strUrl);
		}
		return "";
	}

	/**
	 * @param mapParam 参数Map(已经包含商家的安全码)。
	 * @return
	 */
	public static String generateSign(Map mapParam)
	{
		String strUrl = mapToURL(mapParam);

		return md5(strUrl);
	}

	/**
	 * 对数组里的每一个值从a 到z 的顺序排序，若遇到相同首字母。
	 * 则看第二个字母，以此类推。 排序完成之后，再把所有数组值以“&”字符连接起来。
	 * 1、没有值的参数无需传递，也无需包含到待签名数据中 2、接口需要对请求参数email。
	 * 进行数字签名，那么待签名数据应该是email=test@msn.com，而不是email=test%40msn.com。
	 */
	private static String mapToURL(Map<String, String> mapParams)
	{
		Map<String, String> mapResult = new HashMap<String, String>();
		List<String> lstKeys;
		String strPrestr = "";

		if (mapParams == null || mapParams.size() <= 0)
		{
			return "";
		}

		for (String strKey : mapParams.keySet())
		{
			String strValue = mapParams.get(strKey);

			if (strValue == null || "".equals(strValue) || "sign".equalsIgnoreCase(strKey) || "sign_type".equalsIgnoreCase(strKey) || "data".equalsIgnoreCase(strKey))
			{
				continue;
			}
			// if(value.startsWith("[{")){//如果多条数据，需要对key值排序
			// JSONArray jsArray = (JSONArray.fromObject(value));
			// StringBuffer sf = new StringBuffer("[");
			// List<String> dkeys = null;
			// if(jsArray.size() > 0){
			// Map amap = (Map)jsArray.get(0);
			// dkeys = new ArrayList<String>(amap.keySet());
			// Collections.sort(dkeys);
			// for(int i = 0;i < jsArray.size();i ++){
			// sf.append("{");
			// amap = (Map)jsArray.get(i);
			// for (int j = 0; j < dkeys.size();j++) {
			// String key1 = dkeys.get(j);
			// String value1 = (amap.get(key1) == null) ?
			// "":amap.get(key1).toString();
			// sf.append("\"" + key1 + "\"").append(":").append("\"" + value1 +
			// "\"");
			// if(j < dkeys.size() - 1){
			// sf.append(",");
			// }
			// }
			// sf.append("}");
			// if(i < jsArray.size() - 1){
			// sf.append(",");
			// }
			// }
			// sf.append("]");
			// value = sf.toString();
			// }else{
			// value = "[{}]";
			// }
			// }
			mapResult.put(strKey, strValue);
		}

		lstKeys = new ArrayList<String>(mapResult.keySet());
		Collections.sort(lstKeys);

		for (int i = 0; i < lstKeys.size(); i++)
		{
			String strKey = lstKeys.get(i);
			String strValue = mapResult.get(strKey);

			if (i == lstKeys.size() - 1)
			{
				strPrestr = strPrestr + strKey + "=" + strValue;
			}
			else
			{
				strPrestr = strPrestr + strKey + "=" + strValue + "&";
			}
		}

		return strPrestr;
	}

	/**
	 * md5加密。
	 * 
	 * @param strInputText
	 * @return
	 */
	public static String md5(String strInputText)
	{
		return encrypt(strInputText, "md5");
	}

	/**
	 * md5或者sha-1加密。
	 * 
	 * @param strInputText 要加密的内容
	 * @param strAlgorithmName 加密算法名称：md5或者sha-1，不区分大小写
	 * @return
	 */
	private static String encrypt(String strInputText, String strAlgorithmName)
	{
		String strEncryptText = null;

		if (strInputText == null || "".equals(strInputText.trim()))
		{
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if (strAlgorithmName == null || "".equals(strAlgorithmName.trim()))
		{
			strAlgorithmName = "md5";
		}
		try
		{
			MessageDigest objM = MessageDigest.getInstance(strAlgorithmName);
			byte arrayS[];
			
			objM.update(strInputText.getBytes("UTF8"));
			arrayS = objM.digest();
			
			return hex(arrayS);
		}
		catch (NoSuchAlgorithmException e)
		{
			logger.error("", e);
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error("", e);
		}
		return strEncryptText;
	}

	/**
	 * 返回十六进制字符串。
	 * 
	 * @param arrayArr
	 * @return
	 */
	private static String hex(byte[] arrayArr)
	{
		StringBuffer sbufSb = new StringBuffer();

		for (int i = 0; i < arrayArr.length;++i)
		{
			sbufSb.append(Integer.toHexString((arrayArr[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sbufSb.toString();
	}

	/**
	 * 为返回的结果添加sign。
	 * 
	 * @param strResponse 返回结果
	 * @param strSecretKey
	 * @return
	 */
	public static String addSign(String strResponse, String strSecretKey)
	{
		JSONObject objJson = JSONObject.fromObject(strResponse);
		JSONObject objJson1 = objJson.getJSONObject("response");
		Object objError = objJson1.get("ERROR");
		Iterator objIt = objJson1.keys();
		Map mapAmap = new TreeMap();
		String strSign;
		
		if (objError != null)
		{
			JSONArray objErrorjs = (JSONArray) objError;
			Map mapMap = (Map) objErrorjs.get(0);

			if (mapMap.get("ERR_CODE") != null)
			{
				String strErrorCode = mapMap.get("ERR_CODE").toString();

				objJson1.put("error_code", strErrorCode);
				objJson1.put("is_success", "N");
				objJson1.remove("ERROR");
			}
		}
		else
		{
			objJson1.put("is_success", "Y");
		}

		objIt = objJson1.keys();

		while (objIt.hasNext())
		{
			String strKey = objIt.next().toString();
			String strValue = (objJson1.get(strKey) == null) ? "" : objJson1.get(strKey).toString();

			mapAmap.put(strKey, strValue);
		}
		mapAmap.put("secret_key", strSecretKey);
		strSign = generateSign(mapAmap);

		objJson1.put("sign", strSign);
		return objJson.toString();
	}

	/**
	 * action中验证签名。
	 * 
	 * @param strParams
	 * @param strSecretKey
	 * @param strSign
	 * @return
	 */
	public static String checkSign(String strParams, String strSecretKey, String strSign)
	{
		String strNewSign = StringUtils.generateSign(strParams, strSecretKey);

		if (null == strSign || !strNewSign.equals(strSign))
		{
			String strResponse = "{\"response\":{\"allcount\":\"0\";\"data\":[{}];\"is_success\":\"N\";\"error_code\":\"PSE10001\"}}";

			return addSign(strResponse, strSecretKey);
		}
		else
		{
			return "success";
		}
	}

	/**
	 * 将字符串参数转为Map。
	 * 
	 * @param strParamStr
	 * @return
	 */
	public static Map strParamToMap(String strParamStr)
	{
		Map<String, String> mapParams = new HashMap<String, String>();
		String[] arrayQueryParams = strParamStr.split("&");

		if (arrayQueryParams != null)
		{
			// 1解析参数
			for (String str : arrayQueryParams)
			{
				String[] arrayKeyAndValue = str.split("=");

				if (arrayKeyAndValue == null || arrayKeyAndValue.length != 2)
				{
					continue;
				}
				if (arrayKeyAndValue[1] == null)
				{
					mapParams.put(arrayKeyAndValue[0], "");
				}
				else
				{
					mapParams.put(arrayKeyAndValue[0], arrayKeyAndValue[1]);
				}
			}
		}
		return mapParams;
	}

	/**
	 * 检查对象是否为数字型字符串。
	 */
	public static boolean isNumeric(Object objObj)
	{
		String strStr;
		int nSz;
		
		if (objObj == null)
		{
			return false;
		}
		if ("".equals(objObj.toString().trim()))
		{
			return false;
		}
		strStr = objObj.toString();
		nSz = strStr.length();

		for (int i = 0; i < nSz; i++)
		{
			if (!Character.isDigit(strStr.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 格式化时间为:yyyy-MM-dd HH:mm:ss。
	 */
	public static String dateFormat(java.util.Date objDate)
	{
		return objSdf.format(objDate);
	}

	/**
	 * 转换为日期。
	 * @param strDateStr
	 * @return
	 */
	public static java.util.Date praseToDate(String strDateStr)
	{
		try
		{
			return objSdf.parse(strDateStr);
		}
		catch (ParseException e)
		{
		}
		return null;
	}

	/**
	 * 是否为空。
	 * @param strStr
	 * @return
	 */
	public static boolean isEmpty(String strStr)
	{
		return strStr == null || strStr.length() == 0;
	}

	/**
	 * defaultString。
	 * @param strStr
	 * @return
	 */
	public static String defaultString(String strStr)
	{
		return strStr != null ? strStr : "";
	}

	/**
	 * defaultString。
	 * @param strStr
	 * @param strDefaultStr
	 * @return
	 */
	public static String defaultString(String strStr, String strDefaultStr)
	{
		return strStr != null ? strStr : strDefaultStr;
	}

	/**
	 * defaultIfEmpty。
	 * @param strStr
	 * @param strDefaultStr
	 * @return
	 */
	public static String defaultIfEmpty(String strStr, String strDefaultStr)
	{
		return isEmpty(strStr) ? strDefaultStr : strStr;
	}

}