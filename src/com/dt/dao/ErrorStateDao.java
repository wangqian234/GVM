package com.dt.dao;

import java.util.List;

import com.dt.entity.DetectorTriggerLog;

public interface ErrorStateDao {

	public List getErrorTotalRow(String startDate, String endDate);
	public List<DetectorTriggerLog> findErrorList(String startDate, String endDate);
	public List<Object> analyseErrorPie(String startDate, String endDate);
	public List<Object> analyseErrorLine(String startDate, String endDate);
	public List<Object> selectErrorDetails(String SensorId);
}
