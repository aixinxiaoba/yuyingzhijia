package com.manage.crm.dao.impl;

import javacommon.core.base.BaseDaoImpl;

import org.springframework.stereotype.Repository;

import com.manage.crm.dao.AttachsDao;
import com.manage.crm.entity.Attachs;

@Repository("attachsDao")
public class AttachsDaoImpl extends BaseDaoImpl<Attachs> implements AttachsDao {
}