package javacommon.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 将数据封装到word模版类。
 * 
 * @author Administrator
 * 
 */
public class DocUtil
{

	private static final Logger logger = LoggerFactory.getLogger(DocUtil.class);

	private Configuration objConfiguration = null;

	private Map<String, Object> map = null;
	private String strFtlName = null;
	private String strOuputFilePath = null;

	/**
	 * 构造方法。
	 */
	public DocUtil()
	{
		objConfiguration = new Configuration();
		objConfiguration.setDefaultEncoding("utf-8");
	}

	/**
	 * @param mapObj 封装的数据。
	 * @param strFtlName 要生成的word模板 ftl文件。
	 *            
	 */
	public DocUtil(Map<String, Object> mapObj, String strFtlName, String strOuputFilePath)
	{
		objConfiguration = new Configuration();
		objConfiguration.setDefaultEncoding("utf-8");
		this.map = mapConsoleNullInMap(mapObj);
		this.strFtlName = strFtlName;
		this.strOuputFilePath = strOuputFilePath;
	}

	/**
	 * 创建方法。
	 */
	public void create()
	{
		objConfiguration.setClassForTemplateLoading(this.getClass(), "/docTemplate/");
		try
		{
			Template objT = objConfiguration.getTemplate(strFtlName);
			File objOutFile = new File(strOuputFilePath);
			Writer objOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(objOutFile), "UTF-8"));

			objT.process(map, objOut);
			objOut.flush();
			objOut.close();
			logger.info("成功创建了一个word文件 ！");
		}
		catch (FileNotFoundException e)
		{
			logger.error("文件没有找到--->" + e.toString());
		}
		catch (IOException e)
		{
			logger.error(e.toString());
		}
		catch (TemplateException e)
		{
			logger.error(e.toString());
		}
	}

	/**
	 * 取得图片。
	 * 
	 * @param strImgFilePath 图片保存的路径。
	 *            
	 * @return 经过base64编码的值。
	 */
	public static String getImageStr(String strImgFilePath)
	{
		InputStream objIn = null;
		BASE64Encoder objEncoder;
		byte[] arrayData = null;

		try
		{
			// in = new FileInputStream(imgFilePath);。
			objIn = getUrlImg(strImgFilePath);
			arrayData = new byte[objIn.available()];
			objIn.read(arrayData);
			objIn.close();
		}
		catch (Exception e)
		{
			logger.error(e.toString());
		}
		objEncoder = new BASE64Encoder();
		return objEncoder.encode(arrayData);
	}

	/**
	 * 从网络上取得图片。
	 * @param strURLName
	 * @return
	 * @throws Exception
	 */
	public static InputStream getUrlImg(String strURLName) throws Exception
	{
		InputStream objInputStream;
		int nResult = 0; // 服务器返回的状态
		URL objUrl = new URL(strURLName); // 创建URL
		HttpURLConnection objUrlconn = (HttpURLConnection) objUrl.openConnection(); // 试图连接并取得返回状态码urlconn.connect();

		objUrlconn.setRequestMethod("POST");
		objUrlconn.setReadTimeout(1000 * 5);
		nResult = objUrlconn.getResponseCode();
		logger.info("取得图片时返回的状态码：" + nResult);

		// 不等于HTTP_OK说明连接不成功("fail");
		if (nResult != HttpURLConnection.HTTP_OK)
		{
			logger.info("取得图片时出错");
			return null;
		}
		objInputStream = objUrlconn.getInputStream();
		if (null != objInputStream)
		{
			return objInputStream;
		}
		return null;
	}

	/**
	 * 将map中的null值转换为“”。
	 * 
	 * @param mapObj
	 * @return
	 */
	private Map<String, Object> mapConsoleNullInMap(Map<String, Object> mapObj)
	{
		Map<String, Object> mapConsoleMap;

		mapConsoleMap = new HashMap<String, Object>();
		for (Map.Entry<String, Object> entry : mapObj.entrySet())
		{
			String strKey = entry.getKey();
			Object objValue = entry.getValue();

			if (null == objValue)
			{
				objValue = " ";
			}
			mapConsoleMap.put(strKey, objValue);
		}
		return mapConsoleMap;
	}

	/**
	 * 取得项目路径。
	 * 
	 * @return
	 */
	public static String getContextPath()
	{
		HttpServletRequest objRequest = ServletActionContext.getRequest();
		String strPath = objRequest.getScheme() + "://" + objRequest.getServerName() + ":" + objRequest.getServerPort() + objRequest.getContextPath();

		return strPath;
	}

	/**
	 * http://localhost:8080。
	 * @return
	 */
	public static String getContextPathWithoutName()
	{
		String strPath;
		HttpServletRequest objRequest = ServletActionContext.getRequest();

		strPath = objRequest.getScheme() + "://" + objRequest.getServerName() + ":" + objRequest.getServerPort();
		return strPath;
	}

	// public static void test() throws Exception
	// {
	// String imgFile = "c:/psb.jpg";
	// String ftlFile = "zhongshen.ftl";
	// String strOuputFilePath = "c:/outFile.doc";
	// Map<String, Object> fieldMap = new HashMap<String, Object>();
	// map.put("fullName", "机构全称");
	// map.put("brief", "机构简介");
	// map.put("limit", "所有制");
	// map.put("address", "注册地址");
	// map.put("code", "组织机构代码");
	// map.put("money", "注册资金");
	// map.put("legalPerson", "机构法人");
	// String downloadPath =
	// "http://t2.baidu.com/it/u=1342745825,1863276617&fm=52&gp=0.jpg";
	// downloadPath = DataFormat.encode4FreeMarker(downloadPath);
	// map.put("legalPersonPath", downloadPath);
	// map.put("image", getImageStr(imgFile));

	// fieldMap.put("fcID", "61");
	// fieldMap.put("times", "times"); // 会议场次
	// fieldMap.put("dateTime", "2007-09-26 07:05-09:10"); // 会议时间
	// fieldMap.put("address", "address"); // 会议地址
	// fieldMap.put("theme", "sffsffd"); // 会议主题
	// fieldMap.put("state", " "); // 会议状态
	// fieldMap.put("attache", "YunWei/FinalConf_20121009/trainingitem.txt");//
	// 会议附件

	// HttpServletRequest request = ServletActionContext.getRequest ();
	// String path =
	// request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	// String attachepath =
	// "http://localhost:8080/ZhiXieYunWei/FinalConf_20121009/trainingitem.txt";
	// 对附件地址的特殊字符进行转码
	// attachepath = DataFormat.encode4FreeMarker(attachepath);
	// fieldMap.put("attachepath", attachepath); // 会议附件下载地址
	// fieldMap.put("reserver1", " "); // 备用字段，用于扩展
	// fieldMap.put("reserver2", " ");
	// fieldMap.put("reserver3", " ");
	// fieldMap.put("reserver4", " ");
	// fieldMap.put("reserver5", " ");
	// fieldMap.put("reserver6", " ");
	// fieldMap.put("reserver7", " ");
	// fieldMap.put("reserver8", " ");
	// fieldMap.put("reserver9", " ");
	// fieldMap.put("reserver10", " ");

	// map.put("fullNamePath",
	// "http://t1.baidu.com/it/u=2174856151,1028399492&fm=52&gp=0.jpg");
	// map.put("briefPath",
	// "http://t1.baidu.com/it/u=1426869953,975480262&fm=23&gp=0.jpg");
	// map.put("limitPath",
	// "http://t1.baidu.com/it/u=624559461,234694155&fm=52&gp=0.jpg");
	// map.put("legalPerson",
	// "http://t2.baidu.com/it/u=2965883145,2953172960&fm=52&gp=0.jpg");
	// map.put("addressPath", "http://http://image.baidu.com");
	// map.put("codePath",
	// "http://t3.baidu.com/it/u=4218118579,517944011&fm=52&gp=0.jpg");
	// map.put("moneyPath",
	// "http://t1.baidu.com/it/u=1293670733,2993885551&fm=52&gp=0.jpg");
	// map.put("legalPersonPath", "http://http://image.baidu.com");

	// DocUtil createDoc = new DocUtil(fieldMap, ftlFile, strOuputFilePath);
	//
	// // createDoc.create();
	// createDoc.create();
	// }

}
