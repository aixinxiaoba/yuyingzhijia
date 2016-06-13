package javacommon.util.beanUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

/**
 * 注册BeanUtils Converter工具类。
 * 
 * @author wangzg
 * 
 */
public class ConvertRegisterUtils
{
	/**
	 * 空构造。
	 */
	private ConvertRegisterUtils()
	{
	}

	/**
	 * 注册BeanUtils的转换器。
	 * 
	 * @param convertUtils:---BeanUtilsBean的默认转换器 。
	 */
	public static void registerConverters(ConvertUtilsBean objConvertUtils)
	{
		registerConverters(objConvertUtils, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS", "HH:mm:ss" });
	}

	/**
	 * 注册BeanUtils的转换器。
	 * 
	 * @param objConvertUtils
	 * @param datePatterns
	 */
	private static void registerConverters(ConvertUtilsBean objConvertUtils, String[] arrayDatePatterns)
	{
		// date
		// ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		// ConvertUtils.register(new SqlDateConverter(null),
		// java.sql.Date.class);
		// ConvertUtils.register(new SqlTimeConverter(null), Time.class);
		// ConvertUtils.register(new SqlTimestampConverter(null),
		// Timestamp.class);

		objConvertUtils.register(ConvertRegisterUtils.setPatterns(new DateConverter(null), arrayDatePatterns), java.util.Date.class);

		objConvertUtils.register(ConvertRegisterUtils.setPatterns(new SqlDateConverter(null), arrayDatePatterns), java.sql.Date.class);

		objConvertUtils.register(ConvertRegisterUtils.setPatterns(new SqlTimeConverter(null), arrayDatePatterns), Time.class);

		objConvertUtils.register(ConvertRegisterUtils.setPatterns(new SqlTimestampConverter(null), arrayDatePatterns), Timestamp.class);

		// number
		objConvertUtils.register(new BooleanConverter(null), Boolean.class);
		objConvertUtils.register(new ShortConverter(null), Short.class);
		objConvertUtils.register(new IntegerConverter(null), Integer.class);
		objConvertUtils.register(new LongConverter(null), Long.class);
		objConvertUtils.register(new FloatConverter(null), Float.class);
		objConvertUtils.register(new DoubleConverter(null), Double.class);
		objConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		objConvertUtils.register(new BigIntegerConverter(null), BigInteger.class);
	}

	/**
	 * 设置日期的格式。
	 * 
	 * @param <T>
	 * @param converter
	 * @param patterns
	 * @return
	 */
	public static <T extends DateTimeConverter> T setPatterns(T objConverter, String[] arrayPatterns)
	{
		objConverter.setPatterns(arrayPatterns);

		return objConverter;
	}

}
