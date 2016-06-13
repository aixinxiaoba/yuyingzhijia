<%@ page contentType="text/html;charset=utf-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/commons/jsp/taglibs.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
	<title>消息提示</title>
			<link rel="shortcut icon" href="/favicon.ico?version=3">
	
	<link href="${ctx}/commons/css/common.css" rel="stylesheet" type="text/css" media="all" />
</head>

<body style="text-align:center;">

<div class="commonMsg" style="border: 0px solid red;">
	<div class="mat80 errorp">消息提示</div>
	<div class="mt20 errorp">
		<c:if test="${empty sessionScope.errText}"></c:if>
		<c:if test="${not empty sessionScope.errText}">${sessionScope.errText}</c:if>
	</div>
	<div class="mt20 errorp">
		<img src="${ctx }/commons/images/btn1_03.jpg" style="cursor: pointer;" onclick="javascript:location.href='/jsp/templateOne/index.jsp?projectKey=${param.projectKey}'"/>&nbsp;
		<img src="${ctx }/commons/images/btn2_03.jpg" style="cursor: pointer;" onclick="javascript:history.back();" />
	</div>
<!-- 
<div class="mat80 errorp">对不起！报名时间已经结束，您不能再进行报名了！</div>
<div class="mt20 errorp">结束时间为：2013-10-10</div>
<div class="mt20 errorp">如果您已经注册，您可以：<a href="#">登录个人管理平台</a></div>
 -->
</div>
</body>
</html>