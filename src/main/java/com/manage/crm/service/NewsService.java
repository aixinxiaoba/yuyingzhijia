package com.manage.crm.service;

import java.util.List;

import javacommon.core.base.BaseService;
import javacommon.util.freemarker.FreemarkerUtils;

import com.manage.crm.entity.News;
import com.manage.crm.entity.ProjectMenu;

public interface NewsService extends BaseService<News> {
	String newsDel(String[] paramArrayOfString);

	String newsModify(News paramNews);

	String newsAdd(News paramNews);

	List<News> lstAppointNumNews(Long menuID, int nNewsLen);
	
	void setAppointSubMenuNews(List<ProjectMenu> lstProjectMenu, FreemarkerUtils objFreemarkerUtils) throws Exception;
	
	List<News> lstScrollImgByMenuId(Long menuID, int nNewsLen);

	void setAmScrollPhotoStatic(List<News> lstRollingOfReading, FreemarkerUtils objFreemarkerUtils, String strMenuName) throws Exception;

	void scrollPhotoStatic(List<ProjectMenu> lstProjectMenu, FreemarkerUtils objFreemarkerUtils) throws Exception ;
	
	List<News> lstScrollImgByParMenuId(Long menuID, int nNewsLen);

	void subMenuPageStatic(List<ProjectMenu> lstProjectMenu, FreemarkerUtils objFreemarkerUtils) throws Exception;
	
	List<News> lstNewestNewsByMenuId(Long menuID, int nNewsNum);
	
	List<News> lstNewsByTagId(Long tagId, int nNewsNum);
	
	List<News> lstNewsMostReadingBySubMenuId(Long menuId, int nNewsNum);
	
	List<News> lstNewestNewsWithImgBySubMenuId(Long menuId, int nNewsNum);
	
	List<News> lstNewestyLevelTwoMenuId(Long menuId, int nNewsNum);
	
}