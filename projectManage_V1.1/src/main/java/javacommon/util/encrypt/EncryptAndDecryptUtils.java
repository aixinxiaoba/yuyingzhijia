package javacommon.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单的加密工具类:---提供md5和sha-1两种算法。
 * 自己封装的加密和解密的工具类 。
 * 
 * @author wangzg
 * 
 */
public final class EncryptAndDecryptUtils
{
	/**
	 * 日志对象。
	 */
	protected static final Logger logger = LoggerFactory.getLogger(EncryptAndDecryptUtils.class);

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
	 * 加密一段字符串 。
	 * 
	 * @param strMsg
	 * @return
	 */
	public static String encrypt(String strMsg)
	{
		// 定义参数
		int nLength = strMsg.length();
		int nOffset = randomOffset(nLength);
		StringBuffer objSb = new StringBuffer();
		String strMixChar = "";
		int nCode;
		
		// 开始处理逻辑
		for (int i = 0;i < nLength;i++)
		{
			// 初始化参数
			strMixChar = "";
			
			nCode = strMsg.codePointAt(i) + nOffset;
			if (nLength % 2 == 0)
			{
				strMixChar = mixChar();
				objSb.append(strMixChar);
			}
			
			strMixChar = mixChar();
			objSb.append(nCode).append(strMixChar);
			
			if (i == 0)
			{
				strMixChar = mixChar();
				objSb.append(nOffset).append(strMixChar);
			}
		}
		
		// 返回结果
		return objSb.toString();
	}
	
	/**
	 * 以后加注释 。
	 * 
	 * @param length
	 * @return
	 */
	private static int randomOffset(int length)
	{
		// 定义参数
		int r_num = new Random().nextInt(40);
		int offset = new Random().nextInt(length) + r_num;
		
		// 返回结果
		return offset;
	}
	
	/**
	 * 随机生成一个字符串 。
	 * 
	 * @return
	 */
	private static String mixChar()
	{
		// 定义参数
		Random objRandom = new Random();
		StringBuffer objSb;
		String strGenerateRandom = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int nRandom;
		String strNumRandom;
		int nNum;
		
		// 开始处理逻辑
		nNum = objRandom.nextInt(2);
		while (nNum == 0)
		{
			nNum = objRandom.nextInt(2);
		}
		
		objSb = new StringBuffer();
		for (int i = 0;i < nNum;i++)
		{
			nRandom = objRandom.nextInt(50);
			strNumRandom = strGenerateRandom.charAt(nRandom) + "";
			objSb.append(strNumRandom);
		}
		
		// 返回结果
		return objSb.toString();
	}
		
	/**
	 * 解密字符串 。
	 * 
	 * @param strMsg
	 * @return
	 */
	public static String decrypt(String strMsg)
	{
		// 定义参数
		String[] arrayCodes = strMsg.split("[a-zA-Z]{1,}");
		int nIndex = 1;
		String strFirstChar = strMsg.substring(0, 1);
		int nOffset;
		StringBuffer objSb = new StringBuffer();
		int nCode;
		
		// 开始处理逻辑
		if (strFirstChar.matches("[a-zA-Z]"))
		{
			nIndex = 2;
		}
		
		nOffset = Integer.parseInt(arrayCodes[nIndex]);

		for (int i = 0;i < arrayCodes.length;i++)
		{
			if (i == nIndex || "".equalsIgnoreCase(arrayCodes[i]))
			{
				continue;
			}
			
			nCode = Integer.parseInt(arrayCodes[i]) - nOffset;
			objSb.append((char)nCode);
		}
		
		// 返回结果
		return objSb.toString();
	}
			
}
