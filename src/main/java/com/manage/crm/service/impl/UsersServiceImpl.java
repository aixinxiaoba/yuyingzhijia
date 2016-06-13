package com.manage.crm.service.impl;

import com.manage.crm.dao.ProjectDao;
import com.manage.crm.dao.UsersDao;
import com.manage.crm.entity.Project;
import com.manage.crm.entity.Users;
import com.manage.crm.service.UsersService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javacommon.util.StringUtils;
import javacommon.util.encrypt.EncryptAndDecryptUtils;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("usersService")
@Transactional
public class UsersServiceImpl extends BaseServiceImpl<Users>
  implements UsersService
{
  private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

  @Resource(name="usersDao")
  private UsersDao objUsersDao;

  @Resource(name="projectDao")
  private ProjectDao objProjectDao;

  public BaseDao<Users> getBaseDao()
  {
    return this.objUsersDao;
  }

  public String projectAuthorization(String[] arrayUsersID, String[] arrayProjectID)
  {
    return processProjectAuthorization(arrayUsersID, arrayProjectID, 1);
  }

  public String cancleProjectAuthorization(String[] arrayUsersID, String[] arrayProjectID)
  {
    return processProjectAuthorization(arrayUsersID, arrayProjectID, 2);
  }

  private String processProjectAuthorization(String[] arrayUsersID, String[] arrayProjectID, int nAuthorizationType)
  {
    String strMsg = "success";

    if ((arrayUsersID == null) || (arrayUsersID.length == 0))
    {
      strMsg = "无效的用户ID";
      logger.error(strMsg + "--当前的客户id为：" + arrayUsersID);
      return strMsg;
    }
    if ((arrayUsersID == null) || (arrayUsersID.length == 0))
    {
      strMsg = "无效的项目ID";
      logger.error(strMsg + "--当前的项目id为：" + arrayProjectID);
      return strMsg;
    }

    try
    {
      for (int i = 0; i < arrayUsersID.length; i++)
      {
        int nCount = 0;
        Users objUsers = (Users)getById(Long.valueOf(Long.parseLong(arrayUsersID[i])));

        if (objUsers != null)
        {
          Set lstProject = objUsers.getLstProject();
          for (int j = 0; j < arrayProjectID.length; j++)
          {
            Project objProject = (Project)this.objProjectDao.getById(Long.parseLong(arrayProjectID[j]));

            if (objProject == null)
            {
              logger.error("当前项目为空，项目id为：" + arrayProjectID[j]);
            }
            else if (nAuthorizationType == 1)
            {
              if ((lstProject != null) && (lstProject.size() > 0) && (lstProject.contains(objProject)))
              {
                logger.info("此项目已经授权过，无需再次授权，当前要授权的客户id为：" + objUsers.getlId() + "--授权的项目id为：" + objProject.getlId());
              }
              else
              {
                if (lstProject == null)
                {
                  lstProject = new HashSet();
                }
                lstProject.add(objProject);

                objUsers.setLstProject(lstProject);
                nCount++;
                logger.info("当前要授权的用户为：" + objUsers.getStrName() + "--ID为：" + objUsers.getlId() + "--授权的项目为：" + objProject.getStrPname() + "--ID为：" + objProject.getlId());
              }

            }
            else if ((lstProject != null) && (lstProject.size() > 0) && (lstProject.contains(objProject)))
            {
              lstProject.remove(objProject);
              nCount++;
              logger.info("此项目已经授权过，开始进行取消授权，当前要取消授权的客户id为：" + objUsers.getlId() + "--取消授权的项目id为：" + objProject.getlId());
            }

          }

          if (nCount > 0)
          {
            if (!update(objUsers))
            {
              strMsg = "出现错误，授权失败！";
              logger.error(strMsg);
              return strMsg;
            }
            if (nAuthorizationType == 1)
            {
              logger.info("授权成功，当前要授权的用户为：" + objUsers.getStrName() + "--ID为：" + objUsers.getlId() + "--授权的项目个数为：" + nCount);
            }
            else
            {
              logger.info("取消授权成功，当前要取消授权的用户为：" + objUsers.getStrName() + "--ID为：" + objUsers.getlId() + "--取消授权的项目个数为：" + nCount);
            }
          }
        }
      }
    }
    catch (Exception e) {
      strMsg = "出现异常！操作失败！";
      logger.error(strMsg, e);
      return strMsg;
    }
    return strMsg;
  }

  public String passwordModify(String strUsersID, String strOldPassword, String strPassword, String strRePassword)
  {
    String strMsg;
    if (StringUtils.isEmpty(strOldPassword))
    {
      logger.error(strMsg = "原始密码不能为空");
    }
    else if ((StringUtils.isEmpty(strPassword)) || (StringUtils.isEmpty(strRePassword)))
    {
      logger.error(strMsg = "新密码不能为空");
    }
    else if (!strPassword.equals(strRePassword))
    {
      logger.error(strMsg = "您两次输入的密码不一致！");
    }
    else
    {
      try
      {
        Users objUsers = (Users)getById(Long.valueOf(Long.parseLong(strUsersID)));

        if (!EncryptAndDecryptUtils.md5(strOldPassword).equals(objUsers.getStrPassword()))
        {
          logger.error(strMsg = "您的旧密码填写错误！您不能修改密码！");
        }
        else
        {
          logger.info("旧密码为：" + strOldPassword + "--新密码为：" + strPassword);
          objUsers.setStrPassword(EncryptAndDecryptUtils.md5(strPassword));
          if (!update(objUsers))
          {
            logger.error(strMsg = "出现异常修改失败！");
          }
          else
          {
            strMsg = "success";
          }
        }
      }
      catch (Exception e)
      {
        logger.error(strMsg = "出现异常，请刷新重试", e);
      }
    }

    return strMsg;
  }

  public String saveProject(Users objUsers, Project objProject)
  {
    if ((objUsers.getlId() != null) && (objUsers.getlId().longValue() > 0L))
    {
      objUsers = (Users)getById(objUsers.getlId());
    }
    else
    {
      return "获取用户失败！";
    }
    if (objProject == null)
    {
      return "保存失败！无法获取您要保存的项目信息";
    }

    // 验证项目标志是否重复。
    if (StringUtils.isEmpty(objProject.getProjectKey()))
    {
    	return "项目英文标识不能为空";
    }
    
    if (this.sizeByHql(" from Project where projectKey='"+ objProject.getProjectKey() +"'") > 0)
    {
    	return "此项目英文名称已经存在，请换一个进行注册！";
    }
    objProject.setStrBeginTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));

    // 获取项目类型。
    Set lstProject = objUsers.getLstProject();
    lstProject.add(objProject);
    objUsers.setLstProject(lstProject);

    if (!save(objUsers))
    {
      logger.error("保存失败！");
      return "保存失败";
    }

    return "success";
  }
}