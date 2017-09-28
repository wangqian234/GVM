package com.dt.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.OperaStateDao;
import com.dt.service.OperaStateService;

@Service("OperaStateService")
@Transactional
public class OperaStateServiceImpl implements OperaStateService {

	@Autowired
	private OperaStateDao operaStateDao;
	
	public List<Object> getbaseInfo(String project, String facility) {
		List<Object> list = operaStateDao.getbaseInfo(project, facility);
		Object[] obj = null;
		List<Map<String, String>> listmap = null;
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			obj = (Object[]) it.next();
			map.put("Detector_EquipmentRoom_Name", obj[0].toString());
			map.put("Detector_Equipment_Name", obj[1].toString());
			map.put("Detector_Sensor_Name", obj[2].toString());
			map.put("Detector_Sensor_AlarmValueMin", obj[3].toString());
			map.put("Detector_Sensor_AlarmValueMax", obj[4].toString());
			map.put("Detector_SensorData_Value", obj[5].toString());
			map.put("Detector_SensorData_Type", obj[6].toString());
			map.put("Detector_Sensor_Id", obj[7].toString());
			listmap.add(map);
		}
		
		return list;
	}

	
}
