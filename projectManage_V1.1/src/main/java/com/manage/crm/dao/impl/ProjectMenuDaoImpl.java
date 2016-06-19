package com.manage.crm.dao.impl;

import java.util.List;

import javacommon.core.base.BaseDaoImpl;

import org.springframework.stereotype.Repository;

import com.manage.crm.dao.ProjectMenuDao;
import com.manage.crm.entity.ProjectMenu;

@Repository("projectMenuDao")
public class ProjectMenuDaoImpl extends BaseDaoImpl<ProjectMenu> implements ProjectMenuDao {
	/**
	 * 设置项目子菜单。
	 */
	public List<ProjectMenu> lstValidSubProjectMenu(long lProjectMenuID) {
		List<ProjectMenu> lstProjectMenu;

		// 加载首页需要显示的菜单(只显示有效菜单)。
		lstProjectMenu = this.listByHql(" from ProjectMenu where objParentProjectMenu.lId=" + lProjectMenuID + " and validate=1 and level=2");

		return lstProjectMenu;
	}

	/**
	 * 获取所有有效的二级菜单。
	 */
	public List<ProjectMenu> lstValidSubProjectMenu() {
		List<ProjectMenu> lstProjectMenu;

		// 加载首页需要显示的菜单(只显示有效菜单)。
		lstProjectMenu = this.listByHql(" from ProjectMenu where validate=1 and level=2 and lId >= 63");

		return lstProjectMenu;
	}

	/**
	 * 获取所有有效的一级菜单。
	 */
	public List<ProjectMenu> lstValidProjectMenu() {
		List<ProjectMenu> lstProjectMenu;

		// 加载首页需要显示的菜单(只显示有效菜单)。
		lstProjectMenu = this.listByHql(" from ProjectMenu where validate=1 and level=1");

		return lstProjectMenu;
	}

	/**
	 * 根据project id 查询菜单
	 * 
	 * @return
	 */
	public List<ProjectMenu> lstProjectMenuByProId(Long lProjectId) {
		// 加载首页需要显示的菜单(只显示有效菜单)。
		return this.listByHql(" from ProjectMenu where objProject.lId='" + lProjectId + "' and validate=1 and level=1 and menuKey !='aboutUs'");
	}
}
