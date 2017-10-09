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
import com.dt.util.StringUtil;

import net.sf.json.JSONObject;

@Service("OperaStateService")
@Transactional
public class OperaStateServiceImpl implements OperaStateService {

	@Autowired
	private OperaStateDao operaStateDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getbaseInfo(String project, String facility) {
		List<Object> list = operaStateDao.getbaseInfo(project, facility);
		Object[] obj = null;
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		List<Map<String, String>> listNum = new ArrayList<Map<String, String>>();// 数值型传感器
		List<Map<String, String>> listOth = new ArrayList<Map<String, String>>();// 开关型传感器
		Iterator<Object> it = list.iterator();

		if (it.hasNext()) {
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
			map1.put("Detector_SensorData_Switch", obj[8].toString());
			map1.put("Detector_SensorData_Latitude", obj[9].toString());
			map1.put("Detector_SensorData_Latitude", obj[10].toString());
			map1.put("Detector_SensorData_Time", obj[11].toString());
			map1.put("Detector_Sensor_Unit", obj[12].toString());
			map1.put("mark", "0");
			map1.put("lastNumber", obj[5].toString());
			listmap.add(map1);
		}

		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			obj = (Object[]) it.next();
			String aa = listmap.get(listmap.size() - 1).get("Detector_Sensor_Id");
			String bb = obj[7].toString();
			if (!aa.equals(bb)) {
				map.put("Detector_EquipmentRoom_Name", obj[0].toString());
				map.put("Detector_Equipment_Name", obj[1].toString());
				map.put("Detector_Sensor_AlarmValueMin", obj[2].toString());
				map.put("Detector_Sensor_AlarmValueMax", obj[3].toString());
				map.put("Detector_Sensor_Name", obj[4].toString());
				map.put("Detector_SensorData_Value", obj[5].toString());
				map.put("Detector_SensorData_Type", obj[6].toString());
				map.put("Detector_Sensor_Id", obj[7].toString());
				map.put("Detector_SensorData_Switch", obj[8].toString());
				map.put("Detector_SensorData_Latitude", obj[9].toString());
				map.put("Detector_SensorData_Latitude", obj[10].toString());
				map.put("Detector_SensorData_Time", obj[11].toString());
				map.put("Detector_Sensor_Unit", obj[12].toString());
				map.put("mark", "0");
				map.put("lastNumber", obj[5].toString());
				listmap.add(map);
			} else {
				if (listmap.get(listmap.size() - 1).get("mark") == "0") {
					listmap.get(listmap.size() - 1).put("lastNumber", obj[5].toString());
					listmap.get(listmap.size() - 1).put("mark", "1");
				}

			}
		}

		for (int i = 0; i < listmap.size(); i++) {
			if (listmap.get(i).get("Detector_SensorData_Type").equals("1")) {
				float num = Float.parseFloat(listmap.get(i).get("Detector_SensorData_Value"))
						- Float.parseFloat(listmap.get(i).get("lastNumber"));
				String result="";
				String str="";
				if (num > 0) {
					str = String.valueOf(StringUtil.save2Float(num));
					result="比上次数值高出  "+str;
				} else if (num < 0) {
					str=String.valueOf(num).substring(1);
					result="比上次数值低了  "+String.valueOf(StringUtil.save2Float(Float.parseFloat(str)));
				} else {
					result="与上次数值相等";
				}
				listmap.get(i).put("analyseResult", result);
				listNum.add(listmap.get(i));
			} else {
				listOth.add(listmap.get(i));
			}
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", listNum);
		jsonObject.put("listOth", listOth);

		return jsonObject.toString();
	}

	public List<Map<String, String>> getOperaDetails(String sensorId) {
		List<Object> list = operaStateDao.getOperaDetails(sensorId);
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		Iterator<Object> it = list.iterator();
		Object[] obj = null;

		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			obj = (Object[]) it.next();
			map.put("Detector_SensorData_Time", obj[0].toString());
			map.put("Detector_SensorData_Value", obj[1].toString());
			listmap.add(map);
		}

		return listmap;
	}

	public List<Object> getTypeTotalRow(String project, String facility,Integer type) {
		// TODO Auto-generated method stub
		List<Object> totalRow=operaStateDao.getTypeTotalRow(project,facility,type);
		return totalRow;
	}

}
