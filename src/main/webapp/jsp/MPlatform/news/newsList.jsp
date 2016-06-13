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
		title: "新闻列表",
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[15,20,30,40,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/news/loadNewsList.do?lProjectID=${param.lProjectID}&lProjectMenuID=${param.lProjectMenuID}',
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
				var row = $('#customerList').datagrid('getSelected');
				
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
		alert("请选择您要操作的消息！");
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
</head>
<body>
<div style="margin-top:0px;margin-left:2px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;">
		<img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;
		<span style="font-weight:bold;font-size:12px;">项目菜单列表</span>
		<!-- 返回按钮 -->
		<a href="/jsp/MPlatform/project/menuManage/menuManage.jsp?lProjectID=${param.lProjectID }" style="margin-left:80%;font: 14px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;">返回上一层</a>
		<!-- <img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;margin-left:80%;" onclick="location.href='/jsp/MPlatform/project/operCustomerTypeAndFiledInfo.jsp?lProjectID=${param.lProjectID}'"> -->
	</div>
	<table id="customerList"></table>
</div>
</body>
</html>
