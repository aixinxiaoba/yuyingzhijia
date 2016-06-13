<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link type="text/css" href="/commons/css/_main.css" rel="stylesheet" />
<script type="text/javascript">
var isHide = false;

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
	closeAll();
	// $(".left_treeControl" + 1).attr("src","/commons/image/left_tree_close.gif");
	// $(".left_tree1").show();
	// $("#right_window").load("/jsp/MPlatform/workdesk.jsp");
});
function closeAll(){
	for(var i = 1; i <=4; i++){
		$(".left_treeControl" + i).attr("src","/commons/image/left_tree_open.gif");
		$(".left_tree" + i).hide();
	}
}

</script>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="76" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3" class="topTd"><img src="/commons/image/logo.gif" width="130" height="64"></td>
			</tr>
			<tr>
				<td width="406" class="topLeft" height="30">
					<span style="color: #444444"> 欢迎您 ${objUsers.strName}</span>
					<span style="color: #0871ee">支持热线(010-00000000)</span>
					<span style="color: #ed4101">使用帮助 </span> <span style="color: #0871ee">联系我们</span>
				</td>
				<td width="17">
					<img src="/commons/image/topButLine.gif" width="36" height="30">
				</td>
				<td width="568" class="topRight"><img src="/commons/image/icon/gzt.gif" width="16" height="15" align="absmiddle"> 工作台 | <img src="/commons/image/icon/wdxx.gif" width="16" height="12"> 我的消息(0) | <img src="/commons/image/icon/wdzh.gif" width="16" height="16" align="absmiddle"> 我的账户 | <img src="/commons/image/icon/tc.gif" width="10" height="10"	align="absmiddle">
					<span style="cursor: pointer" onclick="javascript:window.location.href='logout.action'">退出</span> | <img src="/commons/image/icon/bz.gif" width="16" height="15" align="absmiddle"> 帮助 | <img src="/commons/image/icon/gy.gif" width="16" height="16" align="absmiddle"> 关于
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
							<tr>
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
									<a href="/mpf/customer/projectList.do" class="nav" target="right_window">客户管理</a>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<!-- <tr>
						<td class="f_td"><img align="absmiddle"
							src="/commons/image/left_tree2.gif" width="17" height="20"> 销售管理</td>
						<td class="f_td"><img src="/commons/image/left_tree_open.gif"
							width="15" height="15" style="cursor: pointer;"
							class="left_treeControl2"></td>
					</tr>
					<tr class="left_tree2">
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="subTreeTable">
							<tr>
								<td><img align="absmiddle" src="/commons/image/left_tree2_1.gif"
									width="16" height="16"><a
									href="load4ProductTypeMain.action?MType=touch_module"
									class="nav" target="right_window"> 商品类别</a></td>
							</tr>
							<tr>
								<td><img align="absmiddle" src="/commons/image/left_tree2_2.gif"
									width="16" height="17"><a
									href="load4ProductMain.action?MType=touch_module" class="nav"
									target="right_window"> 商品资料</a></td>
							</tr>
							<tr>
								<td><img align="absmiddle" src="/commons/image/left_tree2_3.gif"
									width="15" height="17"><a
									href="load4OrderMain.action?MType=touch_module" class="nav"
									target="right_window"> 销售单</a></td>
							</tr>
							<tr>
								<td><img align="absmiddle" src="/commons/image/left_tree2_4.gif"
									width="16" height="16"> 开销售单</td>
							</tr>
							<tr>
								<td><img src="/commons/image/left_tree2_5.gif" width="17"
									height="17" align="absmiddle" style="margin-right: 5px;">
								<a href="load4NeedGatherMain.action" class="nav"
									target="right_window">应收款项</a></td>
							</tr>
							<tr>
								<td><img src="/commons/image/left_tree2_6.gif" align="absmiddle"
									style="margin-right: 5px;"> <a
									href="load4GatherMain.action" class="nav" target="right_window">销售收款</a></td>

							</tr>
						</table>
						</td>
					</tr> -->
					<tr>
						<td class="f_td"><img align="absmiddle" src="/commons/image/left_tree5.gif"> 系统设置</td>
						<td class="f_td"><img src="/commons/image/left_tree_open.gif"
							width="15" height="15" style="cursor: pointer;"
							class="left_treeControl3"></td>
					</tr>
					<tr class="left_tree3">
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="subTreeTable">
							<tr>
								<td><img align="absmiddle" src="/commons/image/left_tree5_1.gif" width="16" height="16"><a href="/mpf/project/projectList.do" class="nav"> 项目管理 </a></td>
							</tr>
							<!-- 
							<tr>
								<td><img align="absmiddle" src="/commons/image/left_tree5_2.gif" width="16" height="17"><a href="load4UserMain.action" class="nav" target="right_window"> 人事管理</a></td>
							</tr>
							<tr>
								<td><img align="absmiddle" src="/commons/image/left_tree5_3.gif" width="15" height="17"><a href="load4RoleMain.action" class="nav" target="right_window"> 操作权限组</a></td>
							</tr>
							<tr>
								<td><img align="absmiddle" src="/commons/image/left_tree5_4.gif" width="16" height="16"><a href="load4AccessGroupMain.action" class="nav" target="right_window"> 查询权限组</a></td>
							</tr>
							 -->
						</table>
						</td>
					</tr>
					<!-- 
					<tr>
						<td class="f_td"><img align="absmiddle" src="/commons/image/left_tree6.gif"> 用户贡献</td>
						<td class="f_td"><img src="/commons/image/left_tree_open.gif" width="15" height="15" style="cursor: pointer;" class="left_treeControl4"></td>
					</tr>
					<tr class="left_tree4">
						<td colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="subTreeTable">
							<tr>
								<td><img align="absmiddle" src="/commons/image/left_tree6.gif"
									width="16" height="16"><a
									href="load4CompanyChartMain.action" class="nav"
									target="right_window"> 客户贡献图表</a></td>
							</tr>
						</table>
						</td>
					</tr>
					 -->
				</table>
				</td>
				<td width="8" class="center_control" valign="middle"><img src="/commons/image/index_center_control.jpg" id="center_control_img"></td>
				<td valign="top">
					<!-- <iframe src="/jsp/MPlatform/workdesk.jsp" width="100%" height="100%" frameborder="0" name="right_window"></iframe> -->
					<div id="right_window"></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>