<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<A class="logo" href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}">育婴师知识分享</A>
					<A class="more" href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}">
					<!-- more  -->
					<IMG width="26" height="26" alt="育婴之家" src="/commons/yuyingshi/images/more2.gif"></A>
					<s:iterator value="lstProjectMenu" id="projectMenu" status="status">
						<s:if test="showIndex==0'">
							<A class="column" href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${lId }"><s:property value="strMenuName"/></A>
						</s:if>
					</s:iterator>
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