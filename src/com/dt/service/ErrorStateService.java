package com.dt.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.entity.DetectorTriggerLog;

@Service("ErrorStateService")
@Transactional
public interface ErrorStateService {
		
	public List getErrorTotalRow(String startDate, String endDate, String state,String type);
	public List<DetectorTriggerLog> findErrorList(String startDate, String endDate, Integer offset, Integer limit, String state,String type);
	public List<List<Map<String, String>>> analyseErrorPie(String startDate, String endDate);
	public List<Map<String, String>> analyseErrorLine(String startDate, String endDate);
	public Map<String, String> selectErrorDetails(String SensorId);
}
