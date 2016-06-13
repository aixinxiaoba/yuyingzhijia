package javacommon.util.json;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javacommon.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * JSON 工具类 。
 * 
 * @author wangzg
 * 
 */
public final class JSONUtils
{
	/** 默认时间格式 * */
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
	private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** Date默认时区 * */
	private static final String DATE_TIMEZONE = "GMT+8";

	/**
	 * 空构造。
	 */
	private JSONUtils()
	{
		// not access
	}

	/**
	 * json 转换为单个java对象。
	 * 
	 * @param strJson
	 * @param objClazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Object toBean(String strJson, Class objClazz) throws ParserException
	{
		final List lstList = JSONUtils.toList(strJson, objClazz);
		
		if (lstList != null && !lstList.isEmpty())
		{
			if (lstList.size() > 1)
			{
				throw new ParserException("返回的记录数多于一条记录.");
			}
			return lstList.get(0);
		}
		return null;
	}

	/**
	 * JSON字符串 转换为list。
	 * 
	 * @param strJson
	 * @param objClazz
	 * @return
	 * @throws ParserException
	 */
	@SuppressWarnings("unchecked")
	public static List toList(String strJson, Class objClazz) throws ParserException
	{
		// 定义参数
		List lstList = new ArrayList();
		JSONArray objArray;
		int nSize;
		
		// 验证参数
		if (strJson == null || "".equals(strJson))
		{
			throw new IllegalArgumentException("argument json is empty.");
		}

		if (objClazz == null)
		{
			throw new IllegalArgumentException("argument clazz is empty.");
		}

		objArray = (JSONArray.fromObject(strJson));
		nSize = objArray.size();
		
		if (nSize == 0)
		{
			throw new ParserException("返回的数据记录数为零.");
		}

		for (int i = 0; i < nSize; i++)
		{
			JSONObject objJsonObject = (JSONObject) objArray.get(i);
			Object objObj = converter(objClazz, objJsonObject);
			
			lstList.add(objObj);
		}

		// 返回结果
		return lstList;
	}

	/**
	 * 将Bean转换为Json格式,对日期类型做了特殊处理。
	 * 
	 * @param objObj
	 * @return
	 */
	public static String toJson(Object objObj)
	{
		if (objObj != null)
		{
			JsonConfig objJsonConfig = new JsonConfig();
			JSONObject objJsonObj;
			
			objJsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueConfig());
			objJsonObj = JSONObject.fromObject(objObj, objJsonConfig);
			
			return objJsonObj.toString();
		}
		return "";
	}

	/**
	 * 取得key。
	 * @param strFieldName
	 * @param nIndex
	 * @return
	 */
	private static String getKey(String strFieldName, int nIndex)
	{
		// return fieldName.toUpperCase() + "_" + index;
		return strFieldName.toUpperCase();// 新版
	}

	/**
	 * 取值。
	 * @param objJsonObject
	 * @param strItemName
	 * @return
	 */
	private static Object getVal(JSONObject objJsonObject, String strItemName)
	{
		Object objValue = null;
		
		try
		{
			objValue = objJsonObject.get(getKey(strItemName, -1));
			
			if (objValue != null)
			{
				if (objValue instanceof String)
				{
					String strStr = objValue.toString();
					
					// 去除前后单引号
					if (strStr.startsWith("'"))
					{
						strStr = strStr.substring(1, strStr.length() - 1);
					}
					if ("null".equals(strStr))
					{
						return null;
					}
					return strStr;
				}
			}
		}
		catch (Exception e)
		{
			//
		}
		return objValue;
	}

	/**
	 * 把JSONObject转换成Java对象。
	 * 
	 * @param objClazz
	 * @param objJsonObject
	 * @return
	 * @throws ParserException
	 */
	@SuppressWarnings("unchecked")
	private static Object converter(Class objClazz, JSONObject objJsonObject) throws ParserException
	{
		Object objRsp = null;
		
		try
		{
			BeanInfo objBeanInfo;
			PropertyDescriptor[] arrayPds;
			
			objRsp = objClazz.newInstance();
			objBeanInfo = Introspector.getBeanInfo(objClazz, Object.class);
			arrayPds = objBeanInfo.getPropertyDescriptors();

			for (PropertyDescriptor objPd : arrayPds)
			{
				Method objMethod = objPd.getWriteMethod();
				String strItemName;
				Field objField;
				Class<? > objTypeClass;
				Object objValue = null;
				
				if (objMethod == null)
				{
					continue;// ignore read-only fields
				}
				
				strItemName = objPd.getName();
				objField = objClazz.getDeclaredField(strItemName);
				objTypeClass = objField.getType();
				
				// 目前
				if (String.class.isAssignableFrom(objTypeClass))
				{
					objValue = getVal(objJsonObject, strItemName);
					if (objValue instanceof String)
					{
						objMethod.invoke(objRsp, objValue.toString());
					}
					else
					{
						if (objValue != null)
						{
							objMethod.invoke(objRsp, objValue.toString());
						}
						else
						{
							objMethod.invoke(objRsp, "");
						}
					}
				}
				else if (Long.class.isAssignableFrom(objTypeClass))
				{
					objValue = getVal(objJsonObject, strItemName);

					if (objValue instanceof Long)
					{
						objMethod.invoke(objRsp, (Long) objValue);
					}
					else
					{
						if (StringUtils.isNumeric(objValue))
						{
							objMethod.invoke(objRsp, Long.valueOf(objValue.toString()));
						}
					}
				}
				else if (Integer.class.isAssignableFrom(objTypeClass))
				{
					objValue = getVal(objJsonObject, strItemName);
					if (objValue instanceof Integer)
					{
						objMethod.invoke(objRsp, (Integer) objValue);
					}
					else
					{
						if (StringUtils.isNumeric(objValue))
						{
							objMethod.invoke(objRsp, Integer.valueOf(objValue.toString()));
						}
					}
				}
				else if (Boolean.class.isAssignableFrom(objTypeClass))
				{
					objValue = getVal(objJsonObject, strItemName);
					if (objValue instanceof Boolean)
					{
						objMethod.invoke(objRsp, (Boolean) objValue);
					}
					else
					{
						if (objValue != null)
						{
							objMethod.invoke(objRsp, Boolean.valueOf(objValue.toString()));
						}
					}
				}
				else if (Double.class.isAssignableFrom(objTypeClass))
				{
					objValue = getVal(objJsonObject, strItemName);
					if (objValue instanceof Double)
					{
						objMethod.invoke(objRsp, (Double) objValue);
					}
					else
					{
						if (StringUtils.isNumeric(objValue))
						{
							objMethod.invoke(objRsp, Double.valueOf(objValue.toString()));
						}
					}
				}
				else if (Number.class.isAssignableFrom(objTypeClass))
				{
					objValue = getVal(objJsonObject, strItemName);
					if (objValue instanceof Number)
					{
						objMethod.invoke(objRsp, (Number) objValue);
					}
					else
					{
						throw new ParserException(strItemName + " is not a Number");
					}
				}
				else if (Date.class.isAssignableFrom(objTypeClass))
				{
					DateFormat objFormat;
					
					objValue = getVal(objJsonObject, strItemName);
					objFormat = new SimpleDateFormat(TIME_FORMAT);	// 需要修改,完整时间格式被截取了
					objFormat.setTimeZone(TimeZone.getTimeZone(DATE_TIMEZONE));
					
					if (objValue != null && !"".equals(objValue.toString().trim()))
					{
						Date objDate = null;
						
						try
						{
							objDate = objFormat.parse(objValue.toString());
						}
						catch (Exception e)
						{
							objFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
							objFormat.setTimeZone(TimeZone.getTimeZone(DATE_TIMEZONE));
							objDate = objFormat.parse(objValue.toString());
						}
						objMethod.invoke(objRsp, objDate);
					}
				}
				else if (List.class.isAssignableFrom(objTypeClass))
				{
					Type objFieldType = objField.getGenericType();
					
					if (objFieldType instanceof ParameterizedType)
					{
						// throw new UnsupportedOperationException();
					}
				}
				else
				{
					// 读取单个对象值
					// throw new UnsupportedOperationException();
				}
			}
		}
		catch (Exception e)
		{
			throw new ParserException(e);
		}
		return objRsp;
	}

	/**
	 * Just for testing。
	 * 
	 * @param arrayArgs
	 */
	public static void main(String[] arrayArgs)
	{

	}
}
