package com.manage.crm.action;

import com.manage.crm.entity.Customer;
import com.manage.crm.entity.News;
import com.manage.crm.entity.ProFiledInfo;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.Users;
import com.manage.crm.service.CustomerService;
import com.manage.crm.service.NewsService;
import com.manage.crm.service.ProFiledInfoService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.UsersService;
import com.manage.crm.util.Pagination;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javacommon.core.base.BaseStruts2Action;
import javacommon.util.SearchCondition;
import javacommon.util.StringUtils;
import javacommon.util.encrypt.EncryptAndDecryptUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("usersManageAction")
@Scope("prototype")
public class UsersManageAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(UsersManageAtion.class);
	private static final String USERS_INDEX = "usersIndex";
	private static final String USER_LOGIN = "userLogin";
	private static final String USERS_LIST = "usersList";
	private static final String USERS_REGISTER = "usersRegister";
	private static final String USERS_PROJECT_LIST = "usersProjectList";
	private static final String USERS_REGISTER_SUCC = "usersRegisterSucc";
	private Long lUserID;

	@Resource(name = "proFiledInfoService")
	private ProFiledInfoService objProFiledInfoService;

	@Resource(name = "usersService")
	private UsersService objUsersService;

	@Resource(name = "customerService")
	private CustomerService objCustomerService;

	@Resource(name = "newsService")
	private NewsService objNewsService;
	private List<ProFiledInfo> lstProFiledInfo;
	private List<Project> lstProject;
	private List<Customer> lstCustomer;
	List<News> lstNews;
	private Project objProject;
	private Customer objCustomer;
	private Users objUsers;
	private Long lProjectID;

	@Resource(name = "projectService")
	private ProjectService objProjectService;
	String strPassword;
	String strRePassword;
	String strOldPassword;

	public String usersIndex() {
		if (StringUtils.isBlank(getUserIdFromSession())) {
			addFieldError("msg", "Session 已失效，请重新登录！");
			return "userLogin";
		}

		try {
			this.objUsers = ((Users) this.objUsersService.getById(Long.valueOf(Long.parseLong(getUserIdFromSession()))));

			if (this.objUsers == null) {
				addFieldError("msg", "无法获取用户对象");
				return "userLogin";
			}
		} catch (Exception e) {
			setErrorText("出现异常");
			return "commonError";
		}

		return "usersIndex";
	}

	public String register() {
		ProFiledInfo objProFiledInfo = new ProFiledInfo();

		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			setErrorText("对不起当前项目不合法，不能进行注册，请核对网址是否正确！");
			return "commonError";
		}

		this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		if (this.objProject == null) {
			setErrorText("对不起当前项目不合法，不能进行注册，请核对网址是否正确！");
			return "commonError";
		}

		objProFiledInfo.setlPid(this.lProjectID);
		objProFiledInfo.setnStatus(1);
		this.lstProFiledInfo = this.objProFiledInfoService.listByProps(objProFiledInfo, "lPid,nStatus", Order.asc("nSequence"));

		if ((this.lstProFiledInfo == null) || (this.lstProFiledInfo.size() <= 0)) {
			setErrorText("对不起当前项目没有设置注册字段，您还不能进行注册！");
			return "commonError";
		}

		return "usersRegister";
	}

	public String projectList() {
		if (!commonValidateUsers()) {
			return "commonError";
		}

		this.lstProject = new ArrayList(this.objUsers.getLstProject());
		logger.info("项目数量为：" + (this.lstProject == null ? 0 : this.lstProject.size()));
		return "usersProjectList";
	}

	public String usersList() {
		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			setErrorText("对不起当前项目不合法，不能进行注册，请核对网址是否正确！");
			return "commonError";
		}

		this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		if (this.objProject == null) {
			setErrorText("对不起当前项目不合法，不能进行注册，请核对网址是否正确！");
			return "commonError";
		}

		this.lstCustomer = new ArrayList(this.objProject.getLstCustomer());

		return "usersList";
	}

	private boolean commonValidateUsers() {
		if (StringUtils.isBlank(getUserIdFromSession())) {
			setErrorText("Session 已失效，请重新登录 。");
			return false;
		}

		try {
			Long.parseLong(getUserIdFromSession());
		} catch (Exception e) {
			setErrorText("Session 已失效，请重新登录 。");
			return false;
		}

		this.objUsers = ((Users) this.objUsersService.getById(Long.valueOf(Long.parseLong(getUserIdFromSession()))));
		if ((this.objUsers == null) || (this.objUsers.getlId().longValue() <= 0L)) {
			setErrorText("没有获取到用户对象。");
			return false;
		}

		return true;
	}

	public void usersAdd() throws IOException {
		if (this.objUsers == null) {
			toWeb("没有获取到您的任何信息！申请失败！");
			return;
		}
		if (StringUtils.isEmpty(this.objUsers.getStrLoginName())) {
			toWeb("您的登陆名称不能为空");
		} else if (StringUtils.isEmpty(this.objUsers.getStrPassword())) {
			toWeb("您的登陆密码不能为空");
		} else if (StringUtils.isEmpty(strRePassword)) {
			toWeb("确认密码不能为空");
		}else if (!this.objUsers.getStrPassword().equals(strRePassword)) {
			toWeb("您两次的密码不一致");
		} else if (StringUtils.isEmpty(this.objUsers.getStrPersonPhone())) {
			toWeb("您的手机号码不能为空");
		} else if (StringUtils.isEmpty(this.objUsers.getStrReserver1())) {
			toWeb("您的QQ号码不能为空");
		}else {
			try {
				Users objTempUsers = new Users();

				objTempUsers.setlId(Long.valueOf(-1L));
				this.objUsers.setStrPassword(EncryptAndDecryptUtils.md5(objUsers.getStrPassword()));
				this.objUsers.setObjParentUsers(objTempUsers);
				this.objUsers.setStrName(this.objUsers.getStrLoginName());
				this.objUsers.setnIsActive(Integer.valueOf(1));
				this.objUsers.setnIsLastLevel(Integer.valueOf(-1));
				this.objUsers.setnCurrentLevel(Integer.valueOf(2));
				this.objUsers.setStrLastModifyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				Users tempUsers = (Users) this.objUsersService.getByHql("from Users where strLoginName = '" + this.objUsers.getStrLoginName() + "'");
				if ((tempUsers != null) && (tempUsers.getlId().longValue() > 0L)) {
					toWeb("此登录名已经使用，请换一个进行申请！");
					return;
				}

				toWeb(Boolean.valueOf(this.objUsersService.save(this.objUsers)));
			} catch (Exception e) {
				e.printStackTrace();
				toWeb("出现异常，请稍后重试！");
			}
		}
	}

	public void loadUsersList() throws IOException {
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");
		SimpleExpression objConditionOne = null;

		Pagination objPagination = null;
		JsonConfig objJsonConfig = new JsonConfig();

		if (!commonValidateUsers()) {
			toWeb(JSONObject.fromObject(new Pagination(2)).toString());
			logger.error("Sessoin 失效，请重新登录！");
			return;
		}

		try {
			objConditionOne = Restrictions.ne("nCurrentLevel", Integer.valueOf(1));
			objPagination = new Pagination();
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			this.objUsersService.listByCriteria(objPagination, new SearchCondition(objConditionOne), null);
			objJsonConfig.setExcludes(new String[] { "lstChildUsers", "lstProject", "objParentUsers", "objArea" });
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
			toWeb(JSONObject.fromObject(new Pagination("系统异常！")).toString());
			return;
		}
	}

	public void usersActive() throws IOException {
		String[] arrayUsersID = getRequest().getParameterValues("lUsersID");
		String strType = getRequest().getParameter("nType");

		if ((StringUtils.isEmpty(strType)) || ((!strType.equals("1")) && (!strType.equals("0")))) {
			toWeb("出现错误，激活类型不正确");
			return;
		}

		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		if ((arrayUsersID == null) || (arrayUsersID.length <= 0)) {
			logger.error("无法获取您要操作的商户！删除失败");
			toWeb("无法获取您要操作的商户");
			return;
		}

		for (int i = 0; i < arrayUsersID.length; i++) {
			Users objUsers = (Users) this.objUsersService.getById(Long.valueOf(Long.parseLong(arrayUsersID[i])));

			objUsers.setnIsActive(Integer.valueOf(Integer.parseInt(strType)));
			if (objUsers != null) {
				if (!this.objUsersService.update(objUsers)) {
					logger.error("商户【" + objUsers.getStrName() + "】--修改激活状态失败");
					toWeb("商户【" + objUsers.getStrName() + "】修改激活状态失败");
					return;
				}
				logger.info("商户类型【" + objUsers.getStrName() + "】id为【" + objUsers.getlId() + "】激活状态修改为【" + strType + "】更新成功");
			}
		}

		toWeb("success");
	}

	public void delUsers() throws IOException {
		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		String[] arrayUsersID = getRequest().getParameterValues("usersID");

		if ((arrayUsersID == null) || (arrayUsersID.length <= 0)) {
			toWeb("无法获取您要删除的商户！请稍后重试！");
			return;
		}

		try {
			for (int i = 0; i < arrayUsersID.length; i++) {
				Users objTempUsers = (Users) this.objUsersService.getById(Long.valueOf(Long.parseLong(arrayUsersID[i])));

				if (objTempUsers != null) {
					if (!this.objUsersService.delete(objTempUsers)) {
						toWeb("删除商户失败！请稍后重试！");
						return;
					}
				}
			}

			toWeb("success");
		} catch (Exception e) {
			e.printStackTrace();

			toWeb("出现异常，请稍后重试！");
		}
	}

	public void loadUsersProject() throws IOException {
		Pagination objPagination = null;
		JsonConfig objJsonConfig = new JsonConfig();
		Users objCurrentUsers = null;

		if (!commonValidateUsers()) {
			toWeb(JSONObject.fromObject(new Pagination(2)).toString());
			logger.error("Sessoin 失效，请重新登录！");
			return;
		}
		if ((this.lUserID == null) || (this.lUserID.longValue() <= 0L)) {
			toWeb("用户id无效！请重试！");
			return;
		}
		objCurrentUsers = (Users) this.objUsersService.getById(this.lUserID);
		if (objCurrentUsers == null) {
			toWeb(JSONObject.fromObject(new Pagination("用户id无效")).toString());
			logger.error("用户 id无效");
			return;
		}

		try {
			this.lstProject = new ArrayList(objCurrentUsers.getLstProject());
			objPagination = new Pagination();
			objPagination.setTotal(this.lstProject == null ? 0 : this.lstProject.size());
			objPagination.setRowsSimple(this.lstProject);
			objJsonConfig.setExcludes(new String[] { "objParentProject", "lstChildrenProject", "lstUsers", "lstFiledInfo", "lstCustomer", "lstCustomerType", "lstEmail", "rowsSimple",
					"objCustomerType" });
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
		}
		toWeb(JSONObject.fromObject(new Pagination()).toString());
	}

	public void cancleProjectAuthorization() throws IOException {
		String[] arrayCustomerID = getRequest().getParameterValues("usersID");
		String[] arrayProjectID = getRequest().getParameterValues("projectID");

		if (!commonValidateUsers()) {
			toWeb("Session 失效，请重新登录");
			return;
		}

		toWeb(this.objUsersService.cancleProjectAuthorization(arrayCustomerID, arrayProjectID));
	}

	public void projectAuthorization() throws IOException {
		String strUsersID = getRequest().getParameter("lUsersID");
		String[] arrayProjectID = getRequest().getParameterValues("projectID");

		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		toWeb(this.objUsersService.projectAuthorization(new String[] { strUsersID }, arrayProjectID));
	}

	public void usersModifyPassowrd() throws IOException {
		toWeb(this.objUsersService.passwordModify(getUserIdFromSession(), this.strOldPassword, this.strPassword, this.strRePassword));
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

	public List<News> getLstNews() {
		return this.lstNews;
	}

	public void setLstNews(List<News> lstNews) {
		this.lstNews = lstNews;
	}

	public Long getlUserID() {
		return this.lUserID;
	}

	public void setlUserID(Long lUserID) {
		this.lUserID = lUserID;
	}

	public Long getLUserID() {
		return this.lUserID;
	}

	public void setLUserID(Long lUserID) {
		this.lUserID = lUserID;
	}

	public String getStrPassword() {
		return this.strPassword;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
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
}