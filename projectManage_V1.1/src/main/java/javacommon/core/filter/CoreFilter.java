package javacommon.core.filter;

import java.io.IOException;

import javacommon.core.Config;
import javacommon.util.properties.PropsUtil;
import javacommon.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> 系统自启动后进行全局加载：---系统启动时默认调用init()方法/初始化系统核心数据。
 * 
 * @author wangzg
 * 
 */
public class CoreFilter implements Filter
{
	/**
	 * 日志对象。
	 */
	protected Logger logger = LoggerFactory.getLogger(CoreFilter.class);

	/**
	 * 初始化方法。
	 */
	@Override
	public void init(FilterConfig objConf) throws ServletException
	{
		try
		{
			/** 系统内存使用情况 */
			logger.info("\t***********************************************************");
			logger.info("\t*\t Java 虚拟机中的空闲内存量：" + Runtime.getRuntime().freeMemory() / (1024 * 1024) + " M");
			logger.info("\t*\t Java 虚拟机试图使用的最大内存量：" + Runtime.getRuntime().maxMemory() / (1024 * 1024) + " M");
			logger.info("\t*\t Java 虚拟机中的内存总量：" + Runtime.getRuntime().totalMemory() / (1024 * 1024) + " M");
			logger.info("\t*");

			/** 系统应用根目录 */
			Config.SYS_ROOT_PATH = objConf.getServletContext().getRealPath("/");
			logger.info(StringUtils.getCurrentDate("") + "\t*\t系统根路径：" + Config.SYS_ROOT_PATH);

			/** 初始化权限相关文件:---Config.PRIVILEDGE_URL/With priviledge.properties */
			// Config.objCOMConfig = Resources.getURlConf(Config.SYS_ROOT_PATH +"WEB-INF/classes/priviledge.properties");
			// logger.info(StringUtil.getCurrentDate("") + "\t*\t成功加载权限相关文件");
			
			/** 初始化系统公共配置文件:---Config.COMConfig/With COMConfig.properties */
			Config.objCOMConfig = PropsUtil.getURlConf("E:\\Config\\SAASProduct\\COMConfig.properties");
			if ( Config.objCOMConfig == null )
			{
				Config.objCOMConfig = PropsUtil.getURlConf(Config.SYS_ROOT_PATH + "/WEB-INF/upload/config/COMConfig.properties");
				logger.info("E:\\Config\\SAASProduct\\COMConfig.properties" + "没找到对应的文件，现在从项目根路径下面加载");
			}
			logger.info(StringUtils.getCurrentDate("") + "\t*\t成功加载Config.COMConfig配置文件");
			
			/** 初始化SAASProduct产品配置文件:---Config.objSAASConfig/With SAASConfig.properties */
			Config.objSAASConfig = PropsUtil.getURlConf("E:\\Config\\SAASProduct\\SAASConfig.properties");
			if ( Config.objSAASConfig == null )
			{
				Config.objSAASConfig = PropsUtil.getURlConf(Config.SYS_ROOT_PATH + "/WEB-INF/upload/config/SAASConfig.properties");
				logger.info("E:\\Config\\SAASProduct\\SAASConfig.properties" + "没找到对应的文件，现在从项目根路径下面加载");
			}
			
			logger.info(StringUtils.getCurrentDate("") + "\t*\t成功加载Config.objSAASConfig配置文件");

			/** 初始化sql拼接查询条件时要过滤的危险字符 */
			Config.SQL_CHECK_ARRAY = Config.objCOMConfig.getProperty("keywords").split(",");
			logger.info(StringUtils.getCurrentDate("") + "\t*\t成功加载sql拼接查询条件时要过滤的危险字符");
		}
		catch (Exception e)
		{
			logger.error(StringUtils.getCurrentDate("") + "*\t初始化系统参数-失败：" + e.getMessage());
		}

		logger.info(StringUtils.getCurrentDate("") + "*\t初始化系统参数成功~");
	}

	/**
	 * doFilter进行处理。
	 */
	@Override
	public void doFilter(ServletRequest objRequest, ServletResponse objResponse, FilterChain objChain) throws IOException, ServletException
	{
		// 定义参数
		HttpServletRequest objHSRequest = (HttpServletRequest) objRequest;
		HttpServletResponse objHSResponse = (HttpServletResponse)objResponse;
		String strUrl;
		String strActionURL;
		String strUserID;
		
		// 设置项目的编码
		objHSRequest.setCharacterEncoding(Config.objCOMConfig.getProperty("SYS_CHARACTER", "utf-8"));
		objResponse.setCharacterEncoding(Config.objCOMConfig.getProperty("SYS_CHARACTER", "utf-8"));
		
		// 用户的请求
		strUrl = objHSRequest.getRequestURI();
		logger.info("CoreFilter:--- url:---" + strUrl);
		
//        // 用户是否登录的判断
//        if (!StringUtils.isEmpty(strUrl))
//        {
//            strActionURL = strUrl.substring(strUrl.lastIndexOf("/") + 1);
//            if (!(strActionURL.equalsIgnoreCase("forward.do") || strActionURL.indexOf("Login") > 0 || strUrl.indexOf("front") > 0))
//            {
//                strUserID = (String) objHSRequest.getSession().getAttribute(Config.objCOMConfig.getProperty("USER_ID_KEY", "USER_ID"));
//
//                if (StringUtils.isEmpty(strUserID))
//                {
//                    strUserID = (String) objHSRequest.getSession().getAttribute(Config.objSAASConfig.getProperty("ORGLOGIN_ID_KEY", "ORG_ID"));
//                }
//
//                if (StringUtils.isEmpty(strUserID))
//                {
//                    logger.info("用户没有登录或Session已失效，返回到登录页面");
//                    objHSResponse.sendRedirect(objHSRequest.getContextPath() + "/index.jsp");
//                }
//            }
//        }

		// 传递到FilterChain
		objChain.doFilter(objRequest, objResponse);
		logger.info("处理完成，跳转到：" + objHSRequest.getAttribute("struts.view_uri"));
	}

	/**
	 * 销毁方法。
	 */
	@Override
	public void destroy()
	{
		// Do Nothing
	}

}
