package javacommon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类。
 * 
 * @author wangzg
 */
public final class UtilValidate
{
	/**
	 * URL的正则。
	 */
	public static final String URL_REGEXP = "(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";

	public static final String HTTP_URL_REGEXP = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";

	private static final String strEmailAddressPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";

	private static final String strRegex_URI = "^" + "((http(s)?|ftp)://)?" + // 协议名
												"((\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*" + // 主机（域名或IP）
												"(:\\d{1,5})?)?" + // 端口
												"(/)?([\\.\\w-]*/)*" + // 路径（支持相对路径）
												"[\\w-]*(\\.?:?(\\w)*)" + // 文件名
												"(\\?)?" + // 查询字符
												"((&|(&&))*[^\\s]*=[^\\s]*)*" + // 查询
												"(#[\\w-]*)?" + // 片段
												"$";

	/**
	 * 日期正则。
	 */
	private static final String strRegex_date = "^((\\d{2}(([02468][048])|([13579][26]))[\\-]?((((0?[13578])|(1[02]))[\\-]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]?((((0?[13578])|(1[02]))[\\-]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

	/**
	 * 空构造。
	 */
	private UtilValidate()
	{
	}

	/**
	 * 判断是否合法的Http Url。
	 * 
	 * @param strUrl
	 * @return
	 */
	public static boolean isHttpUrl(String strUrl)
	{
		Pattern objPattern = Pattern.compile(strRegex_URI, Pattern.CASE_INSENSITIVE);
		Matcher objMatcher = objPattern.matcher(strUrl);
		
		return objMatcher.matches();
	}

	/**
	 * 判断是否合法的email。
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isEmail(String strEmail)
	{
		Pattern objPattern = Pattern.compile(strEmailAddressPattern, Pattern.CASE_INSENSITIVE);
		Matcher objMatcher = objPattern.matcher(strEmail);
		
		return objMatcher.matches();
	}

	/**
	 * 判断是否合法的日期 。
	 * 
	 * @param strDate
	 * @return
	 */
	public static boolean checkDateFormate(String strDate)
	{
		Pattern objPattern = Pattern.compile(strRegex_date, Pattern.CASE_INSENSITIVE);
		Matcher objMatcher = objPattern.matcher(strDate);
		
		return objMatcher.matches();
	}

}
