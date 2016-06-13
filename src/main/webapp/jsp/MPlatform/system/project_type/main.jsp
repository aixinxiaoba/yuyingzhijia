<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet"  />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>
<style type="text/css">
.promanage li{line-height: 40px;margin-right:20px;}
.promanage li a{border:2px solid green; font-size:20px; font-weight:bolder; font-family:"楷体";text-decoration: none;}
</style>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">项目管理</span></div>
	<div class="formbox">
		<ul style="text-align:center;border:0px solid red;padding:40px;" class="promanage">
			<li><a href="/jsp/MPlatform/project/customerType/customerTypeList.jsp?lProjectID=${param.lProjectID}">客户类型设置</a></li>
			<li>
				<a href="/jsp/MPlatform/project/projectFiledInfoList.jsp?lProjectID=${param.lProjectID}">注册流程设置</a>
			</li>
			<!-- <li>
				<a href="/jsp/MPlatform/project/projectFiledInfoList.jsp?lProjectID=${param.lProjectID}">项目授权</a>
			</li> -->
		</ul>
	</div>
	<div style="text-align: center;">
		<img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="location.href='/mpf/project/projectList.do'">
	</div>
</div>
</body>
</html>
