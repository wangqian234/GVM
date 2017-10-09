package com.dt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dt.dao.PreMainDao;
import com.dt.entity.PreInfo;

@Repository("PreMainDaoImpl")
public class PreMainDaoImpl extends HibernateDaoSupport implements PreMainDao{

	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}
	
	//页数
	@SuppressWarnings("unchecked")
	public List<Object> selectEqTotalRow(int project, int facility) {
		// TODO 自动生成的方法存根
		final StringBuilder eq = new StringBuilder();
		eq.append("select count(*) from Detector_Equipment eq ");
		eq.append(" LEFT JOIN Detector_MaintenanceLog mtl ON eq.Detector_Equipment_Id = mtl.Detector_Equipment_Id");
		eq.append(" LEFT JOIN Detector_EquipmentRoom eqr ON eq.Detector_EquipmentRoom_Id = eqr.Detector_EquipmentRoom_Id");
		eq.append(" LEFT JOIN Detector_Facility ft ON eqr.Detector_Facility_Id = ft.Detector_Facility_Id");
		eq.append(" LEFT JOIN Detector_Project pt ON eqr.Detector_Project_Id = pt.Detector_Project_Id");
		eq.append(" where ft.Detector_Facility_Id = " + facility + " and pt.Detector_Project_Id = " + project);

		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(eq.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
	}

	//设备基本信息
	@SuppressWarnings("unchecked")
	public List<Object> selectEqInfo(int project, int facility, int offset, int limit) {
		// TODO 自动生成的方法存根
		final StringBuilder eq = new StringBuilder();
		eq.append("select eq.Detector_Equipment_No,eq.Detector_Equipment_Name,mtl.Detector_MaintenanceLog_Type,eq.Detector_Equipment_Id , eq.Detector_Equipment_State , eq.Detector_EquipmentType_Id ");
		eq.append(" from Detector_Equipment eq  ");
		eq.append(" LEFT JOIN Detector_MaintenanceLog mtl ON eq.Detector_Equipment_Id = mtl.Detector_Equipment_Id ");
		eq.append(" LEFT JOIN Detector_EquipmentRoom eqr ON eq.Detector_EquipmentRoom_Id = eqr.Detector_EquipmentRoom_Id ");
		eq.append(" left join Detector_EquipmentType eqt  on eqt.Detector_EquipmentType_Id = eq.Detector_EquipmentType_Id ");
		eq.append(" LEFT JOIN Detector_Facility ft ON eqr.Detector_Facility_Id = ft.Detector_Facility_Id ");
		eq.append(" LEFT JOIN Detector_Project pt ON eqr.Detector_Project_Id = pt.Detector_Project_Id ");
		eq.append(" where ft.Detector_Facility_Id = " + facility + " and pt.Detector_Project_Id = " + project);
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(eq.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
	}

	//维护基本信息
	@SuppressWarnings("unchecked")
	public List<Object> selectEquipDateById(Integer equipmentId,Integer equipmentTypeId) {
		// TODO Auto-generated method stub
		final StringBuilder str = new StringBuilder();
		str.append("select Detector_MaintenanceLog_StartDate,Detector_MaintenanceLog_EndDate,Detector_PreLog_Content,Detector_PreLog_Tools,Detector_PreLog_Workers");
		str.append(" from Detector_MaintenanceLog mtl");
		str.append(" left join Detector_Equipment eq on eq.Detector_Equipment_Id = mtl.Detector_Equipment_Id");
		str.append(" left join Detector_EquipmentType eqt on eqt.Detector_EquipmentType_Id = eq.Detector_EquipmentType_Id");
		str.append(" where eq.Detector_Equipment_Id= "+equipmentId+" and eq.Detector_EquipmentType_Id = "+equipmentTypeId+" order by Detector_MaintenanceLog_StartDate DESC");
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(str.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
	}

	//preDate
    @SuppressWarnings("unchecked")
	public List<Object> findDate(Integer equipmentId) {
	    // TODO Auto-generated method stub
    	final StringBuilder str = new StringBuilder();
		str.append("select eq.Detector_Equipment_Lifetime,eq.Detector_Equipment_UseDate,mtl.Detector_MaintenanceLog_EndDate");
		str.append(" from Detector_MaintenanceLog mtl");
		str.append(" left join Detector_Equipment eq on eq.Detector_Equipment_Id = mtl.Detector_Equipment_Id");
		str.append(" left join Detector_EquipmentType eqt on eqt.Detector_EquipmentType_Id = eq.Detector_EquipmentType_Id");
		str.append(" where eq.Detector_Equipment_Id= "+equipmentId+" order by Detector_MaintenanceLog_StartDate DESC");
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(str.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
}

    //preList
	@SuppressWarnings("unchecked")
	public List<Object> selectEqInfo() {
		// TODO Auto-generated method stub
		final StringBuilder eq = new StringBuilder();
		eq.append("select eq.Detector_Equipment_No,eq.Detector_Equipment_Name,mtl.Detector_MaintenanceLog_Type,eq.Detector_Equipment_Id , eq.Detector_Equipment_State , eq.Detector_EquipmentType_Id, ");
		eq.append(" dp.Detector_Project_Name from Detector_Equipment eq  ");
		eq.append(" LEFT JOIN Detector_MaintenanceLog mtl ON eq.Detector_Equipment_Id = mtl.Detector_Equipment_Id ");
		eq.append(" LEFT JOIN Detector_EquipmentRoom eqr ON eq.Detector_EquipmentRoom_Id = eqr.Detector_EquipmentRoom_Id ");
		eq.append(" LEFT JOIN Detector_EquipmentType eqt  on eqt.Detector_EquipmentType_Id = eq.Detector_EquipmentType_Id ");
		eq.append(" LEFT JOIN Detector_Project dp  on eqr.Detector_Project_Id = dp.Detector_Project_Id ");
		return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session) throws org.hibernate.HibernateException {
				SQLQuery qObj = session.createSQLQuery(eq.toString());
				List<Object> list = qObj.list();
				return list;
			}
		});
	}
}
