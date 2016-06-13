package com.manage.crm.service.impl;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.dao.NewsTempDao;
import com.manage.crm.entity.NewsTemp;
import com.manage.crm.service.NewsTempService;

@Service("newsTempService")
@Transactional
public class NewsTempServiceImpl extends BaseServiceImpl<NewsTemp> implements
		NewsTempService {
	private static final Logger logger = LoggerFactory
			.getLogger(NewsTempServiceImpl.class);

	@Resource(name = "newsTempDao")
	private NewsTempDao objNewsTempDao;

	public BaseDao<NewsTemp> getBaseDao() {
		return this.objNewsTempDao;
	}
}