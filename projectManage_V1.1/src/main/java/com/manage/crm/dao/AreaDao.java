package com.manage.crm.dao;

import com.manage.crm.entity.Area;
import javacommon.core.base.BaseDao;

public abstract interface AreaDao extends BaseDao<Area>
{
  public abstract Area getAreaByName(String paramString);

  public abstract Area getPrivinceArea(Area paramArea);
}

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.dao.AreaDao
 * JD-Core Version:    0.6.1
 */