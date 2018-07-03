package com.dt.dao;

import java.util.List;

public interface PreMainDao {

	//统计页码
	List<Object> selectEqTotalRow(int project, int facility);

	//设备信息
	List<Object> selectEqInfo(int project, int facility, int offset, int limit);

	//维护基本信息
	List<Object> selectEquipDateById(Integer equipmentId , Integer equipmentTypeId);

	//预测维护时间
	List<Object> findDate(Integer equipmentId);

	//preList
	List<Object> selectEqInfo();
}
