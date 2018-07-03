package com.dt.dao.impl;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.dt.dao.EvalStateDao;
import com.dt.entity.DetectorEquipment;
import com.sun.org.apache.bcel.internal.generic.Select;


/**
 * 设备健康状态评估
 * @author ghl
 * @date   2017年9月26日
 */
@Repository("evalStateDaoImpl")
public class EvalStateDaoImpl extends HibernateDaoSupport implements EvalStateDao{

	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }
	//查询设备
	@SuppressWarnings("unchecked")
	public List<Object> findEquipment(Integer project, Integer facility, Integer offset, Integer limit) {
		final StringBuilder sql = new StringBuilder();
		sql.append(" SELECT e.Detector_Equipment_Id,e.Detector_Equipment_No ,er.Detector_EquipmentRoom_name,et.Detector_EquipmentType_name ,e.Detector_Equipment_Name,et.Detector_EquipmentType_Id FROM Detector_Equipment e ");
		sql.append(" LEFT JOIN Detector_EquipmentRoom  er ON er.Detector_EquipmentRoom_Id=e.Detector_EquipmentRoom_Id");
		sql.append(" LEFT JOIN Detector_EquipmentType et ON et.Detector_EquipmentType_Id=e.Detector_EquipmentType_Id");
		sql.append(" LEFT JOIN Detector_Facility f on f.Detector_Facility_Id=et.Detector_Facility_Id ");			
		sql.append(" where er.Detector_Project_Id= " + project+ " and f.Detector_Facility_Id= " + facility );
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(sql.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});		
	}
	//查询总行数
	@SuppressWarnings("unchecked")
	public List<Object> getbaseTotalRow(Integer project, Integer facility) {
		final StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from Detector_Equipment e LEFT JOIN  Detector_EquipmentRoom er ON ");
		sql.append("  e.Detector_EquipmentRoom_Id= er.Detector_EquipmentRoom_Id where er.Detector_Project_Id = " + project);
		sql.append(" and er.Detector_Facility_Id= " + facility);
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>(){
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qobj =session.createSQLQuery(sql.toString());
				List<Object> list = qobj.list();
				return list;
			}
		});	
	}
	//查询分析设备的数据(UseDate,lifeTime)
	@SuppressWarnings("unchecked")
	public List<Object> findEquipments(final Integer detector_Equipment_Id) {
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public List<Object>  doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException, SQLException {
				Integer detectorEquipmentId = detector_Equipment_Id;
				String sql = " select Detector_Equipment.Detector_Equipment_UseDate,Detector_Equipment.Detector_Equipment_Lifetime from Detector_Equipment "
						+ " where Detector_Equipment.Detector_Equipment_Id ="+detectorEquipmentId+"";
				 SQLQuery qobj = session.createSQLQuery(sql);
				 List<Object> list = qobj.list();
				return list;
			}			
		});	
	}
	//查询分析设备的数据(Triggler)
	@SuppressWarnings("unchecked")
	public List<Object> findTrigglerlogs(final Integer detector_Equipment_Id) {
		return (List<Object>)getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public List<Object> doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException, SQLException {
				Integer detectorEquipmentId = detector_Equipment_Id;
				StringBuilder sql = new StringBuilder();
				sql.append(" SELECT COUNT(Detector_TriggerLog.Detector_TriggerLog_Id)  FROM Detector_TriggerLog ");
				sql.append(" LEFT JOIN Detector_Sensor ON Detector_Sensor.Detector_Sensor_Id = Detector_TriggerLog.Detector_Sensor_Id ");
				sql.append(" LEFT JOIN Detector_Equipment  ON Detector_Sensor.Detector_Equipment_Id = Detector_Equipment.Detector_Equipment_Id ");
				sql.append(" WHERE Detector_Equipment.Detector_Equipment_Id = "+detectorEquipmentId+""); 
				SQLQuery qobj =session.createSQLQuery(sql.toString());
				List<Object> list = qobj.list();
				return list;
			}		
		});		
	}


}
