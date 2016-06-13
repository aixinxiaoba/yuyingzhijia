package javacommon.core.exception;

/**
 * Service层异常定义 。
 * 
 * @author wangzg
 * 
 */
public class ServiceException extends AbstractException
{

	private static final long serialVersionUID = -4682038128672417881L;

	/**
	 * exception。
	 */
	public ServiceException()
	{
		super();
	}

	/**
	 * exception。
	 */
	public ServiceException(int nErrorCode)
	{
		super(nErrorCode);
	}

	/**
	 * exception。
	 */
	public ServiceException(String strMessage, Throwable objCause)
	{
		super(strMessage, objCause);
	}

	/**
	 * exception。
	 */
	public ServiceException(String strErrorMessage)
	{
		super(strErrorMessage);
	}

	/**
	 * exception。
	 */
	public ServiceException(int[] arrayErrCodeArr)
	{
		super(arrayErrCodeArr);
	}

}
