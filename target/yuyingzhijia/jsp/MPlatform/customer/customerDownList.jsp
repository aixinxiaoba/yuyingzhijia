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

<link type="text/css" href="/commons/css/common.css" rel="stylesheet" ></link>

<link type="text/css" href="/commons/css/index.css" rel="stylesheet" ></link>
<link type="text/css" href="/commons/css/general.css" rel="stylesheet"></link>

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/commons/js/0_core.js"></script>

<script type="text/javascript">
function searchByName()
{
	$('#customerList').datagrid('options').queryParams = {
		strCustomerName:$("#tempCustomerName").val(),
		strCustomerQQ:$("#tempCustomerQQ").val(),
		strCustomerTypeID:$("#tempCustomerType").val()
	};
   	$("#customerList").datagrid('reload'); // 重新加载。
}

/**
 * 删除客户。
 */
function customerDelete()
{
	var strURL = "/mpf/customer/customerDelete.do?flag=1";
	
	$(":checked.class_customer").each(function(i, item){
		strURL += "&lCustomerID=" + item.value;
	});
	
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
				alert("删除成功！");
				location.reload();
			}else
			{
				alert(responseText);
			}
           }
        });
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
				location.reload();
			}else
			{
				alert(responseText);
			}
           }
        });
}
 

// easy UI start
function fixWidth(percent)
{
    return (document.body.clientWidth ) * percent; //这里你可以自己做调整
}

$(function(){
	$('#customerList').datagrid({
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[15,20,30,40,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/customer/loadDwonCustomers.do?lProjectID=${lProjectID}',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strSname',title:"&nbsp;&nbsp;客户名称",align:"left",width:fixWidth(0.1242),align:"center",
				formatter:function(value,rec){
		    		// var strHTML = "<input type='checkbox' class='class_customer' value='" + rec.lId + "'>&nbsp;&nbsp;";
					return "<a href='/mpf/customer/customerDetial.do?lCustomerID=" + rec.lId + "&nType=1' title='查看客户详细' class='normal'>" + rec.strSname + "</a>";				
				}
			}
					]],
		columns:[[
			{field:'strParentCustomerQQ',title:'客户负责人QQ',width:fixWidth(0.1242),align:"center"},
			{field:'strCustomerTypeName',title:'客户类型',width:fixWidth(0.1242),align:"center"},
			{field:'strRegistTime',title:'注册时间',width:fixWidth(0.1242),align:"center"},
			{field:'strSPhone',title:'手机号码',width:fixWidth(0.1242),align:"center"},
			{field:'strQQ',title:'QQ号码',width:fixWidth(0.1242),align:"center"},
			{field:'strIsActive',title:'是否激活',width:fixWidth(0.1242),align:"center"},
			{field:'content',title:'操作',width:fixWidth(0.1),align:"center",
				formatter:function(value,rec){
					
					return "<a href='/mpf/customer/customerDetial.do?lCustomerID=" + rec.lId + "&nType=1&lProjectID=${lProjectID}' title='查看客户详细' class='normal'>查看</a>";
					// return "<a href='/mpf/customer/customerList.do?lProjectID=" + rec.lId + "' class='normal'>查看用户</a>";
				}
			}
		]],
		pagination:true,
		rownumbers:true
	});
});

function showApplication()
{
	$("#application").slideDown();
}
</script>
</head>
<body>
<div style="margin-left:2px;">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1" style="margin-top:0px;">
	  <tr>
		<td height="21" background="/commons/image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">客户搜索</span></td>
	  </tr>
	  <tr>
	    <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
	    	<ul style="margin-top:18px;margin-left:10px;list-style: none;">
	    		<li style="float:left;width: 65px;font-size:14px;margin-top:3px;">客户名称:</li>
	    		<li style="float:left;width: 130px;"><input type="text" name="textfield" class="inputTextStyle" value="${strCustomerName}" id="tempCustomerName"></li>
	    		<li style="float:left;width: 30px;font-size:14px;margin-top:3px;">QQ:</li>
	    		<li style="float:left;width: 165px;"><input type="text" name="textfield" class="inputTextStyle2" value="${strCustomerQQ}" id="tempCustomerQQ"></li>
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
			<li><img src="/commons/image/t1.gif" align="absmiddle"> <span style="font-size:14; font-weight:bold;">客户列表</span></li>
			<li> 
				<span style="font-size:14; font-weight:bold;">
					我的推广地址：
				</span>
			</li>
			<li>
				<%=basePath %>front/customer/register.do?lCustomerID=${objCustomer.lId}&lProjectID=${lProjectID}
			</li>
			<li>
				<a href="javascript:objCore.copyData('loginUrl');">复制链接</a>
				<input type="hidden" id="loginUrl" value="<%=basePath %>front/customer/register.do?lCustomerID=${objCustomer.lId}&lProjectID=${lProjectID}"/>
				&nbsp;|&nbsp;<a href="/front/customer/register.do?lCustomerID=${objCustomer.lId}&lProjectID=${lProjectID}" target="_blank">进入推广页</a>
				&nbsp;|&nbsp;<a href="javascript:showApplication();">推广页制作申请</a>
			</li>
			<li style="border:1px solid green;display:none;" id="application">
				请添加QQ：2496664615 或 376289635 备注为：<span style="color:red;">推广页制作申请</span>。稍后将会有人与您联系。
			</li>
				
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
