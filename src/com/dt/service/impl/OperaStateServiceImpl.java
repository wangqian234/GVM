package com.dt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.OperaStateDao;
import com.dt.service.OperaStateService;

@Service("OperaStateService")
@Transactional
public class OperaStateServiceImpl implements OperaStateService {

	@Autowired
	private OperaStateDao operaStateDao;
	
	public List<Object> getbaseInfo(String project, String facility) {
		List<Object> list = operaStateDao.getbaseInfo(project, facility);
		return list;
	}

	
}
