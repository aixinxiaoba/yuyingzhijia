<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>育婴之家--育婴知识分享学习交流平台</TITLE>
		<META content="IE=11.0000" http-equiv="X-UA-Compatible">
		<META http-equiv="Content-Type" content="text/html; charset=utf-8">
		<META http-equiv="X-UA-Compatible" content="IE=Edge">
		<META http-equiv="pragma" content="no-cache">
		<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
		<META http-equiv="expires" content="0">
		<META name="render" content="webkit">
		<META name="Keywords"
			content="育婴之家，儿童之家，育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
		<META name="description"
			content="育婴之家是一个分享交流育儿知识经验的 平台，我们致力于需求最好的育儿知识，通过我们这个平台知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">
		<link rel="shortcut icon" href="/favicon.ico">
		<LINK href="/commons/yuyingshi/css/style.css" rel="stylesheet" type="text/css">
		<LINK href="/commons/yuyingshi/css/content.css" rel="stylesheet" type="text/css">
		<LINK href="/commons/yuyingshi/css/index.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
		<script type="text/javascript" src="/commons/housekeeper/js/slide.js" ></script>
		<SCRIPT type=text/javascript>
		function OnSubmit(){
			var keyword=document.getElementById('keyword');
			if(keyword.value.length==0){
				keyword.focus();
				alert('输入不能为空！');
				return false;
			}
			if(keyword.value=="输入想解决的幼儿疑惑吧~"){
				keyrowd.focus();
				alert('请输入关键字！');
				return false;
			}
			document.getElementById("search_form").submit();
			return false;
		}
		</SCRIPT>
	</HEAD>
	<BODY>
		<!-- 头部设置 -->
		<#include "common/head.jsp"> 
		
		<!--gloabl_nav start-->
		<%@ include file="../common/nav.jsp" %>
		<!--gloabl_nav end -->
		
		<!--content start-->
		<%@ include file="../common/content.jsp" %>
		<!--content end -->
		
		<!--wrapper-->
		<DIV id="wrapper" style="padding-top: 9px;">
		</DIV>
		
		<!-- 底部版权信息begin -->
		<%@ include file="../common/foot.jsp" %>
		<!-- 底部版权信息end -->
	</BODY>
</HTML>