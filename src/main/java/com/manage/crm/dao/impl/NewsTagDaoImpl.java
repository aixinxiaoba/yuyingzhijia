package com.manage.crm.dao.impl;

import javacommon.core.base.BaseDaoImpl;

import org.springframework.stereotype.Repository;

import com.manage.crm.dao.NewsTagDao;
import com.manage.crm.entity.NewsTag;

@Repository("newsTagDao")
public class NewsTagDaoImpl extends BaseDaoImpl<NewsTag> implements NewsTagDao {
}