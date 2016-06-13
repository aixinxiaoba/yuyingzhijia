<%@ page contentType="text/html;charset=utf-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/commons/jsp/taglibs.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Cache-Control" content="max-age=0">
<META http-equiv="Expires" content="0">
<META http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<META name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0">
<html>
<head>
	<title>系统公共错误页面</title>
	<link rel="shortcut icon" href="/favicon.ico?version=3">
	<link href="${ctx}/commons/css/common.css" rel="stylesheet" type="text/css" media="all" />
</head>
<script type="text/javascript">
var sUserAgent = navigator.userAgent.toLowerCase();
var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
var bIsMidp = sUserAgent.match(/midp/i) == "midp";
var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
var bIsAndroid = sUserAgent.match(/android/i) == "android";
var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM){
	
}
</script>
<body style="text-align:center;">

<div id="pc" class="error" style="border: 0px solid red;">
	<div class="mat80 errorp">消息提示</div>
	<div class="mt20 errorp">
		<c:if test="${empty sessionScope.errText}">出现了错误！请刷新重试，如果您还不能解决请联系管理员</c:if>
		<c:if test="${not empty sessionScope.errText}">${sessionScope.errText}</c:if>
	</div>
	<div class="mt20 errorp">
		<img src="${ctx }/commons/images/btn1_03.jpg" style="cursor: pointer;" onclick="javascript:location.href='/'"/>&nbsp;
		<img src="${ctx }/commons/images/btn2_03.jpg" style="cursor: pointer;" onclick="javascript:history.back();" />
	</div>
</div>


</body>
</html>