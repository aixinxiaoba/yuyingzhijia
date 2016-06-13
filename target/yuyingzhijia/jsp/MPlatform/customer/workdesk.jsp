<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工作台</title>
<link type="text/css" href="/commons/css/_main.css" rel="stylesheet">
<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="/commons/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="/commons/js/highcharts/modules/exporting.js"></script>

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
           url:"/mpf/customer/loadRecentlyCustomer.do?lCustomerID=${objCustomer.lId}",
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
	
	loadNews();
});

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
        	   if (data != null && data.rows.length > 0)
        	   {
        		   var strHTML = "";
        		   
        		   for (var i = 0; i < data.rows.length; i++ )
        		   {
        			   strHTML += "<li title='" + data.rows[i].strContent + "'><a href='/mpf/news/newsRead.do?newsID=" + data.rows[i].lId + "'>" + data.rows[i].strTitle + "</li>";
        		   }
        		   $("#news_show").html(strHTML);
        	   }
           }
    });
}
</script>
</head>

<body STYLE='OVERFLOW:SCROLL;OVERFLOW-X:HIDDEN'>
<table width="97%" border="0" cellspacing="0" cellpadding="0" class="right_title">
				  <tr>
					<td valign="middle" class="right_top"><img src="/commons/image/gzt.gif" width="16" height="14" align="absmiddle"> 工作台</td>
				  </tr>
				</table>
				
	<table width="97%" border="0" cellspacing="0" cellpadding="0" class="right_content_table">
                  <tr>
                    <td width="70%" >
						<table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
						  <tr>
							<td colspan="2" class="content_title"><img src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>公司新闻</strong></td>
						  </tr>
						  <tr>
						    <td width="10%" height="46" align="center" class="content_main"><img src="/commons/image/index_main_div1.gif" width="36" height="36"></td>
					        <td width="90%" class="content_main">
								<ul id="news_show" style="list-style: decimal;line-height: 18px;letter-spacing: 1px;padding:10px;font-size:15px;">
									<li>暂无新闻</li>
								</ul>
							</td>
						  </tr>
						</table>
						<table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
						  <tr>
							<td colspan="2" class="content_title"><img src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>客户拜访</strong></td>
						  </tr>
						  <tr>
						    <td width="10%" height="70" align="center" class="content_main"><img src="/commons/image/index_main_div2.gif" width="36" height="36"></td>
					        <td width="90%" valign="top" class="content_main" style="padding-top:10px;" colspan="2">
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
							<td height="46" class="content_main"  style="padding-left:15px;">
								暂无
							</td>
						  </tr>
						</table>
						
						<table width="99%" border="0" cellspacing="0" cellpadding="0" class="content_table">
						  <tr>
							<td class="content_title" align="left"><img src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle"> <strong>系统通知</strong></td>
						  </tr>
						  <tr>
							<td height="46" class="content_main"  style="padding-left:15px;">暂无</td>
						  </tr>
						</table>
					</td>
                  </tr>
                </table>
</body>
</html>
