package com.manage.crm.service.impl;

import com.manage.crm.dao.CustomerDao;
import com.manage.crm.dao.ProjectDao;
import com.manage.crm.entity.Customer;
import com.manage.crm.entity.Project;
import com.manage.crm.service.CustomerService;
import com.manage.crm.util.Pagination;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javacommon.core.base.BaseDao;
import javacommon.core.base.BaseServiceImpl;
import javacommon.util.SearchCondition;
import javacommon.util.encrypt.EncryptAndDecryptUtils;
import javax.annotation.Resource;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
@Transactional
public class CustomerServiceImpl extends BaseServiceImpl<Customer>
  implements CustomerService
{
  private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

  @Resource(name="customerDao")
  private CustomerDao objCustomerDao;

  @Resource(name="projectDao")
  private ProjectDao objProjectDao;

  public BaseDao<Customer> getBaseDao()
  {
    return this.objCustomerDao;
  }

  public void listCustomerByMultiCondition(Pagination<Customer> objPagination, SearchCondition objSearchCondition, Order objOrder)
  {
    listByCriteria(objPagination, objSearchCondition, objOrder);
  }

  private String processProjectAuthorization(String[] arrayCustomerID, String[] arrayProjectID, int nAuthorizationType)
  {
    String strMsg = "success";

    if ((arrayCustomerID == null) || (arrayCustomerID.length == 0))
    {
      strMsg = "无效的客户ID";
      logger.error(strMsg + "--当前的客户id为：" + arrayCustomerID);
      return strMsg;
    }
    if ((arrayCustomerID == null) || (arrayCustomerID.length == 0))
    {
      strMsg = "无效的项目ID";
      logger.error(strMsg + "--当前的项目id为：" + arrayProjectID);
      return strMsg;
    }

    try
    {
      for (int i = 0; i < arrayCustomerID.length; i++)
      {
        int nCount = 0;
        Customer objCustomer = (Customer)getById(Long.valueOf(Long.parseLong(arrayCustomerID[i])));

        if (objCustomer != null)
        {
          Set lstProject = objCustomer.getLstProject();
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
                logger.info("此项目已经授权过，无需再次授权，当前要授权的客户id为：" + objCustomer.getlId() + "--授权的项目id为：" + objProject.getlId());
              }
              else
              {
                if (lstProject == null)
                {
                  lstProject = new HashSet();
                }
                lstProject.add(objProject);

                objCustomer.setLstProject(lstProject);
                nCount++;
                logger.info("当前要授权的用户为：" + objCustomer.getStrSname() + "--ID为：" + objCustomer.getlId() + "--授权的项目为：" + objProject.getStrPname() + "--ID为：" + objProject.getlId());
              }

            }
            else if ((lstProject != null) && (lstProject.size() > 0) && (lstProject.contains(objProject)))
            {
              if ((objCustomer.getLstChildrenCustomer() != null) && (objCustomer.getLstChildrenCustomer().size() > 0))
              {
                List lstChildCustomers = listBySql("select * from customer where SParent_ID = " + objCustomer.getlId() + " and id in(select cid from customer_project where pid=" + objProject.getlId() + ")");
                if ((lstChildCustomers != null) && (lstChildCustomers.size() > 0))
                {
                  strMsg = "当前客户【" + objCustomer.getStrSname() + "】存在子客户，不允许进行取消授权！";
                  logger.error(strMsg);
                  return strMsg;
                }
              }
              lstProject.remove(objProject);
              nCount++;
              logger.info("此项目已经授权过，开始进行取消授权，当前要取消授权的客户id为：" + objCustomer.getlId() + "--取消授权的项目id为：" + objProject.getlId());
            }

          }

          if (nCount > 0)
          {
            if (!update(objCustomer))
            {
              strMsg = "出现错误，授权失败！";
              logger.error(strMsg);
              return strMsg;
            }
            if (nAuthorizationType == 1)
            {
              logger.info("授权成功，当前要授权的用户为：" + objCustomer.getStrSname() + "--ID为：" + objCustomer.getlId() + "--授权的项目个数为：" + nCount);
            }
            else
            {
              logger.info("取消授权成功，当前要取消授权的用户为：" + objCustomer.getStrSname() + "--ID为：" + objCustomer.getlId() + "--取消授权的项目个数为：" + nCount);
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

  public String projectAuthorization(String[] arrayCustomerID, String[] arrayProjectID)
  {
    return processProjectAuthorization(arrayCustomerID, arrayProjectID, 1);
  }

  public String cancleProjectAuthorization(String[] arrayCustomerID, String[] arrayProjectID)
  {
    return processProjectAuthorization(arrayCustomerID, arrayProjectID, 2);
  }

  public String resetPwd(String[] arrayCustomerID)
  {
    if ((arrayCustomerID == null) || (arrayCustomerID.length <= 0))
    {
      return "修改失败！传递的参数不正确！";
    }

    try
    {
      for (int i = 0; i < arrayCustomerID.length; i++)
      {
        Customer objCustomer = (Customer)getById(Long.valueOf(Long.parseLong(arrayCustomerID[i])));

        if (objCustomer != null)
        {
          if (EncryptAndDecryptUtils.md5("111111").equals(objCustomer.getStrPassword()))
          {
            logger.info("当前密码已经是默认密码，无需还原！");
          }
          else {
            objCustomer.setStrPassword(EncryptAndDecryptUtils.md5("111111"));
            if (!update(objCustomer))
            {
              logger.error("出现错误，还原密码失败！");
              return "出现错误！还原密码失败！";
            }
          }
        }
      }
    }
    catch (Exception e) {
      logger.error("出现异常！", e);
      return "出现异常，请稍后重试";
    }

    return "success";
  }
}