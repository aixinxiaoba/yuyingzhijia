package javacommon.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * HTTP Response对象: 注意处理完成后最要调用disconnect来关闭连接。
 * 
 * @author wangzg
 * 
 */
public class Response
{
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(Response.class);

    /**
     * 返回的状态码
     */
    private int nStatusCode;

    /**
     * 返回的XML对象。
     */
    private Document objResponseAsDocument = null;

    /**
     * 返回的字符串结果。
     */
    private String strResponseAsString = null;

    /**
     * 输出流。
     */
    private InputStream objIs;

    /**
     * HttpURLConnection连接对象: 需要在构造时传递过来。
     */
    private HttpURLConnection objCon;

    /**
     * 监控流的状态: 是否已被消耗。
     */
    private boolean bStreamConsumed = false;

    /**
     * 线程局部变量。
     */
    private static ThreadLocal<DocumentBuilder> objDocBuilder;

    static
    {
        objDocBuilder = new ThreadLocal<DocumentBuilder>()
        {
            /**
             * initialValue。
             */
            @Override
            protected DocumentBuilder initialValue()
            {
                try
                {
                    return DocumentBuilderFactory.newInstance().newDocumentBuilder();
                }
                catch (ParserConfigurationException ex)
                {
                    throw new ExceptionInInitializerError(ex);
                }
            }
        };
    }

    /**
     * 空构造函数。
     */
    public Response()
    {
    }

    /**
     * 构造函数: 需要把HttpURLConnection对象传递过来，从而拿到需要的数据。
     * 
     * @param objCon
     * @throws IOException
     */
    public Response(HttpURLConnection objCon) throws IOException
    {
        this.objCon = objCon;
        this.nStatusCode = objCon.getResponseCode();

        // 拿到流对象
        if (null == (objIs = objCon.getErrorStream()))
        {
            objIs = objCon.getInputStream();
        }

        // GZIP 文件处理
        if (null != objIs && "gzip".equals(objCon.getContentEncoding()))
        {
            objIs = new GZIPInputStream(objIs);
        }
    }

    /**
     * 拿到指定的头字段的值 。
     * 
     * @param strName
     * @return
     */
    public String getResponseHeader(String strName)
    {
        if (objCon != null)
        {
            return objCon.getHeaderField(strName);
        }
        else
        {
            return null;
        }
    }

    /**
     * 拿到流对象:---注意要在连接关闭前调用。
     * 
     * @return
     */
    public InputStream getInPutStream()
    {
        if (bStreamConsumed)
        {
            throw new IllegalStateException("Stream has already been consumed.");
        }

        // 返回结果
        return objIs;
    }

    /**
     * 拿到返回的结果的字符串形式 。
     * 
     * @return
     * @throws HttpException
     */
    public String asString() throws HttpException
    {
        if (null == strResponseAsString)
        {
            BufferedReader objBr;
            StringBuffer sbufBuf = new StringBuffer();
            String strLine;

            try
            {
                InputStream objStream = getInPutStream();

                if (null == objStream)
                {
                    return null;
                }

                objBr = new BufferedReader(new InputStreamReader(objStream, "UTF-8"));

                while (null != (strLine = objBr.readLine()))
                {
                    sbufBuf.append(strLine).append("\n");
                }

                // 给responseAsString赋值
                this.strResponseAsString = sbufBuf.toString();
                logger.info(strResponseAsString);

                // 关闭连接对象
                objStream.close();
                objCon.disconnect();

                // 设置流使用的状态为已使用
                bStreamConsumed = true;
            }
            catch (NullPointerException npe)
            {
                throw new HttpException(npe.getMessage(), npe);
            }
            catch (IOException ioe)
            {
                throw new HttpException(ioe.getMessage(), ioe);
            }
        }

        // 返回结果
        return strResponseAsString;
    }

    /**
     * 拿到返回的结果的Document对象形式 。
     * 
     * @return
     * @throws HttpException
     */
    public Document asDocument() throws HttpException
    {
        if (null == objResponseAsDocument)
        {
            try
            {
                this.objResponseAsDocument = objDocBuilder.get().parse(new ByteArrayInputStream(asString().getBytes("UTF-8")));
            }
            catch (SAXException saxe)
            {
                throw new HttpException("The response body was not well-formed:\n" + strResponseAsString, saxe);
            }
            catch (IOException ioe)
            {
                throw new HttpException("There's something with the connection.", ioe);
            }
        }

        // 返回结果
        return objResponseAsDocument;
    }

    /**
     * 拿到返回的结果的InputStreamReader形式 。
     * 
     * @return
     */
    public InputStreamReader asReader()
    {
        try
        {
            return new InputStreamReader(objIs, "UTF-8");
        }
        catch (java.io.UnsupportedEncodingException uee)
        {
            return new InputStreamReader(objIs);
        }
    }

    /**
     * 关闭连接对象。
     */
    public void disconnect()
    {
        objCon.disconnect();
    }

    /**
     * 重写toString方法。
     */
    @Override
    public String toString()
    {
        if (null != strResponseAsString)
        {
            return strResponseAsString;
        }

        // 返回结果
        return "Response{" + "statusCode=" + nStatusCode + ", response=" + objResponseAsDocument + ", responseString='" + strResponseAsString + '\'' + ", is=" + objIs + ", con=" + objCon + '}';
    }

    /**
     * 拿到返回的字符串结果。
     * 
     * @return
     */
    public String getResponseAsString()
    {
        return strResponseAsString;
    }

    /**
     * 设置responseAsString。
     * 
     * @param strResponseAsString
     */
    public void setResponseAsString(String strResponseAsString)
    {
        this.strResponseAsString = strResponseAsString;
    }

    /**
     * 设置 statusCode。
     * 
     * @param nStatusCode
     */
    public void setStatusCode(int nStatusCode)
    {
        this.nStatusCode = nStatusCode;
    }

    /**
     * 拿到状态码。
     * 
     * @return
     */
    public int getStatusCode()
    {
        return nStatusCode;
    }

}
