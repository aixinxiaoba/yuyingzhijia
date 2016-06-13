package javacommon.util.mail;

import javax.mail.*;

/**
 * 自己实现的邮件认证类 。
 * 
 * @author 
 *
 */
public class MyAuthenticator extends Authenticator
{
	String strUserName = null;
	String strPassword = null;

	/**
	 * 设置用户。
	 * @param username
	 * @param password
	 */
	public MyAuthenticator(String strUserName, String strPassword)
	{
		this.strUserName = strUserName;
		this.strPassword = strPassword;
	}

	/**
	 * 重写的 。
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(strUserName, strPassword);
	}
}
