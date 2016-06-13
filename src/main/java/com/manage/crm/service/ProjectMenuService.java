package com.manage.crm.service;

import java.util.List;

import javacommon.core.base.BaseService;

import com.manage.crm.entity.ProjectMenu;

public interface ProjectMenuService extends BaseService<ProjectMenu>
{
	public List<ProjectMenu> lstValidSubProjectMenu(long lProjectMenuID);
	
	List<ProjectMenu> lstValidSubProjectMenu();

	List<ProjectMenu> lstValidProjectMenu();

	/**
	 * 根据project id 查询菜单
	 * @return
	 */
	List<ProjectMenu> lstProjectMenuByProId(Long lProjectId);
}