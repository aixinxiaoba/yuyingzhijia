<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门列表</title>

<link type="text/css" href="/commons/css/index.css" rel="stylesheet" ></link>
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />

<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>


<script type="text/javascript">

/**
 * 删除项目类型。
 */
function customerTypeDelete(lCustomerTypeID)
{
	var nCount = 0;
	var strURL = "/mpf/customerType/customerTypeDelete.do?flag=1";
	
	$(":checked.class_customerType").each(function(i, item){
		strURL += "&lCustomerTypeID=" + item.value;
		nCount++;
	});
	
	if (nCount == 0 && lCustomerTypeID > 0)
	{
		strURL += "&lCustomerTypeID=" + lCustomerTypeID;
	}
	if (nCount == 0 && lCustomerTypeID <= 0)
	{
		$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要删除的项目类型</p>", "info");
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
				$('#customerTypeList').datagrid("reload");
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

function updateCustomerType(lCustomerTypeID)
{
	var url="/mpf/customerType/customerTypeUpdate.do";
	
	$.ajax({
           url:url,
           type:"post",
           data:{"customerTypeID":lCustomerTypeID,"customerTypeName":$("#customerTypeName_" + lCustomerTypeID).val(), "customerTypeDesc":$("#customerTypeDesc_" + lCustomerTypeID).val(),"customerTypeIsDefaultValue":$("#customerTypeIsDefaultValue_" + lCustomerTypeID).val(), "lProjectID":"${lProjectID}"},
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


$(function(){
	$('#customerTypeList').datagrid({
		loadMsg:'加载数据...',
		title:"项目类型列表",
		pageList:[10,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/customerType/customerTypeList.do?isProjectType=true',
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
		                {field:'strName',title:"项目类型名称",align:"left",width:fixWidth(0.24),
		                	formatter:function(value, rec)
		                	{
		                		var strHTML = "<input type='checkbox' class='class_customerType' value='" + rec.lId + "'>&nbsp;&nbsp;";
		                		
		                		return strHTML + rec.strName;
		                	}
		                }
					]],
		columns:[[
			{field:'strDescrible',title:'项目类型描述',width:fixWidth(0.24),align:"center"},
			{field:'nIsDefaultValue',title:'当前类型',width:fixWidth(0.24),align:"center",
				formatter:function(value, rec){
					if (rec.nIsDefaultValue == 1)
					{
						return "默认";
					}
					else
					{
						return "非默认";
					}
				}},
			{field:'strIsAdd',title:'操作',width:fixWidth(0.24),align:"center",
				formatter:function(value,rec){
					if (rec.nIsDefaultValue == 1)
					{
						return "<a href='javascript:customerTypeUpdateDefaultType(" + rec.lId + ",2);' style='text-decoration: none;'>非默认</a>";
					}
					else
					{
						return "<a href='javascript:customerTypeUpdateDefaultType(" + rec.lId + ",1);' style='text-decoration: none;'>默认</a>";
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
				$("#index_main_div1").hide();
				$("#ck_name").val("");
				$("#ck_desc").val("");
				$(".ck_default").removeAttr("checked");
				$("#index_main_div2").show();
				
				$("#info_title").text("新增项目类型");
				$("#oper_save").show();
				$("#oper_saveInfo").hide();
				$("#customerTypeForm").attr("action","/mpf/customerType/customerTypeAdd.do");
			}
		},
		{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#customerTypeList').datagrid('getSelected');

				customerTypeDelete(row == null ? -1 : row.lId);
			}
		},
		{
			text:'修改',
			iconCls:'icon-edit',
			handler:function(){
				var row = $('#customerTypeList').datagrid('getSelected');
				
				if (row == null)
				{
					$.messager.alert('消息提示',"<p style='font-size:14px;'>请选择您要修改的项目类型！</p>", "info");
					return;
				}
				$("#index_main_div1").hide();
				$("#lCustomerTypeID").val(row.lId);
				$("#ck_name").val(row.strName);
				$("#ck_desc").val(row.strDescrible);
				$(".ck_default").each(function(i,item){
					if (item.value == row.nIsDefaultValue)
					{
						item.checked = true;	
					}
				})
				
				$("#info_title").text("修改项目类型");
				$("#index_main_div2").show();
				$("#oper_save").show();
				$("#oper_saveInfo").hide();
				$("#customerTypeForm").attr("action", "/mpf/customerType/customerTypeUpdate.do");
			}
		}
		]
	});
});	

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
	$('#customerTypeForm').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				$('#customerTypeList').datagrid('reload');
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
 * 设置默认的项目类型。
 * 
 * @param long customerTypeID
 * @param int status
 */
function customerTypeUpdateDefaultType(customerTypeID, status)
{
	$("#lCustomerTypeID").val(customerTypeID);
	$(".ck_default").each(function(i,item){
		if (item.value == status)
		{
			item.checked = true;	
		}
	})
	$("#customerTypeForm").attr("action", "/mpf/customerType/customerTypeUpdate.do");
	submitForm();
}
</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">项目类型列表</span></div>
	<table id="customerTypeList"></table>
</div>
<div id="index_main_div2" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;display:none;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;" id="info_title">新增项目类型</span></div>
	<div id="oper_modify">
		<div style="margin-top: 10px;" id="formbox" class="form">
			<form action="/mpf/customerType/customerTypeAdd.do" method="post" id="customerTypeForm">
				<!-- 项目id -->
				<input type="hidden" value="true" name="isProjectType"/>
				<input type="hidden" value="${objCustomerType.lCustomerTypeID}" name="objCustomerType.lId" id="lCustomerTypeID"/>
				<div class="item">
					<span class="label" id="span_${proFileInfo.strPfidentity}">类型名称：</span>
			        <div class="fl">
			          <input type="text" value="${objCustomerType.strName}" name="objCustomerType.strName" class="text" tabindex="1" id="ck_name"/>
			        </div>
		       </div>
		       <div class="item">
					<span class="label" id="span_${proFileInfo.strPfidentity}">类型描述：</span>
			        <div class="fl">
			          <input type="text" value="${objCustomerType.strDescrible}" name="objCustomerType.strDescrible" class="text" tabindex="1" id="ck_desc"/>
			        </div>
		       </div>
		       
		       <div class="item">
					<span class="label" id="span_${proFileInfo.strPfidentity}">是否为默认类型：</span>
			        <div>
			          	<span style="float: left">是</span><input type="radio" style="margin-top:5px;" value="1" name="objCustomerType.nIsDefaultValue" class="ck_default"/>
			          	<span style="float: left">&nbsp;&nbsp;否</span><input type="radio" style="margin-top:5px;" value="2" name="objCustomerType.nIsDefaultValue" class="ck_default"/>
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
</body>
</html>
