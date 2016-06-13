package javacommon.core.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 过滤请求中的非法字符。
 * 
 * @author wangzg
 * 
 */
public class SafeFilter extends OncePerRequestFilter implements Filter
{

    /**
     * 需要过滤的字符串集合, 可以用Config.SQL_CHECK_ARRAY代替。
     */
    private static Set<String> lstKeywordSet = new HashSet<String>();

    /**
     * 有危险字符串需要返回的页面。
     */
    private static final String REJECT_URL = "/commons/403.jsp";

    /**
     * 初始化initFilterBean。
     */
    @Override
    protected void initFilterBean() throws ServletException
    {
        String strKeywords = this.getFilterConfig().getInitParameter("keywords");

        if (StringUtils.hasText(strKeywords))
        {
            String[] arrayKeywordArray = strKeywords.split(",");

            for (String keyword : arrayKeywordArray)
            {
                lstKeywordSet.add(keyword);
            }
        }
    }

    /**
     * doFilterInternal方法。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest objRequest, HttpServletResponse objResponse, FilterChain objChain) throws ServletException, IOException
    {
        // 包含非法关键词就退出，方法内部已经做重定向。
        if (filterKeywordByTwoCycles(objRequest, objResponse))
        {
            return;
        }

        // 如果没有危险字，则可以传递到FilterChain中
        objChain.doFilter(objRequest, objResponse);
    }

    /**
     * 检查请求参数中是否存在危险字。
     * 
     * @param objRequest
     * @param objResponse
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @SuppressWarnings("unchecked")
    private boolean filterKeywordByTwoCycles(HttpServletRequest objRequest, HttpServletResponse objResponse) throws IOException, ServletException
    {
        // 需要判断的内容
        StringBuilder objContentBuffer = new StringBuilder();

        // step1:拼接request中的参数
        Map<String, String[]> mapPramMap = objRequest.getParameterMap();

        // step2.1:循环获取request中的请求参数数组
        for (Entry < String, String[] > arrayEntry : mapPramMap.entrySet())
        {
            // step2.2:循环获取参数数组中的值，进行StringBuilder的拼接
            String[] arrayValue = arrayEntry.getValue();

            for (int i = 0; i < arrayValue.length; i++)
            {
                if (null != objContentBuffer)
                {
                    objContentBuffer.append(arrayValue[i] + " ");
                }
            }
        }

        // step3:循环关键词列表，进行对比
        for (String strKeyword : lstKeywordSet)
        {
            if (objContentBuffer != null && objContentBuffer.toString().contains(strKeyword))
            {
                ServletOutputStream objOut = objResponse.getOutputStream();

                objOut.println("error keyword");
                objResponse.sendRedirect(objRequest.getContextPath() + REJECT_URL);
                objResponse.setStatus(403);
                return true;
            }
        }

        // 返回结果
        return false;
    }
}
