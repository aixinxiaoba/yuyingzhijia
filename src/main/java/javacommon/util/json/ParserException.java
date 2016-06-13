package javacommon.util.json;

/**
 * json转换异常。
 * 
 * @author wangzg
 * 
 */
public class ParserException extends RuntimeException
{
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = -238091758285157331L;

	/**
	 * 错误码。
	 */
	private String strErrCode;

	/**
	 * 错误信息。
	 */
	private String strErrMsg;

	/**
	 * 空构造。
	 */
	public ParserException()
	{
		super();
	}

	/**
	 * 构造函数 。
	 * 
	 * @param strMessage
	 * @param objCause
	 */
	public ParserException(String strMessage, Throwable objCause)
	{
		super(strMessage, objCause);
	}

	/**
	 * 构造函数 。
	 * 
	 * @param strMessage
	 */
	public ParserException(String strMessage)
	{
		super(strMessage);
	}

	/**
	 * ParserException。
	 * @param objCause
	 */
	public ParserException(Throwable objCause)
	{
		super(objCause);
	}

	/**
	 * 构造函数 。
	 * 
	 * @param strErrCode
	 * @param strErrMsg
	 */
	public ParserException(String strErrCode, String strErrMsg)
	{
		super(strErrCode + ":" + strErrMsg);
		this.strErrCode = strErrCode;
		this.strErrMsg = strErrMsg;
	}

	/**
	 * 拿到错误码 。
	 * 
	 * @return
	 */
	public String getStrErrCode()
	{
		return this.strErrCode;
	}

	/**
	 * 拿到错误信息 。
	 * 
	 * @return
	 */
	public String getStrErrMsg()
	{
		return this.strErrMsg;
	}

}
