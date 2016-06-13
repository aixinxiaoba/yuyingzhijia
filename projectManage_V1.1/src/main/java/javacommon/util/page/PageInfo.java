package javacommon.util.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javacommon.util.StringUtils;

/**
 * 分页信息工具类 。
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PageInfo implements Serializable
{
	/**
	 *  序列化ID 。
	 */
	private static final long serialVersionUID = 7425686824578584180L;

	/**
	 * 分页大小 。
	 */
	public static final int PAGE_SIZE = 20;
	
	/**
	 * 当前页 。
	 */
	public static final int CURRENT_PAGE = 1;
	
	/**
	 * 总数量 。
	 */
	public static final long COUNT_NUM = 0L;
	
	/**
	 * 表单名 。
	 */
	public static final String FORM_NAME = "listForm";
	
	/**
	 * 页数 。
	 */
	public static final int PAGE_COUNT = 0;
	
	/**
	 *  数据 。
	 */
	private List lstData = new ArrayList();
	
	/**
	 *  分页大小 。
	 */
	private int nPageSize = PAGE_SIZE;
	
	/**
	 *  当前页  。
	 */
	private int nCurrentPage = CURRENT_PAGE;
	
	/**
	 * 总数量 。
	 */
	private long lCountNum = COUNT_NUM;
	
	/**
	 * 表单名 。
	 */
	private String strFormName = FORM_NAME;
	
	/**
	 * 页数 。
	 */
	private int nPageCount = PAGE_COUNT;

	/**
	 * 查询的条件：---用于判断查询是否改变了  。
	 */
	private String strCondition;
	
	/**
	 * 没有指定数据的默认构造函数 。
	 */
	public PageInfo()
	{
	    this(new ArrayList());
	}
	
	/**
	 * 根据列表数据的构造函数 。
	 * 
	 * @param paramList
	 */
	public PageInfo(List lstObject)
	{
		this(lstObject, -1, -1);
	}
	
	/**
	 * 根据Set数据的构造函数 。
	 * 
	 * @param lstObject
	 */
	public PageInfo(Set lstObject)
	{
		this(lstObject, -1, -1);
	}
	
	/**
	 * 根据Set数据的构造函数 。
	 * 
	 * @param lstObject
	 * @param nPageSize
	 * @param nCurrentPage
	 */
	public PageInfo(Set lstObject, int nPageSize, int nCurrentPage)
	{
		this(new ArrayList(lstObject), nPageSize, nCurrentPage);
	}
	
	/**
	 * 根据列表数据的构造函数 。
	 * 
	 * @param lstObject
	 * @param nPageSize
	 * @param nCurrentPage
	 */
	public PageInfo(List lstObject, int nPageSize, int nCurrentPage)
	{
		this(lstObject, nPageSize, nCurrentPage, null);
	}
	
	/**
	 * 根据列表数据的构造函数 。
	 * 
	 * @param lstObject
	 * @param nPageSize
	 * @param nCurrentPage
	 */
	public PageInfo(List lstObject, int nPageSize, int nCurrentPage, String strFormName)
	{
		// 处理数据(lstData)和数据总数(lCountNum)
		if (lstObject.size() > 0)
		{
			this.lstData.addAll(lstObject);
			this.lCountNum = lstObject.size();
		}
		
		// 分页大小
		if (nPageSize > 0)
		{
			this.nPageSize = nPageSize;
		}
		
		// 页数
		if (this.lCountNum % this.nPageSize == 0 )
		{
			this.nPageCount = (int)(this.lCountNum / this.nPageSize);
		}
		else
		{
			this.nPageCount = (int)(this.lCountNum / this.nPageSize + 1);
		}
		
		// 当前页
		if (nCurrentPage > 0 && nCurrentPage <= this.nPageCount)
		{
			this.nCurrentPage = nCurrentPage;
		}
		else if (nCurrentPage > this.nPageCount)
		{
			this.nCurrentPage = this.nPageCount;
		}
		
		// 表单名
		if (!StringUtils.isEmpty(strFormName))
		{
			this.strFormName = strFormName;
		}
		
	}

	/**
	 * 拿到当前页的数据 。
	 * 
	 * @return
	 */
	public List getCurrentPageData()
	{
		return getPageData(this.nCurrentPage);
	}
	
	/**
	 * 拿到指定页数的数据 。
	 * 
	 * @param nPageIndex
	 * @return
	 */
	public List getPageData(int nPageIndex)
	{
		// 最后一个和第一页的判断
		if (nPageIndex < 1)
		{
			nPageIndex = 1;
		}
		
		if (nPageIndex > this.nPageCount)
		{
			nPageIndex = this.nPageCount;
		}
		
		if (this.lstData != null && (nPageIndex > 0) && (nPageIndex <= this.nPageCount ))
		{
			int nBegin = (nPageIndex - 1) * this.nPageSize;
			int nCount = (int)((lCountNum - nBegin) < this.nPageSize ?(lCountNum - nBegin) : this.nPageSize);
			
			// 返回结果
			return getPageData(nBegin, nCount);
		}
		
		if (this.lstData != null && this.lstData.size() > 0 )
		{
			return this.lstData;
		}
		
		// 返回结果
		return null;
	}
	
	/**
	 * 拿到指定开始索引和数量的数据  。
	 * 
	 * @param nPageIndex
	 * @return
	 */
	private List getPageData(int nBegin, int nCount)
	{
		// 验证参数 
		if (nBegin < 0 || nCount > this.lCountNum )
		{
			return null;
		}
		
		// 返回结果
		return this.lstData.subList(nBegin, nBegin + nCount);
	}
	
	/**
	 * 判断查询条件是否变化：---即此PageInfo对象是否还能用 。
	 * 
	 * @param strCondition
	 * @return
	 */
	public boolean isConditionChanged(String strCondition)
	{
		// 参数验证
		if (StringUtils.isBlank(strCondition))
		{
			return false;
		}
		else
		{
			return strCondition.trim().equalsIgnoreCase(this.getStrCondition());
		}
	}
	
	/**
	 * 每页显示记录数。
	 */
	public int getnPageSize()
	{
		return this.nPageSize;
	}

	/**
	 * 每页显示记录数。
	 */
	public void setNPageSize(int nPageSize)
	{
		this.nPageSize = nPageSize;
	}

	/**
	 * 当前页码。
	 */
	public int getNCurrentPage()
	{
		return this.nCurrentPage;
	}

	/**
	 * 当前页码。
	 */
	public void setNCurrentPage(int nCurrentPage)
	{
		this.nCurrentPage = nCurrentPage;
	}

	/**
	 * 总记录数。
	 */
	public long getlCountNum()
	{
		return this.lCountNum;
	}
	
	/**
	 * 总记录数。
	 */
	public void setLCountNum(long lCountNum)
	{
		this.lCountNum = lCountNum;
	}

	/**
	 * 表单名称。
	 */
	public String getStrFormName()
	{
		return this.strFormName;
	}

	/**
	 * 表单名称。
	 */
	public void setStrFormName(String strFormName)
	{
		this.strFormName = strFormName;
	}

	/**
	 * 总页数。
	 */
	public int getNPageCount()
	{
		return this.nPageCount;
	}

	/**
	 * 总页数。
	 */
	public void setNPageCount(int nPageCount)
	{
		this.nPageCount = nPageCount;
	}

	/**
	 * 是否有上一页 。
	 */
	public boolean isHasPreviousPage()
	{
		return this.nCurrentPage > 1;
	}

	/**
	 * 是否有下一页 。
	 */
	public boolean isHasNextPage()
	{
		return this.nCurrentPage < this.nPageCount;
	}

	/**
	 * 拿到分页数据的查询条件 。
	 * 
	 * @return
	 */
	public String getStrCondition()
	{
		return strCondition;
	}

	/**
	 * 设置分页数据的查询条件 。
	 * 
	 * @param strCondition
	 */
	public void setStrCondition(String strCondition)
	{
		this.strCondition = strCondition;
	}

}
