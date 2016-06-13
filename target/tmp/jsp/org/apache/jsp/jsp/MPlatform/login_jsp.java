package org.apache.jsp.jsp.MPlatform;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_fielderror_cssStyle_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_s_fielderror_cssStyle_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_s_fielderror_cssStyle_nobody.release();
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head runat=\"server\">\r\n");
      out.write("    <title>项目自动化系统登陆</title>\r\n");
      out.write("    <link href=\"/commons/css/alogin.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"/commons/js/jquery/jquery-1.3.2.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"/commons/js/md5.js\"></script>\r\n");
      out.write("\t\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("function init()\r\n");
      out.write("{\r\n");
      out.write("\t// 验证码处理\r\n");
      out.write("\t$(\"#imageCode\").attr(\"src\", \"/imageServlet?flag=\" + (new Date()).getTime());\r\n");
      out.write("\tvar o = document.getElementById(\"Layer1\");\r\n");
      out.write("\to.style.left = (screen.width/2 - o.offsetLeft/2) + \"px\";\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 验证用户名。\r\n");
      out.write(" */\r\n");
      out.write("function checkUsername()\r\n");
      out.write("{\r\n");
      out.write("\t$(\"#userName_errorMsg\").text(\"\");\r\n");
      out.write("\tif($(\"#username\").val() == \"\")\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\"#userName_errorMsg\").text(\"用户名不能为空\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 验证密码。\r\n");
      out.write(" */\r\n");
      out.write("function checkPassword()\r\n");
      out.write("{\r\n");
      out.write("\t$(\"#pwd_errorMsg\").text(\"\");\r\n");
      out.write("\tif($(\"#password\").val() == \"\")\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\"#pwd_errorMsg\").text(\"密码不能为空\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 验证验证码。\r\n");
      out.write(" */\r\n");
      out.write("function checkImageCode()\r\n");
      out.write("{\r\n");
      out.write("\t$(\"#imageCode_errorMsg\").text(\"\");\r\n");
      out.write("\tif($(\"#verifycode\").val() == \"\")\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\"#imageCode_errorMsg\").text(\"验证码不能为空\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function changeImageCode(){\r\n");
      out.write("\t$(\"#imageCode\").attr(\"src\", \"/imageServlet?flag=\" + (new Date()).getTime());\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function startLogin()\r\n");
      out.write("{\r\n");
      out.write("\tlogin.submit();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 回车触发。\r\n");
      out.write(" */\r\n");
      out.write("document.onkeydown=function(e)\r\n");
      out.write("{\r\n");
      out.write("\tvar keycode=document.all?event.keyCode:e.which;\r\n");
      out.write("\t\r\n");
      out.write("\tif(keycode==13)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tlogin.submit();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"Main\">\r\n");
      out.write("        <ul>\r\n");
      out.write("            <li class=\"top\"></li>\r\n");
      out.write("            <li class=\"top2\"></li>\r\n");
      out.write("            <li class=\"topA\"></li>\r\n");
      out.write("            <li class=\"topB\">\r\n");
      out.write("            \t<span style=\"font-size:40px;\">\r\n");
      out.write("            \t\t<br/>\r\n");
      out.write("            \t\tPM管理系统\r\n");
      out.write("                \t<!-- <img src=\"/commons/images/login/logo.gif\" alt=\"\" style=\"\" /> -->\r\n");
      out.write("            \t</span>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"topC\"></li>\r\n");
      out.write("            <li class=\"topD\">\r\n");
      out.write("\r\n");
      out.write("            \t<form action=\"/login/usersLogin.do\" method=\"post\" name=\"login\" id=\"login\">\r\n");
      out.write("\t                <ul class=\"login\">\r\n");
      out.write("\t                \t<li>\r\n");
      out.write("\t                    \t");
      if (_jspx_meth_s_fielderror_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t                    </li>\r\n");
      out.write("\t                    <li>\r\n");
      out.write("\t                    \t<span class=\"left\">用户名：</span> \r\n");
      out.write("\t                    \t<span style=\"left\">\r\n");
      out.write("\t                        \t<input id=\"Text1\" type=\"text\" class=\"txt\" name=\"strUsername\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${strCustomerName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" onkeydown=\"if(event.keyCode==32) return false\"/>\r\n");
      out.write("\t                    \t</span>\r\n");
      out.write("\t                    \t<span id=\"userName_errorMsg\" style=\"font-size:12px;color:#ff0000;\"></span>\r\n");
      out.write("\t                    </li>\r\n");
      out.write("\t                    <li>\r\n");
      out.write("\t                    \t<span class=\"left\">密 码：</span> \r\n");
      out.write("\t                    \t<span style=\"left\">\r\n");
      out.write("\t                       \t\t<input type=\"password\" class=\"txt\" name=\"strPassword\" id=\"password\" onkeydown=\"if(event.keyCode==32) return false\"/>  \r\n");
      out.write("\t                    \t</span>\r\n");
      out.write("\t                    \t<span id=\"pwd_errorMsg\" style=\"font-size:12px;color:#ff0000;\"></span>\r\n");
      out.write("\t                    </li>\r\n");
      out.write("\t                     <li>\r\n");
      out.write("\t                     \t<span class=\"left\" style=\"\">验证码：</span>\r\n");
      out.write("\t                     \t<span style=\"left\">\r\n");
      out.write("\t                    \t\t<input type=\"text\" class=\"txtCode\" name=\"strVerifycode\" id=\"verifycode\"/>\r\n");
      out.write("\t                    \t</span>\r\n");
      out.write("\t                    \t<span id=\"imageCode_errorMsg\" style=\"font-size:12px;color:#ff0000;\"></span>\r\n");
      out.write("\t                    \t<span style=\"left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<img src=\"/imageServlet\" id=\"imageCode\" name=\"imageCode\" title=\"点击更换验证码\" style=\"width: 75px;height: 29px;vertical-align:middle\" onclick=\"changeImageCode()\" /><a href=\"javascript:changeImageCode();\" style=\"text-decoration: none;\">点击更换</a>\r\n");
      out.write("\t                    \t</span>\r\n");
      out.write("\t                    </li>\r\n");
      out.write("\t                </ul>\r\n");
      out.write("                </form>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"topE\"></li>\r\n");
      out.write("            <li class=\"middle_A\"></li>\r\n");
      out.write("            <li class=\"middle_B\"></li>\r\n");
      out.write("            <li class=\"middle_C\">\r\n");
      out.write("\t            <span class=\"btn\" onclick=\"startLogin();\" style=\"cursor:pointer;\">\r\n");
      out.write("\t               <img alt=\"\" src=\"/commons/images/login/btnlogin.gif\"/>\r\n");
      out.write("\t            </span>\r\n");
      out.write("            </li>\r\n");
      out.write("            <li class=\"middle_D\"></li>\r\n");
      out.write("            <li class=\"bottom_A\"></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div align=center style=\"color:white;font-size:15px;\">\r\n");
      out.write("    QQ：376289635&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("    Phone:15601893123&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("    QQ群：<a target=\"_blank\" href=\"http://shang.qq.com/wpa/qunwpa?idkey=bf25712c54ca049803ee847e1a47f45bd5cfed7f80d1e8b47e4bd9d84da4f632\"><img border=\"0\" src=\"http://pub.idqqimg.com/wpa/images/group.png\" alt=\"PM官方交流群\" title=\"PM官方交流群\"></a>\r\n");
      out.write("    </div>\r\n");
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

  private boolean _jspx_meth_s_fielderror_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:fielderror
    org.apache.struts2.views.jsp.ui.FieldErrorTag _jspx_th_s_fielderror_0 = (org.apache.struts2.views.jsp.ui.FieldErrorTag) _jspx_tagPool_s_fielderror_cssStyle_nobody.get(org.apache.struts2.views.jsp.ui.FieldErrorTag.class);
    _jspx_th_s_fielderror_0.setPageContext(_jspx_page_context);
    _jspx_th_s_fielderror_0.setParent(null);
    _jspx_th_s_fielderror_0.setCssStyle("color: red");
    int _jspx_eval_s_fielderror_0 = _jspx_th_s_fielderror_0.doStartTag();
    if (_jspx_th_s_fielderror_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_fielderror_cssStyle_nobody.reuse(_jspx_th_s_fielderror_0);
      return true;
    }
    _jspx_tagPool_s_fielderror_cssStyle_nobody.reuse(_jspx_th_s_fielderror_0);
    return false;
  }
}
