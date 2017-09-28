package com.dt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.dt.dao.BaseInfoDao;
import com.dt.entity.EquipmentInfo;

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
				StringBuilder sb = new StringBuilder();
				sb.append(
						"select e.Detector_Equipment_Id,e.Detector_Equipment_No,e.Detector_Equipment_Name,er.Detector_EquipmentRoom_name,et.Detector_EquipmentType_name from Detector_Equipment e");
				sb.append(
						" LEFT JOIN Detector_EquipmentRoom er ON e.Detector_EquipmentRoom_Id=er.Detector_EquipmentRoom_Id");
				sb.append(
						" LEFT JOIN Detector_EquipmentType et ON e.Detector_EquipmentType_Id=et.Detector_EquipmentType_Id");
				SQLQuery qObj = session.createSQLQuery(sb.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});

	}

	@SuppressWarnings("unchecked")
	public List<Object> getbaseTotalRow(Integer project, Integer facility) {
		// TODO Auto-generated method stub
		final StringBuilder sb = new StringBuilder();
		sb.append("select count(*) from Detector_Equipment e LEFT JOIN  Detector_EquipmentRoom er ON ");
		sb.append(" e.Detector_EquipmentRoom_Id=er.Detector_EquipmentRoom_Id where er.Detector_Project_Id= " + project
				+ " ");
		sb.append(" and er.Detector_Facility_Id= " + facility);

		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(sb.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object> findBaseList(Integer project, Integer facility,Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		final StringBuilder sb = new StringBuilder();
		sb.append(
				"select e.Detector_Equipment_Id,e.Detector_Equipment_No,e.Detector_Equipment_Name,er.Detector_EquipmentRoom_name,et.Detector_EquipmentType_name from Detector_Equipment e");
		sb.append(
				" LEFT JOIN Detector_EquipmentRoom er ON e.Detector_EquipmentRoom_Id=er.Detector_EquipmentRoom_Id");
		sb.append(
				" LEFT JOIN Detector_EquipmentType et ON e.Detector_EquipmentType_Id=et.Detector_EquipmentType_Id");
		sb.append(" where er.Detector_Project_Id= " + project+ " and er.Detector_Facility_Id= " + facility );
		
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(sb.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Object> selectEquipmentById(Integer equipmentId) {
		// TODO Auto-generated method stub
		
		final StringBuilder sb = new StringBuilder();
		sb.append(
				"select e.Detector_Equipment_Id,e.Detector_Equipment_No,e.Detector_Equipment_Name,e.Detector_Equipment_State,");
		sb.append("  et.Detector_EquipmentType_name,er.Detector_EquipmentRoom_name,e.Detector_Equipment_Version,Detector_Equipment_Price,");
		sb.append("  e.Detector_Equipment_TP,Detector_Equipment_Manufacturers,e.Detector_Equipment_ProductionDate,e.Detector_Equipment_UseDate,");
		sb.append("  e.Detector_Equipment_MaintenancePeriod,e.Detector_Equipment_RepairsDate,e.Detector_Equipment_Tel,e.Detector_Equipment_Mobile,");
		sb.append("  e.Detector_Equipment_FactoryNo,e.Detector_Equipment_Lifetime,e.Detector_Equipment_Director,e.Detector_Equipment_UserID,e.Detector_Equipment_AddDate,");
		sb.append("  e.Detector_Equipment_GUID,e.Detector_Equipment_Memo from Detector_Equipment e");
		sb.append(
				"  LEFT JOIN Detector_EquipmentRoom er ON e.Detector_EquipmentRoom_Id=er.Detector_EquipmentRoom_Id");
		sb.append(
				"  LEFT JOIN Detector_EquipmentType et ON e.Detector_EquipmentType_Id=et.Detector_EquipmentType_Id");
		sb.append("  where e.Detector_Equipment_Id= " + equipmentId );
		
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(sb.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
	}

}
