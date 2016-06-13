package com.manage.crm.dao.impl;

import com.manage.crm.dao.UserMessageDao;
import com.manage.crm.entity.UserMessage;
import javacommon.core.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("userMessageDao")
public class UserMessageDaoImpl extends BaseDaoImpl<UserMessage>
  implements UserMessageDao
{
}

/* Location:           E:\classes\
 * Qualified Name:     com.manage.crm.dao.impl.UserMessageDaoImpl
 * JD-Core Version:    0.6.1
 */