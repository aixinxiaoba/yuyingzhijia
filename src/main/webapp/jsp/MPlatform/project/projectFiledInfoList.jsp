<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门列表</title>

<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet" ></link>
<link type="text/css" href="/commons/css/general.css" rel="stylesheet"></link>

<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>
<script type="text/javascript" src="/commons/js/0_core.js"></script>
<script type="text/javascript">
// easy UI start
$(function(){
	closeWindow();
	$('#projectFiledInfoList').datagrid({
		loadMsg:'加载数据...',
		title:"项目字段",
		pageList:[10,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/project/loadProjectFiledInfoList.do?lProjectID=${param.lProjectID}',
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
		                {field:'strPfname',title:"字段名称",align:"left",width:fixWidth(0.16)}
					]],
		columns:[[
			{field:'strNature',title:'字段属性',width:fixWidth(0.16),align:"center",
				formatter:function(value, rec){
					if (rec.nPfnature == 1)
					{
						return "<a href='javascript:updateProFieldInfoNatureStatus(0," + rec.lId + ")' class='normal' onmouseover='this.text=\"改为默认\"' onmouseout='this.text=\"必填\"'>必填</a>";
					}
					else
					{
						return "<a href='javascript:updateProFieldInfoNatureStatus(1," + rec.lId + ")' class='normal' onmouseover='this.text=\"改为必填\"' onmouseout='this.text=\"默认\"'>默认</a>";
					}
				}},
			{field:'strStatus',title:'字段状态',width:fixWidth(0.16),align:"center"},
			{field:'strIsAdd',title:'注册',width:fixWidth(0.16),align:"center",
				formatter:function(value, rec){
					if (rec.nIsAdd == 1)
					{
						return "<a href='javascript:updateProFieldInfoRegStatus(0," + rec.lId + ")' class='normal' onmouseover='this.text=\"改为否\"' onmouseout='this.text=\"是\"'>是</a>";
					}
					else
					{
						return "<a href='javascript:updateProFieldInfoRegStatus(1," + rec.lId + ")' class='normal' onmouseover='this.text=\"改为是\"' onmouseout='this.text=\"否\"'>否</a>";
					}
				}},
			{field:'strIsModify',title:'修改',width:fixWidth(0.16),align:"center",
				formatter:function(value, rec){
					if (rec.nIsModify == 1)
					{
						return "<a href='javascript:updateProFieldInfoModifyStatus(0," + rec.lId + ")' class='normal' onmouseover='this.text=\"改为否\"' onmouseout='this.text=\"是\"'>是</a>";
					}
					else
					{
						return "<a href='javascript:updateProFieldInfoModifyStatus(1," + rec.lId + ")' class='normal' onmouseover='this.text=\"改为是\"' onmouseout='this.text=\"否\"'>否</a>";
					}
				}},
			{field:'weizhi',title:'操作',width:fixWidth(0.16),align:"center",
				formatter:function(value, rec){
					if (rec.nStatus == 1)
					{
						return "<a href='javascript:updateProFieldInfoActiveStatus(0," + rec.lId + ")' class='normal'>禁用</a>";
					}
					else
					{
						return "<a href='javascript:updateProFieldInfoActiveStatus(1," + rec.lId + ")' class='normal'>启用</a>";
					}
				}}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:[
		{
			text:'修改字段',
			iconCls:'icon-edit',
			handler:function(){
				var row = $('#projectFiledInfoList').datagrid('getSelected');
				if (row){
					$("#proFiledInfoName").val(row.strPfname);
					$("#lProFildInfoID").val(row.lId);
					setRadio("proFiledInfoProp", row.nPfnature);
					setRadio("proFiledInfoStatus", row.nStatus);
					setRadio("proFiledInfoReg", row.nIsAdd);
					setRadio("proFiledInfoModify", row.nIsModify);
					
					$(".proFiledInfoStatus").each(function(){
						if (this.value == row.nStatus)
						{
							this.checked = true;
							return true;
						}
						else if (this.value == row.nStatus)
						{
							this.checked = true;
							return true;
						}
					})
					$("#proFiledInfoName").val(row.strPfname);
					openWindow();
				}
				else
				{
					$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要修改的字段</p>", "info");
				}
			}
		}
		]
	});
	
	$('#filedInfoList').datagrid({
		loadMsg:'加载数据...',
		title:"可用字段列表",
		pageList:[10,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/project/loadFiledInfoList.do?lProjectID=${param.lProjectID}',
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
		                {field:'strFName',title:"字段名称",align:"left",width:fixWidth(0.16)}
					]],
		columns:[[
			{field:'strNature',title:'字段属性',width:fixWidth(0.16),align:"center"},
			{field:'strStatus',title:'字段状态',width:fixWidth(0.16),align:"center",
				formatter:function(value,rec){
					return "未使用";
				}},
			{field:'strIsAdd',title:'注册',width:fixWidth(0.16),align:"center",
				formatter:function(value,rec){
					return "是";
				}},
			{field:'strIsModify',title:'修改',width:fixWidth(0.16),align:"center",
				formatter:function(value,rec){
					return "是";
				}},
			{field:'content',title:'操作',width:fixWidth(0.16),align:"center",
				formatter:function(value,rec){
					return "<a href='javascript:activeFiledInfo(" + rec.lId + ")' class='normal'>启用</a>";
				}
			}
		]],
		rownumbers:true,
		pagination:true
	});
	
	forbiddenEnter();		
});

/**
 * 禁用用户的回车事件。
 */
function forbiddenEnter()
{
	document.onkeydown=function(evt)
	{
		if(evt.keyCode ==13)
		{
		     return false; // 注意这里必须是false。
		}
	}
}

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

function setRadio(className, status)
{
	$("." + className).each(function(){
		if (this.value == status)
		{
			this.checked = true;
			return true;
		}
		else if (this.value == status)
		{
			this.checked = true;
			return true;
		}
	})
}

function closeWindow()
{
	$('#window1').window('close');
}

function openWindow()
{
	$('#window1').window('open');
}

/**
 * 打开或禁用项目字段。
 * 
 * @param {Object} lProFiledInfoID
 * @param {Object} status
 * @param {Object} method
 */
function updateProFieldInfoActiveStatus(status, lProFieldInfoID)
{
	proFiledOper(lProFieldInfoID, status,"updateProFieldInfoActiveStatus");
}

/**
 * 修改项目属性。
 * 
 * @param {Object} lProFiledInfoID
 * @param {Object} status
 * @param {Object} method
 */
function updateProFieldInfoNatureStatus(status, lProFieldInfoID)
{
	proFiledOper(lProFieldInfoID, status,"updateProFieldInfoNatureStatus");
}
 
 /**
 * 是否是注册字段。
 * 
 * @param {Object} lProFiledInfoID
 * @param {Object} status
 * @param {Object} method
 */
function updateProFieldInfoRegStatus(status, lProFieldInfoID)
{
	proFiledOper(lProFieldInfoID, status,"updateProFieldInfoRegStatus");
}

/**
 * 是否是注册字段。
 * 
 * @param {Object} lProFiledInfoID
 * @param {Object} status
 * @param {Object} method
 */
function updateProFieldInfoModifyStatus(status, lProFieldInfoID)
{
	proFiledOper(lProFieldInfoID, status,"updateProFieldInfoModifyStatus");
}
 
/**
 * 项目字段核心处理函数。
 * 
 * @param {Object} lProFiledInfoID
 * @param {Object} status
 * @param {Object} method
 */
function proFiledOper(lProFiledInfoID, status, method)
{
	var url="/mpf/project/" + method + ".do";
	
	$.ajax({
           url:url,
           type:"post",
           data:{"lProFieldInfoID": lProFiledInfoID, "lProjectID":"${param.lProjectID}", "status":status},
           dataType:"text",
           async: false,
           success:function(responseText)
           {	           
			if(responseText == "success")
			{
				$("#projectFiledInfoList").datagrid('reload'); // 重新加载。
			}
			else
			{
				alert(responseText);
			}
           }
        });
}

/**
 * 修改项目字段。
 */
function modifyProFiledInfo()
{
	$("#proFiledInfoForm").ajaxSubmit(function(msg){
			if (msg == "success")
			{
				alert("修改成功！");
				closeWindow();
				$("#projectFiledInfoList").datagrid('reload'); // 重新加载。
			}
			else
			{
				alert(msg);
			}
      	}
    );
}

/**
 * 启用基础字段。
 * 
 * @param {Object} lProFiledInfoID
 */
function activeFiledInfo(lFiledInfoID)
{
    var url="/mpf/project/activeFieldInfo.do";
	
	$.ajax({
           url:url,
           type:"post",
           data:{"lFieldInfoID": lFiledInfoID, "lProjectID":"${param.lProjectID}"},
           dataType:"text",
           async: false,
           success:function(responseText)
           {	           
			if(responseText == "success")
			{
				alert("操作成功！");
				$("#projectFiledInfoList").datagrid('reload'); // 重新加载。
				$("#filedInfoList").datagrid('reload'); // 重新加载。
			}
			else
			{
				alert(responseText);
			}
           }
        });
}
</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;">
		<img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;
		<span style="font-weight:bold;font-size:12px;">字段管理</span>
		<!-- 返回按钮 -->
		<a href="/jsp/MPlatform/project/projectList.jsp" style="margin-left:80%;font: 14px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;">返回上一层</a>
		<!-- <img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;margin-left:80%;" onclick="location.href='/jsp/MPlatform/project/operCustomerTypeAndFiledInfo.jsp?lProjectID=${param.lProjectID}'"> -->
	</div>
	<div style="margin:20px;font: 16px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;">
		当前项目用户注册地址:&nbsp;<a style="color:red;" title="直接访问" target="_blank" href='<%=basePath %>front/customer/register.do?lProjectID=${param.lProjectID}'><%=basePath %>front/customer/register.do?lProjectID=${param.lProjectID}</a>
		<a href="javascript:void(0);" onclick="objCore.copyData('loginUrl');" style="font: 14px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;list-style:none;">复制链接</a>
		<input type="hidden" id="loginUrl" value="<%=basePath %>front/customer/register.do?lProjectID=${param.lProjectID}"/>
	</div>
	<div style="margin:20px;text-align: right;">
		
	</div>
	<div id="div_projectFiledInfoList"> <table id="projectFiledInfoList"></table> </div>
	<div id="div_filedInfo"><table id="filedInfoList"></table></div>
</div>

<div id="window1" class="easyui-window" title="项目字段修改" icon="icon-save" style="width: 450px; height: 350px; padding: 5px; background: #fafafa;">
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
			<div id="">
				<form action="/mpf/project/updateProFieldInfo.do" method="post" id="proFiledInfoForm">
					<!-- 项目id -->
					<input type="hidden" value="" name="objProFiledInfo.lId" id="lProFildInfoID"/>
					<table width="100%" cellspacing="9">
						<tr height="45">
							<td style="text-align:right;width:150px;">字段名称：</td>
							<td><input type="text" value="" name="objProFiledInfo.strPfname" id="proFiledInfoName"/></td>
						</tr>
						<tr height="45">
							<td style="text-align:right;width:80px;">字段属性：</td>
							<td>必填<input type="radio" name="objProFiledInfo.nPfnature" value="1" class="proFiledInfoProp"/>&nbsp;选填<input type="radio" name="objProFiledInfo.nPfnature" value="2" class="proFiledInfoProp"/></td>
						</tr>
						<tr height="45">
							<td style="text-align:right;width:80px;">字段状态：</td>
							<td>启用<input type="radio" name="objProFiledInfo.nStatus" value="1" class="proFiledInfoStatus"/>&nbsp;未启用<input type="radio" name="objProFiledInfo.nStatus" value="0" class="proFiledInfoStatus"/></td>
						</tr>
						<tr height="45">
							<td style="text-align:right;width:80px;">是否是注册：</td>
							<td>&nbsp;&nbsp;是<input type="radio" value="1" name="objProFiledInfo.nIsAdd" class="proFiledInfoReg"/>&nbsp;&nbsp;&nbsp;否<input type="radio" value="0" name="objProFiledInfo.nIsAdd" class="proFiledInfoReg"/></td>
						</tr>
						<tr height="45">
							<td style="text-align:right;width:80px;">是否是修改：</td>
							<td>&nbsp;&nbsp;是<input type="radio" value="1" name="objProFiledInfo.nIsModify" class="proFiledInfoModify"/>&nbsp;&nbsp;&nbsp;否<input type="radio" name="objProFiledInfo.nIsModify" value="0" class="proFiledInfoModify"/></td>
						</tr>
					</table>
			     </form>
			</div>
		</div>
		<div region="south" border="false" style="text-align: center; height: 30px; line-height: 30px;">
			<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="modifyProFiledInfo()" >确定</a>
			<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="closeWindow()">取消</a>
		</div>
	</div>
</div>
</body>
</html>
