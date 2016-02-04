package dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import util.HibernateUtil;

import dao.BaseDao;

/**
 * 数据库基本操作的抽象基类
 * 
 * @author Administrator
 * 
 */
@Repository
public class AbsBaseDao implements BaseDao {
	@Resource
	private HibernateUtil util;
	/**
	 * 添加数据
	 */
	public <T> boolean save(T t) {
		// TODO Auto-generated method stub
		return util.add(t);
	}

	/**
	 * 普通JDBC数据操作使用的删除方法这里不实现
	 */
	public boolean delete(int t) {
		return false;
	}

	/**
	 * Hibernate专用的删除对象方法
	 */
	public <T> boolean delete(Class<T> cla, Integer id) {
		T obj = util.get(cla, id);
		if (obj != null) {
			return util.delete(obj);
		}
		return false;

	}

	/**
	 * 修改数据
	 */
	public <T> boolean update(T t) {
		// TODO Auto-generated method stub
		return util.update(t);
	}

	/**
	 * Hibernate专用的删除对象方法
	 */
	public <T> T get(Class<T> cla, Integer id) {
		// TODO Auto-generated method stub
		return util.get(cla, id);
	}

	/**
	 * 根据多条件查询数据，返回一条数据对象
	 */
	public <T> T get(String hql, Class<T> cla, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryByUniqe(hql, pars);
	}

	/**
	 * 使用HQL语句查询数据
	 */
	public <T> List<T> queryByHQL(String hql, Class<T> cla, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryHQl(hql, true, pars);
	}

	/**
	 * 使用SQL语句查询数据
	 */
	public <T> List<T> queryBySQL(String sql, Class<T> cla, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryBySQL(cla, sql, pars);
	}

	/**
	 * HQL分页获取数据
	 */
	public <T> List<T> queryByHQLForPage(String hql, Class<T> cla, int page,
			int size, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryHQlByPage(hql, page, size, pars);
	}

	/**
	 * SQL分页获取数据
	 */
	public <T> List<T> queryBySQLForPage(String sql, Class<T> cla, int page,
			int size, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryBySQLForPage(cla, sql, page, size, pars);
	}

	/**
	 * 获取数据记录个数
	 */
	public Long getCount(String hql, Object... pras) {
		// TODO Auto-generated method stub
		return util.getCount(hql, pras);
	}

}
