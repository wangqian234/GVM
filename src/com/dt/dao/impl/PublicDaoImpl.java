package com.dt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.dt.dao.PublicDao;

@Repository("PublicDaoImpl")
public class PublicDaoImpl extends HibernateDaoSupport implements PublicDao {

	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@SuppressWarnings("unchecked")
	public List<Object> selectFacilityList() {
		// TODO Auto-generated method stub
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				StringBuilder sb = new StringBuilder();
				sb.append(
						"select f.Detector_Facility_Id,f.Detector_Facility_Name,f.Detector_Facility_Alias,f.Detector_Facility_State from Detector_Facility f");
				
				SQLQuery qObj = session.createSQLQuery(sb.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
	}

}
