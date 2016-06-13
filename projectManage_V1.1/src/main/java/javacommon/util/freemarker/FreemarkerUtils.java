package javacommon.util.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.core.Config;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 初始化Freemarker并生成模版的类 。
 * 
 * ex: <p> ServletContext inServletContext =
 * request.getSession().getServletContext(); Freemarker freemarker = new
 * Freemarker(); freemarker.init(inServletContext); String test =
 * freemarker.run("AllNews.html", tokens); </p>
 * 
 * @author wangzg
 * 
 */
public class FreemarkerUtils
{
	/**
	 * 日志对象
	 */
	protected static final Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);

	/**
	 * 关联Freemarker.jar的Configuration实例
	 */
	private static Configuration objFmConfig;

	/**
	 * 初始化FreeMarker配置。
	 * 
	 * @param objInServletContext Servlet上下文
	 */
	public void init(ServletContext objInServletContext)
	{
		if (objFmConfig == null)
		{
			objFmConfig = new Configuration();
			objFmConfig.setDefaultEncoding("UTF-8");
			objFmConfig.setObjectWrapper(new DefaultObjectWrapper());
			// 设置FreeMarker的模版文件位置/resource/
			objFmConfig.setServletContextForTemplateLoading(objInServletContext, "/template");
		}
	}
	
	/**
	 * 初始化FreeMarker配置 类路径。
	 * 
	 * @param objInServletContext Servlet上下文
	 */
	public void init()
	{
		if (objFmConfig == null)
		{
			objFmConfig = new Configuration();
			objFmConfig.setDefaultEncoding("UTF-8");
			objFmConfig.setObjectWrapper(new DefaultObjectWrapper());
			// 设置FreeMarker的模版文件位置/resource/
			objFmConfig.setClassForTemplateLoading(FreemarkerUtils.class, "/resource/template/");
		}
	}

	/**
	 * 获得模版文件，建立数据模型，生成Freemarker模版。
	 * 
	 * @param strTemplName 模版文件
	 * @param mapInData 数据模型
	 * @return 生成的模版文件
	 */
	@SuppressWarnings("unchecked")
	public String run(String strTemplName, Map mapInData) throws Exception
	{
		Template objTemplate = null;
		Writer objOut;
		
		try
		{
			objTemplate = objFmConfig.getTemplate(strTemplName);
		}
		catch (IOException ex)
		{
			logger.error("Freemarker.run() - 不能加载Freemarker模版 " + strTemplName + "..不在环境变量 - (Error: " + ex);
			return "error";
		}

		// 输出生成流
		objOut = new StringWriter();
		objTemplate.process(mapInData, objOut);
		objOut.flush();

		// 返回结果
		return objOut.toString();
	}
	
	/**
	 * 获得模版文件，建立数据模型，生成Freemarker模版。
	 * 
	 * @param strTemplName 模版文件
	 * @param mapInData 数据模型
	 * @return 生成的模版文件
	 */
	public void createFile(String strTemplName, Map mapInData, String strPath) throws Exception
	{
		Template objTemplate = null;
		Writer objOut;
		String strSystemPath = Config.objSAASConfig.getProperty("app.system.path.local");
		// String strSystemPath = "E:\\WorkSpace\\project\\projectManage_V4.0_20150523\\WebRoot\\";
		String path = strSystemPath + strPath;
		File objFile = new File((path).substring(0,path.lastIndexOf("/") > 0 ? path.lastIndexOf("/") : 0));
		
		if (!objFile.exists())
		{
			objFile.mkdirs();
		}
		OutputStream objOutputStream = new FileOutputStream(strSystemPath + strPath);
		objOut = new BufferedWriter(new OutputStreamWriter(objOutputStream, "UTF-8"));
		
		try
		{
			objTemplate = objFmConfig.getTemplate(strTemplName);
			// 输出生成流
			objTemplate.process(mapInData, objOut);
		}
		catch (IOException ex)
		{
			logger.error("Freemarker.createFile() - 出现异常: " + ex);
		}
		finally
		{
			objOut.flush();
			objOut.close();
			objOutputStream.flush();
			objOutputStream.close();
		}
	}
	
	/**
	 * Just for testing 。
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception
	{
		 Configuration objFmConfig = new Configuration();
		 objFmConfig.setDefaultEncoding("UTF-8");
		 objFmConfig.setObjectWrapper(new DefaultObjectWrapper());
//		
//		 // 设置FreeMarker的模版文件位置
////	 objFmConfig.setServletContextForTemplateLoading(objInServletContext, "templates");
		 objFmConfig.setClassForTemplateLoading(FreemarkerUtils.class, "/resource/");
//		
		 Template objTemplate = objFmConfig.getTemplate("index.ftl");
//		
//		// 数据
//		 Map<String, String> mapData = new HashMap<String, String>();
//		 mapData.put("PName", "55555");
		 
		 Map<String, List> mapList = new HashMap<String, List>();
		 List lst = new ArrayList();
		 lst.add("111");
		 lst.add("222");
		 mapList.put("lstUser", lst);
		
//		 mapData.put("pic", PicUtils.getImageStr("g:/Document/Pthl/pics/15.JPG"));
//		 mapData.put("pic", "g:/Document/Pthl/pics/15.JPG");
		
		 Writer objOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\WorkSpace\\project\\projectManage_V4.0_20150523\\WebRoot\\index.html"), "UTF-8"));
		 objTemplate.process(mapList, objOut);
		 objOut.flush();
		 objOut.close();
		
	}
	
}
