<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>育婴之家网_育婴知识分享平台</title>
<META name="Keywords"
	content="育婴之家，儿童之家，育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
<META name="description"
	content="育婴之家是一个分享交流育儿知识经验的 平台，我们致力于将最好的育儿知识，通过我们这个平台知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">
<!-- 汽车之家css -->
<link href="/commons/css/autohome/com.css" rel="stylesheet" />
<!-- end 汽车之家 -->
<LINK rel="stylesheet" href="/commons/css/tianya/main.css" type="text/css">
<link rel="stylesheet" href="/commons/css/tianya/index2.css">
<SCRIPT src="/m/common/gm/jquery.js"></SCRIPT>
<script type="text/javascript">
$(function(){
	$("#rec_div").load("/static/m/p/${objSubProjectMenu.lId }_1.html");
	$("#j-slider-home").load("/static/m/scroll/l2/${objSubProjectMenu.lId}.html");
});

function nextPage(data)
{
	$("#type_div").load("/static/m/p/" + data + ".html");
}
</script>
</head>
<body>
	<input type="hidden" value="<%=basePath%>" id="basePath"/>
	<div id="j-homepage" class="m-doc">
		<!-- header -->
		<div class="ty-m-nav">
			<header class="m-header" id="j-header">
				<div class="m-bar f-cf">
					<ul class="m-bar-center" id="ty_logo">
						<li class="logo"><span class="" style="font-size:16px;">${objSubProjectMenu.strMenuName }</span></li>
					</ul>
				</div>
			</header>
		</div>
		<!-- For FIS -->
		<!-- main 头部图片循环显示 -->
		<div class="m-main">
			 <!-- 滚动图片显示 -->
			<div class="u-slider u-slider-home">
				<div class="sliderbox gg gg-item" id="j-slider-home" ></div>
			</div>
			<div class="u-box">
				<a name="type"></a>
				<div class="see-wrap">
					<div class="u-tab tab-left-off">
						<div class="tab-wrap" id="tab_wrap" style="overflow: hidden;">
							<ul class="f-cf" style="left: 0px; top: 0px; width: 2030px; position: absolute;" id="f-cf">
								<!-- 
								<li class="cur" style="width: 145px;" data-c='newest/${objSubProjectMenu.lId }_1.html'>
									<span class="">最新</span>
								</li>
								<li style="width: 145px;" data-c='scroll/l2/${objSubProjectMenu.lId}.html'>
									<span  class="">排行</span>
								</li>
								 -->
								<li class="cur" style="width: 145px;" data-c='p/${objSubProjectMenu.lId }_1.html'>
									<span  class="">全部</span>
								</li>
								
							</ul>
						</div>
					</div>
					<div id="type_div"></div>
					<div class="u-bd" id="rec_div"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="http://static.tianyaui.com/global/ty2.0/TY_m_2.0.js"></script>
	<script src="/commons/js/tianya/main.js"></script>
	<script src="/commons/js/tianya/index2.js?20160414"></script>
	<script>
		TY.m.nav.init({
			'app_str' : "bbs",
			navFromHtml : true,
			navOpen : true,
			showHideIcon : false,
			moveHide : false,
			clickDom2Hide : false
		})
	</script>
	
</body>

</html>