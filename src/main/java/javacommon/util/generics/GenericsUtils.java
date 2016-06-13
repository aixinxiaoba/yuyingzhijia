package javacommon.util.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取当前类配置的泛型类型。
 * 
 * @author wangzg
 * 
 */
public class GenericsUtils
{
	/**
	 * 获取当前类配置的泛型类型:---默认只能第一个 。
	 * 
	 * @param objClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class getGeneric(Class objClass)
	{
		return getGeneric(objClass, 1);
	}

	/**
	 * 获取当前类配置的泛型类型 。
	 * 
	 * @param objClass
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Class getGeneric(Class objClass, int nIndex)
	{
		Type objGenType = objClass.getGenericSuperclass();

		if (objGenType instanceof ParameterizedType)
		{
			Type[] arrayParams = ((ParameterizedType) objGenType).getActualTypeArguments();

			if ((arrayParams != null) && (arrayParams.length >= nIndex))
			{
				return (Class) arrayParams[nIndex - 1];
			}
		}

		// 没有就返回null
		return null;
	}

}
