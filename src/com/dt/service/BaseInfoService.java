package com.dt.service;

import java.util.List;

import com.dt.entity.EquipmentInfo;

public interface BaseInfoService {

	List<EquipmentInfo> selectBaseList();

	List<Object> getbaseTotalRow(String project, String facility);

	List<EquipmentInfo> findBaseList(String project, String facility, Integer offset, Integer limit);
	
	EquipmentInfo selectEquipmentById(Integer equipmentId);
}