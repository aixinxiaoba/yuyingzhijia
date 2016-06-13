package com.manage.crm.dao.impl;

import javacommon.core.base.BaseDaoImpl;

import org.springframework.stereotype.Repository;

import com.manage.crm.dao.SuggestionDao;
import com.manage.crm.entity.Suggestion;

@Repository("suggestionDao")
public class SuggestionDaoImpl extends BaseDaoImpl<Suggestion> implements SuggestionDao {
}