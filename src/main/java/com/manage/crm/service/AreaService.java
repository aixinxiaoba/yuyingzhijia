package com.manage.crm.service;

import com.manage.crm.entity.Area;
import java.util.List;
import javacommon.core.base.BaseService;

public abstract interface AreaService extends BaseService<Area>
{
  public abstract List<Area> getProvinceArea();

  public abstract Area getAreaByName(String paramString);

  public abstract Area getPrivinceArea(Area paramArea);
}