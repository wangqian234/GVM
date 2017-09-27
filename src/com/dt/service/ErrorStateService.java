package com.dt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.ErrorStateDao;

@Service("ErrorStateService")
@Transactional
public interface ErrorStateService {
		
	public List getErrorTotalRow(String startDate, String endDate);
}
