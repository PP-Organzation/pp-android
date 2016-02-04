package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Component;

/**
 * Hibernate操作工具类
 * 
 * @author Administrator
 * 
 */
@Component
public class HibernateUtil {
	@Resource(name="sessionFactory")
	private   SessionFactory factory ;	

	/**
	 * 获取回话工厂对象
	 * 
	 * @return
	 */
	public   SessionFactory getFactory() {
		return factory;
	}

	/**
	 * 返回会话链接对象
	 * 
	 * @return
	 */
	public   Session getSession() {
		try {
			return factory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加数据操作
	 * 
	 * @param obj
	 * @return
	 */
	public   boolean add(Object obj) {
		boolean resu = true;

		Session session = getSession();
		try {
			// 开始事务
			Transaction tran = session.beginTransaction();
			// 执行添加操作
			session.save(obj);
			// 提交
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			resu = false;
		} finally {
			session.close();
		}
		return resu;
	}

	/**
	 * 修改数据
	 * 
	 * @param obj
	 * @return
	 */
	public   boolean update(Object obj) {
		boolean resu = true;
		Session session = getSession();
		try {
			// 开始事务
			Transaction tran = session.beginTransaction();
			// 执行添加操作
			session.update(obj);
			// 提交
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			resu = false;
		} finally {
			session.close();
		}
		return resu;
	}

	/**
	 * 删除操作
	 * 
	 * @param obj
	 * @return
	 */
	public   boolean delete(Object obj) {
		boolean resu = true;
		Session session = getSession();
		try {
			// 开始事务
			Transaction tran = session.beginTransaction();
			// 执行添加操作
			session.delete(obj);
			// 提交
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			resu = false;
		} finally {
			session.close();
		}
		return resu;
	}

	/**
	 * 查询，采用泛型<T> T 根据ID返回一条记录
	 * 
	 * @param cla
	 * @param id
	 * @return
	 */
	public   <T> T get(Class cla, int id) {
		T obj = null;
		Session session = getSession();
		try {
			//
			obj = (T) session.get(cla, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return obj;
	}

	/**
	 * 
	 * @param <T>
	 *            集合泛型
	 * @param hql
	 *            语句
	 * @param pras
	 *            参数
	 * @return 使用HQL查询返回一条及以上数据
	 */
	public   <T> List<T> queryHQl(String hql, boolean isCach,
			Object... pras) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {

			Query query = session.createQuery(hql);
			// 设置是否需要将数据设置到缓存
			query.setCacheable(isCach);

			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					query.setString(i, pras[i].toString());
				}
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return list;
	}

	/**
	 * 根据命名方式查询数据返回一条及以上数据
	 * 和queryHQl方法仅仅差一句话session.getNamedQuery(name);
	 * @param name
	 * @param isCach
	 * @param pras
	 * @return
	 */
	public   <T> List<T> queryNameQuery(String name, boolean isCach,
			Object... pras) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {

			Query query = session.getNamedQuery(name);
			// 设置是否需要将数据设置到缓存
			query.setCacheable(isCach);

			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					query.setString(i, pras[i].toString());
				}
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return list;
	}
	/**
	 * 根据命名方式查询数据返回一条及以上数据
	 * 采用的是=:的方式传参
	 * 和上面的queryNameQuery只是传？ 和传=:的差别
	 * @param name
	 * @param isCach
	 * @param pras
	 * @return
	 */
	public   <T> List<T> queryNameQuery(String name, boolean isCach,Map<String, Object> pras) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {

			Query query = session.getNamedQuery(name);
			// 设置是否需要将数据设置到缓存
			query.setCacheable(isCach);

			if (pras != null) {
				for(String key:pras.keySet()){
					query.setString(key, pras.get(key).toString());					
				}
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return list;
	}

	/**
	 * 分页查询
	 * 
	 * @param <T>
	 * @param hql
	 * @param page
	 * @param size
	 * @param pras
	 * @return
	 */
	public   <T> List<T> queryHQlByPage(String hql, int page, int size,
			Object... pras) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {

			Query query = session.createQuery(hql);
			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					query.setString(i, pras[i].toString());
				}
			}
			query.setFirstResult((page - 1) * size);// 设置从那一条开始查询
			query.setMaxResults(size);// 设置当前查询多少条记录
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return list;
	}
	/**
	 * 根据明明方式查询数据分页获取
	 * 和queryHQlByPage方法仅仅差下面一句话session.getNamedQuery(name);
	 * @param name
	 * @param page
	 * @param size
	 * @param pras
	 * @return
	 */
	public   <T> List<T> queryNameQueryByPage(String name, int page, int size,
			Object... pras) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {

			Query query = session.getNamedQuery(name);
			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					query.setString(i, pras[i].toString());
				}
			}
			query.setFirstResult((page - 1) * size);// 设置从那一条开始查询
			query.setMaxResults(size);// 设置当前查询多少条记录
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return list;
	}

	/**
	 * 根据条件使用HQL查询返回单条数据
	 * 
	 * @param <T>
	 */
	public   <T> T queryByUniqe(String hql, Object... pras) {
		T obj = null;
		Session session = getSession();
		try {
			Query query = session.createQuery(hql);
			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					query.setString(i, pras[i].toString());
				}
			}
			// 查询单条数据
			obj = (T) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return obj;
	}

	/**
	 * 
	 * @param hql
	 * @param pras
	 * @return查询一行一列，比如数据记录个数
	 */
	public   Long getCount(String hql, Object... pras) {
		Long count = Long.valueOf("0");
		Session session = getSession();
		try {
			Query query = session.createQuery(hql);
			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					query.setString(i, pras[i].toString());
				}
			}
			// 返回一行一列数据count=(Long) query.list().get(0);还有如下方法
			count = (Long) query.list().iterator().next();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	/**
	 * 应用与离线组合条件查询
	 * 
	 * @param dc
	 * @return
	 */
	public   <T> List<T> queryByDetached(DetachedCriteria dc) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {
			Criteria c = dc.getExecutableCriteria(session);
			list = c.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return list;
	}

	/**
	 * 分页离线组合条件查询
	 * 
	 * @param dc
	 * @return
	 */
	public   <T> List<T> queryByDetachedByPage(DetachedCriteria dc,
			int page, int size) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {
			Criteria c = dc.getExecutableCriteria(session);
			c.setFirstResult((page - 1) * size);// 开始记录的行数
			c.setMaxResults(size);// 每页获取的记录数

			list = c.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}
		return list;
	}

	/**
	 * 使用SQL语句查询
	 * 
	 * @param cla
	 * @param sql
	 * @param pras
	 * @return
	 */
	public   <T> List<T> queryBySQL(Class cla, String sql, Object... pras) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {
			SQLQuery query = session.createSQLQuery(sql);
			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					query.setString(i, pras[i].toString());
				}
			}
			list = query.addEntity(cla).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}
	
	public   <T> List<T> queryBySQLForPage(Class cla, String sql, int page,int size,Object... pras) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {
			SQLQuery query = session.createSQLQuery(sql);
			if (pras != null) {
				for (int i = 0; i < pras.length; i++) {
					query.setString(i, pras[i].toString());
				}
			}
			query.setFirstResult((page - 1) * size);// 设置从那一条开始查询
			query.setMaxResults(size);// 设置当前查询多少条记录
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

}
