<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<DIV class="footer_main" style="">
	<DIV class="footer" style="text-align: center;">
			<A href="#" >寻求合作</A> ｜ 
			<A href="#" >推广服务</A> 
			<SPAN class="bookmark"> ｜
				<A id="bookmark" href="ext:add_favorite" news="">加入书签</A>
			</SPAN>
	</DIV>
	<DIV style="text-align: center; color: rgb(186, 186, 186); font-size: 12px;">
		育婴之家网版权所有 | 服务QQ：2496664615
	</DIV>
</DIV>

<!-- 访问日志 -->
<!-- <script type="text/javascript">$.ajax({  url:"/front/index/visitLog.do",  type:"post", data:{"refer":document.referrer,"thisPage":location.href },  dataType:"text", async: true});</script> -->

<!-- 引入cnzz统计 -->
<%@ include file="/cs.jsp" %>
<%CS cs = new CS(1254920361);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />