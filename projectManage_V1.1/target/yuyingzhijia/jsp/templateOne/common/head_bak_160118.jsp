<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<HEADER class="header_wrap">
			<DIV class="header cc" id="J_header">
				<DIV class="logo">
					<A href="/"><IMG alt="日记谷日记网"
						src="/commons/rjg/logo.png"> </A>
				</DIV>
				<NAV class="nav_wrap">
					<DIV class="nav">
						<UL>
							<LI class="current"><A href="/">育婴之家</A></LI>
							<LI><A href="/front/yuyingshi/newsListByTag.do?lProjectMenuID=23&newsTag=1">最新知识</A></LI>
							<LI><A href="/front/index/newsListByTag.do?projectKey=yuyingzhijia&newsTag=1">好文章</A></LI>
							<LI><A href="/front/index/newsListByTag.do?projectKey=yuyingzhijia&newsTag=1">最热阅读</A></LI>
						</UL>
					</DIV>
				</NAV>
				<DIV class="header_search" role="search">
					<FORM id=search_form onsubmit="return OnSubmit()" method=post name=search action="/front/index/newsSearch.do">
						<INPUT name="projectKey" value="${objProject.projectKey }" type="hidden">
						<INPUT name="searchText" id="s" accesskey="s" aria-label="搜索关键词"
							type="text" placeholder="搜索育婴知识" value="" speech=""
							x-webkit-speech="">
						<BUTTON aria-label="搜索" type="submit">
							<SPAN>搜索</SPAN>
						</BUTTON>
						<INPUT name="csrf_token" type="hidden" value="eceab51f3afed16f">
					</FORM>
				</DIV>
				<DIV class="header_login">
					<A href="/jsp/templateOne/login.jsp" rel="nofollow" style="color:#fff">登录</A>
					<A href="/front/customer/register.do?projectKey=yuyingzhijia" rel="nofollow"  style="color:#fff">注册</A>
				</DIV>
			</DIV>
		</HEADER>