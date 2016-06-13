<LINK href="/commons/yuyingshi/css/educss.pGgnPYlLI01i.21.css" rel="stylesheet" type="text/css">
<LINK href="/commons/yuyingshi/css/edu.qeqMopjuDoMa.14.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>
<script>
$(function(){
	$('#pp').pagination(
	{
		total:parseInt("${objGPagination.total}"),
		pageSize:parseInt("${objGPagination.pageSize}"),
		pageNumber:parseInt("${objGPagination.pageNo}"),
		pageList:[10,15,20,30,50,100],
		loading:false,
		showPageList:true,
		showRefresh:false,
		beforePageText:'第',
		afterPageText:'页，共{pages}页',
		displayMsg:'{from}-{to}/{total}',
		onSelectPage:function(pageNumber,pageSize)
		{
			location.href="/front/yuyingshi/newsList.do?rows=" + pageSize + "&page=" + pageNumber + "&menuID=${menuID}&lProjectMenuID=${objSubProjectMenu.objParentProjectMenu.lId}"
		}
	});
});	
</script>
<DIV class="lanren box-top">
	<DIV class="search lanren">
		<DIV class="">
			当前位置：
			<A href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}" rel="nofollow">首页</A>
			&nbsp;>
			<A href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${objSubProjectMenu.lId}" rel="nofollow">${objSubProjectMenu.strMenuName}</A>
			&nbsp;>
			列表
		</DIV>
	</DIV>
	
	<DIV class="pleft">
		<DIV class="listbox">
			<UL class="e2">
			
				<#if (objGPagination.rows?size > 0)>
                    <#list objGPagination.rows as news>
					<LI>
						<OL >
							<DIV class="panel" >
								<!-- 热点新闻 -->
								<DIV class="edu_news_list">
									<DIV class="news_list_container  clearfix" style="border:1px solid #fd8764;" >
										<DIV class="news_one havepic " >
											<DIV class="news_main_info" style="width:390px;">
												<H2>
												<A style="color:#ff6d93;font-family: 微软雅黑, 黑体, 宋体;font-size:18px;" href="/static/news/${news.lId?string('###')}" target="_blank">${news.strLongTitle}</A>
												</H2>
												<P>
													${news.strSummary}
												</P>
												<DIV class="news_sub_info">
													<DIV class="join_keys">
														<A href="/static/news/${news.lId?string('###')}">阅读</A>
													</DIV>
													
													<DIV class="news_tie">
														<span style="float:left;"><span style="color:red;font-size:15px;">${news.readNum}</span>&nbsp;阅读&nbsp;&nbsp;</span>
														<!-- <A class="join_num" href="javascript:void(0);">评论</A> -->
													</DIV>
													<!-- 
													<DIV class="share-join-tab">
														<SPAN title="分享" class="share-menu color-date-from icons"
															href="">分享</SPAN>
														<DIV class="share-join-item"></DIV>
													</DIV>
													<SPAN class="clear"></SPAN>
													 -->
												</DIV>
											</DIV>
										</DIV>
									</DIV>
								</DIV>
							</DIV>
						</OL>
						<!-- 
						<OL class="newyear">
							<A class="title"
								href="http://www.lanrenzhijia.com/album/3021.html"
								target="_blank">flash+xml全屏立体式图片相册效果</A>
							<SPAN class="info">[02-11] </SPAN>
						</OL>
						 -->
					</LI>
				</#list>
				</#if>
			</UL>
		</DIV>
		
		<div id="pp" style="width: 99%; background: #efefef; border: 1px solid #ccc;float:left;"></div>
		
		<!-- 
		<DIV class="dede_pages">
			<UL class="pagelist">
			  <LI>首页</LI>
			  <LI class="thisclass">1</LI>
			  <LI><A href="http://www.lanrenzhijia.com/album/list_8_2.html">2</A></LI>
			  <LI><A href="http://www.lanrenzhijia.com/album/list_8_3.html">3</A></LI>
			  <LI><A href="http://www.lanrenzhijia.com/album/list_8_4.html">4</A></LI>
			  <LI><A href="http://www.lanrenzhijia.com/album/list_8_5.html">5</A></LI>
			  <LI><A href="http://www.lanrenzhijia.com/album/list_8_2.html">下一页</A></LI>
			  <LI><A href="http://www.lanrenzhijia.com/album/list_8_5.html">末页</A></LI>
			  <LI><SPAN class="pageinfo">共 
			  <STRONG>5</STRONG>页<STRONG>44</STRONG>条</SPAN></LI>
			</UL>
 		 </div>
		 -->
	</DIV>
	
	
	
	<DIV class="list-js">
		<DIV class="top">
			<SPAN class="l">标签墙</SPAN><SPAN class="r"><!-- <A
				href="http://www.lanrenzhijia.com/js/" target="_blank"><IMG
						width="26" height="26" alt="育婴之家"
						src="/commons/yuyingshi/images/more.png">
			</A> --> </SPAN>
		</DIV>
		<!-- 详细页右侧导航begin -->
		<DIV class="nav-sub">
			<#list lstProjectMenu as projectMenu>
				<A href="/static/menu/sm/${projectMenu.menuKey}/1" <#if projectMenu.lId==objSubProjectMenu.lId>class='thisclass'</#if>>${projectMenu.strMenuName}</A>
			</#list>
		</DIV>
		<!-- 详细页右侧导航end -->
		
		<DIV class="ads_right">
			<DIV class="top">
				<SPAN class="l">最近更新</SPAN><SPAN class="r"><A
					href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1"><IMG
							width="26" height="26" alt="育婴之家"
							src="/commons/yuyingshi/images/more.png">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all2">
				<#list lstNewestMessage as news>
					<LI>
						<A href="/static/news/${news.lId?string('###')}">${news.strLongTitle}</A><EM></EM>
					</LI>
				</#list>
			</UL>
		</DIV>
		
		<DIV class="hot">
			<DL>
				<DT>
					推荐阅读
				</DT>
				<#list lstSuggestReading as news>
					<#if news_index < 20>
						<DD>
							<A href="/static/news/${news.lId?string('###')}">${news.strLongTitle}</A><EM></EM>
						</DD>
					</#if>
				</#list>
			</DL>
		</DIV>
	</DIV>
</DIV>