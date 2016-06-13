package com.manage.crm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 编码工具类。
 * @ClassName:     DigestUtil.java
 * @author         YangZhenhua
 * @Date           2013-7-16 下午03:27:50
 */
public class DigestUtil
{

    private static String strEncodingCharset = "UTF-8";

    /**
     * hmacSign。
     * @Title:        hmacSign  
     * @author        YangZhenhua
     * @Date          2013-7-16 下午03:27:41
     */
    public static String hmacSign(String strAValue, String strAKey)
    {
        byte arrayK_ipad[] = new byte[64];
        byte arrayK_opad[] = new byte[64];
        byte arrayKeyb[];
        byte arrayValue[];
        MessageDigest objMd = null;
        byte arrayDg[];

        try
        {
            arrayKeyb = strAKey.getBytes(strEncodingCharset);
            arrayValue = strAValue.getBytes(strEncodingCharset);
        }
        catch (UnsupportedEncodingException objException)
        {
            arrayKeyb = strAKey.getBytes();
            arrayValue = strAValue.getBytes();
        }

        Arrays.fill(arrayK_ipad, arrayKeyb.length, 64, (byte) 54);
        Arrays.fill(arrayK_opad, arrayKeyb.length, 64, (byte) 92);
        for (int i = 0; i < arrayKeyb.length; i++)
        {
            arrayK_ipad[i] = (byte) (arrayKeyb[i] ^ 0x36);
            arrayK_opad[i] = (byte) (arrayKeyb[i] ^ 0x5c);
        }

        try
        {
            objMd = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {

            return null;
        }
        objMd.update(arrayK_ipad);
        objMd.update(arrayValue);
        arrayDg = objMd.digest();

        objMd.reset();
        objMd.update(arrayK_opad);
        objMd.update(arrayDg, 0, 16);
        arrayDg = objMd.digest();

        return toHex(arrayDg);
    }

    /**
     * toHes。
     * @Title:        toHex  
     * @author        YangZhenhua
     * @Date          2013-7-16 下午03:27:15
     */
    public static String toHex(byte arrayInput[])
    {
        StringBuffer sbufOutput;

        if (arrayInput == null)
        {
            return null;
        }

        sbufOutput = new StringBuffer(arrayInput.length * 2);

        for (int i = 0; i < arrayInput.length; i++)
        {
            int nCurrent = arrayInput[i] & 0xff;
            
            if (nCurrent < 16)
            {
                sbufOutput.append("0");
            }
            sbufOutput.append(Integer.toString(nCurrent, 16));
        }

        return sbufOutput.toString();
    }

    /**
     * @param arrayArgs。
     * @param strKey
     * @return
     */
    public static String getHmac(String[] arrayArgs, String strKey)
    {
        StringBuffer sbufStr = new StringBuffer();

        if (arrayArgs == null || arrayArgs.length == 0)
        {
            return (null);
        }
        for (int i = 0; i < arrayArgs.length; i++)
        {
            sbufStr.append(arrayArgs[i]);
        }
        return (hmacSign(sbufStr.toString(), strKey));
    }

    /**
     * @param strAValue。
     * @return
     */
    public static String digest(String strAValue)
    {
        byte arrayValue[];
        MessageDigest objMd = null;

        strAValue = strAValue.trim();

        try
        {
            arrayValue = strAValue.getBytes(strEncodingCharset);
        }
        catch (UnsupportedEncodingException objException)
        {
            arrayValue = strAValue.getBytes();
        }

        try
        {
            objMd = MessageDigest.getInstance("SHA");
        }
        catch (NoSuchAlgorithmException objException)
        {
            return null;
        }
        return toHex(objMd.digest(arrayValue));

    }
}
