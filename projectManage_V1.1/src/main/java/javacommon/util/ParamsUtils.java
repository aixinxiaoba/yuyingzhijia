package javacommon.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javacommon.util.DateTimeUtil;

/**
 * HTTP 参数转换器 。
 * 
 * @author wangzg
 * 
 */
public final class ParamsUtils
{
	/**
	 * 空构造。
	 */
	private ParamsUtils()
	{
		// not access
	}

	/**
	 * 将Bean对象转换为&key=value格式的参数形式 。
	 * 
	 * @param objObj
	 * @param bIgnore 是否去除Null值
	 * @return
	 */
	public static String bean2Paramter(Object objObj, boolean bIgnore)
	{
		return bean2Paramter(objObj, bIgnore, DateTimeUtil.DATE_FORMAT);
	}

	/**
	 * 将Bean对象转换为&key=value格式的参数形式 。
	 * 
	 * @param objObj
	 * @param bIgnore
	 * @param strDateFormat ,日期格式,see DateTimeUtil
	 * @return
	 */
	public static String bean2Paramter(Object objObj, boolean bIgnore, String strDateFormat)
	{
		if (objObj != null)
		{
			StringBuffer sbufParams = new StringBuffer();
			
			try
			{
				Class<? > objClazz = objObj.getClass();
				BeanInfo objBeanInfo = Introspector.getBeanInfo(objClazz, Object.class);
				PropertyDescriptor[] arrayPds = objBeanInfo.getPropertyDescriptors();

				for (PropertyDescriptor pd : arrayPds)
				{
					Method objMethod = pd.getReadMethod();
					String strResult = "";
					Field objField;
					Class<? > objTypeClass;
					String strItemName;
					Object objVal;
					
					if (objMethod == null)
					{
						continue;
					}

					strItemName = pd.getName();
					objVal = objMethod.invoke(objObj);
					
					if (objVal == null)
					{
						continue;
					}

					objField = objClazz.getDeclaredField(strItemName);
					objTypeClass = objField.getType();
					
					if (String.class.isAssignableFrom(objTypeClass))
					{
						strResult = objVal.toString();
					}
					else if (Long.class.isAssignableFrom(objTypeClass))
					{
						strResult = objVal.toString();
					}
					else if (Integer.class.isAssignableFrom(objTypeClass))
					{
						strResult = objVal.toString();
					}
					else if (Boolean.class.isAssignableFrom(objTypeClass))
					{
						strResult = objVal.toString();
					}
					else if (Double.class.isAssignableFrom(objTypeClass))
					{
						strResult = objVal.toString();
					}
					else if (Number.class.isAssignableFrom(objTypeClass))
					{
						strResult = objVal.toString();
					}
					else if (Date.class.isAssignableFrom(objTypeClass))
					{
						if (objVal instanceof Date)
						{
							strResult = DateTimeUtil.getFormatDate((Date) objVal, strDateFormat);
						}
					}
					else
					{
						strResult = objVal.toString();
					}
					if (!bIgnore || objVal != null)
					{
						// 去除null
						sbufParams.append("&" + strItemName.toUpperCase() + "=" + strResult);
					}
				}

				// 返回结果
				return sbufParams.toString();
			}
			catch (Exception e)
			{
				//
			}
		}

		// 返回结果
		return "";
	}

	/**
	 * 将map对象转换为&key=value格式的参数形式。
	 * 
	 * @param o
	 * @param bIgnore
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String map2Paramter(Map mapMap, boolean bIgnore)
	{
		if (mapMap != null)
		{
			Iterator objIt = mapMap.keySet().iterator();
			StringBuffer sbufParams = new StringBuffer();
			
			while (objIt.hasNext())
			{
				String strKey = (String) objIt.next();
				Object objValue = mapMap.get(strKey);
				
				if (!bIgnore || objValue != null)
				{
					sbufParams.append("&" + strKey.toUpperCase() + "=" + objValue);// 现在map统一存放字符串,统一处理为字符串,key大写
				}
			}

			// 返回结果
			return sbufParams.toString();
		}

		// 返回结果
		return "";
	}

}
