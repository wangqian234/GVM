package com.dt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.ErrorStateDao;

@Service("ErrorStateService")
@Transactional
public class ErrorStateService {
	
	@Autowired
	private ErrorStateDao errorStateDao;
	
	public List getErrorTotalRow(String startDate, String endDate){
		List totalRow = errorStateDao.getErrorTotalRow(startDate, endDate);
		return totalRow;
		
	}
}
