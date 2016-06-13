<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>${objProject.strPname}</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<META name=description
	content=${objProject.strPname}>
<META name=keywords content="网站自动化 项目自动化 全民建站">
<link rel="shortcut icon" href="/favicon.ico?version=3">
<link rel="icon" type="image/gif" href="animated_favicon1.gif">
<LINK rel="stylesheet" type="text/css" href="/commons/housekeeper/images/Css.css" />
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/index_css/index_123.css">
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/index_css/jquery.min.js">
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/jquery.kinMaxShow-1.0.min1.js ">
<SCRIPT type=text/javascript
	src="/commons/housekeeper/images/AutoPic.js"></SCRIPT>

<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui_user_defined.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/commons/easyui/jquery.easyui.min.js"></script>
<!--公用文件-->
<script type='text/javascript' src='http://js.adm.cnzz.net/js/abase.js'></script>

<style type="">
.datagrid-row{
	height:65px;
}
</style>


<script type="text/javascript">

/**
 * 加载公司新闻。
 */
function loadNews()
{
	$.ajax({
           url:"/mpf/news/loadNewsList.do?lProjectID=${objProject.lId}&menuKey=index",
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
        			   
        			   if (strTitle.length > 22)
        			   {
        				   strTitle = strTitle.substring(0, 21);
        			   }
        			   strHTML += "<li title='" + strTitle + "'><a style='color:orange;' href='/mpf/news/newsRead.do?newsID=" + data.rows[i].lId + "&projectKey=${param.projectKey}'>" + strTitle + "</li>";
        		   }
        	   }
        	   
        	   if (strHTML == "")
        	   {
        		   strHTML = "暂无新闻";
        	   }
        	   $("#news_show").html(strHTML);
           }
    });
    
    commonLoadNews("hangyedongtai");
    commonLoadNews("zhaopinzhuanqu");
    commonLoadNews("xuexichongdian");
    commonLoadNews("muyingchangshi");
    commonLoadCustomer("yuyingku");
}

function commonLoadNews(menuKey)
{
	$.ajax({
           url:"/mpf/news/loadNewsList.do?projectKey=${param.projectKey}&lProjectID=${param.lProjectID}&menuKey="+menuKey,
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
        			   var strSendDateShort = data.rows[i].strSendDateShort;
        			   if (strTitle.length > 21)
        			   {
        				   strTitle = strTitle.substring(0, 20);
        			   }
        			   strHTML += "<li style='width:70%;float:left;' title='" + strTitle + "'><a style='font-size:12px;color:blue;' href='/mpf/news/newsRead.do?newsID=" + data.rows[i].lId + "&projectKey=${param.projectKey}'>" + strTitle + "</li>";
        		   	   strHTML += "<li style='float:right;'><a style='color:black;cursor:default' href='javascript:void(0);'>["+strSendDateShort+"]</a></li>";
        		   }
        	   }
        	   
        	   if (strHTML == "")
        	   {
        		   strHTML = "暂无新闻";
        	   }
        	   $("#"+menuKey+"_show").html(strHTML);
           }
    });
}

function commonLoadCustomer(menuKey)
{
	$('#customerList').datagrid({
		// title: "客户列表",
		loadMsg:'加载数据...',
		fitColumns:true,
		pageList:[20,30,40,50,100],
		nowrap: true,
		striped: true,
		collapsible:true,
		url:'/mpf/customer/loadCustomers.do?projectKey=${param.projectKey}&menuKey=' + menuKey,
		sortName: 'title',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'title',
		singleSelect:true,
		frozenColumns:[[
		    {field:'strSname',title:"&nbsp;&nbsp;<span style='font-size:13px;color:ff3300;font-weight:bold;font-family:宋体'>育婴人才</span>",align:"left",width:fixWidth(1),
				formatter:function(value,rec){
		    		$("#strSname").text(rec.strSname);
		    		if (rec.strSex != "")
		    		{
		    			$("#strSex").text(rec.strSex);
		    		}
		    		if (rec.strSemail !=  "")
		    		{
		    			$("#strSemail").text(rec.strSemail);
		    		}
		    		var strHTML = $("#customerDetail").html();
					return strHTML ;				
				}
			}
					]]
	});
}

function fixWidth(percent)
{
    return (document.body.clientWidth ) * percent; //这里你可以自己做调整
}
</script>
</HEAD>
<BODY>
	<%@ include file="../common/head.jsp" %>
	<s:if test="objProject.projectKey=='yuyingzhijia'">
		<TABLE style="MARGIN-TOP: 23px" cellSpacing=0 cellPadding=0 width=992
		align=center border=0>
		<TBODY>
			<TR>
				<TD height=243 vAlign=top width=667><SCRIPT
						type=text/javascript>
					var swf_width = 658
					var swf_height = 268
					var files = '/commons/housekeeper/FlashPhotos/220140711195405.jpg|/commons/housekeeper/FlashPhotos/220120612145329.jpg|/commons/housekeeper/FlashPhotos/220120612145413.jpg|/commons/housekeeper/FlashPhotos/220120612145821.jpg';
					var links = '';
					var texts = '';
					document
							.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"  codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ swf_width +'" height="'+ swf_height +'">');
					document
							.write('<param name="movie" value=/commons/housekeeper/flash/bcastr3.swf><param name="quality" value="high">');
					document
							.write('<param name="menu" value="false"><param name=wmode value="opaque">');
					document
							.write('<param name="FlashVars" value="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'">');
					document
							.write('<embed src="/commons/housekeeper/flash/bcastr3.swf" wmode="opaque" FlashVars="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'& menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');
					document.write('</object>');
				</SCRIPT></TD>
				<TD vAlign=top>
					<TABLE
						style="BACKGROUND: url(/commons/housekeeper/images/a_top.jpg)"
						height=243 cellSpacing=0 cellPadding=0 width=325 align=center
						border=0>
						<TBODY>
							<TR>
								<TD style="FONT-SIZE: 14px; FONT-WEIGHT: bold; COLOR: #ffffff" 	height=32 width=260>&nbsp;&nbsp;最新动态</TD>
								<TD width=65 align=center><%--<A style="COLOR: #ffffff" href="javascript:void(0);">更多&gt;&gt;</A> --%></TD>
							</TR>
							<TR>
								<TD
									style="COLOR: #c49859; PADDING-TOP: 12px; PADDING-LEFT: 12px; PADDING-RIGHT: 12px"
									height=233 vAlign=top
									background=/commons/housekeeper/images/a_center.jpg
									colSpan=2>
									<DIV class=con1>
										<ul id="news_show" style="line-height: 23px;padding:3px;">
											<s:iterator value="lstNewestMessage" id="news">
												<li style='float:left;' title='<s:property value="strTitle"/>'>
													<a style='font-size:14px;color:006699;font-weight: bold;font-family:宋体' href="/front/index/newsRead.do?newsID=<s:property value='lId'/>"> 
														<s:property value="strFormatTitle"/>
													</a>
												</li>
												
												<li style='margin-left:220px;'><a style='color:black;cursor:default;font-size:12px;' href='javascript:void(0);'>[<s:property value="strSendDateShort"/>]</a></li>
											</s:iterator>
										</ul>
									</DIV>
								</TD>
							</TR>
							<TR>
								<TD height=3 colSpan=2><IMG
									src="/commons/housekeeper/images/a_bottom.jpg"></TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	</s:if>
	
	<TABLE style="MARGIN-TOP: 13px" cellSpacing=0 cellPadding=0 width=992
		align=center border=0>
		<TBODY>
			<s:iterator value="lstProjectMenu" id="projectMenu" status="status">
				<s:if test="#status.odd">
					<TR>
						<TD vAlign=top  style="width:70%;">
							<TABLE
								style="BACKGROUND: url(/commons/housekeeper/images/b_top.jpg)"
								cellSpacing=0 cellPadding=0 width=584 border=0>
								<TBODY>
								
									<TR>
										<TD
											style="FONT-SIZE: 14px; FONT-WEIGHT: bold; COLOR: #a66000; padding-left: 38px;"
											height=32 width=338><s:property value="strMenuName"/></TD>
										<TD width=100 align=center><A style="COLOR: #a66000" href="/front/index/menuShow.do?lProjectMenuID=<s:property value="lId"/>"
											class="font-style">更多&gt;&gt;</A></TD>
									</TR>
									
									
									<TR>
										<TD height=246 vAlign=top
											background=/commons/housekeeper/images/b_center.jpg
											colSpan=2>
											<DIV style="margin:10px;width:96%;">
											
												<ul style="line-height:25px;" id="hangyedongtai_show">
													<s:iterator value="lstNews" id="news" status="myStatus">
														<s:if test="#myStatus.index < 15">
															<li style='width:87%;float:left;' title='<s:property value="strTitle"/>'>
																<a style='font-size:14px;color:006699;font-weight: bold;font-family:宋体' href="/front/index/newsRead.do?newsID=<s:property value='lId'/>"> 
																	&nbsp;<s:property value="strLongTitleTwo"/>
																</a>
															</li>
															<li style=''><a style='color:black;cursor:default;font-size:12px;' href='javascript:void(0);'>[<s:property value="strSendDateShort"/>]</a></li>
														</s:if>
													</s:iterator>
												</ul>
											</DIV>
										</TD>
									</TR>
									<TR>
										<TD height=3 colSpan=2><IMG
											src="/commons/housekeeper/images/b_bottom.jpg"></TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
						
					</s:if>
					<s:else>
						<TD vAlign=top>
							<TABLE
								style="BACKGROUND: url(/commons/housekeeper/images/c_top.jpg)"
								cellSpacing=0 cellPadding=0 width=400 border=0>
								<TBODY>
									<TR>
										<TD
											style="FONT-SIZE: 14px; FONT-WEIGHT: bold; COLOR: #a66000; padding-left: 38px;"
											height=32><s:property value="strMenuName"/></TD>
										<TD style=" padding-left: 180px;" align=center><A style="COLOR: #a66000" href="/front/index/menuShow.do?lProjectMenuID=<s:property value="lId"/>">更多&gt;&gt;</A></TD>
									</TR>
									<TR>
										<TD height=246 vAlign=top background=/commons/housekeeper/images/c_center.jpg
											colSpan=2>
											<DIV style="margin:10px;width:96%;">
												<ul style="line-height:25px;" id="hangyedongtai_show">
													<s:iterator value="lstNews" id="news" status="myStatus">
														<s:if test="#myStatus.index < 15">
															<li style='width:80%;float:left;' title='<s:property value="strTitle"/>'>
																<a style='font-size:14px;color:006699;font-weight: bold;font-family:宋体' href="/front/index/newsRead.do?newsID=<s:property value='lId'/>"> 
																	&nbsp;<s:property value="strLongTitle"/>
																</a>
															</li>
															<li style=';'><a style='color:black;cursor:default;font-size:12px;' href='javascript:void(0);'>[<s:property value="strSendDateShort"/>]</a></li>
														</s:if>
													</s:iterator>
												</ul>
											</DIV>
										</TD>
									</TR>
									<TR>
										<TD height=3 colSpan=2><IMG
											src="/commons/housekeeper/images/c_bottom.jpg"></TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>
				</s:else>
			</s:iterator>
		</TBODY>
	</TABLE>
	<!-- 
	<TABLE
		style="BACKGROUND: url(/commons/housekeeper/images/d_top.jpg); MARGIN-TOP: 14px"
		height=215 cellSpacing=0 cellPadding=0 width=992 align=center border=0>
		<TBODY>
			<TR>
				<TD
					style="FONT-SIZE: 14px; FONT-WEIGHT: bold; COLOR: #fa2c2c; padding-left: 38px;"
					height=32 width=827>育婴师专区</TD>
				<TD width=65 align=center><A style="COLOR: #fa2c2c"
					href="/jsp/templateOne/front/customerList.jsp?projectKey=${param.projectKey}&projectMenuKey=yuyingku">更多&gt;&gt;</A></TD>
			</TR>
			<TR>
				<TD height=190
					background=/commons/housekeeper/images/d_center.jpg
					colSpan=2 >
					<table id="customerList"></table>
				</TD>
			</TR>
			<TR>
				<TD height=3 colSpan=2><IMG
					src="/commons/housekeeper/images/d_bottom.jpg"></TD>
			</TR>
		</TBODY>
	</TABLE>
	 -->
	
	<!-- 
	<TABLE
		style="BORDER-TOP: #d6d6d6 1px solid; BORDER-RIGHT: #d6d6d6 1px solid; BACKGROUND: url(/commons/housekeeper/images/linkbg.jpg); BORDER-BOTTOM: #d6d6d6 1px solid; BORDER-LEFT: #d6d6d6 1px solid; MARGIN-TOP: 23px"
		cellSpacing=0 cellPadding=0 width=992 align=center border=0>
		<TBODY>
			<TR>
				<TD style="FONT-SIZE: 14px; FONT-WEIGHT: bold; COLOR: #a0a0a0"
					height=30 align=center>友情链接</TD>
				<TD>
				<A href="http://www.yuyingshichina.com/" target=_blank>华夏育婴师网</A>&nbsp;&nbsp;
					<A href="http://www.gongxinyuesao.com/" target=_blank>育儿嫂</A>&nbsp;&nbsp;
					<A href="http://www.shanghaiyuesao.org/" target=_blank>育儿嫂</A>&nbsp;&nbsp;
					<A href="http://www.jiazhengpeixun.com/" target=_blank>月嫂培训班</A>&nbsp;&nbsp;
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	 -->
	<TABLE cellSpacing=0 cellPadding=0 width=992 align=center border=0>
		<TBODY>
			<TR>
				<TD height=80>
					<TABLE cellSpacing=0 cellPadding=0 width=992 border=0>
						<TBODY>
							<TR>
								<TD>
									<P>
										Copyright <A href="www.${param.projectKey}.cn"><FONT
											color=#800080>www.${param.projectKey}.cn</FONT></A>&nbsp;All Rights
										Reserved 版权所有
									</P>
								</TD>
								<TD>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254920361'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1254920361%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
</td>

								<!-- 
								<td><a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=bf25712c54ca049803ee847e1a47f45bd5cfed7f80d1e8b47e4bd9d84da4f632"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="PM官方交流群" title="PM官方交流群"></a></td>
								 -->
								
								<TD width=290 align=right>${objProject.strPname}</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	
	<div id="customerDetail" style="display: none;">
				<ul style="line-height: 20px;float:left;margin-right:10px;font-size:14px;color:black;">
					<li style="float:left;width:78px;height:78px;padding:2px;margin-right:10px;"><img src="/commons/housekeeper/images/moren.jpg" style="width:78px;height:78px;"/></li>
					<li style="padding-top:10px;">姓名：<span id="strSname">--</span></li>
					<li style="">薪资要求：<span id="strSalary">--</span></li>
					<li style="float:left;vertical-align:middle;float:left;">性别：<span id="strSex">--</span></li>
				</ul>
				<ul style="line-height: 20px;float:left;margin-right:10px;;color:black;">
					<li style="padding-top:10px;">年龄：<span id="strAge">--</span></li>
					<li style="">工作经验：<span id="strExperence">--</span></li>
					<li style="float:left;vertical-align:middle;float:left;">期望工作地区：<span id="strArea">--</span></li>
				</ul>
				<ul style="line-height: 20px;float:left;margin-right:10px;font-size:14px;color:black;">
					<li style="padding-top:10px;">育婴师资格证：<span id="strZiGeZheng">--</span></li>
					<li style="">育婴评价：<span id="strPingJia">--</span></li>
					<li style="float:left;vertical-align:middle;float:left;">学历：<span id="strExperence">--</span></li>
				</ul>
				<ul style="line-height: 20px;float:left;margin-right:10px;font-size:14px;color:black;">
					<li style="padding-top:10px;">联系电话：<span id="strPhoneNum">--</span></li>
					<li style="">QQ：<span id="stQQ">--</span></li>
					<li style="float:left;vertical-align:middle;float:left;">邮箱：<span id="strSemail">--</span></li>
				</ul>
				<ul style="line-height: 20px;;color:black;">
					<li style="padding-top:10px;">是否在岗：<span id="isPosition">--</span></li>
				</ul>
		</div>

<!-- 广告位：化妆品-->
<script>CNZZ_SLOT_RENDER('370847');</script>
</BODY>
</HTML>

