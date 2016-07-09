<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="address=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>育婴之家网_育婴知识分享平台</title>
<META name="Keywords" content="育婴之家，儿童之家，育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
<META name="description" content="育婴之家是一个分享交流育儿知识经验的 平台，我们致力于将最好的育儿知识，通过我们这个平台知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">
<meta name="robots" content="index,follow" />

<!-- 汽车之家css -->
<link href="/commons/css/autohome/com.css" rel="stylesheet" />
<!-- end 汽车之家 -->
<LINK rel="stylesheet" href="/commons/css/bbtree/concat.css" type="text/css">
<LINK rel="stylesheet" href="/commons/css/bbtree/home.css" type="text/css">
<LINK rel="stylesheet" href="/commons/css/bbtree/slider.css" type="text/css">
<LINK rel="stylesheet" href="/commons/css/bbtree/slider.default.css" type="text/css">
<style type="text/css">
.jnv-rank-list{
	margin:10px;
}

.jnv-rank-list a:link {
    color: #fff;
    text-decoration: none;
}

.jnv-rank-list a:hover {
    color: #fff;
    text-decoration: none;
    background-color: #fe89a0;
}

.jnv-rank-list a {
    display: inline-block;
    font-family: SimSun;
    height: 22px;
    line-height: 22px;
    vertical-align: middle;
    border-radius: 10px;
    padding: 0 10px;
    margin-right: 10px;
    margin-top: 10px;
    font-size: 13px;
    color: #fff;
    text-decoration: none;
    background-color: #feb4c3;
}

</style>
<script src="/commons/js/bbtree/zepto.min.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/myurchin.js"></script>
<script type="text/javascript" src="/commons/js/bbtree/asset.js"></script>
<script>
$(function(){
		$("#slider").show();
	});
</script>
</head>
<body>
	<div class="wrap">
		<script type="application/javascript"></script>
		<!-- 1级导航 -->
		<div class="user-toolbar-top">
			<div class="wap-logo">
				<a href="/front/m/index.do">yuyingzhijia</a>
			</div>
		</div>
		<nav>
			<ul class="nav-list">
				<li class="current"><a refcode="wap-nav-home" href="/static/m/index.html">首页</a></li>
				<#list lstProjectMenu as projectMenu>
				    <#if projectMenu_index < 4>
				    	<li><a refcode="wap-nav-group" href="/static/m/${projectMenu.lId }.html">${projectMenu.mobileMenuName }</a></li>
				    </#if>
				</#list>
			</ul>
			<ul class="nav-list">
			    <#list lstProjectMenu as projectMenu>
			        <#if projectMenu_index < 4>
					<#elseif projectMenu_index <=8>
				    	<li><a refcode="wap-nav-group" href="/static/m/${projectMenu.lId }.html">${projectMenu.mobileMenuName }</a></li>
					</#if>
				</#list>
			</ul>
		</nav>
		<!-- 1级导航 end -->
		<div id="slider" style="display:none;">
		    <#list lstRollingOfReading as news>
				<div>
					<a href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}"> <img src="${news.imageUrl}">
						<h2 class="gallerytitle">${news.strTitle}</h2>
					</a>
				</div>			
			</#list>
		</div>

		<div class="m-search">
			<form action="/front/m/newsSearch.do" method="get" id="searchform">
				<span class="icon"></span> <input type="text" id="searchText" name="searchText" refcode="wapindex_s01" placeholder="育婴知识搜索" /> <input type="submit" value="搜索" />
			</form>
		</div>

		<!-- 汽车之家 start -->
		<section name="zixunlist" id="zixunlist" class="homepage-info fn-mt">
			<h3 class="tab">
				<span class="item activate">最新动态</span>
			</h3>
			<ul class="list-info fn-mlr" id="newslist">
			    <#list lstRollingOfReading as news>
			    	<li>
			    		<a href="/front/yuyingshi/detail.do?newsID=${news.lId}"> 
			    		<#if news.imageUrl??>
							<img src="/commons/images/defaultpic.gif" />
						<#else>
							<img src="${news.imageUrl}" alt="${news.strLongTitleTwo}" />
						</#if>
						<h4>
							${news.strLongTitleTwo}
						</h4> <time>来自育婴知识分享</time> <span class="comment"><strong>${news.readNum}</strong>阅读</span>
						</a>
					</li>
			    </#list>
			</ul>
		</section>
		
		<section name="zixunlist" id="zixunlist" class="homepage-info fn-mt">
			<h3 class="tab">
				<span class="item activate">阅读排行</span>
				<!-- <a href="/channel/#pvareaid=100261" class="btn small skip">进入阅读排行<i class="iconfont icon-arrow-right"></i></a> -->
			</h3>
			<ul class="m-thumbnail">
				<li><a href="/" refcode="wap-home-redianzhishi"> <img src="/commons/images/0b34855d88833930.jpg" />
						<p class="img-mask">育婴知识分享，分享精彩</p>
				</a></li>
			</ul>
			<ul class="list-info fn-mlr" id="newslist">
			    <#list lstTopOfReading as news>
			    	<li>
			    		<a href="/front/yuyingshi/detail.do?newsID=${news.lId}"> 
			    		<#if news.imageUrl??>
							<img src="/commons/images/defaultpic.gif" />
						<#else>
							<img src="${news.imageUrl}" alt="${news.strLongTitleTwo}" />
						</#if>
						<h4>
							${news.strLongTitleTwo}
						</h4> <time>来自育婴知识分享</time> <span class="comment"><strong>${news.readNum}</strong>阅读</span>
						</a>
					</li>
			    </#list>
			</ul>
		</section>

		<section name="zixunlist" id="zixunlist" class="homepage-info fn-mt">
			<h3 class="tab">
				<span class="item activate">阅读导航</span>
			</h3>
			<div class="jnv-rank-list">
				<#list lstProjectMenu as projectMenu>
					<a refcode="wap-nav-group" href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }">${projectMenu.mobileMenuName }</a>
				</#list>
				<a href="/">首页</a><a href="/static/m/nav.html">全网导航</a>
			</div>
			<!-- <div class="btn full fn-mt-large fn-mb-large fn-mlr" id="NewsloadMore">点击加载20条资讯<i class="iconfont icon-arrow-bottom"></i>
		    </div> -->
		</section>
	</div>
	<br/>
	<DIV class="footer_main" style="">
		<DIV style="text-align: center; color: rgb(186, 186, 186); font-size: 12px;">
			育婴之家网版权所有 | 服务QQ：2496664615
		</DIV>
	</DIV>
	<!-- end of wrap -->
	<script type="text/javascript" src="/commons/js/bbtree/come-true.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/extend/touch.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/extend/matchMedia.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/extend/event.ortchange.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/extend/parseTpl.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/core/gmu.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/core/event.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/core/widget.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/widget/slider/slider.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/widget/slider/autoplay.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/widget/slider/dots.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/widget/slider/touch.min.js"></script>
	<script type="text/javascript" src="/commons/js/bbtree/main.js"></script>
</body>
</html>
