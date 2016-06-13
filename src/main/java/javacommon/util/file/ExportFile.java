package javacommon.util.file;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

/**
 * CVS ,PDF, Excel文件导出工具类。
 * 
 * @author wangzg
 * 
 */
public class ExportFile
{
	/**
	 * 日志对象。
	 */
	private static final Logger logger = LoggerFactory.getLogger(ExportFile.class);

	/**
	 * 文件类型: cvs。
	 */
	public static final String FILE_TYPE_CVS = "cvs";

	/**
	 * 文件类型: csv。
	 */
	public static final String FILE_TYPE_CSV = "csv";

	/**
	 * 文件类型: pdf。
	 */
	public static final String FILE_TYPE_PDF = "pdf";

	/**
	 * 文件类型: xls。
	 */
	public static final String FILE_TYPE_XLS = "xls";

	/**
	 * PDF文件最大支持的数据数。
	 */
	private static final int PDF_MAX_SIZE = 8192;

	/**
	 * 生成需要导出的文件 。
	 * 
	 * @param strFileType 文件类型 cvs pdf xls 三种
	 * @param strFilePath 生成文件的路径
	 * @param lstData 数据集合(此数据集合里的数据将被导出到文件中) 集合为list套map的结构，一个map表示一行数据
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空,为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean exportFile(String strFileType, String strFilePath, List<Map<String, Object>> lstData, List lstHeadList, String strTitle, String strFootText)
	{
		try
		{
			File objFile = new File(strFilePath);
			OutputStream objOut;
			
			if (!objFile.exists())
			{
				objFile.createNewFile();
			}

			objOut = new FileOutputStream(objFile);

			// 返回结果
			return export(objOut, lstData, lstHeadList, strTitle, strFootText, strFileType);
		}
		catch (Exception e)
		{
			logger.error("", e);
			return false;
		}

	}

	/**
	 * 生成需要导出的文件 。
	 * 
	 * @param objOut 文件输出流
	 * @param lstData 数据集合(此数据集合里的数据将被导出到文件中) 集合为list套map的结构，一个map表示一行数据
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空,为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @param strFileType 文件类型 cvs pdf xls 三种
	 * @return true/false 导出成功返回true 导出失败返回false
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static boolean export(OutputStream objOut, List lstData, List lstHeadList, String strTitle, String strFootText, String strFileType) throws Exception
	{
		// 处理表头中的空值的情况
		lstHeadList = processNullValue(lstHeadList);

		// 根据文件类型决定生成什么类型的文件
		if (strFileType.toLowerCase().equals(FILE_TYPE_CVS))
		{
			return exportTocvs(objOut, lstData, lstHeadList, strTitle, strFootText);
		}
		else if (strFileType.toLowerCase().equals(FILE_TYPE_PDF))
		{
			return exportToPDF(objOut, lstData, lstHeadList, strTitle, strFootText);
		}
		else if (strFileType.toLowerCase().equals(FILE_TYPE_XLS))
		{
			return exportToXLS(objOut, lstData, lstHeadList, strTitle, strFootText);
		}
		else if (strFileType.toLowerCase().equals(FILE_TYPE_CSV))
		{
			return exportToCSV(objOut, lstData, lstHeadList, strTitle, strFootText);
		}

		// 如果没成功，则返回失败
		return false;
	}

	/**
	 * 将数据导出到cvs文件中。
	 * 
	 * @param objOut 文件输出流
	 * @param lstData 数据集合(此数据集合里的数据将被导出到文件中) 集合为list套map的结构，一个map表示一行数据
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空，为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @return 导出成功返回true 导出失败返回false
	 * @throws Exception
	 */
	private static boolean exportTocvs(OutputStream objOut, List<Map<String, Object>> lstData, List<Map<String, Object>> lstHeadList, String strTitle, String strFootText) throws Exception
	{
		// 验证参数
		if (lstData == null || lstData.size() == 0)
		{
			throw new Exception("数据集合为空");
		}

		if (lstHeadList == null || lstHeadList.size() == 0)
		{
			throw new Exception("表头为空");
		}

		if (objOut == null)
		{
			throw new Exception("文件输出流为空");
		}

		// 开始生成文件
		try
		{
			BufferedWriter objBw = new BufferedWriter(new OutputStreamWriter(objOut));
			String[] arrayKeys;
			
			if (strTitle != null)
			{
				objBw.write(strTitle);// 添加标题
				objBw.newLine();
			}

			// 存放表头列名
			arrayKeys = new String[lstHeadList.size()];
			
			for (int i = 0; i < lstHeadList.size(); i++)
			{
				Map<String, Object> mapMap = (Map<String, Object>) lstHeadList.get(i);
				Set<String> lstKeySet = mapMap.keySet();
				Iterator<String> iterIt = lstKeySet.iterator();
				String strKey = iterIt.next().toString();

				// Head中的key要和Data中的Key一致
				arrayKeys[i] = strKey;
				
				if (!"".equals(mapMap.get(strKey).toString()))
				{
					objBw.write(mapMap.get(strKey).toString());
				}
				else
				{
					objBw.write(strKey.toString());
				}

				// 表头和数据都用","隔开
				if (i < lstHeadList.size() - 1)
				{
					objBw.write(",");
				}
			}

			// 开始写数据行
			objBw.newLine();
			for (int i = 0; i < lstData.size(); i++)
			{
				Map<String, Object> mapMap = (Map<String, Object>) lstData.get(i);

				// 先处理空值，避免出现空指针异常
				mapMap = processNullValue(mapMap);
				
				for (int j = 0; j < lstHeadList.size(); j++)
				{
					String strKey = arrayKeys[j];

					// 如果取不到值，默认写个空串
					String strVal = "";
					
					if (mapMap.get(strKey) != null)
					{
						strVal = mapMap.get(strKey).toString();
					}
					objBw.write(strVal.toString());

					// 表头和数据都用","隔开
					if (j < lstHeadList.size() - 1)
					{
						objBw.write(",");
					}
				}

				// 每个Map是一条数据： 占一行
				objBw.newLine();
			}

			// 添加尾部
			if (strFootText != null)
			{
				objBw.write(strFootText);
				objBw.newLine();
			}
			objBw.flush();
			objBw.close();
		}
		catch (Exception e)
		{
			logger.error("", e);
		}

		// 返回文件生成成功的状态
		return true;
	}

	/**
	 * 将数据导出到pdf文件中。
	 * 
	 * @param objOut 文件输出流
	 * @param lstData 数据集合(此数据集合里的数据将被导出到文件中) 集合为list套map的结构，一个map表示一行数据
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空，为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @return 导出成功返回true 导出失败返回false
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	private static boolean exportToPDF(OutputStream objOut, List<Map<String, Object>> lstData, List<Map<String, Object>> lstHeadList, String strTitle, String strFootText) throws Exception
	{
		Rectangle objRectPageSize;
		Document objDocument;
		BaseFont objBfChinese;
		
		// 验证参数
		if (lstData == null || lstData.size() == 0)
		{
			throw new Exception("数据集合为空");
		}

		if (lstHeadList == null || lstHeadList.size() == 0)
		{
			throw new Exception("表头为空");
		}

		if (objOut == null)
		{
			throw new Exception("文件输出流为空");
		}

		if (lstData.size() > PDF_MAX_SIZE)
		{
			throw new Exception("数据量太大");
		}

		objRectPageSize = new Rectangle(PageSize.A4);// 定义A4页面大小
		objRectPageSize = objRectPageSize.rotate();// 加上这句可以实现A4页面的横置
		objDocument = new Document(objRectPageSize, 50, 50, 50, 50);// //其余4个参数，设置了页面的4个边距

		try
		{
			Font objFontTitle;
			Font objFontHeader;
			Font objFontCell;
			Table objTable;
			
			// 分页写入，页大小16384，2的14方，8192，2的13方页大小
			int nPagesize;
			// 页数
			int nNum;
			// 余数
			int nYu;
			// 页内数据开始索引
			int nPagebegin;
			
			objBfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
			objFontTitle = new Font(objBfChinese, 16, Font.BOLD);
			objFontHeader = new Font(objBfChinese, 14, Font.BOLD);
			objFontCell = new Font(objBfChinese, 12, Font.NORMAL);
			
			PdfWriter.getInstance(objDocument, objOut);
			objDocument.open();

			// pdf文件的标题
			if (strTitle != null)
			{
				Paragraph objPa = new Paragraph(strTitle, objFontTitle);
				
				objPa.setAlignment(Paragraph.ALIGN_CENTER);
				objDocument.add(objPa);
			}

			objTable = new Table(lstHeadList.size());
			objTable.setAlignment(objTable.ALIGN_BASELINE);
			objTable.setPadding(2);
			objTable.setBorderColor(new Color(0, 0, 0));

			// 分页写入，页大小16384，2的14方，8192，2的13方页大小
			nPagesize = 8192;
			// 页数
			nNum = (lstData.size() >> 13) + 1;
			// 余数
			nYu = lstData.size() & 8191;
			// 页内数据开始索引
			nPagebegin = 0;
			
			for (int n = 0; n < nNum; n++)
			{
				// 添加表头
				String[] arrayKeys = new String[lstHeadList.size()];// 存放表头列名
				int nWidth = 10;
				int[] arrayWidth = new int[lstHeadList.size()];// 每一列的宽度集合
				
				for (int i = 0; i < lstHeadList.size(); i++)
				{
					Map<String, Object> mapMap = (Map<String, Object>) lstHeadList.get(i);
					Set<String> lstKeySet = mapMap.keySet();
					Iterator<String> iterIt = lstKeySet.iterator();
					String strKey = iterIt.next().toString();
					Cell objCell;
					String strCell = "";
					
					arrayKeys[i] = strKey;
					
					if (!"".equals(mapMap.get(strKey).toString()))
					{
						strCell = mapMap.get(strKey).toString();
					}
					else
					{
						strCell = strKey;
					}

					objCell = new Cell(new Paragraph(strCell, objFontHeader));
					objCell.setHorizontalAlignment(objCell.ALIGN_CENTER);// 设置水平居中
					objCell.setVerticalAlignment(objCell.ALIGN_TOP);// 设置垂直居中
					objCell.setBackgroundColor(new Color(167, 167, 167));// 设置背景色
					objCell.setRowspan(2);// 占两行
					objCell.setHeader(true);// 设置表头
					objCell.setBorderColor(new Color(0, 0, 0));
					objTable.addCell(objCell);
					
					arrayWidth[i] = strCell.length() * nWidth;
				}

				objTable.setAutoFillEmptyCells(true);
				objTable.endHeaders();

				// 添加内容数据
				objTable.setAlignment(objTable.ALIGN_BASELINE);
				objTable.setPadding(2);
				objTable.setBorderColor(new Color(0, 0, 0));
				// 页内数据开始索引
				nPagebegin = 8192 * n;
				// 页大小
				nPagesize = 8192;
				if ((nNum - n) == 1)
				{
					nPagesize = nYu;
				}

				for (int i = 0; i < nPagesize; i++)
				{
					Map<String, Object> mapMap = (Map<String, Object>) lstData.get(nPagebegin + i);

					// 处理空值： 避免出现空指针
					mapMap = processNullValue(mapMap);
					
					for (int j = 0; j < lstHeadList.size(); j++)
					{
						String strKey = arrayKeys[j];
						String strVal = "";
						Cell objCell;
						
						if (mapMap.get(strKey) != null)
						{
							strVal = mapMap.get(strKey).toString();
						}

						objCell = new Cell(new Paragraph(strVal, objFontCell));
						
						objCell.setHorizontalAlignment(objCell.ALIGN_CENTER);// 设置水平居中
						objCell.setBorderColor(new Color(0, 0, 0));
						// objCell.setNoWrap(true);
						

						// 添加到Table中
						objTable.addCell(objCell);
					}
				}

				if (strFootText != null && !"".equals(strFootText))
				{
					String[] arrayFootList = strFootText.split(";"); // 添加尾部信息:总计2500
					int nIndex = 0;
					
					for (int i = 0; i < arrayFootList.length; i++)
					{
						if (arrayFootList[i] != null && !"".equals(arrayFootList[i]) && !"总计".equals(arrayFootList[i]))
						{
							nIndex = i - 1;
							break;
						}
					}

					for (int j = 0; j < arrayFootList.length; j++)
					{
						Cell objCell = null;
						
						if (arrayFootList[j] == null)
						{
							arrayFootList[j] = "";
						}

						if (j == 0)
						{
							objCell = new Cell(new Paragraph(arrayFootList[j], objFontHeader));
							objCell.setBackgroundColor(new Color(167, 167, 167));// 设置背景色
							objCell.setColspan(nIndex + 1);
							
							j = nIndex;
						}
						else
						{
							objCell = new Cell(new Paragraph(arrayFootList[j], objFontCell));
						}
						
						objCell.setHorizontalAlignment(objCell.ALIGN_CENTER);// 设置水平居中
						objCell.setVerticalAlignment(objCell.ALIGN_TOP);// 设置垂直居中
						objCell.setHorizontalAlignment(objCell.ALIGN_CENTER);// 设置水平居中
						objCell.setBorderColor(new Color(0, 0, 0));
						// objCell.setNoWrap(true);

						objTable.addCell(objCell);
					}
				}

				// 把Table增加到Document对象中
				objDocument.add(objTable);
			}

			// 把文件写入硬盘
			objDocument.close();
		}
		catch (DocumentException e)
		{
			logger.error("", e);
			return false;
		}
		catch (IOException e)
		{
			logger.error("", e);
			return false;
		}
		finally
		{
			objOut.close();
			objDocument.close();
		}

		// 返回结果
		return true;
	}

	/**
	 * 将数据导出到excel文件中。 
	 * 
	 * @param objOut 文件输出流
	 * @param lstData 数据集合(此数据集合里的数据将被导出到文件中) 集合为list套map的结构，一个map表示一行数据
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空，为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @return 导出成功返回true 导出失败返回false
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	private static boolean exportToXLS(OutputStream objOut, List lstData, List lstHeadList, String strTitle, String strFootText) throws Exception
	{
		WritableWorkbook objWwb = null;
		
		// 验证参数
		if (lstData == null || lstData.size() == 0)
		{
			throw new Exception("数据集合为空");
		}

		if (lstHeadList == null || lstHeadList.size() == 0)
		{
			throw new Exception("表头为空");
		}

		if (objOut == null)
		{
			throw new Exception("文件输出流为空");
		}

		try
		{
			WritableFont objFont1;
			WritableCellFormat objFormat1;
			WritableFont objFont2;
			WritableCellFormat objFormat2;
			int nPagesize;// 写入数据，excel中单个sheet最大行数为65536，分页写入多个sheet，倒序写入 页大小
			int nNum;// 余数
			int nYu;
			
			objWwb = Workbook.createWorkbook(objOut);
			objFont1 = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
			objFormat1 = new WritableCellFormat(objFont1);
			objFormat1.setBackground(Colour.GREY_25_PERCENT);
			objFormat1.setAlignment(objFormat1.getAlignment().CENTRE);
			objFormat1.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);
			objFont2 = new WritableFont(WritableFont.TIMES, 12, WritableFont.NO_BOLD);
			objFormat2 = new WritableCellFormat(objFont2);
			objFormat2.setAlignment(objFormat2.getAlignment().CENTRE);
			objFormat2.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN);

			nPagesize = 65535;
			nNum = (lstData.size() >> 16) + 1;
			nYu = lstData.size() % 65535;

			// 每一页
			for (int n = nNum; n > 0; n--)
			{
				WritableSheet objWs = objWwb.createSheet("sheet" + n, 0);
				int nBeginRow = 0;
				String[] arrayKeys;// 存放表头列名
				Map mapWidthMap;
				float fWidth;
				int nPagebegin;
				
				// 增加标题
				if (strTitle != null)
				{
					objWs.mergeCells(0, 0, lstHeadList.size() - 1, 0);
					objWs.addCell(new Label(0, 0, strTitle, objFormat1));
					
					nBeginRow = 1;
				}

				// 添加表头，直接用key
				arrayKeys = new String[lstHeadList.size()];// 存放表头列名
				mapWidthMap = new TreeMap();
				fWidth = 1.5f;
				
				for (int i = 0; i < lstHeadList.size(); i++)
				{
					Map mapMap = (Map) lstHeadList.get(i);
					Set lstKeySet = mapMap.keySet();
					Iterator iterIt = lstKeySet.iterator();
					String strKey = iterIt.next().toString();
					String strCell = "";
					
					arrayKeys[i] = strKey;
					
					if (!"".equals(mapMap.get(strKey).toString()))
					{
						strCell = mapMap.get(strKey).toString();
					}
					else
					{
						strCell = strKey;
					}
					mapWidthMap.put(strKey, strCell.length() * 4);
					objWs.addCell(new Label(i, nBeginRow, strCell, objFormat1));
				}

				// 页开始处数据索引
				nPagebegin = 65535 * (n - 1);
				// 页大小
				nPagesize = nYu;
				if (nNum != n)
				{
					nPagesize = 65535;
				}

				// 添加数据
				for (int i = 0; i < nPagesize; i++)
				{
					Map mapVmap = (Map) lstData.get(nPagebegin + i);
					
					mapVmap = processNullValue(mapVmap);
					
					for (int j = 0; j < lstHeadList.size(); j++)
					{
						String strKey = arrayKeys[j];
						String strVal = "";
						
						if (mapVmap.get(strKey) != null)
						{
							strVal = mapVmap.get(strKey).toString();
						}
						if (Integer.parseInt(mapWidthMap.get(strKey).toString()) < strVal.length())
						{
							mapWidthMap.put(strKey, strVal.length());
						}

						objWs.addCell(new Label(j, i + (nBeginRow + 1), strVal, objFormat2));
					}
				}

				for (int j = 0; j < lstHeadList.size(); j++)
				{
					String strKey = arrayKeys[j];
					
					objWs.setColumnView(j, (int) (Integer.parseInt(mapWidthMap.get(strKey).toString()) * fWidth));
				}

				if (strFootText != null && !"".equals(strFootText))
				{
					String[] arrayFootList = strFootText.split(";");
					int nIndex = 0;
					int nRowIndex;
					
					for (int i = 0; i < arrayFootList.length; i++)
					{
						if (arrayFootList[i] != null && !"".equals(arrayFootList[i]) && !"总计".equals(arrayFootList[i]))
						{
							nIndex = i - 1;
							break;
						}
					}
					nRowIndex = objWs.getRows();
					
					for (int j = 0; j < arrayFootList.length; j++)
					{
						if (arrayFootList[j] == null)
						{
							arrayFootList[j] = " ";
						}
						if (j == 0)
						{
							objWs.mergeCells(j, nRowIndex, nIndex, nRowIndex);
							objWs.addCell(new Label(j, nRowIndex, arrayFootList[j], objFormat1));
							j = nIndex;
						}
						else
						{
							objWs.addCell(new Label(j, nRowIndex, arrayFootList[j], objFormat2));
						}

					}
				}
			}

			// 一定要一次性写入，不知道为什么
			objWwb.write();
		}
		catch (IOException e)
		{
			logger.error("", e);
			return false;
		}
		catch (RowsExceededException e)
		{
			logger.error("", e);
			return false;
		}
		catch (WriteException e)
		{
			logger.error("", e);
			return false;
		}
		finally
		{
			objWwb.close();
			objOut.close();
		}

		// 返回结果
		return true;
	}

	/**
	 * 将数据导出到csv文件中 。
	 * 
	 * @param objOut 文件输出流
	 * @param lstData 数据集合(此数据集合里的数据将被导出到文件中) 集合为list套map的结构，一个map表示一行数据
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空 为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @return 导出成功返回true 导出失败返回false
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static boolean exportToCSV(OutputStream objOut, List<Map<String, Object>> lstData, List lstHeadList, String strTitle, String strFootText)
	{
		try
		{
			BufferedWriter objBw = new BufferedWriter(new OutputStreamWriter(objOut));
			String[] arrayKeys = new String[lstHeadList.size()];// 存放表头列名
			
			if (strTitle != null)
			{
				objBw.write(strTitle);
				objBw.write("\r\n");
			}
			for (int i = 0; i < lstHeadList.size(); i++)
			{
				Map mapMap = (Map) lstHeadList.get(i);
				Set lstKeySet = mapMap.keySet();
				Iterator iterIt = lstKeySet.iterator();
				String strKey = iterIt.next().toString();
				
				arrayKeys[i] = strKey;
				
				if (!"".equals(mapMap.get(strKey).toString()))
				{
					objBw.write(mapMap.get(strKey).toString());
				}
				else
				{
					objBw.write(strKey.toString());
				}
				if (i < lstHeadList.size() - 1)
				{
					objBw.write(",");
				}
			}
			objBw.write("\r\n");

			// 添加内容数据
			for (int i = 0; i < lstData.size(); i++)
			{
				Map mapMap = (Map) lstData.get(i);
				
				mapMap = processNullValue(mapMap);
				
				for (int j = 0; j < lstHeadList.size(); j++)
				{
					String strKey = arrayKeys[j];
					String strVal = "";
					
					if (mapMap.get(strKey) != null)
					{
						strVal = mapMap.get(strKey).toString();
					}
					objBw.write(strVal.toString());
					if (j < lstHeadList.size() - 1)
					{
						objBw.write(",");
					}
				}
				objBw.write("\r\n");
			}
			if (strFootText != null)
			{
				objBw.write(strFootText);
			}
			objBw.flush();
			objBw.close();
		}
		catch (Exception e)
		{
			logger.error("", e);
			return false;
		}

		// 返回结果
		return true;
	}

	/**
	 * 根据指定的sql 从数据库中获取数据，并将数据导出到文件中 。
	 * 
	 * @param objOut 文件输出流
	 * @param objConn JDBC数据库连接
	 * @param bCloseConn 是否关闭JDBC数据库连接 true/false true 关闭 false 不关闭
	 * @param strSql 查询数据的sql语句
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空，为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @param strFileType 文件类型 cvs pdf xls 三种
	 * @return true/false 导出成功返回true 导出失败返回false
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean export(OutputStream objOut, Connection objConn, boolean bCloseConn, String strSql, List lstHeadList, String strTitle, String strFootText, String strFileType) throws Exception
	{
		// 处理空值： 避免出现空指针异常
		lstHeadList = processNullValue(lstHeadList);

		// 根据文件类型生成不同的文件
		if (strFileType.toLowerCase().equals(FILE_TYPE_CVS))
		{
			return exportTocvs(objOut, objConn, bCloseConn, strSql, lstHeadList, strTitle, strFootText);
		}
		else if (strFileType.toLowerCase().equals(FILE_TYPE_PDF))
		{
			return exportToPDF(objOut, objConn, bCloseConn, strSql, lstHeadList, strTitle, strFootText);
		}
		else if (strFileType.toLowerCase().equals(FILE_TYPE_XLS))
		{
			return exportToXLS(objOut, objConn, bCloseConn, strSql, lstHeadList, strTitle, strFootText);
		}

		// 返回结果
		return false;
	}

	/**
	 * 根据指定的sql 从数据库中获取数据，并将数据导出到cvs文件中 。
	 * 
	 * @param objOut 文件输出流
	 * @param objConn JDBC数据库连接
	 * @param bCloseConn 是否关闭JDBC数据库连接 true/false true 关闭 false 不关闭
	 * @param strSql 查询数据的sql语句
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空 ，为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static boolean exportTocvs(OutputStream objOut, Connection objConn, boolean bCloseConn, String strSql, List lstHeadList, String strTitle, String strFootText) throws Exception
	{
		List lstData = getDataFromDB(objConn, bCloseConn, strSql);

		// 返回结果
		return exportTocvs(objOut, lstData, lstHeadList, strTitle, strFootText);
	}

	/**
	 * 根据指定的sql 从数据库中获取数据，并将数据导出到xls文件中 。
	 * 
	 * @param objOut 文件输出流
	 * @param objConn JDBC数据库连接
	 * @param bCloseConn 是否关闭JDBC数据库连接 true/false true 关闭 false 不关闭
	 * @param strSql 查询数据的sql语句
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空 ，为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static boolean exportToXLS(OutputStream objOut, Connection objConn, boolean bCloseConn, String strSql, List lstHeadList, String strTitle, String strFootText) throws Exception
	{
		List lstData = getDataFromDB(objConn, bCloseConn, strSql);

		// 返回结果
		return exportToXLS(objOut, lstData, lstHeadList, strTitle, strFootText);
	}

	/**
	 * 根据指定的sql 从数据库中获取数据，并将数据导出到xls文件中 。
	 * 
	 * @param objOut 文件输出流
	 * @param objConn JDBC数据库连接
	 * @param bCloseConn 是否关闭JDBC数据库连接 true/false true 关闭 false 不关闭
	 * @param strSql 查询数据的sql语句
	 * @param lstHeadList 表头说明 生成文件时的中英文对照表头 ※表头必须传入 例如： key:id value:序列号
	 *            value值可以为空 ，为空时将不生成中文表头
	 * @param strTitle 标题
	 * @param strFootText 尾部 格式:总计; ; ; 2500; ; ;
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static boolean exportToPDF(OutputStream objOut, Connection objConn, boolean bCloseConn, String strSql, List lstHeadList, String strTitle, String strFootText) throws Exception
	{
		List lstData = getDataFromDB(objConn, bCloseConn, strSql);

		// 返回结果
		return exportToPDF(objOut, lstData, lstHeadList, strTitle, strFootText);
	}

	/**
	 * 从数据中查询获取数据 。
	 * 
	 * @param objConn JDBC数据库连接
	 * @param bCloseConn 是否关闭JDBC数据库连接 true/false true 关闭 false 不关闭
	 * @param strSql 查询数据的sql语句
	 * @return 查询获取的list结果集
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static List getDataFromDB(Connection objConn, boolean bCloseConn, String strSql) throws Exception
	{
		ResultSet objRs = null;
		Statement objState = null;
		List lstData = null;
		
		// 验证参数
		if (strSql == null || strSql.equals(""))
		{
			throw new Exception("查询的sql语句为空");
		}

		if (objConn == null)
		{
			throw new Exception("JDBC数据库连接为空");
		}

		try
		{
			objState = objConn.createStatement();
			objRs = objState.executeQuery(strSql);
			lstData = RStoList(objRs);
		}
		catch (SQLException e)
		{
			logger.error("", e);
			throw new SQLException("查询数据时出现异常");
		}
		finally
		{
			try
			{
				objRs.close();
				objState.close();
				if (bCloseConn)
				{
					objConn.close();
				}
			}
			catch (SQLException e)
			{
				logger.error("", e);
				throw new SQLException("关闭Statement和ResultSet时出现异常");
			}
		}

		// 返回结果
		return lstData;
	}

	/**
	 * 将ResultSet结果集转换为list套map的结构 。
	 * 
	 * @param objRs 连接数据库查询后生成的ResultSet结果集
	 * @return 转换后的list结果集
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static List RStoList(ResultSet objRs) throws Exception
	{
		List lstData = null;
		
		try
		{
			// 获得列名
			ResultSetMetaData objRsmd = objRs.getMetaData();
			int nLen = objRsmd.getColumnCount();
			String strColName = "";
			Object objObj = null;
			
			lstData = new ArrayList();
			
			while (objRs.next())
			{
				Map mapMmap = new HashMap();
				
				for (int i = 1; i <= nLen; i++)
				{
					strColName = objRsmd.getColumnName(i).toLowerCase();
					objObj = objRs.getObject(strColName);
					
					if (objObj != null)
					{
						mapMmap.put(strColName, objObj.toString());
					}
					else
					{
						mapMmap.put(strColName, "");
					}
				}

				// 每一行用一个Map
				lstData.add(mapMmap);
			}
		}
		catch (SQLException e)
		{
			logger.error("", e);
			throw new Exception("解析结果集时报错");
		}

		// 返回结果
		return lstData;
	}

	/**
	 * 过滤null值: 避免出现空指针异常。
	 * 
	 * @param lstHeadList
	 * @return
	 */
	private static List<Map<String, Object>> processNullValue(List<Map<String, Object>> lstHeadList)
	{
		List<Map<String, Object>> lstList = new ArrayList<Map<String, Object>>();
		
		for (int i = 0; i < lstHeadList.size(); i++)
		{
			Map<String, Object> mapMap = (Map<String, Object>) lstHeadList.get(i);
			Set<String> lstKeySet = mapMap.keySet();
			Iterator<String> iterIt = lstKeySet.iterator();
			// headList的size()代表有几列,每个Map就是一列，所以每个Map都应该只有一条数据： 这里也只处理第一条，默认当表头
			String strKey = iterIt.next().toString();
			
			if (mapMap.get(strKey) == null)
			{
				mapMap.put(strKey, "");
			}

			// 增加到新List中
			lstList.add(mapMap);
		}

		// 返回结果
		return lstList;
	}

	/**
	 * 过滤null值: 避免出现空指针异常。
	 * 
	 * @param mapMap
	 * @return Map
	 */
	private static Map<String, Object> processNullValue(Map<String, Object> mapMap)
	{
		Set<String> lstKeySet = mapMap.keySet();
		Map<String, Object> mapMapp = new LinkedHashMap<String, Object>();
		Iterator<String> iterIt = lstKeySet.iterator();
		
		while (iterIt.hasNext())
		{
			String strKey = iterIt.next().toString();
			String strValue = "";
			
			if (mapMapp.get(strKey) == null)
			{
				strValue = "";
			}
			else
			{
				strValue = mapMapp.get(strKey).toString();
			}

			// 保存到新Map中
			mapMapp.put(strKey, strValue);
		}

		// 返回结果
		return mapMapp;
	}

}