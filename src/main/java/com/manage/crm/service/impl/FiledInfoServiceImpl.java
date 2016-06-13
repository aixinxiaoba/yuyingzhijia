package com.manage.crm.service.impl;

import com.manage.crm.dao.FiledInfoDao;
import com.manage.crm.dao.ProFiledInfoDao;
import com.manage.crm.entity.FiledInfo;
import com.manage.crm.entity.ProFiledInfo;
import com.manage.crm.entity.Project;
import com.manage.crm.service.FiledInfoService;
import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javacommon.util.StringUtils;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("filedInfoService")
@Transactional
public class FiledInfoServiceImpl extends BaseServiceImpl<FiledInfo>
  implements FiledInfoService
{
  private static final Logger logger = LoggerFactory.getLogger(FiledInfoServiceImpl.class);

  @Resource(name="filedInfoDao")
  private FiledInfoDao objFiledInfoDao;

  @Resource(name="proFiledInfoDao")
  private ProFiledInfoDao objProFiledInfoDao;

  public BaseDao<FiledInfo> getBaseDao()
  {
    return this.objFiledInfoDao;
  }

  public String activeOrForbidFiledInfos(Project objProject, String[] arrayFIIDs, int nStatus, int nIsBiTian)
  {
    String strResultMsg = "";

    if ((objProject == null) || (objProject.getlId().longValue() <= 0L))
    {
      strResultMsg = strResultMsg + "objProject 不能为空";
      return strResultMsg;
    }

    if ((arrayFIIDs == null) || (arrayFIIDs.length == 0))
    {
      strResultMsg = strResultMsg + "arrayFIIDs 不能为空";
      return strResultMsg;
    }

    if (nStatus < 0)
    {
      strResultMsg = strResultMsg + "nStatus 不合法" + nStatus;
      return strResultMsg;
    }

    for (int i = 0; i < arrayFIIDs.length; i++)
    {
      long lFIID = -1L;
      ProFiledInfo objProFiledInfo = new ProFiledInfo();
      String strFIID = arrayFIIDs[i];
      if (!StringUtils.isEmpty(strFIID))
      {
        try
        {
          lFIID = Long.parseLong(strFIID.trim());
          if (lFIID < 0L)
          {
            strResultMsg = strResultMsg + ",无效的字段ID" + strFIID.trim();
          }

        }
        catch (Exception objEx)
        {
          strResultMsg = strResultMsg + ",无效的字段ID" + strFIID.trim();
        }

        objProFiledInfo.setlFiid(Long.valueOf(lFIID));
        objProFiledInfo.setLPid(objProject.getLId());
        objProFiledInfo = (ProFiledInfo)this.objProFiledInfoDao.getRecordByProps(objProFiledInfo, "lFiid,lPid");

        if (nStatus == 0)
        {
          if ((objProFiledInfo == null) || (objProFiledInfo.getLId().longValue() < 0L))
          {
            strResultMsg = strResultMsg + ",字段ID不存在：" + lFIID;
          }

          objProFiledInfo = addProFiledInfoStatus(objProFiledInfo, nStatus, nIsBiTian);
          if (objProFiledInfo == null)
          {
            strResultMsg = strResultMsg + ",更新状态失败了：" + lFIID;
          }
          else if (!this.objProFiledInfoDao.update(objProFiledInfo))
          {
            strResultMsg = strResultMsg + ",更新状态失败了：" + lFIID;
          }

        }
        else if ((objProFiledInfo == null) || (objProFiledInfo.getLId().longValue() < 0L))
        {
          objProFiledInfo = new ProFiledInfo();
          objProFiledInfo = processNoThirdRecords(objProFiledInfo, lFIID, nStatus, objProject.getlId().longValue(), nIsBiTian);
          if (objProFiledInfo == null)
          {
            strResultMsg = strResultMsg + ",更新状态失败了：" + lFIID;
          }
          else if (!this.objProFiledInfoDao.save(objProFiledInfo))
          {
            logger.error("objProFiledInfoDao 保存失败，lFIID：---" + lFIID);
            strResultMsg = strResultMsg + ",保存失败了：" + lFIID;
          }
        }
        else
        {
          objProFiledInfo = addProFiledInfoStatus(objProFiledInfo, nStatus, nIsBiTian);
          if (objProFiledInfo == null)
          {
            strResultMsg = strResultMsg + ",更新状态失败了：" + lFIID;
          }
          else if (!this.objProFiledInfoDao.update(objProFiledInfo))
          {
            strResultMsg = strResultMsg + ",更新状态失败了：" + lFIID;
          }
        }
      }

    }

    if (StringUtils.isEmpty(strResultMsg))
    {
      return "success";
    }

    return strResultMsg;
  }

  public String activeFiledInfo(String[] arrayFiledInfoID, long lProjectID)
  {
    String strResultMsg = "";

    if ((arrayFiledInfoID == null) || (arrayFiledInfoID.length == 0))
    {
      strResultMsg = "激活的字段不能为空";
      return strResultMsg;
    }
    if (lProjectID <= 0L)
    {
      logger.error(strResultMsg = "出现错误，无法获取项目信息！");
      return strResultMsg;
    }

    for (int i = 0; i < arrayFiledInfoID.length; i++)
    {
      FiledInfo objFiledInfo = null;
      try
      {
        objFiledInfo = (FiledInfo)getById(Long.valueOf(Long.parseLong(arrayFiledInfoID[i])));
      }
      catch (Exception e)
      {
        strResultMsg = "出现错误，无效的字段ID";
        logger.error(strResultMsg, e);
        return strResultMsg;
      }

      if (objFiledInfo == null)
      {
        strResultMsg = "出现错误，无效的字段信息";
        return strResultMsg;
      }

      ProFiledInfo objProFiledInfo = setProFiledProp(objFiledInfo, lProjectID);

      if (!this.objProFiledInfoDao.save(objProFiledInfo))
      {
        strResultMsg = "出现错误，项目字段保存失败!";
        logger.error(strResultMsg + "，字段id为：---" + objFiledInfo.getlId());
        return strResultMsg;
      }

      logger.info("项目字段：" + objFiledInfo.getStrFName() + ",ID为：" + objFiledInfo.getlId() + "，保存成功！");
    }

    return "success";
  }

  private ProFiledInfo setProFiledProp(FiledInfo objFiledInfo, long lProjectID)
  {
    ProFiledInfo objProFiledInfo = new ProFiledInfo();

    objProFiledInfo.setlFiid(Long.valueOf(objFiledInfo.getlId()));
    objProFiledInfo.setlPid(Long.valueOf(lProjectID));

    objProFiledInfo.setNPfhtmlType(objFiledInfo.getNFHtmlType());
    objProFiledInfo.setNPfnature(objFiledInfo.getNNature());
    objProFiledInfo.setStrPfdefault(objFiledInfo.getStrDefalut());
    objProFiledInfo.setStrPfidentity(objFiledInfo.getStrFIdentity());
    objProFiledInfo.setStrPfname(objFiledInfo.getStrFName());
    objProFiledInfo.setnSequence(Integer.parseInt(objFiledInfo.getlId() + ""));

    objProFiledInfo.setnIsAdd(1);
    objProFiledInfo.setnIsModify(1);
    objProFiledInfo.setnStatus(1);

    return objProFiledInfo;
  }

  private ProFiledInfo addProFiledInfoStatus(ProFiledInfo objProFiledInfo, int nStatus, int nIsBiTian)
  {
    if (nStatus < 0)
    {
      logger.error("nStatus 不合法" + nStatus);
      return null;
    }

    if (nStatus == 1)
    {
      objProFiledInfo.setnStatus(1);
    }
    else
    {
      objProFiledInfo.setnStatus(0);
    }

    if (nIsBiTian == 1)
    {
      objProFiledInfo.setnPfnature(1);
    }
    else
    {
      objProFiledInfo.setnPfnature(2);
    }

    return objProFiledInfo;
  }

  private ProFiledInfo processNoThirdRecords(ProFiledInfo objProFiledInfo, long lFIID, int nStatus, long lProID, int nIsBiTian)
  {
    objProFiledInfo.setlPid(Long.valueOf(lProID));
    objProFiledInfo.setLFiid(Long.valueOf(lFIID));

    FiledInfo objFiledInfo = (FiledInfo)this.objFiledInfoDao.getById(lFIID);
    if ((objFiledInfo == null) || (objFiledInfo.getLId() <= 0L))
    {
      logger.error(lFIID + "对应的字段信息不存在");
      return null;
    }

    objProFiledInfo.setNPfhtmlType(objFiledInfo.getNFHtmlType());
    objProFiledInfo.setNPfnature(objFiledInfo.getNNature());
    objProFiledInfo.setStrPfdefault(objFiledInfo.getStrDefalut());
    objProFiledInfo.setStrPfidentity(objFiledInfo.getStrFIdentity());
    objProFiledInfo.setStrPfname(objFiledInfo.getStrFName());
    objProFiledInfo.setnSequence(Integer.parseInt(objFiledInfo.getlId() + ""));

    objProFiledInfo = addProFiledInfoStatus(objProFiledInfo, nStatus, nIsBiTian);

    return objProFiledInfo;
  }
}