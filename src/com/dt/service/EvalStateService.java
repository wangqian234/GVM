package com.dt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.entity.DetectorEquipment;
@Service("EvalStateService")
@Transactional
public interface EvalStateService {
	//设备查询
	List<DetectorEquipment> selectEquipment(Integer detector_Facility_Id);
	//查询分析设备的数据
	String analysisEquipment(Integer detector_Equipment_Id, String failare);
	String findEquipment(Integer detector_Equipment_Id);

	
	

}
