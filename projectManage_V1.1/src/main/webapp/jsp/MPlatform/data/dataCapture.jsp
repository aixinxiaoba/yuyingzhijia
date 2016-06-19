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
function dataCapture()
{
	//$("#btm_start_cap").hide();
	$.ajax({
        url:"/mpf/news/dataCapture.do",
        type:"post",
        data:{"category":$("#category").val(),
        	"maxPageNum":$("#maxPageNum").val(),
        	"categoryNum":$("#categoryNum").val(),
        	"menuKey":$("#menuKey").val(),
        	"startPageNo":$("#startPageNo").val(),
        	"webType":$("#webType").val()
        	
        	},
        dataType:"text",
        async: true,
        success:function(data)
        {
        	alert(data);
        	//$("#btm_start_cap").show();
        }
 		});
}
</script>
</head>
<body style="margin:100px;">
	<form>
		举例：http://www.yuyingnet.com/zbhy/lxzs/list_72_5.html
		<p>
		分类：zbhy/lxzs
		<br/>
		最大页码：5
		<br/>
		分类号：72
		</p>
		
		<div>
			<ul>
				<li>http://baby.5721.net/Newborn/List_1_1.shtml</li>
			</ul>
		</div>
		
		网站标志：<input type="text" name="webType" id="webType" value="5721"/>
		</p>
		菜单ID：<input type="text" name="menuKey" id="menuKey" value=""/>
		<br/>
		分类：<input type="text" name="category" id="category" value=""/>
		<br/>
		最大页码：<input type="text" name="maxPageNum" id="maxPageNum" value=""/>
		<br/>
		分类号：<input type="text" name="categoryNum" id="categoryNum" value=""/>
		<br/>
		开始页码：<input type="text" name="startPageNo" id="startPageNo" value=""/>
		<br/>
	</form>
	<div id="btm_start_cap">
		<input type="button" value="开始获取" onclick="dataCapture();"/>
	</div>
</body>
</html>
