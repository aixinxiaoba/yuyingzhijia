package com.manage.crm.service;

import com.manage.crm.entity.Project;
import com.manage.crm.entity.Users;
import javacommon.core.base.BaseService;

public abstract interface UsersService extends BaseService<Users>
{
  public abstract String cancleProjectAuthorization(String[] paramArrayOfString1, String[] paramArrayOfString2);

  public abstract String projectAuthorization(String[] paramArrayOfString1, String[] paramArrayOfString2);

  public abstract String passwordModify(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract String saveProject(Users paramUsers, Project paramProject);
}

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.service.UsersService
 * JD-Core Version:    0.6.1
 */