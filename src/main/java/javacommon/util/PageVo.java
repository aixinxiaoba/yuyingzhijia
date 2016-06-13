package javacommon.util;

/**
 * pageVo，封装分页信息。
 * @author Administrator
 *
 */
public class PageVo
{

	public static final int PAGE_SIZE = 10;
	public static final int CURRENT_PAGE = 1;
	public static final long COUNT_NUM = 0L;
	public static final String FORM_NAME = "dataListForm";
	public static final int PAGE_COUNT = 0;
	public static final int SHOW_NUM = 5;
	private int nPageSize = PAGE_SIZE;
	private int nCurrentPage = CURRENT_PAGE;
	private long lCountNum = COUNT_NUM;
	private String strFormName = FORM_NAME;
	private int nPageCount = PAGE_COUNT;
	private boolean bIsFirst;
	private boolean bIsLast;
	private boolean bHasLeft;
	private boolean bHasRight;
	private boolean bHasData;
	private int nShowNum;
	private int nShowStart;
	private int nShowEnd;

	/**
	 * 总记录数。
	 * @param pageSize 每页显示记录数。
	 * @param currentPage当前页码。
	 * @return TheWkPageVo实例。
	 */
	public static PageVo getInstance(long lCountNum, int nPageSize, int nCurrentPage)
	{
		return getInstance(lCountNum, nPageSize, nCurrentPage, SHOW_NUM);
	}

	/**
	 * @param countNum 总记录数。
	 * @param pageSize 每页显示记录数。
	 * @param currentPage 当前页码。
	 * @param showNum 如果是按页数点击，则需要显示多少个点击链接。
	 * @return TheWkPageVo实例。
	 */
	public static PageVo getInstance(long lCountNum, int nPageSize, int nCurrentPage, int nShowNum)
	{
		return getInstance(lCountNum, nPageSize, nCurrentPage, nShowNum, FORM_NAME);
	}

	/**
	 * @param countNum 总记录数。
	 * @param pageSize 每页显示记录数。
	 * @param currentPage 当前页码。
	 * @param formName 分页表单名称。
	 * @return TheWkPageVo实例。
	 */
	public static PageVo getInstance(long lCountNum, int nPageSize, int nCurrentPage, String strFormName)
	{
		return getInstance(lCountNum, nPageSize, nCurrentPage, SHOW_NUM, strFormName);
	}

	/**
	 * @param countNum 总记录数。
	 * @param pageSize 每页显示记录数。
	 * @param currentPage 当前页码。
	 * @param formName 分页表单名称。
	 * @param showNum 如果是按页数点击，则需要显示多少个点击链接。
	 */
	public static PageVo getInstance(long lCountNum, int nPageSize, int nCurrentPage, int nShowNum, String strFormName)
	{
		return createInstance(lCountNum, nPageSize, nCurrentPage, nShowNum, strFormName);
	}

	/**
	 * 初始化各个参数,设置了PageUtil的set方法后，必须调用。
	 */
	private void initPageUtil()
	{
		this.initCountNum();
		this.initPageSize();
		this.initPageCount();
		this.initCurrentPage();
		this.initShowNum();
		this.initFormName();
		this.initFirst();
		this.initLast();
		this.initHasLeft();
		this.initHasRight();
		this.initHasData();
		this.initShowStartAndEnd();
	}

	/**
	 * 空构造方法。
	 */
	public PageVo()
	{

	}

	/**
	 * @param countNum 总记录数。
	 * @param pageSize 每页显示记录数。
	 * @param currentPage 当前页码。
	 * @param formName 分页表单名称。
	 * @return PageUtil实例。
	 */
	public PageVo(long lCountNum, int nPageSize, int nCurrentPage, int nShowNum, String strFormName)
	{
		this.lCountNum = lCountNum;
		this.nPageSize = nPageSize;
		this.nCurrentPage = nCurrentPage;
		this.strFormName = strFormName;
		this.nShowNum = nShowNum;
	}

	/**
	 * nothing。
	 */
	private static PageVo createInstance(long lCountNum, int nPageSize, int nCurrentPage, int nShowNum, String strFormName)
	{
		PageVo objPgeUtil = new PageVo(lCountNum, nPageSize, nCurrentPage, nShowNum, strFormName);
		
		objPgeUtil.initPageUtil();
		return objPgeUtil;
	}

	/**
	 * 每页显示记录数。
	 */
	public int getNPageSize()
	{
		return this.nPageSize;
	}

	/**
	 * 每页显示记录数。
	 */
	public void setNPageSize(int nPageSize)
	{
		this.nPageSize = nPageSize;
		this.initPageUtil();
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
		this.initPageUtil();
	}

	/**
	 * 总记录数。
	 */
	public long getLCountNum()
	{
		return this.lCountNum;
	}

	/**
	 * 总记录数。
	 */
	public void setLCountNum(long lCountNum)
	{
		this.lCountNum = lCountNum;
		this.initPageUtil();
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
	 * 发送方撒。
	 */
	public boolean getBHasData()
	{
		return this.bHasData;
	}

	/**
	 * 是否首页。
	 */
	public boolean getBIsFirst()
	{
		return this.bIsFirst;
	}

	/**
	 * 是否尾页。
	 */
	public boolean getBIsLast()
	{
		return this.bIsLast;
	}
	
	/**
	 * 是否首页。
	 */
	public boolean getbIsFirst()
	{
		return this.bIsFirst;
	}

	/**
	 * 是否尾页。
	 */
	public boolean getbIsLast()
	{
		return this.bIsLast;
	}

	/**
	 * 是否有上一页。
	 */
	public boolean getBHasLeft()
	{
		return this.bHasLeft;
	}

	/**
	 * 是否有下一页。
	 */
	public boolean getBHasRight()
	{
		return this.bHasRight;
	}
	
	/**
	 * 是否有上一页。
	 */
	public boolean getbHasLeft()
	{
		return this.bHasLeft;
	}

	/**
	 * 是否有下一页。
	 */
	public boolean getbHasRight()
	{
		return this.bHasRight;
	}

	/**
	 * 发送方撒。
	 */
	public int getNShowNum()
	{
		return this.nShowNum;
	}

	/**
	 * @return。
	 */
	public void setNShowNum(int nShowNum)
	{
		this.nShowNum = nShowNum;
		this.initPageUtil();
	}

	/**
	 * @return。
	 */
	public int getNShowStart()
	{
		return this.nShowStart;
	}

	/**
	 * @return。
	 */
	public int getnShowEnd()
	{
		return this.nShowEnd;
	}

	/**
	 * 发送方撒。
	 */
	private void initShowNum()
	{
		if (this.nShowNum <= 0)
		{
			this.nShowNum = SHOW_NUM;
		}
		if (this.nShowNum > this.nPageCount)
		{
			this.nShowNum = this.nPageCount;
		}
	}

	/**
	 * 发送方撒。
	 */
	private void initCurrentPage()
	{
		if (this.nCurrentPage <= 1L)
		{
			this.nCurrentPage = CURRENT_PAGE;
		}
		else if (this.nCurrentPage > this.nPageCount)
		{
			this.nCurrentPage = this.nPageCount;
		}
	}

	/**
	 * 发送方撒。
	 */
	private void initPageSize()
	{
		if (this.nPageSize <= 0L)
		{
			this.nPageSize = PAGE_SIZE;
		}
	}

	/**
	 * 发送方撒。
	 */
	private void initFormName()
	{
		if (this.strFormName == null || "".equals(this.strFormName.trim()))
		{
			this.strFormName = FORM_NAME;
		}
	}

	/**
	 * 发送方撒。
	 */
	private void initPageCount()
	{
		this.nPageCount = this.lCountNum % this.nPageSize == 0 ? (int) (this.lCountNum / this.nPageSize) : (int) (this.lCountNum / this.nPageSize) + 1;
	}

	/**
	 * 发送方撒。
	 */
	private void initCountNum()
	{
		if (this.lCountNum < 0L)
		{
			this.lCountNum = COUNT_NUM;
		}
	}

	/**
	 * 发送方撒。
	 */
	private void initHasData()
	{
		this.bHasData = this.lCountNum > 0;
	}

	/**
	 * 发送方撒。
	 */
	private void initFirst()
	{
		this.bIsFirst = this.nCurrentPage == 1;
	}

	/**
	 * 发送方撒。
	 */
	private void initLast()
	{
		this.bIsLast = (this.nCurrentPage == this.nPageCount);
	}

	/**
	 * 发送方撒。
	 */
	private void initHasRight()
	{
		this.bHasRight = this.nCurrentPage < this.nPageCount;
	}

	/**
	 * 发送方撒。
	 */
	private void initHasLeft()
	{
		this.bHasLeft = this.nCurrentPage > 1;
	}

	/**
	 * 发送方撒。
	 */
	private void initShowStartAndEnd()
	{
		int nFirstHalfNum = this.getFirstHalfNum(this.nShowNum);
		int nLastHalfNum = this.getLastHalfNum(this.nShowNum);
		
		if (this.nCurrentPage <= nFirstHalfNum)
		{
			this.nShowStart = 1;
			this.nShowEnd = this.nShowNum;
		}
		else
		{
			this.nShowStart = this.nCurrentPage - nLastHalfNum + 1;
			this.nShowEnd = this.nCurrentPage + nFirstHalfNum;
		}
		if (this.nShowEnd > this.nPageCount)
		{
			this.nShowEnd = this.nPageCount;
			this.nShowStart = this.nPageCount - this.nShowNum + 1;
		}
	}

	/**
	 * 发送方撒。
	 */
	private int getFirstHalfNum(int nNum)
	{
		return nNum / 2;
	}

	/**
	 * 发送方撒。
	 */
	private int getLastHalfNum(int nNum)
	{
		return nNum - this.getFirstHalfNum(nNum);
	}

	/**
	 * 发送方撒。
	 */
	@Override
	public String toString()
	{
		StringBuilder objSb = new StringBuilder();
		
		objSb.append("总记录数countNum：");
		objSb.append(this.lCountNum);
		objSb.append(";总页数pageCount:");
		objSb.append(this.nPageCount);
		objSb.append(";当前页数currentPage：");
		objSb.append(this.nCurrentPage);
		objSb.append(";每页显示pageSize：");
		objSb.append(this.nPageSize);
		objSb.append(";是否有下一页：");
		objSb.append(this.bHasRight);
		objSb.append(";是否有上一页：");
		objSb.append(this.bHasLeft);
		return objSb.toString();
	}
}
