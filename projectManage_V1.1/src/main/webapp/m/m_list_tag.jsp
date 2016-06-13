<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML>
<!-- saved from url=(0030)http://m.gmw.cn/node_32337.htm -->
<!DOCTYPE html PUBLIC "" "">
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
<SCRIPT language="javascript">
</SCRIPT>
</HEAD>
<BODY>
	<DIV class="nav_header_wrap">
		<A class="nav_header_left" href="/front/m/index.do"><IMG
			src="/m/common/header_yyzj_logo.png"> </A><A
			class="nav_header_right" href="/front/m/nav.do"><IMG
			src="/m/common/gm/header_nav_btn.png"> </A>
		<!--begin 4062085-0-5-->
		<FONT class="nav_header_center">${objNewsTag.strTagName }</FONT>
		<!--end 4062085-0-5-->
		<BR class="clear">
	</DIV>
	<DIV id="content_main">
		<DIV class="nav_content_list">
			<DIV class="nav_content_item">
				<!--begin 4062086-0-9-->
				<UL class="news_list">
					<s:if test="objGPagination != null && objGPagination.rows.size > 0">
	                    <s:iterator value="objGPagination.rows" id="objNews" status="mystatus">
	                    	<LI><A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.objParentProjectMenu.lId }&newsID=${objNews.lId}"
						target="_self"><s:property value="strFormatTitle"/></A> <EM class="list_em_r">${objNews.strSendDateShort}</EM></LI>
	                    </s:iterator>
                    </s:if>
				</UL>
				<DIV id="displaypagenum">
					<P>
						<CENTER>
								<s:iterator value="{objGPagination.pageNo-2,objGPagination.pageNo-1,objGPagination.pageNo,objGPagination.pageNo+1,objGPagination.pageNo+2}" id="pageNos">
									<s:if test="objGPagination.pageNo == #pageNos">
										<SPAN class="pagefontcon">${pageNos }</SPAN> 
									</s:if>
									<s:else>
										<s:if test="#pageNos <= objGPagination.maxPage && #pageNos>0">
											<A class="pagefontcon" href="/front/m/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId }&newsTag=1&page=${pageNos }">${pageNos }</A> 
										</s:if>
									</s:else>
								</s:iterator>
						</CENTER>
					</P>
				</DIV>
				<!--end 4062086-0-9-->
			</DIV>
			<!-- 分页 -->
		</DIV>
	</DIV>
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

	<DIV class="foot_nav">
		<DIV class="font_wrap">
			<A href="/front/m/nav.do" atremote="">导航</A> 
			<s:iterator value="lstProjectMenu" id="projectMenu" >
				<A href="/front/m/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${lId}" atremote=""><s:property value="strMenuName"/></A>
			</s:iterator>
		</DIV>
	</DIV>
	
	<DIV class="footer_main" style="padding: 15px 0px 20px;">
		<DIV style="text-align: center; color: rgb(186, 186, 186); font-size: 12px;">育婴之家版权所有</DIV>
	</DIV>
</BODY>
</HTML>

