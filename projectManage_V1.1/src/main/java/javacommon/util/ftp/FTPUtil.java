package javacommon.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

/**
 * FTP �����ࡣ
 * 
 * @author wangzg
 * 
 */
public class FTPUtil
{
//	/**
//	 * ��־����
//	 */
//	private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
//
//	/**
//	 * Ĭ�ϵ�FTP��ַ��
//	 */
//	public String strFTPPath = "10.72.63.32";
//
//	/**
//	 * �û���
//	 */
//	public String strUserName = "admin";
//
//	/**
//	 * ����
//	 */
//	public String strPassword = "admin";
//
//	/**
//	 * ����Sun��FtpClient
//	 */
//	private FtpClient objFtpClient = null;
//
//	/**
//	 * �չ��� ��
//	 */
//	public FTPUtil()
//	{
//	}
//
//	/**
//	 * ���캯�� ��
//	 * 
//	 * @param strFTPPath
//	 * @param strUserName
//	 * @param strPassword
//	 */
//	public FTPUtil(String strFTPPath, String strUserName, String strPassword)
//	{
//		super();
//		this.strFTPPath = strFTPPath;
//		this.strUserName = strUserName;
//		this.strPassword = strPassword;
//	}
//
//	/**
//	 * ����ftp������ ��
//	 * 
//	 * @throws IOException
//	 */
//	private void connect() throws IOException
//	{
//
//		objFtpClient = new FtpClient();
//		objFtpClient.openServer(strFTPPath);
//		objFtpClient.login(strUserName, strPassword);
//
//		// ��2�����ϴ�������
//		objFtpClient.binary();
//	}
//
//	/**
//	 * FTP�ϴ��ļ� ��
//	 * 
//	 * @param strSubPath
//	 * @param strFilename
//	 * @param strNewname
//	 * @return
//	 * @throws Exception
//	 */
//	public long upload(String strSubPath, String strFilename, String strNewname) throws Exception
//	{
//		long lResult = 0;
//		TelnetOutputStream objOs = null;
//		FileInputStream objIs = null;
//		
//		// ����FTP Server
//		connect();
//
//		// path��ftp��������Ŀ¼����Ŀ¼
//		if (strSubPath != null && strSubPath.length() != 0)
//		{
//			objFtpClient.cd(strSubPath);
//		}
//
//		try
//		{
//			File objFile_in = new File(strFilename);
//			byte[] arrayBytes = new byte[1024];
//			int nIndex;
//			
//			if (!objFile_in.exists())
//			{
//				return -1;
//			}
//
//			if (objFile_in.length() == 0)
//			{
//				return -2;
//			}
//
//			objOs = objFtpClient.put(strNewname);
//			lResult = objFile_in.length();
//			objIs = new FileInputStream(objFile_in);
//
//			while ((nIndex = objIs.read(arrayBytes)) != -1)
//			{
//				objOs.write(arrayBytes, 0, nIndex);
//			}
//		}
//		finally
//		{
//			if (objIs != null)
//			{
//				objIs.close();
//			}
//
//			if (objOs != null)
//			{
//				objOs.close();
//			}
//		}
//
//		// �Ͽ�����
//		closeServer();
//
//		// ���ؽ��
//		return lResult;
//	}
//
//	/**
//	 * FTP �ϴ��ļ� ��
//	 * 
//	 * @param strSubPath
//	 * @param strFilename
//	 * @return
//	 * @throws Exception
//	 */
//	public long upload(String strSubPath, String strFilename) throws Exception
//	{
//		String strNewname = "";
//		
//		if (strFilename.indexOf("/") > -1)
//		{
//			strNewname = strFilename.substring(strFilename.lastIndexOf("/") + 1);
//		}
//		else
//		{
//			strNewname = strFilename;
//		}
//
//		// ���ؽ��
//		return upload(strSubPath, strFilename, strNewname);
//	}
//
//	/**
//	 * FTP �ļ����� ��
//	 * 
//	 * @param strSubPath
//	 * @param strFilename
//	 * @param strNewfilePath
//	 * @return
//	 * @throws Exception
//	 */
//	public long download(String strSubPath, String strFilename, String strNewfilePath) throws Exception
//	{
//		long lResult = 0;
//		TelnetInputStream objIs = null;
//		FileOutputStream objOs = null;
//		
//		// ����FTP Server
//		connect();
//
//		// path��ftp��������Ŀ¼����Ŀ¼
//		if (strSubPath != null && strSubPath.length() != 0)
//		{
//			objFtpClient.cd(strSubPath);
//		}
//
//		try
//		{
//			File objOutfile;
//			byte[] arrayBytes = new byte[1024];
//			int nIndex;
//			
//			objIs = objFtpClient.get(strFilename);
//			
//			objOutfile = new File(strNewfilePath);
//
//			objOs = new FileOutputStream(objOutfile);
//			
//			while ((nIndex = objIs.read(arrayBytes)) != -1)
//			{
//				objOs.write(arrayBytes, 0, nIndex);
//				lResult = lResult + nIndex;
//			}
//		}
//		catch (IOException e)
//		{
//			logger.error("", e);
//		}
//		finally
//		{
//			if (objIs != null)
//			{
//				objIs.close();
//			}
//
//			if (objOs != null)
//			{
//				objOs.close();
//			}
//		}
//
//		// �Ͽ�����
//		closeServer();
//
//		// ���ؽ��
//		return lResult;
//	}
//
//	/**
//	 * �Ͽ����� ��
//	 * 
//	 * @throws java.io.IOException
//	 */
//	public void closeServer() throws IOException
//	{
//		try
//		{
//			if (objFtpClient != null)
//			{
//				objFtpClient.closeServer();
//			}
//		}
//		catch (IOException e)
//		{
//			logger.error("", e);
//		}
//	}

}