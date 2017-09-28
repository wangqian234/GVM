package com.dt.dao;

import java.util.List;

import com.dt.entity.DetectorEquipment;

public interface EvalStateDao {
	//设备查询
	List<Object> findEquipment(Integer detector_Facility_Id);
	//查询分析设备的数据
	List<Object> findEquipments(Integer detector_Equipment_Id);
	List<Object> findTrigglerlogs(Integer detector_Equipment_Id);


}
