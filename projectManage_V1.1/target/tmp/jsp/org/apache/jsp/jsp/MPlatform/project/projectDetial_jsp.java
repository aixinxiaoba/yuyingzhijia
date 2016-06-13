package org.apache.jsp.jsp.MPlatform.project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class projectDetial_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_iterator_value_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_if_test_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_s_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_s_iterator_value_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_if_test_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_s_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_s_iterator_value_id.release();
    _jspx_tagPool_s_if_test_nobody.release();
    _jspx_tagPool_s_if_test.release();
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
      out.write("<link type=\"text/css\" href=\"/commons/css/register.css\"  rel=\"stylesheet\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/jquery/jquery-1.3.2.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/jquery.form.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function submitForm(){\r\n");
      out.write("\t\r\n");
      out.write("\tif ($(\"#lProjectID\").val() > 0)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\"#projectForm\").attr(\"action\", \"projectModify.do\");\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tif ($(\"#ck_SName\").val() == \"\")\r\n");
      out.write("\t{\r\n");
      out.write("\t\talert(\"请填写项目名称！\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tif ($(\"#ck_SName\").val() == \"\")\r\n");
      out.write("\t{\r\n");
      out.write("\t\talert(\"请填写项目名称！\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif ($(\"#ck_EnSName\").val() == \"\")\r\n");
      out.write("\t{\r\n");
      out.write("\t\talert(\"请填写项目英文名称！\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#oper_module\").hide();\r\n");
      out.write("\t$(\"#msg_module\").html(\"正在处理...\");\r\n");
      out.write("\t$('#projectForm').ajaxSubmit(function(msg){\r\n");
      out.write("\t\t\tif (msg == \"success\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tif ($(\"#lProjectID\").val() > 0)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\talert(\"修改成功！\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\talert(\"保存成功！\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tlocation.href = \"/mpf/project/projectList.do\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(msg);\r\n");
      out.write("\t\t\t\t$(\"#oper_module\").show();\r\n");
      out.write("\t\t\t\t$(\"#msg_module\").html(\"\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("      \t}\r\n");
      out.write("    );\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("$(function (){\r\n");
      out.write("\tvar status = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.nEmailListStatus}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\";\r\n");
      out.write("\tvar resultPage = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.nIsUseResultPage}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\";\r\n");
      out.write("\tif (status == 0)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\"#useQQList\").attr(\"checked\", \"checked\");\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (status == 1)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\"#notUseQQList\").attr(\"checked\", \"checked\");\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tif (resultPage == 0)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\"#notUseResultPage\").attr(\"checked\", \"checked\");\r\n");
      out.write("\t}\r\n");
      out.write("\telse if (resultPage == 1)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\"#useResultPage\").attr(\"checked\", \"checked\");\t\t\r\n");
      out.write("\t}\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"index_main_div1\" style=\"background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;\">\r\n");
      out.write("\t<div style=\"border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;\"><img style=\"margin-left:5px;\" src=\"/commons/image/index_main_div_left.gif\" width=\"6\" height=\"2\" align=\"absmiddle\">&nbsp;<span style=\"font-weight:bold;font-size:12px;\">项目新建</span></div>\r\n");
      out.write("\t<div style=\"margin-top: 10px;min-height:200px;\" id=\"formbox\" class=\"form\">\r\n");
      out.write("\t\t<form name=\"projectForm\" method=\"post\" id=\"projectForm\" action=\"/mpf/project/projectAdd.do\">\r\n");
      out.write("\t\t\t<input type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" name=\"lProjectID\" id=\"lProjectID\"/>\r\n");
      out.write("\t\t\t<div class=\"item\">\r\n");
      out.write("\t\t\t\t<span class=\"label\">项目名称：</span>\r\n");
      out.write("\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t          <input type=\"text\" name=\"objProject.strPname\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.strPname}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"text\" tabindex=\"1\" id=\"ck_SName\"/>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      <div class=\"item\">\r\n");
      out.write("\t\t\t\t<span class=\"label\">项目英文名称：</span>\r\n");
      out.write("\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t        \t<input type=\"text\" name=\"objProject.projectKey\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.projectKey}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"text\" tabindex=\"1\" id=\"ck_EnSName\"/>\r\n");
      out.write("\t\t        \t<span>作为网站访问地址后缀，请牢记！</span>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      ");
      if (_jspx_meth_s_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t      <div class=\"item\">\r\n");
      out.write("\t\t\t\t<span class=\"label\">项目类型：</span>\r\n");
      out.write("\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t          <select name=\"lCustomerTypeID\">\r\n");
      out.write("                       ");
      if (_jspx_meth_s_if_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t          </select>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      <!-- \r\n");
      out.write("\t\t      \t\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      <div class=\"item\">\r\n");
      out.write("\t\t\t\t<span class=\"label\">邮件列表对应的项目ID：</span>\r\n");
      out.write("\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t          <input type=\"text\" name=\"objProject.strEmailListPID\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.strEmailListPID}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"text\" tabindex=\"1\" id=\"ck_SName\"/>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      <div class=\"item\">\r\n");
      out.write("\t\t\t\t<span class=\"label\">是否启用邮件列表：</span>\r\n");
      out.write("\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t        \t<input type=\"radio\" class=\"checkbox\" name=\"objProject.nEmailListStatus\" value=\"0\" id=\"useQQList\"/>\r\n");
      out.write("\t\t        \t<label class=\"pad\" for=\"purpose1\">启用&nbsp;</label>\r\n");
      out.write("\t\t        \t<input type=\"radio\" class=\"checkbox\" name=\"objProject.nEmailListStatus\" value=\"1\" id=\"notUseQQList\"/>\r\n");
      out.write("\t\t        \t<label class=\"pad\" for=\"purpose1\">不启用</label>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      <div class=\"item\">\r\n");
      out.write("\t\t\t\t<span class=\"label\">是否启用结果页：</span>\r\n");
      out.write("\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t        \t<input type=\"radio\" class=\"checkbox\" name=\"objProject.nIsUseResultPage\" value=\"1\" id=\"useResultPage\"/>\r\n");
      out.write("\t\t        \t<label class=\"pad\" for=\"purpose1\">启用&nbsp;</label>\r\n");
      out.write("\t\t        \t<input type=\"radio\" class=\"checkbox\" name=\"objProject.nIsUseResultPage\" value=\"0\" id=\"notUseResultPage\"/>\r\n");
      out.write("\t\t        \t<label class=\"pad\" for=\"purpose1\">不启用</label>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t       -->\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      <!-- \r\n");
      out.write("\t\t      <div class=\"item\">\r\n");
      out.write("\t\t\t\t<span class=\"label\">商户号：</span>\r\n");
      out.write("\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t          <input type=\"text\" name=\"objProject.strMerId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.strMerId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"text\" tabindex=\"1\" id=\"ck_SName\"/>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      <div class=\"item\">\r\n");
      out.write("\t\t\t\t<span class=\"label\">商户密钥：</span>\r\n");
      out.write("\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t          <input type=\"text\" name=\"objProject.strKeyValue\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.strKeyValue}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"text\" tabindex=\"1\" id=\"ck_SName\"/>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      <div class=\"item\">\r\n");
      out.write("\t\t\t\t<span class=\"label\">回调地址：</span>\r\n");
      out.write("\t\t        <div class=\"fl\">\r\n");
      out.write("\t\t          <input type=\"text\" name=\"objProject.strCallBackURL\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.strCallBackURL}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"text\" tabindex=\"1\" id=\"ck_SName\"/>\r\n");
      out.write("\t\t        </div>\r\n");
      out.write("\t\t      </div>\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      <div class=\"\">\r\n");
      out.write("\t\t\t\t\t<span class=\"label\">邮件内容：</span>\r\n");
      out.write("\t\t\t        <div class=\"fl\" style=\"border: 1px solid #f7fbfc;\">\r\n");
      out.write("\t\t\t          <textarea style=\"width:440px;height:200px; \" name=\"objProject.strEmailContent\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objProject.strEmailContent}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</textarea>\r\n");
      out.write("\t\t\t        </div>\r\n");
      out.write("\t\t       </div>\r\n");
      out.write("\t\t       -->\r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div style=\"margin-top: 40px;margin-left:20px;text-align: center;position:relative;\" id=\"oper_module\">\r\n");
      out.write("\t\t<img src=\"/commons/image/s10.gif\" width=\"58\" height=\"22\" style=\"cursor: pointer;\" onclick=\"submitForm();\">\r\n");
      out.write("\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t<img src=\"/commons/image/s8.gif\" width=\"59\" height=\"22\" style=\"cursor: pointer;\" onclick=\"history.back()\">\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div style=\"margin-top: 40px;margin-left:20px;text-align: center;position:relative;\" id=\"msg_module\">\r\n");
      out.write("\t</div>\r\n");
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

  private boolean _jspx_meth_s_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_0 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test_nobody.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_0.setPageContext(_jspx_page_context);
    _jspx_th_s_if_0.setParent(null);
    _jspx_th_s_if_0.setTest("objUsers.nCurrentLevel == 1");
    int _jspx_eval_s_if_0 = _jspx_th_s_if_0.doStartTag();
    if (_jspx_th_s_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_s_if_test_nobody.reuse(_jspx_th_s_if_0);
      return true;
    }
    _jspx_tagPool_s_if_test_nobody.reuse(_jspx_th_s_if_0);
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
    _jspx_th_s_if_1.setTest("lstCustomerType != null && lstCustomerType.size > 0");
    int _jspx_eval_s_if_1 = _jspx_th_s_if_1.doStartTag();
    if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t");
        if (_jspx_meth_s_iterator_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_if_1, _jspx_page_context))
          return true;
        out.write("\t\r\n");
        out.write("                       ");
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

  private boolean _jspx_meth_s_iterator_0(javax.servlet.jsp.tagext.JspTag _jspx_th_s_if_1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:iterator
    org.apache.struts2.views.jsp.IteratorTag _jspx_th_s_iterator_0 = (org.apache.struts2.views.jsp.IteratorTag) _jspx_tagPool_s_iterator_value_id.get(org.apache.struts2.views.jsp.IteratorTag.class);
    _jspx_th_s_iterator_0.setPageContext(_jspx_page_context);
    _jspx_th_s_iterator_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_if_1);
    _jspx_th_s_iterator_0.setValue("lstCustomerType");
    _jspx_th_s_iterator_0.setId("objCustomerType");
    int _jspx_eval_s_iterator_0 = _jspx_th_s_iterator_0.doStartTag();
    if (_jspx_eval_s_iterator_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_iterator_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_iterator_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_iterator_0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t<option value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objCustomerType.lId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("\"  ");
        if (_jspx_meth_s_if_2((javax.servlet.jsp.tagext.JspTag) _jspx_th_s_iterator_0, _jspx_page_context))
          return true;
        out.write('>');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objCustomerType.strName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</option>   \r\n");
        out.write("\t\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_if_2(javax.servlet.jsp.tagext.JspTag _jspx_th_s_iterator_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_2 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_2.setPageContext(_jspx_page_context);
    _jspx_th_s_if_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_s_iterator_0);
    _jspx_th_s_if_2.setTest("%{lCustomerTypeID == #objCustomerType.lId}");
    int _jspx_eval_s_if_2 = _jspx_th_s_if_2.doStartTag();
    if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_2.doInitBody();
      }
      do {
        out.write("selected=\"selected\"");
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
}
