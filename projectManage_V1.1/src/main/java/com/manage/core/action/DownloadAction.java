package com.manage.core.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javacommon.core.base.BaseStruts2Action;
import javacommon.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("downloadAction")
@Scope("prototype")
public class DownloadAction extends BaseStruts2Action
{
  private static final long serialVersionUID = 5247707962477605447L;
  private static final Logger logger = LoggerFactory.getLogger(DownloadAction.class);
  private static final String FILE_PATH = "strFilePath";
  private String strFilePath;
  private String strFileName;
  private static final String FILE_DOWNLOAD_SUCCESS = "fileDownloadSucces";

  public String downloadFile()
  {
    return "fileDownloadSucces";
  }

  public InputStream getDownloadFile()
    throws UnsupportedEncodingException
  {
    setStrFilePath();
    try
    {
      return new FileInputStream(this.strFilePath);
    }
    catch (FileNotFoundException e)
    {
      logger.error("文件未找到", e);
    }return null;
  }

  public void setStrFilePath()
    throws UnsupportedEncodingException
  {
    String strFilePath = getRequest().getParameter("strFilePath");

    if (StringUtils.isBlank(strFilePath))
    {
      setErrorText("下载的文件路径为空~");
      return;
    }

    logger.info("要下载的文件的路径是:---" + strFilePath);

    this.strFilePath = strFilePath;

    if (strFilePath.indexOf("/") >= 0)
    {
      this.strFileName = strFilePath.substring(strFilePath.lastIndexOf("/") + 1);
      if (this.strFileName.indexOf("\\") >= 0)
      {
        this.strFileName = this.strFileName.substring(this.strFileName.lastIndexOf("\\") + 1);
      }
    }
    else if (strFilePath.indexOf("\\") >= 0)
    {
      this.strFileName = strFilePath.substring(strFilePath.lastIndexOf("\\") + 1);
      if (this.strFileName.indexOf("/") >= 0)
      {
        this.strFileName = this.strFileName.substring(this.strFileName.lastIndexOf("/") + 1);
      }
    }
    else if (strFilePath.indexOf(File.separator) >= 0)
    {
      this.strFileName = strFilePath.substring(strFilePath.lastIndexOf(File.separator) + 1);
    }
    else
    {
      this.strFileName = strFilePath;
    }

    setStrFileName(this.strFileName);
  }

  public String getStrFilePath()
  {
    return this.strFilePath;
  }

  public String getStrFileName()
  {
    return this.strFileName;
  }

  public void setStrFileName(String strFileName)
    throws UnsupportedEncodingException
  {
    this.strFileName = URLEncoder.encode(strFileName, "utf-8");
  }
}