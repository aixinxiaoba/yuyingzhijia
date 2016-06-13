package com.manage.crm.service.impl;

import java.util.List;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.dao.ProjectMenuDao;
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.service.ProjectMenuService;

@Service("projectMenuService")
@Transactional
public class ProjectMenuServiceImpl extends BaseServiceImpl<ProjectMenu> implements ProjectMenuService {
	private static final Logger logger = LoggerFactory.getLogger(ProjectMenuServiceImpl.class);

	@Resource(name = "projectMenuDao")
	private ProjectMenuDao objProjectMenuDao;

	public BaseDao<ProjectMenu> getBaseDao() {
		return this.objProjectMenuDao;
	}

	@Override
	public List<ProjectMenu> lstValidSubProjectMenu(long lProjectMenuID) {
		return objProjectMenuDao.lstValidSubProjectMenu(lProjectMenuID);
	}

	@Override
	public List<ProjectMenu> lstValidSubProjectMenu() {
		return objProjectMenuDao.lstValidSubProjectMenu();
	}

	@Override
	public List<ProjectMenu> lstValidProjectMenu() {
		return objProjectMenuDao.lstValidProjectMenu();
	}

	@Override
	public List<ProjectMenu> lstProjectMenuByProId(Long lProjectId) {
		return objProjectMenuDao.lstProjectMenuByProId(lProjectId);
	}
}