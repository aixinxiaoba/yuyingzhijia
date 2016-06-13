package com.manage.crm.dao.impl;

import javacommon.core.base.BaseDaoImpl;

import org.springframework.stereotype.Repository;

import com.manage.crm.dao.CommentsDao;
import com.manage.crm.entity.Comments;

@Repository("commentsDao")
public class CommentsDaoImpl extends BaseDaoImpl<Comments> implements CommentsDao {
}