package org.apache.jsp.jsp.MPlatform.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class usersList_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>部门列表</title>\r\n");
      out.write("\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/commons/easyui/easyui.css\" />\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"/commons/easyui/icon.css\" />\r\n");
      out.write("\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/index.css\" rel=\"stylesheet\" ></link>\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/common.css\" rel=\"stylesheet\" ></link>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/jquery/jquery-1.7.2.min.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("//easy UI start\r\n");
      out.write("$(function(){\r\n");
      out.write("\t$('#customerList').datagrid({\r\n");
      out.write("\t\ttitle: \"商户列表\",\r\n");
      out.write("\t\tloadMsg:'加载数据...',\r\n");
      out.write("\t\tfitColumns:true,\r\n");
      out.write("\t\tpageList:[15,20,30,40,50,100],\r\n");
      out.write("\t\tnowrap: true,\r\n");
      out.write("\t\tautoRowHeight: false,\r\n");
      out.write("\t\tstriped: true,\r\n");
      out.write("\t\tcollapsible:true,\r\n");
      out.write("\t\turl:'/mpf/users/loadUsersList.do',\r\n");
      out.write("\t\tsortName: 'title',\r\n");
      out.write("\t\tsortOrder: 'desc',\r\n");
      out.write("\t\tremoteSort: false,\r\n");
      out.write("\t\tidField:'title',\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tfrozenColumns:[[\r\n");
      out.write("\t\t    {field:'strName',title:\"&nbsp;&nbsp;商户名称\",align:\"left\",width:fixWidth(0.1242),\r\n");
      out.write("\t\t\t\tformatter:function(value,rec)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t    \t\tvar strHTML = \"<input type='checkbox' class='class_customer' value='\" + rec.lId + \"'>&nbsp;&nbsp;\";\r\n");
      out.write("\t\t    \t\t\r\n");
      out.write("\t\t\t\t\treturn strHTML += rec.strName;\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t    ]],\r\n");
      out.write("\t\tcolumns:[[\r\n");
      out.write("\t\t\t{field:'strLoginName',title:'QQ',width:fixWidth(0.1242),align:\"center\"},\r\n");
      out.write("\t\t\t{field:'strPersonPhone',title:'手机号码',width:fixWidth(0.1242),align:\"center\"},\r\n");
      out.write("\t\t\t{field:'strLastModifyTime',title:'申请时间',width:fixWidth(0.1242),align:\"center\"},\r\n");
      out.write("\t\t\t{field:'strActiveStatu',title:'激活状态',width:fixWidth(0.1242),align:\"center\"},\r\n");
      out.write("\t\t\t{field:'null',title:'操作',width:fixWidth(0.1242),align:\"center\",\r\n");
      out.write("\t\t\t\tformatter:function(value,rec)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tvar strURL = \"/jsp/MPlatform/users/projectList.jsp?lUsersID=\" + rec.lId;\r\n");
      out.write("\t\t\t\t\tif (rec.nIsActive == 1)\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\treturn \"<a href='javascript:void(0)' onclick='manageCustomer(\" + rec.lId + \",0);'>禁用</a> | <a href='\" + strURL + \"'>授权</a>\";\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t    \t\telse\r\n");
      out.write("\t\t    \t\t{\r\n");
      out.write("\t\t    \t\t\treturn \"<a href='javascript:void(0)' onclick='manageCustomer(\" + rec.lId + \",1);'>激活</a> | <a href='\" + strURL + \"'>授权</a>\";\r\n");
      out.write("\t\t    \t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t]],\r\n");
      out.write("\t\tpagination:true,\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\ttoolbar:[\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\ttext:'<input type=\"checkbox\" name=\"checkbox\" value=\"checkbox\" onclick=\"checkAll(this);\" style=\"margin-left:20px;\">&nbsp;全选'\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\ttext:'删除用户',\r\n");
      out.write("\t\t\ticonCls:'icon-remove',\r\n");
      out.write("\t\t\thandler:function(){\r\n");
      out.write("\t\t\t\tvar row = $('#customerList').datagrid('getSelected');\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tdeleteMer(row ? row.id : -1);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\ttext:'查看已授权的项目',\r\n");
      out.write("\t\t\ticonCls:'icon-edit',\r\n");
      out.write("\t\t\thandler:function(){\r\n");
      out.write("\t\t\t\tvar row = $('#customerList').datagrid('getSelected');\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif ($(\":checked.class_customer\") != null && $(\":checked.class_customer\").length == 1)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t// 项目授权。\r\n");
      out.write("\t\t\t\t\tauthorizationProjectList($(\":checked.class_customer\").val());\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse if ($(\":checked.class_customer\") != null && $(\":checked.class_customer\").length > 1)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t$.messager.alert('消息提示',\"<p style='font-size:14px;'>您只能选择一个进行查看！</p>\", \"info\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse if (row != null && row.lId > 0)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t// 项目授权。\r\n");
      out.write("\t\t\t\t\tauthorizationProjectList(row.lId);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t$.messager.alert('消息提示',\"<p style='font-size:14px;'>请选择您要授权的用户！</p>\", \"info\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function searchByName()\r\n");
      out.write("{\r\n");
      out.write("\t$('#customerList').datagrid('options').queryParams = {\r\n");
      out.write("\t\tstrCustomerName:$(\"#tempCustomerName\").val(),\r\n");
      out.write("\t\tstrCustomerQQ:$(\"#tempCustomerQQ\").val(),\r\n");
      out.write("\t\tstrParentCustomerQQ:$(\"#tempParentCustomerQQ\").val(),\r\n");
      out.write("\t\tstrCustomerTypeID:$(\"#tempCustomerType\").val()\r\n");
      out.write("\t};\r\n");
      out.write("   \t$(\"#customerList\").datagrid('reload'); // 重新加载。\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(" /**\r\n");
      out.write("  * 删除商户。\r\n");
      out.write("  */\r\n");
      out.write("function deleteMer(id)\r\n");
      out.write("{\r\n");
      out.write("\tvar count = 0;\r\n");
      out.write("\tvar strURL = \"/mpf/users/delUsers.do?flag=1\";\r\n");
      out.write("\tif (id > 0)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrURL += \"&usersID=\" + id;\r\n");
      out.write("\t}\r\n");
      out.write("\telse\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\":checked.class_customer\").each(function(i, item){\r\n");
      out.write("\t\t\tstrURL += \"&usersID=\" + item.value;\r\n");
      out.write("\t\t\tcount++;\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif (count = 0)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\talert(\"请选择您要删除的商户\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tif (!confirm(\"确认要删除吗？\"))\r\n");
      out.write("\t{\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t$.ajax(\r\n");
      out.write("\t{\r\n");
      out.write("        url:strURL,\r\n");
      out.write("        type:\"post\",\r\n");
      out.write("        data:{},\r\n");
      out.write("        dataType:\"text\",\r\n");
      out.write("        async: false,\r\n");
      out.write("        success:function(responseText)\r\n");
      out.write("        {\t           \r\n");
      out.write("\t\t\tif(responseText == \"success\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(\"删除成功！\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(responseText);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("\t$('#customerList').datagrid(\"reload\");\r\n");
      out.write("}\r\n");
      out.write(" \r\n");
      out.write("/**\r\n");
      out.write(" * 客户管理。\r\n");
      out.write(" */\r\n");
      out.write("function manageCustomer(lUsersID, type)\r\n");
      out.write("{\r\n");
      out.write("\tvar nCount = 0;\r\n");
      out.write("\tvar strURL;\r\n");
      out.write("\tvar msg = \"\";\r\n");
      out.write("\t\r\n");
      out.write("\tstrURL = \"/mpf/users/usersActive.do?lUsersID=\" + lUsersID + \"&nType=\" + type;\r\n");
      out.write("\tif (type == 1)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tmsg = \"激活\";\r\n");
      out.write("\t}\r\n");
      out.write("\telse\r\n");
      out.write("\t{\r\n");
      out.write("\t\tmsg = \"禁用\";\r\n");
      out.write("\t}\r\n");
      out.write("\t$.ajax(\r\n");
      out.write("\t{\r\n");
      out.write("        url:strURL,\r\n");
      out.write("        type:\"post\",\r\n");
      out.write("        data:{},\r\n");
      out.write("        dataType:\"text\",\r\n");
      out.write("        async: false,\r\n");
      out.write("        success:function(responseText)\r\n");
      out.write("        {\t           \r\n");
      out.write("\t\t\tif(responseText == \"success\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(msg + \"成功！\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(responseText);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\t$('#customerList').datagrid(\"reload\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function checkAll(objThis)\r\n");
      out.write("{\r\n");
      out.write("\tif (objThis.checked == true)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\".class_customer\").attr(\"checked\",\"checked\");\r\n");
      out.write("\t}\r\n");
      out.write("\telse\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\".class_customer\").removeAttr(\"checked\");\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * \r\n");
      out.write(" * @param {Object} lCustomerID\r\n");
      out.write(" * @param {Object} nType 激活为1\r\n");
      out.write(" */\r\n");
      out.write("function activeOrNotCustomer(lCustomerID, nType)\r\n");
      out.write("{\r\n");
      out.write("\tvar strURL = \"/mpf/customer/customerActive.do?nType=\" + nType;\r\n");
      out.write("\t\r\n");
      out.write("\tif (lCustomerID > 0)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrURL += \"&lCustomerID=\" + lCustomerID;\r\n");
      out.write("\t}\r\n");
      out.write("\telse\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\":checked.class_customer\").each(function(i, item){\r\n");
      out.write("\t\t\tstrURL += \"&lCustomerID=\" + item.value;\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("           url:strURL,\r\n");
      out.write("           type:\"post\",\r\n");
      out.write("           data:{},\r\n");
      out.write("           dataType:\"text\",\r\n");
      out.write("           async: false,\r\n");
      out.write("           success:function(responseText)\r\n");
      out.write("           {\t           \r\n");
      out.write("\t\t\tif(responseText == \"success\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(\"操作成功！\");\r\n");
      out.write("\t\t\t\t$(\"#customerList\").datagrid('reload'); // 重新加载。\r\n");
      out.write("\t\t\t}else\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(responseText);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("           }\r\n");
      out.write("        });\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function fixWidth(percent)\r\n");
      out.write("{\r\n");
      out.write("    return (document.body.clientWidth ) * percent; //这里你可以自己做调整\r\n");
      out.write("}\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function getSelected(){\r\n");
      out.write("\tvar selected = $('#customerList').datagrid('getSelected');\r\n");
      out.write("\t\r\n");
      out.write("\tif (selected){\r\n");
      out.write("\t\talert(selected.code+\":\"+selected.name+\":\"+selected.addr+\":\"+selected.col4);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 列出所有项目。\r\n");
      out.write(" */\r\n");
      out.write("function authorizationProjectList(lUserID)\r\n");
      out.write("{\r\n");
      out.write("\t$(\"#show_customer\").hide();\r\n");
      out.write("\t$(\"#show_project\").show();\r\n");
      out.write("\t$('#projectList').datagrid({\r\n");
      out.write("\t\ttitle:\"项目列表\",\r\n");
      out.write("\t\tloadMsg:'加载数据...',\r\n");
      out.write("\t\t// pageList:[10,50,100],\r\n");
      out.write("\t\tnowrap: true,\r\n");
      out.write("\t\tautoRowHeight: false,\r\n");
      out.write("\t\tstriped: true,\r\n");
      out.write("\t\tcollapsible:true,\r\n");
      out.write("\t\turl:'/mpf/users/loadUsersProject.do?lUserID=' + lUserID,\r\n");
      out.write("\t\tsortName: 'title',\r\n");
      out.write("\t\tsortOrder: 'desc',\r\n");
      out.write("\t\tremoteSort: false,\r\n");
      out.write("\t\tidField:'title',\r\n");
      out.write("\t\tonLoadError:function (data)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\talert(\"加载失败！请稍后重试！\");\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tsingleSelect:true,\r\n");
      out.write("\t\tfrozenColumns:[[\r\n");
      out.write("\t\t                {field:'strPname',title:\"项目名称\",align:\"left\",width:500,\r\n");
      out.write("\t\t                \tformatter:function(value,rec)\r\n");
      out.write("\t\t                \t{\r\n");
      out.write("\t\t                \t\tvar strHTML = \"<input type='checkbox' class='class_project' value='\" + rec.lId + \"'>&nbsp;&nbsp;\";\r\n");
      out.write("\t\t                \t\t\r\n");
      out.write("\t\t                \t\treturn strHTML + rec.strPname;\r\n");
      out.write("\t\t                \t}\r\n");
      out.write("\t\t                }\r\n");
      out.write("\t\t\t\t\t]],\r\n");
      out.write("\t\tcolumns:[[\r\n");
      out.write("\t\t\t{field:'strCreateTime',title:'项目负责人',width:500,align:\"center\",\r\n");
      out.write("\t\t\t\tformatter:function(value,rec){\r\n");
      out.write("\t\t\t\t\treturn \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objUsers.strName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t]],\r\n");
      out.write("\t\trownumbers:true,\r\n");
      out.write("\t\ttoolbar:[\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\ttext:'<input type=\"checkbox\" name=\"checkbox\" value=\"checkbox\" onclick=\"checkAllProject(this);\" style=\"margin-left:20px;\">&nbsp;全选'\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\ttext:'取消授权',\r\n");
      out.write("\t\t\ticonCls:'icon-save',\r\n");
      out.write("\t\t\thandler:function(){\r\n");
      out.write("\t\t\t\tvar row = $('#projectList').datagrid('getSelected');\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif ($(\":checked.class_project\") != null && $(\":checked.class_project\").length > 0)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t// 项目授权。\r\n");
      out.write("\t\t\t\t\tcancleProjectAuthorization(lUserID, -1);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse if (row != null && row.lId > 0)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t// 项目授权。\r\n");
      out.write("\t\t\t\t\tcancleProjectAuthorization(lUserID, row.lId);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\telse\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t$.messager.alert('消息提示',\"<p style='font-size:14px;'>请选择您要取消授权的项目！</p>\", \"info\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\ttext:'返回',\r\n");
      out.write("\t\t\ticonCls:'icon-edit',\r\n");
      out.write("\t\t\thandler:function(){\r\n");
      out.write("\t\t\t\t$(\"#customerList\").datagrid('reload'); // 重新加载。\r\n");
      out.write("\t\t\t\t$(\"#show_customer\").show();\r\n");
      out.write("\t\t\t\t$(\"#show_project\").hide();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 取消授权。\r\n");
      out.write(" */\r\n");
      out.write("function cancleProjectAuthorization(lUserID,lProjectID)\r\n");
      out.write("{\r\n");
      out.write("\tvar nCount = 0;\r\n");
      out.write("\tvar strURL;\r\n");
      out.write("\t\r\n");
      out.write("\tstrURL = \"/mpf/users/cancleProjectAuthorization.do?usersID=\" + lUserID;\r\n");
      out.write("\t\r\n");
      out.write("\tif (lProjectID > 0)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tstrURL += \"&projectID=\" + lProjectID;\r\n");
      out.write("\t}\r\n");
      out.write("\telse\r\n");
      out.write("\t{\r\n");
      out.write("\t\t$(\":checked.class_project\").each(function(i, item){\r\n");
      out.write("\t\t\tstrURL += \"&projectID=\" + item.value;\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tif (!confirm(\"确认要取消授权吗？\"))\r\n");
      out.write("\t{\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t$.ajax(\r\n");
      out.write("\t{\r\n");
      out.write("        url:strURL,\r\n");
      out.write("        type:\"post\",\r\n");
      out.write("        data:{},\r\n");
      out.write("        dataType:\"text\",\r\n");
      out.write("        async: false,\r\n");
      out.write("        success:function(responseText)\r\n");
      out.write("        {\t           \r\n");
      out.write("\t\t\tif(responseText == \"success\")\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(\"取消成功！\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(responseText);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\t$(\"#show_customer\").slideDown();\r\n");
      out.write("\t$(\"#show_project\").slideUp();\r\n");
      out.write("\t$('#customerList').datagrid(\"reload\");\r\n");
      out.write("}\r\n");
      out.write("// easy UI end\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div style=\"margin-top:0px;margin-left:2px;\">\r\n");
      out.write("\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"index_main_div1\"  style=\"margin-top:0px;\">\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t\t<td height=\"21\" background=\"/commons/image/index_main_div_titleBg.gif\"><img style=\"margin-left:5px;\" src=\"/commons/image/index_main_div_left.gif\" width=\"6\" height=\"2\" align=\"absmiddle\">&nbsp;<span style=\"font-weight:bold;font-size:12px;\">商户列表</span></td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t\r\n");
      out.write("\t<div style=\"margin: 20px;\">\r\n");
      out.write("\t\t<span style=\"font-size:20px;font-weight:bolder;\">商户注册地址：</span>\r\n");
      out.write("\t\t<span><a href=\"");
      out.print(basePath );
      out.write("jsp/front/page_authorize.jsp\"  style=\"font-weight:bolder;font-size:20px;border: 2px solid green;\" title=\"进入\" target=\"_blank\">");
      out.print(basePath );
      out.write("jsp/front/page_authorize.jsp</a></span>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"show_customer\">\t\r\n");
      out.write("\t\t<table id=\"customerList\"></table>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"show_project\" style=\"display:none;\">\r\n");
      out.write("\t\t<table id=\"projectList\"></table>\r\n");
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
}