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
<div id="content">
	<DIV class="lanren box-top">
		<!-- 最新更新 begin -->
		<DIV class="list">
			<DIV class="top">
				<SPAN class="l">最近更新</SPAN><SPAN class="r"><A
					href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1" target="_blank"><IMG
							width="26" height="26" alt="育婴之家"
							src="/commons/yuyingshi/images/more.png">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all2">
				<s:iterator value="lstNewestMessage" id="news">
					<LI>
						<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank"><s:property value="strLongTitleTwo"/></A><EM><s:property value="strSendDateShort"/></EM>
					</LI>
				</s:iterator>
			</UL>
		</DIV>
		<!-- 最新更新 end -->
		<!-- 推荐阅读 begin -->
		<DIV class="list line">
			<DIV class="top">
				<SPAN class="l">推荐阅读</SPAN><SPAN class="r"><A
					href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1" target="_blank"><IMG
							width="26" height="26" alt="育婴之家推荐阅读"
							src="/commons/yuyingshi/images/more.png">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all2">
				<s:iterator value="lstSuggestReading" id="news" status="myStatus">
					<s:if test="#myStatus.index < 9">
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
					href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1" target="_blank"><IMG
							width="26" height="26" alt=""
							src="/commons/yuyingshi/images/more2.gif">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all">
				<s:iterator value="lstTopOfReading" id="news" status="myStatus">
					<LI>
						<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank"><s:property value="strLongTitleTwo"/></A><EM><s:property value="strSendDateShort"/></EM>
					</LI>
				</s:iterator>
			</UL>
		</DIV>
		<!--  end -->
	</DIV>
	<DIV class="lanren box-top2">
		<DIV class="left">
			<!-- 大全 begin -->
			<DIV class="texiao">
				<DIV class="head">
					<A class="logo" href="javascript:void(0)">育婴师知识分享--分享你喜欢的育婴知识</A>
					
					<!-- more
					<A class="more" href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}">
					<IMG width="26" height="26" alt="育婴之家" src="/commons/yuyingshi/images/more2.gif"></A>
					<s:iterator value="lstProjectMenu" id="projectMenu" status="status">
						<s:if test="showIndex==0'">
							<A class="column" href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${lId }"><s:property value="strMenuName"/></A>
						</s:if>
					</s:iterator>
					  -->
				</DIV>
				<DIV class="container">
					<!-- 菜单导航 begin -->
					<s:iterator value="lstProjectMenu" id="projectMenu" status="status">
					   <!-- 1 <s:property value="#status.odd"/>-->
						<s:if test="#status.odd">
							<DIV class="content">
								<DIV class="top">
									<SPAN class="l"><s:property value="strMenuName"/></SPAN><SPAN class="r"><A
										href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${lId }" target="_blank">更多&gt;&gt;</A>
									</SPAN>
								</DIV>
								<ul class="u1">
									<s:iterator value="#projectMenu.lstImageNews" id="news" status="myStatus">
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
						</s:if>
						<!-- 2 -->
						<s:else>
							<!-- 第二列 begin -->
							<DIV class="content line">
								<DIV class="top">
									<SPAN class="l"><s:property value="strMenuName"/></SPAN><SPAN class="r"><A
										href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${lId }" target="_blank">更多&gt;&gt;</A>
									</SPAN>
								</DIV>
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
					</s:iterator>
					<!-- 菜单导航 end -->
				</DIV>
			</DIV>
			<!-- 大全 end -->
		</DIV>
		<DIV class="right" style="width: 350px;">
			<!-- 标签栏目导航 begin -->
			<DIV class="column">
				<s:iterator value="lstProjectMenu" id="projectMenu" >
					<A href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${lId}"><s:property value="strMenuName"/></A>
				</s:iterator>
			</DIV>
			<!-- 标签栏目导航 end -->
			<!-- 热点推荐 begin -->
			<DIV class="content2">
				<DIV class="top">
					<SPAN class="l">热点推荐</SPAN><SPAN class="r"><A
						href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1" target="_blank"><IMG
								width="26" height="26" alt=""
								src="/commons/yuyingshi/images/more.png">
					</A> </SPAN>
				</DIV>
				<UL class="box" id="all5">
					<s:iterator value="lstHotSuggest" id="news" status="myStatus">
						<LI>
							<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank"><s:property value="strLongTitleTwo"/></A><EM><s:property value="strSendDateShort"/></EM>
						</LI>
					</s:iterator>
				</UL>
			</DIV>
			<!-- 热点推荐 end -->
			<!-- 育婴技能 begin -->
			<DIV class="content2">
				<DIV class="top">
					<SPAN class="l">育婴技能</SPAN><SPAN class="r"><A
						href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1" target="_blank"><IMG
								width="26" height="26" alt="名站特效"
								src="/commons/yuyingshi/images/more.png">
					</A> </SPAN>
				</DIV>
				<UL class="box" id="all5">
					<s:iterator value="lstYuYingSkill" id="news" status="myStatus">
						<LI>
							<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank"><s:property value="strLongTitleTwo"/></A><EM><s:property value="strSendDateShort"/></EM>
						</LI>
					</s:iterator>
				</UL>
			</DIV>
			<!-- 育婴技能 end -->
		</DIV>
	</DIV>
</div>