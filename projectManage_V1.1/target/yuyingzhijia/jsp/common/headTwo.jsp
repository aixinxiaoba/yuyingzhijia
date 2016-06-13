<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
$(function(){
	// loadMenu();
})
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
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TBODY>
			<TR>
				<TD
					style="BACKGROUND: url(/commons/housekeeper/images/top_bg.jpg)"
					height=29>
					<TABLE cellSpacing=0 cellPadding=0 width=992 align=center border=0>
						<TBODY>
							<TR>
								<TD height=29 rowspan="1" colspan="1" class="font-style">&nbsp;欢迎来到${objProject.strPname}！</TD>
								<TD align=right class="font-style">
									 <A style="CURSOR: hand;padding:10px;font: 17px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;list-style:none;line-height:2em"
									  href="/mpf/customer/login.do" target="_blank">已有账号立即登陆</A></TD>
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
				<TD >
					<TABLE cellSpacing=0 cellPadding=0 width=992 align=center border=0>
						<TBODY>
							<TR>
								<TD>
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
