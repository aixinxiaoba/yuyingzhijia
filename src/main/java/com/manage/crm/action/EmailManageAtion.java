package com.manage.crm.action;

import com.manage.crm.entity.Customer;
import com.manage.crm.entity.Email;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.Users;
import com.manage.crm.service.CustomerService;
import com.manage.crm.service.EmailService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.UsersService;
import com.manage.crm.util.Pagination;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javacommon.core.Config;
import javacommon.core.base.BaseStruts2Action;
import javacommon.util.StringUtils;
import javacommon.util.mail.MailClient;
import javacommon.util.mail.MailConfig;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("emailManageAction")
@Scope("prototype")
public class EmailManageAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(EmailManageAtion.class);

	@Resource(name = "usersService")
	private UsersService objUsersService;

	@Resource(name = "emailService")
	private EmailService objEmailService;

	@Resource(name = "customerService")
	private CustomerService objCustomerService;

	@Resource(name = "projectService")
	private ProjectService objProjectService;
	private Customer objCustomer;
	private Project objProject;
	private Email objEmail;
	private Users objUsers;
	private List<Email> lstEmail;
	private String strCustomerQQ;
	private String strProjectName;
	private Long lProjectID;
	private Long lEmailID;

	public void emailSend() throws IOException {
		if (!commonValidateUsers()) {
			toWeb("Session 失效，请重新登录！");
			return;
		}

		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			logger.error("对不起，无法获取您的项目项目信息！");
			toWeb("对不起，无法获取您的项目项目信息！");
			return;
		}
		if ((this.lEmailID != null) && (this.lEmailID.longValue() > 0L)) {
			this.objEmail = ((Email) this.objEmailService.getById(this.lEmailID));
		}
		if (this.objEmail == null) {
			toWeb("对不起，我们没有获取到您要保存的信息！");
			logger.error("对不起，我们没有获取到您要保存的信息！");
			return;
		}
		if (StringUtils.isEmpty(this.strCustomerQQ)) {
			toWeb("对不起，没有获取到您要发送的用户信息！");
			logger.error("没有获取到用户的QQ号");
			return;
		}
		String[] arrayCustomerQQ = this.strCustomerQQ.split(";");
		try {
			Set lstCustomer = new HashSet();

			for (int i = 0; i < arrayCustomerQQ.length; i++) {
				boolean flag = false;
				this.objCustomer = ((Customer) this.objCustomerService.getBySql("select * from Customer where id in (select cid from customer_project where pid=" + this.lProjectID + ") and qq='"
						+ arrayCustomerQQ[i] + "'"));

				if (this.objCustomer == null) {
					toWeb("对不起当前QQ不合法！没有获取到对应的用户！");
					logger.error("对不起当前QQ不合法！没有获取到对应的用户！");
					return;
				}

				for (int j = 1; j < 6; j++) {
					if (Config.objCOMConfig.getProperty("fromEmail_" + j) == null) {
						logger.error("邮件发送失败！");
						break;
					}
					if (MailClient.sendHtmlMailWithNick(new MailConfig("金泉商校", Config.objCOMConfig.getProperty("fromEmail_" + j), arrayCustomerQQ[i] + "@qq.com", Config.objCOMConfig
							.getProperty("fromEmailPassword_" + j), true, this.objEmail.getStrSubject(), this.objEmail.getStrContent()))) {
						logger.info("邮件发送成功，使用的邮箱为：" + Config.objCOMConfig.getProperty(new StringBuilder("fromEmail_").append(j).toString()));
						flag = true;
						break;
					}
				}
				if (!flag) {
					toWeb("对不起您的邮件发送失败，可能是您的邮箱不合法！");
					logger.error("发送失败！");
					return;
				}
				logger.info("第：【" + (i + 1) + "】封邮件发送成功！");
				lstCustomer.add(this.objCustomer);
			}

			if ((this.objEmail.getlId() != null) && (this.objEmail.getlId().longValue() > 0L)) {
				this.objEmail.getLstCustomer().addAll(lstCustomer);

				if (!this.objEmailService.update(this.objEmail)) {
					logger.error("当前邮件保存失败！但是已经发送成功！");
					toWeb("对不起您的消息保存失败！但是已经发送成功！");
				}

			} else {
				this.objEmail.setObjProject((Project) this.objProjectService.getById(this.lProjectID));
				this.objEmail.setObjUsers(this.objUsers);

				this.objEmail.setLstCustomer(lstCustomer);
				this.objEmail.setStrSendType("2");

				this.objEmail.setStrSendDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				if (!this.objEmailService.save(this.objEmail)) {
					logger.error("当前邮件保存失败！但是已经发送成功！");
					toWeb("对不起您的消息保存失败！但是已经发送成功！");
					return;
				}
			}
		} catch (Exception e) {
			logger.error("出现异常！发送失败！", e);
			toWeb("出现异常！发送失败！");
			return;
		}
		logger.info("邮件发送成功！");
		toWeb("success");
	}

	public void emailDelete() throws IOException {
		String[] arrayEmailID = getRequest().getParameterValues("emailID");

		if ((arrayEmailID == null) || (arrayEmailID.length <= 0)) {
			logger.error("无法获取您要删除的邮件！删除失败");
			toWeb("无法获取您要删除的邮件！删除失败");
			return;
		}

		for (int i = 0; i < arrayEmailID.length; i++) {
			Email objEmail = (Email) this.objEmailService.getById(Long.valueOf(Long.parseLong(arrayEmailID[i])));

			if (objEmail != null) {
				if (!this.objEmailService.deleteById(Long.valueOf(Long.parseLong(arrayEmailID[i])))) {
					logger.error("邮件【" + objEmail.getStrSubject() + "】--删除失败");
					toWeb("邮件【" + objEmail.getStrSubject() + "】删除失败,ID为：" + objEmail.getlId());
					return;
				}
				logger.info("删除邮件【" + objEmail.getStrSubject() + "】成功，id为：" + arrayEmailID[i]);
			}
		}

		toWeb("success");
	}

	public void loadEmails() throws IOException {
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");

		JsonConfig objJsonConfig = new JsonConfig();

		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			setErrorText("对不起当前项目不合法，不能进行查询！");
			toWeb(null);
			return;
		}

		this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		if (this.objProject == null) {
			logger.error("无法获取用户的项目信息！");
			toWeb(null);
			return;
		}

		try {
			Pagination objPagination = new Pagination();
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			objPagination.setRows(new ArrayList(this.objProject.getLstEmail()));
			objPagination.setTotal(this.objProject.getLstEmail() != null ? this.objProject.getLstEmail().size() : 0);
			objJsonConfig.setIgnoreDefaultExcludes(false);
			objJsonConfig.setExcludes(new String[] { "lstCustomer", "objProject", "objUsers" });
			logger.info("当前页数：" + nCurrentPage + "--每页显示的个数为：" + nPageSize);
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
		}
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

	public Email getObjEmail() {
		return this.objEmail;
	}

	public void setObjEmail(Email objEmail) {
		this.objEmail = objEmail;
	}

	public List<Email> getLstEmail() {
		return this.lstEmail;
	}

	public void setLstEmail(List<Email> lstEmail) {
		this.lstEmail = lstEmail;
	}

	public Customer getObjCustomer() {
		return this.objCustomer;
	}

	public void setObjCustomer(Customer objCustomer) {
		this.objCustomer = objCustomer;
	}

	public String getStrCustomerQQ() {
		return this.strCustomerQQ;
	}

	public void setStrCustomerQQ(String strCustomerQQ) {
		this.strCustomerQQ = strCustomerQQ;
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

	public String getStrProjectName() {
		return this.strProjectName;
	}

	public void setStrProjectName(String strProjectName) {
		this.strProjectName = strProjectName;
	}

	public Project getObjProject() {
		return this.objProject;
	}

	public void setObjProject(Project objProject) {
		this.objProject = objProject;
	}

	public Long getlEmailID() {
		return this.lEmailID;
	}

	public void setlEmailID(Long lEmailID) {
		this.lEmailID = lEmailID;
	}

	public Long getLEmailID() {
		return this.lEmailID;
	}

	public void setLEmailID(Long lEmailID) {
		this.lEmailID = lEmailID;
	}
}