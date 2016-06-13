<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>育婴之家网站导航</title>
<META name="Keywords"
	content="育婴之家，儿童之家，育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
<META name="description"
	content="育婴之家是一个分享交流育儿知识经验的 平台，我们致力于需求最好的育儿知识，通过我们这个平台知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no">
<LINK rel="stylesheet" href="/commons/css/mobile.css"  type="text/css">
<SCRIPT src="/m/common/gm/jquery.js"></SCRIPT>
<style>
.t-sitemap-menu {
	position: fixed;
	right: 0px;
	left: 0px;
	right: 0px;
	top: 45px;
	background: rgba(34, 34, 34, 0.84);
	font-size: 16px;
	z-index: 15;
	display: none
}

.t-sitemap-menu li {
	height: 55px;
	width: 33.333%;
	float: left;
	line-height: 55px;
	text-align: center;
}

.t-sitemap-menu li a {
	height: 100%;
	padding: 0px 30px 0px 30px;
	position: relative;
	display: block;
	margin: -1px;
	color: #fff;
}

.t-sitemap-menu li a:hover {
	background: #000;
	color: #FF0047;
}

.t-menu {
	right: 0px;
}

a.icon-menu {
	background-position: -90px 0px;
}

.home-nav div.on a {
	-moz-transform: scaleY(-1);
	-webkit-transform: scaleY(-1);
	-o-transform: scaleY(-1);
	transform: scaleY(-1);
	filter: FlipV;
}

.site-map {
	background: #ECECEC;
}

.smi-title {
	text-align: center;
	line-height: 40px;
	font-size: 16px;
	color: #cc0066;
	font-weight: bold;
}

.smi-title a {
	display: block;
	height: 100%;
	color: inherit;
}

.smi-scroll {
	position: fixed;
	width: 100%;
	margin: 0;
	left: 0;
	top: 45px;
	background: rgba(255, 73, 160, 0.78);
	color: #fff;
	text-shadow: 0px 1px 20px #EE32C8;
}

.sm-item {
	
}

.sm-item ul {
	height: auto;
	position: relative;
	padding-bottom: 5px;
	overflow: hidden;
	background: #fff;
	border-bottom: 2px solid #fff;
	margin: 5px;
	border-radius: 3px;
	border: 1px solid #DFDFDF;
	border-bottom-width: 2px;
}

.sm-item ul li {
	width: 33.3333333333%;
	height: 45px;
	line-height: 45px;
	display: block;
}

.sm-item ul li a {
	width: 100%;
	height: 100%;
	text-align: center;
	color: #444;
	font-size: 14px;
	overflow: hidden;
}

.sm-item ul li.smi-more {
	width: 100%;
	position: absolute;
	bottom: 0;
	right: 0;
	left: 0;
}

.sm-item ul li.smi-more a {
	border: 1px solid #efefef;
	border-radius: 0 0 3px 3px;
	background: #FAFAFA;
}

.sm-item ul.openlist {
	height: auto;
}

a.gotop {
	position: fixed;
	bottom: 50px;
	right: 10px;
	display: block;
	width: 42px;
	height: 42px;
	border-radius: 42px;
	border: 3px solid #fff;
	text-decoration: none;
	text-indent: -9999em;
	background: rgba(255, 73, 160, 0.78)
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAcCAYAAAAjmez3AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDoyRTA4RTM0MTJFNUMxMUU0OTg3RUMyQzZFMkQ1MEMwQyIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDoyRTA4RTM0MjJFNUMxMUU0OTg3RUMyQzZFMkQ1MEMwQyI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjJFMDhFMzNGMkU1QzExRTQ5ODdFQzJDNkUyRDUwQzBDIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjJFMDhFMzQwMkU1QzExRTQ5ODdFQzJDNkUyRDUwQzBDIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+GUPV1QAAAiJJREFUeNrUl9srBGEYxnc3OeawsnJOLRsRJedcuBDlghLKKTdcrPIXuPRfuJELSXshIkWkSA67qygp5BhJm1xhk/F89X2lzey8sztjxlu/m5n3e9/nmffbmf2skiRZdIpYMAkGgBXMgwnwpkczq05GksEuKA+5fgbqwet/MJIG9oFL5v4lqAMBLZvaNDaRAbxhTLBwAh/IMutEMrnAPGL+I6gGD2aaCBN/pMIEi2zgB4VmmQgTcgAcEa5/ATX8t2PYRIr4U3VEUcPOt2SJUUZKuQC7Qh570hcKOang8JfXte5bqwLsgQSFvFe+9YLghr/VwsUHaORTVhfMiEqqwLukHM/A+WNdAXgkrAuCWrW61JpgDT4JYp5A/i/rs8C9RIsGvYw0gS+CgDuQE6ZOJrgimmnW2kgzsfEVF6pUzw7OiTVbtDLSSmzIhKWrmHAKOCXWbo/WSDuxEROUGsGLIwkcE3t0RGqkk9jgBCRGYEIQB3zEXt1qjfQQC/tBfBQmBDFgj9izj2qkn1jwgAuwaMgOsfeQkpFhYqFtYNXYhGCDqGFEzsgoscCWTgZ+skbU4g414iYuXP8DE4JloqZxYaSNuGDlD00IFojauljyJiFx0QATAg9B3z47j+Qq/EH2gE6LcdEDZpWOzTZ+rpCLOdBrMT4GwUyY+142Ohc/A4TGtIHbSY4pma1VKRKKwRIIgFswZkITAvb9uOZaV0EZu/4twAAmN7mMU5JmgAAAAABJRU5ErkJggg==)
		no-repeat center 48%;
	background-size: 25px auto;
	cursor: pointer;
}
</style>
</head>
<body>
	<header class="topbar">
		<h1 class="t-title">育婴之家网导航</h1>
		<span class="t-back"><a href="/front/m/index.do" class="icon-back"></a></span>
	</header>
	<div style="height: 45px;"></div>
	<div class="site-map">
		<s:iterator value="lstProjectMenu" id="projectMenu" >
			<div class="sm-item" id="smi_1">
				<h3 class="smi-title">
					<a href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }">${projectMenu.mobileMenuName }</a>
				</h3>
				<ul class="ulnav float-no">
					<s:iterator value="#projectMenu.lstChildrenProjectMenu" id="subProjectMenu" >
						<li><a href='/front/m/newsList.do?lProjectMenuID=${subProjectMenu.objParentProjectMenu.lId}&menuID=${subProjectMenu.lId}'>${subProjectMenu.mobileMenuName }</a></li>
					</s:iterator>
				</ul>
			</div>
		</s:iterator>
		<!-- end/.site-map/ -->
		<%@include file="foot.jsp"%>
	</div>
	
</body>
</html>