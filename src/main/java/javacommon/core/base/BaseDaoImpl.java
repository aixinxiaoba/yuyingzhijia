package javacommon.core.base;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.util.GenericsUtils;
import javacommon.util.SearchCondition;
import oracle.jdbc.OracleTypes;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.manage.crm.util.Pagination;

/**
 * 通用Dao层抽象类。
 * 
 * @author wangzg
 * 
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>
{
    /**
     * 日志对象。
     */
    protected static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    /**
     * 泛式类。
     */
    private Class<?> objSupportsClass = GenericsUtils.getGeneric(getClass());

    /**
     * 保存对象。
     */
    public String saveObject(T objEntity)
    {
        try
        {
            return (String) this.getHibernateTemplate().save(objEntity);
        }
        catch (Exception e)
        {
            logger.error("saveObject - 保存对象数据时错误：" + e.getMessage());
            return null;
        }
    }

    /**
     * 保存对象。
     */
    public boolean save(T objEntity)
    {
        try
        {
            this.getHibernateTemplate().save(objEntity);
            return true;
        }
        catch (Exception e)
        {
            logger.error("save - 保存对象数据时错误：" + e.getMessage());
            return false;
        }
    }

    /**
     * 保存对象。
     */
    public boolean saveAndFlush(T objEntity)
    {
        try
        {
            this.getHibernateTemplate().save(objEntity);
            this.getHibernateTemplate().flush();
            return true;
        }
        catch (Exception e)
        {
            logger.error("saveAndFlush - 保存对象数据时错误：" + e.getMessage());
            return false;
        }

    }

    /**
     * 保存对象。
     */
    public Serializable insert(T objEntity)
    {
        try
        {
            return this.getHibernateTemplate().save(objEntity);
        }
        catch (Exception e)
        {
            logger.error("insert - 保存对象数据时错误：" + e.getMessage());
            return -1;
        }
    }

    /**
     * 保存对象。
     */
    public boolean saveOrUpdate(T objEntity)
    {
        try
        {
            this.getHibernateTemplate().saveOrUpdate(objEntity);
            return true;
        }
        catch (Exception e)
        {
            logger.error("saveOrUpdate - 保存对象数据时错误：" + e.getMessage());
            return false;
        }
    }

    /**
     * 删除。
     */
    public boolean delete(T objEntity)
    {
        try
        {
            this.getHibernateTemplate().delete(objEntity);
            return true;
        }
        catch (Exception e)
        {
            logger.error("delete - 根据对象删除数据时出错：" + e.getMessage());
            return false;
        }
    }

    /**
     * 删除。
     */
    public boolean deleteByCriteria(final SimpleExpression objOne, final SimpleExpression objTwo)
    {
        try
        {
            List<T> lstList;

            lstList = getHibernateTemplate().executeFind(new HibernateCallback<List<T>>()
            {
                public List<T> doInHibernate(Session objSession) throws HibernateException
                {
                    Criteria objCriteria = objSession.createCriteria(objSupportsClass);

                    if (objOne != null)
                    {
                        objCriteria.add(objOne);
                    }

                    if (objTwo != null)
                    {
                        objCriteria.add(objTwo);
                    }

                    return objCriteria.list();
                }
            });

            // 删除所有的对象。
            if (lstList != null)
            {
                for (T objObj : lstList)
                {
                    this.delete(objObj);
                }
            }

            return true;
        }
        catch (Exception e)
        {
            logger.error("deleteByCriteria - 根据查询条件删除数据时出错：" + e.getMessage());
            return false;
        }
    }

    /**
     * 通过id删除。
     */
    public boolean deleteById(Serializable objID)
    {
        try
        {
            T objEntity = getById(objID);

            if (objEntity != null)
            {
                delete(objEntity);
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            logger.error("deleteById - 根据对象删除数据时出错：" + e.getMessage());
            return false;
        }
    }

    /**
     * 通过属性删除。
     */
    public boolean deleteByProps(final T objEntity, final String strUniquePropertyNames)
    {
        List<T> lstList = null;

        try
        {
            lstList = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>()
            {
                public List<T> doInHibernate(Session session) throws HibernateException, SQLException
                {
                    Criteria objCriteria = session.createCriteria(objSupportsClass);
                    if (StringUtils.hasText(strUniquePropertyNames))
                    {
                        String[] arrayNameList = strUniquePropertyNames.split(",");
                        for (int i = 0; i < arrayNameList.length; i++)
                        {
                            try
                            {
                                objCriteria.add(Restrictions.eq(arrayNameList[i], PropertyUtils.getProperty(objEntity, arrayNameList[i])));
                            }
                            catch (Exception e)
                            {
                                ReflectionUtils.handleReflectionException(e);
                            }
                        }
                    }

                    return objCriteria.list();
                }
            });

            // 删除所有的对象。
            if (lstList != null)
            {
                for (T objObj : lstList)
                {
                    this.delete(objObj);
                }
            }

            return true;
        }
        catch (Exception e)
        {
            logger.error("deleteByProps - 根据查询条件删除数据时出错：" + e.getMessage());
            return false;
        }
    }

    /**
     * 更新。
     */
    public boolean update(T objEntity)
    {
        try
        {
            this.getHibernateTemplate().update(objEntity);
            return true;
        }
        catch (Exception e)
        {
            logger.error("update - 更新对象数据时错误：" + e.getMessage());
            return false;
        }
    }

    /**
     * 判断对象某些属性的值在数据库中是否唯一。
     * 
     * @param objEntity
     * @param strUniquePropertyNames
     * @return
     */
    @Override
    public boolean isUnique(T objEntity, String strUniquePropertyNames)
    {
        return getRecordByProps(objEntity, strUniquePropertyNames) == null;
    }

    /**
     * 查询。
     */
    public T getByCriteria(final SimpleExpression objOne, final SimpleExpression objTwo)
    {
        try
        {
            return (T) getHibernateTemplate().executeFind(new HibernateCallback()
            {
                public Object doInHibernate(Session objSession) throws HibernateException
                {
                    Criteria objCriteria = objSession.createCriteria(objSupportsClass);
                    if (objOne != null)
                    {
                        objCriteria.add(objOne);
                    }

                    if (objTwo != null)
                    {
                        objCriteria.add(objTwo);
                    }

                    return objCriteria.uniqueResult();
                }
            });
        }
        catch (Exception e)
        {
            logger.error("getByCriteria - 根据查询条件得到一个数据对象时出错：" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据id查询。
     */
    public T getById(Serializable objId)
    {
        try
        {
            return (T) getHibernateTemplate().get(objSupportsClass, objId);
        }
        catch (Exception e)
        {
            logger.error("getById - 根据ID得到一个数据对象时出错：" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据id查询。
     */
    
    public T getById(long lId)
    {
        try
        {
            return (T) getHibernateTemplate().get(objSupportsClass, lId);
        }
        catch (Exception e)
        {
            logger.error("getById - 根据ID得到一个数据对象时出错：" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据hql查询。
     */
    public T getBySql(final String strHql)
    {
        try
        {
            return (T) getHibernateTemplate().execute(new HibernateCallback()
            {
                public Object doInHibernate(Session objSession) throws HibernateException
                {
                    Query query = objSession.createSQLQuery(strHql).addEntity(objSupportsClass);

                    query.setMaxResults(1);
                    return query.uniqueResult();
                }
            });
        }
        catch (Exception e)
        {
            logger.error("getBySql - 根据SQL得到一个数据对象时出错：" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据hql查询。
     */
    public T getByHql(final String strHql)
    {
        try
        {
            return (T) getHibernateTemplate().execute(new HibernateCallback()
            {
                public Object doInHibernate(Session objSession) throws HibernateException
                {
                    Query objQuery = objSession.createQuery(strHql);

                    objQuery.setMaxResults(1);
                    return objQuery.uniqueResult();
                }
            });
        }
        catch (Exception e)
        {
            logger.error("getByHql - 根据HQL得到一个数据对象时出错：" + e.getMessage());
            return null;

        }
    }

    /**
     * 查询全部。
     */
    public List<T> listAll(final Order objOrder)
    {
        try
        {
            return getHibernateTemplate().executeFind(new HibernateCallback<List<T>>()
            {
                public List<T> doInHibernate(Session objSession) throws HibernateException
                {
                    Criteria objCriteria = objSession.createCriteria(objSupportsClass);

                    if (objOrder != null)
                    {
                        objCriteria.addOrder(objOrder);
                    }

                    return objCriteria.list();
                }
            });
        }
        catch (Exception e)
        {
            logger.error("listAll - 得到所有的数据时出错：" + e.getMessage());
            return null;
        }
    }

    // /**
    // * 分页查询。
    // */
    // public List<T> listByCriteria(final Pagination objPagination, final long lStart, final long lPagesize, final SimpleExpression objOne, final SimpleExpression objTwo)
    // {
    // return listByCriteria(objPagination, lStart, lPagesize, objOne, objTwo, null);
    // }

    /**
     * 查询。
     */
    public List<T> listByCriteria(final Pagination<T> objPagination, final SearchCondition objCondition, final Order objOrder)
    {
        try
        {
            return getHibernateTemplate().executeFind(new HibernateCallback()
            {
                public Object doInHibernate(Session objSession) throws HibernateException
                {
                    Criteria objCriteria = objSession.createCriteria(objSupportsClass);

                    logger.info("分页起始位置：" + objPagination.getFirstResult());
                    logger.info("每页显示的数量：" + objPagination.getPageSize());
                    // 大小
                    if (objPagination.getFirstResult() > 0)
                    {
                        objCriteria.setFirstResult(objPagination.getFirstResult());
                    }

                    if (objPagination.getPageSize() > 0)
                    {
                        objCriteria.setMaxResults(objPagination.getPageSize());
                    }

                    if (objCondition != null)
                    {
                        List<SimpleExpression> lstSimpleExpression = objCondition.getLstCondition();

                        if (lstSimpleExpression != null && lstSimpleExpression.size() > 0)
                        {
                            for (int i = 0; i < lstSimpleExpression.size(); i++)
                            {
                                if (lstSimpleExpression.get(i) != null)
                                {
                                    objCriteria.add(lstSimpleExpression.get(i));
                                }
                            }
                        }
                    }
                    // 排序
                    if (objOrder != null)
                    {
                        objCriteria.addOrder(objOrder);
                    }
                    if (objPagination == null)
                    {
                        return objCriteria.list();
                    }
                    // 设置查询数据集合。
                    objPagination.setRows(objCriteria.list());
                    // 设置总页数。
                    objPagination.setTotal(sizeByCriteria(objCondition));
                    return objPagination.getRows();
                }
            });
        }
        catch (Exception e)
        {
            logger.error("listByCriteria支持排序 - 根据条件查询数据时出错：" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据hql查询。
     */
    public List<T> listByHql(final String strHql)
    {
        try
        {
            return getHibernateTemplate().executeFind(new HibernateCallback()
            {
                public Object doInHibernate(Session objSession) throws HibernateException
                {
                    Query objQuery = objSession.createQuery(strHql);

                    return objQuery.list();
                }
            });
        }
        catch (Exception e)
        {
            logger.error("listByHql - 根据HQL查询数据时出错：" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据hql查询。
     */
    public List<T> listByHql(final long lStart, final long lPagesize, final String strHql)
    {
        try
        {
            return getHibernateTemplate().executeFind(new HibernateCallback()
            {
                public Object doInHibernate(Session objSession) throws HibernateException
                {
                    Query objQuery = objSession.createQuery(strHql);

                    // 大小
                    if (lStart != -1)
                    {
                        objQuery.setFirstResult((int) lStart);
                    }

                    if (lPagesize != -1)
                    {
                        objQuery.setMaxResults((int) lPagesize);
                    }

                    return objQuery.list();
                }
            });
        }
        catch (Exception e)
        {
            logger.error("listByHql - 根据HQL查询数据时出错：" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据sql查询。
     */
    public List<T> listBySql(final String strSql)
    {
        try
        {
            return getHibernateTemplate().executeFind(new HibernateCallback()
            {
                public Object doInHibernate(Session objSession) throws HibernateException
                {
                    Query objQuery = objSession.createSQLQuery(strSql).addEntity(objSupportsClass);

                    return objQuery.list();
                }
            });
        }
        catch (Exception e)
        {
            logger.error("listBySql - 根据SQL查询数据时出错：" + e.getMessage());
            return null;
        }
    }

    /**
     * 根据sql查询。
     */
    public List<T> listBySql(final long lStart, final long lPagesize, final String strSql)
    {
        try
        {
            return getHibernateTemplate().executeFind(new HibernateCallback()
            {
                public Object doInHibernate(Session objSession) throws HibernateException
                {
                    Query objQuery = objSession.createSQLQuery(strSql).addEntity(objSupportsClass);

                    // 大小
                    if (lStart != -1)
                    {
                        objQuery.setFirstResult((int) lStart);
                    }

                    if (lPagesize != -1)
                    {
                        objQuery.setMaxResults((int) lPagesize);
                    }

                    return objQuery.list();
                }
            });
        }
        catch (Exception e)
        {
            logger.error("listBySql - 根据SQL查询数据时出错：" + e.getMessage());
            return null;
        }
    }

    /**
     * 查询数量。
     */
    public long sizeAll()
    {
        try
        {
            Long lCount = (Long) getHibernateTemplate().execute(new HibernateCallback()

            {
                public Object doInHibernate(Session session) throws HibernateException
                {
                    Criteria objCriteria = session.createCriteria(objSupportsClass);

                    return objCriteria.setProjection(Projections.rowCount()).uniqueResult();
                }
            });

            return lCount.intValue();
        }
        catch (Exception e)
        {
            logger.error("sizeAll - 根据总记录数时候时出错：" + e.getMessage());
            return 0;
        }
    }

    /**
     * 查询数量。
     */
    public long sizeByCriteria(final SimpleExpression objOne, final SimpleExpression objTwo)
    {
        try
        {
            Long lCount = (Long) getHibernateTemplate().execute(new HibernateCallback<Long>()

            {
                public Long doInHibernate(Session objSession) throws HibernateException
                {
                    Criteria objCriteria = objSession.createCriteria(objSupportsClass);

                    // 条件
                    if (objOne != null)
                    {
                        objCriteria.add(objOne);
                    }

                    if (objTwo != null)
                    {
                        objCriteria.add(objTwo);
                    }
                    Object objCount = objCriteria.setProjection(Projections.rowCount()).uniqueResult();

                    // 类型判断。
                    if (objCount instanceof Integer)
                    {
                        return ((Integer) objCount).longValue();
                    }
                    else if (objCount instanceof Long)
                    {
                        return (Long) (objCriteria.setProjection(Projections.rowCount()).uniqueResult());
                    }
                    else
                    {
                        return 0L;
                    }
                }
            });

            return lCount;
        }
        catch (Exception e)
        {
            logger.error("sizeByCriteria - 根据总记录数时候时出错：" + e.getMessage());
            return 0;
        }
    }

    /**
     * 查询数量。
     */
    private long sizeByCriteria(final SearchCondition objCondition)
    {
        try
        {
            Long lCount = (Long) getHibernateTemplate().execute(new HibernateCallback<Long>()

            {
                public Long doInHibernate(Session objSession) throws HibernateException
                {
                    Criteria objCriteria = objSession.createCriteria(objSupportsClass);

                    // 条件
                    if (objCondition != null)
                    {
                        List<SimpleExpression> lstSimpleExpression = objCondition.getLstCondition();

                        if (lstSimpleExpression != null && lstSimpleExpression.size() > 0)
                        {
                            for (int i = 0; i < lstSimpleExpression.size(); i++)
                            {
                                if (lstSimpleExpression.get(i) != null)
                                {
                                    objCriteria.add(lstSimpleExpression.get(i));
                                }
                            }
                        }
                    }

                    Object objCount = objCriteria.setProjection(Projections.rowCount()).uniqueResult();

                    // 类型判断。
                    if (objCount instanceof Integer)
                    {
                        return ((Integer) objCount).longValue();
                    }
                    else if (objCount instanceof Long)
                    {
                        return (Long) (objCriteria.setProjection(Projections.rowCount()).uniqueResult());
                    }
                    else
                    {
                        return 0L;
                    }
                }
            });

            return lCount;
        }
        catch (Exception e)
        {
            logger.error("sizeByCriteria - 根据总记录数时候时出错：" + e.getMessage());
            return 0;
        }
    }

    /**
     * 通过sql查询数量。
     */
    public long sizeBySql(final String strSql)
    {
        try
        {
            Integer nCount = (Integer) getHibernateTemplate().execute(new HibernateCallback<Integer>()

            {
                public Integer doInHibernate(Session objSession) throws HibernateException
                {
                    Query objQuery = objSession.createSQLQuery(strSql);

                    return objQuery.list().size();
                }
            });

            return nCount.intValue();
        }
        catch (Exception e)
        {
            logger.error("sizeBySql - 根据SQL查询数据记录时出错：" + e.getMessage());
            return 0;
        }
    }

    /**
     * 通过hql查询数量。
     */
    public long sizeByHql(final String strHql)
    {
        try
        {
            Integer nCount = (Integer) getHibernateTemplate().execute(new HibernateCallback<Integer>()

            {
                public Integer doInHibernate(Session objSession) throws HibernateException
                {
                    Query objQuery = objSession.createQuery(strHql);

                    return objQuery.list().size();
                }
            });

            return nCount.intValue();
        }
        catch (Exception e)
        {
            logger.error("sizeByHql - 根据HQL查询数据记录时出错：" + e.getMessage());
            return 0;
        }
    }

    /**
     * 查询。
     */
    @SuppressWarnings("deprecation")
    public List<Map<String, Object>> getDataFromProcedure(Session objSession, String strProcedureName, List<Object> lstParams)
    {
        CallableStatement objCs = null;
        ResultSet objRs = null;
        Connection objConn = null;
        List<Map<String, Object>> lstResult = null;

        try
        {
            objConn = objSession.connection();

            objCs = objConn.prepareCall(converParamsFunction(strProcedureName, lstParams));

            objCs.registerOutParameter(1, OracleTypes.CURSOR);
            objCs.execute();
            objCs.setFetchSize(100);

            objRs = (ResultSet) objCs.getObject(1);
            lstResult = resultSetToMapLs(objRs);
        }
        catch (Exception e)
        {
            logger.error("getDataFromProcedure 异常", e);
        }
        finally
        {
            try
            {
                if (objRs != null)
                {
                    objRs.close();
                }

                if (objCs != null)
                {
                    objCs.close();
                }

                if (objConn != null)
                {
                    objConn.close();
                }

                if (objSession != null)
                {
                    objSession.close();
                }

            }
            catch (SQLException e)
            {
                logger.error("getDataFromProcedure 异常", e);
            }
        }

        // 返回结果。
        return lstResult;
    }

    /**
     * 拼调function时需要的参数。
     * 
     * @param strFunctionName
     * @param lstParams
     * @return
     */
    private String converParamsFunction(String strFunctionName, List<Object> lstParams)
    {
        String strProParam = "{?=call " + strFunctionName + "(";

        if (lstParams != null && lstParams.size() > 0)
        {
            for (int i = 0; i < lstParams.size(); i++)
            {
                strProParam += "'" + lstParams.get(i) + "',";
            }

            // 去掉最后一个逗号
            strProParam = strProParam.substring(0, strProParam.length() - 1);
        }
        strProParam += ")}";

        // 返回参数
        return strProParam;
    }

    /**
     * 将resultset转化为list 。
     * 
     * @param objRs
     * @return
     * @throws Exception
     */
    private static List<Map<String, Object>> resultSetToMapLs(ResultSet objRs) throws Exception
    {
        List<Map<String, Object>> lstList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapMap = null;
        ResultSetMetaData objRsMeta = objRs.getMetaData();

        while (objRs.next())
        {
            mapMap = new HashMap<String, Object>();

            for (int i = 0; i < objRsMeta.getColumnCount();)
            {
                String strColumnName = objRsMeta.getColumnLabel(++i);
                Object objColumnValue = objRs.getObject(strColumnName);

                mapMap.put(strColumnName, objColumnValue);
            }

            // 增加到返回的List中。
            lstList.add(mapMap);
        }

        // 返回参数。
        return lstList;
    }

    /**
     * 通过属性查询。
     */
    public T getRecordByProps(final T objEntity, final String strUniquePropertyNames)
    {
        List<T> lstList = (List<T>) getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>()
        {
            public List<T> doInHibernate(Session objSession) throws HibernateException, SQLException
            {
                Criteria objCriteria = objSession.createCriteria(objSupportsClass);
                String[] arrayNameList = strUniquePropertyNames.split(",");

                for (int i = 0; i < arrayNameList.length; i++)
                {
                    try
                    {
                        objCriteria.add(Restrictions.eq(arrayNameList[i], PropertyUtils.getProperty(objEntity, arrayNameList[i])));
                    }
                    catch (Exception e)
                    {
                        ReflectionUtils.handleReflectionException(e);
                    }
                }

                return objCriteria.list();
            }
        });

        // 返回参数。
        if (lstList != null && lstList.size() > 0)
        {
            return (T) lstList.get(0);
        }

        // 没有就返回空值。
        return null;
    }

    /**
     * 通过属性查询。
     */
    public List<T> listByProps(T objEntity, String strUniquePropertyNames)
    {
        return listByProps(objEntity, strUniquePropertyNames, null);
    }

    /**
     * 通过属性查询。
     */
    public List<T> listByProps(T objEntity, String strUniquePropertyNames, Order objOrder)
    {
        return listByProps(-1, -1, objEntity, strUniquePropertyNames, objOrder);
    }

    /**
     * 通过属性查询。
     */
    public List<T> listByProps(long lStart, long lPageSize, T objEntity, String strUniquePropertyNames)
    {
        return listByProps(lStart, lPageSize, objEntity, strUniquePropertyNames, null);
    }

    /**
     * 通过属性查询。
     */
    public List<T> listByProps(final long lStart, final long lPageSize, final T objEntity, final String strUniquePropertyNames, final Order objOrder)
    {
        return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>()
        {
            public List<T> doInHibernate(Session objSession) throws HibernateException, SQLException
            {
                Criteria objCriteria = objSession.createCriteria(objSupportsClass);

                if (StringUtils.hasText(strUniquePropertyNames))
                {
                    String[] lstNameList = strUniquePropertyNames.split(",");

                    for (int i = 0; i < lstNameList.length; i++)
                    {
                        try
                        {
                            objCriteria.add(Restrictions.eq(lstNameList[i], PropertyUtils.getProperty(objEntity, lstNameList[i])));
                        }
                        catch (Exception e)
                        {
                            logger.info("系统发生异常!!", e);
                            ReflectionUtils.handleReflectionException(e);
                        }
                    }
                }
                if (lStart != -1)
                {
                    objCriteria.setFirstResult((int) lStart);
                }
                if (lPageSize != -1)
                {
                    objCriteria.setMaxResults((int) lPageSize);
                }
                if (objOrder != null)
                {
                    objCriteria.addOrder(objOrder);
                }
                return objCriteria.list();
            }
        });
    }

    /**
     * 根据HQL语句与条件集合, 生成Query对象。
     * 
     * @param strHql HQL语句
     * @param arrayValues 条件集合
     * @return Query对象 by YangZhenghua
     */
    public Query createQuery(final String strHql, final Object[] arrayValues)
    {
        Query objQuery = null;

        if ((strHql == null) || (strHql.trim().length() == 0))
        {
            throw new NullPointerException("Hql Can't Be Null!!!");
        }
        if (logger.isDebugEnabled())
        {
            logger.debug("创建 Query 对象(HQL):" + strHql);
        }

        objQuery = this.getSession().createQuery(strHql);

        if (arrayValues != null)
        {
            for (int i = 0, j = arrayValues.length; i < j; i++)
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug("参数[" + (i + 1) + "]:" + arrayValues[i]);
                }
                objQuery.setParameter(i, arrayValues[i]);
            }
        }
        return objQuery;
    }
    
    /**
     * 根据指定sql查询。
     * 
     * @param sql
     * @return
     */
	public List queryBySql(String sql) {
		
		List<Object[]> list = getSession().createSQLQuery(sql).list();
		
		return list;
	}
	
	/**
	 * 执行指定的数据库操作脚本。
	 * 
	 * @param sql
	 * @return
	 */
	public int excuteBySql(String sql) {
		int result;
		SQLQuery query = this.getSession().createSQLQuery(sql);
		result = query.executeUpdate();
		return result;
	}

    /**
     * 按条件进行分页查询 by YangZhenghua。
     */
    public List<T> queryPageListOfHQL(int nPageSize, int nCurrentPage, String strHql, Object[] arrayValues)
    {
        Query objHqlQuery = null;

        if (nPageSize < 1)
        {
            throw new IllegalArgumentException("PageSize Is Must Big Than 1!!!");
        }
        if (nCurrentPage < 1)
        {
            throw new IllegalArgumentException("CurrentPage Is Must Big Than 1!!!");
        }
        if ((strHql == null) || (strHql.trim().length() == 0))
        {
            throw new NullPointerException("Hql Can't Be Null!!!");
        }

        objHqlQuery = this.createQuery(strHql, arrayValues);

        objHqlQuery.setFirstResult(nPageSize * (nCurrentPage - 1));
        objHqlQuery.setMaxResults(nPageSize);

        if (logger.isDebugEnabled())
        {
            logger.debug("==================================================================");
            logger.debug("PageSize:" + nPageSize);
            logger.debug("CurrentPage:" + nCurrentPage);
            logger.debug("==================================================================");
        }
        return objHqlQuery.list();
    }
    
    public List<T> listBySQL(final Pagination<T> objPagination, final String strSQL)
    {
      try
      {
        return getHibernateTemplate().executeFind(new HibernateCallback()
        {
          public Object doInHibernate(Session objSession) throws HibernateException
          {
            Query objQuery = objSession.createSQLQuery(strSQL).addEntity(BaseDaoImpl.this.objSupportsClass);

            if (objPagination == null)
            {
              return objQuery.list();
            }
            BaseDaoImpl.logger.info("分页起始位置：" + objPagination.getFirstResult());
            BaseDaoImpl.logger.info("每页显示的数量：" + objPagination.getPageSize());

            if (objPagination.getFirstResult() != -1)
            {
              objQuery.setFirstResult(objPagination.getFirstResult());
            }

            if (objPagination.getPageSize() != -1)
            {
              objQuery.setMaxResults(objPagination.getPageSize());
            }

            objPagination.setRows(objQuery.list());

            objPagination.setTotal(BaseDaoImpl.this.sizeBySql(strSQL));
            return objQuery.list();
          }
        });
      }
      catch (Exception e)
      {
        logger.error("listBySql - 根据SQL查询数据时出错：" + e.getMessage());
      }return null;
    }

}