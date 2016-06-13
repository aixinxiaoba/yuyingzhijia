package javacommon.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javacommon.core.Config;
import javacommon.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件工具类: 系统资源国际化资源。
 * 
 * @author wangzg
 * 
 */
public class PropsUtil
{
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

	/**
	 * 编码
	 */
	public static final String ENCODING = "UTF-8";

	/**
	 * 系统属性:---关联Properties工具类
	 */
	Properties objProperties = new Properties();

	/**
	 * 文件绝对路径。
	 */
	private String strFilename;

	/**
	 * 得到一个资源文件。
	 * 
	 * @param strPath 资源文件绝对路径
	 * @return
	 */
	public static PropsUtil getParseProperties(String strPath)
	{
		PropsUtil objPp = new PropsUtil();
		
		objPp.setStrFilename(strPath);
		objPp.parseProp();

		// 返回结果
		return objPp;
	}

	/**
	 * 加载资源文件。
	 */
	private void parseProp()
	{
		InputStream objIs = null;
		
		try
		{
			File objFile = new File(getStrFilename());
			
			objIs = new FileInputStream(objFile);
			
			objProperties.load(objIs);
			objIs.close();
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		finally
		{
			try
			{
				objIs.close();
			}
			catch (IOException e)
			{
				logger.error("", e);
			}
		}
	}

	/**
	 * 保存文件 。
	 */
	public void storeProp()
	{
		OutputStream objOut = null;
		
		try
		{
			File objFile = new File(this.strFilename);
			
			objOut = new FileOutputStream(objFile);
			
			objProperties.store(objOut, "MIUKOO - RCP ");
			objOut.close();
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		finally
		{
			try
			{
				objOut.close();
			}
			catch (IOException e)
			{
				logger.error("", e);
			}
		}
	}

	/**
	 * 设置一个资源值 。
	 * 
	 * @param strKey
	 * @param strValue
	 */
	public void setMess(String strKey, String strValue)
	{
		objProperties.setProperty(strKey, strValue);
	}

	/**
	 * 得到一个资源值。
	 * 
	 * @param strKey
	 * @return
	 */
	public String getMess(String strKey)
	{
		return StringUtils.VString(objProperties.getProperty(strKey), "");
	}

	/**
	 * 得到一个资源值，并替换一个标记值。
	 * 
	 * @param strKey KEY
	 * @param strCh 替换值
	 * @return
	 */
	public String getMessOne(String strKey, String strCh)
	{
		String str = objProperties.getProperty(strKey);
		
		str = str.replace("{0}", strCh);
		return str;
	}

	/**
	 * 得到一个资源值，并替换两个标记值。
	 * 
	 * @param strKey KEY
	 * @param strCh1 第一个替换值
	 * @param strCh2 第二个替换值
	 * @return
	 */
	public String getMessTwo(String strKey, String strCh1, String strCh2)
	{
		String str = StringUtils.VString(objProperties.getProperty(strKey), "");
		
		str = str.replace("{0}", strCh1);
		str = str.replace("{1}", strCh2);
		
		return str;
	}

	/**
	 * 得到一个资源值，并替换两个标记值。
	 * 
	 * @param strKey KEY
	 * @param strCh1 第一个替换值
	 * @param strCh2 第二个替换值
	 * @return
	 */
	public String getMessThe(String strKey, String strCh1, String strCh2, String strCh3)
	{
		String strStr = StringUtils.VString(objProperties.getProperty(strKey), "");
		
		strStr = strStr.replace("{0}", strCh1);
		strStr = strStr.replace("{1}", strCh2);
		strStr = strStr.replace("{2}", strCh3);
		
		return strStr;
	}

	/**
	 * 得到一个资源值，并替换两个标记值。
	 * 
	 * @param strKey KEY
	 * @param strCh1 第一个替换值
	 * @param strCh2 第二个替换值
	 * @return
	 */
	public String getMessFor(String strKey, String strCh1, String strCh2, String strCh3, String strCh4)
	{
		String strStr = StringUtils.VString(objProperties.getProperty(strKey), "");
		
		strStr = strStr.replace("{0}", strCh1);
		strStr = strStr.replace("{1}", strCh2);
		strStr = strStr.replace("{2}", strCh3);
		strStr = strStr.replace("{3}", strCh4);
		
		return strStr;
	}

	/**
	 * 读取service 配置文件。
	 * 
	 * @return 数据库连接信息
	 */
	public static Properties getService() throws IOException
	{
		return getURlConf(Config.SYS_ROOT_PATH + "WEB-INF/classes/service.properties");

	}

	/**
	 * 读取URL 配置文件 。
	 * 
	 * @param propertiesUrl:---配置文件的绝对路径
	 * @return
	 * @throws IOException
	 */
	public static Properties getURlConf(String strPropertiesUrl)
	{
		Properties objPro = new Properties();// 定义参数
		InputStream objIn = null;
		String strPath = strPropertiesUrl;
		File objTemlFile;
		
		// 验证参数
		if (StringUtils.isBlank(strPropertiesUrl))
		{
			logger.info("路径不能为空~" + strPropertiesUrl);
			return null;
		}

		objTemlFile = new File(strPropertiesUrl);
		if ( !(objTemlFile.exists() && objTemlFile.isFile()) )
		{
			logger.info("文件位置不存在，返回null 。文件位置是:---" + strPropertiesUrl);
			return null;
		}
		
		try
		{
			objIn = new FileInputStream(strPath);
			objPro.load(objIn);
		}
		catch (IOException e)
		{
			logger.info("文件位置不存在，返回null");
			return null;
		}
		finally
		{
			try
			{
				objIn.close();
			}
			catch (IOException e)
			{
				logger.error("文件位置不存在，返回null");
				return null;
			}
		}

		// 返回结果
		return objPro;
	}

	/**
	 * 拿到文件路径 。
	 * 
	 * @return
	 */
	private String getStrFilename()
	{
		return strFilename;
	}

	/**
	 * 设置文件路径 。
	 * 
	 * @param strFilename
	 */
	private void setStrFilename(String strFilename)
	{
		this.strFilename = strFilename;
	}
	
	/**
	 * Just for testing  。
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// 定义参数
		Properties objProperties;
		
		// 开始处理逻辑
		objProperties = PropsUtil.getURlConf("E:\\Config\\COMConfig.properties");
		logger.info("" + objProperties.getProperty("CONTENT_TYPE"));
		
	}
}