package org.apache.jsp.jsp.templateOne.front.yuyingshi;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class detail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(4);
    _jspx_dependants.add("/jsp/templateOne/front/yuyingshi/common/head.jsp");
    _jspx_dependants.add("/jsp/templateOne/front/yuyingshi/common/nav.jsp");
    _jspx_dependants.add("/jsp/templateOne/front/yuyingshi/common/content-detail.jsp");
    _jspx_dependants.add("/jsp/templateOne/front/yuyingshi/common/foot.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_iterator_value_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_iterator_value_status_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_property_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_else;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_s_iterator_value_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_iterator_value_status_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_property_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_else = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_s_iterator_value_id.release();
    _jspx_tagPool_s_iterator_value_status_id.release();
    _jspx_tagPool_s_if_test.release();
    _jspx_tagPool_s_property_value_nobody.release();
    _jspx_tagPool_s_else.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<HTML xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\t<HEAD>\r\n");
      out.write("\t\t<TITLE>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.strTitle}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write('-');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.strMenuName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("-育婴知识学习交流平台</TITLE>\r\n");
      out.write("\t\t<META content=\"IE=11.0000\" http-equiv=\"X-UA-Compatible\">\r\n");
      out.write("\t\t<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("\t\t<META http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\r\n");
      out.write("\t\t<META http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t\t<META http-equiv=\"Cache-Control\" content=\"no-cache, must-revalidate\">\r\n");
      out.write("\t\t<META http-equiv=\"expires\" content=\"0\">\r\n");
      out.write("\t\t<META name=\"render\" content=\"webkit\">\r\n");
      out.write("\t\t<META name=\"Keywords\" content=\"育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿\">\r\n");
      out.write("\t\t<META name=\"description\" content=\"育婴之家是一个分享交流育儿知识经验的平台，我们致力于提供最好的育儿知识，通过我们这个平台将知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱\">\r\n");
      out.write("\t\t<link rel=\"shortcut icon\" href=\"/favicon.ico?version=3\">\r\n");
      out.write("\t\t<LINK href=\"/commons/yuyingshi/css/style.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\t\t<LINK href=\"/commons/yuyingshi/css/content.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"/commons/js/jquery/jquery-1.7.2.min.js\" ></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"/commons/js/0_core.js\" ></script>\r\n");
      out.write("\t\t<META name=\"GENERATOR\" content=\"MSHTML 11.00.9600.17690\">\r\n");
      out.write("\t<script type=\"text/javascript\"> objCore.browserRedirect(\"p\",\"/static/m_news/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"); </script>\r\n");
      out.write("\t</HEAD>\r\n");
      out.write("\t<BODY>\r\n");
      out.write("\t\t<!-- 头部设置 -->\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("$(function(){\r\n");
      out.write("\t// load login user.\r\n");
      out.write("\tloadCustomer();\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function loadCustomer()\r\n");
      out.write("{\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl : \"/login/loadCustomer.do\",\r\n");
      out.write("\t\ttype : \"post\",\r\n");
      out.write("\t\tdataType : \"json\",\r\n");
      out.write("\t\tasync : true,\r\n");
      out.write("\t\tsuccess:function(data){\r\n");
      out.write("\t\t\tif (data != null && data.lId != null && data.lId > 0)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t$(\"#needLogin\").hide();\r\n");
      out.write("\t\t\t\t$(\"#allreadyLogin\").show();\r\n");
      out.write("\t\t\t\t$(\"#nickName\").html(data.strNickName);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t$(\"#needLogin\").show();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("<DIV class=\"navi-head\" id=\"navi-head\" name=\"navi-head\">\r\n");
      out.write("\t<DIV class=\"navi-con-block\">\r\n");
      out.write("\t\t<DIV class=\"navi-left\">\r\n");
      out.write("\t\t\t<A class=\"page-title-pic\" id=\"page-title\"\r\n");
      out.write("\t\t\t\thref=\"/\"></A>\r\n");
      out.write("\t\t\t<A class=\"first-page\" id=\"first-self\" href=\"/\">首页</A>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!-- \r\n");
      out.write("\t\t\t<DIV class=\"out-block clearfix\">\r\n");
      out.write("\t\t\t\t<DIV class=\"discovery nav-item\" id=\"discovery-self\" href=\"#\">\r\n");
      out.write("\t\t\t\t\t<LABEL>发现</LABEL>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<DIV class=\"navi-pic navi-pic-on4\"></DIV>\r\n");
      out.write("\t\t\t\t\t<SPAN class=\"discovery-block\" id=\"discovery\"\r\n");
      out.write("\t\t\t\t\t\tstyle=\"display: none;\"><I class=\"underline\"></I>\r\n");
      out.write("\t\t\t\t\t\t<UL>\r\n");
      out.write("\t\t\t\t\t\t\t<LI class=\"block-con clearfix\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<DIV class=\"line-title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A href=\"http://info.ci123.com/brand/list/index.php\">用品</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\t\t\t\t<DIV class=\"line-con\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A class=\"black\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\thref=\"http://info.ci123.com/brand/list/index.php\">用品库</A> <A\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tclass=\"black\" href=\"http://shiyong.ci123.com/\">免费试用</A> <A\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tclass=\"black\" href=\"http://info.ci123.com/pingce/\">用品评测</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t\t\t<LI class=\"block-con clearfix\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<DIV class=\"line-title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A href=\"http://rs.ci123.com/\">资源</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\t\t\t\t<DIV class=\"line-con\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A class=\"black\" href=\"http://rs.ci123.com/categories/show/7\">胎教音乐</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A class=\"black\" href=\"http://rs.ci123.com/video/section.html\">孕育视频</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A class=\"black\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\thref=\"http://rs.ci123.com/software/section.html\">启蒙教育</A> <A\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tclass=\"black\" href=\"http://rs.ci123.com/widget/section.html\">育儿工具</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A class=\"black\" href=\"http://rs.ci123.com/apple/section.html\">手机应用</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t\t\t<LI class=\"block-con clearfix\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<DIV class=\"line-title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A href=\"http://good.ci123.com/tudou/happy.php\">其他</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\t\t\t\t<DIV class=\"line-con\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A class=\"black\" href=\"http://good.ci123.com/tudou/happy.php\">活动中心</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A class=\"black\" href=\"http://good.ci123.com/tudou/\">土豆频道</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<A class=\"black\" href=\"http://shiyong.ci123.com/leyun/\">乐孕大礼包</A>\r\n");
      out.write("\t\t\t\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t\t</UL></SPAN>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t -->\r\n");
      out.write("\t\t\t<!-- 暂不使用其他菜单 -->\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div class=\"navi-right\">\r\n");
      out.write("\t\t\t<div id=\"needLogin\" style=\"margin-top:4px;display:none;\">\r\n");
      out.write("\t\t\t\t<a href=\"https://api.weibo.com/oauth2/authorize?client_id=4148667325&redirect_uri=http://www.yuyingzhijia.cn/sinaLogin.do&response_type=code\" class=\"weibo-sina link-icon-style\" title=\"使用新浪微博登陆\"></a>\r\n");
      out.write("\t\t\t\t<!-- \r\n");
      out.write("\t\t\t\t<a href=\"http://user.ci123.com/qq/zone.php?channel=1&back_url=http://ask.ci123.com\" class=\"qq link-icon-style\" title=\"使用QQ登陆\"></a>\r\n");
      out.write("\t\t\t\t -->\r\n");
      out.write("\t\t\t\t<a href=\"/front/customer/register.do?projectKey=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.projectKey }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"head-register re-lo-style black\">注册</a><a href=\"/jsp/templateOne/login.jsp\" target=\"_self\" class=\"head-login re-lo-style black\">登录</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"allreadyLogin\" style=\"display:none;margin-top:13px;\">\r\n");
      out.write("\t\t\t\t <span style=\"float:left;\">欢迎您，</span>\r\n");
      out.write("\t\t\t\t <a href=\"\" id=\"nickName\" style=\"margin-left:0px;\"></a>\r\n");
      out.write("                 <a href=\"/login/destroyCustomerLogin.do\">退出</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</DIV>\r\n");
      out.write("</DIV>\r\n");
      out.write("<SCRIPT>\r\n");
      out.write("    $(\".out-block\").hover(function(){$(this).addClass(\"hover-div\")},function(){$(this).removeClass(\"hover-div\")});$(\".nav-item\").hover(function(){$(this).addClass(\"hover\");$(this).find(\"span\").show()},function(){$(this).removeClass(\"hover\");$(this).find(\"span\").hide()});$(\".user-head\").hover(function(){$(this).addClass(\"hover-r\");$(this).find(\"span\").show()},function(){$(this).removeClass(\"hover-r\");$(this).find(\"span\").hide()});$(\".user-item\").hover(function(){$(this).addClass(\"hover-u\");$(this).find(\"span\").show()},function(){$(this).removeClass(\"hover-u\");$(this).find(\"span\").hide()});$(\".hold_more\").hover(function(){$(\".main_more\").addClass(\"hover_more\");$(\".main_more\").find(\"i\").addClass(\"hover_more\");$(\".more_block\").show()},function(){$(\".main_more\").removeClass(\"hover_more\");$(\".main_more\").find(\"i\").removeClass(\"hover_more\");$(\".more_block\").hide()});$(\".mail-remind\").hover(function(){$(this).addClass(\"mail-hover\");$(\".mail-box\").show()},function(){$(this).removeClass(\"mail-hover\");$(\".mail-box\").hide()});\r\n");
      out.write("</SCRIPT>");
      out.write("\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!--gloabl_nav start-->\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- nav 样式 -->\r\n");
      out.write("<LINK href=\"/commons/yuyingshi/css/index.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("<DIV class=\"header lanren\">\r\n");
      out.write("\t <DIV class=\"category_search clearfix\">\r\n");
      out.write("\t\t<DIV class=\"l clearfix\" style=\"width:auto;height:auto;margin-right:70px;\">\r\n");
      out.write("\t\t\t<A\r\n");
      out.write("\t\t\tstyle='BACKGROUND-IMAGE: url(/commons/yuyingshi/images/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.menuKey}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(".png)'\r\n");
      out.write("\t\t\t href=\"/front/yuyingshi/index.do?lProjectMenuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" alt=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.strMenuName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" target=_self></A>\r\n");
      out.write("\t\t\t<SPAN><BR></SPAN>\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t<DIV class=\"r clearfix\">\r\n");
      out.write("\t\t\t<DIV class=z>\r\n");
      out.write("\t\t\t\t<FORM id=search_form onsubmit=\"return OnSubmit()\" method=post\r\n");
      out.write("\t\t\t\t\tname=search action=\"/front/yuyingshi/newsSearch.do?lProjectMenuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" target=_blank>\r\n");
      out.write("\t\t\t\t\t<DIV class=inside>\r\n");
      out.write("\t\t\t\t\t\t<INPUT name=\"searchText\" id=keyword class=input_search placeholder=\"育婴知识搜索\">\r\n");
      out.write("\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\t<SPAN><INPUT class=n type=submit value=\"\">\r\n");
      out.write("\t\t\t\t\t</SPAN>\r\n");
      out.write("\t\t\t\t</FORM>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t<DIV class=y>\r\n");
      out.write("\t\t\t\t<UL >\r\n");
      out.write("\t\t\t\t\t<LI class=c_one>\r\n");
      out.write("\t\t\t\t\t\t<A href=\"/front/yuyingshi/newsList.do?lProjectMenuID=23&menuID=34\" target=\"_blank\">育儿</A>\r\n");
      out.write("\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t<LI class=c_two>\r\n");
      out.write("\t\t\t\t\t\t<A href=\"/front/yuyingshi/newsList.do?lProjectMenuID=29&menuID=36\" target=\"_blank\">宝妈</A>\r\n");
      out.write("\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t<LI  class=c_three>\r\n");
      out.write("\t\t\t\t\t\t<A href=\"/front/yuyingshi/newsList.do?lProjectMenuID=22&menuID=25\" target=\"_blank\">育婴师</A>\r\n");
      out.write("\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<LI  class=c_four>\r\n");
      out.write("\t\t\t\t\t\t<A href=\"/front/yuyingshi/newsList.do?lProjectMenuID=30&menuID=38\" target=\"_blank\">孕前</A>\r\n");
      out.write("\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<LI  class=c_five>\r\n");
      out.write("\t\t\t\t\t\t<A href=/front/yuyingshi/newsList.do?lProjectMenuID=23&menuID=35 target=\"_blank\">胎教</A>\r\n");
      out.write("\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<LI class=c_six>\r\n");
      out.write("\t\t\t\t\t\t<A href=\"/front/yuyingshi/newsList.do?lProjectMenuID=22&menuID=5\" target=\"_blank\">动态</A>\r\n");
      out.write("\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t</UL>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t</DIV>\r\n");
      out.write("\t\r\n");
      out.write("\t<!--[if lte IE 8]><style>#special_nav{margin-top:93px;}</style><![endif]-->\r\n");
      out.write("\t<!-- 导航 start -->\r\n");
      out.write("\t<DIV class=\"index-nav\">\r\n");
      out.write("\t\t<UL class=\"clearfix\" id=\"special_nav\">\r\n");
      out.write("\t\t\t<LI>\r\n");
      out.write("\t\t\t\t<A class=\"small-sub-nav");
      if (_jspx_meth_s_if_0(_jspx_page_context))
        return;
      out.write("\" href='/front/yuyingshi/index.do?lProjectMenuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("'>首页</A>\r\n");
      out.write("\t\t\t</LI>\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_s_iterator_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</UL>\r\n");
      out.write("\t</DIV>\r\n");
      out.write("\t<!-- 导航 end -->\r\n");
      out.write("</DIV>");
      out.write("\r\n");
      out.write("\t\t<!--gloabl_nav end -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!--content start-->\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("$(function(){\r\n");
      out.write("\tloadComments();\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function comments()\r\n");
      out.write("{\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\t\turl : \"/mpf/comments/comments.do\",\r\n");
      out.write("\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\tdata : {\r\n");
      out.write("\t\t\t\t\"context\" : $(\"#context\").val(),\r\n");
      out.write("\t\t\t\t\"lNewsID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tdataType : \"text\",\r\n");
      out.write("\t\t\tasync : true,\r\n");
      out.write("\t\t\tsuccess:function(data)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tif (data == 1)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t$(\"#showMsg\").css(\"color\",\"green\");\r\n");
      out.write("\t\t\t\t\t$(\"#showMsg\").css(\"font-size\",\"14px\");\r\n");
      out.write("\t\t\t\t\t$(\"#showMsg\").text(\"评论成功！\");\r\n");
      out.write("\t\t\t\t\t$(\"#context\").val(\"\"); // 清空评论框内容\r\n");
      out.write("\t\t\t\t\tloadComments();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t$(\"#showMsg\").css(\"color\",\"red\");\r\n");
      out.write("\t\t\t\t\t$(\"#showMsg\").css(\"font-size\",\"14px\");\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tif (data == 2)\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t$(\"#showMsg\").text(\"请登陆后再进行评论！\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\telse\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t$(\"#showMsg\").text(\"评论失败！请稍后重试！\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tsetTimeout(function(){$(\"#showMsg\").text(\"\");}, 2000);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tsetTimeout(function(){$(\"#showMsg\").text(\"\");}, 2000);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("// 网友评论内容。\r\n");
      out.write("function loadComments()\r\n");
      out.write("{\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl : \"/mpf/comments/showComments.do\",\r\n");
      out.write("\t\ttype : \"post\",\r\n");
      out.write("\t\tdata : {\r\n");
      out.write("\t\t\t\"lNewsID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tdataType : \"json\",\r\n");
      out.write("\t\tasync : true,\r\n");
      out.write("\t\tsuccess:function(data)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tvar commentsNum = 0;\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif (data != null && data.length > 0)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tvar strHTML = \"\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tfor (var i = 0; i < data.length; i++)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t// 如果为登陆用户评论 则显示对应的用户昵称。\r\n");
      out.write("\t\t\t\t\tstrHTML += \"<div class='comment-list-content' style='margin-bottom:10px;'>\";\r\n");
      out.write("\t\t\t\t\tstrHTML += \"<h4 class='content-listtitle'>\";\r\n");
      out.write("\t\t\t\t\tstrHTML += \"<span class='list-title'>\" + data[i].strNickName + \"</span> <span class='list-date'>\"+data[i].createTime+\"</span>\";\r\n");
      out.write("\t\t\t\t\tstrHTML += \"</h4>\"\r\n");
      out.write("\t\t\t\t\tstrHTML += \"<p class='list-content'>\"+data[i].content+\"</p>\"\r\n");
      out.write("\t\t\t\t\tstrHTML += \"</div>\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$(\"#commentList\").html(strHTML);\r\n");
      out.write("\t\t\t\tcommentsNum = data.length;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t$(\"#commentsNum\").text(commentsNum);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<DIV class=\"lanren box-top\">\r\n");
      out.write("\t<DIV class=\"pleft\">\r\n");
      out.write("\t\t<DIV class=\"search lanren\">\r\n");
      out.write("\t\t\t<DIV style=\"border:0px solid #f99391;\">\r\n");
      out.write("\t\t\t\t当前位置：\r\n");
      out.write("\t\t\t\t<A href=\"/front/yuyingshi/index.do?lProjectMenuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" rel=\"nofollow\">首页</A>\r\n");
      out.write("\t\t\t\t>&nbsp;\r\n");
      out.write("\t\t\t\t<A href=\"/front/yuyingshi/newsList.do?lProjectMenuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&menuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" rel=\"nofollow\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.strMenuName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</A>\r\n");
      out.write("\t\t\t\t>&nbsp;\r\n");
      out.write("\t\t\t\t正文\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t<DIV class=\"viewbox\">\r\n");
      out.write("\t\t\t<DIV class=\"title\" style=\"padding:0px;\">\r\n");
      out.write("\t\t\t\t<H2>\r\n");
      out.write("\t\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.strTitle}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("\t\t\t\t</H2>\r\n");
      out.write("\t\t\t\t<DIV class=\"f12\" style=\"margin:3px;\">\r\n");
      out.write("\t\t\t\t\t<SPAN class=\"l\">作者：\r\n");
      out.write("\t\t\t\t\t\t<A href=\"/front/yuyingshi/index.do?lProjectMenuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" target=\"_blank\">育婴之家</A>&nbsp;&nbsp;(转载请注明出处，并添加链接地址)</SPAN>\r\n");
      out.write("\t\t\t\t\t\t<SPAN class=\"r\">发布日期：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.strSendDate}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</SPAN>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"r\">点击次数：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.readNum + 1}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&nbsp;</span>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t<!-- /title -->\r\n");
      out.write("\t\t\t<DIV class=\"content1\">\r\n");
      out.write("\t\t\t\t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.strContent}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<!-- 评论列表 -->\r\n");
      out.write("\t\t\t\t<div style=\"margin-top:10px;margin-bottom: 20px;\">评论列表：</div>\r\n");
      out.write("\t\t\t\t<div id=\"commentList\">\r\n");
      out.write("\t\t\t\t\t \r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- 评论列表 -->\r\n");
      out.write("\t\t\t<!-- \r\n");
      out.write("\t\t\t<div class=\"option\" id=\"row6998404\">\r\n");
      out.write("\t\t\t\t\t<a class=\"reply\" href=\"javascript:void(0)\" rel=\"\">回复</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t -->\r\n");
      out.write("\t\t\t<!-- 评论栏 start \r\n");
      out.write("\t\t\t\t-->\r\n");
      out.write("\t\t\t\t<div style=\"margin-top: 10px;\" class=\"comments\">\r\n");
      out.write("\t\t\t\t\t<FORM name=\"saypl\" id=\"saypl\" onsubmit=\"\" action=\"\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t<li id=\"showMsg\" style=\"height:20px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</li>\t\r\n");
      out.write("\t\t\t\t\t\t\t<li style=\"float:left;\">发表评论</li>\t\r\n");
      out.write("\t\t\t\t\t\t\t<li style=\"float:right;\">共有<span color=\"red\" id=\"commentsNum\"></span>条评论</li>\t\r\n");
      out.write("\t\t\t\t\t\t\t<li style=\"margin-top: 10px;float:none;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<TEXTAREA name=\"context\" id=\"context\" rows=\"6\"  style=\"width:99.5%;border: 1px solid rgb(204, 204, 204);\"></TEXTAREA>\r\n");
      out.write("\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li style=\"margin-top: 10px;float:none;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"javascript:comments();\" class=\"comment-txtbottom_a\" style=\"color:#fff;\">发表评论</a>\r\n");
      out.write("\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</FORM>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- 评论栏 end-->\r\n");
      out.write("\t\t\t<DIV class=\"handle\">\r\n");
      out.write("\t\t\t\t<DIV class=\"context\">\r\n");
      out.write("\t\t\t\t\t<UL>\r\n");
      out.write("\t\t\t\t\t\t<LI>\r\n");
      out.write("\t\t\t\t\t\t\t\t上一篇：\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_if_2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_else_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t\t<LI>\r\n");
      out.write("\t\t\t\t\t\t\t下一篇：\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_if_3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t");
      if (_jspx_meth_s_else_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t</UL>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t<!-- /context -->\r\n");
      out.write("\t\t\t\t<!-- \r\n");
      out.write("\t\t\t\t<DIV class=\"actbox\">\r\n");
      out.write("\t\t\t\t\t<UL class=\"downurllist\">\r\n");
      out.write("\t\t\t\t\t\t<LI>\r\n");
      out.write("\t\t\t\t\t\t\t<A href=\"http://d.lanrenzhijia.com/2015/scale0417\"\r\n");
      out.write("\t\t\t\t\t\t\t\ttarget=\"_blank\" rel=\"nofollow\">在线预览</A>\r\n");
      out.write("\t\t\t\t\t\t</LI>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<LI>\r\n");
      out.write("\t\t\t\t\t\t\t<A href=\"http://d.lanrenzhijia.com/2015/scale0417/scale0417.rar\">本地下载</A>\r\n");
      out.write("\t\t\t\t\t\t</LI>\r\n");
      out.write("\t\t\t\t\t</UL>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t -->\r\n");
      out.write("\t\t\t\t<!-- /actbox -->\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t<!-- /handle -->\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t</DIV>\r\n");
      out.write("\t<!-- /pleft -->\r\n");
      out.write("\r\n");
      out.write("\t<DIV class=\"list-js\">\r\n");
      out.write("\t\t<DIV class=\"top\">\r\n");
      out.write("\t\t\t<SPAN class=\"l\">标签墙</SPAN><SPAN class=\"r\"><!-- \r\n");
      out.write("\t\t\t<A\r\n");
      out.write("\t\t\t\thref=\"http://www.lanrenzhijia.com/js/\" target=\"_blank\"><IMG\r\n");
      out.write("\t\t\t\t\t\twidth=\"26\" height=\"26\" alt=\"育婴之家\"\r\n");
      out.write("\t\t\t\t\t\tsrc=\"/commons/yuyingshi/images/more.png\">\r\n");
      out.write("\t\t\t</A>\r\n");
      out.write("\t\t\t --> </SPAN>\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t<!-- 详细页右侧导航begin -->\r\n");
      out.write("\t\t<DIV class=\"nav-sub\">\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_s_iterator_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t<!-- 详细页右侧导航end -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<DIV class=\"ads_right\">\r\n");
      out.write("\t\t\t<DIV class=\"top\">\r\n");
      out.write("\t\t\t\t<SPAN class=\"l\">最新更新</SPAN><SPAN class=\"r\"><A\r\n");
      out.write("\t\t\t\t\thref=\"/front/yuyingshi/newsListByTag.do?lProjectMenuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&newsTag=1\"><IMG\r\n");
      out.write("\t\t\t\t\t\t\twidth=\"26\" height=\"26\" alt=\"育婴之家\"\r\n");
      out.write("\t\t\t\t\t\t\tsrc=\"/commons/yuyingshi/images/more.png\">\r\n");
      out.write("\t\t\t\t</A> </SPAN>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t<UL class=\"box\" id=\"all2\">\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_s_iterator_2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t</UL>\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<DIV class=\"hot\">\r\n");
      out.write("\t\t\t<DL>\r\n");
      out.write("\t\t\t\t<DT>\r\n");
      out.write("\t\t\t\t\t推荐阅读\r\n");
      out.write("\t\t\t\t</DT>\r\n");
      out.write("\t\t\t\t");
      if (_jspx_meth_s_iterator_3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t</DL>\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t</DIV>\r\n");
      out.write("</DIV>\r\n");
      out.write("<script type=\"text/javascript\"> \r\n");
      out.write("var zhText;\r\n");
      out.write("if ($(\".content1\").text().length > 512)\r\n");
      out.write("{\r\n");
      out.write("\tzhText = $(\".content1\").text().substr(0,200);//\"测试百度文字转语音接口 - 卡卡测速网 www.webkaka.com\"; \r\n");
      out.write("}\r\n");
      out.write("else\r\n");
      out.write("{\r\n");
      out.write("\tzhText = $(\".content1\").text();//\"测试百度文字转语音接口 - 卡卡测速网 www.webkaka.com\"; \r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("zhText = encodeURI(zhText);\r\n");
      out.write("document.write(\"<audio autoplay=\\\"auhoplay\\\">\");\r\n");
      out.write("document.write(\"<source src=\\\"http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&spd=4&text=\"+ zhText +\"\\\" type=\\\"audio/mpeg\\\">\");\r\n");
      out.write("document.write(\"<embed height=\\\"0\\\" width=\\\"0\\\" src=\\\"http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&spd=2&text=\"+ zhText +\"\\\">\");\r\n");
      out.write("document.write(\"</audio>\");\r\n");
      out.write("</script>\r\n");
      out.write("<script>$.ajax( { url:\"/front/index/setReadNum.do\", type:\"post\",  data:{\"newsID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"}, dataType:\"text\",  async: true });</script>");
      out.write("\r\n");
      out.write("\t\t<!--content end -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!--wrapper-->\r\n");
      out.write("\t\t<DIV id=\"wrapper\" style=\"padding-top: 9px;\"></DIV>\r\n");
      out.write("\t\t<!-- 底部版权信息begin -->\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("<DIV class=\"lanren footer\" style=\"margin-top: 20px;\">\r\n");
      out.write("\t\t\t广告联系&nbsp;&nbsp;|&nbsp;&nbsp;关于我们&nbsp;&nbsp;|&nbsp;&nbsp;站长在线：QQ\r\n");
      out.write("\t\t\t2496664615&nbsp;&nbsp; 鲁ICP备14027307号-1\r\n");
      out.write("\t<SPAN style=\"\">\r\n");
      out.write("\t\t<script type=\"text/javascript\">var cnzz_protocol = ((\"https:\" == document.location.protocol) ? \" https://\" : \" http://\");document.write(unescape(\"%3Cspan id='cnzz_stat_icon_1254920361'%3E%3C/span%3E%3Cscript src='\" + cnzz_protocol + \"s11.cnzz.com/z_stat.php%3Fid%3D1254920361%26show%3Dpic' type='text/javascript'%3E%3C/script%3E\"));</script>\r\n");
      out.write("\t</SPAN>\r\n");
      out.write("</DIV>\r\n");
      out.write("<!-- 访问日志 -->\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">$.ajax({  url:\"/front/index/visitLog.do\",  type:\"post\", data:{\"refer\":document.referrer,\"thisPage\":location.href },  dataType:\"text\", async: true});</script>");
      out.write("\r\n");
      out.write("\t\t<!-- 底部版权信息end -->\r\n");
      out.write("\t</BODY>\r\n");
      out.write("</HTML>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_s_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_0 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_0.setPageContext(_jspx_page_context);
    _jspx_th_s_if_0.setParent(null);
    _jspx_th_s_if_0.setTest("strCurMenu == 'index'");
    int _jspx_eval_s_if_0 = _jspx_th_s_if_0.doStartTag();
    if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_0.doInitBody();
      }
      do {
        out.write(" cur-sub-nav");
        int evalDoAfterBody = _jspx_th_s_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_0);
      return true;
    }
    _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_0);
    return false;
  }

  private boolean _jspx_meth_s_iterator_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_iterator_0 = (org.apache.struts2.views.jsp.IteratorTag) _jspx_tagPool_s_iterator_value_id.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_iterator_0.setPageContext(_jspx_page_context);
    _jspx_th_s_iterator_0.setParent(null);
    _jspx_th_s_iterator_0.setValue("lstProjectMenu");
    _jspx_th_s_iterator_0.setId("projectMenu");
    int _jspx_eval_s_iterator_0 = _jspx_th_s_iterator_0.doStartTag();
    if (_jspx_eval_s_iterator_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_iterator_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_iterator_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_iterator_0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<LI>\r\n");
        out.write("\t\t\t\t\t<A class=\"small-sub-nav");
        if (_jspx_meth_s_if_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_iterator_0, _jspx_page_context))
          return true;
        out.write("\" href=\"/front/yuyingshi/newsList.do?lProjectMenuID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("&menuID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write('"');
        out.write('>');
        if (_jspx_meth_s_property_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_iterator_0, _jspx_page_context))
          return true;
        out.write("</A>\r\n");
        out.write("\t\t\t\t</LI>\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_s_iterator_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_iterator_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_iterator_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_iterator_value_id.reuse(_jspx_th_s_iterator_0);
      return true;
    }
    _jspx_tagPool_s_iterator_value_id.reuse(_jspx_th_s_iterator_0);
    return false;
  }

  private boolean _jspx_meth_s_if_1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_iterator_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_1 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_1.setPageContext(_jspx_page_context);
    _jspx_th_s_if_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_iterator_0);
    _jspx_th_s_if_1.setTest("strCurMenu == menuKey");
    int _jspx_eval_s_if_1 = _jspx_th_s_if_1.doStartTag();
    if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_1.doInitBody();
      }
      do {
        out.write(" cur-sub-nav");
        int evalDoAfterBody = _jspx_th_s_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_1);
      return true;
    }
    _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_1);
    return false;
  }

  private boolean _jspx_meth_s_property_0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_iterator_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_0 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_0.setPageContext(_jspx_page_context);
    _jspx_th_s_property_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_iterator_0);
    _jspx_th_s_property_0.setValue("strMenuName");
    int _jspx_eval_s_property_0 = _jspx_th_s_property_0.doStartTag();
    if (_jspx_th_s_property_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_0);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_0);
    return false;
  }

  private boolean _jspx_meth_s_if_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_2 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_2.setPageContext(_jspx_page_context);
    _jspx_th_s_if_2.setParent(null);
    _jspx_th_s_if_2.setTest("objUpNews != null");
    int _jspx_eval_s_if_2 = _jspx_th_s_if_2.doStartTag();
    if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t<A href=\"/front/yuyingshi/detail.do?lProjectMenuID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("&newsID=");
        if (_jspx_meth_s_property_1((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_2, _jspx_page_context))
          return true;
        out.write('"');
        out.write('>');
        if (_jspx_meth_s_property_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_2, _jspx_page_context))
          return true;
        out.write("</A>\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_2);
      return true;
    }
    _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_2);
    return false;
  }

  private boolean _jspx_meth_s_property_1(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_1 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_1.setPageContext(_jspx_page_context);
    _jspx_th_s_property_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_2);
    _jspx_th_s_property_1.setValue("objUpNews.lId");
    int _jspx_eval_s_property_1 = _jspx_th_s_property_1.doStartTag();
    if (_jspx_th_s_property_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_1);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_1);
    return false;
  }

  private boolean _jspx_meth_s_property_2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_2 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_2.setPageContext(_jspx_page_context);
    _jspx_th_s_property_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_2);
    _jspx_th_s_property_2.setValue("objUpNews.strLongTitleTwo");
    int _jspx_eval_s_property_2 = _jspx_th_s_property_2.doStartTag();
    if (_jspx_th_s_property_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_2);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_2);
    return false;
  }

  private boolean _jspx_meth_s_else_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_else_0 = (org.apache.struts2.views.jsp.ElseTag) _jspx_tagPool_s_else.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_else_0.setPageContext(_jspx_page_context);
    _jspx_th_s_else_0.setParent(null);
    int _jspx_eval_s_else_0 = _jspx_th_s_else_0.doStartTag();
    if (_jspx_eval_s_else_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_else_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_else_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_else_0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t没有了\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_else_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_else_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_else_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_else.reuse(_jspx_th_s_else_0);
      return true;
    }
    _jspx_tagPool_s_else.reuse(_jspx_th_s_else_0);
    return false;
  }

  private boolean _jspx_meth_s_if_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_3 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_3.setPageContext(_jspx_page_context);
    _jspx_th_s_if_3.setParent(null);
    _jspx_th_s_if_3.setTest("objNextNews != null");
    int _jspx_eval_s_if_3 = _jspx_th_s_if_3.doStartTag();
    if (_jspx_eval_s_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t<A href=\"/front/yuyingshi/detail.do?lProjectMenuID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("&newsID=");
        if (_jspx_meth_s_property_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_3, _jspx_page_context))
          return true;
        out.write('"');
        out.write('>');
        if (_jspx_meth_s_property_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_3, _jspx_page_context))
          return true;
        out.write("</A>\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_if_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_if_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_3);
      return true;
    }
    _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_3);
    return false;
  }

  private boolean _jspx_meth_s_property_3(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_3 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_3.setPageContext(_jspx_page_context);
    _jspx_th_s_property_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_3);
    _jspx_th_s_property_3.setValue("objNextNews.lId");
    int _jspx_eval_s_property_3 = _jspx_th_s_property_3.doStartTag();
    if (_jspx_th_s_property_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_3);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_3);
    return false;
  }

  private boolean _jspx_meth_s_property_4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_4 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_4.setPageContext(_jspx_page_context);
    _jspx_th_s_property_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_3);
    _jspx_th_s_property_4.setValue("objNextNews.strLongTitleTwo");
    int _jspx_eval_s_property_4 = _jspx_th_s_property_4.doStartTag();
    if (_jspx_th_s_property_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_4);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_4);
    return false;
  }

  private boolean _jspx_meth_s_else_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:else
    org.apache.struts2.views.jsp.ElseTag _jspx_th_s_else_1 = (org.apache.struts2.views.jsp.ElseTag) _jspx_tagPool_s_else.get(org.apache.struts2.views.jsp.ElseTag.class);
    _jspx_th_s_else_1.setPageContext(_jspx_page_context);
    _jspx_th_s_else_1.setParent(null);
    int _jspx_eval_s_else_1 = _jspx_th_s_else_1.doStartTag();
    if (_jspx_eval_s_else_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_else_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_else_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_else_1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t没有了\r\n");
        out.write("\t\t\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_else_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_else_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_else_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_else.reuse(_jspx_th_s_else_1);
      return true;
    }
    _jspx_tagPool_s_else.reuse(_jspx_th_s_else_1);
    return false;
  }

  private boolean _jspx_meth_s_iterator_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_iterator_1 = (org.apache.struts2.views.jsp.IteratorTag) _jspx_tagPool_s_iterator_value_id.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_iterator_1.setPageContext(_jspx_page_context);
    _jspx_th_s_iterator_1.setParent(null);
    _jspx_th_s_iterator_1.setValue("lstProjectMenu");
    _jspx_th_s_iterator_1.setId("projectMenu");
    int _jspx_eval_s_iterator_1 = _jspx_th_s_iterator_1.doStartTag();
    if (_jspx_eval_s_iterator_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_iterator_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_iterator_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_iterator_1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t<A href=\"/front/yuyingshi/newsList.do?lProjectMenuID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("&menuID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write('"');
        out.write(' ');
        if (_jspx_meth_s_if_4((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_iterator_1, _jspx_page_context))
          return true;
        out.write('>');
        if (_jspx_meth_s_property_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_iterator_1, _jspx_page_context))
          return true;
        out.write("</A>\r\n");
        out.write("\t\t\t");
        int evalDoAfterBody = _jspx_th_s_iterator_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_iterator_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_iterator_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_iterator_value_id.reuse(_jspx_th_s_iterator_1);
      return true;
    }
    _jspx_tagPool_s_iterator_value_id.reuse(_jspx_th_s_iterator_1);
    return false;
  }

  private boolean _jspx_meth_s_if_4(javax.servlet.jsp.tagext.JspTag _jspx_th_s_iterator_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_4 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_4.setPageContext(_jspx_page_context);
    _jspx_th_s_if_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_iterator_1);
    _jspx_th_s_if_4.setTest("lId==objNews.objProjectMenu.lId");
    int _jspx_eval_s_if_4 = _jspx_th_s_if_4.doStartTag();
    if (_jspx_eval_s_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_4.doInitBody();
      }
      do {
        out.write("class='thisclass'");
        int evalDoAfterBody = _jspx_th_s_if_4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_if_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_4);
      return true;
    }
    _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_4);
    return false;
  }

  private boolean _jspx_meth_s_property_5(javax.servlet.jsp.tagext.JspTag _jspx_th_s_iterator_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_5 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_5.setPageContext(_jspx_page_context);
    _jspx_th_s_property_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_iterator_1);
    _jspx_th_s_property_5.setValue("strMenuName");
    int _jspx_eval_s_property_5 = _jspx_th_s_property_5.doStartTag();
    if (_jspx_th_s_property_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_5);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_5);
    return false;
  }

  private boolean _jspx_meth_s_iterator_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_iterator_2 = (org.apache.struts2.views.jsp.IteratorTag) _jspx_tagPool_s_iterator_value_id.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_iterator_2.setPageContext(_jspx_page_context);
    _jspx_th_s_iterator_2.setParent(null);
    _jspx_th_s_iterator_2.setValue("lstNewestMessage");
    _jspx_th_s_iterator_2.setId("news");
    int _jspx_eval_s_iterator_2 = _jspx_th_s_iterator_2.doStartTag();
    if (_jspx_eval_s_iterator_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_iterator_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_iterator_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_iterator_2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t<LI>\r\n");
        out.write("\t\t\t\t\t\t<A href=\"/front/yuyingshi/detail.do?lProjectMenuID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.objParentProjectMenu.lId }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("&newsID=");
        if (_jspx_meth_s_property_6((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_iterator_2, _jspx_page_context))
          return true;
        out.write('"');
        out.write('>');
        if (_jspx_meth_s_property_7((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_iterator_2, _jspx_page_context))
          return true;
        out.write("</A><EM></EM>\r\n");
        out.write("\t\t\t\t\t</LI>\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_iterator_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_iterator_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_iterator_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_iterator_value_id.reuse(_jspx_th_s_iterator_2);
      return true;
    }
    _jspx_tagPool_s_iterator_value_id.reuse(_jspx_th_s_iterator_2);
    return false;
  }

  private boolean _jspx_meth_s_property_6(javax.servlet.jsp.tagext.JspTag _jspx_th_s_iterator_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_6 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_6.setPageContext(_jspx_page_context);
    _jspx_th_s_property_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_iterator_2);
    _jspx_th_s_property_6.setValue("lId");
    int _jspx_eval_s_property_6 = _jspx_th_s_property_6.doStartTag();
    if (_jspx_th_s_property_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_6);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_6);
    return false;
  }

  private boolean _jspx_meth_s_property_7(javax.servlet.jsp.tagext.JspTag _jspx_th_s_iterator_2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_7 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_7.setPageContext(_jspx_page_context);
    _jspx_th_s_property_7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_iterator_2);
    _jspx_th_s_property_7.setValue("strLongTitle");
    int _jspx_eval_s_property_7 = _jspx_th_s_property_7.doStartTag();
    if (_jspx_th_s_property_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_7);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_7);
    return false;
  }

  private boolean _jspx_meth_s_iterator_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_iterator_3 = (org.apache.struts2.views.jsp.IteratorTag) _jspx_tagPool_s_iterator_value_status_id.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_iterator_3.setPageContext(_jspx_page_context);
    _jspx_th_s_iterator_3.setParent(null);
    _jspx_th_s_iterator_3.setValue("lstSuggestReading");
    _jspx_th_s_iterator_3.setId("news");
    _jspx_th_s_iterator_3.setStatus("myStatus");
    int _jspx_eval_s_iterator_3 = _jspx_th_s_iterator_3.doStartTag();
    if (_jspx_eval_s_iterator_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_iterator_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_iterator_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_iterator_3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t");
        if (_jspx_meth_s_if_5((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_iterator_3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_iterator_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_iterator_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_iterator_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_iterator_value_status_id.reuse(_jspx_th_s_iterator_3);
      return true;
    }
    _jspx_tagPool_s_iterator_value_status_id.reuse(_jspx_th_s_iterator_3);
    return false;
  }

  private boolean _jspx_meth_s_if_5(javax.servlet.jsp.tagext.JspTag _jspx_th_s_iterator_3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_5 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_5.setPageContext(_jspx_page_context);
    _jspx_th_s_if_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_iterator_3);
    _jspx_th_s_if_5.setTest("#myStatus.index < 20");
    int _jspx_eval_s_if_5 = _jspx_th_s_if_5.doStartTag();
    if (_jspx_eval_s_if_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_5.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t<DD>\r\n");
        out.write("\t\t\t\t\t\t\t<A href=\"/front/yuyingshi/detail.do?lProjectMenuID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.objParentProjectMenu.lId }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("&newsID=");
        if (_jspx_meth_s_property_8((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_5, _jspx_page_context))
          return true;
        out.write("\"\r\n");
        out.write("\t\t\t\t\t\t\t\ttarget=\"_blank\">");
        if (_jspx_meth_s_property_9((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_5, _jspx_page_context))
          return true;
        out.write("</A>\r\n");
        out.write("\t\t\t\t\t\t</DD>\r\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_s_if_5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_s_if_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
        out = _jspx_page_context.popBody();
    }
    if (_jspx_th_s_if_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_5);
      return true;
    }
    _jspx_tagPool_s_if_test.reuse(_jspx_th_s_if_5);
    return false;
  }

  private boolean _jspx_meth_s_property_8(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_8 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_8.setPageContext(_jspx_page_context);
    _jspx_th_s_property_8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_5);
    _jspx_th_s_property_8.setValue("lId");
    int _jspx_eval_s_property_8 = _jspx_th_s_property_8.doStartTag();
    if (_jspx_th_s_property_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_8);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_8);
    return false;
  }

  private boolean _jspx_meth_s_property_9(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:property
    org.apache.struts2.views.jsp.PropertyTag _jspx_th_s_property_9 = (org.apache.struts2.views.jsp.PropertyTag) _jspx_tagPool_s_property_value_nobody.get(org.apache.struts2.views.jsp.PropertyTag.class);
    _jspx_th_s_property_9.setPageContext(_jspx_page_context);
    _jspx_th_s_property_9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_5);
    _jspx_th_s_property_9.setValue("strLongTitle");
    int _jspx_eval_s_property_9 = _jspx_th_s_property_9.doStartTag();
    if (_jspx_th_s_property_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_9);
      return true;
    }
    _jspx_tagPool_s_property_value_nobody.reuse(_jspx_th_s_property_9);
    return false;
  }
}
