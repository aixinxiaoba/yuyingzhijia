package javacommon.struts2.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.org.rapid_framework.web.httpinclude.HttpInclude;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 提供视图渲染的共享变量拦截器。
 * 
 * @author
 * @version 1.0
 */
public class SharedRenderVariableInterceptor implements Interceptor
{
	/**
	 * 序列化ID。
	 */
	private static final long serialVersionUID = 985668207527158469L;

	/**
	 * 系统启动并初始化一次的变量。
	 */
	Map<String, Object> mapGlobalRenderVariables = new HashMap<String, Object>();

	/**
	 * 初始化方法。
	 */
	@Override
	public void init()
	{
		initSharedRenderVariables();
	}

	/**
	 * 初始化变量。
	 */
	private void initSharedRenderVariables()
	{
		mapGlobalRenderVariables.put("global_system_start_time", new Date());
		mapGlobalRenderVariables.put("url_prefix", "http://www.rapid-framework.org.cn");
		mapGlobalRenderVariables.put("media_url_prefix", "/images");
	}

	/**
	 * intercept方法。
	 */
	@Override
	public String intercept(ActionInvocation objInvocation) throws Exception
	{
		String strResult;
		
		before(objInvocation);
		strResult = objInvocation.invoke();
		
		return strResult;
	}

	/**
	 * 拦截后要做的一些操作。
	 * 
	 * @param objInvocation
	 */
	private void before(ActionInvocation objInvocation)
	{
		ValueStack objVs = objInvocation.getInvocationContext().getValueStack();
		
		for (String strKey : mapGlobalRenderVariables.keySet())
		{
			objVs.set(strKey, mapGlobalRenderVariables.get(strKey));
		}

		preRequest(objVs, objInvocation);
	}

	/**
	 * 向ValueStack中保存数据。
	 * 
	 * @param objVs
	 * @param objInvocation
	 */
	private void preRequest(ValueStack objVs, ActionInvocation objInvocation)
	{
		objVs.set("share_current_request_time", new Date());
		objVs.set("share_current_login_userid", ServletActionContext.getRequest().getSession(false).getAttribute(""));
		objVs.set("share_current_login_username", ServletActionContext.getRequest().getSession(false).getAttribute(""));

		// 为freemarker,velocity提供<jsp:include page="/some/page.jsp"/>功能,使用示例:
		// ${httpInclude.include("/servlet/header.do")};
		objVs.set("httpInclude", new HttpInclude(ServletActionContext.getRequest(), ServletActionContext.getResponse()));
	}

	/**
	 * 销毁方法。
	 */
	@Override
	public void destroy()
	{
	}

}
