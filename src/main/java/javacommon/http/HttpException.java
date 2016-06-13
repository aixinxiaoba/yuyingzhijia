package javacommon.http;

/**
 * HttpException: 处理HTTP请求中出现的异常  2: getStatusCode()能拿到错误异常代码。
 * 
 * @author wangzg
 * 
 */
public class HttpException extends Exception
{
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = -2623309261327598087L;

	/**
	 * 返回的状态码。
	 */
	private int nStatusCode = -1;

	/**
	 * 构造函数。
	 * 
	 * @param strMsg
	 */
	public HttpException(String strMsg)
	{
		super(strMsg);
	}

	/**
	 * 构造函数。
	 * 
	 * @param objCause
	 */
	public HttpException(Exception objCause)
	{
		super(objCause);
	}

	/**
	 * 构造函数。
	 * 
	 * @param strMsg
	 * @param nStatusCode
	 */
	public HttpException(String strMsg, int nStatusCode)
	{
		super(strMsg);
		this.nStatusCode = nStatusCode;
	}

	/**
	 * 构造函数。
	 * 
	 * @param strMsg
	 * @param objCause
	 */
	public HttpException(String strMsg, Exception objCause)
	{
		super(strMsg, objCause);
	}

	/**
	 * 构造函数。
	 * 
	 * @param strMsg
	 * @param objCause
	 * @param nStatusCode
	 */
	public HttpException(String strMsg, Exception objCause, int nStatusCode)
	{
		super(strMsg, objCause);
		this.nStatusCode = nStatusCode;

	}

	/**
	 * 拿到错误异常代码。
	 * 
	 * @return
	 */
	public int getStatusCode()
	{
		return this.nStatusCode;
	}

}
