/**
 * 加载项目信息。
 */
function loadProjectInfo(projectKey)
{
	// 获取项目名称。
	$.ajax({
           url:"${pageContext.request.contextPath}/mpf/project/loadProjectInfo.do?projectKey="+projectKey,
           type:"post",
           data:{},
           dataType:"text",
           async: true,
           success:function(data)
           {	
        	   if (data.strErrorMsg != null)
        	   {
        		    $("#menu_key").text(data);
        	   }
        	   else
        	   {
        		   alert(data.strErrorMsg);
        	   }
           }
    });
}