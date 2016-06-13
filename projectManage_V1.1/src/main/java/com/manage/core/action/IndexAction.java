package com.manage.core.action;

import javacommon.core.base.BaseStruts2Action;
import javacommon.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends BaseStruts2Action
{
  private static final long serialVersionUID = -4056575674925675338L;
  private static final Logger logger = LoggerFactory.getLogger(IndexAction.class);
  private static final String FORWARD_URL = "forwardURL";

  public String forward()
  {
    String strForwardURL = getRequest().getParameter("forwardURL");

    logger.info("strForwardURL:---" + strForwardURL);

    if ((!StringUtils.isBlank(strForwardURL)) && (strForwardURL.trim().equalsIgnoreCase("login")))
    {
      getRequest().getSession().invalidate();
    }

    if (!StringUtils.isBlank(strForwardURL))
    {
      return strForwardURL.trim();
    }

    setErrorText("地址没找到~");
    return "commonError";
  }
}