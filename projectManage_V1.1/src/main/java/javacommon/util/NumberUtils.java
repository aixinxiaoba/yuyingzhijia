package javacommon.util;

import java.math.BigDecimal;

/**
 * 数字工具类：---用于处理金额 。
 * 
 * @author wangzg
 * 
 */
public final class NumberUtils
{
	/**
	 * 空构造。
	 */
	private NumberUtils()
	{
		super();
	}

	/**
	 * 元转分 如果还有比分更小单位，则4舍5入到分。
	 * 
	 * @param objYuanAmt
	 * @return
	 */
	public static int yuanToFen(Float objYuanAmt)
	{
		String strFenAmt = multiplication(objYuanAmt.toString(), "100");
		
		return Math.round(new Float(strFenAmt));
	}

	/**
	 * 将元转换为分。
	 * @param strYuanAmt
	 * @return
	 */
	public static long yuanToFen(String strYuanAmt)
	{
		String strFenAmt = multiplication(strYuanAmt, "100");
		
		return Math.round(new Float(strFenAmt));
	}

	/**
	 * 分转元。
	 * 
	 * @param objFenAmt
	 * @return
	 */
	public static Float fenToYuan(Long objFenAmt)
	{
		return divide(objFenAmt.toString(), 100);
	}

	/**
	 * 分转元。
	 * 
	 * @param nFenAmt
	 * @return
	 */
	public static Double fenToYuan(int nFenAmt)
	{
		return divideToDouble(nFenAmt + "", 100);
	}

	/**
	 * 分转元。
	 * 
	 * @param strFenAmt
	 * @return
	 */
	public static Double fenToYuan(String strFenAmt)
	{
		return divideToDouble(strFenAmt, 100);
	}

	/**
	 * 金额相加。
	 * 
	 * @param objOldAmt
	 * @param objFillAmt
	 * @return Float
	 */
	public static Float addToFloat(Float objOldAmt, Float objFillAmt)
	{
		BigDecimal objBdAmt = new BigDecimal(objOldAmt == null ? "0" : objOldAmt.toString());
		BigDecimal objBdcnt = new BigDecimal(objFillAmt == null ? "0" : objFillAmt.toString());
		BigDecimal objTotal = objBdAmt.add(objBdcnt);
		
		return objTotal.floatValue();
	}

	/**
	 * 金额相加 。
	 * 
	 * @param strOldAmt
	 * @param strFillAmt
	 * @return Double
	 */
	public static Double addToDouble(String strOldAmt, String strFillAmt)
	{
		BigDecimal objBdAmt = new BigDecimal(strOldAmt);
		BigDecimal objBdcnt = new BigDecimal(strFillAmt);
		BigDecimal objTotal = objBdAmt.add(objBdcnt);
		
		return objTotal.doubleValue();
	}

	/**
	 * 金额相加。
	 * 
	 * @param objOldAmt
	 * @param objFillAmt
	 * @return Double
	 */
	public static Double addToDouble(Double objOldAmt, Float objFillAmt)
	{
		BigDecimal objBdAmt = new BigDecimal(objOldAmt);
		BigDecimal objBdcnt = new BigDecimal(objFillAmt);
		BigDecimal objTotal = objBdAmt.add(objBdcnt);
		
		return objTotal.doubleValue();
	}

	/**
	 * 相减。
	 * 
	 * @param objSubtractive 被减数
	 * @param objExtraction 减数
	 * @return
	 */
	public static Float subtract(Float objSubtractive, Float objExtraction)
	{
		BigDecimal objBdAmt = new BigDecimal(objSubtractive == null ? "0" : objSubtractive.toString());
		BigDecimal objBdAmt2 = new BigDecimal(objExtraction == null ? "0" : objExtraction.toString());
		BigDecimal objSurplus = objBdAmt.subtract(objBdAmt2);
		
		return objSurplus.floatValue();
	}

	/**
	 * 相减。
	 * 
	 * @param objSubtractive 被减数
	 * @param objExtraction 减数
	 * @return
	 */
	public static Double subtract(Double objSubtractive, Double objExtraction)
	{
		BigDecimal objBdAmt = new BigDecimal(objSubtractive == null ? "0" : objSubtractive.toString());
		BigDecimal objBdAmt2 = new BigDecimal(objExtraction == null ? "0" : objExtraction.toString());
		BigDecimal objSurplus = objBdAmt.subtract(objBdAmt2);
		
		return objSurplus.doubleValue();
	}

	/**
	 * 相减。
	 * 
	 * @param strSubtractive 被减数
	 * @param strExtraction 减数
	 * @return
	 */
	public static String subtract(String strSubtractive, String strExtraction)
	{
		BigDecimal objBdAmt = new BigDecimal(strSubtractive == null ? "0" : strSubtractive.toString());
		BigDecimal objBdAmt2 = new BigDecimal(strExtraction == null ? "0" : strExtraction.toString());
		BigDecimal objSurplus = objBdAmt.subtract(objBdAmt2);
		
		return objSurplus.toString();
	}

	/**
	 * 相除 。
	 * 
	 * @param strOldAmt 被除数，原有金额
	 * @param nDivisor 除数
	 * @return
	 */
	public static Float divide(String strOldAmt, int nDivisor)
	{
		BigDecimal objBdAmt = new BigDecimal(strOldAmt);
		BigDecimal objBdAmt2 = new BigDecimal(String.valueOf(nDivisor));
		BigDecimal objSurplus = objBdAmt.divide(objBdAmt2);
		
		return objSurplus.floatValue();
	}

	/**
	 * 相除。
	 * 
	 * @param strOldAmt 被除数，原有金额
	 * @param nDivisor 除数
	 * @return
	 */
	public static Double divideToDouble(String strOldAmt, int nDivisor)
	{
		BigDecimal objBdAmt = new BigDecimal(strOldAmt);
		BigDecimal objBdAmt2 = new BigDecimal(String.valueOf(nDivisor));
		BigDecimal objSurplus = objBdAmt.divide(objBdAmt2);
		
		return objSurplus.doubleValue();
	}

	/**
	 * 相乘。
	 * 
	 * @param strMulted
	 * @param strFillAmt
	 * @return
	 */
	public static String multiplication(String strMulted, String strFillAmt)
	{
		BigDecimal objBdAmt = new BigDecimal(strFillAmt);
		BigDecimal objBdcnt = new BigDecimal(strMulted);
		BigDecimal objTotal = objBdAmt.multiply(objBdcnt);
		
		return objTotal.toString();
	}

	/**
	 * 相乘。
	 * 
	 * @param strMulted
	 * @param strFillAmt
	 * @return 小数点后为0时返回整数字符串 不为0则返回小数字符串
	 */
	public static String multiplication2(String strMulted, String strFillAmt)
	{
		String strResult = multiplication(strMulted, strFillAmt);
		String[] arrayArr = strResult.split("\\.");
		
		if (arrayArr.length > 1)
		{
			int nA = new Integer(arrayArr[1]);
			
			if (nA > 0)
			{
				return strResult;
			}
			else
			{
				return arrayArr[0];
			}
		}
		return strResult;
	}

	/**
	 * 金额不足一分则返回一分 金额单位为元。
	 * 
	 * @param objAmt
	 * @return
	 */
	public static Float upToOneFen(Float objAmt)
	{
		if (objAmt.floatValue() > 0 && objAmt.floatValue() < 0.01)
		{
			return 0.01F;
		}
		else
		{
			return objAmt;
		}
	}

	/**
	 * 格式化为两位小数点。
	 * 
	 * @param strYuanAmt
	 * @return
	 */
	public static String rounding(String strYuanAmt)
	{
		BigDecimal objBdAmt = new BigDecimal(strYuanAmt);
		BigDecimal objBdAmt2 = new BigDecimal("1");
		BigDecimal objSurplus = objBdAmt.divide(objBdAmt2, 2, BigDecimal.ROUND_HALF_UP);
		
		return objSurplus.toString();
	}

}
