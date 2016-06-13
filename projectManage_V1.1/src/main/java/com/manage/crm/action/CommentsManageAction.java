package com.manage.crm.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javacommon.core.base.BaseStruts2Action;
import javacommon.util.StringUtils;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.manage.crm.entity.Comments;
import com.manage.crm.entity.Customer;
import com.manage.crm.entity.News;
import com.manage.crm.service.CommentsService;
import com.manage.crm.service.CustomerService;
import com.manage.crm.service.NewsService;

@Controller("commentsManageAction")
@Scope("prototype")
public class CommentsManageAction extends BaseStruts2Action {
	private static final long serialVersionUID = 3842259510429485253L;
	private static final Logger logger = LoggerFactory.getLogger(CommentsManageAction.class);

	@Resource(name = "commentsService")
	private CommentsService objCommentsService;
	
	@Resource(name = "customerService")
	private CustomerService objCustomerService;
	
	@Resource(name = "newsService")
	private NewsService objNewsService;
	
	private String context; // 评论内容。
	
	private Customer objCustomer;
	
	private News objNews;
	
	private Long lNewsID;
	
	
	/**
	 * 发表评论。
	 * @throws IOException 
	 * 
	 */
	public void comments() throws IOException
	{
		Comments objComments = new Comments();
		String strSaveStatus = "0";
		
		try {
			if (!commonValidateCustomer()) {
//				strSaveStatus = "2";
				
				objCustomer = new Customer();
				objCustomer.setlId(-1L);
			}
			// 验证news有效性。
			if (lNewsID == null && lNewsID < 0)
			{
				strSaveStatus = "3";
			}
			else
			{
				this.objNews = objNewsService.getById(lNewsID);
				
				if (context != null && context.length() > 0)
				{
					objComments.setContent(context);
					objComments.setObjCustomer(objCustomer);
					objComments.setObjNews(objNews);
					objComments.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					if (this.objCommentsService.save(objComments))
					{
						strSaveStatus = "1";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		toWeb(strSaveStatus);
	}
	
	/**
	 * 展示评论。
	 * @throws IOException 
	 * 
	 */
	public void showComments() throws IOException
	{
		String strNews = "0";
		
		try {
			// 验证news有效性。
			if (lNewsID < 0)
			{
				strNews = "2";
			}
			else
			{
				JsonConfig objJsonConfig = new JsonConfig();
				List<Comments> lstComments = new ArrayList<Comments>();
				
				lstComments = objCommentsService.listByHql(" from Comments where objNews.lId=" + lNewsID + " order by createtime desc ");
				objJsonConfig.setExcludes(new String[] { "objNews","objCustomer" });
				strNews = JSONArray.fromObject(lstComments, objJsonConfig).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		toWeb(strNews);
	}

	/**
	 * 验证用户有效性。
	 * 
	 * @return
	 */
	private boolean commonValidateCustomer() {
		if (StringUtils.isBlank(getCustomerIdFromSession())) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		try {
			Long.parseLong(getCustomerIdFromSession());
		} catch (Exception e) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		this.objCustomer = ((Customer) this.objCustomerService.getById(Long.valueOf(Long.parseLong(getCustomerIdFromSession()))));
		if ((this.objCustomer == null) || (this.objCustomer.getlId().longValue() <= 0L)) {
			setErrorText("Session 已失效，请重新登录 !");
			return false;
		}

		return true;
	}
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Customer getObjCustomer() {
		return objCustomer;
	}

	public void setObjCustomer(Customer objCustomer) {
		this.objCustomer = objCustomer;
	}

	public News getObjNews() {
		return objNews;
	}

	public void setObjNews(News objNews) {
		this.objNews = objNews;
	}

	public Long getlNewsID() {
		return lNewsID;
	}

	public void setNewsID(Long lNewsID) {
		this.lNewsID = lNewsID;
	}
	
	public Long getLNewsID() {
		return lNewsID;
	}

	public void setLNewsID(Long lNewsID) {
		this.lNewsID = lNewsID;
	}

	
}