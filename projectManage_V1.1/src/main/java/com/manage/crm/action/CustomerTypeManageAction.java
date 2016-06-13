package com.manage.crm.action;

import com.manage.crm.entity.CustomerType;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.Users;
import com.manage.crm.service.CustomerTypeService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.UsersService;
import com.manage.crm.util.Pagination;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javacommon.core.base.BaseStruts2Action;
import javacommon.util.StringUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("customerTypeManageAction")
@Scope("prototype")
public class CustomerTypeManageAction extends BaseStruts2Action {
	private static final long serialVersionUID = 3842259510429485253L;
	private static final Logger logger = LoggerFactory.getLogger(CustomerTypeManageAction.class);
	private static final String CUSTOMERTYPE_LIST = "customerTypeList";

	@Resource(name = "projectService")
	private ProjectService objProjectService;

	@Resource(name = "usersService")
	private UsersService objUsersService;

	@Resource(name = "customerTypeService")
	private CustomerTypeService objCustomerTypeService;
	private Users objUsers;
	private Project objProject;
	private CustomerType objCustomerType;
	private List<CustomerType> lstCustomerType;
	private long lProjectID;

	public void customerTypeList() throws IOException {
		Pagination objPagination = null;
		JsonConfig objJsonConfig = new JsonConfig();
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");
		String strIsProjectType = getRequest().getParameter("isProjectType");

		if (StringUtils.isBlank(getUserIdFromSession())) {
			logger.error("Sessoin 失效，请重新登录！");
			toWeb(JSONObject.fromObject(new Pagination()));
			return;
		}
		if ("true".equals(strIsProjectType)) {
			this.lstCustomerType = this.objCustomerTypeService.listByHql("From CustomerType where PID = -1");
		} else {
			if (this.lProjectID <= 0L) {
				logger.error("出现错误，无法获取您的客户类型标识！");
				toWeb(JSONObject.fromObject(new Pagination()));
				return;
			}

			this.objProject = ((Project) this.objProjectService.getById(Long.valueOf(this.lProjectID)));
			if (this.objProject == null) {
				logger.error("出现错误，无法获取您的客户类型信息！");
				toWeb(JSONObject.fromObject(new Pagination()));
				return;
			}
			this.lstCustomerType = new ArrayList(this.objProject.getLstCustomerType());
		}
		try {
			objPagination = new Pagination();
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			objPagination.setTotal((this.lstCustomerType == null) || (this.lstCustomerType.size() == 0) ? 0 : this.lstCustomerType.size());
			objPagination.setRowsSimple(this.lstCustomerType);
			objJsonConfig.setExcludes(new String[] { "objProject", "lstCustomer", "lstProject" });
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
			toWeb(JSONObject.fromObject(new Pagination()));
		}
	}

	public void customerTypeAdd() throws IOException {
		String strIsProjectType = getRequest().getParameter("isProjectType");

		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		if (this.objCustomerType == null) {
			logger.error("出现错误，没有获取到您的客户类型信息！");
			toWeb("出现错误，没有获取到您的客户类型信息！");
			return;
		}
		if (StringUtils.isEmpty(this.objCustomerType.getStrName())) {
			logger.error("客户类型名称不能为空！");
			toWeb("客户类型名称不能为空！");
			return;
		}

		if ("true".equals(strIsProjectType)) {
			this.lProjectID = -1L;
			this.objProject = new Project();
			this.objProject.setlId(Long.valueOf(-1L));
		} else {
			if (this.lProjectID <= 0L) {
				logger.error("出现错误，无法获取对应项目！");
				toWeb("出现错误，出现错误，无法获取对应项目！");
				return;
			}
			this.objProject = ((Project) this.objProjectService.getById(Long.valueOf(this.lProjectID)));
		}

		if (this.objProject == null) {
			logger.error("出现错误，无法获取您的客户类型信息！");
			toWeb("出现错误，无法获取您的客户类型信息！");
			return;
		}

		this.objCustomerType.setObjProject(this.objProject);
		if (!this.objCustomerTypeService.save(this.objCustomerType)) {
			logger.error("出现异常，无法保存您的客户类型，请刷新重试！");
			toWeb("出现异常，无法保存您的客户类型，请刷新重试！");
			return;
		}

		if (this.objCustomerType.getnIsDefaultValue() == 1) {
			CustomerType objTempCustomerType = (CustomerType) this.objCustomerTypeService.getByHql("From CustomerType where objProject.lId=" + this.lProjectID + " and nIsDefaultValue = 1");

			if (objTempCustomerType != null) {
				objTempCustomerType.setnIsDefaultValue(2);
			}
			if (!this.objCustomerTypeService.save(objTempCustomerType)) {
				logger.error("出现异常，无法更新您的客户类型，请刷新重试！");
				toWeb("出现异常，无法修改您的客户类型，请刷新重试！");
				return;
			}
		}
		logger.info("保存成功");
		toWeb("success");
	}

	public void customerTypeDelete() throws IOException {
		String[] arrayCustomerTypeID = getRequest().getParameterValues("lCustomerTypeID");

		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		if ((arrayCustomerTypeID == null) || (arrayCustomerTypeID.length <= 0)) {
			logger.error("无法获取您要删除的客户类型！删除失败");
			toWeb("无法获取您要删除的客户类型！删除失败");
			return;
		}

		for (int i = 0; i < arrayCustomerTypeID.length; i++) {
			CustomerType objCustomerType = (CustomerType) this.objCustomerTypeService.getById(Long.valueOf(Long.parseLong(arrayCustomerTypeID[i])));

			if ((objCustomerType.getLstProject() != null) && (objCustomerType.getLstProject().size() > 0)) {
				logger.error("客户类型为【" + objCustomerType.getStrName() + "】id为" + objCustomerType.getlId() + "存在客户不能删除！");
				toWeb("项目类型【" + objCustomerType.getStrName() + "】下存在项目不能进行删除");
				return;
			}
			if ((objCustomerType.getLstCustomer() != null) && (objCustomerType.getLstCustomer().size() > 0)) {
				logger.error("客户类型为【" + objCustomerType.getStrName() + "】id为" + objCustomerType.getlId() + "存在客户不能删除！");
				toWeb("客户类型【" + objCustomerType.getStrName() + "】下存在客户不能进行删除");
				return;
			}
			if (!this.objCustomerTypeService.deleteById(Long.valueOf(Long.parseLong(arrayCustomerTypeID[i])))) {
				logger.error("客户类型" + objCustomerType.getStrName() + "--删除失败");
				toWeb("客户类型" + objCustomerType.getStrName() + "--删除失败");
				return;
			}
			logger.info("客户类型【" + objCustomerType.getStrName() + "】id为【" + objCustomerType.getlId() + "】删除成功");
		}

		toWeb("success");
	}

	public void customerTypeUpdate() throws IOException {
		CustomerType objTempCustomerType = null;
		CustomerType objCustomerTypeDB;

		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		if ((this.objCustomerType == null) || (this.objCustomerType.getlId() == null) || (this.objCustomerType.getlId().longValue() <= 0L)) {
			logger.error("未获取您要修改客户类型的信息！");
			toWeb("未获取您要修改的信息！");
			return;
		}

		try {
			objCustomerTypeDB = (CustomerType) this.objCustomerTypeService.getById(this.objCustomerType.getlId());
		} catch (Exception e) {
			logger.error("无法获取客户类型");
			toWeb("出现错误，无法获取您的客户类型！");
			return;
		}
		if (objCustomerTypeDB == null) {
			logger.error("无法获取客户类型");
			toWeb("出现错误，无法获取您的客户类型！");
			return;
		}

		if (!StringUtils.isEmpty(this.objCustomerType.getStrName())) {
			objCustomerTypeDB.setStrName(this.objCustomerType.getStrName());
		}
		if (!StringUtils.isEmpty(this.objCustomerType.getStrDescrible())) {
			objCustomerTypeDB.setStrDescrible(this.objCustomerType.getStrDescrible());
		}

		if (this.objCustomerType.getnIsDefaultValue() == 1) {
			objTempCustomerType = (CustomerType) this.objCustomerTypeService.getByHql("From CustomerType where objProject.lId=" + this.lProjectID + " and nIsDefaultValue = 1");
		}

		objCustomerTypeDB.setnIsDefaultValue(this.objCustomerType.getnIsDefaultValue());
		if (!this.objCustomerTypeService.update(objCustomerTypeDB)) {
			logger.error("出现异常，更新失败");
			toWeb("出现异常，更新失败");
			return;
		}

		if ((objTempCustomerType != null) && (objTempCustomerType.getlId() != this.objCustomerType.getlId())) {
			objTempCustomerType.setnIsDefaultValue(2);
			if (!this.objCustomerTypeService.update(objTempCustomerType)) {
				logger.error("出现异常，更新失败");
				toWeb("出现异常，更新失败");
				return;
			}
		}
		toWeb("success");
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

	public long getlProjectID() {
		return this.lProjectID;
	}

	public void setlProjectID(long lProjectID) {
		this.lProjectID = lProjectID;
	}

	public long getLProjectID() {
		return this.lProjectID;
	}

	public void setLProjectID(long lProjectID) {
		this.lProjectID = lProjectID;
	}

	public Project getObjProject() {
		return this.objProject;
	}

	public void setObjProject(Project objProject) {
		this.objProject = objProject;
	}

	public List<CustomerType> getLstCustomerType() {
		return this.lstCustomerType;
	}

	public void setLstCustomerType(List<CustomerType> lstCustomerType) {
		this.lstCustomerType = lstCustomerType;
	}

	public CustomerType getObjCustomerType() {
		return this.objCustomerType;
	}

	public void setObjCustomerType(CustomerType objCustomerType) {
		this.objCustomerType = objCustomerType;
	}

	public Users getObjUsers() {
		return this.objUsers;
	}

	public void setObjUsers(Users objUsers) {
		this.objUsers = objUsers;
	}
}