<!-- 新浪微博 关注按钮-->
<html xmlns:wb="http://open.weibo.com/wb">
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
		
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?6f411ee2d6276cabab47bd3c12794874";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>




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
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 >
		<TBODY>
			<TR>
				<TD
					style="BACKGROUND: url(/commons/housekeeper/images/top_bg.jpg)" height=29>
					<TABLE cellSpacing=0 cellPadding=0 width=1100 border=0 style="margin:auto">
						<TBODY>
							<TR>
								<TD height=29 rowspan="1" colspan="1" class="font1">
									&nbsp;欢迎来到${objProject.strPname}！祝您在这里学习愉快！
								</TD>
								<TD align=right class="font-style">
									<div style="width:62px;height:24px;">
										<wb:follow-button uid="2895295707" type="red_1" />
									</div>
			                     </td>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	
