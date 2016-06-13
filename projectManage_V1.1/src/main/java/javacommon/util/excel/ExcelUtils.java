package javacommon.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Excel工具类 。
 * 
 * @author wangzg
 *
 */
public class ExcelUtils
{
	/**
     * 小数点后面 # 的个数代表转换模式后支持的小数位数
     */
    private static final String NUMBERFORMAT_DECIMAL = "#.####";
    
	/**
     * 拿到单元格中的值 。
     * 
     * @param objCell
     * @return
     */
    public static String getExcelCellData(HSSFCell objCell)
    {
        String strValue;

        if (objCell == null)
        {
            return "";
        }
        switch (objCell.getCellType())
        {
        case HSSFCell.CELL_TYPE_NUMERIC: // 0
        {
            // 日期型
            if (HSSFDateUtil.isCellDateFormatted(objCell))
            {
                strValue = new SimpleDateFormat("yyyy-MM-dd").format(objCell.getDateCellValue());
            }
            else
            {
                strValue = new DecimalFormat(NUMBERFORMAT_DECIMAL).format(objCell.getNumericCellValue());
            }
            break;
        }
        case HSSFCell.CELL_TYPE_STRING: // 1
            strValue = objCell.getRichStringCellValue().toString().replaceAll("'", "''");
            break;
        case HSSFCell.CELL_TYPE_FORMULA: // 2
            strValue = String.valueOf(objCell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK: // 3
            strValue = objCell.getRichStringCellValue().toString();
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN: // 4
            strValue = String.valueOf(objCell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_ERROR: // 5
            strValue = String.valueOf(objCell.getErrorCellValue());
            break;
        default:
            strValue = "";
        }

        // 去掉空格换行等
        strValue = strValue.replaceAll(" ", "").replaceAll("　", "").replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", "").replaceAll("\b", "").replaceAll("'", "").replaceAll("‘", "");

        // 返回结果
        return strValue;
    }
    
    /**
     * 判断Excel行是否为空。
     * @param objDataRow
     * @return
     */
    public static boolean isBlankRow(HSSFRow objDataRow)
    {
    	boolean bResult = true;
    	
        if (objDataRow == null)
        {
        	return true;
        }
        for (int i = objDataRow.getFirstCellNum(); i < objDataRow.getLastCellNum(); i++)
        {
            HSSFCell objCell = objDataRow.getCell(i, HSSFRow.RETURN_BLANK_AS_NULL);
            String strValue = "";
            
            if (objCell != null)
            {
                strValue = getExcelCellData(objCell);
                if (!strValue.trim().equals(""))
                {
                	bResult = false;
                    break;
                }
            }
        }
         
        return bResult;
    }
    
    /**
     * 复制excel行数据。
     * @param objHSSFSheetWrong
     * @param nFaileSum  新建行号
     * @param objDataRow 被复制行
     * @param nTotalColumn 列数
     */
    public static void copyRow(HSSFSheet objHSSFSheetWrong, int nFaileSum, HSSFRow objDataRow, int nTotalColumn)
	{
    	HSSFRow objHSSFRowAdd = objHSSFSheetWrong.createRow(nFaileSum);
    	
    	for (int i = 0; i < nTotalColumn; i++)
		{
    		HSSFCell objCell = objHSSFRowAdd.createCell(i);
    		
    		objCell.setCellValue(ExcelUtils.getExcelCellData(objDataRow.getCell(i)));
		}
		
	}
    
    public static void getExcel(Vector<Vector<String>> vector, String[] fieldName, String sheetName, OutputStream output)
    {
      HSSFWorkbook workbook = new HSSFWorkbook();

      HSSFSheet sheet = workbook.createSheet(sheetName);

      HSSFRow row = sheet.createRow(0);

      for (int i = 0; i < fieldName.length; i++)
      {
        HSSFCell cell = row.createCell(i);

        cell.setCellType(1);

        cell.setCellValue(fieldName[i]);
      }

      for (int i = 0; i < vector.size(); i++)
      {
        row = sheet.createRow(i + 1);
        Vector v = (Vector)vector.get(i);
        for (int j = 0; j < v.size(); j++)
        {
          HSSFCell cell = row.createCell(j);
          cell.setCellType(1);
          cell.setCellValue((String)v.get(j));
        }
      }
      try
      {
        output.flush();
        workbook.write(output);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.out.println("Output is closed");
      }
    }
}
