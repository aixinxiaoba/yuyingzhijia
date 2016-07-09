package com.manage.crm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javacommon.core.base.BaseStruts2Action;
import javacommon.util.freemarker.FreemarkerUtils;

import javax.annotation.Resource;

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
import com.manage.crm.util.Pagination;
import com.opensymphony.xwork2.Action;

/**
 * 静态处理action 处理所有移动端静态化页面请求。
 * 
 * 2016年7月3日21:28:31
 * 
 * @author wei
 */
@Controller("pcStaticDisposalAction")
@Scope("prototype")
public class PCStaticDisposalAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(PCStaticDisposalAtion.class);

	private static final String PROJECT_KEY_YYZJ = "yuyingzhijia";
	
	private static final int PAGE_NUM_1 = 1;

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

	private List<ProjectMenu> lstChildMenu; // 所有子菜单(边缘显示菜单)

	// 最新公告。
	private List<News> lstNewestMessage;

	private Long projectMenuID;

	private String strCurMenu; // 选中菜单

	// 推荐阅读。
	private List<News> lstSuggestReading;

	private List<News> lstTopOfReading; // 阅读排行

	private Long lProjectMenuID;

	private String param1;

	// 热点推荐。
	private List<News> lstHotSuggest;

	// 育婴技能。
	private List<News> lstYuYingSkill;

	/**
	 * 育婴师首页处理逻辑。
	 * 
	 * @return
	 */
	public void subMenuIndexStatic() {
		List<ProjectMenu> lstProjectMenu = new ArrayList<ProjectMenu>();
		
		try {
			// 获取所有有效的主菜单
			lstProjectMenu = objProjectMenuService.lstValidProjectMenu();
			
			if (lstProjectMenu != null && lstProjectMenu.size() > 0)
			{
				for (int i = 0; i < lstProjectMenu.size(); i++) {
					subMenuIndexStaticByMenuId(lstProjectMenu.get(i).getlId());
				}
			}
			
			toWeb(Action.SUCCESS);
		} catch (Exception e) {
			logger.error("出现异常：" + e.getMessage());
		}

		return;
	}

	
	/**
	 * 育婴师首页处理逻辑。
	 * 
	 * @return
	 */
	public void subMenuIndexStaticByMenuId(Long lProjectMenuID) {
		// 验证项目菜单是否有效。
		if (lProjectMenuID == null || lProjectMenuID <= 0) {
			setErrorText("无法获取您的项目菜单信息，请检查后重试！");
			return;
		}

		// 获取项目主菜单。
		objProjectMenu = this.objProjectMenuService.getById(lProjectMenuID);
		if (objProjectMenu == null) {
			setErrorText("无法获取您的菜单信息，请检查后重试！");
			return;
		}

		try {
			// 加载项目信息。
			this.objProject = objProjectMenu.getObjProject();
			// 加载有效的子菜单
			lstProjectMenu = this.objProjectMenuService.lstValidSubProjectMenu(lProjectMenuID);
			// 设置每个子菜单下要显示的新闻消息。
			setProjectSubMenuNews(lstProjectMenu);
			if (objProject == null) {
				setErrorText("无法获取您的项目信息，请检查后重试！");
				return;
			}

			// 加载育婴师最新发布动态 获取前9个
			this.lstNewestMessage = this.objNewsService.lstNewestNewsByMenuId(lProjectMenuID, Pagination.PAGE_SIZE_9);

			// 设置推荐阅读栏目。
			this.lstSuggestReading = this.objNewsService.lstNewsByTagId(NewsTag.G_SUGGESTIONREADING, Pagination.PAGE_SIZE_RIGHT);

			// 加载育婴师 阅读排行
			this.lstTopOfReading = this.objNewsService.lstNewsMostReadingBySubMenuId(lProjectMenuID, Pagination.PAGE_SIZE_9);

			// 热点推荐。
			this.lstHotSuggest = this.objNewsService.lstNewsByTagId(NewsTag.G_HOTSUGGESTION, Pagination.PAGE_SIZE_RIGHT);

			// 育婴技能。
			this.lstYuYingSkill = this.objNewsService.lstNewsByTagId(NewsTag.G_YUYINGSKILL, Pagination.PAGE_SIZE_RIGHT);

			// 设置当前选中菜单。
			strCurMenu = "index"; // 首页

			// 生成静态化文件
			FreemarkerUtils objFreemarkerUtils = new FreemarkerUtils();
			objFreemarkerUtils.init(getRequest().getSession().getServletContext());
			Map<String, Object> mapInData = new HashMap<String, Object>();

			// 获取项目主菜单。
			// mapInData.put("lProjectID", this.lProjectID);
			// 获取项目主菜单。
			mapInData.put("objProjectMenu", this.objProjectMenu);
			// 项目
			mapInData.put("objProject", this.objProject);
			// 项目菜单
			mapInData.put("lstProjectMenu", lstProjectMenu);
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
	 * 设置项目子菜单下新闻。
	 */
	private void setProjectSubMenuNews(List<ProjectMenu> lstProjectMenu) {
		// 设置每个项目菜单的子菜单下显示新闻的数量。
		if (lstProjectMenu != null && lstProjectMenu.size() > 0) {
			List<News> lstCurNews;

			for (ProjectMenu parentProjectMenu : lstProjectMenu) {
				// 获取两个带图片的新闻
				List<News> lstImageNews = objNewsService.lstNewestNewsWithImgBySubMenuId(parentProjectMenu.getlId(), PAGE_NUM_1);
				
				if (lstImageNews != null && lstImageNews.size() > 0) {
					parentProjectMenu.setLstImageNews(lstImageNews);
				}
				// 获取7条数据封装到 边缘菜单中
				lstCurNews = objNewsService.lstNewestyLevelTwoMenuId(parentProjectMenu.getlId(), Pagination.PAGE_SIZE_CONTENT);
				lstCurNews.removeAll(lstImageNews); // 去除附带图片的news。
				// 获取7条数据封装到
				parentProjectMenu.setLstNews(new HashSet<News>(lstCurNews));
			}
		}
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

}