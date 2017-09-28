package com.dt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dt.dao.OperaStateDao;
@Repository("OperaStateDaoImpl")
public class OperaStateDaoImpl extends HibernateDaoSupport implements OperaStateDao {
	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }

	@SuppressWarnings("unchecked")
	public List<Object> getbaseInfo(String project, String facility) {
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT der.Detector_EquipmentRoom_Name, de.Detector_Equipment_Name, ");
		sql.append("ds.Detector_Sensor_AlarmValueMin,ds.Detector_Sensor_AlarmValueMax, ");
		sql.append("ds.Detector_Sensor_Name, dsd.Detector_SensorData_Value, ds.Detector_Sensor_Type, ds.Detector_Sensor_Id ");
		sql.append("FROM Detector_SensorData dsd LEFT JOIN ");
		sql.append("Detector_Sensor ds ON  dsd.Detector_Sensor_Id = ");
		sql.append("ds.Detector_Sensor_Id LEFT JOIN Detector_Equipment de ON ");
		sql.append("de.Detector_Equipment_Id = ds.Detector_Equipment_Id LEFT JOIN ");
		sql.append("Detector_EquipmentRoom der ON de.Detector_EquipmentRoom_Id = ");
		sql.append("der.Detector_EquipmentRoom_Id LEFT JOIN Detector_Facility df ON ");
		sql.append("df.Detector_Facility_Id = der.Detector_Facility_Id LEFT JOIN ");
		sql.append("Detector_Project dp ON dp.Detector_Project_Id = ");
		sql.append("der.Detector_Project_Id WHERE df.Detector_Facility_Id = '"+facility+"'");
		sql.append(" AND dp.Detector_Project_Id = '"+project+"'");
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(sql.toString());
				List<Object> list = qObj.list();
				return list;
				}
		});
	}
}


