package com.dt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dt.dao.PreMainDao;

@Repository("PreMainDaoImpl")
public class PreMainDaoImpl extends HibernateDaoSupport implements PreMainDao{
	
	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}
	
	//设备list
	@SuppressWarnings("unchecked")
	public List<Object> selectEquipList(){
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				String sql = "select * from Detector_Equipment";
				SQLQuery qObj = session.createSQLQuery(sql);
				List<Object> list = qObj.list();
				return list;
			}
		});
	}
}
