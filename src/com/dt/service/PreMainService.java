package com.dt.service;

import java.text.ParseException;
import java.util.List;

import com.dt.entity.PreInfo;

public interface PreMainService {

	//统计页码
	List<Object> selectEqTotalRow(String project, String facility);

	//不同系统的设备信息
	List<PreInfo> selectEqInfo(String project, String facility, int offset, int limit);

	//预测信息
	PreInfo selectEquipDateById(Integer equipmentId,Integer equipmentTypeId) throws ParseException;

	//预测维护时间list
	List<PreInfo> selectEqInfo();
	String selectEquipDateById(int equipmentId);

}
