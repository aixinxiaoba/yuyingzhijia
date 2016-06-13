package com.manage.crm.service;

import com.manage.crm.entity.FiledInfo;
import com.manage.crm.entity.Project;
import javacommon.core.base.BaseService;

public abstract interface FiledInfoService extends BaseService<FiledInfo>
{
  public abstract String activeOrForbidFiledInfos(Project paramProject, String[] paramArrayOfString, int paramInt1, int paramInt2);

  public abstract String activeFiledInfo(String[] paramArrayOfString, long paramLong);
}

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.service.FiledInfoService
 * JD-Core Version:    0.6.1
 */