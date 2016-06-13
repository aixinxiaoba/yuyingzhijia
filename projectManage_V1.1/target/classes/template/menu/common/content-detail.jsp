<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>


<script>

$(document).ready(function() {
	setReadNum();
})

// 设置访问日志。
function setReadNum()
{
	var strURL = "/front/index/setReadNum.do";
	
	$.ajax(
	{
        url:strURL,
        type:"post",
        data:{"newsID":${objNews.lId}},
        dataType:"text",
        async: false,
        success:function(responseText)
        {	           
			
        }
    });
}
</script>

<DIV class="lanren box-top">

	<DIV class="search lanren">
		<DIV class="">
			当前位置：
			<A href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}" rel="nofollow">首页</A>
			&nbsp;>
			<A href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${objNews.objProjectMenu.lId}" rel="nofollow">${objNews.objProjectMenu.strMenuName}</A>
			&nbsp;>
			正文
		</DIV>
	</DIV>
	
	<DIV class="pleft">
		<DIV class="viewbox">
			<DIV class="title" style="padding:0px;">
				<H2>
					${objNews.strTitle}
				</H2>
				
				<!-- 分享 start -->
				<div class="bdsharebuttonbox">
					<a class="bds_more" href="" data-cmd="more"></a><a title="分享到QQ空间"
						class="bds_qzone" href="www.baidu.com" data-cmd="qzone"></a><a title="分享到新浪微博"
						class="bds_tsina" href="#" data-cmd="tsina"></a><a title="分享到腾讯微博"
						class="bds_tqq" href="#" data-cmd="tqq"></a><a title="分享到人人网"
						class="bds_renren" href="#" data-cmd="renren"></a><a title="分享到微信"
						class="bds_weixin" href="#" data-cmd="weixin"></a>
				</div>
				<script>
					window._bd_share_config = {
						"common" : {
							"bdSnsKey" : {
								"tsina" : "育婴之家",
								"tqq" : "育婴之家",
								"t163" : "育婴之家",
								"tsohu" : "育婴之家"
							},
							"bdText" : "育婴之家",
							"bdMini" : "2",
							"bdMiniList" : false,
							"bdPic" : "育婴之家",
							"bdStyle" : "0",
							"bdSize" : "16"
						},
						"share" : {},
						"image" : {
							"viewList" : [ "qzone", "tsina", "tqq", "renren", "weixin" ],
							"viewText" : "分享到：",
							"viewSize" : "16"
						},
						"selectShare" : {
							"bdContainerClass" : null,
							"bdSelectMiniList" : [ "qzone", "tsina", "tqq", "renren",
									"weixin" ]
						}
					};
					with (document)
						0[(getElementsByTagName('head')[0] || body)
								.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
								+ ~(-new Date() / 36e5)];
				</script>
				<!-- 分享 end -->
				<DIV class="f12" style="margin:0px;">
					<SPAN class="l">作者：
						<A href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}" target="_blank">育婴之家</A>&nbsp;&nbsp;(转载请注明出处，并添加链接地址)</SPAN>
						<SPAN class="r">发布日期：${objNews.strSendDate}</SPAN>
						<span class="r">点击次数：${objNews.readNum + 1}&nbsp;</span>
				</DIV>
			</DIV>
			<!-- /title -->
			<DIV class="content1">
				${objNews.strContent}
			</DIV>
			
			<!-- 分享 start -->
				<div class="bdsharebuttonbox">
					<a class="bds_more" href="" data-cmd="more"></a><a title="分享到QQ空间"
						class="bds_qzone" href="www.baidu.com" data-cmd="qzone"></a><a title="分享到新浪微博"
						class="bds_tsina" href="#" data-cmd="tsina"></a><a title="分享到腾讯微博"
						class="bds_tqq" href="#" data-cmd="tqq"></a><a title="分享到人人网"
						class="bds_renren" href="#" data-cmd="renren"></a><a title="分享到微信"
						class="bds_weixin" href="#" data-cmd="weixin"></a>
				</div>
				<script>
					window._bd_share_config = {
						"common" : {
							"bdSnsKey" : {
								"tsina" : "育婴之家",
								"tqq" : "育婴之家",
								"t163" : "育婴之家",
								"tsohu" : "育婴之家"
							},
							"bdText" : "育婴之家",
							"bdMini" : "2",
							"bdMiniList" : false,
							"bdPic" : "育婴之家",
							"bdStyle" : "0",
							"bdSize" : "16"
						},
						"share" : {},
						"image" : {
							"viewList" : [ "qzone", "tsina", "tqq", "renren", "weixin" ],
							"viewText" : "分享到：",
							"viewSize" : "16"
						},
						"selectShare" : {
							"bdContainerClass" : null,
							"bdSelectMiniList" : [ "qzone", "tsina", "tqq", "renren",
									"weixin" ]
						}
					};
					with (document)
						0[(getElementsByTagName('head')[0] || body)
								.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
								+ ~(-new Date() / 36e5)];
				</script>
				<!-- 分享 end -->
			<DIV class="handle">
				<DIV class="context">
					<UL>
						<LI>
								上一篇：
							<s:if test="objUpNews != null">
								<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.lId}&newsID=<s:property value="objUpNews.lId"/>"><s:property value="objUpNews.strLongTitleTwo"/></A>
							</s:if>
							<s:else>
								没有了
							</s:else>
						</LI>
						<LI>
							下一篇：
							<s:if test="objNextNews != null">
								<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.lId}&newsID=<s:property value="objNextNews.lId"/>"><s:property value="objNextNews.strLongTitleTwo"/></A>
							</s:if>
							<s:else>
								没有了
							</s:else>
						</LI>
					</UL>
				</DIV>
				<!-- /context -->
				<!-- 
				<DIV class="actbox">
					<UL class="downurllist">
						<LI>
							<A href="http://d.lanrenzhijia.com/2015/scale0417"
								target="_blank" rel="nofollow">在线预览</A>
						</LI>

						<LI>
							<A href="http://d.lanrenzhijia.com/2015/scale0417/scale0417.rar">本地下载</A>
						</LI>
					</UL>
				</DIV>
				 -->
				<!-- /actbox -->
			</DIV>
			<!-- /handle -->
		</DIV>
	</DIV>
	<!-- /pleft -->

	<DIV class="list-js">
		<DIV class="top">
			<SPAN class="l">标签墙</SPAN><SPAN class="r"><!-- 
			<A
				href="http://www.lanrenzhijia.com/js/" target="_blank"><IMG
						width="26" height="26" alt="育婴之家"
						src="/commons/yuyingshi/images/more.png">
			</A>
			 --> </SPAN>
		</DIV>
		<!-- 详细页右侧导航begin -->
		<DIV class="nav-sub">
			<s:iterator value="lstProjectMenu" id="projectMenu" >
				<A href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${lId}" <s:if test="lId==objNews.objProjectMenu.lId">class='thisclass'</s:if>><s:property value="strMenuName"/></A>
			</s:iterator>
		</DIV>
		<!-- 详细页右侧导航end -->
		
		<DIV class="ads_right">
			<DIV class="top">
				<SPAN class="l">最新更新</SPAN><SPAN class="r"><A
					href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1"><IMG
							width="26" height="26" alt="育婴之家"
							src="/commons/yuyingshi/images/more.png">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all2">
				<s:iterator value="lstNewestMessage" id="news">
					<LI>
						<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"><s:property value="strLongTitle"/></A><EM></EM>
					</LI>
				</s:iterator>
			</UL>
		</DIV>
		
		<DIV class="hot">
			<DL>
				<DT>
					推荐阅读
				</DT>
				<s:iterator value="lstSuggestReading" id="news" status="myStatus">
					<s:if test="#myStatus.index < 20">
						<DD>
							<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"
								target="_blank"><s:property value="strLongTitleTwo"/></A>
						</DD>
					</s:if>
				</s:iterator>
			</DL>
		</DIV>
	</DIV>
</DIV>