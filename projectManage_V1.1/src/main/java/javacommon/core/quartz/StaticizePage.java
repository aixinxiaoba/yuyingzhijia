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
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.service.AttachsService;
import com.manage.crm.service.NewsService;
import com.manage.crm.service.NewsTagService;
import com.manage.crm.service.ProjectMenuService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.VisitLogService;


/**
 * 页面静态化定时器。
 * 
 * @author wei
 *
 */
public class StaticizePage{
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
	
	private List<ProjectMenu> lstProjectMenu;
	
	private List<ProjectMenu> lstChildMenu; // 所有子菜单(边缘显示菜单)
	
	private static boolean isStaticRunning = false;
	
	private static String preUrl;
	
	public void execute()
	{
		preUrl = Config.objSAASConfig.getProperty("app.url.pre");
		
		logger.info("=========================页面静态化定时器开始执行，当前时间为：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "=============");
		if (!isStaticRunning)
		{
			// 阅读页面静态化。
			pageSimple();
			// 格式化子菜单
			subMenu();
			// 菜单静态化。
			menu();
			// 首页静态化
			index();
			isStaticRunning = true;
		}
		else
		{
			logger.info("当前存在正在执行的定时任务！");
		}
	}

	/**
	 * 页面静态化--简单调用方法。
	 */
	private void pageSimple() {
		try {
			// PC端 + 移动端
			String strUrl = preUrl + "/front/yuyingshi/ps.do";
			logger.info("=============定时器 新闻静态化开始==============" + strUrl);
			Jsoup.connect(strUrl).timeout(60000*60).get();
			logger.info("=============定时器 新闻静态化完成==============");
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
			logger.info("=============定时器 菜单静态化开始==============");
			for (ProjectMenu projectMenu : lstProjectMenu) {
				Jsoup.connect(preUrl + "/front/yuyingshi/indexStatic.do?lProjectMenuID=" + projectMenu.getlId()).timeout(30000).get();
			}
			logger.info("=============定时器 菜单静态化完成==============");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void subMenu() {
		this.lstProjectMenu = this.objProjectMenuService.listBySql("select * from projectmenu where level=2");

		try {
			logger.info("=============定时器 子菜单静态化开始==============");
			for (ProjectMenu projectMenu : lstProjectMenu) {
				Jsoup.connect(preUrl + "/front/yuyingshi/newsListStatic.do?menuID=" + projectMenu.getlId()).timeout(30000).get();
			}
			logger.info("=============定时器 子菜单静态化完成==============");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 首页静态化。
	 */
	private void index() {
		try {
			logger.info("=============定时器 首页静态化开始==============");
			Jsoup.connect(preUrl + "/front/index/indexStatic.do").timeout(30000).get();
			logger.info("=============定时器 首页静态化完成==============");
		} catch (IOException e) {
			e.printStackTrace();
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
					List<News> lstNews = this.objNewsService.listBySql("select * from news where staticFlag=1 and mid=" + projectChildMenu.getlId());
					
					logger.info("当前处理菜单为：" + projectChildMenu.getlId() + "--" + projectChildMenu.getStrMenuName());
					
					for (News news : lstNews) {
						try {
							System.out.println("新闻：《" + news.getlId() + "》静态化开始");
							// PC端 + 移动端
							String strUrl = preUrl + "/front/yuyingshi/detailStatic.do?lProjectMenuID=" + projectMenu.getlId() + "&newsID=" + news.getlId();
							Jsoup.connect(strUrl).timeout(60000).get();

							// 移动端
							String strMUrl = preUrl + "/front/yuyingshi/detailMobileStatic.do?newsID=" + news.getlId();
							Jsoup.connect(strMUrl).timeout(60000).get();
							Thread.sleep(2000);
							System.out.println("新闻：《" + news.getlId() + "》静态化完成");
						} catch (Exception e) {
							System.out.println("新闻：《" + news.getlId() + "》静态化失败");
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
