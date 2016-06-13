package javacommon.util.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javacommon.core.Config;
import javacommon.util.properties.PropsUtil;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件工具类 。
 * 
 * @author
 * 
 */
public class MailClient
{
    /**
     * 日志对象。
     */
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    /**
     * 以文本格式发送邮件。
     * 
     * @param objMailInfo 待发送的邮件的信息
     */
    public boolean sendTextMail(MailConfig objMailInfo)
    {
        return sendMail(objMailInfo, false, false);
    }
    
    /**
     * 以文本格式发送邮件带昵称。
     * 
     * @param objMailInfo 待发送的邮件的信息
     */
    public boolean sendTextMailWithNick(MailConfig objMailInfo)
    {
        return sendMail(objMailInfo, false, true);
    }

    /**
     * 以HTML格式发送邮件。
     * 
     * @param objMailInfo 待发送的邮件信息
     * 
     */
    public static boolean sendHtmlMail(MailConfig objMailInfo)
    {
        return sendMail(objMailInfo, true, false);
    }
    
    /**
     * 以HTML格式发送邮件带昵称。
     * 
     * @param objMailInfo 待发送的邮件信息
     * 
     */
    public static boolean sendHtmlMailWithNick(MailConfig objMailInfo)
    {
        return sendMail(objMailInfo, true, true);
    }

    /**
     * 发送邮件 。
     * 
     * @param objMailInfo
     * @param bIsHtml
     * @return
     */
    private static boolean sendMail(MailConfig objMailInfo, boolean bIsHtml, boolean bIsSetNickName)
    {
        // 定义参数
        MyAuthenticator objAuthenticator = null;
        Session objSendMailSession;
        Properties objPro = objMailInfo.getProperties();

        // 判断是否需要身份认证
        if (objMailInfo.isbValidate())
        {
            // 如果需要身份认证，则创建一个密码验证器
            objAuthenticator = new MyAuthenticator(objMailInfo.getStrUserName(), objMailInfo.getStrPassword());
        }

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        objSendMailSession = Session.getDefaultInstance(objPro, objAuthenticator);
        try
        {
            // 根据session创建一个邮件消息
            Message objMailMessage = new MimeMessage(objSendMailSession);
            Address objTo;
            // 创建邮件发送者地址
            Address objFrom = new InternetAddress(objMailInfo.getStrFromAddress());
            String strNick = "admin"; // 发送者昵称
            
            if (bIsSetNickName)
            {
                try
                {
                    strNick = javax.mail.internet.MimeUtility.encodeText(objMailInfo.getStrNickName());
                }
                catch (UnsupportedEncodingException e)
                {
                    logger.error("设置邮件昵称失败！", e);
                    return false;
                }
                
                // 设置邮件消息的发送者 + 昵称
                objMailMessage.setFrom(new InternetAddress(strNick + "<" + objFrom + ">"));
            }
            else
            {
                // 设置邮件消息的发送者
                objMailMessage.setFrom(objFrom);
            }


            // 创建邮件的接收者地址，并设置到邮件消息中
            objTo = new InternetAddress(objMailInfo.getStrToAddress());
            objMailMessage.setRecipient(Message.RecipientType.TO, objTo);

            // 设置邮件消息的主题
            objMailMessage.setSubject(objMailInfo.getStrSubject());

            // 设置邮件消息发送的时间
            objMailMessage.setSentDate(new Date());

            // 设置邮件消息的主要内容
            if (bIsHtml)
            {
                objMailMessage.setContent(objMailInfo.getStrContent(), "text/html; charset=utf-8");
            }
            else
            {
                objMailMessage.setText(objMailInfo.getStrContent());
            }

            // 发送邮件
            Transport.send(objMailMessage);

            // 返回结果
            return true;
        }
        catch (MessagingException ex)
        {
            logger.info("设置邮件出错", ex);
        }

        // 返回结果
        return false;
    }

    public static void main(String[] args)
    {
        Config.objCOMConfig = PropsUtil.getURlConf("E:\\MyWork\\item\\2013\\baseStudy\\mycrm\\WebRoot\\WEB-INF\\upload\\config\\COMConfig.properties");
        MailClient.sendHtmlMail(new MailConfig("896177267@qq.com", "1036349126@qq.com", true, "这是主题", "这是内容<a href='http://www.baidu.com'>百度一下</a>"));

        // 设置自定义发件人昵称
    }
}