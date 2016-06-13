<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String strRefer = request.getHeader("Referer");
String strThisPage = request.getRequestURL().toString();
%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">  
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>${objProject.strPname}</title>  
    <link rel="shortcut icon" href="/favicon.ico?version=3">
<link rel="icon" type="image/gif" href="animated_favicon1.gif">
<%--<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui_user_defined.css" />--%>
<link type="text/css" rel="stylesheet" href="/commons/easyui/1.4.2/easyui.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/1.4.2/mobile.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/1.4.2/icon.css" />



<script type="text/javascript" src="/commons/easyui/1.4.2//jquery.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/commons/easyui/1.4.2/jquery.easyui.mobile.js"></script>

<%--    <link rel="stylesheet" type="text/css" href="../../themes/metro/easyui.css">  --%>
<%--    <link rel="stylesheet" type="text/css" href="../../themes/mobile.css">  --%>
<%--    <link rel="stylesheet" type="text/css" href="../../themes/icon.css">  --%>
<%--    <script type="text/javascript" src="../../jquery.min.js"></script>  --%>
<%--    <script type="text/javascript" src="../../jquery.easyui.min.js"></script> --%>
<%--    <script type="text/javascript" src="../../jquery.easyui.mobile.js"></script> --%>
<script>

$(document).ready(function() {
	setReadNum();
	recordVisitLog();
})

// 设置访问日志。
function setReadNum()
{
	var strURL = "/front/index/setReadNum.do";
	
	$.ajax(
	{
        url:strURL,
        type:"post",
        data:{"newsID":${objNews.lId}},
        dataType:"text",
        async: false,
        success:function(responseText)
        {	           
			
        }
    });
}


// 设置访问日志。
function recordVisitLog()
{
	var strURL = "/front/index/visitLog.do";
	
	$.ajax(
	{
        url:strURL,
        type:"post",
        data:{"refer":"<%=strRefer%>","thisPage":"<%=strThisPage%>"},
        dataType:"text",
        async: false,
        success:function(responseText)
        {	           
			
        }
    });
}

</script>
</head>
<body>
	<div class="easyui-navpanel" style="background: #E3EDCD;">
		<header>
			<div class="m-toolbar">
				<span class="m-title">${objProjectMenu.strMenuName}</span>
			</div>
		</header>
		<ul class="m-list" style="font-size:20px;">
			<s:if test="objGPagination != null && objGPagination.rows.size > 0">
	            <s:iterator value="objGPagination.rows" id="news" status="mystatus">
					<li>
						
						 <a href="/front/index/newsRead.do?newsID=<s:property value='lId'/>" data-options="animation:'slide',direction:'left'"><s:property value="strLongTitle"/></a>
					</li>
				</s:iterator>
			</s:if>
		</ul>
		<%@ include file="../../common/m_foot.jsp" %>
	</div>
	
	
</body>	
</html>
