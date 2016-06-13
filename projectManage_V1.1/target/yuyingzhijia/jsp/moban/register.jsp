<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${objProject.strPname}--用户注册</title>
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />

<!-- Tooltip classes -->
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-violet/tip-violet.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-darkgray/tip-darkgray.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-skyblue/tip-skyblue.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-twitter/tip-twitter.css" type="text/css" />
<link rel="stylesheet" href="/commons/poshytip1.2/css/tip-green/tip-green.css" type="text/css" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.js" ></script>

<!-- the Poshy Tip plugin files -->
<script type="text/javascript" src="/commons/poshytip1.2/js/jquery.poshytip.js"></script>
<script type="text/javascript" src="/commons/js/0_core.js"></script>

<script type="text/javascript" src="/commons/js/jquery.form.js"></script>

<script type="text/javascript">
// create core object
var objCore = new Core();

/**
 * 页面加载完成时调用。
 */
$(function ()
{
	// 设置是否是必填标识
	$(".profileInfo").each(function(i,item){
		var strValue = $(item).val();
		var strIsNotNull = strValue;
		
		// 获取字段标识
		strValue = strValue.substring(strValue.indexOf("_") + 1);
		// 获取是否是必填标识 。
		strIsNotNull = strIsNotNull.substring(0,strIsNotNull.indexOf("_"));
		
		if (strIsNotNull == 1)
		{
			// $("#span_" + strValue).text("*" + $("#td_" + strValue).text());
			$("#span_" + strValue).html("<span class='red'>*</span>" + $("#span_" + strValue).html());
		}
	});
});

function checkRegister()
{
	var flag = true;
	
	$(".profileInfo").each(function(i,item){
		var strValue = $(item).val();
		var strIsNotNull = strValue;
		
		// 获取字段标识
		strValue = strValue.substring(strValue.indexOf("_") + 1);
		// 获取是否是必填标识 。
		strIsNotNull = strIsNotNull.substring(0,strIsNotNull.indexOf("_"));
		if (!dealWithField(strValue, strIsNotNull))
		{
			flag = false
			return false;
		}
		
		// 隐藏提示信息。
		$("#" + strValue).poshytip('hide');
	});
	
	return flag;
}

/**
 * 处理字段。
 */
function dealWithField(strValue, strIsNotNull)
{
	var strCurrentValue = "ck_" + strValue;
	var bIsMust = 2;
	var strName = $("#span_" + strValue).text();
	if (strIsNotNull == 1)
	{
		bIsMust = 1;
	}
	if (strValue == "SName")
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	if (strValue == "QQ")
	{
		return objCore.commonRegValidate("qq", false, strCurrentValue, strName, bIsMust);
	}
	if (strValue == "experience")
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	if (strValue == "IDE_Type") // 身份证类型。
	{
		return objCore.commonSelectValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "SNamePY") // 姓名拼音
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "OAddress") // 通讯地址：
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "PTel") // 固定电话
	{
		return objCore.commonRegValidate("tel", false, strCurrentValue, strName, bIsMust);
	}
	else if (strValue == "phone") // 手机号码
	{
		return objCore.commonRegValidate("phone", false, strCurrentValue, strName, bIsMust);
	}
	else if (strValue == "Email") // 电子邮箱
	{
		return objCore.commonRegValidate("email", false, strCurrentValue, strName, bIsMust);
	}
	else if (strValue == "Sex") // 性别
	{
		return objCore.commonRadioValidate(strCurrentValue, false, "性别", bIsMust);
	}
	else if (strValue == "SBrithday") // 生日
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "GuoJi") // 国籍
	{
		return objCore.commonSelectValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "Nation") // 民族
	{
		return objCore.commonSelectValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "SDegree") // 学历
	{
		return objCore.commonSelectValidate(strCurrentValue, false, strName, bIsMust);
	}
	// 用户自定义字段验证。
	else if (strValue == "BMCustom1") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom2") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom3") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom4") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom5") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom6") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom7") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom8") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom9") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom10") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom11") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom12") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom13") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom14") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	else if (strValue == "BMCustom15") // 用户自定义
	{
		return objCore.commonTextValidate(strCurrentValue, false, strName, bIsMust);
	}
	
	return true;
}

/**
 * 提交表单。
 */
function submitForm(){
	if (!checkRegister())
	{
		return;		
	}
	$("#registsubmit").attr("disabled", "disabled");
	customerForm.submit();
	/*
	 * // 隐藏所哟的提示信息。
	$("#customerForm input").each(function()
	{
		$(this).poshytip("hide");
	});
	$("#oper_modify").slideUp();
	$("#oper_return").slideDown();
	$("#oper_result").text("正在注册...");
	$('#customerForm').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				// 隐藏所哟的提示信息。
				$("#customerForm input").each(function()
				{
					alert(123)
					$(this).poshytip("hide");
				});
				// location.href = "/mpf/project/projectList.do";
			}
			else
			{
				$("#oper_result").text(msg);
			}
      	}
    );
	 * */
}
</script>
</head>
<body>

<div id="formbox">
    <div class="form" id="oper_modify">
      <s:if test="lstProFiledInfo != null && lstProFiledInfo.size > 0">
      	<h3>用户注册</h3>
      	<form id="customerForm" method="post" action="customerAdd.do" name="customerForm">
		  	<input type="hidden" value="${lProjectID }" name="lProjectID"/>
		  	<input type="hidden" value="${lCustomerID }" name="lCustomerID"/>
	      	<s:iterator value="lstProFiledInfo" id="proFileInfo" status="status">
	      	
	      		<!-- 字段默认与必填标识 -->
				<input type="hidden" value="<s:property value="#proFileInfo.nPfnature"/>_<s:property value="#proFileInfo.strPfidentity"/>" class="profileInfo"/>
			
	      		<s:if test="#proFileInfo.strPfidentity == 'SName'">
	      			<div class="item"><span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
				        <div class="fl">
				          <input type="text" name="objCustomer.strSname" class="text" tabindex="1" id="ck_SName" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
				        </div>
				      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'SNamePY'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_SNamePY" name="objCustomer.strSnamePY" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'IDE_Type'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_IDE_Type" name="objCustomer.nIdeType" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'IDE'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_IDE" name="objCustomer.strIde" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'Sex'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_Sex" name="objCustomer.strSex" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'SBrithday'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_SBrithday" name="objCustomer.strSbrithday" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'GuoJi'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_GuoJi" name="objCustomer.strGuoJi" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'Nation'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_Nation" name="objCustomer.strNation" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'SDegree'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_SDegree" name="objCustomer.strSdegree" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'SPIC'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_SPIC" name="objCustomer.strSPic" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'QQ'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <!-- <input type="text" id="qqnum" name="objCustomer.strQQ" class="text" tabindex="5" /> -->
			          <input type="text" id="ck_QQ" name="objCustomer.strQQ" class="text" tabindex="5" onblur="objCore.commonRegValidate('qq', false, 'ck_${proFileInfo.strPfidentity }', '${proFileInfo.strPfname}', <s:property value="nPfnature"/>)"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'experience'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_experience" name="objCustomer.strInterExperience" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'target'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_target" name="objCustomer.strTarget" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom1'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom1" name="objCustomer.strReserver1" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom2'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom2" name="objCustomer.strReserver2" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom3'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom3" name="objCustomer.strReserver3" class="text" tabindex="5" onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom4'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom4" name="objCustomer.strReserver4" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom5'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom5" name="objCustomer.strReserver5" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom6'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom6" name="objCustomer.strReserver6" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom7'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom7" name="objCustomer.strReserver7" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom8'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom8" name="objCustomer.strReserver8" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom9'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom9" name="objCustomer.strReserver9" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom10'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom10" name="objCustomer.strReserver10" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom11'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom11" name="objCustomer.strReserver11" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom12'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom12" name="objCustomer.strReserver12" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom13'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom13" name="objCustomer.strReserver13" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom14'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom14" name="objCustomer.strReserver14" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom15'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_BMCustom15" name="objCustomer.strReserver15" class="text" tabindex="5"  onblur="objCore.commonTextValidate('ck_${proFileInfo.strPfidentity }', false, '${proFileInfo.strPfname}', '<s:property value="nPfnature"/>');"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'phone'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl">
			          <input type="text" id="ck_phone" name="objCustomer.strSPhone" class="text" tabindex="5"onblur="objCore.commonRegValidate('phone', false, 'ck_${proFileInfo.strPfidentity }', '${proFileInfo.strPfname}', <s:property value="nPfnature"/>)"/>
			        </div>
			      </div>
	      		</s:if>
	      		
	      	</s:iterator>
      	</form>
      </s:if>
      <s:else>
      	未获取到注册信息
      </s:else>
      <div class="item"><span class="label">&nbsp;</span>
        <input type="button" class="yellow_button" id="registsubmit" value="提交注册信息" tabindex="8" onclick="submitForm()"/>
      </div>
      <!--item end-->
    </div>
    
    <div style="display:none;text-align:center;" id="oper_return">
	 	<div id="oper_result"></div>
	  	<div style="margin: 30px;"><img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="operReturn()" /></div>
	 </div>
</div>
<!--formbox end-->
</body>
</html>