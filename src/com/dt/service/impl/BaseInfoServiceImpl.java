package com.dt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.BaseInfoDao;
import com.dt.service.BaseInfoService;

/**
 * 用户service
 */
@Service("BaseInfoService")
@Transactional
public class BaseInfoServiceImpl implements BaseInfoService{

	@Autowired
	private BaseInfoDao baseInfoDao;

	public String selectBaseList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
