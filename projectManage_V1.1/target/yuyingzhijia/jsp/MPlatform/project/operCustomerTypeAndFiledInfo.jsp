<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet"  />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>
<style type="text/css">
/*.promanage li{line-height: 40px;margin-right:20px;}*/
/*.promanage li a{border:2px solid green; font-size:20px; font-weight:bolder; font-family:"楷体";text-decoration: none;}*/
</style>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">项目管理</span>
		<a href="/jsp/MPlatform/project/operCustomerTypeAndFiledInfo.jsp?lProjectID=${param.lProjectID}" style="margin-left:80%;font: 14px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;">返回上一层</a>
	</div>
	<div>
		<ul style="text-align:center;padding:20px;" class="promanage">
			<li ><a style="font: 20px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;list-style:none;" href="/jsp/MPlatform/project/customerType/customerTypeList.jsp?lProjectID=${param.lProjectID}">客户类型设置</a></li>
			<li>
				<a style="font: 20px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;list-style:none;" href="/jsp/MPlatform/project/projectFiledInfoList.jsp?lProjectID=${param.lProjectID}">注册流程设置</a>
			</li>
		</ul>
	</div>
</div>
</body>
</html>
