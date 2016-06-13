<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门列表</title>

<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

<link type="text/css" href="/commons/css/index.css" rel="stylesheet" ></link>
<link type="text/css" href="/commons/css/common.css" rel="stylesheet" ></link>

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
//easy UI start
$(function(){
	$('#customerList').datagrid({
		title: "商户列表",
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[15,20,30,40,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/users/loadUsersList.do',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strName',title:"&nbsp;&nbsp;商户名称",align:"left",width:fixWidth(0.1242),
				formatter:function(value,rec)
				{
		    		var strHTML = "<input type='checkbox' class='class_customer' value='" + rec.lId + "'>&nbsp;&nbsp;";
		    		
					return strHTML += rec.strName;				
				}
			}
		    ]],
		columns:[[
			{field:'strLoginName',title:'QQ',width:fixWidth(0.1242),align:"center"},
			{field:'strPersonPhone',title:'手机号码',width:fixWidth(0.1242),align:"center"},
			{field:'strLastModifyTime',title:'申请时间',width:fixWidth(0.1242),align:"center"},
			{field:'strActiveStatu',title:'激活状态',width:fixWidth(0.1242),align:"center"},
			{field:'null',title:'操作',width:fixWidth(0.1242),align:"center",
				formatter:function(value,rec)
				{
					var strURL = "/jsp/MPlatform/users/projectList.jsp?lUsersID=" + rec.lId;
					if (rec.nIsActive == 1)
					{
						return "<a href='javascript:void(0)' onclick='manageCustomer(" + rec.lId + ",0);'>禁用</a> | <a href='" + strURL + "'>授权</a>";
					}
		    		else
		    		{
		    			return "<a href='javascript:void(0)' onclick='manageCustomer(" + rec.lId + ",1);'>激活</a> | <a href='" + strURL + "'>授权</a>";
		    		}
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[
		{
			text:'<input type="checkbox" name="checkbox" value="checkbox" onclick="checkAll(this);" style="margin-left:20px;">&nbsp;全选'
		},
		{
			text:'删除用户',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#customerList').datagrid('getSelected');
				
				deleteMer(row ? row.id : -1);
			}
		},
		{
			text:'查看已授权的项目',
			iconCls:'icon-edit',
			handler:function(){
				var row = $('#customerList').datagrid('getSelected');
				
				if ($(":checked.class_customer") != null && $(":checked.class_customer").length == 1)
				{
					// 项目授权。
					authorizationProjectList($(":checked.class_customer").val());
				}
				else if ($(":checked.class_customer") != null && $(":checked.class_customer").length > 1)
				{
					$.messager.alert('消息提示',"<p style='font-size:14px;'>您只能选择一个进行查看！</p>", "info");
				}
				else if (row != null && row.lId > 0)
				{
					// 项目授权。
					authorizationProjectList(row.lId);
				}
				else
				{
					$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要授权的用户！</p>", "info");
				}
			}
		}
		]
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
  * 删除商户。
  */
function deleteMer(id)
{
	var count = 0;
	var strURL = "/mpf/users/delUsers.do?flag=1";
	if (id > 0)
	{
		strURL += "&usersID=" + id;
	}
	else
	{
		$(":checked.class_customer").each(function(i, item){
			strURL += "&usersID=" + item.value;
			count++;
		});
		
		if (count = 0)
		{
			alert("请选择您要删除的商户");
			return;
		}
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
 
/**
 * 客户管理。
 */
function manageCustomer(lUsersID, type)
{
	var nCount = 0;
	var strURL;
	var msg = "";
	
	strURL = "/mpf/users/usersActive.do?lUsersID=" + lUsersID + "&nType=" + type;
	if (type == 1)
	{
		msg = "激活";
	}
	else
	{
		msg = "禁用";
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
				alert(msg + "成功！");
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

/**
 * 列出所有项目。
 */
function authorizationProjectList(lUserID)
{
	$("#show_customer").hide();
	$("#show_project").show();
	$('#projectList').datagrid({
		title:"项目列表",
		loadMsg:'加载数据...',
		// pageList:[10,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/users/loadUsersProject.do?lUserID=' + lUserID,
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		onLoadError:function (data)
		{
			alert("加载失败！请稍后重试！");
		},
		singleSelect:true,
		frozenColumns:[[
		                {field:'strPname',title:"项目名称",align:"left",width:500,
		                	formatter:function(value,rec)
		                	{
		                		var strHTML = "<input type='checkbox' class='class_project' value='" + rec.lId + "'>&nbsp;&nbsp;";
		                		
		                		return strHTML + rec.strPname;
		                	}
		                }
					]],
		columns:[[
			{field:'strCreateTime',title:'项目负责人',width:500,align:"center",
				formatter:function(value,rec){
					return "${objUsers.strName}";
				}
			}
		]],
		rownumbers:true,
		toolbar:[
		{
			text:'<input type="checkbox" name="checkbox" value="checkbox" onclick="checkAllProject(this);" style="margin-left:20px;">&nbsp;全选'
		},
		{
			text:'取消授权',
			iconCls:'icon-save',
			handler:function(){
				var row = $('#projectList').datagrid('getSelected');
				
				if ($(":checked.class_project") != null && $(":checked.class_project").length > 0)
				{
					// 项目授权。
					cancleProjectAuthorization(lUserID, -1);
				}
				else if (row != null && row.lId > 0)
				{
					// 项目授权。
					cancleProjectAuthorization(lUserID, row.lId);
				}
				else
				{
					$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要取消授权的项目！</p>", "info");
				}
			}
		},
		{
			text:'返回',
			iconCls:'icon-edit',
			handler:function(){
				$("#customerList").datagrid('reload'); // 重新加载。
				$("#show_customer").show();
				$("#show_project").hide();
			}
		}
		]
	});
}

/**
 * 取消授权。
 */
function cancleProjectAuthorization(lUserID,lProjectID)
{
	var nCount = 0;
	var strURL;
	
	strURL = "/mpf/users/cancleProjectAuthorization.do?usersID=" + lUserID;
	
	if (lProjectID > 0)
	{
		strURL += "&projectID=" + lProjectID;
	}
	else
	{
		$(":checked.class_project").each(function(i, item){
			strURL += "&projectID=" + item.value;
		});
	}
	
	if (!confirm("确认要取消授权吗？"))
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
				alert("取消成功！");
			}
			else
			{
				alert(responseText);
			}
        }
    });
	$("#show_customer").slideDown();
	$("#show_project").slideUp();
	$('#customerList').datagrid("reload");
}
// easy UI end
</script>
</head>
<body>
<div style="margin-top:0px;margin-left:2px;">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1"  style="margin-top:0px;">
	  <tr>
		<td height="21" background="/commons/image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">商户列表</span></td>
	  </tr>
	</table>
	
	<div style="margin: 20px;">
		<span style="font-size:20px;font-weight:bolder;">商户注册地址：</span>
		<span><a href="<%=basePath %>jsp/front/page_authorize.jsp"  style="font-weight:bolder;font-size:20px;border: 2px solid green;" title="进入" target="_blank"><%=basePath %>jsp/front/page_authorize.jsp</a></span>
	</div>
	<div id="show_customer">	
		<table id="customerList"></table>
	</div>
	<div id="show_project" style="display:none;">
		<table id="projectList"></table>
	</div>
</div>
</body>
</html>
