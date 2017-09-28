package com.dt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.dt.dao.PreMainDao;
import com.dt.service.PreMainService;

@Service("PreMainService")
@Transactional
public class PreMainServiceImpl implements PreMainService{
	@Autowired
	private PreMainDao preMainDao;
	
	//设备list
	public List<Map<String, String>> selectEquipList() {
		// TODO 自动生成的方法存根
		List<Object> list = preMainDao.selectEquipList();
		Object[] obj = null;
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		Iterator<Object> it = list.iterator();
		System.out.println("zq1"+list.toString());
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			obj = (Object[]) it.next();
			System.out.println("zq"+obj.toString());
			map.put("Detector_Equipment_Name", obj[0].toString());
			map.put("Detector_MaintenanceLog_EndDate", obj[1].toString());
			map.put("Detector_TriggerLog_Time", obj[2].toString());
			map.put("Detector_MaintenanceLog_Content", obj[3].toString());
			map.put("Detector_Equipment_Lifetime", obj[4].toString());
			map.put("Detector_Equipment_UseDate",obj[5].toString());
			listmap.add(map);
		}
		return listmap;
	}

	public List<Map<String, String>> analyzeList() throws ParseException {
		// TODO 自动生成的方法存根
		List<Object> list = preMainDao.analyzeList();
		Object[] obj = null;
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		Iterator<Object> it = list.iterator();
		if(it.hasNext()){
			obj = (Object[]) it.next();
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("Detector_MaintenanceLog_EndDate",obj[0].toString());
			map1.put("Detector_TriggerLog_Time",obj[1].toString());
			map1.put("Detector_Equipment_Lifetime",obj[2].toString());
			map1.put("Detector_Equipment_UseDate",obj[3].toString());
			listmap.add(map1);
		}
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			obj = (Object[]) it.next();
			map.put("Detector_MaintenanceLog_EndDate", obj[0].toString());
			map.put("Detector_TriggerLog_Time", obj[1].toString());
			map.put("Detector_Equipment_Lifetime", obj[2].toString());
			map.put("Detector_Equipment_UseDate",obj[3].toString());
			listmap.add(map);
			JSONObject jsonObject = new JSONObject();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date getDate = new Date();
			String date = sdf.format(getDate);
			Calendar bef = Calendar.getInstance();
			Calendar aft = Calendar.getInstance();
			try{
				bef.setTime(sdf.parse(date));
				aft.setTime(sdf.parse(obj[4].toString()));
			}catch(ParseException e){
				e.printStackTrace();
			}
			int month,monthyear,monthday;
			if(aft.get(Calendar.MONTH)<bef.get(Calendar.MONTH)){
				month = bef.get(Calendar.MONTH)-aft.get(Calendar.MONTH);
			}else{
				month = aft.get(Calendar.MONTH)-bef.get(Calendar.MONTH);
			}
			if(aft.get(Calendar.YEAR)<bef.get(Calendar.YEAR)){
				monthyear = (bef.get(Calendar.YEAR)-aft.get(Calendar.YEAR))*12;
			}else{
				monthyear = (aft.get(Calendar.YEAR)-bef.get(Calendar.YEAR))*12;
			}
			if(aft.get(Calendar.DATE)<bef.get(Calendar.DATE)){
				monthday = (bef.get(Calendar.DATE)-aft.get(Calendar.DATE))/30;
			}else{
				monthday = (aft.get(Calendar.DATE)-bef.get(Calendar.DATE))/30;
			}
			int useTime = month + monthyear + monthday;
			int lifeTime = Integer.getInteger((obj[4].toString()));
			Date repairsDate = sdf.parse(obj[0].toString());
			Date troublesDate = sdf.parse(obj[1].toString());
			String preDate;
			int n = 12*(1-useTime/lifeTime);
			if(repairsDate.after(troublesDate)){
				aft.setTime(repairsDate);
			}else{
				aft.setTime(troublesDate);
			}
			aft.add(Calendar.MONTH, n);
			preDate = sdf.format(aft.getTime());//如何填入表中
		}
		
		return listmap;
	}
}
