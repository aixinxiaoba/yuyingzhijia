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
</STYLE>
<SCRIPT type="text/javascript">
	$(function() {
		
		$(".right dl dd:eq(0)").addClass("on");
		$(".right dl dd:eq(1)").addClass("on");
		$(".right dl dd:eq(2)").addClass("on");
		$(".right dl dd:eq(7)").addClass("nbd");


		});
</SCRIPT>
<div id="content">
	<DIV class="lanren box-top">
		<!-- 最新更新 begin -->
		<DIV class="list" style="width: 275px;">
			<DIV class="l fi_btn">
				<A class="l_view bg_view" href="javascript:void(0);"
					target="_self"></A>
			</DIV>
			<DIV class="slides">
				<UL class="slide-pic center_view"
					width="10000px;right:275px;position:relative;">
					<#list lstRollingOfReading as news>
						<!-- 默认显示第一张图片 -->
						<#list news.lstAttachs as attachs>
							<#if attachs_index == 0>
								<LI class="cur" style="display: list-item;">
									<A style="white-space: nowrap;" href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">
										<IMG width="275" height="374" alt="${news.strTitle}" src="${attachs.path}">
									</A>
									<DIV class="bgtext"></DIV>
									<DIV class="title">
										<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">${news.strTitle}</A>
									</DIV>
								</LI>
							</#if>
						</#list>
					</#list>
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
				<#list lstNewestMessage as news>
					<LI>
						<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
					</LI>
				</#list>
			</UL>
			<DIV class="top">
				<SPAN class="l">推荐阅读</SPAN><SPAN class="r"><A
					href="/front/index/newsListByTag.do?projectKey=${objProject.projectKey }&newsTag=1" target="_blank"><IMG
							width="26" height="26" alt="育婴之家推荐阅读"
							src="/commons/yuyingshi/images/more.png">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all2">
				<#list lstSuggestReading as news>
					<#if news_index < 7>
						<LI>
							<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
						</LI>
					</#if>
				</#list>
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
				<#list lstTopOfReading as news>
					<#if news_index < 6>
						<LI>
							<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
						</LI>
					</#if>
				</#list>
			</UL>
			
			<!-- 边缘菜单显示 -->
			<#list lstChildMenu as slideChildMenu>	
				<#if slideChildMenu_index == 0>
					<DIV class="top" id="no-border">
						<SPAN class="l">${slideChildMenu.strMenuName}</SPAN><SPAN class="r">
							<A
								href="/front/yuyingshi/newsList.do?lProjectMenuID=${slideChildMenu.objParentProjectMenu.lId}&menuID=${slideChildMenu.lId }" target="_blank"><IMG
										width="26" height="26" alt="${slideChildMenu.strMenuName}"
										src="/commons/yuyingshi/images/more2.gif">
							</A>
						</SPAN>
					</DIV>
					<UL class="box" id="all">
						<#list slideChildMenu.lstNews as currentNews>
							<#if currentNews_index < 6>
								<LI>
									<A href="/front/yuyingshi/detail.do?lProjectMenuID=${currentNews.objProjectMenu.objParentProjectMenu.lId }&newsID=${currentNews.lId}" target="_blank">${currentNews.strLongTitleTwo}</A><EM>${currentNews.strSendDateShort}</EM>
								</LI>
							</#if>
						</#list>
					</UL>
				</#if>
			</#list>
		</DIV>
		<!--  end -->
	</DIV>
	
	<DIV class="lanren box-top2">
		<!-- 左边设置start -->
		<DIV class="left">
			<#list lstProjectMenu as parentProjectMenu>
				<!-- 过滤掉关于我们菜单 -->
				<#if parentProjectMenu.menuKey != 'aboutUs'>
					<!-- 中间菜单 begin -->
					<DIV class="texiao">
						<DIV class="head">
							<A class="logo" href="${parentProjectMenu.menuURL}">育婴之家--${parentProjectMenu.strMenuName}</A>
							<A class="more" href="${parentProjectMenu.menuURL}" target="_blank"><IMG width="26" height="26" alt="more" src="/commons/yuyingshi/images/more2.gif"></A>
							
							<!-- 子菜单循环开始 begin -->
							<#list parentProjectMenu.lstChildrenProjectMenu as projectMenu>
								<!-- 显示状态为0的菜单 -->
								<#if projectMenu.showIndex!=1 && projectMenu.menuKey!='zhaopinzhuanqu'>
									<A class="column" href="/front/yuyingshi/newsList.do?lProjectMenuID=${parentProjectMenu.lId}&menuID=${projectMenu.lId }" target="_blank">${projectMenu.strMenuName}</A>
								</#if>
							</#list>
						</DIV>
						<DIV class="container">
						
							<!-- 子菜单循环开始 begin -->
							<#list parentProjectMenu.lstChildrenProjectMenu as childProjectMenu>
								<!-- 判断子菜单是否在首页显示 -->
								<#if childProjectMenu.showIndex==1>
									<#if childProjectMenu_index%2==0>
										<DIV class="content">
											<DIV class="top">
												<SPAN class="l">${childProjectMenu.strMenuName}</SPAN><SPAN class="r"><A
													href="/front/yuyingshi/newsList.do?lProjectMenuID=${parentProjectMenu.lId}&menuID=${childProjectMenu.lId }" target="_blank">更多&gt;&gt;</A>
												</SPAN>
											</DIV>
											<UL class="box" id="all3">
												<#list childProjectMenu.lstNews as news>
														<LI>
															<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}"
																target="_blank">${news.strLongTitleTwo}</A>
														</LI>
												</#list>
											</UL>
										</DIV>
									<#else>
										<!-- 第二列 begin -->
										<DIV class="content line">
											<DIV class="top">
												<SPAN class="l">${childProjectMenu.strMenuName}</SPAN><SPAN class="r"><A
													href="/front/yuyingshi/newsList.do?lProjectMenuID=${parentProjectMenu.lId}&menuID=${childProjectMenu.lId }" target="_blank">更多&gt;&gt;</A>
												</SPAN>
											</DIV>
											<UL class="box" id="all3">
												<#list childProjectMenu.lstNews as news>
													<#if news_index < 7>
														<LI>
															<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}"
																target="_blank">${news.strLongTitleTwo}</A>
														</LI>
													</#if>
												</#list>
											</UL>
										</DIV>
										<!-- 第二列 end -->
									</#if>
								</#if>
							</#list>
							<!-- 菜单导航 end -->
						</DIV>
					</DIV>
				</#if>
				<!-- 中间菜单 end -->
			</#list>
			
		</DIV>
		<DIV class="right" style="width: 350px;">
		
			<!-- 标签栏  导航 begin -->
			<DIV class="column">
				<#list lstProjectMenu  as projectMenu>
					<A href="${projectMenu.menuURL}">${projectMenu.strMenuName}</A>
				</#list>
			</DIV>
			<!-- 标签栏 导航 end -->
			
			<!-- 边缘菜单显示2 start -->
			<#list lstChildMenu as slideChildMenu >	
				<#if slideChildMenu_index != 0>
					<DIV class="content2">
						<DIV class="top">
							<SPAN class="l">${slideChildMenu.strMenuName}</SPAN><SPAN class="r"><A
								href="/front/yuyingshi/newsList.do?lProjectMenuID=${slideChildMenu.objParentProjectMenu.lId}&menuID=${slideChildMenu.lId }" target="_blank"><IMG
										width="26" height="26" alt="${slideChildMenu.strMenuName}"
										src="/commons/yuyingshi/images/more.png">
							</A> </SPAN>
						</DIV>
						<UL class="box" id="all5">
							<#list slideChildMenu.lstNews as news>
								<#if news_index < 15>
								<LI>
									<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
								</LI>
								</#if>
							</#list>
						</UL>
					</DIV>
				</#if>
			</#list>
			<!-- 边缘菜单显示2 end -->
		</DIV>
	</DIV>
</div>