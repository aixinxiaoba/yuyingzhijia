package javacommon.util.json;

import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JSON解析类: 处理时间 。
 * 
 * @author wangzg
 * 
 */
public class DateJsonValueConfig implements JsonValueProcessor
{

	private SimpleDateFormat objSd = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 处理数组。
	 */
	public Object processArrayValue(Object objValue, JsonConfig objJsonConfig)
	{
		return null;
	}

	/**
	 * 处理对象。
	 */
	public Object processObjectValue(String strKey, Object objValue, JsonConfig objJsonConfig)
	{
		return process(objValue);
	}

	/**
	 * process方法，格式化数据。
	 * @param objObj
	 * @return
	 */
	private Object process(Object objObj)
	{
		if (objObj == null)
		{
			return "";
		}
		else
		{
			return objSd.format(objObj);
		}
	}
}
