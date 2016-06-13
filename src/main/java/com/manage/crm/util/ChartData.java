package com.manage.crm.util;

public class ChartData
{
    /**
     * 具体的数据。如折线图的拐点
     */
    private String strFlexData;
    
    /**
     * x轴要显示的数据。
     */
    private String strXAxis;

    public ChartData(){}
    
    public ChartData(String strCurrentTime, int nTodayCount)
    {
        this.strFlexData = String.valueOf(nTodayCount);
        this.strXAxis = strCurrentTime;
    }

    public String getStrFlexData()
    {
        return strFlexData;
    }

    public void setStrFlexData(String strFlexData)
    {
        this.strFlexData = strFlexData;
    }

    public String getStrXAxis()
    {
        return strXAxis;
    }

    public void setStrXAxis(String strXAxis)
    {
        this.strXAxis = strXAxis;
    }
}
