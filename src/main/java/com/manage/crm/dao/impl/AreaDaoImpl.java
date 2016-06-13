/*    */ package com.manage.crm.dao.impl;
/*    */ 
/*    */ import com.manage.crm.dao.AreaDao;
/*    */ import com.manage.crm.entity.Area;
/*    */ import javacommon.core.base.BaseDaoImpl;
/*    */ import javacommon.util.StringUtils;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("areaDao")
/*    */ public class AreaDaoImpl extends BaseDaoImpl<Area>
/*    */   implements AreaDao
/*    */ {
/*    */   public Area getAreaByName(String strName)
/*    */   {
/* 23 */     String strAreaName = "";
/*    */ 
/* 27 */     if (StringUtils.isEmpty(strName))
/*    */     {
/* 29 */       return null;
/*    */     }
/*    */ 
/* 33 */     if (strName.endsWith("省"))
/*    */     {
/* 35 */       strAreaName = strName.trim();
/*    */     }
/* 37 */     else if (strName.endsWith("市"))
/*    */     {
/* 39 */       if (strName.indexOf("省") > 0)
/*    */       {
/* 41 */         strAreaName = strName.substring(strName.indexOf("省") + 1);
/*    */       }
/*    */       else
/*    */       {
/* 45 */         strAreaName = strName.trim();
/*    */       }
/*    */ 
/*    */     }
/* 50 */     else if (strName.indexOf("市") > 0)
/*    */     {
/* 52 */       strAreaName = strName.substring(strName.indexOf("市") + 1);
/*    */     }
/*    */     else
/*    */     {
/* 56 */       strAreaName = strName.trim();
/*    */     }
/*    */ 
/* 60 */     Area objAreaTem = new Area();
/* 61 */     objAreaTem.setStrName(strAreaName);
/* 62 */     objAreaTem = (Area)getRecordByProps(objAreaTem, "strName");
/*    */ 
/* 65 */     return objAreaTem;
/*    */   }
/*    */ 
/*    */   public Area getPrivinceArea(Area objArea)
/*    */   {
/* 77 */     Area objUpArea = objArea.getObjParentArea();
/*    */ 
/* 80 */     if ((objUpArea == null) || (objUpArea.getLId().longValue() <= 0L))
/*    */     {
/* 82 */       return objArea;
/*    */     }
/*    */ 
/* 86 */     return getPrivinceArea(objUpArea);
/*    */   }
/*    */ }

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.dao.impl.AreaDaoImpl
 * JD-Core Version:    0.6.1
 */