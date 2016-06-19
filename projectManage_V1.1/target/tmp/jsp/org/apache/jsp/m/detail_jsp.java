package org.apache.jsp.m;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.util.*;
import java.net.URLEncoder;

public final class detail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


public class CS{

    private  int siteId = 0;
    private final String imageDomain = "c.cnzz.com";
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;

    public CS(int siteId){
        this.setAccount(siteId);
    };

    public void setAccount(int siteId){
        this.siteId = siteId;
    };

    public String trackPageView(){
        String imageLocation = this.request.getScheme() + "://"+ this.imageDomain + "/wapstat.php";
        String r = this.request.getHeader("referer") == null ? "" : this.request.getHeader("referer");
        String rnd = Integer.toString((int)(Math.random() * 0x7fffffff));
        String imageUrl = imageLocation + "?" + "siteid=" + this.siteId + "&r=" + URLEncoder.encode(r) + "&rnd=" + rnd;
        return imageUrl;
    };

    public void setHttpServlet(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/m/foot.jsp");
    _jspx_dependants.add("/cs.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_if_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_else;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_s_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_else = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_s_if_test.release();
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML>\r\n");
      out.write("<HTML>\r\n");
      out.write("<HEAD>\r\n");
      out.write("<META content=\"IE=11.0000\" http-equiv=\"X-UA-Compatible\">\r\n");
      out.write("<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("<META http-equiv=\"Cache-Control\" content=\"max-age=0\">\r\n");
      out.write("<META http-equiv=\"Expires\" content=\"0\">\r\n");
      out.write("<META http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n");
      out.write("<META name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0\">\r\n");
      out.write("<META name=\"Keywords\"\r\n");
      out.write("\tcontent=\"育婴知识分享，分享你最喜欢的育婴知识\">\r\n");
      out.write("<META name=\"description\"\r\n");
      out.write("\tcontent=\"育婴知识分享，分享你最喜欢的育婴知识\">\r\n");
      out.write("<TITLE>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.strTitle}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write('-');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProjectMenu.strMenuName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("-育婴知识学习交流平台</TITLE>\r\n");
      out.write("<LINK href=\"/m/common/gm/m_article_common.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<LINK href=\"/m/common/detail.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".home {\r\n");
      out.write("    background: url(\"/commons/images/top2.png\") no-repeat 0% 0% / 46px 184px;\r\n");
      out.write("    top: 0px;\r\n");
      out.write("    width: 44px;\r\n");
      out.write("    height: 44px;\r\n");
      out.write("    position: absolute;\r\n");
      out.write("    margin-top:-5px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".crumbs_line {\r\n");
      out.write("    float: left;\r\n");
      out.write("    width: 10px;\r\n");
      out.write("    height: 10px;\r\n");
      out.write("    border-top: 2px solid #ffe4f2;\r\n");
      out.write("    border-right: 2px solid #ffe4f2;\r\n");
      out.write("    -moz-transform: rotate(45deg);\r\n");
      out.write("    -webkit-transform: rotate(45deg);\r\n");
      out.write("    -o-transform: rotate(45deg);\r\n");
      out.write("    margin: 12px 7px 0 3px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".comments a {\r\n");
      out.write("    font-size: 14px;\r\n");
      out.write("    background: #ed6d46;\r\n");
      out.write("    color: #fff;\r\n");
      out.write("    height: 35px;\r\n");
      out.write("    line-height: 35px;\r\n");
      out.write("    padding: 0 16px;\r\n");
      out.write("    border-radius: 5px;\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("    margin-left: 16px;\r\n");
      out.write("    text-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("<SCRIPT src=\"/m/common/gm/jquery.js\"></SCRIPT>\r\n");
      out.write("<META name=\"GENERATOR\" content=\"MSHTML 11.00.9600.17842\">\r\n");
      out.write("<!-- <SCRIPT language=\"javascript\" src=\"/m/common/gm/js.js\"></SCRIPT> -->\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function(){\r\n");
      out.write("\t$(\"#goTop\").hide();\r\n");
      out.write("\tloadNewsList();\r\n");
      out.write("\t\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("// 评论模块\r\n");
      out.write("function showComments()\r\n");
      out.write("{\r\n");
      out.write("\t$(\"#comments\").show();\r\n");
      out.write("\tloadMood();\r\n");
      out.write("\tloadComments();\r\n");
      out.write("}\r\n");
      out.write("function loadMood()\r\n");
      out.write("{\r\n");
      out.write("\t// 加载心情模块。\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl : \"/front/m/loadMood.do\",\r\n");
      out.write("\t\ttype : \"post\",\r\n");
      out.write("\t\tdata : {\r\n");
      out.write("\t\t\t\"newsID\" : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\",\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tdataType : \"json\",\r\n");
      out.write("\t\tasync : true,\r\n");
      out.write("\t\tsuccess:function(data)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tif (data != \"\" && data.length > 0)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tvar strHTML = \"\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tfor (var i=0; i < data.length; i++)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tstrHTML+= \" <li> <p id='hits_1' style='color:red;'>\"+data[i][2]+\"</p> \";\r\n");
      out.write("\t\t\t\t\tstrHTML+= \" <div class='pillar' id='zhu_1' style='height: \"+data[i][3]+\"px; line-height: 13px;'>&nbsp;</div> \";\r\n");
      out.write("\t\t\t\t\tstrHTML+= \" <a onclick='getMotions(\" + data[i][0] + \");'><img src='/m/common/m/m\" + data[i][0] + \".gif'> \";\r\n");
      out.write("\t\t\t\t\tstrHTML+= \" <p style='color:#ff6d93;'>\" + data[i][1] + \"</p></a> </li> \";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$(\"#mood_show\").html(strHTML);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\t\r\n");
      out.write("// 获取心情评论列表\r\n");
      out.write("function getMotions(type)\r\n");
      out.write("{\r\n");
      out.write("\tvar size = $(\"#size_zxdt\").val();\r\n");
      out.write("\t\r\n");
      out.write("\t// 心情评论。\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl : \"/front/m/moodExpress.do\",\r\n");
      out.write("\t\ttype : \"post\",\r\n");
      out.write("\t\tdata : {\r\n");
      out.write("\t\t\t\"newsID\" : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\",\r\n");
      out.write("\t\t\t\"moodType\" : type\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tdataType : \"text\",\r\n");
      out.write("\t\tasync : true,\r\n");
      out.write("\t\tsuccess:function(data)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tif (data != \"\" && data.length > 0)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tif (data == 1)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t// 重新加载心情模块。\r\n");
      out.write("\t\t\t\t\tloadMood();\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse if (data == 2)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\talert(\"您已经表达过心情喽，保持平常心有益于身心健康！\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\talert(\"似乎无法评论喽，请稍后重试！\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//加载新闻列表。\r\n");
      out.write("function loadNewsList()\r\n");
      out.write("{\r\n");
      out.write("\tvar size = $(\"#size_zxdt\").val();\r\n");
      out.write("\t$(\"#rd_more\").hide();\r\n");
      out.write("\t$(\"#rd_msg\").show();\r\n");
      out.write("\t// 加载新闻标签。\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl : \"/front/m/loadNewstList.do\",\r\n");
      out.write("\t\ttype : \"post\",\r\n");
      out.write("\t\tdata : {\"newsSize\":size,\"lProjectMenuID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.objParentProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"},\r\n");
      out.write("\t\tdataType : \"json\",\r\n");
      out.write("\t\tasync : true,\r\n");
      out.write("\t\tsuccess:function(data)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tif (data != \"\" && data.length > 0)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tvar strHTML = \"\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tfor (var i=0; i < data.length; i++)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tstrHTML+=\"<LI style='clear:both;'> <DIV>\";\r\n");
      out.write("\t\t\t\t\tstrHTML+=\"<A class='list_title_l' href='/front/yuyingshi/detail.do?newsID=\"+data[i].lId+\"'>\"+data[i].strFormatTitle+\"</A>\";\r\n");
      out.write("\t\t\t\t\tstrHTML+=\" </DIV> </LI> \";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(\"#newstList\").html($(\"#newstList\").html() + strHTML);\r\n");
      out.write("\t\t\t\tsize = parseInt(size) + 7;\r\n");
      out.write("\t\t\t\t$(\"#size_zxdt\").val(size);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif (size > 8)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\turl : \"/front/m/loadProjectMenu.do\",\r\n");
      out.write("\t\t\t\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\t\t\t\tdata : {\"newsSize\":size,\"curProjectMenuID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\",\"lProjectMenuID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.objParentProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"},\r\n");
      out.write("\t\t\t\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\t\t\t\tasync : true,\r\n");
      out.write("\t\t\t\t\t\tsuccess:function(menuData)\r\n");
      out.write("\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\tvar strSubHTML = \"\";\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\tif (menuData != \"\" && menuData.length > 0)\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tstrSubHTML+=\"<LI style='float:left;font-size:14px;border:0px solid red;'>\";\r\n");
      out.write("\t\t\t\t\t\t\t\tstrSubHTML+=\"&nbsp;&nbsp;<span>推荐栏目：</span>\";\t\r\n");
      out.write("\t\t\t\t\t\t\t\tstrSubHTML+=\"</LI>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tfor (var i=0; i < menuData.length; i++)\r\n");
      out.write("\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstrSubHTML+=\"<LI style='float:left;border:0px solid red;'>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstrSubHTML+=\"<A style='font-size:14px;color:#ff6d93;' href='/front/m/newsList.do?menuID=\"+menuData[i].lId+\"'>\"+menuData[i].mobileMenuName+\"</A>&nbsp;&nbsp;&nbsp;\";\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstrSubHTML+=\"</LI>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t$(\"#newstList\").html($(\"#newstList\").html() + strSubHTML);\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(\"#rd_more\").show();\r\n");
      out.write("\t\t\t$(\"#rd_msg\").hide();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 加载同类新闻。\r\n");
      out.write(" */\r\n");
      out.write("function loadSimilarNewsList()\r\n");
      out.write("{\r\n");
      out.write("\tvar size = $(\"#size_similar_n\").val();\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#more_similar_n\").hide();\r\n");
      out.write("\t$(\"#msg_similar_n\").show();\r\n");
      out.write("\t// 加载新闻标签。\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl : \"/front/m/loadSimilarNewstList.do\",\r\n");
      out.write("\t\ttype : \"post\",\r\n");
      out.write("\t\tdata : {\"newsSize\":size,\"lProjectMenuID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"},\r\n");
      out.write("\t\tdataType : \"json\",\r\n");
      out.write("\t\tasync : true,\r\n");
      out.write("\t\tsuccess:function(data)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tif (data != \"\" && data.length > 0)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tvar strHTML = \"\";\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tfor (var i=0; i < data.length; i++)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tstrHTML+=\"<LI style='clear:both;'> <DIV>\";\r\n");
      out.write("\t\t\t\t\tstrHTML+=\"<A class='list_title_l' href='/front/yuyingshi/detail.do?newsID=\"+data[i].lId+\"'>\"+data[i].strFormatTitle+\"</A>\";\r\n");
      out.write("\t\t\t\t\tstrHTML+=\" </DIV> </LI> \";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(\"#newstList_s\").html($(\"#newstList_s\").html() + strHTML);\r\n");
      out.write("\t\t\t\tsize = parseInt(size) + 7;\r\n");
      out.write("\t\t\t\t$(\"#size_similar_n\").val(size);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif (size > 7)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\turl : \"/front/m/loadProjectMenu.do\",\r\n");
      out.write("\t\t\t\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\t\t\t\tdata : {\"newsSize\":size,\"curProjectMenuID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\",\"lProjectMenuID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.objParentProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"},\r\n");
      out.write("\t\t\t\t\t\tdataType : \"json\",\r\n");
      out.write("\t\t\t\t\t\tasync : true,\r\n");
      out.write("\t\t\t\t\t\tsuccess:function(menuData)\r\n");
      out.write("\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\tvar strSubHTML = \"\";\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\tif (menuData != \"\" && menuData.length > 0)\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tstrSubHTML+=\"<LI style='float:left;font-size:14px;border:0px solid red;'>\";\r\n");
      out.write("\t\t\t\t\t\t\t\tstrSubHTML+=\"&nbsp;&nbsp;<span>推荐栏目：</span>\";\t\r\n");
      out.write("\t\t\t\t\t\t\t\tstrSubHTML+=\"</LI>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tfor (var i=0; i < menuData.length; i++)\r\n");
      out.write("\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstrSubHTML+=\"<LI style='float:left;border:0px solid red;'>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstrSubHTML+=\"<A style='font-size:14px;color:#ff6d93;' href='/front/m/newsList.do?menuID=\"+menuData[i].lId+\"'>\"+menuData[i].mobileMenuName+\"</A>&nbsp;&nbsp;&nbsp;\";\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\tstrSubHTML+=\"</LI>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t$(\"#newstList_s\").html($(\"#newstList_s\").html() + strSubHTML);\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t$(\"#more_similar_n\").show();\r\n");
      out.write("\t\t\t$(\"#msg_similar_n\").hide();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("// 评论内容\r\n");
      out.write("function comments()\r\n");
      out.write("{\r\n");
      out.write("\t$(\"#comments_id\").hide();\r\n");
      out.write("\t$(\"#showMsg\").css(\"color\",\"green\");\r\n");
      out.write("\t$(\"#showMsg\").css(\"font-size\",\"14px\");\r\n");
      out.write("\t$(\"#showMsg\").text(\"正在处理...\");\r\n");
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
      out.write("\t\t\t\t\tsetTimeout(function(){$(\"#showMsg\").text(\"\");$(\"#comments_id\").fadeIn(500);}, 2000);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tsetTimeout(function(){$(\"#showMsg\").text(\"\");$(\"#comments_id\").fadeIn(500);}, 2000);\r\n");
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
      out.write("\t\t\t\t\tstrHTML += \"<h4 class='content-listtitle' style='line-height:10px;'>\";\r\n");
      out.write("\t\t\t\t\tstrHTML += \"<span class='list-title'>\" + data[i].strNickName + \"</span> <span class='list-date'>\"+data[i].createTime+\"</span>\";\r\n");
      out.write("\t\t\t\t\tstrHTML += \"</h4>\"\r\n");
      out.write("\t\t\t\t\tstrHTML += \"<p class='list-content' style='line-height:0px;'>\"+data[i].content+\"</p>\"\r\n");
      out.write("\t\t\t\t\tstrHTML += \"</div>\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$(\"#commentList\").html(strHTML);\r\n");
      out.write("\t\t\t\tcommentsNum = data.length;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t$(\"#commentList\").html(\"暂无评论，快来抢沙发吧！\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t$(\"#commentsNum\").text(commentsNum);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</HEAD>\r\n");
      out.write("<BODY class=\"bg_1\">\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<DIV class=\"nav_header_wrap_fix\">\r\n");
      out.write("<!-- \t\t\t<A class=\"nav_header_right\" href=\"/front/m/nav.do\"><IMG src=\"/m/common/gm/header_nav_btn.png\"></A> -->\r\n");
      out.write("\t\t\t<A class=\"nav_header_right\" href=\"/static/m/nav.html\"><IMG src=\"/m/common/gm/header_nav_btn.png\"></A>\r\n");
      out.write("\t\t\t<DIV class=\"nav_header_center_n\" style=\"color:#fff\">\r\n");
      out.write("<!-- \t\t\t\t<a class=\"home\" href=\"/front/m/index.do\"></a> -->\r\n");
      out.write("\t\t\t\t <a class=\"home\" href=\"/static/m/index.html\"></a>\r\n");
      out.write("\t\t\t   <div style=\"margin-left:50px;\">\r\n");
      out.write("\t\t\t   \t\r\n");
      out.write("\t\t\t   \t<A style=\"font-size:18px;float:left;\" class=\"nav_header_breadcrumbs\" href=\"/static/m/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.objParentProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(".html\"\r\n");
      out.write("\t\t\t\t\ttarget=\"_self\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.objParentProjectMenu.mobileMenuName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</A>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t   \t ");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"crumbs_line\"></div>\r\n");
      out.write("\t\t\t\t<A style=\"font-size:18px;float:left;\" class=\"nav_header_breadcrumbs\"\r\n");
      out.write("\t\t\t\t\thref=\"/front/m/newsList.do?lProjectMenuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.objParentProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&menuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("\t\t\t\t\ttarget=\"_self\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.mobileMenuName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</A>\r\n");
      out.write("\t\t\t\t<div class=\"crumbs_line\"></div>\r\n");
      out.write("\t\t\t\t<a  style=\"font-size:18px;float:left;\" class=\"nav_header_breadcrumbs\"> 正文</a>\r\n");
      out.write("\t\t\t   </div>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<BR class=\"clear\">\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<!--正文部分---->\r\n");
      out.write("\t\t<DIV class=\"contentWrapper_fix\">\r\n");
      out.write("\t\t\t<DIV class=\"boxcontent\">\r\n");
      out.write("\t\t\t\t<div class=\"boxcontent_txt\"><H1 id=\"articleTitle\" style=\"color: #fe49a8;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.strTitle}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</H1></div>\r\n");
      out.write("\t\t\t\t<DIV id=\"articleSubtitle\"></DIV>\r\n");
      out.write("\t\t\t\t<DIV id=\"contentMsg\">\r\n");
      out.write("\t\t\t\t\t<SPAN id=\"pubTime\"><!-- ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.strSendDate}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" --></SPAN> <SPAN id=\"source\">育婴之家网</SPAN>\r\n");
      out.write("\t\t\t\t\t<SPAN class=\"fontSize_wrap\">\r\n");
      out.write("\t\t\t\t\t\t<A id=\"fontPlus\" href=\"javascript:;\">A+</A>\r\n");
      out.write("\t\t\t\t\t\t<A id=\"fontMinus\" href=\"javascript:;\">A-</A>\r\n");
      out.write("\t\t\t\t\t</SPAN>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t<DIV id=\"contentMain\"  style=\"\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.strContent}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</DIV>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t<div style=\"clear: both;\"></div>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!-- 发表评论 start -->\r\n");
      out.write("\t\t\t<DIV class=\"bar_nav\" style=\"margin-bottom:15px;\">\r\n");
      out.write("\t\t\t\t<DIV class=\"bar_nav_main\" style=\"font-size:16px;\">\r\n");
      out.write("\t\t\t\t\t<A href=\"javascript:showComments();\" atremote=\"\">\r\n");
      out.write("\t\t\t\t\t\t发表评论\r\n");
      out.write("\t\t\t\t\t</A>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!-- 发表评论 end -->\r\n");
      out.write("\t\t\t<div style=\"display:none\" id=\"comments\">\r\n");
      out.write("\t\t\t\t<div class=\"boxcontent\">\r\n");
      out.write("\t\t\t\t\t<FORM name=\"saypl\" id=\"saypl\" onsubmit=\"\" action=\"\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t\t<ul id=\"comments_msg\"><li id=\"showMsg\" style=\"\"></li></ul>\r\n");
      out.write("\t\t\t\t\t\t<ul id=\"comments_id\">\r\n");
      out.write("\t\t\t\t\t\t\t<li style=\"margin-left:-35px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<TEXTAREA name=\"context\" id=\"context\" rows=\"2\"  style=\"width:97%;border: 1px solid rgb(204, 204, 204);font-size:18px; color:#F00\"></TEXTAREA>\r\n");
      out.write("\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li style=\"margin-top: 10px;margin-left: -50px;\" class=\"comments\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<a href=\"javascript:comments();\" class=\"comment-txtbottom_a\" style=\"color:#fff;\">发表评论</a>\r\n");
      out.write("\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</FORM>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- 评论列表展示 start -->\r\n");
      out.write("\t\t\t\t<DIV class=\"bar_nav\" style=\"margin-bottom:15px;\">\r\n");
      out.write("\t\t\t\t\t<DIV class=\"bar_nav_main\" style=\"font-size:16px;\">\r\n");
      out.write("\t\t\t\t\t\t<A href=\"javascript:void(0)\" atremote=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t评论列表： &nbsp;&nbsp;共有<span id=\"commentsNum\"></span>条评论\r\n");
      out.write("\t\t\t\t\t\t</A>\r\n");
      out.write("\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<div  class=\"boxcontent\">\r\n");
      out.write("\t\t\t\t\t<!-- 评论列表 -->\r\n");
      out.write("\t\t\t\t\t<div id=\"commentList\" style=\"margin:10px;\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- 评论列表展示 end -->\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<!-- start -->\r\n");
      out.write("\t\t\t\t<DIV class=\"bar_nav\" style=\"margin-bottom:15px;\">\r\n");
      out.write("\t\t\t\t\t<DIV class=\"bar_nav_main\" style=\"font-size:16px;\">\r\n");
      out.write("\t\t\t\t\t\t<A href=\"javascript:void(0)\" atremote=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t读后感<span style=\"font-size:14px;\"><!--（找到志同道合的朋友）--></span>\r\n");
      out.write("\t\t\t\t\t\t</A>\r\n");
      out.write("\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<div id=\"motionsDiv\" class=\"boxcontent\">\r\n");
      out.write("\t\t\t\t\t<div id=\"mood\">\r\n");
      out.write("\t\t\t\t\t\t<div style=\"clear: both;\"></div>\r\n");
      out.write("\t\t\t\t\t\t<ul id=\"mood_show\">\r\n");
      out.write("\t\t\t\t\t\t\t<!-- mood -->\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<!-- \r\n");
      out.write("\t\t\t\t\t<div class=\"list_more\">\r\n");
      out.write("\t\t\t\t\t\t<A title=\"每日排行\" href=\"javascript:loadSimilarNewsList();\" atremote=\"\" id=\"more_similar_n\">\r\n");
      out.write("\t\t\t\t\t\t\t\t 查看每日排行 ?</A>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t -->\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- 表情结束 end -->\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- start -->\r\n");
      out.write("\t\t\t<DIV class=\"bar_nav\">\r\n");
      out.write("\t\t\t\t<DIV class=\"bar_nav_main\" style=\"font-size:16px;\">\r\n");
      out.write("\t\t\t\t\t<A href=\"/front/m/newsList.do?menuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\tatremote=\"\">同类阅读<span style=\"font-size:14px;\">（多一次阅读，多一次收获）</span></A>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t<DIV class=\"boxcontent\">\r\n");
      out.write("\t\t\t\t<!-- 热点排行-->\r\n");
      out.write("\t\t\t\t<DIV class=\"nav_content_item\">\r\n");
      out.write("\t\t\t\t\t<DIV class=\"list_wrap\" style=\"display: block;\">\r\n");
      out.write("\t\t\t\t\t\t<UL class=\"news_list\" style=\"display: block;\" id=\"newstList_s\">\r\n");
      out.write("\t\t\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      if (_jspx_meth_s_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      if (_jspx_meth_s_else_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<span style=\"color: rgb(186, 186, 186);font-size:14px\">&nbsp;下一篇：</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      if (_jspx_meth_s_if_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      if (_jspx_meth_s_else_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t</UL>\r\n");
      out.write("\t\t\t\t\t\t<DIV class=\"list_more\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" id=\"size_similar_n\" value=\"0\"/>\r\n");
      out.write("\t\t\t\t\t\t\t<!-- \r\n");
      out.write("\t\t\t\t\t\t\t<A title=\"查看更多\" href=\"/front/m/newsList.do?menuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" atremote=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t 查看更多 <span style=\"color:#ff6d93\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.mobileMenuName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span> ?</A>\r\n");
      out.write("\t\t\t\t\t\t\t -->\r\n");
      out.write("\t\t\t\t\t\t\t<A title=\"查看更多\" href=\"javascript:loadSimilarNewsList();\" atremote=\"\" id=\"more_similar_n\">\r\n");
      out.write("\t\t\t\t\t\t\t 查看更多 ?</A>\r\n");
      out.write("\t\t\t\t\t\t\t <div style=\"text-align: center;display: none;\" id=\"msg_similar_n\">请稍后...</div>\r\n");
      out.write("\t\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t<!-- end -->\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<DIV class=\"bar_nav\">\r\n");
      out.write("\t\t\t\t<DIV class=\"bar_nav_main\"  style=\"font-size:16px;\">\r\n");
      out.write("\t\t\t\t\t<A href=\"/front/m/newsestList.do?lProjectMenuID=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.objProjectMenu.objParentProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" atremote=\"\">最新动态<span style=\"font-size:14px;\">（知识早知道，育儿无烦恼）</span></A>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t<DIV class=\"boxcontent\">\r\n");
      out.write("\t\t\t\t<!-- 热点排行-->\r\n");
      out.write("\t\t\t\t<DIV class=\"nav_content_item\">\r\n");
      out.write("\t\t\t\t\t<DIV class=\"list_wrap\" style=\"display: block;\">\r\n");
      out.write("\t\t\t\t\t\t<!-- <li style=\"text-align:center;\">正在加载...</li> -->\r\n");
      out.write("\t\t\t\t\t\t<UL class=\"news_list\" style=\"display: block;\" id=\"newstList\">\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</UL>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<DIV class=\"list_more\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" id=\"size_zxdt\" value=\"0\"/>\r\n");
      out.write("\t\t\t\t\t\t\t<A title=\"查看更多\" href=\"javascript:loadNewsList();\" atremote=\"\" id=\"rd_more\">查看更多 ?</A>\r\n");
      out.write("\t\t\t\t\t\t\t<div style=\"text-align: center;display: none;\" id=\"rd_msg\">等一会，精彩继续...</div>\r\n");
      out.write("\t\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t<!-- search -->\r\n");
      out.write("\t\t\t<DIV class=\"mainlistsbox\" style=\"margin: 10px 0px 0px;\">\r\n");
      out.write("\t\t\t\t<DIV class=\"sm_schbox\" style=\"display: block;\">\r\n");
      out.write("\t\t\t\t\t<FORM id=\"searchform\" action=\"/front/m/newsSearch.do\" method=\"get\">\r\n");
      out.write("\t\t\t\t\t\t<INPUT name=\"searchText\" class=\"sm_ipt\" type=\"text\" value=\"育婴知识搜索\"\r\n");
      out.write("\t\t\t\t\t\t\tautocomplete=\"off\" onfocus=\"this.value='';\" /> <INPUT\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"sm_sbt\" type=\"submit\" value=\"搜 索\" />\r\n");
      out.write("\t\t\t\t\t</FORM>\r\n");
      out.write("\t\t\t\t</DIV>\r\n");
      out.write("\t\t\t</DIV> ");
      out.write("\r\n");
      out.write("\t\t\t<DIV class=\"clear\"></DIV>\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t<DIV id=\"goTop\" style='background: url(\"/m/common/top1.png\") no-repeat center rgba(0, 0, 0, 0.5); width: 30px; height: 30px; right: 10px; bottom: 50px; position: fixed;'></DIV>\r\n");
      out.write("\t\t<DIV style=\"clear: both;\"></DIV>\r\n");
      out.write("\t\t<DIV class=\"foot_nav\" onclick=\"location.href='/static/m/nav.html?a=1'\">\r\n");
      out.write("\t\t\t<DIV class=\"font_wrap\">\r\n");
      out.write("\t\t\t\t<A>查看更多分类</A>\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t</DIV>\r\n");
      out.write("\t\t</DIV>\r\n");
      out.write("\t\t<!-- 设置文章阅读数量 -->\r\n");
      out.write("\t\t<script>$.ajax( { url:\"/front/index/setReadNum.do\", type:\"post\",  data:{\"newsID\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNews.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"}, dataType:\"text\",  async: true });</script>\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("<DIV class=\"footer_main\" style=\"\">\r\n");
      out.write("\t<DIV class=\"footer\" style=\"text-align: center;\">\r\n");
      out.write("\t\t\t<A href=\"#\" >寻求合作</A> ｜ \r\n");
      out.write("\t\t\t<A href=\"#\" >推广服务</A> \r\n");
      out.write("\t\t\t<SPAN class=\"bookmark\"> ｜\r\n");
      out.write("\t\t\t\t<A id=\"bookmark\" href=\"ext:add_favorite\" news=\"\">加入书签</A>\r\n");
      out.write("\t\t\t</SPAN>\r\n");
      out.write("\t</DIV>\r\n");
      out.write("\t<DIV style=\"text-align: center; color: rgb(186, 186, 186); font-size: 12px;\">\r\n");
      out.write("\t\t育婴之家网版权所有 | 服务QQ：2496664615\r\n");
      out.write("\t</DIV>\r\n");
      out.write("</DIV>\r\n");
      out.write("\r\n");
      out.write("<!-- 访问日志 -->\r\n");
      out.write("<!-- <script type=\"text/javascript\">$.ajax({  url:\"/front/index/visitLog.do\",  type:\"post\", data:{\"refer\":document.referrer,\"thisPage\":location.href },  dataType:\"text\", async: true});</script> -->\r\n");
      out.write("\r\n");
      out.write("<!-- 引入cnzz统计 -->\r\n");
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
CS cs = new CS(1254920361);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();
      out.write(" \r\n");
      out.write("<img src=\"");
      out.print( imgurl );
      out.write("\" width=\"0\" height=\"0\"  />");
      out.write("\r\n");
      out.write("\t</div>\t\r\n");
      out.write("<!-- 百度统计代码开始 -->\r\n");
      out.write("<!-- \r\n");
      out.write("<script>\r\n");
      out.write("var _hmt = _hmt || [];\r\n");
      out.write("(function() {\r\n");
      out.write("  var hm = document.createElement(\"script\");\r\n");
      out.write("  hm.src = \"//hm.baidu.com/hm.js?3b117244a311332d9a3a45a4c7185dbb\";\r\n");
      out.write("  var s = document.getElementsByTagName(\"script\")[0]; \r\n");
      out.write("  s.parentNode.insertBefore(hm, s);\r\n");
      out.write("})();\r\n");
      out.write("</script>\r\n");
      out.write(" -->\r\n");
      out.write("<!-- 百度统计代码结束 -->\r\n");
      out.write("\r\n");
      out.write("</BODY>\r\n");
      out.write("</HTML>\r\n");
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
    _jspx_th_s_if_0.setTest("objUpNews != null");
    int _jspx_eval_s_if_0 = _jspx_th_s_if_0.doStartTag();
    if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<span style=\"color: rgb(186, 186, 186);font-size:14px\">&nbsp;上一篇：</span>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<A class=\"list_title_l\" style=\"padding-left:0px;\" href=\"/front/yuyingshi/detail.do?newsID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objUpNews.lId }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objUpNews.strFormatTitle }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</A>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
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
        out.write("\t\t\t\t\t\t\t\t\t已经是第一篇了！\r\n");
        out.write("\t\t\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_1 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_1.setPageContext(_jspx_page_context);
    _jspx_th_s_if_1.setParent(null);
    _jspx_th_s_if_1.setTest("objNextNews != null");
    int _jspx_eval_s_if_1 = _jspx_th_s_if_1.doStartTag();
    if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<A class=\"list_title_l\" style=\"padding-left:0px;\" href=\"/front/yuyingshi/detail.do?newsID=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNextNews.lId }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objNextNews.strFormatTitle }", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</A>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
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
        out.write("\t\t\t\t\t\t\t\t\t<span style=\"font-size:14px;\">恭喜你已阅读到最后一条记录！</span>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
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
}
