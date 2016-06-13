<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门列表</title>

<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />
<!-- 
<link type="text/css" rel="stylesheet" href="/commons/easyui/demo.css" />
 -->
<link type="text/css" href="/commons/css/common.css" rel="stylesheet" ></link>
<link type="text/css" href="/commons/css/index.css" rel="stylesheet" ></link>
<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">

/**
 * 删除客户。
 */
function emailDelete()
{
	var strURL = "/mpf/email/emailDelete.do?flag=1";
	
	if (!confirm("确认要删除吗？"))
	{
		return;
	}
	$(":checked.class_customer").each(function(i, item){
		strURL += "&emailID=" + item.value;
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
				$('#emailList').datagrid("reload");
			}
			else
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

function fixWidth(percent)
{
    return (document.body.clientWidth ) * percent; //这里你可以自己做调整
}

// easy UI start
$(function(){
	$('#emailList').datagrid({
		loadMsg:'加载数据...',
		// pageList:[10,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/email/loadEmails.do?lProjectID=${param.lProjectID}',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		onLoadError:function (data)
		{
			alert("加载失败！请稍后重试！");
		},
		singleSelect:true,
		columns:[[
			{field:'strSenderName',title:"发送者",align:"left",width:fixWidth(0.18),
				formatter:function(value, rec){
					var strHTML = "<input type='checkbox' class='class_customer' value='" + rec.lId + "'>&nbsp;&nbsp;";
					
					return strHTML += rec.strSenderName;
				}},
			{field:'strReciverName',title:'接收者',width:fixWidth(0.18),align:"center"},
			{field:'strSubject',title:'邮件主题',width:fixWidth(0.2),align:"center"},
			{field:'strSendDate',title:'发送时间',width:fixWidth(0.2),align:"center"},
			{field:'content',title:'操作',width:fixWidth(0.2),align:"center",
				formatter:function(value,rec){
					// "<a href='/mpf/email/emailList.do?lProjectID=" + rec.lId + "' class='normal'>转发</a>";
					return "<a href='/jsp/MPlatform/emailManage/customerList.jsp?lProjectID=${param.lProjectID}&lEmailID=" + rec.lId + "' class='normal'>邮件转发</a>";
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[
		{
			text:'<input type="checkbox" name="checkbox" value="checkbox" onclick="checkAll(this);">全选'
		},
		{
			text:'删除邮件',
			iconCls:'icon-remove',
			handler:function(){
				emailDelete();
			}
		}
		]
	});
});

// easy UI end

function searchByName()
{
   	$('#projectList').datagrid('options').queryParams = {
		strProjectName:$("#tempProjectName").val()
	};
   	$("#projectList").datagrid('reload'); // 重新加载。
}

</script>
</head>
<body>
<div style="margin-left: 2px;">
<!-- 
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1"  style="margin-top:0px;">
	  <tr>
		<td height="21" background="/commons/image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">项目搜索</span></td>
	  </tr>
	  <tr>
	    <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
	    	<ul style="margin-top:10px;margin-left:10px;list-style: none">
	    		<li style="float:left;width: 75px;font-size:14px;margin-top:3px;"><strong>项目名称</strong>:</li>
	    		<li style="float:left;width: 130px;"><input type="text" name="textfield" class="inputTextStyle" id="tempProjectName" value="${strProjectName}"></li>
	    		<li style="float:left;"><img src="/commons/image/s1.gif" width="59" height="22" onclick="searchByName();"></li>
	    	</ul>
	    </td>
         </tr>
</table>
 -->

<!--
<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">基本信息</span></div> 
<div><img src="/commons/image/t1.gif" align="absmiddle"> <span style="font-size:14; font-weight:bold;">项目列表</span></div>
 -->
<table id="emailList"></table>
</div>
</body>
</html>
