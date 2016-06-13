package javacommon.util.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;

import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

/**
 * PDF工具类 。
 * 
 * @author wangzg
 *
 */
@SuppressWarnings({"unused" , "unchecked"})
public class PDFDocumentUtil
{
	/**
	 * 日志文件
	 */
	private static final Logger logger = Logger.getLogger(PDFDocumentUtil.class);

	/**
	 * 关联itext.jar中的PDF操作类
	 */
	private PdfStamper m_objPdfStamper;
	private AcroFields m_objAcroFields;

	private int m_nNumDefaultFontFile = -1;

	/**
	 * 打开模版 。
	 * 
	 * @param strTempPath
	 * @param strPDFFilePath
	 * @return
	 */
	public boolean openTemplate(String strTempPath, String strPDFFilePath)
	{
		// 要生成的文件
		File objLocalFile;
		HashMap<String , AcroFields.Item> mapAcroField;
		Set<String> lstKeySet;
		
		try
		{
			if ((strTempPath == null) || (strTempPath.trim().length() <= 0))
			{
				logger.info("没有指定模版文件。");
				return false;
			}

			if ((!new File(strTempPath).exists()) || (!strTempPath.endsWith(".pdf")))
			{
				logger.info("[PDF Document]模板路径 " + strTempPath + " 不存在。");
				return false;
			}
			
			if ((!(objLocalFile = new File(new File(strPDFFilePath).getParent())).exists()) || (!objLocalFile.isDirectory()))
			{
				objLocalFile.mkdirs();
			}
			logger.info("[PDF Document]模板路径：" + strTempPath + " 生成后路径：" + strPDFFilePath);

			// 初始化
			this.m_objPdfStamper = new PdfStamper(new PdfReader(strTempPath), new FileOutputStream(strPDFFilePath));
			this.m_objAcroFields = this.m_objPdfStamper.getAcroFields();

			// 看下结果
			mapAcroField = this.m_objAcroFields.getFields();

			// lstKeySet = mapAcroField.keySet();

			// for (String strKey : lstKeySet)
			// {
				// logger.info("Key:---" + strKey);
			// }
		}
		catch (Exception objLocalException)
		{
			logger.error("[PDF Document]打开模板：" + strTempPath + " 失败！", objLocalException);
			return false;
		}

		// 返回结果
		return true;
	}

	/**
	 * 更新模版中的值 。
	 * 
	 * @param strTempName
	 * @param strValue
	 * @return
	 */
	public boolean updateBookMark(String strTempName, String strValue)
	{
		boolean bIsSuccess = false;

		// if (this.m_nNumDefaultFontFile == -1)
		// {
		//  this.m_nNumDefaultFontFile = addDefaultFont();
		// }

		try
		{
			if ((this.m_objAcroFields != null) && (hasKey(strTempName)))
			{
				bIsSuccess = this.m_objAcroFields.setField(strTempName, strValue);
			}
			else
			{
				logger.info("[PDF Document]更新域 " + strTempName + " 不存在。");
			}
		}
		catch (Exception objLocalException)
		{
			logger.error("[PDF Document]更新指定域：" + strTempName + " 值：" + strValue + " 失败！", objLocalException);
		}

		// 返回结果
		return bIsSuccess;
	}

	/**
	 * 更新图片域 。
	 * 
	 * @param strTempName
	 * @param strValue
	 * @return
	 */
	public boolean updateImageBookMark(String strTempName, String strValue)
	{
		float fObj1;
		float fObj2;
		
		try
		{
			if (!new File(strValue).exists())
			{
				logger.info("[PDF Document]更新图片域" + strTempName + " 图片 [" + strValue + "] 不存在。");
			}
			else if ((this.m_objAcroFields != null) && (hasKey(strTempName)))
			{
				float[] arrayOfFloat = this.m_objAcroFields.getFieldPositions(strTempName);

				Rectangle objLocalRectangle = new Rectangle(arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3], arrayOfFloat[4]);

				Image objLocalImage;

				if ((objLocalImage = Image.getInstance(strValue)) == null)
				{
					logger.fatal("[PDF Document]更新图片域 " + strTempName + " 获取图片 " + strValue + " 失败");
					return false;
				}

				objLocalImage.scaleToFit(objLocalImage.getWidth(), objLocalRectangle.getHeight());

				fObj1 = arrayOfFloat[1] + (objLocalRectangle.getWidth() - objLocalImage.getScaledWidth()) / 2.0F;

				fObj2 = arrayOfFloat[2] + (objLocalRectangle.getHeight() - objLocalImage.getScaledHeight()) / 2.0F;
				objLocalImage.setAbsolutePosition(fObj1, fObj2);
				this.m_objPdfStamper.getOverContent((int) arrayOfFloat[0]).addImage(objLocalImage);
			}
			else
			{
				logger.info("[PDF Document]更新图片域 " + strTempName + " 不存在。");
			}
		}
		catch (Exception objLocalException)
		{
			logger.error("更新指定图片域" + strTempName + " 值：" + strValue + " 失败！异常信息：" + objLocalException.getMessage(), objLocalException);
		}

		// 返回结果
		return true;
	}

	/**
	 * 模版中是否有此名称 。
	 * 
	 * @param strTempName
	 * @return
	 */
	public boolean hasKey(String strTempName)
	{
		if ((strTempName != null) && (strTempName.length() > 0) && (this.m_objAcroFields != null))
		{
			if (((this.m_objAcroFields.getFields()) != null) && (this.m_objAcroFields.getFieldItem(strTempName.trim()) != null))
			{
				return true;
			}
		}

		// 返回结果
		return false;
	}

	/**
	 * 关闭PDF文件 。
	 * 
	 * @return
	 */
	public boolean close()
	{
		try
		{
			if (this.m_objPdfStamper != null)
			{
				this.m_objPdfStamper.setFormFlattening(true);
				this.m_objPdfStamper.close();
				this.m_objPdfStamper = null;
			}
		}
		catch (Exception objLocalException)
		{
			logger.fatal("关闭PDF异常。", objLocalException);
			return false;
		}

		// 返回结果
		return true;
	}

}
