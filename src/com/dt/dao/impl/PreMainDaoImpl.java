package com.dt.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
				String sql = "select Detector_Equipment_Name,Detector_MaintenanceLog_EndDate,Detector_TriggerLog_Time,Detector_MaintenanceLog_Content,Detector_Equipment_Lifetime,Detector_Equipment_UseDate"
						+ " from Detector_Equipment eq left join Detector_MaintenanceLog mtl on eq.Detector_Equipment_Id = mtl.Detector_Equipment_Id "
						+ "left join Detector_Sensor ss on eq.Detector_Equipment_Id = ss.Detector_Equipment_Id "
						+ "left join  Detector_TriggerLog tgl on ss.Detector_Sensor_Id = tgl.Detector_Sensor_Id";
				SQLQuery qObj = session.createSQLQuery(sql);
				List<Object> list = qObj.list();
				return list;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object> analyzeList() {
		// TODO 自动生成的方法存根
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				String sql = "select Detector_MaintenanceLog_EndDate,Detector_TriggerLog_Time,Detector_Equipment_Lifetime,Detector_Equipment_UseDate"
						+ " from Detector_Equipment eq left join Detector_MaintenanceLog mtl on eq.Detector_Equipment_Id = mtl.Detector_Equipment_Id "
						+ "left join Detector_Sensor ss on eq.Detector_Equipment_Id = ss.Detector_Equipment_Id "
						+ "left join  Detector_TriggerLog tgl on ss.Detector_Sensor_Id = tgl.Detector_Sensor_Id";
				SQLQuery qObj = session.createSQLQuery(sql);
				List<Object> list = qObj.list();
				return list;
			}
		});
		
	}
}
