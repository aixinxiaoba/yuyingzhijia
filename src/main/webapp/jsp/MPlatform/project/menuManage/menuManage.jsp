<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单列表</title>


<link type="text/css" href="/commons/css/index.css" rel="stylesheet" ></link>
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />

<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>


<script type="text/javascript">

/**
 * 删除项目菜单。
 */
function projectMenuDelete(lProjectMenuID)
{
	var nCount = 0;
	var strURL = "/mpf/projectMenu/projectMenuDelete.do?flag=1";
	
	$(":checked.class_customerType").each(function(i, item){
		strURL += "&lProjectMenuID=" + item.value;
		nCount++;
	});
	
	if (nCount == 0 && lProjectMenuID > 0)
	{
		strURL += "&lProjectMenuID=" + lProjectMenuID;
	}
	if (nCount == 0 && lProjectMenuID <= 0)
	{
		$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要删除的项目菜单</p>", "info");
		return;
	}
	
	if (!confirm("确认要删除吗？"))
	{
		return;
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
				alert("删除成功！");
				$('#projectSubMenuList').datagrid("reload");
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
		$(".class_customerType").attr("checked","checked");
	}
	else
	{
		$(".class_customerType").removeAttr("checked");
	}
}

function showCustomerType(lCustomerID)
{
	$("#cutomerID_" + lCustomerID + " input").show();
	$("#cutomerID_" + lCustomerID + " select").show();
	$("#update_" + lCustomerID).show();
	
	$("#cutomerID_" + lCustomerID + " span").hide();
	$("#show_" + lCustomerID).hide();
}

function updateCustomerType(lProjectMenuID)
{
	var url="/mpf/customerType/customerTypeUpdate.do";
	
	$.ajax({
           url:url,
           type:"post",
           data:{"customerTypeID":lProjectMenuID,"customerTypeName":$("#customerTypeName_" + lProjectMenuID).val(), "customerTypeDesc":$("#customerTypeDesc_" + lProjectMenuID).val(),"customerTypeIsDefaultValue":$("#customerTypeIsDefaultValue_" + lProjectMenuID).val(), "lProjectID":"${lProjectID}"},
           dataType:"text",
           async: false,
           success:function(responseText)
           {	           
			if(responseText == "success")
			{
				location.reload();
			}
			else
			{
				alert(responseText);
			}
           }
    });
}

var parentMenu = -1;

$(function(){
	closeWindow();
	$('#projectSubMenuList').datagrid({
		loadMsg:'加载数据...',
		title:"一级项目菜单列表",
		
		pageList:[15,25,50],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/projectMenu/projectMenuList.do?lProjectID=${param.lProjectID}',
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
		                {field:'strName',title:"项目菜单名称",align:"left",width:fixWidth(0.24),
		                	formatter:function(value, rec)
		                	{
		                		var strHTML = "<input type='checkbox' class='class_customerType' value='" + rec.lId + "'>&nbsp;&nbsp;";
		                		
		                		return strHTML + rec.strMenuName;
		                	}
		                }
					]],
		columns:[[
			{field:'nIsDefaultValue',title:'有效状态',width:fixWidth(0.47),align:"center",
				formatter:function(value, rec){
					if (rec.validate == 1)
					{
						return "有效";
					}
					else
					{
						return "无效";
					}
				}},
			{field:'strIsAdd',title:'操作',width:fixWidth(0.24),align:"center",
				formatter:function(value,rec){
					// 判断是父类还是子类。
					if (rec.level == "1")
					{
						return "<a href='javascript:lstSubMenu("+rec.lId+");' style='text-decoration: none;color:blue;'>子类</a>";
					}
					else
					{
						var strHTML = "<a href='/jsp/MPlatform/news/newsList.jsp?lProjectID=${param.lProjectID}&lProjectMenuID="+rec.lId+"' style='text-decoration: none;color:blue;'>设置</a>";
						strHTML += "&nbsp;&nbsp;&nbsp;<a href='javascript:lstParentMenu();' style='text-decoration: none;color:blue;'>父类</a>";
						return strHTML;
					}
				}}
		]],
		rownumbers:true,
		pagination:true,
		toolbar:[
		{
			text:'<input type="checkbox" name="checkbox" value="checkbox" onclick="checkAll(this);">全选'
		},
		{
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				if ($("#level").val() == null || $("#level").val() == "")
				{
					$("#level").val(1);
				}
				$("#projectMenuName").val("");
				$("#add_projectMenuKey").val("");
				$(".projectMenuValidate").attr("checked",false);
				openWindow();
			}
		},
		{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#projectSubMenuList').datagrid('getSelected');

				projectMenuDelete(row == null ? -1 : row.lId);
			}
		},
		{
			text:'修改',
			iconCls:'icon-edit',
			handler:function(){
				var row = $('#projectSubMenuList').datagrid('getSelected');
				
				if (row == null)
				{
					$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要修改的项目菜单！</p>", "info");
					return;
				}
				$("#index_main_div1").hide();
				$("#lProjectMenuID").val(row.lId);
				$("#ck_name").val(row.strMenuName);
				$("#projectMenuKey").val(row.menuKey);
				$("#ck_desc").val(row.strDescrible);
				$(".ck_default").each(function(i,item){
					if (item.value == row.validate)
					{
						item.checked = true;	
					}
				})
				
				$("#info_title").text("修改项目菜单");
				$("#index_main_div2").show();
				$("#oper_save").show();
				$("#oper_saveInfo").hide();
				$("#projectMenuForm").attr("action", "/mpf/projectMenu/projectMenuUpdate.do");
			}
		}
		]
	});
});	

// 获取子类。
function lstSubMenu(lParID)
{
	$("#level").val(2);
	$("#parProjectMenuId").val(lParID);
	parentMenu = lParID;
	var strURL = "/mpf/projectMenu/projectMenuList.do?lProjectID=${param.lProjectID}&parentID=" + lParID;
	$('#projectSubMenuList').datagrid('options').url=strURL;
   	$("#projectSubMenuList").datagrid('reload'); // 重新加载。
}

//获取子类。
function lstParentMenu()
{
	var strURL = "/mpf/projectMenu/projectMenuList.do?lProjectID=${param.lProjectID}";
	$('#projectSubMenuList').datagrid('options').url=strURL;
   	$("#projectSubMenuList").datagrid('reload'); // 重新加载。
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
 * 设置宽高。
 * 
 * @param {Object} percent
 * @return {TypeName} 
 */
function fixWidth(percent)
{
    return (document.body.clientWidth ) * percent; //这里你可以自己做调整
}

function operBack()
{
	$("#index_main_div1").show();
	$("#index_main_div2").hide();
}

function submitForm(){
	$("#oper_save").hide();
	$("#oper_saveInfo").show();
	$('#projectMenuForm').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				$('#projectSubMenuList').datagrid('reload');
				operBack();
			}
			else
			{
				$.messager.alert('消息提示',"<p style='font-size:14px;'>" + msg + "</p>", "error");
				$("#oper_save").show();
				$("#oper_saveInfo").hide();
			}
      	}
    );
}

/**
 * 设置默认的项目菜单。
 * 
 * @param long customerTypeID
 * @param int status
 */
function customerTypeUpdateDefaultType(customerTypeID, status)
{
	$("#lProjectMenuID").val(customerTypeID);
	$(".ck_default").each(function(i,item){
		if (item.value == status)
		{
			item.checked = true;	
		}
	})
	$("#projectMenuForm").attr("action", "/mpf/customerType/customerTypeUpdate.do");
	submitForm();
}

function addProjectMenu(){
	$("#addProjectMenu").ajaxSubmit(function(msg){
			if (msg == "success")
			{
				closeWindow();
				$("#projectSubMenuList").datagrid('reload'); // 重新加载。
			}
			else
			{
				alert(msg);
			}
// 			$("#level").val("");
      	}
    );
}
</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;">
		<img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;
		<span style="font-weight:bold;font-size:12px;">项目菜单列表</span>
		<!-- 返回按钮 -->
		<a href="/jsp/MPlatform/project/projectList.jsp" style="margin-left:80%;font: 14px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;color:#2d64b3;">返回上一层</a>
		<!-- <img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;margin-left:80%;" onclick="location.href='/jsp/MPlatform/project/operCustomerTypeAndFiledInfo.jsp?lProjectID=${param.lProjectID}'"> -->
	</div>
	<table id="projectSubMenuList"></table>
</div>
<div id="index_main_div2" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;display:none;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;" id="info_title">新增项目菜单</span></div>
	<div id="oper_modify">
		<div style="margin-top: 10px;" id="formbox" class="form">
			<form action="/mpf/customerType/customerTypeAdd.do" method="post" id="projectMenuForm">
				<!-- 项目id -->
				<input type="hidden" value="${param.lProjectID}" name="lProjectID"/>
				<input type="hidden" value="" name="objProjectMenu.lId" id="lProjectMenuID"/>
				<div class="item">
					<span class="label" id="span_">菜单名称：</span>
			        <div class="fl">
			          <input type="text" value="" name="objProjectMenu.strMenuName" class="text" tabindex="1" id="ck_name"/>
			        </div>
		       </div>
		       <div class="item">
					<span class="label" id="span_">英文标识：</span>
			        <div class="fl">
			          <input type="text" value="" name="objProjectMenu.menuKey" class="text" tabindex="1" id="projectMenuKey"/>
			        </div>
		       </div>
		       <div class="item">
					<span class="label" id="span_">有效性：</span>
			        <div>
			          	<span style="float: left">有效</span><input type="radio" style="margin-top:5px;" value="1" name="objProjectMenu.validate" class="ck_default"/>
			          	<span style="float: left">&nbsp;&nbsp;无效</span><input type="radio" style="margin-top:5px;" value="0" name="objProjectMenu.validate" class="ck_default"/>
			        </div>
		       </div>
		     </form>
		</div>
		
		<div style="margin-top: 40px;margin-left:20px;text-align: center;" id="oper_save">
			<img src="/commons/image/s10.gif" width="58" height="22" style="cursor: pointer;" onclick="submitForm();">
			&nbsp;&nbsp;
			<img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="operBack()">
		</div>
		<div id="oper_saveInfo" style="text-align:center;display:none;">正在保存...</div>
	</div>
</div>

<div id="window1" class="easyui-window" title="项目菜单新增" icon="icon-save" style="width: 450px; height: 350px; padding: 5px; background: #fafafa;">
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
			<div id="">
				
				<form action="/mpf/projectMenu/projectMenuAdd.do" method="post" id="addProjectMenu">
					<input type="hidden" name="parProjectMenuId" id="parProjectMenuId" value=""/>
					<input type="hidden" name="objProjectMenu.level" id="level" value=""/>
					<!-- 项目id -->
					<input type="hidden" value="${param.lProjectID}" name="lProjectID" id="lProFildInfoID"/>
					<table width="100%" cellspacing="9">
						<tr height="45">
							<td style="text-align:right;width:150px;">菜单名称：</td>
							<td><input type="text" value="" name="objProjectMenu.strMenuName" id="projectMenuName"/></td>
						</tr>
						<tr height="45">
							<td style="text-align:right;width:150px;">英文标识：</td>
							<td><input type="text" value="" name="objProjectMenu.menuKey" id="add_projectMenuKey"/></td>
						</tr>
						<tr height="45">
							<td style="text-align:right;width:80px;">有效性：</td>
							<td>有效<input type="radio" name="objProjectMenu.validate" value="1" class="projectMenuValidate"/>&nbsp;无效<input type="radio" name="objProjectMenu.validate" value="0" class="projectMenuValidate"/></td>
						</tr>
					</table>
			     </form>
			</div>
		</div>
		<div region="south" border="false" style="text-align: center; height: 30px; line-height: 30px;">
			<a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="addProjectMenu()" >确定</a>
			<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="closeWindow()">取消</a>
		</div>
	</div>
</div>
</body>
</html>
