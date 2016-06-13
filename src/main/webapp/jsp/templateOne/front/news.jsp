<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>${objProject.strPname}-${objNews.strTitle}</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<META name="Keywords"
			content="育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
<META name="description"
			content="育婴之家是一个分享交流育儿知识经验的平台，我们致力于提供最好的育儿知识，通过我们这个平台将知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">

<link rel="shortcut icon" href="/favicon.ico?version=3">
<LINK rel="stylesheet" type="text/css" href="/commons/housekeeper/images/Css.css" />
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/index_css/index_123.css">
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/index_css/jquery.min.js">
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/jquery.kinMaxShow-1.0.min1.js ">
<SCRIPT type=text/javascript
	src="/commons/housekeeper/images/AutoPic.js"></SCRIPT>
	<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
	
<style type="">

</style>
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
</HEAD>
<BODY>

	<%@ include file="../../common/head.jsp" %>
	<TABLE style="MARGIN-TOP: 23px" cellSpacing=0 cellPadding=0 width=992
			align=center border=0>
			<TBODY>
				<TR>
					<TD vAlign=top>
						<TABLE cellSpacing=0 cellPadding=0  bgColor=#ffffff
							border=0>
							<TBODY>
								<TR>
									<TD style="BACKGROUND: url(/commons/housekeeper/images/d_top.jpg);MARGIN-TOP: 14px;padding-left: 40px;width:992px;" height=37>
										<span style='font-size:14px;font-weight:bold;color:#a66000'>${objNews.objProjectMenu.strMenuName}</span>
									</TD>
								</TR>
								<TR>
									<TD height=700 vAlign=top style="line-height:22px">
										<TABLE style="MARGIN-BOTTOM: 30px; MARGIN-TOP: 30px"
											cellSpacing=0 cellPadding=0 width="85%" align=center border=0>
											<TBODY>
												<TR>
													<TD>
														<div style="margin-top: 10px;" id="formbox">
																<div style="text-align: center;margin:10px;">
																	<span class="label" id="span_${proFileInfo.strPfidentity}"
																	style="font-size:25px;font-style:normal;font-variant:normal;font-weight:bold;font-size-adjust:none;font-family:宋体;color:orange"
																	
																	>${objNews.strTitle}</span>
																</div>
																<div style="padding: 10px;text-align:center;color:#333">
															          发表时间：${objNews.strSendDate}
															      <!-- start -->    
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
																<!-- end -->
																
															    </div>
														       <div style="border-top:2px solid #ccc;">
															        <div style="margin-top: 8px;color:blue;font-size:12px;">
															          ${objNews.strContent}
															        </div>
														       </div>
														       	<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
														       
														</div>
														
														<!-- 
														<div style="margin-top: 40px;margin-left:20px;text-align: center;">
															<img src="/commons/image/s8.gif" width="58" height="22" style="cursor: pointer;" onclick="history.back()">
														</div>
														 -->
														 
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
													</div>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD height=5 ></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width=992 align=center border=0>
			<TBODY>
				<TR>
					<TD height=80>
						<TABLE cellSpacing=0 cellPadding=0 width=992 border=0>
							<TBODY>
								<TR>
									<TD>
										<P>
											Copyright
											<A href="javascript:void(0);"><FONT color=#800080>www.${objProject.projectKey}.cn</FONT>
											</A>&nbsp;All Rights Reserved 版权所有
										</P>
									</TD>
									<TD width=290 align=right>
										${objProject.strPname}
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
</BODY>
</HTML>

