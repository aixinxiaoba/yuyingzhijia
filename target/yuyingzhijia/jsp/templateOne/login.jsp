<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<!-- saved from url=(0044)http://i.rijigu.com/index.php/Index/register -->
<!DOCTYPE html PUBLIC "" "">
<HTML>
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">

<META charset="UTF-8">
<TITLE>注册 - 育婴之家网</TITLE>
<META http-equiv="X-UA-Compatible" content="chrome=1">
<LINK href="/commons/rjg/core.css" rel="stylesheet">
<LINK href="/commons/rjg/style.css" rel="stylesheet">
<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>
<STYLE>
.main .thread_posts_list .st {
	font-size: 14px;
}
</STYLE>
<LINK href="/commons/rjg/register.css" rel="stylesheet">
<META name="GENERATOR" content="MSHTML 11.00.9600.17801">
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
<BODY style="background:rgb(244, 244, 244)">
	<DIV class="wrap">
		<%@include file="common/head.jsp" %>
		<DIV class="tac"></DIV>
		<DIV class="main_wrap">
			<DIV class="box_wrap register cc">
				<H2 class="reg_head">登录</H2>
				<DIV class="reg_cont_wrap">
					<DIV class="reg_cont">
						<FORM action="/login/customerLogin.do" method="post" target="_blank" id="formLogin">
							<DIV class="reg_form">
								<DIV class="tips">
									<SPAN class="tips_icon">登陆后继续访问育婴之家相关内容</SPAN>
								</DIV>
								<div id='error_msg' style="height:40px;color:red;text-align:center;width:500px;font-size:13px;"></div>
								<DL class="cc">
									<DT>
										<LABEL for="J_u_login_username">帐号：</LABEL>
									</DT>
									<DD>
										<INPUT name="strUsername" class="input length_4"
											id="J_u_login_username" aria-required="true" type="text"
											placeholder=" 昵称  " value="" data-id="username">
									</DD>
									<DD class="dd_r" id="J_u_login_tip_username" role="tooltip"
										aria-hidden="true"></DD>
								</DL>
								<DL class="cc">
									<DT>
										<LABEL for="J_u_login_password">密码：</LABEL>
									</DT>
									<DD>
										<INPUT name="strPassword" class="input length_4"
											id="J_u_login_password" aria-required="true" type="password"
											placeholder=" 密码" value="" data-id="password">
									</DD>
									<DD class="dd_r">
										<SPAN id="J_u_login_tip_password" role="tooltip"
											aria-hidden="true"></SPAN>
									</DD>
								</DL>
								<!-- 
								<DL class="cc pick">
									<DT>&nbsp;</DT>
									<DD>
										<A class="fr mr10"
											href="http://wpa.qq.com/msgrd?v=3&amp;uin=263235266&amp;site=qq&amp;menu=yes"
											target="_blank" rel="nofollow">或者直接联系谷主</A> <A
											class="fr mr10"
											href="http://i.rijigu.com/index.php/Index/findPwd"
											rel="nofollow">找回密码</A> <INPUT name="rememberme"
											class="checkbox" id="cktime" type="checkbox" checked=""
											value="31536000"> <LABEL for="cktime">自动登录</LABEL>
									</DD>
								</DL>
								 -->
								<SCRIPT>
									document.write('<input type="hidden" id="ajaxsubmitBtn" value="1" />');
								</SCRIPT>

								<DL class="cc">
									<DT>&nbsp;</DT>
									<DD style="width: 500px;">
										<BUTTON class="btn btn_big btn_submit mr20" type="button" onclick="submitForm();">登录</BUTTON>
										<!-- 
										<A
											onclick="window.open('http://i.rijigu.com/index.php/Qqlogin', 'oauth2Login' ,'height=525,width=585, toolbar=no, menubar=no, scrollbars=no, status=no, location=yes, resizable=yes')"
											href="javascript:;"><IMG align="middle" alt="QQ登录"
											src="/commons/rjg/qqlogin.png" border="0"></A>
										 -->
										&nbsp;&nbsp; <A
											href="https://api.weibo.com/oauth2/authorize?client_id=4148667325&redirect_uri=http://www.yuyingzhijia.cn/login/sinaLogin.do&response_type=code"><IMG align="middle" alt="微博登录"
											src="/commons/rjg/weibo.png" border="0"></A>
									</DD>
								</DL>
							</DIV>
						</FORM>
					</DIV>
				</DIV>
				<DIV class="reg_side">
					<DIV class="reg_side_cont">
						<P class="mb10">还没有帐号？</P>
						<P class="mb20">
							<A class="btn btn_big" href="/front/customer/register.do?projectKey=yuyingzhijia"
								rel="nofollow">现在注册入住</A>
						</P>
						<P class="mb20">
							<A class="btn btn_big" id="kuaijie" href="javascript:void(0);"
								rel="nofollow">使用快捷登录</A>
						</P>
						<P class="mb20">
							<!-- 
							<A
								onclick="window.open('http://i.rijigu.com/index.php/Qqlogin', 'oauth2Login' ,'height=525,width=585, toolbar=no, menubar=no, scrollbars=no, status=no, location=yes, resizable=yes')"
								href="javascript:;"><IMG align="middle" alt="QQ登录"
								src="/commons/rjg/qqlogin.png" border="0"></A>
							 -->
						</P>
						<P class="mb20">
							<A 	href="https://api.weibo.com/oauth2/authorize?client_id=4148667325&redirect_uri=http://www.yuyingzhijia.cn/login/sinaLogin.do&response_type=code"><IMG align="middle" alt="微博登录"
								src="/commons/rjg/weibo.png" border="0"></A>
						</P>
					</DIV>
				</DIV>
			</DIV>
		</DIV>
		<!--.main-wrap,#main End-->
		<DIV class="tac">
			<BR>
		</DIV>
		<DIV class="footer_wrap" style="text-align: center;">
			<%@ include file="../common/foot.jsp" %>
		</DIV>
	</DIV>
</BODY>
</HTML>
