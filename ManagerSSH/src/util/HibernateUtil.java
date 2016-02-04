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
 * Hibernate����������
 * 
 * @author Administrator
 * 
 */
@Component
public class HibernateUtil {
	@Resource(name="sessionFactory")
	private   SessionFactory factory ;	

	/**
	 * ��ȡ�ػ���������
	 * 
	 * @return
	 */
	public   SessionFactory getFactory() {
		return factory;
	}

	/**
	 * ���ػỰ���Ӷ���
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
	 * ������ݲ���
	 * 
	 * @param obj
	 * @return
	 */
	public   boolean add(Object obj) {
		boolean resu = true;

		Session session = getSession();
		try {
			// ��ʼ����
			Transaction tran = session.beginTransaction();
			// ִ����Ӳ���
			session.save(obj);
			// �ύ
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
	 * �޸�����
	 * 
	 * @param obj
	 * @return
	 */
	public   boolean update(Object obj) {
		boolean resu = true;
		Session session = getSession();
		try {
			// ��ʼ����
			Transaction tran = session.beginTransaction();
			// ִ����Ӳ���
			session.update(obj);
			// �ύ
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
	 * ɾ������
	 * 
	 * @param obj
	 * @return
	 */
	public   boolean delete(Object obj) {
		boolean resu = true;
		Session session = getSession();
		try {
			// ��ʼ����
			Transaction tran = session.beginTransaction();
			// ִ����Ӳ���
			session.delete(obj);
			// �ύ
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
	 * ��ѯ�����÷���<T> T ����ID����һ����¼
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
	 *            ���Ϸ���
	 * @param hql
	 *            ���
	 * @param pras
	 *            ����
	 * @return ʹ��HQL��ѯ����һ������������
	 */
	public   <T> List<T> queryHQl(String hql, boolean isCach,
			Object... pras) {
		List<T> list = new ArrayList<T>();
		Session session = getSession();
		try {

			Query query = session.createQuery(hql);
			// �����Ƿ���Ҫ���������õ�����
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
	 * ����������ʽ��ѯ���ݷ���һ������������
	 * ��queryHQl����������һ�仰session.getNamedQuery(name);
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
			// �����Ƿ���Ҫ���������õ�����
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
	 * ����������ʽ��ѯ���ݷ���һ������������
	 * ���õ���=:�ķ�ʽ����
	 * �������queryNameQueryֻ�Ǵ��� �ʹ�=:�Ĳ��
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
			// �����Ƿ���Ҫ���������õ�����
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
	 * ��ҳ��ѯ
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
			query.setFirstResult((page - 1) * size);// ���ô���һ����ʼ��ѯ
			query.setMaxResults(size);// ���õ�ǰ��ѯ��������¼
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
	 * ����������ʽ��ѯ���ݷ�ҳ��ȡ
	 * ��queryHQlByPage��������������һ�仰session.getNamedQuery(name);
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
			query.setFirstResult((page - 1) * size);// ���ô���һ����ʼ��ѯ
			query.setMaxResults(size);// ���õ�ǰ��ѯ��������¼
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
	 * ��������ʹ��HQL��ѯ���ص�������
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
			// ��ѯ��������
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
	 * @return��ѯһ��һ�У��������ݼ�¼����
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
			// ����һ��һ������count=(Long) query.list().get(0);�������·���
			count = (Long) query.list().iterator().next();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	/**
	 * Ӧ�����������������ѯ
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
	 * ��ҳ�������������ѯ
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
			c.setFirstResult((page - 1) * size);// ��ʼ��¼������
			c.setMaxResults(size);// ÿҳ��ȡ�ļ�¼��

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
	 * ʹ��SQL����ѯ
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
			query.setFirstResult((page - 1) * size);// ���ô���һ����ʼ��ѯ
			query.setMaxResults(size);// ���õ�ǰ��ѯ��������¼
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
