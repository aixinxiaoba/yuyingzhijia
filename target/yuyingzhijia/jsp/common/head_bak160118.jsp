<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 新浪微博 关注按钮-->
<html xmlns:wb="http://open.weibo.com/wb">
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=4148667325" type="text/javascript" charset="utf-8"></script>


<script type="text/javascript">

$(function(){
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
				$("#allreadyLogin").show(500);
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

<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 >
		<TBODY>
			<TR>
				<TD
					style="BACKGROUND: url(/commons/housekeeper/images/top_bg.jpg)" height=29>
					<TABLE cellSpacing=0 cellPadding=0 width=1100 border=0 style="margin:auto">
						<TBODY>
							<TR>
								<TD height=29 rowspan="1" colspan="1" class="font1">
									欢迎来到${objProject.strPname}！祝您在这里学习愉快！
								</TD>
								<TD align="right" class="r_nologin" style="border:0px solid red;" >
									<div id="needLogin" style="margin-top:4px;display:none;">
										<a href="/jsp/templateOne/login.jsp">登&nbsp;陆</a>
										<a href="/front/customer/register.do?projectKey=${objProject.projectKey }">注&nbsp;册</a>
										<A style="border:0px solid red;margin-left:20px;" title=新浪微博账号登录 class=nolg_sina href="https://api.weibo.com/oauth2/authorize?client_id=4148667325&redirect_uri=http://yuyingzhijia.cn/login/sinaLogin.do&response_type=code" target=_blank></A>
									</div>
									<div id="allreadyLogin" style="display:none;">
										 <span style="float:left;margin-top:7px;">欢迎您，</span>
										 <a href="" id="nickName" style="margin-left:0px;"></a>
				                    	 <a href="/login/destroyCustomerLogin.do">退出</a>
									</div>
			                     </td>
								<TD align=right style="border:0px solid yellow;width:78px;">
									<div style="width:62px;height:24px;">
										<wb:follow-button uid="2895295707" type="red_1" />
									</div>
			                     </td>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
