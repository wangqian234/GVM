package com.dt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dt.dao.ErrorStateDao;
import com.dt.entity.DetectorTriggerLog;

@Repository("ErrorStateDaoImpl")
public class ErrorStateDaoImpl extends HibernateDaoSupport implements ErrorStateDao {

	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }
	
	public List getErrorTotalRow(String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		if(startDate !="" && endDate!= ""){
			sql.append("select count (*) from DetectorTriggerLog where Detector_TriggerLog_Time between '" + startDate + "' and '"+ endDate+ "' ");
		} else {
			sql.append("select count (*) from DetectorTriggerLog");
		}
		return getHibernateTemplate().find(sql.toString());
	}

	public List<DetectorTriggerLog> findErrorList(String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		if(startDate !="" && endDate!= ""){
			sql.append("from DetectorTriggerLog where Detector_TriggerLog_Time between '" + startDate + "' and '"+ endDate+ "' ");
		} else {
			sql.append("from DetectorTriggerLog ");
		}
		sql.append("order by Detector_TriggerLog_Time desc ");
		return getHibernateTemplate().find(sql.toString());
	}

	public List<Object> analyseErrorPie(String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		return null;
	}

	public List<Object> analyseErrorLine(String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT de.Detector_Equipment_Name, COUNT(de.Detector_Equipment_Id) ");
//		sql.append("FROM DetectorEquipment de LEFT JOIN DetectorSensor ds WHERE ");
//		sql.append("de.Detector_Equipment_Id =ds.Detector_Equipment_Id ");
//		sql.append("LEFT JOIN (DetectorTriggerLog dt WHERE ");
//		sql.append("dt.Detector_Sensor_Id = ds.Detector_Sensor_Id )");
//		sql.append("AND dt.Detector_TriggerLog_State = 0 and ds.Detector_Sensor_State = 1 ");
//		if(startDate !="" && endDate!= ""){
//			sql.append("dt.Detector_TriggerLog_Time between '" + startDate + "' and '"+ endDate+ "' ");
//		}
//		sql.append(" GROUP BY de.Detector_Equipment_Name ");
//		sql.append("ORDER BY COUNT(de.Detector_Equipment_Id) DESC");
//		return getHibernateTemplate().find(sql.toString());
		return null;
	}

	@SuppressWarnings("unchecked")
	public List selectErrorDetails(String SensorId) {
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT dp.Detector_Project_Name,df.Detector_Facility_Name,de.Detector_Equipment_Name,ds.Detector_Sensor_Name ");
		sql.append("FROM Detector_Sensor ds LEFT JOIN Detector_Equipment de ON ds.Detector_Equipment_Id = de.Detector_Equipment_Id ");
		sql.append("LEFT JOIN Detector_EquipmentRoom der ON der.Detector_EquipmentRoom_Id = de.Detector_EquipmentRoom_Id ");
		sql.append("LEFT JOIN Detector_Project dp ON dp.Detector_Project_Id = der.Detector_Project_Id ");
		sql.append("LEFT JOIN Detector_Facility df ON df.Detector_Facility_Id = der.Detector_Facility_Id ");
		sql.append("WHERE ds.Detector_Sensor_Id = '"+ SensorId+ "' ");
		return (List) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(sql.toString());
				List<Object> list = qObj.list();
				return list;
				}
		});
	}

} 
