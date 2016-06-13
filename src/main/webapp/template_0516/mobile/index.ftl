<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>育婴之家网</title>
<META name="Keywords"
	content="育婴之家，儿童之家，育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
<META name="description"
	content="育婴之家是一个分享交流育儿知识经验的 平台，我们致力于需求最好的育儿知识，通过我们这个平台知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no">
<meta name="applicable-device" content="mobile">
<link rel="stylesheet" href="/commons/css/mobile_ch.css">

<LINK rel="stylesheet" href="/commons/css/mobile.css"  type="text/css">
<LINK rel="stylesheet" href="/commons/css/swiper.min.css"  type="text/css">
<SCRIPT src="/m/common/gm/jquery.js"></SCRIPT>
<script src="/commons/js/swiper.min.js"></script></head>
<body>
	<header id="header">
		<div id="top-line"></div>
		<div class="swiper-wrapper">
			<#list lstProjectMenu as projectMenu>
				 <div class="swiper-slide" id='e2'>
					<li id='e1'><a href="/static/m/${projectMenu.menuKey }">${projectMenu.mobileMenuName }</a></li>
				</div>
			</#list>
		</div>
	</header>
	
	
	
	
	<div id="banner">
		<div class="swiper-wrapper">
			<#list lstRollingOfReading as news>
				<div class="swiper-slide">
					<a href="/front/yuyingshi/detail.do?newsID=${news.lId}" target="_blank">
						<#if news.imageUrl??>
							<img src="${news.imageUrl}" alt="${news.strLongTitleTwo}">
						<#else>
							<img src="/commons/images/defaultpic.gif" />
						</#if>
						<h2 class="gallerytitle">${news.strTitle}</h2>
					</a>
				</div>
			</#list>
		</div>
		<div class="pagination"></div>
	</div>
	
	<!-- 今日最新 start -->
	<div class="home_title">今日最新</div>
	<div class="home-article">
		<ul class="ha-list list-pt">
			<#list lstNewestMessage as news>
				<li>
					<a href="/front/yuyingshi/detail.do?newsID=${news.lId}" target="_blank">
					<#if news.imageUrl??>
						<img src="${news.imageUrl}" alt="${news.strLongTitleTwo}">
					<#else>
						<img src="/commons/images/defaultpic.gif" />
					</#if>
					<h2 class="lp-title">${news.strLongTitleTwo}</h2>
					<p class="lp-sum">${news.strShortOneContent}</p></a>
				</li>
			</#list>
		</ul>
	</div>
	<!-- 今日最新 emd -->
	
	<!-- 推荐阅读 start -->
	<div class="home_title">推荐阅读</div>
	<div class="home-article">
		<ul class="ha-list list-pt">
			<#list lstSuggestReading as news>
				<li>
					<a href="/front/yuyingshi/detail.do?newsID=${news.lId}" target="_blank">
					<#if news.imageUrl??>
						<img src="${news.imageUrl}" alt="${news.strLongTitleTwo}">
					<#else>
						<img src="/commons/images/defaultpic.gif" />
					</#if>
					<h2 class="lp-title">${news.strLongTitleTwo}"</h2>
					<p class="lp-sum">${news.strShortOneContent}</p></a>
				</li>
			</#list>
			
		</ul>
	</div>
	<!-- 推荐阅读 end -->
	
	<!-- 点击排行 start -->
	<div class="home_title">点击排行</div>
	<div class="home-article">
		<ul class="ha-list list-pt">
			<#list lstTopOfReading as news>
				<li>
					<a href="/front/yuyingshi/detail.do?newsID=${news.lId}" target="_blank">
					<#if news.imageUrl??>
						<img src="${news.imageUrl}" alt="${news.strLongTitleTwo}">
					<#else>
						<img src="/commons/images/defaultpic.gif" />
					</#if>
					<h2 class="lp-title">${news.strLongTitleTwo}</h2>
					<p class="lp-sum">${news.strShortOneContent}</p></a>
				</li>
			</#list>
			
		</ul>
	</div>
	<!-- 点击排行 end  -->
	
	
	<div class="home_title">
		<a href="/static/m/nav.html">查看更多</a>
	</div>
	<#include "common/foot.ftl">
	<script type="text/javascript">
		window.onload = function() {
			var mySwiper2 = new Swiper('#banner', {
				autoplay : 5000,
				visibilityFullFit : true,
				loop : true,
				pagination : '.pagination',
			});
		}
	</script>
</body>
</html>