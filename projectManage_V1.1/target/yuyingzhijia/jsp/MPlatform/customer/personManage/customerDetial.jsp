<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet"  />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:1px solid #73c0e0;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">基本信息</span></div>
	<div id="formbox" class="form">
			<input type="hidden" value="${objCustomer.lId}" name="objCustomer.lId"/>
			<s:if test="lstProFiledInfo != null && lstProFiledInfo.size > 0">
	      	  <s:iterator value="lstProFiledInfo" id="proFileInfo" status="status">
	      		<!-- 字段默认与必填标识 -->
				<input type="hidden" value="<s:property value="#proFileInfo.nPfnature"/>_<s:property value="#proFileInfo.strPfidentity"/>" class="profileInfo"/>
	      		<s:if test="#proFileInfo.strPfidentity == 'SName'">
	      			<div class="item"><span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
				        <div class="fl2">
				          ${objCustomer.strSname}
				        </div>
				      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'SNamePY'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strSnamePY}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'IDE_Type'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.nIdeType}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'IDE'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strIde}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'Sex'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strSex}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'SBrithday'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strSbrithday}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'GuoJi'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strGuoJi}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'Nation'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strNation}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'SDegree'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strSdegree}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'SPIC'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strSPic}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'QQ'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strQQ}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'experience'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strInterExperience}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'target'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strTarget}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom1'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver1}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom2'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver2}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom3'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver3}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom4'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver4}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom5'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver5}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom6'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver6}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom7'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver7}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom8'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver8}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom9'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver9}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom10'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver10}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom11'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver11}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom12'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver12}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom13'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver13}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom14'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver14}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'BMCustom15'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strReserver15}
			        </div>
			      </div>
	      		</s:if>
	      		
	      		<s:if test="#proFileInfo.strPfidentity == 'phone'">
	      			<div class="item"> <span class="label" id="span_${proFileInfo.strPfidentity}">${proFileInfo.strPfname}：</span>
			        <div class="fl2">
			          ${objCustomer.strSPhone}
			        </div>
			      </div>
	      		</s:if>
	      	</s:iterator>
	      </s:if>
	</div>
</div>
</body>
</html>
