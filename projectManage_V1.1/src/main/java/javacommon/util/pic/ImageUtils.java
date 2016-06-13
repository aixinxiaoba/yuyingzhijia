package javacommon.util.pic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片处理工具类 。
 * 
 * @author wangzg
 * 
 */
public class ImageUtils
{
    /**
     * 日志对象。
     */
    private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * 压缩图片设置（ 缩放规则，高度根据宽度计算出的缩放比例，进行等比例压缩。输出图路径如果为：null,表示直接覆盖原有图片）。
     * 
     * @param strInputFile 输入图路径
     * @param strOutputFile 输出图路径。
     * @param nWidth设置图片长宽
     * @param nHeight设置图片宽度
     * @param bProportion是否是等比缩放
     * @return
     */
    public static boolean compressPic(String strInputFile, String strOutputFile, int nWidth, int nHeight, boolean bProportion)
    {
        Image objImg;
        BufferedImage objTag;
        FileOutputStream objOut = null;
        JPEGImageEncoder objEncoder;
        File objFile = null; // 文件对象

        try
        {
            // 获得源文件
            objFile = new File(strInputFile);
            if (!objFile.exists())
            {
                logger.error("在" + strInputFile + "路径下，没有找到图片信息，请检查图片路径是否正确！");
                return false;
            }
            objImg = ImageIO.read(objFile);

            // 判断图片格式是否正确
            if (objImg.getWidth(null) < 0 || objImg.getHeight(null) < 0)
            {
                logger.info("计算图片尺寸，出现错误！");
                return false;
            }

            if (nWidth <= 0 || nHeight <= 0)
            {
                logger.info("要输出的图片尺寸，nWidth=" + nWidth + ",nHeight=" + nHeight + "输入有误，请核对后重新输入！");
                return false;
            }

            // 判断是否是等比缩放
            if (bProportion == true)
            {
                // 为等比缩放计算输出的图片宽度及高度
                double dbRate = ((double) objImg.getWidth(null)) / (double) nWidth + 0.1;

                nHeight = (int) (((double) objImg.getHeight(null)) / dbRate);// 缩放规则，宽度缩放到指定的大小，如306px,高度根据宽度的缩放比例，进行等比例压缩。
            }
            objTag = new BufferedImage((int) nWidth, (int) nHeight, BufferedImage.TYPE_INT_RGB);

            // Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢。
            objTag.getGraphics().drawImage(objImg.getScaledInstance(nWidth, nHeight, Image.SCALE_SMOOTH), 0, 0, null);

            if (null == strOutputFile || "".equals(strOutputFile))
            {
                strOutputFile = strInputFile;
            }
            objOut = new FileOutputStream(strOutputFile); // 指定处理图片的生成路径。
            objEncoder = JPEGCodec.createJPEGEncoder(objOut);

            objEncoder.encode(objTag);
        }
        catch (IOException objException)
        {
            logger.error("压缩图片出异常......");
            return false;
        }
        finally
        {
            if (null != objOut)
            {
                try
                {
                    objOut.close();
                }
                catch (IOException objException)
                {
                    logger.error(objException.toString());
                }
            }
        }

        return true;
    }

    /**
     * 压缩图片设置。
     * 
     * @param strInPutDir 输入图路径
     * @param strOutPutDir 输出图路径
     * @param strInPutFileName 输入图文件名
     * @param strOutPutFileName 输出图文件名
     * @param width设置图片长宽
     * @param nHeight
     * @param gp是否是等比缩放 标记
     * @return
     */
    public static String compressPic(String strInPutDir, String strOutPutDir, String strInPutFileName, String strOutPutFileName, int nWidth, int nHeight, boolean bProportion)
    {
        Image objImg;
        BufferedImage objTag;
        FileOutputStream objOut;
        JPEGImageEncoder objEncoder;
        File objFile = null; // 文件对象
        int nOutPutWidth = 100; // 默认输出图片宽
        int nOutPutHeight = 100; // 默认输出图片高

        try
        {
            // 获得源文件
            objFile = new File(strInPutDir + strInPutFileName);
            if (!objFile.exists())
            {
                return "";
            }
            objImg = ImageIO.read(objFile);
            // 判断图片格式是否正确
            if (objImg.getWidth(null) < 0)
            {
                logger.info(" can't read,retry!" + "<BR>");
                return "no";
            }
            else
            {
                int nNewWidth;
                int nNewHeight;

                if (nWidth > 0 && nHeight > 0)
                {
                    nOutPutWidth = nWidth; // 输出的图片宽度
                    nOutPutHeight = nHeight; // 输出的图片高度
                }

                // 判断是否是等比缩放
                if (bProportion == true)
                {
                    // 为等比缩放计算输出的图片宽度及高度
                    double dbRate1 = ((double) objImg.getWidth(null)) / (double) nOutPutWidth + 0.1;
                    double dbRate2 = ((double) objImg.getHeight(null)) / (double) nOutPutHeight + 0.1;
                    // 根据缩放比率大的进行缩放控制
                    double dbRate = dbRate1 > dbRate2 ? dbRate1 : dbRate2;

                    nNewWidth = (int) (((double) objImg.getWidth(null)) / dbRate);
                    nNewHeight = (int) (((double) objImg.getHeight(null)) / dbRate);
                }
                else
                {
                    nNewWidth = nOutPutWidth; // 输出的图片宽度
                    nNewHeight = nOutPutHeight; // 输出的图片高度
                }
                objTag = new BufferedImage((int) nNewWidth, (int) nNewHeight, BufferedImage.TYPE_INT_RGB);

                /*
                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢。
                 */
                objTag.getGraphics().drawImage(objImg.getScaledInstance(nNewWidth, nNewHeight, Image.SCALE_SMOOTH), 0, 0, null);
                objOut = new FileOutputStream(strOutPutDir + strOutPutFileName);
                objEncoder = JPEGCodec.createJPEGEncoder(objOut);

                objEncoder.encode(objTag);
                objOut.close();
            }
        }
        catch (IOException ex)
        {
            logger.info("压缩图片出异常......");
        }
        return "ok";
    }

    /**
     * 图像切割（改）。
     * 
     * @param strSrcImageFile 源图像地址
     * @param strDirImageFile 新图像地址
     * @param nX1 目标切片起点x坐标
     * @param nY1 目标切片起点y坐标
     * @param nDestWidth 目标切片宽度
     * @param nDestHeight 目标切片高度
     */
    public static String abscut(String strSrcImageFile, String strDirImageFile, int nX1, int nY1, int nDestWidth, int nDestHeight)
    {
        String strSuccess = "N";

        try
        {
            Image objImg;
            ImageFilter objCropFilter;
            File objImageFile = new File(strSrcImageFile);
            String strExtensionName = getFileExtension(objImageFile.getName());// 文件扩展名
            BufferedImage objBi = ImageIO.read(objImageFile);// 读取源图像
            int nSrcWidth = objBi.getWidth(); // 源图宽度
            int nSrcHeight = objBi.getHeight(); // 源图高度
            Image objImage;
            BufferedImage objTag;
            Graphics objG;
            File objFile;

            if (nSrcWidth < nDestWidth || nSrcHeight < nDestHeight)
            {
                nDestWidth = nSrcWidth;
                nDestHeight = nSrcHeight;
                nX1 = 0;
                nY1 = 0;
            }
            objImage = objBi.getScaledInstance(nSrcWidth, nSrcHeight, Image.SCALE_DEFAULT);

            // 改进的想法:是否可用多线程加快切割速度
            // 四个参数分别为图像起点坐标和宽高
            // 即: CropImageFilter(int x,int y,int width,int height)
            objCropFilter = new CropImageFilter(nX1, nY1, nDestWidth, nDestHeight);
            objImg = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(objImage.getSource(), objCropFilter));
            objTag = new BufferedImage(nDestWidth, nDestHeight, BufferedImage.TYPE_INT_RGB);
            objG = objTag.getGraphics();

            objG.drawImage(objImg, 0, 0, null); // 绘制缩小后的图
            objG.dispose();
            objFile = new File(strDirImageFile);

            if (!objFile.exists())
            {
                objFile.mkdir();
            }
            if (strExtensionName.equalsIgnoreCase("jpg") || strExtensionName.equalsIgnoreCase("JPEG"))
            {
                ImageIO.write(objTag, "JPEG", objFile);
            }
            else if (strExtensionName.equalsIgnoreCase("gif"))
            {
                ImageIO.write(objTag, "GIF", objFile);
            }

            if (objImageFile.exists())
            {
                // objImageFile.delete();
            }

            strSuccess = "Y";

        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return strSuccess;
    }

    /**
     * 缩放图像。
     * 
     * @param strSrcImageFile 源图像文件地址
     * @param strResult 缩放后的图像地址
     * @param nScale 缩放比例
     * @param bFlag 缩放选择:true 放大; false 缩小;
     */
    public static void scale(String strSrcImageFile, String strResult, int nScale, boolean bFlag)
    {
        try
        {
            File objImageFile = new File(strSrcImageFile);
            String strExtensionName = getFileExtension(objImageFile.getName());// 文件扩展名
            BufferedImage objSrc = ImageIO.read(objImageFile); // 读入文件
            int nWidth = objSrc.getWidth(); // 得到源图宽
            int nHeight = objSrc.getHeight(); // 得到源图长
            Image objImage;
            BufferedImage objTag;
            Graphics objG;
            File objFile;

            if (bFlag)
            {
                // 放大
                nWidth = nWidth * nScale;
                nHeight = nHeight * nScale;
            }
            else
            {
                // 缩小
                nWidth = nWidth / nScale;
                nHeight = nHeight / nScale;
            }
            objImage = objSrc.getScaledInstance(nWidth, nHeight, Image.SCALE_DEFAULT);
            objTag = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
            objG = objTag.getGraphics();
            objG.drawImage(objImage, 0, 0, null); // 绘制缩小后的图
            objG.dispose();
            objFile = new File(strResult);

            if (!objFile.exists())
            {
                objFile.mkdir();
            }
            if (strExtensionName.equalsIgnoreCase("jpg") || strExtensionName.equalsIgnoreCase("JPEG"))
            {
                ImageIO.write(objTag, "JPEG", objFile);
            }
            else if (strExtensionName.equalsIgnoreCase("gif"))
            {
                ImageIO.write(objTag, "GIF", objFile);
            }
        }
        catch (IOException e)
        {
            logger.error("", e);
        }
    }

    /**
     * 重新生成按指定宽度和高度的图像。
     * 
     * @param strSrcImageFile 源图像文件地址
     * @param strResult 新的图像地址
     * @param nWidth 设置新的图像宽度
     * @param nHeight 设置新的图像高度
     */
    public static void scale(String strSrcImageFile, String strResult, int nWidth, int nHeight)
    {
        scale(strSrcImageFile, strResult, nWidth, nHeight, 0, 0);
    }

    /**
     * 图片转换。
     * 
     * @param strSrcImageFile
     * @param strResult
     * @param nWidth
     * @param nHeight
     * @param nX1
     * @param nY1
     */
    public static void scale(String strSrcImageFile, String strResult, int nWidth, int nHeight, int nX1, int nY1)
    {
        try
        {
            File objImageFile = new File(strSrcImageFile);
            String strExtensionName = getFileExtension(objImageFile.getName());// 文件扩展名
            BufferedImage objSrc = ImageIO.read(objImageFile); // 读入文件
            int nWidth1 = objSrc.getWidth(); // 得到源图宽
            int nHeight1 = objSrc.getHeight(); // 得到源图长
            Image objImage;
            BufferedImage objTag;
            Graphics objG;
            File objFile;

            if (nWidth1 > nWidth)
            {
                nWidth1 = nWidth;
            }
            if (nHeight1 > nHeight)
            {
                nHeight1 = nHeight;
            }
            objImage = objSrc.getScaledInstance(nWidth1, nHeight1, Image.SCALE_DEFAULT);
            objTag = new BufferedImage(nWidth1, nHeight1, BufferedImage.TYPE_INT_RGB);
            objG = objTag.getGraphics();

            objG.drawImage(objImage, nX1, nY1, null); // 绘制缩小后的图
            objG.dispose();

            objFile = new File(strResult);// 输出为文件

            if (!objFile.exists())
            {
                objFile.mkdir();
            }
            // 输出到文件流
            if (strExtensionName.equalsIgnoreCase("jpg") || strExtensionName.equalsIgnoreCase("JPEG"))
            {
                ImageIO.write(objTag, "JPEG", objFile);
            }
            else if (strExtensionName.equalsIgnoreCase("gif"))
            {
                ImageIO.write(objTag, "GIF", objFile);
            }
        }
        catch (IOException e)
        {
            logger.error("", e);
        }
    }

    /**
     * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)。
     */
    public static void convert(String strSource, String strResult)
    {
        try
        {
            File objFile = new File(strSource);
            BufferedImage objSrc;
            File objFile2;

            objFile.canRead();
            objFile.canWrite();

            objSrc = ImageIO.read(objFile);
            objFile2 = new File(strResult);

            if (!objFile.exists())
            {
                objFile2.mkdir();
            }
            ImageIO.write(objSrc, "JPG", objFile2);
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
    }

    /**
     * 彩色转为黑白。
     * 
     * @param strSource
     * @param strResult
     */
    public static void gray(String strSource, String strResult)
    {
        try
        {
            BufferedImage objSrc = ImageIO.read(new File(strSource));
            ColorSpace objCs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp objOp = new ColorConvertOp(objCs, null);

            objSrc = objOp.filter(objSrc, null);
            ImageIO.write(objSrc, "JPEG", new File(strResult));
        }
        catch (IOException e)
        {
            logger.error("", e);
        }
    }

    /**
     * 获取文件的扩展名。
     * 
     * @param strPath 文件路径
     */
    public static String getFileExtension(String strPath)
    {
        String strExt = "";
        int nMark;

        if (strPath == null)
        {
            throw new NullPointerException("传入参数不能为空！");
        }
        nMark = strPath.lastIndexOf(".");

        if (nMark != -1)
        {
            strExt = strPath.trim().substring(nMark + 1);
        }
        if (strExt.equals(""))
        {
            throw new IllegalArgumentException(strPath + " 传入参数格式不合法！");
        }

        return strExt;
    }

    /**
     * 取得图片的BASE64 编码形式:--- 。 1:---用于FreeMaker向Word中嵌入图片 。
     * 
     * @param strImgFilePath 图片保存的路径。
     * 
     * @return 经过base64编码的值。
     */
    public static String getImageStr(String strImgFilePath)
    {
        InputStream objIn = null;
        BASE64Encoder objEncoder = new BASE64Encoder();
        byte[] arrayData = null;

        try
        {
            objIn = getUrlImg(strImgFilePath);
            arrayData = new byte[objIn.available()];
            objIn.read(arrayData);
            objIn.close();
        }
        catch (Exception e)
        {
            logger.error(e.toString());
        }

        // 返回结果
        return objEncoder.encode(arrayData);
    }

    /**
     * 根据url地址，从网络上取得图片。
     * 
     * @Title: getUrlImg
     * @author YangZhenhua
     * @Date 2013-8-23 上午11:44:06
     */
    private static InputStream getUrlImg(String strURLName) throws Exception
    {
        InputStream objInputStream;
        int nResult = 0; // 服务器返回的状态
        URL objUrl = new URL(strURLName); // 创建URL
        HttpURLConnection objUrlconn = (HttpURLConnection) objUrl.openConnection(); // 试图连接并取得返回状态码urlconn.connect();

        objUrlconn.setRequestMethod("POST");
        objUrlconn.setReadTimeout(1000 * 5);
        nResult = objUrlconn.getResponseCode();
        logger.info("取得图片时返回的状态码：" + nResult);

        // 不等于HTTP_OK说明连接不成功("fail");
        if (nResult != HttpURLConnection.HTTP_OK)
        {
            logger.info("取得图片时出错");
            return null;
        }

        objInputStream = objUrlconn.getInputStream();
        if (null != objInputStream)
        {
            // 返回结果
            return objInputStream;
        }

        // 返回结果
        return null;
    }

    /**
     * 取得图片信息，包括：RGB颜色、长度、高度、大小、拓展名，以Map方式存储。
     * 
     * @Title:        getPicInfomation  
     * @author        YangZhenhua
     * @Date          2013-8-27 下午02:31:39
     */
    public static Map<String, Object> getPicInfomation(String strPicturePath)
    {
        return getPicInfomation(new File(strPicturePath));
    }

    /**
     * 取得图片信息，包括：RGB颜色、长度、高度、大小、拓展名，以Map方式存储。
     * 
     * @Title:        getPicInfomation  
     * @author        YangZhenhua
     * @Date          2013-8-27 下午02:31:52
     */
    public static Map<String, Object> getPicInfomation(File objImgfile)
    {
        BufferedImage objBuff;
        int nRgb;
        Map<String, Object> mapPicureInfo = new HashMap<String, Object>();

        try
        {
            objBuff = ImageIO.read(objImgfile);
            nRgb = objBuff.getRGB(10, 10);

            // 根据int类型的rgb值，取得每个：R、G、B的值。
            mapPicureInfo = getSinggelRGB(nRgb);

            mapPicureInfo.put("RGB", nRgb);
            mapPicureInfo.put("width", objBuff.getWidth());
            mapPicureInfo.put("height", objBuff.getHeight());

            // File.length取得的是byte字节，除以1024，得到KB
            mapPicureInfo.put("length", Math.round(objImgfile.length() / (1024.0)));
            mapPicureInfo.put("filePath", objImgfile.getPath());
            mapPicureInfo.put("fileName", objImgfile.getName());
            mapPicureInfo.put("extendName", getFileExtension(objImgfile.getName()));

        }
        catch (Exception objException)
        {
            logger.info("所给的图片文件" + objImgfile.getPath() + "不存在,或者图片格式非法！");
            mapPicureInfo = null;
        }

        logger.info("filePath: " + mapPicureInfo.get("filePath"));
        logger.info("RGB: " + mapPicureInfo.get("RGB"));
        logger.info("red: " + mapPicureInfo.get("red"));
        logger.info("green: " + mapPicureInfo.get("green"));
        logger.info("blue: " + mapPicureInfo.get("blue"));
        logger.info("width: " + mapPicureInfo.get("width"));
        logger.info("height: " + mapPicureInfo.get("height"));
        logger.info("length: " + mapPicureInfo.get("length") + "KB");
        logger.info("=====================================");

        return mapPicureInfo;
    }

    /**
     * 判断是否是图片，这里只是简单的通过拓展名判断。
     * 
     * @Title: isPicture
     * @author YangZhenhua
     * @Date 2013-8-23 上午11:31:58
     */
    public static boolean isPicture(String strExtendName)
    {
        // 声明图片后缀名数组
        String[] arrayImgeNames =
        { "bmp", "dib", "gif", "jfif", "jpe", "jpeg", "jpg", "png", "tif", "tiff", "ico", };
        boolean bFlag = false;

        for (String strExt : arrayImgeNames)
        {
            if (strExtendName.equalsIgnoreCase(strExt))
            {
                bFlag = true;
                break;
            }
        }
        return bFlag;
    }

    /**
     * 根据int类型的rgb值，取得每个：R、G、B的值。
     * 
     * @Title: getSinggelRGB
     * @author YangZhenhua
     * @Date 2013-8-23 上午11:31:48
     */
    public static Map<String, Object> getSinggelRGB(int nRgb)
    {
        Map<String, Object> mapInfo = new HashMap<String, Object>();
        int nRed;
        int nGreen;
        int nBlue;

        nRed = (nRgb & 0xff0000) >> 16;
        nGreen = (nRgb & 0xff00) >> 8;
        nBlue = (nRgb & 0xff);

        mapInfo.put("red", nRed);
        mapInfo.put("green", nGreen);
        mapInfo.put("blue", nBlue);

        return mapInfo;
    }

    /**
     * 根据每个：R、G、B的值，取得int类型的rgb值。
     * 
     * @Title: getRGBFromSinggelRGB
     * @author YangZhenhua
     * @Date 2013-8-23 上午11:31:38
     */
    public static int getRGBFromSinggelRGB(int nRed, int nGreen, int nBlue)
    {
        int nRGB = ((nRed * 256) + nGreen) * 256 + nBlue;

        if (nRGB > 8388608)
        {
            nRGB = nRGB - 16777216;
        }
        return nRGB;
    }

    /**
     * Just for testing 。
     * 
     * @param arrayArgs
     */
    public static void main(String[] args)
    {
        String strInputPath = "C:/test.jpg";
        String strOutPath = "C:/testqqqqqqqqqq.jpg";
        boolean bFlag = false;

//        Map<String, Object> mapInfo = getPicInfomation(strInputPath);
//
//        for (Map.Entry<String, Object> objEntry : mapInfo.entrySet())
//        {
//            logger.info(objEntry.getKey() + ":" + objEntry.getValue());
//        }

        bFlag = compressPic(strInputPath, strOutPath, 306, 378, true);

        logger.info("Result: " + bFlag);
    }

}
