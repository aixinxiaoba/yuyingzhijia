package com.manage.crm.action.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javacommon.core.base.BaseStruts2Action;
import javacommon.util.freemarker.FreemarkerUtils;

import com.manage.crm.entity.News;
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.service.NewsService;
import com.manage.crm.service.ProjectMenuService;
import com.manage.crm.util.Pagination;

/**
 * 静态处理action 处理所有移动端静态化页面请求。
 * 
 * 2016年6月1日21:21:47
 * 
 * @author wei
 */
@Controller("staticDisposal")
public class MStaticDisposal extends BaseStruts2Action {

	@Resource(name = "newsService")
	private NewsService objNewsService;

	@Resource(name = "projectMenuService")
	private ProjectMenuService objProjectMenuService;

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
	
	/**
	 * 全局静态化子菜单
	 * 
	 * @param lstProjectMenu
	 *            项目主菜单 level 1
	 * @param objFreemarkerUtils
	 * @throws Exception
	 */
	public void ab() throws Exception {
		
	}

}