<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>育婴之家网</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="Cache-control" content="no-cache" />
<meta name="format-detection" content="telephone=no">
<meta name="applicable-device" content="mobile">
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="stylesheet" href="/commons/css/mobile_ch.css">
<LINK rel="stylesheet" href="/commons/css/swiper.min.css"  type="text/css">
<SCRIPT src="/m/common/gm/jquery.js"></SCRIPT>
<script src="/commons/js/swiper.min.js"></script>
<style type="text/css">
.tabs a {
	font-size: 1em;
	font-weight: normal;
	text-align: center;
	float: left;
	
	<#if lstProjectMenu?size gt 6>
		width: ${200/lstProjectMenu?size}%;
	<#else>
		width: ${100/lstProjectMenu?size}%;
	</#if>
	height: 38px;
	line-height: 38px;
	color: #2a70be;
}

.tabs a.active {
	border-top: 2px solid #2a70be;
	margin-top: -2px;
	background: #FC6464;
	background-repeat: no-repeat;
	
	<#if lstProjectMenu?size gt 6>
		-webkit-background-size: ${200/lstProjectMenu?size}% 39px;
	<#else>
		-webkit-background-size: ${100/lstProjectMenu?size}% 39px;
	</#if>
	
	<#if lstProjectMenu?size gt 6>
	-moz-background-size: ${200/lstProjectMenu?size}% 39px;
	<#else> 
	-moz-background-size: ${100/lstProjectMenu?size}% 39px;
	</#if>
	
	<#if lstProjectMenu?size gt 6>
	-o-background-size: ${200/lstProjectMenu?size}% 39px;
	<#else>  
	-o-background-size: ${100/lstProjectMenu?size}% 39px;
	</#if>
	
	<#if lstProjectMenu?size gt 6>
	background-size: ${200/lstProjectMenu?size}% 39px;
	<#else> 
	background-size: ${100/lstProjectMenu?size}% 39px;
	</#if>
	
	
	background-position: left top;
	color: #fff;
	filter: alpha(opacity = 50);
	-moz-opacity: 0.5;
/* 	opacity: 0.5; */
	opacity: 0.8;
}

#e1 a {
	background: #FC6464;
	color: #fff;
	display: inline-block;
	border-radius: 10px;
	border: 1px solid #f8f8f8;
	padding: 2px 7px;
	line-height: 20px;
	filter: alpha(opacity = 50);
	-moz-opacity: 0.5;
	opacity: 0.5;
}
</style>
</head>
<body>
	<header id="header">
		<div id="top-line"></div>
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<a href="/static/m/index.html">首页</a>
			</div>
			<#list lstMainProjectMenu as projectMenu>
				 <#if objProjectMenu.lId == projectMenu.lId>
				 		<div class="swiper-slide" id='e1'>
							<a href="/static/m/${projectMenu.menuKey }">${projectMenu.mobileMenuName }</a>
						</div>
				 <#else>
				 		<div class="swiper-slide" id='e2'>
							<a href="/static/m/${projectMenu.menuKey }">${projectMenu.mobileMenuName }</a>
						</div>
				 </#if>
				</#list>
		</div>
	</header>
	<div id="banner">
		<div class="swiper-wrapper">
			<#list lstRollingOfReading as news>
				<div class="swiper-slide">
					<a href="/front/yuyingshi/detail.do?newsID=${news.lId}"
						target="_blank">
					<#if news.imageUrl??>
						<img src="${news.imageUrl}" alt="${news.strLongTitleTwo}">
					<#else>
						<img src="/commons/images/defaultpic.gif" />
					</#if>
					<h2 class="gallerytitle">${news.strLongTitleTwo}</h2></a>
				</div>
			</#list>
		</div>
		<div class="pagination"></div>
	</div>
	<div class="tabs">
		<#list lstProjectMenu as projectMenu>
			<#if projectMenu_index == 0>
					<a href="#" class="active">${projectMenu.mobileMenuName}</a>
			<#else>
					<a href="#">${projectMenu.mobileMenuName}</a>
			</#if>
		</#list>
	</div>
	<div id="tabs-container" class="swiper-container">
		<div class="swiper-wrapper">
			<#list lstProjectMenu as projectMenu>
				<div class="swiper-slide">
					<div class="home-article">
						<ul class="ha-list list-pt">
							<#list projectMenu.lstManualNews as news> 
								<li>
									<a href="/front/yuyingshi/detail.do?newsID=${news.lId}"
									target="_blank">
									<#if news.imageUrl??>
										<img src="${news.imageUrl}" alt="${news.strLongTitleTwo}">
									<#else>
										<img src="/commons/images/defaultpic.gif" />
									</#if>
									
									<h2 class="lp-title">${news.strLongTitle}</h2>
									<p class="lp-sum">${news.strShortOneContent}</p></a></li>
							</#list>
						</ul>
					</div>
				</div>
			</#list>
		</div>
	</div>
	<div class="th_title">人气排行榜</div>
	<div class="home-article">
		<ul class="ha-list list-pt">
			<#list lstNewestMessage as news>
				<li><a href="/front/yuyingshi/detail.do?newsID=${news.lId}"
				target="_blank">
				<#if news.imageUrl??>
					<img src="${news.imageUrl}" alt="${news.strLongTitleTwo}">
				<#else>
					<img src="/commons/images/defaultpic.gif" />
				</#if>
				<h2 class="lp-title">${news.strLongTitle}</h2>
					<p class="lp-sum">${news.strShortOneContent}</p></a></li>
			</#list>
		</ul>
	</div>
	<div class="th_title">
		<a href="/static/m/nav.html">查看更多</a>
	</div>
	<script type="text/javascript">
		window.onload = function() {
			var mySwiper1 = new Swiper('#header', {
				freeMode : true,
				slidesPerView : 'auto',
			});
			var mySwiper2 = new Swiper('#banner', {
				autoplay : 5000,
				visibilityFullFit : true,
				loop : true,
				pagination : '.pagination',
			});

			var tabsSwiper = new Swiper('#tabs-container', {
				speed : 500,
				onSlideChangeStart : function() {
					$(".tabs .active").removeClass('active')
					$(".tabs a").eq(tabsSwiper.activeIndex).addClass('active')
				}
			})
			$(".tabs a").on('touchstart mousedown', function(e) {
				e.preventDefault()
				$(".tabs .active").removeClass('active')
				$(this).addClass('active')
				tabsSwiper.slideTo($(this).index())
			})
			$(".tabs a").click(function(e) {
				e.preventDefault()
			})
			
			$("#cnzz_stat_icon_1254920361").hide();
		}
	</script>
	
	<#include "common/foot.ftl">
	
</body>
</html>