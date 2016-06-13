package javacommon.util.mail;

import java.util.Properties;
import javacommon.core.Config;

public class MailConfig
{
  private String strMailServerHost;
  private String strMailServerPort = "25";
  private String strFromAddress;
  private String strNickName;
  private String strToAddress;
  private String strUserName;
  private String strPassword;
  private boolean bMyValidate = false;
  private String strSubject;
  private String strContent;
  private String[] arrayAttachFileNames;

  public MailConfig(String strFromAddress, String strToAddress, boolean bValidate, String strSubject, String strContent)
  {
    this.strFromAddress = strFromAddress;
    this.strToAddress = strToAddress;
    this.bMyValidate = bValidate;
    this.strSubject = strSubject;
    this.strContent = strContent;

    this.strMailServerHost = Config.objCOMConfig.getProperty("fromEmailSmtp");
    this.strMailServerPort = Config.objCOMConfig.getProperty("mail.smtp.port");
    this.strUserName = Config.objCOMConfig.getProperty("fromEmail");
    this.strPassword = Config.objCOMConfig.getProperty("fromEmailPassword");
  }

  public MailConfig(String strNickName, String strFromAddress, String strToAddress, boolean bValidate, String strSubject, String strContent)
  {
    this.strFromAddress = strFromAddress;
    this.strToAddress = strToAddress;
    this.bMyValidate = bValidate;
    this.strSubject = strSubject;
    this.strContent = strContent;
    this.strNickName = strNickName;

    this.strMailServerHost = Config.objCOMConfig.getProperty("fromEmailSmtp");
    this.strMailServerPort = Config.objCOMConfig.getProperty("mail.smtp.port");
    this.strUserName = Config.objCOMConfig.getProperty("fromEmail");
    this.strPassword = Config.objCOMConfig.getProperty("fromEmailPassword");
  }

  public MailConfig(String strNickName, String strFromAddress, String strToAddress, String strFromEmailPassword, boolean bValidate, String strSubject, String strContent)
  {
    this.strFromAddress = strFromAddress;
    this.strToAddress = strToAddress;
    this.bMyValidate = bValidate;
    this.strSubject = strSubject;
    this.strContent = strContent;
    this.strNickName = strNickName;

    this.strMailServerHost = Config.objCOMConfig.getProperty("fromEmailSmtp");
    this.strMailServerPort = Config.objCOMConfig.getProperty("mail.smtp.port");
    this.strUserName = strToAddress;
    this.strPassword = strFromEmailPassword;
  }

  public Properties getProperties()
  {
    Properties objP = new Properties();

    objP.put("mail.smtp.host", this.strMailServerHost);
    objP.put("mail.smtp.port", this.strMailServerPort);
    objP.put("mail.smtp.auth", this.bMyValidate ? "true" : "false");
    return objP;
  }

  public String getStrMailServerHost()
  {
    return this.strMailServerHost;
  }

  public void setStrMailServerHost(String strMailServerHost)
  {
    this.strMailServerHost = strMailServerHost;
  }

  public String getStrMailServerPort()
  {
    return this.strMailServerPort;
  }

  public void setStrMailServerPort(String strMailServerPort)
  {
    this.strMailServerPort = strMailServerPort;
  }

  public boolean isbValidate()
  {
    return this.bMyValidate;
  }

  public void setbValidate(boolean bValidate)
  {
    this.bMyValidate = bValidate;
  }

  public String[] getArrayAttachFileNames()
  {
    return this.arrayAttachFileNames;
  }

  public void setArrayAttachFileNames(String[] arrayAttachFileNames)
  {
    this.arrayAttachFileNames = arrayAttachFileNames;
  }

  public String getStrFromAddress()
  {
    return this.strFromAddress;
  }

  public void setStrFromAddress(String strFromAddress)
  {
    this.strFromAddress = strFromAddress;
  }

  public String getStrPassword()
  {
    return this.strPassword;
  }

  public void setStrPassword(String strPassword)
  {
    this.strPassword = strPassword;
  }

  public String getStrToAddress()
  {
    return this.strToAddress;
  }

  public void setStrToAddress(String strToAddress)
  {
    this.strToAddress = strToAddress;
  }

  public String getStrUserName()
  {
    return this.strUserName;
  }

  public void setStrUserName(String strUserName)
  {
    this.strUserName = strUserName;
  }

  public String getStrSubject()
  {
    return this.strSubject;
  }

  public void setStrSubject(String strSubject)
  {
    this.strSubject = strSubject;
  }

  public String getStrContent()
  {
    return this.strContent;
  }

  public void setStrContent(String strContent)
  {
    this.strContent = strContent;
  }

  public String getStrNickName()
  {
    return this.strNickName;
  }
}