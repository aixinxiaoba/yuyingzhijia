package javacommon.core.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 输出请求处理中提示:---用于逻辑处理时间长的请求。
 * 
 * @author wangzg
 * 
 */
public class LoadingFilter extends OncePerRequestFilter implements Filter
{
	/**
	 * doFilterInternal方法。
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest objRequest, HttpServletResponse objResponse, FilterChain objChain) throws ServletException, IOException
	{
		try
		{
			String strPath = objRequest.getContextPath();
			String strBasePath = objRequest.getScheme() + "://" + objRequest.getServerName() + ":" + objRequest.getServerPort() + strPath + "/";
			
			objResponse.getWriter().write(html(strBasePath));
			objResponse.getWriter().flush();

			// 传递到FilterChain中
			objChain.doFilter(objRequest, objResponse);
		}
		finally
		{
			objResponse.getWriter().write(afterHtml(null));
			objResponse.getWriter().flush();
		}
	}

	/**
	 * 向页面中输出等待的提示信息。
	 *
	 * @param strBasePath
	 * @return
	 */
	private String html(String strBasePath)
	{
		StringBuilder objStringBuffer = new StringBuilder();
		
		objStringBuffer.append("<span id=\"_message_div\"><img id=\"_message_img\" src=\"" + strBasePath + "styles/images/ajax-loader.gif\" width=\"16\" height=\"16\"" + " align=\"absmiddle\"><label id=\"_message\">\u8bf7\u6c42\u5904\u7406\u4e2d...</label></span>");

		// 返回结果.
		return objStringBuffer.toString();
	}

	/**
	 * 用JS隐藏等待的提示信息。
	 * 
	 * @param strBasePath
	 * @return
	 */
	private String afterHtml(String strBasePath)
	{
		String strJS = "" + "<script type=\"text/javascript\">\n" + "		var _msgObj = document.getElementById(\"_message_div\");\n" + " 	if(_msgObj){\n" + "			_msgObj.style.display = \"none\";\n" + "		}\n" + "</script>\n";

		// 返回结果
		return strJS;
	}

}