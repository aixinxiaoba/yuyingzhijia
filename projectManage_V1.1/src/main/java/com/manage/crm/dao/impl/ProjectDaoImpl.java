package com.manage.crm.dao.impl;

import com.manage.crm.dao.ProjectDao;
import com.manage.crm.entity.Project;

import javacommon.core.base.BaseDaoImpl;

import org.springframework.stereotype.Repository;

@Repository("projectDao")
public class ProjectDaoImpl extends BaseDaoImpl<Project>
  implements ProjectDao
{

	/**
	 * 根据项目标志获取项目信息
	 */
	@Override
	public Project getProjectByProjectKey(String projectKey) {
		// 根据项目标志获取项目信息
		return this.getByHql(" from Project where projectKey='" + projectKey + "'");
	}
}