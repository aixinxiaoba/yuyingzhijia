<!DOCTYPE HTML>
<!-- saved from url=(0077)http://m.gmw.cn/2015-06/13/content_15968938.htm -->
<!DOCTYPE html PUBLIC "" "">
<HTML>
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">
<!-- tplid:20632,name:普通文章页 -->
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Cache-Control" content="max-age=0">
<META http-equiv="Expires" content="0">
<META http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<META name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0">
<META name="Keywords"
	content="育婴之家，儿童之家，育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
<META name="description"
	content="育婴之家是一个分享交流育儿知识经验的 平台，我们致力于需求最好的育儿知识，通过我们这个平台知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">
<TITLE>${objNews.strTitle}-${objProjectMenu.strMenuName }-育婴知识学习交流平台</TITLE>
<LINK href="/m/common/gm/m_article_common.css" rel="stylesheet"
	type="text/css">
<LINK href="/m/common/detail.css" rel="stylesheet" type="text/css">
<SCRIPT src="/m/common/gm/jquery.js"></SCRIPT>
<META name="GENERATOR" content="MSHTML 11.00.9600.17842">
<SCRIPT language="javascript" src="/m/common/gm/js.js"></SCRIPT>
<script type="text/javascript">
$(function(){
	loadMood();
})

function loadMood()
{
	// 加载心情模块。
	$.ajax({
		url : "/front/m/loadMood.do",
		type : "post",
		data : {
			"newsID" : "${objNews.lId}",
		},
		dataType : "json",
		async : true,
		success:function(data)
		{
			if (data != "" && data.length > 0)
			{
				var strHTML = "";
				
				for (var i=0; i < data.length; i++)
				{
					strHTML+= " <li> <p id='hits_1'>"+data[i][2]+"</p> ";
					strHTML+= " <div class='pillar' id='zhu_1' style='height: "+data[i][3]+"px; line-height: 13px;'>&nbsp;</div> ";
					strHTML+= " <a onclick='getMotions(" + data[i][0] + ");'><img src='/m/common/m/m" + data[i][0] + ".gif'> ";
					strHTML+= " <p>" + data[i][1] + "</p></a> </li> ";
				}
				$("#mood_show").html(strHTML);
			}
		}
	});
}
	
// 心情评论
function getMotions(type)
{
	// 心情评论。
	$.ajax({
		url : "/front/m/moodExpress.do",
		type : "post",
		data : {
			"newsID" : "${objNews.lId}",
			"moodType" : type
		},
		dataType : "text",
		async : true,
		success:function(data)
		{
			if (data != "" && data.length > 0)
			{
				if (data == 1)
				{
					// 重新加载心情模块。
					loadMood();
				}
				else if (data == 2)
				{
					alert("您已经表达过心情喽，保持平常心有益于身心健康！");
				}
				else
				{
					alert("似乎无法评论喽，请稍后重试！");
				}
			}
		}
	});
}
</script>
</HEAD>
<BODY>
	<DIV class="nav_header_wrap">
		<A class="nav_header_left" href="/front/m/index.do"><IMG
			src="/m/common/header_yyzj_logo.png"></A><A
			class="nav_header_right" href="/front/m/nav.do"><IMG
			src="/m/common/gm/header_nav_btn.png"></A>
		<DIV class="nav_header_center">
			<A class="nav_header_breadcrumbs"
				href="/front/m/subMenuShow.do?lProjectMenuID=${objNews.objProjectMenu.objParentProjectMenu.lId}"
				target="_self">${objNews.objProjectMenu.objParentProjectMenu.mobileMenuName}</A><A
				class="nav_header_breadcrumbs"
				href="/front/m/newsList.do?lProjectMenuID=${objNews.objProjectMenu.objParentProjectMenu.lId}&menuID=${objNews.objProjectMenu.lId}"
				target="_self">${objNews.objProjectMenu.mobileMenuName}</A>
		</DIV>
		<BR class="clear">
	</DIV>
	<!--正文部分---->
	<DIV class="contentWrapper">
		<DIV class="contentLeft">
			<H1 id="articleTitle">${objNews.strTitle}</H1>
			<DIV id="articleSubtitle"></DIV>
			<DIV id="contentMsg">
				<SPAN id="pubTime">${objNews.strSendDate}</SPAN> <SPAN id="source">育婴之家网</SPAN>
				<SPAN class="fontSize_wrap"><A id="fontPlus"
					href="javascript:;">A+</A><A id="fontMinus" href="javascript:;">A-</A>
					<DIV class="clear"></DIV></SPAN>
			</DIV>
			<DIV id="contentMain">${objNews.strContent}</DIV>
		</DIV>
		<!-- 表情start -->
		<div id="motionsDiv">
			<div id="mood">
				<div>
					<div id="mood_tl"></div>
					<div id="mood_tr">
						<a href="http://www.gmw.cn/motionsdaytop.htm" target="_blank">日</a>/<a
							href="http://www.gmw.cn/motionsweektop.htm" target="_blank">周</a>
					</div>
				</div>
				<div style="clear: both;"></div>
				<ul id="mood_show">
					<!-- mood -->
					
				</ul>
			</div>
			
			<!-- 
			暂不使用
			<div class="list_more">
				<a title="每日排行" href="http://motions.gmw.cn/m_top/day"
					target="_blank">查看每日排行 ?</a>
			</div>
			 -->
		</div>
		<!-- 表情结束 end -->
		<!---- tplid:20577;tplname:文章页下部推荐（新）; pagename:test_recom.htm ---->
		<DIV class="mainlistsbox">
			<DIV class="bar_nav">
				<DIV class="bar_nav_main">
					<A
						href="/front/m/newsListByTag.do?lProjectMenuID=${lProjectMenuID}&newsTag=1"
						atremote="">推荐阅读</A>
				</DIV>
			</DIV>
			<DIV class="nav_content_list">
				<!-- 推荐阅读-->
				<DIV class="nav_content_item">
					<DIV class="list_wrap" style="display: block;">
						<UL class="news_list" style="display: block;">
							<#list lstSuggestReading as objSuggesNews>
								<LI>
									<DIV>
										<A class="list_title_l"
											href="/front/yuyingshi/detail.do?lProjectMenuID=${objSuggesNews.objProjectMenu.objParentProjectMenu.lId }&newsID=${objSuggesNews.lId }">${objSuggesNews.strFormatTitle }</A>
									</DIV>
								</LI>
							</#list>
						</UL>
						<DIV class="list_more">
							<A title="进入更多推荐"
								href="/front/m/newsListByTag.do?lProjectMenuID=${lProjectMenuID}&newsTag=1"
								atremote="">进入更多推荐 ?</A>
						</DIV>
					</DIV>
				</DIV>
			</DIV>
		</DIV>
		<DIV class="mainlistsbox" style="">
			<DIV class="bar_nav">
				<DIV class="bar_nav_main">
					<A
						href="/front/m/newsListByTag.do?lProjectMenuID=${lProjectMenuID}&newsTag=3"
						atremote="">热点排行</A>
				</DIV>
			</DIV>
			<DIV class="nav_content_list">
				<!-- 热点排行-->
				<DIV class="nav_content_item">
					<DIV class="list_wrap" style="display: block;">
						<UL class="news_list" style="display: block;">
							<#list lstNewestMessage as objSuggesNews>
								<LI>
									<DIV>
										<A class="list_title_l"
											href="/front/yuyingshi/detail.do?lProjectMenuID=${objSuggesNews.objProjectMenu.objParentProjectMenu.lId }&newsID=${objSuggesNews.lId }">${objSuggesNews.strFormatTitle }</A>
									</DIV>
								</LI>
							</#list>
						</UL>
						<DIV class="list_more">
							<A title="进入更多热点"
								href="/front/m/newsListByTag.do?lProjectMenuID=${lProjectMenuID}&newsTag=3"
								atremote="">进入更多热点 ?</A>
						</DIV>
					</DIV>
				</DIV>
			</DIV>
		</DIV>

		<!-- search -->
		<DIV class="mainlistsbox" style="margin: 10px 0px 0px;">
			<DIV class="sm_schbox" style="display: block;">
				<FORM id="searchform" action="/front/m/newsSearch.do" method="get">
					<INPUT name="searchText" class="sm_ipt" type="text" value="育婴知识搜索"
						autocomplete="off" onfocus="this.value='';" /> <INPUT
						class="sm_sbt" type="submit" value="搜 索" />
				</FORM>
			</DIV>
		</DIV>
		<DIV class="clear"></DIV>
	</DIV>
	<DIV id="goTop"
		style='background: url("/m/common/top1.png") no-repeat center rgba(0, 0, 0, 0.5); width: 30px; height: 30px; right: 10px; bottom: 50px; position: fixed;'></DIV>
	<DIV style="clear: both;"></DIV>
	<DIV class="foot_nav">
		<DIV class="font_wrap">
			<A href="/front/m/nav.do" atremote="">导航</A>
			<#list lstProjectMenu as projectMenu>
				<A href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }"
					atremote="">${projectMenu.mobileMenuName }</A>
			</#list>
		</DIV>
	</DIV>

	<#include "m_foot.ftl">
</BODY>
</HTML>
