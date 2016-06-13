<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>项目自动化系统登陆</title>
    <link href="/commons/css/alogin.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="/commons/js/md5.js"></script>
<script language="javascript">
function init()
{
	// 验证码处理
	$("#imageCode").attr("src", "/imageServlet?flag=" + (new Date()).getTime());
	var o = document.getElementById("Layer1");
	o.style.left = (screen.width/2 - o.offsetLeft/2) + "px";
}

/**
 * 验证用户名。
 */
function checkUsername()
{
	$("#userName_errorMsg").text("");
	if($("#username").val() == "")
	{
		$("#userName_errorMsg").text("用户名不能为空");
		return false;
	}
	
	return true;
}

/**
 * 验证密码。
 */
function checkPassword()
{
	$("#pwd_errorMsg").text("");
	if($("#password").val() == "")
	{
		$("#pwd_errorMsg").text("密码不能为空");
		return false;
	}
	
	return true;
}

/**
 * 验证验证码。
 */
function checkImageCode()
{
	$("#imageCode_errorMsg").text("");
	if($("#verifycode").val() == "")
	{
		$("#imageCode_errorMsg").text("验证码不能为空");
		return false;
	}
	
	return true;
}

function changeImageCode(){
	$("#imageCode").attr("src", "/imageServlet?flag=" + (new Date()).getTime());
}

function startLogin()
{
	login.submit();
}

/**
 * 回车触发。
 */
document.onkeydown=function(e)
{
	var keycode=document.all?event.keyCode:e.which;
	
	if(keycode==13)
	{
		login.submit();
	}
}
</script>

</head>
<body>
    <div class="Main">
        <ul>
            <li class="top"></li>
            <li class="top2"></li>
            <li class="topA"></li>
            <li class="topB">
            	<span style="font-size:40px;">
            		<br/>
            		个人后台登陆
                	<!-- <img src="/commons/images/login/logo.gif" alt="" style="" /> -->
            	</span>
            </li>
            <li class="topC"></li>
            <li class="topD">
<%--            	<s:form action="usersLogin.do" method="post" name="login" id="login">--%>
            	<s:form action="/mpf/customer/customerLogin.do" method="post" name="login" id="login">
	                <ul class="login">
	                	<li>
	                    	<s:fielderror cssStyle="color: red"></s:fielderror>
	                    </li>
	                    <li>
	                    	<span class="left">用户名：</span> 
	                    	<span style="left">
	                        	<input id="Text1" type="text" class="txt" name="strNickName" value="${strNickName}" onkeydown="if(event.keyCode==32) return false"/>
	                    	</span>
	                    	<span id="userName_errorMsg" style="font-size:12px;color:#ff0000;"></span>
	                    </li>
	                    <li>
	                    	<span class="left">密 码：</span> 
	                    	<span style="left">
	                       		<input type="password" class="txt" name="strPassword" id="password" onkeydown="if(event.keyCode==32) return false"/>  
	                    	</span>
	                    	<span id="pwd_errorMsg" style="font-size:12px;color:#ff0000;"></span>
	                    </li>
	                     <li>
	                     	<span class="left" style="">验证码：</span>
	                     	<span style="left">
	                    		<input type="text" class="txtCode" name="strVerifycode" id="verifycode"/>
	                    	</span>
	                    	<span id="imageCode_errorMsg" style="font-size:12px;color:#ff0000;"></span>
	                    	<span style="left">
								<img src="/imageServlet" id="imageCode" name="imageCode" title="点击更换验证码" style="width: 75px;height: 29px;vertical-align:middle" onclick="changeImageCode()" /><a href="javascript:changeImageCode();" style="text-decoration: none;">点击更换</a>
	                    	</span>
	                    </li>
	                </ul>
                </s:form>
            </li>
            <li class="topE"></li>
            <li class="middle_A"></li>
            <li class="middle_B"></li>
            <li class="middle_C">
	            <span class="btn" onclick="startLogin();" style="cursor:pointer;">
	               <img alt="" src="/commons/images/login/btnlogin.gif"/>
	            </span>
            </li>
            <li class="middle_D"></li>
            <li class="bottom_A" style="font-size: 15px; color:red;text-align:center;font-variant: 2px;">说明：1、新注册用户默认密码为6个1，用户名则为您注册时的登录名。<br/>2、如果提示您的用户被禁用了则说明您已被您的管理员设置为禁止登陆状态。<br/>3、您在使用过程中遇到任何问题都可通过qq与qq群与我们联系</li>
        </ul>
     <div>
       
    </div>
    </div>
    <div align=center style="color:white;font-size:15px;">
    QQ：376289635&nbsp;&nbsp;&nbsp;&nbsp;
    Phone:15601893123&nbsp;&nbsp;&nbsp;&nbsp;
    QQ群：<a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=bf25712c54ca049803ee847e1a47f45bd5cfed7f80d1e8b47e4bd9d84da4f632"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="PM官方交流群" title="PM官方交流群"></a>
    </div>
</body>
</html>
