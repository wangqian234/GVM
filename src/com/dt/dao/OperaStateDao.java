package com.dt.dao;

import java.util.List;

public interface OperaStateDao {

	public List<Object> getbaseInfo(String project, String facility);
	public List<Object> getOperaDetails(String sensorId);
	
}
