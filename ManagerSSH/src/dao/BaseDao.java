package dao;

import java.util.List;
/**
 * 数据库操作基础接口
 * @author Administrator
 *
 */
public interface BaseDao {
	/**
	 * 添加数据
	 * 
	 * @param t
	 */
	public <T> boolean save(T t);

	/**
	 * 普通JDBC数据操作使用的删除方法
	 * 
	 * @param t
	 */
	public boolean delete(int t);

	/**
	 * Hibernate专用的删除对象方法
	 * 
	 * @param cla
	 * @param id
	 * @return
	 */
	public <T> boolean delete(Class<T> cla, Integer id);

	/**
	 * 修改数据
	 * 
	 * @param t
	 * @return
	 */
	public <T> boolean update(T t);

	/**
	 * 根据主键ID查询对象
	 * 
	 * @param cla
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> cla, Integer id);

	/**
	 * 根据多条件查询数据，返回一条数据对象
	 * 
	 * @param cla
	 * @param pars
	 * @return
	 */
	public <T> T get(String hql,Class<T> cla, Object... pars);

	/**
	 * 使用HQL语句查询数据
	 * @param hql
	 * @param cla尸体类的对象
	 * @param pars参数列表
	 * @return
	 */
	public <T> List<T> queryByHQL(String hql, Class<T> cla, Object... pars);

	/**
	 * 使用SQL语句查询数据
	 * 
	 * @param hql
	 * @param cla
	 * @param pars
	 * @return
	 */
	public <T> List<T> queryBySQL(String sql, Class<T> cla, Object... pars);

	/**
	 * HQL分页获取数据
	 * 
	 * @param hql
	 * @param cla
	 * @param page页数
	 * @param size个数
	 * @param pars
	 * @return
	 */
	public <T> List<T> queryByHQLForPage(String hql, Class<T> cla, int page,
			int size, Object... pars);

	/**
	 * SQL分页获取数据
	 * 
	 * @param sql
	 * @param cla
	 * @param page
	 * @param size
	 * @param pars
	 * @return
	 */
	public <T> List<T> queryBySQLForPage(String sql, Class<T> cla, int page,
			int size, Object... pars);
	/**
	 * 获取数据记录个数
	 * @param pras
	 * @return
	 */
	public Long getCount( String hql,Object ...pras);
}
