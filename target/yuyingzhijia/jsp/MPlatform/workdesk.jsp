<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工作台</title>
<link type="text/css" href="/commons/css/_main.css" rel="stylesheet">

<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="/commons/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="/commons/js/highcharts/modules/exporting.js"></script>

<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">

////////////////////////heighcharts start//////////////////
var chart;
$(document).ready(function() {
	var options = {
	    chart: {
			renderTo: 'container',
			defaultSeriesType: 'line'
		},
		title: {
			text: '一周平均注册人数变化折线图（注册人数/日）'
		},
		subtitle: {
			text: '' // Source: WorldClimate.com
		},
		xAxis: {
			categories: []
		},
		yAxis: {
			title: {
				text: '人数 '
			}
		},
		tooltip: {
			enabled: false,
			formatter: function() {
				return '<b>'+ this.series.name +'</b><br/>'+
					this.x +': '+ this.y +'°C';
			}
		},
		plotOptions: {
			line: {
				dataLabels: {
					enabled: true
				},
				enableMouseTracking: false
			}
		},
		series: [],
		credits: { enabled: false }
	};
	$.ajax({
           url:"/mpf/customer/loadRecentlyCustomer.do?lUsersID=${objUsers.lId}",
           type:"post",
           data:{},
           dataType:"json",
           async: false,
           success:function(data)
           {	
        	   if (data != null && data.lstChartData.length > 0)
        	   {
        		   var series = {
		                data: []
		            };
        		   series.name = "日平均变化折线图";
        		   for (var i = 0; i < data.lstChartData.length; i++)
        		   {
        			   var objChartData = data.lstChartData[i];
        			   
        			   options.xAxis.categories.push(objChartData.strXAxis);
        			   series.data.push(parseInt(objChartData.strFlexData));
        			   
        			   if (i == (data.lstChartData.length - 1))
        			   {
        			       $("#newAddCustomer").text(parseInt(objChartData.strFlexData));
        			   }
        		   }
        		   options.series.push(series);
        	   }
           }
    });
	
	chart = new Highcharts.Chart(options);
	// 新闻
	// loadNews();
});

/**
 * 加载公司新闻。
 */
function loadNews()
{
	$.ajax({
           url:"/mpf/news/loadNewsList.do",
           type:"post",
           data:{},
           dataType:"json",
           async: false,
           success:function(data)
           {
        	   var strHTML = "";
        	   if (data != null && data.rows.length > 0)
        	   {
        		   for (var i = 0; i < data.rows.length; i++ )
        		   {
        			   var strTitle = data.rows[i].strTitle;
        			   
        			   if (strTitle.length > 50)
        			   {
        				   strTitle = strTitle.substring(0, 30);
        			   }
        			   strHTML += "<li title='" + strTitle + "'><a href='/mpf/news/newsRead.do?newsID=" + data.rows[i].lId + "'>" + data.rows[i].strTitle + "</li>";
        		   }
        	   }
        	   
        	   if (strHTML == "")
        	   {
        		   strHTML = "暂无新闻";
        	   }
        	   $("#news_show").html(strHTML);
           }
    });
}

//easy UI start
/*
 * $(function(){
	$('#customerList').datagrid({
		title: "",
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[15,20,30,40,50,100],
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'/mpf/news/loadNewsList.do?lProjectID=-1&lProjectMenuID=-1',
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strTitle',title:"&nbsp;&nbsp;新闻标题",align:"left",width:fixWidth(0.1242),
				formatter:function(value,rec)
				{
		    		var strHTML = "<input type='checkbox' class='class_customer' value='" + rec.lId + "'>&nbsp;&nbsp;";
		    		
					return rec.strTitle;	
				}
			}
		    ]],
		columns:[[
			{field:'strProjectMenuName',title:'新闻类型',width:fixWidth(0.1242),align:"center"},
			{field:'strSendDate',title:'发布时间',width:fixWidth(0.1242),align:"center"},
			{field:'null',title:'操作',width:fixWidth(0.1242),align:"center",
				formatter:function(value,rec)
				{
					var strURL = "/mpf/news/newsRead.do?newsID=" + rec.lId;
		    		var strHTML = "<a href='" + strURL + "' style='text-decoration:none;'>查看</a>";
		    		
		    		return strHTML;
				}
			}
		]],
		pagination:true,
		rownumbers:true
	});
});

function fixWidth(percent)
{
    return (document.body.clientWidth ) * percent; //这里你可以自己做调整
}
 * */
</script>
</head>

<body STYLE='OVERFLOW:SCROLL;OVERFLOW-X:HIDDEN'>
<div id="index_main_div1" style="margin-top:0px;margin-left:3px;min-height:100px;">
	<div class="right_top" style="padding: 6px;"><img src="/commons/image/gzt.gif" width="16" height="14" align="absmiddle">&nbsp;工作台</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="right_content_table">
                <tr>
                  <td width="70%" >
                  <table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
					  <tr>
						<td colspan="2" class="content_title"><img src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>公司新闻</strong></td>
					  </tr>
					  <tr>
				        <td width="100%" class="content_main">
				        	<div style="margin: 10px;">
				        		暂无新闻！
				        	</div>
							<!-- 
							<table id="customerList"></table>
							 -->
						</td>
					  </tr>
					</table>
                 <table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
				  <tr>
					<td colspan="2" class="content_title"><img src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>客户拜访</strong></td>
				  </tr>
				  <tr>
				    <td width="10%" height="70" align="center" class="content_main"><img src="/commons/image/index_main_div2.gif" width="36" height="36"></td>
			        <td width="90%" valign="top" class="content_main" style="padding-top:10px;">
						<ul>
							<li style="list-style: none;"><span style="font-size:20px;font-weight: bold">今日新增客户:<span style="color:#FF0000;font-size: 25px;" id="newAddCustomer">0</span>个</span></li>
						</ul>
					</td>
				  </tr>
				</table>
				<table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
				  <tr>
					<td colspan="2" class="content_title"><img src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>图型走势</strong></td>
				  </tr>
				  <tr>
			        <td width="90%" class="content_main" colspan="2">
			        	<div id="container" style="width: 800px; height: 400px; margin: 0 auto;color:#424446;"></div>
			        </td>
				  </tr>
				</table>
				<table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
				  <tr>
					<td class="content_title"><img src="image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>销售单审核</strong></td>
				  </tr>
				  <tr>
				    <td height="46" class="content_main">&nbsp;</td>
			      </tr>
				</table>
				<table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
				  <tr>
					<td class="content_title"><img src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>收款单审核</strong></td>
				  </tr>
				  <tr>
				    <td height="46" class="content_main">&nbsp;</td>
			      </tr>
				</table>
				</td>
                  <td width="1%">&nbsp;</td>
                  <td width="29%" align="center" valign="top">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
				  <tr>
					<td class="content_title" align="left"><img src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>公司公告</strong></td>
				  </tr>
				  <tr>
					<td height="46" class="content_main" style="padding-left:15px;"> 暂无公告</td>
				  </tr>
				</table>
				
				<table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
				  <tr>
					<td class="content_title" align="left"><img src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>系统通知</strong></td>
				  </tr>
				  <tr>
					<td height="46" class="content_main">暂无通知！</td>
				  </tr>
				</table>
			</td>
                </tr>
              </table>
</div>
</body>
</html>
