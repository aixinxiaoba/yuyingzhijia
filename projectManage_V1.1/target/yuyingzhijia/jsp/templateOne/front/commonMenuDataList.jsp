<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0034)http://www.yuyingzhijia.cn/index.asp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>${objProject.strPname}</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<META name=description
	content="${objProject.strPname}">
<META name=keywords content="网站自动化 项目自动化 全民建站">
<link rel="shortcut icon" href="/favicon.ico?version=3">
<link rel="icon" type="image/gif" href="animated_favicon1.gif">
<LINK rel="stylesheet" type="text/css" href="/commons/housekeeper/images/Css.css" />
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/index_css/index_123.css">
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/jquery.kinMaxShow-1.0.min1.js ">
	
<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui_user_defined.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>

<style type="">
</style>
<script type="text/javascript">
//easy UI start
$(function(){
	$('#customerList').datagrid({
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[15,20,30,40,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/news/loadNewsList.do?lProjectID=${lProjectID}&lProjectMenuID=${lProjectMenuID}',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strTitle',title:"&nbsp;&nbsp;<span style='font-size:13px;color:ff3300;font-weight:bold;font-family:宋体'>标题</span>",align:"left",width:fixWidth(0.6),
				formatter:function(value,rec)
				{
		    		var strURL = "/front/index/newsRead.do?newsID=" + rec.lId;
		    		strHTML = "<a style='color:006699;font-size:14px;font-weight: bold;font-family:宋体' href='" + strURL + "' target='_blank'>"+rec.strTitle+"</a>";
					return strHTML;
				}
			}
		    ]],
		columns:[[
			// {field:'strProjectMenuName',title:'新闻类型',width:fixWidth(0.1242),align:"center"},
			{field:'strSendDate',title:"&nbsp;&nbsp;<span style='font-size:13px;color:ff3300;font-weight:bold;font-family:宋体'>发布时间</span>",width:fixWidth(0.2),align:"center",
				formatter:function(value,rec)
				{
		    		strHTML = "<span style='color:black;font-size:12px;font-family:宋体'>" + rec.strSendDate + "</span>";
					return strHTML;
				}
			}
		]],
		rowStyler:function(index,row){  
            // return 'background-color:pink;';
     	},
		pagination:true,
		rownumbers:true
	});
});

function searchByName()
{
	$('#customerList').datagrid('options').queryParams = {
		strCustomerName:$("#tempCustomerName").val(),
		strCustomerQQ:$("#tempCustomerQQ").val(),
		strParentCustomerQQ:$("#tempParentCustomerQQ").val(),
		strCustomerTypeID:$("#tempCustomerType").val()
	};
   	$("#customerList").datagrid('reload'); // 重新加载。
}

/**
 * 客户管理。
 */
function manageCustomer(lCustomerID)
{
	var nCount = 0;
	var strURL;
	
	strURL = "/mpf/news/newsDel.do?flag=1";
	
	$(":checked.class_customer").each(function(i, item){
		strURL += "&newsIDs=" + item.value;
		nCount++;
	});
	
	/**
	 * 不适用多选使用单选的情况。
	 */
	if (nCount == 0 && lCustomerID > 0)
	{
		strURL += "&newsIDs=" + lCustomerID;
	}
	
	/**
	 * 没有选择的情况。
	 */
	if (nCount == 0 && lCustomerID <= 0)
	{
		alert("请选择您要操作的用户！");
		return;
	}
	
	if (!confirm("确认要删除吗？"))
	{
		return;
	}
	
	$.ajax(
	{
        url:strURL,
        type:"post",
        data:{},
        dataType:"text",
        async: false,
        success:function(responseText)
        {	           
			if(responseText == "success")
			{
				alert("删除成功！");
			}
			else
			{
				alert(responseText);
			}
        }
    });
	$('#customerList').datagrid("reload");
}

function checkAll(objThis)
{
	if (objThis.checked == true)
	{
		$(".class_customer").attr("checked","checked");
	}
	else
	{
		$(".class_customer").removeAttr("checked");
	}
}

/**
 * 
 * @param {Object} lCustomerID
 * @param {Object} nType 激活为1
 */
function activeOrNotCustomer(lCustomerID, nType)
{
	var strURL = "/mpf/customer/customerActive.do?nType=" + nType;
	
	if (lCustomerID > 0)
	{
		strURL += "&lCustomerID=" + lCustomerID;
	}
	else
	{
		$(":checked.class_customer").each(function(i, item){
			strURL += "&lCustomerID=" + item.value;
		});
	}
	$.ajax({
           url:strURL,
           type:"post",
           data:{},
           dataType:"text",
           async: false,
           success:function(responseText)
           {	           
			if(responseText == "success")
			{
				alert("操作成功！");
				$("#customerList").datagrid('reload'); // 重新加载。
			}else
			{
				alert(responseText);
			}
           }
        });
}

function fixWidth(percent)
{
    return (document.body.clientWidth ) * percent; //这里你可以自己做调整
}
 


function getSelected(){
	var selected = $('#customerList').datagrid('getSelected');
	
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}
// easy UI end
</script>
</HEAD>
<BODY>
	<%@ include file="../../common/head.jsp" %>
	<TABLE style="MARGIN-TOP: 23px;" cellSpacing=0 cellPadding=0 width=992
			align=center border=0>
			<TBODY>
				<TR>
					<TD vAlign=top>
						<TABLE cellSpacing=0 cellPadding=0  bgColor=#ffffff
							border=0>
							<TBODY>
								<TR>
									<TD style="BACKGROUND: url(/commons/housekeeper/images/d_top.jpg);MARGIN-TOP: 14px;padding-left: 40px;width:992px;" height=37>
										<span style='font-size:15px;color:ff6600;font-weight: bold'  id="menu_key">${objProjectMenu.strMenuName}</span>
									</TD>
								</TR>
								<TR>
									<TD >
										<table id="customerList"></table>
									</TD>
								</TR>
								<TR>
									<TD height=5 ></TD>
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
					<TD height=80>
						<TABLE cellSpacing=0 cellPadding=0 width=992 border=0>
							<TBODY>
								<TR>
									<TD>
										<P>
											Copyright
											<A href="javascript:void(0);"><FONT color=#800080>www.${objProject.projectKey}.cn</FONT>
											</A>&nbsp;All Rights Reserved 版权所有
										</P>
									</TD>
									<TD width=290 align=right>
										${objProject.strPname}
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
</BODY>
</HTML>

