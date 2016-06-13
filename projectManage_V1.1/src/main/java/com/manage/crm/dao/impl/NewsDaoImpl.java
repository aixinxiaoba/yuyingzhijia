package com.manage.crm.dao.impl;

import java.util.List;

import javacommon.core.base.BaseDaoImpl;

import org.springframework.stereotype.Repository;

import com.manage.crm.dao.NewsDao;
import com.manage.crm.entity.News;
import com.manage.crm.util.DBSql;
import com.manage.crm.util.Pagination;

@Repository("newsDao")
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao {

	/**
	 * 获取指定个数的文章。
	 * 
	 * @return
	 */
	public List<News> lstAppointNumNews(Long menuID, int nNewsLen) {
		List<News> lstNews;

		lstNews = this.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from News where mid=" + menuID + "  ORDER BY id desc LIMIT " + nNewsLen);

		return lstNews;
	}
	
	/**
	 * 获取指定个数的文章。
	 * 
	 * @return
	 */
	public List<News> lstScrollImgByMenuId(Long menuID, int nNewsLen) {
		List<News> lstNews;

		lstNews = this.listBySql(" SELECT * FROM news s WHERE s.mid = " + menuID + " and s.`imageurl` IS NOT NULL and s.imageurl != '' ORDER BY s.`ID` DESC LIMIT " + nNewsLen);

		return lstNews;
	}
	
	/**
	 * 获取指定个数的文章。
	 * 
	 * @return
	 */
	public List<News> lstScrollImgByParMenuId(Long menuID, int nNewsLen) {
		List<News> lstNews;

		lstNews = this.listBySql(" SELECT * FROM news s WHERE s.mid in (select id from projectmenu s2 where s2.parid='"+menuID+"') and s.`imageurl` IS NOT NULL and s.imageurl != '' ORDER BY s.`ID` DESC LIMIT " + nNewsLen);

		return lstNews;
	}

}