<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML>
<!-- saved from url=(0030)http://m.gmw.cn/node_32337.htm -->
<HTML>
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">
<!-- tplid:20158 -->
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Cache-Control" content="max-age=0">
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Expires" content="0">
<META http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<META name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0">
<META name="Keywords"
			content="问答，ask，ci123，育儿，育儿网，儿童，怀孕，胎教，孕妇，产后，婴儿，宝宝，纸尿裤，奶粉，小孩，幼儿，幼儿园，儿童玩具，儿歌，童谣，幼教。">
		<META name="description"
			content="育儿网网友互助问答是一个汇聚网友智慧，交流育儿经验的频道。关心育儿的网友可以在这里提出问题，悬赏征答，回答问题获取育儿网积分和等级。在这里您也可以找到过去回答的一些答案，同时发表自己的见解，为共同努力打造互联网上的育儿百科全书贡献自己的智慧。">
<META name="filetype" content="0">
<META name="publishedtype" content="1">
<META name="pagetype" content="2">
<META name="catalogs" content="32337">
<TITLE>${objProjectMenu.strMenuName }-育婴知识学习交流平台</TITLE>
<LINK href="/m/common/gm/common_column.css" rel="stylesheet">
<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<META name="GENERATOR" content="MSHTML 11.00.9600.17842">

<style>
/* 懒人之家 www.lanrenzhijia.com */
#wrapper{position:absolute;z-index:1;top:0;bottom:0;left:0;width:100%;background:#fff;overflow:hidden;margin-top:50px;}
#scroller{position:absolute;z-index:1;-webkit-tap-highlight-color:rgba(0,0,0,0);width:100%;-webkit-transform:translateZ(0);-moz-transform:translateZ(0);-ms-transform:translateZ(0);-o-transform:translateZ(0);transform:translateZ(0);-webkit-touch-callout:none;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;-webkit-text-size-adjust:none;-moz-text-size-adjust:none;-ms-text-size-adjust:none;-o-text-size-adjust:none;text-size-adjust:none}
#scroller-pullDown,#scroller-pullUp{background:#fff;height:40px;line-height:40px;padding:0px 10px;font-weight:bold;font-size:14px;color:#888;text-align:center;position:absolute;left:0;width:100%}
#scroller-pullDown{top:-40px}
#scroller-pullUp{bottom:-40px}
#scroller-pullDown .pull-up-msg,#scroller-pullUp .pull-down-msg{padding-left:20px}
#scroller-pullDown .pull-down-icon,#scroller-pullUp .pull-up-icon{display:inline-block;color:red;font-size:1.4;-webkit-transform:rotate(0);-webkit-transition-property:-webkit-transform;-webkit-transition-duration:500ms}
#scroller-pullDown .pull-down-icon.reverse_icon,#scroller-pullUp .pull-up-icon.reverse_icon{-webkit-transform:rotate(-180deg)}
#scroller ul{list-style:none;padding:0;margin:0;width:100%;text-align:left}
#scroller li{padding:0 10px;height:40px;line-height:40px;border-bottom:1px solid #ccc;border-top:1px solid #fff;background-color:#fafafa;font-size:18px}
</style>
<STYLE>
body {
	margin: 0;
	padding: 0;
}

.foot_nav {
	min-width: 320px;
	height: 40px;
	background: url(/m/common/images/bgnav.jpg) repeat-x;
}

.foot_nav a {
	margin: 0 3px;
	color: #fff;
	text-decoration: none;
	line-height: 40px;
	text-align: center;
	font-size: 16px;
}

.font_wrap {
	text-align: center;
	margin: 0 auto;
}

#onlineReader {
	text-align: center;
	padding: 10px 0;
	font-size: 16px;
	line-height: 1.8em;
}

#onlineReader a {
	color: #33485b;
	text-decoration: none;
	padding: 0 5px
}
</STYLE>
</HEAD>
<BODY>

	<input type="hidden" value="${objSubProjectMenu.lId}" name="menuID" id="menuID"/>
	<input type="hidden" value="${menuID}" name="lProjectMenuID" id="lProjectMenuID"/>
	<input type="hidden" value="${objGPagination.pageNo }" name="page" id="page"/>
	
	<DIV class="nav_header_wrap">
		<!-- 
		<A class="nav_header_left" href="/front/m/index.do">
		 -->
		 <A class="nav_header_left" href="/static/m/index.html">
		<IMG
			src="/m/common/header_yyzj_logo.png"> </A>
			<!-- 
			<A class="nav_header_right" href="/front/m/nav.do">
			 -->
			 <A class="nav_header_right" href="/static/m/nav.html">
			<IMG
			src="/m/common/gm/header_nav_btn.png"> </A>
		<FONT class="nav_header_center">${objSubProjectMenu.strMenuName }</FONT>
		<BR class="clear">
	</DIV>
	<!--代码部分begin-->
	<div id="wrapper">
		<div id="scroller">
			<div id="scroller-pullDown">
	        	  <span id="down-icon" class="icon-double-angle-down pull-down-icon"></span>
	        	  <span id="pullDown-msg" class="pull-down-msg">下拉刷新</span>		
	        </div>
			<div id="scroller-content">
				<ul id="list">
					<s:if test="objGPagination != null && objGPagination.rows.size > 0">
	                    <s:iterator value="objGPagination.rows" id="objNews" status="mystatus">
							<li>
								<A href="/front/yuyingshi/detail.do?newsID=${objNews.lId}"
								target="_self"><s:property value="strFormatTitle"/></A>
							</li>
						</s:iterator>
					</s:if>
			      </ul>
		    </div>
		    <div id="scroller-pullUp">
				<span id="up-icon" class="icon-double-angle-up pull-up-icon"></span>
			    <span id="pullUp-msg" class="pull-up-msg">上拉刷新</span>
	        </div>
		</div>
	</div>
	<script src="/commons/js/iscroll/iscroll.js"></script>
	<script src="/commons/js/iscroll/loading.js"></script>
	<!--代码部分end-->
	
</BODY>
</HTML>

