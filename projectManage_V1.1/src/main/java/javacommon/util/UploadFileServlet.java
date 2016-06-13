package javacommon.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javacommon.core.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;

/**
 * 文件上传Servlet。
 * 
 * @author wangzg
 * 
 */
public class UploadFileServlet extends HttpServlet
{
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = 5641112098050582634L;

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
	public void doPost(HttpServletRequest objRequest, HttpServletResponse objResponse) throws ServletException, IOException
	{
		String strUpload_type = objRequest.getParameter("upload_type");// 接收参数
		PrintWriter objPw = objResponse.getWriter();
		String strResult = null;

		objResponse.setContentType("text/plain;charset=UTF-8");// 设置返回的数据类型
		
		try
		{
			if ("xls".equals(strUpload_type))
			{
				// 上传xls文件:---这里只处理了Excel的文件
				strResult = doImport(Config.objCOMConfig.getProperty("FILE_DIR_XLS"), objRequest, objResponse, strUpload_type);
			}

			// 返回结果:---JSON的格式
			objPw.print(strResult);
			objPw.flush();
			objPw.close();
		}
		catch (Exception e)
		{
			// 返回失败结果:---JSON的格式
			objPw.print("{'flag':'3'}");
			objPw.flush();
			objPw.close();
		}

	}

	/**
	 * 上传文件 。
	 * 
	 * @param temporaryForder:---临时的存放目录
	 * @param objRequest
	 * @param objResponse
	 * @return
	 * @throws Exception
	 */
	private String doImport(String strTemporaryForder, HttpServletRequest objRequest, HttpServletResponse objResponse, String strFileType) throws Exception
	{
		SmartUpload objSu;
		
		// 设置请求的编码
		objRequest.setCharacterEncoding("UTF-8");
		objSu = new SmartUpload();
		
		objSu.initialize(this.getServletConfig(), objRequest, objResponse);
		
		try
		{
			String strPath;
			java.io.File objMyFilePath;
			File objFile;
			String strFileExt;
			
			objSu.upload();
			strPath = this.getServletContext().getRealPath("/");
			strPath = (new StringBuilder(String.valueOf(strPath))).append(strTemporaryForder).toString();
			strPath = strPath.replace("\\", "\\\\");
			objMyFilePath = new java.io.File(strPath);

			// 创建文件存储目录
			if (!objMyFilePath.exists())
			{
				objMyFilePath.mkdir();
			}

			objFile = objSu.getFiles().getFile(0);
			strFileExt = objFile.getFileExt();// 后缀名
			
			if (null == strFileExt || "".equals(strFileExt))
			{
				String strImgname = objFile.getFileName();
				
				strImgname = new String(strImgname.getBytes("GBK"), "UTF-8");
				strFileExt = strImgname.substring(strImgname.lastIndexOf("?") + 1);
			}

			if (strFileType.equals("xls"))
			{
				String strFilePath = "";
				
				if (strFileExt.toLowerCase().equals("xls") || strFileExt.toLowerCase().equals("xlsx"))
				{
					Calendar objCal = Calendar.getInstance();
					Date objDate = objCal.getTime();
					SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
					String strFileName = objSdf.format(objDate) + "." + strFileExt;
					
					strFilePath = (new StringBuilder("/")).append(strTemporaryForder).append("/").append(strFileName).toString();
					
					objFile.saveAs(strFilePath, 1);
				}

				// 返回结果:---JSON格式
				return "{\"flag\":\"1\",\"filepath\":\"" + strFilePath + "\"}";
			}
			else
			{
				// 返回结果:---JSON格式
				return "{\"flag\":\"2\"}";
			}
		}
		catch (RuntimeException e)
		{
			// 返回结果:---JSON格式
			return "{\"flag\":\"3\"}";
		}
	}

}
