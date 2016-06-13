package javacommon.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取当前类配置的泛型类型。
 * 
 * <pre>
 * |
 * </pre>
 * 
 * <br>
 * JDK Version：1.6
 * 
 * @author wangzg
 *@version 2.0
 *@see
 *@since 1.0
 * 
 */
public class GenericsUtils
{
	
	/**
	 * 获取类。
	 * @param objClazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class getGeneric(Class objClazz)
	{
		return getGeneric(objClazz, 1);
	}

	/**
	 * 获取类。
	 * @param objClazz
	 * @param nIndex
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Class getGeneric(Class objClazz, int nIndex)
	{
		Type objGenType = objClazz.getGenericSuperclass();

		if (objGenType instanceof ParameterizedType)
		{
			Type[] arrayParams = ((ParameterizedType) objGenType).getActualTypeArguments();

			if ((arrayParams != null) && (arrayParams.length >= nIndex))
			{
				return (Class) arrayParams[nIndex - 1];
			}
		}
		return null;
	}
}
