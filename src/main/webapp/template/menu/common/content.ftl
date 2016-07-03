
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
				<#list lstNewestMessage as news>
					<LI>
						<A href="/static/news/${news.lId?string('###')}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
					</LI>
				</#list>
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
				<#list lstSuggestReading as news >
					<#if news_index < 9>
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
					href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1" target="_blank"><IMG
							width="26" height="26" alt=""
							src="/commons/yuyingshi/images/more2.gif">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all">
				<#list lstTopOfReading as news >
					<LI>
						<A href="/static/news/${news.lId?string('###')}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
					</LI>
				</#list>
			</UL>
		</DIV>
		<!--  end -->
	</DIV>
	<DIV class="lanren box-top2">
		<DIV class="left">
			<!-- 大全 begin -->
			<DIV class="texiao">
				<DIV class="head">
					<A class="logo" href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}">育婴之家--育婴师知识分享</A>
					
					<!-- more 
					<A class="more" href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}">
					<IMG width="26" height="26" alt="育婴之家" src="/commons/yuyingshi/images/more2.gif"></A>
					<#list lstProjectMenu as projectMenu>
						<A class="column" href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${projectMenu.lId }">${projectMenu.strMenuName}</A>
					</#list>
					 -->
					
					
				</DIV>
				<DIV class="container">
					<!-- 菜单导航 begin -->
					<#list lstProjectMenu as projectMenu>
						<#if projectMenu_index%2==0>
							<DIV class="content">
								<DIV class="top">
									<SPAN class="l">${projectMenu.strMenuName}</SPAN><SPAN class="r"><A
										href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${projectMenu.lId }" target="_blank">更多&gt;&gt;</A>
									</SPAN>
								</DIV>
								
								<!-- 图文展示 -->
								<ul class="u1">
								  <#if projectMenu.lstImageNews??>
								    <#list projectMenu.lstImageNews as news>
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
								
								<UL class="box" id="all3">
									<#list projectMenu.lstNews as news>
										<LI>
											<A href="/static/news/${news.lId?string('###')}"
												target="_blank">${news.strLongTitleTwo}</A>
										</LI>
									</#list>
								</UL>
							</DIV>
						<#else>
							<!-- 第二列 begin -->
							<DIV class="content line">
								<DIV class="top">
									<SPAN class="l">${projectMenu.strMenuName}</SPAN><SPAN class="r"><A
										href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${projectMenu.lId }" target="_blank">更多&gt;&gt;</A>
									</SPAN>
								</DIV>
								
								<!-- 图文展示 -->
								<ul class="u1">
								   <#if projectMenu.lstImageNews??>
								   		<#list projectMenu.lstImageNews as news>
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
									<#list projectMenu.lstNews as news>
										<LI>
											<A href="/static/news/${news.lId?string('###')}"
												target="_blank">${news.strLongTitleTwo}</A>
										</LI>
									</#list>
								</UL>
							</DIV>
							<!-- 第二列 end -->
						</#if>
					</#list>
					<!-- 菜单导航 end -->
				</DIV>
			</DIV>
			<!-- 大全 end -->
		</DIV>
		<DIV class="right" style="width: 350px;">
			<!-- 标签栏目导航 begin -->
			<DIV class="column">
				<#list lstProjectMenu as projectMenu>
					<A href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${projectMenu.lId}">${projectMenu.strMenuName}</A>
				</#list>
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
					<#list lstHotSuggest as news >
						<LI>
							<A href="/static/news/${news.lId?string('###')}/static/news/${news.lId?string('###')}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
						</LI>
					</#list>
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
					<#list lstYuYingSkill as news >
						<LI>
							<A href="/static/news/${news.lId?string('###')}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
						</LI>
					</#list>
					
				</UL>
			</DIV>
			<!-- 育婴技能 end -->
		</DIV>
	</DIV>
</div>