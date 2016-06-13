package org.apache.jsp.m;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.struts2.components.Include;
import java.util.*;

public final class m_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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
      response.setContentType("text/html;charset=utf-8");
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
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width,initial-scale=1, maximum-scale=1, user-scalable=no\">\r\n");
      out.write("<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\r\n");
      out.write("<title>育婴之家网_育婴知识分享平台</title>\r\n");
      out.write("<META name=\"Keywords\"\r\n");
      out.write("\tcontent=\"育婴之家，儿童之家，育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿\">\r\n");
      out.write("<META name=\"description\"\r\n");
      out.write("\tcontent=\"育婴之家是一个分享交流育儿知识经验的 平台，我们致力于将最好的育儿知识，通过我们这个平台知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱\">\r\n");
      out.write("<!-- 汽车之家css -->\r\n");
      out.write("<link href=\"/commons/css/autohome/com.css\" rel=\"stylesheet\" />\r\n");
      out.write("<!-- end 汽车之家 -->\r\n");
      out.write("<LINK rel=\"stylesheet\" href=\"/commons/css/tianya/main.css\" type=\"text/css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/commons/css/tianya/index2.css\">\r\n");
      out.write("<SCRIPT src=\"/m/common/gm/jquery.js\"></SCRIPT>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function(){\r\n");
      out.write("\t$(\"#rec_div\").load(\"/static/m/p/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objSubProjectMenu.lId }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("_1.html\");\r\n");
      out.write("\t$(\"#j-slider-home\").load(\"/static/m/scroll/l2/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objSubProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(".html\");\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function nextPage(data)\r\n");
      out.write("{\r\n");
      out.write("\t$(\"#type_div\").load(\"/static/m/p/\" + data + \".html\");\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<input type=\"hidden\" value=\"");
      out.print(basePath);
      out.write("\" id=\"basePath\"/>\r\n");
      out.write("\t<div id=\"j-homepage\" class=\"m-doc\">\r\n");
      out.write("\t\t<!-- header -->\r\n");
      out.write("\t\t<div class=\"ty-m-nav\">\r\n");
      out.write("\t\t\t<header class=\"m-header\" id=\"j-header\">\r\n");
      out.write("\t\t\t\t<div class=\"m-bar f-cf\">\r\n");
      out.write("\t\t\t\t\t<ul class=\"m-bar-center\" id=\"ty_logo\">\r\n");
      out.write("\t\t\t\t\t\t<li class=\"logo\"><span class=\"\" style=\"font-size:16px;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objSubProjectMenu.strMenuName }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span></li>\r\n");
      out.write("\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</header>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- For FIS -->\r\n");
      out.write("\t\t<!-- main 头部图片循环显示 -->\r\n");
      out.write("\t\t<div class=\"m-main\">\r\n");
      out.write("\t\t\t <!-- 滚动图片显示 -->\r\n");
      out.write("\t\t\t<div class=\"u-slider u-slider-home\">\r\n");
      out.write("\t\t\t\t<div class=\"sliderbox gg gg-item\" id=\"j-slider-home\" ></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"u-box\">\r\n");
      out.write("\t\t\t\t<a name=\"type\"></a>\r\n");
      out.write("\t\t\t\t<div class=\"see-wrap\">\r\n");
      out.write("\t\t\t\t\t<div class=\"u-tab tab-left-off\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"tab-wrap\" id=\"tab_wrap\" style=\"overflow: hidden;\">\r\n");
      out.write("\t\t\t\t\t\t\t<ul class=\"f-cf\" style=\"left: 0px; top: 0px; width: 2030px; position: absolute;\" id=\"f-cf\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<!-- \r\n");
      out.write("\t\t\t\t\t\t\t\t<li class=\"cur\" style=\"width: 145px;\" data-c='newest/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objSubProjectMenu.lId }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("_1.html'>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span class=\"\">最新</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t<li style=\"width: 145px;\" data-c='scroll/l2/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objSubProjectMenu.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(".html'>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span  class=\"\">排行</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t -->\r\n");
      out.write("\t\t\t\t\t\t\t\t<li class=\"cur\" style=\"width: 145px;\" data-c='p/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objSubProjectMenu.lId }", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("_1.html'>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<span  class=\"\">全部</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"type_div\"></div>\r\n");
      out.write("\t\t\t\t\t<div class=\"u-bd\" id=\"rec_div\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script src=\"http://static.tianyaui.com/global/ty2.0/TY_m_2.0.js\"></script>\r\n");
      out.write("\t<script src=\"/commons/js/tianya/main.js\"></script>\r\n");
      out.write("\t<script src=\"/commons/js/tianya/index2.js?20160414\"></script>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tTY.m.nav.init({\r\n");
      out.write("\t\t\t'app_str' : \"bbs\",\r\n");
      out.write("\t\t\tnavFromHtml : true,\r\n");
      out.write("\t\t\tnavOpen : true,\r\n");
      out.write("\t\t\tshowHideIcon : false,\r\n");
      out.write("\t\t\tmoveHide : false,\r\n");
      out.write("\t\t\tclickDom2Hide : false\r\n");
      out.write("\t\t})\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
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
}
