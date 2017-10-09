package com.dt.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("OperaStateService")
@Transactional
public interface OperaStateService {

	public String getbaseInfo(String project, String facility);

	public List<Map<String, String>> getOperaDetails(String sensorId);

	List<Object> getTypeTotalRow(String project, String facility,Integer type);
}
