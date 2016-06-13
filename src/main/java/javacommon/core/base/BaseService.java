package javacommon.core.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javacommon.util.SearchCondition;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.dao.DataAccessException;

import com.manage.crm.util.Pagination;

/**
 * 通用Service层接口。
 * 
 * @author wangzg
 * 
 * @param <E>
 */
public interface BaseService<E>
{
    /**
     * 增加一个实体。
     * 
     * @param E 实体
     * @return 返回的ID
     */
    public String saveObject(E objEntity);

    /**
     * 增加一个实体。
     * 
     * @param E 实体
     * @return
     * @throws DataAccessException
     */
    public boolean save(E entity) throws DataAccessException;

    /**
     * 立即保存实体对象 。
     * 
     * @param entity
     * @return
     */
    public boolean saveAndFlush(E objEntity);

    /**
     * 保存数据，并返回主键id 。
     * 
     * @param objEntity
     * @return
     */
    public Serializable insert(E objEntity);

    /**
     * 增加或更新一个实体。
     * 
     * @param E 实体
     * @return
     * @throws DataAccessException
     */
    public boolean saveOrUpdate(E entity) throws DataAccessException;

    /**
     * 根据主键删除对象。
     * 
     * @param id 主键
     * @return
     * @throws DataAccessException
     */
    public boolean deleteById(Serializable id) throws DataAccessException;

    /**
     * 根据查询条件删除对象。
     * 
     * @param one 查询条件一
     * @param two 查询条件二
     * @return
     * @throws DataAccessException
     */
    public boolean deleteByCriteria(SimpleExpression one, SimpleExpression two) throws DataAccessException;

    /**
     * 删除一个实体。
     * 
     * @param E 更新实体
     * @return
     * @throws DataAccessException
     */
    public boolean delete(E entity) throws DataAccessException;

    /**
     * 删除对象。
     * 
     * @param entity
     * @param uniquePropertyNames
     * @return
     * @throws DataAccessException
     */
    public boolean deleteByProps(E objEntity, String strUniquePropertyNames) throws DataAccessException;

    /**
     * 更新一个实体。
     * 
     * @param E 实体
     * @return
     * @throws DataAccessException
     */
    public boolean update(E entity) throws DataAccessException;

    /**
     * 判断对象是否唯一 。
     * 
     * @param entity
     * @param uniquePropertyNames
     * @return
     */
    public boolean isUnique(E objEntity, String strUniquePropertyNames);

    /**
     * 根据主键得到对象 。
     * 
     * @param id 主键
     * @return
     */
    public E getById(Serializable objId);

    /**
     * 根据主键得到对象 。
     * 
     * @param id 主键
     * @return
     */
    public E getById(String id) throws DataAccessException;

    /**
     * 根据sql查询一个对象 。
     * 
     * @param sql 语句
     * @return
     */
    public E getBySql(String strSql);

    /**
     * 根据sql查询一个对象 。
     * 
     * @param sql 语句
     * @return
     */
    public E getByHql(String strSql);

    /**
     * 根据Criteria查询一个对象 。
     * 
     * @param one 查询条件一
     * @param two 查询条件二
     * @return
     */
    public E getByCriteria(SimpleExpression objOne, SimpleExpression objTwo);

    /**
     * 查询对象所有数据 。
     * 
     * @return
     */
    public List<E> listAll(Order objOrd);

    /**
     * 查询对象所有数据的大小 。
     * 
     * @return
     */
    public long sizeAll();

    /**
     * 根据SQL查询数据 。
     * 
     * @param Sql 查询语句
     * @return
     */
    public List<E> listBySql(String strSql);

    /**
     * 根据SQL查询数据 。
     * 
     * @param Sql 查询语句
     * @param start 分页的开始数据
     * @param pagesize 分页大小
     * @return
     */
    public List<E> listBySql(long start, long pagesize, String strSql);

    /**
     * 根据HQL查询数据 。
     * 
     * @param Sql 查询语句
     * @param start 分页的开始数据
     * @param pagesize 分页大小
     * @return
     */
    public List<E> listByHql(long start, long pagesize, String strHql);

    /**
     * 根据SQL查询数据大小 。
     * 
     * @param strSql 查询语句
     * @return long
     */
    public long sizeBySql(String strSql);

    /**
     * 根据HQL查询数据大小 。
     * 
     * @param strHql 查询语句
     * @return long
     */
    public long sizeByHql(String strHql);

    /**
     * 根据HQL查询数据 。
     * 
     * @param strSql 查询语句
     * @return
     */
    public List<E> listByHql(String strSql);

    // /**
    // * 根据Criteria查询数据 。
    // *
    // * @param start 分页的开始数据
    // * @param pagesize 分页大小
    // * @param objOne 查询条件一
    // * @param objTwo 查询条件二
    // * @return
    // */
    // public List<E> listByCriteria(Pagination objPagination, long start, long pagesize, SimpleExpression objOne, SimpleExpression objTwo) throws DataAccessException;

    /**
     * 根据Criteria查询数据 。
     * 
     * @param start 分页的开始数据
     * @param pagesize 分页大小
     * @param objOne 查询条件一
     * @param objTwo 查询条件二
     * @param objOrder 数据排序
     * @return
     */
    public List<E> listByCriteria(Pagination<E> objPagination, SearchCondition objCondition, Order objOrder) throws DataAccessException;

    //    
    // /**
    // * 根据Criteria查询数据 。
    // *
    // * @param start 分页的开始数据
    // * @param pagesize 分页大小
    // * @param objOne 查询条件一
    // * @param objTwo 查询条件二
    // * @return
    // */
    // public List<E> listByCriteria(long start, long pagesize, SimpleExpression objOne, SimpleExpression objTwo);
    //
    // /**
    // * 根据Criteria查询数据 。
    // *
    // * @param start 分页的开始数据
    // * @param pagesize 分页大小
    // * @param objOne 查询条件一
    // * @param objTwo 查询条件二
    // * @param objOrder 数据排序
    // * @return
    // */
    // public List<E> listByCriteria(long start, long pagesize, SimpleExpression objOne, SimpleExpression objTwo, Order objOrder);

    /**
     * 根据Criteria查询数据大小 。
     * 
     * @param objOne 查询条件一
     * @param objTwo 查询条件二
     * @return
     */
    public long sizeByCriteria(SimpleExpression objOne, SimpleExpression objTwo);

    /**
     * 根据实体对象的属性拿对象 。
     * 
     * @param objEntity
     * @param strUniquePropertyNames
     * @return
     */
    public E getRecordByProps(E objEntity, String strUniquePropertyNames);

    /**
     * 根据实体对象的属性拿对象 。
     * 
     * @param objEntity
     * @param strUniquePropertyNames
     * @return
     */
    public List<E> listByProps(E objEntity, String strUniquePropertyNames);

    /**
     * 根据实体对象的属性拿对象。
     * 
     * @param objEntity
     * @param strUniquePropertyNames
     * @param objOrder
     * @return
     */
    public List<E> listByProps(E objEntity, String strUniquePropertyNames, Order objOrder);

    /**
     * 根据实体对象的属性拿对象。
     * 
     * @param start
     * @param pageSize
     * @param entity
     * @param uniquePropertyNames
     * @return
     */
    public List<E> listByProps(long start, long pageSize, E objEntity, String strUniquePropertyNames);

    /**
     * 根据实体对象的属性拿对象。
     * 
     * @param start
     * @param pageSize
     * @param objEntity
     * @param strUniquePropertyNames
     * @param order
     * @return
     */
    public List<E> listByProps(long start, long pageSize, E objEntity, String strUniquePropertyNames, Order objOrder);

    /**
     * 根据HQL进行分页查询。
     * 
     * @param pageSize 每页显示数量
     * @param currentPage 当前页码
     * @param hql HQL语句
     * @param values HQL语句的参数数组
     * @return 实体集合
     * 
     */
    public List<E> queryPageListOfHQL(final int pageSize, final int currentPage, final String hql, final Object[] values);

    /**
     * 取得总记录数。
     * 
     * @param hql
     * @param values
     * @return
     */
    public Query createQuery(final String strHql, final Object[] values);

    /**
     * 调用oracle函数(存储程序):---要求function中前两个参数分别为: 分页开始数, 分页结束数。
     * 
     * @param strProcedureName 函数名
     * @param lstParams 参数列表
     */
    public List<Map<String, Object>> getDataFromProcedure(Session objSession, String strProcedureName, List<Object> lstParams);

    public List<E> listBySQL(final Pagination<E> objPagination, final String strSQL);
    
    /**
     * 根据指定sql查询。。
     * 
     * @param hql
     * @param values
     * @return
     */
    public List queryBySql(String sql);
    
    /**
	 * 执行指定的数据库操作脚本。
	 * 
	 * @param sql
	 * @return
	 */
	public int excuteBySql(String sql);
    
}
