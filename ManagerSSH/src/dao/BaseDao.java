package dao;

import java.util.List;
/**
 * ���ݿ���������ӿ�
 * @author Administrator
 *
 */
public interface BaseDao {
	/**
	 * �������
	 * 
	 * @param t
	 */
	public <T> boolean save(T t);

	/**
	 * ��ͨJDBC���ݲ���ʹ�õ�ɾ������
	 * 
	 * @param t
	 */
	public boolean delete(int t);

	/**
	 * Hibernateר�õ�ɾ�����󷽷�
	 * 
	 * @param cla
	 * @param id
	 * @return
	 */
	public <T> boolean delete(Class<T> cla, Integer id);

	/**
	 * �޸�����
	 * 
	 * @param t
	 * @return
	 */
	public <T> boolean update(T t);

	/**
	 * ��������ID��ѯ����
	 * 
	 * @param cla
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> cla, Integer id);

	/**
	 * ���ݶ�������ѯ���ݣ�����һ�����ݶ���
	 * 
	 * @param cla
	 * @param pars
	 * @return
	 */
	public <T> T get(String hql,Class<T> cla, Object... pars);

	/**
	 * ʹ��HQL����ѯ����
	 * @param hql
	 * @param claʬ����Ķ���
	 * @param pars�����б�
	 * @return
	 */
	public <T> List<T> queryByHQL(String hql, Class<T> cla, Object... pars);

	/**
	 * ʹ��SQL����ѯ����
	 * 
	 * @param hql
	 * @param cla
	 * @param pars
	 * @return
	 */
	public <T> List<T> queryBySQL(String sql, Class<T> cla, Object... pars);

	/**
	 * HQL��ҳ��ȡ����
	 * 
	 * @param hql
	 * @param cla
	 * @param pageҳ��
	 * @param size����
	 * @param pars
	 * @return
	 */
	public <T> List<T> queryByHQLForPage(String hql, Class<T> cla, int page,
			int size, Object... pars);

	/**
	 * SQL��ҳ��ȡ����
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
	 * ��ȡ���ݼ�¼����
	 * @param pras
	 * @return
	 */
	public Long getCount( String hql,Object ...pras);
}
