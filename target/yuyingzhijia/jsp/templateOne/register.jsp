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
<STYLE>
.main .thread_posts_list .st {
	font-size: 14px;
}
</STYLE>
<LINK href="/commons/rjg/register.css" rel="stylesheet">
<META name="GENERATOR" content="MSHTML 11.00.9600.17801">
</HEAD>
<BODY style="background:rgb(244, 244, 244)">
	<DIV class="wrap">
		<%@include file="common/head.jsp" %>
		<DIV class="tac"></DIV>
		<DIV class="main_wrap">
			<DIV class="box_wrap register cc">
				<H2 class="reg_head">欢迎注册成为【育婴之家】的会员</H2>
				<DIV class="reg_cont_wrap">
					<DIV class="reg_cont">
						<%@ include file="../register.jsp" %>
					</DIV>
				</DIV>
				<DIV class="reg_side">
					<DIV class="reg_side_cont">
						<P class="mb10">已经有帐号？</P>
						<P class="mb20">
							<A class="btn btn_big"
								href="/jsp/templateOne/login.jsp" rel="nofollow">立即登录</A>
						</P>
						<P class="mb20">
							<A class="btn btn_big" id="kuaijie" href="javascript:;"
								rel="nofollow">合作账号登录</A>
						</P>
						
						<!-- 
						<P class="mb20">
							<A
								onclick="window.open('http://i.rijigu.com/index.php/Qqlogin', 'oauth2Login' ,'height=525,width=585, toolbar=no, menubar=no, scrollbars=no, status=no, location=yes, resizable=yes')"
								href="javascript:;"><IMG align="middle" alt="QQ登录"
								src="/commons/rjg/qqlogin.png" border="0"></A>
						</P>
						 -->
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
