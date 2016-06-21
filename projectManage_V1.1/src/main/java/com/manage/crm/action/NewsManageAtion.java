package com.manage.crm.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javacommon.core.Config;
import javacommon.core.base.BaseStruts2Action;
import javacommon.util.HtmlRegexpUtil;
import javacommon.util.NetImageDisposal;
import javacommon.util.SearchCondition;
import javacommon.util.StringUtils;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.manage.crm.entity.News;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.entity.Users;
import com.manage.crm.service.NewsService;
import com.manage.crm.service.NewsTempService;
import com.manage.crm.service.ProjectMenuService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.UsersService;
import com.manage.crm.util.Pagination;

@Controller("newsManageAction")
@Scope("prototype")
public class NewsManageAtion extends BaseStruts2Action {
	private static final long serialVersionUID = -4144061686382328218L;
	private static final Logger logger = LoggerFactory.getLogger(NewsManageAtion.class);
	private static final String NEWS_DETIAL = "newsDetial";
	private static final String NEWS_READ = "newsRead";

	public static String G_2K28= "http://www.2k28.com";
	
	public static String G_5721= "http://baby.5721.net";
	
	private Long lProjectID;
	
	private Long lProjectMenuID;
	
	@Resource(name = "usersService")
	private UsersService objUsersService;

	@Resource(name = "newsService")
	private NewsService objNewsService;
	
	@Resource(name = "projectService")
	private ProjectService objProjectService;
	
	@Resource(name = "projectMenuService")
	private ProjectMenuService objProjectMenuService;
	
	@Resource(name = "newsTempService")
	private NewsTempService objNewsTempService;
	
	private Project objProject; // 项目。
	
	private ProjectMenu objProjectMenu; // 项目菜单。
	
	private List<ProjectMenu> lstProjectMenu;
	
	private News objNews;
	private Users objUsers;
	private Long newsID;
	private String projectKey;
	private String menuKey;
	
	private String searchParam;
	
	private String tempNewsName;
	
	private String tempNewsID;

	/**
	 * 数据抓取。
	 * 
	 * @throws IOException
	 */
	public void dataCapture() throws IOException
	{
		if (!commonValidateUsers()) {
			toWeb("Session 失效！请重新登录");
			return;
		}
		
		if (StringUtils.isEmpty(menuKey))
		{
			toWeb("请输入menuKey");
			return;
		}
		
		String category  = getRequest().getParameter("category");
		String maxPageNum  = getRequest().getParameter("maxPageNum");
		String categoryNum  = getRequest().getParameter("categoryNum");
		String startPageNo  = getRequest().getParameter("startPageNo");
		String webType  = getRequest().getParameter("webType");
		// 判断分类
		if (StringUtils.isEmpty(category))
		{
			toWeb("请输入分类");
			return;
		}
		// 判断最大页数
		if (StringUtils.isEmpty(maxPageNum))
		{
			toWeb("请输入页数");
			return;
		}
		// 判断分类编码
		if (StringUtils.isEmpty(categoryNum))
		{
			toWeb("请输入分类编码");
			return;
		}
		try {
			if (startPageNo == null || startPageNo.length() <= 0)
			{
				startPageNo = "1";
			}
			this.objUsers = this.objUsersService.getByHql(" from Users where lId='1'");
			// 项目
			this.objProject = objProjectService.getByHql(" from Project where projectKey='yuyingzhijia'");
			// 菜单
			this.objProjectMenu = objProjectMenuService.getByHql(" from ProjectMenu where id=" + menuKey + "");
			
			for (int i = Integer.parseInt(startPageNo); i <= Integer.parseInt(maxPageNum); i++) {
				if ("yuyingnet".equals(webType))
				{
					dealOthersPage(i, category, Integer.parseInt(categoryNum));
				}
				else if ("2k28".equals(webType))
				{
					dealsFor2k28(i, category, Integer.parseInt(categoryNum));
				}
				else if ("5721".equals(webType))
				{
					dealsFor5721(i, category, Integer.parseInt(categoryNum));
				}
			}
			
			// 重新汇总最新数据。
			gatherlatestNews();
			toWeb("处理成功！！！");
		} catch (Exception e) {
			logger.error("数据抓取出现异常：", e.getMessage());
			e.printStackTrace();
			toWeb("出现异常，请稍后再试！");
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
     * 
     * 
     * modify 2016年1月22日00:01:43
     * @param pageNo
     * 
     * @throws IOException
     */
    private void dealOthersPage(int pageNo, String type, int totle) throws Exception
	{
		Document doc;
		doc = Jsoup.connect("http://www.yuyingnet.com/"+type+"/list_"+totle+"_" + pageNo + ".html").get();
		
		Elements es = doc.getElementsByClass("list_body_txt");
		Elements ec = es.get(0).getElementsByTag("li");
		
		for (int i = 0; i < ec.size(); i++){
			try {
				objNews = new News();
				
				Attributes as = ec.get(i).child(1).attributes();
				
				doc = Jsoup.connect(as.get("href")).timeout(200000).get();
				Elements escs = doc.getElementsByClass("article_content");
				Elements escTitle = doc.getElementsByClass("article_body");
				
				// 查询此标题文章是否已经存在，如果存在则进行下一个文章处理。
				String strTitle = escTitle.get(0).getElementsByTag("h2").get(0).text();
				News dbNews = objNewsService.getBySql("select * from News where title like '%" + strTitle + "%'");
				if (dbNews != null && dbNews.getlId() > 0)
				{
					// 此文章已经存在，进行下一个文章处理
					logger.info("文章已经存在，继续处理：" + strTitle);
					continue;
				}
				
				logger.info("标题：" + strTitle);;
				// 设置消息标题
				objNews.setStrTitle(strTitle);
				objNews.setStaticFlag(1);// 设置为需要静态化。
				escs.get(0).select("script").remove();
//				escs.get(0).select("img").remove();
				escs.get(0).select("a[href]").remove();
				escs.get(0).select("div[style=float:right; width:300px; height:250px;]").remove();
				escs.get(0).select("[class=article_pages]").remove();
//				escs.get(0).select("br").remove();
				Elements brE =escs.get(0).select("br");
				if (brE.size() >= 2){
					escs.get(0).select("br").last().remove();
					escs.get(0).select("br").last().remove();
				}
				escs.get(0).select("font[color=#FF00FF]").remove();
				logger.info("文章html链接：" + as.get("href") + "--" + pageNo);
				objNews.setStrContent("<font size='3'>" + escs + "</font>");
				//  设置消息内容
				objNews.setAddressFrom("育婴网（www.yuyingnet.com）");
				objNews.setObjProject(objProject);
				objNews.setObjProjectMenu(objProjectMenu);
				objNews.setObjUsers(this.objUsers);
				objNews.setStrSendDate(ec.get(i).child(0).text());
				
				if ("".equals((escs + "")) && HtmlRegexpUtil.filterHtml(escs + "").trim().length() < 98)
				{
					objNews.setStrSummary(HtmlRegexpUtil.filterHtml(escs + "").trim());
				}
				else
				{
					objNews.setStrSummary(HtmlRegexpUtil.filterHtml(escs + "").trim().substring(0,97));
				}
				
				if (escs.get(0).select("img")!=null && escs.get(0).select("img").size() > 0)
				{
					// 如果存在图片则判断是否需要进行下载图片操作。
					NetImageDisposal.startDownLoad(objNews, G_2K28);
				}
				objNewsService.save(objNews);
			} catch (Exception e) {
				logger.error("error page num is ", pageNo);
			}finally{
				
			}
			Thread.sleep(1000);
		}
	}
    
    
    /**
     * 
     * http://baby.5721.net/Newborn/List_1.shtml
     * 中国孕育网
     * create 2016年6月19日17:36:41
     * @param pageNo
     * 
     * @throws IOException
     */
    private void dealsFor5721(int pageNo, String type, int totle) throws Exception
	{
		Document doc;
		String webURL = "http://baby.5721.net";
		doc = Jsoup.connect(webURL + "/"+type+"/list_"+totle+"_" + pageNo + ".shtml").get();
//		Elements es = doc.getElementsByClass("endText");
//		Elements ec = es.get(0).getElementsByClass("listA");
		Elements ec = doc.getElementsByClass("listA");
		
		for (int i = 0; i < ec.size(); i++){
			try {
				objNews = new News();
				
				Attributes as = ec.get(i).attributes();
				doc = Jsoup.connect(webURL + as.get("href")).timeout(200000).get();
				Elements escs = doc.getElementsByClass("Newscontent");
				Element escTitle = doc.getElementById("Con_Left");
				
				// 查询此标题文章是否已经存在，如果存在则进行下一个文章处理。
				String strTitle = escTitle.getElementsByTag("h2").get(0).text();
				News dbNews = objNewsService.getBySql("select * from News where title like '%" + strTitle + "%'");
				
//				if (!"打开紧握的小拳头".equals(strTitle))
//				{
//					continue;
//				}
				logger.info("标题：" + strTitle);;
				// 设置消息标题
				objNews.setStrTitle(strTitle);
//				objNews.setStaticFlag(1);// 设置为需要静态化。
				escs.get(0).select("script").remove();
//				escs.get(0).select("img").remove();
				//escs.get(0).select("a[href]").remove();
				//escs.get(0).select("a[href]").remove();
				escs.get(0).select("li").remove();
				escs.get(0).select("strong").remove();
				escs.get(0).select("div[style=float:right; width:300px; height:250px;]").remove();
				escs.get(0).select("li").remove();
				
				Elements escPs = escs.get(0).getElementsByTag("p");
				String content = "";
//				Element contentMore = null;
				
				if (escPs.size() >= 2)
				{
					for (int j = 0; j < escPs.size(); j++)
					{
						if ("".equals(escPs.get(j).text().trim()))
						{
							escPs.get(j).remove();
						}
					}
//					content = escPs.get(0).toString();
//					escs.get(0).toString();
//					contentMore = escPs.get(1);
					
//					Elements elas = contentMore.getElementsByTag("a");
					Elements elas = escs.get(0).getElementsByTag("a");
					
					if (elas != null && elas.size() > 0)
					{
						escs.get(0).select("a").remove();
						content = escs.get(0).toString();
						for (int j = 0; j < elas.size() - 1; j++)
						{
							Element ela = elas.get(j);
							Attributes atti = ela.attributes();
							
							content += getContentOnly(Jsoup.connect(webURL + atti.get("href")).timeout(200000).get());
						}
					}
					else
					{
						content = escs.get(0).toString();
					}
				}
				escs.get(0).select("[class=article_pages]").remove();
//				escs.get(0).select("br").remove();
//				Elements brE =escs.get(0).select("br");
//				if (brE.size() >= 2){
//					escs.get(0).select("br").last().remove();
//					escs.get(0).select("br").last().remove();
//				}
//				escs.get(0).select("font[color=#FF00FF]").remove();
				logger.info("文章html链接：" + as.get("href") + "--" + pageNo);
				objNews.setStrContent("<font size='3'>" + content + "</font>");
				//  设置消息内容
				objNews.setAddressFrom("中国孕育网（www.5721.com）");
				objNews.setObjProject(objProject);
				objNews.setObjProjectMenu(objProjectMenu);
				objNews.setObjUsers(this.objUsers);
				objNews.setStrSendDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				
				if ("".equals((content)) && HtmlRegexpUtil.filterHtml(content).trim().length() < 98)
				{
					objNews.setStrSummary(HtmlRegexpUtil.filterHtml(content).trim());
				}
				else
				{
					objNews.setStrSummary(HtmlRegexpUtil.filterHtml(content).trim().substring(0,96));
				}
				
				if (escPs.select("img")!=null && escPs.select("img").size() > 0)
				{
					if (dbNews != null && dbNews.getlId() > 0)
					{
						dbNews.setStrContent(objNews.getStrContent());
						// 此文章已经存在，进行下一个文章处理
						logger.info("文章已经存在且存在图片，更新处理：");
						NetImageDisposal.startDownLoad(dbNews, G_5721);
						objNewsService.update(dbNews);
						logger.info("文章更新完成，继续处理：" + strTitle);
						staticDealWith(dbNews);
						continue;
					}
					// 如果存在图片则判断是否需要进行下载图片操作。
					NetImageDisposal.startDownLoad(objNews, G_5721);
				}
				
				if (dbNews != null && dbNews.getlId() > 0)
				{
					logger.info("文章已经存在，继续处理：" + strTitle);
				}
				else
				{
					objNewsService.save(objNews);
					staticDealWith(objNews);
				}
			} catch (Exception e) {
				logger.error("error page num is ", pageNo);
				e.printStackTrace();
			}finally{
				
			}
			Thread.sleep(10000);
		}
	}
    
    /**
     * 获取文章中content
     * @param document
     */
    private String getContentOnly(Document document) {
    	Elements escs = document.getElementsByClass("Newscontent");
    	Elements escPs = escs.get(0).getElementsByTag("p");
    	return escPs.get(0).toString();
	}

	/**
     * 
     * http://www.2k28.com
     * modify 2016年1月22日00:01:43
     * @param pageNo
     * 
     * @throws IOException
     */
    private void dealsFor2k28(int pageNo, String type, int totle) throws Exception
	{
		Document doc;
		String webURL = "http://www.2k28.com";
		doc = Jsoup.connect(webURL + "/"+type+"/list_"+totle+"_" + pageNo + ".html").get();
		Elements es = doc.getElementsByClass("l_r_m");
		Elements ec = es.get(0).getElementsByTag("li");
		
		for (int i = 0; i < ec.size(); i++){
			try {
				objNews = new News();
				
				Attributes as = ec.get(i).child(1).attributes();
				doc = Jsoup.connect(webURL + as.get("href")).timeout(200000).get();
				Elements escs = doc.getElementsByClass("content");
				Elements escTitle = doc.getElementsByClass("viewbox");
				
				// 查询此标题文章是否已经存在，如果存在则进行下一个文章处理。
				String strTitle = escTitle.get(0).getElementsByTag("h1").get(0).text();
				News dbNews = objNewsService.getBySql("select * from News where title like '%" + strTitle + "%'");
				logger.info("标题：" + strTitle);;
				// 设置消息标题
				objNews.setStrTitle(strTitle);
//				objNews.setStaticFlag(1);// 设置为需要静态化。
				escs.get(0).select("script").remove();
//				escs.get(0).select("img").remove();
				escs.get(0).select("a[href]").remove();
				escs.get(0).select("div[style=float:right; width:300px; height:250px;]").remove();
				escs.get(0).select("[class=article_pages]").remove();
//				escs.get(0).select("br").remove();
				Elements brE =escs.get(0).select("br");
				if (brE.size() >= 2){
					escs.get(0).select("br").last().remove();
					escs.get(0).select("br").last().remove();
				}
				escs.get(0).select("font[color=#FF00FF]").remove();
				logger.info("文章html链接：" + as.get("href") + "--" + pageNo);
				objNews.setStrContent("<font size='3'>" + escs + "</font>");
				//  设置消息内容
				objNews.setAddressFrom("育婴网（www.2k28.com）");
				objNews.setObjProject(objProject);
				objNews.setObjProjectMenu(objProjectMenu);
				objNews.setObjUsers(this.objUsers);
				objNews.setStrSendDate(ec.get(i).child(0).text());
				
				if ("".equals((escs + "")) && HtmlRegexpUtil.filterHtml(escs + "").trim().length() < 98)
				{
					objNews.setStrSummary(HtmlRegexpUtil.filterHtml(escs + "").trim());
				}
				else
				{
					objNews.setStrSummary(HtmlRegexpUtil.filterHtml(escs + "").trim().substring(0,97));
				}
				
				if (escs.get(0).select("img")!=null && escs.get(0).select("img").size() > 0)
				{
					if (dbNews != null && dbNews.getlId() > 0)
					{
						dbNews.setStrContent(objNews.getStrContent());
						// 此文章已经存在，进行下一个文章处理
						logger.info("文章已经存在且存在图片，更新处理：");
						NetImageDisposal.startDownLoad(dbNews, G_2K28);
						objNewsService.update(dbNews);
						logger.info("文章更新完成，继续处理：" + strTitle);
						staticDealWith(dbNews);
						continue;
					}
					// 如果存在图片则判断是否需要进行下载图片操作。
					NetImageDisposal.startDownLoad(objNews, G_2K28);
				}
				
				if (dbNews != null && dbNews.getlId() > 0)
				{
					logger.info("文章已经存在，继续处理：" + strTitle);
				}
				else
				{
					objNewsService.save(objNews);
					staticDealWith(objNews);
				}
			} catch (Exception e) {
				logger.error("error page num is ", pageNo);
			}finally{
				
			}
			Thread.sleep(10000);
		}
	}
    
    /**
     * 静态化处理
     * @param objNews
     * @throws Exception
     */
    private void staticDealWith(News objNews) throws Exception{
    	logger.info("文章静态化处理开始");
//    	String preUrl = "http://localhost:8080";//Config.objSAASConfig.getProperty("app.url.pre");
//		// PC端 + 移动端
//		String strUrl = preUrl + "/front/yuyingshi/detailStatic.do?newsID=" + objNews.getlId();
//		Jsoup.connect(strUrl).timeout(60000).get();
//		Thread.sleep(2000);
    	YuYingShiManageAtion objYuYingShiManageAtion = new YuYingShiManageAtion();
    	objYuYingShiManageAtion.objNewsService=this.objNewsService;
    	objYuYingShiManageAtion.objProjectMenuService=this.objProjectMenuService;
    	objYuYingShiManageAtion.setNewsID(objNews.getlId());
    	objYuYingShiManageAtion.detailStatic();
		logger.info("文章静态化处理结束");
    }
    
//	private void dealOthersPage(int pageNo) throws IOException
//	{
//		Document doc;
//		doc = Jsoup.connect("http://www.chinatat.com/yuyingshi/424/page"+pageNo+".shtm").get();
//		Elements es = doc.getElementsByClass("listcont");
//		Elements objLis = es.get(0).getElementsByTag("ul");
//		Elements ec = objLis.get(0).getElementsByTag("a");
//		
//		for (int i = 0; i < ec.size(); i++){
//			objNews = new News();
//			Attributes as = ec.get(i).attributes();
//			
//			doc = Jsoup.connect(as.get("href")).get();
//			Elements esc = doc.getElementsByClass("index-content");
//			
//			String strTitle = esc.get(0).getElementsByTag("h1").get(0).text();
//			
//			// 设置消息标题
//			objNews.setStrTitle(strTitle);
//			Element cur = esc.get(0).getElementById("fontzoom");
//			
//			Elements table = cur.getElementsByTag("table");
//			if (table != null && table.size() == 1)
//			{
//				//  设置消息内容
//				objNews.setStrContent("<font size='4'>"+ table + "</font>");
//			}
//			else
//			{
//				//  设置消息内容
//				Elements p = cur.getElementsByTag("p");
//				objNews.setStrContent("<font size='4'>"+ p + "</font>");
//			}
//			
//			objNews.setObjProject(objProject);
//			objNews.setObjProjectMenu(objProjectMenu);
//			objNews.setObjUsers(this.objUsers);
//			objNews.setStrSendDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			
//			objNewsService.save(objNews);
//		}
//	}
	
//	private void dealFirstPage() throws IOException
//	{
//		Document doc;
//		doc = Jsoup.connect("http://www.chinatat.com/yuyingshi/424/page1.shtm").get();
//		
//		Elements es = doc.getElementsByTag("divnewslist");
//		
//		Elements ec = es.get(0).getElementsByTag("a");
//		for (int i = 0; i < ec.size(); i++){
//			objNews = new News();
//			Attributes as = ec.get(i).attributes();
//			
//			doc = Jsoup.connect(as.get("href")).get();
//			Elements esc = doc.getElementsByClass("index-content");
//			
//			String strTitle = esc.get(0).getElementsByTag("h1").get(0).text();
//			
//			// 设置消息标题
//			objNews.setStrTitle(strTitle);
//			Element cur = esc.get(0).getElementById("fontzoom");
//			
//			//  设置消息内容
//			Elements p = cur.getElementsByTag("p");
//			objNews.setStrContent("<font size='4'>"+ p + "</font>");
//			
//			objNews.setObjProject(objProject);
//			objNews.setObjProjectMenu(objProjectMenu);
//			objNews.setObjUsers(this.objUsers);
//			objNews.setStrSendDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			
//			objNewsService.save(objNews);
//		}
//	}
	
	public void loadNewsList() throws IOException {
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");
		Pagination objPagination = null;
		JsonConfig objJsonConfig = new JsonConfig();
		SimpleExpression objConditionOne = null;
		
		try {
			if (this.lProjectMenuID == null) {
				setErrorText("无法获取项目菜单id！");
				toWeb(null);
				return;
			}
			if (this.lProjectMenuID.longValue() > 0L)
			{
				objConditionOne = Restrictions.eq("objProjectMenu.lId", this.lProjectMenuID);
			}
			objPagination = new Pagination();
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			this.objNewsService.listByCriteria(objPagination, new SearchCondition(objConditionOne), Order.desc("lId"));
			objJsonConfig.setExcludes(new String[] { "objUsers","objProject","objProjectMenu","lstNewsTag","lstAttachs" });
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
			toWeb(JSONObject.fromObject(new Pagination("系统异常！")).toString());
			return;
		}
	}
	
	/**
	 * 新增新闻消息。
	 * 
	 * @throws IOException
	 */
	public void newsAdd() throws IOException {
		if (!commonValidateUsers()) {
			toWeb("Session 失效！请重新登录");
			return;
		}
		if (this.objNews == null) {
			toWeb("没有获取您要新增的内容！");
			return;
		}

		this.objNews.setObjUsers(this.objUsers);
		this.objNews.setStrSendDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		toWeb(this.objNewsService.newsAdd(this.objNews));
	}

	public void newsDel() throws IOException {
		String[] arrayNewsID = getRequest().getParameterValues("newsIDs");

		if (!commonValidateUsers()) {
			toWeb("Session 失效！请重新登录");
			return;
		}
		toWeb(this.objNewsService.newsDel(arrayNewsID));
	}

	public String newsDetial() {
		if (!commonValidateUsers()) {
			return "commonError";
		}

		if ((this.newsID == null) || (this.newsID.longValue() <= 0L)) {
			setErrorText("没有获取到要修改的新闻");
			return "commonError";
		}

		try {
			this.objNews = ((News) this.objNewsService.getById(this.newsID));

			if (this.objNews == null) {
				setErrorText("无法获取到新闻信息，请检查新闻参数是否传递正确");
				return "commonError";
			}
		} catch (Exception e) {
			logger.error("出现异常", e);
			setErrorText("出现异常请刷新重试！");
			return "commonError";
		}

		return "newsDetial";
	}

	/**
	 * 新闻阅读。
	 * 
	 * @return
	 */
	public String newsRead() {
		
		if ((this.newsID == null) || (this.newsID.longValue() <= 0L)) {
			setErrorText("没有获取到要修改的新闻");
			return "commonError";
		}

		try {
			this.objNews = ((News) this.objNewsService.getById(this.newsID));
			if (this.objNews == null) {
				setErrorText("无法获取到新闻信息，请检查新闻参数是否传递正确");
				return "commonError";
			}
		} catch (Exception e) {
			logger.error("出现异常", e);
			setErrorText("出现异常请刷新重试！");
			return "commonError";
		}
		
//		if (!StringUtils.isEmpty(this.projectKey))
//		{
//			return "projectNews";
//		}
		return "newsRead";
	}

	public void newsModify() throws IOException {
		if (!commonValidateUsers()) {
			toWeb("Session 失效！请重新登录！");
			return;
		}
		
		toWeb(this.objNewsService.newsModify(this.objNews));
	}

	private boolean commonValidateUsers() {
		if (StringUtils.isBlank(getUserIdFromSession())) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		try {
			Long.parseLong(getUserIdFromSession());
		} catch (Exception e) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		this.objUsers = ((Users) this.objUsersService.getById(Long.valueOf(Long.parseLong(getUserIdFromSession()))));
		if ((this.objUsers == null) || (this.objUsers.getlId().longValue() <= 0L)) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		return true;
	}
	
	/**
	 * 查询。
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void loadSearchList() throws IOException
	{
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");
		Pagination<News> objPagination = new Pagination<News>();
		JsonConfig objJsonConfig = new JsonConfig();
		SimpleExpression objConditionOne = null;
		String strErrorMsg = "";
		Criterion objSQLCondition = null;
		
		try {
			// 根据项目标志获取项目信息
			if (this.tempNewsID != null && this.tempNewsID.length() > 0L)
			{
				objSQLCondition = Restrictions.sqlRestriction("id=" + this.tempNewsID);
			}
			
			if (this.tempNewsName != null && this.tempNewsName.length() > 0L)
			{
				objConditionOne = Restrictions.like("strContent", this.tempNewsName, MatchMode.ANYWHERE);
			}
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}
			
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			this.objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition,objConditionOne), Order.desc("lId"));
			
			objJsonConfig.setExcludes(new String[] { "objUsers","objProject","objProjectMenu","lstNewsTag","lstAttachs","lstComments" });
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
//			if (objPagination.getRows().size() > 0)
//			{
//				objJsonConfig.setExcludes(new String[] { "objUsers","objProject","objProjectMenu","lstNewsTag" });
//				toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
//			}
//			else
//			{
//				objPagination = new Pagination<News>("");
//			}
			
		} catch (Exception e) {
			logger.info("系统异常", e);
			toWeb(JSONObject.fromObject(new Pagination<News>("系统异常！")).toString());
		}
	}

	/**
	 * 查询。
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void loadSearch() throws IOException
	{
		String nPageSize = getRequest().getParameter("rows");
		String nCurrentPage = getRequest().getParameter("page");
		Pagination objPagination = new Pagination();
		JsonConfig objJsonConfig = new JsonConfig();
		SimpleExpression objConditionOne = null;
		String strErrorMsg = "";
		Criterion objSQLCondition;
		
		try {
			if (lProjectID != null && lProjectID <= 0)
			{
				strErrorMsg = "无法获取您的项目信息，请检查后重试！";
				toWeb(JSONObject.fromObject(new Pagination("无法获取您的项目信息，请检查后重试！")).toString());
				return;
			}
			// 根据项目标志获取项目信息
			objProject = this.objProjectService.getByHql(" from Project where lId='" + this.lProjectID + "'");
			if (objProject == null) {
				strErrorMsg = "无法获取您的项目信息，请检查后重试！";
				toWeb(JSONObject.fromObject(new Pagination("无法获取您的项目信息，请检查后重试！")).toString());
				return;
			}
			objSQLCondition = Restrictions.sqlRestriction("pid='" + objProject.getlId() + "'");
			if (this.searchParam != null && this.searchParam.length() > 0L)
			{
				objConditionOne = Restrictions.like("strContent", this.searchParam, MatchMode.ANYWHERE);
			}
			if ((nPageSize == null) || (nPageSize.length() <= 0)) {
				nPageSize = "20";
			}
			if ((nCurrentPage == null) || (nCurrentPage.length() <= 0)) {
				nCurrentPage = "1";
			}
			objPagination.setErrorMsg(strErrorMsg);
			objPagination.setPageNo(Integer.parseInt(nCurrentPage));
			objPagination.setPageSize(Integer.parseInt(nPageSize));
			this.objNewsService.listByCriteria(objPagination, new SearchCondition(objSQLCondition,objConditionOne), Order.desc("lId"));
			objJsonConfig.setExcludes(new String[] { "objUsers","objProject","objProjectMenu","lstNewsTag" });
			toWeb(JSONObject.fromObject(objPagination, objJsonConfig).toString());
		} catch (Exception e) {
			logger.info("系统异常", e);
			toWeb(JSONObject.fromObject(new Pagination("系统异常！")).toString());
		}
	}
	
	public News getObjNews() {
		return this.objNews;
	}

	public void setObjNews(News objNews) {
		this.objNews = objNews;
	}

	public Users getObjUsers() {
		return this.objUsers;
	}

	public void setObjUsers(Users objUsers) {
		this.objUsers = objUsers;
	}

	public Long getNewsID() {
		return this.newsID;
	}

	public void setNewsID(Long newsID) {
		this.newsID = newsID;
	}

	public Long getLProjectID() {
		return lProjectID;
	}

	public void setLProjectID(Long projectID) {
		lProjectID = projectID;
	}

	public Long getLProjectMenuID() {
		return lProjectMenuID;
	}

	public void setLProjectMenuID(Long projectMenuID) {
		lProjectMenuID = projectMenuID;
	}
	
	public Long getlProjectMenuID() {
		return lProjectMenuID;
	}

	public void setlProjectMenuID(Long projectMenuID) {
		lProjectMenuID = projectMenuID;
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

	public Project getObjProject() {
		return objProject;
	}

	public void setObjProject(Project objProject) {
		this.objProject = objProject;
	}

	public List<ProjectMenu> getLstProjectMenu() {
		return lstProjectMenu;
	}

	public void setLstProjectMenu(List<ProjectMenu> lstProjectMenu) {
		this.lstProjectMenu = lstProjectMenu;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}

	public String getTempNewsName() {
		return tempNewsName;
	}

	public void setTempNewsName(String tempNewsName) {
		this.tempNewsName = tempNewsName;
	}

	public String getTempNewsID() {
		return tempNewsID;
	}

	public void setTempNewsID(String tempNewsID) {
		this.tempNewsID = tempNewsID;
	}
	
}