<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
function manageCustomer(lCustomerID, nType)
{
	var nCount = 0;
	var strURL;
	
	if (nType == 1) // 删除客户
	{
		strURL = "/mpf/customer/customerDelete.do?flag=1";
	}
	else if (nType == 2) // 升级客户。
	{
		strURL = "/mpf/customer/promotePrivilege.do?lProjectID=${lProjectID}";	
	}
	else
	{
		alert("无效的类型！请刷新重试！");
		return;
	}
	
	$(":checked.class_customer").each(function(i, item){
		strURL += "&customerID=" + item.value;
		nCount++;
	});
	
	/**
	 * 不适用多选使用单选的情况。
	 */
	if (nCount == 0 && lCustomerID > 0)
	{
		strURL += "&customerID=" + lCustomerID;
	}
	
	/**
	 * 没有选择的情况。
	 */
	if (nCount == 0 && lCustomerID <= 0)
	{
		alert("请选择您要操作的用户！");
		return;
	}
	
	if (!confirm("确认要" + (nType == 1 ? "删除":"升级为项目管理员") + "吗？"))
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
				alert((nType == 1 ? "删除":"升级为项目管理员") + "成功！");
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
	manageCustomer(lCustomerID, 1);
}

/**
 * 客户升级。
 * 
 */
function customerPromotePrivilege(lCustomerID)
{
	manageCustomer(lCustomerID, 2);
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
		url:'/mpf/customer/loadCustomers.do?lProjectID=${objProject.lId}',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strSname',title:"&nbsp;&nbsp;客户名称",align:"left",width:fixWidth(0.1242),
				formatter:function(value,rec){
		    		var strHTML = "<input type='checkbox' class='class_customer' value='" + rec.lId + "'>&nbsp;&nbsp;";
		    		
					return strHTML += "<a href='/mpf/customer/customerDetial.do?lCustomerID=" + rec.lId + "&lProjectID=${lProjectID}&nType=1' title='查看客户详细' class='normal'>" + rec.strSname + "</a>";				
				}
			}
					]],
		columns:[[
			{field:'strParentCustomerQQ',title:'客户负责人',width:fixWidth(0.1442),align:"center",
				formatter:function(value, rec)
				{
					if (rec.strParentCustomerQQ == "暂无")
					{
						return rec.strParentCustomerQQ;
					}
					var strHTML = "<a style='text-decoration: none;' href='tencent://message/?uin="+ rec.strParentCustomerQQ +"&amp;Site=在线咨询&amp;Menu=yes' target='_blank'>";
					
					strHTML += "<img style='border:0px;vertical-align: middle;' src=http://wpa.qq.com/pa?p=1:" + rec.strParentCustomerQQ + ":4></a>&nbsp;" + rec.strParentCustomerQQ;
					return rec.strParentNickName;
				}
			},
			{field:'strCustomerTypeName',title:'客户类型',width:fixWidth(0.1242),align:"center"},
			{field:'strRegistTime',title:'注册时间',width:fixWidth(0.1242),align:"center"},
			{field:'strSPhone',title:'手机号码',width:fixWidth(0.1242),align:"center"},
			{field:'strQQ',title:'QQ号码',width:fixWidth(0.1542),align:"center",
				formatter:function(value, rec){
					var objInput = "<input type='hidden' value='" + rec.strQQ + "' class='c_qq_" + rec.lId + "' />";
					var strHTML = "<a style='text-decoration: none;' href='tencent://message/?uin="+ rec.strQQ +"&amp;Site=在线咨询&amp;Menu=yes' target='_blank'>";
					
					strHTML += "<img style='border:0px;vertical-align: middle;' src=http://wpa.qq.com/pa?p=1:" + rec.strQQ + ":4></a>&nbsp;" + rec.strQQ;
					return objInput + strHTML;
				}
			},
			{field:'strCurrentLevel',title:'用户等级',width:fixWidth(0.1242),align:"center"},
			{field:'strIsActive',title:'是否激活',width:fixWidth(0.1242),align:"center"},
			{field:'content',title:'操作',width:fixWidth(0.1),align:"center",
				formatter:function(value,rec){
					// var strHTML = "<a href='/mpf/customer/customerDetial.do?lCustomerID=" + rec.lId + "&nType=2' class='normal'>修改</a>&nbsp";
					if (rec.nIsActive == 1)
					{
						return "<a href='javascript:activeOrNotCustomer(" + rec.lId + ", 0)' class='normal'>关闭</a>";
					}
					
					return "<a href='javascript:activeOrNotCustomer(" + rec.lId + ", 1)' class='normal'>激活</a>";
					// return "<a href='/mpf/customer/customerList.do?lProjectID=" + rec.lId + "' class='normal'>查看用户</a>";
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
				
				customerDelete((row ? row.lId : -1));
			}
		},
		{
			text:'修改用户',
			iconCls:'icon-edit',
			handler:function(){
				var row = $('#customerList').datagrid('getSelected');
				
				if (row){
					var lCustomerID = row.lId;
					
					location.href = "/mpf/customer/customerDetial.do?lCustomerID=" + lCustomerID + "&lProjectID=${lProjectID}&nType=2";
				}
				else
				{
					alert("请选择您要修改的用户");
				}
			}
		},
		{
			text:'邮件发送',
			iconCls:'icon-edit',
			handler:function(){
				sendEmail();
			}
		}
		,
		{
			text:'密码重置',
			iconCls:'icon-edit',
			handler:function(){
				var row = $('#customerList').datagrid('getSelected');
				
				if (!row || row.lId <= 0)
				{
					alert("请选择用户！");
					return;
				}
				
				if (!confirm("确认要还原" + row.strQQ + "的密码吗？"))
				{
					return;
				}
				resetPassword(row.lId);
			}
		} ,
		{
			text:'升级为项目管理员',
			iconCls:'icon-edit',
			handler:function(){
				var row = $('#customerList').datagrid('getSelected');
				
				customerPromotePrivilege((row ? row.lId : -1));
			}
		},
		{
			text:'导出当前页到excel',
			iconCls:'icon-edit',
			handler:function(){
				// var row = $('#customerList').datagrid('getSelected');
				
				// customerPromotePrivilege((row ? row.lId : -1));
				// alert("正在建设中...");
				var row = $('#customerList').datagrid('options');
				
				location.href = "/mpf/customer/batchExportExcel.do?lProjectID=${lProjectID}&rows=" + row.pageSize + "&page=" + row.pageNumber;
			}
		}
		]
	});
	
});

/*
 * <s:if test="objUsers.nCurrentLevel == 1">
		,
		{
			text:'升级为项目管理员',
			iconCls:'icon-edit',
			handler:function(){
				var row = $('#customerList').datagrid('getSelected');
				
				customerPromotePrivilege((row ? row.lId : -1));
			}
		}
		</s:if>*/
function getSelected(){
	var selected = $('#customerList').datagrid('getSelected');
	
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}

function resetPassword(customerID)
{
	var strURL =  "/mpf/customer/resetPwd.do?customerID=" + customerID;
	
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
				// $("#customerList").datagrid('reload'); // 重新加载。
			}else
			{
				alert(responseText);
			}
           }
        });
}
// easy UI end
</script>
</head>
<body>
<div style="margin-top:0px;margin-left:2px;">
	<form name="formSearch" id="formSearch" method="post" action="/mpf/customer/customerSearch.do">
		<input type="hidden" name="lProjectID" id="lProjectID" value="${lProjectID}">
		<input type="hidden" name="strCustomerName" id="customerName">
		<input type="hidden" name="strCustomerQQ" id="customerQQ">
		<input type="hidden" name="strCustomerType" id="customerType">
	</form>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1"  style="margin-top:0px;">
	  <tr>
		<td height="21" background="/commons/image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">客户管理 》 客户列表</span></td>
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
	    		<li style="float:left;width: 65px;font-size:14px;margin-top:3px;">客户类型:</li>
	    		<li style="float:left;width: 130px;">
	    			<select id="tempCustomerType" class="inputTextStyle">
                           	<option value="-1">请选择</option>
                           	<s:if test="lstCustomerType != null && lstCustomerType.size > 0">
								<s:iterator value="lstCustomerType" id="objCustomerType">
									<option <s:if test="strCustomerType == strName">selected='selected'</s:if> value="${objCustomerType.lId}">${objCustomerType.strName}</option>   
								</s:iterator>	
                           	</s:if>
                     </select>
	    		</li>
	    		<li style="float:left;"><img src="/commons/image/s1.gif" width="59" height="22" onclick="searchByName();" style="cursor: pointer;"></li>
	    	</ul>
	    </td>
	        </tr>
	</table>	
	<div>
		<ul style="line-height: 30px;">
			<!-- <li><img src="/commons/image/t1.gif" align="absmiddle"> <span style="font-size:14; font-weight:bold;">客户列表</span></li> -->
			<li><span style="font-size:14; font-weight:bold;">用户登陆地址：&nbsp;<%=basePath %>mpf/customer/login.do</span></li>
			<!-- 
			<li>
				<img src="/commons/image/s3.gif" width="62" height="22" onclick="location.href='/mpf/project/projectDetial.do'" style="cursor: pointer;">
				<img src="/commons/image/s7.gif" width="59" height="22" onclick="customerDelete();" style="cursor: pointer;">
			</li>
			 -->
		</ul>
	</div>
	<table id="customerList"></table>
</div>
</body>
</html>
