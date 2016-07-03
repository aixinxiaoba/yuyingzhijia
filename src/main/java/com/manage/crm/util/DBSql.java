package com.manage.crm.util;

/**
 * 心情。
 * 
 * @author wei
 *
 */
public class DBSql
{
	public static String getNewsColumnWithOutContent()
    {
    	StringBuffer sbuf = new StringBuffer();
    	
    	sbuf.append("  `ID`,");
    	sbuf.append("  `MID`,");
    	sbuf.append("  `PID`,");
    	sbuf.append("  `UID`,");
    	sbuf.append("  `title`,");
    	sbuf.append(" '' as NContent,");
    	sbuf.append("  `type`,");
    	sbuf.append("  `sendDate`,");
    	sbuf.append("  `stick`,");
    	sbuf.append("  `reserver1`,");
    	sbuf.append("  `reserver2`,");
    	sbuf.append("  `reserver3`,");
    	sbuf.append("  `reserver4`,");
    	sbuf.append("  `summary`,");
    	sbuf.append("  `readnum`,");
    	sbuf.append("  `addressFrom`,");
    	sbuf.append("  `sendStatus`,");
    	sbuf.append("  `shortURL`,");
    	sbuf.append("  `staticFlag`,");
    	sbuf.append("  `imageurl` ");
    	
    	return sbuf.toString();
    }
	
	public static String getNewsColumnWithOutReadNum()
    {
    	StringBuffer sbuf = new StringBuffer();
    	
    	sbuf.append("  `ID`,");
    	sbuf.append("  `MID`,");
    	sbuf.append("  `PID`,");
    	sbuf.append("  `UID`,");
    	sbuf.append("  `title`,");
    	sbuf.append(" '' as NContent,");
    	sbuf.append("  `type`,");
    	sbuf.append("  `sendDate`,");
    	sbuf.append("  `stick`,");
    	sbuf.append("  `reserver1`,");
    	sbuf.append("  `reserver2`,");
    	sbuf.append("  `reserver3`,");
    	sbuf.append("  `reserver4`,");
    	sbuf.append("  `summary`,");
    	sbuf.append("  `addressFrom`,");
    	sbuf.append("  `sendStatus`,");
    	sbuf.append("  `shortURL`,");
    	sbuf.append("  `staticFlag`,");
    	sbuf.append("  `imageurl` ");
    	return sbuf.toString();
    }
	
	public static String getNewsColumnWithOutContentOne()
    {
    	StringBuffer sbuf = new StringBuffer();
    	
    	sbuf.append("  a.ID,");
    	sbuf.append("  a.MID,");
    	sbuf.append("  a.PID,");
    	sbuf.append("  a.UID,");
    	sbuf.append("  a.title,");
    	sbuf.append("  '' as NContent,");
    	sbuf.append("  a.type,");
    	sbuf.append("  a.sendDate,");
    	sbuf.append("  a.stick,");
    	sbuf.append("  a.reserver1,");
    	sbuf.append("  a.reserver2,");
    	sbuf.append("  a.reserver3,");
    	sbuf.append("  a.reserver4,");
    	sbuf.append("  a.summary,");
    	sbuf.append("  a.readnum,");
    	sbuf.append("  a.addressFrom,");
    	sbuf.append("  a.sendStatus,");
    	sbuf.append("  a.shortURL,");
    	sbuf.append("  a.staticFlag,");
    	sbuf.append("  a.imageurl ");
    	return sbuf.toString();
    }
	
    private static String getNewsTempSqlWithOutContent()
    {
    	StringBuffer sbuf = new StringBuffer();
    	
    	sbuf.append("SELECT ");
    	sbuf.append("  `ID`,");
    	sbuf.append("  `MID`,");
    	sbuf.append("  `PID`,");
    	sbuf.append("  `UID`,");
    	sbuf.append("  `title`,");
    	sbuf.append("  '' as NContent,");
    	sbuf.append("  `type`,");
    	sbuf.append("  `sendDate`,");
    	sbuf.append("  `stick`,");
    	sbuf.append("  `reserver1`,");
    	sbuf.append("  `reserver2`,");
    	sbuf.append("  `reserver3`,");
    	sbuf.append("  `reserver4`,");
    	sbuf.append("  `summary`,");
    	sbuf.append("  `readnum`,");
    	sbuf.append("  `addressFrom`,");
    	sbuf.append("  `sendStatus`,");
    	sbuf.append("  `shortURL`,");
    	sbuf.append("  `staticFlag`,");
    	sbuf.append("  `imageurl` ");
    	sbuf.append("FROM");
    	sbuf.append("  newstemp s  ");
    	return sbuf.toString();
    }
    
    
}
