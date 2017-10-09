package com.dt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dt.dao.PreMainDao;
import com.dt.entity.PreInfo;
import com.dt.service.PreMainService;

@Service("PreMainService")
@Transactional
public class PreMainServiceImpl implements PreMainService{

	@Autowired
	private PreMainDao preMainDao;
	
	//查询页码
	public List<Object> selectEqTotalRow(String project, String facility) {
		// TODO 自动生成的方法存根
		List<Object> list = preMainDao.selectEqTotalRow(Integer.parseInt(project), Integer.parseInt(facility));
		return list;
	}

	//不同系统的设备基本信息
	public List<PreInfo> selectEqInfo(String project, String facility, int offset, int limit) {
		// TODO 自动生成的方法存根
		List<Object> listSource = preMainDao.selectEqInfo(Integer.parseInt(project), Integer.parseInt(facility),offset, limit);
		List<PreInfo> listGoal = new ArrayList<PreInfo>();
		for (int i = offset; i < limit + offset && i < listSource.size(); i++) {
			PreInfo preInfo = new PreInfo();
			Object[] obj = (Object[]) listSource.get(i);
			preInfo.setEquipment_No(obj[0].toString());
			preInfo.setEquipment_Name(obj[1].toString());
			preInfo.setMaintenanceLog_Type(Integer.parseInt(obj[2].toString()));
			preInfo.setDetector_Equipment_Id(Integer.parseInt(obj[3].toString()));
			preInfo.setEquipment_State(Integer.parseInt(obj[4].toString()));
			preInfo.setEquipmentType_Id(Integer.parseInt(obj[5].toString()));
			listGoal.add(preInfo);
		}
		return listGoal;
	}
	
	//获取分析信息 故障日期、维护日期等
	public PreInfo selectEquipDateById(Integer equipmentId , Integer equipmentTypeId) throws ParseException {
		// TODO Auto-generated method stub
		List<Object> listSource = preMainDao.selectEquipDateById(equipmentId,equipmentTypeId);
		PreInfo preInfo = new PreInfo();
		if(listSource.size()!=0){
			Object[] obj=(Object[])listSource.get(0);
			preInfo.setMaintenanceLog_StartDate(obj[0].toString());
			preInfo.setMaintenanceLog_EndDate(obj[1].toString());
			preInfo.setPreLog_Content(obj[2].toString());
			preInfo.setPreLog_Tools(obj[3].toString());
			preInfo.setPreLog_Workers(obj[4].toString());
		}
		List<Object> list = preMainDao.findDate(equipmentId);
		Object[] o=(Object[])list.get(0);
		int lifeTime = Integer.parseInt(o[0].toString());
		int month,monthyear,monthday;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date getDate = new Date();
		String date = sdf.format(getDate);
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		try{
			bef.setTime(sdf.parse(date));
			aft.setTime(sdf.parse(o[1].toString()));//useDate
		}catch(ParseException e){
			e.printStackTrace();
		}
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
		int n = 12*(1-useTime/lifeTime);
		Date repairsDate = sdf.parse(o[2].toString());//enddate
		aft.setTime(repairsDate);//now
		aft.add(Calendar.MONTH, n);
		String preDate = sdf.format(aft.getTime());
		preInfo.setPreDate(preDate);//封装入实体
		return preInfo;
	}

	
	//preList
	public List<PreInfo> selectEqInfo() {
		// TODO Auto-generated method stub
		List<Object> listSource = preMainDao.selectEqInfo();
		List<PreInfo> listGoal = new ArrayList<PreInfo>();
		for (int i = 1;i < listSource.size(); i++) {
			PreInfo preInfo = new PreInfo();
			Object[] obj = (Object[]) listSource.get(i);
			preInfo.setDetector_Equipment_Id(Integer.parseInt(obj[3].toString()));
			preInfo.setEquipmentType_Id(Integer.parseInt(obj[5].toString()));
			preInfo.setEquipment_Name(obj[1].toString());
			preInfo.setProject(obj[6].toString());
			listGoal.add(preInfo);
		}
		return listGoal;
	}

	public String  selectEquipDateById(int equipmentId) {
		// TODO Auto-generated method stub
		List<Object> list = preMainDao.findDate(equipmentId);
		Object[] o=(Object[])list.get(0);
		int lifeTime = Integer.parseInt(o[0].toString());
		int month,monthyear,monthday;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date getDate = new Date();
		String date = sdf.format(getDate);
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		try{
			bef.setTime(sdf.parse(date));
			aft.setTime(sdf.parse(o[1].toString()));//useDate
		}catch(ParseException e){
			e.printStackTrace();
		}
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
		int n = 12*(1-useTime/lifeTime);
		Date repairsDate = null;
		try {
			repairsDate = sdf.parse(o[2].toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aft.setTime(repairsDate);//now
		aft.add(Calendar.MONTH, n);
		String preDate = sdf.format(aft.getTime());
		
		return preDate;
	}

}
