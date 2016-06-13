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
function submitForm(){
	$("#content").val($(".nicEdit-main").html());
	$('#usersForm').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				alert("保存成功！");
				location.href = "/jsp/MPlatform/news/newsList.jsp";
			}
			else
			{
				alert(msg);
			}
      	}
    );
}

bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">关于我们</span></div>
	
	<div style="padding:40px;font: 17px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;list-style:none;line-height:2em">
		服务宗旨：致力于帮助更多的人来完成自己的互联网创业梦想，快速搭建项目，帮您解除项目搭建的烦恼！<br/>
		服务内容：项目辅助建设，项目定制化开发，项目维护，项目设计等服务。<br/>
		发展目标：致力于各种项目的定制开发，帮助更多人完成创业梦想。<br/>
		我们的优势：我们一直专注于项目自动化开发领域，在项目自动化开发方面有着足够的经验，相信我们的努力，一定可以为您带去成功的喜悦，欢迎您进入到我们团队中来。<br/>
		联系电话：15601813123（上海） 姜先生<br/>
		联系地址：上海市浦东新区宁桥路999号
	</div>
</div>


</body>
</html>
