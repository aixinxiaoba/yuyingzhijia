 <#list lstRollingOfReading as news>
	<div class="item">
		<a
			href="/front/yuyingshi/detail.do?newsID=${news.lId}">
			<img lazyload="${news.imageUrl}" />
		</a>
		<p class="caption">
			${news.strLongTitleTwo}
		</p>
	</div>
</#list>
