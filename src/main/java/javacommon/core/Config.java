package javacommon.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 定义系统全局共用配置信息:整合了Config和Constants这两个类， 目前定义成全配置的方式，从配置文件中读取数据供系统调用。
 * 
 * <pre>
 * |
 * </pre>
 * 
 * <br>
 * JDK Version：1.6
 * 
 * @author wangzg
 * @version 2.0
 * @see
 * @since 1.0
 */
public class Config
{

	/** 系统应用根目录 */
	public static String SYS_ROOT_PATH = "";

	/** 获取系统的访问权限 */
	public static Properties ACCESSAUTH = null;

	/** 保存用户色session信息，由于监听用户是否登陆 */
	@SuppressWarnings("unchecked")
	public static Map mapSessionMap = new HashMap();

	/** 获取sql拼接查询条件时要过滤的危险字符 */
	public static String[] SQL_CHECK_ARRAY = null;

	/** 获取权限相关信息 */
	public static Properties PRIVILEDGE_URL = null;

	/** 通用配置 **/
	public static Properties objCOMConfig = null;

	/** 职协项目配置 **/
	public static Properties objZXConfig = null;
	
	/** SAAS项目配置 **/
	public static Properties objSAASConfig = null;

}
