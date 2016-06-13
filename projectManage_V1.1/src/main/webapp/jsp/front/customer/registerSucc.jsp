<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${objProject.strPname}--邮箱验证</title>
		<link type="text/css"
			href="/commons/css/register.css"
			rel="stylesheet" />
		<style type="text/css">
body {
	line-height: 20px;
	text-align: center;
}

a img {
	border: none
}

.content {
	width: 724px;
	margin: 100px auto 0 auto;
}

.style14 {
	font-size: 33px;
	font-family: "长城特粗黑体";
	color: #0099ff;
}

.style16 {
	color: #ff0000;
	font-size: 32px;
}

.style21 {
	font-size: 40px;
	font-family: "长城特粗黑体";
	color: #ffff00;
}

.style24 {
	color: #000000
}

.style30 {
	color: #0000cc
}

.style31 {
	color: #0000ff
}

.style32 {
	font-size: 31px
}

.style33 {
	font-family: "黑体";
	font-weight: bold;
}

a:hover {
	color: #999999;
}
</style>
<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>

<script type="text/javascript" src="/commons/js/jquery.funkyUI.js"></script>

		<script type="text/javascript">

$.unfunkyUI = function ()
{
	location.href = "/jsp/moban/${objProject.lId }/index.html";
}
		
function block(){
	$.funkyUI({url:"/jsp/front/customer/email_handler.jsp?id=${objProject.strEmailListPID}&to=${objCustomer.strQQ}@qq.com",css:{width:"850",height:"500"}});
}

function submitform() {
	// document.myform.submit();
	$("#oper_dingyue").text("正在处理，请稍后...");
	// $("#msg_module").html("正在处理...");
	/*$('#myform').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				alert("修改成功！");
				i
				// location.href = "/mpf/project/projectList.do";
			}
			else
			{
				alert(msg);
			}
      	}
    );*/
    
    block();
    /*
    
    var strURL = "/mpf/customer/sendQQEmailList.do";
    $.ajax({
          url:strURL,
          type:"post",
          data:{"emailListID": "${objProject.strEmailListPID}", "to":$("#to").val()},
          dataType:"text",
          async: false,
          success:function(responseText)
          {	           
			if(responseText == "success")
			{
				alert("一封订阅邮件已经成功发送到您的邮箱！请赶快去邮箱订阅吧！");
				location.href="/jsp/moban/${objProject.lId }/result.html";
			}
			else
			{
				document.myform.submit();
			}
          }
       });
    */
}

// qq号码改变时触发。
function onQQChange(objThis)
{
	$("#to").val(objThis.value + "@qq.com");
}

</script>
	</head>
	<body>

		<!--
		<form name="myform" id="myform"
			action="http://list.qq.com/cgi-bin/qf_compose_send" method="post">
			<input name="t" type="hidden" value="qf_booked_feedback" />
			<input name="id" type="hidden" value="${objProject.strEmailListPID}" />
			<input name="to" class="rsstxt" id="to" type="hidden"
				value="${objCustomer.strQQ}@qq.com" />
		</form> 
		 -->
		 
		<div class="content">
			<div
				style="border: 20px solid #8aab54; height: 180px; width: 600px; padding: 30px;">
				<div>
					<span class="style14">注册成功</span>
					<span class="style32" style="font-weight:bolder;line-height:30px;font-family:楷体"> 
						<span class="style24" style="">请点击下面的【立即验证】获取我们的QQ邮件服务！</span>
					</span>
					<%-- <span class="style16">【获取】</span>--%>
				</div>
				<!-- 
				<div style="padding: 70px;">
					<a target="_self" onclick="submitform()"
						href="/jsp/moban/${objProject.lId }/result.html	">
						<img alt="填写您的邮件地址，订阅我们的精彩内容：" border="0" src="/commons/image/m_05.jpg" />
					</a>
				</div>
				 -->
				 <div style="padding-top:50px;vertical-align: middle">
				 	<span>订阅QQ为：<input class="rsstxt" type="text" value="${objCustomer.strQQ}" style="border:blackpx solid green;height:25px;font-size:25px;width:160px;" onchange="onQQChange(this);"/>@qq.com</span>
				 </div>
				 <div style="margin-top:15px;" id="oper_dingyue">
				 	
					<a target="_self" onclick="submitform()" style="cursor: pointer;">
						<img alt="填写您的邮件地址，订阅我们的精彩内容：" border="0" src="/commons/image/m_05.jpg" />
					</a>
				</div>
			</div>
			<!-- 
			<div>
				<strong>订阅失败!</strong>
				<span class="style31">【</span><a class="style31"
					href="/jsp/moban/${objProject.lId }/index.html">返回首页</a><span
					class="style30">】</span>
			</div>
			 -->
		</div>
		<!-- 请在这里加入您想修改的内容 -->
		<div style="text-align: center; margin-top: 100px;">
			<div>
				技术支持：
				<img src="/mycrm/commons/images/aliyun.jpg" width="130" height="40" />
				&nbsp;&nbsp;
				<img src="/mycrm/commons/images/jiquanshangxiao.jpg" width="130"
					height="40" />
				&nbsp;&nbsp;
				<img src="/mycrm/commons/images/360logo.jpg" width="130" height="40" />
			</div>
			<div style="margin-top: 20px;">
				联系地址：哈尔滨创业园区
			</div>
		</div>
	</body>
</html>