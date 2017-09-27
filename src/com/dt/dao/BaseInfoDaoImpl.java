package com.dt.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dt.util.Paging;

public class BaseInfoDaoImpl extends HibernateDaoSupport implements BaseInfoDao {
	
	//按HQL对数据的保存及更新操作
	public Object saveOrUpdate(final Object model) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.saveOrUpdate(model);
				return model;
			}
		});
	}
	
	//按HQL对数据的删除操作
	public void delete(Object model) {
		getHibernateTemplate().delete(model);
	}

	//按HQL查找单个数据
	public Object getObjectById(final Class cs, final Integer id) {
		return (Object) getHibernateTemplate().get(cs, id);
	}
	public Object getObjectById(Class cs, String id) {
		return (Object) getHibernateTemplate().get(cs, id);
	}
		
	//按HQl或者SQL执行操作
	public int updateBySQL(String sql,String type){
		int rows = -1;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		if(type!= null && type.equals("HQL")) {
			rows = session.createQuery(sql).executeUpdate(); 
		}
		if(type!= null && type.equals("SQL")) {
			rows = session.createSQLQuery(sql).executeUpdate(); 
		}
		if(session!=null && session.isConnected()){
        	session.close();
        }
		return rows;
	}
	
	//按HQL语句条件查询，return List
	@SuppressWarnings("unchecked")
	public List<Object> createQuery(String queryString) {
		return getHibernateTemplate().find(queryString);
				
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> createSqlQuery(String queryString) {   
        return getHibernateTemplate().find(queryString);
    }  
	
	//按HQL分页查询，return Paging
	@SuppressWarnings("unchecked")
	public Paging createQueryFy(final Class ca, final Map<String, Object> map,
			final Map<String, Object> pxmap, final Integer curPage,
			final Integer pageSize) {
		return (Paging) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Criteria criteria = session.createCriteria(ca);
						Criteria criteriaTot = session.createCriteria(ca);
						if (map != null) {
							Set set = map.keySet();
							for (Iterator it = set.iterator(); it.hasNext();) {
								String str = (String) it.next();
								if (map.get(str) != null
										&& !map.get(str).equals("")) {
									criteria.add(Restrictions.eq(str, map
											.get(str)));
									criteriaTot.add(Restrictions.eq(str, map
											.get(str)));
								}
							}
						}
						if (pxmap != null) {
							Set set = pxmap.keySet();
							for (Iterator it = set.iterator(); it.hasNext();) {
								String str = (String) it.next();
								if (pxmap.get(str).equals("asc")) {
									criteria.addOrder(Order.asc(str));
								} else {
									criteria.addOrder(Order.desc(str));
								}
							}
						}

						Paging paging = new Paging();
						Long totalRows = ((Long) criteriaTot.setProjection(
								Projections.rowCount()).uniqueResult())
								.longValue();
						criteria.setFirstResult(curPage * pageSize);
						criteria.setMaxResults(pageSize);
						paging.setList(criteria.list());
						paging.setTotalPage(totalRows.intValue());
						paging.setCurPage(curPage);
						paging.setPageSize(pageSize);
						return paging;
					}
				});
	}
	
	//按HQL对某个表中的数据做orderby
	@SuppressWarnings("unchecked")
	public List createCriteria(final Class cs, final Map<String, Object> map,
			final Map<String, Object> orderMap) {
		return (List) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Criteria criteria = session.createCriteria(cs);

						if (map != null) {
							Set<String> key = map.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								criteria.add(Restrictions.eq(s, map.get(s)));
							}
						}

						if (orderMap != null) {
							Set<String> key = orderMap.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								if (orderMap.get(s).equals("desc")) {
									criteria.addOrder(Order.desc(s));
								} else {
									criteria.addOrder(Order.asc(s));
								}
							}
						}
						return criteria.list();
					}
				});
	}
	
	//按HQL分页+orderby
	@SuppressWarnings("unchecked")
	public Paging createQueryPaging(final Class cs,
			final Map<String, Object> map, final Map<String, Object> orderMap,
			final Integer curPage, final Integer pageSize) {
		return (Paging) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Paging paging = new Paging();
						Criteria criteria = session.createCriteria(cs);
						Criteria criteriaTot = session.createCriteria(cs);
						criteria.setFirstResult(curPage * pageSize);
						criteria.setMaxResults(pageSize);
						if (map != null) {
							Set<String> key = map.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								criteria.add(Restrictions.eq(s, map.get(s)));
								criteriaTot.add(Restrictions.eq(s, map.get(s)));
							}
						}
						if (orderMap != null) {
							Set<String> key = orderMap.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								if (orderMap.get(s).equals("desc")) {
									criteria.addOrder(Order.desc(s));
								} else {
									criteria.addOrder(Order.asc(s));
								}
							}
						}
						paging.setList(criteria.list());

						Long totalRows = ((Long) criteriaTot.setProjection(
								Projections.rowCount()).uniqueResult())
								.longValue();
						paging.setTotalPage(totalRows.intValue());
						paging.setCurPage(curPage);
						paging.setPageSize(pageSize);
						return paging;
					}
				});
	}
	
	//分页查询，可用SQL或HQL
	public Paging createQueryPaging(final String queryString,final String countQueryString,final String type,final Integer curPage,final Integer pageSize) {   
        return (Paging) getHibernateTemplate().execute(   
                new HibernateCallback<Object>() {   
                    public Object doInHibernate(org.hibernate.Session session)   
                            throws org.hibernate.HibernateException {   
                    	Paging paging = new Paging();
                    	int start = curPage * pageSize;
                    	if(type != null && type.equals("HQL")){
                    		Query qObj = session.createQuery(queryString);
                    		qObj.setFirstResult(start);
                    		qObj.setMaxResults(pageSize);
                    		List list = qObj.list();
                    		paging.setList(list);
                    		
                    		Query countQueryObj = session.createQuery(countQueryString);
                    		//BigInteger totalRows = (BigInteger)countQueryObj.uniqueResult();
                    		// Long totalRows =((Long) countQueryObj.uniqueResult()).longValue();
                    		Integer totalRows = Integer.parseInt(countQueryObj.uniqueResult().toString());
                    		paging.setTotalPage(totalRows.intValue());
                    		
                    		paging.setTotalRows(totalRows.intValue());
                    		paging.setPageRows(list.size());
                    	}
                    	else if(type != null && type.equals("SQL")){
                    		SQLQuery qObj  = session.createSQLQuery(queryString);
                    		qObj.setFirstResult(start);
                    		qObj.setMaxResults(pageSize);
                    		List list = qObj.list();
                    		paging.setList(list);
                    		
                    		SQLQuery countQueryObj = session.createSQLQuery(countQueryString);
                    		//BigInteger totalRows =((BigInteger) countQueryObj.uniqueResult());
                    		// Long totalRows =((Long) countQueryObj.uniqueResult()).longValue();
                    		Integer totalRows = Integer.parseInt(countQueryObj.uniqueResult().toString());
                    		paging.setTotalPage(totalRows.intValue());
                    		

                    		paging.setTotalRows(totalRows.intValue());
                    		paging.setPageRows(list.size());
                    	}
                    	else{
                    		paging.setTotalPage(0);
                    	}
    					paging.setCurPage(curPage);
    					paging.setPageSize(pageSize);
                        return paging;   
                    }   
                });   
    } 

}
