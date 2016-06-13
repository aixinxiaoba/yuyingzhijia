package javacommon.struts2.interceptor;

import java.io.PrintWriter;

import javacommon.core.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * action拦截器
 * 
 * @author Administrator
 * 
 */
public class LoginInterceptor extends MethodFilterInterceptor
{
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 4174975558343571486L;

	/**
	 * 过滤器的doIntercept。
	 */
	@Override
	public String doIntercept(ActionInvocation objInvocation) throws Exception
	{
		Object objUser = objInvocation.getInvocationContext().getSession().get(Config.objCOMConfig.getProperty("USER_ID_KEY", "USER_ID"));
		Object objExpert = objInvocation.getInvocationContext().getSession().get(Config.objCOMConfig.getProperty("EXPERT_USER_ID_KEY", "EXPERT_USER_ID"));
		HttpServletRequest objRequest = ServletActionContext.getRequest();
		HttpServletResponse objResponse = ServletActionContext.getResponse();
		
		// 对LoginAction不做该项拦截
		Object objAction = objInvocation.getAction();

		PrintWriter objOut = objResponse.getWriter();

		// 如果已经在Session中存在，则允许其继续操作
		if (objUser != null)
		{
			return objInvocation.invoke();
		}
		
		if (objExpert != null)
		{
			return objInvocation.invoke();
		}

		// AJAX请求不做拦截
		if (bAjaxRequest())
		{
			return objInvocation.invoke();
		}

		// 如果用户不在上述条件，则转到登陆页面
		objResponse.setContentType("text/html");
		objResponse.setCharacterEncoding("UTF-8");

		objOut.println("<script>alert('请登录后再操作!');window.parent.location='" + objRequest.getContextPath() + "/forward.do?forwardURL=login'</script>");
		objOut.flush();
		objOut.close();
		
		// 返回结果
		return null;
	}

	/**
	 * 判断是否是ajax请求。
	 * 
	 * @return
	 */
	private boolean bAjaxRequest()
	{
		HttpServletRequest objRequest = ServletActionContext.getRequest();

		String strHeader = objRequest.getHeader("X-Requested-With");

		if (strHeader != null && "XMLHttpRequest".equals(strHeader))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
