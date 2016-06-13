package com.manage.crm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * <p> Title: </p> <p> Description: http utils </p> <p> Copyright: Copyright (c) 2006 </p> <p> Company: </p>
 * 
 * @author jiangwei
 * @version 1.0
 */
public class HttpUtils
{
    private static final Logger logger = Logger.getLogger(HttpUtils.class);
    private static final String URL_PARAM_CONNECT_FLAG = "&";
    private static final int SIZE = 1024 * 1024;

    /**
     * 无参构造。
     */
    private HttpUtils()
    {
    }

    /**
     * GET METHOD。
     * 
     * @param strUrl String
     * @param map Map
     * @throws IOException
     * @return List
     */
    public static List URLGet(String strUrl, Map map)
    {
        String strStartTotalURL = "";
        List lstResult = new ArrayList();

        logger.info("进入URLGet（）函数处理.");
        if (strStartTotalURL.indexOf("?") == -1)
        {
            logger.info("strtTotalURL.indexOf = -1");
            strStartTotalURL = strUrl + "?" + getUrl(map);
            logger.info("strtTotalURL：" + strStartTotalURL);
        }
        else
        {
            logger.info("进入 else");
            strStartTotalURL = strUrl + "&" + getUrl(map);
            logger.info("strtTotalURL：" + strStartTotalURL);
        }
        try
        {
            URL strURL = new URL(strStartTotalURL);
            HttpURLConnection objHttpURLCon;
            BufferedReader objBRIn;

            logger.info("url:" + strURL);
            objHttpURLCon = (HttpURLConnection) strURL.openConnection();
            objHttpURLCon.setUseCaches(false);
            HttpURLConnection.setFollowRedirects(true);

            objBRIn = new BufferedReader(new InputStreamReader(objHttpURLCon.getInputStream()), SIZE);
            while (true)
            {
                String strLine = objBRIn.readLine();

                if (strLine == null)
                {
                    break;
                }
                else
                {
                    lstResult.add(strLine);
                }
            }
            objBRIn.close();
            return (lstResult);
        }
        catch (MalformedURLException e2)
        {
            logger.info("URL url = new URL(strtTotalURL)异常", e2);
            return (lstResult);
        }
        catch (IOException e)
        {
            logger.info("url连接打开异常：", e);
            return (lstResult);
        }
    }

    /**
     * POST METHOD。
     * 
     * @param strUrl String
     * @param content Map
     * @throws IOException
     * @return List
     */
    public static List URLPost(String strUrl, Map map) throws IOException
    {

        String strContent = "";
        String strMyTotalURL = null;
        URL strURL;
        HttpURLConnection objHttpURLCon;
        BufferedReader objBin;
        List lstResult;
        BufferedWriter bout;

        strContent = getUrl(map);
        if (strUrl.indexOf("?") == -1)
        {
            strMyTotalURL = strUrl + "?" + strContent;
        }
        else
        {
            strMyTotalURL = strUrl + "&" + strContent;
        }
        strURL = new URL(strUrl);
        objHttpURLCon = (HttpURLConnection) strURL.openConnection();
        objHttpURLCon.setDoInput(true);
        objHttpURLCon.setDoOutput(true);
        objHttpURLCon.setAllowUserInteraction(false);
        objHttpURLCon.setUseCaches(false);
        objHttpURLCon.setRequestMethod("POST");
        objHttpURLCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
        bout = new BufferedWriter(new OutputStreamWriter(objHttpURLCon.getOutputStream()));
        bout.write(strContent);
        bout.flush();
        bout.close();
        objBin = new BufferedReader(new InputStreamReader(objHttpURLCon.getInputStream()), SIZE);
        lstResult = new ArrayList();
        while (true)
        {
            String strLine = objBin.readLine();

            if (strLine == null)
            {
                break;
            }
            else
            {
                lstResult.add(strLine);
            }
        }
        return (lstResult);
    }

    /**
     * 获取url。
     * 
     * @param map Map
     * @return String
     */
    private static String getUrl(Map map)
    {
        StringBuffer sbufURL;
        Set setKeys;
        String strURL = "";

        logger.info("进入 getUrl（） 函数处理。");
        if (null == map || map.keySet().size() == 0)
        {
            return ("");
        }
        sbufURL = new StringBuffer();
        setKeys = map.keySet();
        for (Iterator i = setKeys.iterator(); i.hasNext();)
        {
            String strKey = String.valueOf(i.next());

            if (map.containsKey(strKey))
            {
                Object objVal = map.get(strKey);
                String str = objVal != null ? objVal.toString() : "";

                try
                {
                    str = URLEncoder.encode(str, "GBK");
                }
                catch (UnsupportedEncodingException e)
                {
                    logger.info("str = URLEncoder.encode(str, gbk);。异常", e);
                }
                sbufURL.append(strKey).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
            }
        }
        strURL = sbufURL.toString();
        if (URL_PARAM_CONNECT_FLAG.equals("" + strURL.charAt(strURL.length() - 1)))
        {
            strURL = strURL.substring(0, strURL.length() - 1);
        }
        return (strURL);
    }

    public static String URLPostForContent(String strUrl, Map map)
    throws IOException
  {
    String strContent = "";
    String strMyTotalURL = null;

    StringBuffer sbufContent = new StringBuffer();

    strContent = getUrl(map);
    if (strUrl.indexOf("?") == -1)
    {
      strMyTotalURL = strUrl + "?" + strContent;
    }
    else
    {
      strMyTotalURL = strUrl + "&" + strContent;
    }
    URL strURL = new URL(strMyTotalURL);
    HttpURLConnection objHttpURLCon = (HttpURLConnection)strURL.openConnection();
    objHttpURLCon.setDoInput(true);
    objHttpURLCon.setDoOutput(true);
    objHttpURLCon.setAllowUserInteraction(false);
    objHttpURLCon.setUseCaches(false);
    objHttpURLCon.setRequestMethod("POST");
    objHttpURLCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
    BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(objHttpURLCon.getOutputStream()));
    bout.write(strContent);
    bout.flush();
    bout.close();
    BufferedReader objBin = new BufferedReader(new InputStreamReader(objHttpURLCon.getInputStream(), "GBK"), 1048576);
    while (true)
    {
      String strLine = objBin.readLine();

      if (strLine == null)
      {
        break;
      }

      sbufContent.append(strLine);
    }

    return sbufContent.toString();
  }
}
