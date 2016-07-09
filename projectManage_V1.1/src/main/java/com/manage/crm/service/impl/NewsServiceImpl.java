package com.manage.crm.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javacommon.util.NetImageDisposal;
import javacommon.util.SearchCondition;
import javacommon.util.StringUtils;
import javacommon.util.freemarker.FreemarkerUtils;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.action.NewsManageAtion;
import com.manage.crm.dao.NewsDao;
import com.manage.crm.dao.ProjectDao;
import com.manage.crm.dao.ProjectMenuDao;
import com.manage.crm.entity.News;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.service.NewsService;
import com.manage.crm.util.DBSql;
import com.manage.crm.util.Pagination;

@Service("newsService")
@Transactional
public class NewsServiceImpl extends BaseServiceImpl<News> implements
		NewsService {
	private static final Logger logger = LoggerFactory
			.getLogger(NewsServiceImpl.class);

	@Resource(name = "newsDao")
	private NewsDao objNewsDao;

	@Resource(name = "projectDao")
	private ProjectDao objProjectDao;

	@Resource(name = "projectMenuDao")
	private ProjectMenuDao objProjectMenuDao;

	public BaseDao<News> getBaseDao() {
		return this.objNewsDao;
	}

	public String newsDel(String[] arrayNewsID) {
		if ((arrayNewsID == null) || (arrayNewsID.length == 0)) {
			return "没有获取到要删除的新闻信息";
		}

		try {
			News objNews = new News();
			for (int i = 0; i < arrayNewsID.length; i++) {
				objNews = (News) getById(Long.valueOf(Long
						.parseLong(arrayNewsID[i])));

				if (objNews != null) {
					if (!delete(objNews)) {
						return "出现错误，删除失败！";
					}
				}
			}
		} catch (Exception e) {
			logger.error("出现异常", e);
			return "出现异常";
		}

		return "success";
	}

	public String newsModify(News objNews) {
		News objNewsFromDB = new News();

		if ((objNews == null) || (objNews.getlId() == null)
				|| (objNews.getlId().longValue() <= 0L)) {
			return "无法获取到新闻信息！";
		}

		try {
			objNewsFromDB = (News) getById(objNews.getlId());

			if (objNewsFromDB == null) {
				return "出现错误，无法获取到新闻信息";
			}

			if (!StringUtils.isEmpty(objNews.getStrContent())) {
				objNewsFromDB.setStrContent(objNews.getStrContent());
			}

			if (!StringUtils.isEmpty(objNews.getStrTitle())) {
				objNewsFromDB.setStrTitle(objNews.getStrTitle());
			}

			// 设置项目id。
			if (objNews.getObjProject() == null
					|| objNews.getObjProject().getlId() <= 0) {
				return "无法获取项目信息，请刷新重试！";
			}
			Project objProject = objProjectDao.getById(objNews.getObjProject()
					.getlId());
			if (objProject == null) {
				return "无法获取项目信息，请刷新重试！";
			}

			// 设置项目id。
			if (objNews.getObjProjectMenu() == null
					|| objNews.getObjProjectMenu().getlId() <= 0) {
				return "无法获取菜单信息，请刷新重试！";
			}
			ProjectMenu objProjectMenu = objProjectMenuDao.getById(objNews
					.getObjProjectMenu().getlId());
			if (objProjectMenu == null) {
				return "无法获取项目菜单信息，请刷新重试！";
			}
			objNewsFromDB.setObjProject(objProject);
			objNewsFromDB.setObjProjectMenu(objProjectMenu);

			// 从网络上下载图片 start
			// 如果存在图片则判断是否需要进行下载图片操作。
			NetImageDisposal.startDownLoad(objNewsFromDB, NewsManageAtion.G_2K28);
			// end
			if (!update(objNewsFromDB)) {
				return "出现异常，请刷新重试！";
			}
			return "success";
		} catch (Exception e) {
			logger.error("出现异常", e);
		}
		return "出现异常！请刷新重试";
	}

	/**
	 * 新闻新增。
	 */
	public String newsAdd(News objNews) {
		if ((objNews == null)) {
			return "无法获取到新闻信息！";
		}

		try {
			if (StringUtils.isEmpty(objNews.getStrContent())) {
				return "请填写消息内容";
			}

			if (StringUtils.isEmpty(objNews.getStrTitle())) {
				return "请填写标题";
			}

			// 设置项目id。
			if (objNews.getObjProject() == null
					|| objNews.getObjProject().getlId() <= 0) {
				return "无法获取项目信息，请刷新重试！";
			}
			Project objProject = objProjectDao.getById(objNews.getObjProject()
					.getlId());
			if (objProject == null) {
				return "无法获取项目信息，请刷新重试！";
			}

			// 设置项目id。
			if (objNews.getObjProjectMenu() == null
					|| objNews.getObjProjectMenu().getlId() <= 0) {
				return "无法获取菜单信息，请刷新重试！";
			}
			ProjectMenu objProjectMenu = objProjectMenuDao.getById(objNews
					.getObjProjectMenu().getlId());
			if (objProjectMenu == null) {
				return "无法获取项目菜单信息，请刷新重试！";
			}
			objNews.setObjProject(objProject);
			objNews.setObjProjectMenu(objProjectMenu);
			// 网络图片处理
			NetImageDisposal.startDownLoad(objNews, NewsManageAtion.G_2K28);
			if (!save(objNews)) {
				return "出现异常，请刷新重试！";
			}
			return "success";
		} catch (Exception e) {
			logger.error("出现异常", e);
		}
		return "出现异常！请刷新重试";
	}

	@Cacheable(value = "andCache", key = "#objId + 'findById'")
	@Override
	public News getById(Serializable objId) {
		return super.getById(objId);
	}

	@Override
	public List<News> lstAppointNumNews(Long menuID, int nNewsLen) {
		return objNewsDao.lstAppointNumNews(menuID, nNewsLen);
	}
	
	/**
	 * 将所有的菜单分类封装七条数据。（处理相对较慢）
	 * @throws Exception 
	 */
	public void setAppointSubMenuNews(List<ProjectMenu> lstProjectMenu, FreemarkerUtils objFreemarkerUtils) throws Exception {
		Map<String, Object> mapInData = new HashMap<String, Object>();
		// 设置每个项目菜单的子菜单下显示新闻的数量。
		if (lstProjectMenu != null && lstProjectMenu.size() > 0)
		{
			List<News> lstCurNews;
			
			for (ProjectMenu curProjectMenu : lstProjectMenu)
			{
				// 每个分类封装七条数据
				lstCurNews = objNewsDao.lstAppointNumNews(curProjectMenu.getlId(), Pagination.PAGE_SIZE_CONTENT);
				curProjectMenu.setLstManualNews(lstCurNews);
				
				mapInData.put("curMenu", curProjectMenu);
				mapInData.put("lstNews", lstCurNews);
				// 生成静态化文件 文件命名：子菜单+文件名
				objFreemarkerUtils.createFile("mobile/sub_menu_news.ftl", mapInData, "static/m/newest/" + curProjectMenu.getlId() + ".html");
			}
			
		}
	}

	@Override
	public List<News> lstScrollImgByMenuId(Long menuID, int nNewsLen) {
		return objNewsDao.lstScrollImgByMenuId(menuID, nNewsLen);
	}

	@Override
	public void setAmScrollPhotoStatic(List<News> lstRollingOfReading, FreemarkerUtils objFreemarkerUtils, String menuId) throws Exception {
		Map<String, Object> mapInData = new HashMap<String, Object>();
		
		mapInData.put("lstRollingOfReading", lstRollingOfReading);
		// 生成静态化文件
		objFreemarkerUtils.createFile("mobile/sub_menu_news.ftl", mapInData, "static/m/scroll/" + menuId + ".html");
	}

	@Override
	public void scrollPhotoStatic(List<ProjectMenu> lstProjectMenu, FreemarkerUtils objFreemarkerUtils) throws Exception {
		Map<String, Object> mapInData = new HashMap<String, Object>();
		String strPath = "";
		// 设置每个项目菜单的子菜单下显示新闻的数量。
		if (lstProjectMenu != null && lstProjectMenu.size() > 0)
		{
			List<News> lstCurNews;
			
			for (ProjectMenu curProjectMenu : lstProjectMenu)
			{
				// 每个分类封装七条数据
				if (curProjectMenu.getLevel() == 2)
				{
					lstCurNews = objNewsDao.lstScrollImgByMenuId(curProjectMenu.getlId(), Pagination.PAGE_SIZE_5);

					strPath = "l2";// 二级菜单静态化使用
				}
				else
				{
					lstCurNews = objNewsDao.lstScrollImgByParMenuId(curProjectMenu.getlId(), Pagination.PAGE_SIZE_5);
				}
				mapInData.put("lstRollingOfReading", lstCurNews);
				// 生成静态化文件
				objFreemarkerUtils.createFile("mobile/sub_menu_scroll_news.ftl", mapInData, "static/m/scroll/" + strPath + "/" + curProjectMenu.getlId() + ".html");
			}
		}
	}

	@Override
	public List<News> lstScrollImgByParMenuId(Long menuID, int nNewsLen) {
		return objNewsDao.lstScrollImgByParMenuId(menuID, nNewsLen);
	}

	@Override
	public void subMenuPageStatic(List<ProjectMenu> lstProjectMenu, FreemarkerUtils objFreemarkerUtils) throws Exception {
		Map<String, Object> mapInData = new HashMap<String, Object>();
		// 设置每个项目菜单的子菜单下显示新闻的数量。
		if (lstProjectMenu != null && lstProjectMenu.size() > 0)
		{
			List<News> lstCurNews;
			
			for (ProjectMenu curProjectMenu : lstProjectMenu)
			{
				Pagination<News> objPagination = new Pagination<News>();
				long pageCount;
				String nPageSize = "10";
				
				pageCount = this.sizeBySql(" select id from news where mid= " + curProjectMenu.getlId());
				if (pageCount > 0)
				{
					objPagination.setTotal(pageCount);
					objPagination.setPageSize(Integer.parseInt(nPageSize));
					for (int i = 1; i <= objPagination.getMaxPage(); i++)
					{
						objPagination.setPageNo(i);
						// 组装查询条件。
						Criterion objSQLCondition = Restrictions.sqlRestriction(" mid =" + curProjectMenu.getlId());
						lstCurNews = this.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
						mapInData.put("nextPage", (i+1)<pageCount ? (i+1) + "" : null);
						mapInData.put("lastPage", i >  1 ? (i-1) + "" : null);
						mapInData.put("mid", curProjectMenu.getlId());
						mapInData.put("lstNews", lstCurNews);
						// 生成静态化文件 文件命名：子菜单+文件名
						objFreemarkerUtils.createFile("mobile/sub_menu_news_page.ftl", mapInData, "static/m/p/" + curProjectMenu.getlId() + "_" + i + ".html");
					}
					
					//Thread.sleep(1000*60);
				}
			}
			
		}
	}
	
	public void subMenuPageStatic(ProjectMenu objProjectMenu, FreemarkerUtils objFreemarkerUtils) throws Exception {
		Map<String, Object> mapInData = new HashMap<String, Object>();
		List<News> lstCurNews;
		Pagination<News> objPagination = new Pagination<News>();
		long pageCount;
		String nPageSize = "10";
		
		pageCount = this.sizeBySql(" select id from news where mid= " + objProjectMenu.getlId());
		if (pageCount > 0)
		{
			objPagination.setTotal(pageCount);
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			for (int i = 1; i <= objPagination.getMaxPage(); i++)
			{
				objPagination.setPageNo(i);
				// 组装查询条件。
				Criterion objSQLCondition = Restrictions.sqlRestriction(" mid =" + objProjectMenu.getlId());
				lstCurNews = this.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
				mapInData.put("nextPage", (i+1)<pageCount ? (i+1) + "" : null);
				mapInData.put("lastPage", i >  1 ? (i-1) + "" : null);
				mapInData.put("mid", objProjectMenu.getlId());
				mapInData.put("lstNews", lstCurNews);
				// 生成静态化文件 文件命名：子菜单+文件名
				objFreemarkerUtils.createFile("mobile/sub_menu_news_page.ftl", mapInData, "static/m/p/" + objProjectMenu.getlId() + "_" + i + ".html");
			}
		}
	}

	@Override
	public List<News> lstNewestNewsByMenuId(Long menuID, int nNewsNum) {
		return objNewsDao.lstNewestNewsByMenuId(menuID, nNewsNum);
	}

	@Override
	public List<News> lstNewsByTagId(Long tagId, int nNewsNum) {
		return objNewsDao.lstNewsByTagId(tagId, nNewsNum);
	}

	@Override
	public List<News> lstNewsMostReadingBySubMenuId(Long menuId, int nNewsNum) {
		return objNewsDao.lstNewsMostReadingBySubMenuId(menuId, nNewsNum);
	}

	@Override
	public List<News> lstNewestNewsWithImgBySubMenuId(Long menuId, int nNewsNum) {
		return objNewsDao.lstNewestNewsWithImgBySubMenuId(menuId, nNewsNum);
	}
	
	@Override
	public List<News> lstNewestyLevelTwoMenuId(Long menuId, int nNewsNum) {
		return objNewsDao.lstNewestyLevelTwoMenuId(menuId, nNewsNum);
	}
}