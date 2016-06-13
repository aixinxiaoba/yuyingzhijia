<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>Error Page</title>
	<script language="javascript">
		function showDetail()
		{
			var elm = document.getElementById('detail_system_error_msg');
			if(elm.style.display == '') {
				elm.style.display = 'none';
			}else {
				elm.style.display = '';
			}
		}
	</script>
</head>

<body>

<div id="content">

	示例远程图片,请自行下载<br />
	<img alt="system internal error" src="<c:url value="/styles/images/error.gif"/>" />
	<h3>
		对不起,发生系统内部错误,不能处理你的请求<br />
	</h3>
	<br>
	<div style="display:none;">
		 
	</div>
	<button onclick="history.back();">返回</button>
	<br>

	<p><a href="#" onclick="showDetail();">点击这里查看具体错误消息</a>,报告以下错误消息给系统管理员,可以更加快速的解决问题</p>

</div>
</body>
</html>