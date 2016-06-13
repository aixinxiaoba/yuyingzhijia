package com.manage.crm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.core.base.BaseStruts2Action;
import javacommon.util.freemarker.FreemarkerUtils;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.manage.crm.action.MStaticDisposal;
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
@Controller("mStaticDisposal")
public class MStaticDisposal {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(MStaticDisposal.class);

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

	/**
	 * 全局静态化子菜单
	 * 
	 * @param lstProjectMenu
	 *            项目主菜单 level 1
	 * @param objFreemarkerUtils
	 * @throws Exception
	 */
	public void subMenuGlobalStatic(List<ProjectMenu> lstProjectMenu, FreemarkerUtils objFreemarkerUtils) throws Exception {
		// 设置每个项目菜单的子菜单下显示新闻的数量。
		if (lstProjectMenu != null && lstProjectMenu.size() > 0) {
			List<ProjectMenu> lstSubProjectMenu;
			List<News> lstRollingOfReading;

			for (ProjectMenu curProjectMenu : lstProjectMenu) {
				Map<String, Object> mapInData = new HashMap<String, Object>();

				if (curProjectMenu != null) {
					lstRollingOfReading = objNewsService.lstScrollImgByMenuId(curProjectMenu.getlId(), Pagination.PAGE_SIZE_5);
					lstSubProjectMenu = objProjectMenuService.lstValidSubProjectMenu(curProjectMenu.getlId());
					// subMenuGlobalStaticStart(lstSubProjectMenu,
					// lstProjectMenu, objFreemarkerUtils);
					// 项目菜单
					mapInData.put("lstMainProjectMenu", lstProjectMenu);
					// 项目子菜单
					mapInData.put("objProjectMenu", lstSubProjectMenu);

					// 滚动图片 加载前5个(id倒序)。Rolling
					mapInData.put("lstRollingOfReading", lstRollingOfReading);
					// 设置选中菜单
					// mapInData.put("strCurMenu", this.strCurMenu);
					objFreemarkerUtils.createFile("mobile/sub_menu.ftl", mapInData, "static/m/" + curProjectMenu.getMenuKey());
				}
			}
		}
	}
}