<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="address=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>育婴之家网_育婴知识分享平台</title>
<META name="Keywords"
	content="育婴之家，儿童之家，育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
<META name="description"
	content="育婴之家是一个分享交流育儿知识经验的 平台，我们致力于将最好的育儿知识，通过我们这个平台知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">
<meta name="robots" content="index,follow" />
<!--  -->
<LINK rel="stylesheet" href="/commons/css/bbtree/concat.css"
	type="text/css">
<LINK rel="stylesheet" href="/commons/css/bbtree/home.css"
	type="text/css">
<LINK rel="stylesheet" href="/commons/css/bbtree/slider.css"
	type="text/css">
<LINK rel="stylesheet" href="/commons/css/bbtree/slider.default.css"
	type="text/css">
<!-- <link type="text/css" rel="stylesheet" href="http://static02.babytreeimg.com/concat/??/img/ng/dist/common/css/mobile-common.css,/img/ng/dist/product/wap/home/css/home.css,/img/ng/lib/gmu/2.1.1/css/widget/slider/slider.css,/img/ng/lib/gmu/2.1.1/css/widget/slider/slider.default.css?ver=1461723000" /> -->

<%-- <script type="text/javascript" src="http://static01.babytreeimg.com/img/ng/lib/zepto/1.0.0/zepto.min.js?ver=20110707"></script> --%>
<script src="/commons/js/bbtree/zepto.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/myurchin.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/asset.js"></script>
<script>
	_myuacct = "UA-430199-5";
	myurchinTracker();
</script>
</head>
<body>
	<div class="wrap">
		<script type="application/javascript">
		</script>
		<!-- 1级导航 -->
		<div class="user-toolbar-top">
			<div class="wap-logo">
				<a href="/">babytree</a>
			</div>
			<!-- 
			<div class="top-entrance">
				<a href="/reg/register.php?url=%2F">注册</a>
				<a href="/reg/signin.php?url=%2F">登录</a>
			</div>
			 -->
		</div>
		<nav>
			<ul class="nav-list">
				<li class="current"><a refcode="wap-nav-home" href="/">首页</a></li>
				<s:iterator value="lstProjectMenu" id="projectMenu" status="myStatus">
				    <s:if test="#myStatus.index <= 4">
				       <li><a refcode="wap-nav-group" href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }">${mobileMenuName }</a></li>
				    </s:if>
				</s:iterator>
			</ul>
		</nav>
		<!-- 1级导航 end -->
		<div id="slider">
		 <s:iterator value="lstRollingOfReading" id="news">
		    <div>
				<a href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}"><img src="${news.imageUrl}"></a>
			</div>
		 </s:iterator>
		</div>

		<div class="m-search">
			<form action="/ask/newSearch.php" method="get" id="searchform">
				<span class="icon"></span>
				<input type="text" id="search_input" name="q" refcode="wapindex_s01"
					placeholder="请输入您的育儿困惑" /> <input type="submit" value="搜索" />
			</form>
		</div>
 
		<div class="m-list">
			<h4 class="hd">最新动态</h4>
			<ul class="bd">
			 <s:iterator value="lstNewestMessage" id="news" status="curStatus">
			    <li>
				  <a class="block" href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" refcode="wap-home-remenwenda"> 
				        <span class="title"><s:property value="strLongTitleTwo"/></span>
						<span class="column"></span>
						<span><strong>0</strong>浏览</span>
				   </a>
				</li>
			 </s:iterator>
			</ul>
			<a class="button green" href="/ask/" refcode="wap-home-ask">进入问答频道</a>
		</div>
		
		<div class="m-media">
			<h2 class="hd">精华推荐</h2>
			<ul class="bd">
			    <s:iterator value="lstSuggestReading" id="news" status="curStatus">
				<li><a class="block"
					href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"
					refcode="wap-home-jinghuatuijian">
						<div class="pull-left">
							<img src="/commons/images/defaultpic.gif" />
						</div>
						<div class="media-body">
							<h4 class="media-heading"><s:property value="strLongTitleTwo"/></h4>
							<!-- <div>摘要：<s:property value="strShortOneContent"/></div> -->
							<div class="media-info">
								<span>来自育婴知识分享</span> <span class="column"></span> <span><strong>0</strong>回复</span>
							</div>
						</div>
					</a>
				</li>
				</s:iterator>
			</ul>
			<a class="button green" href="/community/" refcode="wap-home-group">进入圈子频道</a>
		</div>

		<div class="m-list">
			<h4 class="hd">热点知识</h4>
			<ul class="m-thumbnail">
				<li><a href="/" refcode="wap-home-redianzhishi"> <img
						src="http://pic01.babytreeimg.com/foto3/common_photo/original/2015/0818/0b34855d88833930.jpg" />
						<p class="img-mask">改变只为给宝宝更好的</p>
				</a></li>
			</ul>
			<ul class="bd">
				<li><a class="block" href="/learn/article/6203"
					refcode="wap-home-redianzhishi"> <span class="title">抓住怀孕“黄金”时刻</span>
						<span class="column"></span> <span><strong>365135</strong>浏览</span>
				</a></li>
				<li><a class="block" href="/learn/article/10107"
					refcode="wap-home-redianzhishi"> <span class="title">在孕期的房事禁忌有哪些</span>
						<span class="column"></span> <span><strong>180037</strong>浏览</span>
				</a></li>
				<li><a class="block" href="/learn/article/30985"
					refcode="wap-home-redianzhishi"> <span class="title">影响胎儿性别的13大诱因</span>
						<span class="column"></span> <span><strong>646691</strong>浏览</span>
				</a></li>
				<li><a class="block" href="/community/yuer/topic_57611317.html"
					refcode="wap-home-redianzhishi"> <span class="title">舌尖上的营养，我为萌宝选辅食！</span>
						<span class="column"></span> <span><strong>2569</strong>浏览</span>
				</a></li>
				<li><a class="block" href="/learn/article/14686"
					refcode="wap-home-redianzhishi"> <span class="title">新生宝宝的五官护理手册</span>
						<span class="column"></span> <span><strong>108461</strong>浏览</span>
				</a></li>
				<li><a class="block" href="/community/yuer/topic_44253954.html"
					refcode="wap-home-redianzhishi"> <span class="title">法国诺曼底纯净之旅火热招募中</span>
						<span class="column"></span> <span><strong>2569</strong>浏览</span>
				</a></li>
			</ul>
			<a class="button green" href="/learn/" refcode="wap-home-knowledge">进入知识频道</a>
		</div>

		<!-- 
		<div class="m-media">
			<h4 class="hd">精彩日记</h4>
			<ul class="bd">
				<li><a class="block"
					href="http://m.babytree.com/home/33440503/journal/13860003"
					refcode="wap-home-jingcairiji">
						<div class="pull-left">
							<img
								src="http://pic05.babytreeimg.com/foto3/thumbs/2016/0522/48/6/1fe42f723bd5067f31328_sm.jpg" />
						</div>
						<div class="media-body">
							<h4 class="media-heading">舌尖上的回家——山城的水 河里的鱼</h4>
							<div class="media-info">
								<span>来自水儿雪</span> <span class="column"></span> <span><strong>813</strong>浏览</span>
							</div>
						</div>
				</a></li>
				<li><a class="block"
					href="http://m.babytree.com/home/u72037115158/journal/13859621"
					refcode="wap-home-jingcairiji">
						<div class="pull-left">
							<img
								src="http://pic02.babytreeimg.com/foto3/thumbs/2016/0524/01/6/10c5bf25161f4d50d2a893f8_sm.jpg" />
						</div>
						<div class="media-body">
							<h4 class="media-heading">【萌萌Day286】提前到的六一礼物</h4>
							<div class="media-info">
								<span>来自阿紫子</span> <span class="column"></span> <span><strong>537</strong>浏览</span>
							</div>
						</div>
				</a></li>
				<li><a class="block"
					href="http://m.babytree.com/home/u1008082409/journal/13861343"
					refcode="wap-home-jingcairiji">
						<div class="pull-left">
							<img
								src="http://pic10.babytreeimg.com/2016/0520/FuRxzUXjg3zlccL-g-lu1478eF4Q_sm.jpg" />
						</div>
						<div class="media-body">
							<h4 class="media-heading">点，为片刻！</h4>
							<div class="media-info">
								<span>来自亮亮之家</span> <span class="column"></span> <span><strong>521</strong>浏览</span>
							</div>
						</div>
				</a></li>
				<li><a class="block"
					href="http://m.babytree.com/home/u2675085312/journal/13858911"
					refcode="wap-home-jingcairiji">
						<div class="pull-left">
							<img
								src="http://pic01.babytreeimg.com/foto3/thumbs/2016/0505/27/0/9f7290009cd4cfc251657_sm.jpg" />
						</div>
						<div class="media-body">
							<h4 class="media-heading">小村夏木深，水峪遇水</h4>
							<div class="media-info">
								<span>来自龟背竹</span> <span class="column"></span> <span><strong>411</strong>浏览</span>
							</div>
						</div>
				</a></li>
				<li><a class="block"
					href="http://m.babytree.com/home/u157067027/journal/13857998"
					refcode="wap-home-jingcairiji">
						<div class="pull-left">
							<img
								src="http://pic01.babytreeimg.com/foto3/thumbs/2016/0523/88/7/95ca713270d509ab71f41_sm.jpg" />
						</div>
						<div class="media-body">
							<h4 class="media-heading">第一次观看架子鼓独奏音乐会（8岁5月5天记）</h4>
							<div class="media-info">
								<span>来自豆丁王</span> <span class="column"></span> <span><strong>405</strong>浏览</span>
							</div>
						</div>
				</a></li>
				<li><a class="block"
					href="http://m.babytree.com/home/u80768135511/journal/13860775"
					refcode="wap-home-jingcairiji">
						<div class="pull-left">
							<img
								src="http://pic04.babytreeimg.com/foto3/thumbs/2016/0523/66/5/12ce27f1571c9d5093ca1a44_sm.jpg" />
						</div>
						<div class="media-body">
							<h4 class="media-heading">悠闲午后，快乐萌妞~</h4>
							<div class="media-info">
								<span>来自南方明珠</span> <span class="column"></span> <span><strong>357</strong>浏览</span>
							</div>
						</div>
				</a></li>
			</ul>
			<a class="button green" href="/reg/signin.php?url=/home/"
				refcode="wap-home-profile">进入我的小家</a>
		</div>
		 -->

		<!-- footer.php start here -->
		<div class="user-toolbar">
			<div class="column">
				<a class="sign-up" href="/reg/register.php?url=%2F">注册</a> <a
					class="log-in" href="/reg/signin.php?url=%2F">登录</a>
			</div>
		</div>


		<footer>
			<p>
				<a href="/">首页</a>| <a href="/community">圈子</a>| <a href="/ask">问答</a>|
				<a href="/learn">知识</a>| <a href="/home">小家</a>
				<!-- <a href="javascript;" class="current">触屏版</a>| <a href="www.yuyingzhijia.cn">电脑版</a> -->
			</p>
			<p class="copyright">© 2016 育婴之家 与你同行</p>
		</footer>
	</div>
	<!-- end of wrap -->
<script type="text/javascript" src="/commons/js/bbtree/come-true.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/extend/touch.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/extend/matchMedia.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/extend/event.ortchange.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/extend/parseTpl.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/core/gmu.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/core/event.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/core/widget.min.js">"></script>
<script type="text/javascript" src="/commons/js/bbtree/widget/slider/slider.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/widget/slider/autoplay.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/widget/slider/dots.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/widget/slider/touch.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/main.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/app-down.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/stat.js"></script>
</body>
</html>
