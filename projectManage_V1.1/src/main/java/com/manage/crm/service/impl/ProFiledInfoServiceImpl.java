package com.manage.crm.service.impl;

import com.manage.crm.dao.ProFiledInfoDao;
import com.manage.crm.entity.ProFiledInfo;
import com.manage.crm.service.ProFiledInfoService;
import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("proFiledInfoService")
@Transactional
public class ProFiledInfoServiceImpl extends BaseServiceImpl<ProFiledInfo>
  implements ProFiledInfoService
{
  private static final Logger logger = LoggerFactory.getLogger(ProFiledInfoServiceImpl.class);

  @Resource(name="proFiledInfoDao")
  private ProFiledInfoDao objProFiledInfoDao;

  public BaseDao<ProFiledInfo> getBaseDao()
  {
    return this.objProFiledInfoDao;
  }

  public String updateFiledInfoActiveStatus(Long lProjectID, String[] arrayProFiledInfoID, int nStatus)
  {
    return updateFiledInfoStatus(lProjectID, arrayProFiledInfoID, nStatus, false, true, false, false);
  }

  public String updateFiledInfoNatureStatus(Long lProjectID, String[] arrayProFiledInfoID, int nStatus)
  {
    return updateFiledInfoStatus(lProjectID, arrayProFiledInfoID, nStatus, true, false, false, false);
  }

  public String updateFiledInfoRegStatus(Long lProjectID, String[] arrayProFiledInfoID, int nStatus)
  {
    return updateFiledInfoStatus(lProjectID, arrayProFiledInfoID, nStatus, false, false, true, false);
  }

  public String updateFiledInfoModifyStatus(Long lProjectID, String[] arrayProFiledInfoID, int nStatus)
  {
    return updateFiledInfoStatus(lProjectID, arrayProFiledInfoID, nStatus, false, false, false, true);
  }

  public String updateFiledInfoStatus(Long lProjectID, String[] arrayProFiledInfoID, int nStatus, boolean bIsProp, boolean bStatus, boolean bReg, boolean bModify)
  {
    boolean bUpdate = false;
    String strResultMsg = null;

    if ((lProjectID == null) || (lProjectID.longValue() <= 0L))
    {
      strResultMsg = "出现错误！无法获取项目信息 ！";
      logger.error(strResultMsg + "--当前项目id为：" + lProjectID);
      return strResultMsg;
    }

    if ((arrayProFiledInfoID == null) || (arrayProFiledInfoID.length == 0))
    {
      strResultMsg = "出现错误！没有获取到您要操作的字段！";
      logger.error(strResultMsg + "--当前字段集合为：" + arrayProFiledInfoID);
      return strResultMsg;
    }
    for (int i = 0; i < arrayProFiledInfoID.length; i++)
    {
      try
      {
        ProFiledInfo objProFiledInfo = (ProFiledInfo)getById(Long.valueOf(Long.parseLong(arrayProFiledInfoID[i])));

        if (objProFiledInfo != null)
        {
          if (bIsProp)
          {
            if (objProFiledInfo.getnPfnature() == nStatus)
            {
              logger.info("当前字段性质为：【" + objProFiledInfo.getnPfnature() + "】与修改后相同无需修改");
            }
            else
            {
              objProFiledInfo.setnPfnature(nStatus);
              bUpdate = true;
            }
          }
          else if (bStatus)
          {
            if (objProFiledInfo.getnStatus() == nStatus)
            {
              logger.info("当前字段状态为：【" + objProFiledInfo.getnStatus() + "】与修改后相同无需修改");
            }
            else
            {
              objProFiledInfo.setNStatus(nStatus);
              bUpdate = true;
            }
          }
          else if (bReg)
          {
            if (objProFiledInfo.getnIsAdd() == nStatus)
            {
              logger.info("当前字段状态为：【" + objProFiledInfo.getnIsAdd() + "】与修改后相同无需修改");
            }
            else
            {
              objProFiledInfo.setnIsAdd(nStatus);
              bUpdate = true;
            }
          }
          else if (bModify)
          {
            if (objProFiledInfo.getnIsModify() == nStatus)
            {
              logger.info("当前字段状态为：【" + objProFiledInfo.getnIsModify() + "】与修改后相同无需修改");
            }
            else
            {
              objProFiledInfo.setnIsModify(nStatus);
              bUpdate = true;
            }
          }

          if (bUpdate)
          {
            if (!update(objProFiledInfo))
            {
              logger.error("更新失败！当前字段id为：" + objProFiledInfo.getlId());
              return "更新失败！";
            }

            logger.info("更新成功字段名称【" + objProFiledInfo.getStrPfname() + "】字段状态由【" + objProFiledInfo.getlId() + "】修改成【" + nStatus + "】");
          }
        }
        else
        {
          logger.error(strResultMsg = "无效的项目字段ID");
          return strResultMsg;
        }
      }
      catch (Exception e)
      {
        logger.error("更新失败！", e);
        return "更新失败！";
      }

    }

    return "success";
  }
}