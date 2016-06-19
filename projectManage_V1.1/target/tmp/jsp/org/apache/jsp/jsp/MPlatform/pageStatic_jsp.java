package org.apache.jsp.jsp.MPlatform;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class pageStatic_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("function pageStatic(flag)\r\n");
      out.write("{\r\n");
      out.write("\tvar strURL = \"/front/m/static/\";\r\n");
      out.write("\tvar strFlag = \"\";\r\n");
      out.write("\t\r\n");
      out.write("\tif (flag == 1)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrFlag = \"indexStatic\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (flag == 2)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrFlag = \"subMenuGlobalStatic\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (flag == 3)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrFlag = \"subMenuStatic\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (flag == 4)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrFlag = \"appointSubMenuStatic\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (flag == 5)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrFlag = \"scrollPhotoStatic\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (flag == 6)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrFlag = \"amScrollPhotoStatic\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (flag == 7)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrFlag = \"scrollPhotoLevel2Static\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (flag == 8)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrFlag = \"subMenuPageStatic\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (flag == 9)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrFlag = \"nav\";\r\n");
      out.write("\t}\r\n");
      out.write("\t$(\"#div_data\").hide();\r\n");
      out.write("\t$(\"#div_msg\").show();\r\n");
      out.write("\tstrURL = strURL + strFlag + \".do\";\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("        url:strURL,\r\n");
      out.write("        type:\"post\",\r\n");
      out.write("        data:{},\r\n");
      out.write("        dataType:\"text\",\r\n");
      out.write("        async: true,\r\n");
      out.write("        success:function(data)\r\n");
      out.write("        {\r\n");
      out.write("        \tif (data == \"success\")\r\n");
      out.write("        \t{\r\n");
      out.write("        \t\t$(\"#div_return_msg\").text(\"执行成功\");\r\n");
      out.write("        \t\t$(\"#div_return_msg\").show();\r\n");
      out.write("        \t}\r\n");
      out.write("        \telse\r\n");
      out.write("        \t{\r\n");
      out.write("        \t\t$(\"#div_return_msg\").text(\"执行失败\");\r\n");
      out.write("        \t\t$(\"#div_return_msg\").show();\r\n");
      out.write("        \t}\r\n");
      out.write("        \t$(\"#div_data\").show();\r\n");
      out.write("        \t$(\"#div_msg\").hide();\r\n");
      out.write("        }\r\n");
      out.write(" \t\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body style=\"margin:100px;font-size:16px;\">\r\n");
      out.write("    <div id=\"div_return_msg\" style=\"display:none;\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"div_data\">\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<ul style=\"line-height:35px;\">\r\n");
      out.write("\t\t\t    <li>1、移动端首页静态化：<input type=\"button\" value=\"执行\" onclick=\"pageStatic(1);\"/></li>\r\n");
      out.write("\t\t\t\t<li>2、移动端子菜单全局静态化：<input type=\"button\" value=\"执行\" onclick=\"pageStatic(2);\"/></li>\r\n");
      out.write("\t\t\t\t<li>3、移动端所有子菜单前七条知识静态化：<input type=\"button\" value=\"执行\" onclick=\"pageStatic(3);\"/></li>\r\n");
      out.write("\t\t\t\t<li>4、移动端指定的二级分类的静态处理：<input type=\"text\" value=\"\" /><input type=\"button\" value=\"执行\" onclick=\"pageStatic(4);\"/></li>\r\n");
      out.write("\t\t\t\t<li>5、移动端子菜单最新滚动知识静态化：<input type=\"button\" value=\"执行\" onclick=\"pageStatic(5);\"/></li>\r\n");
      out.write("\t\t\t\t<li>6、移动端静态化指定一级菜单下的滚动图片：<input type=\"text\" value=\"\" /><input type=\"button\" value=\"执行\" onclick=\"pageStatic(6);\"/></li>\r\n");
      out.write("\t\t\t\t<li>7、移动端静态化二级级菜单下的滚动图片：<input type=\"button\" value=\"执行\" onclick=\"pageStatic(7);\"/></li>\r\n");
      out.write("\t\t\t\t<li>8、移动端二级菜单下所有知识分页：<input type=\"button\" value=\"执行\" onclick=\"pageStatic(8);\"/></li>\r\n");
      out.write("\t\t\t\t<li>9、移动端导航静态化：<input type=\"button\" value=\"执行\" onclick=\"pageStatic(9);\"/></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"btm_start_cap\" style=\"margin-top:40px;\">\r\n");
      out.write("\t\t\t<input type=\"button\" value=\"全部静态化\" onclick=\"pageStatic(7);\"/>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"div_msg\" style=\"display:none;\">\r\n");
      out.write("\t\t执行中...\r\n");
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
