package javacommon.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.net.Proxy.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClient 工具类：--- 1：---用法:---new HttpClient().httpRequest(String url,
 * PostParameter[] postParams, boolean authenticated,String httpMethod)。
 * 
 * @author wangzg
 * 
 */
public class HttpClient implements java.io.Serializable
{
    /**
     * 序列化ID。
     */
    private static final long serialVersionUID = -899510126290897171L;

    /**
     * 日志对象。
     */
    protected Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private static final int OK = 200;// OK: Success!
    private static final int NOT_MODIFIED = 304;// Not Modified: There was no
    // new data to return.
    private static final int BAD_REQUEST = 400;// Bad Request: The request was
    // invalid. An accompanying
    // error message will explain
    // why. This is the status code
    // will be returned during rate
    // limiting.
    private static final int NOT_AUTHORIZED = 401;// Not Authorized:
    // Authentication
    // credentials were missing
    // or incorrect.
    private static final int FORBIDDEN = 403;// Forbidden: The request is
    // understood, but it has been
    // refused. An accompanying
    // error message will explain
    // why.
    private static final int NOT_FOUND = 404;// Not Found: The URI requested is
    // invalid or the resource
    // requested, such as a user,
    // does not exists.
    private static final int NOT_ACCEPTABLE = 406;// Not Acceptable: Returned by
    // the Search API when an
    // invalid format is
    // specified in the request.
    private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server
    // Error: Something
    // is broken. Please
    // post to the group
    // so the Weibo team
    // can investigate.
    private static final int BAD_GATEWAY = 502;// Bad Gateway: Weibo is down or
    // being upgraded.
    private static final int SERVICE_UNAVAILABLE = 503;// Service Unavailable:
    // The Weibo servers are
    // up, but overloaded
    // with requests. Try
    // again later. The
    // search and trend
    // methods use this to
    // indicate when you are
    // being rate limited.

    /**
     * 类名。
     */
    public final static String strModule = HttpClient.class.getName();

    /* 尝试次数 */
    private int nRetryCount = 3;
    private int nRetryIntervalMillis = 3 * 1000;

    private String strProxyHost = null;
    private int nProxyPort = -1;
    private String strProxyAuthUser = null;
    private String strProxyAuthPassword = null;
    private int nConnectionTimeout = 30000;
    private int nReadTimeout = 30000;

    /**
     * 有参构造。
     * 
     * @param strProxyHost
     * @param nProxyPort
     * @param strProxyAuthUser
     * @param strProxyAuthPassword
     */
    public HttpClient(String strProxyHost, int nProxyPort, String strProxyAuthUser, String strProxyAuthPassword)
    {
        super();
        this.strProxyHost = strProxyHost;
        this.nProxyPort = nProxyPort;
        this.strProxyAuthUser = strProxyAuthUser;
        this.strProxyAuthPassword = strProxyAuthPassword;
    }

    /**
     * 空构造。
     */
    public HttpClient()
    {
    }

    /**
     * 发送HTTP的请求。
     * 
     * @param strUrl
     * @param arrayPostParams
     * @param bAuthenticated
     * @param strHttpMethod
     * @return
     * @throws HttpException
     */
    public Response httpRequest(String strUrl, PostParameter[] arrayPostParams, boolean bAuthenticated, String strHttpMethod) throws HttpException
    {
        int nRetriedCount;
        int nRetry = nRetryCount;
        Response objRes = null;

        for (nRetriedCount = 0; nRetriedCount < nRetry; nRetriedCount++)
        {
            int nResponseCode = -1;

            try
            {
                HttpURLConnection objCon = null;
                OutputStream objOsw = null;

                try
                {
                    objCon = getConnection(strUrl);
                    objCon.setDoInput(true);
                    objCon.setUseCaches(false);

                    setHeaders(strUrl, arrayPostParams, objCon, bAuthenticated, strHttpMethod);
                    if (null != arrayPostParams || "POST".equals(strHttpMethod))
                    {
                        String strPostParam = "";
                        byte[] arrayBytes;

                        objCon.setRequestMethod("POST");
                        objCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        objCon.setDoOutput(true);

                        // 编码
                        if (arrayPostParams != null)
                        {
                            strPostParam = encodeParameters(arrayPostParams);
                        }

                        arrayBytes = strPostParam.getBytes("UTF-8");

                        objCon.setRequestProperty("Content-Length", Integer.toString(arrayBytes.length));

                        objOsw = objCon.getOutputStream();

                        objOsw.write(arrayBytes);
                        objOsw.flush();
                        objOsw.close();
                    }
                    else if ("DELETE".equals(strHttpMethod))
                    {
                        objCon.setRequestMethod("DELETE");
                    }
                    else
                    {
                        objCon.setRequestMethod("GET");
                    }

                    objRes = new Response(objCon);
                    nResponseCode = objCon.getResponseCode();

                    // 打印响应头信息
                    if (true)
                    {
                        Map<String, List<String>> mapResponseHeaders = objCon.getHeaderFields();

                        for (String strKey : mapResponseHeaders.keySet())
                        {
                            List<String> lstValues = mapResponseHeaders.get(strKey);

                            for (String strValue : lstValues)
                            {
                                if (null != strKey)
                                {
                                    logger.info(strKey + ": " + strValue);
                                }
                                else
                                {

                                }
                            }
                        }
                    }

                    // will retry if the status code is INTERNAL_SERVER_ERROR
                    if (nResponseCode != OK)
                    {
                        if (nResponseCode < INTERNAL_SERVER_ERROR || nRetriedCount == nRetryCount)
                        {
                            throw new HttpException(getCause(nResponseCode) + "\n" + objRes.asString(), nResponseCode);
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                finally
                {
                    try
                    {
                        objOsw.close();
                    }
                    catch (Exception ignore)
                    {
                        logger.error("" + ignore);
                    }
                }
            }
            catch (IOException ioe)
            {
                if ("Read timed out".equals(ioe.getMessage()))
                {
                    logger.error("HttpClient消息已经发送成功，读取主机超时！", strModule);

                    // 读超时则不再发送
                    break;
                }

                // connection timeout
                if (nRetriedCount == nRetryCount)
                {
                    throw new HttpException(ioe.getMessage(), ioe, nResponseCode);
                }
            }

            try
            {
                if (null != objRes)
                {
                    logger.info(objRes.asString());
                }

                logger.info("Sleeping " + nRetryIntervalMillis + " millisecs for next retry.");
                Thread.sleep(nRetryIntervalMillis);
            }
            catch (InterruptedException ignore)
            {
                // Ignore this error
            }
        }

        // 返回结果
        return objRes;
    }

    /**
     * 请求的POST参数统一做编码。
     * 
     * @param arrayPostParams
     * @return
     */
    private String encodeParameters(PostParameter[] arrayPostParams)
    {
        StringBuffer sbufBuf = new StringBuffer();

        for (int j = 0; j < arrayPostParams.length; j++)
        {
            if (j != 0)
            {
                sbufBuf.append("&");
            }
            try
            {
                sbufBuf.append(URLEncoder.encode(arrayPostParams[j].getStrName(), "UTF-8")).append("=").append(URLEncoder.encode(arrayPostParams[j].getStrValue(), "UTF-8"));
            }
            catch (java.io.UnsupportedEncodingException neverHappen)
            {
                logger.error("" + neverHappen);
            }
        }

        // 返回结果
        return sbufBuf.toString();
    }

    /**
     * 设置HttpURLConnection的表头，主要是编码utf-8。
     * 
     * @param strUrl
     * @param arrayParams
     * @param objConnection
     * @param bAuthenticated
     * @param strHttpMethod
     */
    private void setHeaders(String strUrl, PostParameter[] arrayParams, HttpURLConnection objConnection, boolean bAuthenticated, String strHttpMethod)
    {
        Map<String, String> mapRequestHeaders = new HashMap<String, String>();

        mapRequestHeaders.put("Content-Language", "utf-8");
        mapRequestHeaders.put("Accept-Charset", "utf-8");

        // 设置到HttpURLConnection中
        for (String strKey : mapRequestHeaders.keySet())
        {
            objConnection.setRequestProperty(strKey, mapRequestHeaders.get(strKey));
        }
    }

    /**
     * 根据URL拿到HttpURLConnection对象。
     * 
     * @param strUrl
     * @return
     * @throws IOException
     */
    private HttpURLConnection getConnection(String strUrl) throws IOException
    {
        HttpURLConnection objCon = null;
        final Proxy objProxy;

        if (strProxyHost != null && !strProxyHost.equals(""))
        {
            if (strProxyAuthUser != null && !strProxyAuthUser.equals(""))
            {
                logger.info("Proxy AuthUser: " + strProxyAuthUser + "Proxy AuthPassword: " + strProxyAuthPassword);
                Authenticator.setDefault(new Authenticator()
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        // respond only to proxy auth requests
                        if (getRequestorType().equals(RequestorType.PROXY))
                        {
                            return new PasswordAuthentication(strProxyAuthUser, strProxyAuthPassword.toCharArray());
                        }
                        else
                        {
                            return null;
                        }
                    }
                });
            }

            // 获得代理对象
            objProxy = new Proxy(Type.HTTP, InetSocketAddress.createUnresolved(strProxyHost, nProxyPort));

            logger.info("Opening proxied connection(" + strProxyHost + ":" + nProxyPort + ")");

            objCon = (HttpURLConnection) new URL(strUrl).openConnection(objProxy);
        }
        else
        {
            objCon = (HttpURLConnection) new URL(strUrl).openConnection();
        }

        // 设置超时值
        if (nConnectionTimeout > 0)
        {
            objCon.setConnectTimeout(nConnectionTimeout);
        }

        // 设置读值超时值
        if (nReadTimeout > 0)
        {
            objCon.setReadTimeout(nReadTimeout);
        }

        // 返回结果
        return objCon;
    }

    /**
     * 拿到出错误的原因。
     * 
     * @param nStatusCode
     * @return
     */
    private static String getCause(int nStatusCode)
    {
        String strCause = null;

        switch (nStatusCode)
        {
        case NOT_MODIFIED:
            break;
        case BAD_REQUEST:
            strCause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
            break;
        case NOT_AUTHORIZED:
            strCause = "Authentication credentials were missing or incorrect.";
            break;
        case FORBIDDEN:
            strCause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
            break;
        case NOT_FOUND:
            strCause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
            break;
        case NOT_ACCEPTABLE:
            strCause = "Returned by the Search API when an invalid format is specified in the request.";
            break;
        case INTERNAL_SERVER_ERROR:
            strCause = "Something is broken.  Please post to the group so the Weibo team can investigate.";
            break;
        case BAD_GATEWAY:
            strCause = "Weibo is down or being upgraded.";
            break;
        case SERVICE_UNAVAILABLE:
            strCause = "Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
            break;
        default:
            strCause = "";
        }

        // 返回结果
        return nStatusCode + ":" + strCause;
    }

}
