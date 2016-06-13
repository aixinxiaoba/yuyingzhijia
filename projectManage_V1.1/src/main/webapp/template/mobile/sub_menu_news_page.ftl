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
	
    <div id="j-look-more" class="u-more">
	    <#if lastPage?? >
	    	<span onclick="nextPage('${mid}_${lastPage}')">上一页<i class="more_face">☺</i></span>&nbsp;&nbsp;&nbsp;
	    </#if>
		<#if nextPage?? >
	    	<span onclick="nextPage('${mid}_${nextPage}')">下一页<i class="more_face">☺</i></span>
	    </#if>	    
</section>
