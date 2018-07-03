package com.dt.dao;

import java.util.List;


public interface EvalStateDao {
	//设备查询
	List<Object> findEquipment(Integer project, Integer facility, Integer offset, Integer limit);
	//查询分析设备的数据
	List<Object> findEquipments(Integer detector_Equipment_Id);
	List<Object> findTrigglerlogs(Integer detector_Equipment_Id);
	//查询列表总行数
	List<Object> getbaseTotalRow(Integer project, Integer facility);
	


}
