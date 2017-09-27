package com.dt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dt.dao.OperaStateDao;
@Repository("OperaStateDaoImpl")
public class OperaStateDaoImpl extends HibernateDaoSupport implements OperaStateDao {
	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }

	public List<Object> getbaseInfo(String project, String facility) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT [gywygl].[dbo].[Detector_EquipmentRoom].[Detector_EquipmentRoom_Name],[gywygl].[dbo].[Detector_Equipment].[Detector_Equipment_Name], ");
		sql.append("[gywygl].[dbo].[Detector_Sensor].[Detector_Sensor_Name],[gywygl].[dbo].[Detector_SensorData].[Detector_SensorData_Value], ");
		sql.append("[gywygl].[dbo].[Detector_SensorData].[Detector_SensorData_Time],[gywygl].[dbo].[Detector_Sensor].[Detector_Sensor_Type] FROM  ");
		sql.append("[gywygl].[dbo].[Detector_SensorData] LEFT JOIN ");
		sql.append("[gywygl].[dbo].[Detector_Sensor] ON  [gywygl].[dbo].[Detector_SensorData].[Detector_Sensor_Id] = ");
		sql.append("[gywygl].[dbo].[Detector_Sensor].[Detector_Sensor_Id] LEFT JOIN [gywygl].[dbo].[Detector_Equipment] ON ");
		sql.append("[gywygl].[dbo].[Detector_Equipment].[Detector_Equipment_Id] = [gywygl].[dbo].[Detector_Sensor].[Detector_Equipment_Id] LEFT JOIN");
		sql.append("[gywygl].[dbo].[Detector_EquipmentRoom] on [gywygl].[dbo].[Detector_Equipment].[Detector_EquipmentRoom_Id] = ");
		sql.append("[gywygl].[dbo].[Detector_EquipmentRoom].[Detector_EquipmentRoom_Id] LEFT JOIN [gywygl].[dbo].[Detector_Facility] ON ");
		sql.append("[gywygl].[dbo].[Detector_Facility].[Detector_Facility_Id] = [gywygl].[dbo].[Detector_EquipmentRoom].[Detector_Facility_Id] LEFT JOIN");
		sql.append("[gywygl].[dbo].[Detector_Project] ON [gywygl].[dbo].[Detector_Project].[Detector_Project_Id] = ");
		sql.append("[gywygl].[dbo].[Detector_EquipmentRoom].[Detector_Project_Id] WHERE [gywygl].[dbo].[Detector_Facility].[Detector_Facility_Id] = '"+facility+"'");
		sql.append(" AND [gywygl].[dbo].[Detector_Project].[Detector_Project_Id] = '"+project+"'");
		return getHibernateTemplate().find(sql.toString());
	}

}
