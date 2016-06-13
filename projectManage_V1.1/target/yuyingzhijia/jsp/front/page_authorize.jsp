<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=utf-8" />
		<title>===商户申请===</title>
		<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="/commons/js/jquery.form.js"></script>
<script type="text/javascript">
	function submitForm(){
		$("#oper_module").hide();
		$("#msg_show").html("正在处理...");
		$('#usersForm').ajaxSubmit(function(msg){
				if (msg == "true")
				{
					$("#msg_show").html("申请成功！");
				}
				else
				{
					$("#msg_show").html(msg);
					$("#btn_return").show();
				}
	      	}
	    );
	}
	
	// 重新申请。
	function renewApplay()
	{
		$("#oper_module").show();
		$("#btn_return").hide();
		$("#msg_show").html("");
	}
</script>
	</head>

	<body>
		<div id="oper_module" align="center">
			<h1>
				欢迎加入我们
			</h1>
			<form action="/mpf/users/usersAdd.do" method="post" name="usersForm" id="usersForm">
				<table>
					<tr>
						<td>
							商户名称：
						</td>
						<td>
							<input type="text" name="objUsers.strName" id="strName">
						</td>
					</tr>
					<tr>
						<td>
							联系QQ：
						</td>
						<td>
							<input type="text" name="objUsers.strLoginName" value="">
						</td>
					</tr>
					<tr>
						<td>
							联系电话：
						</td>
						<td>
							<input type="text" name="objUsers.strPersonPhone" id="">
						</td>
					</tr>
					<tr>
						<td>
							想要申请的项目：
						</td>
						<td>
							<textarea rows="" cols="" name="objUsers.strRemarks"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<input type="button" onClick="submitForm();" value="提交申请"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div id="msg_show" align="center" style="margin: 20px;">
			
		</div>
		<div id="btn_return" style="display: none;" align="center">
			<input type="button" value="返回" onclick="renewApplay();"/>
		</div>
	</body>
</html>
