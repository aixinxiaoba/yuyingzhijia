<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(function(){
	loadComments();
});

function comments()
{
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
					
					
					setTimeout(function(){$("#showMsg").text("");}, 2000);
				}
				
				setTimeout(function(){$("#showMsg").text("");}, 2000);
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
					strHTML += "<h4 class='content-listtitle'>";
					strHTML += "<span class='list-title'>" + data[i].strNickName + "</span> <span class='list-date'>"+data[i].createTime+"</span>";
					strHTML += "</h4>"
					strHTML += "<p class='list-content'>"+data[i].content+"</p>"
					strHTML += "</div>";
				}
				$("#commentList").html(strHTML);
				commentsNum = data.length;
			}
			
			$("#commentsNum").text(commentsNum);
		}
	});
}
</script>
<DIV class="lanren box-top">
	<DIV class="pleft">
		<DIV class="search lanren">
			<DIV style="border:0px solid #f99391;">
				当前位置：
				<A href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}" rel="nofollow">首页</A>
				>&nbsp;
				<A href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${objNews.objProjectMenu.lId}" rel="nofollow">${objNews.objProjectMenu.strMenuName}</A>
				>&nbsp;
				正文
			</DIV>
		</DIV>
		<DIV class="viewbox">
			<DIV class="title" style="padding:0px;">
				<H2>
					${objNews.strTitle}
				</H2>
				<DIV class="f12" style="margin:3px;">
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
			
			<%--
			<!-- 分享 start -->
				<div class="bdsharebuttonbox" style="height:30px;">
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
			 --%>
				
				<!-- 评论列表 -->
				<div style="margin-top:10px;margin-bottom: 20px;">评论列表：</div>
				<div id="commentList">
					 
				</div>
				<!-- 评论列表 -->
			<!-- 
			<div class="option" id="row6998404">
					<a class="reply" href="javascript:void(0)" rel="">回复</a>
				</div>
			 -->
			<!-- 评论栏 start 
				-->
				<div style="margin-top: 10px;" class="comments">
					<FORM name="saypl" id="saypl" onsubmit="" action="" method="post">
						<ul>
							<li id="showMsg" style="height:20px;">
								
							</li>	
							<li style="float:left;">发表评论</li>	
							<li style="float:right;">共有<span color="red" id="commentsNum"></span>条评论</li>	
							<li style="margin-top: 10px;float:none;">
								<TEXTAREA name="context" id="context" rows="6"  style="width:99.5%;border: 1px solid rgb(204, 204, 204);"></TEXTAREA>
							</li>
							<li style="margin-top: 10px;float:none;">
								<a href="javascript:comments();" class="comment-txtbottom_a" style="color:#fff;">发表评论</a>
							</li>
						</ul>
					</FORM>
				</div>
			<!-- 评论栏 end-->
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
								target="_blank"><s:property value="strLongTitle"/></A>
						</DD>
					</s:if>
				</s:iterator>
			</DL>
		</DIV>
	</DIV>
</DIV>
<script type="text/javascript"> 
var zhText;
if ($(".content1").text().length > 512)
{
	zhText = $(".content1").text().substr(0,200);//"测试百度文字转语音接口 - 卡卡测速网 www.webkaka.com"; 
}
else
{
	zhText = $(".content1").text();//"测试百度文字转语音接口 - 卡卡测速网 www.webkaka.com"; 

}
zhText = encodeURI(zhText);
document.write("<audio autoplay=\"auhoplay\">");
document.write("<source src=\"http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&spd=4&text="+ zhText +"\" type=\"audio/mpeg\">");
document.write("<embed height=\"0\" width=\"0\" src=\"http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&spd=2&text="+ zhText +"\">");
document.write("</audio>");
</script>
<script>$.ajax( { url:"/front/index/setReadNum.do", type:"post",  data:{"newsID":"${objNews.lId}"}, dataType:"text",  async: true });</script>