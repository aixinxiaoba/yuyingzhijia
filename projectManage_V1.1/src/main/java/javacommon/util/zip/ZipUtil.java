package javacommon.util.zip;

import java.io.File;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream; // 这个包在ant.jar里，要到官方网下载
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * 基于Ant的Zip压缩工具类:--- 。
 *                   1:---JDK的Zip压缩工具类常出现中文乱码问题/所以用ant.jar的实现比较好 。
 * 
 * @author Administrator
 * 
 */
public class ZipUtil
{
	/**
	 * 日志对象。
	 */
	private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);

	/**
	 * 默认的编码
	 */
	public static final String ENCODING_DEFAULT = "UTF-8";
	
	private static final int buffer = 2048;

	/**
	 * 默认的缓冲区大小
	 */
	public static final int BUFFER_SIZE_DIFAULT = 128;
	
	/**
	 * 构造方法。
	 */
	public ZipUtil()
	{
	}

	/**
	 * inputFileName 输入一个文件夹 zipFileName 输出一个压缩文件夹。
	 */
	public void zip(String strInputFileName, String strZipFileName) throws Exception
	{

		logger.info(strZipFileName);
		zip(strZipFileName, new File(strInputFileName));
	}

	/**
	 * 设置路径。
	 * 
	 * @param strInputFileName
	 *            输入一个文件夹
	 * @param strZipFileName
	 *            输出一个压缩文件夹
	 * @param strEncode
	 *            设置编码格式
	 * @throws Exception
	 */
	public void zip(String strInputFileName, String strZipFileName, String strEncode) throws Exception
	{

		logger.info(strZipFileName);
		zip(strZipFileName, new File(strInputFileName), strEncode);
	}

	/**
	 * 设置。
	 * 
	 * @param strZipFileName
	 * @param objInputFile
	 * @throws Exception
	 */
	private void zip(String strZipFileName, File objInputFile) throws Exception
	{
		ZipOutputStream objOut = new ZipOutputStream(new FileOutputStream(strZipFileName));

		objOut.setEncoding("gbk");
		zip(objOut, objInputFile, "", strZipFileName);
		logger.info("zip done");
		objOut.close();
	}

	/**
	 *设置。
	 * 
	 * @param strZipFileName
	 * @param objInputFile
	 * @param strEncode
	 * @throws Exception
	 */
	private void zip(String strZipFileName, File objInputFile, String strEncode) throws Exception
	{
		ZipOutputStream objOut = new ZipOutputStream(new FileOutputStream(strZipFileName));

		objOut.setEncoding(strEncode);
		zip(objOut, objInputFile, "", strZipFileName);
		logger.info("zip done");
		objOut.close();
	}

	/**
	 * 设置。
	 * 
	 * @param objOut
	 * @param objF
	 * @param strBase
	 * @param strZipFileName
	 * @throws Exception
	 */
	private void zip(ZipOutputStream objOut, File objF, String strBase, String strZipFileName) throws Exception
	{
		if (objF.isDirectory())
		{
			File[] arrayFl = objF.listFiles();

			objOut.putNextEntry(new org.apache.tools.zip.ZipEntry(strBase + "/"));
			strBase = strBase.length() == 0 ? "" : strBase + "/";
			for (int i = 0; i < arrayFl.length; i++)
			{
				String strExcludeFileName = strZipFileName.substring(strZipFileName.lastIndexOf("/") + 1, strZipFileName.length());

				if (arrayFl[i].getName().equals(strExcludeFileName))
				{
					// 过滤掉打包生成的文件
					continue;
				}
				zip(objOut, arrayFl[i], strBase + arrayFl[i].getName(), strZipFileName);
			}
		}
		else
		{
			FileInputStream objIn = new FileInputStream(objF);
			int nB;

			objOut.putNextEntry(new org.apache.tools.zip.ZipEntry(strBase));
			logger.info(strBase);
			while ((nB = objIn.read()) != -1)
			{
				objOut.write(nB);
			}
			objIn.close();
		}
	}
	
	/**
	 * 压缩文件 。
	 * 
	 * @param arrayInFilePaths ：---需要压缩的文件列表 。
	 * @param strZipPath     :---压缩后的文件路径
	 * @throws Exception
	 */
	public static void makeZip(String[] arrayInFilePaths, String strZipPath) throws Exception
	{
		makeZip(arrayInFilePaths, strZipPath, ENCODING_DEFAULT);
	}

	/**
	 * 压缩文件 。
	 * 
	 * @param arrayInFilePaths  ：---需要压缩的文件列表 。
	 * @param strZipPath      :---压缩后的文件路径
	 * @param strEncoding     :---文件编码
	 * @throws Exception
	 */
	public static void makeZip(String[] arrayInFilePaths, String strZipPath, String strEncoding) throws Exception
	{
		File[] arrayInFiles = new File[arrayInFilePaths.length];
		
		for (int i = 0; i < arrayInFilePaths.length; i++)
		{
			arrayInFiles[i] = new File(arrayInFilePaths[i]);
		}
		
		makeZip(arrayInFiles, strZipPath, strEncoding);
	}

	/**
	 * 压缩文件 。
	 * 
	 * @param arrayInFiles
	 * @param strZipPath
	 * @throws Exception
	 */
	public static void makeZip(File[] arrayInFiles, String strZipPath) throws Exception
	{
		makeZip(arrayInFiles, strZipPath, ENCODING_DEFAULT);
	}

	/**
	 * 压缩文件 。
	 * 
	 * @param arrayInFiles
	 * @param strZipPath
	 * @param strEncoding
	 * @throws Exception
	 */
	public static void makeZip(File[] arrayInFiles, String strZipPath, String strEncoding) throws Exception
	{
		File objFfile;
		
		ZipOutputStream objZipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(strZipPath)));
		
		objZipOut.setEncoding(strEncoding);
		for (int i = 0; i < arrayInFiles.length; i++)
		{
			objFfile = arrayInFiles[i];
			doZipFile(objZipOut, objFfile, objFfile.getParent());
		}
		
		// 关闭相关的流
		objZipOut.flush();
		objZipOut.close();
	}

	/**
	 * 压缩文件 。
	 * 
	 * @param objZipOut
	 * @param objFile
	 * @param strDirPath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void doZipFile(ZipOutputStream objZipOut, File objFile, String strDirPath) throws FileNotFoundException, IOException
	{
		BufferedInputStream objBis;
		String strZipName;
		ZipEntry objEntry;
		byte[] arrayBuff;
		int nSize;
		File[] arraySubFiles;
		
		if (objFile.isFile())
		{
			objBis = new BufferedInputStream(new FileInputStream(objFile));
			
			strZipName = objFile.getPath().substring(strDirPath.length());
			while (strZipName.charAt(0) == '\\' || strZipName.charAt(0) == '/')
			{
				strZipName = strZipName.substring(1);
			}
			
			objEntry = new ZipEntry(strZipName);
			objZipOut.putNextEntry(objEntry);
			arrayBuff = new byte[BUFFER_SIZE_DIFAULT];
			
			while ((nSize = objBis.read(arrayBuff, 0, arrayBuff.length)) != -1)
			{
				objZipOut.write(arrayBuff, 0, nSize);
			}
			
			objZipOut.closeEntry();
			objBis.close();
		}
		else
		{
			arraySubFiles = objFile.listFiles();
			for (File objSubFile : arraySubFiles)
			{
				doZipFile(objZipOut, objSubFile, strDirPath);
			}
		}
	}

	/**
	 * 解压文件 。
	 * 
	 * @param strZipFilePath
	 * @param strStorePath
	 * @throws IOException
	 */
	public static void unZip(String strZipFilePath, String strStorePath) throws IOException
	{
		unZip(new File(strZipFilePath), strStorePath);
	}

	/**
	 * 解压文件 。
	 * 
	 * @param objZipFile
	 * @param strStorePath
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void unZip(File objZipFile, String strStorePath) throws IOException
	{
		ZipFile objZip;
		Enumeration<ZipEntry> objEntries;
		ZipEntry objZzipEntry;
		String strZipEntryName;
		String strZipEntryDir;
		String strUnzipFileDir;
		File objUnzipFileDirFile;
		InputStream objIis;
		FileOutputStream objFfos;
		byte[] arrayBbuff;
		int nSize;
		
		// 如果文件目录存在，则需要删除目录
		if (new File(strStorePath).exists())
		{
			new File(strStorePath).delete();
		}
		
		// 新建解压后的文件目录 。
		new File(strStorePath).mkdirs();

		// objZip = new ZipFile(objZipFile);
		objZip = new ZipFile(objZipFile.getAbsolutePath(),"GBK");
		objEntries = (Enumeration<ZipEntry>) objZip.getEntries();
		while (objEntries.hasMoreElements())
		{
			objZzipEntry = objEntries.nextElement();
			if (objZzipEntry.isDirectory())
			{
				// Undone
			}
			else
			{
				strZipEntryName = objZzipEntry.getName();
				
				if (strZipEntryName.contains("/"))
				{
				    strZipEntryName = strZipEntryName.replaceAll("/", "\\\\");
				}
				if (strZipEntryName.indexOf(File.separator) > 0)
				{
					strZipEntryDir = strZipEntryName.substring(0, strZipEntryName.lastIndexOf(File.separator) + 1);
					strUnzipFileDir = strStorePath + File.separator + strZipEntryDir;
					objUnzipFileDirFile = new File(strUnzipFileDir);
					if (!objUnzipFileDirFile.exists())
					{
						objUnzipFileDirFile.mkdirs();
					}
				}

				objIis = objZip.getInputStream(objZzipEntry);
				
				objFfos = new FileOutputStream(new File(strStorePath + File.separator + strZipEntryName));
				arrayBbuff = new byte[BUFFER_SIZE_DIFAULT];
				while ((nSize = objIis.read(arrayBbuff)) > 0)
				{
					objFfos.write(arrayBbuff, 0, nSize);
				}
				
				objFfos.flush();
				objFfos.close();
				objIis.close();
			}
		}
	}
	
	/**
	 *解压zip文件。
	 * 
	 * @param strPath zip文件路径
	 * @param strSavePath 解压目标路径
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static void unZip2(String strPath, String strSavePath) throws IOException
	{
		int nCount = -1;
		int nIndex = -1;
		boolean bFlag = false;
		File objFile = null;
		InputStream objIsStream = null;
		FileOutputStream objFos = null;
		BufferedOutputStream objBos = null;
		Enumeration objEntries;
		ZipFile objZipFile;
		
		// 判断文件夹是否存在,如果不存在则创建文件夹
        if (!new File(strSavePath).exists())
        {
        	new File(strSavePath).mkdirs();
        }
		objZipFile = new ZipFile(strPath);
		objEntries = objZipFile.getEntries();
		while (objEntries.hasMoreElements())
		{
			byte arrayBuf[] = new byte[buffer];
			ZipEntry objEntry = (ZipEntry) objEntries.nextElement();
			String strFilename = objEntry.getName();
			
			nIndex = strFilename.lastIndexOf("/");
			if (nIndex > -1)
			{
				strFilename = strFilename.substring(nIndex + 1);
			}
			strFilename = strSavePath + strFilename;
			bFlag = isPics(strFilename);
			if (!bFlag)
			{
				continue;
			}
			objFile = new File(strFilename);
			objFile.createNewFile();
			objIsStream = objZipFile.getInputStream(objEntry);
			objFos = new FileOutputStream(objFile);
			objBos = new BufferedOutputStream(objFos, buffer);
			while ((nCount = objIsStream.read(arrayBuf)) > -1)
			{
				objBos.write(arrayBuf, 0, nCount);
			}
			objFos.close();
			objIsStream.close();
		}
		objZipFile.close();
	}
	
	/**
	 * 判断是否是图片文件。
	 * 
	 * @param strFilename
	 * @return
	 */
	public static boolean isPics(String strFilename)
	{
		boolean bFlag = false;
		
		if (strFilename.endsWith(".jpg") || strFilename.endsWith(".gif") || strFilename.endsWith(".bmp") || strFilename.endsWith(".png"))
		{
			bFlag = true;
		}
		return bFlag;
	}

	/**
	 * Just for testing 。
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		String strFilePath1 = "E:/Config/SAASProduct/upload/project\\9\\ZKZ\\36\\81\\41\\566.pdf";
		String strFilePath2 = "E:/Config/SAASProduct/upload/project\\9\\ZKZ\\36\\81\\41\\565.pdf";
		
		String strRootDir = "G:\\ZIP\\txt\\";
		String strZipPath = "G:\\ZIP\\test.zip";
		
		// File[] inFiles = new File(rootDir).listFiles();
		// makeZip(inFiles, zipPath);
		// makeZip(new String[] { strFilePath1 , strFilePath2 }, strZipPath, "gbk");

		unZip("D:/war/测试用照片(1).zip", "D:/war/1");
	}
}