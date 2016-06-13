package com.manage.crm.dao.impl;

import javacommon.core.base.BaseDaoImpl;

import org.springframework.stereotype.Repository;

import com.manage.crm.dao.VisitLogDao;
import com.manage.crm.entity.VisitLog;

@Repository("visitLogDao")
public class VisitLogDaoImpl extends BaseDaoImpl<VisitLog> implements VisitLogDao {
}