<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0034)http://www.yuyingzhijia.cn/index.asp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>月嫂，月嫂公司，月嫂培训，正规月嫂公司，育儿嫂，育婴师</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<META name=description
	content=育婴之家庭服务为您提供专业月嫂，月嫂公司，月嫂，育儿嫂，育婴师。>
<META name=keywords content=月嫂，月嫂公司，月嫂，月嫂公司，正规月嫂公司，育儿嫂，育婴师>
<LINK rel="stylesheet" type="text/css" href="/commons/housekeeper/images/Css.css" />
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/index_css/index_123.css">
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/jquery.kinMaxShow-1.0.min1.js ">
	
	
<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui_user_defined.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

<!-- 
<link type="text/css" href="/commons/css/index.css" rel="stylesheet" ></link>
<link type="text/css" href="/commons/css/common.css" rel="stylesheet" ></link>
 -->

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>

<style type="">
.datagrid-row{
	height:65px;
}
</style>
<script type="text/javascript">
//easy UI start
$(function(){
	// 获取菜单名称。
	$.ajax({
           url:"/mpf/projectMenu/loadMenuName.do?projectKey=${param.projectKey}&menuKey=${param.projectMenuKey}",
           type:"post",
           data:{},
           dataType:"text",
           async: true,
           success:function(data)
           {	
        	   $("#menu_key").text(data);  
           }
    });
	
	$('#customerList').datagrid({
		// title: "客户列表",
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[20,30,40,50,100],
		nowrap: true,
		striped: true,
		collapsible:true,
		url:'/mpf/customer/loadCustomers.do?projectKey=${param.projectKey}&menuKey=${param.projectMenuKey}',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strSname',title:"&nbsp;&nbsp;<span style='font-size:13px;color:ff3300;font-weight:bold;font-family:宋体'>育婴人才</span>",align:"left",width:fixWidth(1),
				formatter:function(value,rec){
		    		$("#strSname").text(rec.strSname);
		    		if (rec.strSex != "")
		    		{
		    			$("#strSex").text(rec.strSex);
		    		}
		    		if (rec.strSemail !=  "")
		    		{
		    			$("#strSemail").text(rec.strSemail);
		    		}
		    		var strHTML = $("#customerDetail").html();
					return strHTML ;// += "<a href='/mpf/customer/customerDetial.do?lCustomerID=" + rec.lId + "&lProjectID=${lProjectID}&nType=1' title='查看客户详细' class='normal'>" + rec.strSname + "</a>";				
				}
			}
					]],/*
					
		columns:[[
			{field:'strRegistTime',title:"&nbsp;&nbsp;<span style='font-size:13px;color:ff3300;font-weight:bold;font-family:宋体'>&nbsp;</span>",width:fixWidth(0.2),align:"center",
				formatter:function(value,rec){
		    		// var strHTML = "<div style='height:42px;padding-top:40px;'>"+rec.strRegistTime+"</div>";
		    		var strHTML = "<div style='height:42px;padding-top:40px;'>&nbsp;</div>";
					return strHTML ;				
				}
			}
		]],
					*/
		pagination:true,
		rownumbers:true
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
		alert("请选择您要操作的用户！");
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
</HEAD>
<BODY>
	<%@ include file="../../common/head.jsp" %>
	<TABLE style="MARGIN-TOP: 23px;" cellSpacing=0 cellPadding=0 width=992
			align=center border=0>
			<TBODY>
				<TR>
					<TD vAlign=top>
						<TABLE cellSpacing=0 cellPadding=0  bgColor=#ffffff
							border=0>
							<TBODY>
								<TR>
									<TD style="BACKGROUND: url(/commons/housekeeper/images/d_top.jpg);MARGIN-TOP: 14px;padding-left: 40px;width:992px;" height=37>
										<span style='font-size:15px;color:ff6600;font-weight: bold'  id="menu_key"></span>
									</TD>
								</TR>
								<TR>
									<TD >
										<table id="customerList"></table>
									</TD>
								</TR>
								<TR>
									<TD height=5 ></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width=992 align=center border=0>
			<TBODY>
				<TR>
					<TD height=80>
						<TABLE cellSpacing=0 cellPadding=0 width=992 border=0>
							<TBODY>
								<TR>
									<TD>
										<P>
											Copyright
											<A href="http://www.yuyingzhijia.cn/"><FONT color=#800080>www.yuyingzhijia.cn</FONT>
											</A>&nbsp;All Rights Reserved 版权所有
										</P>
									</TD>
									<TD width=290 align=right>
										育婴之家
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<div id="customerDetail" style="display: none;">
				<ul style="line-height: 20px;float:left;margin-right:10px;font-size:14px;color:black;">
					<li style="float:left;width:78px;height:78px;padding:2px;margin-right:10px;"><img src="/commons/housekeeper/images/moren.jpg" style="width:78px;height:78px;"/></li>
					<li style="padding-top:10px;">姓名：<span id="strSname">--</span></li>
					<li style="">薪资要求：<span id="strSalary">--</span></li>
					<li style="float:left;vertical-align:middle;float:left;">性别：<span id="strSex">--</span></li>
				</ul>
				<ul style="line-height: 20px;float:left;margin-right:10px;;color:black;">
					<li style="padding-top:10px;">年龄：<span id="strAge">--</span></li>
					<li style="">工作经验：<span id="strExperence">--</span></li>
					<li style="float:left;vertical-align:middle;float:left;">期望工作地区：<span id="strArea">--</span></li>
				</ul>
				<ul style="line-height: 20px;float:left;margin-right:10px;font-size:14px;color:black;">
					<li style="padding-top:10px;">育婴师资格证：<span id="strZiGeZheng">--</span></li>
					<li style="">育婴评价：<span id="strPingJia">--</span></li>
					<li style="float:left;vertical-align:middle;float:left;">学历：<span id="strExperence">--</span></li>
				</ul>
				<ul style="line-height: 20px;float:left;margin-right:10px;font-size:14px;color:black;">
					<li style="padding-top:10px;">联系电话：<span id="strPhoneNum">--</span></li>
					<li style="">QQ：<span id="stQQ">--</span></li>
					<li style="float:left;vertical-align:middle;float:left;">邮箱：<span id="strSemail">--</span></li>
				</ul>
				<ul style="line-height: 20px;;color:black;">
					<li style="padding-top:10px;">是否在岗：<span id="isPosition">--</span></li>
				</ul>
		</div>
</BODY>
</HTML>

