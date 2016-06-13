package com.manage.crm.service.impl;

import com.manage.crm.dao.EmailDao;
import com.manage.crm.entity.Email;
import com.manage.crm.service.EmailService;
import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("emailService")
@Transactional
public class EmailServiceImpl extends BaseServiceImpl<Email>
  implements EmailService
{
  private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

  @Resource(name="emailDao")
  private EmailDao objEmailDao;

  public BaseDao<Email> getBaseDao()
  {
    return this.objEmailDao;
  }
}