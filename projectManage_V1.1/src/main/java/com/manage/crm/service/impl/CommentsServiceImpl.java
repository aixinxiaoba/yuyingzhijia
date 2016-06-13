package com.manage.crm.service.impl;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.dao.CommentsDao;
import com.manage.crm.entity.Comments;
import com.manage.crm.service.CommentsService;

@Service("commentsService")
@Transactional
public class CommentsServiceImpl extends BaseServiceImpl<Comments> implements CommentsService {
	private static final Logger logger = LoggerFactory.getLogger(CommentsServiceImpl.class);

	@Resource(name = "commentsDao")
	private CommentsDao objCommentsDao;

	public BaseDao<Comments> getBaseDao() {
		return this.objCommentsDao;
	}

}