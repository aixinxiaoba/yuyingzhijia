package com.manage.crm.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javacommon.core.Config;
import javacommon.core.base.BaseStruts2Action;
import javacommon.util.SearchCondition;
import javacommon.util.StringUtils;
import javacommon.util.encrypt.EncryptAndDecryptUtils;
import javacommon.util.excel.ExcelUtils;
import javacommon.util.mail.MailClient;
import javacommon.util.mail.MailConfig;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.manage.crm.entity.Customer;
import com.manage.crm.entity.CustomerType;
import com.manage.crm.entity.Email;
import com.manage.crm.entity.ProFiledInfo;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.Users;
import com.manage.crm.service.CustomerService;
import com.manage.crm.service.CustomerTypeService;
import com.manage.crm.service.EmailService;
import com.manage.crm.service.ProFiledInfoService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.UsersService;
import com.manage.crm.util.ChartData;
import com.manage.crm.util.ChartsUtil;
import com.manage.crm.util.HttpUtils;
import com.manage.crm.util.Pagination;

@Controller("customerManageAction")
@Scope("prototype")
public class CustomerManageAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(CustomerManageAtion.class);
	private static final String CUSTOMER_LOGIN = "customerLogin";
	private static final String CUSTOMER_LOGIN_SUCCESS = "customerLoginSuccess";
	private static final String CUSTOMER_DOWN_LIST = "customerDownList";
	private static final String CUSTOMER_LIST = "customerList";
	private static final String CUSTOMER_REGISTER = "customerRegister";
	private static final String CUSTOMER_INDEX = "customerIndex";
	private static final String CUSTOMER_REGISTER_SUCC = "customerRegisterSucc";
	private static final String CUSTOMER_REGISTER_RESULT = "customerRegisterResult";
	private static final String CUSTOMER_DETIAL = "customerDetial";
	private static final String CUSTOMER_DOWN_DETIAL = "customerDownDetial";
	private static final String CUSTOMER_UPDATE = "customerUpdate";
	private static final String CUSTOMER_DOWN_UPDATE = "customerDownUpdate";

	@Resource(name = "proFiledInfoService")
	private ProFiledInfoService objProFiledInfoService;

	@Resource(name = "usersService")
	private UsersService objUsersService;

	@Resource(name = "customerService")
	private CustomerService objCustomerService;

	@Resource(name = "projectService")
	private ProjectService objProjectService;

	@Resource(name = "customerTypeService")
	private CustomerTypeService objCustomerTypeService;

	@Resource(name = "emailService")
	private EmailService objEmailService;
	private List<ProFiledInfo> lstProFiledInfo;
	private Long lUsersID;
	private List<Project> lstProject;
	private List<Customer> lstCustomer;
	private List<CustomerType> lstCustomerType;
	private Project objProject;
	private Customer objCustomer;
	private Users objUsers;
	private Long lProjectID;
	private Long lCustomerID;
	private Long lCustomerTypeID;
	private String strCustomerName;
	private String strNickName;
	private String strCustomerTypeID;
	private String strCustomerType;
	private String strCustomerQQ;
	private String strParentCustomerQQ;
	private String strProjectName;
	String strEmailContent = "你好，欢迎注册《极速暴力产品行销》系统，认真看完介绍之后，立即联系金泉QQ:653739294 电话：13613669861 了解具体内容";
	private Integer nIsDownCustomer;
	private int nType;
	private String myUrl;
	String strVerifycode;
	String strPassword;
	String strRePassword;
	String strOldPassword;
	
	private String projectKey;
	private String menuKey;

	public String login() {
//		if (!StringUtils.isEmpty(this.projectKey))
//		{
//			// 根据项目标志获取项目信息
//			objProject = this.objProjectService.getByHql(" from Project where projectKey='" + this.projectKey + "'");
//			
//			this.lProjectID = objProject != null ? objProject.getlId() : null;
//		}
//		
//		if ((this.lProjectID != null) && (this.lProjectID.longValue() > 0L)) {
//			this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
//		} else if (this.objCustomer != null) {
//			if ((this.objCustomer.getLstProject() != null) && (this.objCustomer.getLstProject().size() > 0)) {
//				this.objProject = ((Project) new ArrayList(this.objCustomer.getLstProject()).get(0));
//			} else {
//				setErrorText("无法获取项目信息，不能进行注册，请核对注册地址");
//				return "commonError";
//			}
//		} else {
//			setErrorText("无法获取项目信息，不能进行注册，请核对注册地址");
//			return "commonError";
//		}
//
//		if (this.objProject == null) {
//			setErrorText("对不起当前项目不合法，不能进行注册，请核对网址是否正确！");
//			return "commonError";
//		}
		
		return "customerLogin";
	}

	public String customerLogin() {
		if (StringUtils.isBlank(this.strNickName)) {
			addFieldError("username", "用户名不能为空");
			return "customerLogin";
		}

		if (StringUtils.isBlank(this.strPassword)) {
			addFieldError("password", "密码不能为空");
			return "customerLogin";
		}

		if (StringUtils.isBlank(this.strVerifycode)) {
			addFieldError("verifycode", "验证码不能为空");
			return "customerLogin";
		}

		logger.info(getRequest().getRemoteHost() + ",username:---" + this.strCustomerName + ",password:---" + this.strPassword + "登陆开始" + ",verifycode:---" + this.strVerifycode);

		String strVerifyCodeInSession = (String) getRequest().getSession().getAttribute(Config.objCOMConfig.getProperty("validateCode", "validateCode"));
		if (!this.strVerifycode.equalsIgnoreCase(strVerifyCodeInSession)) {
			addFieldError("verifycode", "验证码错误");
			return "customerLogin";
		}

		this.objCustomer = new Customer();

		this.objCustomer.setStrNickName(this.strNickName);
		// this.objCustomer.setnIsActive(1);
		// this.objCustomer = ((Customer) this.objCustomerService.getRecordByProps(this.objCustomer, "strNickName,nIsActive"));
		this.objCustomer = ((Customer) this.objCustomerService.getRecordByProps(this.objCustomer, "strNickName"));
		if ((this.objCustomer != null) && (this.objCustomer.getLId().longValue() > 0L)) {
			if (!StringUtils.isBlank(this.objCustomer.getStrPassword())) {
				if ((this.strPassword != null) && (EncryptAndDecryptUtils.md5(this.strPassword.trim()).equals(this.objCustomer.getStrPassword()))) {
					if (this.objCustomer.getnIsActive() == 0) {
						addFieldError("username", "此用户已禁用");
						return "customerLogin";
					}

					setCustomerIdToSession(String.valueOf(this.objCustomer.getLId()));
					setBaseEntityToSession("objCustomer", this.objCustomer);
					logger.info("用户【" + this.objCustomer.getStrSname() + "】登陆成功!");

					return "customerLoginSuccess";
				}

				logger.info("用户密码错误" + this.objCustomer.getLId());
				addFieldError("password", "用户密码错误");
				return "customerLogin";
			}

			logger.error(this.objCustomer.getLId() + "用户没有密码");

			addFieldError("password", "密码错误");
			return "customerLogin";
		}

		logger.error("没有拿到用户对象");

		addFieldError("username", "无此用户");
		return "customerLogin";
	}
	
	public String customerLoginOld() {
		if (StringUtils.isBlank(this.strCustomerName)) {
			addFieldError("username", "用户名不能为空");
			return "customerLogin";
		}

		if (StringUtils.isBlank(this.strPassword)) {
			addFieldError("password", "密码不能为空");
			return "customerLogin";
		}

		if (StringUtils.isBlank(this.strVerifycode)) {
			addFieldError("verifycode", "验证码不能为空");
			return "customerLogin";
		}

		logger.info(getRequest().getRemoteHost() + ",username:---" + this.strCustomerName + ",password:---" + this.strPassword + "登陆开始" + ",verifycode:---" + this.strVerifycode);

		String strVerifyCodeInSession = (String) getRequest().getSession().getAttribute(Config.objCOMConfig.getProperty("validateCode", "validateCode"));
		if (!this.strVerifycode.equalsIgnoreCase(strVerifyCodeInSession)) {
			addFieldError("verifycode", "验证码错误");
			return "customerLogin";
		}

		this.objCustomer = new Customer();

		this.objCustomer.setStrQQ(this.strCustomerName);
		this.objCustomer.setnIsActive(1);
		this.objCustomer = ((Customer) this.objCustomerService.getRecordByProps(this.objCustomer, "strQQ,nIsActive"));

		if ((this.objCustomer != null) && (this.objCustomer.getLId().longValue() > 0L)) {
			if (!StringUtils.isBlank(this.objCustomer.getStrPassword())) {
				if ((this.strPassword != null) && (EncryptAndDecryptUtils.md5(this.strPassword.trim()).equals(this.objCustomer.getStrPassword()))) {
					if (this.objCustomer.getnIsActive() == 0) {
						addFieldError("username", "此用户已禁用");
						return "customerLogin";
					}

					setCustomerIdToSession(String.valueOf(this.objCustomer.getLId()));
					setBaseEntityToSession("objCustomer", this.objCustomer);
					logger.info("用户【" + this.objCustomer.getStrSname() + "】登陆成功!");

					return "customerLoginSuccess";
				}

				logger.info("用户的密码错误" + this.objCustomer.getLId());

				addFieldError("password", "用户密码错误");
				return "customerLogin";
			}

			logger.error(this.objCustomer.getLId() + "用户没有密码");

			addFieldError("password", "密码错误");
			return "customerLogin";
		}

		logger.error("没有拿到用户对象");

		addFieldError("username", "无此用户");
		return "customerLogin";
	}

	public String customerIndex() {
		if (!commonValidateCustomer()) {
			addFieldError("msg", "Session 失效，请重新登录！");
			return "customerLogin";
		}

		return "customerIndex";
	}

	/*
	 * 验证用户昵称。
	 */
	public void checkNickName() throws IOException
	{
		String strMsg = "0";
		// 验证用户昵称唯一性。
		Customer objCurrentCustomer = objCustomerService.getByHql(" from Customer where strNickName='"+ this.strNickName +"'");
		
		if (objCurrentCustomer != null && objCurrentCustomer.getlId() > 0)
		{
			strMsg = "1";
		}
		
		toWeb(strMsg);
	}
	
	/**
	 * 查询下级客户。
	 * 
	 * @return
	 */
	public String customerDownList() {
		if (!commonValidateCustomer()) {
			return "commonError";
		}

		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			setErrorText("对不起，无法获取您的项目项目信息！");
			return "commonError";
		}
		this.lstCustomerType = this.objCustomerTypeService.listByHql("From CustomerType where objProject.lId = " + this.lProjectID);

		return "customerDownList";
	}
	
	public String customerDownSearch() {
		String strHQL = "From Customer";

		if (!commonValidateCustomer()) {
			return "commonError";
		}

		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			setErrorText("对不起当前项目不合法");
			return "commonError";
		}

		this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		if (this.objProject == null) {
			setErrorText("对不起当前项目不合法");
			return "commonError";
		}

		strHQL = strHQL + " where objProject.lId=" + this.lProjectID;

		logger.info("父类id为：" + this.objCustomer.getlId());
		strHQL = strHQL + " and objParentCustomer.lId=" + this.objCustomer.getlId();
		if (!StringUtils.isEmpty(this.strCustomerType)) {
			strHQL = strHQL + " and objCustomerType.strName ='" + this.strCustomerType + "'";
		}

		if (!StringUtils.isEmpty(this.strCustomerName)) {
			strHQL = strHQL + " and strSname like '%" + this.strCustomerName + "%'";
		}

		if (!StringUtils.isEmpty(this.strCustomerQQ)) {
			strHQL = strHQL + " and strQQ like '%" + this.strCustomerQQ + "%'";
		}

		logger.info("开始搜索客户名称包含【" + this.strCustomerName + "】的，客户类型为【" + this.strCustomerType + "】,QQ包含【" + this.strCustomerQQ + "】的项目");
		this.lstCustomer = this.objCustomerService.listByHql(strHQL);
		this.lstCustomerType = new ArrayList(this.objProject.getLstCustomerType());

		return "customerDownList";
	}

	public String register() {
		ProFiledInfo objProFiledInfo = new ProFiledInfo();

		if ((this.lCustomerID != null) && (this.lCustomerID.longValue() > 0L)) {
			this.objCustomer = ((Customer) this.objCustomerService.getById(this.lCustomerID));
			if (this.objCustomer == null) {
				setErrorText("无法获取客户信息，不能进行注册，请核对注册地址");
				return "commonError";
			}
		}

		if (!StringUtils.isEmpty(this.projectKey))
		{
			// 根据项目标志获取项目信息
			objProject = this.objProjectService.getByHql(" from Project where projectKey='" + this.projectKey + "'");
			
			this.lProjectID = objProject != null ? objProject.getlId() : null;
		}
		
		if ((this.lProjectID != null) && (this.lProjectID.longValue() > 0L)) {
			this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		} else if (this.objCustomer != null) {
			if ((this.objCustomer.getLstProject() != null) && (this.objCustomer.getLstProject().size() > 0)) {
				this.objProject = ((Project) new ArrayList(this.objCustomer.getLstProject()).get(0));
			} else {
				setErrorText("无法获取项目信息，不能进行注册，请核对注册地址");
				return "commonError";
			}
		} else {
			setErrorText("无法获取项目信息，不能进行注册，请核对注册地址");
			return "commonError";
		}

		if (this.objProject == null) {
			setErrorText("对不起当前项目不合法，不能进行注册，请核对网址是否正确！");
			return "commonError";
		}

		objProFiledInfo.setlPid(this.objProject.getlId());
		objProFiledInfo.setnStatus(1);
		objProFiledInfo.setnIsAdd(1);
		this.lstProFiledInfo = this.objProFiledInfoService.listByProps(objProFiledInfo, "lPid,nStatus,nIsAdd", Order.asc("nSequence"));

		if ((this.lstProFiledInfo == null) || (this.lstProFiledInfo.size() <= 0)) {
			setErrorText("对不起当前项目没有设置注册字段，您还不能进行注册！");
			return "commonError";
		}

		return "customerRegister";
	}

	public String customerAdd() {
		Set lstProject = new HashSet();
		CustomerType objCustomerType = null;
		Set lstTempCustomer = new HashSet();
		Customer objTempCustomer = null;
		Customer objCustomerFromCheck = null;

		if ((this.lCustomerID != null) && (this.lCustomerID.longValue() > 0L)) {
			objTempCustomer = (Customer) this.objCustomerService.getById(this.lCustomerID);

			if (objTempCustomer == null) {
				setErrorText("无法获取客户信息，不能进行注册，请核对注册地址");
				return "commonError";
			}
			lstTempCustomer.add(objTempCustomer);
			this.objCustomer.setObjParentCustomer(objTempCustomer);
		}

		if ((this.lProjectID != null) && (this.lProjectID.longValue() > 0L)) {
			this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		} else if (objTempCustomer != null) {
			if ((objTempCustomer.getLstProject() != null) && (objTempCustomer.getLstProject().size() > 0)) {
				this.objProject = ((Project) new ArrayList(objTempCustomer.getLstProject()).get(0));
			} else {
				setErrorText("无法获取项目信息，不能进行注册，请核对注册地址");
				return "commonError";
			}
		} else {
			setErrorText("无法获取项目信息，不能进行注册，请核对注册地址");
			return "commonError";
		}

		if (this.objProject == null) {
			setErrorText("对不起当前项目不合法，不能进行注册，请核对网址是否正确！");
			return "commonError";
		}

		if (this.objCustomer == null) {
			setErrorText("注册失败！无法获取到您的注册信息，请返回重新注册！");
			return "commonError";
		}
		lstProject.add(this.objProject);
//		String strSQL;
//		if ((objTempCustomer != null) && (objTempCustomer.getlId().longValue() > 0L)) {
//			strSQL = "select * from customer where SParent_ID=" + objTempCustomer.getlId() + " and id in(select cid from customer_project where pid = " + this.objProject.getlId() + ") and QQ='"
//					+ this.objCustomer.getStrQQ() + "'";
//		} else {
//			strSQL = "select * from customer where QQ='" + this.objCustomer.getStrQQ() + "' and id in(select cid from customer_project where pid = " + this.objProject.getlId() + ")";
//		}
//
//		objCustomerFromCheck = (Customer) this.objCustomerService.getBySql(strSQL);
//		if ((objCustomerFromCheck != null) && (objCustomerFromCheck.getlId().longValue() > 0L)) {
//			logger.info("当前用户是已经注册用户直接跳转到结果页面！");
////			if (this.objProject.getnEmailListStatus() == 1) {
////				return "customerRegisterResult";
////			}
//			return "customerRegisterSucc";
//		}

		objCustomerType = (CustomerType) this.objCustomerTypeService.getByHql("From CustomerType where objProject.lId=" + this.objProject.getlId() + " and nIsDefaultValue = 1");
		if (objCustomerType != null) {
			this.objCustomer.setObjCustomerType(objCustomerType);
			logger.info("当前客户的客户类型设置为：" + objCustomerType.getStrName());
		}
		
		// 验证登陆名。
		if (StringUtils.isEmpty(this.objCustomer.getStrNickName()))
		{
			setErrorText("登陆名不能为空！");
			return "commonMsg";
		}
		
		// 验证密码。
		if (StringUtils.isEmpty(this.objCustomer.getStrPassword()))
		{
			setErrorText("密码不能为空！");
			return "commonMsg";
		}
		
		// 验证密码。
		if (StringUtils.isEmpty(this.objCustomer.getStrRePassword()))
		{
			setErrorText("确认密码不能为空！");
			return "commonMsg";
		}
		
		// 验证密码。
		if (!this.objCustomer.getStrRePassword().equals(this.objCustomer.getStrPassword()))
		{
			setErrorText("您输入的两次密码不一致，请重新填写！");
			return "commonMsg";
		}
		
		// 验证用户昵称唯一性。
		Customer objCurrentCustomer = objCustomerService.getByHql(" from Customer where strNickName='"+ this.objCustomer.getStrNickName() +"'");
		
		if (objCurrentCustomer != null && objCurrentCustomer.getlId() > 0)
		{
			setErrorText("登陆名：【" + this.objCustomer.getStrNickName() + "】已被占用，请更换一个你喜欢的昵称");
			return "commonMsg";
		}
		// 设置初始密码
		this.objCustomer.setStrPassword(EncryptAndDecryptUtils.md5(this.objCustomer.getStrPassword()));
		this.objCustomer.setnCurrentLevel(1);
		this.objCustomer.setnIsActive(1);
		this.objCustomer.setnIsLastLevel(0);
		this.objCustomer.setStrRegistTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		this.objCustomer.setLstProject(lstProject);

		if (!this.objCustomerService.save(this.objCustomer)) {
			setErrorText("出现异常，注册失败");
			return "commonError";
		}

		if (!StringUtils.isEmpty(this.objProject.getStrEmailContent())) {
			this.strEmailContent = this.objProject.getStrEmailContent();
		}
//		new Thread(new Runnable() {
//			public void run() {
//				try {
//					CustomerManageAtion.logger.info("准备发送邮件...");
//					boolean bStatus = MailClient.sendHtmlMailWithNick(new MailConfig("育婴之家", Config.objCOMConfig.getProperty("fromEmail"), CustomerManageAtion.this.objCustomer.getStrQQ() + "@qq.com",
//							true, CustomerManageAtion.this.objProject.getStrPname(), CustomerManageAtion.this.strEmailContent));
//
//					CustomerManageAtion.logger.info("邮件发送状态：" + bStatus);
//					if (!bStatus) {
//						for (int i = 3; i < 8; i++) {
//							if (Config.objCOMConfig.getProperty("fromEmail_" + i) == null) {
//								CustomerManageAtion.logger.error("邮件发送失败！");
//								break;
//							}
//							if (MailClient.sendHtmlMailWithNick(new MailConfig("育婴之家", Config.objCOMConfig.getProperty("fromEmail_" + i), CustomerManageAtion.this.objCustomer.getStrQQ() + "@qq.com",
//									Config.objCOMConfig.getProperty("fromEmailPassword_" + i), true, CustomerManageAtion.this.objProject.getStrPname(), CustomerManageAtion.this.strEmailContent))) {
//								CustomerManageAtion.logger.info("邮件发送成功，使用的邮箱为：" + Config.objCOMConfig.getProperty(new StringBuilder("fromEmail_").append(i).toString()));
//								break;
//							}
//						}
//					}
//				} catch (Exception e) {
//					CustomerManageAtion.logger.error("邮件发送失败", e);
//				}
//			}
//		}).start();

//		if (this.objProject.getnEmailListStatus() == 1) {
//			return "customerRegisterResult";
//		}

		// 将用户信息存放到session中。
		// 验证通过，把用户ID保存到Session中
		setCustomerIdToSession(String.valueOf(objCustomer.getLId()));
		setBaseEntityToSession("objCustomer", objCustomer);
		
		return "customerRegisterSucc";
	}

	public void sendQQEmailList() throws IOException {
		try {
			Map paramMap = new HashMap();

			paramMap.put("t", "qf_booked_feedback");
			paramMap.put("id", getRequest().getParameter("emailListID"));
			paramMap.put("to", getRequest().getParameter("to"));
			String strContent = HttpUtils.URLPostForContent("http://list.qq.com/cgi-bin/qf_compose_send", paramMap);

			logger.info("返回订阅信息：" + strContent);
			if (strContent.contains("一封订阅确认邮件已发送至您的邮箱")) {
				toWeb("success");
			} else {
				toWeb("自动订阅失败，开始使用手动订阅！");
			}
		} catch (Exception e) {
			logger.error("", e);
			setErrorText("出现异常，请稍后重试！");
			toWeb("自动订阅失败，开始使用手动订阅！");
		}
	}

	public void loadUsersProject() throws IOException {
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");
		Pagination objPagination = null;
		JsonConfig objJsonConfig = new JsonConfig();
		String strSQL = "";

		if (StringUtils.isBlank(getUserIdFromSession())) {
			logger.error("Sessoin 失效，请重新登录！");
			toWeb(JSONObject.fromObject(new Pagination()).toString());
			return;
		}
		try {
			strSQL = "select * from project where id in (select PID from Users_Project where UID = " + getUserIdFromSession() + ")";

			if ((this.lCustomerTypeID != null) && (this.lCustomerTypeID.longValue() > 0L)) {
				strSQL = strSQL + " And CTID = " + this.lCustomerTypeID;
			}
			if (!StringUtils.isEmpty(this.strProjectName)) {
				strSQL = strSQL + " and pname like '%" + this.strProjectName + "%'";
			}
			objPagination = new Pagination();
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));

			this.objProjectService.listBySQL(objPagination, strSQL);

			objJsonConfig.setExcludes(new String[] { "objParentProject", "lstChildrenProject", "lstUsers", "lstFiledInfo", "lstCustomer", "lstCustomerType", "lstEmail", "objCustomerType","lstProjectMenu","lstNews" });
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
		}
		toWeb(JSONObject.fromObject(new Pagination()).toString());
	}

	public void loadCustomerProject() throws IOException {
		Pagination objPagination = null;
		JsonConfig objJsonConfig = new JsonConfig();

		if ((this.lCustomerID != null) && (this.lCustomerID.longValue() > 0L)) {
			this.objCustomer = ((Customer) this.objCustomerService.getById(this.lCustomerID));
			if (this.objCustomer == null) {
				toWeb(JSONObject.fromObject(new Pagination("项目id无效")).toString());
				logger.error("项目id无效");
			}

		} else if (!commonValidateCustomer()) {
			toWeb(JSONObject.fromObject(new Pagination(2)).toString());
			logger.error("Sessoin 失效，请重新登录！");
			return;
		}
		try {
			this.lstProject = new ArrayList(this.objCustomer.getLstProject());
			objPagination = new Pagination();
			objPagination.setTotal(this.lstProject == null ? 0 : this.lstProject.size());
			objPagination.setRowsSimple(this.lstProject);
			objJsonConfig.setExcludes(new String[] { "objParentProject", "lstChildrenProject", "lstUsers", "lstFiledInfo", "lstCustomer", "lstCustomerType", "lstEmail", "objCustomerType","lstProjectMenu","lstNews" });
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
		}
		toWeb(JSONObject.fromObject(new Pagination()).toString());
	}

	public void loadCustomers() throws IOException {
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");

		JsonConfig objJsonConfig = new JsonConfig();
		SimpleExpression objConditionOne = null;
		SimpleExpression objConditionTwo = null;
		SimpleExpression objConditionThree = null;
		SimpleExpression objConditionFour = null;

		if (!StringUtils.isEmpty(this.projectKey))
		{
			// 根据项目标志获取项目信息
			objProject = this.objProjectService.getByHql(" from Project where projectKey='" + this.projectKey + "'");
			
			this.lProjectID = objProject != null ? objProject.getlId() : null;
		}
		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			setErrorText("对不起当前项目不合法，不能进行注册，请核对网址是否正确！");
			toWeb(null);
			return;
		}

		try {
			if ((!StringUtils.isEmpty(this.strCustomerTypeID)) && (Long.parseLong(this.strCustomerTypeID) > 0L)) {
				objConditionOne = Restrictions.eq("objCustomerType.lId", Long.valueOf(Long.parseLong(this.strCustomerTypeID)));
			}
			if (!StringUtils.isEmpty(this.strCustomerQQ)) {
				objConditionTwo = Restrictions.like("strQQ", "%" + this.strCustomerQQ + "%");
			}
			if (!StringUtils.isEmpty(this.strCustomerName)) {
				objConditionThree = Restrictions.like("strSname", "%" + this.strCustomerName + "%");
			}
			if (!StringUtils.isEmpty(this.strParentCustomerQQ)) {
				Customer objParentCustomer = (Customer) this.objCustomerService.getByHql("from Customer where strQQ='" + this.strParentCustomerQQ + "'");

				if (objParentCustomer != null) {
					objConditionFour = Restrictions.eq("objParentCustomer.lId", objParentCustomer.getlId());
				} else {
					objConditionFour = Restrictions.eq("objParentCustomer.lId", Long.valueOf(-1L));
				}
			}

			logger.info("开始搜索客户名称包含【" + this.strCustomerName + "】的，客户类型为【" + this.strCustomerTypeID + "】,QQ包含【" + this.strCustomerQQ + "】的项目");
			Pagination objPagination = new Pagination();
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}

			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			Criterion objSQLCondition = Restrictions.sqlRestriction("id in(select cid from customer_project where pid=" + this.lProjectID + ")");
			this.objCustomerService.listCustomerByMultiCondition(objPagination, new SearchCondition(objSQLCondition, objConditionOne, objConditionTwo, objConditionThree, objConditionFour), null);
			objJsonConfig.setIgnoreDefaultExcludes(false);
			objJsonConfig.setExcludes(new String[] { "objParentCustomer", "lstChildrenCustomer", "lstProject", "objCustomerType", "lstEmail" });
			logger.info("当前页数：" + nCurrentPage + "--每页显示的个数为：" + nPageSize);
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
		}
	}

	/**
	 * 查询下级客户（制定项目下的用户）。
	 * 
	 * @throws IOException
	 */
	public void loadDwonCustomers() throws IOException {
		loadDownCustomerOper(true);
	}
	
	/**
	 * 加载所有下级客户（不区分项目类型）。
	 * 
	 * @throws IOException
	 */
	public void loadAllDwonCustomers() throws IOException {
		loadDownCustomerOper(false);
	}
	
	/**
	 * 查询下级客户ch
	 * @throws IOException 
	 */
	public void loadDownCustomerOper(boolean bIsCheckProject) throws IOException
	{
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");

		JsonConfig objJsonConfig = new JsonConfig();
		SimpleExpression objConditionOne = null;
		SimpleExpression objConditionTwo = null;
		SimpleExpression objConditionThree = null;
		Criterion objSQLCondition = null;

		if (!commonValidateCustomer()) {
			toWeb(JSONObject.fromObject(new Pagination(2)).toString());
			logger.error("Sessoin 失效，请重新登录！");
			return;
		}

		if (bIsCheckProject)
		{
			if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
				toWeb(JSONObject.fromObject(new Pagination("获取项目失败，请重试！")).toString());
				logger.error("获取项目失败，请重试！");
				return;
			}
		}
		
		try {
			if ((!StringUtils.isEmpty(this.strCustomerTypeID)) && (Long.parseLong(this.strCustomerTypeID) > 0L)) {
				objConditionOne = Restrictions.eq("objCustomerType.lId", Long.valueOf(Long.parseLong(this.strCustomerTypeID)));
			}
			if (!StringUtils.isEmpty(this.strCustomerQQ)) {
				objConditionTwo = Restrictions.like("strQQ", "%" + this.strCustomerQQ + "%");
			}
			if (!StringUtils.isEmpty(this.strCustomerName)) {
				objConditionThree = Restrictions.like("strSname", "%" + this.strCustomerName + "%");
			}

			logger.info("开始搜索客户名称包含【" + this.strCustomerName + "】的，客户类型为【" + this.strCustomerTypeID + "】,QQ包含【" + this.strCustomerQQ + "】的项目");
			Pagination objPagination = new Pagination();
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}

			if (bIsCheckProject)
			{
				objSQLCondition = Restrictions.sqlRestriction("id in(select cid from customer_project where pid=" + this.lProjectID + ")");
			}
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));

			this.objCustomerService.listCustomerByMultiCondition(objPagination, new SearchCondition(objSQLCondition, Restrictions.eq("objParentCustomer.lId", this.objCustomer.getlId()),
					objConditionOne, objConditionTwo, objConditionThree), null);
			objJsonConfig.setIgnoreDefaultExcludes(false);
			objJsonConfig.setExcludes(new String[] { "objParentCustomer", "lstChildrenCustomer", "lstProject", "objCustomerType", "lstEmail" });
			logger.info("当前页数：" + nCurrentPage + "--每页显示的个数为：" + nPageSize);
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
		}
	}

	public void loadRecentlyCustomer() throws IOException, ParseException {
		SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat objSDF2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List lstChartData = new ArrayList();
		ChartsUtil objChartData = new ChartsUtil();
		int nTodayCount = 0;
		int nOneCount = 0;
		int nTwoCount = 0;
		int nThreeCount = 0;
		int nFourCount = 0;
		int nFiveCount = 0;
		int nSixCount = 0;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(5, -6);

		String strDaysOfSixAgo = objSDF.format(calendar.getTime());
		calendar.add(5, 1);
		String strDaysOfFiveAgo = objSDF.format(calendar.getTime());
		calendar.add(5, 1);
		String strDaysOfFourAgo = objSDF.format(calendar.getTime());
		calendar.add(5, 1);
		String strDaysOfThreeAgo = objSDF.format(calendar.getTime());
		calendar.add(5, 1);
		String strDaysOfTwoAgo = objSDF.format(calendar.getTime());
		calendar.add(5, 1);
		String strDaysOfOneAgo = objSDF.format(calendar.getTime());
		String strCurrentTime = objSDF.format(new Date());

		if ((this.lUsersID != null) && (this.lUsersID.longValue() > 0L)) {
			this.lstCustomer = this.objCustomerService.listBySql("SELECT * FROM CUSTOMER WHERE REGISTTIME > '" + strDaysOfSixAgo
					+ "'AND ID IN(select cid from customer_project where pid in(select PID from users_project where uid in (select id from users where id = " + this.lUsersID + ")))");
		} else if ((this.lCustomerID != null) && (this.lCustomerID.longValue() > 0L)) {
			this.lstCustomer = this.objCustomerService.listByHql("From Customer where objParentCustomer.lId=" + this.lCustomerID + " and strRegistTime > '" + strDaysOfSixAgo + "'");
		}

		if ((this.lstCustomer != null) && (this.lstCustomer.size() > 0)) {
			for (Customer objCustomer : this.lstCustomer) {
				String strRegDate = objCustomer.getStrRegistTime();

				if ((strRegDate != null) && (!StringUtils.isEmpty(strRegDate))) {
					if ((objSDF2.parse(strRegDate).getTime() <= objSDF.parse(strDaysOfFiveAgo).getTime()) && (objSDF2.parse(strRegDate).getTime() > objSDF.parse(strDaysOfSixAgo).getTime())) {
						nSixCount++;
					}

					if ((objSDF2.parse(strRegDate).getTime() <= objSDF.parse(strDaysOfFourAgo).getTime()) && (objSDF2.parse(strRegDate).getTime() > objSDF.parse(strDaysOfFiveAgo).getTime())) {
						nFiveCount++;
					}

					if ((objSDF2.parse(strRegDate).getTime() <= objSDF.parse(strDaysOfThreeAgo).getTime()) && (objSDF2.parse(strRegDate).getTime() >= objSDF.parse(strDaysOfFourAgo).getTime())) {
						nFourCount++;
					}

					if ((objSDF2.parse(strRegDate).getTime() <= objSDF.parse(strDaysOfTwoAgo).getTime()) && (objSDF2.parse(strRegDate).getTime() >= objSDF.parse(strDaysOfThreeAgo).getTime())) {
						nThreeCount++;
					}

					if ((objSDF2.parse(strRegDate).getTime() < objSDF.parse(strDaysOfOneAgo).getTime()) && (objSDF2.parse(strRegDate).getTime() >= objSDF.parse(strDaysOfTwoAgo).getTime())) {
						nTwoCount++;
					}

					if ((objSDF2.parse(strRegDate).getTime() < objSDF.parse(strCurrentTime).getTime()) && (objSDF2.parse(strRegDate).getTime() >= objSDF.parse(strDaysOfOneAgo).getTime())) {
						nOneCount++;
					}

					if (objSDF2.parse(strRegDate).getTime() >= objSDF.parse(strCurrentTime).getTime()) {
						nTodayCount++;
					}
				}
			}
		}
		lstChartData.add(new ChartData(strDaysOfSixAgo.replace(" 00:00:00", ""), nSixCount));
		lstChartData.add(new ChartData(strDaysOfFiveAgo.replace(" 00:00:00", ""), nFiveCount));
		lstChartData.add(new ChartData(strDaysOfFourAgo.replace(" 00:00:00", ""), nFourCount));
		lstChartData.add(new ChartData(strDaysOfThreeAgo.replace(" 00:00:00", ""), nThreeCount));
		lstChartData.add(new ChartData(strDaysOfTwoAgo.replace(" 00:00:00", ""), nTwoCount));
		lstChartData.add(new ChartData(strDaysOfOneAgo.replace(" 00:00:00", ""), nOneCount));
		lstChartData.add(new ChartData(strCurrentTime.replace(" 00:00:00", ""), nTodayCount));
		objChartData.setLstChartData(lstChartData);

		toWeb(JSONObject.fromObject(objChartData));
	}

	public String customerList() {
//		if (!commonValidateUsers()) {
//			return "commonError";
//		}
		if (this.lProjectID == null) {
			setErrorText("无法获取项目id！");
			return "commonError";
		}
		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			setErrorText("对不起当前项目不合法，您不能进行查询");
			return "commonError";
		}
		this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		if (this.objProject == null) {
			setErrorText("对不起当前项目不合法，您不能进行查询");
			return "commonError";
		}

		this.lstCustomerType = new ArrayList(this.objProject.getLstCustomerType());

		return "customerList";
	}

	public String loadMultiProCustomerList() throws IOException {
		JsonConfig objJsonConfig = new JsonConfig();
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");

		if (!commonValidateUsers()) {
			toWeb(JSONObject.fromObject(new Pagination(2)).toString());
			logger.error("Sessoin 失效，请重新登录！");
			return "commonError";
		}

		this.lstCustomer = new ArrayList((Collection) this.objCustomerService.listByHql("From Customer where nCurrentLevel = 2"));
		if ((this.lstCustomer != null) && (this.lstCustomer.size() > 0)) {
			for (int i = this.lstCustomer.size() - 1; i >= 0; i--) {
				this.objCustomer = ((Customer) this.lstCustomer.get(i));

				if ((this.objCustomer == null) || (this.objCustomer.getlId().longValue() <= 0L)) {
					this.lstCustomer.remove(i);
				} else {
					if (!StringUtils.isEmpty(this.strCustomerName)) {
						if (!this.objCustomer.getStrSname().contains(this.strCustomerName)) {
							this.lstCustomer.remove(i);
							continue;
						}
					}
					if (!StringUtils.isEmpty(this.strCustomerQQ)) {
						if (!this.objCustomer.getStrQQ().contains(this.strCustomerQQ)) {
							this.lstCustomer.remove(i);
							continue;
						}
					}
					if (!StringUtils.isEmpty(this.strParentCustomerQQ)) {
						if ((this.objCustomer.getObjParentCustomer() == null) || (!this.objCustomer.getObjParentCustomer().getStrQQ().contains(this.strParentCustomerQQ))) {
							this.lstCustomer.remove(i);
						}
					}
				}
			}
		}

		Pagination objPagination = new Pagination();

		if ((nPageSize == null) || (nPageSize.length() <= 0)) {
			nPageSize = "20";
		}
		if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
			nCurrentPage = "1";
		}
		objPagination.setPageNo(Integer.parseInt(nCurrentPage));
		objPagination.setPageSize(Integer.parseInt(nPageSize));

		objPagination.setTotal(this.lstCustomer == null ? 0 : this.lstCustomer.size());
		objPagination.setRowsSimple(this.lstCustomer);
		objJsonConfig.setExcludes(new String[] { "lstEmail", "lstProject", "objParentCustomer", "lstChildrenCustomer", "objCustomerType" });
		toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		return "customerList";
	}

	public void loadAllCustomerListOld() throws IOException {
		JsonConfig objJsonConfig = new JsonConfig();
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");

		if (!commonValidateUsers()) {
			toWeb(JSONObject.fromObject(new Pagination(2)).toString());
			logger.error("Sessoin 失效，请重新登录！");
			return;
		}

		this.lstCustomer = new ArrayList((Collection) this.objCustomerService.listByHql("From Customer"));
		if ((this.lstCustomer != null) && (this.lstCustomer.size() > 0)) {
			for (int i = this.lstCustomer.size() - 1; i >= 0; i--) {
				this.objCustomer = ((Customer) this.lstCustomer.get(i));

				if ((this.objCustomer == null) || (this.objCustomer.getlId().longValue() <= 0L)) {
					this.lstCustomer.remove(i);
				} else {
					if (!StringUtils.isEmpty(this.strCustomerName)) {
						if (!this.objCustomer.getStrSname().contains(this.strCustomerName)) {
							this.lstCustomer.remove(i);
							continue;
						}
					}
					if (!StringUtils.isEmpty(this.strCustomerQQ)) {
						if (!this.objCustomer.getStrQQ().contains(this.strCustomerQQ)) {
							this.lstCustomer.remove(i);
							continue;
						}
					}
					if (!StringUtils.isEmpty(this.strParentCustomerQQ)) {
						if ((this.objCustomer.getObjParentCustomer() == null) || (!this.objCustomer.getObjParentCustomer().getStrQQ().contains(this.strParentCustomerQQ))) {
							this.lstCustomer.remove(i);
						}
					}
				}
			}
		}

		Pagination objPagination = new Pagination();

		if ((nPageSize == null) || (nPageSize.length() <= 0)) {
			nPageSize = "20";
		}
		if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
			nCurrentPage = "1";
		}
		objPagination.setPageNo(Integer.parseInt(nCurrentPage));
		objPagination.setPageSize(Integer.parseInt(nPageSize));

		objPagination.setTotal(this.lstCustomer == null ? 0 : this.lstCustomer.size());
		objPagination.setRowsSimple(this.lstCustomer);
		objJsonConfig.setExcludes(new String[] { "lstEmail", "lstProject", "objParentCustomer", "lstChildrenCustomer", "objCustomerType" });
		toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
	}
	
	public void loadAllCustomerList() throws IOException {
		JsonConfig objJsonConfig = new JsonConfig();
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");
		String strSearchSQL = "";
		Pagination<Customer> objPagination = new Pagination<Customer>();
		
		if (!commonValidateUsers()) {
			toWeb(JSONObject.fromObject(new Pagination(2)).toString());
			logger.error("Sessoin 失效，请重新登录！");
			return;
		}

		strSearchSQL = " SELECT a.* FROM customer a,customer_project b,users_project c WHERE a.ID=b.CID AND b.PID=c.PID AND c.UID='"+ objUsers.getlId() +"'";
		if (!StringUtils.isEmpty(this.strCustomerName)) {
			strSearchSQL += " and a.SName='"+this.strCustomerName+"'";
		}
		if (!StringUtils.isEmpty(this.strCustomerQQ)) {
			strSearchSQL += " and a.qq='"+this.strCustomerQQ+"'";
		}
//		if (!StringUtils.isEmpty(this.strParentCustomerQQ)) {
//			strSearchSQL += " and a.SName='"+this.strParentCustomerQQ+"'";
//		}
		
		if ((nPageSize == null) || (nPageSize.length() <= 0)) {
			nPageSize = "20";
		}
		if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
			nCurrentPage = "1";
		}
		objPagination.setPageNo(Integer.parseInt(nCurrentPage));
		objPagination.setPageSize(Integer.parseInt(nPageSize));
		this.objCustomerService.listBySQL(objPagination, strSearchSQL);
//		objPagination.setTotal(this.lstCustomer == null ? 0 : this.lstCustomer.size());
//		objPagination.setRowsSimple(this.lstCustomer);
		objJsonConfig.setIgnoreDefaultExcludes(false);
		objJsonConfig.setExcludes(new String[] { "lstEmail", "lstProject", "objParentCustomer", "lstChildrenCustomer", "objCustomerType" });
		toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
	}

	public String customerDetial() {
		ProFiledInfo objProFiledInfo = new ProFiledInfo();

		if ((this.lCustomerID == null) || (this.lCustomerID.longValue() <= 0L)) {
			setErrorText("对不起没有获取到客户信息");
			return "commonError";
		}
		this.objCustomer = ((Customer) this.objCustomerService.getById(this.lCustomerID));
		if (this.objCustomer == null) {
			setErrorText("对不起无法获取客户信息");
			return "commonError";
		}

		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			if (commonValidateCustomer()) {
				this.lstProject = new ArrayList(this.objCustomer.getLstProject());
				if ((this.lstProject == null) && (this.lstProject.size() <= 0)) {
					setErrorText("对不起没有获取到项目信息，无法进行查看操作");
					return "commonError";
				}
				this.objProject = ((Project) this.lstProject.get(0));
			}

		} else {
			this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		}

		if (this.objProject == null) {
			setErrorText("对不起当前项目不合法");
			return "commonError";
		}

		objProFiledInfo.setlPid(this.objProject.getlId());
		objProFiledInfo.setnStatus(1);
		this.lstProFiledInfo = this.objProFiledInfoService.listByProps(objProFiledInfo, "lPid,nStatus", Order.asc("nSequence"));
		objProFiledInfo.setlPid(this.objProject.getlId());
		objProFiledInfo.setnStatus(1);
		objProFiledInfo.setnIsModify(1);
		this.lstProFiledInfo = this.objProFiledInfoService.listByProps(objProFiledInfo, "lPid,nStatus,nIsModify", Order.asc("nSequence"));
		if ((this.lstProFiledInfo == null) || (this.lstProFiledInfo.size() <= 0)) {
			setErrorText("对不起当前项目没有设置用户字段请先设置！");
			return "commonError";
		}

		if (this.nType == 2) {
			if ((this.nIsDownCustomer != null) && (this.nIsDownCustomer.intValue() == 1)) {
				return "customerDownUpdate";
			}
			this.lstCustomerType = new ArrayList(this.objProject.getLstCustomerType());
			return "customerUpdate";
		}

		if ((this.nIsDownCustomer != null) && (this.nIsDownCustomer.intValue() == 1)) {
			return "customerDownDetial";
		}
		return "customerDetial";
	}

	public void customerUpdate() throws IOException {
		if ((this.objCustomer == null) || (this.objCustomer.getlId().longValue() <= 0L)) {
			toWeb("对不起没有获取到客户信息");
			return;
		}
		this.lCustomerID = this.objCustomer.getlId();
		Customer objCustomerFromDB = (Customer) this.objCustomerService.getById(this.lCustomerID);
		if (objCustomerFromDB == null) {
			toWeb("对不起无法获取客户信息");
			return;
		}

		if ((this.lCustomerTypeID != null) && (this.lCustomerTypeID.longValue() > 0L)) {
			CustomerType objCustomerType = (CustomerType) this.objCustomerTypeService.getById(this.lCustomerTypeID);

			if (objCustomerType != null) {
				objCustomerFromDB.setObjCustomerType(objCustomerType);
			}
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSname())) {
			objCustomerFromDB.setStrSname(this.objCustomer.getStrSname());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrQQ())) {
			objCustomerFromDB.setStrQQ(this.objCustomer.getStrQQ());
		}

		if (!StringUtils.isEmpty(this.objCustomer.getStrSnamePY())) {
			objCustomerFromDB.setStrSnamePY(this.objCustomer.getStrSnamePY());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrIde())) {
			objCustomerFromDB.setStrIde(this.objCustomer.getStrIde());
		}
		if (this.objCustomer.getnIdeType() > 0) {
			objCustomerFromDB.setnIdeType(this.objCustomer.getnIdeType());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSbrithday())) {
			objCustomerFromDB.setStrSbrithday(this.objCustomer.getStrSbrithday());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrGuoJi())) {
			objCustomerFromDB.setStrGuoJi(this.objCustomer.getStrGuoJi());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrNation())) {
			objCustomerFromDB.setStrNation(this.objCustomer.getStrNation());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSdegree())) {
			objCustomerFromDB.setStrSdegree(this.objCustomer.getStrSdegree());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSemail())) {
			objCustomerFromDB.setStrSemail(this.objCustomer.getStrSemail());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrStel())) {
			objCustomerFromDB.setStrStel(this.objCustomer.getStrStel());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSPhone())) {
			objCustomerFromDB.setStrSPhone(this.objCustomer.getStrSPhone());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSWork())) {
			objCustomerFromDB.setStrSWork(this.objCustomer.getStrSWork());
		}
		if (this.objCustomer.getnSworky() > 0) {
			objCustomerFromDB.setnSworky(this.objCustomer.getnSworky());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSWorkUnit())) {
			objCustomerFromDB.setStrSWorkUnit(this.objCustomer.getStrSWorkUnit());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSAddress())) {
			objCustomerFromDB.setStrSAddress(this.objCustomer.getStrSAddress());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSPic())) {
			objCustomerFromDB.setStrSPic(this.objCustomer.getStrSPic());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrLiticalStatus())) {
			objCustomerFromDB.setStrLiticalStatus(this.objCustomer.getStrLiticalStatus());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSex())) {
			objCustomerFromDB.setStrSex(this.objCustomer.getStrSex());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSArea())) {
			objCustomerFromDB.setStrSArea(this.objCustomer.getStrSArea());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrSChuanzhen())) {
			objCustomerFromDB.setStrSChuanzhen(this.objCustomer.getStrSChuanzhen());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrJiGuan())) {
			objCustomerFromDB.setStrJiGuan(this.objCustomer.getStrJiGuan());
		}
		if (this.objCustomer.getnHunYinStatus() > 0) {
			objCustomerFromDB.setnHunYinStatus(this.objCustomer.getnHunYinStatus());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRemarks())) {
			objCustomerFromDB.setStrRemarks(this.objCustomer.getStrRemarks());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrYWName())) {
			objCustomerFromDB.setStrYWName(this.objCustomer.getStrYWName());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrInterExperience())) {
			objCustomerFromDB.setStrInterExperience(this.objCustomer.getStrInterExperience());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrTarget())) {
			objCustomerFromDB.setStrTarget(this.objCustomer.getStrTarget());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrPassword())) {
			objCustomerFromDB.setStrPassword(this.objCustomer.getStrPassword());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom1())) {
			objCustomerFromDB.setStrRegCustom1(this.objCustomer.getStrRegCustom1());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom2())) {
			objCustomerFromDB.setStrRegCustom2(this.objCustomer.getStrRegCustom2());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom3())) {
			objCustomerFromDB.setStrRegCustom3(this.objCustomer.getStrRegCustom3());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom4())) {
			objCustomerFromDB.setStrRegCustom4(this.objCustomer.getStrRegCustom4());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom5())) {
			objCustomerFromDB.setStrRegCustom5(this.objCustomer.getStrRegCustom5());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom6())) {
			objCustomerFromDB.setStrRegCustom6(this.objCustomer.getStrRegCustom6());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom7())) {
			objCustomerFromDB.setStrRegCustom7(this.objCustomer.getStrRegCustom7());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom8())) {
			objCustomerFromDB.setStrRegCustom8(this.objCustomer.getStrRegCustom8());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom9())) {
			objCustomerFromDB.setStrRegCustom9(this.objCustomer.getStrRegCustom9());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom10())) {
			objCustomerFromDB.setStrRegCustom10(this.objCustomer.getStrRegCustom10());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom11())) {
			objCustomerFromDB.setStrRegCustom11(this.objCustomer.getStrRegCustom11());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom12())) {
			objCustomerFromDB.setStrRegCustom12(this.objCustomer.getStrRegCustom12());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom13())) {
			objCustomerFromDB.setStrRegCustom13(this.objCustomer.getStrRegCustom13());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom14())) {
			objCustomerFromDB.setStrRegCustom14(this.objCustomer.getStrRegCustom14());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrRegCustom15())) {
			objCustomerFromDB.setStrRegCustom15(this.objCustomer.getStrRegCustom15());
		}

		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver1())) {
			objCustomerFromDB.setStrReserver1(this.objCustomer.getStrReserver1());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver2())) {
			objCustomerFromDB.setStrReserver2(this.objCustomer.getStrReserver2());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver3())) {
			objCustomerFromDB.setStrReserver3(this.objCustomer.getStrReserver3());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver4())) {
			objCustomerFromDB.setStrReserver4(this.objCustomer.getStrReserver4());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver5())) {
			objCustomerFromDB.setStrReserver5(this.objCustomer.getStrReserver5());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver6())) {
			objCustomerFromDB.setStrReserver6(this.objCustomer.getStrReserver6());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver7())) {
			objCustomerFromDB.setStrReserver7(this.objCustomer.getStrReserver7());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver8())) {
			objCustomerFromDB.setStrReserver8(this.objCustomer.getStrReserver8());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver9())) {
			objCustomerFromDB.setStrReserver9(this.objCustomer.getStrReserver9());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver10())) {
			objCustomerFromDB.setStrReserver10(this.objCustomer.getStrReserver10());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver11())) {
			objCustomerFromDB.setStrReserver11(this.objCustomer.getStrReserver11());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver12())) {
			objCustomerFromDB.setStrReserver12(this.objCustomer.getStrReserver12());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver13())) {
			objCustomerFromDB.setStrReserver13(this.objCustomer.getStrReserver13());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver14())) {
			objCustomerFromDB.setStrReserver14(this.objCustomer.getStrReserver14());
		}
		if (!StringUtils.isEmpty(this.objCustomer.getStrReserver15())) {
			objCustomerFromDB.setStrReserver15(this.objCustomer.getStrReserver15());
		}

		if (!this.objCustomerService.update(objCustomerFromDB)) {
			toWeb("出现异常！保存失败！");
			return;
		}

		toWeb("success");
	}

	public String customerSearch() {
		String strHQL = "From Customer";

		if (!commonValidateUsers()) {
			return "commonError";
		}

		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			setErrorText("对不起当前项目不合法");
			return "commonError";
		}

		this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		if (this.objProject == null) {
			setErrorText("对不起当前项目不合法");
			return "commonError";
		}

		strHQL = strHQL + " where objProject.lId=" + this.lProjectID;
		if (!StringUtils.isEmpty(this.strCustomerName)) {
			strHQL = strHQL + " and strSname like '%" + this.strCustomerName + "%'";
		}

		if (!StringUtils.isEmpty(this.strCustomerType)) {
			strHQL = strHQL + " and objCustomerType.strName ='" + this.strCustomerType + "'";
		}

		if (!StringUtils.isEmpty(this.strCustomerQQ)) {
			strHQL = strHQL + " and strQQ like '%" + this.strCustomerQQ + "%'";
		}

		logger.info("开始搜索客户名称包含【" + this.strCustomerName + "】的，客户类型为【" + this.strCustomerType + "】,QQ包含【" + this.strCustomerQQ + "】的项目");

		this.lstCustomer = this.objCustomerService.listByHql(strHQL);
		this.lstCustomerType = new ArrayList(this.objProject.getLstCustomerType());

		return "customerList";
	}

	public void customerDelete() throws IOException {
		String[] arrayCustomerID = getRequest().getParameterValues("customerID");

		if ((arrayCustomerID == null) || (arrayCustomerID.length <= 0)) {
			logger.error("无法获取您要删除的客户！删除失败");
			toWeb("无法获取您要删除的客户！删除失败");
			return;
		}

		for (int i = 0; i < arrayCustomerID.length; i++) {
			List lstDwonCustomer;
			Customer objCustomer;
			try {
				objCustomer = (Customer) this.objCustomerService.getById(Long.valueOf(Long.parseLong(arrayCustomerID[i])));
				lstDwonCustomer = new ArrayList(objCustomer.getLstChildrenCustomer());
			} catch (Exception e) {
				logger.error("获取客户信息失败，当前id可能不合法，当前传递的参数id为：" + arrayCustomerID[i]);
				toWeb("获取客户信息失败，请重试!");
				return;
			}

			if ((lstDwonCustomer != null) && (lstDwonCustomer.size() > 0)) {
				logger.error("删除客户失败，当前客户下存在子客户，请先删除子客户才能删除此客户！当前客户id为：" + objCustomer.getlId());
				toWeb("删除客户失败，【" + objCustomer.getStrSname() + "】下存在子客户，请先删除子客户才能删除此客户！");
				return;
			}
			if (objCustomer != null) {
				List lstEmail = new ArrayList(objCustomer.getLstEmail());

				if ((lstEmail != null) && (lstEmail.size() > 0)) {
					for (int j = 0; j < lstEmail.size(); j++) {
						Email objEmail = (Email) lstEmail.get(j);

						if ((objEmail != null) && (objEmail.getLId().longValue() >= 0L)) {
							objEmail.getLstCustomer().remove(objCustomer);
							if (!this.objEmailService.update(objEmail)) {
								logger.error("邮件更新失败了，邮件id为：" + objEmail.getLId());
							}
						}
					}

				}

				try {
					if (!this.objCustomerService.delete(objCustomer)) {
						logger.error("客户【" + objCustomer.getStrSname() + "】--删除失败");
						toWeb("客户【" + objCustomer.getStrSname() + "】删除失败");
						return;
					}
				} catch (Exception e) {
					logger.error("删除失败，出现异常！", e);
					toWeb("删除失败，出现异常！");
					return;
				}
				logger.info("客户类型【" + objCustomer.getStrSname() + "】id为【" + objCustomer.getlId() + "】删除成功");
			}
		}

		toWeb("success");
	}

	public void customerActive() throws IOException {
		String[] arrayCustomerID = getRequest().getParameterValues("lCustomerID");
		String strType = getRequest().getParameter("nType");

		if ((StringUtils.isEmpty(strType)) || ((!strType.equals("1")) && (!strType.equals("0")))) {
			toWeb("出现错误，激活类型不正确");
			return;
		}

		if ((arrayCustomerID == null) || (arrayCustomerID.length <= 0)) {
			logger.error("无法获取您要删除的客户！删除失败");
			toWeb("无法获取您要删除的客户！删除失败");
			return;
		}

		for (int i = 0; i < arrayCustomerID.length; i++) {
			Customer objCustomer = (Customer) this.objCustomerService.getById(Long.valueOf(Long.parseLong(arrayCustomerID[i])));

			objCustomer.setnIsActive(Integer.parseInt(strType));
			if (objCustomer != null) {
				if (!this.objCustomerService.update(objCustomer)) {
					logger.error("客户【" + objCustomer.getStrSname() + "】--修改激活状态失败");
					toWeb("客户【" + objCustomer.getStrSname() + "】修改激活状态失败");
					return;
				}
				logger.info("客户类型【" + objCustomer.getStrSname() + "】id为【" + objCustomer.getlId() + "】激活状态修改为【" + strType + "】更新成功");
			}
		}

		toWeb("success");
	}

	public void customerModifyPassowrd() throws IOException {
		if (!commonValidateCustomer()) {
			toWeb("Session 已失效，请重新登录！");
			return;
		}
		if (StringUtils.isEmpty(this.strOldPassword)) {
			toWeb("原始密码不能为空");
			return;
		}
		if ((StringUtils.isEmpty(this.strPassword)) || (StringUtils.isEmpty(this.strRePassword))) {
			toWeb("新密码不能为空");
			return;
		}
		if (!this.strPassword.equals(this.strRePassword)) {
			toWeb("您两次输入的密码不一致！");
			return;
		}

		if (!EncryptAndDecryptUtils.md5(this.strOldPassword).equals(this.objCustomer.getStrPassword())) {
			toWeb("您的旧密码填写错误！您不能修改密码！");
			return;
		}

		logger.info("旧密码为：" + this.strOldPassword + "--新密码为：" + this.strPassword);
		this.objCustomer.setStrPassword(EncryptAndDecryptUtils.md5(this.strPassword));
		if (!this.objCustomerService.update(this.objCustomer)) {
			toWeb("出现异常，修改失败！");
			return;
		}

		toWeb("修改成功！");
	}

	/**
	 * 推广地址制作。
	 * 
	 * @return
	 */
	public String tuiGuangAddress()
	{
		if (!commonValidateCustomer()) {
			setErrorText("Session 已失效，请重新登录 !");
			return "commonError";
		}
		try {
			this.lstProject = new ArrayList(this.objCustomer.getLstProject());
		} catch (Exception e) {
			logger.info("系统异常", e);
		}
		
		return "tuiGuangAddress";
	}
	
	private boolean commonValidateUsers() {
		if (StringUtils.isBlank(getUserIdFromSession())) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		try {
			Long.parseLong(getUserIdFromSession());
		} catch (Exception e) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		this.objUsers = ((Users) this.objUsersService.getById(Long.valueOf(Long.parseLong(getUserIdFromSession()))));
		if ((this.objUsers == null) || (this.objUsers.getlId().longValue() <= 0L)) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		return true;
	}

	public void promotePrivilege() throws IOException {
		String[] arrayCustomerID = getRequest().getParameterValues("customerID");

		if ((arrayCustomerID == null) || (arrayCustomerID.length <= 0)) {
			logger.error("客户ID不合法，客户参数id为：" + arrayCustomerID);
			toWeb("获取客户信息失败！");
			return;
		}
		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			logger.error("项目ID不合法，项目参数id为：" + this.lProjectID);
			toWeb("获取项目信息失败！");
			return;
		}

		try {
			this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
			if (this.objProject == null) {
				logger.error("获取项目信息出错！请刷新重试！当前项目参数id为：" + this.lProjectID);
				toWeb("获取项目信息出错，请刷新重试！");
				return;
			}

			for (String strCustomerID : arrayCustomerID) {
				this.objCustomer = ((Customer) this.objCustomerService.getById(Long.valueOf(Long.parseLong(strCustomerID))));
				if (this.objCustomer == null) {
					logger.error("获取客户信息出错！请刷新重试！当前客户参数id为：" + strCustomerID);
					toWeb("获取客户信息出错，请刷新重试！");
					return;
				}

				if (this.objCustomer.getnCurrentLevel() == 1) {
					logger.info("当前用户为普通用户可以进行升级！");
					this.objCustomer.setnCurrentLevel(2);
					if (!this.objCustomerService.update(this.objCustomer)) {
						logger.error("升级失败，请重试！");
						toWeb("升级失败，请重试！");
						return;
					}
					logger.info("用户【" + this.objCustomer.getStrSname() + "】升级成功！");

					boolean bStatus = MailClient.sendHtmlMail(new MailConfig("dobetter_jw@163.com", this.objCustomer.getStrQQ() + "@qq.com", true, this.objProject.getStrPname(), "恭喜您已经升级为多项目管理员角色！"));
					logger.info("邮件发送状态：" + bStatus);
				} else {
					if (this.objCustomer.getnCurrentLevel() == 2) {
						logger.error("当前客户已经升级！当前客户参数id为：" + strCustomerID);
						toWeb("当前客户已经升级,请不要重复操作！");
						return;
					}

					toWeb("未知的客户类型！");
					return;
				}
			}

			toWeb("用户升级成功！");
		} catch (Exception e) {
			logger.error("获取客户信息出错！请刷新重试！当前客户参数id为：" + arrayCustomerID, e);
			toWeb("获取客户信息出错！请刷新重试！");
			return;
		}
	}

	public void projectAuthorization() throws IOException {
		String[] arrayCustomerID = getRequest().getParameterValues("customerID");
		String[] arrayProjectID = getRequest().getParameterValues("projectID");

		if (!commonValidateUsers()) {
			toWeb("Session 失效，请重新登录");
			return;
		}

		toWeb(this.objCustomerService.projectAuthorization(arrayCustomerID, arrayProjectID));
	}

	public void cancleProjectAuthorization() throws IOException {
		String[] arrayCustomerID = getRequest().getParameterValues("customerID");
		String[] arrayProjectID = getRequest().getParameterValues("projectID");

		if (!commonValidateUsers()) {
			toWeb("Session 失效，请重新登录");
			return;
		}

		toWeb(this.objCustomerService.cancleProjectAuthorization(arrayCustomerID, arrayProjectID));
	}

	public void batchExportExcel() throws IOException {
		getResponse().setContentType("application/vnd.ms-excel");
		Vector objVector = new Vector();

		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");

		SimpleExpression objConditionOne = null;
		SimpleExpression objConditionTwo = null;
		SimpleExpression objConditionThree = null;
		SimpleExpression objConditionFour = null;

		String[] fieldName = { "客户名称", "客户负责人QQ", "客户类型", "注册时间", "手机号码", "QQ号码", "用户等级", "是否激活" };
		String sheetName = "客户信息";

		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			setErrorText("对不起当前项目不合法，不能进行注册，请核对网址是否正确！");
			toWeb(null);
			return;
		}

		try {
			if ((!StringUtils.isEmpty(this.strCustomerTypeID)) && (Long.parseLong(this.strCustomerTypeID) > 0L)) {
				objConditionOne = Restrictions.eq("objCustomerType.lId", Long.valueOf(Long.parseLong(this.strCustomerTypeID)));
			}
			if (!StringUtils.isEmpty(this.strCustomerQQ)) {
				objConditionTwo = Restrictions.like("strQQ", "%" + this.strCustomerQQ + "%");
			}
			if (!StringUtils.isEmpty(this.strCustomerName)) {
				objConditionThree = Restrictions.like("strSname", "%" + this.strCustomerName + "%");
			}
			if (!StringUtils.isEmpty(this.strParentCustomerQQ)) {
				Customer objParentCustomer = (Customer) this.objCustomerService.getByHql("from Customer where strQQ='" + this.strParentCustomerQQ + "'");

				if (objParentCustomer != null) {
					objConditionFour = Restrictions.eq("objParentCustomer.lId", objParentCustomer.getlId());
				} else {
					objConditionFour = Restrictions.eq("objParentCustomer.lId", Long.valueOf(-1L));
				}
			}

			logger.info("开始搜索客户名称包含【" + this.strCustomerName + "】的，客户类型为【" + this.strCustomerTypeID + "】,QQ包含【" + this.strCustomerQQ + "】的项目");
			Pagination objPagination = new Pagination();
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}

			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			Criterion objSQLCondition = Restrictions.sqlRestriction("id in(select cid from customer_project where pid=" + this.lProjectID + ")");
			this.objCustomerService.listCustomerByMultiCondition(objPagination, new SearchCondition(objSQLCondition, objConditionOne, objConditionTwo, objConditionThree, objConditionFour), null);

			if ((objPagination.getRows() != null) && (objPagination.getRows().size() > 0)) {
				for (int i = 0; i < objPagination.getRows().size(); i++) {
					Customer objTempCustomer = (Customer) objPagination.getRows().get(i);

					Vector vector = new Vector();
					vector.add(objTempCustomer.getStrSname());
					vector.add(objTempCustomer.getStrParentCustomerQQ());
					vector.add(objTempCustomer.getStrCustomerTypeName());
					vector.add(objTempCustomer.getStrRegistTime());
					vector.add(objTempCustomer.getStrSPhone());
					vector.add(objTempCustomer.getStrQQ());
					vector.add(objTempCustomer.getStrCurrentLevel());
					vector.add(objTempCustomer.getStrIsActive());
					objVector.add(vector);
				}
			}
			ExcelUtils.getExcel(objVector, fieldName, sheetName, getResponse().getOutputStream());
			getResponse().getOutputStream().flush();
			getResponse().getOutputStream().close();
		} catch (Exception e) {
			logger.info("系统异常", e);
		}
	}

	// public void createShortUrl() throws Exception {
	// if ((this.myUrl == null) || (this.myUrl.equals(""))) {
	// toWeb("error@@'请输入您要生成短网址的连接！！！'");
	// return;
	// }
	//
	// try {
	// Map paramMap = new HashMap();
	//
	// paramMap.put("url", this.myUrl);
	//
	// String strContent = HttpUtils.URLGetForContent("http://955.cc/",
	// paramMap);
	//
	// strContent = strContent.substring(strContent.indexOf("<TEXTAREA
	// class=\"url\""), strContent.indexOf("</textarea>"));
	// strContent = strContent.substring(strContent.indexOf("http://"));
	// logger.info("返回订阅信息：" + strContent);
	// toWeb("success@@" + strContent);
	// } catch (Exception e) {
	// logger.error("出现异常", e);
	// toWeb("error@@'出现异常'");
	// }
	// }

	private boolean commonValidateCustomer() {
		if (StringUtils.isBlank(getCustomerIdFromSession())) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		try {
			Long.parseLong(getCustomerIdFromSession());
		} catch (Exception e) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		this.objCustomer = ((Customer) this.objCustomerService.getById(Long.valueOf(Long.parseLong(getCustomerIdFromSession()))));
		if ((this.objCustomer == null) || (this.objCustomer.getlId().longValue() <= 0L)) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		return true;
	}

	public void resetPwd() throws IOException {
		String[] arrayCustomerID = getRequest().getParameterValues("customerID");

		toWeb(this.objCustomerService.resetPwd(arrayCustomerID));
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

	public Project getObjProject() {
		return this.objProject;
	}

	public void setObjProject(Project objProject) {
		this.objProject = objProject;
	}

	public List<ProFiledInfo> getLstProFiledInfo() {
		return this.lstProFiledInfo;
	}

	public void setLstProFiledInfo(List<ProFiledInfo> lstProFiledInfo) {
		this.lstProFiledInfo = lstProFiledInfo;
	}

	public Customer getObjCustomer() {
		return this.objCustomer;
	}

	public void setObjCustomer(Customer objCustomer) {
		this.objCustomer = objCustomer;
	}

	public List<Customer> getLstCustomer() {
		return this.lstCustomer;
	}

	public void setLstCustomer(List<Customer> lstCustomer) {
		this.lstCustomer = lstCustomer;
	}

	public List<Project> getLstProject() {
		return this.lstProject;
	}

	public void setLstProject(List<Project> lstProject) {
		this.lstProject = lstProject;
	}

	public Users getObjUsers() {
		return this.objUsers;
	}

	public void setObjUsers(Users objUsers) {
		this.objUsers = objUsers;
	}

	public Long getlCustomerID() {
		return this.lCustomerID;
	}

	public void setlCustomerID(Long lCustomerID) {
		this.lCustomerID = lCustomerID;
	}

	public Long getLCustomerID() {
		return this.lCustomerID;
	}

	public void setLCustomerID(Long lCustomerID) {
		this.lCustomerID = lCustomerID;
	}

	public String getStrCustomerName() {
		return this.strCustomerName;
	}

	public void setStrCustomerName(String strCustomerName) {
		this.strCustomerName = strCustomerName;
	}

	public List<CustomerType> getLstCustomerType() {
		return this.lstCustomerType;
	}

	public void setLstCustomerType(List<CustomerType> lstCustomerType) {
		this.lstCustomerType = lstCustomerType;
	}

	public String getStrCustomerType() {
		return this.strCustomerType;
	}

	public void setStrCustomerType(String strCustomerType) {
		this.strCustomerType = strCustomerType;
	}

	public int getnType() {
		return this.nType;
	}

	public void setnType(int nType) {
		this.nType = nType;
	}

	public int getNType() {
		return this.nType;
	}

	public void setNType(int nType) {
		this.nType = nType;
	}

	public Long getlCustomerTypeID() {
		return this.lCustomerTypeID;
	}

	public void setlCustomerTypeID(Long lCustomerTypeID) {
		this.lCustomerTypeID = lCustomerTypeID;
	}

	public Long getLCustomerTypeID() {
		return this.lCustomerTypeID;
	}

	public void setLCustomerTypeID(Long lCustomerTypeID) {
		this.lCustomerTypeID = lCustomerTypeID;
	}

	public String getStrVerifycode() {
		return this.strVerifycode;
	}

	public void setStrVerifycode(String strVerifycode) {
		this.strVerifycode = strVerifycode;
	}

	public String getStrPassword() {
		return this.strPassword;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

	public String getStrProjectName() {
		return this.strProjectName;
	}

	public void setStrProjectName(String strProjectName) {
		this.strProjectName = strProjectName;
	}

	public String getStrCustomerQQ() {
		return this.strCustomerQQ;
	}

	public void setStrCustomerQQ(String strCustomerQQ) {
		this.strCustomerQQ = strCustomerQQ;
	}

	public String getStrCustomerTypeID() {
		return this.strCustomerTypeID;
	}

	public void setStrCustomerTypeID(String strCustomerTypeID) {
		this.strCustomerTypeID = strCustomerTypeID;
	}

	public Long getlUsersID() {
		return this.lUsersID;
	}

	public void setlUsersID(Long lUsersID) {
		this.lUsersID = lUsersID;
	}

	public Long getLUsersID() {
		return this.lUsersID;
	}

	public void setLUsersID(Long lUsersID) {
		this.lUsersID = lUsersID;
	}

	public Integer getnIsDownCustomer() {
		return this.nIsDownCustomer;
	}

	public void setnIsDownCustomer(Integer nIsDownCustomer) {
		this.nIsDownCustomer = nIsDownCustomer;
	}

	public Integer getNIsDownCustomer() {
		return this.nIsDownCustomer;
	}

	public void setNIsDownCustomer(Integer nIsDownCustomer) {
		this.nIsDownCustomer = nIsDownCustomer;
	}

	public String getStrRePassword() {
		return this.strRePassword;
	}

	public void setStrRePassword(String strRePassword) {
		this.strRePassword = strRePassword;
	}

	public String getStrOldPassword() {
		return this.strOldPassword;
	}

	public void setStrOldPassword(String strOldPassword) {
		this.strOldPassword = strOldPassword;
	}

	public String getStrParentCustomerQQ() {
		return this.strParentCustomerQQ;
	}

	public void setStrParentCustomerQQ(String strParentCustomerQQ) {
		this.strParentCustomerQQ = strParentCustomerQQ;
	}

	public String getMyUrl() {
		return this.myUrl;
	}

	public void setMyUrl(String myUrl) {
		this.myUrl = myUrl;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}


	public String getStrNickName() {
		return strNickName;
	}


	public void setStrNickName(String strNickName) {
		this.strNickName = strNickName;
	}
}