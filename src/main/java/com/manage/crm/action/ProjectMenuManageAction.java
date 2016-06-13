package com.manage.crm.action;

import java.io.IOException;
import java.util.List;

import javacommon.core.base.BaseStruts2Action;
import javacommon.util.SearchCondition;
import javacommon.util.StringUtils;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.manage.crm.entity.Project;
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.entity.Users;
import com.manage.crm.service.ProjectMenuService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.UsersService;
import com.manage.crm.util.Pagination;

@Controller("projectMenuManageAction")
@Scope("prototype")
public class ProjectMenuManageAction extends BaseStruts2Action {
	private static final long serialVersionUID = 3842259510429485253L;
	private static final Logger logger = LoggerFactory.getLogger(ProjectMenuManageAction.class);
	private static final String PROJECTMENULIST = "projectMenuList";

	@Resource(name = "projectService")
	private ProjectService objProjectService;

	@Resource(name = "usersService")
	private UsersService objUsersService;

	@Resource(name = "projectMenuService")
	private ProjectMenuService objProjectMenuService;
	private Users objUsers;
	private Project objProject;
	private ProjectMenu objProjectMenu;
	private List<ProjectMenu> lstProjectMenu;
	private Long lProjectID;
	private String projectKey; // 项目标志
	private String menuKey; // 子菜单标志
	private Long parProjectMenuId; // 父类菜单id。

	public void loadMenuName() throws IOException {
		Project objProject;
		ProjectMenu objProjectMenu= null;
		
		try {	
			if (!StringUtils.isEmpty(this.projectKey))
			{
				// 根据项目标志获取项目信息
				objProject = this.objProjectService.getByHql(" from Project where projectKey='" + this.projectKey + "'");
				
				this.lProjectID = objProject != null ? objProject.getlId() : null;
			}
			if (!StringUtils.isEmpty(this.menuKey) && this.lProjectID > 0)
			{
				// 根据项目标志获取项目信息
				objProjectMenu = this.objProjectMenuService.getByHql("from ProjectMenu where menuKey='" + this.menuKey + "' and objProject.lId=" + this.lProjectID);
			}
			String strProjectMenuName = objProjectMenu != null ? objProjectMenu.getStrMenuName() : "";
			toWeb(strProjectMenuName);
		} catch (Exception e) {
			logger.info("系统异常", e);
			toWeb("系统异常");
			return;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void projectMenuList() throws IOException {
		JsonConfig objJsonConfig = new JsonConfig();
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");
		String strValidate = getRequest().getParameter("validate");
		String strParentID = getRequest().getParameter("parentID");
		String strSubMenuID = getRequest().getParameter("subMenuID");
		String upMenu = getRequest().getParameter("upMenu");
//		SimpleExpression objConditionOne = null; // condition one
//		SimpleExpression objConditionTwo = null; // condition Two
//		Criterion objConditionThree = null; // condition Three
		Criterion objSQLCondition;
		

		if (strSubMenuID != null && Long.parseLong(strSubMenuID) > 0)
		{
			if (upMenu != null && "1".equals(upMenu))
			{
				objSQLCondition = Restrictions.sqlRestriction("   parid=(select parid from projectmenu s where s.id=" + strSubMenuID+")");
			}
			else
			{
				objSQLCondition = Restrictions.sqlRestriction("  id=" + strSubMenuID);
			}
			
		}
		else if (strParentID != null && Long.parseLong(strParentID) > 0)
		{
			objSQLCondition = Restrictions.sqlRestriction("  parid=" + strParentID);
		}
		else
		{
			objSQLCondition = Restrictions.sqlRestriction(" PID = "+this.lProjectID + " and level=1 ");
		}
		
		if (this.lProjectID <= 0L) {
			logger.error("出现错误，无法获取您的项目菜单标识！");
			toWeb(JSONObject.fromObject(new Pagination()));
			return;
		}
		this.objProject = ((Project) this.objProjectService.getById(Long.valueOf(this.lProjectID)));
		if (this.objProject == null) {
			logger.error("出现错误，无法获取您的项目菜单信息！");
			toWeb(JSONObject.fromObject(new Pagination()));
			return;
		}
//		objConditionTwo = Restrictions.eq("objProject.lId", this.lProjectID);
//		if ((!StringUtils.isEmpty(strValidate))) {
//			objConditionOne = Restrictions.eq("validate", Integer.parseInt(strValidate));
//		}
//		
//		if (strParentID != null && Long.parseLong(strParentID) > 0)
//		{
//			objConditionThree = Restrictions.eq("objParentProjectMenu.lId", Long.parseLong(strParentID));
//		}
//		else
//		{
//			objConditionThree = Restrictions.isNull("objParentProjectMenu.lId");
//		}
		Pagination<ProjectMenu> objPagination = new Pagination<ProjectMenu>();
		if ((nPageSize == null) || (nPageSize.length() <= 0)) {
			nPageSize = "20";
		}
		if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
			nCurrentPage = "1";
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
			this.objProjectMenuService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), null);
			objJsonConfig.setIgnoreDefaultExcludes(false);
			objJsonConfig.setExcludes(new String[] { "objProject", "lstCustomer", "lstProject", "lstNews", "objParentProjectMenu", "lstChildrenProjectMenu"});
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
			toWeb(JSONObject.fromObject(new Pagination()));
		}
	}

	/**
	 * 新增。
	 * 
	 * @throws IOException
	 */
	public void projectMenuAdd() throws IOException {
		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		if (this.objProjectMenu == null) {
			logger.error("出现错误，没有获取到您的项目菜单信息！");
			toWeb("出现错误，没有获取到您的项目菜单信息！");
			return;
		}
		if (StringUtils.isEmpty(this.objProjectMenu.getStrMenuName())) {
			logger.error("项目菜单名称不能为空！");
			toWeb("项目菜单名称不能为空！");
			return;
		}
		if (StringUtils.isEmpty(this.objProjectMenu.getMenuKey())) {
			logger.error("项目菜单标识不能为空！请填写！");
			toWeb("项目菜单标识不能为空！请填写！");
			return;
		}
		if (this.lProjectID <= 0L) {
			logger.error("出现错误，无法获取对应项目！");
			toWeb("出现错误，出现错误，无法获取对应项目！");
			return;
		}
		this.objProject = this.objProjectService.getById(Long.valueOf(this.lProjectID));
		if (this.objProject == null) {
			logger.error("出现错误，无法获取您的项目菜单信息！");
			toWeb("出现错误，无法获取您的项目菜单信息！");
			return;
		}
		
		this.objProjectMenu.setObjProject(this.objProject);
		if (this.parProjectMenuId != null && this.parProjectMenuId > 0)
		{
			this.objProjectMenu.setObjParentProjectMenu(objProjectMenuService.getById(this.parProjectMenuId));
		}
		if (!this.objProjectMenuService.save(this.objProjectMenu)) {
			logger.error("出现异常，无法保存您的项目菜单，请刷新重试！");
			toWeb("出现异常，无法保存您的项目菜单，请刷新重试！");
			return;
		}
		// 更新菜单对应的使用url
		this.objProjectMenu.setMenuURL("/front/yuyingshi/index.do?lProjectMenuID=" + this.objProjectMenu.getlId());
		this.objProjectMenuService.update(this.objProjectMenu);
		logger.info("保存成功");
		toWeb("success");
	}

	public void projectMenuDelete() throws IOException {
		String[] arrayProjectMenuID = getRequest().getParameterValues("lProjectMenuID");

		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}

		if ((arrayProjectMenuID == null) || (arrayProjectMenuID.length <= 0)) {
			logger.error("无法获取您要删除的项目菜单！删除失败");
			toWeb("无法获取您要删除的项目菜单！删除失败");
			return;
		}

		for (int i = 0; i < arrayProjectMenuID.length; i++) {
			ProjectMenu objProjectMenu = (ProjectMenu) this.objProjectMenuService.getById(Long.valueOf(Long.parseLong(arrayProjectMenuID[i])));

			if (!this.objProjectMenuService.deleteById(Long.valueOf(Long.parseLong(arrayProjectMenuID[i])))) {
				logger.error("项目菜单" + objProjectMenu.getStrMenuName() + "--删除失败");
				toWeb("项目菜单" + objProjectMenu.getStrMenuName() + "--删除失败");
				return;
			}
			logger.info("项目菜单【" + objProjectMenu.getStrMenuName() + "】id为【" + objProjectMenu.getlId() + "】删除成功");
		}
		logger.info("整体删除项目菜单成功！");
		toWeb("success");
	}

	public void projectMenuUpdate() throws IOException {
		if (!commonValidateUsers()) {
			toWeb("Session失效 请重新登录！");
			return;
		}
		if (this.lProjectID <= 0L) {
			logger.error("出现错误，无法获取对应项目！");
			toWeb("出现错误，出现错误，无法获取对应项目！");
			return;
		}
		this.objProject = ((Project) this.objProjectService.getById(Long.valueOf(this.lProjectID)));
		if (this.objProject == null) {
			logger.error("出现错误，无法获取您的项目菜单信息！");
			toWeb("出现错误，无法获取您的项目菜单信息！");
			return;
		}
		if ((this.objProjectMenu == null) || (this.objProjectMenu.getlId() == null) || (this.objProjectMenu.getlId().longValue() <= 0L)) {
			logger.error("未获取您要修改项目菜单的信息！");
			toWeb("未获取您要修改的信息！");
			return;
		}
		
		if ("".equals(this.objProjectMenu.getStrMenuName())) {
			logger.error("未获取您要修改项目菜单名称！请重新填写");
			toWeb("未获取您要修改项目菜单名称！请重新填写！");
			return;
		}
		if (StringUtils.isEmpty(this.objProjectMenu.getMenuKey())) {
			logger.error("未获取您要修改项目菜单标识！请重新填写");
			toWeb("未获取您要修改项目菜单标识！请重新填写！");
			return;
		}
		
		// 获取original project menu
		ProjectMenu objOriginalProjectMenu = objProjectMenuService.getById(this.objProjectMenu.getlId());
		objOriginalProjectMenu.setStrMenuName(this.objProjectMenu.getStrMenuName());
		objOriginalProjectMenu.setMenuKey(this.objProjectMenu.getMenuKey());
		objOriginalProjectMenu.setValidate(this.objProjectMenu.getValidate());
		objOriginalProjectMenu.setObjProject(objProject);
		if (!this.objProjectMenuService.update(objOriginalProjectMenu)) {
			logger.error("出现异常，更新失败");
			toWeb("出现异常，更新失败");
			return;
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

	public Long getlProjectID() {
		return lProjectID;
	}

	public void setlProjectID(Long lProjectID) {
		this.lProjectID = lProjectID;
	}
	
	public Long getLProjectID() {
		return lProjectID;
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

	public List<ProjectMenu> getLstProjectMenu() {
		return this.lstProjectMenu;
	}

	public void setLstProjectMenu(List<ProjectMenu> lstProjectMenu) {
		this.lstProjectMenu = lstProjectMenu;
	}

	public ProjectMenu getObjProjectMenu() {
		return this.objProjectMenu;
	}

	public void setObjProjectMenu(ProjectMenu objProjectMenu) {
		this.objProjectMenu = objProjectMenu;
	}

	public Users getObjUsers() {
		return this.objUsers;
	}

	public void setObjUsers(Users objUsers) {
		this.objUsers = objUsers;
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

	public Long getParProjectMenuId() {
		return parProjectMenuId;
	}

	public void setParProjectMenuId(Long parProjectMenuId) {
		this.parProjectMenuId = parProjectMenuId;
	}

	
	
	
}