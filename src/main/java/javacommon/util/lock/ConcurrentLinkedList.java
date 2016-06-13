package javacommon.util.lock;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 公平锁支持的LinkedList工具类 。
 * 
 * @author wangzg
 * 
 */
@SuppressWarnings("unchecked")
public class ConcurrentLinkedList
{
	/**
	 * 关联公平的读写锁。
	 */
	private final ReentrantReadWriteLock objReadWriteLock = new ReentrantReadWriteLock(true);

	/**
	 * 读锁。
	 */
	private final Lock objReadLock = objReadWriteLock.readLock();

	/**
	 * 写锁。
	 */
	private final Lock objWriteLock = objReadWriteLock.writeLock();

	/**
	 * 链表List。
	 */
	public final LinkedList lstKeyLRUList = new LinkedList();

	/**
	 * 空构造。
	 */
	public ConcurrentLinkedList()
	{
		super();
	}

	/**
	 * 增加元素 。
	 * 
	 * @param objObj
	 */
	public void add(Object objObj)
	{
		objWriteLock.lock();
		try
		{
			lstKeyLRUList.add(objObj);
		}
		finally
		{
			objWriteLock.unlock();
		}
	}

	/**
	 * 在表头增加元素 。
	 * 
	 * @param objKey
	 */
	public void addFirst(Object objKey)
	{
		objWriteLock.lock();
		try
		{
			lstKeyLRUList.addFirst(objKey);
		}
		finally
		{
			objWriteLock.unlock();
		}
	}

	/**
	 * 移到First位置。
	 * 
	 * @param objKey
	 */
	public void moveFirst(Object objKey)
	{
		objWriteLock.lock();
		try
		{
			lstKeyLRUList.remove(objKey);
			lstKeyLRUList.addFirst(objKey);
		}
		catch (Exception e)
		{
		}
		finally
		{
			objWriteLock.unlock();
		}
	}

	/**
	 * 拿到最后一个元素 。
	 * 
	 * @return
	 */
	public Object getLast()
	{
		objReadLock.lock();
		try
		{
			return lstKeyLRUList.getLast();
		}
		finally
		{
			objReadLock.unlock();
		}
	}

	/**
	 * 返回此列表的元素数 。
	 * 
	 * @return
	 */
	public int size()
	{
		objReadLock.lock();
		try
		{
			return lstKeyLRUList.size();
		}
		finally
		{
			objReadLock.unlock();
		}
	}

	/**
	 * 移除链表的第一个元素 。
	 * 
	 * @param objKey
	 */
	public void remove(Object objKey)
	{
		objReadLock.lock();
		try
		{
			lstKeyLRUList.remove(objKey);
		}
		finally
		{
			objReadLock.unlock();
		}
	}

	/**
	 * 清空链表 。
	 */
	public void clear()
	{
		objWriteLock.lock();
		try
		{
			lstKeyLRUList.clear();
		}
		finally
		{
			objWriteLock.unlock();
		}
	}

	/**
	 * 提取缓存中所有数据,并清空 。
	 * 
	 * @param maxCount
	 * @return
	 */
	public synchronized Object[] pollAll()
	{
		int nSize = lstKeyLRUList.size();
		int nIndex = 0;
		Object[] arrayOS = new Object[nSize];
		Iterator<Object> objIt = lstKeyLRUList.iterator();
		Iterator<Object> objItt;
		
		while (objIt.hasNext())
		{
			arrayOS[nIndex] = objIt.next();
			nIndex++;
		}
		
		objItt = lstKeyLRUList.iterator();
		
		while (objItt.hasNext())
		{
			lstKeyLRUList.remove();
		}

		// 返回结果
		return arrayOS;
	}

}
