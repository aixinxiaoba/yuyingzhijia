package com.manage.crm.service.impl;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.dao.AttachsDao;
import com.manage.crm.entity.Attachs;
import com.manage.crm.service.AttachsService;

@Service("attachsService")
@Transactional
public class AttachsServiceImpl extends BaseServiceImpl<Attachs> implements
AttachsService {
	private static final Logger logger = LoggerFactory
			.getLogger(AttachsServiceImpl.class);

	@Resource(name = "attachsDao")
	private AttachsDao objAttachsDao;

	public BaseDao<Attachs> getBaseDao() {
		return this.objAttachsDao;
	}

}