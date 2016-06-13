package org.apache.jsp.jsp.MPlatform.shortUrl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ip_005freturn_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("function submitForm(){\r\n");
      out.write("\t$(\"#result\").show(\"请稍后...\");\r\n");
      out.write("\t$(\"#make\").hide();\r\n");
      out.write("\t$('#usersForm').ajaxSubmit(function(msg){\r\n");
      out.write("\t\t\tmsg = msg.split(\"@@\");\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif (msg[0] == \"error\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(msg[1]);\r\n");
      out.write("\t\t\t\t$(\"#result\").hide();\r\n");
      out.write("\t\t\t\t$(\"#make\").show();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t$(\"#result\").html(\"制作成功，您的短网址为：\" + msg[1] + \" 请牢记！！！\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("      \t}\r\n");
      out.write("    );\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function getShortUrl()\r\n");
      out.write("{\r\n");
      out.write("\tif ($(\"#myUrl\").val() == \"\")\r\n");
      out.write("\t{\r\n");
      out.write("\t\talert(\"请输入短网址！！！\");\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar myUrl = document.getElementById(\"myUrl\").value.trim();\r\n");
      out.write("\tvar flag = myUrl.substring(myUrl.lastIndexOf(\"/\") + 1);\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#result\").show(\"请稍后...\");\r\n");
      out.write("\t$(\"#make\").hide();\r\n");
      out.write("\t\r\n");
      out.write("\tvar myUrl = document.getElementById(\"myUrl\").value.trim();\r\n");
      out.write("\tvar flag = myUrl.substring(myUrl.lastIndexOf(\"/\") + 1);\r\n");
      out.write("\tvar strURL = \"http://955.cc/short/?url=955.cc/\" + flag + \"&format=json&callback=test\";\r\n");
      out.write("\t\r\n");
      out.write("\t$.getJSON(strURL, function(data){\r\n");
      out.write("\t\t\t\tif (data.errno == 0)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tvar strHTML = \"<div>原网址为：\" + data.url + \"</div>\";\r\n");
      out.write("\t\t\t\t\t$(\"#result\").html(strHTML);\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\talert(data.error);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"index_main_div1\" style=\"background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;\">\r\n");
      out.write("\t<div style=\"border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;\"><img style=\"margin-left:5px;\" src=\"/commons/image/index_main_div_left.gif\" width=\"6\" height=\"2\" align=\"absmiddle\">&nbsp;<span style=\"font-weight:bold;font-size:12px;\">短网址还原</span></div>\r\n");
      out.write("\t<div id=\"make\">\r\n");
      out.write("\t\t<form action=\"/mpf/customer/createShortUrl.do\" method=\"post\" id=\"usersForm\">\r\n");
      out.write("\t\t\t<div style=\"text-align:center;margin-top:20px;\">\r\n");
      out.write("\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t<span style=\"font-size:20px;\">请输入您要还原的短网址：</span>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" value=\"\" id=\"myUrl\" name=\"myUrl\" class=\"text\" tabindex=\"1\" id=\"ck_SName\" style=\"width: 500px; height:25px;border:1px solid green;font-size:15px;\"/>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<div style=\"margin-top: 40px;margin-left:20px;text-align: center;\">\r\n");
      out.write("\t\t\t<input type=\"button\" onclick=\"getShortUrl();\" value=\"开始查询\" name=\"create\"/>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"result\" style=\"margin-top: 40px;margin-left:20px;text-align: center;font-size:25px;\"></div>\r\n");
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
