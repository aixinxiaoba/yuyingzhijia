package javacommon.util.ehcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存处理器, 只用于action层,目前是存于session, 用一次就清空。
 * 
 * @author wangzg
 * @version 2.0
 * @see
 * @since 1.0
 * 
 */
public class MemoryCacheHandler
{
	// 全局缓存,防止瞬间重复提交,另外需要注意的是交易完要清空,考虑并发
	public final static Map<String, String> lstCacheRecords = new ConcurrentHashMap<String, String>();

	/**
	 * Constructor:---Private。
	 */
	private MemoryCacheHandler()
	{
		super();
	}

	/**
	 * 判断是否已接受处理。
	 * 
	 * @param rebateRequest
	 * @return
	 */
	public static boolean isProcessed(String strKey)
	{
		if (lstCacheRecords.containsKey(strKey))
		{
			return true;
		}

		// 返回结果
		return false;
	}

	/**
	 * 将记录存入缓存。
	 * 
	 * @param rebateRequest
	 * @return
	 */
	public static boolean addItem(String strKey)
	{
		return lstCacheRecords.put(strKey, "") == null;
	}

	/**
	 * 要注意清空,否则内存溢出。
	 * 
	 * @param rebateRequest
	 */
	public static void removeItem(String strKey)
	{
		lstCacheRecords.remove(strKey);
	}
}
