package com.manage.crm.service.impl;

import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.dao.MoodNewsRelaDao;
import com.manage.crm.entity.MoodNewsRela;
import com.manage.crm.service.MoodNewsRelaService;

@Service("moodNewsRelaService")
@Transactional
public class MoodNewsRelaServiceImpl extends BaseServiceImpl<MoodNewsRela>
  implements MoodNewsRelaService
{
  private static final Logger logger = LoggerFactory.getLogger(MoodNewsRelaServiceImpl.class);

  @Resource(name="moodNewsRelaDao")
  private MoodNewsRelaDao objMoodNewsRelaDao;

  public BaseDao<MoodNewsRela> getBaseDao()
  {
    return this.objMoodNewsRelaDao;
  }
}