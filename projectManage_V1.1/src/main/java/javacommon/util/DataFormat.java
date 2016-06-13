package javacommon.util;

import java.io.UnsupportedEncodingException;

/**
 * 数据。
 * 
 * @author Administrator
 * 
 */
public class DataFormat
{
	/**
	 *构造方法。
	 */
	private DataFormat()
	{
	}

	/**
	 * 转换为html title/Alt属性文本，主要转换&,单引号和双引号字符。
	 */
	public static String encode4Title(String strValue)
	{
		return notNull(strValue).replaceAll("&", "&amp;").replaceAll("'", "&#39;").replaceAll("\"", "&quot;");
	}

	/**
	 * escape sql query characters | sql字符转换。
	 */
	public static String escape4Sql(String strValue)
	{
		StringBuffer sbufBuffer;

		strValue = notNull(strValue);
		if (strValue.equals(""))
		{
			return "";
		}
		sbufBuffer = new StringBuffer();
		if (strValue.length() > 15)
		{
			strValue = strValue.substring(0, 15);
		}
		for (int i = 0; i < strValue.length(); i++)
		{
			char cH = strValue.charAt(i);

			if (cH == '[')
			{
				sbufBuffer.append("[[]");
			}
			else if (cH == '\'')
			{
				sbufBuffer.append("''");
			}
			else if (cH == '%')
			{
				sbufBuffer.append("[%]");
			}
			else if (cH == '_')
			{
				sbufBuffer.append("[_]");
			}
			else if (cH == '^')
			{
				sbufBuffer.append("[^]");
			}
			else
			{
				sbufBuffer.append(cH);
			}
		}
		return sbufBuffer.toString();
	}

	/**
	 * 转换为html字符。
	 */
	public static String encode4Html(String strValue)
	{
		StringBuffer sbufResult;

		strValue = notNull(strValue);
		if (strValue.equals(""))
		{
			return "";
		}
		sbufResult = new StringBuffer();
		for (int i = 0; i < strValue.length(); i++)
		{
			char cH = strValue.charAt(i);

			if (cH == '<')
			{
				sbufResult.append("&lt;");
			}
			else if (cH == '&')
			{
				sbufResult.append("&amp;");
			}
			else if (cH == '"')
			{
				sbufResult.append("&quot;");
			}
			else if (cH == '\r')
			{
				sbufResult.append("<BR>");
			}
			else if (cH == '\n')
			{
				if (strValue.charAt(i - 1) == '\r')
				{
				}
				else
				{
					sbufResult.append("<BR>");
				}
			}
			else if (cH == '\t')
			{
				sbufResult.append("&nbsp;&nbsp;&nbsp;&nbsp");
			}
			else if (cH == ' ')
			{
				sbufResult.append("&nbsp;");
			}
			else
			{
				sbufResult.append(cH);
			}
		}
		return sbufResult.toString();
	}

	/**
	 * 转换为freeMarker字符 注：此处是将java中的字符串转换为在 freeMarker认识的字符。不是在freeMarker中转换字符。
	 */
	public static String encode4FreeMarker(String strValue)
	{
		StringBuffer sbufResult;

		strValue = notNull(strValue);
		if (strValue.equals(""))
		{
			return "";
		}
		sbufResult = new StringBuffer();
		for (int i = 0; i < strValue.length(); i++)
		{
			char cH = strValue.charAt(i);

			if (cH == '<')
			{
				sbufResult.append("&lt;");
			}
			if (cH == '>')
			{
				sbufResult.append("&gt;");
			}
			else if (cH == '&')
			{
				sbufResult.append("&amp;");
			}
			else if (cH == '"')
			{
				sbufResult.append("&quot;");
			}
			else if (cH == ' ')
			{
				sbufResult.append("&nbsp;");
			}
			else if (cH == '\\')
			{
				sbufResult.append("/");
			}
			else
			{
				sbufResult.append(cH);
			}
		}
		return sbufResult.toString();
	}

	/**
	 * 转换非空对象，防止产生NullPointerException异常。
	 */
	public static String notNull(Object objS)
	{
		if (objS instanceof String)
		{
			if (objS == null || objS.toString().trim().equals(""))
			{
				return "";
			}
			else
			{
				return objS.toString().trim();
			}
		}
		else if (objS instanceof Integer)
		{
			if (objS == null)
			{
				return "";
			}
			else
			{
				return objS.toString();
			}
		}
		else
		{
			return "";
		}
	}

	/**
	 * encode for non-ASCII characters。
	 * 
	 * @param strA
	 * @return
	 */
	public static String strChinese(String strA)
	{
		try
		{
			return new String(strA.getBytes("iso-8859-1"));
		}
		catch (UnsupportedEncodingException ex)
		{
			return "";
		}
	}
}
