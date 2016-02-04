package dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import util.HibernateUtil;

import dao.BaseDao;

/**
 * ���ݿ���������ĳ������
 * 
 * @author Administrator
 * 
 */
@Repository
public class AbsBaseDao implements BaseDao {
	@Resource
	private HibernateUtil util;
	/**
	 * �������
	 */
	public <T> boolean save(T t) {
		// TODO Auto-generated method stub
		return util.add(t);
	}

	/**
	 * ��ͨJDBC���ݲ���ʹ�õ�ɾ���������ﲻʵ��
	 */
	public boolean delete(int t) {
		return false;
	}

	/**
	 * Hibernateר�õ�ɾ�����󷽷�
	 */
	public <T> boolean delete(Class<T> cla, Integer id) {
		T obj = util.get(cla, id);
		if (obj != null) {
			return util.delete(obj);
		}
		return false;

	}

	/**
	 * �޸�����
	 */
	public <T> boolean update(T t) {
		// TODO Auto-generated method stub
		return util.update(t);
	}

	/**
	 * Hibernateר�õ�ɾ�����󷽷�
	 */
	public <T> T get(Class<T> cla, Integer id) {
		// TODO Auto-generated method stub
		return util.get(cla, id);
	}

	/**
	 * ���ݶ�������ѯ���ݣ�����һ�����ݶ���
	 */
	public <T> T get(String hql, Class<T> cla, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryByUniqe(hql, pars);
	}

	/**
	 * ʹ��HQL����ѯ����
	 */
	public <T> List<T> queryByHQL(String hql, Class<T> cla, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryHQl(hql, true, pars);
	}

	/**
	 * ʹ��SQL����ѯ����
	 */
	public <T> List<T> queryBySQL(String sql, Class<T> cla, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryBySQL(cla, sql, pars);
	}

	/**
	 * HQL��ҳ��ȡ����
	 */
	public <T> List<T> queryByHQLForPage(String hql, Class<T> cla, int page,
			int size, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryHQlByPage(hql, page, size, pars);
	}

	/**
	 * SQL��ҳ��ȡ����
	 */
	public <T> List<T> queryBySQLForPage(String sql, Class<T> cla, int page,
			int size, Object... pars) {
		// TODO Auto-generated method stub
		return util.queryBySQLForPage(cla, sql, page, size, pars);
	}

	/**
	 * ��ȡ���ݼ�¼����
	 */
	public Long getCount(String hql, Object... pras) {
		// TODO Auto-generated method stub
		return util.getCount(hql, pras);
	}

}
