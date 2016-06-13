<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet"  />
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>

<script type="text/javascript">
function submitForm(){
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

</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">新闻阅读</span></div>
	<div style="margin-top: 10px;" id="formbox">
			<div style="text-align: center;margin:10px;">
				<span class="label" id="span_${proFileInfo.strPfidentity}">新闻标题：${objNews.strTitle}</span>
			</div>
	       <div>
				<span class="label" id="span_${proFileInfo.strPfidentity}">新闻内容：</span>
		        <div style="padding: 15px;">
		          ${objNews.strContent}
		        </div>
	       </div>
	</div>
	
	<div style="margin-top: 40px;margin-left:20px;text-align: center;">
		<img src="/commons/image/s8.gif" width="58" height="22" style="cursor: pointer;" onclick="history.back()">
	</div>
</div>
</body>
</html>
