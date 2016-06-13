package javacommon.core.exception;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Action层异常定义 。
 * 
 * @author wangzg
 * 
 */

public class ActionException extends AbstractException
{

	private static final long serialVersionUID = -4682038128672417881L;

	/**
	 * exception。
	 */
	public ActionException()
	{
		super();
	}

	/**
	 * exception。
	 */
	public ActionException(int nErrorCode)
	{
		super(nErrorCode);
	}

	/**
	 * exception。
	 */
	public ActionException(String strMessage, Throwable objCause)
	{
		super(strMessage, objCause);
	}

	/**
	 * exception。
	 */
	public ActionException(String strErrorMessage)
	{
		super(strErrorMessage);
	}

	/**
	 * exception。
	 */
	public ActionException(int[] arrayErrCodeArr)
	{
		super(arrayErrCodeArr);
	}

}
