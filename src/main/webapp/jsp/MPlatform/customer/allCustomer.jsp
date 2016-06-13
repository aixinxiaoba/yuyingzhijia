<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="/commons/js/0_core.js"></script>

<script type="text/javascript">
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
function manageCustomer(lCustomerID, nType, lProjectID)
{
	var nCount = 0;
	var strURL;
	
	if (nType == 1) // 删除客户
	{
		strURL = "/mpf/customer/customerDelete.do?flag=1";
	}
	else if (nType == 2) // 项目授权。
	{
		strURL = "/mpf/customer/projectAuthorization.do?flag=1";
	}
	else if (nType == 3) // 取消授权。
	{
		strURL = "/mpf/customer/cancleProjectAuthorization.do?flag=1";
	}
	else
	{
		alert("无效的类型！请刷新重试！");
		return;
	}
	
	if (lCustomerID > 0)
	{
		strURL += "&customerID=" + lCustomerID;
	}
	else
	{
		$(":checked.class_customer").each(function(i, item){
			strURL += "&customerID=" + item.value;
		});
	}
	
	
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
	
	
	if (!confirm("确认要" + (nType == 1 ? "删除": nType == 2 ? "授权" : "取消授权") + "吗？"))
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
				if (nType == 1)
				{
					alert("删除成功");
				}
				else if (nType == 3)
				{
					alert("取消授权成功");
					$("#projectList").datagrid('reload'); // 重新加载。
				}
				else
				{
					alert("授权成功");
					$("#show_customer").slideDown();
					$("#show_project").slideUp();
				}
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
 * 删除客户。
 */
function customerDelete(lCustomerID)
{
	manageCustomer(lCustomerID, 1, -1);
}

/**
 * 项目授权。
 */
function projectAuthorization(lCustomerID,lProjectID)
{
	manageCustomer(lCustomerID, 2, lProjectID);
}

/**
 * 取消授权。
 */
function cancleProjectAuthorization(lCustomerID,lProjectID)
{
	manageCustomer(lCustomerID, 3, lProjectID);
}

/**
 * 邮件发送。
 */
function sendEmail()
{
	var nCount = 0;
	var strURL = "/jsp/MPlatform/customer/email_edit.jsp";
	var strCustomerID = "";
	var strCustomerQQ = "";
	
	$(":checked.class_customer").each(function(i, item){
		if (strCustomerQQ == "")
		{
			strCustomerQQ += $(".c_qq_" + item.value).val();
		}
		else
		{
			strCustomerQQ += ";" + $(".c_qq_" + item.value).val();
		}
		nCount++;
	});
	if (nCount == 0)
	{
		alert("请选择您要发送的用户！");
		return;
	}
	strURL += "?customerQQ=" + strCustomerQQ + "&lProjectID=${lProjectID}";
	location.href = strURL;
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
 
// easy UI start
$(function(){
	$('#customerList').datagrid({
		title: "客户列表",
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[15,20,30,40,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/customer/loadAllCustomerList.do',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strSname',title:"&nbsp;&nbsp;客户昵称",align:"left",width:fixWidth(0.1242),
				formatter:function(value,rec){
		    		var strHTML = "<input type='checkbox' class='class_customer' value='" + rec.lId + "'>&nbsp;&nbsp;";
		    		
					// return strHTML += "<a href='/mpf/customer/customerDetial.do?lCustomerID=" + rec.lId + "&lProjectID=${lProjectID}&nType=1' title='查看客户详细' class='normal'>" + rec.strSname + "</a>";
					// return strHTML + rec.strSname;return strHTML + rec.strSname;
					return strHTML + rec.strNickName;
				}
			}
					]],
		columns:[[
			{field:'strParentNickName',title:'客户负责人',width:fixWidth(0.1242),align:"center"},
			{field:'strCustomerTypeName',title:'客户类型',width:fixWidth(0.1242),align:"center"},
			{field:'strRegistTime',title:'注册时间',width:fixWidth(0.1242),align:"center"},
			{field:'strSPhone',title:'手机号码',width:fixWidth(0.1242),align:"center"},
			{field:'strQQ',title:'QQ号码',width:fixWidth(0.1242),align:"center",
				formatter:function(value, rec){
					var objInput = "<input type='hidden' value='" + rec.strQQ + "' class='c_qq_" + rec.lId + "' />";
					
					return objInput + rec.strQQ;
				}
			},
			{field:'strCurrentLevel',title:'用户等级',width:fixWidth(0.1242),align:"center"},
			{field:'strIsActive',title:'是否激活',width:fixWidth(0.1242),align:"center"},
			{field:'content',title:'操作',width:fixWidth(0.1),align:"center",
				formatter:function(value,rec){
					if (rec.nIsActive == 1)
					{
						return "<a href='javascript:activeOrNotCustomer(" + rec.lId + ", 0)' class='normal'>关闭</a>";
					}
					
					return "<a href='javascript:activeOrNotCustomer(" + rec.lId + ", 1)' class='normal'>激活</a>";
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
			text:'查看已授权项目',
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
		},
		{
			text:'项目授权',
			iconCls:'icon-edit',
			handler:function(){
				var row = $('#customerList').datagrid('getSelected');
				
				if ($(":checked.class_customer") != null && $(":checked.class_customer").length > 0)
				{
					// 项目授权。
					projectList(-1);
				}
				else if (row != null && row.lId > 0)
				{
					// 项目授权。
					projectList(row.lId);
				}
				else
				{
					$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要授权的用户！</p>", "info");
				}
			}
		},
		{
			text:'邮件发送',
			iconCls:'icon-edit',
			handler:function(){
				sendEmail();
			}
		},
		{
			text:'删除用户',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#customerList').datagrid('getSelected');
				
				var row = $('#customerList').datagrid('getSelected');
				
				if ($(":checked.class_customer") != null && $(":checked.class_customer").length > 0)
				{
					// 项目授权。
					customerDelete(-1);
				}
				else if (row != null && row.lId > 0)
				{
					// 项目授权。
					customerDelete(row.lId);
				}
				else
				{
					$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要删除的用户！</p>", "info");
				}
			}
		}
		]
	});
	
});

function getSelected(){
	var selected = $('#customerList').datagrid('getSelected');
	
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}

/**
 * 列出所有项目。
 */
function projectList(lCustomerID)
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
		url:'/mpf/customer/loadUsersProject.do',
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
		                	formatter:function(value,rec){
		                		var strHTML = "<input type='checkbox' class='class_project' value='" + rec.lId + "'>&nbsp;&nbsp;";
		                		
		                		return strHTML + rec.strPname;
		                }}
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
			text:'确认授权',
			iconCls:'icon-save',
			handler:function(){
				var row = $('#projectList').datagrid('getSelected');
				
				if ($(":checked.class_project") != null && $(":checked.class_project").length > 0)
				{
					// 项目授权。
					projectAuthorization(lCustomerID, -1);
				}
				else if (row != null && row.lId > 0)
				{
					// 项目授权。
					projectAuthorization(lCustomerID, row.lId);
				}
				else
				{
					$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要授权的项目！</p>", "info");
				}
			}
		},
		{
			text:'返回',
			iconCls:'icon-edit',
			handler:function(){
				$("#show_customer").slideDown();
				$("#show_project").slideUp();
			}
		}
		]
	});
}

/**
 * 列出所有项目。
 */
function authorizationProjectList(lCustomerID)
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
		url:'/mpf/customer/loadCustomerProject.do?lCustomerID=' + lCustomerID,
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
					cancleProjectAuthorization(lCustomerID, -1);
				}
				else if (row != null && row.lId > 0)
				{
					// 项目授权。
					cancleProjectAuthorization(lCustomerID, row.lId);
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
 * 项目全选。
 * 
 * @param
 */
function checkAllProject(objThis)
{
	if (objThis.checked == true)
	{
		$(".class_project").attr("checked","checked");
	}
	else
	{
		$(".class_project").removeAttr("checked");
	}
}
// easy UI end
</script>
</head>
<body>
<div style="margin-top:0px;margin-left:2px;">
	<div id="show_customer">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1"  style="margin-top:0px;">
		  <tr>
			<td height="21" background="/commons/image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">全部客户</span></td>
		  </tr>
		  <tr>
		    <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
		    	<ul style="margin-top:18px;margin-left:10px;list-style: none;">
		    		<li style="float:left;width: 65px;font-size:14px;margin-top:3px;">客户名称:</li>
		    		<li style="float:left;width: 130px;"><input type="text" name="textfield" class="inputTextStyle" value="${strCustomerName}" id="tempCustomerName"></li>
		    		<li style="float:left;width: 65px;font-size:14px;margin-top:3px;">客户QQ:</li>
		    		<li style="float:left;width: 165px;"><input type="text" name="textfield" class="inputTextStyle2" value="${strCustomerQQ}" id="tempCustomerQQ"></li>
		    		<li style="float:left;width: 105px;font-size:14px;margin-top:3px;">客户负责人QQ:</li>
		    		<li style="float:left;width: 165px;"><input type="text" name="textfield" class="inputTextStyle2" value="${strCustomerQQ}" id="tempParentCustomerQQ"></li>
		    		<li style="float:left;"><img src="/commons/image/s1.gif" width="59" height="22" onclick="searchByName();" style="cursor: pointer;"></li>
		    	</ul>
		    </td>
		        </tr>
		</table>	
		<div>
			<ul style="line-height: 30px;">
				<li><span style="font-size:14; font-weight:bold;">多项目管理用户登陆地址：&nbsp;<%=basePath %>mpf/customer/login.do</span>
				<input type="button" onclick="objCore.copyData('loginUrl');" value="复制"/>
				<input type="hidden" id="loginUrl" value="<%=basePath %>mpf/customer/login.do"/>
				</li>
				
			</ul>
		</div>
		<table id="customerList"></table>
	</div>
	
	<div id="show_project" style="display:none;">
		<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-size:12px;">多项目管理 > 项目授权</span></div>
		<div style="margin: 20px;">请选择您要授权的项目！</div>
		<table id="projectList"></table>
	</div>
</div>
</body>
</html>
