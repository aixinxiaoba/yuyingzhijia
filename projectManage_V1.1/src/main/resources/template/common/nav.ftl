<DIV class="header lanren">
			 <DIV class="category_search clearfix">
				<DIV class="l clearfix" style="width:auto;height:auto;margin-right:70px;">
					<A
					<#if strCurMenu == 'aboutUs'>style='BACKGROUND-IMAGE: url(/commons/yuyingshi/images/logo-small-gray.png)'</#if>
					 href="/" target=_self></A>
					<SPAN><BR></SPAN>
				</DIV>
				<DIV class="r clearfix">
					<DIV class=z>
						<FORM id=search_form onsubmit="return OnSubmit()" method=post
							name=search action="/front/index/newsSearch.do">
							<DIV class=inside>
								<INPUT name="projectKey" value="${objProject.projectKey }" type="hidden">
								<INPUT name="searchText" id=keyword class=input_search placeholder="育婴知识搜索" value="${searchText }">
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
			<DIV class="index-nav"  id="special_nav">
				<UL class="clearfix">
					<LI>
						<A class="small-sub-nav<#if strCurMenu == 'index'> cur-sub-nav</#if>" href='/'>育婴首页</A>
					</LI>
					<#list lstProjectMenu as objProjectMenu>
						<LI>
							<A class="small-sub-nav<#if strCurMenu == objProjectMenu.menuKey> cur-sub-nav</#if>" href="${objProjectMenu.menuURL}"  <#if objProjectMenu.menuKey!='aboutUs'>target="_blank"</#if> >${objProjectMenu.strMenuName}</A>
							<!--
							<A class="small-sub-nav<#if strCurMenu == objProjectMenu.menuKey> cur-sub-nav</#if>" href="/static/sub/${objProjectMenu.menuKey}.html"  <#if objProjectMenu.menuKey!='aboutUs'>target="_blank"</#if> >${objProjectMenu.strMenuName}</A>
							-->
						</LI>
					</#list>
				</UL>
			</DIV>
			<!-- 导航 end -->
		</DIV>