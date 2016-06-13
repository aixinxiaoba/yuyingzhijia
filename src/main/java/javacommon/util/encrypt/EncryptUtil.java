package javacommon.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单的加密工具类:---提供md5和sha-1两种算法。
 * 
 * @author wangzg
 * 
 */
public final class EncryptUtil
{
	/**
	 * 日志对象。
	 */
	protected static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

	/**
	 * MD5加密 。
	 * 
	 * @param strInputText
	 * @return
	 */
	public static String md5(String strInputText)
	{
		return encrypt(strInputText, "md5");
	}

	/**
	 * SHA加密 。
	 * 
	 * @param strInputText
	 * @return
	 */
	public static String sha(String strInputText)
	{
		return encrypt(strInputText, "sha-1");
	}

	/**
	 * 加密算法 。
	 * 
	 * @param strInputText 要加密的内容
	 * @param strAlgorithmName 加密算法名称：md5或者sha-1，不区分大小写
	 * @return
	 */
	private static String encrypt(String strInputText, String strAlgorithmName)
	{
		// 验证参数
		if (strInputText == null || "".equals(strInputText.trim()))
		{
			throw new IllegalArgumentException("请输入要加密的内容");
		}

		// 默认按MD5加密
		if (strAlgorithmName == null || "".equals(strAlgorithmName.trim()))
		{
			strAlgorithmName = "md5";
		}

		try
		{
			MessageDigest objMessageDigest = MessageDigest.getInstance(strAlgorithmName);
			byte arrayByte[];
			
			objMessageDigest.update(strInputText.getBytes("UTF8"));
			arrayByte = objMessageDigest.digest();

			// 返回16进制的结果
			return hex(arrayByte);
		}
		catch (NoSuchAlgorithmException e)
		{
			logger.error("加密错误", e);
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error("加密错误", e);
		}

		// 如果出错，返回空
		return null;
	}

	/**
	 * 返回十六进制字符串 。
	 * 
	 * @param arrayArr
	 * @return
	 */
	private static String hex(byte[] arrayArr)
	{
		StringBuffer sbufSb = new StringBuffer();
		
		for (int i = 0; i < arrayArr.length;++i)
		{
			sbufSb.append(Integer.toHexString((arrayArr[i] & 0xFF) | 0x100).substring(1, 3));
		}

		// 返回结果
		return sbufSb.toString();
	}
	
	/**
	 * Just for testing 。
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
	}
}
