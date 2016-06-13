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
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">寻求帮助</span></div>
	
	<div style="padding:50px;font-size:20px;color:#545454;font-family:'宋体';">
		<ul>
			<li style="font: 14px/50px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;list-style:none;cursor:pointer;">项目使用介绍</a></li>
			<li style="font: 14px/50px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;list-style:none;cursor:pointer;">
				<div>
					&nbsp;&nbsp;使用说明；
					
					资料文档正在完善中（感谢您的支持与使用）...
					<br/>
					<!-- 
					1、客户管理
					管理注册用户，对注册用户的简单操作。
					
					2、项目推广
					项目一旦提交注册并审核通过您将会得到一个项目地址您可以对您的项目进行管理。
					3、定制化开发
					 -->
					 
					 如有疑问您可以通过以下方式联系我们：<br/>
					 个人QQ：376289635<br/>
					 PM交流群：438564861<a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=bf25712c54ca049803ee847e1a47f45bd5cfed7f80d1e8b47e4bd9d84da4f632"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="PM官方交流群" title="PM官方交流群"></a>
					 <br/>
					 电话：15601813123<br/>
					 
					 注意：<span style="color: red;">系统更新时间为晚上11点，持续时间为5-10分钟，具体更新日期以系统通知为准，如有给您造成的不便敬请谅解</span>
				</div>
			</li>
		</ul>
	</div>
</div>


</body>
</html>
