package com.dt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.ErrorStateDao;
import com.dt.service.ErrorStateService;

@Service("ErrorStateService")
@Transactional
public class ErrorStateServiceImpl implements ErrorStateService{
	
	@Autowired
	private ErrorStateDao errorStateDao;
	
	public List getErrorTotalRow(String startDate, String endDate){
		List totalRow = errorStateDao.getErrorTotalRow(startDate, endDate);
		return totalRow;
		
	}

}
