package com.dt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.dt.dao.BaseInfoDao;

@Repository("BaseInfoDaoImpl")
public class BaseInfoDaoImpl extends HibernateDaoSupport implements BaseInfoDao {

	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@SuppressWarnings("unchecked")
	public List<Object> selectBaseList() {
		// TODO Auto-generated method stub
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
