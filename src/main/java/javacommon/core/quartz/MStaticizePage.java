package javacommon.core.quartz;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javacommon.core.Config;
import javacommon.util.freemarker.FreemarkerUtils;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manage.crm.entity.News;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.service.AttachsService;
import com.manage.crm.service.NewsService;
import com.manage.crm.service.NewsTagService;
import com.manage.crm.service.ProjectMenuService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.VisitLogService;


/**
 * 移动端页面静态化定时器。
 * 
 * @author wei
 *
 */
public class MStaticizePage{
	/**
	 * 日志对象
	 */
	protected static final Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);
	
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
	
	@Resource(name = "attachsService")
	private AttachsService objAttachsService;
	
	private Project objProject;
	
	private List<ProjectMenu> lstProjectMenu;
	
	private List<ProjectMenu> lstChildMenu; // 所有子菜单(边缘显示菜单)
	
	// 最新公告。
	private List<News> lstNewestMessage;
	
	private String projectKey;
	
	private String strCurMenu; // 选中菜单
	
	// 推荐阅读。
	private List<News> lstSuggestReading;
	
	private List<News> lstTopOfReading; // 阅读排行
	
	private List<News> lstRollingOfReading; // 滚动新闻。
	
	private String searchText; // 查询条件
	
	private static boolean isRunning = false;
	
	private static String preUrl;
	
	public void execute()
	{
		preUrl = Config.objSAASConfig.getProperty("app.url.pre");
		logger.info("页面静态化定时器开始执行，当前时间为：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		if (!isRunning)
		{
			// 阅读页面静态化。
			page();
//			// 格式化子菜单
			subMenu();
//			// 菜单静态化。
			menu();
//			// 首页静态化
			index();
			isRunning = true;
		}
		else
		{
			logger.info("当前存在正在执行的定时任务！");
		}
	}

	/**
	 * 页面静态化。
	 */
	private void page() {
		try {
			// 获取所有父类。
			this.lstProjectMenu = this.objProjectMenuService.listBySql("select * from projectmenu where level= 1 and menuKey!='aboutUs'");

			for (ProjectMenu projectMenu : lstProjectMenu) {
				// 获取所有的二级菜单。
				this.lstChildMenu = this.objProjectMenuService.listBySql("select * from projectmenu where parid=" + projectMenu.getlId());

				for (ProjectMenu projectChildMenu : lstChildMenu) {
					List<News> lstNews = this.objNewsService.listBySql("select * from news where mid=" + projectChildMenu.getlId());
					
					for (News news : lstNews) {
						String strUrl = preUrl + "/front/yuyingshi/detailMobileStatic.do?newsID=" + news.getlId();
						Jsoup.connect(strUrl).timeout(30000).get();
						Thread.sleep(2000);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 菜单静态化。
	 */
	private void menu() {
		this.lstProjectMenu = this.objProjectMenuService.listBySql("select * from projectmenu where level=1 and menuKey != 'aboutUs'");
	
		try {
			for (ProjectMenu projectMenu : lstProjectMenu) {
				Jsoup.connect(preUrl + "/front/yuyingshi/indexStatic.do?lProjectMenuID=" + projectMenu.getlId()).timeout(30000).get();
			}
			System.out.println("菜单静态化完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void subMenu() {
		this.lstProjectMenu = this.objProjectMenuService.listBySql("select * from projectmenu where level=2");

		try {
			for (ProjectMenu projectMenu : lstProjectMenu) {
				Jsoup.connect(preUrl + "/front/yuyingshi/newsListStatic.do?menuID=" + projectMenu.getlId()).timeout(30000).get();
			}
			System.out.println("菜单静态化完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 首页静态化。
	 */
	private void index() {
		try {
			Jsoup.connect(preUrl + "/indexStatic.do").timeout(30000).get();
			System.out.println("首页静态化完成");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
