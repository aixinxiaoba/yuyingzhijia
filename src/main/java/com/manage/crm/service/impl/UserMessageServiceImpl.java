package com.manage.crm.service.impl;

import com.manage.crm.dao.UserMessageDao;
import com.manage.crm.entity.UserMessage;
import com.manage.crm.service.UserMessageService;
import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userMessageService")
@Transactional
public class UserMessageServiceImpl extends BaseServiceImpl<UserMessage>
  implements UserMessageService
{
  private static final Logger logger = LoggerFactory.getLogger(UserMessageServiceImpl.class);

  @Resource(name="userMessageDao")
  private UserMessageDao objUserMessageDao;

  public BaseDao<UserMessage> getBaseDao()
  {
    return this.objUserMessageDao;
  }
}