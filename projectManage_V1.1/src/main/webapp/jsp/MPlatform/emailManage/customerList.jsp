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
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />

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
 * 邮件发送。
 */
function transmitEmail(strQQ)
{
	var nCount = 0;
	var strURL = "/mpf/email/emailSend.do";
	var strCustomerID = "";
	var strCustomerQQ = "";
	
	
	if (strQQ != null && strQQ > 0)
	{
		strCustomerQQ = strQQ;
	}
	else
	{
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
			alert("请选择您要转发的用户！");
			return;
		}
	}
	strURL += "?strCustomerQQ=" + strCustomerQQ + "&lProjectID=${param.lProjectID}&lEmailID=${param.lEmailID}";
	
	$("#oper_modify").slideUp();
	$("#oper_return").slideDown();
	$("#oper_result").text("正在发送...可能比较慢，请耐心等候...");
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
			       $("#oper_result").text("发送成功！");
			   }
		       else
			   {
			       $("#oper_result").text(responseText);
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
	$('#customerList').datagrid({
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[15,20,30,40,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/customer/loadCustomers.do?lProjectID=${param.lProjectID}',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strSname',title:"&nbsp;&nbsp;客户名称",align:"left",width:fixWidth(0.1242),
				formatter:function(value,rec){
		    		var strHTML = "<input type='checkbox' class='class_customer' value='" + rec.lId + "'>&nbsp;&nbsp;";
		    		
					return strHTML += "<a href='/mpf/customer/customerDetial.do?lCustomerID=" + rec.lId + "&nType=1' title='查看客户详细' class='normal'>" + rec.strSname + "</a>";				
				}
			}
					]],
		columns:[[
			{field:'strParentCustomerQQ',title:'客户负责人QQ',width:fixWidth(0.1242),align:"center"},
			{field:'strCustomerTypeName',title:'客户类型',width:fixWidth(0.1242),align:"center"},
			{field:'strRegistTime',title:'注册时间',width:fixWidth(0.1242),align:"center"},
			{field:'strSPhone',title:'手机号码',width:fixWidth(0.1242),align:"center"},
			{field:'strQQ',title:'QQ号码',width:fixWidth(0.1242),align:"center",
				formatter:function(value, rec){
					var objInput = "<input type='hidden' value='" + rec.strQQ + "' class='c_qq_" + rec.lId + "' />";
					
					return objInput + rec.strQQ;
				}
			},
			{field:'strIsActive',title:'是否激活',width:fixWidth(0.1242),align:"center"},
			{field:'content',title:'操作',width:fixWidth(0.1),align:"center",
				formatter:function(value,rec){
					
					return "<a href=javascript:transmitEmail(" + rec.strQQ + ") class='normal'>转发</a>";
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
			text:'批量转发',
			iconCls:'icon-edit',
			handler:function(){
				transmitEmail(-1);
			}
		}
		]
	});
	
});

function operReturn()
{
	$("#oper_modify").slideDown();
	$("#oper_return").slideUp();
}
// easy UI end
</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">客户列表</span></div>
	
	<div id="oper_modify">
		<div style="text-align:center;margin:20px;">请选择您要发送的客户！</div>
		<table id="customerList"></table>
	</div>
	<div style="display:none;text-align:center;" id="oper_return" class="formbox">
	 	<div id="oper_result"></div>
	  	<div style="margin: 30px;"><img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="operReturn()"></div>
	 </div>
</div>
</body>
</html>
