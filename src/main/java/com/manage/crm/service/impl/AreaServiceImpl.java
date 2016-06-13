package com.manage.crm.service.impl;

import com.manage.crm.dao.AreaDao;
import com.manage.crm.entity.Area;
import com.manage.crm.service.AreaService;
import java.util.List;
import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javax.annotation.Resource;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("areaService")
@Transactional
public class AreaServiceImpl extends BaseServiceImpl<Area>
  implements AreaService
{
  private static final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

  @Resource(name="areaDao")
  private AreaDao objAreaDao;

  public BaseDao<Area> getBaseDao()
  {
    return this.objAreaDao;
  }

  public List<Area> getProvinceArea()
  {
    Area objArea = new Area();

    objArea.setlParId(-1L);
    return this.objAreaDao.listByProps(objArea, "lParId", Order.asc("lId"));
  }

  public Area getAreaByName(String strName)
  {
    return this.objAreaDao.getAreaByName(strName);
  }

  public Area getPrivinceArea(Area objArea)
  {
    return this.objAreaDao.getPrivinceArea(objArea);
  }
}