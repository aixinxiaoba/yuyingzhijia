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
	</table>	
	
	<div>
	
		<s:if test="lstProject != null && lstProject.size > 0">
			<s:iterator value="lstProject" id="objProject">
				<ul style="margin: 30px;margin-top:30px;border:1px solid green;list-style: none;line-height: 25px;">
					<li> 
						<span style="font-size:14; font-weight:bold;">
							项目名称：<s:property value="strPname"/>
						</span>
					</li>
					<li> 
						<span style="font-size:14; font-weight:bold;">
							我的推广地址：
						</span>
					</li>
					<li>
						<%=basePath %>front/customer/register.do?lCustomerID=${objCustomer.lId}&lProjectID=<s:property value='lId'/>
					</li>
					<li>
						<a href="javascript:objCore.copyData('loginUrl');">复制链接</a>
						<input type="hidden" id="loginUrl" value="<%=basePath %>front/customer/register.do?lCustomerID=${objCustomer.lId}&lProjectID=<s:property value='lId'/>"/>
						&nbsp;|&nbsp;<a href="/front/customer/register.do?lCustomerID=${objCustomer.lId}&lProjectID=<s:property value='lId'/>" target="_blank">进入推广页</a>
						&nbsp;|&nbsp;<a href="javascript:showApplication();">推广页制作申请</a>
					</li>
					<li style="border:1px solid green;display:none;" id="application">
						请添加QQ：2496664615 或 376289635 备注为：<span style="color:red;">推广页制作申请</span>。稍后将会有人与您联系。
					</li>
				</ul>
			</s:iterator>	
        </s:if>
        <s:else>
        	暂无推广地址，您可以主动申请幺！请添加QQ：2496664615 或 376289635我们将协助您申请。
        </s:else>
	</div>
</div>
</body>
</html>
