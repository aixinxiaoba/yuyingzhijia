package com.manage.crm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.core.Config;

import org.apache.log4j.Logger;

/**
 * 银行支付工具类。
 * 
 * @Time 2013年7月25日9:55:05
 * 
 * @author jiangwei
 */
public class PaymentOnlineUtil
{

    /**
     * 日志对象
     */
    private static final Logger logger = Logger.getLogger(PaymentOnlineUtil.class);

    private static String decodeCharset = "GBK";
    private static String refund_Cmd = "RefundOrd";// 退款请求，固定值 ” RefundOrd”
    private static String query_Cmd = "QueryOrdDetail";
    // private static String p1_MerId = Config.objSAASConfig.getProperty("p1_MerId");// 商家ID
    // private static String keyValue = Config.objSAASConfig.getProperty("keyValue");// 商家密钥
    private static String queryRefundAndRefundExtURL = Config.objSAASConfig.getProperty("app.yeepay.queryByOrderAndRefundExtURL");// 退费请求地址 + 退款地址

    /**
     * 生成数据包。
     * 
     * @param messageType
     * @param merchantId
     * @param orderId
     * @param amount
     * @param currency
     * @param productId
     * @param productCat
     * @param productDesc
     * @param merchantCallbackURL
     * @param addressFlag
     * @param sMctProperties 新增加的参数
     * @param pm_Period
     * @param pn_Unit
     * 
     * @param needResponse
     * @param frpID
     * @param keyValue
     * @return
     */
    public static String getReqMd5HmacForOnlinePayment(String messageType, String merchantId, String orderId, String amount, String currency, String productId, String productCat, String productDesc, String merchantCallbackURL, String addressFlag, String sMctProperties,
            String pm_Period, String pn_Unit, String needResponse, String frpID, String keyValue)
    {
        String sNewString = null;
        StringBuffer sbOld = new StringBuffer();

        sbOld.append(messageType);
        // 商户代码易宝给商户分配的唯一标识
        sbOld.append(merchantId);
        // 商户订单号
        sbOld.append(orderId);
        // 扣款金额
        sbOld.append(amount);
        // 交易币种
        sbOld.append(currency);
        // 商品ID
        sbOld.append(productId);
        // 商品类别
        sbOld.append(productCat);
        // 商品描述（此参数可以不用）
        sbOld.append(productDesc);
        // 商户回报url
        sbOld.append(merchantCallbackURL);
        // 是否在易宝留下送货地址
        sbOld.append(addressFlag);
        // 商户扩展信息
        sbOld.append(sMctProperties);
        // 银行编码
        sbOld.append(frpID);
        // 订单有效期 数值
        sbOld.append(pm_Period);
        // 有效期单位 默认为：day
        sbOld.append(pn_Unit);
        // 是否需要应答机制
        sbOld.append(needResponse);

        sNewString = DigestUtil.hmacSign(sbOld.toString(), keyValue);
        return (sNewString);
    }

    /**
     * 验证返回数据包。
     * 
     * @param hmac
     * @param merchantId
     * @param sCmd
     * @param sErrorCode
     * @param sTrxId
     * @param amount
     * @param currency
     * @param productId
     * @param orderId
     * @param userId
     * @param mp
     * @param bType
     * @param keyValue
     * @return
     */
    public static boolean verifyCallback(String hmac, String merchantId, String sCmd, String sErrorCode, String sTrxId, String amount, String currency, String productId, String orderId, String userId, String mp, String bType, String keyValue)
    {
        StringBuffer sbOld = new StringBuffer();
        String sNewString = null;

        // 商户代码易宝给商户分配的唯一标识
        sbOld.append(merchantId);
        // 业务类型
        sbOld.append(sCmd);
        // 扣款结果 该字段值为1时表示扣款成功
        sbOld.append(sErrorCode);
        // 易宝交易流水号
        sbOld.append(sTrxId);
        // 扣款金额
        sbOld.append(amount);
        // 交易币种
        sbOld.append(currency);
        // 商品ID
        sbOld.append(productId);
        // 商户订单号
        sbOld.append(orderId);
        // 易宝会员ID
        sbOld.append(userId);
        // 商户扩展信息
        sbOld.append(mp);
        // 交易结果返回类型
        sbOld.append(bType);
        sNewString = DigestUtil.hmacSign(sbOld.toString(), keyValue);

        if (hmac.equals(sNewString))
        {
            return (true);
        }
        return (false);
    }

    /**
     * 订单退款请求参数。
     * 
     * @param pb_TrxId 易宝支付交易流水号
     * @param p3_Amt 退款金额
     * @param p5_Desc 退款说明
     * @param strP1_MerId 商户编号
     * @param strKeyValue 商户密钥
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean refundByTrxId(String pb_TrxId, String p3_Amt, String p5_Desc, String strP1_MerId, String strKeyValue)
    {
        RefundResult objResult = null;
        String hmac = DigestUtil.getHmac(new String[]
        { refund_Cmd, strP1_MerId, pb_TrxId, p3_Amt, "CNY", p5_Desc }, strKeyValue);
        Map<String, String> reParams = new HashMap<String, String>();
        List<String> responseStr = null;

        reParams.put("p0_Cmd", refund_Cmd);
        reParams.put("p1_MerId", strP1_MerId);
        reParams.put("pb_TrxId", pb_TrxId); // 易宝流水号
        reParams.put("p3_Amt", p3_Amt); // 退款金额
        reParams.put("p4_Cur", "CNY"); // 交易币种
        reParams.put("p5_Desc", p5_Desc);

        // reParams.put("p0_Cmd", refund_Cmd);
        // reParams.put("p1_MerId", p1_MerId);
        // reParams.put("p2_TrxId", p2_TrxId);
        // reParams.put("p3_Desc", p3_Desc);
        // reParams.put("p5_Amt", p5_Amt);
        reParams.put("hmac", hmac);
        try
        {
            logger.info("Begin http communications.data[" + reParams + "]");
            responseStr = HttpUtils.URLGet(queryRefundAndRefundExtURL, reParams);
            logger.info("End http communications.responseStr.data[" + responseStr + "]");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        if (responseStr.size() == 0)
        {
            throw new RuntimeException("No response.");
        }

        objResult = new RefundResult();
        for (int t = 0; t < responseStr.size(); t++)
        {
            String currentResult = (String) responseStr.get(t);
            int i;

            if (currentResult == null || currentResult.equals(""))
            {
                continue;
            }
            try
            {
                URLDecoder.decode(currentResult, decodeCharset);
            }
            catch (UnsupportedEncodingException e)
            {
                throw new RuntimeException(e.getMessage());
            }
            i = currentResult.indexOf("=");
            if (i >= 0)
            {
                String sKey = currentResult.substring(0, i);
                String sValue = currentResult.substring(i + 1);

                if (sKey.equals("r0_Cmd"))
                {
                    logger.info("业务类型:" + sValue);
                    objResult.setR0_Cmd(sValue);
                }
                else if (sKey.equals("r1_Code"))
                {
                    logger.info("退款结果:" + sValue);
                    objResult.setR1_Code(sValue);
                }
                else if (sKey.equals("r2_TrxId"))
                {
                    logger.info("易宝支付交易流水号:" + sValue);
                }
                else if (sKey.equals("r3_Amt"))
                {
                    logger.info("退款金额:" + sValue);
                }
                else if (sKey.equals("r4_Cur"))
                {
                    logger.info("交易币种:" + sValue);
                }
            }
        }

        // 退款成功
        // 2 账户状态无效 7 该订单不支持退款 10 退款金额超限
        // 18 余额不足 50 订单不存在
        if (!objResult.getR1_Code().equals("1"))
        {
            logger.info("退款失败。error code：" + objResult.getR1_Code());
            return false;
        }
        logger.info("退款成功！");
        return true;
    }

    /**
     * 订单查询请求参数。
     * 
     * @param strOrder 商户订单号
     * @param strMerchantID 商户id。
     * @param MerchantKey 商户密钥
     * 
     * @return queryResult
     */
    @SuppressWarnings("unchecked")
    public static QueryResult queryByOrder(String strOrder, String strMerchantID, String strMerchantKey)
    {
        QueryResult objQueryResult = null;
        String strHmac = DigestUtil.getHmac(new String[]
        { query_Cmd, strMerchantID, strOrder }, strMerchantKey);
        String strNewHmac = "";
        Map<String, String> mapReturnParams = new HashMap<String, String>();
        List lstResponseStr = null;

        // 封装参数。
        mapReturnParams.put("p0_Cmd", query_Cmd);
        mapReturnParams.put("p1_MerId", strMerchantID);
        mapReturnParams.put("p2_Order", strOrder);
        mapReturnParams.put("hmac", strHmac);

        try
        {
            logger.info("Begin http communications.data[" + mapReturnParams + "]");
            lstResponseStr = HttpUtils.URLGet(queryRefundAndRefundExtURL, mapReturnParams);
            logger.info("End http communications.responseStr.data[" + lstResponseStr + "]");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        if (lstResponseStr.size() == 0)
        {
            throw new RuntimeException("No response.");
        }
        // 创建结果集，封装数据。
        objQueryResult = new QueryResult();
        for (int t = 0; t < lstResponseStr.size(); t++)
        {
            String strCurrentResult = (String) lstResponseStr.get(t);
            int nIndex;

            if (strCurrentResult == null || strCurrentResult.equals(""))
            {
                continue;
            }
            try
            {
                URLDecoder.decode(strCurrentResult, decodeCharset);
            }
            catch (UnsupportedEncodingException e)
            {
                throw new RuntimeException(e.getMessage());
            }
            nIndex = strCurrentResult.indexOf("=");
            if (nIndex >= 0)
            {
                String strKey = strCurrentResult.substring(0, nIndex);
                String strValue = strCurrentResult.substring(nIndex + 1);

                if (strKey.equals("r0_Cmd"))
                {
                    logger.info("业务类型：" + strValue);
                    objQueryResult.setR0_Cmd(strValue);
                }
                else if (strKey.equals("r1_Code"))
                {
                    logger.info("查询结果：" + strValue);
                    objQueryResult.setR1_Code(strValue);
                }
                else if (strKey.equals("r2_TrxId"))
                {
                    logger.info("易宝支付交易流水号：" + strValue);
                    objQueryResult.setR2_TrxId(strValue);
                }
                else if (strKey.equals("r3_Amt"))
                {
                    logger.info("支付金额：" + strValue);
                    objQueryResult.setR3_Amt(strValue);
                }
                else if (strKey.equals("r4_Cur"))
                {
                    logger.info("交易币种：" + strValue);
                    objQueryResult.setR4_Cur(strValue);
                }
                else if (strKey.equals("r5_Pid"))
                {
                    logger.info("商品名称：" + strValue);
                    objQueryResult.setR5_Pid(strValue);
                }
                else if (strKey.equals("r6_Order"))
                {
                    logger.info("商户订单号：" + strValue);
                    objQueryResult.setR6_Order(strValue);
                }
                else if (strKey.equals("r8_MP"))
                {
                    logger.info("商户扩展信息：" + strValue);
                    objQueryResult.setR8_MP(strValue);
                }
                else if (strKey.equals("rb_PayStatus"))
                {
                    logger.info("支付状态：" + strValue);
                    objQueryResult.setRb_PayStatus(strValue);
                }
                else if (strKey.equals("rc_RefundCount"))
                {
                    logger.info("已退款次数：" + strValue);
                    objQueryResult.setRc_RefundCount(strValue);
                }
                else if (strKey.equals("rd_RefundAmt"))
                {
                    logger.info("已退款金额：" + strValue);
                    objQueryResult.setRd_RefundAmt(strValue);
                }
                else if (strKey.equals("hmac"))
                {
                    objQueryResult.setHmac(strValue);
                }
            }
        }
        // 1：订单存在；2：订单不存在
        if (!objQueryResult.getR1_Code().equals("1") && !objQueryResult.getR1_Code().equals("50"))
        {
            throw new RuntimeException("Query fail.Error code:" + objQueryResult.getR1_Code());
        }

        // 新的加密串。
        strNewHmac = DigestUtil.getHmac(new String[]
        { objQueryResult.getR0_Cmd(), objQueryResult.getR1_Code(), objQueryResult.getR2_TrxId(), objQueryResult.getR3_Amt(), objQueryResult.getR4_Cur(), objQueryResult.getR5_Pid(), objQueryResult.getR6_Order(), objQueryResult.getR8_MP(), objQueryResult.getRb_PayStatus(),
                objQueryResult.getRc_RefundCount(), objQueryResult.getRd_RefundAmt() }, strMerchantKey);
        if (!strNewHmac.equals(objQueryResult.getHmac()))
        {
            throw new RuntimeException("Hmac error.");
        }

        return (objQueryResult);
    }
}