package javacommon.util.file;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javacommon.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具类:--- 1:---定义一些常用的文件操作 。
 * 
 * @author wangzg
 * 
 */
public class FileUtils
{
	/**
	 * 日志对象。
	 */
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * UTF-8编码。
	 */
	private static final String ENCODING_UTF8 = "UTF-8";

	/**
	 * 把数据写入到output字符串指定的文件中 。
	 * 
	 * @param strOutput
	 * @param strContent
	 * @throws Exception
	 */
	public static void createFile(String strOutput, String strContent) throws Exception
	{
		OutputStreamWriter objFw = null;
		PrintWriter objOut = null;
		
		try
		{
			objFw = new OutputStreamWriter(new FileOutputStream(strOutput), ENCODING_UTF8);
			objOut = new PrintWriter(objFw);
			objOut.print(strContent);
		}
		catch (Exception ex)
		{
			throw new Exception(ex);
		}
		finally
		{
			if (objOut != null)
			{
				objOut.close();
			}

			if (objFw != null)
			{
				objFw.close();
			}
		}

	}

	/**
	 * 读取一个文件中的所有内容 。
	 * 
	 * @param strInput
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String strInput) throws Exception
	{
		char[] arrayBuffer = new char[4096];
		int nLen = 0;
		StringBuffer sbufContent = new StringBuffer(4096);
		InputStreamReader objFr = null;
		BufferedReader objBr = null;
		
		try
		{
			objFr = new InputStreamReader(new FileInputStream(strInput), ENCODING_UTF8);
			objBr = new BufferedReader(objFr);
			while ((nLen = objBr.read(arrayBuffer)) > -1)
			{
				sbufContent.append(arrayBuffer, 0, nLen);
			}
		}
		catch (Exception e)
		{
			throw new Exception(e);
		}
		finally
		{
			if (objBr != null)
			{
				objBr.close();
			}

			if (objFr != null)
			{
				objFr.close();
			}
		}

		// 返回结果
		return sbufContent.toString();
	}

	/**
	 * 移动文件到新的路径，移动后旧的文件将不存在 。
	 * 
	 * @param String input file to move from
	 * @param String output file
	 * 
	 */
	public static void moveFile(String strInput, String strOutput) throws Exception
	{
		File objInputFile = new File(strInput);
		File objOutputFile = new File(strOutput);
		
		try
		{
			objInputFile.renameTo(objOutputFile);
		}
		catch (Exception ex)
		{
			throw new Exception("Can not mv" + strInput + " to " + strOutput + ex.getMessage());
		}

	}

	/**
	 * 文件复制 。
	 * 
	 * @param String input file to copy from
	 * @param String output file
	 */
	public static boolean copyFile(String strInput, String strOutput) throws Exception
	{
		File objFile = new File(strOutput);
		int nBufsize;// 用流复制文件内容
		FileInputStream objFis;
		FileOutputStream objFos;
		
		// 如果目标文件不存在，则新建文件和目录
		if (!objFile.exists())
		{
			String strDirPaht;
			
			logger.info(objFile.getAbsolutePath() + " 不存在，现在新建文件~");
			strDirPaht = objFile.getAbsolutePath().substring(0, objFile.getAbsolutePath().lastIndexOf("\\"));
			
			new File(strDirPaht).mkdirs();
		}

		// 用流复制文件内容
		nBufsize = 65536;
		objFis = new FileInputStream(strInput);
		objFos = new FileOutputStream(strOutput);
		
		try
		{
			int nIndex;
			byte[] arrayOuf = new byte[nBufsize];
			
			while ((nIndex = objFis.read(arrayOuf)) > -1)
			{
				objFos.write(arrayOuf, 0, nIndex);
			}

		}
		catch (Exception ex)
		{
			throw new Exception("makehome" + ex.getMessage());
		}
		finally
		{
			objFis.close();
			objFos.close();
		}

		// 返回结果
		return true;
	}

	/**
	 * 生成目录 。
	 * 
	 * @param strHome
	 * @throws Exception
	 */
	public static void createDirectorys(String strHome) throws Exception
	{
		File objHomedir = new File(strHome);
		
		if (!objHomedir.exists())
		{
			try
			{
				objHomedir.mkdirs();
			}
			catch (Exception ex)
			{
				throw new Exception("Can not mkdir :" + strHome + " Maybe include special charactor!");
			}
		}
	}

	/**
	 * 根据文件路径创建文件，如果路径不存在，则先创建该路径。
	 * 
	 * @param strFilePath
	 * @return
	 * @throws Exception
	 */
	public static File createDirectorysAndReturn(String strFilePath) throws Exception
	{
		File objHomedir = new File(strFilePath);
		
		if (!objHomedir.exists())
		{
			try
			{
				objHomedir.mkdirs();
			}
			catch (Exception ex)
			{
				throw new Exception("Can not mkdir :" + strFilePath + " Maybe include special charactor!");
			}
		}

		// 返回结果
		return objHomedir;
	}

	/**
	 * 复制整个目录:---包含子目录 。
	 * 
	 * @param strSourcedir
	 * @param strDestdir
	 * @throws Exception
	 */
	public static void CopyDir(String strSourcedir, String strDestdir) throws Exception
	{
		File objDest = new File(strDestdir);
		File objSource = new File(strSourcedir);
		String[] arrayFiles;
		
		// 如果目标目录不存在，需要新建目录 。
		try
		{
			createDirectorysAndReturn(strDestdir);
		}
		catch (Exception ex)
		{
			throw new Exception("CopyDir:" + ex.getMessage());
		}

		// 遍历源文件夹下的所有文件和目录
		arrayFiles = objSource.list();
		
		for (int i = 0; i < arrayFiles.length; i++)
		{
			String strSourcefile = objSource + File.separator + arrayFiles[i];
			String strDestfile = objDest + File.separator + arrayFiles[i];
			File objTemp = new File(strSourcefile);
			
			if (objTemp.isFile())
			{
				try
				{
					copyFile(strSourcefile, strDestfile);
				}
				catch (Exception ex)
				{
					throw new Exception("CopyDir:" + ex.getMessage());
				}
			}
			else
			{
				// 目录的貌似没处理呢
			}
		}
	}

	/**
	 * 删除指定文件夹下所有文件。
	 * 
	 * @param strPath 文件夹完整绝对路径
	 * @return
	 */
	public static boolean delAllFile(String strPath)
	{
		boolean bFlag = false;
		File objFile = new File(strPath);
		
		if (!objFile.exists())
		{
			return bFlag;
		}

		if (!objFile.isDirectory())
		{
			return bFlag;
		}

		try
		{
			deleteDir(objFile, false);
			bFlag = true;
		}
		catch (Exception e)
		{
			logger.error("", e);
		}

		// 返回结果
		return bFlag;
	}

	/**
	 * 递归的删除目录下的所有文件和子目录 。
	 * 
	 * @param objDirectory
	 * @throws Exception
	 */
	public static void deleteDir(File objDirectory, boolean bDeleteThis) throws Exception
	{
		String[] arrayFilelist;// 遍历所有的文件和目录
		File objFile;
		
		// 参数验证
		if (!objDirectory.exists())
		{
			throw new IOException(objDirectory.toString() + " do not exist!");
		}

		arrayFilelist = objDirectory.list();

		for (int i = 0; i < arrayFilelist.length; i++)
		{
			objFile = new File(objDirectory.getAbsolutePath(), arrayFilelist[i]);
			
			if (objFile.isDirectory())
			{
				deleteDir(objFile, true);
			}
			else if (objFile.isFile())
			{
				try
				{
					objFile.delete();
				}
				catch (Exception ex)
				{
					throw new Exception(objFile.toString() + " can not be deleted " + ex.getMessage());
				}
			}
		}

		// 最后删除掉此目录
		if (bDeleteThis)
		{
			try
			{
				objDirectory.delete();
			}
			catch (Exception ex)
			{
				throw new Exception(objDirectory.toString() + " can not be deleted " + ex.getMessage());
			}
			finally
			{
				arrayFilelist = null;
			}
		}

	}

	/**
	 * 拿到XML文件的路径 。
	 * 
	 * @param strFilePathName
	 * @return
	 */
	public String getConfPathXmlFile(String strFilePathName)
	{
		int nIdex = strFilePathName.lastIndexOf(".xml");
		String strName = strFilePathName.substring(0, nIdex);
		
		strName = strName.replace('.', '/');
		strName += ".xml";

		// 返回结果
		return getConfFile(strName);
	}

	/**
	 * 拿到XML文件的路径 。
	 * 
	 * @param strFileName
	 * @return
	 */
	public String getConfFile(String strFileName)
	{
		ClassLoader objClassLoader = Thread.currentThread().getContextClassLoader();// 拿到ClassLoader对象
		URL objConfURL;// 拿到对应的URL对象
		
		if (objClassLoader == null)
		{
			objClassLoader = getClass().getClassLoader();
		}

		objConfURL = objClassLoader.getResource(strFileName);
		
		if (objConfURL == null)
		{
			objConfURL = objClassLoader.getResource("META-INF/" + strFileName);
		}

		// 返回结果
		if (objConfURL == null)
		{
			return null;
		}
		else
		{
			File objFile1 = new File(objConfURL.getFile());
			
			if (objFile1.isFile())
			{
				return objConfURL.getFile();
			}
			else
			{
				return null;
			}
		}

	}

	/**
	 * 拿到XML文件的流结果 。
	 * 
	 * @param strFilePathName
	 * @return
	 */
	public InputStream getConfPathXmlStream(String strFilePathName)
	{
		int nIndex = strFilePathName.lastIndexOf(".xml");
		String strName = strFilePathName.substring(0, nIndex);
		
		strName = strName.replace('.', '/');
		strName += ".xml";

		// 返回结果
		return getConfStream(strName);
	}

	/**
	 * 拿到XML文件的流结果 。
	 * 
	 * @param strFileName
	 * @return
	 */
	public InputStream getConfStream(String strFileName)
	{
		// 拿到ClassLoader对象
		ClassLoader objClassLoader = Thread.currentThread().getContextClassLoader();
		InputStream objStream;
		
		if (objClassLoader == null)
		{
			objClassLoader = this.getClass().getClassLoader();
		}

		objStream = objClassLoader.getResourceAsStream(strFileName);
		
		if (objStream == null)
		{
			objStream = objClassLoader.getResourceAsStream("META-INF/" + strFileName);
		}

		// 返回结果
		return objStream;
	}

	/**
	 * 用用户ID和时间等生成文件名 。
	 * 
	 * @param strUserid 用户登录账号
	 * @param strFileType 文件类型
	 * @return
	 */
	public static String getAutoFilePath(String strUserid, String strFileType)
	{
		Calendar objCal = Calendar.getInstance();
		Date objDate = objCal.getTime();
		SimpleDateFormat objSdf = new SimpleDateFormat("yyyyMMddHHmmssSS");// 时分秒毫秒
		String strFileName = strUserid + "_" + objSdf.format(objDate) + StringUtils.generateRandom() + "." + strFileType;

		// 返回结果
		return strFileName;
	}

	/**
	 * 循环压缩需要压缩的文件。
	 * 
	 * @param lstList 存放需要压缩文件的文件名包含路径
	 * @param strFileName 压缩文件的名称
	 * @return
	 */
	public boolean zipFileProc(List<String> lstList, String strFileName, String strFilePath)
	{
		File objFile = null;
		File objZipFile = new File(strFilePath + strFileName);
		ZipOutputStream objZos;
		
		try
		{
			if (!objZipFile.exists())
			{
				objZipFile.createNewFile();
			}
			
			objZos = new ZipOutputStream(new FileOutputStream(objZipFile));
			
			for (int i = 0; i < lstList.size(); i++)
			{
				objFile = new File(strFilePath + lstList.get(i).toString());
				zipFile(objZos, objFile, lstList.get(i).toString());
				objFile.delete();
			}
			objZos.close();
			return true;
		}
		catch (FileNotFoundException e)
		{
			logger.error("", e);
			return false;
		}
		catch (IOException e)
		{
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 压缩文件基础类。
	 * 
	 * @param objZos 压缩文件流
	 * @param objFile 被压缩文件
	 * @param strFileName 被压缩文件的名
	 */
	public void zipFile(ZipOutputStream objZos, File objFile, String strFileName)
	{
		try
		{
			FileInputStream objFis = new FileInputStream(objFile);
			int nLen;
			
			objZos.putNextEntry(new ZipEntry(strFileName));
			while ((nLen = objFis.read()) != -1)
			{
				objZos.write(nLen);
			}
			// zos.closeEntry();

			objFis.close();
		}
		catch (IOException e)
		{
			logger.error("", e);
		}

	}

	/**
	 * 取得文件大小。
	 * @param objFile
	 * @return
	 * @throws Exception
	 */
	public static long getFileSizes(File objFile) throws Exception
	{
        long lFileSize = 0;
        
        if (objFile.exists())
        {
            FileInputStream objFis = null;
            
            objFis = new FileInputStream(objFile);
            lFileSize = objFis.available();
        }
        else
        {
            logger.info("文件不存在");
        }
        return lFileSize;
    }
	
	/**
	 * Just for testing 。
	 * 
	 * @param arrayArgs
	 * @throws Exception
	 */
	public static void main(String[] arrayArgs) throws Exception
	{
		// FileUtils.copy("G:/data/project/upload/fileTem/2.txt",
		// "G:/data/project/upload/fileTem/2/3/1.txt");
		File objTempFile = new File("G:/b2c_config.xml");
		
		objTempFile.renameTo(new File("G:/Backup/b2c_config.xml"));
	}

}
