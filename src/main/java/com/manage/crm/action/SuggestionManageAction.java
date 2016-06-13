package com.manage.crm.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javacommon.core.base.BaseStruts2Action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.manage.crm.entity.Suggestion;
import com.manage.crm.service.SuggestionService;

@Controller("suggestionManageAction")
@Scope("prototype")
public class SuggestionManageAction extends BaseStruts2Action {
	private static final long serialVersionUID = 3842259510429485253L;
	private static final Logger logger = LoggerFactory.getLogger(SuggestionManageAction.class);

	@Resource(name = "suggestionService")
	private SuggestionService objSuggestionService;
	
	private Suggestion objSuggestion;
	
	private String textContent;
	
	private String emailAddress;

	/**
	 * 提供意见与建议。
	 * 
	 */
	public void putSuggestion()
	{
		if (textContent != null && textContent != null && textContent.length() > 0)
		{
			try {
				objSuggestion = new Suggestion();
				objSuggestion.setIp(getRequest().getRemoteHost());
				objSuggestion.setCreateDate(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(new Date()));
				objSuggestion.setContent(textContent);
				objSuggestion.setConnectWay(emailAddress);
				objSuggestionService.save(objSuggestion);
				logger.info("保存意见成功！");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("保存意见失败" + e.getMessage());
			}
		}
	}

	public Suggestion getObjSuggestion() {
		return objSuggestion;
	}

	public void setObjSuggestion(Suggestion objSuggestion) {
		this.objSuggestion = objSuggestion;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
	
}