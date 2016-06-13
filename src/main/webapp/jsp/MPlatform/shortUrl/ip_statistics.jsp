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
	$("#result").show("请稍后...");
	$("#make").hide();
	$('#usersForm').ajaxSubmit(function(msg){
			msg = msg.split("@@");
			
			if (msg[0] == "error")
			{
				alert(msg[1]);
				$("#result").hide();
				$("#make").show();
			}
			else
			{
				$("#result").html("制作成功，您的短网址为：" + msg[1] + " 请牢记！！！");
			}
      	}
    );
}

function getShortUrl()
{
	if ($("#myUrl").val() == "")
	{
		alert("请输入短网址！！！");
		return;
	}
	
	var myUrl = document.getElementById("myUrl").value.trim();
	var flag = myUrl.substring(myUrl.lastIndexOf("/") + 1);
	
	$("#result").show("请稍后...");
	$("#make").hide();
	$.getJSON("http://955.cc/api/statistics/" + flag + ".json?type=all",
		function(data){
			var strHTML = "<div>短网址编码为：" + data.code + "</div>";
			strHTML += "<div>IP数为：" + data.data.ips + "</div>";
			strHTML += "<div>点击数为：" + data.data.clicks + "</div>";
			$("#result").html(strHTML);
		}
	);
}

</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">短网址统计</span></div>
	<div id="make">
		<form action="/mpf/customer/createShortUrl.do" method="post" id="usersForm">
			<div style="text-align:center;margin-top:20px;">
				<div>
					<span style="font-size:20px;">请输入您的短网址：</span>
					<input type="text" value="" id="myUrl" name="myUrl" class="text" tabindex="1" id="ck_SName" style="width: 500px; height:25px;border:1px solid green;font-size:15px;"/>
				</div>
			</div>
		</form>
		<div style="margin-top: 40px;margin-left:20px;text-align: center;">
			<input type="button" onclick="getShortUrl();" value="开始生成" name="create"/>
		</div>
	</div>
	
	<div id="result" style="margin-top: 40px;margin-left:20px;text-align: center;font-size:25px;"></div>
</div>
</body>
</html>
