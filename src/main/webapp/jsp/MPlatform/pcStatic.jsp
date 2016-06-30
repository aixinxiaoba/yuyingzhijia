<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet"  />
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>
<script type="text/javascript" src="/commons/js/nicEdit.js"></script>

<script type="text/javascript">
function pageStatic(strUrl)
{
	$("#div_data").hide();
	$("#div_msg").show();
	$.ajax({
        url:strUrl,
        type:"post",
        data:{},
        dataType:"text",
        async: true,
        success:function(data)
        {
        	if (data == "success")
        	{
        		$("#div_return_msg").text("执行成功");
        		$("#div_return_msg").show();
        	}
        	else
        	{
        		$("#div_return_msg").text("执行失败");
        		$("#div_return_msg").show();
        	}
        	$("#div_data").show();
        	$("#div_msg").hide();
        }
 		});
}
</script>
</head>
<body style="margin:100px;font-size:16px;">
    <div id="div_return_msg" style="display:none;">
	</div>
	<div id="div_data">
		<div>
			<ul style="line-height:35px;">
			    <li>1、PC端首页静态化：<input type="button" value="执行" onclick="pageStatic('/front/index/indexStatic.do')"/></li>
			    <li>2、PC端汇总最新数据：<input type="button" value="执行" onclick="pageStatic('/front/yuyingshi/gatherlatestNews.do')"/></li>
			</ul>
		</div>
	</div>
	<div id="div_msg" style="display:none;">
		执行中...
	</div>
</body>
</html>
