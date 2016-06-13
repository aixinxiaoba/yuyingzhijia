package com.manage.crm.util;

/**
 * 支付工具类。
 * @ClassName:     PayOnlineUtil.java
 * @author         YangZhenhua
 * @Date           2013-7-16 下午03:28:20
 */
public class PayOnlineUtil
{
    /**
     * 生成hmac方法。
     * 
     * @param strP0_Cmd 业务类型
     * @param strP1_MerId 商户编号
     * @param strP2_Order 商户订单ID号
     * @param strP3_Amt 支付金额
     * @param strP4_Cur 交易币种
     * @param strP5_Pid 商品名称
     * @param strP6_Pcat 商品种类
     * @param strP7_Pdesc 商品描述
     * @param strP8_Url 商户接受支付成功后的地址
     * @param strP9_SAF 送货地址
     * @param strPa_MP 商户扩展信息
     * @param strPd_FrpId 银行编码
     * @param strPr_NeedResponse 应答机制
     * @param strKeyValue 商户密钥
     * @return
     */
    public static String createHmac(String strP0_Cmd, String strP1_MerId, String strP2_Order, String strP3_Amt, String strP4_Cur, String strP5_Pid, String strP6_Pcat, String strP7_Pdesc, String strP8_Url, String strP9_SAF, String strPa_MP, String strPd_FrpId, String strPr_NeedResponse, String strKeyValue)
    {
        StringBuffer sbufValue = new StringBuffer();
        String strResultValue;
        
        sbufValue.append(strP0_Cmd);
        sbufValue.append(strP1_MerId);
        sbufValue.append(strP2_Order);
        sbufValue.append(strP3_Amt);
        sbufValue.append(strP4_Cur);
        sbufValue.append(strP5_Pid);
        sbufValue.append(strP6_Pcat);
        sbufValue.append(strP7_Pdesc);
        sbufValue.append(strP8_Url);
        sbufValue.append(strP9_SAF);
        sbufValue.append(strPa_MP);
        sbufValue.append(strPd_FrpId);
        sbufValue.append(strPr_NeedResponse);
        strResultValue = DigestUtil.hmacSign(sbufValue.toString(), strKeyValue);
        
        return strResultValue;
    }

    /**
     * 返回校验hmac方法。
     * 
     * @param strHmac 支付网关发来的加密验证码
     * @param strP1_MerId 商户编号
     * @param strR0_Cmd 业务类型
     * @param strr1_Code 支付结果
     * @param strR2_TrxId 易宝支付交易流水号
     * @param strR3_Amt 支付金额
     * @param strR4_Cur 交易币种
     * @param strR5_Pid 商品名称
     * @param strR6_Order 商户订单号
     * @param strR7_Uid 易宝支付会员ID
     * @param strR8_MP 商户扩展信息
     * @param strR9_BType 交易结果返回类型
     * @param strKeyValue 密钥
     * @return
     */
    public static boolean verifyCallback(String strHmac, String strP1_MerId, String strR0_Cmd, String strR1_Code, String strR2_TrxId, String strR3_Amt, String strR4_Cur, String strR5_Pid, String strR6_Order, String strR7_Uid, String strR8_MP, String strR9_BType, String strKeyValue)
    {
        StringBuffer sbufValue = new StringBuffer();
        String strResultValue;
        
        // 商户编号
        sbufValue.append(strP1_MerId);
        // 业务类型
        sbufValue.append(strR0_Cmd);
        // 支付结果
        sbufValue.append(strR1_Code);
        // 易宝支付交易流水号
        sbufValue.append(strR2_TrxId);
        // 支付金额
        sbufValue.append(strR3_Amt);
        // 交易币种
        sbufValue.append(strR4_Cur);
        // 商品名称
        sbufValue.append(strR5_Pid);
        // 商户订单号
        sbufValue.append(strR6_Order);
        // 易宝支付会员ID
        sbufValue.append(strR7_Uid);
        // 商户扩展信息
        sbufValue.append(strR8_MP);
        // 交易结果返回类型
        sbufValue.append(strR9_BType);
        strResultValue = DigestUtil.hmacSign(sbufValue.toString(), strKeyValue);

        if (strHmac.equals(strResultValue))
        {
            return true;
        }
        return false;
    }
}
