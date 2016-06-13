package com.manage.crm.dao.impl;

import com.manage.crm.dao.EmailDao;
import com.manage.crm.entity.Email;
import javacommon.core.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("emailDao")
public class EmailDaoImpl extends BaseDaoImpl<Email>
  implements EmailDao
{
}

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.dao.impl.EmailDaoImpl
 * JD-Core Version:    0.6.1
 */