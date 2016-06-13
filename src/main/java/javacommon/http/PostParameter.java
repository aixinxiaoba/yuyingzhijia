package javacommon.http;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

/**
 * A data class representing HTTP Post parameter 。
 * 
 * @author wangzg
 * 
 */
@SuppressWarnings("unchecked")
public class PostParameter implements java.io.Serializable, Comparable
{
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -8708108746980739212L;

	/**
	 * 参数名
	 */
	private String strName;

	/**
	 * 参数值
	 */
	private String strValue;

	/**
	 * 文件参数
	 */
	private File objFile = null;

	/**
	 * 文件相关
	 */
	private static final String JPEG = "image/jpeg";
	private static final String GIF = "image/gif";
	private static final String PNG = "image/png";
	private static final String OCTET = "application/octet-stream";

	/**
	 * 构造函数。
	 * 
	 * @param strName
	 * @param strValue
	 */
	public PostParameter(String strName, String strValue)
	{
		this.strName = strName;
		this.strValue = strValue;
	}

	/**
	 * 构造函数。
	 * 
	 * @param strName
	 * @param dbValue
	 */
	public PostParameter(String strName, double dbValue)
	{
		this.strName = strName;
		this.strValue = String.valueOf(dbValue);
	}

	/**
	 * 构造函数。
	 * 
	 * @param strName
	 * @param nValue
	 */
	public PostParameter(String strName, int nValue)
	{
		this.strName = strName;
		this.strValue = String.valueOf(nValue);
	}

	/**
	 * 构造函数。
	 * 
	 * @param strName
	 * @param objFile
	 */
	public PostParameter(String strName, File objFile)
	{
		this.strName = strName;
		this.objFile = objFile;
	}

	/**
	 * 拿到文件类型 。
	 * 
	 * @return
	 */
	public String getContentType()
	{
		// 定义参数
		String strContentType;
		String strExtensions = objFile.getName();
		int nIndex = strExtensions.lastIndexOf(".");

		// 验证是否有文件
		if (!isFile())
		{
			throw new IllegalStateException("not a file");
		}

		strExtensions = objFile.getName();
		nIndex = strExtensions.lastIndexOf(".");
		
		if (-1 == nIndex)
		{
			strContentType = OCTET;
		}
		else
		{
			strExtensions = strExtensions.substring(strExtensions.lastIndexOf(".") + 1).toLowerCase();
			if (strExtensions.length() == 3)
			{
				if ("gif".equals(strExtensions))
				{
					strContentType = GIF;
				}
				else if ("png".equals(strExtensions))
				{
					strContentType = PNG;
				}
				else if ("jpg".equals(strExtensions))
				{
					strContentType = JPEG;
				}
				else
				{
					strContentType = OCTET;
				}
			}
			else if (strExtensions.length() == 4)
			{
				if ("jpeg".equals(strExtensions))
				{
					strContentType = JPEG;
				}
				else
				{
					strContentType = OCTET;
				}
			}
			else
			{
				strContentType = OCTET;
			}
		}

		// 返回结果
		return strContentType;
	}

	/**
	 * 验证参数中是否包含文件。
	 * 
	 * @param lstParams
	 * @return
	 */
	public static boolean containsFile(List<PostParameter> lstParams)
	{
		return containsFile(lstParams.toArray(new PostParameter[0]));
	}

	/**
	 * 验证参数中是否包含文件。
	 * 
	 * @param arrayParams
	 * @return
	 */
	public static boolean containsFile(PostParameter[] arrayParams)
	{
		// 定义参数
		boolean bContainsFile = false;

		// 验证参数
		if (null == arrayParams)
		{
			return false;
		}

		// 遍历所有的Post参数
		for (PostParameter objParam : arrayParams)
		{
			if (objParam.isFile())
			{
				bContainsFile = true;
				break;
			}
		}

		// 返回结果
		return bContainsFile;
	}

	/**
	 * 拿到PostParameter数组。
	 * 
	 * @param strName
	 * @param strValue
	 * @return
	 */
	public static PostParameter[] getParameterArray(String strName, String strValue)
	{
		return new PostParameter[] { new PostParameter(strName, strValue) };
	}

	/**
	 * 拿到PostParameter数组。
	 * 
	 * @param strName
	 * @param nValue
	 * @return
	 */
	public static PostParameter[] getParameterArray(String strName, int nValue)
	{
		return getParameterArray(strName, String.valueOf(nValue));
	}

	/**
	 * 重写hashCode方法。
	 */
	@Override
	public int hashCode()
	{
		int nResult = strName.hashCode();
		
		nResult = 31 * nResult + strValue.hashCode();
		nResult = 31 * nResult + (objFile != null ? objFile.hashCode() : 0);
		
		return nResult;
	}

	/**
	 * 重写equals方法。
	 */
	@Override
	public boolean equals(Object objObj)
	{
		if (null == objObj)
		{
			return false;
		}

		if (this == objObj)
		{
			return true;
		}

		if (objObj instanceof PostParameter)
		{
			PostParameter objThat = (PostParameter) objObj;

			if (objFile != null ? !objFile.equals(objThat.objFile) : objThat.objFile != null)
			{
				return false;
			}

			return this.strName.equals(objThat.strName) && this.strValue.equals(objThat.strValue);
		}

		// 返回结果
		return false;
	}

	/**
	 * 重写toString方法。
	 */
	@Override
	public String toString()
	{
		return "PostParameter{" + "name='" + strName + '\'' + ", value='" + strValue + '\'' + ", file=" + objFile + '}';
	}

	/**
	 * 重写compareTo方法。
	 */
	@Override
	public int compareTo(Object objObj)
	{
		int nCompared;
		PostParameter objThat = (PostParameter) objObj;
		
		nCompared = strName.compareTo(objThat.strName);
		
		if (0 == nCompared)
		{
			nCompared = strValue.compareTo(objThat.strValue);
		}

		// 返回结果
		return nCompared;
	}

	/**
	 * 对参数进行编码 。
	 * 
	 * @param arrayHttpParams
	 * @return
	 */
	public static String encodeParameters(PostParameter[] arrayHttpParams)
	{
		// 定义参数
		StringBuffer sbufBuf = new StringBuffer();

		// 验证参数
		if (null == arrayHttpParams)
		{
			return "";
		}

		// 遍历所有的参数
		for (int j = 0; j < arrayHttpParams.length; j++)
		{
			if (arrayHttpParams[j].isFile())
			{
				throw new IllegalArgumentException("parameter [" + arrayHttpParams[j].strName + "]should be text");
			}

			if (j != 0)
			{
				sbufBuf.append("&");
			}
			try
			{
				sbufBuf.append(URLEncoder.encode(arrayHttpParams[j].strName, "UTF-8")).append("=").append(URLEncoder.encode(arrayHttpParams[j].strValue, "UTF-8"));
			}
			catch (java.io.UnsupportedEncodingException neverHappen)
			{
				// Ignore this error
			}
		}

		// 返回结果
		return sbufBuf.toString();
	}

	/**
	 * 拿到参数名。
	 * 
	 * @return
	 */
	public String getStrName()
	{
		return strName;
	}

	/**
	 * 拿到参数值。
	 * 
	 * @return
	 */
	public String getStrValue()
	{
		return strValue;
	}

	/**
	 * 拿到文件。
	 * 
	 * @return
	 */
	public File getObjFile()
	{
		return objFile;
	}

	/**
	 * 验证是否有文件。
	 * 
	 * @return
	 */
	public boolean isFile()
	{
		return null != objFile;
	}

}
