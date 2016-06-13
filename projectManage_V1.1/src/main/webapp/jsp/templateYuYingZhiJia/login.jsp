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
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/index_css/index_123.css">
<LINK rel="stylesheet" type="text/css" href="/commons/housekeeper/images/Css.css" />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js"></script>

	<script type="text/javascript" src="/commons/js/md5.js"></script>
<!-- 

<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/jquery.kinMaxShow-1.0.min1.js ">
	
	
<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui_user_defined.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

   <link href="/commons/css/site.css" rel="stylesheet" type="text/css" />
		<link href="/commons/css/swfupload/default.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/commons/js/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="/commons/js/swfupload/swfupload.queue.js"></script>
		<script type="text/javascript" src="/commons/js/swfupload/fileprogress.js"></script>
		<script type="text/javascript" src="/commons/js/swfupload/handlers.js"></script>
        <script type="text/javascript" src="/commons/js/jquery.form.js"></script>
	
 -->
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
		login.submit();
	}
}
</script>
</HEAD>
<BODY>
	<%@ include file="../common/head.jsp" %>
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
										<span style='font-size:15px;color:ff6600;font-weight: bold'  id="menu_key">注册</span>
									</TD>
								</TR>
								<TR>
									<TD >
										<%@ include file="../register.jsp" %>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
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

