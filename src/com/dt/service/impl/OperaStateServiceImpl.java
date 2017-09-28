package com.dt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, String>> getbaseInfo(String project, String facility) {
		List<Object> list = operaStateDao.getbaseInfo(project, facility);
		Object[] obj = null;
//		List<List<Map<String, String>>> listGoal =  new ArrayList<List<Map<String, String>>>();
//		List<Map<String, String>> listGoalMap =  new ArrayList<Map<String, String>>();
//		List<Map<String, String>> temp = new ArrayList<Map<String, String>>();
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		Iterator<Object> it = list.iterator();

		if(it.hasNext()){
			obj = (Object[]) it.next();
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("Detector_EquipmentRoom_Name", obj[0].toString());
			map1.put("Detector_Equipment_Name", obj[1].toString());
			map1.put("Detector_Sensor_AlarmValueMin", obj[2].toString());
			map1.put("Detector_Sensor_AlarmValueMax", obj[3].toString());
			map1.put("Detector_Sensor_Name", obj[4].toString());
			map1.put("Detector_SensorData_Value", obj[5].toString());
			map1.put("Detector_SensorData_Type", obj[6].toString());
			map1.put("Detector_Sensor_Id", obj[7].toString());
			map1.put("Detector_SensorData_Time", obj[8].toString());
			listmap.add(map1);
		}
			
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			obj = (Object[]) it.next();
			String aa = listmap.get(listmap.size()-1).get("Detector_Sensor_Id");
			String bb = obj[7].toString();
			if(!aa.equals(bb)){
				map.put("Detector_EquipmentRoom_Name", obj[0].toString());
				map.put("Detector_Equipment_Name", obj[1].toString());
				map.put("Detector_Sensor_AlarmValueMin", obj[2].toString());
				map.put("Detector_Sensor_AlarmValueMax", obj[3].toString());
				map.put("Detector_Sensor_Name", obj[4].toString());
				map.put("Detector_SensorData_Value", obj[5].toString());
				map.put("Detector_SensorData_Type", obj[6].toString());
				map.put("Detector_Sensor_Id", obj[7].toString());
				map.put("Detector_SensorData_Time", obj[8].toString());
				listmap.add(map);
			}
		}
		
		return listmap;
	}

	
}
