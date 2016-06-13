package com.manage.crm.util;

/**
 * 封装银行返回结果。
 * 
 * Time 2013年7月25日8:58:35
 * 
 * @author jiangwei
 * 
 */
public class QueryResult
{
    private String strR0_Cmd; // 业务类型
    private String strR1_Code; // 查询结果
    private String strR2_TrxId; // 易宝支付交易流水号
    private String strR3_Amt; // 支付金额
    private String strR4_Cur; // 交易币种
    private String strR5_Pid; // 商品名称
    private String strR6_Order; // 商户订单号
    private String strR8_MP; // 商户扩展信息
    private String strRb_PayStatus; // 支付状态
    private String strRc_RefundCount; // 已退款次数
    private String strRd_RefundAmt; // 已退款金额
    private String strHmac; // 密钥

    /**
     * 密钥。
     * 
     * @return
     */
    public String getHmac()
    {
        return strHmac;
    }

    /**
     * 设置密钥。
     * 
     * @param hmac
     */
    public void setHmac(String hmac)
    {
        this.strHmac = hmac;
    }

    /**
     * 业务类型。
     * 
     * @return
     */
    public String getR0_Cmd()
    {
        return strR0_Cmd;
    }

    /**
     * 业务类型。
     * 
     * @param rd_RefundAmt
     */
    public void setR0_Cmd(String cmd)
    {
        strR0_Cmd = cmd;
    }

    /**
     * 查询结果。
     * 
     * @return
     */
    public String getR1_Code()
    {
        return strR1_Code;
    }

    /**
     * 查询结果。
     * 
     * @param rd_RefundAmt
     */
    public void setR1_Code(String code)
    {
        strR1_Code = code;
    }

    /**
     * 易宝支付交易流水号。
     * 
     * @return
     */
    public String getR2_TrxId()
    {
        return strR2_TrxId;
    }

    /**
     * 易宝支付交易流水号。
     * 
     * @param rd_RefundAmt
     */
    public void setR2_TrxId(String trxId)
    {
        strR2_TrxId = trxId;
    }

    /**
     * 支付金额。
     * 
     * @return
     */
    public String getR3_Amt()
    {
        return strR3_Amt;
    }

    /**
     * 支付金额。
     * 
     * @param rd_RefundAmt
     */
    public void setR3_Amt(String amt)
    {
        strR3_Amt = amt;
    }

    /**
     * 交易币种。
     * 
     * @return
     */
    public String getR4_Cur()
    {
        return strR4_Cur;
    }

    /**
     * 交易币种。
     * 
     * @param rd_RefundAmt
     */
    public void setR4_Cur(String cur)
    {
        strR4_Cur = cur;
    }

    /**
     * 商品名称。
     * 
     * @return
     */
    public String getR5_Pid()
    {
        return strR5_Pid;
    }

    /**
     * 商品名称。
     * 
     * @param rd_RefundAmt
     */
    public void setR5_Pid(String pid)
    {
        strR5_Pid = pid;
    }

    /**
     * 商户订单号。
     * 
     * @return
     */
    public String getR6_Order()
    {
        return strR6_Order;
    }

    /**
     * 商户订单号。
     * 
     * @param rd_RefundAmt
     */
    public void setR6_Order(String order)
    {
        strR6_Order = order;
    }

    /**
     * 商户扩展信息。
     * 
     * @return
     */
    public String getR8_MP()
    {
        return strR8_MP;
    }

    /**
     * 商户扩展信息。
     * 
     * @param rd_RefundAmt
     */
    public void setR8_MP(String r8_mp)
    {
        strR8_MP = r8_mp;
    }

    /**
     * 支付状态。
     * 
     * @return
     */
    public String getRb_PayStatus()
    {
        return strRb_PayStatus;
    }

    /**
     * 支付状态。
     * 
     * @param rd_RefundAmt
     */
    public void setRb_PayStatus(String rb_PayStatus)
    {
        this.strRb_PayStatus = rb_PayStatus;
    }

    /**
     * 已退款次数。
     * 
     * @return
     */
    public String getRc_RefundCount()
    {
        return strRc_RefundCount;
    }

    /**
     * 已退款次数。
     * 
     * @param rd_RefundAmt
     */
    public void setRc_RefundCount(String rc_RefundCount)
    {
        this.strRc_RefundCount = rc_RefundCount;
    }

    /**
     * 已退款金额。
     * 
     * @return
     */
    public String getRd_RefundAmt()
    {
        return strRd_RefundAmt;
    }

    /**
     * 已退款金额。
     * 
     * @param rd_RefundAmt
     */
    public void setRd_RefundAmt(String rd_RefundAmt)
    {
        this.strRd_RefundAmt = rd_RefundAmt;
    }
}