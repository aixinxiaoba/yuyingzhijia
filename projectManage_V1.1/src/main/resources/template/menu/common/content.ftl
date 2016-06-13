
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
						<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=<s:property value='lId'/>" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
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
					href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1" target="_blank"><IMG
							width="26" height="26" alt=""
							src="/commons/yuyingshi/images/more2.gif">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all">
				<#list lstTopOfReading as news >
					<LI>
						<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
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
					<A class="more" href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}">
					<!-- more  -->
					<IMG width="26" height="26" alt="育婴之家" src="/commons/yuyingshi/images/more2.gif"></A>
					<#list lstProjectMenu as projectMenu>
						<A class="column" href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${projectMenu.lId }">${projectMenu.strMenuName}</A>
					</#list>
					
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
								<UL class="box" id="all3">
									<#list projectMenu.lstNews as news>
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
									<SPAN class="l">${projectMenu.strMenuName}</SPAN><SPAN class="r"><A
										href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${projectMenu.lId }" target="_blank">更多&gt;&gt;</A>
									</SPAN>
								</DIV>
								<UL class="box" id="all3">
									<#list projectMenu.lstNews as news>
										<LI>
											<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}"
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
							<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
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
							<A href="/front/yuyingshi/detail.do?lProjectMenuID=${news.objProjectMenu.objParentProjectMenu.lId }&newsID=${news.lId}" target="_blank">${news.strLongTitleTwo}</A><EM>${news.strSendDateShort}</EM>
						</LI>
					</#list>
					
				</UL>
			</DIV>
			<!-- 育婴技能 end -->
		</DIV>
	</DIV>
</div>