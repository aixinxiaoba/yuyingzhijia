<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet"  />
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>

<script type="text/javascript">
function submitForm(){
	
	if ($("#lProjectID").val() > 0)
	{
		$("#projectForm").attr("action", "projectModify.do");
	}
	
	if ($("#ck_SName").val() == "")
	{
		alert("请填写项目名称！");
		return false;
	}
	
	if ($("#ck_SName").val() == "")
	{
		alert("请填写项目名称！");
		return false;
	}
	if ($("#ck_EnSName").val() == "")
	{
		alert("请填写项目英文名称！");
		return false;
	}
	
	$("#oper_module").hide();
	$("#msg_module").html("正在处理...");
	$('#projectForm').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				if ($("#lProjectID").val() > 0)
				{
					alert("修改成功！");
				}
				else
				{
					alert("保存成功！");
				}
				location.href = "/mpf/project/projectList.do";
			}
			else
			{
				alert(msg);
				$("#oper_module").show();
				$("#msg_module").html("");
			}
      	}
    );
}

$(function (){
	var status = "${objProject.nEmailListStatus}";
	var resultPage = "${objProject.nIsUseResultPage}";
	if (status == 0)
	{
		$("#useQQList").attr("checked", "checked");
	}
	else if (status == 1)
	{
		$("#notUseQQList").attr("checked", "checked");
	}
	
	if (resultPage == 0)
	{
		$("#notUseResultPage").attr("checked", "checked");
	}
	else if (resultPage == 1)
	{
		$("#useResultPage").attr("checked", "checked");		
	}
});

</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">项目新建</span></div>
	<div style="margin-top: 10px;min-height:200px;" id="formbox" class="form">
		<form name="projectForm" method="post" id="projectForm" action="/mpf/project/projectAdd.do">
			<input type="hidden" value="${objProject.lId}" name="lProjectID" id="lProjectID"/>
			<div class="item">
				<span class="label">项目名称：</span>
		        <div class="fl">
		          <input type="text" name="objProject.strPname" value="${objProject.strPname}" class="text" tabindex="1" id="ck_SName"/>
		        </div>
		      </div>
		      <div class="item">
				<span class="label">项目英文名称：</span>
		        <div class="fl">
		        	<input type="text" name="objProject.projectKey" value="${objProject.projectKey}" class="text" tabindex="1" id="ck_EnSName"/>
		        	<span>作为网站访问地址后缀，请牢记！</span>
		        </div>
		      </div>
		      <s:if test="objUsers.nCurrentLevel == 1"></s:if>
		      <div class="item">
				<span class="label">项目类型：</span>
		        <div class="fl">
		          <select name="lCustomerTypeID">
                       <s:if test="lstCustomerType != null && lstCustomerType.size > 0">
							<s:iterator value="lstCustomerType" id="objCustomerType">
								<option value="${objCustomerType.lId}"  <s:if test="%{lCustomerTypeID == #objCustomerType.lId}">selected="selected"</s:if>>${objCustomerType.strName}</option>   
							</s:iterator>	
                       </s:if>
		          </select>
		        </div>
		      </div>
		      <!-- 
		      	
		      
		      <div class="item">
				<span class="label">邮件列表对应的项目ID：</span>
		        <div class="fl">
		          <input type="text" name="objProject.strEmailListPID" value="${objProject.strEmailListPID}" class="text" tabindex="1" id="ck_SName"/>
		        </div>
		      </div>
		      
		      <div class="item">
				<span class="label">是否启用邮件列表：</span>
		        <div class="fl">
		        	<input type="radio" class="checkbox" name="objProject.nEmailListStatus" value="0" id="useQQList"/>
		        	<label class="pad" for="purpose1">启用&nbsp;</label>
		        	<input type="radio" class="checkbox" name="objProject.nEmailListStatus" value="1" id="notUseQQList"/>
		        	<label class="pad" for="purpose1">不启用</label>
		        </div>
		      </div>
		      
		      <div class="item">
				<span class="label">是否启用结果页：</span>
		        <div class="fl">
		        	<input type="radio" class="checkbox" name="objProject.nIsUseResultPage" value="1" id="useResultPage"/>
		        	<label class="pad" for="purpose1">启用&nbsp;</label>
		        	<input type="radio" class="checkbox" name="objProject.nIsUseResultPage" value="0" id="notUseResultPage"/>
		        	<label class="pad" for="purpose1">不启用</label>
		        </div>
		      </div>
		       -->
		      
		      <!-- 
		      <div class="item">
				<span class="label">商户号：</span>
		        <div class="fl">
		          <input type="text" name="objProject.strMerId" value="${objProject.strMerId}" class="text" tabindex="1" id="ck_SName"/>
		        </div>
		      </div>
		      
		      <div class="item">
				<span class="label">商户密钥：</span>
		        <div class="fl">
		          <input type="text" name="objProject.strKeyValue" value="${objProject.strKeyValue}" class="text" tabindex="1" id="ck_SName"/>
		        </div>
		      </div>
		      
		      <div class="item">
				<span class="label">回调地址：</span>
		        <div class="fl">
		          <input type="text" name="objProject.strCallBackURL" value="${objProject.strCallBackURL}" class="text" tabindex="1" id="ck_SName"/>
		        </div>
		      </div>
		      
		      <div class="">
					<span class="label">邮件内容：</span>
			        <div class="fl" style="border: 1px solid #f7fbfc;">
			          <textarea style="width:440px;height:200px; " name="objProject.strEmailContent">${objProject.strEmailContent}</textarea>
			        </div>
		       </div>
		       -->
		      
		      
		</form>
	</div>
	<div style="margin-top: 40px;margin-left:20px;text-align: center;position:relative;" id="oper_module">
		<img src="/commons/image/s10.gif" width="58" height="22" style="cursor: pointer;" onclick="submitForm();">
		&nbsp;&nbsp;
		<img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="history.back()">
	</div>
	
	<div style="margin-top: 40px;margin-left:20px;text-align: center;position:relative;" id="msg_module">
	</div>
</div>
</body>
</html>
