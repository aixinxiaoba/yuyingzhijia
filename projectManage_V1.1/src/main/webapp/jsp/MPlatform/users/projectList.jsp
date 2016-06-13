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
function checkAll(objThis)
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


// easy UI start
$(function(){
	$('#projectList').datagrid({
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
		                	formatter:function(value,rec)
		                	{
		                		var strHTML = "<input type='checkbox' class='class_project' value='" + rec.lId + "'>&nbsp;&nbsp;";
		                		
		                		return strHTML + rec.strPname;
		                	}
		                }
					]],
		columns:[[
			{field:'adminName',title:'项目负责人',width:500,align:"center"},
			{field:'content',title:'操作',width:138,align:"center",
				formatter:function(value,rec)
				{
					return "<a href='javascript:void(0)' class='normal' onclick='projectAuthorization(" + rec.lId + ");'>授权</a>";
				}
			}
		]],
		rownumbers:true,
		pagination:true
	});
});

// easy UI end
/*
 * ,
		toolbar:[
		{
			text:'<input type="checkbox" name="checkbox" value="checkbox" onclick="checkAll(this);">全选'
		},
		{
			text:'授权',
			iconCls:'icon-edit',
			handler:function()
			{
				projectAuthorization(-1);
			}
		}
		]
 * */

/**
 * 授权项目。
 */
function projectAuthorization(projectID)
{
	var strURL = "/mpf/users/projectAuthorization.do?lUsersID=${param.lUsersID}";
	var count = 0;
	
	if (projectID > 0)
	{
		strURL += "&projectID=" + projectID;
	}
	else
	{
		$(":checked.class_project").each(function(i, item){
			strURL += "&projectID=" + item.value;
			count++;
		});
		
		if (count <= 0)
		{
			alert("请选择要授权的项目");	
		}
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
					alert("授权成功！");
					location.href = "/jsp/MPlatform/users/usersList.jsp";
				}else
				{
					alert(responseText);
				}
           }
        });
}

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
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1"  style="margin-top:0px;">
		  <tr>
			<td height="21" background="/commons/image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">项目管理</span></td>
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
	<table id="projectList"></table>
</div>
</body>
</html>
