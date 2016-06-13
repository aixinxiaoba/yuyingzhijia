<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0034)http://www.yuyingzhijia.cn/index.asp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>月嫂，月嫂公司，月嫂培训，正规月嫂公司，育儿嫂，育婴师</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<META name=description
	content=育婴之家庭服务为您提供专业月嫂，月嫂公司，月嫂，育儿嫂，育婴师。>
<META name=keywords content=月嫂，月嫂公司，月嫂，月嫂公司，正规月嫂公司，育儿嫂，育婴师>
<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/index_css/index_123.css">
<LINK rel="stylesheet" type="text/css" href="/commons/housekeeper/images/Css.css" />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js"></script>

<!-- 

<LINK rel=stylesheet type=text/css
	href="/commons/housekeeper/jquery.kinMaxShow-1.0.min1.js ">
	
	
<link type="text/css" rel="stylesheet" href="/commons/easyui/easyui_user_defined.css" />
<link type="text/css" rel="stylesheet" href="/commons/easyui/icon.css" />

   <link href="/commons/css/site.css" rel="stylesheet" type="text/css" />
		<link href="/commons/css/swfupload/default.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/commons/js/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="/commons/js/swfupload/swfupload.queue.js"></script>
		<script type="text/javascript" src="/commons/js/swfupload/fileprogress.js"></script>
		<script type="text/javascript" src="/commons/js/swfupload/handlers.js"></script>
        <script type="text/javascript" src="/commons/js/jquery.form.js"></script>
	
 -->
<style type="">
.datagrid-row{
	height:65px;
}
</style>
<script type="text/javascript">
$(function(){
	});
/**
 * 文件上传操作。
 */
function initSWFUpload()
{
	var settings = {
			flash_url : "/commons/js/swfupload/swfupload.swf",
			upload_url: "/front/web/photoUpload.do",
			// post_params: {"paramOne" : "valueOne"}, // 参数
			file_size_limit : "100 MB", // 大小限制
			file_types : "*.jpg;*.gif;*.png", // 类型
			file_types_description : "文件类型", // 上传类型描述
			file_upload_limit : 100,
			file_queue_limit : 10,
			custom_settings : {
				progressTarget : "fsUploadProgress",
				cancelButtonId : "btnCancel", // 设置取消上传按钮。
				projectName : ""
			},
			debug: false,
			button_cursor: SWFUpload.CURSOR.HAND, // 鼠标移上的状态。
			// Button settings
			button_image_url: "/commons/images/upload.png",
			button_width: "211",
			button_height: "48",
			button_placeholder_id: "spanButtonPlaceHolder", // 该必要参数指定了swfupload.swf将要替换的页面内的DOM元素的ID值 当对应的DOM元素被替换为SWF元素时，SWF的容器会被添加一个名称为"swfupload"的样式选择器供CSS自定义使用。 
		    // button_text: '<span class="theFont"></span>',
			// button_text_style: ".theFont {width:212;height:46xp; font-size: 16;}", 
			button_text_left_padding: 3, // 设置Flash Button上文字距离左部的距离，可以使用负值。
			button_text_top_padding: 2,// 设置Flash Button上文字距离顶部的距离，可以使用负值。
			// The event handler functions are defined in handlers.js
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete, // 选择完文件后进行的操作。或者是未选择文件点取消的操作。
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress, //uploadProgress(file object, bytes complete, total bytes)提供三个参数分别访问上传文件对象、已上传的字节数，总共的字节数
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess, // 当文件上传的处理已经完成（这里的完成只是指向目标处理程序发送了Files信息，只管发，不管是否成功接收），并且服务端返回了200的HTTP状态时，触发此事件。
			upload_complete_handler : uploadComplete,
			queue_complete_handler : queueComplete	// Queue plugin event
		};
		swfu = new SWFUpload(settings);
}
</script>
</HEAD>
<BODY>
	<%@ include file="../common/head.jsp" %>
	<TABLE style="MARGIN-TOP: 23px;" cellSpacing=0 cellPadding=0 width=992
			align=center border=0>
			<TBODY>
				<TR>
					<TD vAlign=top>
						<TABLE cellSpacing=0 cellPadding=0  bgColor=#ffffff
							border=0>
							<TBODY>
								<TR>
									<TD style="BACKGROUND: url(/commons/housekeeper/images/d_top.jpg);MARGIN-TOP: 14px;padding-left: 40px;width:992px;" height=37>
										<span style='font-size:15px;color:ff6600;font-weight: bold'  id="menu_key">注册</span>
									</TD>
								</TR>
								<TR>
									<TD >
										<%@ include file="../register.jsp" %>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width=992 align=center border=0>
			<TBODY>
				<TR>
					<TD height=80>
						<TABLE cellSpacing=0 cellPadding=0 width=992 border=0>
							<TBODY>
								<TR>
									<TD>
										<P>
											Copyright
											<A href="http://www.yuyingzhijia.cn/"><FONT color=#800080>www.yuyingzhijia.cn</FONT>
											</A>&nbsp;All Rights Reserved 版权所有
										</P>
									</TD>
									<TD width=290 align=right>
										育婴之家
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		
</BODY>
</HTML>

