package com.dt.dao.impl;



import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dt.dao.BaseInfoDao;


@Repository("BaseInfoDaoImpl")
public class BaseInfoDaoImpl extends HibernateDaoSupport implements BaseInfoDao {
	
	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

}
