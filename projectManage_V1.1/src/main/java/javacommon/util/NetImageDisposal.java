package javacommon.util;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manage.crm.entity.News;

/**
 * 生成验证码的Servlet 。
 * 
 */
public class NetImageDisposal extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(NetImageDisposal.class); 
	
	public static final Pattern PATTERN = Pattern.compile("<img\\s+(?:[^>]*)src\\s*=\\s*([^>]+)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	private static int DOWN_COUNT = 0;
	
	public static List<String> getImgSrc(String html) {
		Matcher matcher = PATTERN.matcher(html);
		List<String> list = new ArrayList<String>();
		while (matcher.find()) {
			String group = matcher.group(1);
			if (group == null) {
				continue;
			}
			// 这里可能还需要更复杂的判断,用以处理src="...."内的一些转义符
			if (group.startsWith("'")) {
				list.add(group.substring(1, group.indexOf("'", 1)));
			} else if (group.startsWith("\"")) {
				list.add(group.substring(1, group.indexOf("\"", 1)));
			} else {
				list.add(group.split("\\s")[0]);
			}
		}
		return list;
	}
	
	/**
     * @param urlPath 图片路径
     * @throws Exception 
     */
    public static void getImagesNew(String urlPath,String fileName) throws Exception{
        URL url = new URL(urlPath);//：获取的路径
        //:http协议连接对象
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setReadTimeout(6 * 10000);
        if (conn.getResponseCode() <10000){
            InputStream inputStream = conn.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(fileName);
            
            byte[] buffer = new byte[2048];
            int len = -1;
            while((len = inputStream.read(buffer)) !=-1){
                outputStream.write(buffer, 0, len);
            }
            
            outputStream.close();
            inputStream.close();
            logger.info("第"+ ++DOWN_COUNT +"图片下载成功");
        }
    }
    
    /**
     * 开始下载图片
     * @throws Exception 
     */
    public static void startDownLoad(News objNews,String webAddress) throws Exception
    {
    	String strCurFileName;
		String strImgForReturn = null;
		String strContent = objNews.getStrContent();
		Long lProjectId = objNews.getObjProjectMenu().getlId();
		List<String> lstImage;
		String filePath = "D:/work/application/yuyingzhijia/upload/" + lProjectId + "/";
		
		if (!new File(filePath).exists())
		{
			new File(filePath).mkdirs();
		}
		lstImage = NetImageDisposal.getImgSrc(strContent);
		if (lstImage != null && lstImage.size() > 0)
		{
			String tempCurImg;
			
			logger.info("存在图片，开始从网络上下载图片");
			for (String cuImg : lstImage) {
				tempCurImg = cuImg;
				if (cuImg == null || cuImg.length() <= 0)
				{
					continue;
				}
				cuImg = webAddress + cuImg;
				if (cuImg.indexOf("/upload") == 0)
				{
					continue;
				}
				try {
					logger.info("当前处理图片为：=========" + cuImg + "==================");
					// 图片名称。
					strCurFileName = cuImg.substring(cuImg.lastIndexOf("/") + 1);
					NetImageDisposal.getImagesNew(cuImg, filePath + strCurFileName);
					strContent = strContent.replace(tempCurImg, "/upload/" + lProjectId + "/" + strCurFileName);
					if (strImgForReturn == null)
					{
						strImgForReturn = "/upload/" + lProjectId + "/" + strCurFileName;
					}
					
					String srcImgPath = filePath + strCurFileName;
					String iconPath = "D:/work/application/yuyingzhijia/commons/images/watermark.jpg";
					Image srcImg = ImageIO.read(new File(srcImgPath));
					logger.info("给图片添加水印图片开始...");
			        //ImageMarkLogoUtil.setImageMarkOptions(1f,srcImg.getWidth(null)-210,srcImg.getHeight(null)-60,null,null);
			        // 给图片添加水印图片  
			        //ImageMarkLogoUtil.markImageByIcon(iconPath, srcImgPath, srcImgPath);  
			        logger.info("给图片添加水印图片结束...");
			        
					
				} catch (Exception e) {
					logger.error("=========文章ID:" + objNews.getlId() + "=========图片URL:" + cuImg + "========错误信息=====" + e.getMessage());
				}
			}
			logger.info("========图片下载完成=========");
		}
		
		objNews.setStrContent(strContent);
		if (strImgForReturn != null)
		{
			objNews.setImageUrl(strImgForReturn);
		}
    }
}
