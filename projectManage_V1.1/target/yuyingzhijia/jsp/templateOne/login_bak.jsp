<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0034)http://www.yuyingzhijia.cn/index.asp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>月嫂，月嫂公司，月嫂培训，正规月嫂公司，育儿嫂，育婴师</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<META name=description
	content=育婴之家庭服务为您提供专业月嫂，月嫂公司，月嫂，育儿嫂，育婴师。>
<META name=keywords content=月嫂，月嫂公司，月嫂，月嫂公司，正规月嫂公司，育儿嫂，育婴师>

<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/index_css/index_123.css">
<LINK rel="stylesheet" type="text/css" href="/commons/housekeeper/images/Css.css" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>

<style type="">
.datagrid-row{
	height:65px;
}
</style>
<script type="text/javascript">
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
		submitForm();
	}
}

function submitForm(){
	$('#formLogin').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				location.href = "/";
			}
			else
			{
				$("#error_msg").text(msg);
			}
      	}
    );
}
</script>
</HEAD>
<BODY>
	<%@ include file="../common/headTwo.jsp" %>
	<TABLE style="MARGIN-TOP: 23px;" cellSpacing=0 cellPadding=0 width=992
			align=center border=0>
			<TBODY>
				<TR>
					<TD vAlign=top>
						<TABLE cellSpacing=0 cellPadding=0  bgColor=#ffffff
							border=0>
							<TBODY>
								<TR>
									<TD style="BACKGROUND: url(/commons/housekeeper/images/d_top.jpg);MARGIN-TOP: 14px;padding-left: 40px;width:992px;" height=37>
										<span style='font-size:15px;color:ff6600;font-weight: bold'  id="menu_key">登陆</span>
									</TD>
								</TR>
								<TR>
									<TD >
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		
		<!-- 登陆信息 -->
		<div id="formbox">
    <div class="form" id="oper_modify">
      	<h3>用户登陆</h3>
      	<div id='error_msg' style="color:red;text-align:center;font-size:13px;"></div>
      	<s:form action="/login/customerLogin.do" method="post" id="formLogin">
      	<!-- 平台标志 -->
      	<input type="hidden" name="strPlatformFlag" value="1"/>
      	<input type="hidden" name="projectKey" value="${projectKey}"/>
      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">登录名：</span>
		        <div class="fl">
		          <input type="text" id="ck_email" name="strUsername" class="text" tabindex="5"onblur="objCore.commonRegValidate('email', false, 'ck_${proFileInfo.strPfidentity }', '${proFileInfo.strPfname}', <s:property value="nPfnature"/>)"/>
		        </div>
		      </div>
		      
		      <div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">密码：</span>
		        <div class="fl">
		          <input type="password" id="ck_email" name="strPassword" class="text" tabindex="5"onblur="objCore.commonRegValidate('email', false, 'ck_${proFileInfo.strPfidentity }', '${proFileInfo.strPfname}', <s:property value="nPfnature"/>)"/>
		        </div>
		      </div>
		      
		      <div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">验证码：</span>
		        <div class="fl">
		          <input type="text" id="ck_email" name="strVerifycode" class="text" tabindex="5"onblur="objCore.commonRegValidate('email', false, 'ck_${proFileInfo.strPfidentity }', '${proFileInfo.strPfname}', <s:property value="nPfnature"/>)"/>
		        	<span style="left">
						<img src="/imageServlet" id="imageCode" name="imageCode" title="点击更换验证码" style="width: 75px;height: 29px;vertical-align:middle" onclick="changeImageCode()" /><a href="javascript:changeImageCode();" style="text-decoration: none;">点击更换</a>
                   	</span>
		        </div>
		      </div>
      	</s:form>
      <div class="item"><span class="label">&nbsp;</span>
        <input type="button" class="yellow_button" id="registsubmit" value="登&nbsp;&nbsp;陆" tabindex="8" onclick="submitForm();"/>
      </div>
    </div>
    
    <div style="display:none;text-align:center;" id="oper_return">
	 	<div id="oper_result"></div>
	  	<div style="margin: 30px;"><img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="operReturn()" /></div>
	 </div>
</div>
		<!--  -->
		
		<TABLE cellSpacing=0 cellPadding=0 width=992 align=center border=0>
			<TBODY>
				<TR>
					<TD height=80>
						<TABLE cellSpacing=0 cellPadding=0 width=992 border=0>
							<TBODY>
								<TR>
									<TD>
										<P>
											Copyright
											<A href="http://www.yuyingzhijia.cn/"><FONT color=#800080>www.yuyingzhijia.cn</FONT>
											</A>&nbsp;All Rights Reserved 版权所有
										</P>
									</TD>
									<TD width=290 align=right>
										育婴之家
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		
</BODY>
</HTML>

