package org.apache.jsp.jsp.MPlatform;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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
      response.setContentType("text/html; charset=utf-8");
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
      out.write("<title>系统界面</title>\r\n");
      out.write("\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/_main.css\" rel=\"stylesheet\" />\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/common.css\" rel=\"stylesheet\" />\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/general.css\" rel=\"stylesheet\" />\r\n");
      out.write("<link type=\"text/css\" href=\"/commons/css/calendar.css\" rel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/jquery/jquery-1.3.2.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/popup.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/popup4nextTDate.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/popup4exExcel.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/popup4Linkman.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/cal.js\" ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/commons/js/popup4exExcel_sub.js\" ></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var isHide = false;\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 动态显示时间。\r\n");
      out.write(" */\r\n");
      out.write("function dynamicShowTime()\r\n");
      out.write("{\r\n");
      out.write("   var allDay = new Array( \"星期日\", \"星期一\",  \"星期二\", \"星期三\", \"星期四\", \"星期五\", \"星期六\");\r\n");
      out.write("   //获得显示时间的div\r\n");
      out.write("   var strTimeInfo = document.getElementById('showtime');\r\n");
      out.write("   var now=new Date();\r\n");
      out.write("   \r\n");
      out.write("   //替换div内容 \r\n");
      out.write("   strTimeInfo.innerHTML = \"现在是\" + now.getFullYear() + \"年\" + (now.getMonth() + 1) + \"月\" + now.getDate()  + \"日&nbsp;&nbsp;\" + allDay[now.getDay()] + \"&nbsp;&nbsp;\" + now.getHours() + \"时\" + now.getMinutes() + \"分\" + now.getSeconds() + \"秒\";\r\n");
      out.write("   //等待一秒钟后调用time方法，由于settimeout在time方法内，所以可以无限调用\r\n");
      out.write("   setTimeout(dynamicShowTime,1000);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("$(function(){\r\n");
      out.write("\t// 显示时间。\r\n");
      out.write("\tdynamicShowTime();\r\n");
      out.write("\t\r\n");
      out.write("\t$(\".subTreeTable td\").bind(\"mouseover\",function(){\r\n");
      out.write("\t\t$(this).removeClass(\"mouseOutClass\");\r\n");
      out.write("\t\t$(this).addClass(\"mouseOverClass\");\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(\".subTreeTable td\").bind(\"mouseout\",function(){\r\n");
      out.write("\t\t$(this).removeClass(\"mouseOverClass\");\r\n");
      out.write("\t\t$(this).addClass(\"mouseOutClass\");\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(\".center_control\").bind(\"click\",function(){\r\n");
      out.write("\t\r\n");
      out.write("\t\tif(isHide == false){\r\n");
      out.write("\t\t\t$(\"#leftT\").hide();\r\n");
      out.write("\t\t\tisHide = true;\r\n");
      out.write("\t\t\t$(\"#center_control_img\").attr(\"src\",\"/commons/image/index_center_control1.jpg\");\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$(\"#leftT\").show();\r\n");
      out.write("\t\t\tisHide = false;\r\n");
      out.write("\t\t\t$(\"#center_control_img\").attr(\"src\",\"/commons/image/index_center_control.jpg\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfor(var i = 1; i <=8; i++){\r\n");
      out.write("\t\t$(\".left_treeControl\" + i).attr(\"src\",\"/commons/image/left_tree_close.gif\");\r\n");
      out.write("\t\t$(\".left_treeControl\" + i).bind(\"click\",function(){\r\n");
      out.write("\t\t\tvar indexNum = $(this).attr(\"class\").substring($(this).attr(\"class\").length-1,$(this).attr(\"class\").length);\r\n");
      out.write("\t\t\tif($(this).attr(\"src\").indexOf(\"open\") != -1){\r\n");
      out.write("\t\t\t\tcloseAll();\r\n");
      out.write("\t\t\t\t$(this).attr(\"src\",\"/commons/image/left_tree_close.gif\");\r\n");
      out.write("\t\t\t\t$(\".left_tree\" + indexNum).show();\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t$(this).attr(\"src\",\"/commons/image/left_tree_open.gif\");\r\n");
      out.write("\t\t\t\t$(\".left_tree\" + indexNum).hide();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t})\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tcloseAll();\r\n");
      out.write("\t// 显示第一个菜单。\r\n");
      out.write("\tfor(var i = 1; i <=8; i++){\r\n");
      out.write("\t\t$(\".left_tree\" + i).show();\r\n");
      out.write("\t}\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * 关闭所有菜单。\r\n");
      out.write(" */\r\n");
      out.write("function closeAll(){\r\n");
      out.write("\tfor(var i = 1; i <=8; i++){\r\n");
      out.write("\t\t$(\".left_treeControl\" + i).attr(\"src\",\"/commons/image/left_tree_open.gif\");\r\n");
      out.write("\t\t$(\".left_tree\" + i).hide();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body STYLE='OVERFLOW: HIDDEN; OVERFLOW-Y: HIDDEN' onload=\"\">\r\n");
      out.write("<table width=\"100%\" height=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td height=\"76\" valign=\"top\">\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td colspan=\"3\" class=\"topTd\"><img src=\"/commons/image/logo.gif\" width=\"130\" height=\"64\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=\"406\" class=\"topLeft\" height=\"30\">\r\n");
      out.write("\t\t\t\t\t<span style=\"color: #444444;font-size:13px;\">欢迎您 ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objUsers.strName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t<span style=\"color: #0871ee\">&nbsp;&nbsp;技术支持（QQ:376289635）</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<span><a target=\"_blank\" href=\"http://shang.qq.com/wpa/qunwpa?idkey=bf25712c54ca049803ee847e1a47f45bd5cfed7f80d1e8b47e4bd9d84da4f632\"><img border=\"0\" src=\"http://pub.idqqimg.com/wpa/images/group.png\" alt=\"PM官方交流群\" title=\"PM官方交流群\"></a></span>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td width=\"17\">\r\n");
      out.write("\t\t\t\t\t<img src=\"/commons/image/topButLine.gif\" width=\"36\" height=\"30\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td width=\"700\" class=\"topRight\"><img src=\"/commons/image/icon/gzt.gif\" width=\"16\" height=\"15\" align=\"absmiddle\">\r\n");
      out.write("\t\t\t\t\t<a href=\"/jsp/MPlatform/workdesk.jsp\" target=\"right_window\" style=\"color:white;font-size:13px;\">工作台</a>| <img src=\"/commons/image/icon/wdxx.gif\" width=\"16\" height=\"12\"> \r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<a href=\"/jsp/MPlatform/myInformation/information_show.jsp\" target=\"right_window\"   style=\"color:white;font: 13px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;list-style:none;\">我的消息(0)</a>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t| <img src=\"/commons/image/icon/wdzh.gif\" width=\"16\" height=\"16\" align=\"absmiddle\">\r\n");
      out.write("\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t<a href=\"/jsp/MPlatform/myAccount/account_show.jsp\" target=\"right_window\"  style=\"color:white;font: 13px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;list-style:none;\">我的账户</a>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t| <img src=\"/commons/image/icon/bz.gif\" width=\"16\" height=\"15\" align=\"absmiddle\"> \r\n");
      out.write("\t\t\t\t\t<!-- 帮助  -->\r\n");
      out.write("\t\t\t\t\t<a href=\"/jsp/MPlatform/help.jsp\" target=\"right_window\"  style=\"color:white;font: 13px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;list-style:none;\">帮助</a>\r\n");
      out.write("\t\t\t\t\t| <img src=\"/commons/image/icon/gy.gif\" width=\"16\" height=\"16\" align=\"absmiddle\"> \r\n");
      out.write("\t\t\t\t\t<!-- 关于 -->\r\n");
      out.write("\t\t\t\t\t<a href=\"/jsp/MPlatform/aboutUs.jsp\" target=\"right_window\"  style=\"color:white;font: 13px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;list-style:none;\">关于</a>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t| <img src=\"/commons/image/icon/tc.gif\" width=\"10\" height=\"10\"\talign=\"absmiddle\">\r\n");
      out.write("\t\t\t\t\t<span style=\"cursor: pointer\" onclick=\"javascript:location.href='/login/destroyLogin.do'\">安全退出</span> \r\n");
      out.write("\t\t\t\t\t<span id=\"showtime\" style=\"margin-left:10px;font-size:13px;\"></span>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td height=\"6\"></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td valign=\"top\">\r\n");
      out.write("\t\t<table width=\"100%\" height=\"100%\" border=\"0\" cellspacing=\"0\"\r\n");
      out.write("\t\t\tcellpadding=\"0\" id=\"left_tree_main\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=\"159\" height=\"100%\" valign=\"top\" id=\"leftT\">\r\n");
      out.write("\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td height=\"27\" colspan=\"2\" align=\"center\" background=\"/commons/image/left_main_bg.gif\"><span style=\"color: #004e7b; font-size: 14px; font-weight: bold;\">操作菜单</span></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"companyTitle\">\r\n");
      out.write("\t\t\t\t\t\t\t<tr  style=\"cursor: pointer;\" class=\"left_treeControl1\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"80%\" class=\"f_td\"><img align=\"absmiddle\"\tsrc=\"/commons/image/left_tree1.gif\" width=\"17\" height=\"20\"> 客户管理</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"20%\" class=\"f_td\"><img src=\"/commons/image/left_tree_open.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr class=\"left_tree1\">\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"subTreeTable\" id=\"companyTable\">\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<img align=\"absmiddle\" src=\"/commons/image/left_tree1_2.gif\" width=\"16\" height=\"17\"> \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t <a href=\"/jsp/MPlatform/customer/allCustomer.jsp\" class=\"nav\" target=\"right_window\">所有客户</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t  ");
      if (_jspx_meth_s_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<img align=\"absmiddle\" src=\"/commons/image/left_tree1_2.gif\" width=\"16\" height=\"17\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_s_if_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t ");
      if (_jspx_meth_s_else_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<tr style=\"cursor: pointer;\" class=\"left_treeControl2\">\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"80%\" class=\"f_td\"><img align=\"absmiddle\" src=\"/commons/image/left_tree5.gif\" width=\"17\" height=\"20\"> 项目管理</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"20%\" class=\"f_td\"><img src=\"/commons/image/left_tree_open.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr class=\"left_tree2\">\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"subTreeTable\">\r\n");
      out.write("\t\t\t\t\t\t\t\t ");
      if (_jspx_meth_s_if_2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/mpf/project/preProjectAdd.do\" class=\"nav\" target=\"right_window\"> 新建项目 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/project/projectList.jsp\" class=\"nav\" target=\"right_window\"> 综合设置 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/project/quickSearch.jsp\" class=\"nav\" target=\"right_window\"> 快速查询 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<tr style=\"cursor: pointer;\" class=\"left_treeControl7\">\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"80%\" class=\"f_td\"><img align=\"absmiddle\" src=\"/commons/image/left_tree5.gif\" width=\"17\" height=\"20\"> 系统设置</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"20%\" class=\"f_td\"><img src=\"/commons/image/left_tree_open.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr class=\"left_tree7\">\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"subTreeTable\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/users/passwordModify.jsp\" class=\"nav\" target=\"right_window\"> 修改密码 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<!-- \r\n");
      out.write("\t\t\t\t\t\t<tr style=\"cursor: pointer;\" class=\"left_treeControl3\">\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"80%\" class=\"f_td\"><img align=\"absmiddle\" src=\"/commons/image/email.png\" width=\"17\" height=\"20\"> 邮件管理</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"20%\" class=\"f_td\"><img src=\"/commons/image/left_tree_open.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr class=\"left_tree3\">\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"subTreeTable\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_12.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/emailManage/projectList.jsp\" class=\"nav\" target=\"right_window\"> 邮件阅读 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t -->\r\n");
      out.write("\t\t\t\t\t\t<!-- \r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/email_edit.png\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/emailManage/email_edit.jsp\" class=\"nav\" target=\"right_window\"> 邮件编写 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t -->\r\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_s_if_3(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<tr style=\"cursor: pointer;\" class=\"left_treeControl6\">\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"80%\" class=\"f_td\"><img align=\"absmiddle\" src=\"/commons/image/left_tree5.gif\" width=\"17\" height=\"20\"> 短网址制作 </td>\r\n");
      out.write("\t\t\t\t\t\t\t<td width=\"20%\" class=\"f_td\"><img src=\"/commons/image/left_tree_open.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr class=\"left_tree6\">\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"subTreeTable\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_12.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/shortUrl/make.jsp\" class=\"nav\" target=\"right_window\"> 马上开始 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_12.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/shortUrl/ip_statistics.jsp\" class=\"nav\" target=\"right_window\"> IP统计 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_12.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/shortUrl/ip_return.jsp\" class=\"nav\" target=\"right_window\"> 查看原网址 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<tr style=\"cursor: pointer;\" class=\"left_treeControl8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"80%\" class=\"f_td\"><img align=\"absmiddle\" src=\"/commons/image/left_tree5.gif\" width=\"17\" height=\"20\"> 常用操作</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"20%\" class=\"f_td\"><img src=\"/commons/image/left_tree_open.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr class=\"left_tree8\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"subTreeTable\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/data/dataCapture.jsp\" class=\"nav\" target=\"right_window\"> 数据抓取 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/pageStatic.jsp\" class=\"nav\" target=\"right_window\"> 移动端页面静态化 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/pcStatic.jsp\" class=\"nav\" target=\"right_window\"> PC端页面静态化 </a></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td width=\"8\" class=\"center_control\" valign=\"middle\"><img src=\"/commons/image/index_center_control.jpg\" id=\"center_control_img\"></td>\r\n");
      out.write("\t\t\t\t<td valign=\"top\" style='border:0px solid #73c0e0;'>\r\n");
      out.write("\t\t\t\t\t<iframe src=\"/jsp/MPlatform/workdesk.jsp\" width=\"100%\" height=\"100%\" frameborder=\"0\" name=\"right_window\"></iframe>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("<!-- \r\n");
      if (_jspx_meth_s_if_4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write(" -->\r\n");
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
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_0 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_0.setPageContext(_jspx_page_context);
    _jspx_th_s_if_0.setParent(null);
    _jspx_th_s_if_0.setTest("#session.objUsers.nCurrentLevel == 1");
    int _jspx_eval_s_if_0 = _jspx_th_s_if_0.doStartTag();
    if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_0.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t<img align=\"absmiddle\" src=\"/commons/image/left_tree1_2.gif\" width=\"16\" height=\"17\"> \r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t\t <a href=\"/jsp/MPlatform/customer/multiProCustomerList.jsp\" class=\"nav\" target=\"right_window\">多项目客户管理</a>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_1 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_1.setPageContext(_jspx_page_context);
    _jspx_th_s_if_1.setParent(null);
    _jspx_th_s_if_1.setTest("#session.objUsers.nCurrentLevel == 1");
    int _jspx_eval_s_if_1 = _jspx_th_s_if_1.doStartTag();
    if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_1.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t <a href=\"/jsp/MPlatform/customer/project_type_List.jsp\" class=\"nav\" target=\"right_window\">客户管理</a>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t ");
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
        out.write("\t\t\t\t\t\t\t\t\t <a href=\"/jsp/MPlatform/customer/projectList.jsp\" class=\"nav\" target=\"right_window\">客户管理</a>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t ");
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

  private boolean _jspx_meth_s_if_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_2 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_2.setPageContext(_jspx_page_context);
    _jspx_th_s_if_2.setParent(null);
    _jspx_th_s_if_2.setTest("#session.objUsers.nCurrentLevel == 1");
    int _jspx_eval_s_if_2 = _jspx_th_s_if_2.doStartTag();
    if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_2 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_2.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_2.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/system/project_type/project_type_List.jsp\" class=\"nav\" target=\"right_window\"> 项目类型设置 </a></td>\r\n");
        out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_if_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_3 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_3.setPageContext(_jspx_page_context);
    _jspx_th_s_if_3.setParent(null);
    _jspx_th_s_if_3.setTest("#session.objUsers.nCurrentLevel == 1");
    int _jspx_eval_s_if_3 = _jspx_th_s_if_3.doStartTag();
    if (_jspx_eval_s_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_3 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_3.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_3.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t<!-- \r\n");
        out.write("\t\t\t\t\t\t\t<tr style=\"cursor: pointer;\" class=\"left_treeControl4\">\r\n");
        out.write("\t\t\t\t\t\t\t\t<td width=\"80%\" class=\"f_td\"><img align=\"absmiddle\" src=\"/commons/image/left_tree5.gif\" width=\"17\" height=\"20\"> 新闻管理</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td width=\"20%\" class=\"f_td\"><img src=\"/commons/image/left_tree_open.gif\" width=\"15\" height=\"15\"></td>\r\n");
        out.write("\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t<tr class=\"left_tree4\">\r\n");
        out.write("\t\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
        out.write("\t\t\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n");
        out.write("\t\t\t\t\t\t\t\t\tclass=\"subTreeTable\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/news/newsList.jsp\" class=\"nav\" target=\"right_window\"> 新闻列表 </a></td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/news/newsAdd.jsp\" class=\"nav\" target=\"right_window\"> 发布新闻 </a></td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t</table>\r\n");
        out.write("\t\t\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t -->\r\n");
        out.write("\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t<tr style=\"cursor: pointer;\" class=\"left_treeControl5\">\r\n");
        out.write("\t\t\t\t\t\t\t\t<td width=\"80%\" class=\"f_td\"><img align=\"absmiddle\" src=\"/commons/image/left_tree5.gif\" width=\"17\" height=\"20\"> 商户管理</td>\r\n");
        out.write("\t\t\t\t\t\t\t\t<td width=\"20%\" class=\"f_td\"><img src=\"/commons/image/left_tree_open.gif\" width=\"15\" height=\"15\"></td>\r\n");
        out.write("\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t<tr class=\"left_tree5\">\r\n");
        out.write("\t\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
        out.write("\t\t\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"\r\n");
        out.write("\t\t\t\t\t\t\t\t\tclass=\"subTreeTable\">\r\n");
        out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\t<td><img align=\"absmiddle\" src=\"/commons/image/left_tree5_1.gif\" width=\"16\" height=\"16\"><a href=\"/jsp/MPlatform/users/usersList.jsp\" class=\"nav\" target=\"right_window\"> 商户列表 </a></td>\r\n");
        out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t\t</table>\r\n");
        out.write("\t\t\t\t\t\t\t\t</td>\r\n");
        out.write("\t\t\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t\t\t\t<!--  -->\r\n");
        out.write("\t\t\t\t\t\t");
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

  private boolean _jspx_meth_s_if_4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  s:if
    org.apache.struts2.views.jsp.IfTag _jspx_th_s_if_4 = (org.apache.struts2.views.jsp.IfTag) _jspx_tagPool_s_if_test.get(org.apache.struts2.views.jsp.IfTag.class);
    _jspx_th_s_if_4.setPageContext(_jspx_page_context);
    _jspx_th_s_if_4.setParent(null);
    _jspx_th_s_if_4.setTest("#session.objUsers.nCurrentLevel == 1");
    int _jspx_eval_s_if_4 = _jspx_th_s_if_4.doStartTag();
    if (_jspx_eval_s_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_s_if_4 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_s_if_4.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_s_if_4.doInitBody();
      }
      do {
        out.write("\r\n");
        out.write("\t\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\t");
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
}
