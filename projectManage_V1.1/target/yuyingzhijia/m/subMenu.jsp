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
	// $("#j-slider-home").load("/static/m/scroll/${objProjectMenu.lId }.html");
	$("#rec_div").load("/static/m/newest/"+$("#firstMenu").val()+".html");
});
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
						<li class="logo"><a href="<%=basePath %>front/m/index.do"
							class="logo-img"><span class="f-vh">育婴之家网</span></a></li>
					</ul>
					<ul class="m-bar-right user-info"></ul>
				</div>
			</header>
			<!-- nav -->
			<nav id="ty_nav" class="m-nav">
				<div class="nav-wrap show">
					<div class="m-nav-row">
<%-- 						<a appstr="shouye" href="<%=basePath %>front/m/index.do">首页</a> --%>
						<a appstr="shouye" href="<%=basePath %>front/m/index.do">首页</a> 
						<s:iterator value="lstMainProjectMenu" id="projectMenu"
							status="myStatus">
							<s:if test="#myStatus.index < 4">
								<a appstr="shouye"
									href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }"
									class='<s:if test="objProjectMenu.lId==#projectMenu.lId">active</s:if><s:else>""</s:else>'>
									<s:property value="mobileMenuName" />
								</a>
							</s:if>
						</s:iterator>
					</div>

					<div class="m-nav-row">
						<s:iterator value="lstMainProjectMenu" id="projectMenu" status="myStatus">
							<s:if test="#myStatus.index >= 4">
								<a appstr="shouye"
									href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }"
									class='<s:if test="objProjectMenu.lId==#projectMenu.lId">active</s:if><s:else>""</s:else>'>
									<s:property value="mobileMenuName" />
								</a>
							</s:if>
						</s:iterator>
					</div>
				</div>
			</nav>
			<div class="nav-mask" id="nav_mask"></div>
		</div>
		<!-- For FIS -->
		<!-- main 头部图片循环显示 -->
		<div class="m-main">
			<div class="u-slider u-slider-home">
			    <!-- 滚动图片显示 -->
				<div class="sliderbox gg gg-item" id="j-slider-home" >
					 <s:iterator value="lstRollingOfReading" id="news">
						<div class="item">
							<a
								href="/front/yuyingshi/detail.do?newsID=${news.lId}">
								<img lazyload="${news.imageUrl}" />
							</a>
							<p class="caption">
								${news.strLongTitleTwo}
							</p>
						</div>
					 </s:iterator>
				</div>
			</div>
			<div class="u-box">
				<a name="type"></a>
				<div class="see-wrap">
					<div class="u-tab tab-left-off">
						<div class="tab-wrap" id="tab_wrap" style="overflow: hidden;">
							<ul class="f-cf" style="left: 0px; top: 0px; width: 2030px; position: absolute;" id="f-cf">
							 <s:iterator value="lstProjectMenu" id="projectMenu"  status="myStatus">
							    <s:if test="#myStatus.index == 0">
									<li class="cur" style="width: 145px;" data-c='newest/<s:property value="lId"/>.html'>
										<span class=""><s:property value="mobileMenuName"/></span>
										<input type="hidden" value="<s:property value="lId"/>" id="firstMenu"/>
									</li>
								</s:if>
							 	<s:else>
									<li style="width: 145px;" data-c='newest/<s:property value="lId"/>.html'><span  class=""><s:property value="mobileMenuName"/></span></li>
								</s:else>
							 </s:iterator>
							</ul>
						</div>
					</div>
					
					<div id="type_div"></div>
					<div class="u-bd" id="rec_div"></div>
					
				</div>
			</div>

			<div class="u-box" id="dashang_div">
				<div class="u-hd f-cf">
					<h2>阅读排行</h2>
					<!-- 
					<a href="http://shang.tianya.cn/rank/m/dsRanking.do?from=t&type=1"
						class="u-hd-more">排行榜>></a>
					 -->
				</div>
				<div class="u-bd">
					<!-- 
                    <ul class="u-list-shang">
                        <li>
							<span class="nodataloading">耐心等等哈，骚年(☆_☆)<i class="three-quarters-loader"></i></span>
						</li>
                    </ul>
                     -->
					<ul class="u-list-shang">
						<s:iterator value="lstNewestMessage" id="news">
							<li>
								<h3 class="shang-title">
									<a
										href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"><s:property
											value="strLongTitle" /></a>
								</h3>
								<div class="shang-name">
									<a class="shang-head"
										href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"><em
										class="icon shang-head-photo"> </em> <span
										class="shang-head-name">育婴知识分享</span> </a> <span class="shang-num"><i
										class="icon icon-shang"></i> <s:property value="reamNum" /></span>
								</div>
							</li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<!-- 
			<div class="loading-weilun f-cf">
				
			</div>
			 -->
		</div>
	</div>
	<script src="/commons/js/tianya/TY_m_2.0.js"></script>
	<script src="/commons/js/tianya/main.js"></script>
	<script src="/commons/js/tianya/index2.js?20160414"></script>

	<%--     <script src="http://static.tianyaui.com/global/m/v3/static/js/main.js"></script> --%>
	<%--     <script src="http://static.tianyaui.com/global/m/v3/static/js/index2.js?20160414"></script> --%>

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
	
	<!-- Start Alexa Certify Javascript -->
	<!-- 
<script type="text/javascript">
_atrk_opts = { atrk_acct:"WCmTk1a4eFf2mh", domain:"tianya.cn",dynamic: true};
(function() { var as = document.createElement('script'); as.type = 'text/javascript'; as.async = true; as.src = "https://d31qbv1cthcecs.cloudfront.net/atrk.js"; var s = document.getElementsByTagName('script')[0];s.parentNode.insertBefore(as, s); })();
</script>
<noscript><img src="https://d5nxst8fruw4z.cloudfront.net/atrk.gif?account=WCmTk1a4eFf2mh" style="display:none" height="1" width="1" alt="" /></noscript>

 -->
	<!-- End Alexa Certify Javascript -->
	
</body>

</html>