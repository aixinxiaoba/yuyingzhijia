<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">

$(function(){
	// load login user.
	loadCustomer();
});

function loadCustomer()
{
	$.ajax({
		url : "/login/loadCustomer.do",
		type : "post",
		dataType : "json",
		async : true,
		success:function(data){
			if (data != null && data.lId != null && data.lId > 0)
			{
				$("#needLogin").hide();
				$("#allreadyLogin").show();
				$("#nickName").html(data.strNickName);
			}
			else
			{
				$("#needLogin").show();
			}
		}
	});
}
	
</script>
<DIV class="navi-head" id="navi-head" name="navi-head">
	<DIV class="navi-con-block">
		<DIV class="navi-left">
			<A class="page-title-pic" id="page-title"
				href="/"></A>
			<A class="first-page" id="first-self" href="/">首页</A>
			
			<!-- 
			<DIV class="out-block clearfix">
				<DIV class="discovery nav-item" id="discovery-self" href="#">
					<LABEL>发现</LABEL>

					<DIV class="navi-pic navi-pic-on4"></DIV>
					<SPAN class="discovery-block" id="discovery"
						style="display: none;"><I class="underline"></I>
						<UL>
							<LI class="block-con clearfix">
								<DIV class="line-title">
									<A href="http://info.ci123.com/brand/list/index.php">用品</A>
								</DIV>
								<DIV class="line-con">
									<A class="black"
										href="http://info.ci123.com/brand/list/index.php">用品库</A> <A
										class="black" href="http://shiyong.ci123.com/">免费试用</A> <A
										class="black" href="http://info.ci123.com/pingce/">用品评测</A>
								</DIV>
							</LI>
							<LI class="block-con clearfix">
								<DIV class="line-title">
									<A href="http://rs.ci123.com/">资源</A>
								</DIV>
								<DIV class="line-con">
									<A class="black" href="http://rs.ci123.com/categories/show/7">胎教音乐</A>
									<A class="black" href="http://rs.ci123.com/video/section.html">孕育视频</A>
									<A class="black"
										href="http://rs.ci123.com/software/section.html">启蒙教育</A> <A
										class="black" href="http://rs.ci123.com/widget/section.html">育儿工具</A>
									<A class="black" href="http://rs.ci123.com/apple/section.html">手机应用</A>
								</DIV>
							</LI>
							<LI class="block-con clearfix">
								<DIV class="line-title">
									<A href="http://good.ci123.com/tudou/happy.php">其他</A>
								</DIV>
								<DIV class="line-con">
									<A class="black" href="http://good.ci123.com/tudou/happy.php">活动中心</A>
									<A class="black" href="http://good.ci123.com/tudou/">土豆频道</A>
									<A class="black" href="http://shiyong.ci123.com/leyun/">乐孕大礼包</A>
								</DIV>
							</LI>
						</UL></SPAN>
				</DIV>
			</DIV>
			 -->
			<!-- 暂不使用其他菜单 -->
		</DIV>
		
		<div class="navi-right">
			<div id="needLogin" style="margin-top:4px;display:none;">
				<a href="https://api.weibo.com/oauth2/authorize?client_id=4148667325&redirect_uri=http://www.yuyingzhijia.cn/sinaLogin.do&response_type=code" class="weibo-sina link-icon-style" title="使用新浪微博登陆"></a>
				<!-- 
				<a href="http://user.ci123.com/qq/zone.php?channel=1&back_url=http://ask.ci123.com" class="qq link-icon-style" title="使用QQ登陆"></a>
				 -->
				<a href="/front/customer/register.do?projectKey=${objProject.projectKey }" class="head-register re-lo-style black">注册</a><a href="/jsp/templateOne/login.jsp" target="_self" class="head-login re-lo-style black">登录</a>
			</div>
			<div id="allreadyLogin" style="display:none;margin-top:13px;">
				 <span style="float:left;">欢迎您，</span>
				 <a href="" id="nickName" style="margin-left:0px;"></a>
                 <a href="/login/destroyCustomerLogin.do">退出</a>
			</div>
			
		</div>
	</DIV>
</DIV>
<SCRIPT>
    $(".out-block").hover(function(){$(this).addClass("hover-div")},function(){$(this).removeClass("hover-div")});$(".nav-item").hover(function(){$(this).addClass("hover");$(this).find("span").show()},function(){$(this).removeClass("hover");$(this).find("span").hide()});$(".user-head").hover(function(){$(this).addClass("hover-r");$(this).find("span").show()},function(){$(this).removeClass("hover-r");$(this).find("span").hide()});$(".user-item").hover(function(){$(this).addClass("hover-u");$(this).find("span").show()},function(){$(this).removeClass("hover-u");$(this).find("span").hide()});$(".hold_more").hover(function(){$(".main_more").addClass("hover_more");$(".main_more").find("i").addClass("hover_more");$(".more_block").show()},function(){$(".main_more").removeClass("hover_more");$(".main_more").find("i").removeClass("hover_more");$(".more_block").hide()});$(".mail-remind").hover(function(){$(this).addClass("mail-hover");$(".mail-box").show()},function(){$(this).removeClass("mail-hover");$(".mail-box").hide()});
</SCRIPT>