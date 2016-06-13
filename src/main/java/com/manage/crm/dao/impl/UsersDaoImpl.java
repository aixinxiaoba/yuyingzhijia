package com.manage.crm.dao.impl;

import com.manage.crm.dao.UsersDao;
import com.manage.crm.entity.Users;
import javacommon.core.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("usersDao")
public class UsersDaoImpl extends BaseDaoImpl<Users>
  implements UsersDao
{
}

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.dao.impl.UsersDaoImpl
 * JD-Core Version:    0.6.1
 */