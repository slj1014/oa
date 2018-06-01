package oa.base.dao.impl;



import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import oa.base.dao.BaseDao;
import oa.cfg.Configuration;
import oa.domain.PageBean;
import oa.utils.HqlHelper;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	protected Class<T> clazz; // 这是一个问题！

	public BaseDaoImpl() {
		// 通过反射得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class) pt.getActualTypeArguments()[0];
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Long id) {
		Object obj = getSession().get(clazz, id);
		getSession().delete(obj);
	}

	public T getById(Long id) {
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}

		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	// 最终版
		public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) {
			System.out.println("--------------> BaseDaoImpl.getPageBean( int pageNum, HqlHelper hqlHelper )");
			int pageSize = Configuration.getPageSize();
			List<Object> parameters = hqlHelper.getParameters();

			// 查询本页的数据列表
			Query listQuery = getSession().createQuery(hqlHelper.getQueryListHql());
			if (parameters != null && parameters.size() > 0) { // 设置参数
				for (int i = 0; i < parameters.size(); i++) {
					listQuery.setParameter(i, parameters.get(i));
				}
			}
			listQuery.setFirstResult((pageNum - 1) * pageSize);
			listQuery.setMaxResults(pageSize);
			List list = listQuery.list(); // 执行查询

			// 查询总记录数
			Query countQuery = getSession().createQuery(hqlHelper.getQueryCountHql());
			if (parameters != null && parameters.size() > 0) { // 设置参数
				for (int i = 0; i < parameters.size(); i++) {
					countQuery.setParameter(i, parameters.get(i));
				}
			}
			Long count = (Long) countQuery.uniqueResult(); // 执行查询

			return new PageBean(pageNum, pageSize, list, count.intValue());
		}


}
