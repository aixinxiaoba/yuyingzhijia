package com.manage.crm.service.impl;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.dao.NewsTagDao;
import com.manage.crm.entity.NewsTag;
import com.manage.crm.service.NewsTagService;

@Service("newsTagService")
@Transactional
public class NewsTagServiceImpl extends BaseServiceImpl<NewsTag> implements
		NewsTagService {
	private static final Logger logger = LoggerFactory
			.getLogger(NewsTagServiceImpl.class);

	@Resource(name = "newsTagDao")
	private NewsTagDao objNewsTagDao;

	public BaseDao<NewsTag> getBaseDao() {
		return this.objNewsTagDao;
	}

}