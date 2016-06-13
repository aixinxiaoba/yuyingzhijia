package org.apache.jsp.jsp.MPlatform.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class passwordModify_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/index.css\" rel=\"stylesheet\"  />\r\n");
      out.write("\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/register.css\"  rel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("<!-- Tooltip classes -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/commons/poshytip1.2/css/tip-yellow/tip-yellow.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/commons/poshytip1.2/css/tip-violet/tip-violet.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/commons/poshytip1.2/css/tip-darkgray/tip-darkgray.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/commons/poshytip1.2/css/tip-skyblue/tip-skyblue.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/commons/poshytip1.2/css/tip-yellowsimple/tip-yellowsimple.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/commons/poshytip1.2/css/tip-twitter/tip-twitter.css\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/commons/poshytip1.2/css/tip-green/tip-green.css\" type=\"text/css\" />\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/jquery/jquery-1.7.2.js\" ></script>\r\n");
      out.write("\r\n");
      out.write("<!-- the Poshy Tip plugin files -->\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/poshytip1.2/js/jquery.poshytip.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/0_core.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/jquery.form.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function submitForm(){\r\n");
      out.write("\t$('#usresForm').ajaxSubmit(function(msg){\r\n");
      out.write("\t\t\t$(\"#oper_modify\").slideUp();\r\n");
      out.write("\t\t\t$(\"#oper_return\").slideDown();\r\n");
      out.write("\t\t\tif (msg == \"success\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t$(\"#oper_result\").text(\"修改成功！\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t$(\"#oper_result\").text(msg);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("      \t}\r\n");
      out.write("    );\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function operReturn()\r\n");
      out.write("{\r\n");
      out.write("\t$(\"#oper_modify\").slideDown();\r\n");
      out.write("\t$(\"#oper_return\").slideUp();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div id=\"index_main_div1\" style=\"background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;\">\r\n");
      out.write("\t<div style=\"border:1px solid #73c0e0;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;\"><img style=\"margin-left:5px;\" src=\"/commons/image/index_main_div_left.gif\" width=\"6\" height=\"2\" align=\"absmiddle\">&nbsp;<span style=\"font-weight:bold;font-size:12px;\">密码修改</span></div>\r\n");
      out.write("\t<div id=\"oper_modify\">\r\n");
      out.write("\t\t<form name=\"usresForm\" method=\"post\" id=\"usresForm\" action=\"/mpf/users/usersModifyPassowrd.do\">\r\n");
      out.write("\t\t\t<div id=\"formbox\" class=\"form\">\r\n");
      out.write("\t\t\t\t<div class=\"item\"><span class=\"label\" >原始密码：</span>\r\n");
      out.write("\t\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t\t          <input type=\"password\" name=\"strOldPassword\" class=\"text\" tabindex=\"1\" id=\"ck_SName\"/>\r\n");
      out.write("\t\t\t          <label id=\"username_succeed\" class=\"blank\"></label>\r\n");
      out.write("\t\t\t          <span class=\"clear\"></span>\r\n");
      out.write("\t\t\t          <div id=\"username_error\"></div>\r\n");
      out.write("\t\t\t        </div>\r\n");
      out.write("\t\t\t      </div>\r\n");
      out.write("\t\t\t      \r\n");
      out.write("\t\t\t      <div class=\"item\"><span class=\"label\">请输入新密码：</span>\r\n");
      out.write("\t\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t\t          <input type=\"password\" name=\"strPassword\" class=\"text\" tabindex=\"1\" id=\"ck_SName\"/>\r\n");
      out.write("\t\t\t          <label id=\"username_succeed\" class=\"blank\"></label>\r\n");
      out.write("\t\t\t          <span class=\"clear\"></span>\r\n");
      out.write("\t\t\t          <div id=\"username_error\"></div>\r\n");
      out.write("\t\t\t        </div>\r\n");
      out.write("\t\t\t      </div>\r\n");
      out.write("\t\t\t      \r\n");
      out.write("\t\t\t      <div class=\"item\"><span class=\"label\">请再次输入新密码：</span>\r\n");
      out.write("\t\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t\t          <input type=\"password\" name=\"strRePassword\" class=\"text\" tabindex=\"1\" id=\"ck_SName\"/>\r\n");
      out.write("\t\t\t          <label id=\"username_succeed\" class=\"blank\"></label>\r\n");
      out.write("\t\t\t          <span class=\"clear\"></span>\r\n");
      out.write("\t\t\t          <div id=\"username_error\"></div>\r\n");
      out.write("\t\t\t        </div>\r\n");
      out.write("\t\t\t      </div>\r\n");
      out.write("\t      </div>\r\n");
      out.write("\t      <div style=\"text-align:center;\">\r\n");
      out.write("\t\t\t<img src=\"/commons/image/s10.gif\" width=\"58\" height=\"22\" style=\"cursor: pointer;\" onclick=\"submitForm();\">\r\n");
      out.write("\t\t  </div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t <div style=\"display:none;text-align:center;\" id=\"oper_return\" class=\"formbox\">\r\n");
      out.write("\t \t<div id=\"oper_result\"></div>\r\n");
      out.write("\t  \t<div style=\"margin: 30px;\"><img src=\"/commons/image/s8.gif\" width=\"59\" height=\"22\" style=\"cursor: pointer;\" onclick=\"operReturn()\"></div>\r\n");
      out.write("\t </div>\r\n");
      out.write("</div>\r\n");
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
