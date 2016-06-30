package com.manage.crm.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.core.base.BaseStruts2Action;
import javacommon.util.SearchCondition;
import javacommon.util.freemarker.FreemarkerUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import com.manage.crm.util.Pagination;
import com.opensymphony.xwork2.Action;

/**
 * 静态处理action 处理所有移动端静态化页面请求。
 * 
 * 2016年6月1日21:21:47
 * 
 * @author wei
 */
@Controller("mStaticDisposalAction")
@Scope("prototype")
public class MStaticDisposalAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(MStaticDisposalAtion.class);

	private static final String PROJECT_KEY_YYZJ = "yuyingzhijia";

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
	private Long projectMenuID;

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
	
	private Long lProjectMenuID;
	
	private String param1;

	/**
	 * 移动端首页静态化。
	 * 
	 * @return
	 * @throws Exception
	 */
	public void indexStatic() throws Exception {
		try {
			this.objProject = objProjectService.getProjectByProjectKey(PROJECT_KEY_YYZJ);
			// 验证项目是否合法
			if (this.objProject == null) {
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return;
			}

			// 设置项目子菜单。
			lstProjectMenu = objProjectMenuService.lstProjectMenuByProId(this.objProject.getlId());
			
			// 加载最新动态 获取前6个
			this.lstNewestMessage = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutReadNum() + ",(select a.readnum from news a where a.id=b.id) readnum from NewsTemp b ORDER BY id desc LIMIT " + Pagination.PAGE_SIZE_6);
			// 设置推荐阅读栏。
			this.lstSuggestReading = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContentOne() + " from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_SUGGESTIONREADING
					+ " and a.imageurl is not null and imageurl !='' ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
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
//			 项目子菜单
//			mapInData.put("lstChildMenu", this.lstChildMenu);
			// 加载最新动态 获取前6个
			mapInData.put("lstNewestMessage", this.lstNewestMessage);
			// 设置推荐阅读栏。
			mapInData.put("lstSuggestReading", this.lstSuggestReading);
			// 阅读量排行 加载前六个。
			mapInData.put("lstTopOfReading", this.lstTopOfReading);
			// 滚动图片 加载前5个(id倒序)。Rolling
			mapInData.put("lstRollingOfReading", this.lstRollingOfReading);
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objFreemarkerUtils.createFile("mobile/index.ftl", mapInData, "static/m/index.html");

		} catch (Exception e) {
			e.printStackTrace();
		}

		toWeb(Action.SUCCESS);
	}
	
	/**
	 * 移动端二级分类整体静态化。
	 * 
	 * @return
	 * @throws Exception
	 */
	public void subMenuGlobalStatic() throws Exception {
		try {
			lstProjectMenu = this.objProjectMenuService.lstValidProjectMenu();
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			this.subMenuGlobalStatic(lstProjectMenu, objFreemarkerUtils);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			setErrorText("静态化处理异常");
			return;
		}

		toWeb(Action.SUCCESS);
	}
	
	/**
	 * 全局静态化子菜单
	 * 
	 * @param lstProjectMenu
	 *            项目主菜单 level 1
	 * @param objFreemarkerUtils
	 * @throws Exception
	 */
	public void subMenuGlobalStatic(List<ProjectMenu> lstProjectMenu, FreemarkerUtils objFreemarkerUtils) throws Exception {
		HttpServletRequest request = getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		request.setAttribute("basePath", basePath);
		// 设置每个项目菜单的子菜单下显示新闻的数量。
		if (lstProjectMenu != null && lstProjectMenu.size() > 0) {
			List<ProjectMenu> lstSubProjectMenu;
			List<News> lstRollingOfReading;

			for (ProjectMenu curProjectMenu : lstProjectMenu) {
				Map<String, Object> mapInData = new HashMap<String, Object>();

				if (curProjectMenu != null) {
					lstRollingOfReading = objNewsService.lstScrollImgByParMenuId(curProjectMenu.getlId(), Pagination.PAGE_SIZE_5);
					lstSubProjectMenu = objProjectMenuService.lstValidSubProjectMenu(curProjectMenu.getlId());
					lstNewestMessage = objNewsService.listBySql(" select a.* from News a  where a.mid in(select b.id from projectmenu b where b.parid=" + curProjectMenu.getlId() + ") ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_9);
					// subMenuGlobalStaticStart(lstSubProjectMenu,
					// lstProjectMenu, objFreemarkerUtils);
					// 项目下所有的一级菜单
					mapInData.put("objProjectMenu", curProjectMenu);
					// 项目下所有的一级菜单
					mapInData.put("lstMainProjectMenu", lstProjectMenu);
					// 项目子菜单
					mapInData.put("lstSubProjectMenu", lstSubProjectMenu);

					// 滚动图片 加载前5个(id倒序)。Rolling
					mapInData.put("lstRollingOfReading", lstRollingOfReading);
					// 滚动图片 加载前5个(id倒序)。Rolling
					mapInData.put("lstNewestMessage", lstNewestMessage);
					mapInData.put("basePath", basePath);
					objFreemarkerUtils.createFile("mobile/sub_menu.ftl", mapInData, "static/m/" + curProjectMenu.getlId() + ".html");
				}
			}
		}
	}

	/**
	 * 指定的二级分类的静态处理。 手动触发此操作，获取最新的二级分类数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public void appointSubMenuStatic() throws Exception {
		try {
			lstProjectMenu = this.objProjectMenuService.lstValidSubProjectMenu(projectMenuID);
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objNewsService.setAppointSubMenuNews(lstProjectMenu, objFreemarkerUtils);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			setErrorText("静态化处理异常");
			toWeb(COMMON_ERROR);
			return;
		}

		toWeb(Action.SUCCESS);
	}

	/**
	 * 所有的二级分类的静态处理。 手动触发此操作，获取最新的二级分类数据
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void subMenuStatic() throws IOException {
		try {
			lstProjectMenu = this.objProjectMenuService.lstValidSubProjectMenu();
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();

			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objNewsService.setAppointSubMenuNews(lstProjectMenu, objFreemarkerUtils);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			setErrorText("静态化处理异常");
			toWeb(COMMON_ERROR);
			return;
		}

		toWeb(Action.SUCCESS);
	}
	
	/**
	 * 所有的二级分类的静态处理。 手动触发此操作，获取最新的二级分类数据
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void subMenuPageStatic() throws IOException {
		try {
			lstProjectMenu = this.objProjectMenuService.lstValidSubProjectMenu();
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();

			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objNewsService.subMenuPageStatic(lstProjectMenu, objFreemarkerUtils);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			setErrorText("静态化处理异常");
			toWeb(COMMON_ERROR);
			return;
		}

		toWeb(Action.SUCCESS);
	}

	/**
	 * 静态化一级菜单下的滚动图片 am=appoint menu
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void amScrollPhotoStatic() throws IOException {
		try {
			// 滚动图片 加载前5个(id倒序)。Rolling projectMenuID 大类id，level=1
			this.objProjectMenu = this.objProjectMenuService.getById(projectMenuID);
			this.lstRollingOfReading = objNewsService.lstScrollImgByParMenuId(projectMenuID, Pagination.PAGE_SIZE_5);
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();

			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objNewsService.setAmScrollPhotoStatic(lstRollingOfReading, objFreemarkerUtils, String.valueOf(objProjectMenu.getlId()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			setErrorText("静态化处理异常");
			toWeb(COMMON_ERROR);
			return;
		}

		toWeb(Action.SUCCESS);
	}

	/**
	 * 静态化一级菜单下的滚动图片
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void scrollPhotoStatic() throws IOException {
		try {
			// 滚动图片 加载前5个(id倒序)。Rolling projectMenuID 大类id，level=1
			lstProjectMenu = this.objProjectMenuService.lstValidProjectMenu();
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();

			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objNewsService.scrollPhotoStatic(lstProjectMenu, objFreemarkerUtils);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			setErrorText("静态化处理异常");
			toWeb(COMMON_ERROR);
			return;
		}

		toWeb(Action.SUCCESS);
	}
	
	/**
	 * 静态化二级菜单下的滚动图片
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void scrollPhotoLevel2Static() throws IOException {
		try {
			// 滚动图片 加载前5个(id倒序)。Rolling projectMenuID 大类id，level=1
			lstProjectMenu = this.objProjectMenuService.lstValidSubProjectMenu();
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();

			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			objNewsService.scrollPhotoStatic(lstProjectMenu, objFreemarkerUtils);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			setErrorText("静态化处理异常");
			toWeb(COMMON_ERROR);
			return;
		}

		toWeb(Action.SUCCESS);
	}

	/**
	 * 移动端导航。
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void nav() throws IOException
	{
		try {
			lstProjectMenu = this.objProjectMenuService.lstValidProjectMenu();
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
		
		toWeb(Action.SUCCESS);
	}
	
	/**
	 *  移动端 pc端 静态化。
	 * 
	 * @return
	 */
	public void detailStatic()
	{
		try {
			this.newsID = Long.parseLong(this.param1);
			// 获取消息详细信息。
			if ((this.newsID == null) || (this.newsID.longValue() <= 0L)) {
				setErrorText("没有获取到要查看的文章");
				toWeb(Action.ERROR);
				return;
			}
			this.objNews = this.objNewsService.getById(this.newsID);
			lProjectMenuID = this.objNews.getObjProjectMenu().getObjParentProjectMenu().getlId();
			if (this.objNews == null) {
				setErrorText("无法获取到文章信息，请检查您的网址是否正确！");
				toWeb(Action.ERROR);
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
			lstProjectMenu = this.objProjectMenuService.lstValidSubProjectMenu(lProjectMenuID);
			
			if (objProject == null) {
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return;
			}
			
			// 加载育婴师最新发布动态 获取前9个
			this.objNewsService.lstAppointNumNews(lProjectMenuID, Pagination.PAGE_SIZE_5);
			
			// 设置推荐阅读栏。lstSuggestReading
			this.lstSuggestReading = objNewsService.listBySql(" select " + DBSql.getNewsColumnWithOutContentOne() + " from News a,newstagrela b where a.id=b.nid and b.ntid=" + NewsTag.G_SUGGESTIONREADING
					+ " and a.imageurl is not null and imageurl !='' ORDER BY a.id desc LIMIT " + Pagination.PAGE_SIZE_RIGHT);
						
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
			
			// 静态化PC端。
			// objFreemarkerUtils.createFile("menu/news.ftl", mapInData, "static/news/" + this.objNews.getlId());
			// 静态化移动端。
			objFreemarkerUtils.createFile("menu/m_news.ftl", mapInData, "static/m_news/" + this.objNews.getlId());
			
			// 推送url到百度。
//			pushUrlToBaiDu();
			
			// 更新静态化状态。
			objNews.setStaticFlag(0);
			objNewsService.update(objNews);
			toWeb(Action.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将链接推送到百度。
	 * 一次推送两条数据。
	 */
	private void pushUrlToBaiDu()
	{
		long projectMenuId = this.objNews.getObjProjectMenu().getlId();
		logger.info("开始向百度推送url");
		String url = "http://data.zz.baidu.com/urls?site=www.yuyingzhijia.cn&token=aHy5BwoLTA7ZGSUJ";//网站的服务器连接
		String[] param = {"http://www.yuyingzhijia.cn/detail/M" + projectMenuId + "/" + this.objNews.getlId() + "",// pc端
				          "http://www.yuyingzhijia.cn/detail/P" + projectMenuId + "/" + this.objNews.getlId() + ""//mobile 端
		};
		// json为推送返回信息。
		String json = Post(url, param);//执行推送方法
		logger.info("向百度推送url结束：" + json);
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
        } finally{
            try{
                if(out != null){
                    out.close();
                }
                if(in!= null){
                    in.close();
                }
            }catch(IOException ex){
            	System.out.println("关闭流出现异常！"+ex);
            }
        }
        return result;
    }
	
	public Long getProjectMenuID() {
		return projectMenuID;
	}

	public void setProjectMenuID(Long projectMenuID) {
		this.projectMenuID = projectMenuID;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

}