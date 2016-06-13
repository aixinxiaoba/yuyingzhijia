package com.manage.crm.service.impl;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.dao.VisitLogDao;
import com.manage.crm.entity.VisitLog;
import com.manage.crm.service.VisitLogService;

@Service("visitLogService")
@Transactional
public class VisitLogServiceImpl extends BaseServiceImpl<VisitLog> implements
		VisitLogService {
	private static final Logger logger = LoggerFactory
			.getLogger(VisitLogServiceImpl.class);

	@Resource(name = "visitLogDao")
	private VisitLogDao objVisitLogDao;

	public BaseDao<VisitLog> getBaseDao() {
		return this.objVisitLogDao;
	}

}