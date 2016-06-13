package com.manage.crm.dao;

import java.util.List;

import javacommon.core.base.BaseDao;

import com.manage.crm.entity.News;

public interface NewsDao extends BaseDao<News>
{
	public List<News> lstAppointNumNews(Long menuID, int nNewsLen);
	
	public List<News> lstScrollImgByMenuId(Long menuID, int nNewsLen);
	
	public List<News> lstScrollImgByParMenuId(Long menuID, int nNewsLen);

	
}