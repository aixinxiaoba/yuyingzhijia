package com.manage.crm.service;

import com.manage.crm.entity.Project;
import com.manage.crm.util.Pagination;

import javacommon.core.base.BaseService;
import javacommon.util.SearchCondition;

import org.hibernate.criterion.Order;

public interface ProjectService extends BaseService<Project> {
	void getByCondition(Pagination<Project> paramPagination, SearchCondition paramSearchCondition, Order paramOrder);

	/**
	 * 根据项目标志获取项目信息
	 * 
	 * @param projectKey
	 * @return
	 */
	Project getProjectByProjectKey(String projectKey);
}