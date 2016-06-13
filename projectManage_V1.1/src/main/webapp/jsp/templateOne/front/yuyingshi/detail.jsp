<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>${objNews.strTitle}-${objProjectMenu.strMenuName }-育婴知识学习交流平台</TITLE>
		<META content="IE=11.0000" http-equiv="X-UA-Compatible">
		<META http-equiv="Content-Type" content="text/html; charset=utf-8">
		<META http-equiv="X-UA-Compatible" content="IE=Edge">
		<META http-equiv="pragma" content="no-cache">
		<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
		<META http-equiv="expires" content="0">
		<META name="render" content="webkit">
		<META name="Keywords" content="育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
		<META name="description" content="育婴之家是一个分享交流育儿知识经验的平台，我们致力于提供最好的育儿知识，通过我们这个平台将知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">
		<link rel="shortcut icon" href="/favicon.ico?version=3">
		<LINK href="/commons/yuyingshi/css/style.css" rel="stylesheet" type="text/css">
		<LINK href="/commons/yuyingshi/css/content.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
		<script type="text/javascript" src="/commons/js/0_core.js" ></script>
		<META name="GENERATOR" content="MSHTML 11.00.9600.17690">
	<script type="text/javascript"> objCore.browserRedirect("p","/static/m_news/${objNews.lId}"); </script>
	</HEAD>
	<BODY>
		<!-- 头部设置 -->
		<%@ include file="common/head.jsp" %>
		
		<!--gloabl_nav start-->
		<%@ include file="common/nav.jsp" %>
		<!--gloabl_nav end -->
		
		<!--content start-->
		<%@ include file="common/content-detail.jsp" %>
		<!--content end -->
		
		<!--wrapper-->
		<DIV id="wrapper" style="padding-top: 9px;"></DIV>
		<!-- 底部版权信息begin -->
		<%@ include file="common/foot.jsp" %>
		<!-- 底部版权信息end -->
	</BODY>
</HTML>