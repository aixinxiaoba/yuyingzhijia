<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<s:if test="lstProjectMenu.size> 6">
		width: <s:property value="200/lstProjectMenu.size"/>%;
	</s:if>
	<s:else>
		width: <s:property value="100/lstProjectMenu.size"/>%;
	</s:else>
	height: 38px;
	line-height: 38px;
	color: #2a70be;
}

.tabs a.active {
	border-top: 2px solid #2a70be;
	margin-top: -2px;
	background: #47c18e;
	background-repeat: no-repeat;
	
	<s:if test="lstProjectMenu.size> 6">
		-webkit-background-size: <s:property value="200/lstProjectMenu.size"/>% 39px;
	</s:if>
	<s:else>
		-webkit-background-size: <s:property value="100/lstProjectMenu.size"/>% 39px;
	</s:else>
	
	<s:if test="lstProjectMenu.size> 6">
	-moz-background-size: <s:property value="200/lstProjectMenu.size"/>% 39px;
	</s:if>
	<s:else>
	-moz-background-size: <s:property value="100/lstProjectMenu.size"/>% 39px;
	</s:else>
	
	<s:if test="lstProjectMenu.size> 6">
	-o-background-size: <s:property value="200/lstProjectMenu.size"/>% 39px;
	</s:if>
	<s:else>
	-o-background-size: <s:property value="100/lstProjectMenu.size"/>% 39px;
	</s:else>
	
	<s:if test="lstProjectMenu.size> 6">
	background-size: <s:property value="200/lstProjectMenu.size"/>% 39px;
	</s:if>
	<s:else>
	background-size: <s:property value="100/lstProjectMenu.size"/>% 39px;
	</s:else>
	
	
	background-position: left top;
	color: #fff;
	filter: alpha(opacity = 50);
	-moz-opacity: 0.5;
/* 	opacity: 0.5; */
	opacity: 0.8;
}

#e1 a {
	background: #47c18e;
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
				<a href="/front/m/index.do">首页</a>
			</div>
			<s:iterator value="lstMainProjectMenu" id="projectMenu"  status="myStatus">
				 <s:if test="objProjectMenu.lId==lId">
				 	<div class="swiper-slide" id='e1'>
						<a href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }"><s:property value="mobileMenuName"/></a>
					</div>
				 </s:if>
				 <s:else>
					 <div class="swiper-slide" id='e2'>
						<a href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }"><s:property value="mobileMenuName"/></a>
					</div>
				 </s:else>
			</s:iterator>
		</div>
	</header>
	<div id="banner">
		<div class="swiper-wrapper">
			<s:iterator value="lstRollingOfReading" id="news"  status="myStatus">
				<div class="swiper-slide">
					<a href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"
						target="_blank"><img src="${news.imageUrl}" />
					<h2 class="gallerytitle"><s:property value="strLongTitleTwo"/></h2></a>
				</div>
			</s:iterator>
		</div>
		<div class="pagination"></div>
	</div>
	<div class="tabs">
		<s:iterator value="lstProjectMenu" id="projectMenu"  status="myStatus">
			<s:if test="#myStatus.index == 0">
				<a href="#" class="active"><s:property value="mobileMenuName"/></a>
			</s:if>
			<s:else>
				<a href="#"><s:property value="mobileMenuName"/></a>
			</s:else>
		</s:iterator>
	</div>
	<div id="tabs-container" class="swiper-container">
		<div class="swiper-wrapper">
			<s:iterator value="lstProjectMenu" id="projectMenu"  status="myStatus">
				<div class="swiper-slide">
					<div class="home-article">
						<ul class="ha-list list-pt">
							<s:iterator value="lstManualNews" id="news" status="myStatus">
								<li>
									<a href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"
									target="_blank"><img
										src="${news.imageUrl }"
										alt="<s:property value="strLongTitle" />">
										<h2 class="lp-title"><s:property value="strLongTitle" /></h2>
										<p class="lp-sum"><s:property value="strShortOneContent"/></p></a></li>
							</s:iterator>
						</ul>
					</div>
					<!-- 
					暂时屏蔽
					<div class="th_title">
						<a href="/huaiyun/yq/">查看更多</a>
					</div>
					 -->
				</div>
			</s:iterator>
		</div>
	</div>
	<div class="th_title">人气排行榜</div>
	<div class="home-article">
		<ul class="ha-list list-pt">
			<s:iterator value="lstNewestMessage" id="news">
				<li><a href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"
				target="_blank">
				<img src="${news.imageUrl }" alt="<s:property value="strLongTitle" />">
				<h2 class="lp-title"><s:property value="strLongTitle" /></h2>
					<p class="lp-sum"><s:property value="strShortOneContent" /></p></a></li>
			</s:iterator>
		</ul>
	</div>
	<div class="th_title">
		<a href="/front/m/nav.do">查看更多</a>
	</div>
	<%@include file="foot.jsp"%>
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
		}
	</script>
</body>
</html>