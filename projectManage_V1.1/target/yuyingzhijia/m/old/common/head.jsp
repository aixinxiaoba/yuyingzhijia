<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<LINK 	href="/commons/jquery_m/demo.css" rel="stylesheet" type="text/css">
<LINK href="/commons/jquery_m/jquery.mmenu.all.css" 	rel="stylesheet" type="text/css">
<SCRIPT src="/commons/jquery_m/jquery.mmenu.min.all.js" type="text/javascript"></SCRIPT>

<SCRIPT type="text/javascript">
	$(function() {
		// 设置菜单。
		loadMenu();
	});
	
	function loadMenu()
	{
		// 设置菜单。
		$.ajax({
			url : "/front/m/loadMenu.do",
			type : "post",
			data : {
			},
			dataType : "text",
			async : true,
			success:function(data)
			{
				$("#loadMenu").html(data);
				// 设置菜单。
				$("#menu").mmenu({
					extensions : [ "pageshadow" ],
					counters : true,
					searchfield : true,
					dividers : {
						add : true,
						addTo : "[id*='contacts-']",
						fixed : true
					},
					sectionIndexer : {
						add : true,
						addTo : "[id*='contacts-']"
					},
					header : {
						add : true,
						update : true,
						title : "育婴之家"
					}
				}).on('click', 'a[href^="#/"]', function() {
					alert("Thank you for clicking, but that's a demo link.");
					return false;
				});
			}
		});
	}
</SCRIPT>
<DIV id="page">
	<DIV class="header">
		<A class="header" style="padding:0px 0px;left:0px;" href="#menu"></A>
		<span id="curTitle"></span>
	</DIV>
</DIV>

<!-- 菜单展示start -->
<NAV id="menu">
	<UL id="loadMenu">
	</UL>
</NAV>
<!-- 菜单展示end -->


