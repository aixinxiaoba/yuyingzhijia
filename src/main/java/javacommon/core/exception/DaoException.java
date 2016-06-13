package javacommon.core.exception;

/**
 * dao层可实例化异常定义 。
 * 
 * @author wangzg
 * 
 */
public class DaoException extends AbstractException
{

	private static final long serialVersionUID = -4682038128672417881L;

	/**
	 * exception。
	 */
	public DaoException()
	{
		super();
	}

	/**
	 * exception。
	 */
	public DaoException(int nErrorCode)
	{
		super(nErrorCode);
	}

	/**
	 * exception。
	 */
	public DaoException(String strMessage, Throwable objCause)
	{
		super(strMessage, objCause);
	}

	/**
	 * exception。
	 */
	public DaoException(String strErrorMessage)
	{
		super(strErrorMessage);
	}

}
