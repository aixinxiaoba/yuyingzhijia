package javacommon.core.quartz;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javacommon.util.freemarker.FreemarkerUtils;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.ShortUrl;
import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONObject;

import com.manage.crm.entity.Customer;
import com.manage.crm.entity.News;
import com.manage.crm.entity.ProjectMenu;
import com.manage.crm.service.AttachsService;
import com.manage.crm.service.CustomerService;
import com.manage.crm.service.NewsService;
import com.manage.crm.service.NewsTagService;
import com.manage.crm.service.ProjectMenuService;
import com.manage.crm.service.ProjectService;
import com.manage.crm.service.VisitLogService;

/**
 * 新浪微博定时发送。
 * 
 * @author wei
 *
 */
public class SinaWeiBoSend {
	/**
	 * 日志对象
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(FreemarkerUtils.class);

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
	
	@Resource(name = "customerService")
	private CustomerService objCustomerService;

	public void execute() {
		try {
			logger.info("==========================【育婴之家网】微博定时发送开始=========================");
			send("2895295707"); // 育婴之家网
			logger.info("==========================【育婴之家网】微博定时发送结束=========================");
			Thread.sleep(2*60000L); // 十分钟后触发
			logger.info("==========================【育婴知识分享网】微博定时发送开始=========================");
			send("5601769804"); // 育婴知识分享网
			logger.info("==========================【育婴知识分享网】微博定时发送结束=========================");
			Thread.sleep(2*60000L); // 十分钟后触发
			logger.info("==========================【知识分享网】微博定时发送开始=========================");
			send("3216094902"); // 知识分享网
			logger.info("==========================【知识分享网】微博定时发送结束=========================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 微博定时发送。
	 */
	private void send(String strUID) {
		News objNews = this.objNewsService.getBySql("select * from News where sendStatus=0 and mid not in(34,2) and imageurl is not null and imageurl !='' ");
		// 获取token
		Customer objCustomer = objCustomerService.getBySql("select * from customer s where s.uid='" + strUID + "'");
		String access_token = objCustomer.getAccessToken();
		Timeline tm = null;
		String statuses = null;
		Status status = null;
		String strShortURL = null;
		
		try {
			// 如果有图信息为空则取无图信息发送。
			if (objNews == null)
			{
				objNews = this.objNewsService.getBySql("select * from News where sendStatus=0 and mid not in(34,2) and imageurl is not null ");
			}
			// 生成短链接。
			ShortUrl su = new ShortUrl(access_token);
			String url = "http://yuyingzhijia.cn/front/yuyingshi/detail.do?newsID=" + objNews.getlId();
			JSONObject jo = su.longToShortUrl(url);
			strShortURL = ((JSONObject) (((JSONArray) jo.opt("urls")).get(0))).get("url_short").toString();
			statuses = objNews.getStrSummary();
			statuses = replaceBlank(statuses);
			statuses = statuses.replace("&nbsp;", "");
			statuses = statuses.replace("&nbsp", "");
			statuses = statuses.replace("&nbs", "");
			statuses = statuses.replace("&nb", "");
			statuses = statuses.replace("&n", "");
			objNews.setStrSummary(statuses);
			
			ProjectMenu objProjectMenu = this.objProjectMenuService.getBySql("select * from projectmenu where id=(select mid from news where id="+objNews.getlId()+") ");
			statuses = "【" + objNews.getStrTitle() + "】"+ statuses;
			// 增加话题
			statuses = "【育婴知识分享-" + objProjectMenu.getStrMenuName() + "】" + statuses;
//			if (statuses.length() > 110)
//			{
//				statuses = statuses.substring(0, 110);
//			}
			statuses = statuses + "..." + strShortURL;
			tm = new Timeline(access_token);
			
			//  如果微博中存在图片则上传相应图片。
			if (objNews.getImageUrl() != null && !"".equals(objNews.getImageUrl().trim()))
			{
				// 去除url中upload。
//				objNews.setImageUrl(objNews.getImageUrl().replaceFirst("/upload", ""));
				byte[] content = readFileImage("D:/work/application/yuyingzhijia/" + objNews.getImageUrl());
				ImageItem pic = new ImageItem("pic", content);
				String s = java.net.URLEncoder.encode(statuses, "utf-8");
				status = tm.uploadStatus(s, pic);
			}
			else
			{
				// 普通微博发送
				status = tm.updateStatus(statuses);
			}
			
			logger.info("=======================微博发送状态：" + status.toString());
			// 更新文章发布状态。
			objNews.setSendStatus(1);
			objNews.setShortURL(strShortURL);
			this.objNewsService.update(objNews);
		} catch (Exception e) {
			try {
				logger.error("=======================微博发送异常，异常信息：" + e.getMessage());
				status = tm.updateStatus(statuses);
				// 更新新闻。  虽然发送失败依然将发送状态设置为已发送
				objNews.setSendStatus(1);
				objNews.setShortURL(strShortURL);
				this.objNewsService.update(objNews);
//				e.printStackTrace();
			} catch (WeiboException e1) {
				e1.printStackTrace();
			}
		} finally {
			
		}
	}
	
	/**
	 * replace 空格。
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|t|r|n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	/**
	 * photo 读取。
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFileImage(String filename) throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(filename));
		int len = bufferedInputStream.available();
		byte[] bytes = new byte[len];
		int r = bufferedInputStream.read(bytes);
		if (len != r) {
			bytes = null;
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();
		return bytes;
	}
}
