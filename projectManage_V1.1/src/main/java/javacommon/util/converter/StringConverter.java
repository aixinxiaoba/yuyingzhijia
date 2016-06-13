package javacommon.util.converter;

import org.apache.commons.beanutils.Converter;

/**
 * 自己实现的StringConverter 1:---可以代替BeanUtils中的org.apache.commons.beanutils.converters.StringConverter。
 * 
 * @author wangzg
 * 
 */
@SuppressWarnings("unchecked")
public final class StringConverter implements Converter
{
	/**
	 * convert方法。
	 */
	@Override
	public Object convert(Class objType, Object objValue)
	{
		if (objValue == null || "".equals(objValue.toString()))
		{
			return (String) null;
		}
		else
		{
			return objValue.toString();
		}
	}
}
