package com.manage.crm.service.impl;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.dao.SuggestionDao;
import com.manage.crm.entity.Suggestion;
import com.manage.crm.service.SuggestionService;

@Service("suggestionService")
@Transactional
public class SuggestionServiceImpl extends BaseServiceImpl<Suggestion> implements
SuggestionService {	
	private static final Logger logger = LoggerFactory
			.getLogger(SuggestionServiceImpl.class);

	@Resource(name = "suggestionDao")
	private SuggestionDao objSuggestionDao;

	public BaseDao<Suggestion> getBaseDao() {
		return this.objSuggestionDao;
	}

}