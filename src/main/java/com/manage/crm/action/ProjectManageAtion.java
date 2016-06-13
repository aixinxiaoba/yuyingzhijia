package com.manage.crm.action;

import com.manage.crm.entity.CustomerType;
import com.manage.crm.entity.FiledInfo;
import com.manage.crm.entity.ProFiledInfo;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.Users;
import com.manage.crm.service.CustomerTypeService;
import com.manage.crm.service.FiledInfoService;
import com.manage.crm.service.ProFiledInfoService;
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

import org.hibernate.criterion.Order;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("projectManageAction")
@Scope("prototype")
public class ProjectManageAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -6865508346891430289L;
	private static final Logger logger = LoggerFactory.getLogger(ProjectManageAtion.class);
	private static final String PROJECT_LIST = "projectList";
	private static final String PROJECT_DETIAL = "projectDetial";
	private static final String PROJECTFILEDINFO_LIST = "projectFiledInfoList";
	private static final String PRE_PROJECT_ADD = "preProjectAdd";
	private static final String USER_LOGIN = "userLogin";

	@Resource(name = "projectService")
	private ProjectService objProjectService;

	@Resource(name = "filedInfoService")
	private FiledInfoService objFiledService;

	@Resource(name = "customerTypeService")
	private CustomerTypeService objCustomerTypeService;

	@Resource(name = "usersService")
	private UsersService objUsersService;

	@Resource(name = "proFiledInfoService")
	private ProFiledInfoService objProFiledInfoService;

	@Resource(name = "filedInfoService")
	private FiledInfoService objFiledInfoService;
	private List<Project> lstProject;
	private List<FiledInfo> lstAllFiledInfo;
	private List<FiledInfo> lstFiledInfo;
	private List<ProFiledInfo> lstProFiledInfo;
	private List<CustomerType> lstCustomerType;
	private ProFiledInfo objProFiledInfo;
	private Users objUsers;
	private Project objProject;
	private Long lProjectID;
	private String strProjectName;
	private Long lCustomerTypeID;
	
	private String projectKey;
	
	public String projectList() {
		if (!commonValidateUsers()) {
			return "commonError";
		}

		this.lstProject = new ArrayList(this.objUsers.getLstProject());
		logger.info("项目数量为：" + (this.lstProject == null ? 0 : this.lstProject.size()));
		return "projectList";
	}

	public String projectDetial() {
		if (!commonValidateUsers()) {
			addFieldError("msg", "Session 已失效，请重新登录！");
			return "userLogin";
		}

		if ((this.lProjectID != null) && (this.lProjectID.longValue() > 0L)) {
			this.objProject = ((Project) this.objProjectService.getById(this.lProjectID));
		}

		this.lstCustomerType = this.objCustomerTypeService.listByHql("From CustomerType where PID = -1");
		return "projectDetial";
	}

	public String preProjectAdd() {
		if (!commonValidateUsers()) {
			setErrorText("Session失效 请重新登录！");
			return "commonError";
		}

		this.lstCustomerType = this.objCustomerTypeService.listByHql("From CustomerType where PID = -1");

		return "preProjectAdd";
	}

	public void projectAdd() throws IOException {
		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		// 判断当前用户拥有几个项目
		if (this.objUsers.getnCurrentLevel() != null && this.objUsers.getnCurrentLevel() == 2 && this.objUsers.getLstProject().size() >= 1)
		{
			toWeb("对不起，您当前等级只能注册一个项目！如需注册多个项目请通过QQ或QQ群与我们联系！");
			return;
		}
		// 获取项目类型。
		if (lCustomerTypeID == null || lCustomerTypeID <= 0)
		{
			toWeb("请选择您的项目类型！");
			return;
		}
		
		CustomerType objCustomerType = this.objCustomerTypeService.getByHql(" From CustomerType where lId='"+lCustomerTypeID+"'");
		if (objCustomerType == null)
		{
			toWeb("请重新选择您的项目类型！");
			return;
		}
		objProject.setObjCustomerType(objCustomerType);
		toWeb(this.objUsersService.saveProject(this.objUsers, this.objProject));
	}

	public void projectModify() throws IOException {
		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		if ((this.lProjectID == null) || (this.lProjectID.longValue() <= 0L)) {
			toWeb("保存失败！无法获取您要保存的项目信息");
			return;
		}

		Project objProjectFromDB = (Project) this.objProjectService.getById(this.lProjectID);
		if (this.objProject == null) {
			toWeb("保存失败！无法获取您要保存的项目信息");
			return;
		}
		if ((this.lCustomerTypeID == null) || (this.lCustomerTypeID.longValue() <= 0L)) {
			toWeb("保存失败！无法获取项目类型");
			return;
		}
		CustomerType objCustomerType = (CustomerType) this.objCustomerTypeService.getById(this.lCustomerTypeID);
		objProjectFromDB.setObjCustomerType(objCustomerType);
		if (!StringUtils.isEmpty(this.objProject.getStrPname())) {
			objProjectFromDB.setStrPname(this.objProject.getStrPname());
		}
		if (!StringUtils.isEmpty(this.objProject.getStrEmailListPID())) {
			objProjectFromDB.setStrEmailListPID(this.objProject.getStrEmailListPID());
		}
		if (!StringUtils.isEmpty(this.objProject.getStrEmailContent())) {
			objProjectFromDB.setStrEmailContent(this.objProject.getStrEmailContent());
		}

		if (!StringUtils.isEmpty(this.objProject.getStrMerId())) {
			objProjectFromDB.setStrMerId(this.objProject.getStrMerId());
		}
		if (!StringUtils.isEmpty(this.objProject.getStrKeyValue())) {
			objProjectFromDB.setStrKeyValue(this.objProject.getStrKeyValue());
		}
		if (!StringUtils.isEmpty(this.objProject.getStrCallBackURL())) {
			objProjectFromDB.setStrCallBackURL(this.objProject.getStrCallBackURL());
		}

		if (this.objProject.getnEmailListStatus() >= 0) {
			objProjectFromDB.setnEmailListStatus(this.objProject.getnEmailListStatus());
		}
		if (this.objProject.getnIsUseResultPage() >= 0) {
			objProjectFromDB.setnIsUseResultPage(this.objProject.getnIsUseResultPage());
		}
		if (!StringUtils.isEmpty(this.objProject.getProjectKey())) {
			objProjectFromDB.setProjectKey(this.objProject.getProjectKey());
		}
		if (!this.objProjectService.update(objProjectFromDB)) {
			toWeb("保存失败！出现异常！");
			return;
		}

		toWeb("success");
	}

	public void projectDelete() throws IOException {
		String[] arrayProjectID = getRequest().getParameterValues("projectID");

		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		if ((arrayProjectID == null) || (arrayProjectID.length <= 0)) {
			logger.error("无法获取您要删除的项目！删除失败");
			toWeb("无法获取您要删除的项目！删除失败");
			return;
		}

		Set lstTempProject = this.objUsers.getLstProject();

		for (int i = 0; i < arrayProjectID.length; i++) {
			Project objProject = (Project) this.objProjectService.getById(Long.valueOf(Long.parseLong(arrayProjectID[i])));

			if ((objProject.getLstCustomer() != null) && (objProject.getLstCustomer().size() > 0)) {
				logger.error("项目id为【" + objProject.getlId() + "】存在客户不能删除！");
				toWeb("项目【" + objProject.getStrPname() + "】存在客户！删除失败");
				return;
			}

			lstTempProject.remove(objProject);
			logger.info("项目【" + objProject.getStrPname() + "】准备删除...");
		}

		this.objUsers.setLstProject(lstTempProject);

		if (!this.objUsersService.update(this.objUsers)) {
			logger.error("项目删除失败");
			toWeb("项目删除失败，请稍后重试！");
			return;
		}

		toWeb("success");
	}

	public String projectSearch() {
		if (!commonValidateUsers()) {
			return "commonError";
		}

		logger.info("开始搜索项目名称包含【" + this.strProjectName + "】的项目");
		this.lstProject = this.objProjectService.listByHql("From Project where strPname like '%" + this.strProjectName + "%'");
		return "projectList";
	}

	public String projectFiledInfoList() {
		List lstProFiledInfoFID = new ArrayList();
		ProFiledInfo objProFiledInfo = new ProFiledInfo();

		if (!commonValidateUsers()) {
			return "commonError";
		}

		this.lstAllFiledInfo = this.objFiledService.listAll(Order.asc("lId"));
		if ((this.lstAllFiledInfo == null) || (this.lstAllFiledInfo.size() == 0)) {
			setErrorText("没拿到所有信息字段");
			return "commonError";
		}

		objProFiledInfo.setlPid(this.lProjectID);
		objProFiledInfo.setnStatus(1);
		this.lstProFiledInfo = this.objProFiledInfoService.listByProps(objProFiledInfo, "lPid,nStatus", Order.asc("nSequence"));

		if ((this.lstProFiledInfo != null) && (this.lstProFiledInfo.size() > 0)) {
			for (int i = 0; i < this.lstProFiledInfo.size(); i++) {
				objProFiledInfo = (ProFiledInfo) this.lstProFiledInfo.get(i);

				if ((objProFiledInfo != null) && (objProFiledInfo.getlId().longValue() > 0L)) {
					lstProFiledInfoFID.add(objProFiledInfo.getlFiid());
				}

			}

			for (int i = this.lstAllFiledInfo.size() - 1; i >= 0; i--) {
				FiledInfo objFiledInfo = (FiledInfo) this.lstAllFiledInfo.get(i);

				if ((objFiledInfo != null) && (objFiledInfo.getLId() > 0L)) {
					if (lstProFiledInfoFID.contains(Long.valueOf(objFiledInfo.getLId()))) {
						this.lstAllFiledInfo.remove(i);
					}
				}
			}
		}
		return "projectFiledInfoList";
	}

	public void loadProjectFiledInfoList() throws IOException {
		Pagination objPagination = null;
		JsonConfig objJsonConfig = new JsonConfig();
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");

		if (!commonValidateUsers()) {
			logger.error("Sessoin 失效，请重新登录！");
			toWeb(JSONObject.fromObject(new Pagination()).toString());
			return;
		}

		try {
			this.lstProFiledInfo = this.objProFiledInfoService.listByHql("From Pro_FiledInfo where lPid = " + this.lProjectID + " order by nStatus desc");
			objPagination = new Pagination();
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			objPagination.setTotal((this.lstProFiledInfo == null) || (this.lstProFiledInfo.size() == 0) ? 0 : this.lstProFiledInfo.size());
			objPagination.setRowsSimple(this.lstProFiledInfo);
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
			toWeb(JSONObject.fromObject(new Pagination()).toString());
		}
	}

	public void loadFiledInfoList() throws IOException {
		Pagination objPagination = null;
		JsonConfig objJsonConfig = new JsonConfig();
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");

		if (!commonValidateUsers()) {
			logger.error("Sessoin 失效，请重新登录！");
			toWeb(null);
			return;
		}

		try {
			this.lstFiledInfo = this.objFiledService.listByHql("From FiledInfo where lId not in( select lFiid from Pro_FiledInfo where lPid=" + this.lProjectID + ")");
			if ((this.lstFiledInfo != null) && (this.lstFiledInfo.size() > 0)) {
				objPagination = new Pagination();
				if ((nPageSize == null) || (nPageSize.length() <= 0)) {
					nPageSize = "20";
				}
				if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
					nCurrentPage = "1";
				}

				objPagination.setPageNo(Integer.parseInt(nCurrentPage));
				objPagination.setPageSize(Integer.parseInt(nPageSize));
				objPagination.setTotal(this.lstFiledInfo.size());
				objPagination.setRowsSimple(this.lstFiledInfo);
				objJsonConfig.setExcludes(new String[] { "lstProject" });
				toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
			} else {
				toWeb(null);
			}
		} catch (Exception e) {
			logger.info("系统异常", e);
		}
	}

	public void loadProjectTypeList() throws IOException {
		Pagination objPagination = null;
		JsonConfig objJsonConfig = new JsonConfig();
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");

		if (!commonValidateUsers()) {
			logger.error("Sessoin 失效，请重新登录！");
			toWeb(JSONObject.fromObject(new Pagination()).toString());
			return;
		}

		try {
			this.lstCustomerType = this.objCustomerTypeService.listByHql("From CustomerType where PID = -1");
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
			objJsonConfig.setExcludes(new String[] { "objProject", "lstProject" });
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
			toWeb(JSONObject.fromObject(new Pagination()).toString());
		}
	}

	public void updateProFieldInfoActiveStatus() throws IOException {
		String strStatus = getRequest().getParameter("status");
		String[] arrayProFiledInfoID = getRequest().getParameterValues("lProFieldInfoID");

		if (!commonValidateUsers()) {
			toWeb("sessoin 失效，请重新登录");
			return;
		}

		try {
			toWeb(this.objProFiledInfoService.updateFiledInfoActiveStatus(this.lProjectID, arrayProFiledInfoID, Integer.parseInt(strStatus)));
		} catch (Exception e) {
			toWeb("修改失败，请稍后重试！");
		}
	}

	public void updateProFieldInfoNatureStatus() throws IOException {
		String strStatus = getRequest().getParameter("status");
		String[] arrayProFiledInfoID = getRequest().getParameterValues("lProFieldInfoID");

		if (!commonValidateUsers()) {
			toWeb("sessoin 失效，请重新登录");
			return;
		}

		toWeb(this.objProFiledInfoService.updateFiledInfoNatureStatus(this.lProjectID, arrayProFiledInfoID, Integer.parseInt(strStatus)));
	}

	public void updateProFieldInfoRegStatus() throws IOException {
		String strStatus = getRequest().getParameter("status");
		String[] arrayProFiledInfoID = getRequest().getParameterValues("lProFieldInfoID");

		if (!commonValidateUsers()) {
			toWeb("sessoin 失效，请重新登录");
			return;
		}

		toWeb(this.objProFiledInfoService.updateFiledInfoRegStatus(this.lProjectID, arrayProFiledInfoID, Integer.parseInt(strStatus)));
	}

	public void updateProFieldInfoModifyStatus() throws IOException {
		String strStatus = getRequest().getParameter("status");
		String[] arrayProFiledInfoID = getRequest().getParameterValues("lProFieldInfoID");

		if (!commonValidateUsers()) {
			toWeb("sessoin 失效，请重新登录");
			return;
		}

		toWeb(this.objProFiledInfoService.updateFiledInfoModifyStatus(this.lProjectID, arrayProFiledInfoID, Integer.parseInt(strStatus)));
	}

	public void updateProFieldInfo() throws IOException {
		ProFiledInfo objProFiledInfoDB;

		if (!commonValidateUsers()) {
			toWeb("sessoin 失效，请重新登录");
			return;
		}

		if ((this.objProFiledInfo == null) || (this.objProFiledInfo.getlId() == null) || (this.objProFiledInfo.getlId().longValue() <= 0L)) {
			toWeb("修改失败，无效的项目字段ID！");
			return;
		}

		try {
			objProFiledInfoDB = (ProFiledInfo) this.objProFiledInfoService.getById(this.objProFiledInfo.getlId());
			if (objProFiledInfoDB == null) {
				logger.error("当前项目字段为空不能进行修改，id为--" + this.objProFiledInfo.getlId());
				toWeb("当前项目字段为空不能进行修改");
				return;
			}
			if (StringUtils.isEmpty(this.objProFiledInfo.getStrPfname())) {
				logger.error("当前项目字段ID为：" + this.objProFiledInfo.getlId());
				toWeb("项目字段名称不能为空请重新设置");
				return;
			}

			objProFiledInfoDB.setnStatus(this.objProFiledInfo.getnStatus());
			objProFiledInfoDB.setnIsAdd(this.objProFiledInfo.getnIsAdd());
			objProFiledInfoDB.setnIsModify(this.objProFiledInfo.getnIsModify());
			objProFiledInfoDB.setStrPfname(this.objProFiledInfo.getStrPfname());
			objProFiledInfoDB.setnPfnature(this.objProFiledInfo.getNPfnature());
		} catch (Exception e) {
			logger.error("出现异常！更新失败");
			toWeb("出现异常！更新失败");
			return;
		}
		// ProFiledInfo objProFiledInfoDB;
		if (!this.objProFiledInfoService.update(objProFiledInfoDB)) {
			logger.error("更新失败！");
		}

		logger.info("更新字段成功");
		toWeb("success");
	}

	/**
	 * 获取项目信息。
	 * 
	 * @throws IOException
	 */
	public void loadProjectInfo() throws IOException
	{
		if (!StringUtils.isEmpty(this.projectKey))
		{
			// 根据项目标志获取项目信息
			objProject = this.objProjectService.getByHql(" from Project where projectKey='" + this.projectKey + "'");
		}
		else
		{
			objProject = new Project("无法获取项目信息，请稍后重试！");
		}
		toWeb(JSONObject.fromObject(objProject).toString());
	}
	public void activeFieldInfo() throws IOException {
		String[] arrayFiledInfoID = getRequest().getParameterValues("lFieldInfoID");

		if (!commonValidateUsers()) {
			toWeb("sessoin 失效，请重新登录");
			return;
		}

		toWeb(this.objFiledInfoService.activeFiledInfo(arrayFiledInfoID, this.lProjectID.longValue()));
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
			setErrorText("Session 已失效，请重新登录 ");
			return false;
		}

		return true;
	}

	public Project getObjProject() {
		return this.objProject;
	}

	public void setObjProject(Project objProject) {
		this.objProject = objProject;
	}

	public List<Project> getLstProject() {
		return this.lstProject;
	}

	public void setLstProject(List<Project> lstProject) {
		this.lstProject = lstProject;
	}

	public FiledInfoService getObjFiledService() {
		return this.objFiledService;
	}

	public void setObjFiledService(FiledInfoService objFiledService) {
		this.objFiledService = objFiledService;
	}

	public List<FiledInfo> getLstAllFiledInfo() {
		return this.lstAllFiledInfo;
	}

	public void setLstAllFiledInfo(List<FiledInfo> lstAllFiledInfo) {
		this.lstAllFiledInfo = lstAllFiledInfo;
	}

	public List<ProFiledInfo> getLstProFiledInfo() {
		return this.lstProFiledInfo;
	}

	public void setLstProFiledInfo(List<ProFiledInfo> lstProFiledInfo) {
		this.lstProFiledInfo = lstProFiledInfo;
	}

	public long getlProjectID() {
		return this.lProjectID.longValue();
	}

	public void setlProjectID(long lProjectID) {
		this.lProjectID = Long.valueOf(lProjectID);
	}

	public long getLProjectID() {
		return this.lProjectID.longValue();
	}

	public void setLProjectID(long lProjectID) {
		this.lProjectID = Long.valueOf(lProjectID);
	}

	public String getStrProjectName() {
		return this.strProjectName;
	}

	public void setStrProjectName(String strProjectName) {
		this.strProjectName = strProjectName;
	}

	public List<FiledInfo> getLstFiledInfo() {
		return this.lstFiledInfo;
	}

	public void setLstFiledInfo(List<FiledInfo> lstFiledInfo) {
		this.lstFiledInfo = lstFiledInfo;
	}

	public ProFiledInfo getObjProFiledInfo() {
		return this.objProFiledInfo;
	}

	public void setObjProFiledInfo(ProFiledInfo objProFiledInfo) {
		this.objProFiledInfo = objProFiledInfo;
	}

	public List<CustomerType> getLstCustomerType() {
		return this.lstCustomerType;
	}

	public void setLstCustomerType(List<CustomerType> lstCustomerType) {
		this.lstCustomerType = lstCustomerType;
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
}