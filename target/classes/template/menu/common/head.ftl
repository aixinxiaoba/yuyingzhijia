<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript">
$(document).ready(function() {
	recordVisitLog();
})

// 设置访问日志。
function recordVisitLog()
{
	var strURL = "/front/index/visitLog.do";
	
	$.ajax(
	{
        url:strURL,
        type:"post",
        data:{"refer":"<%=strRefer%>","thisPage":"<%=strThisPage%>"},
        dataType:"text",
        async: false,
        success:function(responseText)
        {	           
			
        }
    });
}
</script>

<DIV class="navi-head" id="navi-head" name="navi-head">
	<DIV class="navi-con-block">
		<DIV class="navi-left">
			<A class="page-title-pic" id="page-title"
				href="/index.html"></A>
			<A class="first-page" id="first-self" href="/index.html">首页</A>
			<!-- 暂不使用其他菜单 -->
		</DIV>
	</DIV>
</DIV>