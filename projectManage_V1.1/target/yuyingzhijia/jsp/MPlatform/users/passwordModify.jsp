<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet"  />

<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />

<!-- Tooltip classes -->
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-violet/tip-violet.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-darkgray/tip-darkgray.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-skyblue/tip-skyblue.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-twitter/tip-twitter.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-green/tip-green.css" type="text/css" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.js" ></script>

<!-- the Poshy Tip plugin files -->
<script type="text/javascript" src="/commons/poshytip1.2/js/jquery.poshytip.js"></script>
<script type="text/javascript" src="/commons/js/0_core.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>

<script type="text/javascript">
function submitForm(){
	$('#usresForm').ajaxSubmit(function(msg){
			$("#oper_modify").slideUp();
			$("#oper_return").slideDown();
			if (msg == "success")
			{
				$("#oper_result").text("修改成功！");
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
	<div style="border:1px solid #73c0e0;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">密码修改</span></div>
	<div id="oper_modify">
		<form name="usresForm" method="post" id="usresForm" action="/mpf/users/usersModifyPassowrd.do">
			<div id="formbox" class="form">
				<div class="item"><span class="label" >原始密码：</span>
			        <div class="fl">
			          <input type="password" name="strOldPassword" class="text" tabindex="1" id="ck_SName"/>
			          <label id="username_succeed" class="blank"></label>
			          <span class="clear"></span>
			          <div id="username_error"></div>
			        </div>
			      </div>
			      
			      <div class="item"><span class="label">请输入新密码：</span>
			        <div class="fl">
			          <input type="password" name="strPassword" class="text" tabindex="1" id="ck_SName"/>
			          <label id="username_succeed" class="blank"></label>
			          <span class="clear"></span>
			          <div id="username_error"></div>
			        </div>
			      </div>
			      
			      <div class="item"><span class="label">请再次输入新密码：</span>
			        <div class="fl">
			          <input type="password" name="strRePassword" class="text" tabindex="1" id="ck_SName"/>
			          <label id="username_succeed" class="blank"></label>
			          <span class="clear"></span>
			          <div id="username_error"></div>
			        </div>
			      </div>
	      </div>
	      <div style="text-align:center;">
			<img src="/commons/image/s10.gif" width="58" height="22" style="cursor: pointer;" onclick="submitForm();">
		  </div>
		</form>
	</div>
	 <div style="display:none;text-align:center;" id="oper_return" class="formbox">
	 	<div id="oper_result"></div>
	  	<div style="margin: 30px;"><img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="operReturn()"></div>
	 </div>
</div>
</body>
</html>
