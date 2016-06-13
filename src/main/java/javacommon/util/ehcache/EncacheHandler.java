package javacommon.util.ehcache;

import java.io.InputStream;
import java.util.Collection;

import javacommon.core.Config;
import javacommon.util.file.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存工具类 1: EnCache缓存工具类 2: 可以用SP来管理/配置成单利的形式。
 * 
 * @author wangzg
 * 
 */
public class EncacheHandler
{
	/**
	 * 日志对象
	 */
	protected static final Logger logger = LoggerFactory.getLogger(EncacheHandler.class);

	/**
	 * 关联EnCache 的 CacheManager
	 */
	private static CacheManager objManager;

	/**
	 * 关联EnCache 的 Cache
	 */
	private static Cache objCache;

	/**
	 * 静态代码块: 初始化Cache对象。
	 */
	static
	{
		synchronized (CacheManager.class)
		{
			if (objManager == null)
			{
				FileUtils objFileUtils = new FileUtils();
				InputStream objPathCongfgName = objFileUtils.getConfStream(Config.objCOMConfig.getProperty("ehcacheConfFileName"));

				objManager = new CacheManager(objPathCongfgName);
				objCache = objManager.getCache(Config.objCOMConfig.getProperty("predefinedCacheName"));
			}
		}
	}

	/**
	 * 空构造。
	 */
	public EncacheHandler()
	{
	}

	/**
	 * 停止缓存。
	 */
	public static void stop()
	{
		objCache.removeAll();
		objManager.removeCache(Config.objCOMConfig.getProperty("predefinedCacheName"));
		objManager.removalAll();
		objManager.clearAll();
		objManager.shutdown();
		objManager = null;
	}

	/**
	 * 从缓存中取得数据 。
	 * 
	 * @param objKey
	 * @return
	 */
	public static Object get(Object objKey)
	{
		Element objElement = (Element) objCache.get(objKey);
		
		if (objElement == null)
		{
			return null;
		}
		else
		{
			// 返回结果
			return objElement.getObjectValue();
		}

	}

	/**
	 * 向缓存中保存数据 。
	 * 
	 * @param objKey
	 * @param objValue
	 */
	public static void put(Object objKey, Object objValue)
	{
		Element objElement = new Element(objKey, objValue);
		
		objCache.put(objElement);
	}

	/**
	 * 从缓存中移除元素 。
	 * 
	 * @param objKey
	 */
	public static void remove(Object objKey)
	{
		objCache.remove(objKey);
	}

	/**
	 * 拿到缓存的数量 。
	 * 
	 * @return
	 */
	public static long size()
	{
		return objCache.getMemoryStoreSize();
	}

	/**
	 * 清除缓存 。
	 */
	public static void clear()
	{
		objCache.removeAll();
	}

	/**
	 * 缓存中是否包含Key的数据 。
	 * 
	 * @param objKey
	 * @return
	 */
	public static boolean contain(Object objKey)
	{
		return objCache.isKeyInCache(objKey);
	}

	/**
	 * 拿到所有的Key集合。
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Collection keySet()
	{
		return objCache.getKeys();
	}

}