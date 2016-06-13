package javacommon.core.exception;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 通用异常抽象类: 1用于集中处理系统异常/异常处理模块的核心 2 系统中抛出异常，如: throw new。
 * ServiceException(strErrorCode) 3:---在Action层拿到异常，然后调用
 * setErrorText(e.getErrorCode() + "")设置异常信息
 * 
 * @author wangzg
 * 
 */
public abstract class AbstractException extends RuntimeException
{
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = 2148374270769534530L;

	/**
	 * 错误信息的国际化文件位置。
	 */
	private static final String ERROR_BUNDLE = "i18n/messages";

	/**
	 * 错误代码,默认为未知错误。
	 */
	protected int nErrorCode = 10001;

	/**
	 * 多条错误。
	 */
	protected int[] arrayErrCodeArr;

	/**
	 * 错误信息中的参数。
	 */
	protected String[] arrayErrorArgs = null;

	/**
	 * 兼容纯错误信息，不含error code,errorArgs的情况。
	 */
	protected String strErrorMessage = null;

	/**
	 * 错误信息的i18n ResourceBundle。
	 */
	final static protected ResourceBundle objRb = ResourceBundle.getBundle(ERROR_BUNDLE, LocaleContextHolder.getLocale());

	/**
	 * Constructor。
	 */
	public AbstractException()
	{
		super();
	}

	/**
	 * Constructor。
	 */
	public AbstractException(String strMessage, Throwable objCause)
	{
		super(strMessage, objCause);
	}

	/**
	 * Constructor。
	 */
	public AbstractException(int nErrorCode)
	{
		this.nErrorCode = nErrorCode;
	}

	/**
	 * Constructor。
	 */
	public AbstractException(int[] arrayErrCodeArr)
	{
		this.nErrorCode = arrayErrCodeArr[0];
		this.arrayErrCodeArr = arrayErrCodeArr;
	}

	/**
	 * Constructor。
	 */
	public AbstractException(String strErrorMessage)
	{
		super(strErrorMessage);
		this.strErrorMessage = strErrorMessage;
	}

	/**
	 * 获得出错信息:---是公共对外调用的方法/有国际化处理。
	 */
	@Override
	public String getMessage()
	{
		String strMessage;// 否则用errorCode查询Properties文件获得出错信息

		// 如果errorMessage不为空,直接返回出错信息.
		if (strErrorMessage != null)
		{
			return strErrorMessage;
		}

		try
		{
			strMessage = "ErrorCode:" + nErrorCode + "," + objRb.getString(String.valueOf(nErrorCode));
		}
		catch (MissingResourceException mse)
		{
			strMessage = "ErrorCode is: " + nErrorCode + ", but can't get the message of the Error Code";
		}

		// 如果有更多的异常信息。
		if (isMoreError())
		{
			final StringBuilder objSb = new StringBuilder();

			for (int i = 1; i < arrayErrCodeArr.length; i++)
			{
				String strT;// 第一条刚才加过了

				try
				{
					strT = objRb.getString(String.valueOf(arrayErrCodeArr[i]));
				}
				catch (MissingResourceException mse)
				{
					strT = "ErrorCode is: " + arrayErrCodeArr[i] + ", but can't get the message of the Error Code";
				}

				objSb.append("ErrorCode:" + arrayErrCodeArr[i] + "" + strT + " ");
			}

			// 所有的错误信息。
			strMessage = strMessage + " " + objSb.toString();
		}

		// 将出错信息中的参数代入到出错信息中。
		if (arrayErrorArgs != null)
		{
			strMessage = MessageFormat.format(strMessage, (Object[]) arrayErrorArgs);
		}

		// 返回结果
		return strMessage;
	}

	/**
	 * 获取错误代码串 。
	 * 
	 * @return
	 */
	public String getErrCodeStr()
	{
		String strErrCode = nErrorCode + "";

		if (isMoreError())
		{
			StringBuilder objBf = new StringBuilder();

			for (int i = 1; i < arrayErrCodeArr.length; i++)
			{
				objBf.append("," + arrayErrCodeArr[i]);// 第一条记录去除
			}

			strErrCode = strErrCode + objBf.toString();
		}

		// 返回结果
		return strErrCode;
	}

	/**
	 * 检查是否多天错误信息。
	 * 
	 * @return
	 */
	private boolean isMoreError()
	{
		return this.arrayErrCodeArr != null && this.arrayErrCodeArr.length > 1;
	}

	/**
	 * get。
	 */
	public int getErrorCode()
	{
		return nErrorCode;
	}

	/**
	 * get。
	 */
	public int[] getErrCodeArr()
	{
		if (arrayErrCodeArr != null)
		{
			return arrayErrCodeArr;
		}
		return new int[] { nErrorCode };
	}

	/**
	 * get。
	 */
	public String[] getErrorArgs()
	{
		return arrayErrorArgs;
	}

	/**
	 * set。
	 */
	public void setErrorArgs(String[] arrayErrorArgs)
	{
		this.arrayErrorArgs = arrayErrorArgs;
	}

	/**
	 * get。
	 */
	public String getErrorMessage()
	{
		return strErrorMessage;
	}

	/**
	 * set。
	 */
	public void setErrorMessage(String strErrorMessage)
	{
		this.strErrorMessage = strErrorMessage;
	}

	/**
	 * set。
	 */
	public void setErrorCode(int nErrorCode)
	{
		this.nErrorCode = nErrorCode;
	}

	/**
	 * set。
	 */
	public void setErrCodeArr(int[] arrayErrCodeArr)
	{
		this.arrayErrCodeArr = arrayErrCodeArr;
	}

}
