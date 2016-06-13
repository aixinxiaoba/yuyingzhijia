<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="/commons/css/index.css" rel="stylesheet"  />
<link type="text/css" href="/commons/css/register.css"  rel="stylesheet" />
<script type="text/javascript" src="/commons/js/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/commons/js/jquery.form.js"></script>
<script type="text/javascript" src="/commons/js/nicEdit.js"></script>

<script type="text/javascript">
function submitForm(){
	$("#content").val($(".nicEdit-main").html());
	$('#usersForm').ajaxSubmit(function(msg){
			if (msg == "success")
			{
				alert("保存成功！");
				location.href = "/jsp/MPlatform/news/newsList.jsp?lProjectID=${param.lProjectID}&lProjectMenuID=${param.lProjectMenuID}";
			}
			else
			{
				alert(msg);
			}
      	}
    );
}

/**
 * 加载公司新闻类型。
 */
function loadNewsType()
{
	$.ajax({
           url:"/mpf/projectMenu/projectMenuList.do?lProjectID=${param.lProjectID}&subMenuID=${param.lProjectMenuID}",
           type:"post",
           data:{},
           dataType:"json",
           async: false,
           success:function(data)
           {
        	   var strHTML = "<select name='objNews.objProjectMenu.lId' style='width: 500px; height:25px;border:1px solid blue;font-size:15px;'><option value='-1'>请选择</option>";
        	   if (data != null && data.rows.length > 0)
        	   {
        		   for (var i = 0; i < data.rows.length; i++ )
        		   {
        			   var strProjectMenu = data.rows[i].strMenuName;
        			   var strSelected = "";
        			   if ("${param.lProjectMenuID }" == data.rows[i].lId)
        			   {
        			   		strSelected = "selected='selected'";
        			   }
        			   strHTML += "<option "+strSelected+" value='"+ data.rows[i].lId +"'>"+strProjectMenu+"</option>";
        		   }
        	   }
        	   strHTML += "</select>";
        	   
        	   if (strHTML == "")
        	   {
        		   strHTML = "暂无新闻";
        	   }
        	   $("#projectMenu_show").html(strHTML);
           }
    });
}

$(function(){
	loadNewsType();
})

bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
</script>
</head>
<body>
<div id="index_main_div1" style="background-color:#f7fbfc;margin-top:0px;margin-left:3px;min-height:500px;">
	<div style="border:0px solid red;background-image:url(/commons/image/index_main_div_titleBg.gif);height:21px;"><img style="margin-left:5px;" src="/commons/image/index_main_div_left.gif" width="6" height="2" align="absmiddle">&nbsp;<span style="font-weight:bold;font-size:12px;">发布新闻</span></div>
	
	<!-- 
	<form action="/mpf/news/newsAdd.do" method="post" id="usersForm">
		<div style="padding: 20px;">
			<div>
				<span style="font-size:20px;">新闻标题</span>
				<input type="text" value="" name="objNews.strTitle" class="text" tabindex="1" id="ck_SName" style="width: 500px; height:25px;border:1px solid green;font-size:15px;"/>
			</div>
			<div style="font-size:20px;">
				<div> 新闻内容</div>
				<div>
					<textarea name="objNews.strContent" style="width:100%; min-height: 200px;" id="content">
					</textarea>
				</div>
			</div>
		</div>
	</form>
	 -->
	<form action="/mpf/news/newsAdd.do" method="post" id="usersForm">
		<input type="hidden" value="${param.newsID }" name="objNews.lId"/>
		<input type="hidden" value="${param.lProjectID }" name="objNews.objProject.lId"/>
		<div style="padding: 20px;">
			<div style="padding-right:15px;">
				<span style="font-size:15px;">消息标题</span>
				<input type="text" value="${objNews.strTitle }" name="objNews.strTitle" class="text" tabindex="1" id="ck_SName" style="width: 500px; height:25px;border:1px solid blue;font-size:15px;"/>
			</div>
			
			<div style="margin-top:15px;padding-right:15px;">
				<span style="font-size:15px;">消息类型</span>
				<span id="projectMenu_show">
					<select style="width: 500px; height:25px;border:1px solid blue;font-size:15px;" name='objNews.objProjectMenu.lId'>
						<option value="-1">请选择</option>
					</select>
				</span>
			</div>
			
			<div style="padding-right:15px;">
				<span style="font-size:15px;">摘要</span>
				<input type="text" value="${objNews.strSummary }" name="objNews.strSummary" class="text" tabindex="1" id="ck_SName" style="width: 500px; height:25px;border:1px solid blue;font-size:15px;"/>
			</div>
			<div style="margin-top:15px;padding-right:15px;">
				<div style="font-size:15px;"> 消息内容</div>
				<div>
					<textarea name="objNews.strContent" style="width:100%; min-height: 200px;" id="content"></textarea>
				</div>
			</div>
		</div>
	</form>
	
	<!-- 
	<div style="margin-top: 10px;" id="formbox" class="form">
		<form action="/mpf/news/newsAdd.do" method="post" id="usersForm">
			<div class="item">
			<span class="label" id="span_${proFileInfo.strPfidentity}">新闻标题：</span>
	        <div class="fl">
	          <input type="text" value="" name="objNews.strTitle" class="text" tabindex="1" id="ck_SName"/>
	        </div>
	       </div>
	       
	       <!-- 
	       <div class="item">
				<span class="label" id="span_${proFileInfo.strPfidentity}">分类：</span>
		        <div class="fl">
		          	<select class="text" name="objNews.strCategory">
		          		<option value="请选择">请选择</option>
		          		<option value="首页焦点">首页焦点</option>
		          		<option value="学员感言">学员感言</option>
		          		<option value="常见问题">常见问题</option>
		          		<option value="招聘信息">招聘信息</option>
		          		<option value="课程信息">课程信息</option>
		          		<option value="业务信息">业务信息</option>
		          		<option value="资源信息">资源信息</option>
		          	</select>
		        </div>
	       </div>
	       
	       <div class="item" style="border:0px solid red;min-height:302px;">
				<span class="label" id="span_${proFileInfo.strPfidentity}">新闻内容：</span>
		        <div class="fl">
		         <textarea name="area3" style="width: 500px; min-height: 200px;">
					
				 </textarea>
		        </div>
	       </div>
	     
	        
	     </form>
	</div>
	 -->
	
	<div style="margin-top: 40px;margin-left:20px;text-align: center;">
		<img src="/commons/image/s10.gif" width="58" height="22" style="cursor: pointer;" onclick="submitForm();">
	</div>
</div>


</body>
</html>
