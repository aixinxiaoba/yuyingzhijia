<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<STYLE type="text/css">
.list {
	position: relative;
}

.list .slides {
	width: 350px;
	height: 374px;
	overflow: hidden;
	position: relative;
}

.list .slides .slide-pic li {
	float: left;
	_display: inline;
	width: 350px;
}

.list .slide-li {
	bottom: 8px;
	cursor: pointer;
	position: absolute;
	height: 17px;
	text-align: center;
	width: 175px;
	margin-left: 80px;
	z-index: 100;
}

.list .fi_btn {
	cursor: pointer;
	display: inline-block;
	height: 48px;
	overflow: hidden;
	position: absolute;
	top: 140px;
	width: 36px;
	z-index: 1;
}

.list .l {
	left: 0;
}

.list .r {
	right: 0;
}

.list .l_view {
	background: url("/commons/yuyingshi/images/icon_left.png") no-repeat 0 0;
	_background: url("/commons/yuyingshi/images/icon_left_8bit.png") no-repeat 0 0;
}

.list .bg_view {
	display: block;
	height: 48px;
	width: 36px;
}

.list .r_view {
	background: url("/commons/yuyingshi/images/icon_right.png") no-repeat 0 0;
	_background: url("/commons/yuyingshi/images/icon_right_8bit.png") no-repeat 0 0;
}

.list a {
	color: #515151;
}

.list .slide-pic li {
	position: relative;
	height: 374px;
	overflow: hidden;
}

.list .slide-pic li .bgtext {
	height: 58px;
	width: 350px;
	background-color: #000;
	-moz-opacity: .65;
	opacity: .65;
	filter: alpha(opacity = 65);
	z-index: 1;
	position: absolute;
	left: 0;
	bottom: 0;
}

.list .slide-pic li .title {
	width: 275px;
	text-align: center;
	font-size: 18px;
	color: #fff;
	position: absolute;
	left: 0;
	bottom: 0;
	height: 50px;
	z-index: 10;
}

.list .slide-pic li .title a {
	color: #fff;
	white-space: nowrap;
}

.slide-li li {
	float: left;
	_display: inline;
	background: url("/commons/yuyingshi/images/icon_noton.png") no-repeat 0 0;
	_background: url("/commons/yuyingshi/images/icon_noton_8bit.png") no-repeat 0 0;
	cursor: pointer;
	font-size: 0;
	height: 17px;
	margin: 0 3px;
	overflow: hidden;
	width: 17px;
}

.slide-li li.cur {
	background: url("/commons/yuyingshi/images/icon_on.png") no-repeat 0 0;
	_background: url("/commons/yuyingshi/images/icon_on_8bit.png") no-repeat 0 0;
	font-size: 0;
	line-height: 500px;
}

.op li {
	float: left;
	_display: inline;
	background: url("/commons/yuyingshi/images/icon_noton.png") no-repeat 0 0;
	_background: url("/commons/yuyingshi/images/icon_noton_8bit.png") no-repeat 0 0;
	cursor: pointer;
	font-size: 0;
	height: 17px;
	margin: 0 3px;
	overflow: hidden;
	width: 17px;
}

.op li.cur {
	background: url("/commons/yuyingshi/images/icon_on.png") no-repeat 0 0;
	_background: url("/commons/yuyingshi/images/icon_on_8bit.png") no-repeat 0 0;
	font-size: 0;
	line-height: 500px;
}

#all3 li:hover {
/*     background: url("../images/l_ico.gif") no-repeat 6px rgb(242, 242, 242); */
    border-top-color: rgb(217, 217, 217);
    border-bottom-color: rgb(217, 217, 217);
    border-top-width: 1px;
    border-bottom-width: 1px;
    border-top-style: dashed;
    border-bottom-style: dashed;
}

.u1 li{ width:342px; height:140px; overflow:hidden; position:relative; float:left; /*margin:0 12px 12px 0;*/}
.u1 li img{ position:absolute; left:0; top:0; z-index:1; cursor:pointer;}
.u1 li h2{ font-size:14px; width:100%; font-weight:normal; text-align:left; display:block; position:absolute; left:0; bottom:0; height:30px; line-height:30px; padding:0 10px; background:url(http://demo.lanrenzhijia.com/2014/tupian1212/images/dot.png) repeat; display:block; z-index:2; color:#444;overflow:hidden; white-space: nowrap;text-overflow: ellipsis;}

</STYLE>
<SCRIPT type="text/javascript">
	$(function() {
		$(".right dl dd:eq(0)").addClass("on");
		$(".right dl dd:eq(1)").addClass("on");
		$(".right dl dd:eq(2)").addClass("on");
		$(".right dl dd:eq(7)").addClass("nbd");
		});
</SCRIPT>
<script>
$(function(){
	var imgs = $('.u1 li');
	var imgWidth = 342; //图片的默认宽度，主意不要带单位
	var imgHeight = 140; //图片的默认高度，主意不要带单位
	imgs.hover(function(){
		$(this).find('img').stop().animate({
			left:'-20',
			top:'-20',
			right:'-20',
			bottom:'-20px',
			width:imgWidth+20+'px',
			height:imgHeight+20+'px'
		},500);
	},function(){
		$(this).find('img').stop().animate({
			left:'0',
			top:'0',
			right:'0',
			bottom:'0',
			width:imgWidth+'px',
			height:imgHeight+'px'
		},500);
	});
});
</script>
<div id="content">
	<DIV class="lanren box-top">
		<!-- 最新更新 begin -->
		<%--	<DIV class="list">"list"--%>
		<%--临时注释 --%>
		
		<DIV class="list" style="width: 275px;">
			<DIV class="l fi_btn">
				<A class="l_view bg_view" href="javascript:void(0);"
					target="_self"></A>
			</DIV>
			<DIV class="slides">
				<UL class="slide-pic center_view"
					width="10000px;right:275px;position:relative;">
					<s:iterator value="lstRollingOfReading" id="news">
						<LI class="cur" style="display: list-item;">
							<A style="white-space: nowrap;" href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank">
								<IMG width="275" height="374" alt="${news.strTitle}" src="${news.imageUrl}">
							</A>
							<DIV class="bgtext"></DIV>
							<DIV class="title">
								<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank">${news.strTitle}</A>
							</DIV>
						</LI>
						<!-- 默认显示第一张图片 -->
						<!-- 
						<s:iterator value="lstAttachs" id="attachs" status="myStatus">
							<s:if test="#myStatus.index == 0">
								<LI class="cur" style="display: list-item;">
									<A style="white-space: nowrap;" href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank">
										<IMG width="275" height="374" alt="${strTitle}" src="${path}">
									</A>
									<DIV class="bgtext"></DIV>
									<DIV class="title">
										<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank">${strTitle}</A>
									</DIV>
								</LI>
							</s:if>
						</s:iterator>
						 -->
					</s:iterator>
				</UL>
				<UL class="slide-li op">
					<LI class="cur"></LI>
					<LI></LI>
					<LI></LI>
					<LI></LI>
					<LI></LI>
				</UL>
				<UL class="slide-txt slide-li">
					<LI class="cur"></LI>
					<LI></LI>
					<LI></LI>
					<LI></LI>
					<LI></LI>
				</UL>
			</DIV>
			 <DIV class="r fi_btn">
				<A class="r_view bg_view" href="javascript:void(0);"
					target="_self"></A>
			</DIV>
		</DIV>
		
		
	<!-- 最新更新 end -->
	<!-- 推荐阅读 begin -->
	<!-- 临时注释 -->
	<%----%>
	<DIV class="list line" style="width: 435px;">
			<DIV class="top">
				<SPAN class="l">最新动态</SPAN>
				<SPAN class="r">
					<!-- 最新动态暂时不提供more链接 -->
					<!-- 
					<A href="/front/index/newsListByTag.do?projectKey=${objProject.projectKey }&newsTag=-1" target="_blank"><IMG
							width="26" height="26" alt="育婴之家推荐阅读"
							src="/commons/yuyingshi/images/more.png">
					</A>
					 -->
				</SPAN>
			</DIV>
			<UL class="box" id="all2">
				<s:iterator value="lstNewestMessage" id="news">
					<LI>
						<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank"><s:property value="strLongTitleTwo"/></A><EM><s:property value="strSendDateShort"/></EM>
					</LI>
				</s:iterator>
			</UL>
			<DIV class="top">
				<SPAN class="l">推荐阅读</SPAN><SPAN class="r"><A
					href="/front/index/newsListByTag.do?projectKey=${objProject.projectKey }&newsTag=1" target="_blank"><IMG
							width="26" height="26" alt="育婴之家推荐阅读"
							src="/commons/yuyingshi/images/more.png">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all2">
				<s:iterator value="lstSuggestReading" id="news" status="myStatus">
					<s:if test="#myStatus.index < 7">
						<LI>
							<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank"><s:property value="strLongTitleTwo"/></A><EM><s:property value="strSendDateShort"/></EM>
						</LI>
					</s:if>
				</s:iterator>
			</UL>
		</DIV>
		<!-- 推荐阅读 end -->
		<!--  begin -->
		<DIV class="css3">
			<DIV class="top" id="no-border">
				<SPAN class="l">阅读排行</SPAN><SPAN class="r"><A
					href="/front/index/newsListByTag.do?projectKey=${objProject.projectKey }&newsTag=1" target="_blank"><IMG
							width="26" height="26" alt="阅读排行"
							src="/commons/yuyingshi/images/more2.gif">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all">
				<s:iterator value="lstTopOfReading" id="news" status="myStatus">
					<s:if test="#myStatus.index < 6">
						<LI>
							<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank"><s:property value="strLongTitleTwo"/></A><EM><s:property value="strSendDateShort"/></EM>
						</LI>
					</s:if>
				</s:iterator>
			</UL>
			
			<!-- 边缘菜单显示 -->
			<s:iterator value="lstChildMenu" id="slideChildMenu" status="parentStatus">	
				<s:if test="#parentStatus.index == 0">
					<DIV class="top" id="no-border">
						<SPAN class="l">${strMenuName}</SPAN><SPAN class="r">
							<A
								href="/front/yuyingshi/newsList.do?lProjectMenuID=${slideChildMenu.objParentProjectMenu.lId}&menuID=${slideChildMenu.lId }" target="_blank"><IMG
										width="26" height="26" alt="${strMenuName}"
										src="/commons/yuyingshi/images/more2.gif">
							</A>
						</SPAN>
					</DIV>
					<UL class="box" id="all">
						<s:iterator value="lstNews" status="myStatus">
							<s:if test="#myStatus.index < 6">
								<LI>
									<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank"><s:property value="strLongTitleTwo"/></A><EM><s:property value="strSendDateShort"/></EM>
								</LI>
							</s:if>
						</s:iterator>
					</UL>
				</s:if>
			</s:iterator>
		</DIV>
		<!--  end -->
	</DIV>
	 
	<DIV class="lanren box-top2">
		<!-- 左边设置start -->
		<DIV class="left">
			<s:iterator value="lstProjectMenu" id="parentProjectMenu" >
				<!-- 过滤掉关于我们菜单 -->
				<s:if test="menuKey != 'aboutUs'">
					<!-- 中间菜单 begin -->
					<DIV class="texiao">
						<DIV class="head">
							<A class="logo" href="${menuURL}">${strMenuName}</A>
							<A class="more" href="${menuURL}" target="_blank"><IMG width="26" height="26" alt="more" src="/commons/yuyingshi/images/more2.gif"></A>
							
							<!-- 子菜单循环开始 begin -->
							<s:iterator value="lstChildrenProjectMenu" id="projectMenu" status="status">
								<!-- 显示状态为0的菜单 -->
								<s:if test="showIndex==0 && menuKey!='zhaopinzhuanqu'">
									<A class="column" href="/front/yuyingshi/newsList.do?lProjectMenuID=${parentProjectMenu.lId}&menuID=${projectMenu.lId }" target="_blank"><s:property value="strMenuName"/></A>
								</s:if>
							</s:iterator>
						</DIV>
						<DIV class="container">
						
							<!-- 子菜单循环开始 begin -->
							<s:iterator value="lstChildrenProjectMenu" id="childProjectMenu" status="status">
								<!-- 判断子菜单是否在首页显示 -->
								<s:if test="showIndex==1">
									<s:if test="#status.odd">
										<DIV class="content">
											<DIV class="top">
												<SPAN class="l"><s:property value="strMenuName"/></SPAN><SPAN class="r"><A
													href="/front/yuyingshi/newsList.do?lProjectMenuID=${parentProjectMenu.lId}&menuID=${childProjectMenu.lId }" target="_blank">更多&gt;&gt;</A>
												</SPAN>
											</DIV>
											
											<!-- 图文展示 -->
											<ul class="u1">
												<s:iterator value="#childProjectMenu.lstImageNews" id="news" status="myStatus">
													<LI style="float:left;width:100%;" onclick="javascript:window.open('/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}')">
														<img alt="${news.strTitle13 }" src="${news.imageUrl }" width="342" height="140" />
														<h2>${news.strTitle13 }</h2>
													</li>
												</s:iterator>
												<!-- 
												<LI style="margin-top:37px;">
													<a href="">
														<img alt="" src="${news.imageUrl }" style="width:160px;height:110px;">
													</a>
													<br/>
													<A href="" target="_blank" style="">cccccccccccc </A>
												</li>
												 -->												
											</ul>
											<UL class="box" id="all3">
												<s:iterator value="#childProjectMenu.lstNews" id="news" status="myStatus">
													<s:if test="#myStatus.index < 7">
														<LI>
															<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"
																target="_blank"><s:property value="strLongTitleTwo"/></A>
														</LI>
													</s:if>
												</s:iterator>
											</UL>
										</DIV>
									</s:if>
									<s:else>
										<!-- 第二列 begin -->
										<DIV class="content line">
											<DIV class="top">
												<SPAN class="l"><s:property value="strMenuName"/></SPAN><SPAN class="r"><A
													href="/front/yuyingshi/newsList.do?lProjectMenuID=${parentProjectMenu.lId}&menuID=${childProjectMenu.lId }" target="_blank">更多&gt;&gt;</A>
												</SPAN>
											</DIV>
											<!-- 图文展示 -->
											<ul class="u1">
												<s:iterator value="#childProjectMenu.lstImageNews" id="news" status="myStatus">
													<LI style="float:left;width:100%;" onclick="javascript:window.open('/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}')">
															<img alt="${news.strTitle13 }" src="${news.imageUrl }" width="342" height="140" />
															<h2>${news.strTitle13 }</h2>
<%-- 														<a href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" style="text-decoration:none;font-size:14px;"> --%>
<%-- 															<img alt="${news.strTitle13 }" src="${news.imageUrl }" style="width:100%;height:140px;"> --%>
<!-- 														</a> -->
<!-- 														<br/> -->
<%-- 														<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank" style=""> --%>
<%-- 															${news.strTitle13 } --%>
<!-- 														</A> -->
													</li>
												</s:iterator>
											</ul>
											<UL class="box" id="all3">
												<s:iterator value="lstNews" id="news" status="myStatus">
													<s:if test="#myStatus.index < 7">
														<LI>
															<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>"
																target="_blank"><s:property value="strLongTitleTwo"/></A>
														</LI>
													</s:if>
												</s:iterator>
											</UL>
										</DIV>
										<!-- 第二列 end -->
									</s:else>
								</s:if>
							</s:iterator>
							<!-- 菜单导航 end -->
						</DIV>
					</DIV>
				</s:if>
				<!-- 中间菜单 end -->
			</s:iterator>
			
		</DIV>
		<DIV class="right" style="width: 350px;">
		
			<!-- 标签栏  导航 begin -->
			<DIV class="column">
				<s:iterator value="lstProjectMenu" id="projectMenu" >
					<A href="${menuURL}"><s:property value="strMenuName"/></A>
				</s:iterator>
			</DIV>
			<!-- 标签栏 导航 end -->
			
			<!-- 边缘菜单显示2 start -->
			<s:iterator value="lstChildMenu" id="slideChildMenu" status="parentStatus">	
				<s:if test="#parentStatus.index != 0">
					<DIV class="content2">
						<DIV class="top">
							<SPAN class="l">${strMenuName}</SPAN>
							<SPAN class="r">
								<A
								href="/front/yuyingshi/newsList.do?lProjectMenuID=${slideChildMenu.objParentProjectMenu.lId}&menuID=${slideChildMenu.lId }" target="_blank"><IMG
										width="26" height="26" alt="${strMenuName}"
										src="/commons/yuyingshi/images/more.png">
								</A>
							</SPAN>
						</DIV>
						
						<!-- 图文展示 -->
						<ul class="u1">
							<s:iterator value="#slideChildMenu.lstImageNews" id="news" status="myStatus">
								<LI style="float:left;width:100%;" onclick="javascript:window.open('/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}')">
									<img alt="${news.strTitle13 }" src="${news.imageUrl }" width="342" height="140" />
									<h2>${news.strTitle13 }</h2>
								</li>
							</s:iterator>
						</ul>
						
						<UL class="box" id="all5">
							<s:iterator value="lstNews" id="news" status="myStatus">
								<s:if test="#myStatus.index < 15">
									<LI>
										<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank"><s:property value="strLongTitleTwo"/></A><EM><s:property value="strSendDateShort"/></EM>
									</LI>
								</s:if>
							</s:iterator>
						</UL>
					</DIV>
				</s:if>
			</s:iterator>
			<!-- 边缘菜单显示2 end -->
		</DIV>
	</DIV>
	</div>
</div>