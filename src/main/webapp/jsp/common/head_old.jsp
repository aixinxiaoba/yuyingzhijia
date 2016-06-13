<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<%
String strRefer = request.getHeader("Referer");
String strThisPage = request.getRequestURL().toString();
%>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?6f411ee2d6276cabab47bd3c12794874";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>




<script type="text/javascript">

$(document).ready(function() {
	recordVisitLog();
})

// 设置访问日志。
function recordVisitLog()
{
	var strURL = "/front/index/visitLog.do";
	
	$.ajax(
	{
        url:strURL,
        type:"post",
        data:{"refer":"<%=strRefer%>","thisPage":"<%=strThisPage%>"},
        dataType:"text",
        async: false,
        success:function(responseText)
        {	           
			
        }
    });
}

function loadMenu()
{
	$.ajax({
           url:"/mpf/projectMenu/projectMenuList.do?lProjectID=${lProjectID}&validate=1",
           type:"post",
           data:{},
           dataType:"json",
           async: false,
           success:function(data)
           {
        	   var strHTML = "";
        	   if (data != null && data.rows.length > 0)
        	   {
        		   var td_html="";
        		   var td_index = "";
        		   for (var i = 0; i < data.rows.length; i++ )
        		   {
        			   var strMenuName = data.rows[i].strMenuName;
        			   var menuKey = data.rows[i].menuKey;
        			   var lId = data.rows[i].lId;
        			   
        			   if (menuKey == "index")
        			   {
        				   td_index = "<TD align=center><A style='COLOR: #ffffff' href='/'>"+strMenuName+"</A></TD>";
        			   }
        			   else if (menuKey == "yuyingku")
        			   {
        				    td_html += "<TD align=center><A style='COLOR: #ffffff' href='/jsp/templateOne/front/customerList.jsp?projectKey=${param.projectKey}&projectMenuKey="+menuKey+"'>"+strMenuName+"</A></TD>";
        			   }
        			   else
        			   {
	        			   td_html += "<TD align=center><A style='COLOR: #ffffff' href='/menuShow.do?lProjectID=${lProjectID}&lProjectMenuID="+lId+"'>"+strMenuName+"</A></TD>";
        			   }
        		   }
        		   $("#td_menu").html(td_index + td_html);
        	   }
           }
    });
}
</script>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 >
		<TBODY>
			<TR>
				<TD
					style="BACKGROUND: url(/commons/housekeeper/images/top_bg.jpg)"
					height=29>
					<TABLE cellSpacing=0 cellPadding=0 width=992 align=center border=0>
						<TBODY>
							<TR>
								<TD height=29 rowspan="1" colspan="1"  class="font1">&nbsp;欢迎来到${objProject.strPname}！</TD>
								<!-- 
								<TD align=right class="font-style">
									<c:if test="${sessionScope.CUSTOMER_ID > 0}">
			                        	<span style="font:12px/150% Arial, Verdana, '宋体';">您好，${sessionScope.objCustomer.strNickName }
			                        		<a href="/login/destroyCustomerLogin.do?projectKey=${objProject.projectKey }" class="font1">&nbsp;&nbsp;[退出]</a>
			                        	</span>
			                        </c:if>
			                         <c:if test="${sessionScope.CUSTOMER_ID == null || sessionScope.CUSTOMER_ID <= 0}">
				                        <a href="/mpf/customer/login.do" class="font1" style="color:blue;" target="_blank"> 登录</a>
				                        &nbsp;|&nbsp;<a href="/front/customer/register.do?projectKey=${objProject.projectKey }" class="font1" style="color:blue;">注册</a>
			                        </c:if>	
			                     </td>
								 -->
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TBODY>
			<TR>
				<TD
					style="BACKGROUND: url(/commons/housekeeper/images//logobg.jpg)"
					height=29>
					<TABLE cellSpacing=0 cellPadding=0 width=992 align=center border=0>
						<TBODY>
							<TR>
								<TD height=70>
<%-- 									<span style="font-size:20px;color:orange;">${objProject.strPname}欢迎您</span> --%>
									<IMG src="/commons/housekeeper/images/logo.gif">
								</TD>
								<TD vAlign=top width=261>
									<FORM method=post name=form1 action="/front/index/search.do">
										<TABLE style="MARGIN-TOP: 31px" cellSpacing=0 cellPadding=0
											width=252 border=0>
											<TBODY>
												<TR>
													<TD height=31
														background=/commons/housekeeper/images/sear_l.jpg
														width=8></TD>
													<TD
														background=/commons/housekeeper/images/sear_c.jpg
														width=181><INPUT
														style="BORDER-TOP-STYLE: none; FONT-SIZE: 14px; HEIGHT: 28px; WIDTH: 181px; BACKGROUND: none transparent scroll repeat 0% 0%; BORDER-BOTTOM-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; LINE-HEIGHT: 28px"
														name=searchParam value="">
														<input type="hidden" value="${objProject.lId}" name="lProjectID"/>
														<input type="hidden" value="${objProject.projectKey}" name="projectKey"/>
														</TD>
													<TD
														background=/commons/housekeeper/images/sear_r.jpg
														width=63><INPUT
														src="/commons/housekeeper/images/sear_r.jpg"
														type=image></TD>
												</TR>
											</TBODY>
										</TABLE>
									</FORM>
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
				<TD height=36
					background=/commons/housekeeper/images/navbg.jpg>
					<TABLE class=nav height=36 cellSpacing=0 cellPadding=0 width=992
						align=center border=0>
						<TBODY>
							<TR id="td_menu">
								<TD align=center><A style='COLOR: #ffffff' href='/'>首页</A></TD>
								<TD align=center><A style='COLOR: #ffffff' href='/front/yuyingshi/index.do?lProjectMenuID=22' target="_blank">育婴师专区</A></TD>
								<s:iterator value="lstProjectMenu" id="projectMenu" >
									<TD align=center><A style='COLOR: #ffffff' href='/front/index/menuShow.do?lProjectMenuID=<s:property value="lId"/>'><s:property value="strMenuName"/></A></TD>
									<!-- 
									<TD align=center><A style='COLOR: #ffffff' target="_blank" href='/front/<s:property value="menuKey"/>/index.do?lProjectMenuID=<s:property value="lId"/>'><s:property value="strMenuName"/></A></TD>
									 -->	
								</s:iterator>
								
								<s:if test="objProject.projectKey == 'yuyingzhijia'">
									
									<TD align=center><A style='COLOR: #ffffff' href='/front/index/aboutUs.do?projectKey=${objProject.projectKey}'>关于我们</A></TD>
								</s:if>
								<!-- 
								<TD align=center><A style='COLOR: #ffffff' href='/front/index/aboutUs.do?projectKey=${objProject.projectKey}'>关于我们</A></TD>
								 -->
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
