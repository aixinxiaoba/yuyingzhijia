package com.manage.crm.dao;

import com.manage.crm.entity.Project;
import javacommon.core.base.BaseDao;

public interface ProjectDao extends BaseDao<Project>
{
	/**
	 * 根据项目标志获取项目信息
	 * @param projectKey
	 * @return
	 */
	Project getProjectByProjectKey(String projectKey);
}
