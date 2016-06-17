<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META http-equiv="Cache-Control" content="max-age=0">
<META http-equiv="Expires" content="0">
<META http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<META name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0">
<META name="Keywords"
	content="育婴知识分享，分享你最喜欢的育婴知识">
<META name="description"
	content="育婴知识分享，分享你最喜欢的育婴知识">
<TITLE>${objNews.strTitle}-${objProjectMenu.strMenuName }-育婴知识学习交流平台</TITLE>
<LINK href="/m/common/gm/m_article_common.css" rel="stylesheet" type="text/css">
<LINK href="/m/common/detail.css" rel="stylesheet" type="text/css">
<style type="text/css">
.home {
    background: url("/commons/images/top2.png") no-repeat 0% 0% / 46px 184px;
    top: 0px;
    width: 44px;
    height: 44px;
    position: absolute;
    margin-top:-5px;
}

.crumbs_line {
    float: left;
    width: 10px;
    height: 10px;
    border-top: 2px solid #ffe4f2;
    border-right: 2px solid #ffe4f2;
    -moz-transform: rotate(45deg);
    -webkit-transform: rotate(45deg);
    -o-transform: rotate(45deg);
    margin: 12px 7px 0 3px;
}

.comments a {
    font-size: 14px;
    background: #ed6d46;
    color: #fff;
    height: 35px;
    line-height: 35px;
    padding: 0 16px;
    border-radius: 5px;
    display: inline-block;
    margin-left: 16px;
    text-decoration: none;
}

</style>
<SCRIPT src="/m/common/gm/jquery.js"></SCRIPT>
<META name="GENERATOR" content="MSHTML 11.00.9600.17842">
<!-- <SCRIPT language="javascript" src="/m/common/gm/js.js"></SCRIPT> -->
<script type="text/javascript">
$(function(){
	$("#goTop").hide();
	loadNewsList();
	
})

// 评论模块
function showComments()
{
	$("#comments").show();
	loadMood();
	loadComments();
}
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
					strHTML+= " <li> <p id='hits_1' style='color:red;'>"+data[i][2]+"</p> ";
					strHTML+= " <div class='pillar' id='zhu_1' style='height: "+data[i][3]+"px; line-height: 13px;'>&nbsp;</div> ";
					strHTML+= " <a onclick='getMotions(" + data[i][0] + ");'><img src='/m/common/m/m" + data[i][0] + ".gif'> ";
					strHTML+= " <p style='color:#ff6d93;'>" + data[i][1] + "</p></a> </li> ";
				}
				$("#mood_show").html(strHTML);
			}
		}
	});
}
	
// 获取心情评论列表
function getMotions(type)
{
	var size = $("#size_zxdt").val();
	
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


//加载新闻列表。
function loadNewsList()
{
	var size = $("#size_zxdt").val();
	$("#rd_more").hide();
	$("#rd_msg").show();
	// 加载新闻标签。
	$.ajax({
		url : "/front/m/loadNewstList.do",
		type : "post",
		data : {"newsSize":size,"lProjectMenuID":"${objNews.objProjectMenu.objParentProjectMenu.lId}"},
		dataType : "json",
		async : true,
		success:function(data)
		{
			if (data != "" && data.length > 0)
			{
				var strHTML = "";
				
				for (var i=0; i < data.length; i++)
				{
					strHTML+="<LI style='clear:both;'> <DIV>";
					strHTML+="<A class='list_title_l' href='/front/yuyingshi/detail.do?newsID="+data[i].lId+"'>"+data[i].strFormatTitle+"</A>";
					strHTML+=" </DIV> </LI> ";
				}
				
				$("#newstList").html($("#newstList").html() + strHTML);
				size = parseInt(size) + 7;
				$("#size_zxdt").val(size);
				
				if (size > 8)
				{
					$.ajax({
						url : "/front/m/loadProjectMenu.do",
						type : "post",
						data : {"newsSize":size,"curProjectMenuID":"${objNews.objProjectMenu.lId}","lProjectMenuID":"${objNews.objProjectMenu.objParentProjectMenu.lId}"},
						dataType : "json",
						async : true,
						success:function(menuData)
						{
							var strSubHTML = "";
							
							if (menuData != "" && menuData.length > 0)
							{
								strSubHTML+="<LI style='float:left;font-size:14px;border:0px solid red;'>";
								strSubHTML+="&nbsp;&nbsp;<span>推荐栏目：</span>";	
								strSubHTML+="</LI>";
								
								for (var i=0; i < menuData.length; i++)
								{
									strSubHTML+="<LI style='float:left;border:0px solid red;'>";
									strSubHTML+="<A style='font-size:14px;color:#ff6d93;' href='/front/m/newsList.do?menuID="+menuData[i].lId+"'>"+menuData[i].mobileMenuName+"</A>&nbsp;&nbsp;&nbsp;";	
									strSubHTML+="</LI>";
								}
								
								$("#newstList").html($("#newstList").html() + strSubHTML);
							}
						}
					});
				}
			}
			$("#rd_more").show();
			$("#rd_msg").hide();
		}
	});
}

/**
 * 加载同类新闻。
 */
function loadSimilarNewsList()
{
	var size = $("#size_similar_n").val();
	
	$("#more_similar_n").hide();
	$("#msg_similar_n").show();
	// 加载新闻标签。
	$.ajax({
		url : "/front/m/loadSimilarNewstList.do",
		type : "post",
		data : {"newsSize":size,"lProjectMenuID":"${objNews.objProjectMenu.lId}"},
		dataType : "json",
		async : true,
		success:function(data)
		{
			if (data != "" && data.length > 0)
			{
				var strHTML = "";
				
				for (var i=0; i < data.length; i++)
				{
					strHTML+="<LI style='clear:both;'> <DIV>";
					strHTML+="<A class='list_title_l' href='/front/yuyingshi/detail.do?newsID="+data[i].lId+"'>"+data[i].strFormatTitle+"</A>";
					strHTML+=" </DIV> </LI> ";
				}
				
				$("#newstList_s").html($("#newstList_s").html() + strHTML);
				size = parseInt(size) + 7;
				$("#size_similar_n").val(size);
				
				if (size > 7)
				{
					$.ajax({
						url : "/front/m/loadProjectMenu.do",
						type : "post",
						data : {"newsSize":size,"curProjectMenuID":"${objNews.objProjectMenu.lId}","lProjectMenuID":"${objNews.objProjectMenu.objParentProjectMenu.lId}"},
						dataType : "json",
						async : true,
						success:function(menuData)
						{
							var strSubHTML = "";
							
							if (menuData != "" && menuData.length > 0)
							{
								strSubHTML+="<LI style='float:left;font-size:14px;border:0px solid red;'>";
								strSubHTML+="&nbsp;&nbsp;<span>推荐栏目：</span>";	
								strSubHTML+="</LI>";
								
								for (var i=0; i < menuData.length; i++)
								{
									strSubHTML+="<LI style='float:left;border:0px solid red;'>";
									strSubHTML+="<A style='font-size:14px;color:#ff6d93;' href='/front/m/newsList.do?menuID="+menuData[i].lId+"'>"+menuData[i].mobileMenuName+"</A>&nbsp;&nbsp;&nbsp;";	
									strSubHTML+="</LI>";
								}
								
								$("#newstList_s").html($("#newstList_s").html() + strSubHTML);
							}
						}
					});
				}
			}
			$("#more_similar_n").show();
			$("#msg_similar_n").hide();
		}
	});
}

// 评论内容
function comments()
{
	$("#comments_id").hide();
	$("#showMsg").css("color","green");
	$("#showMsg").css("font-size","14px");
	$("#showMsg").text("正在处理...");
	$.ajax({
			url : "/mpf/comments/comments.do",
			type : "post",
			data : {
				"context" : $("#context").val(),
				"lNewsID":"${objNews.lId}"
			},
			dataType : "text",
			async : true,
			success:function(data)
			{
				if (data == 1)
				{
					$("#showMsg").css("color","green");
					$("#showMsg").css("font-size","14px");
					$("#showMsg").text("评论成功！");
					$("#context").val(""); // 清空评论框内容
					loadComments();
				}
				else
				{
					$("#showMsg").css("color","red");
					$("#showMsg").css("font-size","14px");
					
					if (data == 2)
					{
						
						$("#showMsg").text("请登陆后再进行评论！");
					}
					else
					{
						$("#showMsg").text("评论失败！请稍后重试！");
					}
					setTimeout(function(){$("#showMsg").text("");$("#comments_id").fadeIn(500);}, 2000);
				}
				setTimeout(function(){$("#showMsg").text("");$("#comments_id").fadeIn(500);}, 2000);
			}
		});
}

// 网友评论内容。
function loadComments()
{
	$.ajax({
		url : "/mpf/comments/showComments.do",
		type : "post",
		data : {
			"lNewsID":"${objNews.lId}"
		},
		dataType : "json",
		async : true,
		success:function(data)
		{
			var commentsNum = 0;
			
			if (data != null && data.length > 0)
			{
				var strHTML = "";
				
				for (var i = 0; i < data.length; i++)
				{
					// 如果为登陆用户评论 则显示对应的用户昵称。
					strHTML += "<div class='comment-list-content' style='margin-bottom:10px;'>";
					strHTML += "<h4 class='content-listtitle' style='line-height:10px;'>";
					strHTML += "<span class='list-title'>" + data[i].strNickName + "</span> <span class='list-date'>"+data[i].createTime+"</span>";
					strHTML += "</h4>"
					strHTML += "<p class='list-content' style='line-height:0px;'>"+data[i].content+"</p>"
					strHTML += "</div>";
				}
				$("#commentList").html(strHTML);
				commentsNum = data.length;
			}
			else
			{
				$("#commentList").html("暂无评论，快来抢沙发吧！");
			}
			
			$("#commentsNum").text(commentsNum);
		}
	});
}

</script>
</HEAD>
<BODY class="bg_1">
	<div>
		<DIV class="nav_header_wrap_fix">
			<A class="nav_header_right" href="/front/m/nav.do"><IMG src="/m/common/gm/header_nav_btn.png"></A>
			<DIV class="nav_header_center_n" style="color:#fff">
<!-- 				<a class="home" href="/front/m/index.do"></a> -->
				 <a class="home" href="/static/m/index.html"></a>
			   <div style="margin-left:50px;">
			   	
<%-- 			   	<A style="font-size:18px;float:left;" class="nav_header_breadcrumbs" href="/static/m/${objNews.objProjectMenu.objParentProjectMenu.lId}.html" --%>
<%-- 					target="_self">${objNews.objProjectMenu.objParentProjectMenu.mobileMenuName}</A> --%>
					
			   	<A style="font-size:18px;float:left;" class="nav_header_breadcrumbs" href="/front/m/subMenuShow.do?lProjectMenuID=${objNews.objProjectMenu.objParentProjectMenu.lId}"
					target="_self">${objNews.objProjectMenu.objParentProjectMenu.mobileMenuName}</A>
					
			   	 <%--
			   	 <A style="font-size:18px;float:left;" class="nav_header_breadcrumbs" href="/static/m/${objNews.objProjectMenu.objParentProjectMenu.menuKey}"
					target="_self">${objNews.objProjectMenu.objParentProjectMenu.mobileMenuName}</A>--%>
				<div class="crumbs_line"></div>
				<A style="font-size:18px;float:left;" class="nav_header_breadcrumbs"
					href="/front/m/newsList.do?lProjectMenuID=${objNews.objProjectMenu.objParentProjectMenu.lId}&menuID=${objNews.objProjectMenu.lId}"
					target="_self">${objNews.objProjectMenu.mobileMenuName}</A>
				<div class="crumbs_line"></div>
				<a  style="font-size:18px;float:left;" class="nav_header_breadcrumbs"> 正文</a>
			   </div>
			</DIV>
			
			<BR class="clear">
		</DIV>
		
		<!--正文部分---->
		<DIV class="contentWrapper_fix">
			<DIV class="boxcontent">
				<div class="boxcontent_txt"><H1 id="articleTitle" style="color: #fe49a8;">${objNews.strTitle}</H1></div>
				<DIV id="articleSubtitle"></DIV>
				<DIV id="contentMsg">
					<SPAN id="pubTime"><!-- ${objNews.strSendDate} --></SPAN> <SPAN id="source">育婴之家网</SPAN>
					<SPAN class="fontSize_wrap">
						<A id="fontPlus" href="javascript:;">A+</A>
						<A id="fontMinus" href="javascript:;">A-</A>
					</SPAN>
				</DIV>
				<DIV id="contentMain"  style="">${objNews.strContent}</DIV>
			</DIV>
			<div style="clear: both;"></div>
			
			<!-- 发表评论 start -->
			<DIV class="bar_nav" style="margin-bottom:15px;">
				<DIV class="bar_nav_main" style="font-size:16px;">
					<A href="javascript:showComments();" atremote="">
						发表评论
					</A>
				</DIV>
			</DIV>
			
			<!-- 发表评论 end -->
			<div style="display:none" id="comments">
				<div class="boxcontent">
					<FORM name="saypl" id="saypl" onsubmit="" action="" method="post">
						<ul id="comments_msg"><li id="showMsg" style=""></li></ul>
						<ul id="comments_id">
							<li style="margin-left:-35px;">
								<TEXTAREA name="context" id="context" rows="2"  style="width:97%;border: 1px solid rgb(204, 204, 204);font-size:18px; color:#F00"></TEXTAREA>
							</li>
							<li style="margin-top: 10px;margin-left: -50px;" class="comments">
								<a href="javascript:comments();" class="comment-txtbottom_a" style="color:#fff;">发表评论</a>
							</li>
						</ul>
					</FORM>
				</div>
				<!-- 评论列表展示 start -->
				<DIV class="bar_nav" style="margin-bottom:15px;">
					<DIV class="bar_nav_main" style="font-size:16px;">
						<A href="javascript:void(0)" atremote="">
							评论列表： &nbsp;&nbsp;共有<span id="commentsNum"></span>条评论
						</A>
					</DIV>
				</DIV>
				
				<div  class="boxcontent">
					<!-- 评论列表 -->
					<div id="commentList" style="margin:10px;"></div>
				</div>
				<!-- 评论列表展示 end -->
				
				<!-- start -->
				<DIV class="bar_nav" style="margin-bottom:15px;">
					<DIV class="bar_nav_main" style="font-size:16px;">
						<A href="javascript:void(0)" atremote="">
							读后感<span style="font-size:14px;"><!--（找到志同道合的朋友）--></span>
						</A>
					</DIV>
				</DIV>
				
				<div id="motionsDiv" class="boxcontent">
					<div id="mood">
						<div style="clear: both;"></div>
						<ul id="mood_show">
							<!-- mood -->
						</ul>
					</div>
					<!-- 
					<div class="list_more">
						<A title="每日排行" href="javascript:loadSimilarNewsList();" atremote="" id="more_similar_n">
								 查看每日排行 ?</A>
					</div>
					 -->
				</div>
				<!-- 表情结束 end -->
				
			</div>
			<!-- start -->
			<DIV class="bar_nav">
				<DIV class="bar_nav_main" style="font-size:16px;">
					<A href="/front/m/newsList.do?menuID=${objNews.objProjectMenu.lId}"
						atremote="">同类阅读<span style="font-size:14px;">（多一次阅读，多一次收获）</span></A>
				</DIV>
			</DIV>
			<DIV class="boxcontent">
				<!-- 热点排行-->
				<DIV class="nav_content_item">
					<DIV class="list_wrap" style="display: block;">
						<UL class="news_list" style="display: block;" id="newstList_s">
							<li>
								<s:if test="objUpNews != null">
									<span style="color: rgb(186, 186, 186);font-size:14px">&nbsp;上一篇：</span>
									<A class="list_title_l" style="padding-left:0px;" href="/front/yuyingshi/detail.do?newsID=${objUpNews.lId }">
									${objUpNews.strFormatTitle }</A>
								</s:if>
								<s:else>
									已经是第一篇了！
								</s:else>
							</li>
							<li>
								<span style="color: rgb(186, 186, 186);font-size:14px">&nbsp;下一篇：</span>
								<s:if test="objNextNews != null">
									<A class="list_title_l" style="padding-left:0px;" href="/front/yuyingshi/detail.do?newsID=${objNextNews.lId }">
									${objNextNews.strFormatTitle }</A>
								</s:if>
								<s:else>
									<span style="font-size:14px;">恭喜你已阅读到最后一条记录！</span>
								</s:else>
							</li>
						</UL>
						<DIV class="list_more">
							<input type="hidden" id="size_similar_n" value="0"/>
							<!-- 
							<A title="查看更多" href="/front/m/newsList.do?menuID=${objNews.objProjectMenu.lId}" atremote="">
							 查看更多 <span style="color:#ff6d93">${objNews.objProjectMenu.mobileMenuName}</span> ?</A>
							 -->
							<A title="查看更多" href="javascript:loadSimilarNewsList();" atremote="" id="more_similar_n">
							 查看更多 ?</A>
							 <div style="text-align: center;display: none;" id="msg_similar_n">请稍后...</div>
						</DIV>
					</DIV>
				</DIV>
			</DIV>
			<!-- end -->
			
			<DIV class="bar_nav">
				<DIV class="bar_nav_main"  style="font-size:16px;">
					<A href="/front/m/newsestList.do?lProjectMenuID=${objNews.objProjectMenu.objParentProjectMenu.lId}" atremote="">最新动态<span style="font-size:14px;">（知识早知道，育儿无烦恼）</span></A>
				</DIV>
			</DIV>
			<DIV class="boxcontent">
				<!-- 热点排行-->
				<DIV class="nav_content_item">
					<DIV class="list_wrap" style="display: block;">
						<!-- <li style="text-align:center;">正在加载...</li> -->
						<UL class="news_list" style="display: block;" id="newstList">
							
						</UL>
						
						<DIV class="list_more">
							<input type="hidden" id="size_zxdt" value="0"/>
							<A title="查看更多" href="javascript:loadNewsList();" atremote="" id="rd_more">查看更多 ?</A>
							<div style="text-align: center;display: none;" id="rd_msg">等一会，精彩继续...</div>
						</DIV>
					</DIV>
				</DIV>
			</DIV>
			
			<%--
			<div id="listModel">
				<DIV class="mainlistsbox" >
					<DIV class="bar_nav">
						<DIV class="bar_nav_main" >
							<A href="/front/m/newsListByTag.do?lProjectMenuID=${lProjectMenuID}&newsTag=1"
								atremote=""  style="font-size:16px;">推荐阅读
								<span style="font-size:14px;">（多学一点是一点！go on）</span>
								</A>
						</DIV>
					</DIV>
					<DIV class="nav_content_list">
						<!-- 推荐阅读-->
						<DIV class="nav_content_item">
							<DIV class="list_wrap" style="display: block;">
								<UL class="news_list" style="display: block;">
									<s:iterator value="lstSuggestReading" id="objSuggesNews">
										<LI>
											<DIV>
												<A class="list_title_l"
													href="/front/yuyingshi/detail.do?lProjectMenuID=${objSuggesNews.objProjectMenu.objParentProjectMenu.lId }&newsID=${objSuggesNews.lId }">${objSuggesNews.strFormatTitle }</A>
											</DIV>
										</LI>
									</s:iterator>
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
				</DIV>
			</div>
			 --%>
	
			<!-- search -->
			<DIV class="mainlistsbox" style="margin: 10px 0px 0px;">
				<DIV class="sm_schbox" style="display: block;">
					<FORM id="searchform" action="/front/m/newsSearch.do" method="get">
						<INPUT name="searchText" class="sm_ipt" type="text" value="育婴知识搜索"
							autocomplete="off" onfocus="this.value='';" /> <INPUT
							class="sm_sbt" type="submit" value="搜 索" />
					</FORM>
				</DIV>
			</DIV> <%----%>
			<DIV class="clear"></DIV>
		</DIV>
		<DIV id="goTop" style='background: url("/m/common/top1.png") no-repeat center rgba(0, 0, 0, 0.5); width: 30px; height: 30px; right: 10px; bottom: 50px; position: fixed;'></DIV>
		<DIV style="clear: both;"></DIV>
		<DIV class="foot_nav" onclick="location.href='/static/m/nav.html?a=1'">
			<DIV class="font_wrap">
				<A>查看更多分类</A>
				<%---
				<A href="/static/m/index.html" atremote="">首页</A>
				<!-- 
				<A href="/front/m/nav.do" atremote="">全站导航</A>
				<A href="m.yuyingzhijia.cn" atremote="">首页</A>
				 -->
				<s:iterator value="lstProjectMenu" id="projectMenu">
					<A href="/front/m/newsList.do?lProjectMenuID=${objNews.objProjectMenu.objParentProjectMenu.lId}&menuID=${projectMenu.lId }"
						atremote="">${projectMenu.mobileMenuName }</A>
				</s:iterator>
				 --%>
			</DIV>
		</DIV>
		<!-- 设置文章阅读数量 -->
		<script>$.ajax( { url:"/front/index/setReadNum.do", type:"post",  data:{"newsID":"${objNews.lId}"}, dataType:"text",  async: true });</script>
		<%@include file="foot.jsp"%>
	</div>	
<!-- 百度统计代码开始 -->
<!-- 
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?3b117244a311332d9a3a45a4c7185dbb";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
 -->
<!-- 百度统计代码结束 -->

</BODY>
</HTML>
