<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title>jQuery funkyUI plugin</title>
	<script type="text/javascript">
		window.onload = function ()
		{
			document.myform.submit();
		}
		
		// window.parent.location.href = "http://www.baidu.com"
	</script>
	</head>
	<body>
	<div style="font-size:20px;margin:40px;">
		正在加载，请稍后...
	</div>
	<form name="myform" id="myform" action="http://list.qq.com/cgi-bin/qf_compose_send" method="post">
			<input name="t" type="hidden" value="qf_booked_feedback" />
			<input name="id" type="hidden" value="${param.id}" />
			<input name="to" class="rsstxt" id="to" type="hidden" value="${param.to}" />
		</form>
</body>
	</html>