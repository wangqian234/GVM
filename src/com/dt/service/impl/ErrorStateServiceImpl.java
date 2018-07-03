package com.dt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.ErrorStateDao;
import com.dt.entity.DetectorTriggerLog;
import com.dt.service.ErrorStateService;

import javassist.expr.NewArray;

@Service("ErrorStateService")
@Transactional
public class ErrorStateServiceImpl implements ErrorStateService{
	
	@Autowired
	private ErrorStateDao errorStateDao;
	
	@SuppressWarnings("rawtypes")
	public List getErrorTotalRow(String startDate, String endDate, String state,String type){
		List totalRow = errorStateDao.getErrorTotalRow(startDate, endDate, state,type);
		return totalRow;
		
	}

	public List<DetectorTriggerLog> findErrorList(String startDate, String endDate, Integer offset, Integer limit, String state,String type) {
		List<DetectorTriggerLog> temp = errorStateDao.findErrorList(startDate, endDate, state,type);
		List<DetectorTriggerLog> list = new ArrayList<DetectorTriggerLog>();
		for(int i = offset; i<limit+offset&&i<temp.size(); i++){
			DetectorTriggerLog listtemp = new DetectorTriggerLog();
			listtemp.setDetector_TriggerLog_Id(temp.get(i).getDetector_TriggerLog_Id());
			listtemp.setDetector_Sensor_Id(temp.get(i).getDetector_Sensor_Id());
			listtemp.setDetector_Trigger(temp.get(i).getDetector_Trigger());
			listtemp.setDetector_Trigger_AlarmMode(temp.get(i).getDetector_Trigger_AlarmMode());
			listtemp.setDetector_Trigger_AlarmUser(temp.get(i).getDetector_Trigger_AlarmUser());
			listtemp.setDetector_TriggerLog_RemoveTime(temp.get(i).getDetector_TriggerLog_RemoveTime());
			listtemp.setDetector_TriggerLog_State(temp.get(i).getDetector_TriggerLog_State());
			listtemp.setDetector_TriggerLog_Time(temp.get(i).getDetector_TriggerLog_Time());
			listtemp.setDetector_TriggerLog_Memo(temp.get(i).getDetector_TriggerLog_Memo());
			list.add(listtemp);
		}
		return list;
	}

	public List<List<Map<String, String>>> analyseErrorPie(String startDate, String endDate) {
		List<Object> temp = errorStateDao.analyseErrorPie(startDate, endDate);
		return null;
	}

	public List<Map<String, String>> analyseErrorLine(String startDate, String endDate) {
		List<Object> temp = errorStateDao.analyseErrorLine(startDate, endDate);
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> selectErrorDetails(String SensorId) {
		List temp = errorStateDao.selectErrorDetails(SensorId);
		Iterator<Object> it = temp.iterator();
		Object[] obj = null;
		obj = (Object[]) it.next();
		Map<String, String> map = new HashMap();
		map.put("Detector_Project_Name", String.valueOf(obj[0]));
		map.put("Detector_Facility_Name", String.valueOf(obj[1]));
		map.put("Detector_Equipment_Name", String.valueOf(obj[2]));
		map.put("Detector_Sensor_Name", String.valueOf(obj[3]));
		return map;
	}

}
