package javacommon.core.base;

import java.lang.reflect.Field;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity基类 。
 * 
 * @author wangzg
 *
 */
public abstract class BaseEntity implements java.io.Serializable
{

	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = -7200095849148417467L;

	/**
	 * 日志对象。
	 */
	private static final Logger logger = LoggerFactory.getLogger(BaseEntity.class);

	/**
	 * 时间格式:---日期。
	 */
	protected static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 时间格式:---时间。
	 */
	protected static final String TIME_FORMAT = "HH:mm:ss";

	/**
	 * 时间格式:---日期+时间。
	 */
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 时间格式:---日期+时间+毫秒。
	 */
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

	/**
	 * 简化toString()方法。
	 */
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 把对象封装成JSON的形式。
	 * 
	 * @return
	 */
	public JSONObject toJson()
	{
		String[] arrayTypeArray = null;
		JSONObject objJson = new JSONObject();
		Field[] arrayFields = this.getClass().getDeclaredFields();

		for (Field objField : arrayFields)
		{
			arrayTypeArray = new String[] { "String", "Long", "Integer", "int", "long" };

			if (ArrayUtils.contains(arrayTypeArray, objField.getType().getSimpleName()))
			{
				boolean bAccessible = objField.isAccessible();
				
				objField.setAccessible(true);

				try
				{
					objJson.put(objField.getName(), objField.get(this));
				}
				catch (IllegalArgumentException e)
				{
					logger.error("", e);
					throw new RuntimeException(e.getMessage());
				}
				catch (IllegalAccessException e)
				{
					logger.error("", e);
					throw new RuntimeException(e.getMessage());
				}

				// 还原字段的访问权限
				objField.setAccessible(bAccessible);
			}
		}

		// 返回参数
		return objJson;
	}

}
