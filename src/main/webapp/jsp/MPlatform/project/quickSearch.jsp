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


//easy UI start
// /mpf/news/loadNewsList.do?lProjectID=${param.lProjectID}&lProjectMenuID=${param.lProjectMenuID}
$(function(){
	$('#newsList').datagrid({
		title: "新闻列表",
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[15,20,30,40,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strTitle',title:"&nbsp;&nbsp;新闻标题",align:"left",width:fixWidth(0.1242),
				formatter:function(value,rec)
				{
		    		var strHTML = "<input type='checkbox' class='class_customer' value='" + rec.lId + "'>&nbsp;&nbsp;";
		    		
					return strHTML += rec.strTitle;	
				}
			}
		    ]],
		columns:[[
			{field:'strProjectMenuName',title:'新闻类型',width:fixWidth(0.1242),align:"center"},
			{field:'strSendDate',title:'发布时间',width:fixWidth(0.1242),align:"center"},
			{field:'null',title:'操作',width:fixWidth(0.1242),align:"center",
				formatter:function(value,rec)
				{
					var strURL = "/mpf/news/newsDetial.do?newsID=" + rec.lId + "&lProjectID=${param.lProjectID}&lProjectMenuID=" + rec.lProjectMenuID;
		    		var strHTML = "<a href='" + strURL + "'>修改</a>";
		    		
		    		return strHTML;
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
			text:'删除新闻',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#newsList').datagrid('getSelected');
				
				manageCustomer(row ? row.id : -1);
			}
		},
		{
			text:'发布新闻',
			iconCls:'icon-edit',
			handler:function(){
				location.href = "/jsp/MPlatform/news/newsAdd.jsp?lProjectID=${param.lProjectID}&lProjectMenuID=${param.lProjectMenuID}";
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
				var row = $('#newsList').datagrid('getSelected');
				
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
				$('#newsList').datagrid("reload");
			}else
			{
				alert(responseText);
			}
           }
        });
}

function search()
{
   	$('#newsList').datagrid('options').queryParams = {
   		tempNewsName:$("#tempNewsName").val(),
   		tempNewsID:$("#tempNewsID").val()
	};
   	var strURL = "/mpf/news/loadSearchList.do";
	$('#newsList').datagrid('options').url=strURL;
   	$("#newsList").datagrid('reload'); // 重新加载。
}

</script>
</head>
<body>
<div style="margin-left: 2px;">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1"  style="margin-top:0px;">
		  <tr>
			<td height="21" background="/commons/image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">快速查询</span></td>
		  </tr>
		  <tr>
		    <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
		    	<ul style="margin-top:10px;margin-left:10px;list-style: none">
		    		<li style="float:left;width: 75px;font-size:14px;margin-top:3px;"><strong>新闻名称</strong>:</li>
		    		<li style="float:left;width: 130px;"><input type="text" name="tempNewsName" class="inputTextStyle" id="tempNewsName" value="${tempNewsName}"></li>
		    		<li style="float:left;width: 75px;font-size:14px;margin-top:3px;"><strong>新闻ID</strong>:</li>
		    		<li style="float:left;width: 130px;"><input type="text" name="tempNewsID" class="inputTextStyle" id="tempNewsID" value="${tempNewsID}"></li>
		    		<li style="float:left;"><img src="/commons/image/s1.gif" width="59" height="22" onclick="search();"></li>
		    	</ul>
		    </td>
	         </tr>
	</table>
	<table id="newsList"></table>
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
