<%@ page contentType="text/html;charset=utf-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/commons/jsp/taglibs.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
	<title>注册成功</title>
	<link href="${ctx}/commons/css/common.css" rel="stylesheet" type="text/css" media="all" />
	
	<script>
var i = 4;
function setTimes(){
	i--;
	if (i==0)
	{
		location.href='/jsp/templateOne/index.jsp?projectKey=${param.projectKey}';
	}
	document.getElementById("times").innerHTML=i;
	window.setTimeout("setTimes()",1000);
}

window.onload = function(){
	setTimes();
}
	
	</script>
</head>

<body style="text-align:center;">

<div class="commonMsg" style="border: 0px solid red;">
	<div class="mat80 errorp"></div>
	<div class="mt20 errorp"> 注册成功！<span id="times">3</span>s后将跳转到<a href="/jsp/templateOne/index.jsp?projectKey=${param.projectKey}">首页</a></div>
	<!-- 
	<div class="mt20 errorp" style="margin-top:20px;">
		<img src="${ctx }/commons/images/btn1_03.jpg" style="cursor: pointer;" onclick="javascript:location.href='/jsp/templateOne/index.jsp?projectKey=${param.projectKey}';"/>
	</div>
	 -->
</div>
</body>
</html>