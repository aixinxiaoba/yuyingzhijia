package com.manage.crm.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javacommon.core.Config;
import javacommon.core.base.BaseStruts2Action;
import javacommon.util.NetImageDisposal;
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
import com.manage.crm.service.NewsService;
import com.manage.crm.service.NewsTagService;
import com.manage.crm.service.NewsTempService;
import com.manage.crm.service.ProjectMenuService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.util.DBSql;
import com.manage.crm.util.Pagination;
import com.opensymphony.xwork2.Action;

@Controller("yuYingShiManageAction")
@Scope("prototype")
public class YuYingShiManageAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(YuYingShiManageAtion.class);

	private static final String INDEX = "index";
	
	private static final String LIST_SEARCH = "list_search";

	@Resource(name = "projectService")
	private ProjectService objProjectService;
	
	@Resource(name = "projectMenuService")
	public ProjectMenuService objProjectMenuService;
	
	@Resource(name = "newsService")
	public NewsService objNewsService;
	
	@Resource(name = "newsTempService")
	private NewsTempService objNewsTempService;
	
	@Resource(name = "newsTagService")
	private NewsTagService ojbNewsTagService;
	
	private Project objProject;
	private ProjectMenu objProjectMenu;
	
	private ProjectMenu objSubProjectMenu;
	
	private List<ProjectMenu> lstProjectMenu;
	
	private Pagination<News> objGPagination;
	
	// 最新公告。
	private List<News> lstNewestMessage;
	
	// 推荐阅读。
	private List<News> lstSuggestReading;
	
	// 热点推荐。
	private List<News> lstHotSuggest;
	
	// 育婴技能。
	private List<News> lstYuYingSkill;
	
	private List<ProjectMenu> lstChildMenu; // 所有子菜单(边缘显示菜单)
	
	private News objUpNews;
	
	private News objNextNews;
	
	private String projectKey;
	private String projectMenuKey;
	private Long lProjectMenuID;
	
	private News objNews;
	private Long newsID;
	
	private String menuKey;
	
	private Long lProjectID;
	
	// 菜单id。
	private Long menuID;
	
	// 标签id。
	private Long newsTag;
	
	/**
	 * 考生图片。
	 */
	private File objFileData;
	private String strFileDataName;
	
	private String searchParam;
	
	private String strCurMenu;
	
	private List<News> lstTopOfReading; // 阅读排行

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

	/**
	 * 育婴师首页处理逻辑。
	 * 
	 * @return
	 */
	public String index()
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
			
			// 加载有效的子菜单
			setSubProjectMenu(lProjectMenuID);
			// 设置每个子菜单下要显示的新闻消息。
			setProjectSubMenuNews();
			
			if (objProject == null) {
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return COMMON_ERROR;
			}
			
			// 加载育婴师最新发布动态 获取前9个
			setNewestMessage(Pagination.PAGE_SIZE_9);
			
			// 设置推荐阅读栏目。setSuggestReading(Pagination.PAGE_SIZE_RIGHT);
			setSuggestReading(Pagination.PAGE_SIZE_RIGHT);
			
			// 加载育婴师 阅读排行
			this.lstTopOfReading = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from Newstemp a  where a.parmid=" + this.lProjectMenuID + " ORDER BY readNum desc LIMIT " + Pagination.PAGE_SIZE_9);
			
			// 热点推荐。
			this.lstHotSuggest = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContentOne() + " from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_HOTSUGGESTION + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			
			// 育婴技能。
			this.lstYuYingSkill = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContentOne() + " from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_YUYINGSKILL + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			
			// 设置当前选中菜单。
			strCurMenu = "index"; // 首页
		} catch (Exception e) {
			logger.error("出现异常：" + e.getMessage());
		}
		return "index";
	}
	
	/**
	 * 育婴师首页处理逻辑。
	 * 
	 * @return
	 */
	public void indexStatic()
	{
		// 验证项目菜单是否有效。
		if (this.lProjectMenuID == null || this.lProjectMenuID <= 0)
		{
			setErrorText("无法获取您的项目菜单信息，请检查后重试！");
			return;
		}
		
		// 获取项目主菜单。
		objProjectMenu = this.objProjectMenuService.getById(this.lProjectMenuID);
		if (objProjectMenu == null) {
			setErrorText("无法获取您的菜单信息，请检查后重试！");
			return;
		}
		
		try {
			// 加载项目信息。
			this.objProject=objProjectMenu.getObjProject();
			
			// 加载有效的子菜单
			setSubProjectMenu(lProjectMenuID);
			// 设置每个子菜单下要显示的新闻消息。
			setProjectSubMenuNews();
			
			if (objProject == null) {
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return;
			}
			
			// 加载育婴师最新发布动态 获取前9个
			setNewestMessage(Pagination.PAGE_SIZE_9);
			
			// 设置推荐阅读栏目。
			setSuggestReading(Pagination.PAGE_SIZE_RIGHT);
			
			// 加载育婴师 阅读排行
			this.lstTopOfReading = objNewsService.listBySql(" select a.* from News a,projectmenu b where a.mid=b.id and b.parid=" + this.lProjectMenuID + " ORDER BY readNum desc LIMIT " + Pagination.PAGE_SIZE_9);
			
			// 热点推荐。
			this.lstHotSuggest = objNewsService.listBySql(" select a.* from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_HOTSUGGESTION + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			
			// 育婴技能。
			this.lstYuYingSkill = objNewsService.listBySql(" select a.* from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_YUYINGSKILL + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
			
			// 设置当前选中菜单。
			strCurMenu = "index"; // 首页
			
			// 生成静态化文件
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			Map<String, Object> mapInData = new HashMap<String, Object>();
			
			// 获取项目主菜单。
//			mapInData.put("lProjectID", this.lProjectID);
			// 获取项目主菜单。
			mapInData.put("objProjectMenu", this.objProjectMenu);
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
			
			// 阅读量排行 加载前六个。
			mapInData.put("lstHotSuggest", this.lstHotSuggest);
			// 阅读量排行 加载前六个。
			mapInData.put("lstYuYingSkill", this.lstYuYingSkill);
			// 设置选中菜单
			mapInData.put("strCurMenu", this.strCurMenu);
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objFreemarkerUtils.createFile("menu/index.ftl", mapInData, "static/menu/bm/" + objProjectMenu.getMenuKey());
		} catch (Exception e) {
			logger.error("出现异常：" + e.getMessage());
		}
		
		return;
	}
	
	/**
	 * 新增测试;OK。
	 * 
	 * @throws IOException
	 */
	public void dealWithImg() throws IOException {
		
		// 获取所有的菜单。
		List<ProjectMenu> projectMenu = this.objProjectMenuService.listBySql("select * from projectMenu s where s.level=2");
		if (projectMenu != null && projectMenu.size() > 0)
		{
			try {
				for (int j = 0; j < projectMenu.size(); j++)
				{
					Long objProjectMenuID = projectMenu.get(j).getlId();
					List<News> lstNews = this.objNewsService.listBySql("SELECT * FROM news s WHERE s.mid = "+objProjectMenuID+" and s.`NContent` LIKE '%.jpg%'  and (s.imageurl ='' OR s.imageurl IS NULL) ");
					News objNews;
					
					if (lstNews != null && lstNews.size() > 0) {
						for (int i = 0; i < lstNews.size(); i++) {
							objNews = lstNews.get(i);
							
							// 如果存在图片则判断是否需要进行下载图片操作。
							NetImageDisposal.startDownLoad(objNews, NewsManageAtion.G_2K28);
							this.objNewsService.update(objNews);
							Thread.sleep(1000);
						}
					}
				}
			} catch (Exception e) {
				logger.error("=========图片处理出错，错误信息为=========", e.getMessage());
			}
		}
		
	}
	
	/**
	 * 设置项目子菜单下新闻。
	 */
	private void setProjectSubMenuNews() {
		// 设置每个项目菜单的子菜单下显示新闻的数量。
		if (this.lstProjectMenu != null && lstProjectMenu.size() > 0)
		{
			List<News> lstCurNews;
			
			for (ProjectMenu parentProjectMenu : lstProjectMenu)
			{
				String strNewsID = "";
				
				// 获取两个带图片的新闻
				List<News> lstImageNews = objNewsService.listBySql(" select * from News where mid=" + parentProjectMenu.getlId() + " and imageurl is not null and imageurl !='' ORDER BY id desc LIMIT 1");
				
				if (lstImageNews != null && lstImageNews.size() > 0)
				{
					parentProjectMenu.setLstImageNews(lstImageNews);
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
				lstCurNews = objNewsService.listBySql(" select * from News where mid=" + parentProjectMenu.getlId() + strNewsID +" ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
//				objCurProjectMenu.setLstNews(new HashSet<News>(lstCurNews));
				// 获取7条数据封装到
//				lstCurNews = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from News where mid=" + parentProjectMenu.getlId() + " ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_CONTENT);
				parentProjectMenu.setLstNews(new HashSet<News>(lstCurNews));
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
	 * 信息详细处理。
	 * 
	 * @return
	 */
	public String detail()
	{
		// 获取消息详细信息。
		if ((this.newsID == null) || (this.newsID.longValue() <= 0L)) {
			setErrorText("没有获取到要查看的文章");
			return "commonError";
		}
		
		try {
			this.objNews = this.objNewsService.getById(this.newsID);
			if (this.objNews == null) {
				setErrorText("无法获取到文章信息，请检查您的网址是否正确！");
				return "commonError";
			}
			
			// 获取新闻 所对应菜单。
			objSubProjectMenu = objNews.getObjProjectMenu();
			// 父菜单。
			objProjectMenu = objSubProjectMenu.getObjParentProjectMenu();
			// 父菜单id。
			lProjectMenuID = objProjectMenu.getlId();
			// 加载有效的子菜单
			setSubProjectMenu(lProjectMenuID);
			// 获取项目
			this.objProject = objNews.getObjProject();
			
			// 验证项目主菜单。
			if (objProjectMenu == null) {
				setErrorText("无法获取您的菜单信息，请检查后重试！");
				return COMMON_ERROR;
			}
			
			if (objProject == null) {
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return COMMON_ERROR;
			}
			
			// 获取上一页第一个数据。
			List<News> lstUpNews = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from news where mid=" + this.objSubProjectMenu.getlId() + " and id < " + newsID + " ORDER BY id DESC  LIMIT 1  ");
			if (lstUpNews != null && lstUpNews.size() > 0)
			{
				this.objUpNews = lstUpNews.get(0);
			}
			// 获取下一页第一个数据。
			List<News> lstNextNews = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from news where mid=" + this.objSubProjectMenu.getlId() + " and id > " + newsID + "  LIMIT 1  ");
			if (lstNextNews != null && lstNextNews.size() > 0)
			{
				this.objNextNews = lstNextNews.get(0);
			}
			
			// 判断是否是移动端查看，如果为移动端则自动设置为手机查看模式。
			if (isMobileDevice())
			{
				// 加载育婴师最新发布动态 获取前9个
				this.setNewestMessage(5);
				
				// 设置推荐阅读栏。lstSuggestReading
				this.setSuggestReading(5);
				
				return "m_detail";
			}
			
			// 加载育婴师最新发布动态 获取前9个
			this.setNewestMessage(Pagination.PAGE_SIZE_9);
			
			// 设置推荐阅读栏。lstSuggestReading
			this.setSuggestReading(Pagination.PAGE_SIZE_RIGHT);
			
			// 设置当前选中菜单。
			strCurMenu = this.objSubProjectMenu.getMenuKey(); // 默认首页
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "detail";
	}
	
	/**
	 *  PC端 + 移动端 静态化。
	 * 
	 * @return
	 */
	public void detailStatic()
	{
		try {
			// 获取消息详细信息。
			if ((this.newsID == null) || (this.newsID.longValue() <= 0L)) {
				setErrorText("没有获取到要查看的文章");
				return;
			}
			this.objNews = this.objNewsService.getById(this.newsID);
			lProjectMenuID = this.objNews.getObjProjectMenu().getObjParentProjectMenu().getlId();
			if (this.objNews == null) {
				setErrorText("无法获取到文章信息，请检查您的网址是否正确！");
				return;
			}
			
			// 设置小类。
			this.objSubProjectMenu = this.objNews.getObjProjectMenu();
			
			// 获取项目主菜单。
			objProjectMenu = this.objProjectMenuService.getByHql(" from ProjectMenu where id='" + this.lProjectMenuID + "'");
			if (objProjectMenu == null) {
				setErrorText("无法获取您的菜单信息，请检查后重试！");
				return;
			}
			
			// 加载项目信息。
			this.objProject=objProjectMenu.getObjProject();
			
			// 加载有效的子菜单
			setSubProjectMenu(lProjectMenuID);
			
			if (objProject == null) {
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return;
			}
			
			// 加载育婴师最新发布动态 获取前9个
			this.setNewestMessage(5);
			
			// 设置推荐阅读栏。lstSuggestReading
			this.setSuggestReading(5);
			
			// 获取上一页第一个数据。
			Pagination<News> objUpPagination = new Pagination<News>(1, 1);
			Criterion objUpSQLCondition = Restrictions.sqlRestriction(" mid=" + this.objSubProjectMenu.getlId() + " and id < " + newsID);
			objNewsService.listByCriteria(objUpPagination, new SearchCondition(objUpSQLCondition, null), null);
			if (objUpPagination.getRows() != null && objUpPagination.getRows().size() > 0)
			{
				this.objUpNews = objUpPagination.getRows().get(0);
			}
			
			// 获取下一页第一个数据。
			Pagination<News> objNextPagination = new Pagination<News>(1, 1);
			Criterion objNextSQLCondition = Restrictions.sqlRestriction(" mid=" + this.objSubProjectMenu.getlId() + " and id > " + newsID);
			objNewsService.listByCriteria(objNextPagination, new SearchCondition(objNextSQLCondition, null), null);
			if (objNextPagination.getRows() != null && objNextPagination.getRows().size() > 0)
			{
				this.objNextNews = objNextPagination.getRows().get(0);
			}
			
			// 设置当前选中菜单。
			strCurMenu = this.objSubProjectMenu.getMenuKey(); // 默认首页
			
			// 生成静态化文件
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			Map<String, Object> mapInData = new HashMap<String, Object>();
			
			// 项目菜单id。
			mapInData.put("lProjectMenuID", lProjectMenuID);
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
			// 设置选中菜单
			mapInData.put("strCurMenu", this.strCurMenu);
			mapInData.put("objNews", this.objNews);
			mapInData.put("objUpNews", this.objUpNews);
			mapInData.put("objNextNews", this.objNextNews);
			mapInData.put("objProjectMenu", this.objProjectMenu);
			
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			// 静态化PC端。
//			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
//			+s.format(s.parse(this.objNews.getStrSendDate()))+"/"
			objFreemarkerUtils.createFile("menu/news.ftl", mapInData, "static/news/" + this.objNews.getlId());
			
			// 加载育婴师最新发布动态 获取前9个
			this.setNewestMessage(Pagination.PAGE_SIZE_9);
			
			// 设置推荐阅读栏。lstSuggestReading
			this.setSuggestReading(Pagination.PAGE_SIZE_RIGHT);
			
			// 静态化移动端
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objFreemarkerUtils.createFile("menu/m_news.ftl", mapInData, "static/m_news/" + this.objNews.getlId());
			
			// 更新静态化状态。
			objNews.setStaticFlag(0);
			objNewsService.update(objNews);
			
//			logger.info("开始向百度推送url");
//			String url = "http://data.zz.baidu.com/urls?site=www.yuyingzhijia.cn&token=aHy5BwoLTA7ZGSUJ";//网站的服务器连接
//			String[] param = {"http://www.yuyingzhijia.cn/static/news/" + this.objNews.getlId(),// pc端
//					"http://www.yuyingzhijia.cn/static/m_news/" + this.objNews.getlId()//mobile 端
//			};
//			String json = Post(url, param);//执行推送方法
//			logger.info(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 百度链接实时推送
	 * @param PostUrl
	 * @param Parameters
	 * @return
	 */
	public static String Post(String PostUrl,String[] Parameters){
		if(null == PostUrl || null == Parameters || Parameters.length ==0){
			return null;
		}
        String result="";
        PrintWriter out=null;
        BufferedReader in=null;
        try {
            //建立URL之间的连接
            URLConnection conn=new URL(PostUrl).openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("Host","data.zz.baidu.com");
            conn.setRequestProperty("User-Agent", "curl/7.12.1");
            conn.setRequestProperty("Content-Length", "83");
            conn.setRequestProperty("Content-Type", "text/plain");
             
            //发送POST请求必须设置如下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);
             
            //获取conn对应的输出流
            out=new PrintWriter(conn.getOutputStream());
            //发送请求参数
            String param = "";
            for(String s : Parameters){
            	param += s+"\n";
            }
            out.print(param.trim());
            //进行输出流的缓冲
            out.flush();
            //通过BufferedReader输入流来读取Url的响应
            in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=in.readLine())!= null){
                result += line;
            }
             
        } catch (Exception e) {
            System.out.println("发送post请求出现异常！"+e);
            e.printStackTrace();
        } finally{
            try{
                if(out != null){
                    out.close();
                }
                if(in!= null){
                    in.close();
                }
                 
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
	
	/**
	 * 页面静态化。
	 */
	public void ps() {
		try {
			// 每个菜单分开初始化。
			this.lstProjectMenu = this.objProjectMenuService.listBySql(" SELECT * FROM projectMenu s WHERE s.`level`=2 AND s.`validate`=1");
			// 获取所有需要静态化的新闻。
			if (lstProjectMenu != null && lstProjectMenu.size() > 0)
			{
				for (ProjectMenu projectMenu : lstProjectMenu) {
					List<News> lstNews = this.objNewsService.listBySql("select * from news where staticFlag=1 and mid=" + projectMenu.getlId());
					
					if (lstNews != null && lstNews.size() >0 )
					{
						for (News news : lstNews) {
							try {
								logger.info("新闻：《" + news.getlId() + "》静态化开始");
								
								this.newsID = news.getlId();
								// 静态化逻辑操作
								detailStatic();
								logger.info("新闻：《" + news.getlId() + "》静态化完成");
							} catch (Exception e) {
								logger.error("新闻：《" + news.getlId() + "》静态化失败", e.getMessage());
							}
						}
						Thread.sleep(2000);
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void psV2() {
		List<Object> list = this.objNewsService.queryBySql("select count(*) from news where staticFlag=1");
		int allRecord = 0;
		if (list == null || list.size() <= 0)
		{
			return;
		}
		
		Object objAllRecord = list.get(0);
		
		allRecord = Integer.parseInt(objAllRecord.toString());
		if (allRecord > 200) {
			int allPage = (allRecord - 1) / 100 + 1;

			for (int i = 0; i < allPage; i++) {
				List<News> lstNews = this.objNewsService.listBySql("select * from news where staticFlag=1  order by id asc limit " + (i * 100 + 1) + ",100");

				if (lstNews != null && lstNews.size() > 0) {
					for (News news : lstNews) {
						try {
							logger.info("开始静态化新闻：《" + news.getlId() + "》");

							this.newsID = news.getlId();
							// 静态化逻辑操作
							detailStatic();
							// 睡眠1s
							Thread.sleep(1000);
						} catch (Exception e) {
							logger.info("新闻：《" + news.getlId() + "》静态化失败");
							e.printStackTrace();
						}
					}
				}
			}

		} else {
			List<News> lstNews = this.objNewsService.listBySql("select * from news where staticFlag=1");

			if (lstNews != null && lstNews.size() > 0) {
				for (News news : lstNews) {
					try {
						logger.info("新闻：《" + news.getlId() + "》静态化开始");

						this.newsID = news.getlId();
						// 静态化逻辑操作
						detailStatic();

						logger.info("新闻：《" + news.getlId() + "》静态化完成");
					} catch (Exception e) {
						logger.info("新闻：《" + news.getlId() + "》静态化失败");
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 定时汇总最新数据。
	 */
	public void gatherlatestNews()
	{
		// 每个菜单分开初始化。
		this.lstProjectMenu = this.objProjectMenuService.listBySql(" SELECT * FROM projectMenu s WHERE s.`level`=1 AND s.`validate`=1 and s.menukey != 'aboutUs'");
		
		try {
			if (this.lstProjectMenu != null && this.lstProjectMenu.size() > 0)
			{
				for (ProjectMenu projectMenu : lstProjectMenu) {
					// 删掉原有数据。
					this.objNewsTempService.excuteBySql("delete from newstemp where parmid=" + projectMenu.getlId());
					
					// 获取对应菜单分类下最新新闻。
					StringBuffer sbufNewsTemp = new StringBuffer();
					sbufNewsTemp = newsInsertSql(projectMenu.getlId());
					sbufNewsTemp.append(" FROM");
					sbufNewsTemp.append("  news a ");
					sbufNewsTemp.append(" WHERE a.mid in(select b.id from projectmenu b where b.parid=" + projectMenu.getlId() + ") ORDER BY a.id desc LIMIT 15 ");
					this.objNewsTempService.excuteBySql(sbufNewsTemp.toString());
					
					// 获取阅读排行。
					StringBuffer sbufTopOfReading = new StringBuffer();
					sbufTopOfReading = newsInsertSql(projectMenu.getlId());
					sbufTopOfReading.append(" FROM");
					sbufTopOfReading.append(" news a , projectmenu b ");
					sbufTopOfReading.append(" WHERE a.mid = b.id AND b.parid = " + projectMenu.getlId() + " and a.id not in(select id from newstemp) ORDER BY readNum DESC LIMIT 9 ");
					this.objNewsTempService.excuteBySql(sbufTopOfReading.toString());
				}
			}
			
			// 子菜单 阅读排行。
			toWeb(Action.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
					
	}
	
	/**
	 * 插入sql。
	 * 
	 * @return
	 */
	private StringBuffer newsInsertSql(Long mid)
	{
		StringBuffer sbufNewsTemp = new StringBuffer();
		
		sbufNewsTemp.append(" INSERT INTO `newstemp` (");
		sbufNewsTemp.append("  `id`,");
		sbufNewsTemp.append("  `MID`,");
		sbufNewsTemp.append("  `PID`,");
		sbufNewsTemp.append("  `UID`,");
		sbufNewsTemp.append("  `title`,");
		sbufNewsTemp.append("  `NContent`,");
		sbufNewsTemp.append("  `type`,");
		sbufNewsTemp.append("  `sendDate`,");
		sbufNewsTemp.append("  `stick`,");
		sbufNewsTemp.append("  `reserver1`,");
		sbufNewsTemp.append("  `reserver2`,");
		sbufNewsTemp.append("  `reserver3`,");
		sbufNewsTemp.append("  `reserver4`,");
		sbufNewsTemp.append("  `summary`,");
		sbufNewsTemp.append("  `readnum`,");
		sbufNewsTemp.append("  `addressFrom`,");
		sbufNewsTemp.append("  `sendStatus`,");
		sbufNewsTemp.append("  `shortURL`,");
		sbufNewsTemp.append("  `staticFlag`,");
		sbufNewsTemp.append("  `parmid`,");
		sbufNewsTemp.append("  `imageURL`");
		sbufNewsTemp.append(") ");
		sbufNewsTemp.append(" SELECT ");
		sbufNewsTemp.append("  a.`id`, ");
		sbufNewsTemp.append("    `MID`,");
		sbufNewsTemp.append("    a.`PID`,");
		sbufNewsTemp.append("    `UID`,");
		sbufNewsTemp.append("    `title`,");
		sbufNewsTemp.append("    `NContent`,");
		sbufNewsTemp.append("    `type`,");
		sbufNewsTemp.append("    `sendDate`,");
		sbufNewsTemp.append("    `stick`,");
		sbufNewsTemp.append("    `reserver1`,");
		sbufNewsTemp.append("    `reserver2`,");
		sbufNewsTemp.append("    `reserver3`,");
		sbufNewsTemp.append("    `reserver4`,");
		sbufNewsTemp.append("    `summary`,");
		sbufNewsTemp.append("    `readnum`,");
		sbufNewsTemp.append("    `addressFrom`,");
		sbufNewsTemp.append("    `sendStatus`,");
		sbufNewsTemp.append("    `shortURL`,");
		sbufNewsTemp.append("    `staticFlag`,");
		sbufNewsTemp.append(mid);
		sbufNewsTemp.append(" , `imageURL`");
		return sbufNewsTemp;
	}
	
	/**
	 * 移动端访问静态化---暂不使用。
	 * 
	 * @return
	 */
	public void detailMobileStatic()
	{
		// 获取消息详细信息。
		if ((this.newsID == null) || (this.newsID.longValue() <= 0L)) {
			setErrorText("没有获取到要查看的文章");
			return;
		}
				
		try {
			
			this.objNews = this.objNewsService.getById(this.newsID);
			
			if (this.objNews == null) {
				setErrorText("无法获取到文章信息，请检查您的网址是否正确！");
				return;
			}
			
			lProjectMenuID = objNews.getObjProjectMenu().getObjParentProjectMenu().getlId();
			// 加载有效的子菜单
			setSubProjectMenu(lProjectMenuID);
			objSubProjectMenu = objNews.getObjProjectMenu();
			this.objProject = objNews.getObjProject();
			setProjectMenu();
			
			// 加载育婴师最新发布动态 获取前9个
			this.setNewestMessage(5);
			
			// 设置推荐阅读栏。lstSuggestReading
			this.setSuggestReading(5);
			
			// 生成静态化文件
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			Map<String, Object> mapInData = new HashMap<String, Object>();
			
			// 项目菜单id。
			mapInData.put("lProjectMenuID", lProjectMenuID);
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
			// 设置选中菜单
			mapInData.put("strCurMenu", this.strCurMenu);
			mapInData.put("objNews", this.objNews);
			mapInData.put("objUpNews", this.objUpNews);
			mapInData.put("objNextNews", this.objNextNews);
			mapInData.put("objProjectMenu", this.objProjectMenu);
			
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objFreemarkerUtils.createFile("menu/m_news.ftl", mapInData, "static/m_news/" + this.objNews.getlId());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		// 获取项目主菜单。
		this.objProjectMenu = this.objSubProjectMenu.getObjParentProjectMenu();
		
		// 加载项目信息。
		this.objProject=objProjectMenu.getObjProject();
		
		// 加载有效的子菜单
		setSubProjectMenu(lProjectMenuID);
		
		if (objProject == null) {
			setErrorText("无法获取您的项目信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
		try {
			Pagination<News> objPagination = new Pagination<News>();
			
			// 加载育婴师最新发布动态 获取前9个
			setNewestMessage(Pagination.PAGE_SIZE_9);
			
			// 设置推荐阅读栏目。
			setSuggestReading(Pagination.PAGE_SIZE_RIGHT);
			
			// 根据菜单id获取菜单下信息列表。
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
			Criterion objSQLCondition = Restrictions.sqlRestriction(" mid ='" + objSubProjectMenu.getlId() + "'");
			objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
			this.objGPagination = objPagination;
			
			// 生成静态化文件
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			Map<String, Object> mapInData = new HashMap<String, Object>();
			
			// 设置当前选中菜单。
			strCurMenu = objSubProjectMenu.getMenuKey(); // 默认当前菜单页面
			
			// 获取项目主菜单。
			mapInData.put("objProjectMenu", this.objProjectMenu);
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
			
			// 阅读量排行 加载前六个。
			mapInData.put("lstHotSuggest", this.lstHotSuggest);
			// 设置选中菜单
			mapInData.put("strCurMenu", this.strCurMenu);
			
			mapInData.put("objGPagination", this.objGPagination);
			mapInData.put("menuID", this.menuID);
			mapInData.put("objSubProjectMenu", this.objSubProjectMenu);
			
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objFreemarkerUtils.createFile("menu/news-list.ftl", mapInData, "static/menu/sm/" + this.objSubProjectMenu.getMenuKey() + "/" + nCurrentPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "newsList";
	}
	
	/**
	 * 获取指定菜单下新闻信息。
	 * 
	 * @return
	 */
	public void newsListStatic()
	{
		String nPageSize = getRequest().getParameter("rows"); // 每页显示多少个数据。
		String nCurrentPage = getRequest().getParameter("page");// 第几页。
		
		// 获取消息详细信息。
		if ((this.menuID == null) || (this.menuID.longValue() <= 0L)) {
			setErrorText("没有获取到指定菜单参数，请检查网址是否正确！");
			return;
		}
		
		// 获取项目子菜单。
		objSubProjectMenu = this.objProjectMenuService.getByHql(" from ProjectMenu where id='" + this.menuID + "'");
		if (objSubProjectMenu == null) {
			setErrorText("无法获取您的菜单信息，请检查后重试！");
			return;
		}
		
		// 获取项目主菜单。
		this.objProjectMenu = this.objSubProjectMenu.getObjParentProjectMenu();
		
		// 加载项目信息。
		this.objProject=objProjectMenu.getObjProject();
		
		// 加载有效的子菜单
		setSubProjectMenu(objProjectMenu.getlId());
		
		if (objProject == null) {
			setErrorText("无法获取您的项目信息，请检查后重试！");
			return;
		}
		
		try {
			Pagination<News> objPagination = new Pagination<News>();
			
			// 加载育婴师最新发布动态 获取前9个
			setNewestMessage(Pagination.PAGE_SIZE_9);
			
			// 设置推荐阅读栏目。
			setSuggestReading(Pagination.PAGE_SIZE_RIGHT);
			
			// 根据菜单id获取菜单下信息列表。
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
			Criterion objSQLCondition = Restrictions.sqlRestriction(" mid ='" + objSubProjectMenu.getlId() + "'");
			objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
			this.objGPagination = objPagination;
			
			// 生成静态化文件
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			Map<String, Object> mapInData = new HashMap<String, Object>();
			
			// 设置当前选中菜单。
			strCurMenu = objSubProjectMenu.getMenuKey(); // 默认当前菜单页面
			
			// 获取项目主菜单。
			mapInData.put("objProjectMenu", this.objProjectMenu);
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
			
			// 阅读量排行 加载前六个。
			mapInData.put("lstHotSuggest", this.lstHotSuggest);
			// 设置选中菜单
			mapInData.put("strCurMenu", this.strCurMenu);
			
			mapInData.put("objGPagination", this.objGPagination);
			mapInData.put("menuID", this.menuID);
			mapInData.put("objSubProjectMenu", this.objSubProjectMenu);
			
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objFreemarkerUtils.createFile("menu/news-list.ftl", mapInData, "static/menu/sm/" + this.objSubProjectMenu.getMenuKey() + "/" + nCurrentPage);
		} catch (Exception e) {
			e.printStackTrace();
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
		
		// 获取项目子菜单。
		objProjectMenu = this.objProjectMenuService.getByHql(" from ProjectMenu where id='" + this.lProjectMenuID + "'");
		if (objProjectMenu == null) {
			setErrorText("无法获取您的标签信息，请检查后重试！");
			return COMMON_ERROR;
		}
		
//		// 获取项目主菜单。
//		this.objProjectMenu = this.objSubProjectMenu.getObjParentProjectMenu();
		
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
			setNewestMessage(Pagination.PAGE_SIZE_9);
			
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
			NewsTag objNewsTag = ojbNewsTagService.getById(NewsTag.G_SUGGESTIONREADING);
			this.lstSuggestReading = new ArrayList<News>(objNewsTag.getLstNews());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 设置当前选中菜单。
		// strCurMenu = objSubProjectMenu.getMenuKey(); // 默认当前菜单页面
		
		return "newsList";
	}
	
	/**
	 * 新闻搜索。
	 * 
	 * @return
	 */
	public String newsSearch()
	{
		String searchText = getRequest().getParameter("searchText"); // 查询文本。
		String nPageSize = getRequest().getParameter("rows"); // 每页显示多少个数据。
		String nCurrentPage = getRequest().getParameter("page");// 第几页。
		
		if (searchText == null || "".equals(searchText.trim()))
		{
			setErrorText("请输入查询条件！");
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
			setNewestMessage(Pagination.PAGE_SIZE_9);
			
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
			Criterion objSQLCondition = Restrictions.sqlRestriction(" mid in(select s.id from projectmenu s where s.parid='" + this.lProjectMenuID + "') and ncontent like '%" + searchText + "%'");
			objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition, null), Order.desc("strSendDate"));
			this.objGPagination = objPagination;
			
			// 设置推荐阅读栏。lstSuggestReading
			NewsTag objNewsTag = ojbNewsTagService.getById(NewsTag.G_SUGGESTIONREADING);
			this.lstSuggestReading = new ArrayList<News>(objNewsTag.getLstNews());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "search";
	}
	
	/**
	 * 设置推荐阅读栏目。
	 */
	private void setSuggestReading(int num)
	{
		// 设置推荐阅读栏。
		this.lstSuggestReading = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContentOne() + " from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_SUGGESTIONREADING + " ORDER BY a.id desc LIMIT " + num);
	}
	
	/**
	 * 加载育婴师最新发布动态 获取前9个
	 */
	private void setNewestMessage(int num)
	{
		// 加载育婴师最新发布动态 获取前9个
		// this.lstNewestMessage = objNewsService.listBySql(" select a.* from News a,projectmenu b where a.mid=b.id and b.parid=" + this.lProjectMenuID + " ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_9);
		this.lstNewestMessage = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContent() + " from newstemp a  where a.parmid=" + this.lProjectMenuID + " ORDER BY a.id desc LIMIT " + num);
	}
	
	/**
	 * 设置项目信息。
	 */
	private boolean setProjectInfo()
	{
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
		lstProjectMenu = this.objProjectMenuService.listByHql(" from ProjectMenu where objProject.lId='" + this.objProject.getlId() + "' and validate=1 and level=1 and menuKey !='aboutUs'");
	}
	
	/**
	 * 设置项目子菜单。
	 */
	private void setSubProjectMenu(long lProjectMenuID)
	{
		// 加载首页需要显示的菜单(只显示有效菜单)。
		lstProjectMenu = this.objProjectMenuService.listByHql(" from ProjectMenu where objParentProjectMenu.lId=" + lProjectMenuID + " and validate=1 and level=2");
	}
	
//	/**
//	 * 菜单信息展示。
//	 * 
//	 * @return
//	 */
//	public String menuShow()
//	{
//		if (this.lProjectMenuID == null || this.lProjectMenuID <= 0)
//		{
//			setErrorText("无法获取您的项目菜单信息，请检查后重试！");
//			return COMMON_ERROR;
//		}
//		
//		objProjectMenu = this.objProjectMenuService.getByHql(" from ProjectMenu where id='" + this.lProjectMenuID + "'");
//		if (objProjectMenu == null) {
//			setErrorText("无法获取您的菜单信息，请检查后重试！");
//			return COMMON_ERROR;
//		}
//		
//		this.objProject=objProjectMenu.getObjProject();
//		setProjectMenu();
//		
//		if (objProject == null) {
//			setErrorText("无法获取您的项目信息，请检查后重试！");
//			return COMMON_ERROR;
//		}
//		
//		return "toMenuShow";
//	}
	
//	/**
//	 * 新闻阅读。
//	 * 
//	 * @return
//	 */
//	public String newsRead() {
//		
//		if ((this.newsID == null) || (this.newsID.longValue() <= 0L)) {
//			setErrorText("没有获取到要修改的新闻");
//			return "commonError";
//		}
//
//		try {
//			this.objNews = this.objNewsService.getById(this.newsID);
//			if (this.objNews == null) {
//				setErrorText("无法获取到新闻信息，请检查新闻参数是否传递正确");
//				return "commonError";
//			}
//			
//			// 获取项目信息。
//			this.objProject = this.objNews.getObjProject();
//			setProjectMenu();
//		} catch (Exception e) {
//			logger.error("出现异常", e);
//			setErrorText("出现异常请刷新重试！");
//			return "commonError";
//		}
//		
//		return "projectNews";
//	}
	
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
		return "aboutUs";
	}

	/**
	 * 查询。
	 * 
	 * @return
	 */
	public String search()
	{
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

	public String getStrCurMenu() {
		return strCurMenu;
	}

	public Long getMenuID() {
		return menuID;
	}

	public void setMenuID(Long menuID) {
		this.menuID = menuID;
	}

	public void setStrCurMenu(String strCurMenu) {
		this.strCurMenu = strCurMenu;
	}

	public ProjectMenu getObjSubProjectMenu() {
		return objSubProjectMenu;
	}

	public void setObjSubProjectMenu(ProjectMenu objSubProjectMenu) {
		this.objSubProjectMenu = objSubProjectMenu;
	}

	public Pagination getObjGPagination() {
		return objGPagination;
	}

	public void setObjGPagination(Pagination objGPagination) {
		this.objGPagination = objGPagination;
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

	public Long getNewsTag() {
		return newsTag;
	}

	public void setNewsTag(Long newsTag) {
		this.newsTag = newsTag;
	}

	public NewsTagService getOjbNewsTagService() {
		return ojbNewsTagService;
	}

	public void setOjbNewsTagService(NewsTagService ojbNewsTagService) {
		this.ojbNewsTagService = ojbNewsTagService;
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

	public List<ProjectMenu> getLstChildMenu() {
		return lstChildMenu;
	}

	public void setLstChildMenu(List<ProjectMenu> lstChildMenu) {
		this.lstChildMenu = lstChildMenu;
	}
	
	
}