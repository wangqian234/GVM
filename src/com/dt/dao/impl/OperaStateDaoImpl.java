package com.dt.dao.impl;

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

}
