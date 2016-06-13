package com.manage.crm.dao.impl;

import javacommon.core.base.BaseDaoImpl;

import org.springframework.stereotype.Repository;

import com.manage.crm.dao.NewsTempDao;
import com.manage.crm.entity.NewsTemp;

@Repository("newsTempDao")
public class NewsTempDaoImpl extends BaseDaoImpl<NewsTemp>
  implements NewsTempDao
{
//	@Cacheable(value="accountCache")// 使用了一个缓存名叫 accountCache 
//	@Override
//	public News getById(Serializable objId) {
//		// TODO Auto-generated method stub
//		return super.getById(objId);
//	}
}