package com.manage.crm.service.impl;

import com.manage.crm.dao.ProjectDao;
import com.manage.crm.entity.Project;
import com.manage.crm.service.ProjectService;
import com.manage.crm.util.Pagination;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javacommon.util.SearchCondition;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectService")
@Transactional
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {
	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Resource(name = "projectDao")
	private ProjectDao objProjectDao;

	public BaseDao<Project> getBaseDao() {
		return this.objProjectDao;
	}

	public void getByCondition(Pagination<Project> objPagination, SearchCondition objSearchCondition, Order objOrder) {
		listByCriteria(objPagination, objSearchCondition, objOrder);
	}

	@Override
	public Project getProjectByProjectKey(String projectKey) {
		return objProjectDao.getProjectByProjectKey(projectKey);
	}
}