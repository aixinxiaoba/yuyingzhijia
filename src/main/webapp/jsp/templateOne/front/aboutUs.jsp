<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>育婴之家--育婴师知识</TITLE>
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
			content="育婴之家是一个分享交流育儿知识经验的平台，我们致力于提供最好的育儿知识，通过我们这个平台将知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">
		<link rel="shortcut icon" href="/favicon.ico?version=3">
		<LINK href="/commons/yuyingshi/css/style5.css" rel="stylesheet" type="text/css">
		<LINK href="/commons/yuyingshi/css/style.css" rel="stylesheet" type="text/css">
		<LINK href="/commons/yuyingshi/css/content.css" rel="stylesheet" type="text/css">
		<LINK href="/commons/yuyingshi/css/index.css" rel="stylesheet" type="text/css">
		<LINK href="/commons/css/about-us.min.css" rel="stylesheet" type="text/css">
		
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
		<%@ include file="../../common/head.jsp" %>
		<!-- 头部设置 end -->
		
		<!--gloabl_nav start-->
		<%@ include file="../../common/nav.jsp" %>
		<!--gloabl_nav end -->
		
		<DIV class="bg-img">
			<DIV class="main">
				<DIV class="navigation nav-abs">
					<UL style="min-height: 430px;">
						<LI class="cur">
							<I class="aui"></I><A
								href="javascript:void(0)" target="_self">关于我们</A><EM></EM>
						</LI>
						<!-- 
						<LI>
							<I class="bpi"></I><A href="http://www.ci123.com/intro.html"
								target="_self">机构简介</A><EM></EM>
						</LI>
						<LI class="pe">
							<I class="pei"></I><A
								href="http://www.ci123.com/2008/ad/event.php" target="_self">育儿大事件</A><EM></EM>
						</LI>
						<LI class="as">
							<I class="asi"></I><A class="as-title"
								href="http://www.ci123.com/ad/" target="_blank">广告服务</A><I
								class="list-icon"></I><EM></EM>
						</LI>
						<LI>
							<I class="cui"></I><A href="http://www.ci123.com/contact.html"
								target="_self">联系我们</A><EM></EM>
						</LI>
						<LI>
							<I class="hpi"></I><A href="http://www.ci123.com/hr.html"
								target="_self">诚聘英才</A><EM></EM>
						</LI>
						<LI>
							<I class="tosi"></I><A href="http://www.ci123.com/terms.html"
								target="_self">服务条款</A><EM></EM>
						</LI>
						<LI>
							<I class="ppi"></I><A href="http://www.ci123.com/privacy.html"
								target="_self">隐私保护</A><EM></EM>
						</LI>
						<LI>
							<I class="mri"></I><A
								href="http://user.ci123.com/account/NewAccount/" target="_blank">会员注册</A><EM></EM>
						</LI>
						<LI>
							<I class="whi"></I><A href="http://help.ci123.com/"
								target="_blank">网站帮助</A><EM></EM>
						</LI>
						<LI>
							<I class="smi"></I><A href="http://www.ci123.com/sitemap.html"
								target="_blank">网站地图</A><EM></EM>
						</LI>
						<LI>
							<I class="fli"></I><A href="http://www.ci123.com/friendlink.html"
								target="_self">友情链接</A><EM></EM>
						</LI>
						 -->
					</UL>
				</DIV>
				<DIV class="content">
					<DIV class="as-content">
						<DIV class="title">
							<SPAN class="orange-block"></SPAN>关于我们
						</DIV>
						<P>
							育婴之家成立于2015年4月！旨在通过育婴分享平台将更多的育婴知识分享到经验困乏的妈妈手中！来为宝妈们提供一个良好的阅读环境！为育婴师学习的朋友提供更好的资料！
						</P>
						<p>
							育婴之家现主要包含：育婴师专区、育儿专区、宝妈专区、怀孕小知识等主要模块！各个模块资料我们正在筹备中，欢迎大家来信说出自己喜欢的模块，我们将着重收集您喜欢的资料。同时您也可以关注我们的官方微博，更方便的获取育婴信息。
						</p>
						<p>
							本站部分资料来源于网络，如无意中侵犯到了您的原创的作品权利，请及时通知我们，我们会在12小时内立即删除。
						</p>
						<p>
							育婴之家团队感谢各位朋友的支持。祝大家在这里学习愉快！
							我们期待你的加入: &nbsp;<a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=fddd1956a39b264c84962a6b2bb3ef27c525c62d84989c8231aa012d219bfacb"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="育婴师交流群" title="育婴师交流群"></a>
							您也可以通过微博 与我们互动，分享您的精彩育婴时刻！
						</p>
					</DIV>
				</DIV>
			</DIV>
		</DIV>
	</BODY>
</HTML>