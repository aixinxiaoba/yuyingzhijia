<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet"  />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>

<script type="text/javascript">
function submitForm(){
	$('#projectForm').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				alert("保存成功！");
				location.href = "/mpf/project/projectList.do";
			}
			else
			{
				alert("保存失败");
			}
      	}
    );
}

</script>
</head>
<body>
<form name="projectForm" method="post" id="projectForm" action="projectAdd.do">
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="index_content">
	           <tr>
	             <td height="12"></td>
	           </tr>
	           <tr>
	             <td valign="middle" >
	             	<img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="history.back()">
	             </td>
	           </tr>
	            <tr>
	            <td id="errorInfo" style="font-size:12px;color:red;"><s:actionmessage /></td>
	           </tr>
	           <tr>
	             <td valign="middle" ><table width="100%" border="0" cellspacing="0" cellpadding="0" id="index_main_div1">
	               <tr>
	                 <td height="21" valign="top" background="/commons/image/index_main_div_titleBg.gif"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">基本信息</span></td>
	               </tr>
	               <tr>
	                 <td height="56" align="left" valign="top" bgcolor="#f7fbfc">
	                 	<s:if test="lstProFiledInfo != null && lstProFiledInfo.size > 0">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" id="selectTable">
								<s:iterator value="lstProFiledInfo" id="proFileInfo" status="status">
      								<s:if test="#proFileInfo.strPfidentity == 'SName'">
      									<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
      								</s:if>
      								<s:if test="#proFileInfo.strPfidentity == 'SNamePY'">
      									<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
      								</s:if>
      								
      								<s:if test="#proFileInfo.strPfidentity == 'IDE_Type'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'IDE'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'Sex'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'SBrithday'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'GuoJi'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'Nation'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'SDegree'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'SPIC'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'QQ'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'experience'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'target'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom1'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom2'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom3'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom4'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom5'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom6'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom7'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom8'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom9'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom10'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom11'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom12'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom13'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom14'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
						      		
						      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom15'">
						      			<tr>
					                         <td width="10%">${proFileInfo.strPfname}:</td>
					                         <td width="37%"><input type="text" name="objProject.strPname" value="${userGroup.name }" class="addCText"></td>
					                    </tr>
						      		</s:if>
      							</s:iterator>
		                     </table>                 	
	                 	</s:if>
	                </td>
	               </tr>
	             </table>				</td>
	           </tr>
	  <tr>
	             <td valign="middle" >&nbsp;</td>
	           </tr>
	         </table>
</form>
</body>
</html>
