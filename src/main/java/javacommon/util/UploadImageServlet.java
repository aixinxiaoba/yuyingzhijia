package javacommon.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javacommon.core.Config;
import javacommon.util.pic.ImageUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jspsmart.upload.SmartUpload;

/**
 * 用于后台银行设置中传logo。
 * 
 * @author wangzg
 * 
 */
public class UploadImageServlet extends HttpServlet
{
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = 4526848618050141068L;

	/**
	 * 日志对象。
	 */
	private static final Logger logger = LoggerFactory.getLogger(UploadImageServlet.class);

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
		PrintWriter objPw = objResponse.getWriter();
		String strUpload_flag = objRequest.getParameter("upload_flag");// 得到上传状态
		String strResult = null;

		objResponse.setContentType("text/plain;charset=UTF-8");
		try
		{
			if (strUpload_flag.equals("1"))
			{
				strResult = doImport(Config.objCOMConfig.getProperty("BANKCODE_DIRECTORY"), objRequest, objResponse);
			}
			else
			{
				int nImg_x = Integer.parseInt(objRequest.getParameter("img_x"));
				int nImg_y = Integer.parseInt(objRequest.getParameter("img_y"));
				int nImg_w = Integer.parseInt(objRequest.getParameter("img_w"));
				int nImg_h = Integer.parseInt(objRequest.getParameter("img_h"));
				String strImg_spath = objRequest.getParameter("img_spath");
				String strDivwidth = objRequest.getParameter("divwidth");
				String strDivheight = objRequest.getParameter("divheight");
				int nLastIndex = strImg_spath.lastIndexOf("/");
				String strPath = strImg_spath.substring(0, nLastIndex);
				String strFileName = strImg_spath.substring(nLastIndex);
				String strSfileName = strFileName.substring(0, strFileName.lastIndexOf("."));
				String strFileExt = strFileName.substring(strFileName.lastIndexOf("."));
				String strNewpath = new StringBuilder(strPath).append(strSfileName).append("_preview").append(strFileExt).toString();
				String strRealpath = this.getServletContext().getRealPath("/");
				String strAbsPath;
				String strFlag;

				strRealpath = strRealpath.substring(0, strRealpath.length() - 1);
				strRealpath = strRealpath.replace("\\", "\\\\");
				strAbsPath = strImg_spath;
				strAbsPath = strAbsPath.replace("/", "\\\\");

				// 按指定宽高度重新生成新图
				ImageUtils.scale(strRealpath + strAbsPath, strRealpath + strAbsPath, Integer.parseInt(strDivwidth), Integer.parseInt(strDivheight));

				// 对生成的新图进行裁剪
				strFlag = ImageUtils.abscut(strRealpath + strAbsPath, strRealpath + strNewpath, nImg_x, nImg_y, nImg_w, nImg_h);

				if ("Y".equals(strFlag))
				{
					strResult = strNewpath;
				}
				delTmpImage(strRealpath + strAbsPath);// 删除临时文件

			}
			objPw.print(strResult);
			objPw.flush();
			objPw.close();
		}
		catch (Exception e)
		{
			logger.error("", e);
			objPw.print("{'flag':'3'}");
			objPw.flush();
			objPw.close();
		}

	}

	/**
	 * 删除临时文件。
	 * 
	 * @param stortPath 文件名
	 * @param rootPath 根路径
	 */
	private void delTmpImage(String strFilePath)
	{

		File objImageFile = new File(strFilePath);

		if (objImageFile.exists())
		{
			objImageFile.delete();// 删除源文件
		}
	}

	/**
	 * 上传图片。
	 * 
	 * @param strTemporaryForder
	 * @param objRequest
	 * @param objResponse
	 * @return
	 * @throws Exception
	 */
	public String doImport(String strTemporaryForder, HttpServletRequest objRequest, HttpServletResponse objResponse) throws Exception
	{
		SmartUpload objSu;

		objRequest.setCharacterEncoding("UTF-8");
		objSu = new SmartUpload();

		objSu.initialize(this.getServletConfig(), objRequest, objResponse);
		try
		{
			String strPath;
			java.io.File objMyFilePath;
			com.jspsmart.upload.File objFile;
			String strFileExt;
			
			objSu.upload();
			strPath = this.getServletContext().getRealPath("/");
			strPath = (new StringBuilder(String.valueOf(strPath))).append(strTemporaryForder).toString();
			strPath = strPath.replace("\\", "\\\\");
			objMyFilePath = new java.io.File(strPath);

			if (!objMyFilePath.exists())
			{
				objMyFilePath.mkdir();
			}

			objFile = objSu.getFiles().getFile(0);
			strFileExt = objFile.getFileExt();// 后缀名

			if (strFileExt.toLowerCase().equals("bmp") || strFileExt.toLowerCase().equals("jpg") || strFileExt.toLowerCase().equals("gif") || strFileExt.toLowerCase().equals("png") || strFileExt.toLowerCase().equals("pic") || strFileExt.toLowerCase().equals("tif") || strFileExt.toLowerCase().equals("wmf"))
			{
				Calendar objCal = Calendar.getInstance();
				Date objSdate = objCal.getTime();
				SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
				String strFileName = objSdf.format(objSdate) + "." + strFileExt;
				String strFilePath = (new StringBuilder("/")).append(strTemporaryForder).append("/").append(strFileName).toString();
				String strNewFilepath;
				File objImageFile;
				BufferedImage objBi;
				int nSrcWidth;
				int nSrcHeight;

				objFile.saveAs(strFilePath, 1);
				strNewFilepath = objMyFilePath + "\\" + strFileName;// 读取源图像
				objImageFile = new File(strNewFilepath);
				objBi = ImageIO.read(objImageFile);
				nSrcWidth = objBi.getWidth(); // 源图宽度
				nSrcHeight = objBi.getHeight(); // 源图高度

				return "{\"flag\":\"1\",\"filepath\":\"" + strFilePath + "\",\"srcWidth\":\"" + nSrcWidth + "\",\"srcHeight\":\"" + nSrcHeight + "\"}";

			}
			else
			{
				return "{\"flag\":\"2\"}";
			}
		}
		catch (RuntimeException e)
		{
			logger.error("", e);
			return "{\"flag\":\"3\"}";
		}
	}

}
