package javacommon.util;

import java.io.IOException;

import javacommon.core.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

/**
 * 文件下载Servlet 。
 * 
 * @author wangzg
 * 
 */
public class FileDownloadServlet extends HttpServlet
{
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = 2327760680988080074L;

	/**
	 * 初始化方法。
	 */
	@Override
	public void init() throws ServletException
	{

	}

	/**
	 * doGet方法。
	 */
	@Override
	public void doGet(HttpServletRequest objRequest, HttpServletResponse objResponse) throws ServletException, IOException
	{
		this.doPost(objRequest, objResponse);
	}

	/**
	 * doPost方法。
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest objRequest, HttpServletResponse objResponse) throws ServletException, IOException
	{
		String strFilePath;
		String strIsPermitMany;
		String strSeq;
		HttpSession objSession;
		String strUserId = "";
		String strNewPath;
		String strPath;
		String strAgent;
		
		objRequest.setCharacterEncoding("UTF-8");
		objResponse.setCharacterEncoding("UTF-8");

		// 获取下载文件路径
		strFilePath = objRequest.getParameter("reqFileName");
		strIsPermitMany = objRequest.getParameter("isPermitMany");
		strSeq = objRequest.getParameter("seq");
		objSession = objRequest.getSession();
		strUserId = "";
		
		if (objSession.getAttribute("username") != null)
		{
			strUserId = objSession.getAttribute("username").toString();
		}
		else if (objSession.getAttribute(Config.objCOMConfig.getProperty("ADMIN_EML")) != null)
		{
			strUserId = objSession.getAttribute(Config.objCOMConfig.getProperty("ADMIN_EML")).toString();
		}

		// 删除文件下载历史列表的数据，需要用到下面的参数
		if (strSeq != null)
		{
			objRequest.setAttribute("seq", strSeq);
			objRequest.setAttribute("userId", strUserId);
		}

		strNewPath = objRequest.getRealPath("/");
		strPath = strNewPath + strFilePath;
		
		objRequest.setAttribute("filePath", strPath);

		strAgent = (String) objRequest.getHeader("USER-AGENT");
		
		if (strAgent != null && strAgent.indexOf("MSIE") == -1)
		{
			String strEnableFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(strFilePath.getBytes("UTF-8")))) + "?=";
			
			objResponse.setHeader("Content-Disposition", "attachment; filename=" + strEnableFileName);
		}
		else
		{
			String strEnableFileName = new String(strFilePath.getBytes("GBK"), "ISO-8859-1");
			
			objResponse.setHeader("Content-Disposition", "attachment; filename=" + strEnableFileName);
		}

		// 转向到的页面
		if (strIsPermitMany != null && !"".equals(strIsPermitMany))
		{
			objRequest.getRequestDispatcher("/commons/page/downLoad.jsp?isPermitMany=" + strIsPermitMany).forward(objRequest, objResponse);
		}
		else
		{
			objRequest.getRequestDispatcher("/commons/page/downLoad.jsp").forward(objRequest, objResponse);
		}
	}

	/**
	 * destroy。
	 */
	@Override
	public void destroy()
	{
		super.destroy();
	}

}
