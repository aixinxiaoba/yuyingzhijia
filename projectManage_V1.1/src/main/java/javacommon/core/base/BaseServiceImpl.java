package javacommon.core.base;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javacommon.util.SearchCondition;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.manage.crm.util.Pagination;

/**
 * 通用Service层抽象类。
 * 
 * @author wangzg
 * 
 * @param <T>
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	/**
	 * 提示信息 。
	 */
	protected String strMag;

	/**
	 * 格式化日期对象格式:---到秒
	 */
	protected DateFormat objDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

	/**
	 * 格式化日期对象格式:---到天
	 */
	protected DateFormat objDateFormatByDay = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 格式化日期对象格式:---到分钟
	 */
	protected DateFormat objDateFormatBySecond = new SimpleDateFormat("yyyy-MM-dd HH-mm");

	/**
	 * 格式化日期对象格式:---年
	 */
	protected DateFormat objFormatYesr = new SimpleDateFormat("yyyy");

	/**
	 * 格式化日期对象格式:---月
	 */
	protected DateFormat objFormatMonth = new SimpleDateFormat("MM");

	/**
	 * 格式化日期对象格式:---日
	 */
	protected DateFormat objFormatDay = new SimpleDateFormat("dd");

	/**
	 * 拿到具体的Dao层类，子类一定要重写这个方法, Server层中所有操作都依赖具体的Dao实现类。
	 *
	 * @return
	 */
	public abstract BaseDao<T> getBaseDao();

	/**
	 * 删除。
	 */
	public boolean delete(T objEntity) throws DataAccessException {
		return getBaseDao().delete(objEntity);
	}

	/**
	 * 删除ByCriteria。
	 */
	@Override
	public boolean deleteByCriteria(SimpleExpression objOne, SimpleExpression objTwo) throws DataAccessException {
		return getBaseDao().deleteByCriteria(objOne, objTwo);
	}

	/**
	 * 根据id删除。
	 */
	@Override
	public boolean deleteById(Serializable objId) throws DataAccessException {
		return getBaseDao().deleteById(objId);
	}

	/**
	 * 查询数据。
	 */
	@Override
	public T getByCriteria(SimpleExpression objOne, SimpleExpression objTwo) throws DataAccessException {
		return getBaseDao().getByCriteria(objOne, objTwo);
	}

	/**
	 * 通过hql查询数据。
	 */
	@Override
	public T getByHql(String strHql) throws DataAccessException {
		return getBaseDao().getByHql(strHql);
	}

	/**
	 * 根据id查询数据。
	 */
	@Override
	public T getById(String strId) throws DataAccessException {
		return getBaseDao().getById(strId);
	}

	/**
	 * 根据hql查询数据。
	 */
	@Override
	public T getBySql(String strSql) throws DataAccessException {
		return getBaseDao().getBySql(strSql);
	}

	/**
	 * 查询全部数据。
	 */
	@Override
	public List<T> listAll(Order objOrd) throws DataAccessException {
		return getBaseDao().listAll(objOrd);
	}

	// /**
	// *查询全部数据。
	// */
	// @Override
	// public List<T> listByCriteria(Pagination objPagination, long lStart, long
	// lPagesize, SimpleExpression objOne, SimpleExpression objTwo) throws
	// DataAccessException
	// {
	// return getBaseDao().listByCriteria(objPagination, lStart, lPagesize,
	// objOne, objTwo);
	// }

	/**
	 * 查询全部数据。
	 */
	@Override
	public List<T> listByCriteria(Pagination<T> objPagination, SearchCondition objCondition, Order objOrder) throws DataAccessException {
		return getBaseDao().listByCriteria(objPagination, objCondition, objOrder);
	}

	// /**
	// *查询全部数据。
	// */
	// @Override
	// public List<T> listByCriteria(long lStart, long lPagesize,
	// SimpleExpression objOne, SimpleExpression objTwo) throws
	// DataAccessException
	// {
	// return getBaseDao().listByCriteria(lStart, lPagesize, objOne, objTwo);
	// }
	//
	// /**
	// *查询全部数据。
	// */
	// @Override
	// public List<T> listByCriteria(long lStart, long lPagesize,
	// SimpleExpression objOne, SimpleExpression objTwo, Order objOrder) throws
	// DataAccessException
	// {
	// return getBaseDao().listByCriteria(lStart, lPagesize, objOne, objTwo,
	// objOrder);
	// }

	/**
	 * 根据hql查询全部数据。
	 */
	@Override
	public List<T> listByHql(long lStart, long lPagesize, String strHql) throws DataAccessException {
		return getBaseDao().listByHql(lStart, lPagesize, strHql);
	}

	/**
	 * 根据hql查询全部数据。
	 */
	@Override
	public List<T> listByHql(String strHql) throws DataAccessException {
		return getBaseDao().listByHql(strHql);
	}

	/**
	 * 根据sql查询全部数据。
	 */
	@Override
	public List<T> listBySql(String strHql) throws DataAccessException {
		return getBaseDao().listBySql(strHql);
	}

	/**
	 * 根据sql查询全部数据。
	 */
	@Override
	public List<T> listBySql(long lStart, long lPagesize, String strHql) throws DataAccessException {
		return getBaseDao().listBySql(lStart, lPagesize, strHql);
	}

	/**
	 * 保存数据。
	 */
	@Override
	public boolean save(T objEntity) throws DataAccessException {
		return getBaseDao().save(objEntity);
	}

	/**
	 * 保存更新数据。
	 */
	@Override
	public boolean saveOrUpdate(T objEntity) throws DataAccessException {
		return getBaseDao().saveOrUpdate(objEntity);
	}

	/**
	 * 取得集合大小。
	 */
	@Override
	public long sizeAll() throws DataAccessException {
		return getBaseDao().sizeAll();
	}

	/**
	 * 取得集合大小。
	 */
	@Override
	public long sizeByCriteria(SimpleExpression objOne, SimpleExpression objTwo) throws DataAccessException {
		return getBaseDao().sizeByCriteria(objOne, objTwo);
	}

	/**
	 * 取得集合大小。
	 */
	@Override
	public long sizeByHql(String strHql) throws DataAccessException {
		return getBaseDao().sizeByHql(strHql);
	}

	/**
	 * 取得集合大小。
	 */
	@Override
	public long sizeBySql(String strHql) throws DataAccessException {
		return getBaseDao().sizeBySql(strHql);
	}

	/**
	 * 更新。
	 */
	@Override
	public boolean update(T objEntity) throws DataAccessException {
		return getBaseDao().update(objEntity);
	}

	/**
	 * 是否唯一。
	 */
	@Override
	public boolean isUnique(T objEntity, String strUniquePropertyNames) {
		return getBaseDao().isUnique(objEntity, strUniquePropertyNames);
	}

	/**
	 * createQuery。
	 */
	@Override
	public Query createQuery(String strHql, Object[] arrayValues) {
		return getBaseDao().createQuery(strHql, arrayValues);
	}

	/**
	 * 删除。
	 */
	@Override
	public boolean deleteByProps(T objEntity, String strUniquePropertyNames) throws DataAccessException {
		return getBaseDao().deleteByProps(objEntity, strUniquePropertyNames);
	}

	/**
	 * 根据id查询数据。
	 */
	@Override
	public T getById(Serializable objId) {
		return getBaseDao().getById(objId);
	}

	/**
	 * getDataFromProcedure。
	 */
	@Override
	public List<Map<String, Object>> getDataFromProcedure(Session objSession, String strProcedureName, List<Object> lstParams) {
		return getBaseDao().getDataFromProcedure(objSession, strProcedureName, lstParams);
	}

	/**
	 * 根据属性查询。
	 */
	@Override
	public T getRecordByProps(T objEntity, String strUniquePropertyNames) {
		return getBaseDao().getRecordByProps(objEntity, strUniquePropertyNames);
	}

	/**
	 * 保存数据。
	 */
	@Override
	public Serializable insert(T objEntity) {
		return getBaseDao().insert(objEntity);
	}

	/**
	 * 查询数据。
	 */
	@Override
	public List<T> listByProps(T objEntity, String strUniquePropertyNames) {
		return getBaseDao().listByProps(objEntity, strUniquePropertyNames);
	}

	/**
	 * 查询数据。
	 */
	@Override
	public List<T> listByProps(T objEntity, String strUniquePropertyNames, Order objOrder) {
		return getBaseDao().listByProps(objEntity, strUniquePropertyNames, objOrder);
	}

	/**
	 * 查询数据。
	 */
	@Override
	public List<T> listByProps(long lStart, long lPageSize, T objEntity, String strUniquePropertyNames) {
		return getBaseDao().listByProps(lStart, lPageSize, objEntity, strUniquePropertyNames);
	}

	/**
	 * 查询数据。
	 */
	@Override
	public List<T> listByProps(long lStart, long lPageSize, T objEntity, String strUniquePropertyNames, Order objOrder) {
		return getBaseDao().listByProps(lStart, lPageSize, objEntity, strUniquePropertyNames, objOrder);
	}

	/**
	 * 查询数据。
	 */
	@Override
	public List<T> queryPageListOfHQL(int nPageSize, int nCurrentPage, String strHql, Object[] arrayValues) {
		return getBaseDao().queryPageListOfHQL(nPageSize, nCurrentPage, strHql, arrayValues);
	}

	/**
	 * 保存数据。
	 */
	@Override
	public boolean saveAndFlush(T objEntity) {
		return getBaseDao().saveAndFlush(objEntity);
	}

	/**
	 * 保存数据。
	 */
	@Override
	public String saveObject(T objEntity) {
		return getBaseDao().saveObject(objEntity);
	}

	public List<T> listBySQL(Pagination<T> objPagination, String strSQL) {
		return getBaseDao().listBySQL(objPagination, strSQL);
	}
	
	/**
     * 根据指定sql查询。
     * 
     * @param sql
     * @return
     */
	public List queryBySql(String sql) {
		return getBaseDao().queryBySql(sql);
	}
	
	/**
	 * 执行指定的数据库操作脚本。
	 * 
	 * @param sql
	 * @return
	 */
	public int excuteBySql(String sql) {
		return getBaseDao().excuteBySql(sql);
	}
}
