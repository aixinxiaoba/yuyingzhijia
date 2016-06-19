package org.apache.jsp.jsp.MPlatform.data;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class dataCapture_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/index.css\" rel=\"stylesheet\"  />\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/register.css\"  rel=\"stylesheet\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/jquery/jquery-1.3.2.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/jquery.form.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/nicEdit.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function dataCapture()\r\n");
      out.write("{\r\n");
      out.write("\t//$(\"#btm_start_cap\").hide();\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("        url:\"/mpf/news/dataCapture.do\",\r\n");
      out.write("        type:\"post\",\r\n");
      out.write("        data:{\"category\":$(\"#category\").val(),\r\n");
      out.write("        \t\"maxPageNum\":$(\"#maxPageNum\").val(),\r\n");
      out.write("        \t\"categoryNum\":$(\"#categoryNum\").val(),\r\n");
      out.write("        \t\"menuKey\":$(\"#menuKey\").val(),\r\n");
      out.write("        \t\"startPageNo\":$(\"#startPageNo\").val(),\r\n");
      out.write("        \t\"webType\":$(\"#webType\").val()\r\n");
      out.write("        \t\r\n");
      out.write("        \t},\r\n");
      out.write("        dataType:\"text\",\r\n");
      out.write("        async: true,\r\n");
      out.write("        success:function(data)\r\n");
      out.write("        {\r\n");
      out.write("        \talert(data);\r\n");
      out.write("        \t//$(\"#btm_start_cap\").show();\r\n");
      out.write("        }\r\n");
      out.write(" \t\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body style=\"margin:100px;\">\r\n");
      out.write("\t<form>\r\n");
      out.write("\t\t举例：http://www.yuyingnet.com/zbhy/lxzs/list_72_5.html\r\n");
      out.write("\t\t<p>\r\n");
      out.write("\t\t分类：zbhy/lxzs\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t\t最大页码：5\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t\t分类号：72\r\n");
      out.write("\t\t</p>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li>http://baby.5721.net/Newborn/List_1_1.shtml</li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t网站标志：<input type=\"text\" name=\"webType\" id=\"webType\" value=\"5721\"/>\r\n");
      out.write("\t\t</p>\r\n");
      out.write("\t\t菜单ID：<input type=\"text\" name=\"menuKey\" id=\"menuKey\" value=\"\"/>\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t\t分类：<input type=\"text\" name=\"category\" id=\"category\" value=\"\"/>\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t\t最大页码：<input type=\"text\" name=\"maxPageNum\" id=\"maxPageNum\" value=\"\"/>\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t\t分类号：<input type=\"text\" name=\"categoryNum\" id=\"categoryNum\" value=\"\"/>\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t\t开始页码：<input type=\"text\" name=\"startPageNo\" id=\"startPageNo\" value=\"\"/>\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<div id=\"btm_start_cap\">\r\n");
      out.write("\t\t<input type=\"button\" value=\"开始获取\" onclick=\"dataCapture();\"/>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
