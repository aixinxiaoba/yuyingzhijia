package com.manage.crm.service;

import com.manage.crm.entity.ProFiledInfo;
import javacommon.core.base.BaseService;

public abstract interface ProFiledInfoService extends BaseService<ProFiledInfo>
{
  public abstract String updateFiledInfoActiveStatus(Long paramLong, String[] paramArrayOfString, int paramInt);

  public abstract String updateFiledInfoNatureStatus(Long paramLong, String[] paramArrayOfString, int paramInt);

  public abstract String updateFiledInfoRegStatus(Long paramLong, String[] paramArrayOfString, int paramInt);

  public abstract String updateFiledInfoModifyStatus(Long paramLong, String[] paramArrayOfString, int paramInt);
}

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.service.ProFiledInfoService
 * JD-Core Version:    0.6.1
 */