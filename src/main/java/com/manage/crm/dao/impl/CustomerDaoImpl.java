package com.manage.crm.dao.impl;

import com.manage.crm.dao.CustomerDao;
import com.manage.crm.entity.Customer;
import javacommon.core.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer>
  implements CustomerDao
{
}

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.dao.impl.CustomerDaoImpl
 * JD-Core Version:    0.6.1
 */