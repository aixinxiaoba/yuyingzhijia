package javacommon.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 每个项目单独的Context/每个项目自己的容器 。
 * 
 * @author wangzg
 *
 */
public class AppContext
{
	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(AppContext.class);
	
	// Forward的常量值
	public static final String STR_FORWARD = "forwardURL";
	
	/**
	 * 单例
	 */
	private static AppContext objInstance;
	
	/**
	 * 用于在Action层以外的地方拿到AC
	 */
	private AbstractApplicationContext objAppContext;
	
	/**
	 * 私有的构造方法 。
	 */
	private AppContext()
	{
		this.objAppContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
    }
	
	/**
	 * 单例 。
	 * @return
	 */
	public synchronized static AppContext getInstance()
	{
	    if (objInstance == null)
	    {
	    	objInstance = new AppContext();
	    }
	    
	    // 返回结果
	    return objInstance;
	}
	
	/**
	 * 拿到AC对象 。
	 * 
	 * @return
	 */
	public AbstractApplicationContext getAppContext()
	{
		return objAppContext;
	}
	
	/**
	 * 取得项目的绝对路径 。
	 * 
	 * @return
	 */
	public static String getContextPath()
	{
		HttpServletRequest objRequest = ServletActionContext.getRequest();
		String strPath = objRequest.getScheme() + "://" + objRequest.getServerName() + ":" + objRequest.getServerPort() + objRequest.getContextPath();

		// 返回结果
		return strPath;
	}

	/**
	 * 取得项目的绝对路径:---不带项目名 。
	 * 
	 * @return
	 */
	public static String getContextPathWithoutName()
	{
		HttpServletRequest objRequest = ServletActionContext.getRequest();
		String strPath = objRequest.getScheme() + "://" + objRequest.getServerName() + ":" + objRequest.getServerPort();
		
		// 返回结果
		logger.info("strPath:---" + strPath);
		return strPath;
	}

	
}
