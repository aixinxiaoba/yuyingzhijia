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
		                {field:'strPname',title:"项目名称",align:"left",width:fixWidth(0.25),
		                	formatter:function(value,rec)
		                	{
		                		var strHTML = "<input type='checkbox' class='class_project' value='" + rec.lId + "'>&nbsp;&nbsp;";
		                		
		                		return strHTML + rec.strPname;
		                	}
		                }
					]],
		columns:[[
			{field:'adminName',title:'项目负责人',width:fixWidth(0.25),align:"center"},
			{field:'strCustomerTypeName',title:'项目类型',width:fixWidth(0.25),align:"center"},
			{field:'strStatus',title:'网站预览',width:fixWidth(0.25),align:"center",
				formatter:function(value,rec){
					var strHTML = "<a href='/projectIndex.do?projectKey="+rec.projectKey+"' target='_blank'>预览</a>";
					return strHTML;
				}
			}
		]],
		rownumbers:true,
		pagination:true,
		toolbar:[
		{
			text:'<input type="checkbox" name="checkbox" value="checkbox" onclick="checkAll(this);">全选'
		},
		{
			text:'修改项目',
			iconCls:'icon-edit',
			handler:function()
			{
				var row = $('#projectList').datagrid('getSelected');
				
				if (row)
				{
					location.href = "/mpf/project/projectDetial.do?lProjectID=" + row.lId + "&lCustomerTypeID=" + row.lCustomerTypeID;
				}
				else
				{
					alert("请选择您要修改的项目");
				}
			}
		},
		{
			text:'删除项目',
			iconCls:'icon-remove',
			handler:function()
			{
				projectDelete();
			}
		},
		{
			text:'客户类型设置',
			iconCls:'icon-edit',
			handler:function()
			{
				var row = $('#projectList').datagrid('getSelected');
				if (row)
				{
					location.href = "/jsp/MPlatform/project/customerType/customerTypeList.jsp?lProjectID=" + row.lId;
				}
				else
				{
					alert("请选择您要操作的项目");
				}
			}
		},
		{
			text:'菜单管理',
			iconCls:'icon-edit',
			handler:function()
			{
				var row = $('#projectList').datagrid('getSelected');
				
				if (row)
				{
					location.href = "/jsp/MPlatform/project/menuManage/menuManage.jsp?lProjectID=" + row.lId;
				}
				else
				{
					alert("请选择您要操作的项目");
				}
			}
		},
		{
			text:'注册流程设置',
			iconCls:'icon-edit',
			handler:function()
			{
				var row = $('#projectList').datagrid('getSelected');
				
				if (row)
				{
					location.href = "/jsp/MPlatform/project/projectFiledInfoList.jsp?lProjectID=" + row.lId;
				}
				else
				{
					alert("请选择您要操作的项目");
				}
			}
		}
		]
	});
});

/*
 * ,
		{
			text:'菜单管理',
			iconCls:'icon-edit',
			handler:function()
			{
				var row = $('#projectList').datagrid('getSelected');
				
				if (row)
				{
					location.href = "/jsp/MPlatform/project/menuManage/menuManage.jsp?lProjectID=" + row.lId;
				}
				else
				{
					alert("请选择您要操作的项目");
				}
			}
		}
 * */
// easy UI end

/**
 * 设置宽高。
 * 
 * @param {Object} percent
 * @return {TypeName} 
 */
function fixWidth(percent)
{
    return (document.body.clientWidth ) * percent; //这里你可以自己做调整
}

/**
 * 删除项目。
 */
function projectDelete()
{
	var strURL = "/mpf/project/projectDelete.do?flag=1";
	
	$(":checked.class_project").each(function(i, item){
		strURL += "&projectID=" + item.value;
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
				// location.reload();
				$('#projectList').datagrid("reload");
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
	<ul style="border:1px solid orange;margin-top:30px;text-align:left;color:green;font-size:20px;height:100px;line-height: 20px;">
   		<li style="">
       	 		<br/>说明：如果您对当前生成的网站模板不满意，您可以通过QQ号与QQ群与我们联系，我们将竭诚为您服务。提供您满意的产品！
   		</li>
   		<li style="text-align:center;">
   			<br/><a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=bf25712c54ca049803ee847e1a47f45bd5cfed7f80d1e8b47e4bd9d84da4f632"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="PM官方交流群" title="PM官方交流群"></a>
   		</li>
   	</ul>
</div>
</body>
</html>
