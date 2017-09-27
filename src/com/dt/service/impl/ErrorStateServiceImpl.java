package com.dt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.ErrorStateDao;
import com.dt.entity.DetectorTriggerLog;
import com.dt.service.ErrorStateService;

@Service("ErrorStateService")
@Transactional
public class ErrorStateServiceImpl implements ErrorStateService{
	
	@Autowired
	private ErrorStateDao errorStateDao;
	
	@SuppressWarnings("rawtypes")
	public List getErrorTotalRow(String startDate, String endDate){
		List totalRow = errorStateDao.getErrorTotalRow(startDate, endDate);
		return totalRow;
		
	}

	public List<DetectorTriggerLog> findErrorList(String startDate, String endDate, Integer offset, Integer limit) {
		List<DetectorTriggerLog> temp = errorStateDao.findErrorList(startDate, endDate);
		List<DetectorTriggerLog> list = new ArrayList<DetectorTriggerLog>();
		for(int i = offset; i<limit; i++){
			DetectorTriggerLog listtemp = new DetectorTriggerLog();
			listtemp.setDetector_TriggerLog_Id(temp.get(i).getDetector_TriggerLog_Id());
			listtemp.setDetector_Sensor(temp.get(i).getDetector_Sensor());
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

}
