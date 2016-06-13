package com.manage.crm.util;

import java.util.List;

public class Pagination<T>
{
    /**
     * session失效，对应responseType
     */
    public static final int RESPONSE_SESSION_INVALIDATION = 2;
    
    /**
     * 每页显示5条数据。
     */
    public static final int PAGE_SIZE_5 = 5;
    
    /**
     * 每页显示9条数据。
     */
    public static final int PAGE_SIZE_9 = 9;
    
    /**
     * 每页显示7条数据。
     */
    public static final int PAGE_SIZE_CONTENT = 8;
    
    /**
     * 每页显示15条数据。
     */
    public static final int PAGE_SIZE_RIGHT = 15;
    
    /**
     * 每页显示6条数据。
     */
    public static final int PAGE_SIZE_6 = 6;

    private int pageNo = 1;// 当前页码
    private long total;// 数据个数
    private int pageSize = 8;// 每页显示的个数
    private long maxPage;// 总页码
    private String errorMsg; // 错误信息
    private int responseType = 1; // 响应类型 1为成功 2为session失效

    // private int upperLimit;// 上限
    // private int lowerLimit;// 下限
    private List<T> rows;// 查询出的数据

    public Pagination() {}

    /**
     * 构造函数 -- 设置响应类型。
     * @param responseType
     */
    public Pagination(int responseType)
    {
        this.responseType = responseType;
    }
    
    /**
     * 构造函数 -- 设置错误信息。
     * @param responseType
     */
    public Pagination(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }
    
    /**
     * 设置页数。
     * 
     * @param responseType
     */
    public Pagination(int pageSize, int pageNo)
    {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    /**
     * 得到上限
     * 
     * @return
     */
    public int getLastResult()
    {
        return pageNo * pageSize;
    }

    public long getMaxPage()
    {
        maxPage = (this.getTotal() - 1) / pageSize + 1;
        return maxPage;
    }

    /*
     * 得到下限
     */
    public int getFirstResult()
    {
        return (pageNo - 1) * pageSize;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public long getTotal()
    {
        return total;
    }

    public void setMaxPage(int maxPage)
    {
        this.maxPage = maxPage;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    /**
     * 获取rows。
     * 
     * @return the rows
     */
    public List<T> getRows()
    {
        return rows;
    }

    /**
     * 设置rows。
     * 
     * @param rows the rows to set
     */
    public void setRows(List<T> rows)
    {
        this.rows = rows;
    }

    /**
     * 拿到指定开始索引和数量的数据 。
     * 
     * @param nPageIndex
     * @return
     */
    private List<T> getPageData(List<T> lstObject, int nBegin, int nCount)
    {
        // 验证参数
        if (nBegin < 0 || nCount > this.total)
        {
            return null;
        }

        // 返回结果
        return lstObject.subList(nBegin, nBegin + nCount);
    }

    /**
     * 拿到指定页数的数据 。
     * 
     * @param this.pageNo
     * @return
     */
    public void setRowsSimple(List<T> lstObject)
    {
        // 最后一个和第一页的判断
        if (this.pageNo < 1)
        {
            this.pageNo = 1;
        }

        if (this.pageNo > getMaxPage())
        {
            this.pageNo = Integer.parseInt(getMaxPage() + "");
        }

        if (lstObject != null && (this.pageNo > 0) && (this.pageNo <= getMaxPage()))
        {
            int nBegin = (this.pageNo - 1) * this.pageSize;
            int nCount = (int) ((this.total - nBegin) < this.pageSize ? (this.total - nBegin) : this.pageSize);

            this.rows = lstObject.subList(nBegin, nBegin + nCount);
        }
        else if (lstObject != null && lstObject.size() > 0)
        {
            this.rows = lstObject;
        }
    }

    // public String getMessage()
    // {
    // return message;
    // }
    //
    // public void setMessage(String message)
    // {
    // this.message = message;
    // }

    public int getResponseType()
    {
        return responseType;
    }

    public void setResponseType(int responseType)
    {
        this.responseType = responseType;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }
}
