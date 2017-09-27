package com.dt.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dt.dao.ErrorStateDao;

@Repository("ErrorStateDaoImpl")
public class ErrorStateDaoImpl extends HibernateDaoSupport implements ErrorStateDao {

	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    }
	
	public List getErrorTotalRow(String startDate, String endDate) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		if(startDate !="" && endDate!= ""){
			sql.append("select count (*) from DetectorTriggerLog where Detector_TriggerLog_Time between '" + startDate + "' and '"+ endDate+ "' ");
		} else {
			sql.append("select count (*) from DetectorTriggerLog");
		}
		return getHibernateTemplate().find(sql.toString());
	}

}
