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
			<div style="margin-top:10px;font-size:17px;color:#ffab92;font-weight:bold;font-family:'Microsoft YaHei';letter-spacing:4px">
				欢迎来到<span style="color:#ffc12d;font-size:18px;">${objProject.strPname}</span>祝您在这里学习愉快！
			</div>
			<!-- 暂不使用其他菜单 -->
		</DIV>
		
		<div class="navi-right">
			<div id="needLogin" style="margin-top:4px;display:none;">
				<a href="https://api.weibo.com/oauth2/authorize?client_id=4148667325&redirect_uri=http://www.yuyingzhijia.cn&response_type=code" class="weibo-sina link-icon-style" title="使用新浪微博登陆"></a>
				<!-- 
				<a href="http://user.ci123.com/qq/zone.php?channel=1&back_url=http://ask.ci123.com" class="qq link-icon-style" title="使用QQ登陆"></a>
				 -->
				<a href="/front/customer/register.do?projectKey=${objProject.projectKey }" class="head-register re-lo-style black" style="color:#f35b8f;text-decoration:none;font-size:16px;letter-spacing:1px">注册</a>
				<a href="/jsp/templateOne/login.jsp" target="_self" class="head-login re-lo-style black" style="color:#f35b8f;text-decoration:none;font-size:16px;letter-spacing:1px">登录</a>
				
				<!-- 新浪微博 关注按钮-->
				<html xmlns:wb="http://open.weibo.com/wb">
				<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=4148667325" type="text/javascript" charset="utf-8"></script>
				<a href="javascript:void(0);" target="_self" class="head-login re-lo-style black" style="color:#f35b8f;text-decoration:none;font-size:16px;letter-spacing:1px"><wb:follow-button uid="2895295707" type="red_1" /></a>
			</div>
			<div id="allreadyLogin" style="display:none;margin-top:13px;">
				 <span style="float:left;">欢迎您，</span>
				 <a href="" id="nickName" style="margin-left:0px;"></a>
                 <a href="/login/destroyCustomerLogin.do">退出</a>
			</div>
		</div>
	</DIV>
</DIV>