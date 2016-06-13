<!-- nav 样式 -->
<LINK href="/commons/yuyingshi/css/index.css" rel="stylesheet" type="text/css">

<DIV class="header lanren">
	 <DIV class="category_search clearfix">
		<DIV class="l clearfix" style="width:auto;height:auto;margin-right:70px;">
			<A
			style='BACKGROUND-IMAGE: url(/commons/yuyingshi/images/${objProjectMenu.menuKey}.png)'
			 href="/static/menu/bm/${objProjectMenu.menuKey}" alt="${objProjectMenu.strMenuName }" target=_self></A>
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
						<A href="/static/menu/sm/yuerzhishi/1" target="_blank">育儿</A>
					</LI>
					<LI class=c_two>
						<A href="/static/menu/sm/baomazhishi/1" target="_blank">宝妈</A>
					</LI>
					<LI  class=c_three>
						<A href="/static/menu/sm/examinfo/1" target="_blank">育婴师</A>
					</LI>
					
					<LI  class=c_four>
						<A href="/static/menu/sm/yunqianzhunbei/1" target="_blank">孕前</A>
					</LI>
					
					<LI  class=c_five>
						<A href="/static/menu/sm/taijiaozhishi/1" target="_blank">胎教</A>
					</LI>
					
					<LI class=c_six>
						<A href="/static/menu/sm/hangyedongtai/1" target="_blank">动态</A>
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
				<A class="small-sub-nav<#if strCurMenu == 'index'> cur-sub-nav</#if>" href='/static/menu/bm/${objProjectMenu.menuKey}'>首页</A>
			</LI>
			<#list lstProjectMenu as projectMenu>
				<LI>
					<A class="small-sub-nav<#if strCurMenu == projectMenu.menuKey> cur-sub-nav</#if>" href="/static/menu/sm/${projectMenu.menuKey}/1">${projectMenu.strMenuName}</A>
				</LI>
			</#list>
		</UL>
	</DIV>
	<!-- 导航 end -->
</DIV>