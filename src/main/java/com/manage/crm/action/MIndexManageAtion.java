package com.manage.crm.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javacommon.core.base.BaseStruts2Action;
import javacommon.util.SearchCondition;
import javacommon.util.freemarker.FreemarkerUtils;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.manage.crm.entity.MoodNewsRela;
import com.manage.crm.entity.News;
import com.manage.crm.entity.NewsTag;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.service.MoodNewsRelaService;
import com.manage.crm.service.NewsService;
import com.manage.crm.service.NewsTagService;
import com.manage.crm.service.ProjectMenuService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.VisitLogService;
import com.manage.crm.util.DBSql;
import com.manage.crm.util.IPUtil;
import com.manage.crm.util.Pagination;

import freemarker.template.utility.DateUtil;

@Controller("mIndexManageAction")
@Scope("prototype")
public class MIndexManageAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(MIndexManageAtion.class);

	@Resource(name = "projectService")
	private ProjectService objProjectService;
	
	@Resource(name = "projectMenuService")
	private ProjectMenuService objProjectMenuService;
	
	@Resource(name = "moodNewsRelaService")
	private MoodNewsRelaService objMoodNewsRelaService;
	
	@Resource(name = "newsService")
	private NewsService objNewsService;
	
	@Resource(name = "visitLogService")
	private VisitLogService objVisitLogService;
	
	@Resource(name = "newsTagService")
	private NewsTagService objNewsTagService;
	
	private Project objProject;
	private ProjectMenu objProjectMenu;
	
	private List<ProjectMenu> lstProjectMenu;
	
	private List<ProjectMenu> lstMainProjectMenu;
	
	private List<ProjectMenu> lstChildMenu; // 所有子菜单(边缘显示菜单)
	
	private Integer newsSize; // 显示的新闻数量。
	
	// 菜单id。
	private Long menuID;
	
	// 最新公告。
	private List<News> lstNewestMessage;
	
	private NewsTag objNewsTag; // 标签
	
	private ProjectMenu objSubProjectMenu;
	
	private String projectKey;
	private String projectMenuKey;
	private Long lProjectMenuID;
	
	private News objNews;
	private Long newsID;
	
	private int moodType; // 心情类型。
	
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
	
	// 热点推荐。
	private List<News> lstHotSuggest;
	
	// 育婴技能。
	private List<News> lstYuYingSkill;
	

	/**
	 * 考生图片。
	 */
	private File objFileData;
	private String strFileDataName;
	
	private String searchParam;

	
	/**
	 * 移动端首页。
	 * 
	 * @return
	 */
	public String index()
	{
		try {
			// 验证项目是否合法
			if (!setProjectInfo())
			{
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return this.commonError();
			}
			
			// 设置项目菜单
			setProjectMenu();
			
//			setProjectSubMenuNews();
			
			// 加载最新动态 获取前6个
			this.lstNewestMessage = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutReadNum() + ",(select a.readnum from news a where a.id=b.id) readnum from NewsTemp b ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_6);

			// 设置推荐阅读栏。
			this.lstSuggestReading = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContentOne() + " from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_SUGGESTIONREADING + " and a.imageurl is not null and imageurl !='' ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			// 阅读量排行 加载前六个 -- 本周阅读排行。
			this.lstTopOfReading = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from News a where a.imageurl is not null and imageurl !='' ORDER BY readNum desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
			// 滚动图片 加载前5个(id倒序)。Rolling 
			this.lstRollingOfReading = objNewsService.listBySql("  SELECT " + DBSql.getNewsColumnWithOutContent() + " FROM news s WHERE s.`imageurl` IS NOT NULL and s.imageurl != '' ORDER BY s.`ID` DESC LIMIT 5 ");
			
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
			objFreemarkerUtils.createFile("mobile/index.ftl", mapInData, "static/m/index.html");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	/**
	 * 移动端导航。
	 * 
	 * @return
	 */
	public String nav()
	{
		try {
			// 验证项目是否合法
			if (!setProjectInfo())
			{
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return this.commonError();
			}
			
			// 设置项目菜单
			setProjectMenu();
			
			// 生成静态化文件
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			Map<String, Object> mapInData = new HashMap<String, Object>();
			
			// 项目菜单
			mapInData.put("lstProjectMenu", this.lstProjectMenu);
			// 查询条件。
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objFreemarkerUtils.createFile("mobile/nav.ftl", mapInData, "static/m/nav.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "nav";
	}
	
	/**
	 * 心情模块。
	 * @throws IOException 
	 */
	public void loadMood() throws IOException
	{
		String strMood = "0";
		
		try {
			if (this.newsID == null || this.newsID <= 0)
			{
				strMood = "1";
			}
			else
			{
				StringBuffer sbufMood = new StringBuffer();
				SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
//				String strNowDate = sdfNow.format(new Date());
				
				sbufMood.append(" SELECT   c.`codeKey`, ");
				sbufMood.append(" c.`codeValue`,  COUNT(m.`id`), ");
				sbufMood.append("   COUNT(m.`id`)*(100/(SELECT REPLACE(COUNT(m.`id`),0,1) FROM moodnewsrela m WHERE m.`nid`="+ this.newsID +" )) ");
				sbufMood.append(" FROM  code_record c  ");
				sbufMood.append("   LEFT JOIN moodnewsrela m  ");
				sbufMood.append("     ON c.`codeKey` = m.`Mtype`  ");
//				sbufMood.append("    AND m.`nid` = "+ this.newsID +" and m.`startdate`=DATE'" + strNowDate + "'");
				sbufMood.append("    AND m.`nid` = "+ this.newsID);
				sbufMood.append("  WHERE c.`codeType` = 'mood'  ");
				sbufMood.append(" GROUP BY c.`codeKey`,c.`codeValue` ");
				
				strMood = JSONArray.fromObject(this.objMoodNewsRelaService.queryBySql(sbufMood.toString())).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		toWeb(strMood);
	}
	
	/**
	 * 加载最新发布内容列表。
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void loadNewstList() throws IOException
	{
		JsonConfig objJsonConfig = new JsonConfig();
		String strNews = "0";
		
		try {
			if (lProjectMenuID == null || this.lProjectMenuID < 0 || newsSize == null || newsSize < 0)
			{
				logger.error("获取项目菜单/最大新闻id失败！");
				strNews = "1";
			}
			else
			{
				// List<News> lstMoreNews = objNewsService.listBySql(" select * from News where mid in (select id from projectmenu where parid=" + lProjectMenuID + ")  ORDER BY id desc  LIMIT " + newsSize + ",7");
				// 加载最新动态 获取前6个
				this.lstNewestMessage = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from NewsTemp ORDER BY id desc LIMIT " + newsSize + ",7");
				
				if (this.lstNewestMessage == null || this.lstNewestMessage.size() <= 0)
				{
					strNews = "2"; // 无更多新闻。
				}
				else
				{
					objJsonConfig.setExcludes(new String[] { "objUsers","objProject","objProjectMenu","lstNewsTag","lstAttachs","lstComments" });
					strNews = JSONArray.fromObject(this.lstNewestMessage, objJsonConfig).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		toWeb(strNews);
	}
	
	/**
	 * 加载菜单列表。
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void loadProjectMenu() throws IOException
	{
		JsonConfig objJsonConfig = new JsonConfig();
		String strNews = "0";
		String curProjectMenuID = getRequest().getParameter("curProjectMenuID");
		
		try {
			if (lProjectMenuID == null || this.lProjectMenuID < 0)
			{
				logger.error("获取项目菜单/最大新闻id失败！");
				strNews = "1";
			}
			else
			{
				List<ProjectMenu> lstMoreProjectMenu = objProjectMenuService.listBySql(" select * from projectmenu where parid=" + lProjectMenuID + " and id!='"+ curProjectMenuID +"'");
				
				if (lstMoreProjectMenu == null || lstMoreProjectMenu.size() <= 0)
				{
					strNews = "2"; // 无更多新闻。
				}
				else
				{
					objJsonConfig.setExcludes(new String[] { "objProject","objParentProjectMenu","lstChildrenProjectMenu","lstNews" });
					strNews = JSONArray.fromObject(lstMoreProjectMenu, objJsonConfig).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		toWeb(strNews);
	}
	
	/**
	 * 加载相同分类内容列表。
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void loadSimilarNewstList() throws IOException
	{
		JsonConfig objJsonConfig = new JsonConfig();
		String strNews = "0";
		
		try {
			if (lProjectMenuID == null || this.lProjectMenuID < 0 || newsSize == null || newsSize < 0)
			{
				logger.error("获取项目菜单/最大新闻id失败！");
				strNews = "1";
			}
			else
			{
				List<News> lstMoreNews = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from News where mid ="+lProjectMenuID+"  ORDER BY id desc  LIMIT " + newsSize + ",7");
				
				if (lstMoreNews == null || lstMoreNews.size() <= 0)
				{
					strNews = "2"; // 无更多新闻。
				}
				else
				{
					objJsonConfig.setExcludes(new String[] { "objUsers","objProject","objProjectMenu","lstNewsTag","lstAttachs","lstComments" });
					strNews = JSONArray.fromObject(lstMoreNews, objJsonConfig).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		toWeb(strNews);
	}
	
//	/**
//	 * 新闻标签模块。
//	 * 
//	 * @throws IOException 
//	 */
//	public void loadNewsTagList() throws IOException
//	{
//		String strNews = "0";
//		
//		try {
//			List<List<News>> listNews = new ArrayList<List<News>>();
//			
//			listNews.add(objNewsService.listBySql(" SELECT n.* FROM newstagrela s,news n WHERE s.`nid`=n.`ID` AND s.ntid=3 LIMIT 5 "));
//			listNews.add(objNewsService.listBySql(" SELECT n.* FROM newstagrela s,news n WHERE s.`nid`=n.`ID` AND s.ntid=1 LIMIT 5 "));
//
//			JsonConfig objJsonConfig = new JsonConfig();
//			objJsonConfig.setExcludes(new String[] { "objUsers","objProject","objProjectMenu","lstNewsTag","lstAttachs" });
//
//			strNews = JSONArray.fromObject(listNews, objJsonConfig).toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		toWeb(strNews);
//	}
	
	/**
	 * 表述心情。
	 * @throws IOException 
	 */
	public void moodExpress() throws IOException
	{
		String strMood = "0";
		
		try {
			if (this.newsID == null || this.newsID <= 0)
			{
				strMood = "2";
			}
			else if (this.moodType <= 0)
			{
				strMood = "3";
			}
			else
			{
				// 判断此ip是否已经发表过看法。
				MoodNewsRela objMoodNewsRela = new MoodNewsRela();
				SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfNowOther = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startDate = sdfNow.parse(sdfNow.format(new Date()));
				objMoodNewsRela.setMoodType(moodType);
				objMoodNewsRela.setIp(IPUtil.getIpAddr(getRequest()));
				objMoodNewsRela.setNewsID(newsID);
				objMoodNewsRela.setStartDate(startDate);
				objMoodNewsRela.setOperateTime(sdfNowOther.format(new Date()));
				this.objMoodNewsRelaService.save(objMoodNewsRela);
				strMood = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		toWeb(strMood);
	}
	
	public String subMenuShow()
	{
		// 验证项目菜单是否有效。
		if (this.lProjectMenuID == null || this.lProjectMenuID <= 0)
		{
			setErrorText("无法获取您的项目菜单信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		// 获取项目主菜单。
		objProjectMenu = this.objProjectMenuService.getById(this.lProjectMenuID);
		
		if (objProjectMenu == null) {
			setErrorText("无法获取您的菜单信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		try {
			// 加载项目信息。
			this.objProject=objProjectMenu.getObjProject();
			
			// 加载首页需要显示的菜单(只显示有效菜单)。
			lstMainProjectMenu = this.objProjectMenuService.listByHql(" from ProjectMenu where objProject.lId='" + this.objProject.getlId() + "' and validate=1 and level=1 and menuKey !='aboutUs'");

			// 加载有效的子菜单
			setSubProjectMenu(lProjectMenuID);
			// 设置每个子菜单下要显示的新闻消息。(通过引入html方式加载)
//			setProjectMenuNews();
			
			if (objProject == null) {
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return COMMON_ERROR;
			}
			
			// 加载育婴师最新发布动态 获取前9个
			setNewestMessage();
			
			// 设置推荐阅读栏目。
//			setSuggestReading();
			
//			// 加载育婴师 阅读排行
//			this.lstTopOfReading = objNewsService.listBySql(" select a.* from Newstemp a  where a.parmid=" + this.lProjectMenuID + " ORDER BY readNum desc LIMIT " + Pagination.PAGE_SIZE_9);
			
//			// 热点推荐。
//			this.lstHotSuggest = objNewsService.listBySql(" select a.* from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_HOTSUGGESTION + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			
//			// 育婴技能。
//			this.lstYuYingSkill = objNewsService.listBySql(" select a.* from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_YUYINGSKILL + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			
			// 滚动图片 加载前5个(id倒序)。Rolling 
//			this.lstRollingOfReading = objNewsService.listBySql(" SELECT * FROM news s WHERE s.mid in (select id from projectmenu s2 where s2.parid='"+lProjectMenuID+"') and s.`imageurl` IS NOT NULL and s.imageurl != '' ORDER BY s.`ID` DESC LIMIT 5");
			this.lstRollingOfReading = objNewsService.lstScrollImgByParMenuId(lProjectMenuID, Pagination.PAGE_SIZE_5);
			// 设置当前选中菜单。
			strCurMenu = "index"; // 首页
			getRequest().setAttribute("mid", lstProjectMenu.get(0).getlId());
			
			// 暂不将生成静态文件集成到具体业务处理中。
//			// 生成静态化文件
//			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
//			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
//			Map<String, Object> mapInData = new HashMap<String, Object>();
//			
//			// 项目
//			mapInData.put("objProject", this.objProject);
//			// 项目菜单
//			mapInData.put("lstMainProjectMenu", this.lstMainProjectMenu);
//			// 项目子菜单
//			mapInData.put("objProjectMenu", this.objProjectMenu);
//			// 加载最新动态 获取前6个
//			mapInData.put("lstNewestMessage", this.lstNewestMessage);
//			// 设置推荐阅读栏。
//			mapInData.put("lstProjectMenu", this.lstProjectMenu);
//			// 阅读量排行 加载前六个。
//			mapInData.put("lstTopOfReading", this.lstTopOfReading);
//			// 滚动图片 加载前5个(id倒序)。Rolling 
//			mapInData.put("lstRollingOfReading", this.lstRollingOfReading);
//			// 设置选中菜单
//			mapInData.put("strCurMenu", this.strCurMenu);
//			// 查询条件。
//			mapInData.put("searchText", this.searchText == null ? "" : this.searchText);
//			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
//			objFreemarkerUtils.createFile("mobile/sub_menu.ftl", mapInData, "static/m/" + objProjectMenu.getMenuKey());
		} catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return "subIndex";
	}
	
	/**
	 * 获取指定菜单下新闻信息。
	 * 
	 * @return
	 */
	public String newsList()
	{
		String nPageSize = getRequest().getParameter("rows"); // 每页显示多少个数据。
		String nCurrentPage = getRequest().getParameter("page");// 第几页。
		
		// 获取消息详细信息。
		if ((this.menuID == null) || (this.menuID.longValue() <= 0L)) {
			setErrorText("没有获取到指定菜单参数，请检查网址是否正确！");
			return "commonError";
		}
		
		// 获取项目子菜单。
		objSubProjectMenu = this.objProjectMenuService.getByHql(" from ProjectMenu where id='" + this.menuID + "'");
		if (objSubProjectMenu == null) {
			setErrorText("无法获取您的菜单信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		lProjectMenuID = objSubProjectMenu.getObjParentProjectMenu().getlId();
		// 获取项目主菜单。
//		this.objProjectMenu = this.objSubProjectMenu.getObjParentProjectMenu();
//		
//		// 加载项目信息。
//		this.objProject=objProjectMenu.getObjProject();
		
		// 加载有效的子菜单
		//setSubProjectMenu(lProjectMenuID);
		
//		if (objProject == null) {
//			setErrorText("无法获取您的项目信息，请检查后重试！");
//			return COMMON_ERROR;
//		}
		
		try {
//			Pagination<News> objPagination = new Pagination<News>();
			lstRollingOfReading = objNewsService.lstScrollImgByMenuId(lProjectMenuID, Pagination.PAGE_SIZE_5);
			// 加载育婴师最新发布动态 获取前9个
			//setNewestMessage();
			
			// 设置推荐阅读栏目。
			//setSuggestReading();
			
			// 根据菜单id获取菜单下信息列表。
//			if (nPageSize == null || nPageSize.length() <= 0)
//			{
//				nPageSize = "20";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "newsList";
	}
	
	/**
	 * 异步获取指定菜单下新闻信息。
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void loadNewsList() throws Exception
	{
		JsonConfig objJsonConfig = new JsonConfig();
		String strNews = "0";
		String nPageSize = getRequest().getParameter("rows"); // 每页显示多少个数据。
		String nCurrentPage = getRequest().getParameter("page");// 第几页。
		
		// 获取消息详细信息。
		if ((this.menuID == null) || (this.menuID.longValue() <= 0L)) {
			setErrorText("没有获取到指定菜单参数，请检查网址是否正确！");
			strNews = "2"; // 无更多新闻。
		}
		
		try {
			Pagination<News> objPagination = new Pagination<News>();
			
			// 根据菜单id获取菜单下信息列表。
			if (nPageSize == null || nPageSize.length() <= 0)
			{
				nPageSize = "20";
			}
			if (nCurrentPage == null || nCurrentPage.length() <= 0)
			{
				nCurrentPage = "1";
			}
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			logger.info("当前页数：" + nCurrentPage + "--每页显示的个数为：" + nPageSize);
			
			// 组装查询条件。
			Criterion objSQLCondition = Restrictions.sqlRestriction(" mid ='" + this.menuID + "'");
			objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
			objJsonConfig.setExcludes(new String[] { "objUsers","objProject","objProjectMenu","lstNewsTag","lstAttachs","lstComments" });
			strNews = JSONArray.fromObject(objPagination.getRows(), objJsonConfig).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		toWeb(strNews);
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
		
		// 根据标签id获取标签名称。
		objNewsTag = this.objNewsTagService.getBySql("select * from newstag where id=" + newsTag);
		
		if (objNewsTag == null)
		{
			setErrorText("标签编码无效，请检查网址是否正确！");
			return "commonError";
		}
		
		// 获取项目子菜单。
		objProjectMenu = this.objProjectMenuService.getByHql(" from ProjectMenu where id='" + this.lProjectMenuID + "'");
		if (objProjectMenu == null) {
			setErrorText("无法获取您的标签信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		// 加载项目信息。
		this.objProject=objProjectMenu.getObjProject();
		
		// 加载有效的子菜单
		setSubProjectMenu(lProjectMenuID);
		
		if (objProject == null) {
			setErrorText("无法获取您的项目信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		try {
			// 加载育婴师最新发布动态 获取前9个
			setNewestMessage();
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "newsListTag";
	}
	
	/**
	 * 获取最新的新闻信息列表。
	 * 
	 * @return
	 */
	public String newsestList()
	{
		String nPageSize = getRequest().getParameter("rows"); // 每页显示多少个数据。
		String nCurrentPage = getRequest().getParameter("page");// 第几页。
		
		// 加载有效的子菜单
		setSubProjectMenu(lProjectMenuID);
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
			Criterion objSQLCondition = Restrictions.sqlRestriction(" mid in(select s.id from projectmenu s where s.parid='" + this.lProjectMenuID + "')");
			objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("lId"));
			this.objGPagination = objPagination;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "newsestList";
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
//				setErrorText("请输入查询条件！");
				// return "commonError";
				// 如果查询条件为空直接跳转到搜索页面。
				return "search";
			}
			
			this.searchText = new String(searchText.getBytes("iso-8859-1"), "utf-8");
			
			// 根据项目标志获取项目信息
			if (!setProjectInfo())
			{
				setErrorText("无法获取项目信息！");
				return COMMON_ERROR;
			}
			
			// 设置项目菜单
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
			// 组装查询条件，每页限制查询10
			
			Criterion objSQLCondition = Restrictions.sqlRestriction(" ncontent like '%" + searchText + "%' or title like '%"+searchText+"%'");
			objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
			this.objGPagination = objPagination;
			
			// 设置推荐阅读栏。lstSuggestReading
//			NewsTag objNewsTag = objNewsTagService.getById(NewsTag.G_SUGGESTIONREADING);
//			this.lstSuggestReading = new ArrayList<News>(objNewsTag.getLstNews());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "search";
	}
	
	/**
	 * 加载更多新闻。
	 * @throws IOException 
	 */
	public void loadMoreNews() throws IOException
	{
		JsonConfig objJsonConfig = new JsonConfig();
		String strNews = "0";
		
		try {
			if (lProjectMenuID == null || this.lProjectMenuID < 0 || newsSize == null || newsSize < 0)
			{
				logger.error("获取项目菜单/最大新闻id失败！");
				strNews = "1";
			}
			else
			{
				List<News> lstMoreNews = objNewsService.listBySql(" select * from News where mid=" + lProjectMenuID + "  ORDER BY id desc  LIMIT " + newsSize + ",7");
				
				if (lstMoreNews == null || lstMoreNews.size() <= 0)
				{
					strNews = "2"; // 无更多新闻。
				}
				else
				{
					objJsonConfig.setExcludes(new String[] { "objUsers","objProject","objProjectMenu","lstNewsTag","lstAttachs","lstComments" });
					strNews = JSONArray.fromObject(lstMoreNews, objJsonConfig).toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		toWeb(strNews);
	}
	
	/**
	 * 加载育婴师最新发布动态 获取前9个
	 */
	private void setNewestMessage()
	{
		// 加载育婴师最新发布动态 获取前9个
		// this.lstNewestMessage = objNewsService.listBySql(" select a.* from News a,projectmenu b where a.mid=b.id and b.parid=" + this.lProjectMenuID + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_9);
		this.lstNewestMessage = objNewsService.listBySql(" select a.* from News a  where a.mid in(select b.id from projectmenu b where b.parid=" + this.lProjectMenuID + ") ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_9);
	}
	
	/**
	 * 设置推荐阅读栏目。
	 */
	private void setSuggestReading()
	{
		// 设置推荐阅读栏。
		this.lstSuggestReading = objNewsService.listBySql(" select a.* from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_SUGGESTIONREADING + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
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
							// 获取7条数据封装到 边缘菜单中
							lstCurNews = objNewsService.listBySql(" select * from News where mid=" + objCurProjectMenu.getlId() + " ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
							objCurProjectMenu.setLstNews(new HashSet<News>(lstCurNews));
						}
						else if (objCurProjectMenu.getShowIndex() == 2)
						{
							lstCurNews = objNewsService.listBySql(" select * from News where mid=" + objCurProjectMenu.getlId() + " ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
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
	 * 设置项目子菜单下新闻。
	 */
	private void setProjectMenuNews() {
		// 设置每个项目菜单的子菜单下显示新闻的数量。
		if (this.lstProjectMenu != null && lstProjectMenu.size() > 0)
		{
			List<News> lstCurNews;
			
			for (ProjectMenu parentProjectMenu : lstProjectMenu)
			{
				// 获取7条数据封装到
				lstCurNews = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from News where mid=" + parentProjectMenu.getlId() + "  ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
				parentProjectMenu.setLstManualNews(lstCurNews);
//				if (parentProjectMenu.getShowIndex() == 1)
//				{
//					// 获取7条数据封装到
//					lstCurNews = objNewsService.listBySql(" select * from News where mid=" + parentProjectMenu.getlId() + " ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
//					parentProjectMenu.setLstNews(new HashSet<News>(lstCurNews));
//				}
//				else if (parentProjectMenu.getShowIndex() == 2) // 本模块下显示到边缘上的菜单。
//				{
//					// 获取7条数据封装到 边缘新闻显示。
//					lstCurNews = objNewsService.listBySql(" select * from News where mid=" + parentProjectMenu.getlId() + " ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
//					parentProjectMenu.setLstNews(new HashSet<News>(lstCurNews));
//					if (lstChildMenu == null)
//					{
//						lstChildMenu = new ArrayList<ProjectMenu>();
//					}
//					lstChildMenu.add(parentProjectMenu);
//				}
			}
		}
	}
	
	/**
	 * 设置项目菜单。
	 */
	private void setProjectMenu()
	{
		// 加载首页需要显示的菜单(只显示有效菜单)。
		lstProjectMenu = this.objProjectMenuService.listByHql(" from ProjectMenu where objProject.lId='" + this.objProject.getlId() + "' and validate=1 and level=1 and menuKey !='aboutUs'");
	}
	
	/**
	 * 设置项目信息。
	 */
	private boolean setProjectInfo()
	{
		this.projectKey="yuyingzhijia";
		
		// 根据项目标志获取项目信息
		objProject = this.objProjectService.getByHql(" from Project where projectKey='" + this.projectKey + "'");
		if (objProject == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 移动端菜单展示。
	 * @throws IOException 
	 */
	public void loadMenu() throws IOException
	{
		String strHTML = "";
		
		List<ProjectMenu> lstProjectMenu = this.objProjectMenuService.listBySql(" select * from ProjectMenu where level=1 and menukey!='aboutUs'");
		
		if (lstProjectMenu != null && lstProjectMenu.size() > 0)
		{
			for (int i = 0; i < lstProjectMenu.size(); i++) {
				ProjectMenu projectMenu = lstProjectMenu.get(i);
				
				strHTML += "<LI><SPAN>" + projectMenu.getStrMenuName() + "</SPAN>";
				strHTML += "<UL id='contacts-"+ projectMenu.getlId() + "'>";
				
				if (projectMenu != null && projectMenu.getLstChildrenProjectMenu().size() > 0)
				{
					Iterator<ProjectMenu> iteProjectMenu = projectMenu.getLstChildrenProjectMenu().iterator();
					
					while (iteProjectMenu.hasNext()) {
						ProjectMenu subProjectMenu = iteProjectMenu.next();
						strHTML += "<LI class='img'>";
						strHTML += "<a href='/front/web/listCommodityByType.do?lCategoryID=" + subProjectMenu.getlId() + "'>" + subProjectMenu.getStrMenuName() + "</a>";
						strHTML += "</LI>";
					}
				}
				
				strHTML += "</UL></LI>";
			}
		}
		
		toWeb(strHTML);
	}
	
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

	public Integer getNewsSize() {
		return newsSize;
	}

	public void setNewsSize(Integer newsSize) {
		this.newsSize = newsSize;
	}

	public VisitLogService getObjVisitLogService() {
		return objVisitLogService;
	}

	public void setObjVisitLogService(VisitLogService objVisitLogService) {
		this.objVisitLogService = objVisitLogService;
	}

	public Long getMenuID() {
		return menuID;
	}

	public void setMenuID(Long menuID) {
		this.menuID = menuID;
	}

	public ProjectMenu getObjSubProjectMenu() {
		return objSubProjectMenu;
	}

	public void setObjSubProjectMenu(ProjectMenu objSubProjectMenu) {
		this.objSubProjectMenu = objSubProjectMenu;
	}

	public List<News> getLstHotSuggest() {
		return lstHotSuggest;
	}

	public void setLstHotSuggest(List<News> lstHotSuggest) {
		this.lstHotSuggest = lstHotSuggest;
	}

	public List<News> getLstYuYingSkill() {
		return lstYuYingSkill;
	}

	public void setLstYuYingSkill(List<News> lstYuYingSkill) {
		this.lstYuYingSkill = lstYuYingSkill;
	}

	public int getMoodType() {
		return moodType;
	}
	
	public List<ProjectMenu> getLstMainProjectMenu() {
		return lstMainProjectMenu;
	}

	public void setLstMainProjectMenu(List<ProjectMenu> lstMainProjectMenu) {
		this.lstMainProjectMenu = lstMainProjectMenu;
	}

	public void setMoodType(int moodType) {
		this.moodType = moodType;
	}

}