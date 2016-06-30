package com.manage.crm.action;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.core.Config;
import javacommon.core.base.BaseStruts2Action;
import javacommon.util.SearchCondition;
import javacommon.util.StringUtils;
import javacommon.util.file.FileUtils;
import javacommon.util.freemarker.FreemarkerUtils;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.manage.crm.entity.News;
import com.manage.crm.entity.NewsTag;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.entity.VisitLog;
import com.manage.crm.service.NewsService;
import com.manage.crm.service.NewsTagService;
import com.manage.crm.service.ProjectMenuService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.VisitLogService;
import com.manage.crm.util.IPUtil;
import com.manage.crm.util.Pagination;
import com.opensymphony.xwork2.Action;

@Controller("indexManageAction")
@Scope("prototype")
public class IndexManageAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(IndexManageAtion.class);

	private static final String INDEX = "index";
	
	private static final String LIST_SEARCH = "list_search";

	@Resource(name = "projectService")
	private ProjectService objProjectService;
	
	@Resource(name = "projectMenuService")
	private ProjectMenuService objProjectMenuService;
	
	@Resource(name = "newsService")
	private NewsService objNewsService;
	
	@Resource(name = "visitLogService")
	private VisitLogService objVisitLogService;
	
	@Resource(name = "newsTagService")
	private NewsTagService objNewsTagService;
	
	private Project objProject;
	private ProjectMenu objProjectMenu;
	
	private List<ProjectMenu> lstProjectMenu;
	
	private List<ProjectMenu> lstChildMenu; // 所有子菜单(边缘显示菜单)
	
	// 最新公告。
	private List<News> lstNewestMessage;
	
	private NewsTag objNewsTag; // 标签
	
	private String projectKey;
	private String projectMenuKey;
	private Long lProjectMenuID;
	
	private News objNews;
	private Long newsID;
	
	private String menuKey;
	
	private Long lProjectID;
	
	private Pagination<News> objGPagination;
	
	private String strCurMenu; // 选中菜单
	
	// 标签id。
	private Long newsTag;
	
	private News objUpNews;
	
	private News objNextNews;
	
	// 推荐阅读。
	private List<News> lstSuggestReading;
	
	private List<News> lstTopOfReading; // 阅读排行
	
	private List<News> lstRollingOfReading; // 滚动新闻。
	
	private List<News> lstYuErZiXun; // 滚动新闻。
	
	private String searchText; // 查询条件
	
	/**
	 * 考生图片。
	 */
	private File objFileData;
	private String strFileDataName;
	
	private String searchParam;

	public File getObjFileData() {
		return objFileData;
	}

	public void setObjFileData(File objFileData) {
		this.objFileData = objFileData;
	}

	public String getStrFileDataName() {
		return strFileDataName;
	}

	public void setStrFileDataName(String strFileDataName) {
		this.strFileDataName = strFileDataName;
	}

	public String index()
	{
		return projectIndex();
		//return "index";
	}
	
	/**
	 * 网站首页。
	 * 
	 * @return
	 */
	public String projectIndex()
	{
		try {
			// 验证项目是否合法
			if (!setProjectInfo())
			{
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return this.commonError();
			}
			
			// 获取请求的域名。
//			if ("m.yuyingzhijia.cn".equals(getRequest().getServerName()))
//			{
//				mobileIndex();
//				return "web_mobile";
//			}
			// 判断是否为移动端访问。
			if (this.isMobileDevice())
			{
				mobileIndex();
				return "web_mobile";
			}
			
			// 设置项目菜单
			setProjectMenu();
			setProjectSubMenuNews();
			
			// 加载最新动态 获取前6个
			this.lstNewestMessage = objNewsService.listBySql(" select * from NewsTemp ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_6);
			
			// 设置推荐阅读栏。
			this.lstSuggestReading = objNewsService.listBySql(" select a.* from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_SUGGESTIONREADING + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			
			// 阅读量排行 加载前六个。
			this.lstTopOfReading = objNewsService.listBySql(" select * from News ORDER BY readNum desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
			
			// 滚动图片 加载前5个(id倒序)。Rolling 
			this.lstRollingOfReading = this.objNewsService.listBySql(" select * from News a where a.imageurl is not null and imageurl !='' order by a.id desc limit 5");
			//this.lstRollingOfReading = objNewsService.listBySql(" select * from News a,attachs b where a.id=b.nid ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_5);
			// 最新发布滚动图片五个
//			this.lstRollingOfReading = objNewsService.listBySql(" select * from News a where id=50230");
			// 设置选中菜单
			strCurMenu = "index"; // 网站首页。
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "toIndex";
	}
	
	/**
	 * 移动端首页。
	 * 
	 * @return
	 */
	public void mobileIndex()
	{
		try {
			// 设置项目菜单
			lstProjectMenu = this.objProjectMenuService.listByHql(" from ProjectMenu where objProject.lId='" + this.objProject.getlId() + "' and validate=1 and level=1 and menuKey !='aboutUs'");
			
			// 加载最新动态 获取前6个
			this.lstNewestMessage = objNewsService.listBySql(" select * from NewsTemp ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_6);
			// 设置推荐阅读栏。
			this.lstSuggestReading = objNewsService.listBySql(" select a.* from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_SUGGESTIONREADING + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			// 阅读量排行 加载前六个。
			this.lstTopOfReading = objNewsService.listBySql(" select * from News ORDER BY readNum desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
			// 滚动图片 加载前5个(id倒序)。Rolling 
			this.lstRollingOfReading = objNewsService.listBySql("  SELECT * FROM news s WHERE s.`imageurl` IS NOT NULL and s.imageurl != '' ORDER BY s.`ID` DESC LIMIT 5 ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 网站首页。
	 * 
	 * @return
	 */
	public void indexStatic()
	{
		try {
			// 验证项目是否合法
			if (!setProjectInfo())
			{
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return;
			}
			
			// 设置项目菜单
			setProjectMenu();
			setProjectSubMenuNews();
			
			// 加载最新动态 获取前6个
			this.lstNewestMessage = objNewsService.listBySql(" select * from News ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_6);
			
			// 设置推荐阅读栏。
			this.lstSuggestReading = objNewsService.listBySql(" select a.* from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_SUGGESTIONREADING + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			
			// 阅读量排行 加载前六个。
			this.lstTopOfReading = objNewsService.listBySql(" select * from News ORDER BY readNum desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
			
			// 滚动图片 加载前5个(id倒序)。Rolling 
			this.lstRollingOfReading = this.objNewsService.listBySql(" select * from News a where a.imageurl is not null and imageurl !='' order by a.id desc limit 5");
//			this.lstRollingOfReading = objNewsService.listBySql(" select * from News a,attachs b where a.id=b.nid ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_5);
		
			// 设置选中菜单
			strCurMenu = "index"; // 网站首页。
						
			// 生成静态化文件
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			Map<String, Object> mapInData = new HashMap<String, Object>();
			
			// 项目
			mapInData.put("objProject", this.objProject);
			// 项目菜单
			mapInData.put("lstProjectMenu", this.lstProjectMenu);
			// 项目子菜单
			mapInData.put("lstChildMenu", this.lstChildMenu);
			// 加载最新动态 获取前6个
			mapInData.put("lstNewestMessage", this.lstNewestMessage);
			// 设置推荐阅读栏。
			mapInData.put("lstSuggestReading", this.lstSuggestReading);
			// 阅读量排行 加载前六个。
			mapInData.put("lstTopOfReading", this.lstTopOfReading);
			// 滚动图片 加载前5个(id倒序)。Rolling 
			mapInData.put("lstRollingOfReading", this.lstRollingOfReading);
			// 设置选中菜单
			mapInData.put("strCurMenu", this.strCurMenu);
			// 查询条件。
			mapInData.put("searchText", this.searchText == null ? "" : this.searchText);
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objFreemarkerUtils.createFile("index.ftl", mapInData, "index.html");
			
			toWeb(Action.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 获取指定菜单下新闻信息。
//	 * 
//	 * @return
//	 */
//	public String newsList()
//	{
//		String nPageSize = getRequest().getParameter("rows"); // 每页显示多少个数据。
//		String nCurrentPage = getRequest().getParameter("page");// 第几页。
//		
//		// 获取项目子菜单。
//		objSubProjectMenu = this.objProjectMenuService.getByHql(" from ProjectMenu where id='" + this.menuID + "'");
//		if (objSubProjectMenu == null) {
//			setErrorText("无法获取您的菜单信息，请检查后重试！");
//			return COMMON_ERROR;
//		}
//		
//		// 获取项目主菜单。
//		this.objProjectMenu = this.objSubProjectMenu.getObjParentProjectMenu();
//		
//		// 加载项目信息。
//		this.objProject=objProjectMenu.getObjProject();
//		
//		// 加载有效的子菜单
//		setSubProjectMenu(lProjectMenuID);
//		
//		if (objProject == null) {
//			setErrorText("无法获取您的项目信息，请检查后重试！");
//			return COMMON_ERROR;
//		}
//		
//		try {
//			Pagination<News> objPagination = new Pagination<News>();
//			
//			// 加载育婴师最新发布动态 获取前9个
//			setNewestMessage();
//			
//			// 设置推荐阅读栏目。
//			setSuggestReading();
//			
//			// 根据菜单id获取菜单下信息列表。
//			if (nPageSize == null || nPageSize.length() <= 0)
//			{
//				nPageSize = "10";
//			}
//			if (nCurrentPage == null || nCurrentPage.length() <= 0)
//			{
//				nCurrentPage = "1";
//			}
//			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
//			objPagination.setPageSize(Integer.parseInt(nPageSize));
//			logger.info("当前页数：" + nCurrentPage + "--每页显示的个数为：" + nPageSize);
//			
//			// 组装查询条件。
//			Criterion objSQLCondition = Restrictions.sqlRestriction(" mid ='" + objSubProjectMenu.getlId() + "'");
//			objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
//			this.objGPagination = objPagination;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		// 设置当前选中菜单。
//		strCurMenu = objSubProjectMenu.getMenuKey(); // 默认当前菜单页面
//		
//		return "newsList";
//	}
	
	/**
	 * 设置项目子菜单下新闻。
	 */
	private void setProjectSubMenuNews() {
		// 设置每个项目菜单的子菜单下显示新闻的数量。
		if (this.lstProjectMenu != null && lstProjectMenu.size() > 0)
		{
			Set<ProjectMenu> setCurChildProjectMenu;
			ProjectMenu objCurProjectMenu;
			List<News> lstCurNews;
			
			for (ProjectMenu parentProjectMenu : lstProjectMenu)
			{
				setCurChildProjectMenu = parentProjectMenu.getLstChildrenProjectMenu();
				
				if (setCurChildProjectMenu != null && setCurChildProjectMenu.size() > 0)
				{
					Iterator<ProjectMenu> iteProjectMenu = setCurChildProjectMenu.iterator();
					
					while (iteProjectMenu.hasNext()) {
						objCurProjectMenu = iteProjectMenu.next();
						if (objCurProjectMenu.getShowIndex() == 1)
						{
							String strNewsID = "";
							
							// 获取两个带图片的新闻
							List<News> lstImageNews = objNewsService.listBySql(" select * from News where mid=" + objCurProjectMenu.getlId() + " and imageurl is not null and imageurl !='' ORDER BY id desc LIMIT 1");
							
							if (lstImageNews != null && lstImageNews.size() > 0)
							{
								objCurProjectMenu.setLstImageNews(lstImageNews);
								strNewsID += " and id not in ";
								for (int i = 0; i < lstImageNews.size(); i++)
								{
									if (i == 0)
									{
										strNewsID += "(" + lstImageNews.get(i).getlId();
									}
									else
									{
										strNewsID += "," + lstImageNews.get(i).getlId();
									}
								}
								strNewsID+=")";
							}
							
							// 获取7条数据封装到 边缘菜单中
							lstCurNews = objNewsService.listBySql(" select * from News where mid=" + objCurProjectMenu.getlId() + strNewsID +" ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
							objCurProjectMenu.setLstNews(new HashSet<News>(lstCurNews));
						}
						else if (objCurProjectMenu.getShowIndex() == 2)
						{
							String strNewsID = "";
							// 获取两个带图片的新闻
							List<News> lstImageNews = objNewsService.listBySql(" select * from News where mid=" + objCurProjectMenu.getlId() + " and imageurl is not null  and imageurl !='' ORDER BY id desc LIMIT 1");
							
							if (lstImageNews != null && lstImageNews.size() > 0)
							{
								objCurProjectMenu.setLstImageNews(lstImageNews);
								strNewsID += " and id not in ";
								for (int i = 0; i < lstImageNews.size(); i++)
								{
									if (i == 0)
									{
										strNewsID += "(" + lstImageNews.get(i).getlId();
									}
									else
									{
										strNewsID += "," + lstImageNews.get(i).getlId();
									}
								}
								strNewsID+=")";
							}
							
							lstCurNews = objNewsService.listBySql(" select * from News where mid=" + objCurProjectMenu.getlId() +strNewsID + " ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
							objCurProjectMenu.setLstNews(new HashSet<News>(lstCurNews));
							
							if (lstChildMenu == null)
							{
								lstChildMenu = new ArrayList<ProjectMenu>();
							}
							lstChildMenu.add(objCurProjectMenu);
						}
					}
				}
			}
		}
	}

	/**
	 * 获取指定标签下新闻信息。
	 * 
	 * @return
	 */
	public String newsListByTag()
	{
		String nPageSize = getRequest().getParameter("rows"); // 每页显示多少个数据。
		String nCurrentPage = getRequest().getParameter("page");// 第几页。
		
		// 获取消息详细信息。
		if ((this.newsTag == null) || (this.newsTag.longValue() <= 0L)) {
			setErrorText("没有获取到指定标签参数，请检查网址是否正确！");
			return "commonError";
		}
		
		try {
			// 根据标签id获取标签名称。
			this.objNewsTag = objNewsTagService.getById(newsTag);
			
			// 设置项目信息
			if (!setProjectInfo())
			{
				setErrorText("无法获取项目信息，请检查地址后重试！");
				return this.commonError();
			}
			// 加载有效的子菜单
			setProjectMenu();
			
			// 加载最新动态 获取前9个
			Pagination<News> objNewsetPagination = new Pagination<News>(Pagination.PAGE_SIZE_9, 1);
			objNewsService.listByCriteria(objNewsetPagination, null, Order.desc("strSendDate"));
			lstNewestMessage = objNewsetPagination.getRows();
			
			// 根据菜单id获取菜单下信息列表。
			Pagination<News> objPagination = new Pagination<News>();
			if (nPageSize == null || nPageSize.length() <= 0)
			{
				nPageSize = "10";
			}
			if (nCurrentPage == null || nCurrentPage.length() <= 0)
			{
				nCurrentPage = "1";
			}
			
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			logger.info("当前页数：" + nCurrentPage + "--每页显示的个数为：" + nPageSize);
			
			// 组装查询条件。
			Criterion objSQLCondition = Restrictions.sqlRestriction(" id in(select s.nid from newstagrela s where s.ntid='" + this.newsTag + "')");
			objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
			this.objGPagination = objPagination;
			
			// 设置推荐阅读栏。lstSuggestReading
			NewsTag objNewsTag = objNewsTagService.getById(NewsTag.G_SUGGESTIONREADING);
			this.lstSuggestReading = new ArrayList<News>(objNewsTag.getLstNews());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "newsList";
	}
	
	/**
	 * 设置项目信息。
	 */
	private boolean setProjectInfo()
	{
		if (this.projectKey == null || "".equals(this.projectKey))
		{
			this.projectKey="yuyingzhijia";
		}
		// 根据项目标志获取项目信息
		objProject = this.objProjectService.getByHql(" from Project where projectKey='" + this.projectKey + "'");
		if (objProject == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 设置项目菜单。
	 */
	private void setProjectMenu()
	{
		// 加载首页需要显示的菜单(只显示有效菜单)。
		lstProjectMenu = this.objProjectMenuService.listByHql(" from ProjectMenu where objProject.lId='" + this.objProject.getlId() + "' and validate=1 and level=1");
	}
	
	/**
	 * 菜单信息展示。
	 * 
	 * @return
	 */
	public String menuShow()
	{
		if (this.lProjectMenuID == null || this.lProjectMenuID <= 0)
		{
			setErrorText("无法获取您的项目菜单信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		objProjectMenu = this.objProjectMenuService.getByHql(" from ProjectMenu where id='" + this.lProjectMenuID + "'");
		if (objProjectMenu == null) {
			setErrorText("无法获取您的菜单信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		this.objProject=objProjectMenu.getObjProject();
		setProjectMenu();
		
		if (objProject == null) {
			setErrorText("无法获取您的项目信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		if (this.isMobileDevice())
		{
			String nPageSize = getRequest().getParameter("rows"); // 每页显示多少个数据。
			String nCurrentPage = getRequest().getParameter("page");// 第几页。
			
			try {
				// 根据菜单id获取菜单下信息列表。
				Pagination<News> objPagination = new Pagination<News>();
				if (nPageSize == null || nPageSize.length() <= 0)
				{
					nPageSize = "10";
				}
				if (nCurrentPage == null || nCurrentPage.length() <= 0)
				{
					nCurrentPage = "1";
				}
				objPagination.setPageNo(Integer.parseInt(nCurrentPage));
				objPagination.setPageSize(Integer.parseInt(nPageSize));
				logger.info("当前页数：" + nCurrentPage + "--每页显示的个数为：" + nPageSize);
				
				// 组装查询条件。
				Criterion objSQLCondition = Restrictions.sqlRestriction(" mid ='" + this.lProjectMenuID + "'");
				objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
				this.objGPagination = objPagination;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "M_ToMenuShow";
		}
		else
		{
			return "toMenuShow";
		}
		
	}
	
	/**
	 * 新闻阅读。
	 * 
	 * @return
	 */
	public String newsRead() {
		// 获取消息详细信息。
		if ((this.newsID == null) || (this.newsID.longValue() <= 0L)) {
			setErrorText("没有获取到要查看的文章");
			return "commonError";
		}
		
		objNews = objNewsService.getById(this.newsID);
		if (objNews == null) {
			setErrorText("无法获取您要阅读的信息，请检查地址无误后重试！");
			return COMMON_ERROR;
		}
		
		objProjectMenu = objNews.getObjProjectMenu().getObjParentProjectMenu();
		// 获取项目主菜单。
		if (objProjectMenu == null) {
			setErrorText("无法获取您的菜单信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		// 加载项目信息。
		this.objProject=objProjectMenu.getObjProject();
		
		// 加载有效的子菜单
		setSubProjectMenu(objProjectMenu.getlId());
		
		if (objProject == null) {
			setErrorText("无法获取您的项目信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		// 加载育婴师最新发布动态 获取前9个
		Pagination<News> objPagination = new Pagination<News>(Pagination.PAGE_SIZE_9, 1);
		Criterion objSQLCondition = Restrictions.sqlRestriction(" mid in(select id from projectmenu where parid=" + this.lProjectMenuID + ")");
		objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
		lstNewestMessage = objPagination.getRows();
		
		// 设置推荐阅读栏。lstSuggestReading
		NewsTag objNewsTag = objNewsTagService.getById(NewsTag.G_SUGGESTIONREADING);
		this.lstSuggestReading = new ArrayList<News>(objNewsTag.getLstNews());
		
		try {
			// 获取上一页第一个数据。
			Pagination<News> objUpPagination = new Pagination<News>(1, 1);
			Criterion objUpSQLCondition = Restrictions.sqlRestriction(" id < " + newsID);
			objNewsService.listByCriteria(objUpPagination, new SearchCondition(objUpSQLCondition, null), Order.desc("strSendDate"));
			if (objUpPagination.getRows() != null && objUpPagination.getRows().size() > 0)
			{
				this.objUpNews = objUpPagination.getRows().get(0);
			}
			
			// 获取下一页第一个数据。
			Pagination<News> objNextPagination = new Pagination<News>(1, 1);
			Criterion objNextSQLCondition = Restrictions.sqlRestriction(" id > " + newsID);
			objNewsService.listByCriteria(objNextPagination, new SearchCondition(objNextSQLCondition, null), Order.desc("strSendDate"));
			if (objNextPagination.getRows() != null && objNextPagination.getRows().size() > 0)
			{
				this.objNextNews = objNextPagination.getRows().get(0);
			}
			
			this.objNews = this.objNewsService.getById(this.newsID);
			if (this.objNews == null) {
				setErrorText("无法获取到文章信息，请检查您的网址是否正确！");
				return "commonError";
			}
			
			// 判断是否是移动端查看，如果为移动端则自动设置为手机查看模式。
			if (isMobileDevice())
			{
				return "m_detail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 设置当前选中菜单。
		return "detail";
	}
	
	/**
	 * 设置项目子菜单。
	 */
	private void setSubProjectMenu(long lProjectMenuID)
	{
		// 加载首页需要显示的菜单(只显示有效菜单)。
		lstProjectMenu = this.objProjectMenuService.listByHql(" from ProjectMenu where objParentProjectMenu.lId=" + lProjectMenuID + " and validate=1 and level=2");
	}
	
	/**
	 * 关于我们。
	 * 
	 * @return
	 */
	public String aboutUs() {
		
		if (StringUtils.isEmpty(projectKey))
		{
			setErrorText("无法获取您的项目信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		// 根据项目标志获取项目信息
		if (!setProjectInfo())
		{
			return COMMON_ERROR;
		}
		// 加载首页需要显示的菜单(只显示有效菜单)。
		setProjectMenu();
		
		strCurMenu = "aboutUs"; // 关于我们
		
		try {
			// 生成静态化文件
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			Map<String, Object> mapInData = new HashMap<String, Object>();
			
			// 项目
			mapInData.put("objProject", this.objProject);
			// 项目菜单
			mapInData.put("lstProjectMenu", this.lstProjectMenu);
			// 设置选中菜单
			mapInData.put("strCurMenu", this.strCurMenu);
			
			// 查询条件。
			mapInData.put("searchText", this.searchText == null ? "" : this.searchText);
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objFreemarkerUtils.createFile("aboutUs.ftl", mapInData, "static/menu/bm/aboutUs");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "aboutUs";
	}

	/**
	 * 查询。
	 * 
	 * @return
	 */
	public String search()
	{
		if (StringUtils.isEmpty(searchParam))
		{
			setErrorText("请输入查询条件！");
			return COMMON_ERROR;
		}
		if (lProjectID != null && lProjectID <= 0)
		{
			setErrorText("无法获取您的项目信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		// 根据项目标志获取项目信息
		if (!setProjectInfo())
		{
			return COMMON_ERROR;
		}
		setProjectMenu();	
		
		return LIST_SEARCH;
	}
	
	public void photoUpload() throws IOException
	{
		// 定义参数
		String strFilePath;

		// 复制单个照片到指定路径
		strFilePath = "temp" + File.separator + "photo" + File.separator + strFileDataName;

		try
		{
			FileUtils.copyFile(objFileData.getAbsolutePath(), Config.objSAASConfig.getProperty("ROOT_PATH") + strFilePath);
		}
		catch (Exception e)
		{
			logger.error("复制文件出错", e);
			toWeb("error_复制文件出错");
			return;
		}

		toWeb(strFilePath);
		return;
	}
	
	/**
	 * 新闻搜索。
	 * 
	 * @return
	 */
	public String newsSearch()
	{
		String nPageSize = getRequest().getParameter("rows"); // 每页显示多少个数据。
		String nCurrentPage = getRequest().getParameter("page");// 第几页。
		
		try {
			if (searchText == null || "".equals(searchText.trim()))
			{
				setErrorText("请输入查询条件！");
				return "commonError";
			}
			
			// 根据项目标志获取项目信息
			if (!setProjectInfo())
			{
				setErrorText("无法获取项目信息！");
				return COMMON_ERROR;
			}
			
			// 设置项目猜到你
			setProjectMenu();
			
			// 加载最新动态 获取前9个
			Pagination<News> objNewstPagination = new Pagination<News>(Pagination.PAGE_SIZE_9, 1);
			objNewsService.listByCriteria(objNewstPagination, null, Order.desc("strSendDate"));
			lstNewestMessage = objNewstPagination.getRows();
			
			// 根据菜单id获取菜单下信息列表。
			Pagination<News> objPagination = new Pagination<News>();
			if (nPageSize == null || nPageSize.length() <= 0)
			{
				nPageSize = "10";
			}
			if (nCurrentPage == null || nCurrentPage.length() <= 0)
			{
				nCurrentPage = "1";
			}
			
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			logger.info("当前页数：" + nCurrentPage + "--每页显示的个数为：" + nPageSize);
			// 组装查询条件。
			Criterion objSQLCondition = Restrictions.sqlRestriction(" ncontent like '%" + searchText + "%' or title like '%"+searchText+"%'");
			objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
			this.objGPagination = objPagination;
			
			// 设置推荐阅读栏。lstSuggestReading
			NewsTag objNewsTag = objNewsTagService.getById(NewsTag.G_SUGGESTIONREADING);
			this.lstSuggestReading = new ArrayList<News>(objNewsTag.getLstNews());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "search";
	}
	
	/**
	 * 设置用户访问日志。
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void visitLog() throws IOException
	{
		VisitLog objVisitLog = new VisitLog();
		String strRefer = getRequest().getParameter("refer");
		String strThisPage = getRequest().getParameter("thisPage");
		
		objVisitLog.setStrStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		objVisitLog.setStrIP(IPUtil.getIpAddr(getRequest()));
		objVisitLog.setStrRefer(strRefer);
		objVisitLog.setStrThisPage(strThisPage);
		objVisitLogService.save(objVisitLog);
		logger.info("==========" + (isMobileDevice() ? "当前用户为移动端访问" : "当前为pc端访问"+ "========="));
		logger.info("==========当前用户访问ip为" + IPUtil.getIpAddr(getRequest()) + "===============");
		logger.info("==========当前用户访问页面为：" + strThisPage + "=====================");
	}
	
	/**
	 * 设置阅读量。
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void setReadNum() throws IOException
	{
		News objNews;
		String strNewsID = getRequest().getParameter("newsID");
		
		if (!StringUtils.isEmpty(strNewsID))
		{
			objNews = objNewsService.getById(Long.parseLong(strNewsID));
			objNews.setReadNum(objNews.getReadNum() + 1);
			objNewsService.update((objNews));
		}
		toWeb("");
	}
	
	/**
	 * 意见与建议。
	 */
	public void suggestoin()
	{
		
	}
	
	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public Long getLProjectID() {
		return lProjectID;
	}

	public void setLProjectID(Long projectID) {
		lProjectID = projectID;
	}
	
	public Long getlProjectID() {
		return lProjectID;
	}

	public void setlProjectID(Long projectID) {
		lProjectID = projectID;
	}

	public Project getObjProject() {
		return objProject;
	}

	public void setObjProject(Project objProject) {
		this.objProject = objProject;
	}

	public String getProjectMenuKey() {
		return projectMenuKey;
	}

	public void setProjectMenuKey(String projectMenuKey) {
		this.projectMenuKey = projectMenuKey;
	}

	public Long getlProjectMenuID() {
		return lProjectMenuID;
	}

	public void setlProjectMenuID(Long lProjectMenuID) {
		this.lProjectMenuID = lProjectMenuID;
	}

	public Long getLProjectMenuID() {
		return lProjectMenuID;
	}

	public void setLProjectMenuID(Long lProjectMenuID) {
		this.lProjectMenuID = lProjectMenuID;
	}

	public ProjectMenu getObjProjectMenu() {
		return objProjectMenu;
	}

	public void setObjProjectMenu(ProjectMenu objProjectMenu) {
		this.objProjectMenu = objProjectMenu;
	}

	public List<ProjectMenu> getLstProjectMenu() {
		return lstProjectMenu;
	}

	public void setLstProjectMenu(List<ProjectMenu> lstProjectMenu) {
		this.lstProjectMenu = lstProjectMenu;
	}

	public News getObjNews() {
		return objNews;
	}

	public void setObjNews(News objNews) {
		this.objNews = objNews;
	}

	public Long getNewsID() {
		return newsID;
	}

	public void setNewsID(Long newsID) {
		this.newsID = newsID;
	}

	public List<News> getLstNewestMessage() {
		return lstNewestMessage;
	}

	public void setLstNewestMessage(List<News> lstNewestMessage) {
		this.lstNewestMessage = lstNewestMessage;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}

	public Pagination<News> getObjGPagination() {
		return objGPagination;
	}

	public void setObjGPagination(Pagination<News> objGPagination) {
		this.objGPagination = objGPagination;
	}

	public String getStrCurMenu() {
		return strCurMenu;
	}

	public void setStrCurMenu(String strCurMenu) {
		this.strCurMenu = strCurMenu;
	}

	public List<News> getLstSuggestReading() {
		return lstSuggestReading;
	}

	public void setLstSuggestReading(List<News> lstSuggestReading) {
		this.lstSuggestReading = lstSuggestReading;
	}

	public List<News> getLstTopOfReading() {
		return lstTopOfReading;
	}

	public void setLstTopOfReading(List<News> lstTopOfReading) {
		this.lstTopOfReading = lstTopOfReading;
	}

	public List<News> getLstRollingOfReading() {
		return lstRollingOfReading;
	}

	public void setLstRollingOfReading(List<News> lstRollingOfReading) {
		this.lstRollingOfReading = lstRollingOfReading;
	}

	public Long getNewsTag() {
		return newsTag;
	}

	public void setNewsTag(Long newsTag) {
		this.newsTag = newsTag;
	}

	public NewsTag getObjNewsTag() {
		return objNewsTag;
	}

	public void setObjNewsTag(NewsTag objNewsTag) {
		this.objNewsTag = objNewsTag;
	}

	public List<News> getLstYuErZiXun() {
		return lstYuErZiXun;
	}

	public void setLstYuErZiXun(List<News> lstYuErZiXun) {
		this.lstYuErZiXun = lstYuErZiXun;
	}

	public List<ProjectMenu> getLstChildMenu() {
		return lstChildMenu;
	}

	public void setLstChildMenu(List<ProjectMenu> lstChildMenu) {
		this.lstChildMenu = lstChildMenu;
	}

	public News getObjUpNews() {
		return objUpNews;
	}

	public void setObjUpNews(News objUpNews) {
		this.objUpNews = objUpNews;
	}

	public News getObjNextNews() {
		return objNextNews;
	}

	public void setObjNextNews(News objNextNews) {
		this.objNextNews = objNextNews;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	
	
}