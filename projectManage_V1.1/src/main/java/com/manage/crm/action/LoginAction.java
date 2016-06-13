package com.manage.crm.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javacommon.core.Config;
import javacommon.core.base.BaseStruts2Action;
import javacommon.util.StringUtils;
import javacommon.util.encrypt.EncryptAndDecryptUtils;

import javax.annotation.Resource;

import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import weibo4j.SUsers;
import weibo4j.http.HttpClient;
import weibo4j.http.Response;
import weibo4j.model.PostParameter;
import weibo4j.model.SUser;
import weibo4j.org.json.JSONObject;

import com.manage.crm.entity.Customer;
import com.manage.crm.entity.Users;
import com.manage.crm.service.CustomerService;
import com.manage.crm.service.UsersService;
import com.opensymphony.xwork2.Action;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseStruts2Action {
	private static final long serialVersioncUID = -4056575674925675338L;
	private static final Logger logger = LoggerFactory
			.getLogger(LoginAction.class);
	private static final String LOGIN_SUCCESS = "loginSuccess";
	private static final String USER_LOGIN = "userLogin";
	private static final String USERS_INDEX = "usersIndex";
	private static final String DESTROY_LOGIN = "destroyLogin";
	private static final String DESTROY_CUSTOMER_LOGIN = "destroyCustomerLogin";

	private static final int FLAG_LOGIN_SUCCESS = 0;

	private static final int FLAG_LOGIN_ERROR = 1;

	@Resource(name = "usersService")
	private UsersService objUsersService;

	/**
	 * 用户Service
	 */
	@Resource(name = "customerService")
	private CustomerService objCustomerService;

	String strVerifycode;
	String strUsername;
	String strPassword;
	private Long lProjectID;
	Users objUsers;

	/**
	 * 普通用户对象
	 */
	Customer objCustomer;

	String strPlatformFlag; // 平台标志。

	String projectKey;

	public String login() {
		return "userLogin";
	}

	/**
	 * 设置平台。
	 * 
	 * @return
	 */
	private String setPlatformFlag(int flag) {
		String strForward;

		if (flag == FLAG_LOGIN_SUCCESS) {
			strForward = LOGIN_SUCCESS;
			if (strPlatformFlag != null && !"".equals(strPlatformFlag)) {
				if ("1".equals(strPlatformFlag)) {
					strForward = "tamplateOneLoginSuccess";
				}
			}
		} else {
			strForward = USER_LOGIN;
			if (strPlatformFlag != null && !"".equals(strPlatformFlag)) {
				if ("1".equals(strPlatformFlag)) {
					strForward = "tamplateOneLogin";
				}
			}
		}

		return strForward;
	}

	public String usersLogin() throws UnsupportedEncodingException {
		if (StringUtils.isBlank(this.strUsername)) {
			addFieldError("username", "用户名不能为空");
			return setPlatformFlag(FLAG_LOGIN_ERROR);
		}

		if (StringUtils.isBlank(this.strPassword)) {
			addFieldError("password", "密码不能为空");
			return setPlatformFlag(FLAG_LOGIN_ERROR);
		}

		if (StringUtils.isBlank(this.strVerifycode)) {
			addFieldError("verifycode", "验证码不能为空");
			return setPlatformFlag(FLAG_LOGIN_ERROR);
		}
		logger.info(getRequest().getRemoteHost() + ",username:---"
				+ this.strUsername + ",password:---" + this.strPassword
				+ "登陆开始" + ",verifycode:---" + this.strVerifycode);

		String strVerifyCodeInSession = (String) getRequest().getSession()
				.getAttribute(
						Config.objCOMConfig.getProperty("validateCode",
								"validateCode"));
		if (!this.strVerifycode.equalsIgnoreCase(strVerifyCodeInSession)) {
			addFieldError("verifycode", "验证码错误");
			return setPlatformFlag(FLAG_LOGIN_ERROR);
		}

		this.objUsers = new Users();
		this.objUsers.setStrLoginName(this.strUsername);
		this.objUsers = ((Users) this.objUsersService.getRecordByProps(
				this.objUsers, "strLoginName"));

		if ((this.objUsers != null)
				&& (this.objUsers.getLId().longValue() > 0L)) {
			if (!StringUtils.isBlank(this.objUsers.getStrPassword())) {
				if ((this.strPassword != null)
						&& (EncryptAndDecryptUtils.md5(this.strPassword.trim())
								.equals(this.objUsers.getStrPassword()))) {
					if ((this.objUsers.getNIsActive() == null)
							|| (this.objUsers.getNIsActive().intValue() == 0)) {
						addFieldError("username", "此用户已禁用");
						return setPlatformFlag(FLAG_LOGIN_ERROR);
					}

					setUserIdToSession(String.valueOf(this.objUsers.getLId()));
					setBaseEntityToSession("objUsers", this.objUsers);

					// return setPlatformFlag(FLAG_LOGIN_SUCCESS);
					return USERS_INDEX;
				}

				logger.info("用户的密码错误" + this.objUsers.getLId());

				addFieldError("password", "用户密码错误");
				return setPlatformFlag(FLAG_LOGIN_ERROR);
			}

			logger.error(this.objUsers.getLId() + "用户没有密码");

			addFieldError("password", "密码错误");
			return setPlatformFlag(FLAG_LOGIN_ERROR);
		}

		logger.error("没有拿到用户对象");

		addFieldError("username", "无此用户");
		return setPlatformFlag(FLAG_LOGIN_ERROR);
	}

	/**
	 * 用户登陆处理 。
	 * 
	 * @return
	 * @throws IOException
	 * @throws IOException
	 */
	public void customerLogin() throws IOException {
//		String strVerifyCodeInSession;

		// 参数验证
		if (StringUtils.isBlank(strUsername)) {
			logger.error("用户名不能为空");
			toWeb("用户名不能为空");
			return;
		}

		if (StringUtils.isBlank(strPassword)) {
			logger.error("密码不能为空");
			toWeb("密码不能为空");
			return;
		}

//		if (StringUtils.isBlank(strVerifycode)) {
//			logger.error("验证码不能为空");
//			toWeb("验证码不能为空");
//			return;
//		}
		logger.info(getRequest().getRemoteHost() + ",username:---"
				+ strUsername + ",password:---" + strPassword + "登陆开始"
				+ ",verifycode:---" + strVerifycode);

		// 开始处理逻辑
		// 先验证验证码
//		strVerifyCodeInSession = (String) getRequest().getSession()
//				.getAttribute(
//						Config.objCOMConfig.getProperty("validateCode",
//								"validateCode"));
//		if (!strVerifycode.equalsIgnoreCase(strVerifyCodeInSession)) {
//			addFieldError("verifycode", "验证码错误");
//			toWeb("验证码错误");
//			return;
//		}

		// 验证用户名和密码
		objCustomer = new Customer();
		objCustomer.setStrNickName(strUsername);
		try {
			objCustomer = objCustomerService.getRecordByProps(objCustomer,
					Customer.NICKNAME);

			if (objCustomer != null && objCustomer.getLId() > 0) {
				if (!StringUtils.isBlank(objCustomer.getStrPassword())) {
					if (strPassword != null
							&& EncryptAndDecryptUtils.md5(strPassword.trim())
									.equals(objCustomer.getStrPassword())) {
						if (objCustomer.getNIsActive() == null
								|| objCustomer.getNIsActive() == 0) {
							logger.error("此用户已禁用");
							toWeb("此用户已禁用");
							return;
						}

						// 验证通过，把用户ID保存到Session中
						setCustomerIdToSession(String.valueOf(objCustomer
								.getLId()));
						setBaseEntityToSession("objCustomer", objCustomer);

						logger.info("用户登陆成功，用户：" + strUsername + "--id="
								+ objCustomer.getlId() + "--密码：" + strPassword
								+ "--ip:" + getRequest().getRemoteHost());
						toWeb(Action.SUCCESS);
						return;
					} else {
						logger.error("用户密码错误");
						toWeb("用户密码错误");
						return;
					}
				} else {
					logger.error(objCustomer.getLId() + "用户没有密码");
					toWeb("密码错误");
					return;
				}
			} else {
				logger.error("没有拿到用户对象");
				toWeb("无此用户");
				return;
			}
		} catch (Exception e) {
			logger.error("", e);

			setErrorText("出现异常，请重新登录！");
			toWeb("出现异常，请重新登录！");
			return;
		}
	}

	public String destroyLogin() {
		logger.info("开始注销登录...");
		if (getBaseEntityFromSession("objUsers") != null) {
			Users objUsres = (Users) getBaseEntityFromSession("objUsers");

			logger.info("当前登录用户【" + objUsres.getStrName() + "】用户ID为【"
					+ objUsres.getlId() + "】");
			removeAttributeFromSession("objOrganization");
		}
		removeAttributeFromSession(Config.objCOMConfig.getProperty(
				"USER_ID_KEY", "USER_ID"));

		return "destroyLogin";
	}

	public String usersIndex() {
		if (StringUtils.isBlank(getUserIdFromSession())) {
			addFieldError("msg", "Session 已失效，请重新登录！");
			return "userLogin";
		}

		return "usersIndex";
	}

	public String destroyCustomerLogin() {
		logger.info("开始注销登录...");
		if (getBaseEntityFromSession("objCustomer") != null) {
			Customer objCustomer = (Customer) getBaseEntityFromSession("objCustomer");

			logger.info("当前登录用户【" + objCustomer.getStrSname() + "】用户ID为【"
					+ objCustomer.getlId() + "】");
			removeAttributeFromSession("objCustomer");
		}
		removeAttributeFromSession(Config.objCOMConfig.getProperty(
				"CUSTOMER_ID_KEY", "CUSTOMER_ID"));
		return "destroyCustomerLogin";
	}
	
	/**
	 * 新浪授权成功跳转页面。
	 * 
	 * @return
	 */
	public String sinaLogin() {
		// 获取授权成功返回的参数code
		String strCode = getRequest().getParameter("code");
		// 新增微博用户。
		Customer objCustomer = null;
		if (strCode == null || strCode.length() <= 0) {
			setErrorText("无法获取您的授权信息！");
			return this.commonError();
		}
		try {
			// 根据strCode 获取 access_token
			HttpClient objClient = new HttpClient();
			StringBuffer sbufURL = new StringBuffer();

			sbufURL.append("https://api.weibo.com/oauth2/access_token");
			sbufURL.append("?client_id=4148667325");
			sbufURL.append("&client_secret=a4483496c7a565538f37a87e0ead9bbf");
			sbufURL.append("&grant_type=authorization_code");
			// sbufURL.append("&redirect_uri=http://127.0.0.1:8080/login/sinaLogin.do");
//			sbufURL.append("&redirect_uri=http://www.yuyingzhijia.cn/login/sinaLogin.do");
			sbufURL.append("&redirect_uri=http://www.yuyingzhijia.cn");
			sbufURL.append("&code=" + strCode);

			Response response = objClient.post(sbufURL.toString(), new PostParameter[0], false, null);
			JSONObject objJSON = response.asJSONObject();

			String access_token = objJSON.get("access_token").toString();
			String uid = objJSON.get("uid").toString();
			SUsers um = new SUsers(access_token);
			SUser user = um.showUserById(uid);
			user.getAvatarLarge();

//			Object objCustomerIDs = getRequest().getSession().getAttribute("CUSTOMER_ID");
			// 判断此用户使用已经授权过，如果已经授权则直接获取数据库中数据进行更新。
			objCustomer = this.objCustomerService.getBySql(" select * from customer s where s.uid='" + uid + "'");
			logger.info("=============access_token：" + access_token + "=================");
			logger.info("=============当前用户昵称为：" + user.getScreenName() + "=================");
			if (objCustomer == null)
			{
				logger.info("=============当前用户为新增用户=================");
				objCustomer = new Customer();
				objCustomer.setStrNickName(user.getScreenName()); // 设置昵称。
				objCustomer.setAccessToken(access_token); // access_token
				objCustomer.setuId(uid);
				objCustomer.setAvatarLarge(user.getAvatarLarge());
				objCustomer.setStrRegistTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()).toString());
				this.objCustomerService.save(objCustomer);
			}
			else
			{
				logger.info("=============当前用户为老用户=================");
				objCustomer.setAccessToken(access_token); // access_token
				this.objCustomerService.update(objCustomer);
			}
			// 验证通过，把用户ID保存到Session中
			setCustomerIdToSession(String.valueOf(objCustomer.getLId()));
			setBaseEntityToSession("objCustomer", objCustomer);
//			Object objCustomerID = getRequest().getSession().getAttribute("CUSTOMER_ID");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("微博授权失败：" + e.getMessage());
			setErrorText("新浪授权失败,请重新授权登陆！");
			return this.commonError();
		}

		// 新浪授权成功
		logger.info("新浪授权成功，当前用户昵称:" + objCustomer.getStrNickName());
		return "tamplateOneLoginSuccess";
	}
	
	/**
	 * 判断用户是否登陆。
	 * 
	 */
	public void loadCustomer()
	{
		try {
			Object objCustomerID = getRequest().getSession().getAttribute("CUSTOMER_ID");
			Customer objCustomer;
			JsonConfig objJsonConfig = new JsonConfig();
			
			if (objCustomerID == null)
			{
				objCustomer = new Customer(); 
			}
			else
			{
				Object objCur = getRequest().getSession().getAttribute("objCustomer");
				
				if (objCur != null)
				{
					objCustomer = (Customer)objCur;
					logger.info("当前用户已登陆，用户id为：" + objCustomer.getlId());
				}
				else
				{
					objCustomer = new Customer();
				}
			}
			
			objJsonConfig.setIgnoreDefaultExcludes(false);
			objJsonConfig.setExcludes(new String[] {"strCustomerTypeName", "objParentCustomer", "lstChildrenCustomer", "lstProject", "objCustomerType", "lstEmail" });
			toWeb(net.sf.json.JSONObject.fromObject(objCustomer, objJsonConfig).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("从session中获取用户登录对象异常，异常信息：" + e.getMessage());
		}
	}

	public String getStrVerifycode() {
		return this.strVerifycode;
	}

	public void setStrVerifycode(String strVerifycode) {
		this.strVerifycode = strVerifycode;
	}

	public String getStrUsername() {
		return this.strUsername;
	}

	public void setStrUsername(String strUsername) {
		this.strUsername = strUsername;
	}

	public String getStrPassword() {
		return this.strPassword;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

	public Long getlProjectID() {
		return this.lProjectID;
	}

	public void setlProjectID(Long lProjectID) {
		this.lProjectID = lProjectID;
	}

	public Long getLProjectID() {
		return this.lProjectID;
	}

	public void setLProjectID(Long lProjectID) {
		this.lProjectID = lProjectID;
	}

	public String getStrPlatformFlag() {
		return strPlatformFlag;
	}

	public void setStrPlatformFlag(String strPlatformFlag) {
		this.strPlatformFlag = strPlatformFlag;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

}