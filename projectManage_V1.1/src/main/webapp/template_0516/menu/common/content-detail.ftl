<DIV class="lanren box-top">
	<DIV class="search lanren">
		<DIV style="border:0px solid #f99391;">
			当前位置：
			<A href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}" rel="nofollow">首页</A>
			&nbsp;>
			<A href="/front/yuyingshi/newsList.do?lProjectMenuID=${objProjectMenu.lId}&menuID=${objNews.objProjectMenu.lId}" rel="nofollow">${objNews.objProjectMenu.strMenuName}</A>
			&nbsp;>
			正文
		</DIV>
	</DIV>
	
	<DIV class="pleft">
		<DIV class="viewbox">
			<DIV class="title" style="padding:0px;">
				<H2>
					${objNews.strTitle}
				</H2>
				<DIV class="f12" style="margin:3px;">
					<SPAN class="l">作者：
						<A href="/front/yuyingshi/index.do?lProjectMenuID=${objProjectMenu.lId}" target="_blank">育婴之家</A>&nbsp;&nbsp;(转载请注明出处，并添加链接地址)</SPAN>
						<SPAN class="r">发布日期：${objNews.strSendDate}</SPAN>
						<span class="r">点击次数：${objNews.readNum + 1}&nbsp;</span>
				</DIV>
			</DIV>
			<!-- /title -->
			<DIV class="content1">
				${objNews.strContent}
			</DIV>
			
			<!-- 分享 start -->
			<div class="bdsharebuttonbox" style="">
				<a class="bds_more" href="" data-cmd="more"></a><a title="分享到QQ空间"
					class="bds_qzone" href="www.baidu.com" data-cmd="qzone"></a><a title="分享到新浪微博"
					class="bds_tsina" href="#" data-cmd="tsina"></a><a title="分享到腾讯微博"
					class="bds_tqq" href="#" data-cmd="tqq"></a><a title="分享到人人网"
					class="bds_renren" href="#" data-cmd="renren"></a><a title="分享到微信"
					class="bds_weixin" href="#" data-cmd="weixin"></a>
			</div>
			<script>
				window._bd_share_config = {
					"common" : {
						"bdSnsKey" : {
							"tsina" : "育婴之家",
							"tqq" : "育婴之家",
							"t163" : "育婴之家",
							"tsohu" : "育婴之家"
						},
						"bdText" : "育婴之家",
						"bdMini" : "2",
						"bdMiniList" : false,
						"bdPic" : "育婴之家",
						"bdStyle" : "0",
						"bdSize" : "16"
					},
					"share" : {},
					"image" : {
						"viewList" : [ "qzone", "tsina", "tqq", "renren", "weixin" ],
						"viewText" : "分享到：",
						"viewSize" : "16"
					},
					"selectShare" : {
						"bdContainerClass" : null,
						"bdSelectMiniList" : [ "qzone", "tsina", "tqq", "renren",
								"weixin" ]
					}
				};
				with (document)
					0[(getElementsByTagName('head')[0] || body)
							.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
							+ ~(-new Date() / 36e5)];
			</script>
			<!-- 分享 end -->
			<DIV class="handle">
				<DIV class="context">
					<UL>
						<LI>
								上一篇：
							<#if objUpNews??>
								<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.lId}&newsID=${objUpNews.lId?string('###')}">${objUpNews.strLongTitleTwo}</A>
							<#else>
								没有了
							</#if>
						</LI>
						<LI>
							下一篇：
							<#if objNextNews??>
								<A href="/front/yuyingshi/detail.do?lProjectMenuID=${objProjectMenu.lId}&newsID=${objNextNews.lId?string('###')}">${objNextNews.strLongTitleTwo}</A>
							<#else>
								没有了
							</#if>
						</LI>
					</UL>
				</DIV>
				<!-- /context -->
				<!-- 
				<DIV class="actbox">
					<UL class="downurllist">
						<LI>
							<A href="http://d.lanrenzhijia.com/2015/scale0417"
								target="_blank" rel="nofollow">在线预览</A>
						</LI>

						<LI>
							<A href="http://d.lanrenzhijia.com/2015/scale0417/scale0417.rar">本地下载</A>
						</LI>
					</UL>
				</DIV>
				 -->
				<!-- /actbox -->
			</DIV>
			<!-- /handle -->
		</DIV>
	</DIV>
	<!-- /pleft -->

	<DIV class="list-js">
		<DIV class="top">
			<SPAN class="l">标签墙</SPAN><SPAN class="r">
			<!-- 
			<A
				href="http://www.lanrenzhijia.com/js/" target="_blank"><IMG
						width="26" height="26" alt="育婴之家"
						src="/commons/yuyingshi/images/more.png">
			</A>
			 --> </SPAN>
		</DIV>
		<!-- 详细页右侧导航begin -->
		<DIV class="nav-sub">
			<#list lstProjectMenu as projectMenu >
				<A href="/static/menu/sm/${projectMenu.menuKey}/1" <#if projectMenu.lId==objNews.objProjectMenu.lId>class='thisclass'</#if>>${projectMenu.strMenuName}</A>
			</#list>
		</DIV>
		<!-- 详细页右侧导航end -->
		
		<DIV class="ads_right">
			<DIV class="top">
				<SPAN class="l">最新更新</SPAN><SPAN class="r"><A
					href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=${objProjectMenu.lId}&newsTag=1"><IMG
							width="26" height="26" alt="育婴之家"
							src="/commons/yuyingshi/images/more.png">
				</A> </SPAN>
			</DIV>
			<UL class="box" id="all2">
				<#list lstNewestMessage as curNews>
					<LI>
						<A href="/static/news/${curNews.lId?string('###')}">${curNews.strLongTitle}</A><EM></EM>
					</LI>
				</#list>
			</UL>
		</DIV>
		
		<DIV class="hot">
			<DL>
				<DT>
					推荐阅读
				</DT>
				<#list lstSuggestReading as curNews>
					<#if curNews_index < 20>
						<DD>
							<A href="/static/news/${curNews.lId?string('###')}"
								target="_blank">${curNews.strLongTitle}</A>
						</DD>
					</#if>
				</#list>
			</DL>
		</DIV>
	</DIV>
</DIV>
<script>$.ajax( { url:"/front/index/setReadNum.do", type:"post",  data:{"newsID":"${objNews.lId?string('###')}"}, dataType:"text",  async: true });</script>
