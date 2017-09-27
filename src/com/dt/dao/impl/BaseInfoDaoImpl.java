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
				StringBuilder sb=new StringBuilder();
				sb.append("select e.Detector_Equipment_No,e.Detector_Equipment_Name,er.Detector_EquipmentRoom_name,et.Detector_EquipmentType_name from Detector_Equipment e");
				sb.append(" LEFT JOIN Detector_EquipmentRoom er ON e.Detector_EquipmentRoom_Id=er.Detector_EquipmentRoom_Id");
				sb.append(" LEFT JOIN Detector_EquipmentType et ON e.Detector_EquipmentType_Id=et.Detector_EquipmentType_Id");
				SQLQuery qObj = session.createSQLQuery(sb.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
		

	}

}
