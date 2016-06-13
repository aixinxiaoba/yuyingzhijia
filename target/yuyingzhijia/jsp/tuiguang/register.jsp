<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>===商户注册申请===</title>
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
// create core object
var objCore = new Core();

/**
 * 页面加载完成时调用。
 */
$(function ()
{
	// 设置是否是必填标识
	 
});

function checkRegister()
{
	return dealWithField();
}

/**
 * 处理字段。
 */
function dealWithField()
{
	if (!objCore.commonTextValidate("ck_SName", false, "登陆名", 1))
	{
		return false;
	}
	if (!objCore.commonTextValidate("ck_password", false, "登陆密码", 1))
	{
		return false;
	}
	if (!objCore.commonTextValidate("ck_repassword", false, "确认密码", 1))
	{
		return false;
	}
	if (!objCore.commonRegValidate("phone", false, "ck_phone", "手机号码", 1))
	{
		return false;
	}
	if (!objCore.commonRegValidate("qq", false, "ck_qq", "QQ号码", 1))
	{
		return false;
	}
	return true;
}

/**
 * 提交表单。
 */
function submitForm(){
	if (!checkRegister())
	{
		return;		
	}
	$("#registsubmit").attr("disabled", "disabled");
	$('#usersForm').ajaxSubmit(function(msg){
				if (msg == "true")
				{
					$("#msg_show").html("恭喜您注册成功！正在引领你到登陆页面！");
					
					alert("恭喜您注册成功！马上登陆看看吧！如您在使用过程中遇到任何问题您可以通过QQ与我们联系，我们将竭诚为您服务");
					location.href = "/login/login.do";
				}
				else
				{
					$("#msg_show").html(msg);
					$("#registsubmit").attr("disabled", false);
				}
	      	}
	    );
}
</script>
</head>
<body style="background-image: url('images/section9.jpg');margin-top: 80px;">

<div id="formbox" style="color: white;">
<div style="color: orange;font-size:15px;margin:10px;">
	说明：此注册窗口为推广窗口，为了提供更好的服务于支持首期注册名额有限，请大家抓紧机会，抓紧时间注册！一旦此窗口关闭您将无法进行注册！
</div>
    <div class="form" id="oper_modify">
      	<span style="font-size:25px;">商户注册</span>
      	<div id="msg_show" align="center" style="margin: 20px;color:orange;height:25px;font-size:15px;"> </div>
      	<form id="usersForm" method="post" action="/mpf/users/usersAdd.do" name="usersForm">
	      			<div class="item"><span class="label" id="span_loginName">登录名：</span>
				        <div class="fl">
				          <input type="text" name="objUsers.strLoginName" class="text" tabindex="1" id="ck_SName" onblur="objCore.commonTextValidate('ck_SName', false, '登录名', '1');"/>
				        </div>
				      </div>
	      		
	      		<div class="item"><span class="label" id="span_loginName">登录密码：</span>
				        <div class="fl">
				          <input type="password" name="objUsers.strPassword" class="text" tabindex="1" id="ck_password" onblur="objCore.commonTextValidate('ck_password', false, '登录密码', '1');"/>
				        </div>
				      </div>
				      
				      <div class="item"><span class="label" id="span_loginName">确认密码：</span>
				        <div class="fl">
				          <input type="password" name="strRePassword" class="text" tabindex="1" id="ck_repassword" onblur="objCore.commonTextValidate('ck_repassword', false, '确认密码', '1');"/>
				        </div>
				      </div>
				      
				       <div class="item"><span class="label" id="span_loginName">手机号：</span>
				        <div class="fl">
				          <input type="text" name="objUsers.strPersonPhone" class="text" tabindex="1" id="ck_phone" onblur="objCore.commonRegValidate('phone', false, 'ck_phone', '手机号码', 1);"/>
				        </div>
				      </div>
				      
				      <div class="item"><span class="label" id="span_loginName">QQ号：</span>
				        <div class="fl">
				          <input type="text" name="objUsers.strReserver1" class="text" tabindex="1" id="ck_qq" onblur="objCore.commonRegValidate('qq', false, 'ck_qq', 'QQ号码', 1);"/>
				        </div>
				      </div>
      	</form>
      
      <div class="item"><span class="label">&nbsp;</span>
        <input type="button" class="yellow_button" id="registsubmit" value="提交注册信息" tabindex="8" onclick="submitForm()"/>
      </div>
    </div>
    
    <div style="text-align:center;border:1px solid green;margin:20px;font-size:20px;font-weight: bold" id="oper_return">
	 	如您在注册过程中遇到问题可以咨询我们，我们将真诚为您解决。
	 	联系QQ：2496664615
	 </div>
</div>
</body>
</html>