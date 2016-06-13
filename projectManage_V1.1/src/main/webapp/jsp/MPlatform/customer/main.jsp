<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统界面</title>

<link type="text/css" href="/commons/css/_main.css" rel="stylesheet" />
<link type="text/css" href="/commons/css/common.css" rel="stylesheet" />
<link type="text/css" href="/commons/css/general.css" rel="stylesheet" />
<link type="text/css" href="/commons/css/calendar.css" rel="stylesheet" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/popup.js" ></script>
<script type="text/javascript" src="/commons/js/popup4nextTDate.js" ></script>
<script type="text/javascript" src="/commons/js/popup4exExcel.js" ></script>
<script type="text/javascript" src="/commons/js/popup4Linkman.js" ></script>
<script type="text/javascript" src="/commons/js/cal.js" ></script>
<script type="text/javascript" src="/commons/js/popup4exExcel_sub.js" ></script>

<script type="text/javascript">
var isHide = false;

/**
 * 动态显示时间。
 */
function dynamicShowTime()
{
   var allDay = new Array( "星期日", "星期一",  "星期二", "星期三", "星期四", "星期五", "星期六");
   //获得显示时间的div
   var strTimeInfo = document.getElementById('showtime');
   var now=new Date();
   
   //替换div内容 
   strTimeInfo.innerHTML = "现在是" + now.getFullYear() + "年" + (now.getMonth() + 1) + "月" + now.getDate()  + "日&nbsp;&nbsp;" + allDay[now.getDay()] + "&nbsp;&nbsp;" + now.getHours() + "时" + now.getMinutes() + "分" + now.getSeconds() + "秒";
   //等待一秒钟后调用time方法，由于settimeout在time方法内，所以可以无限调用
   setTimeout(dynamicShowTime,1000);
}

function getNewVal(objname){
	var sel = $(window.frames[0].document).find(":select#" + objname);
	var queryString = {code:objname};

	$.ajax({
		type:"post",
		url:"getDictinaryDetails.action",
		data:queryString,
		dataType:"json",
		success: function(msg) {
			var str1 = "<option value=''>---</option>";
			if(msg.SDictionaryDetails.length > 0){
				for(var i = 0; i < msg.SDictionaryDetails.length; i++){
					str1 += "<option value='"+msg.SDictionaryDetails[i].value+"'>"+msg.SDictionaryDetails[i].value+"</option>";
				}
			}
			sel.html(str1);
		}
	});
}

function getNewVal4Linkman(objname){
	var sel = $(window.frames["right_window"].document.frames["linkmanFrame"].document).find(":select#" + objname);
	var queryString = {code:objname};

	$.ajax({
		type:"post",
		url:"getDictinaryDetails.action",
		data:queryString,
		dataType:"json",
		success: function(msg) {
			var str1 = "<option value=''>---</option>";
			if(msg.SDictionaryDetails.length > 0){
				for(var i = 0; i < msg.SDictionaryDetails.length; i++){
					str1 += "<option value='"+msg.SDictionaryDetails[i].value+"'>"+msg.SDictionaryDetails[i].value+"</option>";
				}
			}
			sel.html(str1);
		}
	});
}

$(function(){
	$(".subTreeTable td").bind("mouseover",function(){
		$(this).removeClass("mouseOutClass");
		$(this).addClass("mouseOverClass");
	});
	
	$(".subTreeTable td").bind("mouseout",function(){
		$(this).removeClass("mouseOverClass");
		$(this).addClass("mouseOutClass");
	});
	
	$(".center_control").bind("click",function(){
	
		if(isHide == false){
			$("#leftT").hide();
			isHide = true;
			$("#center_control_img").attr("src","/commons/image/index_center_control1.jpg");
		}else{
			$("#leftT").show();
			isHide = false;
			$("#center_control_img").attr("src","/commons/image/index_center_control.jpg");
		}
	});
	
	for(var i = 1; i <=4; i++){
		$(".left_treeControl" + i).attr("src","/commons/image/left_tree_close.gif");
		$(".left_treeControl" + i).bind("click",function(){
			var indexNum = $(this).attr("class").substring($(this).attr("class").length-1,$(this).attr("class").length);
			if($(this).attr("src").indexOf("open") != -1){
				closeAll();
				$(this).attr("src","/commons/image/left_tree_close.gif");
				$(".left_tree" + indexNum).show();
			}else{
				
				$(this).attr("src","/commons/image/left_tree_open.gif");
				$(".left_tree" + indexNum).hide();
			}
		})
	}
});

 $(function(){
	// 显示时间。
	dynamicShowTime();
	 
	closeAll();
	$(".left_treeControl" + 1).attr("src","/commons/image/left_tree_close.gif");
	$(".left_tree1").show();
		
});
function closeAll(){
	for(var i = 1; i <=4; i++){
		$(".left_treeControl" + i).attr("src","/commons/image/left_tree_open.gif");
		$(".left_tree" + i).hide();
	}
}
//判断选项以选择相应的函数
function exportExcelBySelect(){
	var o = document.getElementsByName("exportExcelOption");
	for(var i = 0; i < o.length; i++){
		
		if(o[i].checked){
			
			if(o[i].value == "excel_all"){
				
				exportAll("excel_all");
			}
			if(o[i].value == "excel_page"){
				
				exportPage("excel_page");
			}
			if(o[i].value == "excel_selected"){
				
				exportSelected("excel_selected");
			}
		}
	}
}

//判断选项以选择相应的函数.用于嵌套2层的iframe页面如联系人子页面

function exportExcelBySelect_sub(){
	var o = document.getElementsByName("exportExcelOption");
	for(var i = 0; i < o.length; i++){
		
		if(o[i].checked){
			
			if(o[i].value == "excel_all"){
				
				exportAll_sub("excel_all");
			}
			if(o[i].value == "excel_page"){
				
				exportPage_sub("excel_page");
			}
			if(o[i].value == "excel_selected"){
				
				exportSelected_sub("excel_selected");
			}
		}
	}
}
</script>

</head>

<body STYLE='OVERFLOW: HIDDEN; OVERFLOW-Y: HIDDEN' onload="">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="76" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3" class="topTd"><img src="/commons/image/logo.gif" width="130" height="64"></td>
			</tr>
			<tr>
				<td width="406" class="topLeft" height="30">
					<span style="color: #444444;font-size:13px;">欢迎您 ${objUsers.strName}</span>
					<span style="color: #0871ee;vertical-align:middle">&nbsp;&nbsp;技术支持（QQ:376289635）</span>
					<span><a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=bf25712c54ca049803ee847e1a47f45bd5cfed7f80d1e8b47e4bd9d84da4f632"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="PM官方交流群" title="PM官方交流群"></a></span>
				</td>
				<td width="17">
					<img src="/commons/image/topButLine.gif" width="36" height="30">
				</td>
				<td width="700" class="topRight"><img src="/commons/image/icon/gzt.gif" width="16" height="15" align="absmiddle">
					<a href="/jsp/MPlatform/customer/workdesk.jsp" target="right_window" style="color:white;font-size:13px;text-decoration: none">工作台</a>| <img src="/commons/image/icon/wdxx.gif" width="16" height="12"> 
					
					<a href="/jsp/MPlatform/myInformation/information_show.jsp" target="right_window"   style="color:white;font: 13px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;list-style:none;">我的消息(0)</a>
					
					| <img src="/commons/image/icon/wdzh.gif" width="16" height="16" align="absmiddle">
					 
					<a href="/jsp/MPlatform/myAccount/account_show.jsp" target="right_window"  style="color:white;font: 13px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;list-style:none;">我的账户</a>
					
					| <img src="/commons/image/icon/bz.gif" width="16" height="15" align="absmiddle"> 
					<!-- 帮助  -->
					<a href="/jsp/MPlatform/help.jsp" target="right_window"  style="color:white;font: 13px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;list-style:none;">帮助</a>
					| <img src="/commons/image/icon/gy.gif" width="16" height="16" align="absmiddle"> 
					<!-- 关于 -->
					<a href="/jsp/MPlatform/aboutUs.jsp" target="right_window"  style="color:white;font: 13px/24px arial, '宋体', sans-serif, 'Microsoft YaHei', tahoma;list-style:none;">关于</a>
					
					| <img src="/commons/image/icon/tc.gif" width="10" height="10"	align="absmiddle">
					<span style="cursor: pointer" onclick="window.location.href='/login/destroyCustomerLogin.do'">安全退出</span>  
					<span id="showtime" style="margin-left:10px;font-size:13px;"></span>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="6"></td>
	</tr>
	<tr>
		<td valign="top">
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0" id="left_tree_main">
			<tr>
				<td width="159" height="100%" valign="top" id="leftT">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="27" colspan="2" align="center" background="/commons/image/left_main_bg.gif"><span style="color: #004e7b; font-size: 14px; font-weight: bold;">操作菜单</span></td>
					</tr>
					<tr>
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="companyTitle">
							<tr  style="cursor: pointer;" class="left_treeControl1">
								<td width="80%" class="f_td"><img align="absmiddle"	src="/commons/image/left_tree1.gif" width="17" height="20"> 客户管理</td>
								<td width="20%" class="f_td"><img src="/commons/image/left_tree_open.gif" width="15" height="15" style="cursor: pointer;" class="left_treeControl1"></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr class="left_tree1">
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="subTreeTable" id="companyTable">
							<tr>
								<td>
									<img align="absmiddle" src="/commons/image/left_tree1_2.gif" width="16" height="17"> 
									<a href="/jsp/MPlatform/customer/allCustomerDown.jsp" class="nav" target="right_window">我的客户</a>
								</td>
							</tr>
							<tr>
								<td>
									<img align="absmiddle" src="/commons/image/left_tree1_2.gif" width="16" height="17"> 
									<a href="/mpf/customer/tuiGuangAddress.do" class="nav" target="right_window">我的推广地址</a>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					
					<tr style="cursor: pointer;" class="left_treeControl3">
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="companyTitle">
							<tr>
								<td width="80%" class="f_td"><img align="absmiddle"	src="/commons/image/left_tree1.gif" width="17" height="20"> 项目管理</td>
								<td width="20%" class="f_td"><img src="/commons/image/left_tree_open.gif" width="15" height="15" style="cursor: pointer;" class="left_treeControl3"></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr class="left_tree3">
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="subTreeTable" id="companyTable">
							<tr>
								<td>
									<img align="absmiddle" src="/commons/image/left_tree1_2.gif" width="16" height="17"> 
									<a href="/jsp/MPlatform/customer/customer_projectList.jsp" class="nav" target="right_window">已经加盟的项目</a>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					
					<tr style="cursor: pointer;" class="left_treeControl2">
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="companyTitle">
							<tr>
								<td width="80%" class="f_td"><img align="absmiddle"	src="/commons/image/left_tree1.gif" width="17" height="20"> 个人管理</td>
								<td width="20%" class="f_td"><img src="/commons/image/left_tree_open.gif" width="15" height="15" style="cursor: pointer;" class="left_treeControl2"></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr class="left_tree2">
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="subTreeTable" id="companyTable">
							<tr>
								<td>
									<img align="absmiddle" src="/commons/image/left_tree1_2.gif" width="16" height="17"> 
									<a href="/mpf/customer/customerDetial.do?lCustomerID=${objCustomer.lId}&nIsDownCustomer=1" class="nav" target="right_window">资料查看</a>
								</td>
							</tr>
							<tr>
								<td>
									<img align="absmiddle" src="/commons/image/left_tree1_2.gif" width="16" height="17"> 
									<a href="/mpf/customer/customerDetial.do?lCustomerID=${objCustomer.lId}&nType=2&nIsDownCustomer=1" class="nav" target="right_window">资料修改</a>
								</td>
							</tr>
							<tr>
								<td>
									<img align="absmiddle" src="/commons/image/left_tree1_2.gif" width="16" height="17"> 
									<a href="/jsp/MPlatform/customer/personManage/passwordModify.jsp" class="nav" target="right_window">密码修改</a>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					
					<tr style="cursor: pointer;" class="left_treeControl4">
							<td width="80%" class="f_td"><img align="absmiddle" src="/commons/image/left_tree5.gif" width="17" height="20"> 短网址制作 </td>
							<td width="20%" class="f_td"><img src="/commons/image/left_tree_open.gif" width="15" height="15"></td>
						</tr>
						<tr class="left_tree4">
							<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="subTreeTable">
								<tr>
									<td><img align="absmiddle" src="/commons/image/left_tree5_12.gif" width="16" height="16"><a href="/jsp/MPlatform/shortUrl/make.jsp" class="nav" target="right_window"> 马上开始 </a></td>
								</tr>
								
								<tr>
									<td><img align="absmiddle" src="/commons/image/left_tree5_12.gif" width="16" height="16"><a href="/jsp/MPlatform/shortUrl/ip_statistics.jsp" class="nav" target="right_window"> IP统计 </a></td>
								</tr>
								
								<tr>
									<td><img align="absmiddle" src="/commons/image/left_tree5_12.gif" width="16" height="16"><a href="/jsp/MPlatform/shortUrl/ip_return.jsp" class="nav" target="right_window"> 查看原网址 </a></td>
								</tr>
							</table>
							</td>
						</tr>
					
				</table>
				</td>
				<td width="8" class="center_control" valign="middle"><img src="/commons/image/index_center_control.jpg" id="center_control_img"></td>
				<td valign="top">
				<iframe src="/jsp/MPlatform/customer/workdesk.jsp" width="100%" height="100%" frameborder="0" name="right_window"></iframe>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<div id="popupContact"><a id="popupContactClose">x</a>
<h1>标题</h1>
<div id="dictionaryMsg"></div>
<p id="contactArea">这里是正文</p>
</div>
<div id="backgroundPopup"></div>

</body>
</html>
