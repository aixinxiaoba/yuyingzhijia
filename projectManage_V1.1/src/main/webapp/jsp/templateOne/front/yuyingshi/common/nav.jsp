<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- nav 样式 -->
<LINK href="/commons/yuyingshi/css/index.css" rel="stylesheet" type="text/css">

<DIV class="header lanren">
	 <DIV class="category_search clearfix">
		<DIV class="l clearfix" style="width:auto;height:auto;margin-right:70px;">
			<A
			style='BACKGROUND-IMAGE: url(/commons/yuyingshi/images/${objProjectMenu.menuKey}.png)'
			 href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}" alt="${objProjectMenu.strMenuName }" target=_self></A>
			<SPAN><BR></SPAN>
		</DIV>
		<DIV class="r clearfix">
			<DIV class=z>
				<FORM id=search_form onsubmit="return OnSubmit()" method=post
					name=search action="/front/yuyingshi/newsSearch.do?lProjectMenuID=${objProjectMenu.lId}" target=_blank>
					<DIV class=inside>
						<INPUT name="searchText" id=keyword class=input_search placeholder="育婴知识搜索">
					</DIV>
					<SPAN><INPUT class=n type=submit value="">
					</SPAN>
				</FORM>
			</DIV>
			<DIV class=y>
				<UL >
					<LI class=c_one>
						<A href="/front/yuyingshi/newsList.do?lProjectMenuID=23&menuID=34" target="_blank">育儿</A>
					</LI>
					<LI class=c_two>
						<A href="/front/yuyingshi/newsList.do?lProjectMenuID=29&menuID=36" target="_blank">宝妈</A>
					</LI>
					<LI  class=c_three>
						<A href="/front/yuyingshi/newsList.do?lProjectMenuID=22&menuID=25" target="_blank">育婴师</A>
					</LI>
					
					<LI  class=c_four>
						<A href="/front/yuyingshi/newsList.do?lProjectMenuID=30&menuID=38" target="_blank">孕前</A>
					</LI>
					
					<LI  class=c_five>
						<A href=/front/yuyingshi/newsList.do?lProjectMenuID=23&menuID=35 target="_blank">胎教</A>
					</LI>
					
					<LI class=c_six>
						<A href="/front/yuyingshi/newsList.do?lProjectMenuID=22&menuID=5" target="_blank">动态</A>
					</LI>
				</UL>
			</DIV>
		</DIV>
	</DIV>
	
	<!--[if lte IE 8]><style>#special_nav{margin-top:93px;}</style><![endif]-->
	<!-- 导航 start -->
	<DIV class="index-nav">
		<UL class="clearfix" id="special_nav">
			<LI>
				<A class="small-sub-nav<s:if test="strCurMenu == 'index'"> cur-sub-nav</s:if>" href='/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}'>首页</A>
			</LI>
			<s:iterator value="lstProjectMenu" id="projectMenu" >
				<s:if test="#projectMenu.subShowIndex == 1">
					<LI>
						<A class="small-sub-nav<s:if test="strCurMenu == menuKey"> cur-sub-nav</s:if>" href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${lId}"><s:property value="strMenuName"/></A>
					</LI>
				</s:if>
			</s:iterator>
		</UL>
	</DIV>
	<!-- 导航 end -->
</DIV>