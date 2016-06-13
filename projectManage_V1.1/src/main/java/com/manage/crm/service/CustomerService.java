package com.manage.crm.service;

import com.manage.crm.entity.Customer;
import com.manage.crm.util.Pagination;
import javacommon.core.base.BaseService;
import javacommon.util.SearchCondition;
import org.hibernate.criterion.Order;

public abstract interface CustomerService extends BaseService<Customer>
{
  public abstract void listCustomerByMultiCondition(Pagination<Customer> paramPagination, SearchCondition paramSearchCondition, Order paramOrder);

  public abstract String projectAuthorization(String[] paramArrayOfString1, String[] paramArrayOfString2);

  public abstract String cancleProjectAuthorization(String[] paramArrayOfString1, String[] paramArrayOfString2);

  public abstract String resetPwd(String[] paramArrayOfString);
}