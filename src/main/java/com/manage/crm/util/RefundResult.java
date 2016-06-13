package com.manage.crm.util;

/**
 * 退款请求实体类。
 * 
 * @author jiangwei
 * 
 */
public class RefundResult
{
    private String strR0_Cmd; // 请求命令
    private String strR1_Code; // 请求结果
    private String strR2_TrxId; // 交易流水号
    private String strR3_Amt; // 交易金额
    private String strR4_Cur; // 交易币种
    private String strHmac; // 签名校验

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
     * @param cmd
     */
    public void setR0_Cmd(String cmd)
    {
        strR0_Cmd = cmd;
    }

    /**
     * 退款结果。
     * 
     * @return
     */
    public String getR1_Code()
    {
        return strR1_Code;
    }

    /**
     * 退款结果。
     * 
     * @param code
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
     * @param trxId
     */
    public void setR2_TrxId(String trxId)
    {
        strR2_TrxId = trxId;
    }

    /**
     * 退款金额。
     * 
     * @return
     */
    public String getR3_Amt()
    {
        return strR3_Amt;
    }

    /**
     * 退款金额。
     * 
     * @param amt
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
     * @param cur
     */
    public void setR4_Cur(String cur)
    {
        strR4_Cur = cur;
    }

    /**
     * 签名数据。
     * 
     * @return
     */
    public String getHmac()
    {
        return strHmac;
    }

    /**
     * 签名数据。
     * 
     * @param hmac
     */
    public void setHmac(String hmac)
    {
        this.strHmac = hmac;
    }
}
