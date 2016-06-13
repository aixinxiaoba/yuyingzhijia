package com.manage.crm.dao.impl;

import com.manage.crm.dao.CustomerTypeDao;
import com.manage.crm.entity.CustomerType;
import javacommon.core.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("customerTypeDao")
public class CustomerTypeDaoImpl extends BaseDaoImpl<CustomerType>
  implements CustomerTypeDao
{
}

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.dao.impl.CustomerTypeDaoImpl
 * JD-Core Version:    0.6.1
 */