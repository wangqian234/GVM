package com.dt.service;

import java.util.List;

import com.dt.entity.DetectorEquipment;
import com.dt.entity.EquipmentInfo;

public interface PreMainService {

	List<EquipmentInfo> selectEquipList();
	
	List<DetectorEquipment> analyzeList(Integer preid);

	

}
