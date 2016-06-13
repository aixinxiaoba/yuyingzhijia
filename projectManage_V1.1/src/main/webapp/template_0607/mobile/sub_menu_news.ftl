<section class="homepage-info" id="zixunlist" name="zixunlist">
	<ul class="list-info fn-mlr" id="newslist">
	    <#list lstNews as news>
			<li><a href="/front/yuyingshi/detail.do?newsID=${news.lId}">
					<#if news.imageUrl??> <img src="${news.imageUrl}" alt="${news.strLongTitleTwo}">
					 <#else> <img src="/commons/images/defaultpic.gif" /> </#if>
					<h4>${news.strLongTitle}</h4> <time>来自育婴知识分享</time> <span class="comment"><strong>${news.readNum}</strong>阅读</span>
					</a>
			</li>
		</#list>
	</ul>
	<#if (lstNews?size>0) >
		<span id="j-look-more" class="u-more" onclick="location.href='/front/m/newsList.do?menuID=${curMenu.lId}'">使劲戳，看更多<i class="more_face">☺</i></span>
	</#if>
</section>
