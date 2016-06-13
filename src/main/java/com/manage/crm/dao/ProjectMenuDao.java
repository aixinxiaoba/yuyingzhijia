package com.manage.crm.dao;

import java.util.List;

import javacommon.core.base.BaseDao;

import com.manage.crm.entity.ProjectMenu;

public interface ProjectMenuDao extends BaseDao<ProjectMenu>
{
	List<ProjectMenu> lstValidSubProjectMenu(long lProjectMenuID);
	
	List<ProjectMenu> lstValidSubProjectMenu();

	List<ProjectMenu> lstValidProjectMenu();
	
	/**
	 * 根据project id 查询菜单
	 * @return
	 */
	List<ProjectMenu> lstProjectMenuByProId(Long lProjectId);

	
}