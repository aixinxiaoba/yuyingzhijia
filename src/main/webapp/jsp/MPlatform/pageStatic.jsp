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
function pageStatic(flag)
{
	var strURL = "/front/m/static/" + flag + ".do";
	$("#div_data").hide();
	$("#div_msg").show();
	
	$.ajax({
        url:strURL,
        type:"post",
        data:{"param1":$("#param1").val()},
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
    <div id="div_return_msg" style="display:none;"></div>
	<div id="div_data">
		<div>参数1：<input type="text" name="param1" id="param1" /></div>
		<div>
			<ul style="line-height:35px;">
			    <li>1、移动端首页静态化：<input type="button" value="执行" onclick="pageStatic('indexStatic');"/></li>
				<li>2、移动端子菜单全局静态化：<input type="button" value="执行" onclick="pageStatic('subMenuGlobalStatic');"/></li>
				<li>3、移动端所有子菜单前七条知识静态化：<input type="button" value="执行" onclick="pageStatic('subMenuStatic');"/></li>
				<li>4、移动端指定的二级分类的静态处理：<input type="text" value="" /><input type="button" value="执行" onclick="pageStatic('appointSubMenuStatic');"/></li>
				<li>5、移动端子菜单最新滚动知识静态化：<input type="button" value="执行" onclick="pageStatic('scrollPhotoStatic');"/></li>
				<li>6、移动端静态化指定一级菜单下的滚动图片：<input type="text" value="" /><input type="button" value="执行" onclick="pageStatic('amScrollPhotoStatic');"/></li>
				<li>7、移动端静态化二级级菜单下的滚动图片：<input type="button" value="执行" onclick="pageStatic('scrollPhotoLevel2Static');"/></li>
				<li>8、移动端二级菜单下所有知识分页：<input type="button" value="执行" onclick="pageStatic('subMenuPageStatic');"/></li>
				<li>9、移动端导航静态化：<input type="button" value="执行" onclick="pageStatic('nav');"/></li>
				<li>10、移动端内容静态化：<input type="button" value="执行" onclick="pageStatic('detailStatic');"/></li>
			</ul>
		</div>
		<div id="btm_start_cap" style="margin-top:40px;">
			<input type="button" value="全部静态化" onclick="pageStatic(7);"/>
		</div>
	</div>
	<div id="div_msg" style="display:none;">
		执行中...
	</div>
</body>
</html>
