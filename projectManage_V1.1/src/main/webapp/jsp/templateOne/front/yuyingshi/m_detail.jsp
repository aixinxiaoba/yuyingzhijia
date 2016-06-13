<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">  
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>${objNews.strTitle}-育婴知识分享平台</title>
    <link rel="shortcut icon" href="/favicon.ico?version=3">
	<link type="text/css" rel="stylesheet" href="/commons/easyui/1.4.2/easyui.css" />
	<link type="text/css" rel="stylesheet" href="/commons/easyui/1.4.2/mobile.css" />
	<link type="text/css" rel="stylesheet" href="/commons/easyui/1.4.2/icon.css" />
	<script type="text/javascript" src="/commons/easyui/1.4.2//jquery.min.js" ></script>
	<script type="text/javascript" src="/commons/easyui/1.4.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/commons/easyui/1.4.2/jquery.easyui.mobile.js"></script>
</HEAD>
<BODY>
<div id="p2" class="easyui-navpanel">
	<header>
		<div class="m-toolbar" >
			<div class="m-left">
				<a href="/front/index/menuShow.do?lProjectMenuID=${objNews.objProjectMenu.lId}" class="easyui-linkbutton m-back" data-options="plain:true,outline:true">列表</a>
			</div>
			<div class="m-title">${objNews.strTitle}</div>
		</div>
	</header>
	<div style="padding:10px;font-size:20px;">
		<p>${objNews.strContent}</p>
	</div>
	
	<%@ include file="../../../common/m_foot.jsp" %>
</div>

<!-- 阅读量 -->
<script>$.ajax( { url:"/front/index/setReadNum.do", type:"post",  data:{"newsID":"${objNews.lId}"}, dataType:"text",  async: true });</script>
<!-- 访问日志 -->
<script type="text/javascript">$.ajax({  url:"/front/index/visitLog.do",  type:"post", data:{"refer":document.referrer,"thisPage":location.href },  dataType:"text", async: true});</script>
</BODY>
</HTML>

