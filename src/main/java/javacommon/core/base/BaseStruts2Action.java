package javacommon.core.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javacommon.core.Config;
import javacommon.util.beanUtils.ConvertRegisterUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.org.rapid_framework.beanutils.BeanUtils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 通用Action抽象类 。
 * 
 * @author wangzg
 * 
 */
public abstract class BaseStruts2Action extends ActionSupport implements RequestAware
{
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = 190553809254615841L;

	/**
	 * 日志对象。
	 */
	private static final Logger logger = LoggerFactory.getLogger(BaseStruts2Action.class);

	/**
	 * 公共异常的返回结果。
	 */
	protected static final String COMMON_ERROR = "commonError";
	
	/**
	 * 需要提示的公共页面。
	 */
	protected static final String COMMON_OK = "commonOk";
	
	/**
	 * 下载操作。
	 */
	protected static final String COMMON_DOWNLOAD = "commonDownLoad";
	
	/**
	 * 注册失败。
	 */
	protected static final String REGIST_ERROR = "registError";

	/**
	 * 注册成功。
	 */
	protected static final String REGIST_SUCCESS = "registSuccess";

	/**
	 * 用户没登录的情况返回。
	 */
	protected static final String TO_LOGIN = "toLogin";

	/**
	 * step常量。
	 */
	protected static final String STEP = "step";

	/**
	 * 操作相关的常量。
	 */
	protected static final String OPERATE = "operate";
	protected static final String SAVE = "save";
	protected static final String NEXT = "next";
	
	/**
	 * Action中常用操作结束后，返回的值。
	 */
	protected static final String LIST = "list";
	protected static final String TO_LIST = "toList";
	protected static final String EDIT_UI = "editUI";
	protected static final String ADD__UI = "addUI";

	/**
	 * 错误信息在Session属性中的属性名。
	 */
	private static final String ERR_TEXT = "errText";

	/**
	 * 错误提示信息。
	 */
	public String strErrText;

	/**
	 * 错误信息代码。
	 */
	public String strErrCode;
	
	/**
	 * 往页面传递的信息
	 */
	protected String strMsg;
	
	/**
	 * 公共页面上确定按钮点击需要跳到的地址
	 */
	protected String strURL;

	/**
	 * Request对象中的Atrribute集合。
	 */
	@SuppressWarnings("unchecked")
	protected Map mapRequestMap = null;
	
	/**
	 * 格式化日期对象格式:---到秒
	 */
	protected DateFormat objDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	
	/**
	 * 格式化日期对象格式:---到天
	 */
	protected DateFormat objDateFormatByDay = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 格式化日期对象格式:---到分钟
	 */
	protected DateFormat objDateFormatBySecond = new SimpleDateFormat("yyyy-MM-dd HH-mm");

	/**
	 * 静态代码块: 注册BeanUtils Converters。
	 */
	static
	{
		ConvertRegisterUtils.registerConverters(BeanUtilsBean.getInstance().getConvertUtils());
	}

	/**
	 * 复制对象。
	 * 
	 * @param objTarget
	 * @param objSource
	 */
	public void copyProperties(Object objTarget, Object objSource)
	{
		BeanUtils.copyProperties(objTarget, objSource);
	}

	/**
	 * 复制对象：需要返回实体。
	 * 
	 * @param <T>
	 * @param objDestClass
	 * @param objOrig
	 * @return
	 */
	public <T> T copyProperties(Class<T> objDestClass, Object objOrig)
	{
		return BeanUtils.copyProperties(objDestClass, objOrig);
	}

	/**
	 * 拿到HttpServletRequest对象。
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest()
	{
		return ServletActionContext.getRequest();
	}

	/**
	 * 拿到Request中的参数值 。
	 * 
	 * @param strKey
	 * @return
	 */
	public String getParameter(String strKey)
	{
		return getRequest().getParameter(strKey);
	}
	
	/**
	 * 拿到Request中的属性值。
	 * 
	 * @param strKey
	 */
	public Object getAttribute(String strKey)
	{
		return getRequest().getAttribute(strKey);
	}

	/**
	 * 拿到HttpServletResponse对象。
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse()
	{
		return ServletActionContext.getResponse();
	}

	/**
	 * 向request中设置值。
	 * 
	 * @param strKey
	 * @param objValue
	 */
	public void put(String strKey, Object objValue)
	{
		getRequest().setAttribute(strKey, objValue);
	}

	/**
	 * 将Request中的参数转换为一级结构的map: 如果某个参数传递过来的是数组，则会丢失第一个后面的数据/注意使用。
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, String> convertHttpParamter()
	{
		final Map mapParamMap = getRequest().getParameterMap();
		final Map<String, String> mapWrapperMap = new HashMap<String, String>();
		Set lstKeySet = mapParamMap.keySet();
		Iterator objIt = lstKeySet.iterator();
		
		while (objIt.hasNext())
		{
			Object objObj = objIt.next();
			String strKey = objObj.toString();
			Object objValues = mapParamMap.get(strKey);
			
			if (objValues != null && objValues instanceof String[])
			{
				String[] arraySz = (String[]) objValues;

				// 丢失多个同名的参数值,待修正
				if (arraySz.length > 0)
				{
					mapWrapperMap.put(strKey, arraySz[0]);
				}

				// 如果要修正，则去掉下面的注释代码
				// if(sz.length == 1){
				// wrapperMap.put(key, sz[0]);
				// }
				// else if(sz.length > 1)
				// {
				// wrapperMap.put(key, sz);
				// }
			}
		}

		// 返回结果。
		return mapWrapperMap;
	}

	/**
	 * 把实体对象保存到Session中。
	 * 
	 * @param strKey
	 * @param objEntity
	 */
	protected void setBaseEntityToSession(String strKey, BaseEntity objEntity)
	{
		HttpSession objSession = getRequest().getSession(false);
		
		if (objSession == null)
        {
        	objSession = getRequest().getSession(true);
        }
		objSession.setAttribute(strKey, objEntity);
	}

	/**
	 * 从Session中根据Key拿到实体对象。
	 * 
	 * @param strKey
	 * @return
	 */
	protected BaseEntity getBaseEntityFromSession(String strKey)
	{
		HttpSession objSession = getRequest().getSession(false);
		
		return objSession.getAttribute(strKey) == null ? null : (BaseEntity) objSession.getAttribute(strKey);
	}

	/**
	 * 把实体对象ID保存到Session中。
	 * 
	 * @param strKey
	 * @param entity
	 */
	protected void setBaseEntityIDToSession(String strKey, String strID)
	{
		HttpSession objSession = getRequest().getSession(false);
		
		objSession.setAttribute(strKey, strID);
	}

	/**
	 * 从Session中根据Key拿到实体对象ID。
	 * 
	 * @param strKey
	 * @return
	 */
	protected String getBaseEntityIDFromSession(String strKey)
	{
		HttpSession objSession = getRequest().getSession(false);
		
		return objSession.getAttribute(strKey) == null ? null : (String) objSession.getAttribute(strKey);
	}

	/**
	 * 设置当前登录用户。
	 * 
	 * @return
	 */
	protected void setUserIdToSession(String strUserID)
	{
		HttpSession objSession = getRequest().getSession(false);
		
		if (objSession == null)
		{
			objSession = getRequest().getSession(true);
		}
		objSession.setAttribute(Config.objCOMConfig.getProperty("USER_ID_KEY", "USER_ID"), strUserID);
	}
	
	/**
     * 设置当前登录用户--个人报名。
     * 
     * @return
     */
    protected void setCustomerIdToSession(String strStudentID)
    {
        HttpSession objSession = getRequest().getSession(false);
        
        if (objSession == null)
        {
        	objSession = getRequest().getSession(true);
        }
        objSession.setAttribute(Config.objSAASConfig.getProperty("CUSTOMER_ID_KEY", "CUSTOMER_ID"), strStudentID);
    }
    
    /**
     * 设置当前登录用户--集体报名。
     * 
     * @return
     */
    protected void setOrgIdToSession(String strOrgID)
    {
        HttpSession objSession = getRequest().getSession(false);
        
        objSession.setAttribute(Config.objSAASConfig.getProperty("ORGLOGIN_ID_KEY", "ORG_ID"), strOrgID);
    }
	
	/**
	 * 获取当前登录用户。
	 * 
	 * @return
	 */
	protected String getUserIdFromSession()
	{
		HttpSession objSession = getRequest().getSession(false);
		
		// 系统重启session验证。
		if (objSession == null)
		{
			return null;
		}
		String strUserId = (String) objSession.getAttribute(Config.objCOMConfig.getProperty("USER_ID_KEY", "USER_ID"));
		
		return StringUtils.defaultString(strUserId, "");
	}

	/**
     * 获取当前登录用户--个人报名。
     * 
     * @return
     */
    protected String getCustomerIdFromSession()
    {
        HttpSession objSession = getRequest().getSession(false);
        
        // 系统重启session验证。
		if (objSession == null)
		{
			return null;
		}
        String strStudentID = (String) objSession.getAttribute(Config.objSAASConfig.getProperty("CUSTOMER_ID_KEY", "CUSTOMER_ID"));
        
        return StringUtils.defaultString(strStudentID, "");
    }
    
    /**
     * 获取当前登录用户--集体报名。
     * 
     * @return
     */
    protected String getOrgIdFromSession()
    {
        HttpSession objSession = getRequest().getSession(false);
        String strOrgID = (String) objSession.getAttribute(Config.objSAASConfig.getProperty("ORGLOGIN_ID_KEY", "ORG_ID"));
        
        return StringUtils.defaultString(strOrgID, "");
    }
    
    /**
     * 移除session中指定的值。
     * 
     * @return
     */
    protected void removeAttributeFromSession(String strKey)
    {
        HttpSession objSession = getRequest().getSession();
        
        if (objSession.getAttribute(strKey) != null)
        {
            logger.info("开始从session中移除：【" + strKey + "】");
            objSession.removeAttribute(strKey);
        }
    }
    
	/**
	 * 保存当前专家登录用户。
	 * 
	 * @return
	 */
	protected void setExpertUserIdToSession(String strExpertuserID)
	{
		HttpSession objSession = getRequest().getSession(false);
		
		objSession.setAttribute(Config.objCOMConfig.getProperty("EXPERT_USER_ID_KEY", "EXPERT_USER_ID"), strExpertuserID);
	}

	/**
	 * 获取当前专家登录用户。
	 * 
	 * @return
	 */
	protected String getExpertUserIdFromSession()
	{
		HttpSession objSession = getRequest().getSession(false);
		String strExpertuserId = (String) objSession.getAttribute(Config.objCOMConfig.getProperty("EXPERT_USER_ID_KEY", "EXPERT_USER_ID"));
		
		return StringUtils.defaultString(strExpertuserId, "");
	}

	/**
	 * 返回list对象的json格式。
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	// protected String getReturnString(List list) {
	// ActionMsg msg = new ActionMsg();
	// msg.setData(list);
	// String json = JSONParser.toJson(msg);
	// return json;
	// }
	
	/**
	 * 用于往前台输出:--- 。
	 *           1:---常用于Ajax和FreeMaker模版
	 *           2:---obj可以是任何对象或String:---如果是文件路径，则用户可以下载此文件
	 * 
	 * @param obj
	 * @throws IOException
	 */
	protected void toWeb(Object objObj) throws IOException
	{
		PrintWriter objPw = getResponse().getWriter();
		
		objPw.print(objObj);
		objPw.flush();
		objPw.close();
	}

	/**
	 * 设置错误描述信息:---先从国际化文件中取得错误信息,如果没有，就是默认errCode本身是错误信息的情况。
	 * 
	 * @param strErrCode
	 */
	protected void setErrorText(String strErrCode)
	{
		if (processErrCode(strErrCode) == null || processErrCode(strErrCode).length() <= 0)
		{
			this.strErrText = strErrCode;
		}
		else
		{
			this.strErrCode = strErrCode;
			this.strErrText = processErrCode(strErrCode);
		}
		logger.info("errCode:---" + strErrCode + " ,strErrText:---" + strErrText);

		// 把错误信息放到Session中，因为web.xml中<error-page>的配置是重定向，所以只能从Session中取值。
		getRequest().getSession().setAttribute(ERR_TEXT, this.strErrText);
	}

	/**
	 * 获取错误描述信息:---i18n/异常处理机制中用到。
	 * 
	 * @param strErrCode
	 * @return
	 */
	protected String getErrorText(String strErrCode)
	{
		return processErrCode(strErrCode);
	}

	/**
	 * 从国际化文件中拿到错误信息:---支持多条错误信息的格式。
	 * 
	 * @param strErrCode
	 * @return
	 */
	private String processErrCode(String strErrCode)
	{
		String[] arrayArr = strErrCode.split(",");
		StringBuilder objBf = new StringBuilder();
		
		for (int i = 0; i < arrayArr.length; i++)
		{
			objBf.append(getText(arrayArr[i]) + ",");
		}

		// 返回参数
		return objBf.substring(0, objBf.length() - 1);
	}

	/**
	 * 拿到Request中的所有属性信息。
	 */
	@SuppressWarnings("unchecked")
	public void setRequest(Map mapRequest)
	{
		this.mapRequestMap = mapRequest;
	}

	/**
	 * 拿到错误信息。
	 * 
	 * @return
	 */
	public String getErrText()
	{
		return strErrText;
	}

	/**
	 * 拿到错误代码。
	 * 
	 * @return
	 */
	public String getErrCode()
	{
		return strErrCode;
	}

	/**
	 * 设置错误代码。
	 * 
	 * @param strErrCode
	 */
	public void setErrCode(String strErrCode)
	{
		this.strErrCode = strErrCode;
	}

	/**
	 * 往页面传递的信息 。
	 * 
	 * @return
	 */
	public String getStrMsg()
	{
		return strMsg;
	}

	/**
	 * 往页面传递的信息 。
	 * 
	 * @param strMsg
	 */
	public void setStrMsg(String strMsg)
	{
		this.strMsg = strMsg;
	}

	/**
	 * 公共页面上确定按钮点击需要跳到的地址 。
	 * 
	 * @return
	 */
	public String getStrURL()
	{
		return strURL;
	}

	/**
	 * 公共页面上确定按钮点击需要跳到的地址 。
	 * 
	 * @param strURL
	 */
	public void setStrURL(String strURL)
	{
		this.strURL = strURL;
	}
	
	
	// 判断是移动端还是pc端访问
	/** Wap网关Via头信息中特有的描述信息 */
	private static String mobileGateWayHeaders[] = new String[] { "ZXWAP",// 中兴提供的wap网关的via信息，例如：Via=ZXWAP
			// GateWayZTE
			// Technologies，
			"chinamobile.com",// 中国移动的诺基亚wap网关，例如：Via=WTP/1.1
			// GDSZ-PB-GW003-WAP07.gd.chinamobile.com (Nokia
			// WAP Gateway 4.1 CD1/ECD13_D/4.1.04)
			"monternet.com",// 移动梦网的网关，例如：Via=WTP/1.1
			// BJBJ-PS-WAP1-GW08.bj1.monternet.com. (Nokia WAP
			// Gateway 4.1 CD1/ECD13_E/4.1.05)
			"infoX",// 华为提供的wap网关，例如：Via=HTTP/1.1 GDGZ-PS-GW011-WAP2 (infoX-WISG
			// Huawei Technologies)，或Via=infoX WAP Gateway V300R001
			// Huawei Technologies
			"XMS 724Solutions HTG",// 国外电信运营商的wap网关，不知道是哪一家
			"wap.lizongbo.com",// 自己测试时模拟的头信息
			"Bytemobile",// 貌似是一个给移动互联网提供解决方案提高网络运行效率的，例如：Via=1.1 Bytemobile OSN
	// WebProxy/5.1
	};
	
	 /** 电脑上的IE或Firefox浏览器等的User-Agent关键词 */
	private static String[] pcHeaders = new String[] { "Windows 98",
			"Windows ME", "Windows 2000", "Windows XP", "Windows NT", "Ubuntu" };
	
	/** 手机浏览器的User-Agent里的关键词 */
	private static String[] mobileUserAgents = new String[] { "Nokia",// 诺基亚，有山寨机也写这个的，总还算是手机，Mozilla/5.0
			// (Nokia5800
			// XpressMusic)UC
			// AppleWebkit(like
			// Gecko)
			// Safari/530
			"SAMSUNG",// 三星手机
			// SAMSUNG-GT-B7722/1.0+SHP/VPP/R5+Dolfin/1.5+Nextreaming+SMM-MMS/1.2.0+profile/MIDP-2.1+configuration/CLDC-1.1
			"MIDP-2",// j2me2.0，Mozilla/5.0 (SymbianOS/9.3; U; Series60/3.2
			// NokiaE75-1 /110.48.125 Profile/MIDP-2.1
			// Configuration/CLDC-1.1 ) AppleWebKit/413 (KHTML like
			// Gecko) Safari/413
			"CLDC1.1",// M600/MIDP2.0/CLDC1.1/Screen-240X320
			"SymbianOS",// 塞班系统的，
			"MAUI",// MTK山寨机默认ua
			"UNTRUSTED/1.0",// 疑似山寨机的ua，基本可以确定还是手机
			"Windows CE",// Windows CE，Mozilla/4.0 (compatible; MSIE 6.0;
			// Windows CE; IEMobile 7.11)
			"iPhone",// iPhone是否也转wap？不管它，先区分出来再说。Mozilla/5.0 (iPhone; U; CPU
			// iPhone OS 4_1 like Mac OS X; zh-cn) AppleWebKit/532.9
			// (KHTML like Gecko) Mobile/8B117
			"iPad",// iPad的ua，Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X;
			// zh-cn) AppleWebKit/531.21.10 (KHTML like Gecko)
			// Version/4.0.4 Mobile/7B367 Safari/531.21.10
			"Android",// Android是否也转wap？Mozilla/5.0 (Linux; U; Android
			// 2.1-update1; zh-cn; XT800 Build/TITA_M2_16.22.7)
			// AppleWebKit/530.17 (KHTML like Gecko) Version/4.0
			// Mobile Safari/530.17
			"BlackBerry",// BlackBerry8310/2.7.0.106-4.5.0.182
			"UCWEB",// ucweb是否只给wap页面？ Nokia5800
			// XpressMusic/UCWEB7.5.0.66/50/999
			"ucweb",// 小写的ucweb貌似是uc的代理服务器Mozilla/6.0 (compatible; MSIE 6.0;)
			// Opera ucweb-squid
			"BREW",// 很奇怪的ua，例如：REW-Applet/0x20068888 (BREW/3.1.5.20; DeviceId:
			// 40105; Lang: zhcn) ucweb-squid
			"J2ME",// 很奇怪的ua，只有J2ME四个字母
			"YULONG",// 宇龙手机，YULONG-CoolpadN68/10.14 IPANEL/2.0 CTC/1.0
			"YuLong",// 还是宇龙
			"COOLPAD",// 宇龙酷派YL-COOLPADS100/08.10.S100 POLARIS/2.9 CTC/1.0
			"TIANYU",// 天语手机TIANYU-KTOUCH/V209/MIDP2.0/CLDC1.1/Screen-240X320
			"TY-",// 天语，TY-F6229/701116_6215_V0230 JUPITOR/2.2 CTC/1.0
			"K-Touch",// 还是天语K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223
			// Release/30.07.2008 Browser/WAP2.0
			"Haier",// 海尔手机，Haier-HG-M217_CMCC/3.0 Release/12.1.2007
			// Browser/WAP2.0
			"DOPOD",// 多普达手机
			"Lenovo",// 联想手机，Lenovo-P650WG/S100 LMP/LML Release/2010.02.22
			// Profile/MIDP2.0 Configuration/CLDC1.1
			"LENOVO",// 联想手机，比如：LENOVO-P780/176A
			"HUAQIN",// 华勤手机
			"AIGO-",// 爱国者居然也出过手机，AIGO-800C/2.04 TMSS-BROWSER/1.0.0 CTC/1.0
			"CTC/1.0",// 含义不明

			"CTC/2.0",// 含义不明

			"CMCC",// 移动定制手机，K-Touch_N2200_CMCC/TBG110022_1223_V0801 MTK/6223

			// Release/30.07.2008 Browser/WAP2.0

			"DAXIAN",// 大显手机DAXIAN X180 UP.Browser/6.2.3.2(GUI) MMP/2.0

			"MOT-",// 摩托罗拉，MOT-MOTOROKRE6/1.0 LinuxOS/2.4.20 Release/8.4.2006
                    // Browser/Opera8.00 Profile/MIDP2.0 Configuration/CLDC1.1
                    // Software/R533_G_11.10.54R
            "SonyEricsson",// 索爱手机，SonyEricssonP990i/R100 Mozilla/4.0
                            // (compatible; MSIE 6.0; Symbian OS; 405) Opera
                            // 8.65 [zh-CN]
            "GIONEE",// 金立手机
            "HTC",// HTC手机
            "ZTE",// 中兴手机，ZTE-A211/P109A2V1.0.0/WAP2.0 Profile
            "HUAWEI",// 华为手机，
            "webOS",// palm手机，Mozilla/5.0 (webOS/1.4.5; U; zh-CN)
                    // AppleWebKit/532.2 (KHTML like Gecko) Version/1.0
                    // Safari/532.2 Pre/1.0
            "GoBrowser",// 3g GoBrowser.User-Agent=Nokia5230/GoBrowser/2.0.290
                        // Safari
            "IEMobile",// Windows CE手机自带浏览器，

            "WAP2.0"// 支持wap 2.0的
    };
	
	/**
	 * 
	 * 根据当前请求的特征，判断该请求是否来自手机终端，主要检测特殊的头信息，以及user-Agent这个header
	 * @param request
	 *            http请求
	 * 
	 * @return 如果命中手机特征规则，则返回对应的特征字符串
	 */
	public boolean isMobileDevice() {
		boolean b = false;
		boolean pcFlag = false;
		boolean mobileFlag = false;
		String via = getRequest().getHeader("Via");
		
		String userAgent = getRequest().getHeader("user-agent");

		logger.info("Via:" + via + "--user-agent:" + userAgent);
		for (int i = 0; via != null && !via.trim().equals("")
		&& i < mobileGateWayHeaders.length; i++) {
			if (via.contains(mobileGateWayHeaders[i])) {
				mobileFlag = true;
				break;
			}
		}

		for (int i = 0; !mobileFlag && userAgent != null
		&& !userAgent.trim().equals("") && i < mobileUserAgents.length; i++) {
			if (userAgent.contains(mobileUserAgents[i])) {
				mobileFlag = true;
				break;
			}
		}

		for (int i = 0; userAgent != null && !userAgent.trim().equals("")
		&& i < pcHeaders.length; i++) {
			if (userAgent.contains(pcHeaders[i])) {
				pcFlag = true;
				break;
			}
		}

		if (mobileFlag == true && pcFlag == false) {
			b = true;
		}
		
		return b;// false pc true shouji
	}
	
	/**
	 * 判断是移动端还是pc端返回错误信息。
	 * 
	 * @return
	 */
	public String commonError()
	{
		if (this.isMobileDevice())
		{
			return "m_common_error";
		}
		else
		{
			return COMMON_ERROR;
		}
	}
}
