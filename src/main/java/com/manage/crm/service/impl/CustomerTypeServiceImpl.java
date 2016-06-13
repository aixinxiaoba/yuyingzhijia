package com.manage.crm.service.impl;

import com.manage.crm.dao.CustomerTypeDao;
import com.manage.crm.entity.CustomerType;
import com.manage.crm.service.CustomerTypeService;
import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerTypeService")
@Transactional
public class CustomerTypeServiceImpl extends BaseServiceImpl<CustomerType>
  implements CustomerTypeService
{
  private static final Logger logger = LoggerFactory.getLogger(CustomerTypeServiceImpl.class);

  @Resource(name="customerTypeDao")
  private CustomerTypeDao objCustomerTypeDao;

  public BaseDao<CustomerType> getBaseDao()
  {
    return this.objCustomerTypeDao;
  }
}