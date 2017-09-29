package com.dt.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
	
	//设备list
	public List<PreInfo> selectEquipList() {
		// TODO 自动生成的方法存根
		List<Object> listSource = preMainDao.selectEquipList();
		Iterator<Object> it = listSource.iterator();
		List<PreInfo> listInfo = objToPreInfo(it);
		return listInfo;
	}
	@SuppressWarnings("unused")
	private List<PreInfo> objToPreInfo(Iterator<Object> it) {
		Object[] obj = null;
		PreInfo preInfo = null;
		List<PreInfo> listGoal = new ArrayList<PreInfo>();
		int i = 0;
		while (it.hasNext()) {
			i++;
			obj = (Object[]) it.next();
			preInfo = new PreInfo();
			if(obj[0] != null){
				preInfo.setEquipment_Name(obj[0].toString());
			}
			if(obj[1] != null){
				preInfo.setMaintenanceLog_EndDate(obj[1].toString());
			}
			if(obj[2] != null){
				preInfo.setTriggerLog_Time(obj[2].toString());
			}
			if(obj[3] != null){
				preInfo.setMaintenanceLog_Content(obj[3].toString());
			}
			if(obj[4] != null){
				preInfo.setEquipment_UseDate(obj[4].toString());
			}//useDate使用日期
			if(obj[5] != null){
				preInfo.setEquipment_Lifetime(obj[5].toString());
			}//lifeTime设备标准寿命
			listGoal.add(preInfo);
		}
		return listGoal;
	}
	public String analyzeList(Integer preId, Object[] obj) throws ParseException {
		// TODO Auto-generated method stub
		
		//如何找到和preId一致的数据
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date getDate = new Date();
		String date = sdf.format(getDate);
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(sdf.parse(date));
		aft.setTime(sdf.parse(obj[4].toString()));
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
		int lifeTime = Integer.getInteger((obj[5].toString()));
		Date repairsDate = sdf.parse(obj[1].toString());
		Date troublesDate = sdf.parse(obj[2].toString());
		int n = 12*(1-useTime/lifeTime);
		if(repairsDate.after(troublesDate)){
			aft.setTime(repairsDate);
		}else{
			aft.setTime(troublesDate);
		}
		aft.add(Calendar.MONTH, n);
		String preDate = sdf.format(aft.getTime());
		return preDate;
	}

	/*
			
			
			
			
			
			
			
			
			
			//如何填入表中
		}
	}
		return listmap;
		}*/
}
