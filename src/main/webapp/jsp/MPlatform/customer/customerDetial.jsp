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
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">基本信息</span></div>
	<div style="text-align:center;border:0px solid black;margin:20px;">
		<s:if test="lstProFiledInfo != null && lstProFiledInfo.size > 0">
			<table width="100%" cellspacing="9">
				<s:iterator value="lstProFiledInfo" id="proFileInfo" status="status">
					<s:if test="#proFileInfo.strPfidentity == 'SName'">
					   	   <tr class="select_content_bg2" style="line-height: 20px;">
		                       <td width="50%" align="right" >${proFileInfo.strPfname}:</td>
		                       <td width="50%">${objCustomer.strSname}</td>
		                  </tr>
					</s:if>
					<s:if test="#proFileInfo.strPfidentity == 'SNamePY'">
							<tr class="select_content_bg2" style="margin-top:10px;">
		                       <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                       <td width="50%">${objCustomer.strSnamePY}</td>
		                     </tr>
					</s:if>
									
					<s:if test="#proFileInfo.strPfidentity == 'IDE_Type'">
		     				<tr class="select_content_bg2">
		                       <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                       <td width="50%">${objCustomer.nIdeType}</td>
		                  </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'IDE'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strIde}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'Sex'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strSex}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'SBrithday'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strSbrithday}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'GuoJi'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strGuoJi}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'Nation'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strNation}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'SDegree'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strSdegree}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'SPIC'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strSPic}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'QQ'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strQQ}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'experience'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strInterExperience}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'target'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strTarget}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom1'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver1}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom2'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver2}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom3'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver3}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom4'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver4}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom5'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver5}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom6'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver6}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom7'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver7}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom8'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver8}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom9'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver9}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom10'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver10}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom11'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver11}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom12'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver12}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom13'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver13}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom14'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver14}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom15'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strReserver15}</td>
		                   </tr>
		      		</s:if>
		      		
		      		<s:if test="#proFileInfo.strPfidentity == 'phone'">
		      			<tr class="select_content_bg2">
		                        <td width="50%" align="right">${proFileInfo.strPfname}:</td>
		                        <td width="50%">${objCustomer.strSPhone}</td>
		                   </tr>
		      		</s:if>
				</s:iterator>
		    </table>                 	
		</s:if>
	</div>
	<div style="margin-top: 40px;margin-left:20px;text-align: center;"><img src="/commons/image/s8.gif" width="59" height="22" style="cursor: pointer;" onclick="history.back()"></div>
</div>
</body>
</html>
