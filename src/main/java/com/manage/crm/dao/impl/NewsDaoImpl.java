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
		return this.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from News where mid=" + menuID + "  ORDER BY id desc LIMIT " + nNewsLen);
	}

	/**
	 * 获取指定个数的文章。
	 * 
	 * @return
	 */
	public List<News> lstScrollImgByMenuId(Long menuID, int nNewsLen) {
		return this.listBySql(" SELECT * FROM news s WHERE s.mid = " + menuID + " and s.`imageurl` IS NOT NULL and s.imageurl != '' ORDER BY s.`ID` DESC LIMIT " + nNewsLen);
	}

	/**
	 * 获取指定个数的文章。
	 * 
	 * @return
	 */
	public List<News> lstScrollImgByParMenuId(Long menuID, int nNewsLen) {
		return this.listBySql(" SELECT * FROM news s WHERE s.mid in (select id from projectmenu s2 where s2.parid='" + menuID + "') and s.`imageurl` IS NOT NULL and s.imageurl != '' ORDER BY s.`ID` DESC LIMIT " + nNewsLen);
	}

	/**
	 * 根据menu id 获取最新的news 。
	 * 
	 * @return
	 */
	public List<News> lstNewestNewsByMenuId(Long menuId, int nNewsNum) {
		return this.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from newstemp a  where a.parmid=" + menuId + " ORDER BY a.id desc LIMIT " + nNewsNum);
	}

	/**
	 * 根据指定tag获取news。
	 */
	public List<News> lstNewsByTagId(Long tagId, int nNewsNum) {
		// 设置推荐阅读栏。
		return this.listBySql(" select " + DBSql.getNewsColumnWithOutContentOne() + " from News a,newstagrela b where a.id=b.nid and b.ntid=" + tagId + " ORDER BY a.id desc LIMIT " + nNewsNum);
	}
	
	/**
	 * 获取二级分类下，阅读数最多的number条数据。
	 */
	public List<News> lstNewsMostReadingBySubMenuId(Long menuId, int nNewsNum) {
		// 加载育婴师 阅读排行
		return this.listBySql(" select a.* from News a,projectmenu b where a.mid=b.id and b.parid=" + menuId + " ORDER BY readNum desc LIMIT " + nNewsNum);
//		return this.listBySql(" select * from News where mid=" + menuId  + " and imageurl is not null and imageurl !='' ORDER BY id desc LIMIT " + nNewsNum);
	}
	
	/**
	 * 获取最新的带图片的news
	 */
	public List<News> lstNewestNewsWithImgBySubMenuId(Long menuId, int nNewsNum) {
		// 获取两个带图片的新闻
		return this.listBySql(" select * from News where mid=" + menuId + " and imageurl is not null and imageurl !='' ORDER BY id desc LIMIT " + nNewsNum);
	}
	
	/**
	 * 获取最新的带图片的news
	 */
	public List<News> lstNewestyLevelTwoMenuId(Long menuId, int nNewsNum) {
		// 获取两个带图片的新闻
		return this.listBySql(" select * from News where mid=" + menuId +" ORDER BY id desc LIMIT " + nNewsNum);
	}
		

}