
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
    /*background: url("../images/l_ico.gif") no-repeat 6px rgb(242, 242, 242);*/
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
		<DIV class="list" style="width: 275px;">
			<DIV class="l fi_btn">
				<A class="l_view bg_view" href="javascript:void(0);"
					target="_self"></A>
			</DIV>
			<DIV class="slides">
				<UL class="slide-pic center_view"
					width="10000px;right:275px;position:relative;">
					<#list lstRollingOfReading as news>
						<!-- 开始展示图片 -->
							<LI class="cur" style="display: list-item;">
									<A style="white-space: nowrap;" href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">
										<IMG width="275" height="374" alt="${news.strTitle}" src="${news.imageUrl}">
									</A>
									<DIV class="bgtext"></DIV>
									<DIV class="title">
										<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">${news.strTitle}</A>
									</DIV>
								</LI>
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
						<A href="/static/news/${news.lId?string('###')}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
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
							<A href="/static/news/${news.lId?string('###')}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
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
							<A href="/static/news/${news.lId?string('###')}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
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
									<A href="/static/news/${currentNews.lId?string('###')}" target="_blank">${currentNews.strLongTitleTwo}</A><EM>${currentNews.strSendDateShort}</EM>
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
							<A class="logo" href="/static/menu/bm/${parentProjectMenu.menuKey}">育婴之家--${parentProjectMenu.strMenuName}</A>
							<A class="more" href="/static/menu/bm/${parentProjectMenu.menuKey}" target="_blank"><IMG width="26" height="26" alt="more" src="/commons/yuyingshi/images/more2.gif"></A>
							
							<!-- 子菜单循环开始 begin -->
							<#list parentProjectMenu.lstChildrenProjectMenu as projectMenu>
								<!-- 显示状态为0的菜单 -->
								<#if projectMenu.showIndex==0 && projectMenu.menuKey!='zhaopinzhuanqu'>
									<A class="column" href="/static/menu/sm/${projectMenu.menuKey}/1" target="_blank">${projectMenu.strMenuName}</A>
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
													href="/static/menu/sm/${childProjectMenu.menuKey}/1" target="_blank">更多&gt;&gt;</A>
												</SPAN>
											</DIV>
											
											<!-- 图文展示 -->
											<ul class="u1">
											  <#if childProjectMenu.lstImageNews??>
											    <#list childProjectMenu.lstImageNews as news>
													  <LI style="float:left;width:100%;" onclick="javascript:window.open('/static/news/${news.lId?string('###')}')">
															<img alt="${news.strTitle13 }" src="${news.imageUrl }" width="342" height="140" />
															<h2>${news.strTitle13 }</h2>
														</li>
														<!--
														<LI style="float:left;width:100%;">
															<a href="/static/news/${news.lId?string('###')}" >
																<img alt="${news.strTitle13 }" src="${news.imageUrl }" style="width:100%;height:140px;">
															</a>
															<br/>
															<A href="/static/news/${news.lId?string('###')}" target="_blank" style="">
																${news.strTitle13 }
															</A>
														</li>
														-->
													</#list>		
											  </#if>				
											</ul>
											<!-- 无图片分类信息展示 -->
											<UL class="box" id="all3">
												<#list childProjectMenu.lstNews as news>
												  <#if news_index < 7>
														<LI>
															<A href="/static/news/${news.lId?string('###')}"
																target="_blank">${news.strLongTitleTwo}</A>
														</LI>
													 </#if>
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
											
											<!-- 图文展示 -->
											<ul class="u1">
											   <#if childProjectMenu.lstImageNews??>
											   		<#list childProjectMenu.lstImageNews as news>
											   		  <LI style="float:left;width:100%;" onclick="javascript:window.open('/static/news/${news.lId?string('###')}')">
																<img alt="${news.strTitle13 }" src="${news.imageUrl }" width="342" height="140" />
																<h2>${news.strTitle13 }</h2>
															</li>
															<!--
															<LI style="float:left;width:100%;">
																<a href="/static/news/${news.lId?string('###')}" >
																	<img alt="${news.strTitle13 }" src="${news.imageUrl }" style="width:100%;height:140px;">
																</a>
																<br/>
																<A href="/static/news/${news.lId?string('###')}" target="_blank" style="">
																	${news.strTitle13 }
																</A>
															</li>
															-->
														</#list>
											  </#if>
																						
											</ul>
											<!-- 无图片分类信息展示 -->
											
											<UL class="box" id="all3">
												<#list childProjectMenu.lstNews as news>
													<#if news_index < 7>
														<LI>
															<A href="/static/news/${news.lId?string('###')}"
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
					<A href="/static/menu/bm/${projectMenu.menuKey}">${projectMenu.strMenuName}</A>
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
						
						<!-- 图文展示 -->
						<ul class="u1">
						  <#if slideChildMenu.lstImageNews??>
						  	<#list slideChildMenu.lstImageNews as news>
						  	  <LI style="float:left;width:100%;" onclick="javascript:window.open('/static/news/${news.lId?string('###')}')">
										<img alt="${news.strTitle13 }" src="${news.imageUrl }" width="342" height="140" />
										<h2>${news.strTitle13 }</h2>
									</li>
									<!--
									<LI style="float:left;width:100%;">
										<a href="/static/news/${news.lId?string('###')}" style="text-decoration:none;font-size:14px;">
											<img alt="${news.strTitle13 }" src="${news.imageUrl }" style="width:100%;height:140px;">
										</a>
										<br/>
										<A href="/static/news/${news.lId?string('###')}" target="_blank" style="">
											${news.strTitle13 }
										</A>
									</li>
									-->
							  </#list>
						  </#if>
							
						</ul>
						
						<UL class="box" id="all5">
							<#list slideChildMenu.lstNews as news>
								<#if news_index < 15>
								<LI>
									<A href="/static/news/${news.lId?string('###')}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
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