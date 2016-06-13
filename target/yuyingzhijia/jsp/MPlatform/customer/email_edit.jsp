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

$(function(){
	bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
});

function submitForm(){
	$("#oper_modify").slideUp();
	$("#oper_return").slideDown();
	$("#oper_result").text("正在发送...可能比较慢，请耐心等候...");
	$("#content").val($(".nicEdit-main").html());
	$('#emailForm').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				$("#oper_result").text("发送成功！");
			}
			else
			{
				$("#oper_result").text(msg);
			}
      	}
    );
}

function operReturn()
{
	$("#oper_modify").slideDown();
	$("#oper_return").slideUp();
}
</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">基本信息</span></div>
	<div id="oper_modify">
		<div style="margin-top: 10px;" id="formbox" class="form">
			<form action="/mpf/email/emailSend.do" method="post" id="emailForm">
				<!-- 项目id -->
				<input type="hidden" value="${param.lProjectID}" name="lProjectID"/>
				<div class="item">
					<span class="label" id="span_${proFileInfo.strPfidentity}">邮件标题：</span>
			        <div class="fl">
			          <input type="text" value="${objCustomer.strSname}" name="objEmail.strSubject" class="text" tabindex="1" id="ck_SName"/>
			        </div>
		       </div>
		       <div class="item">
					<span class="label" id="span_${proFileInfo.strPfidentity}">邮件接收者QQ：</span>
			        <div class="fl">
			          <input type="text" value="${param.customerQQ}" name="strCustomerQQ" class="text" tabindex="1" id="ck_SName" readonly="readonly"/>
			                 &nbsp;注：多个qq之间用分号间隔
			        </div>
		       </div>
		       
		       <div style="overflow: hidden;">
					<span class="label" id="span_${proFileInfo.strPfidentity}">邮件内容：</span>
			        <div class="fl" style="border: 1px solid #f7fbfc;">
			          <textarea rows="" cols="" style="width:440px;height:200px; " name="objEmail.strContent" id="content"></textarea>
			        </div>
		       </div>
		     </form>
		</div>
		
		<div style="margin-top: 40px;margin-left:20px;text-align: center;">
			<img src="/commons/image/s10.gif" width="58" height="22" style="cursor: pointer;" onclick="submitForm();">
			&nbsp;&nbsp;
			<img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="history.back()">
		</div>
	</div>
	
	<div style="display:none;text-align:center;" id="oper_return" class="formbox">
	 	<div id="oper_result"></div>
	  	<div style="margin: 30px;"><img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="operReturn()" /></div>
	 </div>
	 
</div>


</body>
</html>
