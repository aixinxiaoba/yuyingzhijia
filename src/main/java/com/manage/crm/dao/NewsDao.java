package com.manage.crm.dao;

import java.util.List;

import javacommon.core.base.BaseDao;

import com.manage.crm.entity.News;

public interface NewsDao extends BaseDao<News>
{
	List<News> lstAppointNumNews(Long menuID, int nNewsLen);
	
	List<News> lstScrollImgByMenuId(Long menuID, int nNewsLen);
	
	List<News> lstScrollImgByParMenuId(Long menuID, int nNewsLen);

	List<News> lstNewestNewsByMenuId(Long menuId, int nNewsNum);
	
	List<News> lstNewsByTagId(Long tagId, int nNewsNum);
	
	List<News> lstNewsMostReadingBySubMenuId(Long menuId, int nNewsNum);
	
	List<News> lstNewestNewsWithImgBySubMenuId(Long menuId, int nNewsNum);
	
	List<News> lstNewestyLevelTwoMenuId(Long menuId, int nNewsNum);
}